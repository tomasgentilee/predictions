package org.example;

import org.example.methods.RegresionLinealImp;

import java.util.Scanner;

public class RegresionLineal {
    public static void main(String[] args) {

        // Datos de entrenamiento
        double[][] trainingData = {
                {0, 32},
                {10, 50},
                {20, 68},
                {30, 86},
                {40, 104},
                {50, 122}
        };

        RegresionLinealImp regresionLinealImp = new RegresionLinealImp();

        // Llamada a la función para realizar la regresión lineal
        double[] coefficients = regresionLinealImp.performLinearRegression(trainingData);

        System.out.println("Este programa hace la conversión de grados Celcius a Fahrenheit");
        System.out.println("Ingrese grados Celsius:");
        // Valor de entrada para predecir
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextInt();

        // Predicción del valor en grados Fahrenheit
        double[] prediction = regresionLinealImp.predictLin(input, coefficients);



        // Imprimir la predicción
        System.out.println("Predicción: " + prediction[0] + "°F");

        // Imprimir la desviación
        System.out.println("standard deviation: " + prediction[1]);

        // Imprimir el coeficiente de correlación para la recta de regresión
        System.out.println("correlation coefficient: " + prediction[2]);

        //Imprimir el coeficiente de determinación r2
        double determinationPercent = (prediction[2] * prediction[2]) * 100;
        System.out.println("Determination coefficient: " + determinationPercent + "%");

    }
}
