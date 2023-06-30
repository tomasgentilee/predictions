package org.example.methods;

public class RegresionLinealImp {
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
    public static double predictMult(double[] input, double[][] modeloRegMultiple) {

        double prediction = modeloRegMultiple[0][0];


        for (int i = 0; i < input.length; i ++){
            prediction += input[i] * modeloRegMultiple[i+1][0];
        }

        return prediction;
    }
    public static double[] predictLin(double input, double[] coefficients) {
        double intercept = coefficients[0];
        double slope = coefficients[1];
        double prediction = slope * input + intercept;

        double standardDeviation = coefficients[2];

        double correlationCoefficient = coefficients[3];

        return new double[] {prediction, standardDeviation, correlationCoefficient};
    }
    public static double[] estimationErrorAndDeterminationCoeMult(double[][] trainingData, double[][] modeloRegMultiple, double[][] trainingDataY) {

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
