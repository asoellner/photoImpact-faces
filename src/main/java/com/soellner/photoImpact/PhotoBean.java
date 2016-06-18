package com.soellner.photoImpact;

import com.soellner.photoImpact.data.Photo;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "photoBean")
@SessionScoped
public class PhotoBean {


    public MapModel getAllPhotosMapModel() throws IOException, SQLException {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        List<Photo> photos = manager.createQuery("SELECT a FROM Photo a order by a.id DESC", Photo.class).getResultList();
        MapModel model = new DefaultMapModel();

        for (Photo photo : photos) {
            if (photo.getGpsLatidude() != null && photo.getGpsLongitude() != null) {
                Double latidue = Double.valueOf(photo.getGpsLatidude());
                Double longitude = Double.valueOf(photo.getGpsLongitude());
                model.addOverlay(new Marker(new LatLng(latidue, longitude), "M" + photos.indexOf(photo)));

            }


        }


        return model;
        //return new DefaultStreamedContent(new ByteArrayInputStream(image));


    }

    public String getCenterOfAllPhotosMapModel() throws IOException, SQLException {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        List<Photo> photos = manager.createQuery("SELECT a FROM Photo a order by a.id DESC", Photo.class).getResultList();
        String center = "";

        for (Photo photo : photos) {
            if (photo.getGpsLatidude() != null && photo.getGpsLongitude() != null) {
                Double latidue = Double.valueOf(photo.getGpsLatidude());
                Double longitude = Double.valueOf(photo.getGpsLongitude());
                center = latidue + "," + longitude;
                return center;
            }


        }


        return "0,0";
        //return new DefaultStreamedContent(new ByteArrayInputStream(image));


    }


    public StreamedContent getPhoto() throws IOException, SQLException {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("pid");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
            EntityManager manager = factory.createEntityManager();
            List<Photo> photos = manager.createQuery("SELECT a FROM Photo a where a.id=?1", Photo.class).setParameter(1, id).getResultList();
            if (!photos.isEmpty()) {
                return new DefaultStreamedContent(new ByteArrayInputStream(photos.get(0).getImage()));

            }

            return null;
            //return new DefaultStreamedContent(new ByteArrayInputStream(image));

        }
    }

    public MapModel getCustomMapModel(String id) throws IOException, SQLException {


        FacesContext context = FacesContext.getCurrentInstance();


        //String id = context.getExternalContext().getRequestParameterMap().get("pid");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        List<Photo> photos = manager.createQuery("SELECT a FROM Photo a where a.id=?1", Photo.class).setParameter(1, id).getResultList();
        if (!photos.isEmpty()) {
            MapModel model = new DefaultMapModel();
            Photo photo = photos.get(0);
            Double latidue = Double.valueOf(photo.getGpsLatidude());
            Double longitude = Double.valueOf(photo.getGpsLongitude());

            model.addOverlay(new Marker(new LatLng(latidue, longitude), "M1"));
            return model;
        }

        return null;
        //return new DefaultStreamedContent(new ByteArrayInputStream(image));


    }


    public List<Photo> getImages() throws IOException {

        // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        List<Photo> photos = manager.createQuery("SELECT a FROM Photo a order by a.id DESC", Photo.class).getResultList();
        if (!photos.isEmpty()) {
            return photos;

        }

        return Collections.emptyList();

    }


}
