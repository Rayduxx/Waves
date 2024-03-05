package tn.esprit.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.interfaces.IProduction;
import tn.esprit.models.Production;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceProduction implements IProduction<Production> {
    private Connection cnx;

    public ServiceProduction() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void Add(Production user) {
        String qry = "INSERT INTO `production`(`nom`,`genre`, `description`, `moodtag`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, user.getNom());
            stm.setString(2, user.getGenre());
            stm.setString(3, user.getDesc());
            stm.setString(4, user.getTags());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Production> afficher() {
        ObservableList<Production> prod = FXCollections.observableArrayList();
        String sql = "SELECT `id`, `nom`, `genre`, `description`, `moodtag` FROM `production`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Production production = new Production();
                production.setId(rs.getInt("id"));
                production.setNom(rs.getString("nom"));
                production.setGenre(rs.getString("genre"));
                production.setDesc(rs.getString("description"));
                production.setTags(rs.getString("moodtag"));
                prod.add(production);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

    @Override
    public ArrayList<Production> getAll() {
        ArrayList<Production> production = new ArrayList();
        String qry = "SELECT * FROM `production`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Production prod = new Production();
                prod.setId(rs.getInt("id"));
                prod.setNom(rs.getString("nom"));
                prod.setNom(rs.getString("genre"));
                prod.setNom(rs.getString("description"));
                prod.setNom(rs.getString("moodtag"));
                production.add(prod);
                System.out.println("Ajout effectué");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return production;
    }

    @Override
    public void Update(Production prod) {
        try {
            String qry = "UPDATE `production` SET `nom`=?,`genre`=?,`description`=?,`moodtag`=? WHERE `id`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, prod.getNom());
            stm.setString(2, prod.getGenre());
            stm.setString(3, prod.getDesc());
            stm.setString(4, prod.getTags());
            stm.setInt(5, prod.getId());
            stm.executeUpdate();
            System.out.println("Modification effectué");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(Production prod) {
        try {
            String qry = "DELETE FROM `production` WHERE id=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, prod.getId());
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void DeleteByID(int id) {
        try {
            String qry = "DELETE FROM `production` WHERE id=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, id);
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}