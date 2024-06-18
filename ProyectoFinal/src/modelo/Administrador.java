package modelo;

public class Administrador extends Usuario {
    protected int id_admin;

    public Administrador(String nombre, String password, String correo) {
        super(nombre, password, correo);
    }

    public Administrador(int id_admin, String nombre, String password, String correo, boolean habilitacion) {
        super(nombre, password, correo, habilitacion);
        this.id_admin = id_admin;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    
}
