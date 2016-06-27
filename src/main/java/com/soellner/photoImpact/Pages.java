package com.soellner.photoImpact;

import javax.faces.bean.ManagedBean;

/**
 * Created by Alex on 27.06.2016.
 */
@ManagedBean
public class Pages {

    private String dynamicaPagesInclude;

    public String getDynamicaPagesInclude() {
        return dynamicaPagesInclude;
    }

    public void setDynamicaPagesInclude(String dynamicaPagesInclude) {
        this.dynamicaPagesInclude = dynamicaPagesInclude;
    }

    public void changePage(int itemSelected ) {
        if (itemSelected == 1) {
            dynamicaPagesInclude = "photos.xhtml";
        } else {
            dynamicaPagesInclude = "gps.xhtml";
        }
    }
}