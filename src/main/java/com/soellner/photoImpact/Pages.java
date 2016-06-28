package com.soellner.photoImpact;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 * Created by Alex on 27.06.2016.
 */
@ManagedBean
public class Pages {

    private String dynamicaPagesInclude = "welcome.xhtml";

    public String getDynamicaPagesInclude() {
        return dynamicaPagesInclude;
    }

    public void setDynamicaPagesInclude(String dynamicaPagesInclude) {
        this.dynamicaPagesInclude = dynamicaPagesInclude;
    }

    /*public void changePage(int itemSelected) {
        if (itemSelected == 1) {
            dynamicaPagesInclude = "photos.xhtml";
        } else if (itemSelected == 2) {
            dynamicaPagesInclude = "gps.xhtml";
        } else {
            dynamicaPagesInclude = "welcome.xhtml";
        }
    }
*/
    public void changePage(ActionEvent event) {
        MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
        int itemSelected = Integer.valueOf(menuItem.getParams().get("id").get(0));
       if (itemSelected == 1) {
            dynamicaPagesInclude = "photos.xhtml";
        } else if (itemSelected == 2) {
            dynamicaPagesInclude = "gps.xhtml";
        } else {
            dynamicaPagesInclude = "welcome.xhtml";
        }
    }
}