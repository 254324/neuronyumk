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


    ////// Zad 3////
    Vector<PrzykladUczacy> przyklady;
    Vector<PrzykladUczacy> przykladyWlasciwe = new Vector<PrzykladUczacy>();
    int random;

    ////

    public Autoasocjator(int n, Vector<Perceptron> perceptrony) {
        this.n = n;
        this.perceptrony = perceptrony;
     //   this.wejscia = new double[20];
        ///// Zad 3
        Random r = new Random();
        przyklady = new PrzykladUczacy(20).cyferki();
        for(PrzykladUczacy pu : przyklady) {
                for(int i=0;i<pu.getVal().length;i++) {
                    if(r.nextDouble()<0.1) {
                        System.out.println("Szum dla " + i);
                        pu.getVal()[i]=-pu.getVal()[i];
                    }
                }
        }
        this.uczSie(100000);
        przyklady = new PrzykladUczacy(20).cyferki();
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
            System.out.println("Perceptorn (" + perceptrony.indexOf(p) + " ): " + p.getProg() + " :: " + p.getWagi()[0] + " " +p.getWagi()[19]);
        }

    }

    public void stworzPrzyklady(int indeks) {
  //      System.out.println("Tworze przyklad dla perceptrony o indeksie: " + indeks);
        przykladyWlasciwe = new Vector<PrzykladUczacy>();
        for(int i=0;i<przyklady.size();i++) {
            int res =(int)przyklady.get(i).getVal()[indeks];
    //        System.out.println("("+indeks+", przyklad " + i +" ) Mamy res: " + res);
            String out="";
            for(int j=0;j<przyklady.get(i).getVal().length;j++) {
                out+=przyklady.get(i).getVal()[j]+"(" +j+") ";
            }
      //      System.out.println(out);
      //      System.out.println("****************");
     //       System.out.println("ROZMIAR PRZYKLADOW UCZACYCH ATM: " + przykladyWlasciwe.size());
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

    public int[] testInput(int[] values) {
       int[] result = new int[20];
       // System.out.println("Rozmiar perceptornow: " + perceptrony.size());
        for (int x = 0; x < perceptrony.size(); x++) {
            int res = perceptrony.get(x).calculate(values);
            result[x] = res;
        }
        return result;
    }


}
