package modelo;

public abstract class Usuario {
    
    protected String nombre;
    protected String password;
    protected String correo;
    protected boolean habilitacion;

    public Usuario() {
    }

    public Usuario(String nombre, String password, String correo) {
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.habilitacion = true;
    }

    public Usuario(String nombre, String password, String correo, boolean habilitacion) {
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.habilitacion = habilitacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isHabilitacion() {
        return habilitacion;
    }

    public void setHabilitacion(boolean habilitacion) {
        this.habilitacion = habilitacion;
    }
}
