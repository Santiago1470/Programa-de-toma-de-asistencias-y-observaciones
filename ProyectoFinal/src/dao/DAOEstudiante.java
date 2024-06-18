package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import modelo.Estudiante;

public class DAOEstudiante implements DAOCrud<Estudiante> {

    private static final String CONSULTA_TODO = "SELECT * FROM tbl_estudiante;";
    private static final String CONSULTA_ESTUDIANTE = "SELECT * FROM tbl_estudiante WHERE nombre = ?;";
    private static final String AGREGAR = "INSERT INTO tbl_estudiante(nombre, password, correo, habilitacion, admin_est, doc_asignado) VALUES(?, ?, ?, 1, 1, %s);";
    private static final String ACTUALIZAR = "UPDATE tbl_estudiante SET nombre = ?, password = ?, correo = ?, doc_asignado = ? WHERE nombre = ?;";
    private static final String ELIMINAR = "DELETE FROM tbl_estudiante WHERE nombre = ?;";
    private static final String HABILITAR = "UPDATE tbl_estudiante SET habilitacion = ? WHERE nombre = ?;";
    private static final String CONSULTA_CREDENCIALES = "SELECT * FROM tbl_estudiante "
            + "WHERE nombre = BINARY ? AND password = BINARY ?";

    public DAOEstudiante() {
    }

    @Override
    public boolean agregar(Estudiante objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        String profesorAsignado = objeto.getProfesorAsignado();
        String agregarEstudiante = agregarEstudiante = String.format(AGREGAR, profesorAsignado);
        try {
            if (objeto.getProfesorAsignado().equals("0")) {
                profesorAsignado = "null";
                agregarEstudiante = String.format(AGREGAR, profesorAsignado);
            }
            ps = conexion.prepareStatement(agregarEstudiante);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPassword());
            ps.setString(3, objeto.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    @Override
    public List<Estudiante> consultarTodo() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Estudiante> lista = null;
        try {
            ps = conexion.prepareStatement(CONSULTA_TODO);
            rs = ps.executeQuery();
            lista = new LinkedList<>();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("correo"),
                        rs.getString("doc_asignado")
                );
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar datos de la tabla tbl_estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    @Override
    public Estudiante consultar(Estudiante objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Estudiante estudiante = null;
        boolean habilitacion = false;
        try {
            ps = conexion.prepareStatement(CONSULTA_ESTUDIANTE);
            ps.setString(1, objeto.getNombre());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                estudiante = new Estudiante(rs.getInt("id_estudiante"), rs.getInt("doc_asignado"), 
                        rs.getInt("admin_est"), rs.getString("nombre"), rs.getString("password"),
                        rs.getString("correo"), habilitacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar el estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return estudiante;
    }

    @Override
    public boolean actualizar(Estudiante objetoNuevo, Estudiante objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ACTUALIZAR);
            ps.setString(1, objetoNuevo.getNombre());
            ps.setString(2, objetoNuevo.getPassword());
            ps.setString(3, objetoNuevo.getCorreo());
            ps.setString(4, objetoNuevo.getProfesorAsignado());
            ps.setString(5, objeto.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    @Override
    public boolean eliminar(Estudiante objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ELIMINAR);
            ps.setString(1, objeto.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    public boolean habilitar(int tipo, String nombre) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            if (tipo == 2) {
                tipo = 0;
            }
            ps = conexion.prepareStatement(HABILITAR);
            ps.setString(1, String.valueOf(tipo));
            ps.setString(2, nombre);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al habilitar o deshabilitar el estudiante, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    public Estudiante validarCredenciales(Estudiante objeto) {
        Connection conexion = Conexion.getInstance();
        // System.out.println("La conexion con la BD al validar Docente es:" + Conexion.estadoConexion());
        PreparedStatement ps = null;
        ResultSet rs = null;
        Estudiante estudiante = null;
        boolean habilitacion = false;
        try {
            ps = conexion.prepareStatement(CONSULTA_CREDENCIALES);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                }
                estudiante = new Estudiante(rs.getInt("id_estudiante"), rs.getInt("doc_asignado"), 
                        rs.getInt("admin_est"),
                        rs.getString("nombre"), rs.getString("password"),
                        rs.getString("correo"), habilitacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al validar credenciales del docente.");
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
            // System.out.println("La conexion con la BD al validar Docente al finalizar es:" + Conexion.estadoConexion());
        }

        return estudiante;
    }

}
