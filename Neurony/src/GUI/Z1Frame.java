/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Z1Frame.java
 *
 * Created on 2010-01-14, 14:42:04
 */

package GUI;

import Logic.zad1.Perceptron;
import Logic.zad1.PrzykladUczacy;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Pawel
 */
public class Z1Frame extends javax.swing.JFrame {

    String filename;
    Perceptron p;
    Vector<PrzykladUczacy> przyklady;
    /** Creates new form Z1Frame */
    public Z1Frame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        z1DrawingPanel1 = new GUI.Z1DrawingPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ucz i rysuj");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Otworz");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        z1DrawingPanel1.setBackground(new java.awt.Color(204, 204, 204));
        z1DrawingPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout z1DrawingPanel1Layout = new javax.swing.GroupLayout(z1DrawingPanel1);
        z1DrawingPanel1.setLayout(z1DrawingPanel1Layout);
        z1DrawingPanel1Layout.setHorizontalGroup(
            z1DrawingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        z1DrawingPanel1Layout.setVerticalGroup(
            z1DrawingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(z1DrawingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(z1DrawingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        p = new Perceptron(2);
        przyklady = new PrzykladUczacy(2).wczytaj(filename);
        p.uczSie(przyklady);
        z1DrawingPanel1.setPrzyklady(przyklady);
        z1DrawingPanel1.setPerceptron(p);
        z1DrawingPanel1.repaint();
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       JFileChooser chooser = new JFileChooser();
       File f;
       chooser.setCurrentDirectory(new File("res\\"));
       int returnVal = chooser.showOpenDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
           f = chooser.getSelectedFile();
           filename = f.getPath();
       }
    }//GEN-LAST:event_jButton2MouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Z1Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private GUI.Z1DrawingPanel z1DrawingPanel1;
    // End of variables declaration//GEN-END:variables

}
