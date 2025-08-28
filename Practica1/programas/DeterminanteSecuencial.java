/**
 * Programa que calcula el determinante de una matriz 3x3 de forma secuencial,
 * sin utilizar hilos.
 * 
 */
public class DeterminanteSecuencial {

    static int n_prueba = 3;

    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};

    /**
     * Método principal que calcula el determinante y mide el tiempo de ejecución.
     * @param args argumentos de línea de comando (no se usan)
     */
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int determinante = determinanteMatriz3x3(matriz_prueba);
        long endTime = System.nanoTime();

        System.out.println("Program took " + (endTime - startTime) + " ns, result: " + determinante);
    }

    /**
     * Calcula el determinante de una matriz 3x3 usando la regla de Sarrus.
     * @param matriz matriz de enteros 3x3
     * @return determinante calculado
     */
    public static int determinanteMatriz3x3(int[][] matriz) {
        int d1 = matriz[0][0] * matriz[1][1] * matriz[2][2];
        int d2 = matriz[1][0] * matriz[2][1] * matriz[0][2];
        int d3 = matriz[2][0] * matriz[0][1] * matriz[1][2];

        int ds1 = matriz[2][0] * matriz[1][1] * matriz[0][2];
        int ds2 = matriz[1][0] * matriz[0][1] * matriz[2][2];
        int ds3 = matriz[0][0] * matriz[2][1] * matriz[1][2];

        return (d1 + d2 + d3) - (ds1 + ds2 + ds3);
    }
}
