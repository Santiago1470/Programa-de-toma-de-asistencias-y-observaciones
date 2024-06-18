
package modelo;

public class Observacion {
    private int id_observ;
    private String observacion;
    private String fecha;
    private int doc_observ;
    private int est_observ;
    private String doc_asig;
    private String est_asig;

    public Observacion() {
    }

    public Observacion(int id_observ, String observacion, String fecha, int doc_observ, int est_observ) {
        this.id_observ = id_observ;
        this.observacion = observacion;
        this.fecha = fecha;
        this.doc_observ = doc_observ;
        this.est_observ = est_observ;
    }

    public Observacion(int id_observ, String observacion, String fecha, int doc_observ, int est_observ, String doc_asig, String est_asig) {
        this.id_observ = id_observ;
        this.observacion = observacion;
        this.fecha = fecha;
        this.doc_observ = doc_observ;
        this.est_observ = est_observ;
        this.doc_asig = doc_asig;
        this.est_asig = est_asig;
    }
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_observ() {
        return id_observ;
    }

    public void setId_observ(int id_observ) {
        this.id_observ = id_observ;
    }

    public int getDoc_observ() {
        return doc_observ;
    }

    public void setDoc_observ(int doc_observ) {
        this.doc_observ = doc_observ;
    }

    public int getEst_observ() {
        return est_observ;
    }

    public void setEst_observ(int est_observ) {
        this.est_observ = est_observ;
    }

    public String getDoc_asig() {
        return doc_asig;
    }

    public void setDoc_asig(String doc_asig) {
        this.doc_asig = doc_asig;
    }

    public String getEst_asig() {
        return est_asig;
    }

    public void setEst_asig(String est_asig) {
        this.est_asig = est_asig;
    }
}
