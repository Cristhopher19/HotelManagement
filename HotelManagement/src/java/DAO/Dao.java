package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

    private Connection Dao = null;

    public void Conexion() {
        FabricaConexion fcn;
        try {
            if (Dao == null) {
                fcn = new FabricaConexion();
                Class.forName("oracle.jdbc.OracleDriver");
                Dao = fcn.getConexion(fcn.Cloud);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error -> " + e.getMessage());
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

    public static void main(String[] args) throws ClassNotFoundException {
        Dao dao = new Dao();
        dao.Conexion();
        if (dao.getDao() != null) {
            System.out.println("Conectado");
        } else {
            System.err.println("Coneccion es null Error");
        }
    }
}
