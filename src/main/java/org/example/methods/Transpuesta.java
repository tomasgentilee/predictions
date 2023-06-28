package org.example.methods;

public class Transpuesta {
    public static double[][] trans(double[][]data){

        double[][] matrizT = new double[data[0].length][data.length];

        for (int x=0; x < data.length; x++) {
            for (int y=0; y < data[x].length; y++) {
                matrizT[y][x] = data[x][y];
            }
        }

        return matrizT;
    }
}
