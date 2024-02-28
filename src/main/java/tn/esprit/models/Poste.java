package tn.esprit.models;

public class Poste {

    private int id , duree ;
    private String genre , titre, artiste, description, image;

    public Poste() {}

    public Poste(int id, String titre, String artiste, String genre, String image, int duree, String description) {
        this.id = id;
        this.titre = titre;
        this.artiste = artiste;
        this.genre = genre;
        this.image = image;
        this.duree = duree;
        this.description = description;

    }


    public Poste( String titre, String artiste, String genre, String image, int duree, String description) {
        this.titre = titre;
        this.artiste = artiste;
        this.genre = genre;
        this.image = image;
        this.duree = duree;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Poste{" +
                "id=" + id +
                ", duree=" + duree +
                ", genre='" + genre + '\'' +
                ", titre='" + titre + '\'' +
                ", artiste='" + artiste + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
