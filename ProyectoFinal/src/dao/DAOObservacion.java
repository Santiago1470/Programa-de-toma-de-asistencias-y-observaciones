package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import modelo.Asistencia;
import modelo.Observacion;

public class DAOObservacion implements DAOCrud<Observacion> {

    private static LocalDateTime fechaLocal = LocalDateTime.now();
    private static int year = fechaLocal.getYear();
    private static int mes = fechaLocal.getMonthValue();
    private static int dia = fechaLocal.getDayOfMonth();
    private static String fecha = year + "-" + mes + "-" + dia;
    private static final String AGREGAR = "INSERT INTO tbl_observacion(observacion, fecha, doc_observ, est_observ) VALUES(?, ?, ?, ?);";
    private static final String CARGAR_DATOS = "SELECT * FROM tbl_observacion, tbl_docente, tbl_estudiante "
            + "WHERE doc_observ = ? AND doc_observ = id_doc AND fecha = ? AND est_observ = %s AND est_observ = id_estudiante;";
    private static final String CARGAR_OBSERV_EST = "SELECT * FROM tbl_observacion, tbl_docente, tbl_estudiante "
            + "WHERE doc_observ = ? AND doc_observ = id_doc AND est_observ = %s AND est_observ = id_estudiante;";
    private static final String CONSULTAR_OBSERV_DIA = "SELECT * FROM tbl_observacion, tbl_docente WHERE fecha = ? AND doc_observ = ? %s;";
    private static final String CONSULTAR_POR_FECHA = "SELECT * FROM tbl_observacion, tbl_docente, tbl_estudiante "
            + "WHERE doc_observ = ? AND doc_observ = id_doc AND fecha = ? AND est_observ = id_estudiante;";
    private static final String ACTUALIZAR = "UPDATE tbl_observacion SET observacion = ? WHERE fecha = ? AND doc_observ = ? AND est_observ = ?";
    private static final String ELIMINAR = "DELETE FROM tbl_observacion WHERE fecha = ? AND doc_observ = ? AND est_observ = ?";

    public DAOObservacion() {

    }

    @Override
    public boolean agregar(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        if (objeto.getFecha().equals("")) {
            objeto.setFecha(fecha);
        }
        try {
            ps = conexion.prepareStatement(AGREGAR);
            ps.setString(1, objeto.getObservacion());
            ps.setString(2, objeto.getFecha());
            ps.setInt(3, objeto.getDoc_observ());
            ps.setInt(4, objeto.getEst_observ());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar observacion, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }
        return false;
    }

    public boolean consultarObservDelDia(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta = "";
        if (objeto.getEst_observ() != 0) {
            consulta = String.format(CONSULTAR_OBSERV_DIA, "AND est_observ = " + objeto.getEst_observ());
        } else {
            consulta = String.format(CONSULTAR_OBSERV_DIA, "");
        }

        try {
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, fecha);
            ps.setInt(2, objeto.getDoc_observ());
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
    public List<Observacion> consultarTodo() {
        return null;
    }

    public List<Observacion> cargarObservaciones(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Observacion> lista = null;
        String consulta = "";
        if (objeto.getEst_observ() == 0) {
            consulta = String.format(CARGAR_DATOS, "id_estudiante");
        } else {
            consulta = String.format(CARGAR_DATOS, objeto.getEst_observ());
        }
        if (objeto.getFecha().equals("")) {
                objeto.setFecha(fecha);
            } else if(objeto.getFecha().equals("estudiante")) {
                consulta = String.format(CARGAR_OBSERV_EST, objeto.getEst_observ());
            }
        try {
            lista = new LinkedList<>();
            ps = conexion.prepareStatement(consulta);
            if (objeto.getFecha().equals("estudiante")) {
                ps.setInt(1, objeto.getDoc_observ());
            } else {
                ps.setInt(1, objeto.getDoc_observ());
                ps.setString(2, objeto.getFecha());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Observacion observacion = new Observacion(
                        rs.getInt("id_observ"), rs.getString("observacion"), rs.getString("fecha"),
                        rs.getInt("doc_observ"), rs.getInt("est_observ"),
                        rs.getString("tbl_docente.nombre"), rs.getString("tbl_estudiante.nombre")
                );
                lista.add(observacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar las observaciones por docente, " + ex);
            ex.printStackTrace();
            // this.agregar(objeto);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    public Observacion cargarObservacion(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Observacion observacion = null;
        String consulta = "";
        if (objeto.getEst_observ() == 0) {
            consulta = String.format(CARGAR_DATOS, "id_estudiante");
        } else {
            consulta = String.format(CARGAR_DATOS, objeto.getEst_observ());
        }
        if (objeto.getFecha().equals("")) {
            objeto.setFecha(fecha);
        }
        try {
            ps = conexion.prepareStatement(consulta);
            ps.setInt(1, objeto.getDoc_observ());
            ps.setString(2, objeto.getFecha());
            rs = ps.executeQuery();

            if (rs.next()) {
                observacion = new Observacion(
                        rs.getInt("id_observ"), rs.getString("observacion"), rs.getString("fecha"),
                        rs.getInt("doc_observ"), rs.getInt("est_observ"),
                        rs.getString("tbl_docente.nombre"), rs.getString("tbl_estudiante.nombre")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar las observaciones por docente, " + ex);
            ex.printStackTrace();
            // this.agregar(objeto);
        } finally {
            Conexion.cerrarConexion();
        }

        return observacion;
    }

    @Override
    public Observacion consultar(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Observacion observacion = null;
        try {
            ps = conexion.prepareStatement(CONSULTAR_POR_FECHA);
            ps.setInt(1, objeto.getDoc_observ());
            ps.setString(2, objeto.getFecha());
            rs = ps.executeQuery();
            observacion = new Observacion(rs.getInt("id_observ"), rs.getString("observacion"),
                    rs.getString("fecha"), rs.getInt("doc_observ"), rs.getInt("est_observ")
            );
        } catch (SQLException ex) {
            System.out.println("Error al consultar la asistencia por fecha, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return observacion;
    }

    @Override
    public boolean actualizar(Observacion objetoNuevo, Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ACTUALIZAR);
            ps.setString(1, objetoNuevo.getObservacion());
            ps.setString(2, objetoNuevo.getFecha());
            ps.setInt(3, objetoNuevo.getDoc_observ());
            ps.setInt(4, objetoNuevo.getEst_observ());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al modificar observacion, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean eliminar(Observacion objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ELIMINAR);
            ps.setString(1, objeto.getFecha());
            ps.setInt(2, objeto.getDoc_observ());
            ps.setInt(3, objeto.getEst_observ());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar observacion, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }
}
