/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Rafael
 */
public class PrincipalFrame extends JFrame{
    
    private Controlador controlador;
    private JButton btnChoose;
    private JPasswordField pssLlave;
    private JButton btnAceptar;
    private JLabel lblArchivo;
    private JLabel other;
    
    public PrincipalFrame() {
        super("Encriptador");
        this.controlador = new Controlador();
        super.setSize(430, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        
        super.add(this.crearPanelBotones(),BorderLayout.SOUTH);
        super.add(this.crearPanelTrabajo(),BorderLayout.CENTER);
        super.setResizable(false);
        super.setVisible(true);
    }
    
    private JPanel crearPanelTrabajo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.pssLlave = new JPasswordField(20);
        this.btnAceptar = new JButton("Aceptar");
        this.lblArchivo = new JLabel("Archivo: No seleccionado");
        
        this.btnAceptar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String pass = new String(PrincipalFrame.this.pssLlave.getPassword());
                        PrincipalFrame.this.controlador.setLlave(pass);
                        System.out.println(pass);
                        PrincipalFrame.this.btnChoose.setEnabled(true);
                    }
                }).start();
            }
        });
        panel.add(new JLabel("Contraseña: "));
        panel.add(this.pssLlave);
        panel.add(this.btnAceptar);
        panel.add(this.lblArchivo);
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        this.btnChoose = new JButton("Seleccionar archivo");
        JFileChooser fileChooser = new JFileChooser();
        Imagen solve = new Imagen("/images/lock.png");
        Imagen desen = new Imagen("/images/open-lock.png");

        panel.setLayout(new FlowLayout());
        btnChoose.setEnabled(false);
        solve.setEnabled(false);
        desen.setEnabled(false);
        
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int returnVal = fileChooser.showOpenDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                        controlador.setPath(fileChooser.getSelectedFile().getAbsolutePath());
                        //other.setText(fileChooser.getSelectedFile().getParentFile());
                        controlador.setPath2(fileChooser.getSelectedFile().getParent()+"\\Encriptado\\input.txt");
                        lblArchivo.setText("Archivo: "+fileChooser.getSelectedFile().getParent());

                        solve.setEnabled(true);
                        desen.setEnabled(true);
                    }
            }
            }
        );
        
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.encriptador();
                    JOptionPane.showMessageDialog(PrincipalFrame.this,String.format("El archivo se generó con éxito"));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,"Ocurrió un error al encriptar el archivo");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        desen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.desencriptar();
                    JOptionPane.showMessageDialog(PrincipalFrame.this,String.format("El archivo se generó con éxito"));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,"Ocurrió un error al desencriptar el archivo");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        panel.add(btnChoose);
        panel.add(solve);
        panel.add(desen);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        return panel;
    }
}
