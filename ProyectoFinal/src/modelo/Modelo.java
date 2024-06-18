package modelo;

import dao.DAOAdmin;
import dao.DAOAsistencia;
import dao.DAODocente;
import dao.DAOEstudiante;
import dao.DAOObservacion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class Modelo {
    private DAOAdmin daoAdmin = new DAOAdmin();
    private DAODocente daoDocente = new DAODocente();
    private DAOEstudiante daoEstudiante = new DAOEstudiante();
    private DAOAsistencia daoAsistencia = new DAOAsistencia();
    private DAOObservacion daoObservacion = new DAOObservacion();
    private boolean modificarCeldaAsis = false;

    public Modelo() {

    }

    public Usuario validarCredencialesUser(String usuario, String password, byte rol) {
        return switch (rol) {
            case 1 ->
                daoAdmin.validarCredenciales(new Administrador(usuario, password, ""));
            case 2 ->
                daoDocente.validarCredenciales(new Docente(usuario, password, ""));
            default ->
                daoEstudiante.validarCredenciales(new Estudiante(usuario, password, "", ""));
        };
    }

    public DefaultTableModel cargarDatosAdministrador(Usuario user, int tipo) {
        Object[][] mat = null;
        String[] columnas = null;
        if (tipo == 1) {
            List<Docente> lista = null;
            lista = daoAdmin.consultarDocentes();
            mat = new Object[lista.size()][4];
            int f = 0;
            int c = 0;
            for (Docente i : lista) {
                mat[f][c] = i.getNombre();
                c = c + 1;
                mat[f][c] = i.getPassword();
                c = c + 1;
                mat[f][c] = i.getCorreo();
                c = 0;
                f = f + 1;
            }
            columnas = new String[3];
            columnas[0] = "Nombre";
            columnas[1] = "Contraseña";
            columnas[2] = "Correo";
        }
        if (tipo == 2) {
            List<Estudiante> lista = null;
            lista = daoAdmin.consultarEstudiante();
            mat = new Object[lista.size()][4];
            int f = 0;
            int c = 0;
            for (Estudiante i : lista) {
                mat[f][c] = i.getNombre();
                c = c + 1;
                mat[f][c] = i.getPassword();
                c = c + 1;
                mat[f][c] = i.getCorreo();
                c = c + 1;
                mat[f][c] = i.getProfesorAsignado();
                c = 0;
                f = f + 1;
            }
            columnas = new String[4];
            columnas[0] = "Nombre";
            columnas[1] = "Contraseña";
            columnas[2] = "Correo";
            columnas[3] = "Profesor";
        } else if (tipo == 3) {
            List<Usuario> lista = null;
            lista = daoAdmin.consultarUsuarios();
            mat = new Object[lista.size()][5];
            int f = 0;
            int c = 0;
            for (Usuario i : lista) {
                if (i instanceof Docente) {
                    mat[f][c] = "Docente";

                } else {
                    mat[f][c] = "Estudiante";
                }
                c = c + 1;
                mat[f][c] = i.getNombre();
                c = c + 1;
                mat[f][c] = i.getPassword();
                c = c + 1;
                mat[f][c] = i.getCorreo();
                c = c + 1;
                mat[f][c] = i.isHabilitacion();
                c = 0;
                f = f + 1;
            }
            columnas = new String[5];
            columnas[0] = "Tipo";
            columnas[1] = "Nombre";
            columnas[2] = "Contraseña";
            columnas[3] = "Correo";
            columnas[4] = "Habilitación";
        }

        DefaultTableModel modelo = new DefaultTableModel(
                mat,
                columnas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return modelo;
    }

    public DefaultComboBoxModel cargarListaDocentes() {
        List<Docente> listaDocentes = daoDocente.consultarTodo();
        String[] lista = new String[(listaDocentes.size() + 1)];
        lista[0] = "Seleccionar";
        int i = 1;
        for (Docente d : listaDocentes) {
            lista[i] = d.getNombre();
            i++;
        }
        return new DefaultComboBoxModel<>(lista);
    }

    // Tipos:
    // 1. Docente
    // 2. Estudiante
    public void agregarUsuarios(int tipo, Object objeto) {

        if (tipo == 1) {
            Docente docente = new Docente(((Docente) objeto).getNombre(),
                    ((Docente) objeto).getPassword(), ((Docente) objeto).getCorreo());
            if (!daoAdmin.validarExistenciaUsuario(docente)) {
                daoDocente.agregar(docente);
            }

        } else if (tipo == 2) {
            Estudiante estudiante = new Estudiante(((Estudiante) objeto).getNombre(),
                    ((Estudiante) objeto).getPassword(), ((Estudiante) objeto).getCorreo(),
                    ((Estudiante) objeto).getProfesorAsignado());
            if (!daoAdmin.validarExistenciaUsuario(estudiante)) {
                Docente docente = daoDocente.consultar(new Docente(estudiante.getProfesorAsignado(), "", ""));
                if (docente != null) {
                    estudiante.setProfesorAsignado(String.valueOf(docente.getId_doc()));
                } else {
                    estudiante.setProfesorAsignado("0");
                }
                daoEstudiante.agregar(estudiante);
            }
        }
    }

    public void eliminarUsuarios(Usuario user) {
        if (user instanceof Docente) {
            daoDocente.eliminar(((Docente) user));
        } else {
            daoEstudiante.eliminar(((Estudiante) user));
        }

    }

    public void modificarUsuarios(Object objetoModificado, Object objeto) {
        if (objetoModificado instanceof Docente) {
            daoDocente.actualizar(((Docente) objetoModificado), ((Docente) objeto));
        } else {
            Docente profesorAsignado = daoDocente.consultar(new Docente(((Estudiante) objetoModificado).getProfesorAsignado(), "", ""));
            if (profesorAsignado == null) {
                ((Estudiante) objetoModificado).setProfesorAsignado(null);
            } else {
                ((Estudiante) objetoModificado).setProfesorAsignado(String.valueOf(profesorAsignado.getId_doc()));
            }

            daoEstudiante.actualizar(((Estudiante) objetoModificado), ((Estudiante) objeto));
        }

    }

    public void habilitarUsuarios(int tipo, String nombre, String tipoUsuario) {
        int tipoUser = 0;

        if (tipoUsuario.equals("Docente")) {
            tipoUser = 1;
            daoDocente.habilitar(tipo, nombre);
        } else if (tipoUsuario.equals("Estudiante")) {
            tipoUser = 2;
            daoEstudiante.habilitar(tipo, nombre);
        }
    }

    // Tipos:
    // 1. Asistencia
    // 2. Asistencias y observaciones
    // 3. Observaciones
    public DefaultTableModel cargarDatosDocentes(Usuario user, int tipo) {
        List<Estudiante> estudiantes = daoDocente.consultarEstudiantes(((Docente) user));
        Object[][] mat = null;
        String[] columnas = null;
        if (tipo == 1) {
            List<Asistencia> lista = null;
            for (Estudiante e : estudiantes) {
                if (!daoAsistencia.consultarAsisDelDia(new Asistencia(0, "", true,
                        ((Docente) user).getId_doc(), e.getId_estudiante()))) {
                    daoAsistencia.agregarAsistenciaDelDia(new Asistencia(0, "", false,
                            ((Docente) user).getId_doc(), e.getId_estudiante()));

                }
            }
            lista = daoAsistencia.cargarAsistencias(new Asistencia(0, "", true,
                    ((Docente) user).getId_doc(), 0));
            mat = new Object[lista.size()][4];
            int f = 0;
            int c = 0;
            for (Asistencia i : lista) {
                mat[f][c] = i.getEstudiante_asig();
                c = c + 1;
                mat[f][c] = i.isAsistir();
                c = c + 1;
                mat[f][c] = i.getFecha();
                c = 0;
                f = f + 1;
            }
            columnas = new String[3];
            columnas[0] = "Estudiante";
            columnas[1] = "Asistencia";
            columnas[2] = "Fecha";

        } else if (tipo == 3) {
            mat = new Object[estudiantes.size()][3];
            int f = 0;
            int c = 0;
            for (Estudiante e : estudiantes) {
                if (!daoObservacion.consultarObservDelDia(new Observacion(0, "", "",
                        ((Docente) user).getId_doc(), e.getId_estudiante()))) {
                    mat[f][c] = e.getNombre();
                    c = c + 1;
                    mat[f][c] = "";
                    c = c + 1;
                    mat[f][c] = "";
                    c = 0;
                    f = f + 1;

                } else {
                    Observacion observacion = daoObservacion.cargarObservacion(new Observacion(0, "", "",
                            ((Docente) user).getId_doc(), e.getId_estudiante()));
                    mat[f][c] = observacion.getEst_asig();
                    c = c + 1;
                    mat[f][c] = observacion.getObservacion();
                    c = c + 1;
                    mat[f][c] = observacion.getFecha();
                    c = 0;
                    f = f + 1;

                }
            }
            columnas = new String[3];
            columnas[0] = "Estudiante";
            columnas[1] = "Observación";
            columnas[2] = "Fecha";

        }
        DefaultTableModel modelo = null;
        if (tipo == 1) {
            modelo = new DefaultTableModel(
                    mat,
                    columnas
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1;
                }
            };
        } else {
            modelo = new DefaultTableModel(
                    mat,
                    columnas
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }

        return modelo;
    }

    public DefaultTableModel cargarDatosDocenteAyO(Docente docente, String fecha) {
        Object[][] mat = null;
        String[] columnas = null;
        List<Estudiante> estudiantes = daoDocente.consultarEstudiantes(docente);
        mat = new Object[estudiantes.size()][4];
        int f = 0;
        int c = 0;
        for (Estudiante e : estudiantes) {
            mat[f][c] = e.getNombre();
            c = c + 1;
            Asistencia asistencia = daoAsistencia.consultar(new Asistencia(0, fecha, true,
                    docente.getId_doc(), e.getId_estudiante()));
            if (asistencia == null) {
                mat[f][c] = "No se encontraron datos.";
                c = c + 1;
                modificarCeldaAsis = true;
            } else {
                mat[f][c] = asistencia.isAsistir();
                c = c + 1;
                modificarCeldaAsis = false;
            }
            Observacion observacion = daoObservacion.cargarObservacion(new Observacion(0, "", fecha,
                    docente.getId_doc(), e.getId_estudiante()));
            if (observacion == null) {
                mat[f][c] = "";
                c = c + 1;
            } else {
                mat[f][c] = observacion.getObservacion();
                c = c + 1;
            }
            mat[f][c] = fecha;
            c = 0;
            f = f + 1;
        }
        columnas = new String[4];
        columnas[0] = "Estudiante";
        columnas[1] = "Asistencia";
        columnas[2] = "Observación";
        columnas[3] = "Fecha";
        DefaultTableModel modelo = new DefaultTableModel(
                mat,
                columnas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return modelo;
    }

    public void marcarAsistencias(Docente docente, String nombreEstudiante, boolean asistir, String fecha) {
        Estudiante estudiante = daoEstudiante.consultar(new Estudiante(nombreEstudiante, "", "", ""));
        daoAsistencia.actualizar(new Asistencia(0, fecha, asistir, docente.getId_doc(), estudiante.getId_estudiante()), null);
    }

    public DefaultTableModel buscarAsistencias(Usuario user, String fecha) throws NullPointerException {
        Object[][] mat = null;
        String[] columnas = null;
        List<Asistencia> lista = null;
        lista = daoAsistencia.cargarAsistencias(new Asistencia(0, fecha, true,
                ((Docente) user).getId_doc(), 0));
        mat = new Object[lista.size()][4];
        int f = 0;
        int c = 0;
        for (Asistencia i : lista) {
            mat[f][c] = i.getEstudiante_asig();
            c = c + 1;
            mat[f][c] = i.isAsistir();
            c = c + 1;
            mat[f][c] = i.getFecha();
            c = 0;
            f = f + 1;
        }
        columnas = new String[3];
        columnas[0] = "Estudiante";
        columnas[1] = "Asistencia";
        columnas[2] = "Fecha";

        DefaultTableModel modelo = new DefaultTableModel(
                mat,
                columnas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        return modelo;
    }

    public boolean agregarObservacion(Docente user, String nombreEstudiante, String observacion) {
        Estudiante estudiante = daoEstudiante.consultar(new Estudiante(nombreEstudiante, "", "", ""));
        if (!daoObservacion.consultarObservDelDia(new Observacion(0, "", "",
                ((Docente) user).getId_doc(), estudiante.getId_estudiante()))) {
            daoObservacion.agregar(new Observacion(0, observacion, "", user.getId_doc(), estudiante.getId_estudiante()));
            return true;
        }

        return false;
    }

    public Observacion buscarObservacion(Docente docente, String nombre, String fecha) {
        if (docente.getId_doc() == 0) {
            docente = daoDocente.consultar(new Docente(docente.getNombre(), "", ""));
        }
        Estudiante estudiante = daoEstudiante.consultar(new Estudiante(nombre, "", "", ""));
            return daoObservacion.cargarObservacion(new Observacion(0, "", fecha,
                    docente.getId_doc(), estudiante.getId_estudiante()));

    }

    public void modificarObservacion(Docente docente, String nombre, String observacion, String fecha) {
        Estudiante estudiante = daoEstudiante.consultar(new Estudiante(nombre, "", "", ""));
        daoObservacion.actualizar(new Observacion(0, observacion, fecha, docente.getId_doc(),
                estudiante.getId_estudiante()), null);
    }

    public void eliminarObservacion(Docente docente, String nombre, String fecha) {
        Estudiante estudiante = daoEstudiante.consultar(new Estudiante(nombre, "", "", ""));
        daoObservacion.eliminar(new Observacion(0, "", fecha, docente.getId_doc(),
                estudiante.getId_estudiante()));
    }

    public DefaultTableModel cargarDatosEstudiante(Usuario user, int tipo) {
        Object[][] mat = null;
        String[] columnas = null;
        if (tipo == 1) {
            List<Asistencia> lista = null;
            lista = daoAsistencia.cargarAsistencias(new Asistencia(0, "estudiante", true,
                    ((Estudiante) user).getDoc_asignado(), ((Estudiante) user).getId_estudiante()));
            mat = new Object[lista.size()][3];
            int f = 0;
            int c = 0;
            for (Asistencia i : lista) {
                mat[f][c] = i.getDocente_asig();
                c = c + 1;
                mat[f][c] = i.isAsistir();
                c = c + 1;
                mat[f][c] = i.getFecha();
                c = 0;
                f = f + 1;
            }
            columnas = new String[3];
            columnas[0] = "Docente Asignado";
            columnas[1] = "Asistencia";
            columnas[2] = "Fecha";
        }
        if (tipo == 2) {
            List<Observacion> lista = null;
            lista = daoObservacion.cargarObservaciones(new Observacion(0, "", "estudiante",
                    ((Estudiante) user).getDoc_asignado(), ((Estudiante) user).getId_estudiante()));
            mat = new Object[lista.size()][3];
            int f = 0;
            int c = 0;
            for (Observacion i : lista) {
                mat[f][c] = i.getDoc_asig();
                c = c + 1;
                mat[f][c] = i.getObservacion();
                c = c + 1;
                mat[f][c] = i.getFecha();
                c = 0;
                f = f + 1;
            }
            columnas = new String[3];
            columnas[0] = "Docente asignado";
            columnas[1] = "Observación";
            columnas[2] = "Fecha";
        }

        DefaultTableModel modelo = new DefaultTableModel(
                mat,
                columnas
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return modelo;
    }

    public boolean isModificarCeldaAsis() {
        return modificarCeldaAsis;
    }

    public void setModificarCeldaAsis(boolean modificarCeldaAsis) {
        this.modificarCeldaAsis = modificarCeldaAsis;
    }

}
