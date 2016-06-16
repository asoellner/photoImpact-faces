package com.soellner.photoImpact;

import com.soellner.photoImpact.data.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

        //validate user und pass

        if (!_uname.equals("") && !_password.equals("")) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
            EntityManager manager = factory.createEntityManager();
            // int numberOfUsers = manager.createQuery("Select a From User a where a.login LIKE ?1", User.class).setParameter(1, "asoel").getResultList().size();

            List<User> users = manager.createQuery("Select a From User a where a.login=?1 AND a.pass=?2", User.class).setParameter(1, _uname).setParameter(2, _password).getResultList();
            if (!users.isEmpty()) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                sessionMap.put("user", users.get(0));
                FacesContext context = FacesContext.getCurrentInstance();
                SessionBean sessionBean = (SessionBean) context.getApplication().evaluateExpressionGet(context, "#{sessionBean}", SessionBean.class);
                sessionBean.setUser(users.get(0));

                return "home";
            }

        }


        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Invalid Login!",
                        "Please Try Again!"));
        return "login";


    }

    public void logoutProject() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath());
        } catch (IOException e) {
            //Todo
            e.printStackTrace();
        }
    }
}