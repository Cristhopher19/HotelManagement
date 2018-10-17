package DAO;

import Modelo.RegistradorM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDao extends Dao{
    
    public RegistradorM UserLogeo(String usu, String psw) throws Exception {
        this.Conexion();
        RegistradorM user = null;
        ResultSet rs;
        try {
//            String contraseña = psw;
            String sql = "Select NOM_REG, APE_REG,Niv_Reg From T_REGISTRADOR where DNI_REG like ? and Pas_Reg like ? and Est_Reg like 'A'" ;
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, usu);
            ps.setString(2, psw);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new RegistradorM();
                user.setNOM_REG(rs.getString("NOM_REG"));
                user.setAPE_REG(rs.getString("APE_REG"));
                user.setNIV_REG(rs.getString("Niv_Reg"));
            }
            return user;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}


