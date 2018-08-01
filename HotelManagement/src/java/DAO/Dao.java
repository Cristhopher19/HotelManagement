package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    private Connection Dao;

    public void Conectar() throws Exception {       //Metodo con los datos de acceso
        try {
            if (Dao == null) {
                Class.forName("oracle.jdbc.OracleDriver");
                Dao = DriverManager.getConnection("jdbc:oracle:thin:@104.196.160.10:1521:XE", "HotelManagement", "admin");
//                Dao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "HotelManagement", "admin");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public void Cerrar() throws SQLException {      //Cerrar la coneccion
        if (Dao != null) {
            if (Dao.isClosed() == false) {
                Dao.close();
                Dao = null;
            }
        }
    }

    public Connection getDao() {
        return Dao;
    }

    public void setCn(Connection Dao) {
        this.Dao = Dao;
    }

    public static void main(String[] args) throws Exception {
        Dao dao = new Dao();
        dao.Conectar();
        if (dao.getDao() != null) {
            System.out.println("conectado");
        } else {
            System.out.println("error");
        }
    }

}
