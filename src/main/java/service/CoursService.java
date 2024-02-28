/*
package service;

import entities.Cours;
import entities.Formation;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static jdk.internal.org.jline.utils.Colors.s;

public class CoursService implements IService<Cours>{


    private final Connection cnx;
    private Statement stm;



    public CoursService() {
        cnx= DataSource.getInstance().getCnx();
    }


    public void add(Cours cours) throws SQLException{
        String qry="INSERT INTO `cours` (`titre_cours` , `date_debut` , `date_fin`) values (?,?,?)";
        PreparedStatement stm = cnx.prepareStatement(qry);
        stm.setString(1,cours.getTitre_cours());
        stm.setString(2,cours.getDate_debut());
        stm.setString(3,cours.getDate_fin());

        stm.executeUpdate();



    }

        /*public void addPst(Sport s){
            String redquete="insert into personne (nom,description,image) values (?,?,?)";
            try {
                pst=conn.prepareStatement(redquete);
                pst.setString(1,s.getNom());
                pst.setString(2,s.getDescription());
                pst.setString(2,s.getImage());
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }*/

  /*  @Override
    public void delete(Cours cours) {
        try {
            String qry = "DELETE FROM `cours` WHERE id_cours=?";
            PreparedStatement smt = this.cnx.prepareStatement(qry);
            smt.setInt(1, cours.getId_cours());
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }


    @Override
    public void update(Cours cours) {
        try {
            String qry = "UPDATE `cours` SET `titre_cours`=?,`date_debut`=?,`date_fin`=? WHERE `id_cours`=?";
            PreparedStatement stm = this.cnx.prepareStatement(qry);

            stm.setString(1, cours.getTitre_cours());
            stm.setString(2, cours.getDate_debut());
            stm.setString(3, cours.getDate_fin());

            stm.executeUpdate();
            System.out.println("Modification effectué");
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }
    @Override
    public ArrayList<Cours> getAll(){

        ArrayList<Cours> cours = new ArrayList<>();
        String qry = "SELECT * FROM `cours`";
        try{
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Cours c = new Cours();
                c.setId_cours(rs.getInt(1));
                c.setTitre_cours(rs.getString(2));
                c.setDate_debut(rs.getDate(3));
                c.setDate_fin(rs.getDate(4));
                cours.add(c);



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cours;
    }


        /*@Override
        public List<Sport> readAll() {
            List<Sport> list=new ArrayList<>();
            String requete="SELECT * FROM `sport`";

            try {
                ste=conn.createStatement();
                ResultSet rs= ste.executeQuery(requete);
                while(rs.next()){
                    list.add(new Sport(rs.getInt(1),rs.getString("nom"),rs.getString("description")));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                //throw new RuntimeException(e);
            }

            return list;
        }*/


/*
    @Override
    public Cours readById(int id) {
        String requete = "SELECT *  FROM `cours` WHERE `id_cours`=?";
/*        List<Cours> list = new ArrayList<>();

        try {
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            if (rs.next()) {
                Cours c = new Cours();

                c.setId_cours(rs.getInt(1));
                c.setTitre_cours(rs.getString(2));
                c.setDate_debut(rs.getDate(3));
                c.setDate_fin(rs.getDate(4));


                list.add(c);
            }
            //list.add(new Sport(rs.getInt(1),rs.getString("nom"),rs.getString("description")));

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Cours> triCourstBytitre_cours() {
        return getAll().stream().sorted((o1, o2) -> o1.getTitre_cours().compareTo(o2.getTitre_cours())).collect(Collectors.toList());
    }


    public List<Cours> recherchePartitre_cours(String titre_cours) {

        return getAll().stream()
                .filter(cours -> cours.getTitre_cours().equalsIgnoreCase(cours.getTitre_cours()))
                .collect(Collectors.toList());
    }
}*/

