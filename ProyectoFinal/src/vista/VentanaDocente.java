
package vista;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class VentanaDocente extends javax.swing.JFrame {
    private VentanaCrudObservacion ventanaCrudObservacion;
    private VentanaBuscarAsistencia ventanaBuscarAsistencia;
    private VentanaBuscarObservacion ventanaBuscarObservacion;
    private VentanaCargarFecha ventanaCargarFecha;
    
    public VentanaDocente() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.btnAgregar.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
        this.btnMarcarAsistencia.setVisible(false);
        this.btnBuscarAsistencia.setVisible(false);
        this.btnSalir.setVisible(true);
        this.btnVerObservaciones.setVisible(false);
        
        ventanaCrudObservacion = new VentanaCrudObservacion();
        ventanaCrudObservacion.setVisible(false);
        this.desktopPanel.add(ventanaCrudObservacion);
        
        ventanaBuscarAsistencia = new VentanaBuscarAsistencia();
        ventanaBuscarAsistencia.setVisible(false);
        this.desktopPanel.add(ventanaBuscarAsistencia);
        
        ventanaBuscarObservacion = new VentanaBuscarObservacion();
        ventanaBuscarObservacion.setVisible(false);
        this.desktopPanel.add(ventanaBuscarObservacion);
        
        ventanaCargarFecha = new VentanaCargarFecha();
        ventanaCargarFecha.setVisible(false);
        this.desktopPanel.add(ventanaCargarFecha);
        
        JInternalFrame jif = new JInternalFrame();
        jif.setVisible(false);
        this.desktopPanel.add(jif);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new javax.swing.JDesktopPane();
        jpnTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTitulo2 = new javax.swing.JLabel();
        jpnCrud = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMarcarAsistencia = new javax.swing.JButton();
        btnVerObservaciones = new javax.swing.JButton();
        btnBuscarAsistencia = new javax.swing.JButton();
        jpnOpciones = new javax.swing.JPanel();
        btnAsistencia = new javax.swing.JButton();
        btnEstudiantes = new javax.swing.JButton();
        btnObservaciones = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        jpnDatos = new javax.swing.JPanel();
        scpTabla = new javax.swing.JScrollPane();
        tblInicio = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Docente");
        setResizable(false);

        desktopPanel.setLayout(new java.awt.BorderLayout());

        jpnTitulo.setBackground(new java.awt.Color(181, 199, 211));
        jpnTitulo.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jpnTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Docente");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitulo.setPreferredSize(new java.awt.Dimension(800, 60));
        jpnTitulo.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 669, -1));

        lblTitulo2.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        lblTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/profe.png"))); // NOI18N
        lblTitulo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitulo2.setPreferredSize(new java.awt.Dimension(800, 60));
        jpnTitulo.add(lblTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 0, 129, 95));

        desktopPanel.add(jpnTitulo, java.awt.BorderLayout.PAGE_START);

        jpnCrud.setBackground(new java.awt.Color(181, 199, 211));
        jpnCrud.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jpnCrud.setPreferredSize(new java.awt.Dimension(750, 80));
        jpnCrud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(181, 199, 211));
        btnSalir.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/white.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(null);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setFocusPainted(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(90, 30));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jpnCrud.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 120, 28));

        btnAgregar.setBackground(new java.awt.Color(181, 199, 211));
        btnAgregar.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/green.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jpnCrud.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 120, 20));

        btnModificar.setBackground(new java.awt.Color(181, 199, 211));
        btnModificar.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/beige.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.setFocusPainted(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jpnCrud.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 120, 20));

        btnEliminar.setBackground(new java.awt.Color(181, 199, 211));
        btnEliminar.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/red.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jpnCrud.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 120, 20));

        btnMarcarAsistencia.setBackground(new java.awt.Color(181, 199, 211));
        btnMarcarAsistencia.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnMarcarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/beige.png"))); // NOI18N
        btnMarcarAsistencia.setText("Marcar Asistencia");
        btnMarcarAsistencia.setBorder(null);
        btnMarcarAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMarcarAsistencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMarcarAsistencia.setPreferredSize(new java.awt.Dimension(140, 30));
        btnMarcarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarAsistenciaActionPerformed(evt);
            }
        });
        jpnCrud.add(btnMarcarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 120, 20));

        btnVerObservaciones.setBackground(new java.awt.Color(181, 199, 211));
        btnVerObservaciones.setFont(new java.awt.Font("Cambria", 0, 10)); // NOI18N
        btnVerObservaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/beige.png"))); // NOI18N
        btnVerObservaciones.setText("Ver Observaciones");
        btnVerObservaciones.setBorder(null);
        btnVerObservaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerObservaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVerObservaciones.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVerObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerObservacionesActionPerformed(evt);
            }
        });
        jpnCrud.add(btnVerObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 120, 20));

        btnBuscarAsistencia.setBackground(new java.awt.Color(181, 199, 211));
        btnBuscarAsistencia.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnBuscarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/white.png"))); // NOI18N
        btnBuscarAsistencia.setText("Buscar Asistencia");
        btnBuscarAsistencia.setBorder(null);
        btnBuscarAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscarAsistencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarAsistencia.setPreferredSize(new java.awt.Dimension(140, 30));
        btnBuscarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAsistenciaActionPerformed(evt);
            }
        });
        jpnCrud.add(btnBuscarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 120, 20));

        desktopPanel.add(jpnCrud, java.awt.BorderLayout.PAGE_END);

        jpnOpciones.setBackground(new java.awt.Color(181, 199, 211));
        jpnOpciones.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jpnOpciones.setPreferredSize(new java.awt.Dimension(170, 300));
        jpnOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAsistencia.setBackground(new java.awt.Color(181, 199, 211));
        btnAsistencia.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonEstudiante.png"))); // NOI18N
        btnAsistencia.setText("Marcar Asistencia");
        btnAsistencia.setBorder(null);
        btnAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAsistencia.setFocusPainted(false);
        btnAsistencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAsistencia.setPreferredSize(new java.awt.Dimension(150, 30));
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jpnOpciones.add(btnAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 40));

        btnEstudiantes.setBackground(new java.awt.Color(181, 199, 211));
        btnEstudiantes.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnEstudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonEstudiante.png"))); // NOI18N
        btnEstudiantes.setText("Mostrar Estudiantes");
        btnEstudiantes.setBorder(null);
        btnEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEstudiantes.setFocusPainted(false);
        btnEstudiantes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstudiantes.setPreferredSize(new java.awt.Dimension(150, 30));
        btnEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudiantesActionPerformed(evt);
            }
        });
        jpnOpciones.add(btnEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, 40));

        btnObservaciones.setBackground(new java.awt.Color(181, 199, 211));
        btnObservaciones.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnObservaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonEstudiante.png"))); // NOI18N
        btnObservaciones.setText("Observaciones");
        btnObservaciones.setBorder(null);
        btnObservaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnObservaciones.setFocusPainted(false);
        btnObservaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnObservaciones.setPreferredSize(new java.awt.Dimension(150, 30));
        btnObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservacionesActionPerformed(evt);
            }
        });
        jpnOpciones.add(btnObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, 40));

        btnInicio.setBackground(new java.awt.Color(181, 199, 211));
        btnInicio.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/botonEstudiante.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicio.setFocusPainted(false);
        btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInicio.setPreferredSize(new java.awt.Dimension(115, 30));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jpnOpciones.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 150, 40));

        desktopPanel.add(jpnOpciones, java.awt.BorderLayout.LINE_START);

        jpnDatos.setBackground(new java.awt.Color(181, 199, 211));
        jpnDatos.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N

        tblInicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scpTabla.setViewportView(tblInicio);

        javax.swing.GroupLayout jpnDatosLayout = new javax.swing.GroupLayout(jpnDatos);
        jpnDatos.setLayout(jpnDatosLayout);
        jpnDatosLayout.setHorizontalGroup(
            jpnDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDatosLayout.createSequentialGroup()
                .addComponent(scpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jpnDatosLayout.setVerticalGroup(
            jpnDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        desktopPanel.add(jpnDatos, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:

        // Ocultar ventana actual
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        ventanaCrudObservacion.getLabelTitulo().setText("Agregar Observación");
        ventanaCrudObservacion.getBtnAgregar().setVisible(true);
        ventanaCrudObservacion.getBtnModificar().setVisible(false);
        ventanaCrudObservacion.getBtnEliminar().setVisible(false);
        ventanaCrudObservacion.getBtnCancelar().setVisible(true);
        
        ventanaCrudObservacion.getTxtaObservacion().setEditable(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ventanaCrudObservacion.getLabelTitulo().setText("Modificar Observación");
        ventanaCrudObservacion.getBtnAgregar().setVisible(false);
        ventanaCrudObservacion.getBtnModificar().setVisible(true);
        ventanaCrudObservacion.getBtnEliminar().setVisible(false);
        ventanaCrudObservacion.getBtnCancelar().setVisible(true);
        
        ventanaCrudObservacion.getTxtaObservacion().setEditable(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        ventanaCrudObservacion.getLabelTitulo().setText("Eliminar Observación");
        ventanaCrudObservacion.getBtnAgregar().setVisible(false);
        ventanaCrudObservacion.getBtnModificar().setVisible(false);
        ventanaCrudObservacion.getBtnEliminar().setVisible(true);
        ventanaCrudObservacion.getBtnCancelar().setVisible(true);
        
        ventanaCrudObservacion.getTxtaObservacion().setEditable(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVerObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerObservacionesActionPerformed
        // TODO add your handling code here:
        ventanaCrudObservacion.getBtnAgregar().setVisible(false);
        ventanaCrudObservacion.getBtnEliminar().setVisible(false);
        ventanaCrudObservacion.getBtnModificar().setVisible(false);
        
        ventanaCrudObservacion.getTxtaObservacion().setEditable(false);
    }//GEN-LAST:event_btnVerObservacionesActionPerformed

    private void btnBuscarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAsistenciaActionPerformed
        // TODO add your handling code here:
        ventanaBuscarAsistencia.setVisible(true);
    }//GEN-LAST:event_btnBuscarAsistenciaActionPerformed

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
        // TODO add your handling code here:
        this.btnSalir.setVisible(false);
        this.btnAgregar.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
        this.btnVerObservaciones.setVisible(false);

        this.btnMarcarAsistencia.setVisible(true);
        this.btnBuscarAsistencia.setVisible(true);

        this.lblTitulo.setText("Marcar Asistencia");
        this.lblTitulo.setPreferredSize(new Dimension(800, 60));
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void btnEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudiantesActionPerformed
        // TODO add your handling code here:
        this.btnSalir.setVisible(false);
        this.btnAgregar.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
        this.btnMarcarAsistencia.setVisible(false);
        this.btnBuscarAsistencia.setVisible(false);
        this.btnVerObservaciones.setVisible(false);

        this.lblTitulo.setText("Mostrar Estudiantes");
        this.lblTitulo.setPreferredSize(new Dimension(800, 60));
    }//GEN-LAST:event_btnEstudiantesActionPerformed

    private void btnObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservacionesActionPerformed
        // TODO add your handling code here:
        this.btnSalir.setVisible(false);
        this.btnMarcarAsistencia.setVisible(false);
        this.btnBuscarAsistencia.setVisible(false);

        this.btnAgregar.setVisible(true);
        this.btnModificar.setVisible(true);
        this.btnEliminar.setVisible(true);
        this.btnVerObservaciones.setVisible(true);

        this.lblTitulo.setText("Observaciones");
        this.lblTitulo.setPreferredSize(new Dimension(800, 60));
    }//GEN-LAST:event_btnObservacionesActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        this.btnSalir.setVisible(true);

        this.btnAgregar.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
        this.btnMarcarAsistencia.setVisible(false);
        this.btnBuscarAsistencia.setVisible(false);
        this.btnVerObservaciones.setVisible(false);

        // this.labelTitulo.setText("Docente");
        this.lblTitulo.setPreferredSize(new Dimension(800, 60));
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnMarcarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarAsistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMarcarAsistenciaActionPerformed

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JButton btnBuscarAsistencia;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEstudiantes;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMarcarAsistencia;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnObservaciones;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerObservaciones;
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JPanel jpnCrud;
    private javax.swing.JPanel jpnDatos;
    private javax.swing.JPanel jpnOpciones;
    private javax.swing.JPanel jpnTitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JScrollPane scpTabla;
    private javax.swing.JTable tblInicio;
    // End of variables declaration//GEN-END:variables

    public JTable getTblInicio(){
        return tblInicio;
    }
    
    public void setTblInicio(JTable tblInicio){
        this.tblInicio = tblInicio;
    }
    
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnAsistencia() {
        return btnAsistencia;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEstudiantes() {
        return btnEstudiantes;
    }

    public JButton getBtnInicio() {
        return btnInicio;
    }

    public JButton getBtnMarcarAsistencia() {
        return btnMarcarAsistencia;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnObservaciones() {
        return btnObservaciones;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnBuscarAsistencia() {
        return btnBuscarAsistencia;
    }

    public void setBtnBuscarAsistencia(JButton btnBuscarAsistencia) {
        this.btnBuscarAsistencia = btnBuscarAsistencia;
    }

    public VentanaCrudObservacion getVentanaCrudObservacion() {
        return ventanaCrudObservacion;
    }

    public VentanaBuscarAsistencia getVentanaBuscarAsistencia() {
        return ventanaBuscarAsistencia;
    }

    public JButton getBtnVerObservaciones() {
        return btnVerObservaciones;
    }

    public void setBtnVerObservaciones(JButton btnVerObservaciones) {
        this.btnVerObservaciones = btnVerObservaciones;
    }

    public VentanaBuscarObservacion getVentanaBuscarObservacion() {
        return ventanaBuscarObservacion;
    }

    public VentanaCargarFecha getVentanaCargarFecha() {
        return ventanaCargarFecha;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    
    
}
