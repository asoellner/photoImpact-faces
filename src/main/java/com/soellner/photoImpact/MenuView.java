package com.soellner.photoImpact;

/**
 * Created by asoel on 28.06.2016.
 */

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuView {

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

        DefaultMenuItem gpsItem = new DefaultMenuItem("Gps");
        gpsItem.setIcon("ui-icon-signal-diag");
        gpsItem.setAjax(true);
        gpsItem.setCommand("#{pages.changePage}");
        gpsItem.setUpdate("outputPanel");
        gpsItem.setParam("id", 2);

        firstSubmenu.addElement(gpsItem);

        DefaultMenuItem logoutItem = new DefaultMenuItem("Logout");
        logoutItem.setIcon("ui-icon-power");
        //logoutItem.setAjax(true);
        logoutItem.setUrl("login.xhtml");
        //gpsItem.setParam("id", 3);

        firstSubmenu.addElement(logoutItem);

        model.addElement(firstSubmenu);

        /*//Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

        item = new DefaultMenuItem("Save");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{menuView.save}");
        item.setUpdate("messages");
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Delete");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Redirect");
        item.setIcon("ui-icon-search");
        item.setCommand("#{menuView.redirect}");
        secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);*/
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
}