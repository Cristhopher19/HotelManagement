package Controller;


import DAO.UbigeoDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "ubigeoController")
@SessionScoped
public class UbigeoController implements Serializable {

//METODO PARA AUTOCOMPLETAR
    private String NombreUbigeo;

    public List<String> autocompleteDistUbigeo(String query) throws Exception {
        UbigeoDao dao = new UbigeoDao();
        return dao.queryAutoCompleteUbi(query);

    }

    public String buscarCodigoUbigeo(String query) throws Exception {
        UbigeoDao dao;
        try {
            dao = new UbigeoDao();
            return dao.leerUbigeo(NombreUbigeo);
        } catch (Exception e) {
            throw e;
        }
    }

}
