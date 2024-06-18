package controlador;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.*;
import vista.*;

public class Controlador implements ActionListener, KeyListener, MouseListener {
    private Modelo modelo;
    /*
    Posiciones:
    0: VentanaInicio
    1: VentanaAdmin
    2: VentanaDocente
    3: VentanaEstudiante
     */
    private ArrayList<JFrame> ventanas = new ArrayList<>();
    private VentanaInicio ventanaInicio;
    private VentanaAdmin ventanaAdmin;
    private VentanaDocente ventanaDocente;
    private VentanaEstudiante ventanaEstudiante;
    private Usuario user;

    public Controlador(Modelo modelo, ArrayList<JFrame> ventanas) {
        this.modelo = modelo;
        this.ventanas = ventanas;

    }

    public void iniciar() {
        ventanaInicio = (VentanaInicio) ventanas.get(0);
        ventanaAdmin = (VentanaAdmin) ventanas.get(1);
        ventanaDocente = (VentanaDocente) ventanas.get(2);
        ventanaEstudiante = (VentanaEstudiante) ventanas.get(3);

        ventanaInicio.setVisible(true);
        escucharVentanaInicio();
        escucharVentanaAdmin();
        escucharVentanaDocente();
        escucharVentanaEstudiante();

        // cargarUsuariosPre();
    }

    public void escucharVentanaInicio() {
        ventanaInicio.getBtnIngresar().addActionListener(this);
        ventanaInicio.getTxtUsuario().addKeyListener(this);
        ventanaInicio.getPwdUsuario().addKeyListener(this);
    }

    public void escucharVentanaAdmin() {
        ventanaAdmin.getBtnAgregar().addActionListener(this);
        ventanaAdmin.getBtnDeshabilitar().addActionListener(this);
        ventanaAdmin.getBtnEliminar().addActionListener(this);
        ventanaAdmin.getBtnHabilitar().addActionListener(this);
        ventanaAdmin.getBtnHabilitarUser().addActionListener(this);
        ventanaAdmin.getBtnInicio().addActionListener(this);
        ventanaAdmin.getBtnModificar().addActionListener(this);
        ventanaAdmin.getBtnRegistrarDoc().addActionListener(this);
        ventanaAdmin.getBtnRegistrarEstu().addActionListener(this);
        ventanaAdmin.getBtnSalir().addActionListener(this);

        ventanaAdmin.getVentanaCrudDoc().getTxtNombre().addKeyListener(this);
        ventanaAdmin.getVentanaCrudDoc().getTxtPassword().addKeyListener(this);
        ventanaAdmin.getVentanaCrudDoc().getTxtCorreo().addKeyListener(this);

        ventanaAdmin.getVentanaCrudDoc().getBtnAgregar().addActionListener(this);
        ventanaAdmin.getVentanaCrudDoc().getBtnEliminar().addActionListener(this);
        ventanaAdmin.getVentanaCrudDoc().getBtnModificar().addActionListener(this);
        ventanaAdmin.getVentanaCrudDoc().getBtnCancelar().addActionListener(this);

        ventanaAdmin.getVentanaCrudEst().getBtnAgregar().addActionListener(this);
        ventanaAdmin.getVentanaCrudEst().getBtnEliminar().addActionListener(this);
        ventanaAdmin.getVentanaCrudEst().getBtnModificar().addActionListener(this);
        ventanaAdmin.getVentanaCrudEst().getBtnCancelar().addActionListener(this);
    }

    public void escucharVentanaDocente() {
        ventanaDocente.getBtnAgregar().addActionListener(this);
        ventanaDocente.getBtnAsistencia().addActionListener(this);
        ventanaDocente.getBtnEliminar().addActionListener(this);
        ventanaDocente.getBtnEstudiantes().addActionListener(this);
        ventanaDocente.getBtnInicio().addActionListener(this);
        ventanaDocente.getBtnMarcarAsistencia().addActionListener(this);
        ventanaDocente.getBtnModificar().addActionListener(this);
        ventanaDocente.getBtnObservaciones().addActionListener(this);
        ventanaDocente.getBtnSalir().addActionListener(this);
        ventanaDocente.getBtnVerObservaciones().addActionListener(this);

        ventanaDocente.getVentanaBuscarAsistencia().getBtnCargarAsistencia().addActionListener(this);
        ventanaDocente.getVentanaBuscarAsistencia().getBtnCancelar().addActionListener(this);

        ventanaDocente.getVentanaCargarFecha().getBtnCargar().addActionListener(this);
        ventanaDocente.getVentanaCargarFecha().getBtnCancelar().addActionListener(this);

        ventanaDocente.getVentanaCrudObservacion().getBtnAgregar().addActionListener(this);
        ventanaDocente.getVentanaCrudObservacion().getBtnCancelar().addActionListener(this);
        ventanaDocente.getVentanaCrudObservacion().getBtnModificar().addActionListener(this);
        ventanaDocente.getVentanaCrudObservacion().getBtnEliminar().addActionListener(this);

        ventanaDocente.getVentanaBuscarObservacion().getBtnCargar().addActionListener(this);
        ventanaDocente.getVentanaBuscarObservacion().getBtnCancelar().addActionListener(this);
    }

