package tn.esprit.test;

import tn.esprit.models.Commentaire;
import tn.esprit.models.Poste;
import tn.esprit.services.ServiceCommentaire;
import tn.esprit.services.ServicePoste;

public class Main {
    public static void main(String[] args) {

        Poste p1 = new Poste(43,"aziz","ben mohamed ", "Tech House ","Tech house","lol","ejnkedlzkmlzdmlkd");
       // Poste p2 = new Poste(8945,"ben mohamed ", "mohamed aziz ",35);
       // Poste p3 = new Poste(4565,"ben mohamed ", "mohamed ali ",99);
        Commentaire c1 = new Commentaire(p1,13,"same");

        ServicePoste sp = new ServicePoste();
        ServiceCommentaire sc = new ServiceCommentaire();
        //sp.add(p1);
        //sp.add(p2);
       // sp.add(p3);

        sc.addComm(c1,p1);
           // sc.delete(c1);
        //System.out.println(sp.getAll());
        System.out.println(sc.getAllCommentairesByPoste(p1));


    }
}