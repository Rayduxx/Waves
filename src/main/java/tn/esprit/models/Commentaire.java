package tn.esprit.models;

public class Commentaire {

    private int  idComm  ;
    private String comment;

    private Poste poste;

    public Commentaire( Poste poste, int idComm, String comment) {
        this.idComm = idComm;
        this.comment = comment;
        this.poste=poste;
    }





    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Commentaire() {
    }




    public int getIdComm() {
        return idComm;
    }



    public String getComment() {
        return comment;
    }


    public void setIdComm(int idComm) {
        this.idComm = idComm;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                ", idComm=" + idComm +
                ", comment='" + comment + '\'' +
                ", poste=" + poste +
                '}';
    }
}
