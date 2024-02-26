/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Personne;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.PersonneService;

/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Personne p = new Personne("Nouha","BENSLIMEN");
        
        PersonneService sp = new PersonneService();
        
        try {
            sp.ajouterp(p);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            System.out.println(sp.afficherpersonne());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
