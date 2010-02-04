/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad6;

import java.util.Random;

/**
 *
 * @author Pawel
 */
public class Perceptron {

    private double[] wagi;
    int n;

    public Perceptron(double[] wagi, int n) {
        this.wagi = wagi;
        this.n = n;
    }

    public Perceptron(int n) {
        this.n = n;
        this.wagi = new double[n];
        Random r= new Random();
        for(int i=0;i<n;i++) {
            wagi[i]=r.nextDouble()/100.0;
        }
    }






    public double[] getWagi() {
        return wagi;
    }

    public void setWagi(double[] wagi) {
        this.wagi = wagi;
    }



    public double getActivation(double[] traning) {
        double activation = 0;

        for (int i = 0; i < n; ++i) {
            activation += traning[i] * getWagi()[i];
        }

        return activation;
    }

}
