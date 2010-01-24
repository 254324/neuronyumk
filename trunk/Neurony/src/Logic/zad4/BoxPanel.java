/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad4;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Pawel
 */
public class BoxPanel extends JPanel {

    public static int SIZE = 10;
    public int kolor;

    public BoxPanel() {
        super();
        kolor = 255;
        this.setPreferredSize(new Dimension(25,25));
        this.setBackground(new Color(kolor,kolor,kolor));
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed (java.awt.event.MouseEvent evt) {

                BoxPanel bp = (BoxPanel) evt.getSource();
                if(bp.kolor==255) {
                    kolor = 0;
                    bp.setBackground(Color.BLACK);
                } else {
                    kolor=255;
                    bp.setBackground(Color.WHITE);
                }
            }
        });
        this.setVisible(true);
    }

    public int getKolor() {
        return kolor;
    }

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }

}
