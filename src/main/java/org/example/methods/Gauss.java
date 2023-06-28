package org.example.methods;

public class Gauss {
    public static double[][] GaussMatriz(double[][] data) {

        //variables
        double[][] vMatriz = data;
        double vNum;

        //recorrido del pivote
        for (int i=0; i<=vMatriz.length-1; i++){
            vNum = vMatriz[i][i];

            //fila pivote
            for(int j=0; j<=vMatriz[0].length-1; j++){
                vMatriz[i][j] = vMatriz[i][j]/vNum;
            }
            //convertir a cero
            if (i <= vMatriz.length-2){
                //filas para calcular
                for (int j=i+1; j<=vMatriz.length-1; j++){
                    //columnas para calcular
                    vNum = vMatriz[j][i];
                    for (int k=0; k<=vMatriz[0].length-1; k++){
                        vMatriz[j][k] = (vMatriz[j][k] - (vNum*vMatriz[i][k]));
                    }
                }
            }

        }

        return vMatriz;
    };
}
