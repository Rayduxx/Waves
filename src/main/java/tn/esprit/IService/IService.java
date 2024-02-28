package tn.esprit.IService;

import tn.esprit.models.Poste;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IService<T> {


    ArrayList<T> getAll();
    void update(T t);
    void delete(T t);

    T readById (int id);

    List<Poste> rechercheParArtiste (String artiste);
    List<Poste> triPosteByArtiste();
//getOne getById

}
