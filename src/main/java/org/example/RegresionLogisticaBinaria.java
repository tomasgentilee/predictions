package org.example;

import org.example.methods.Inverse;
import org.example.methods.ProductoMatricial;
import org.example.methods.RegresionLinealImp;
import org.example.methods.Transpuesta;

import java.util.Scanner;

public class RegresionLogisticaBinaria {

    public static void main(String [] args){

        //En la regresión logistica binaria buscamos dos opciones como resultado
        //

        //En este ejemplo tendremos 4 variables y dos resultados esperados:
        //Coeficiente intelectual, si dedica o no horas extras, si participa o no en clase y el promedio academico
        //El coeficiente intelectual y el promedio son variables cuantitativas
        //Las horas extras y la participación son variables categoricas dividida en 0 como no y 1 como si
        double[][] features = {
                {112, 0, 1, 80},
                {91, 0, 0, 62},
                {92, 1, 0, 69},
                {104, 0, 1, 73},
                {113, 0, 1, 88},
                {100, 0, 1, 75},
                {118, 1, 0, 99},
                {96, 1, 0, 70},
                {117, 1, 1, 89},
                {99, 0, 0, 67},
                {107, 0, 0, 76},
                {110, 0, 1, 89},
                {107, 0, 0, 70},
                {108, 1, 0, 80},
        };

        // Etiquetas de clase (0 o 1). Esperamos como resultado si consigue o no trabajo (Exito laboral)
        //0 no consigue 1 si consigue
        double[][] labels = {
                {1},
                {0},
                {0},
                {0},
                {0},
                {0},
                {0},
                {0},
                {1},
                {1},
                {0},
                {0},
                {1},
                {0}
        };

        /*
        RegresionLinealImp regresionLinealImp = new RegresionLinealImp();

        double[][] multiEq = regresionLinealImp.performMultipleLinearRegression(features, labels);

        Scanner scn = new Scanner(System.in);
        double [] data2predict = new double[features[0].length];

        System.out.println("Este programa predice si un estudiante en base a ciertos parametros consigue o no trabajo");

        System.out.println("Ingrese los valores a predecir:");
        for (int i = 0; i < features[0].length; i++){
            data2predict[i] = scn.nextInt();
        }

        double prediction = regresionLinealImp.predictMult(data2predict, multiEq);

        double pY = 1 / 1 + (Math.exp(-prediction));

        System.out.println(pY);

         */
    }


}
