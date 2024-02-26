package tn.esprit.services;

import tn.esprit.IService.Iitem;
import tn.esprit.models.Item;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceItem implements Iitem<Item> {
    private Connection cnx;
    public ServiceItem() {cnx = MyDataBase.getInstance().getCnx();}

    @Override
    public void Add(Item item) {
        String qry = "INSERT INTO `item`(`itemID`, `titre`, `description`, `auteur`, `prix`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, item.getItemID());
            stm.setString(2, item.getTitre());
            stm.setString(3, item.getDescription());
            stm.setString(4, item.getAuteur());
            stm.setFloat(5, item.getPrix());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> items = new ArrayList<>();
        String qry = "SELECT * FROM `item`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Item item = new Item();
                item.setItemID(rs.getInt("itemID"));
                item.setTitre(rs.getString("titre"));
                item.setDescription(rs.getString("description"));
                item.setAuteur(rs.getString("auteur"));
                item.setPrix(rs.getFloat("prix"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void Update(Item item) {
        try
        {
            String qry="UPDATE `item` SET `titre`=?,`description`=?,`auteur`=?,`prix`=? WHERE `itemID`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, item.getTitre());
            stm.setString(2, item.getDescription());
            stm.setString(3, item.getAuteur());
            stm.setFloat(4, item.getPrix());
            stm.setInt(5, item.getItemID());
            stm.executeUpdate();
            System.out.println("Modification effectuée");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(Item item) {
        try
        {
            String qry="DELETE FROM `item` WHERE itemID=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, item.getItemID());
            smt.executeUpdate();
            System.out.println("Suppression effectuée");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
