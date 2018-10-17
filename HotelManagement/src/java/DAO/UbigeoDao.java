package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbigeoDao extends Dao {

    public String leerUbigeo(String a) throws Exception { // BUSCA EL CODIGO DE DISTRITO
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT IDUBI FROM UBIGEO WHERE CONCAT(CONCAT(CONCAT(CONCAT(DEPUBI,','),PROVUBI),','),DISTUBI) = ?";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

//Lista el autocomplete de ubigeo mientras escriben
    public List<String> queryAutoCompleteUbi(String a) throws Exception { //LISTA LOS PROVINCIAS Y DISTRITOS
        this.Conexion();
        List<String> lista;
        ResultSet rs;
        String sql;
        try {
            if (a == null || "".equals(a)) {
                sql = "SELECT CONCAT(CONCAT(CONCAT(CONCAT(DEPUBI,','),PROVUBI),','),DISTUBI) AS NOMBRE FROM UBIGEO WHERE PROVUBI LIKE UPPER(?)";
                a = "CAÑETE";
            } else {
                sql = "SELECT CONCAT(CONCAT(CONCAT(CONCAT(DEPUBI,','),PROVUBI),','),DISTUBI) AS NOMBRE FROM UBIGEO WHERE DISTUBI like UPPER(?)";
            }
            PreparedStatement ps = this.getDao().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("NOMBRE"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