    public void escucharVentanaEstudiante() {
        ventanaEstudiante.getBtnAsistencias().addActionListener(this);
        ventanaEstudiante.getBtnInicio().addActionListener(this);
        ventanaEstudiante.getBtnObservaciones().addActionListener(this);
        ventanaEstudiante.getBtnSalir().addActionListener(this);

        ventanaEstudiante.getTblInicio().addMouseListener(this);

        ventanaEstudiante.getVentanaObservacion().getBtnCerrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
// Eventos de ventana de inicio
        if (e.getSource() == ventanaInicio.getBtnIngresar()) {
            validarCredencialesUser();

            if (this.user instanceof Docente) {
                String[] nombreCompleto = this.user.getNombre().split(" ");
                String nombre = nombreCompleto[0];
                String apellido = "";
                if (nombreCompleto.length == 4) {
                    apellido = nombreCompleto[2];
                } else {
                    apellido = nombreCompleto[1];
                }
                ventanaDocente.getLblTitulo().setText("Bienvenido(a) " + nombre + " " + apellido);
            } else if (this.user instanceof Estudiante) {
                String[] nombreCompleto = this.user.getNombre().split(" ");
                String nombre = nombreCompleto[0];
                String apellido = "";
                if (nombreCompleto.length == 4) {
                    apellido = nombreCompleto[2];
                } else {
                    apellido = nombreCompleto[1];
                }
                ventanaEstudiante.getLblTitulo().setText("Bienvenido(a) " + nombre + " " + apellido);
            }
        }

// Inicio de sección de eventos para ventana de Administrador
        if (e.getSource() == ventanaAdmin.getBtnRegistrarDoc()) {
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 1));

