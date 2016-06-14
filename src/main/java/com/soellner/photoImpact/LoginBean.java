package com.soellner.photoImpact;

import com.soellner.photoImpact.data.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

/**
 * Created by Alex on 11.06.2016.
 */
@ManagedBean(name = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String _uname;
    private String _password;


    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getUname() {
        return _uname;
    }

    public void setUname(String uname) {
        this._uname = uname;
    }

    public String loginProject() {

        boolean noLogin=true;

        if (!_uname.equals("") && !_password.equals("")) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
            EntityManager manager = factory.createEntityManager();
            int numberOfUsers = manager.createQuery("Select a From User a where User.login="+_uname, User.class).getResultList().size();
            if(numberOfUsers>0) {
                return "home";
            }

        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!",
                        "Please Try Again!"));
        return "login";

        /*
        //boolean result = UserDAO.login(_uname, _password);
        if (!true) {
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "login";
        }
        */
    }
}