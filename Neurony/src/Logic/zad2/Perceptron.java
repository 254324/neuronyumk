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
public class Perceptron {

    // liczba wejsc
    private int n;
    private double[] wagi;
    private double[] wejscia;
    private double prog;


    public Perceptron(int n) {
        this.n = n;
        Random r = new Random();
        prog = r.nextDouble();
        wejscia = new double[n];
        wagi = new double[n];
        for(int i=0;i<n;i++) {
            wagi[i] = r.nextDouble();
        }
    }

    public double suma() {

        double sum = 0;
        for(int i=0;i<n;i++) {
            sum+=wagi[i]*wejscia[i];
        }
        return sum;
    }


    public int bipolar(double p) {

        if(this.suma()>=p) {
            return 1;
        }
        else {
            return -1;
        }
    }

    public double id(double[] wejscia) {
        this.wejscia=wejscia;
        return this.suma();
    }

    public double getProg() {
        return prog;
    }

    public double[] getWagi() {
        return wagi;
    }



    public void uczSie(Vector<PrzykladUczacy> x) {

 //       Vector<PrzykladUczacy> x = new PrzykladUczacy(n).wczytaj(filename);
        pocketLearning(x);

    }

    public void pocketLearning (Vector<PrzykladUczacy> x) {

        Random r = new Random();
        int lifetime = 0;

        double[] maxWagi = new double[n];
        int maxLifetime = 0;
        double maxProg = 0;

        for(int i=0;i<n;i++) {
            wagi[i]= r.nextDouble();
        }

        int i =0 ;

        for(int j=0;j<1000000;j++) {

            i = r.nextInt(x.size());
            PrzykladUczacy ex = x.get(i);
            if(ex.bipolar(wagi)>=prog) {
                if(ex.getResult()==1) {
                    lifetime++;
                    if(lifetime>maxLifetime) {
                        maxLifetime=lifetime;
                        maxProg = prog;
                        maxWagi[0] = wagi[0];
                        maxWagi[1] = wagi[1];
                    }
                } else {
                    wagi[0] += ex.getResult()*ex.getVal()[0];
                    wagi[1] += ex.getResult()*ex.getVal()[1];
                    prog-=ex.getResult();
                    lifetime = 0;
                }
            } else {
                if(x.get(i).getResult()==-1) {
                    lifetime++;
                    if(lifetime>maxLifetime) {
                        maxLifetime=lifetime;
                        maxProg = prog;
                        maxWagi[0] = wagi[0];
                        maxWagi[1] = wagi[1];
                    }
                } else {
                    wagi[0] += ex.getResult()*ex.getVal()[0];
                    wagi[1] += ex.getResult()*ex.getVal()[1];
                    prog-=ex.getResult();
                    lifetime = 0;
                }
            }
        }

        wagi[0] = maxWagi[0];
        wagi[1] = maxWagi[1];
        prog = maxProg;



  /*     int dobrze = 0;
       int zle = 0;

       for(int j=0;j<x.size();j++) {
           double z = x.get(j).bipolar(maxWagi);
           if(z>=maxProg) {
               dobrze++;
           } else {
               zle++;
           }
       }

        System.out.println("Dobrze klasykowany: " + dobrze + " Zle: " + zle); */

    }

    public void dodajWagi(double[] add) {

        if(add.length==this.n) {
            for(int i=0;i<n;i++) {
                wagi[i]+=add[i];
            }
        }

    }

    public void odejmijWagi(double[] sub) {

        if(sub.length==this.n) {
            for(int i=0;i<n;i++) {
                wagi[i]-=sub[i];
            }
        }

    }

    public double[] getWejscia() {
        return wejscia;
    }

    public void setWejscia(double[] wejscia) {
        this.wejscia = wejscia;
    }




}
