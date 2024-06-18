package dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import modelo.Administrador;
import modelo.Docente;
import modelo.Estudiante;

public class DAODocente implements DAOCrud<Docente>, DAOUsuario<Docente> {

    private static final String CONSULTA_TODO = "SELECT * FROM tbl_docente;";
    private static final String CONSULTA_DOCENTE = "SELECT * FROM tbl_docente WHERE nombre = ?;";
    private static final String AGREGAR = "INSERT INTO tbl_docente(nombre, password, correo, habilitacion, admin_doc) VALUES(?, ?, ?, 1, 1);";
    private static final String ACTUALIZAR = "UPDATE tbl_docente SET nombre = ?, password = ?, correo = ? WHERE nombre = ?;";
    private static final String ELIMINAR = "DELETE FROM tbl_docente WHERE nombre = ?;";
    private static final String CONSULTA_CREDENCIALES = "SELECT * FROM tbl_docente "
            + "WHERE nombre = BINARY ? AND password = BINARY ?";
    private static final String HABILITAR = "UPDATE tbl_docente SET habilitacion = ? WHERE nombre = ?;";
    private static final String CONSULTAR_ESTUDIANTES = "SELECT * FROM tbl_estudiante, tbl_docente WHERE id_doc = ? AND id_doc = doc_asignado;";

    public DAODocente() {

    }

    @Override
    public boolean agregar(Docente objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(AGREGAR);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPassword());
            ps.setString(3, objeto.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    @Override
    public List<Docente> consultarTodo() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Docente> lista = null;
        try {
            ps = conexion.prepareStatement(CONSULTA_TODO);
            rs = ps.executeQuery();
            lista = new LinkedList<>();
            while (rs.next()) {
                Docente docente = new Docente(
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("correo")
                );
                lista.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar datos de la tabla tbl_docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    @Override
    public Docente consultar(Docente objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Docente docente = null;
        boolean habilitacion = false;
        try {
            ps = conexion.prepareStatement(CONSULTA_DOCENTE);
            ps.setString(1, objeto.getNombre());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                docente = new Docente(rs.getInt("id_doc"), rs.getInt("admin_doc"), rs.getString("nombre"),
                        rs.getString("password"), rs.getString("correo"), habilitacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar el docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return docente;
    }
    
    public List<Estudiante> consultarEstudiantes(Docente objeto){
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Estudiante> lista = null;
        boolean habilitacion = false;
        try {
            ps = conexion.prepareStatement(CONSULTAR_ESTUDIANTES);
            ps.setInt(1, objeto.getId_doc());
            rs = ps.executeQuery();
            lista = new LinkedList<>();
            while (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                Estudiante estudiante = new Estudiante(rs.getInt("id_estudiante"), 
                        rs.getInt("doc_asignado"), rs.getInt("admin_est"), 
                        rs.getString("tbl_estudiante.nombre"), rs.getString("tbl_estudiante.password"), 
                        rs.getString("tbl_estudiante.correo"), habilitacion
                );
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar estudiantes del docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return lista;
    }

    @Override
    public boolean actualizar(Docente objetoNuevo, Docente objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ACTUALIZAR);
            ps.setString(1, objetoNuevo.getNombre());
            ps.setString(2, objetoNuevo.getPassword());
            ps.setString(3, objetoNuevo.getCorreo());
            ps.setString(4, objeto.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    @Override
    public boolean eliminar(Docente objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(ELIMINAR);
            ps.setString(1, objeto.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al elimianar el docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

    @Override
    public Docente validarCredenciales(Docente objeto) {
        Connection conexion = Conexion.getInstance();
        // System.out.println("La conexion con la BD al validar Docente es:" + Conexion.estadoConexion());
        PreparedStatement ps = null;
        ResultSet rs = null;
        Docente docente = null;
        boolean habilitacion = false;
        try {
            ps = conexion.prepareStatement(CONSULTA_CREDENCIALES);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                docente = new Docente(rs.getInt("id_doc"), rs.getInt("admin_doc"),
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

        return docente;
    }

    public boolean habilitar(int tipo, String nombre) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;

        try {
            if(tipo == 2){
                tipo = 0;
            }
            ps = conexion.prepareStatement(HABILITAR);
            ps.setString(1, String.valueOf(tipo));
            ps.setString(2, nombre);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al habilitar o deshabilitar el docente, " + ex);
        } finally {
            Conexion.cerrarConexion();
        }

        return false;
    }

}
