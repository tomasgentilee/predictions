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

        double[][] multiLinRegEq = performMultipleLinearRegression(trainingData, trainingDataY);

        Scanner scn = new Scanner(System.in);
        double [] data2predict = new double[trainingData[0].length];

        System.out.println("Ingrese los valores a predecir:");
        for (int i = 0; i < trainingData[0].length; i++){
            data2predict[i] = scn.nextInt();
        }

        double prediction = predict(data2predict, multiLinRegEq);
        double[] estimationError = estimationErrorAndDeterminationCoe(trainingData, multiLinRegEq, trainingDataY);

        System.out.println("Predicción:");
        System.out.println(prediction);

        System.out.println("Error de estimación");
        System.out.println(estimationError[0]);

        System.out.println("Coeficiente de determinación");
        System.out.println(estimationError[1] * 100 + "%");

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

    public static double predict(double[] input, double[][] modeloRegMultiple) {

        double prediction = modeloRegMultiple[0][0];


        for (int i = 0; i < input.length; i ++){
            prediction += input[i] * modeloRegMultiple[i+1][0];
        }

        return prediction;
    }

    public static double[] estimationErrorAndDeterminationCoe(double[][] trainingData, double[][] modeloRegMultiple, double[][] trainingDataY) {

        double[] promiseY = new double[trainingData.length];
        double[] promiseY4Reg = new double[trainingData.length];
        double prediction = modeloRegMultiple[0][0];
        double sumSquareErr = 0;
        double sumSquareReg = 0;
        double sumY = 0;
        double promY = 0;
        double R2 = 0;

        //calculamos el pronostico para Y con nuestro modelo
        for (int i = 0; i < trainingData.length; i ++){
            double sumYpromise = 0;
            for (int j = 0; j < modeloRegMultiple.length-1; j ++){
                sumYpromise += trainingData[i][j] * modeloRegMultiple[j+1][0];
            }
            promiseY[i] += prediction + sumYpromise;
        }

        //CALCULO ERROR ESTANDAR DE ESTIMACIÓN

        //le restamos a nuestro Y el pronostico de Y
        for (int i = 0; i < trainingData.length; i ++){
            promiseY[i] = trainingDataY[i][0] - promiseY[i];
        }

        //suma de cuadrados del error
        for (int i = 0; i < trainingData.length; i ++){
            sumSquareErr += promiseY[i] * promiseY[i];
        }

        //calculamos el error estandar de la estimación
        double sYK = Math.sqrt((sumSquareErr)/(trainingData.length - modeloRegMultiple.length));


        //CALCULO COEFICIENTE DE DETERMINACIÓN

        //calculamos la media de Y
        for (int i = 0; i < trainingDataY.length; i ++){
            sumY += trainingDataY[i][0];
        }

        promY = sumY/trainingData.length;

        //realizamos la suma de cuadrados de la regresión la cual es la sumatoría de:
        //(promiseY[n]-promY[n])^2

        for (int i = 0; i < trainingData.length; i ++){
            double sumYpromiseReg = 0;
            for (int j = 0; j < modeloRegMultiple.length-1; j ++){
                sumYpromiseReg += trainingData[i][j] * modeloRegMultiple[j+1][0];
            }
            promiseY4Reg[i] += (prediction + sumYpromiseReg - promY) * (prediction + sumYpromiseReg - promY);
        }

        for (int i = 0; i < promiseY4Reg.length; i ++){
            sumSquareReg += promiseY4Reg[i];
        }

        R2 = sumSquareReg / (sumSquareReg + sumSquareErr);


        return new double[] {sYK, R2};
    }

}
