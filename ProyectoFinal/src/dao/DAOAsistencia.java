package dao;

import java.util.List;
import modelo.Asistencia;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DAOAsistencia implements DAOCrud<Asistencia> {

    private static LocalDateTime fechaLocal = LocalDateTime.now();
    private static int year = fechaLocal.getYear();
    private static int mes = fechaLocal.getMonthValue();
    private static int dia = fechaLocal.getDayOfMonth();
    private static String fecha = year + "-" + mes + "-" + dia;
    private static final String CONSULTAR_ASISTENCIAS = "SELECT * FROM tbl_asistencia;";
    private static final String CARGAR_DATOS = "SELECT * FROM tbl_asistencia, tbl_docente, tbl_estudiante "
            + "WHERE doc_asis = ? AND doc_asis = id_doc AND fecha = ? AND est_asis = %s AND est_asis = id_estudiante;";
    private static final String CARGAR_ASIS_EST = "SELECT * FROM tbl_asistencia, tbl_docente, tbl_estudiante "
            + "WHERE doc_asis = ? AND doc_asis = id_doc AND est_asis = %s AND est_asis = id_estudiante;";
    private static final String AGREGAR = "INSERT INTO tbl_asistencia(fecha, asistir, doc_asis, est_asis) VALUES(?, ?, ?, ?)";
    private static final String CONSULTAR_ASIS_DIA = "SELECT * FROM tbl_asistencia, tbl_docente WHERE fecha = ? AND doc_asis = ? %s;";
    private static final String MARCAR_ASISTENCIAS = "UPDATE tbl_asistencia SET asistir = ? WHERE doc_asis = ? AND est_asis = ? AND fecha = ?;";

    public DAOAsistencia() {

    }

    @Override
    public boolean agregar(Asistencia objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        int asistir = 0;
        if (objeto.isAsistir()) {
            asistir = 1;
        }
        try {
            ps = conexion.prepareStatement(AGREGAR);
            ps.setString(1, objeto.getFecha());
            ps.setInt(dia, asistir);
            ps.setInt(3, objeto.getDoc_asis());
            ps.setInt(4, objeto.getEst_asis());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar asistencia, " + ex);
        }
        return false;
    }

    public boolean agregarAsistenciaDelDia(Asistencia objeto) {
        objeto.setFecha(fecha);
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        int asistir = 0;
        if (objeto.isAsistir()) {
            asistir = 1;
        }
        try {
            ps = conexion.prepareStatement(AGREGAR);
            ps.setString(1, objeto.getFecha());
            ps.setInt(2, asistir);
            ps.setInt(3, objeto.getDoc_asis());
            ps.setInt(4, objeto.getEst_asis());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar asistencia del dia, " + ex);
        }
        return false;
    }

    public boolean consultarAsisDelDia(Asistencia objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "";
        if (objeto.getEst_asis()!= 0) {
            consulta = String.format(CONSULTAR_ASIS_DIA, "AND est_asis = " + objeto.getEst_asis());
        } else {
            consulta = String.format(CONSULTAR_ASIS_DIA, "");
        }

        try {
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, fecha);
            ps.setInt(2, objeto.getDoc_asis());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Error al consultar la observacion del dia, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public List<Asistencia> consultarTodo() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Asistencia> lista = null;
        Boolean asistir = false;
        try {
            lista = new LinkedList<>();
            ps = conexion.prepareStatement(CONSULTAR_ASISTENCIAS);
            rs = ps.executeQuery();
            if (rs.getInt("asistir") == 1) {
                asistir = true;
            } else {
                asistir = false;
            }
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                        rs.getInt("id_asis"), rs.getString("fecha"), asistir,
                        rs.getInt("doc_asis"), rs.getInt("est_asis")
                );
                lista.add(asistencia);
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar todas las asistencias, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    public List<Asistencia> cargarAsistencias(Asistencia objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Asistencia> lista = null;
        Boolean asistir = false;
        String consulta = "";
        if (objeto.getEst_asis() == 0) {
            consulta = String.format(CARGAR_DATOS, "id_estudiante");
        } else {
            consulta = String.format(CARGAR_DATOS, objeto.getEst_asis());
        }
        if (objeto.getFecha().equals("")) {
            objeto.setFecha(fecha);
        } else if (objeto.getFecha().equals("estudiante")) {
            consulta = String.format(CARGAR_ASIS_EST, objeto.getEst_asis());
        }
        
        try {
            lista = new LinkedList<>();
            ps = conexion.prepareStatement(consulta);
            if (objeto.getFecha().equals("estudiante")) {
                ps.setInt(1, objeto.getDoc_asis());
            } else {
                ps.setInt(1, objeto.getDoc_asis());
                ps.setString(2, objeto.getFecha());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getInt("asistir") == 1) {
                    asistir = true;
                } else {
                    asistir = false;
                }
                Asistencia asistencia = new Asistencia(
                        rs.getInt("id_asis"), rs.getString("fecha"), asistir,
                        rs.getInt("doc_asis"), rs.getInt("est_asis"),
                        rs.getString("tbl_estudiante.nombre"), rs.getString("tbl_docente.nombre")
                );
                lista.add(asistencia);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar las asistencias por docente, " + ex);
            ex.printStackTrace();
            // this.agregar(objeto);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    @Override
    public Asistencia consultar(Asistencia objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Asistencia asistencia = null;
        boolean asistir = false;
        String consulta = "";
        if (objeto.getEst_asis() == 0) {
            consulta = String.format(CARGAR_DATOS, "id_estudiante");
        } else {
            consulta = String.format(CARGAR_DATOS, objeto.getEst_asis());
        }
        if (objeto.getFecha().equals("")) {
            objeto.setFecha(fecha);
        }
        try {
            ps = conexion.prepareStatement(consulta);
            ps.setInt(1, objeto.getDoc_asis());
            ps.setString(2, objeto.getFecha());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("asistir") == 1) {
                    asistir = true;
                } else {
                    asistir = false;
                }
                asistencia = new Asistencia(
                        rs.getInt("id_asis"), rs.getString("fecha"), asistir,
                        rs.getInt("doc_asis"), rs.getInt("est_asis")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la asistencia por fecha, " + ex);
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }

        return asistencia;
    }

    @Override
    public boolean actualizar(Asistencia objetoNuevo, Asistencia objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        int asistir = 0;
        try {
            if (objetoNuevo.isAsistir()) {
                asistir = 1;
            }
            ps = conexion.prepareStatement(MARCAR_ASISTENCIAS);
            ps.setInt(1, asistir);
            ps.setInt(2, objetoNuevo.getDoc_asis());
            ps.setInt(3, objetoNuevo.getEst_asis());
            ps.setString(4, objetoNuevo.getFecha());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar o marcar asistencia, " + ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(Asistencia objeto) {
        return false;
    }

}
