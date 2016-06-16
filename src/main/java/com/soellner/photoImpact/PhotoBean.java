package com.soellner.photoImpact;

import com.soellner.photoImpact.data.Photo;
import com.soellner.photoImpact.data.User;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "photoBean")
@SessionScoped
public class PhotoBean {

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("photosMySQL");
        EntityManager manager = factory.createEntityManager();
        List<Photo> photos = manager.createQuery("SELECT a FROM Photo a", Photo.class).getResultList();
        if (!photos.isEmpty()) {
            return new DefaultStreamedContent(new ByteArrayInputStream(photos.get(0).getImage()));

        }

        return null;

    }
}
