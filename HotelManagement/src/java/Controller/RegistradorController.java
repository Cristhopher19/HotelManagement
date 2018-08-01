package Controller;

import DAO.RegistradorDao;
import DAO.UbigeoDao;
import Modelo.RegistradorM;
import Services.Funciones;
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
import org.primefaces.context.RequestContext;

@Named(value = "registradorController")
@SessionScoped
public class RegistradorController implements Serializable {
    
    List<RegistradorM> LstReg = new ArrayList();
    RegistradorM registradorM = new RegistradorM();
    private String user, pwd;
    
    @PostConstruct
    public void star() {
        
        try {
            listMantenimiento();
        } catch (Exception ex) {
            Logger.getLogger(RegistradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listMantenimiento() throws Exception {
        RegistradorDao dao;
        try {
            dao = new RegistradorDao();
            LstReg = dao.listarHab();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void ingresar() throws Exception {
        RegistradorDao dao;
        UbigeoDao dao2;
        try {
            dao = new RegistradorDao();
            dao2 = new UbigeoDao();
            registradorM.setIDUBI(dao2.leerUbigeo(registradorM.getNOMBREUBIGEO()));
            dao.Ingresar(registradorM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar() throws Exception {
        RegistradorDao dao;
        UbigeoDao dao2;
        try {
            dao2 = new UbigeoDao();
            dao = new RegistradorDao();
            registradorM.setIDUBI(dao2.leerUbigeo(registradorM.getNOMBREUBIGEO()));
            dao.Actualizar(registradorM);
            listMantenimiento();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void delete() throws Exception {
        RegistradorDao dao;
        try {
            dao = new RegistradorDao();
            dao.delete(registradorM.getCOD_REG());
            star();
        } catch (Exception e) {
            throw e;
        }
    }

    // LOGIN
//    public void startSession() throws Exception {
//        RegistradorDao dao;
//        try {
//            dao = new RegistradorDao();
//            registradorM = dao.startSession(user, Funciones.encriptar(pwd));
//            if (registradorM != null) {
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("registrador", registradorM);
//                RequestContext.getCurrentInstance().execute("ListarCarrera();");
//                RequestContext.getCurrentInstance().execute("PostSessionAlumno();");
//                FacesContext.getCurrentInstance().getExternalContext().redirect("Vistas/Profesor/Alternancias.xhtml");
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario/Contraseña Inconrrecto"));
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//    public void startSession(String us, String pass) throws Exception {
//        RegistradorDao dao;
//        try {
//            dao = new RegistradorDao();
//            registradorM = dao.startSession(us, Funciones.encriptar(pass));
//            if (registradorM != null) {
//                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("monitor", registradorM);
//                RequestContext.getCurrentInstance().execute("ListarCarrera();");
//                RequestContext.getCurrentInstance().execute("PostSessionAlumno();");
//                RequestContext.getCurrentInstance().execute("$('body').removeClass('loaded');");
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/HotelManagement/faces/Vistas/Recepcionista/Registrador.xhtml");
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario/Contraseña Inconrrecto"));
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    // Get and Set
    public void limpiar() {
        registradorM = new RegistradorM();
        
    }
    
    public List<RegistradorM> getLstReg() {
        return LstReg;
    }
    
    public void setLstReg(List<RegistradorM> LstReg) {
        this.LstReg = LstReg;
    }
    
    public RegistradorM getRegistradorM() {
        return registradorM;
    }
    
    public void setRegistradorM(RegistradorM registradorM) {
        this.registradorM = registradorM;
    }
    
}
