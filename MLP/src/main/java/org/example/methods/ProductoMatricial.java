package org.example.methods;

public class ProductoMatricial {
    public static double[][] productoMatriz(double[][] data1, double[][] data2) {

        // filas de la matriz A
        int numRows1 = data1.length;
        // columnas de la matriz A
        int numCols1 = data1[0].length;
        // filas de la matriz B
        int numRows2 = data2.length;
        // columnas de la matriz B
        int numCols2 = data2[0].length;

        //nueva matriz
        double[][] producto = new double[numRows1][numCols2];
        // Verificar si las matrices se pueden multiplicar
        if (numCols1 != numRows2) {
            throw new IllegalArgumentException("No se pueden multiplicar las matrices. El número de columnas de la matriz A debe ser igual al número de filas de la matriz B.");
        }

        //tenemos un triple bucle for
        //el primero va a recorrer cada fila de la matriz A
        //el segundo va a recorrer cada columna de la matriz B
        //el tercero va a recorrer todos los elementos de la columna n en la fila correspondiente de la matriz A y la fila correspondiente de la matriz B en la posición n
        //de esa manera se multiplica el elemento en la posición 0,0 con el 0,0 - el 0,1 con el 1,0 - el 0,2 con el 2,0 y así sucesivamente

        for (int i = 0; i < numRows1; i++) {
            for (int j = 0; j < numCols2; j++) {
                for (int k = 0; k < numCols1; k++) {
                    producto[i][j] += data1[i][k] * data2[k][j];
                }
            }
        }


        return producto;

    };
}
