package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Usuario;
import modelo.UsuarioExistente;

public class DAOAdmin implements DAOCrud<Administrador>, DAOUsuario<Administrador> {

    private static final String ADMIN_PREDEFINIDO = "INSERT INTO tbl_administrador(id_admin, nombre, password, correo) VALUES (?, ?, ?, ?)";
    private static final String CONSULTA_ADMIN = "SELECT * FROM tbl_administrador;";
    private static final String CONSULTA = "SELECT * FROM tbl_administrador WHERE nombre = ?;";
    private static final String CONSULTA_CREDENCIALES = "SELECT * FROM tbl_administrador WHERE nombre = BINARY ? AND password = BINARY ?";
    private static final String CONSULTA_DOCENTES = "SELECT * FROM tbl_docente, tbl_administrador WHERE tbl_administrador.id_admin = 1 AND tbl_administrador.id_admin = tbl_docente.admin_doc";
    private static final String CONSULTA_ESTUDIANTES = "SELECT DISTINCT tbl_estudiante.*, tbl_administrador.*, tbl_docente.*\n"
            + "FROM tbl_estudiante\n"
            + "LEFT JOIN tbl_administrador ON tbl_administrador.id_admin = tbl_estudiante.admin_est\n"
            + "LEFT JOIN tbl_docente ON tbl_docente.id_doc = tbl_estudiante.doc_asignado\n"
            + "WHERE tbl_administrador.id_admin = 1 AND (tbl_docente.id_doc IS NOT NULL OR tbl_estudiante.doc_asignado IS NULL);";
    private static final String CONSULTA_EXISTENCIA = "SELECT * FROM %s WHERE %s.nombre = ?;";

    public DAOAdmin() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // System.out.println("La conexion con la BD del constructor Admin es:" + Conexion.estadoConexion());
            ps = conexion.prepareStatement(CONSULTA_ADMIN);
            rs = ps.executeQuery();
            int cuenta = 0;
            while (rs.next()) {
                cuenta += 1;
            }
            if (cuenta < 1) {
                ps = conexion.prepareStatement(ADMIN_PREDEFINIDO);
                ps.setInt(1, 1);
                ps.setString(2, "Admin");
                ps.setInt(3, 12345);
                ps.setString(4, "admin@correo.com");
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar admin, " + ex);
        } finally {
            Conexion.cerrarConexion();
            // System.out.println("La conexion con la BD del constructor Admin al finalizar es:" + Conexion.estadoConexion());
        }
    }
    
    @Override
    public boolean agregar(Administrador objeto) {
        return false;
    }

    @Override
    public List<Administrador> consultarTodo() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Administrador> lista = null;
        try {
            ps = conexion.prepareStatement(CONSULTA_ADMIN);
            rs = ps.executeQuery();
            lista = new LinkedList<Administrador>();
            while (rs.next()) {
                Administrador admin = new Administrador(
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("correo")
                );
                lista.add(admin);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar todos los datos de la tabla tbl_administrador.");
        } finally {
            Conexion.cerrarConexion();
        }
        return lista;
    }

    @Override
    public Administrador consultar(Administrador objeto) {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Administrador admin = null;
        try {
            ps = conexion.prepareStatement(CONSULTA);
            ps.setString(1, objeto.getNombre());
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Administrador(rs.getString("nombre"), rs.getString("password"), rs.getString("correo"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar el administrador.");
        } finally {
            Conexion.cerrarConexion();
        }

        return admin;
    }

    @Override
    public boolean actualizar(Administrador objetoNuevo, Administrador objeto) {
        return false;
    }

    @Override
    public boolean eliminar(Administrador objeto) {
        return false;
    }

    @Override
    public Administrador validarCredenciales(Administrador objeto) {
        Connection conexion = Conexion.getInstance();
        // System.out.println("La conexion con la BD al validar Admin es:" + Conexion.estadoConexion());
        PreparedStatement ps = null;
        ResultSet rs = null;
        Administrador admin = null;
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
                admin = new Administrador(rs.getInt("id_admin"), rs.getString("nombre"),
                        rs.getString("password"), rs.getString("correo"), habilitacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al validar credenciales del administrador.");
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
            // System.out.println("La conexion con la BD al validar Admin al finalizar es:" + Conexion.estadoConexion());
        }

        return admin;
    }

    public List<Docente> consultarDocentes() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Docente> lista = null;
        try {
            ps = conexion.prepareStatement(CONSULTA_DOCENTES);
            rs = ps.executeQuery();
            boolean habilitacion = false;
            lista = new LinkedList<Docente>();
            while (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                Docente docente = new Docente(
                        rs.getInt("id_doc"),
                        rs.getInt("admin_doc"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("correo"),
                        habilitacion
                );
                lista.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar los docentes del administrador.");
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
        return lista;
    }

    public List<Estudiante> consultarEstudiante() {
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Estudiante> lista = null;
        try {
            ps = conexion.prepareStatement(CONSULTA_ESTUDIANTES);
            rs = ps.executeQuery();
            boolean habilitacion = false;
            lista = new LinkedList<Estudiante>();
            while (rs.next()) {
                if (rs.getInt("habilitacion") == 1) {
                    habilitacion = true;
                } else {
                    habilitacion = false;
                }
                Estudiante estudiante = new Estudiante(
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("correo"),
                        rs.getString("tbl_docente.nombre"),
                        habilitacion
                );
                lista.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar los estudiantes del administrador.");
            ex.printStackTrace();
        } finally {
            Conexion.cerrarConexion();
        }
        return lista;
    }

    public List<Usuario> consultarUsuarios() {
        List<Usuario> lista = new LinkedList<>();
        lista.addAll(this.consultarDocentes());
        lista.addAll(this.consultarEstudiante());

        return lista;
    }

    public boolean validarExistenciaUsuario(Usuario user) {
        UsuarioExistente myE;
        myE = new UsuarioExistente("No pueden existir dos usuarios con el mismo nombre.");
        Connection conexion = Conexion.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consultaExistencia = null;
        try {
            if (user instanceof Docente) {
                consultaExistencia = String.format(CONSULTA_EXISTENCIA, "tbl_docente", "tbl_docente");
            } else {
                consultaExistencia = String.format(CONSULTA_EXISTENCIA, "tbl_estudiante", "tbl_estudiante");
            }
            ps = conexion.prepareStatement(consultaExistencia);
            ps.setString(1, user.getNombre());
            rs = ps.executeQuery();
            while (rs.next()) {
                throw myE;
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar existencia del nuevo usuario.");
            ex.printStackTrace();
        } catch (UsuarioExistente ue) {
            JOptionPane.showMessageDialog(null, "No pueden existir dos usuarios con el mismo nombre.");
            System.err.println("Error: " + ue);
            return true;
        } finally {
            Conexion.cerrarConexion();
        }
        return false;
    }
    
    

}
