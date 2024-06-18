/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Ingenieria
 */
public class VentanaBuscarAsistencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form VentanaBuscarAsistencia
     */
    public VentanaBuscarAsistencia() {
        initComponents();
       
            /*MaskFormatter mascara = null;
            try {
            mascara = new MaskFormatter("##/##/####");
            } catch (ParseException ex) {
            ex.printStackTrace();
            }
            fTxtFecha = new JFormattedTextField(mascara);
            fTxtFecha.setValue("");
            try {
            fTxtFecha.commitEdit();
            } catch (ParseException ex1) {
            ex1.printStackTrace();
            }*/
            /*MaskFormatter mascara = null;
            
            try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
            mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            fTxtFecha = new JFormattedTextField(mascara);
            fTxtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(dateFormat)));
            } catch (ParseException ex) {
            ex.printStackTrace();
            }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnBuscar = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        fTxtFecha = new javax.swing.JFormattedTextField();
        lblFormatoFecha = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        jpnBotones = new javax.swing.JPanel();
        btnCargarAsistencia = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnBuscar.setBackground(new java.awt.Color(181, 199, 211));
        jpnBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        lblTitulo.setText("Buscar Asistencia");
        jpnBuscar.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 18, -1, -1));

        lblFecha.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblFecha.setText("Fecha:");
        jpnBuscar.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 60, -1, -1));

        try {
            fTxtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jpnBuscar.add(fTxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 58, 220, -1));

        lblFormatoFecha.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        lblFormatoFecha.setText("AAAA-MM-DD");
        jpnBuscar.add(lblFormatoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 86, -1, -1));

        lblTitulo1.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        lblTitulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/monocromatico.png"))); // NOI18N
        jpnBuscar.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 27, -1, -1));

        getContentPane().add(jpnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 553, 120));

        jpnBotones.setBackground(new java.awt.Color(181, 199, 211));
        jpnBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCargarAsistencia.setBackground(new java.awt.Color(181, 199, 211));
        btnCargarAsistencia.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnCargarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/grey.png"))); // NOI18N
        btnCargarAsistencia.setText("Cargar Asistencia");
        btnCargarAsistencia.setBorder(null);
        btnCargarAsistencia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCargarAsistencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCargarAsistencia.setPreferredSize(new java.awt.Dimension(130, 30));
        jpnBotones.add(btnCargarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 22, 134, 38));

        btnCancelar.setBackground(new java.awt.Color(181, 199, 211));
        btnCancelar.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/white.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setPreferredSize(new java.awt.Dimension(80, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jpnBotones.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 100, 40));

        getContentPane().add(jpnBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, 553, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarAsistencia;
    private javax.swing.JFormattedTextField fTxtFecha;
    private javax.swing.JPanel jpnBotones;
    private javax.swing.JPanel jpnBuscar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFormatoFecha;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnCargarAsistencia() {
        return btnCargarAsistencia;
    }

    public void setBtnCargarAsistencia(JButton btnCargarAsistencia) {
        this.btnCargarAsistencia = btnCargarAsistencia;
    }

    public JFormattedTextField getfTxtFecha() {
        return fTxtFecha;
    }

    public void setfTxtFecha(JFormattedTextField fTxtFecha) {
        this.fTxtFecha = fTxtFecha;
    }
    
    
}
