/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad6;

/**
 *
 * @author Pawel
 */
public class PrzykladUczacy {


    private double[] data;
    private int classification;

    public PrzykladUczacy(double[] data, int classification) {
        this.data = data;
        this.classification = classification;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }



}
