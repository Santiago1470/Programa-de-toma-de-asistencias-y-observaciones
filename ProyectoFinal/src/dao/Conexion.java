package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conexion;

    // private String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3307/registroasistencias";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static boolean estado;

    private Conexion() {

    }

    public static Connection getInstance() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                // JOptionPane.showMessageDialog(null, "Base de datos conectada correctamente.");
                estado = true;
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Error al conectar la base de datos, " + ex);
                // JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.");
                ex.printStackTrace();
                estado = false;
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null) {
                if (!conexion.isClosed()) {
                    conexion.close();
                    conexion = null;
                    estado = false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar conexion con BD.");
        }
    }

    public static boolean estadoConexion() {
        return estado;
    }

}
