package org.example;

import entities.Formation;
import service.FormationService;

import java.sql.SQLException;

//import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws SQLException {

        Formation s1 = new Formation(8,"hamma", "aziz", "didouu", "hddg");

        FormationService fs = new FormationService();
       fs.add(s1);
        // ps.addPst(s1);
        //fs.delete(s1);
       // ps.update(s1);

        //System.out.println(fs.recherchePartitre("hghghg"));
        //System.out.println(ps.getAll());
       // ps.readAll().forEach(System.out::println);
    }
}