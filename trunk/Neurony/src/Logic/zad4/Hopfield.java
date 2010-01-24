/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Pawel
 */
public class Hopfield {
    public static int iloscPrzykladow =4;
    public static int rozmiarPrzykladu = 100;
    public static int iloscIteracji = 1000;
    public int[][] przyklady;
    public double[][] wagi;

    public Hopfield(){
        try{
        czytajPrzyklady("res/hopfield");
        obliczWagi();
        } catch (Exception e) {}
    }

    private void czytajPrzyklady(String katalog) throws FileNotFoundException, IOException {
        przyklady = new int[iloscPrzykladow][rozmiarPrzykladu];
        wagi = new double[rozmiarPrzykladu][rozmiarPrzykladu];

        for(int i=0;i<iloscPrzykladow;i++) {
            BufferedReader br = new BufferedReader(new FileReader(katalog+"/przyklad"+i+".txt"));

            String s = "";
            int n = 0;
            while((s=br.readLine())!=null) {
                String[] tab = s.split(":");
                for(int j=0;j<tab.length;j++) {
                    przyklady[i][j+n*BoxPanel.SIZE] = (Integer.parseInt(tab[j])==255) ? 1:-1;
                }
                n++;
            }
            br.close();
        }
    }

    private void obliczWagi() {
        for(int i=0;i<rozmiarPrzykladu;i++) {
            for(int j=0;j<rozmiarPrzykladu;j++) {
                wagi[i][j]=0;
            }
        }
        for(int i=0;i<rozmiarPrzykladu;i++) {
            for(int j=0;j<rozmiarPrzykladu;j++) {
                for(int k=0;k<iloscPrzykladow;k++) {
                    if(i!=j) {
                        wagi[i][j] += (1.0/(double)iloscPrzykladow) * (przyklady[k][i]*przyklady[k][j]);
                    }
                }
            }
        }
        for(int i=0;i<rozmiarPrzykladu;i++) {
            wagi[i][i]=0;
        }
    }

    public int[][] getPrzyklady() {
        return przyklady;
    }

    public void recognize(int[] example, BoxPanel[][] boxes) {
        Random r = new Random();

        for (int i = 0; i < iloscIteracji; i++) {

            double sigma = 0;
            int exampleNumber = r.nextInt(rozmiarPrzykladu);
            for (int j = 0; j < rozmiarPrzykladu; j++) {
                sigma += ((double) example[j] * wagi[j][exampleNumber]);
            }

            int sgnSigma = (int) Math.signum(sigma);

            if (sgnSigma != example[exampleNumber]) {
                example[exampleNumber] = sgnSigma;
                int colorValue = (example[exampleNumber] == 1) ? 255 : 0;
                paintSpin(boxes, exampleNumber, colorValue);
            }
        }
    }

    private void paintSpin(BoxPanel[][] boxes, int exampleNumber, int colorValue) {
        boxes[exampleNumber / BoxPanel.SIZE][exampleNumber % BoxPanel.SIZE].setBackground(new Color(colorValue, colorValue, colorValue));
        boxes[exampleNumber / BoxPanel.SIZE][exampleNumber % BoxPanel.SIZE].setKolor(colorValue);
        boxes[exampleNumber / BoxPanel.SIZE][exampleNumber % BoxPanel.SIZE].paintImmediately(0, 0, 30, 30);
    }
    
}
