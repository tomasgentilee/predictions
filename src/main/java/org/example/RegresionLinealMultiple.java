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

        RegresionLinealImp regresionLinealImp = new RegresionLinealImp();

        double[][] multiLinRegEq = regresionLinealImp.performMultipleLinearRegression(trainingData, trainingDataY);

        Scanner scn = new Scanner(System.in);
        double [] data2predict = new double[trainingData[0].length];

        System.out.println("Este programa predice el colesterol");

        System.out.println("Ingrese los valores a predecir:");
        for (int i = 0; i < trainingData[0].length; i++){
            data2predict[i] = scn.nextInt();
        }

        double prediction = regresionLinealImp.predictMult(data2predict, multiLinRegEq);
        double[] estimationError = regresionLinealImp.estimationErrorAndDeterminationCoeMult(trainingData, multiLinRegEq, trainingDataY);

        System.out.println("Predicción:");
        System.out.println(prediction);

        System.out.println("Error de estimación");
        System.out.println(estimationError[0]);

        System.out.println("Coeficiente de determinación");
        System.out.println(estimationError[1] * 100 + "%");

    }

}
