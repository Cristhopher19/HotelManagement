package Controller;

import DAO.ClientesDao;
import DAO.UbigeoDao;
import Modelo.ClientesM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "clientesCrontroller")
@SessionScoped
public class ClientesCrontroller implements Serializable {

    List<ClientesM> LstCli = new ArrayList();
//    ClientesM clientesM = new ClientesM();
    private ClientesM clientesM;

    @PostConstruct
    public void star() {
        try {
            listMantenimiento();
        } catch (Exception ex) {
            Logger.getLogger(ClientesCrontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listMantenimiento() throws Exception {
        ClientesDao dao;
        try {
            dao = new ClientesDao();
            LstCli = dao.listarCli();
        } catch (Exception e) {
            throw e;
        }
    }

////METODO PARA AUTOCOMPLETAR
//    private String NombreUbigeo;
//
//    public List<String> autocompleteDistUbigeo(String query) throws Exception {
//        ClientesDao dao = new ClientesDao();
//        return dao.queryAutoCompleteUbi(query);
//
//    }
//
//    public String buscarCodigoUbigeo(String query) throws Exception {
//        ClientesDao dao;
//        try {
//            dao = new ClientesDao();
//            return dao.leerUbigeo(clientesM.getUbigeo());
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public void ingresar() throws Exception {
        ClientesDao dao;
        UbigeoDao dao2;
        try {
            dao = new ClientesDao();
            dao2 = new UbigeoDao();
            clientesM.setUbigeo(dao2.leerUbigeo(clientesM.getNombreUbigeo()));
            dao.Ingresar(clientesM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar() throws Exception {
        ClientesDao dao;
        UbigeoDao dao2;
        try {
            dao = new ClientesDao();
            dao2 = new UbigeoDao();
            clientesM.setUbigeo(dao2.leerUbigeo(clientesM.getNombreUbigeo()));
            dao.Actualizar(clientesM);
            listMantenimiento();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }
    

    public void delete() throws Exception {
        ClientesDao dao;
        try {
            dao = new ClientesDao();
            dao.delete(clientesM.getCod_Cli());
            star();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingresado Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        clientesM = new ClientesM();
    }

    public List<ClientesM> getLstCli() {
        return LstCli;
    }

    public void setLstCli(List<ClientesM> LstCli) {
        this.LstCli = LstCli;
    }

    public ClientesM getClientesM() {
        return clientesM;
    }

    public void setClientesM(ClientesM clientesM) {
        this.clientesM = clientesM;
    }

}
