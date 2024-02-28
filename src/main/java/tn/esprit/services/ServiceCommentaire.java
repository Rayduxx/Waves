package tn.esprit.services;

import tn.esprit.IService.IService;
import tn.esprit.models.Commentaire;
import tn.esprit.models.Poste;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class ServiceCommentaire implements IService<Commentaire> {
    private Connection cnx;
    public ServiceCommentaire() {cnx = MyDataBase.getInstance().getCnx();

    }

    String sql = "";


    public void addComm(Commentaire commentaire, Poste poste)  {
         sql = "INSERT INTO `commentaire`(idPoste, idComm, comment) VALUES (?,?,?)";
    try {

        PreparedStatement stm = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, poste.getId());
        stm.setInt(2, commentaire.getIdComm());
        stm.setString(3, commentaire.getComment());
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        if (rs.next()){
            commentaire.setIdComm(rs.getInt(1));
        }
        System.out.println("commentaire ajouteé");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }}

    @Override
    public ArrayList<Commentaire> getAll() {
        ArrayList<Commentaire> commentaires = new ArrayList();
        String qry = "SELECT c.*, p.titre FROM commentaire c inner join poste p on c.idPoste = p.idPoste";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Commentaire c = new Commentaire();
                c.setIdComm(rs.getInt(2));
                Poste p = new Poste();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                c.setPoste(p);
                c.setComment(rs.getString(3));







                commentaires.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commentaires;


    }


    public void update(Commentaire commentaire) {
        try
        {
            String qry="UPDATE `commentaire` SET `comment`=? WHERE `idComm`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, commentaire.getComment());
            stm.setInt(2, commentaire.getIdComm());
            stm.executeUpdate();
            System.out.println("Modification effectué");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Commentaire commentaire) {
        try
        {
            if (commentaire != null) {
                String qry="DELETE FROM `commentaire` WHERE idComm=?";
                PreparedStatement smt = cnx.prepareStatement(qry);
                smt.setInt(1, commentaire.getIdComm());
                smt.executeUpdate();
                System.out.println("Suppression Effectué");
            } else {
                System.out.println("Suppression Impossible");
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Commentaire readById(int idComm) {
        String qry = "SELECT *  FROM commentaire WHERE `idComm`=?";
        List<Commentaire> list = new ArrayList<>();

        try {
            PreparedStatement stm =  cnx.prepareStatement(qry);

            ResultSet rs = stm.executeQuery(qry);
            if (rs.next()) {
                Commentaire s = new Commentaire();
                s.setIdComm(rs.getInt(1));
                s.setComment(rs.getString(3));



                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Poste> rechercheParArtiste(String artiste) {
        return null;
    }

    @Override
    public List<Poste> triPosteByArtiste() {
        return null;
    }
}
