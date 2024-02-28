package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private final Connection cnx;
    private final String url="jdbc:mysql://localhost:3306/waves";
    private final String login="root";
    private final String pwd="";
    private static DataSource instance;

    private DataSource() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getInstance(){
        if(instance==null)
            instance=new DataSource();
        return instance;
    }


    public Connection getCnx() {
        return cnx;
    }
}
