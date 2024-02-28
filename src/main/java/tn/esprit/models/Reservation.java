package tn.esprit.models;

public class Reservation {

    private int id ;
    private int   id_utilisateur , id_evenement ;
    private String  date_reservation ;
    private String statut ;

    public Reservation() {
    }

    public Reservation(int id, int id_utilisateur, int id_evenement, String date_reservation, String statut) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.id_evenement = id_evenement;
        this.date_reservation = date_reservation;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", id_utilisateur=" + id_utilisateur +
                ", id_evenement=" + id_evenement +
                ", date_reservation=" + date_reservation +
                ", statut='" + statut + '\'' +
                '}';
    }
}
