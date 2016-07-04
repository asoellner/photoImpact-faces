package com.soellner.photoImpact;

import com.soellner.photoImpact.data.Location;
import com.soellner.photoImpact.data.User;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "locationBean")
@ViewScoped
public class LocationBean {
    private static String PERSISTENCE_UNIT = "soellnerMySQL";

    @ManagedProperty(value = "#{pages}")
    private Pages pages;


    public MapModel getAllLocationsMapModel() throws IOException, SQLException {

        User currentGpsUser = pages.getGpsUser();
        assert currentGpsUser != null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager manager = factory.createEntityManager();
        List<Location> locations = manager.createQuery("SELECT a FROM Location a WHERE a.userID=" + currentGpsUser.getId() + " order by a.id DESC", Location.class).setMaxResults(10).getResultList();


        MapModel model = new DefaultMapModel();

        boolean lastKownPostion = true;
        for (Location location : locations) {

            Double latidue = location.getLatitude();
            Double longitude = location.getLongitude();
            Date date = new Date(Long.valueOf(location.getDateTime()));
            SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Marker marker = new Marker(new LatLng(latidue, longitude), dateformat.format(date));
            if (lastKownPostion) {
                marker.setIcon("http://www.google.com/mapfiles/markerC.png");
                lastKownPostion = false;
            }

            model.addOverlay(marker);


        }


        return model;
        //return new DefaultStreamedContent(new ByteArrayInputStream(image));


    }

    public String getLastTrack() {
        User currentGpsUser = pages.getGpsUser();
        assert currentGpsUser != null;


        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager manager = factory.createEntityManager();
        List<Location> locations = manager.createQuery("SELECT a FROM Location a WHERE a.userID=" + currentGpsUser.getId() + " order by a.id DESC", Location.class).setMaxResults(1).getResultList();
        String center = "";

        if (!locations.isEmpty()) {
            Date date = new Date(Long.valueOf(locations.get(0).getDateTime()));
            SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            return dateformat.format(date);
        }
        return "no tracking available";
    }

    public String getCenterOfLocations() throws IOException, SQLException {
        User currentGpsUser = pages.getGpsUser();
        assert currentGpsUser != null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager manager = factory.createEntityManager();
        List<Location> locations = manager.createQuery("SELECT a FROM Location a WHERE a.userID=" + currentGpsUser.getId() + " order by a.id DESC", Location.class).setMaxResults(10).getResultList();
        String center = "";

        for (Location location : locations) {

            Double latidue = location.getLatitude();
            Double longitude = location.getLongitude();
            center = latidue + "," + longitude;
            return center;


        }


        return "0,0";
        //return new DefaultStreamedContent(new ByteArrayInputStream(image));


    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
