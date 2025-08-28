public class HilosDisponibles {
    public static void main(String[] args) {
        int hilos = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de hilos/procesadores lógicos disponibles: " + hilos);
    }
}
