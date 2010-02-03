/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImageJPanel.java
 *
 * Created on 2009-11-30, 00:37:23
 */

package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cwiku
 */
public class ImageJPanel extends javax.swing.JPanel {

    private BufferedImage image=null;
    /** Creates new form ImageJPanel */
    public ImageJPanel() {
        initComponents();
    }

    public void setImage(BufferedImage img) {
        this.image = img;
    }

    public ImageJPanel(BufferedImage img) {
          initComponents();
          image = img;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(350, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
