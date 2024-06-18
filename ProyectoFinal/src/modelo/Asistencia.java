
package modelo;

import java.util.Date;

public class Asistencia {
    private int id_asis;
    private String fecha;
    private boolean asistir;
    private int doc_asis;
    private int est_asis;
    private String estudiante_asig;
    private String docente_asig;
    
    public Asistencia() {
    }
    
    public Asistencia(int id_asis, String fecha, boolean asistir, int doc_asis, int est_asis) {
        this.id_asis = id_asis;
        this.fecha = fecha;
        this.asistir = asistir;
        this.doc_asis = doc_asis;
        this.est_asis = est_asis;
    }

    public Asistencia(int id_asis, String fecha, boolean asistir, int doc_asis, int est_asis, String estudiante_asig, String docente_asig) {
        this.id_asis = id_asis;
        this.fecha = fecha;
        this.asistir = asistir;
        this.doc_asis = doc_asis;
        this.est_asis = est_asis;
        this.estudiante_asig = estudiante_asig;
        this.docente_asig = docente_asig;
    }

    public String getEstudiante_asig() {
        return estudiante_asig;
    }

    public void setEstudiante_asig(String estudiante_asig) {
        this.estudiante_asig = estudiante_asig;
    }

    public String getDocente_asig() {
        return docente_asig;
    }

    public void setDocente_asig(String docente_asig) {
        this.docente_asig = docente_asig;
    }

    public int getId_asis() {
        return id_asis;
    }

    public void setId_asis(int id_asis) {
        this.id_asis = id_asis;
    }

    public int getDoc_asis() {
        return doc_asis;
    }

    public void setDoc_asis(int doc_asis) {
        this.doc_asis = doc_asis;
    }

    public int getEst_asis() {
        return est_asis;
    }

    public void setEst_asis(int est_asis) {
        this.est_asis = est_asis;
    }

    public boolean isAsistir() {
        return asistir;
    }

    public void setAsistir(boolean asistir) {
        this.asistir = asistir;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
