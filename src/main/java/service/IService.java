package service;

import entities.Formation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IService <T>{

    void add(T t) throws SQLException;
    void delete(T t);

    void update(T t);

    ArrayList<T> getAll();
    //List<T> readAll();
     List<T> triFormationtBytitre();
    List<Formation> recherchePartitre(String titre);

    T readById(int id);
}
