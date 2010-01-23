/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad2;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Pawel
 */
public class MaszynaLiniowa {

    private int n;
    private Vector<Perceptron> perceptrony;
    private double[] wejscia;


    ////// Zad 2////
    Vector<PrzykladUczacy> przyklady;
    int random;

    ////

    public MaszynaLiniowa(int n, Vector<Perceptron> perceptrony,double[] wejscia) {
        this.n = n;
        this.perceptrony = perceptrony;
        this.wejscia = wejscia;
        ///// Zad 2
        przyklady = new PrzykladUczacy(20).cyferki();
        this.uczSie(100000);
    }

    public MaszynaLiniowa(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Vector<Perceptron> getPerceptrony() {
        return perceptrony;
    }

    public void setPerceptrony(Vector<Perceptron> perceptrony) {
        this.perceptrony = perceptrony;
    }

    public double getOut(int j) {
        return perceptrony.get(random).id(przyklady.get(random).getVal());
    }

    public int maxOut() {

        double max=0;
        int p=0;
        double d;

        for(int i=0;i<n;i++) {
            d = getOut(i);
            if(d>max) {
                max=d;
                p = i;
            }
        }

        return p;

    }

    public void uczSie(int iteCnt) {
        Random r = new Random();
        for(int i=0;i<iteCnt;i++) {
            random = r.nextInt(przyklady.size());
        //    System.out.println("Numer iteracji: " + i + "/" + iteCnt + " Random: " + random);
            PrzykladUczacy ex = przyklady.get(random);
            // max - indeks perceptronu z maxem
            int max = maxOut();
            if(ex.getResult()!=max) {
                perceptrony.get(ex.getResult()).dodajWagi(ex.getVal());
                perceptrony.get(max).odejmijWagi(ex.getVal());
            }
        }


    }

    public Vector<PrzykladUczacy> getPrzyklady() {
        return przyklady;
    }

    public void setPrzyklady(Vector<PrzykladUczacy> przyklady) {
        this.przyklady = przyklady;
    }


    public PrzykladUczacy getPrzyklad(int i) {
        return this.przyklady.get(i);
    }


}
