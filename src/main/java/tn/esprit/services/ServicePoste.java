package tn.esprit.services;

import tn.esprit.IService.IService;
import tn.esprit.models.Poste;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicePoste implements IService<Poste> {
    private Connection cnx;
    public ServicePoste() {cnx = MyDataBase.getInstance().getCnx();}

    @Override
    public void add(Poste poste) throws SQLException {
        String qry = "INSERT INTO `poste`(`titre`, `artiste`, `genre`,`image`, `duree` ,`description` ) VALUES (?,?,?,?,?,?)";

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, poste.getTitre());
            stm.setString(2, poste.getArtiste());
            stm.setString(3, poste.getGenre());
            stm.setString(4, poste.getImage());
            stm.setInt(5, poste.getDuree());
            stm.setString(6, poste.getDescription());

            stm.executeUpdate();
        }



    @Override
    public ArrayList<Poste> getAll() {
        ArrayList<Poste> postes = new ArrayList();
        String qry = "SELECT * FROM `poste`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Poste p = new Poste();
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setArtiste(rs.getString("artiste"));
                p.setGenre(rs.getString("genre"));
                p.setImage(rs.getString("image"));
                p.setDuree(rs.getInt("duree"));
                p.setDescription(rs.getString("description"));

                postes.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return postes;
    }

    @Override
    public void update(Poste poste) {
        try
        {
            String qry="UPDATE `poste` SET `titre`=?,`artiste`=?,`genre`=?,`image`=?,`duree`=? ,`description` =?WHERE `id`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, poste.getTitre());
            stm.setString(2, poste.getArtiste());
            stm.setString(3, poste.getGenre());
            stm.setString(4, poste.getImage());
            stm.setInt(5, poste.getDuree());
            stm.setString(6, poste.getDescription());
            stm.setInt(7, poste.getId());
            stm.executeUpdate();
            System.out.println("Modification effectué");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Poste poste) {
        try
        {
            String qry="DELETE FROM `poste` WHERE id=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, poste.getId());
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public Poste readById(int id) {
        String qry = "SELECT *  FROM poste WHERE `id`=?";
        List<Poste> list = new ArrayList<>();

        try {
            PreparedStatement stm =  cnx.prepareStatement(qry);

            ResultSet rs = stm.executeQuery(qry);
            if (rs.next()) {
                Poste s = new Poste();
                s.setId(rs.getInt(1));
                s.setTitre(rs.getString(2));
                s.setArtiste(rs.getString(3));
                s.setGenre(rs.getString(4));
                s.setImage(rs.getString(5));
                s.setDuree (rs.getInt(6));
                s.setDescription(rs.getString(7));


                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Poste> rechercheParArtiste(String artiste) {

        return getAll().stream()
                .filter(sport -> sport.getArtiste().equalsIgnoreCase(artiste))
                .collect(Collectors.toList());
    }
    @Override
    public List<Poste> triPosteByArtiste() {
        return getAll().stream().sorted((o1, o2) -> o1.getArtiste().compareTo(o2.getArtiste())).collect(Collectors.toList());
    }
}
