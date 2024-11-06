package edu.badpals;



import edu.badpals.Controller.Menu;
import edu.badpals.Model.ConnectionDDBB;
import edu.badpals.View.ViewMenu;


import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectionDDBB conectar = new ConnectionDDBB();

        try (Connection conector = conectar.createConection()) {
            if (conector != null) {
                ViewMenu viewMenu = new ViewMenu(conectar);
                Menu menu = new Menu(viewMenu, conector);
                menu.mostrarMenu();

            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}