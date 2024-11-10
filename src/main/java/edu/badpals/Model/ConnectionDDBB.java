package edu.badpals.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionDDBB {
    String urlDB ="jdbc:mysql://localhost:3306/db_videoclub";

    public Connection createConection() throws SQLException {
        Properties conectionProperties = new Properties();
        conectionProperties.setProperty("user", "root");
        conectionProperties.setProperty("password", "root");
        return DriverManager.getConnection(urlDB, conectionProperties);
    }

    public List<Pelicula> listarPeliculas(Connection conector) throws SQLException {
        List<Pelicula> filmList = new ArrayList<>();
        String readFilms = "SELECT * FROM t_peliculas";
        try (Statement st = conector.createStatement();
             ResultSet rs = st.executeQuery(readFilms)) {
            while (rs.next()) {
                filmList.add(new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("actor_protagonista"),
                        Pelicula.Tematica.valueOf(rs.getString("tematica")),
                        rs.getString("guion"),
                        rs.getBoolean("disponible")
                ));
            }
        }
        return filmList;
    }

    public void insertarPelicula(Connection conector, Pelicula pelicula) throws SQLException {
        String insertFilm = "INSERT INTO t_peliculas (titulo, actor_protagonista, tematica, guion, disponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stInsertFilm = conector.prepareStatement(insertFilm)) {
            stInsertFilm.setString(1, pelicula.getTitulo());
            stInsertFilm.setString(2, pelicula.getActor_protagonista());
            stInsertFilm.setString(3, pelicula.getTematica().toString());
            stInsertFilm.setString(4, pelicula.getGuion());
            stInsertFilm.setBoolean(5, pelicula.getDisponible());
            int numFilasInsertadas = stInsertFilm.executeUpdate();
            System.out.println("Pelicula insertada, número de peliculas insertadas: " + numFilasInsertadas);
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public Pelicula obtenerPeliculaPorId(Connection conector, int id) throws SQLException {
        String readFilmById = "SELECT * FROM t_peliculas WHERE id = ?";
        try (PreparedStatement st = conector.prepareStatement(readFilmById)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Pelicula(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("actor_protagonista"),
                            Pelicula.Tematica.valueOf(rs.getString("tematica")),
                            rs.getString("guion"),
                            rs.getBoolean("disponible")
                    );
                }
            }
        }
        return null;
    }

    public void borrarPeliculaPorId(Connection conector, int id) {
        String deleteFilmById = "DELETE FROM t_peliculas WHERE id = ?";
        try (PreparedStatement st = conector.prepareStatement(deleteFilmById)) {
            st.setInt(1, id);
            int numFilasBorradas = st.executeUpdate();
            System.out.println("Pelicula eliminada, número de peliculas eliminadas: " + numFilasBorradas);
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public void actualizarPeliculaPorId(Connection conector, Pelicula pelicula) {

        String updateFilmById = "UPDATE t_peliculas SET titulo = ?, actor_protagonista = ?, tematica = ?, guion = ?, disponible = ? WHERE id = ?";
        try (PreparedStatement st = conector.prepareStatement(updateFilmById)) {
            st.setString(1, pelicula.getTitulo());
            st.setString(2, pelicula.getActor_protagonista());
            st.setString(3, pelicula.getTematica().toString());
            st.setString(4, pelicula.getGuion());
            st.setBoolean(5, pelicula.getDisponible());
            st.setInt(6, pelicula.getID());
            int numFilasActualizadas = st.executeUpdate();
            System.out.println("Pelicula actualizada, número de peliculas actualizadas: " + numFilasActualizadas);
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

}
