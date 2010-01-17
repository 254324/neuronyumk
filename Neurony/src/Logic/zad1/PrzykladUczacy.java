/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author Pawel
 */
public class PrzykladUczacy {
    int n;
    private int result;
    private double[] val;

    public PrzykladUczacy(int n) {
        this.n = n;
    }

    public int getResult() {
        return result;
    }



    public PrzykladUczacy(int n, int result, double[] val) {
        this.n = n;
        this.result = result;
        this.val = val;
    }


    public double bipolar(double[] d) {

        double sum = 0;
        if(val.length==d.length) {
            for(int i=0;i<d.length;i++) {
                sum+=d[i]*val[i];
            }
        }
        return sum;
    }

    public Vector<PrzykladUczacy> wczytaj(String filename) {

        Vector<PrzykladUczacy> vec = new Vector<PrzykladUczacy> ();
        try{

            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] str = strLine.split(" ");
                double[] db = {Double.parseDouble(str[0]), Double.parseDouble(str[1])};
                vec.add(new PrzykladUczacy(n,Integer.parseInt(str[2]),db ));
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return vec;

    }

    public double[] getVal() {
        return val;
    }



    @Override
    public String toString() {

       String str = null;

       str = "DANE (" + n + ") : " + val[0] + " " + val[1] + "Wynik:  " + result;

       return str;

    }

}
