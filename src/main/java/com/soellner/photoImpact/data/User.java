package com.soellner.photoImpact.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by Alex on 11.06.2016.
 */
@Entity
public class User {
    private int _id;
    private String _login;
    private String _pass;
    private String _creationDate="";


    public User() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    public String getPass() {
        return _pass;
    }

    public void setPass(String pass) {
        _pass = pass;
    }


    public String getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(String creationDate) {
        _creationDate = creationDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", _login='" + _login + '\'' +
                ", _pass='" + _pass + '\'' +
                ", _creationDate='" + _creationDate + '\'' +
                '}';
    }
}
