package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Estudiante extends Usuario {
    private int id_estudiante;
    private int doc_asignado;
    private int admin_est;
    private String profesorAsignado;

    public Estudiante() {
        
    }
    
    public Estudiante(int id_estudiante, int doc_asignado, int admin_est, String nombre, String password, String correo, boolean habilitacion) {
        super(nombre, password, correo, habilitacion);
        this.id_estudiante = id_estudiante;
        this.doc_asignado = doc_asignado;
        this.admin_est = admin_est;
    }

    public Estudiante(String nombre, String password, String correo, String profesorAsignado, boolean habilitacion) {
        super(nombre, password, correo, habilitacion);
        this.profesorAsignado = profesorAsignado;
    }
    
    public Estudiante(String nombre, String password, String correo, String profesorAsignado) {
        super(nombre, password, correo);
        this.profesorAsignado = profesorAsignado;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getDoc_asignado() {
        return doc_asignado;
    }

    public void setDoc_asignado(int doc_asignado) {
        this.doc_asignado = doc_asignado;
    }

    public int getAdmin_est() {
        return admin_est;
    }

    public void setAdmin_est(int admin_est) {
        this.admin_est = admin_est;
    }
    
    public String getProfesorAsignado() {
        return profesorAsignado;
    }

    public void setProfesorAsignado(String profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }

}
