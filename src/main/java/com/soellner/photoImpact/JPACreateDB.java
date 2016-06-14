package com.soellner.photoImpact;

import com.soellner.photoImpact.data.Photo;
import com.soellner.photoImpact.data.User;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asoel on 14.06.2016.
 */
@ManagedBean(name = "jpaTest")
public class JPACreateDB {


    public JPACreateDB(EntityManager manager) {

    }


    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        JPACreateDB test = new JPACreateDB(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            //test.createPhotos(manager);
            test.createUser(manager);
            test.createPhotos(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listPhotos(manager);

        System.out.println(".. done");


    }


    private void createUser(EntityManager manager) {
        int numOfEmployees = manager.createQuery("Select a From User a", User.class).getResultList().size();
        //if (numOfEmployees == 0) {
        if (numOfEmployees == 0) {
            User user = new User();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
            Date dt = new Date();
            String readableDate = sdf.format(dt);
            user.setCreationDate(readableDate);
            user.setLogin("asoel");
            user.setPass("pass");
            manager.persist(user);
            System.out.println("created user: " + user);
        }


    }

    private void createPhotos(EntityManager manager) {
        int numOfEmployees = manager.createQuery("Select a From Photo a", Photo.class).getResultList().size();
        //if (numOfEmployees == 0) {
        if (numOfEmployees == 0) {
            Photo photo = new Photo();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
            Date dt = new Date();
            String readableDate = sdf.format(dt);
            photo.setDate(readableDate);

            manager.persist(photo);
            System.out.println("created Photo: " + photo);
        }


        //}
    }

    private void listPhotos(EntityManager manager) {
        List<Photo> resultList = manager.createQuery("Select a From Photo a", Photo.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (Photo next : resultList) {
            System.out.println("next Photo: " + next);
        }
    }
}