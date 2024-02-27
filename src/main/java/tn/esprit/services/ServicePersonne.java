package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Personne;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServicePersonne implements IService<Personne> {
  private Connection cnx ;
  public ServicePersonne(){
      cnx =MyDataBase.getInstance().getCnx();
  }

    @Override
    public void add(Personne personne) {
        // ajouter une personne dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry ="INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,personne.getNom());
            stm.setString(2,personne.getPrenom());
            stm.setInt(3,personne.getAge());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }

    @Override
    public ArrayList<Personne> getAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Personne> personnes = new ArrayList();
        String qry ="SELECT * FROM `personne`";
        try {
            Statement stm = cnx.createStatement();
          ResultSet rs = stm.executeQuery(qry);
          while (rs.next()){
              Personne p = new Personne();
              p.setId(rs.getInt(1));
              p.setNom(rs.getString("nom"));
              p.setPrenom(rs.getString(3));
              p.setAge(rs.getInt("age"));

              personnes.add(p);
          }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return personnes;
    }

    @Override
    public void update(Personne personne) {

    }

    @Override
    public boolean delete(Personne personne) {
        return false;
    }
}
