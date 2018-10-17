package DAO;

import Modelo.RegistradorM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistradorDao extends Dao {

    public List<RegistradorM> listarHab() throws Exception {
        this.Conexion();
        List<RegistradorM> lista;
        ResultSet rs;

        try {
            String sql = "select COD_REG,TUR_REG, DNI_REG, NOM_REG, APE_REG,CAR_REG,PAS_REG, (Ubigeo.DEPUBI || ',' || Ubigeo.PROVUBI || ',' ||Ubigeo.DISTUBI) as NomUbigeo From T_REGISTRADOR\n"
                    + "       inner join UBIGEO on T_REGISTRADOR.IDUBI = UBIGEO.IDUBI where Est_Reg = 'A'";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            RegistradorM Model;
            while (rs.next()) {
                Model = new RegistradorM();
                Model.setCOD_REG(rs.getString("COD_REG"));
                Model.setTUR_REG(rs.getString("TUR_REG"));
                Model.setDNI_REG(rs.getString("DNI_REG"));
                Model.setNOM_REG(rs.getString("NOM_REG"));
                Model.setAPE_REG(rs.getString("APE_REG"));
                Model.setCAR_REG(rs.getString("CAR_REG"));
                Model.setPAS_REG(rs.getString("PAS_REG"));
                Model.setNOMBREUBIGEO(rs.getString("NomUbigeo"));
                lista.add(Model);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Ingresar(RegistradorM Model) throws Exception {
        this.Conexion();
        try {
            String sql = "insert into T_REGISTRADOR (DNI_REG, NOM_REG, APE_REG, IDUBI,CAR_REG,NIV_REG,PAS_REG,TUR_REG) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, Model.getDNI_REG());
            ps.setString(2, Model.getNOM_REG());
            ps.setString(3, Model.getAPE_REG());
            ps.setString(4, Model.getIDUBI());
            ps.setString(5, Model.getCAR_REG());
            ps.setString(6, Model.getNIV_REG());
            ps.setString(7, Model.getPAS_REG());
            ps.setString(8, Model.getTUR_REG());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Actualizar(RegistradorM Model) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE T_REGISTRADOR set DNI_REG=?, NOM_REG=? , APE_REG=? , IDUBI=?,CAR_REG=? ,NIV_REG=? ,PAS_REG=?, TUR_REG=? WHERE COD_REG like ?";
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, Model.getDNI_REG());
            ps.setString(2, Model.getNOM_REG());
            ps.setString(3, Model.getAPE_REG());
            ps.setString(4, Model.getIDUBI());
            ps.setString(5, Model.getCAR_REG());
            ps.setString(6, Model.getNIV_REG());
            ps.setString(7, Model.getPAS_REG());
            ps.setString(8, Model.getTUR_REG());
            ps.setString(9, Model.getCOD_REG());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void delete(String cod) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE T_REGISTRADOR set EST_REG ='I' WHERE COD_REG=?";
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, cod);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public RegistradorM startSession(String User, String Pass) throws Exception {
        this.Conexion();
        ResultSet rs;
        RegistradorM registradorM = null;
        try {
            String sql = "Select DNI_REG, NOM_REG, APE_REG, IDUBI From T_REGISTRADOR where Niv_Reg like ? and Pas_Reg like ? and Est_Reg like 'A'";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                registradorM = new RegistradorM();
                registradorM.setDNI_REG(rs.getString("DNI_REG"));
                registradorM.setNOM_REG(rs.getString("NOM_REG"));
                registradorM.setAPE_REG(rs.getString("APE_REG"));
                registradorM.setIDUBI(rs.getString("IDUBI"));
                registradorM.setDNI_REG(User);
                registradorM.setPAS_REG(Pass);
            }
            return registradorM;
        } catch (SQLException e) {
            throw e;
        }
    }
}
