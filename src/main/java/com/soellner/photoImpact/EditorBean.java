package com.soellner.photoImpact;

import javax.faces.bean.ManagedBean;

/**
 * Created by Alex on 11.06.2016.
 */

@ManagedBean(name = "editor")
public class EditorBean {

    private String value = "This editor is provided by PrimeFaces";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
