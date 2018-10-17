package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexion {

    public int Cloud = 1;
    public int Localhost = 2;
    public int LocalHost2 = 3;

    public Connection getConexion(int Servidor) throws SQLException {
        switch (Servidor) {
            case 1:
                return DriverManager.getConnection("jdbc:oracle:thin:@104.196.160.10:1521:XE", "HotelManagement", "admin");
            case 2:
                return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "HotelManagement", "admin");
//                return DriverManager.getConnection("jdbc:oracle:thin:@testing.vallegrande.edu.pe:1521:XE", "CAME", "vallegrande2018");
            case 3:
                return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CAME", "CAME");
            default:
                return null;
        }
    }

}
