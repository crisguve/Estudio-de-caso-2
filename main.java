import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        AVL arbol = new AVL();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;

        do {
            System.out.println("\n=== Menú Árbol AVL ===");
            System.out.println("1. Insertar arbol");
            System.out.println("2. Eliminar arbol");
            System.out.println("3. Buscar arbol");
            System.out.println("4. Imprimir arbol");
            System.out.println("5. Salir");
            System.out.print("Ingrese opción: ");

            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, intente de nuevo.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    int valInsert = Integer.parseInt(br.readLine());
                    arbol.insertar(valInsert);
                    break;
                case 2:
                    System.out.print("Valor a eliminar: ");
                    int valEliminar = Integer.parseInt(br.readLine());
                    arbol.eliminar(valEliminar);
                    break;
                case 3:
                    System.out.print("Valor a buscar: ");
                    int valBuscar = Integer.parseInt(br.readLine());
                    NodoAVL encontrado = arbol.buscar(valBuscar);
                    if (encontrado != null) System.out.println("Nodo encontrado: " + encontrado.getLlave());
                    break;
                case 4:
                  
                  System.out.println("Árbol AVL:");
                  arbol.imprimir();
                  break;

               
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }
}
