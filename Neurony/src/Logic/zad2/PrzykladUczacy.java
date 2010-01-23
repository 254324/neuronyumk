/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad2;

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

    public Vector<PrzykladUczacy> cyferki() {
         Vector<PrzykladUczacy> vec = new Vector<PrzykladUczacy> ();
         double[] zero = {1,1,1,1, 1,-1,-1,1,1,-1,-1,1, 1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 0, zero));

       double[] jeden = {-1,-1,-1,1, -1,-1,1,1, -1,1,-1,1, -1,-1,-1,1, -1,-1,-1,1};
       vec.add(new PrzykladUczacy(20, 1, jeden));

       double[] dwa = {1,1,1,1, -1,-1,-1,1, 1,1,1,1, 1,-1,-1,-1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 2, dwa));

       double[] trzy = {1,1,1,1, -1,-1,-1,1, 1,1,1,1, -1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 3, trzy));

       double[] cztery = {1,-1,-1,-1, 1,-1,1,-1, 1,1,1,1, -1,-1,1,-1, -1,-1,1,-1};
       vec.add(new PrzykladUczacy(20, 4, cztery));

       double[] piec = {1,1,1,1, 1,-1,-1,-1, 1,1,1,1, -1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 5, piec));

       double[] szesc = {1,1,1,1, 1,-1,-1,-1, 1,1,1,1, 1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 6, szesc));

       double[] siedem = {1,1,1,1, -1,-1,-1,1, -1,-1,1,1-1, -1,1,-1,-1, 1,-1,-1,-1};
       vec.add(new PrzykladUczacy(20, 7, siedem));

       double[] osiem = {1,1,1,1, 1,-1,-1,1, 1,1,1,1, 1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 8, osiem));

       double[] dziewiec = {1,1,1,1, 1,-1,-1,1, 1,1,1,1, -1,-1,-1,1, 1,1,1,1};
       vec.add(new PrzykladUczacy(20, 9, dziewiec));

         return vec;
        
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
