/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad3;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Pawel
 */
public class Autoasocjator {

    private int n;
    private Vector<Perceptron> perceptrony;
    private double[] wejscia;


    ////// Zad 2////
    Vector<PrzykladUczacy> przyklady;
    Vector<PrzykladUczacy> przykladyWlasciwe = new Vector<PrzykladUczacy>();
    int random;

    ////

    public Autoasocjator(int n, Vector<Perceptron> perceptrony) {
        this.n = n;
        this.perceptrony = perceptrony;
        this.wejscia = new double[20];
        ///// Zad 3
        przyklady = new PrzykladUczacy(20).cyferki();
        this.uczSie(100000);
    }

    public Autoasocjator(int n) {
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
        for(Perceptron p : perceptrony) {
           stworzPrzyklady(perceptrony.indexOf(p));
           p.pocketLearning(przykladyWlasciwe);
      //      System.out.println("Perceptorn: " + p.getProg() + " :: " + p.getWagi()[0] + " " +p.getWagi()[1]);
        }

    }

    public void stworzPrzyklady(int indeks) {
   //     System.out.println("Tworze przyklad dla: " + indeks);
        for(int i=0;i<przyklady.size();i++) {
            int res =(int)przyklady.get(i).getVal()[indeks];
     //       System.out.println("Dla i " + i + " mamy res: " + res);
     //       System.out.println("Tablica: " + przyklady.get(i).getVal().length);
            przykladyWlasciwe.add(new PrzykladUczacy(20,res,przyklady.get(i).getVal()));
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

    public int test(double[] values) {
        double maxOut = Double.MIN_VALUE;
        int perceptronNo = 0;
        for (Perceptron p : perceptrony){
                double dotProduct = p.id(values);
                if (dotProduct > maxOut){
                    maxOut = dotProduct;
                    perceptronNo = perceptrony.indexOf(p);
                }
                System.out.println(perceptrony.indexOf(p)+": "+dotProduct);
            }

        return perceptronNo;
    }

    public double[] testInput(double[] values) {
       double[] result = new double[20];
        for (int x = 0; x < perceptrony.size(); x++) {
            int res = perceptrony.get(x).calculate(values);
            result[x] = res;
        }
        return result;
    }


}
