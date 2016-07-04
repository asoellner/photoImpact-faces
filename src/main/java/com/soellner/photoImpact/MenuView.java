package com.soellner.photoImpact;

/**
 * Created by asoel on 28.06.2016.
 */

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.soellner.photoImpact.data.User;
import com.soellner.photoImpact.service.UserService;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.util.List;

@ManagedBean
public class MenuView {

    @ManagedProperty("#{userService}")
    private UserService service;

    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Menu");
        firstSubmenu.setIcon("ui-icon-home");

        DefaultMenuItem photoItem = new DefaultMenuItem("Photos");
        //item.setUrl("http://www.primefaces.org");
        photoItem.setIcon("ui-icon-image");
        photoItem.setAjax(true);
        photoItem.setCommand("#{pages.changePage}");
        photoItem.setUpdate("outputPanel");
        photoItem.setParam("id", 1);

        firstSubmenu.addElement(photoItem);


        List<User> users = service.getUsers();
        for (User user : users) {
            DefaultMenuItem gpsItem = new DefaultMenuItem("GPS - " + user.getLogin());
            gpsItem.setIcon("ui-icon-signal-diag");
            gpsItem.setAjax(true);
            gpsItem.setCommand("#{pages.changePage}");
            gpsItem.setUpdate("outputPanel");
            gpsItem.setParam("id", 2);
            gpsItem.setParam("userID", user.getId());

            firstSubmenu.addElement(gpsItem);

        }

/*
        DefaultMenuItem userItem = new DefaultMenuItem("User Management");
        userItem.setIcon("ui-icon-person");
        userItem.setAjax(true);
        userItem.setCommand("#{pages.changePage}");
        userItem.setUpdate("outputPanel");
        userItem.setParam("id", 3);

        firstSubmenu.addElement(userItem);*/

        DefaultMenuItem logoutItem = new DefaultMenuItem("Logout");
        logoutItem.setIcon("ui-icon-power");
        //logoutItem.setAjax(true);
        //logoutItem.setUrl("login.xhtml");
        logoutItem.setCommand("#{loginBean.logoutProject}");
        //gpsItem.setParam("id", 3);

        firstSubmenu.addElement(logoutItem);

        model.addElement(firstSubmenu);


    }

    public MenuModel getModel() {
        return model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

}