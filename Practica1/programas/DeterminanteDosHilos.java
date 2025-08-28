/**
 * Programa que calcula el determinante de una matriz 3x3 usando 2 hilos.
 * Un hilo calcula la suma de las diagonales principales y el otro
 * la suma de las diagonales secundarias.
 * 
 */
public class DeterminanteDosHilos {

    static int determinante;

    static int n_prueba = 3;

    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        determinante = determinanteMatriz3x3(matriz_prueba);
        long endTime = System.nanoTime();

        System.out.println("Program took " + (endTime - startTime) + " ns, result: " + determinante);
    }

    /**
     * Calcula el determinante de una matriz 3x3 usando 2 hilos.
     * @param matriz matriz de enteros 3x3
     * @return determinante calculado
     */
    public static int determinanteMatriz3x3(int[][] matriz) {
        PartialSum sumPos = new PartialSum(
            matriz[0][0] * matriz[1][1] * matriz[2][2],
            matriz[1][0] * matriz[2][1] * matriz[0][2],
            matriz[2][0] * matriz[0][1] * matriz[1][2]
        );

        PartialSum sumNeg = new PartialSum(
            matriz[2][0] * matriz[1][1] * matriz[0][2],
            matriz[1][0] * matriz[0][1] * matriz[2][2],
            matriz[0][0] * matriz[2][1] * matriz[1][2]
        );

        Thread t1 = new Thread(sumPos);
        Thread t2 = new Thread(sumNeg);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sumPos.partialSum - sumNeg.partialSum;
    }
}

/**
 * Clase que implementa Runnable para calcular la suma de tres productos.
 */
class PartialSum implements Runnable {
    int num1, num2, num3;
    int partialSum;

    /**
     * Constructor que recibe los tres productos a sumar.
     * @param num1 primer producto
     * @param num2 segundo producto
     * @param num3 tercer producto
     */
    public PartialSum(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    /**
     * Calcula la suma de los tres productos.
     */
    @Override
    public void run() {
        this.partialSum = num1 + num2 + num3;
    }
}
