package tn.esprit.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.interfaces.IUtilisateur;
import tn.esprit.models.Utilisateur;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceUtilisateur implements IUtilisateur<Utilisateur> {
    private Connection cnx;

    public ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void Add(Utilisateur user) {
        String qry = "INSERT INTO `user`(`nom`, `prenom`, `email`,`password`, `numtel`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, user.getNom());
            stm.setString(2, user.getPrenom());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setInt(5, user.getNumtel());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Utilisateur> afficher() {
        // List<Equipement> Equipement = new ArrayList<>();
        ObservableList<Utilisateur> Equipement = FXCollections.observableArrayList();
        String sql = "SELECT `id`, `nom`, `prenom`, `email`, `password`, `numtel`, `role` FROM `user`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNumtel(rs.getInt("numtel"));
                user.setRole(rs.getString("role"));
                Equipement.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Equipement;

    }

    @Override
    public ArrayList<Utilisateur> getAll() {
        ArrayList<Utilisateur> users = new ArrayList();
        String qry = "SELECT * FROM `user`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNumtel(rs.getInt("numtel"));
                user.setRole(rs.getString("role"));
                users.add(user);
                System.out.println("Ajout effectué");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void Update(Utilisateur user) {
        try {
            String qry = "UPDATE `user` SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`numtel`=? WHERE `id`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, user.getNom());
            stm.setString(2, user.getPrenom());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setInt(5, user.getNumtel());
            stm.setInt(6, user.getId());
            stm.executeUpdate();
            System.out.println("Modification effectué");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(Utilisateur user) {
        try {
            String qry = "DELETE FROM `user` WHERE id=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, user.getId());
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void DeleteByID(int id) {
        try {
            String qry = "DELETE FROM `user` WHERE id=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, id);
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String[] Connection(String email, String mdp) {
        try {
            String query = "SELECT id,password,role FROM user WHERE email = ? AND password = ?";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, email);
            smt.setString(2, mdp);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                String[] r = new String[2];
                r[0] = Integer.toString(rs.getInt("id"));
                r[1] = rs.getString("role");
                return r;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}