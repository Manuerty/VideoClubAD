package edu.badpals.Model;

public class Pelicula {

    private int ID;
    private String titulo;
    private String actor_protagonista;
    private Tematica tematica;
    private String guion;
    private Boolean disponible;

    public enum Tematica {
        Accion, Aventura,  Ciencia_Ficcion, Romance, Terror
    }

    public Pelicula(int ID, String titulo, String actor_protagonista, Tematica tematica, String guion, Boolean disponible) {
        this.ID = ID;
        this.titulo = titulo;
        this.actor_protagonista = actor_protagonista;
        this.tematica = tematica;
        this.guion = guion;
        this.disponible = disponible;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getActor_protagonista() {
        return actor_protagonista;
    }

    public void setActor_protagonista(String actor_protagonista) {
        this.actor_protagonista = actor_protagonista;
    }


    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }


    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public boolean getDisponible() {
        return disponible;
    }


    public String getDisponibleStr() {
        if (disponible) {
            return "Disponible";
        } else {
            return "No disponible";
        }

    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public static Boolean isDisponible(String disponible) {
        if (disponible.equals("Disponible") || disponible.equals("disponible")) {
            return   true;
        } else {
            return  false;
        }
    }


    @Override
    public String toString() {
        return "Pelicula{" +
                "ID=" + ID +
                ", titulo='" + titulo + '\'' +
                ", actor_protagonista='" + actor_protagonista + '\'' +
                ", tematica=" + tematica +
                ", guion='" + guion + '\'' +
                ", disponible=" + disponible +
                '}';
    }


}
