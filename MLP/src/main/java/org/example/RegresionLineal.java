package org.example;

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

        // Llamada a la función para realizar la regresión lineal
        double[] coefficients = performLinearRegression(trainingData);

        // Valor de entrada para predecir
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextInt();

        // Predicción del valor en grados Fahrenheit
        double[] prediction = predict(input, coefficients);



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

    // Función para realizar la regresión lineal
    public static double[] performLinearRegression(double[][] data) {
        int n = data.length;
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        double sumY2 = 0;

        // Calcular las sumatorias
        for (int i = 0; i < n; i++) {
            double x = data[i][0];
            double y = data[i][1];

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
            sumY2 += y * y;
        }

        // Calcular los coeficientes
        double meanX = sumX / n;
        double meanY = sumY / n;
        double slope = (sumXY - n * meanX * meanY) / (sumX2 - n * meanX * meanX);
        //En el calculo de slope se calcula b, la inclinación de la recta.
        double intercept = meanY - slope * meanX;
        // la ecuación que utilizo es a = promedio del valor de las y - (pendiente de la recta de regresión * promedio del valor de las x)

        //Error de estimación
        double standardDeviation = Math.sqrt((sumY2 - intercept*sumY - slope*sumXY) / (n-2));

        //coeficiente de correlación
        double correlationCoefficient = (n * sumXY - sumX * sumY) / Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));


        // Retornar los coeficientes
        return new double[] {intercept, slope, standardDeviation, correlationCoefficient};
    }


    // Función para predecir el valor en grados Fahrenheit y la desviación standar
    public static double[] predict(double input, double[] coefficients) {
        double intercept = coefficients[0];
        double slope = coefficients[1];
        double prediction = slope * input + intercept;

        double standardDeviation = coefficients[2];

        double correlationCoefficient = coefficients[3];

        return new double[] {prediction, standardDeviation, correlationCoefficient};
    }
}
