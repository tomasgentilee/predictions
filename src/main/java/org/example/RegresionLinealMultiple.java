package org.example;

import org.example.methods.*;

import java.util.Scanner;

public class RegresionLinealMultiple {
    public static void main (String[] args){
        double[][] trainingData = {
                {76, 80, 13.5},
                {61, 72, 12.1},
                {50, 70, 11.6},
                {94, 122, 12.5},
                {55, 75, 13.5},
                {61, 95, 14},
                {80, 120, 12.5},
                {52, 68, 14.5}
        };
        double[][] trainingDataY = {
                {250},
                {220},
                {200},
                {350},
                {210},
                {205},
                {285},
                {190}
        };

        double[][] matriz = performMultipleLinearRegression(trainingData, trainingDataY);

        System.out.println("Ingrese el valor a predecir:");

        Scanner scn = new Scanner(System.in);
        double input = scn.nextInt();

        System.out.println();



        /*
        int numRows = matriz.length;
        int numCols = matriz[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(matriz[row][col] + " ");
            }
            System.out.println();
        }

         */
    }

    public static double[][] performMultipleLinearRegression(double[][] data, double[][] trainingDataY) {
        int numRows = data.length;
        int numCols = data[0].length;

        // Crear una nueva matriz con un tamaño mayor y copiar los datos de data
        double[][] newData = new double[data.length][data[0].length + 1];
        for (int row = 0; row < numRows; row++) {
            System.arraycopy(data[row], 0, newData[row], 1, numCols);
            newData[row][0] = 1;  // Establecer el primer elemento de cada fila en 1
        }

        Transpuesta transpuesta = new Transpuesta();
        ProductoMatricial productoMatricial = new ProductoMatricial();
        Inverse gaussJordan = new Inverse();


        double[][] transMa = transpuesta.trans(newData);

        double[][] prodMa = productoMatricial.productoMatriz(transMa, newData);

        double[][] matrizGaussJordan = gaussJordan.invert(prodMa);

        double[][] prodMaTxY = productoMatricial.productoMatriz(transMa, trainingDataY);

        double[][] modeloRegMultiple = productoMatricial.productoMatriz(matrizGaussJordan, prodMaTxY);

        return modeloRegMultiple;

    };

    public static double predict(double input, double[][] multipleEquation) {

        double prediction = 0;


        for (int i = 0; i < multipleEquation.length; i ++){
            prediction += multipleEquation[0][i];
        }

        return prediction;
    }

}
