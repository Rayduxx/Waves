/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.MyDB;

/**
 *
 * @author macbook
 */
public class PersonneService implements IPersonne<Personne> {

    Connection connexion;
    Statement stm;

    public PersonneService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterp(Personne p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( '"
                + p.getNom() + "', '" + p.getPrenom() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public void ajouterProduitPs(Personne p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getPrenom());
        ps.executeUpdate();
    }

    @Override
    public List<Personne> afficherpersonne() throws SQLException {
        List<Personne> presonnes = new ArrayList<>();
        String req = "select * from personne";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Personne p = new Personne(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"));
            presonnes.add(p);
        }
        return presonnes;
    }
}