            //cargarDatos(user, 1);
        } else if (e.getSource() == ventanaAdmin.getBtnRegistrarEstu()) {
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 2));
            // cargarDatos(user, 2);
        } else if (e.getSource() == ventanaAdmin.getBtnHabilitar()) {
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 3));
            agregarCheckBoxAdmin();

            // cargarDatos(user, 3);
        } else if (e.getSource() == ventanaAdmin.getBtnInicio()) {
            ventanaAdmin.getTblInicio().setModel(new DefaultTableModel());
        } else if (e.getSource() == ventanaAdmin.getBtnSalir()) {
            // ventanaAdmin.setVisible(false);
            ventanaAdmin.dispose();
            ventanaInicio.setVisible(true);
        } else if (e.getSource() == ventanaAdmin.getBtnAgregar()) {
            if (ventanaAdmin.isRegistrarDocente()) {
                ventanaAdmin.getVentanaCrudDoc().setVisible(true);
                ventanaAdmin.getVentanaCrudDoc().getTxtNombre().setEditable(true);
            } else if (ventanaAdmin.isRegistrarEstudiante()) {
                ventanaAdmin.getVentanaCrudEst().getCmbAsignarDoc().setModel(modelo.cargarListaDocentes());
                ventanaAdmin.getVentanaCrudEst().setVisible(true);
                ventanaAdmin.getVentanaCrudEst().getTxtNombre().setEditable(true);
            }
        } else if (e.getSource() == ventanaAdmin.getVentanaCrudDoc().getBtnAgregar()) {
            // Agregar Usuario de tipo Docente
            agregarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getVentanaCrudDoc().getTxtNombre().setEditable(false);
        } else if (e.getSource() == ventanaAdmin.getVentanaCrudEst().getBtnAgregar()) {
            // Agregar Usuario de tipo Estudiante
            agregarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getVentanaCrudEst().getTxtNombre().setEditable(false);
        } else if (e.getSource() == ventanaAdmin.getBtnModificar()) {
            // JInternalFrame para modificar usuarios
            try {
                mostrarDatosUsuarioVentanasCrudAdmin();
            } catch (ArrayIndexOutOfBoundsException ae) {
                System.err.println("Error: " + ae);
                ventanaAdmin.mostrarDatos("Por favor, seleccione la fila del usuario que desea.");
                if (ventanaAdmin.isRegistrarDocente()) {
                    limpiarDatosCrudVentanaAdmin(1);
                } else if (ventanaAdmin.isRegistrarEstudiante()) {
                    limpiarDatosCrudVentanaAdmin(2);
                }
            }
        } else if (e.getSource() == ventanaAdmin.getVentanaCrudDoc().getBtnModificar()) {
            // Modificar usuario de tipo Docente
            modificarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(1);

        } else if (e.getSource() == ventanaAdmin.getVentanaCrudEst().getBtnModificar()) {
            // Modificar usuario de tipo Estudiante
            modificarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(2);

        } else if (e.getSource() == ventanaAdmin.getBtnEliminar()) {
            // JInternalFrame para modificar usuarios
            try {
                mostrarDatosUsuarioVentanasCrudAdmin();
            } catch (ArrayIndexOutOfBoundsException ae) {
                System.err.println("Error: " + ae);
                ventanaAdmin.mostrarDatos("Por favor, seleccione la fila del usuario que desea.");
                if (ventanaAdmin.isRegistrarDocente()) {
                    limpiarDatosCrudVentanaAdmin(1);
                } else if (ventanaAdmin.isRegistrarEstudiante()) {
                    limpiarDatosCrudVentanaAdmin(2);
                }
            }
        } else if (e.getSource() == ventanaAdmin.getVentanaCrudDoc().getBtnEliminar()) {
            // Eliminar usuario de tipo Docente
            eliminarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(1);

        } else if (e.getSource() == ventanaAdmin.getVentanaCrudEst().getBtnEliminar()) {
            // Eliminar usuario de tipo Estudiante
            eliminarUsuarioVentanasCrudAdmin();
            limpiarDatosCrudVentanaAdmin(2);

        } else if (e.getSource() == ventanaAdmin.getVentanaCrudDoc().getBtnCancelar()) {
            // Cancelar cualquier operación CRUD en la ventanaCrudDoc
            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getVentanaCrudDoc().getTxtNombre().setEditable(false);
        } else if (e.getSource() == ventanaAdmin.getVentanaCrudEst().getBtnCancelar()) {
            // Cancelar cualquier operación CRUD en la ventanaCrudEst
            limpiarDatosCrudVentanaAdmin(2);
            ventanaAdmin.getVentanaCrudEst().getTxtNombre().setEditable(false);
        } else if (e.getSource() == ventanaAdmin.getBtnHabilitarUser()) {
            // Habilitar usuario en ventana Administrador
            habilitarUsuarioVentanaAdmin(1);
            // ventanaAdmin.getTblInicio().changeSelection(0, 4, false, false);
        } else if (e.getSource() == ventanaAdmin.getBtnDeshabilitar()) {
            // Deshabilitar usuario en ventana Administrador
            habilitarUsuarioVentanaAdmin(2);
            // ventanaAdmin.getTblInicio().changeSelection(0, 4, false, false);
        }
// Fin de sección de eventos para ventana de Administrador

