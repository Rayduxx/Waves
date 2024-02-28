package tn.esprit.services;

import tn.esprit.interfaces.IEvent;
import tn.esprit.models.Event;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceEvent implements IEvent<Event> {
    private Connection cnx;
    public ServiceEvent() {cnx = MyDataBase.getInstance().getCnx();}

    @Override
    public void Add(Event event) {
        String qry = "INSERT INTO `event`( `nomE`, `adrE`,`desc`, `date`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, event.getNomE());
            stm.setString(2, event.getAdrE());
            stm.setString(3, event.getDesc());
            stm.setString(4, event.getDate());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Event> getAll() {
        ArrayList<Event> events = new ArrayList();
        String qry = "SELECT * FROM `event`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Event p = new Event();
                p.setEid(rs.getInt("Eid"));
                p.setNomE(rs.getString("nomE"));
                p.setAdrE(rs.getString("adrE"));
                p.setDesc(rs.getString("desc"));
                p.setDate(rs.getString("date"));

                events.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public void Update(Event event) {
        try
        {
            String qry="UPDATE `event` SET `nomE`=?,`adrE`=?,`desc`=?,`date`=? WHERE `Eid`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, event.getNomE());
            stm.setString(2, event.getAdrE());
            stm.setString(3, event.getDesc());
            stm.setString(4, event.getDate());

            stm.executeUpdate();
            System.out.println("Modification effectué");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(Event event) {
        try
        {
            String qry="DELETE FROM `event` WHERE Eid=?";
            PreparedStatement smt = cnx.prepareStatement(qry);
            smt.setInt(1, event.getEid());
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
