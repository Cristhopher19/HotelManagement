package Controller;

import DAO.AlquileresDao;
import Modelo.AlquileresM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named(value = "alquileresController")
@SessionScoped
public class AlquileresController implements Serializable{

    List<AlquileresM> lstAlq = new ArrayList();
    AlquileresM alquileresM = new AlquileresM();

    @PostConstruct
    public void star() {
        try {
            listMantenimiento();
        } catch (Exception ex) {
            Logger.getLogger(HabitacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listMantenimiento() throws Exception {
        AlquileresDao dao;
        try {
            dao = new AlquileresDao();
            lstAlq = dao.listarAlq();
        } catch (Exception e) {
            throw e;
        }
    }
}
