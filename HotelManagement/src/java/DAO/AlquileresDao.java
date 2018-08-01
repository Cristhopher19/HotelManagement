package DAO;

import Modelo.AlquileresM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquileresDao extends Dao {

    public List<AlquileresM> listarAlq() throws Exception {
        this.Conectar();
        List<AlquileresM> lista;
        ResultSet rs;
        try {
            String sql = "SELECT COD_ALQ, COS_ALQ, TO_CHAR(HOR_ENT,'DD-MM-YYYY HH24:MI')AS ENTRADA,\n"
                    + "TO_CHAR(HOR_SAL,'DD-MM-YYYY HH24:MI')AS SALIDA,OBS_ALQ,T_HABITACIONES.NUM_HAB,\n"
                    + "T_TIPOHABITACION.TIP_HAB,(T_CLIENTE.APE_CLI || ', ' || T_CLIENTE.NOM_CLI)as NomApeCli,\n"
                    + "(T_REGISTRADOR.APE_REG || ', ' || T_REGISTRADOR.NOM_REG)AS NomApeReg\n"
                    + "FROM T_ALQUILERES\n"
                    + "INNER JOIN T_HABITACIONES ON T_ALQUILERES.COD_HAB = T_HABITACIONES.COD_HAB\n"
                    + "INNER JOIN T_TIPOHABITACION ON T_HABITACIONES.COD_TIP = T_TIPOHABITACION.COD_TIP\n"
                    + "INNER JOIN T_CLIENTE ON T_ALQUILERES.COD_CLI = T_CLIENTE.COD_CLI\n"
                    + "INNER JOIN T_REGISTRADOR ON T_ALQUILERES.COD_REG = T_REGISTRADOR.COD_REG";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            AlquileresM Model;
            while (rs.next()) {
                Model = new AlquileresM();
                Model.setCOD_ALQ(rs.getString("COD_ALQ"));
                Model.setCOS_ALQ(rs.getString("COS_ALQ"));
                Model.setOBS_ALQ(rs.getString("OBS_ALQ"));
                Model.setENTRADA(rs.getString("ENTRADA"));
                Model.setSALIDA(rs.getString("SALIDA"));
                Model.setNUM_HAB(rs.getString("NUM_HAB"));
                Model.setTIP_HAB(rs.getString("TIP_HAB"));
                Model.setCLIENTE(rs.getString("NomApeCli"));
                Model.setREGISTRADOR(rs.getString("NomApeReg"));
                lista.add(Model);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.Cerrar();
        }

    }
}
