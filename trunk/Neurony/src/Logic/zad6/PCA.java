/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.zad6;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 *
 * @author Pawel
 */
public class PCA {

    private static final int ILOSC_ITERACJI = 50000;
    private static final double LEARNING_CONST = 0.05;

    private int height;
    private int width;
    private int size;

    private String PATH = "D:\\UMK\\Neurony\\res\\oj\\";

    public Vector<BufferedImage> images;

    private double[] pc1;
    private double[] pc2;
    private double[] pc3;

    public Vector<BufferedImage> throws1;
    public Vector<BufferedImage> throws2;
    public Vector<BufferedImage> throws3;


    public void start() {

        //1. Ladujemy dane z obrazkow
        String path = PATH;
        File dir = new File(path);
        String[] files = dir.list();

        images = new Vector<BufferedImage>();
        Vector<PrzykladUczacy> traningData = new Vector<PrzykladUczacy>();

        for (String file : files) {
            try {
                images.add(ImageIO.read(new File(path + file)));
            } catch (IOException e) {
            }
        }

        height = images.get(0).getHeight();
        width = images.get(0).getWidth();
        size = height * width;

        for (BufferedImage image : images) {

            double[] data = new double[width * height];
            int k = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    data[k] = pobierzPoziomSzarosci(image, j, i);
                    k += 1;
                }
            }
            traningData.add(normalizuj(new PrzykladUczacy(data, 0)));
        }

        System.out.println("Obliczam pierwsza skladowa glowna");
        pc1 = learn(traningData, size, ILOSC_ITERACJI);
        System.out.println("Rzutujemy na plaszczyzne");
        Vector<PrzykladUczacy> pca1TraningData = changeTraningPca(traningData,  pc1);
        System.out.println("Tworzenie rzutów na SG1");
        throws1 = generateImages(images, width, height, pca1TraningData);

        System.out.println("Obliczam druga skladowa glowna");
        pc2 = learn(pca1TraningData, size, ILOSC_ITERACJI);
        System.out.println("Rzutujemy na plaszczyzne");
        Vector<PrzykladUczacy> pca2TraningData = changeTraningPca(pca1TraningData,  pc2);
        System.out.println("Tworzenie rzutów na SG2");
        throws2 = generateImages(images, width, height, pca2TraningData);

        System.out.println("Obliczam trzecia skladowa glowna");
        pc3 = learn(pca2TraningData, size, ILOSC_ITERACJI);
        System.out.println("Rzutujemy na plaszczyzne");
        Vector<PrzykladUczacy> pca3TraningData = changeTraningPca(pca2TraningData,  pc3);
        System.out.println("Tworzenie rzutów na SG3");
        throws3 = generateImages(images, width, height, pca3TraningData);
    }

     private int pobierzPoziomSzarosci(BufferedImage image, int x, int y) {
        int rgb[] = new int[3];
        Color color = new Color(image.getRGB(x, y));
        rgb[0] = color.getRed();
        rgb[1] = color.getGreen();
        rgb[2] = color.getBlue();
        return rgb[0];
    }


    public PrzykladUczacy normalizuj(PrzykladUczacy vector) {
        double sum = 0;
        for (double d : vector.getData()) {
            sum += d * d;
        }
        double len = Math.sqrt(sum);

        for (int i = 0; i < vector.getData().length; ++i) {
            vector.getData()[i] = vector.getData()[i] / len;
        }

        return vector;
    }

    public double[] learn(Vector<PrzykladUczacy> traningData, int size, int iteration) {
        Perceptron perceptron = new Perceptron (size);
        Random r = new Random();
        for (int j = 0; j < iteration; j++) {

            PrzykladUczacy traningVector = traningData.get(r.nextInt(traningData.size()));

            double activation = perceptron.getActivation(traningVector.getData());
            for (int i = 0; i < perceptron.getWagi().length; ++i) {
                perceptron.getWagi()[i] = (perceptron.getWagi()[i] + LEARNING_CONST * activation * (traningVector.getData()[i] - activation * perceptron.getWagi()[i]));
            }
        }
        return perceptron.getWagi();

    }

    private Vector<PrzykladUczacy> changeTraningPca(Vector<PrzykladUczacy> traningData, double[] pca1) {
        Vector<PrzykladUczacy> pcaTraningData = new Vector<PrzykladUczacy>();

        for (int i = 0; i < traningData.size(); ++i) {
            PrzykladUczacy vector = traningData.get(i);
            PrzykladUczacy pcaVector = new PrzykladUczacy(new double[vector.getData().length], 0);
            double number = iloczynSkalarny(pca1, vector.getData()) / iloczynSkalarny(pca1, pca1);
            for (int j = 0; j < vector.getData().length; ++j) {
                pcaVector.getData()[j] = vector.getData()[j] - pca1[j] * number;
            }
            pcaTraningData.add(pcaVector);
        }
        return pcaTraningData;
    }

    public double iloczynSkalarny(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; ++i) {
            result += a[i] * b[i];
        }
        return result;
    }

    private Vector<BufferedImage> generateImages(Vector<BufferedImage> images, int w, int h, Vector<PrzykladUczacy> traningData) {
        Vector<BufferedImage> result = new Vector<BufferedImage>();
        for (int i = 0; i < images.size(); ++i) {
            //change images 1
            BufferedImage changedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            int k1 = 0;
            double[] generatedRGB = generateRgb(traningData.get(i).getData());
            for (int i2 = 0; i2 < h; i2++) {
                for (int j = 0; j < w; j++) {
                    setPixelData(changedImage, j, i2, (int) generatedRGB[k1]);
                    k1 += 1;
                }
            }
            result.add(changedImage);
        }
        return result;
    }

    private double[] generateRgb(double[] data) {
        double[] result = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        //min
        double min = result[0];
        for (double d : result) {
            if (d < min) {
                min = d;
            }
        }

        //transform
        if (min < 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] - min;
            }
        }

        //get max
        double max = result[0];
        for (double d : result) {
            if (d > max) {
                max = d;
            }
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] * 255 / max;
            if (result[i] < 0) {
                result[i] = 0;
                //System.out.println("Ujemna");
            } else if (result[i] > 255.0) {
                result[i] = 255;
                //System.out.println("Za dużo");
            }
        }

        return result;
    }

    private void setPixelData(BufferedImage image, int x, int y, int color) {
        image.setRGB(x, y, new Color(color, color, color).getRGB());
    }


    public BufferedImage drawPca(int w, int h, double[] pca1) {
        //add pca
        BufferedImage pcaImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int k = 0;
        double[] newData = generateRgb(pca1);
        for (int ih = 0; ih < h; ih++) {
            for (int jw = 0; jw < w; jw++) {
                setPixelData(pcaImage, jw, ih, (int) newData[k]);
                k += 1;
            }
        }
        return pcaImage;
    }

    public BufferedImage getPc1(){
        return drawPca(width, height, pc1);
    }

    public BufferedImage getPc2(){
        return drawPca(width, height, pc2);
    }

    public BufferedImage getPc3(){
        return drawPca(width, height, pc3);
    }


}