// Inicio de sección de eventos para ventana de Docente
        if (e.getSource() == ventanaDocente.getBtnInicio()) {
            String[] nombreCompleto = this.user.getNombre().split(" ");
            String nombre = nombreCompleto[0];
            String apellido = "";
            if (nombreCompleto.length == 4) {
                apellido = nombreCompleto[2];
            } else {
                apellido = nombreCompleto[1];
            }
            ventanaDocente.getLblTitulo().setText("Bienvenido(a) " + nombre + " " + apellido);
            ventanaDocente.getTblInicio().setModel(new DefaultTableModel());
        } else if (e.getSource() == ventanaDocente.getBtnSalir()) {
            // this.ventanaDocente.setVisible(false);
            this.ventanaDocente.dispose();
            this.ventanaInicio.setVisible(true);
        } else if (e.getSource() == ventanaDocente.getBtnAsistencia()) {
            ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocentes(user, 1));
            agregarCheckBoxDocente();
        } else if (e.getSource() == ventanaDocente.getBtnMarcarAsistencia()) {
            marcarAsistencias();
        } else if (e.getSource() == ventanaDocente.getBtnBuscarAsistencia()) {
            ventanaDocente.getVentanaBuscarAsistencia().setVisible(true);
        } else if (e.getSource() == ventanaDocente.getVentanaBuscarAsistencia().getBtnCargarAsistencia()) {
            try {
                buscarAsistencias();
                ventanaDocente.getVentanaBuscarAsistencia().setVisible(false);
                ventanaDocente.getVentanaBuscarAsistencia().getfTxtFecha().setText("");
            } catch (NullPointerException ne) {
                ventanaDocente.mostrarMensaje("No existen asistencias con la fecha buscada o formato de fecha incorrecto.");
                System.out.println("Error: " + ne);
            }
        } else if (e.getSource() == ventanaDocente.getVentanaBuscarAsistencia().getBtnCancelar()) {
            ventanaDocente.getVentanaBuscarAsistencia().setVisible(false);
            ventanaDocente.getVentanaBuscarAsistencia().getfTxtFecha().setText("");
        } else if (e.getSource() == ventanaDocente.getBtnEstudiantes()) {
            ventanaDocente.getVentanaCargarFecha().setVisible(true);

        } else if (e.getSource() == ventanaDocente.getVentanaCargarFecha().getBtnCargar()) {
            mostrarEstudiantes();
        } else if (e.getSource() == ventanaDocente.getVentanaCargarFecha().getBtnCancelar()) {
            ventanaDocente.getVentanaCargarFecha().setVisible(false);
            ventanaDocente.getVentanaCargarFecha().getfTxtFecha().setText("");
            ventanaDocente.getTblInicio().setModel(new DefaultTableModel());
        } else if (e.getSource() == ventanaDocente.getBtnObservaciones()) {
            ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocentes(user, 3));
            // cargarDatos(user, 3);
        } else if (e.getSource() == ventanaDocente.getBtnAgregar()) {
            try {
                ventanaDocente.getVentanaCrudObservacion().getBtnCancelar().setText("Cancelar");
                seleccionarObservacion();
                ventanaDocente.getVentanaCrudObservacion().setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ae) {
                ventanaDocente.getVentanaCrudObservacion().setVisible(false);
                System.err.println("Error: " + ae);
                ventanaDocente.mostrarMensaje("Por favor, seleccione la fila del usuario que desea.");
            }
        } else if (e.getSource() == ventanaDocente.getVentanaCrudObservacion().getBtnAgregar()) {
            agregarObservacionCrud();
        } else if (e.getSource() == ventanaDocente.getVentanaCrudObservacion().getBtnCancelar()) {
            ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText("");
            ventanaDocente.getVentanaCrudObservacion().setVisible(false);
        } else if (e.getSource() == ventanaDocente.getBtnModificar()) {
            try {
                ventanaDocente.getVentanaCrudObservacion().getBtnCancelar().setText("Cancelar");
                seleccionarObservacion();
                ventanaDocente.getVentanaBuscarObservacion().setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ae) {
                ventanaDocente.getVentanaCrudObservacion().setVisible(false);
                System.err.println("Error: " + ae);
                ventanaDocente.mostrarMensaje("Por favor, seleccione la fila del usuario que desea.");
            }

        } else if (e.getSource() == ventanaDocente.getVentanaBuscarObservacion().getBtnCargar()) {
            buscarObservacion();
        } else if (e.getSource() == ventanaDocente.getVentanaBuscarObservacion().getBtnCancelar()) {
            ventanaDocente.getVentanaBuscarObservacion().setVisible(false);
            ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().setText("");
        } else if (e.getSource() == ventanaDocente.getVentanaCrudObservacion().getBtnModificar()) {
            modificarObservacion();
            ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().setText("");
        } else if (e.getSource() == ventanaDocente.getBtnEliminar()) {
            try {
                ventanaDocente.getVentanaCrudObservacion().getBtnCancelar().setText("Cancelar");
                seleccionarObservacion();
                ventanaDocente.getVentanaBuscarObservacion().setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ae) {
                ventanaDocente.getVentanaCrudObservacion().setVisible(false);
                System.err.println("Error: " + ae);
                ventanaDocente.mostrarMensaje("Por favor, seleccione la fila del usuario que desea.");
            }
        } else if (e.getSource() == ventanaDocente.getVentanaCrudObservacion().getBtnEliminar()) {
            eliminarObservacion();
        } else if (e.getSource() == ventanaDocente.getBtnVerObservaciones()) {
            try {
                seleccionarObservacion();
                ventanaDocente.getVentanaBuscarObservacion().setVisible(true);
                ventanaDocente.getVentanaCrudObservacion().getLabelTitulo().setText("Observación");
                ventanaDocente.getVentanaCrudObservacion().getBtnCancelar().setText("Cerrar");
                ventanaDocente.getVentanaCrudObservacion().getBtnAgregar().setVisible(false);
                // mostrarObservacion();
            } catch (ArrayIndexOutOfBoundsException ae) {
                ventanaDocente.getVentanaCrudObservacion().setVisible(false);
                System.err.println("Error: " + ae);
                ventanaDocente.mostrarMensaje("Por favor, seleccione la fila del usuario que desea.");
            }
        }

