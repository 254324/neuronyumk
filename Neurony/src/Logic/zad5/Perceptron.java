/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad5;

import java.util.Random;

/**
 *
 * @author Pawel
 */
public class Perceptron {

    private int wymiar = 2;
    private double[] wagi = null;

    public Perceptron(int dimension) {
        super();
        this.wymiar = dimension;
        this.wagi = new double[dimension];
    }

    public double getWage(int i) {
        return wagi[i];
    }

    public void ustawWage(int i, double val) {
        wagi[i] = val;
    }

    /** Losuje wagi z przedzialu (0..d)
     * @param d gorna granica wag
     */
    public void losujWagi(double d) {
        if (d == 0) {
            d = 1;
        }
        double epslion = 0.0001;
        Random r = new Random();
        for (int i = 0; i < wymiar; i++) {
            double w = r.nextDouble() + epslion;
            w = w * d;
            wagi[i] = w;
        }
    }
}
