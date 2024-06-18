package vista;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class VentanaBuscarObservacion extends JInternalFrame {

    private JPanel jpnTitulo, jpnFecha, jpnFormato, jpnBotones;
    private JLabel lblTitulo, lblFecha, lblFormatoFecha;
    private JFormattedTextField fTxtFecha;
    private JButton btnCargar, btnCancelar;
    private Font fuenteTitulo, fuenteTexto;

    public VentanaBuscarObservacion() {
        fuenteTitulo = new Font("Cambria", 0, 20);
        fuenteTexto = new Font("Cambria", 0, 14);

        jpnTitulo = new JPanel(new FlowLayout());
        jpnTitulo.setBackground(new Color(181, 199, 211));
        lblTitulo = new JLabel("Buscar Observación");
        lblTitulo.setFont(fuenteTitulo);
        jpnTitulo.add(lblTitulo);

        jpnFecha = new JPanel(new FlowLayout());
        jpnFecha.setBackground(new Color(181, 199, 211));
        lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(fuenteTexto);
        // fTxtFecha = new JFormattedTextField();
        MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("####-##-##");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        fTxtFecha = new JFormattedTextField(mascara);
        // fTxtFecha.setValue("");
        /*try {
            fTxtFecha.commitEdit();
        } catch (ParseException ex1) {
            ex1.printStackTrace();
        }*/
        // fTxtFecha.set
        fTxtFecha.setPreferredSize(new Dimension(100, 20));
        fTxtFecha.setFont(fuenteTexto);
        lblFormatoFecha = new JLabel("YYYY-MM-DD");
        lblFormatoFecha.setFont(fuenteTexto);
        jpnFecha.add(lblFecha);
        jpnFecha.add(fTxtFecha);
        jpnFecha.add(lblFormatoFecha);

        // jpnFormato = new JPanel(new FlowLayout());
        // jpnFormato.add(lblFormatoFecha);
        jpnBotones = new JPanel(new FlowLayout());
        jpnBotones.setBackground(new Color(181, 199, 211));
        btnCargar = new JButton("Cargar");
        btnCargar.setBackground(new java.awt.Color(181, 199, 211));
        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/green.png")));
        btnCargar.setFocusPainted(false);
        btnCargar.setFont(fuenteTexto);
        btnCargar.setBorder(null);
        btnCargar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(181, 199, 211));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/white.png")));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setFont(fuenteTexto);
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpnBotones.add(btnCargar);
        jpnBotones.add(btnCancelar);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.jpnTitulo, BorderLayout.NORTH);
        this.getContentPane().add(this.jpnFecha, BorderLayout.CENTER);
        // this.getContentPane().add(this.jpnFormato, BorderLayout.CENTER);
        this.getContentPane().add(this.jpnBotones, BorderLayout.SOUTH);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setBounds(0, 0, 300, 125);
        this.setVisible(false);
        this.setTitle("Observación");
        this.setResizable(false);
    }

    public JFormattedTextField getfTxtFecha() {
        return fTxtFecha;
    }

    public void setfTxtFecha(JFormattedTextField fTxtFecha) {
        this.fTxtFecha = fTxtFecha;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public void setBtnCargar(JButton btnCargar) {
        this.btnCargar = btnCargar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

}
