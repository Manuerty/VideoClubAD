package edu.badpals.Controller;

import edu.badpals.View.ViewMenu;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {

    private final ViewMenu viewMenu;
    private final Connection conector;

    public Menu(ViewMenu viewMenu, Connection conector) {
        this.viewMenu = viewMenu;
        this.conector = conector;
    }

    public void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;
            do {
                System.out.println("\n--- Menú VIDEOCLUB ---");
                System.out.println("Elige una opción:");
                System.out.println("1. Listar películas");
                System.out.println("2. Obtener datos de una película por Id");
                System.out.println("3. Borrar película por Id");
                System.out.println("4. Actualizar película por Id");
                System.out.println("5. Crear película");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción (1-6): ");

                // Validación de entrada de opción
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Consumir la nueva línea

                    switch (opcion) {
                        case 1 -> viewMenu.listarPeliculas(conector);
                        case 2 -> viewMenu.obtenerPeliculaPorId(conector, scanner);
                        case 3 -> viewMenu.borrarPeliculaPorId(conector, scanner);
                        // case 4 -> viewMenu.actualizarPeliculaPorId(conector, scanner);
                        case 5 -> viewMenu.insertarPelicula(conector, scanner);
                        case 6 -> System.out.println("Saliendo del programa...");
                        default -> System.out.println("Opción no válida.");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor ingrese un número entre 1 y 6.");
                    scanner.next();  // Consumir la entrada no numérica
                }
            } while (opcion != 6);
        } catch (Exception e) {
            System.err.println("Error al procesar el menú: " + e.getMessage());
        }
    }


}
