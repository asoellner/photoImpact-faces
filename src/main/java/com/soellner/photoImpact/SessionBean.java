package com.soellner.photoImpact;

import com.soellner.photoImpact.data.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "sessionBean")
@ViewScoped
public class SessionBean {
    private User _user;

    public User getUser() {
        return _user;
    }

    public void setUser(User user) {
        _user = user;
    }
}
