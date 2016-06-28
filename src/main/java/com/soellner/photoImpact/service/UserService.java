package com.soellner.photoImpact.service;

import com.soellner.photoImpact.data.Photo;
import com.soellner.photoImpact.data.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asoel on 28.06.2016.
 */
@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {
    private static String PERSISTENCE_UNIT = "soellnerMySQL";

    public List<User> getUsers() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager manager = factory.createEntityManager();
        return manager.createQuery("Select a From User a", User.class).getResultList();
    }

    public String createUser(String username, String password) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager manager = factory.createEntityManager();
        // int numberOfUsers = manager.createQuery("Select a From User a where a.login LIKE ?1", User.class).setParameter(1, "asoel").getResultList().size();

        List<User> users = manager.createQuery("Select a From User a where a.login=?1", User.class).setParameter(1, username).getResultList();
        if (!users.isEmpty()) {
            return "User already exists";
        }
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        User user = new User();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
        Date dt = new Date();
        String readableDate = sdf.format(dt);
        user.setCreationDate(readableDate);
        user.setLogin(username);
        user.setPass(password);
        manager.persist(user);
        tx.commit();


        return "";
    }
}
