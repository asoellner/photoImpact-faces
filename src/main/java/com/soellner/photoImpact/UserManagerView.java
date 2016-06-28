package com.soellner.photoImpact;

import com.soellner.photoImpact.data.User;
import com.soellner.photoImpact.service.UserService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Created by asoel on 28.06.2016.
 */
@ManagedBean(name = "userManagerView")
@ViewScoped
public class UserManagerView implements Serializable {
    private String _username;

    private String _password;

    private List<User> _users;

    @ManagedProperty("#{userService}")
    private UserService service;

    @PostConstruct
    public void init() {
        _users = service.getUsers();
    }

    public List<User> getUsers() {
        return _users;
    }

    public void createUser() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean created = false;
        if (_username != null && _username.equals("") && _password != null && _password.equals("")) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User added", _username);
            String returnMessage = service.createUser(_username, _password);
            created = true;
            if (!returnMessage.equals("")) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, returnMessage, _username);
                created = false;
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "User not created");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("created", created);
    }


    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }


}