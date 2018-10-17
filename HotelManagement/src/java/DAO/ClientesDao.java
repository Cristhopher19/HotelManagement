package DAO;

import Modelo.ClientesM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDao extends Dao {

    public List<ClientesM> listarCli() throws Exception {
        this.Conexion();
        List<ClientesM> lista;
        ResultSet rs;

        // JALAR EL CODIGO (Cod_Cli) PARA PODER ACTUALIZAR
        try {
            String sql = "Select Cod_Cli,DNI_CLI, NOM_CLI,APE_CLI,(Ubigeo.DEPUBI || ',' || Ubigeo.PROVUBI || ',' ||Ubigeo.DISTUBI) as NomUbigeo,DIR_CLI,NAC_CLI from T_CLIENTE \n"
                    + "    inner join Ubigeo on T_CLIENTE.IDUBI = Ubigeo.IDUBI\n"
                    + "    where EST_Cli LIKE 'A' ORDER BY Ape_Cli ASC";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            ClientesM Model;
            while (rs.next()) {
                Model = new ClientesM();
                Model.setCod_Cli(rs.getString("Cod_Cli"));
                Model.setDni_Cli(rs.getString("DNI_CLI"));
                Model.setNom_Cli(rs.getString("NOM_CLI"));
                Model.setApe_Cli(rs.getString("APE_CLI"));
                Model.setNombreUbigeo(rs.getString("NomUbigeo"));
                Model.setDir_Cli(rs.getString("DIR_CLI"));
                Model.setNac_Cli(rs.getString("NAC_CLI"));
                lista.add(Model);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Ingresar(ClientesM Model) throws Exception {
        this.Conexion();
        try {
            String sql = "insert into T_CLIENTE (Dni_Cli, Nom_Cli,Ape_Cli,IdUbi,Dir_Cli,NAC_CLI) values (?,?,?,?,?,?)";
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, Model.getDni_Cli());
            ps.setString(2, Model.getNom_Cli());
            ps.setString(3, Model.getApe_Cli());
            ps.setString(4, Model.getUbigeo());
            ps.setString(5, Model.getDir_Cli());
            ps.setString(6, Model.getNac_Cli());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Actualizar(ClientesM Model) throws Exception {
        this.Conexion();
        try {
            String sql = "UPDATE T_CLIENTE set Dni_Cli =? ,Nom_Cli =? ,Ape_Cli =? ,IdUbi =? ,Nac_Cli =? ,Dir_Cli=?  WHERE COD_CLI= ?";
            PreparedStatement ps = this.getDao().prepareStatement(sql);
            ps.setString(1, Model.getDni_Cli());
            ps.setString(2, Model.getNom_Cli());
            ps.setString(3, Model.getApe_Cli());
            ps.setString(4, Model.getUbigeo());
            ps.setString(5, Model.getNac_Cli());
            ps.setString(6, Model.getDir_Cli());
            ps.setString(7, Model.getCod_Cli());
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
            String sql = "Update T_CLIENTE set EST_CLI='I' where COD_CLI = ?";
            PreparedStatement ps = this.getDao().prepareCall(sql);
            ps.setString(1, cod);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
