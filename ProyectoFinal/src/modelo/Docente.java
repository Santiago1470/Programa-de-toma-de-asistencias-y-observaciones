package modelo;

public class Docente extends Usuario {
    private int id_doc;
    private int admin_doc;

    public Docente() {
    }
    
    public Docente(String nombre, String password, String correo) {
        super(nombre, password, correo);
    }

    public Docente(String nombre, String password, String correo, boolean habilitacion) {
        super(nombre, password, correo, habilitacion);
    }
    
    public Docente(int id_doc, int admin_doc, String nombre, String password, String correo, boolean habilitacion) {
        super(nombre, password, correo, habilitacion);
        this.id_doc = id_doc;
        this.admin_doc = admin_doc;
    }

    public int getId_doc() {
        return id_doc;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }

    public int getAdmin_doc() {
        return admin_doc;
    }

    public void setAdmin_doc(int admin_doc) {
        this.admin_doc = admin_doc;
    }
    
    

}
