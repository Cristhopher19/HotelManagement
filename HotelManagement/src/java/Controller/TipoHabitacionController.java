package Controller;

import DAO.TipoHabitacionDao;
import Modelo.TipoHabitacionM;
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

@Named(value = "tipoHabitacionController")
@SessionScoped
public class TipoHabitacionController implements Serializable {

    List<TipoHabitacionM> LstTipHab = new ArrayList();
    TipoHabitacionM tipoHabitacionM = new TipoHabitacionM();

    @PostConstruct
    public void star() {
        try {
            listMantenimiento();
        } catch (Exception ex) {
            Logger.getLogger(TipoHabitacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listMantenimiento() throws Exception {
        TipoHabitacionDao dao;
        try {
            dao = new TipoHabitacionDao();
            LstTipHab = dao.listarTip();
        } catch (Exception e) {
            throw e;
        }
    }

    public void ingresar() throws Exception {
        TipoHabitacionDao dao;
        try {
            dao = new TipoHabitacionDao();
            dao.Ingresar(tipoHabitacionM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizar() throws Exception {
        TipoHabitacionDao dao;
        try {
            dao = new TipoHabitacionDao();
            dao.Actualizar(tipoHabitacionM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
     public void delete() throws Exception {
        TipoHabitacionDao dao;
        try {
            dao = new TipoHabitacionDao();
            dao.delete(tipoHabitacionM.getCod_Tip());
            star();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        tipoHabitacionM = new TipoHabitacionM();

    }

    public List<TipoHabitacionM> getLstTipHab() {
        return LstTipHab;
    }

    public void setLstTipHab(List<TipoHabitacionM> LstTipHab) {
        this.LstTipHab = LstTipHab;
    }

    public TipoHabitacionM getTipoHabitacionM() {
        return tipoHabitacionM;
    }

    public void setTipoHabitacionM(TipoHabitacionM tipoHabitacionM) {
        this.tipoHabitacionM = tipoHabitacionM;
    }

}