// Inicio de sección de eventos para ventana Estudiante
        if (e.getSource() == ventanaEstudiante.getBtnInicio()) {
            String[] nombreCompleto = this.user.getNombre().split(" ");
            String nombre = nombreCompleto[0];
            String apellido = "";
            if (nombreCompleto.length == 4) {
                apellido = nombreCompleto[2];
            } else {
                apellido = nombreCompleto[1];
            }
            ventanaEstudiante.getLblTitulo().setText("Bienvenido(a) " + nombre + " " + apellido);
        } else if (e.getSource() == ventanaEstudiante.getBtnAsistencias()) {
            ventanaEstudiante.getTblInicio().setModel(modelo.cargarDatosEstudiante(user, 1));
            agregarCheckBoxEstudiante();
        } else if (e.getSource() == ventanaEstudiante.getBtnObservaciones()) {
            ventanaEstudiante.getTblInicio().setModel(modelo.cargarDatosEstudiante(user, 2));
        } else if (e.getSource() == ventanaEstudiante.getBtnSalir()) {
            // this.ventanaEstudiante.setVisible(false);
            this.ventanaEstudiante.dispose();
            this.ventanaInicio.setVisible(true);
        } else if (e.getSource() == ventanaEstudiante.getVentanaObservacion().getBtnCerrar()) {
            this.ventanaEstudiante.getVentanaObservacion().setVisible(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == e.VK_ENTER && ventanaInicio.isActive()) {
            ventanaInicio.getBtnIngresar().doClick();
        }

        // Para Ventana de agregar docentes
        /* if(e.getKeyChar() == e.VK_ENTER){
            if(ventanaAdmin.isRegistrarDocente()){
                ventanaAdmin.getVentanaCrudDoc().getBtnAgregar().doClick();
            }
        }
         */
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

// Inicio de sección de metodos para Administrador
    // Agregar usuarios con las ventasnas CRUD de Administrador
    public void agregarUsuarioVentanasCrudAdmin() {

        if (ventanaAdmin.isRegistrarDocente()) {
            // Agregar usuario de tipo Docente

            String nombre = ventanaAdmin.getVentanaCrudDoc().getTxtNombre().getText();
            String password = ventanaAdmin.getVentanaCrudDoc().getTxtPassword().getText();
            String correo = ventanaAdmin.getVentanaCrudDoc().getTxtCorreo().getText();
            if (!nombre.equals("") && !password.equals("") && !correo.equals("")) {
                modelo.agregarUsuarios(1, new Docente(nombre, password, correo));
            } else {
                JOptionPane.showMessageDialog(null, "Los campos nombre, password o correo no pueden estar vacíos.");
            }
            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 1));

        } else if (ventanaAdmin.isRegistrarEstudiante()) {
            // Agregar usuario de tipo Estudiante

            String nombre = ventanaAdmin.getVentanaCrudEst().getTxtNombre().getText();
            String password = ventanaAdmin.getVentanaCrudEst().getTxtPassword().getText();
            String correo = ventanaAdmin.getVentanaCrudEst().getTxtCorreo().getText();
            String profesorAsignado = ((String) ventanaAdmin.getVentanaCrudEst().getCmbAsignarDoc().getSelectedItem());

            if (!nombre.equals("") && !password.equals("") && !correo.equals("")) {
                modelo.agregarUsuarios(2, new Estudiante(nombre, password, correo, profesorAsignado));
            } else {
                JOptionPane.showMessageDialog(null, "Los campos nombre, password o correo no pueden estar vacíos.");
            }

            limpiarDatosCrudVentanaAdmin(2);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 2));
        }
    }

    public void mostrarDatosUsuarioVentanasCrudAdmin() throws ArrayIndexOutOfBoundsException {
        if (ventanaAdmin.isRegistrarDocente()) {
            // Buscar y asignar datos de Docentes en JInternalFrame

            int fila = ventanaAdmin.getTblInicio().getSelectedRow();

            String nombre = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);
            String password = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 1);
            String correo = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 2);
            ventanaAdmin.getVentanaCrudDoc().setVisible(true);

            ventanaAdmin.getVentanaCrudDoc().getTxtNombre().setText(nombre);
            ventanaAdmin.getVentanaCrudDoc().getTxtPassword().setText(password);
            ventanaAdmin.getVentanaCrudDoc().getTxtCorreo().setText(correo);

        } else if (ventanaAdmin.isRegistrarEstudiante()) {
            // Buscar y asignar datos de Estudiantes en JInternalFrame

            int fila = ventanaAdmin.getTblInicio().getSelectedRow();

            String nombre = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);
            String password = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 1);
            String correo = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 2);
            String profesorAsignado = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 3);

            ventanaAdmin.getVentanaCrudEst().setVisible(true);

            ventanaAdmin.getVentanaCrudEst().getTxtNombre().setText(nombre);
            ventanaAdmin.getVentanaCrudEst().getTxtPassword().setText(password);
            ventanaAdmin.getVentanaCrudEst().getTxtCorreo().setText(correo);
            ventanaAdmin.getVentanaCrudEst().getCmbAsignarDoc().setModel(modelo.cargarListaDocentes());
            ventanaAdmin.getVentanaCrudEst().getCmbAsignarDoc().setSelectedItem(profesorAsignado);
        }
    }

    public void modificarUsuarioVentanasCrudAdmin() {
        if (ventanaAdmin.isRegistrarDocente()) {

            int fila = ventanaAdmin.getTblInicio().getSelectedRow();
            String nombreBuscar = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);

            String nombre = ventanaAdmin.getVentanaCrudDoc().getTxtNombre().getText();
            String password = ventanaAdmin.getVentanaCrudDoc().getTxtPassword().getText();
            String correo = ventanaAdmin.getVentanaCrudDoc().getTxtCorreo().getText();

            modelo.modificarUsuarios(new Docente(nombre, password, correo), new Docente(nombreBuscar, "", ""));

            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 1));
            
        } else if (ventanaAdmin.isRegistrarEstudiante()) {

            int fila = ventanaAdmin.getTblInicio().getSelectedRow();
            String nombreBuscar = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);
            String nombre = ventanaAdmin.getVentanaCrudEst().getTxtNombre().getText();
            String password = ventanaAdmin.getVentanaCrudEst().getTxtPassword().getText();
            String correo = ventanaAdmin.getVentanaCrudEst().getTxtCorreo().getText();
            String profesorAsignado = ((String) ventanaAdmin.getVentanaCrudEst().getCmbAsignarDoc().getSelectedItem());

            modelo.modificarUsuarios(new Estudiante(nombre, password, correo, profesorAsignado), new Estudiante(nombreBuscar, "", "", ""));

            limpiarDatosCrudVentanaAdmin(2);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 2));
        }
    }

    public void eliminarUsuarioVentanasCrudAdmin() {
        if (ventanaAdmin.isRegistrarDocente()) {

            int fila = ventanaAdmin.getTblInicio().getSelectedRow();
            String nombreBuscar = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);
            modelo.eliminarUsuarios(new Docente(nombreBuscar, "", ""));
            limpiarDatosCrudVentanaAdmin(1);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 1));

        } else if (ventanaAdmin.isRegistrarEstudiante()) {
            
            int fila = ventanaAdmin.getTblInicio().getSelectedRow();
            String nombreBuscar = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(fila, 0);
            modelo.eliminarUsuarios(new Estudiante(nombreBuscar, "", "", ""));
            limpiarDatosCrudVentanaAdmin(2);
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 2));
        }
    }

    // Tipo:
    // 1. Habilitar
    // 2. Deshabilitar
    public void habilitarUsuarioVentanaAdmin(int tipo) {
        try {
            int fila[] = ventanaAdmin.getTblInicio().getSelectedRows();
            // int fila = ventanaAdmin.getTblInicio().getSelectedRow();
            for (int i : fila) {
                String nombreBuscar = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(i, 1);
                String tipoUsuario = (String) ventanaAdmin.getTblInicio().getModel().getValueAt(i, 0);
                modelo.habilitarUsuarios(tipo, nombreBuscar, tipoUsuario);
            }
            ventanaAdmin.getTblInicio().setModel(modelo.cargarDatosAdministrador(user, 3));
            agregarCheckBoxAdmin();
            // cargarDatos(user, 3);
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.err.println("Error: " + ae);
            ventanaAdmin.mostrarDatos("Por favor, seleccione la fila del usuario que desea.");
        }
    }

    public void limpiarDatosCrudVentanaAdmin(int tipo) {
        if (tipo == 1) {
            ventanaAdmin.getVentanaCrudDoc().getTxtNombre().setText("");
            ventanaAdmin.getVentanaCrudDoc().getTxtPassword().setText("");
            ventanaAdmin.getVentanaCrudDoc().getTxtCorreo().setText("");
            ventanaAdmin.getVentanaCrudDoc().setVisible(false);
        } else if (tipo == 2) {
            ventanaAdmin.getVentanaCrudEst().getTxtNombre().setText("");
            ventanaAdmin.getVentanaCrudEst().getTxtPassword().setText("");
            ventanaAdmin.getVentanaCrudEst().getTxtCorreo().setText("");
            ventanaAdmin.getVentanaCrudEst().setVisible(false);
        }
    }
