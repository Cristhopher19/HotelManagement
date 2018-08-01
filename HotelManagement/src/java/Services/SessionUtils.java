package Services;

import Modelo.RegistradorM;
import javax.faces.context.FacesContext;

public class SessionUtils {

    public static String ObtenerDNISesion() {
        RegistradorM us = (RegistradorM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return us != null ? us.getDNI_REG() : null;
    }

}
