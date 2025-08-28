/**
 * Programa que calcula el determinante de una matriz 3x3 usando 6 hilos.
 * Implementa la interfaz Runnable en lugar de extender Thread.
 */
public class DeterminanteConcurrenteRunnable {

    static int determinante;

    static int n_prueba = 3;

    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        determinante = determinanteMatriz3x3(matriz_prueba, n_prueba);
        long endTime = System.nanoTime();

        System.out.println("Program took " + (endTime - startTime) + " ns, result: " + determinante);
    }

    /**
     * Calcula el determinante de una matriz 3x3 usando 6 hilos.
     * @param matriz matriz de enteros 3x3
     * @param n tamaño de la matriz (debe ser 3)
     * @return el determinante calculado
     */
    public static int determinanteMatriz3x3(int[][] matriz, int n) {
        int result = 0;

        PartialProduct p1 = new PartialProduct(matriz[0][0], matriz[1][1], matriz[2][2]);
        PartialProduct p2 = new PartialProduct(matriz[1][0], matriz[2][1], matriz[0][2]);
        PartialProduct p3 = new PartialProduct(matriz[2][0], matriz[0][1], matriz[1][2]);
        PartialProduct p4 = new PartialProduct(matriz[2][0], matriz[1][1], matriz[0][2]);
        PartialProduct p5 = new PartialProduct(matriz[1][0], matriz[0][1], matriz[2][2]);
        PartialProduct p6 = new PartialProduct(matriz[0][0], matriz[2][1], matriz[1][2]);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);
        Thread t6 = new Thread(p6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = p1.partial + p2.partial + p3.partial - p4.partial - p5.partial - p6.partial;

        return result;
    }
}

/**
 * Clase que implementa Runnable para calcular un producto parcial de tres números.
 */
class PartialProduct implements Runnable {
    int num1, num2, num3;
    int partial;

    public PartialProduct(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }
    
    @Override
    public void run() {
        this.partial = num1 * num2 * num3;
    }
}