// Fin de sección de métodos para Administrador

    public void validarCredencialesUser() {
        String usuario = ventanaInicio.getTxtUsuario().getText();
        String password = "";
        for (Character c : ventanaInicio.getPwdUsuario().getPassword()) {
            password += c;
        }
        byte rol = (byte) ventanaInicio.getCmbRoles().getSelectedIndex();

        user = modelo.validarCredencialesUser(usuario, password, rol);

        if (user == null) {
            ventanaInicio.mostrarDatos("Usuario y/o contraseña incorrectos.");
        } else if (user instanceof Administrador) {
            if (((Administrador) user).isHabilitacion()) {
                ventanaInicio.mostrarDatos("Bienvenido Administrador.");
                // ventanaInicio.setVisible(false);
                ventanaInicio.dispose();
                ventanaAdmin.setVisible(true);
            } else {
                ventanaInicio.mostrarDatos("Usuario inhabilitado.");
            }

        } else if (user instanceof Docente) {
            if (((Docente) user).isHabilitacion()) {
                ventanaInicio.mostrarDatos("Bienvenido Docente.");
                // ventanaInicio.setVisible(false);
                ventanaInicio.dispose();
                ventanaDocente.setVisible(true);
            } else {
                ventanaInicio.mostrarDatos("Usuario inhabilitado.");
            }

        } else if (user instanceof Estudiante) {
            if (((Estudiante) user).isHabilitacion()) {
                ventanaInicio.mostrarDatos("Bienvenido Estudiante.");
                // ventanaInicio.setVisible(false);
                ventanaInicio.dispose();
                ventanaEstudiante.setVisible(true);
            } else {
                ventanaInicio.mostrarDatos("Usuario inhabilitado.");
            }

        }
    }

