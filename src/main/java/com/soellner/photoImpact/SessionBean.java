package com.soellner.photoImpact;

import com.soellner.photoImpact.data.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {
    private User _user;

    public User getUser() {
        return _user;
    }

    public void setUser(User user) {
        _user = user;
    }
}
