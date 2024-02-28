package tn.esprit.test;

import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

public class Main {
    public static void main(String[] args) {

        Poste addPoste = new Poste(1000,"Ala", "Moussa","Tech House","123",288,"hhjbjnh");
        Poste editPoste = new Poste(6,"Ala", "Moussa","Tech House","123",288,"hh");
        Poste deletePoste = new Poste(6,"Ala", "Moussa","Tech House","123",288,"hh");

        ServicePoste PosteS = new ServicePoste();

       // PosteS.Add(addPoste);
        //PosteS.Update(editPoste);
        //PosteS.Delete(deletePoste);
        //System.out.println(PosteS.triPosteByArtiste());
        System.out.println(PosteS.rechercheParArtiste("moussa"));



        // System.out.println(PosteS.getAll());
    }
}