// Método para Administrador
    public void agregarCheckBoxAdmin() {
        TableColumn tc = ventanaAdmin.getTblInicio().getColumnModel().getColumn(4);
        tc.setCellEditor(ventanaAdmin.getTblInicio().getDefaultEditor(Boolean.class));
        tc.setCellRenderer(ventanaAdmin.getTblInicio().getDefaultRenderer(Boolean.class));
    }

    public void agregarCheckBoxDocente() {
        TableColumn tc = ventanaDocente.getTblInicio().getColumnModel().getColumn(1);
        tc.setCellEditor(ventanaDocente.getTblInicio().getDefaultEditor(Boolean.class));
        tc.setCellRenderer(ventanaDocente.getTblInicio().getDefaultRenderer(Boolean.class));
    }

    public void agregarCheckBoxEstudiante() {
        TableColumn tc = ventanaEstudiante.getTblInicio().getColumnModel().getColumn(1);
        tc.setCellEditor(ventanaEstudiante.getTblInicio().getDefaultEditor(Boolean.class));
        tc.setCellRenderer(ventanaEstudiante.getTblInicio().getDefaultRenderer(Boolean.class));
    }

    public void marcarAsistencias() {
        for (int i = 0; i < ventanaDocente.getTblInicio().getRowCount(); i++) {
            modelo.marcarAsistencias((Docente) user, (String) ventanaDocente.getTblInicio().getValueAt(i, 0),
                    (boolean) ventanaDocente.getTblInicio().getValueAt(i, 1),
                    (String) ventanaDocente.getTblInicio().getValueAt(i, 2));
        }
    }

    public void buscarAsistencias() throws NullPointerException {
        String fechaAsistencia = ventanaDocente.getVentanaBuscarAsistencia().getfTxtFecha().getText();
        ventanaDocente.getTblInicio().setModel(modelo.buscarAsistencias(user, fechaAsistencia));

        agregarCheckBoxDocente();
    }

    public void seleccionarObservacion() {
        int fila = ventanaDocente.getTblInicio().getSelectedRow();
        String estudiante = (String) ventanaDocente.getTblInicio().getModel().getValueAt(fila, 0);
        ventanaDocente.getVentanaCrudObservacion().getLblNombre().setText(estudiante);
    }

    public void agregarObservacionCrud() {
        String observacion = ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().getText();
        String nombreEstudiante = ventanaDocente.getVentanaCrudObservacion().getLblNombre().getText();
        boolean observacionAgregada = modelo.agregarObservacion(((Docente) user), nombreEstudiante, observacion);
        if (!observacionAgregada) {
            ventanaDocente.mostrarMensaje("Ya existe una observacion con la fecha del día de hoy.");
        }

        ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText("");
        ventanaDocente.getVentanaCrudObservacion().setVisible(false);

        ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocentes(user, 3));
    }

    public void buscarObservacion() {
        String fechaObservacion = ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().getText();
        String nombreEstudiante = ventanaDocente.getVentanaCrudObservacion().getLblNombre().getText();
        Observacion observacion = modelo.buscarObservacion(((Docente) user), nombreEstudiante, fechaObservacion);
        if (observacion == null) {
            ventanaDocente.mostrarMensaje("No existen observaciones con la fecha buscada o formato de fecha incorrecto.");
        } else {
            ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText(observacion.getObservacion());
            ventanaDocente.getVentanaCrudObservacion().setVisible(true);
            ventanaDocente.getVentanaBuscarObservacion().setVisible(false);
        }
    }

    public void modificarObservacion() {
        String nombreEstudiante = ventanaDocente.getVentanaCrudObservacion().getLblNombre().getText();
        String observacion = ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().getText();
        String fechaObservacion = ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().getText();
        
        modelo.modificarObservacion(((Docente)user), nombreEstudiante, observacion, fechaObservacion);
        ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText("");
        ventanaDocente.getVentanaCrudObservacion().setVisible(false);
        
        ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocentes(user, 3));
    }

    public void eliminarObservacion() {
        String nombreEstudiante = ventanaDocente.getVentanaCrudObservacion().getLblNombre().getText();
        String fechaObservacion = ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().getText();
        
        modelo.eliminarObservacion(((Docente)user), nombreEstudiante, fechaObservacion);
        
        ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocentes(user, 3));
        
        // cargarDatos(user, 3);
        ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText("");
        ventanaDocente.getVentanaCrudObservacion().setVisible(false);
    }

    public void mostrarObservacion() {
        String nombreEstudiante = ventanaDocente.getVentanaCrudObservacion().getLblNombre().getText();
        String fechaObservacion = ventanaDocente.getVentanaBuscarObservacion().getfTxtFecha().getText();
        System.out.println("Fecha: " + fechaObservacion);
        String[] letrasFecha = fechaObservacion.split("/");
        fechaObservacion = "";
        for (int i = 0; i < letrasFecha.length; i++) {
            if (letrasFecha[i].charAt(0) == '0') {
                fechaObservacion += letrasFecha[i].charAt(1);
            } else {
                fechaObservacion += letrasFecha[i];
            }
            if (i != (letrasFecha.length - 1)) {
                fechaObservacion += "/";
            }
        }
        System.out.println("Fecha: " + fechaObservacion);
        Observacion observacion = modelo.buscarObservacion(((Docente)user), nombreEstudiante, fechaObservacion);
        ventanaDocente.getVentanaCrudObservacion().getTxtaObservacion().setText(observacion.getObservacion());
    }

    public void mostrarEstudiantes() {
        String fechaCargar = ventanaDocente.getVentanaCargarFecha().getfTxtFecha().getText();

        ventanaDocente.getTblInicio().setModel(modelo.cargarDatosDocenteAyO(((Docente)user), fechaCargar));
        if(modelo.isModificarCeldaAsis()){
            TableColumn tc = ventanaDocente.getTblInicio().getColumnModel().getColumn(1);
            tc.setCellEditor(ventanaDocente.getTblInicio().getDefaultEditor(String.class));
            tc.setCellRenderer(ventanaDocente.getTblInicio().getDefaultRenderer(String.class));
        } else {
            agregarCheckBoxDocente();
        }
        
        
        ventanaDocente.getVentanaCargarFecha().setVisible(false);
        ventanaDocente.getVentanaCargarFecha().getfTxtFecha().setText("");
    }

    public ArrayList<JFrame> getVentanas() {
        return ventanas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == ventanaEstudiante.getTblInicio()) {
            if (e.getClickCount() == 2) {
                if (ventanaEstudiante.isObservacion()) {
                    int fila = ventanaEstudiante.getTblInicio().getSelectedRow();
                    String fecha = String.valueOf(ventanaEstudiante.getTblInicio().getModel().getValueAt(fila, 2));
                    String docente = String.valueOf(ventanaEstudiante.getTblInicio().getModel().getValueAt(fila, 0));
                    Observacion observacion = modelo.buscarObservacion(new Docente(0, 0, docente
                            , "", "", true), ((Estudiante)user).getNombre(), fecha);
                    ventanaEstudiante.getVentanaObservacion().getTxtaObservacion().setText(observacion.getObservacion());
                    ventanaEstudiante.getVentanaObservacion().getLblFecha().setText(observacion.getFecha());
                    ventanaEstudiante.getVentanaObservacion().getLblProfesor().setText(((Estudiante) this.user).getProfesorAsignado());
                    ventanaEstudiante.getVentanaObservacion().setVisible(true);
                }
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
