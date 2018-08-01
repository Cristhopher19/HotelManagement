
package Controller;

import DAO.HabitacionesDao;
import Modelo.HabitacionesM;
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


@Named(value = "habitacionesController")
@SessionScoped
public class HabitacionesController implements Serializable {

    List<HabitacionesM> LstHab = new ArrayList();
    HabitacionesM HabitacionesM = new HabitacionesM();

    @PostConstruct
    public void star() {
        try {
            listMantenimiento();
        } catch (Exception ex) {
            Logger.getLogger(HabitacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void listMantenimiento() throws Exception {
        HabitacionesDao dao;
        try {
            dao = new HabitacionesDao();
            LstHab = dao.listarHab();
        } catch (Exception e) {
            throw e;
        }
    }

    public void ingresar() throws Exception {
        HabitacionesDao dao;
        try {
            dao = new HabitacionesDao();
            dao.Ingresar(HabitacionesM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar() throws Exception {
        HabitacionesDao dao;
        try {
            dao = new HabitacionesDao();
            dao.Actualizar(HabitacionesM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void delete() throws Exception {
        HabitacionesDao dao;
        try {
            dao = new HabitacionesDao();
            dao.delete(HabitacionesM.getCod_Hab());
            star();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingresado Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        HabitacionesM = new HabitacionesM();

    }

    public List<HabitacionesM> getLstHab() {
        return LstHab;
    }

    public void setLstHab(List<HabitacionesM> LstHab) {
        this.LstHab = LstHab;
    }

    public HabitacionesM getHabitacionesM() {
        return HabitacionesM;
    }

    public void setHabitacionesM(HabitacionesM HabitacionesM) {
        this.HabitacionesM = HabitacionesM;
    }

    

 
}
