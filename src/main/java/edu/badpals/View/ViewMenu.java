package edu.badpals.View;

import edu.badpals.Model.ConnectionDDBB;
import edu.badpals.Model.Pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewMenu {

    private final ConnectionDDBB conectar;


    public ViewMenu(ConnectionDDBB conectar) {
        this.conectar = conectar;
    }

    public void listarPeliculas(Connection conector) throws SQLException {
        List<Pelicula> peliculas = conectar.listarPeliculas(conector);
        StringBuilder sb = new StringBuilder();

        for (Pelicula pelicula : peliculas) {

            sb.append("- ID: ").append(pelicula.getID()).append(" ")
                    .append("/ Titulo: ").append(pelicula.getTitulo()).append(" ")
                    .append("\n");
        }

        System.out.println(sb);
    }

    public void insertarPelicula(Connection conector, Scanner sc) throws SQLException {

        System.out.println("Dame el titulo de la pelicula: ");
        String titulo = sc.nextLine();

        System.out.println("Dame el actor protagonista: ");
        String actor_protagonista = sc.nextLine();

        System.out.println("Dame la tematica: ");
        boolean continuar = true;
        String tematica = "";
        while (continuar) {
            System.out.println("-- OPCIONES --");
            System.out.println(" 1. Accion");
            System.out.println(" 2. Aventura");
            System.out.println(" 3. Ciencia_Ficcion");
            System.out.println(" 4. Romance");
            System.out.println(" 5. Terror");
            System.out.print("Seleccione una opción (1-5): ");

            if (sc.hasNextInt()) {
                int opcion = sc.nextInt();
                sc.nextLine();
                continuar = false;

                switch (opcion) {
                    case 1 -> tematica = "Accion";
                    case 2 -> tematica = "Aventura";
                    case 3 -> tematica = "Ciencia_Ficcion";
                    case 4 -> tematica = "Romance";
                    case 5 -> tematica = "Terror";
                    default -> {
                        continuar = true;
                        System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número entre 1 y 5.");
                sc.nextLine();  // Consumir la nueva línea después de la entrada inválida
            }
        }

        System.out.println("Dame el guion: ");
        String guion = sc.nextLine();

        System.out.println("Dame el estado de la pelicula: ");
        boolean continuarEstado = true;
        String disponible = "";
        while (continuarEstado) {
            System.out.println("-- OPCIONES --");
            System.out.println(" 1. Disponible");
            System.out.println(" 2. No disponible");
            System.out.print("Seleccione una opción (1-2): ");

            if (sc.hasNextInt()) {
                int opcion = sc.nextInt();
                sc.nextLine();
                continuarEstado = false;

                switch (opcion) {
                    case 1 -> disponible = "Disponible";
                    case 2 -> disponible = "No disponible";
                    default -> {
                        continuarEstado = true;
                        System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número entre 1 y 2.");
                sc.nextLine();
            }
        }
        // Creación de la película y llamada a la función de inserción
        Pelicula pelicula = new Pelicula(0, titulo, actor_protagonista, Pelicula.Tematica.valueOf(tematica), guion, Pelicula.isDisponible(disponible));
        conectar.insertarPelicula(conector, pelicula);
    }

    public void obtenerPeliculaPorId(Connection conector, Scanner sc) throws SQLException {
        System.out.println("Dame el ID de la pelicula: ");
        int id = sc.nextInt();
        sc.nextLine();
        Pelicula pelicula = conectar.obtenerPeliculaPorId(conector, id);
        if (pelicula != null) {
            System.out.println("- ID: " + pelicula.getID());
            System.out.println("- Titulo: " + pelicula.getTitulo());
            System.out.println("- Actor protagonista: " + pelicula.getActor_protagonista());
            System.out.println("- Tematica: " + pelicula.getTematica());
            System.out.println("- Guion: " + pelicula.getGuion());
            System.out.println("- Estado: " + pelicula.getDisponibleStr());
        } else {
            System.out.println("No se encontró la película con el ID: " + id);
        }
    }

}
