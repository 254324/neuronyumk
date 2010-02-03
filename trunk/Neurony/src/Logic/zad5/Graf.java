/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad5;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Pawel
 */
public class Graf {

    private int wymiar = 2;
    // wierzcholki==perceptrony
    private int iloscWierzcholkow = 0;
    // G = (V,E)
    private Vector<Perceptron> wierzcholki = null;
    private Vector<Vector<Integer>> krawedzie = null;

    private int iloscIteracji = 100000;

    public Graf(int iloscWierzcholkow) {

        this.iloscWierzcholkow = iloscWierzcholkow;
        ustawWierzcholki();
        ustawKrawedzie();
    }

    public Graf(int wymiar, int iloscWierzcholkow) {
        this.wymiar = wymiar;
        this.iloscWierzcholkow = iloscWierzcholkow;
        ustawWierzcholki();
        ustawKrawedzie();
    }


    private void ustawWierzcholki() {

        wierzcholki = new Vector();
        for(int i=0; i< iloscWierzcholkow; i++) {
            Perceptron p = new Perceptron(wymiar);
            p.losujWagi(1);
            wierzcholki.add(p);
        }

    }

    private void ustawKrawedzie() {
        // siec jest lancuchowa p1-p2-...-pX

        krawedzie = new Vector();

        for(int i=0; i<iloscWierzcholkow; i++) {
            Vector krawedz = new Vector();
            if(i!=0) {
                krawedz.add(new Integer(i-1));
            }
            if (i != iloscWierzcholkow - 1) {
                krawedz.add(new Integer(i + 1));
            }
            krawedzie.add(krawedz);
        }

    }

    public int getIloscIteracji() {
        return iloscIteracji;
    }

    public int getWymiar() {
        return wymiar;
    }

    public void setIloscWierzcholkow(int iloscWierzcholkow) {
        if (iloscWierzcholkow <= 0) {
            return;
        }
        this.iloscWierzcholkow = iloscWierzcholkow;
        ustawWierzcholki();
        ustawKrawedzie();
    }

    public int getIloscWierzcholkow() {
        return iloscWierzcholkow;
    }

    public void setIloscIteracji(int iloscIteracji) {
        this.iloscIteracji = iloscIteracji;
    }


    public Perceptron pobierzWierzcholek(int i) {
        return (Perceptron) wierzcholki.elementAt(i);
    }

    public int pobierzIloscSasiadow(int i) {
        return ((Vector) krawedzie.elementAt(i)).size();
    }
    
    public int pobierzNumerSasiadaWierzcholka(int wierzcholek, int sasiad) {
        return ((Integer) ((Vector) krawedzie.elementAt(wierzcholek)).elementAt(sasiad)).intValue();
    }

    public Perceptron pobierzSasiada(int i, int j) {
        int nr = ((Integer) ((Vector) krawedzie.elementAt(i)).elementAt(j)).intValue();
        return pobierzWierzcholek(nr);
    }

    // odleglosc punktu od wierzcholka
    public double pobierzOdleglosc(Punkt punkt, Perceptron wierzcholek) {
        double result = 0;
        for (int i = 0; i < wymiar; i++) {
            double d = punkt.pobierzWspolrzedna(i) - wierzcholek.getWage(i);
            result += d * d;
        }
        return Math.sqrt(result);
    }

    public int pobierzNumerNajblizszegoWierzcholka(Punkt punkt) {
        int nearestNumber = 0;
        double nearestDistance = pobierzOdleglosc(punkt, pobierzWierzcholek(0));
        for (int i = 0; i < iloscWierzcholkow; i++) {
            Perceptron perc = pobierzWierzcholek(i);
            double distance = pobierzOdleglosc(punkt, perc);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestNumber = i;
            }
        }
        return nearestNumber;
    }

    public double alpha(int t) {
        return 1 - (t - 1.0) / iloscIteracji;
    }

    private void edytujWagi(int v, int t, Punkt punkt) {
        Perceptron perceptron = pobierzWierzcholek(v);
        for (int i = 0; i < wymiar; i++) {
            double w = perceptron.getWage(i);
            double ww = 0;
            w = w + alpha(t) * (punkt.pobierzWspolrzedna(i) - w);
     //       ww = (alpha(t)*w)+(1-alpha(t)*punkt.pobierzWspolrzedna(i));
            perceptron.ustawWage(i, w);
     //       perceptron.ustawWage(i, ww);
        }
    }


    public void Kohonen(Vector<Punkt> examples) {
        // liczba powtorzen ustalona
        // wagi wylosowane
        Random r = new Random();
        for (int t = 0; t < iloscIteracji; t++) {
            Punkt point = (Punkt) examples.elementAt(r.nextInt(examples.size()));
            int v = pobierzNumerNajblizszegoWierzcholka(point);

            edytujWagi(v, t, point);

            // korekcja wag sasiadow
            for (int j = 0; j < pobierzIloscSasiadow(v); j++) {
                int s = pobierzNumerSasiadaWierzcholka(v, j);
                edytujWagi(s, t, point);
            }
        }
    }


}
