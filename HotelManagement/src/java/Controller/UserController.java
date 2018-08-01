package Controller;

import DAO.userDao;
import Modelo.RegistradorM;
import Services.SessionUtils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    RegistradorM registradorM = new RegistradorM();
    private String user, psw;
    private boolean admin;
    private boolean usuario;

    public RegistradorM getRegistradorM() {
        return registradorM;
    }

    public void setRegistradorM(RegistradorM registradorM) {
        this.registradorM = registradorM;
    }

    @PostConstruct
    public void inicio() {
        try {

        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validar() throws IOException, Exception {
        RegistradorM us = (RegistradorM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (us == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }
    }

    public void logeoSession() throws Exception {
        userDao dao;
        try {
            dao = new userDao();
            registradorM = dao.UserLogeo(user, psw);
            if (registradorM != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", registradorM);
                switch (registradorM.getNIV_REG()) {
                    case "1":
                        admin = true;
                        usuario = false;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/Vistas/Clientes/Clientes.xhtml");
                        
                        break;
                    case "2":
                        admin = false;
                        usuario = true;
                        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/Vistas/Clientes/Clientes.xhtml");
                        break;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario/Contraseña incorrecto"));
            }
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de conexcion"));
            throw e;
        }
    }

    public void registrar(){
        registradorM.setDNI_REG(SessionUtils.ObtenerDNISesion());
    }
            
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }


}