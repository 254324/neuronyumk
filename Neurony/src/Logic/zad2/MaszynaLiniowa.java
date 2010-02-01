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
        Random r = new Random();

        Vector<PrzykladUczacy> przyklady2 = new Vector<PrzykladUczacy>();
        przyklady2 = (Vector<PrzykladUczacy>) przyklady.clone();

        for(PrzykladUczacy pu : przyklady) {
       //     for(int j=0;j<100;j++) {
                for(int i=0;i<pu.getVal().length;i++) {
                    if(r.nextDouble()<0.15) {
                        System.out.println("Szum dla " + i);
                        pu.getVal()[i]=-pu.getVal()[i];
                    }
                }
         //   }
        }
        this.uczSie(100000);
        przyklady = new PrzykladUczacy(20).cyferki();
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
            PrzykladUczacy ex = przyklady.get(random);
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


}
