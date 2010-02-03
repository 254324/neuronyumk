/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad5;

/**
 *
 * @author Pawel
 */
public class Punkt {

    private double[] wspolrzedne = null;

    public Punkt(double[] wsp) {
        this.wspolrzedne = wsp;
    }

    public double[] getWspolrzedne() {
        return wspolrzedne;
    }

    public void setWspolrzedne(double[] wspolrzedne) {
        this.wspolrzedne = wspolrzedne;
    }

    public double pobierzWspolrzedna(int i) {
        return wspolrzedne[i];
    }
    
}
