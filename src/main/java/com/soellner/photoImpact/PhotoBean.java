package com.soellner.photoImpact;

import com.soellner.photoImpact.data.Photo;
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
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by asoel on 16.06.2016.
 */
@ManagedBean(name = "photoBean")
@SessionScoped
public class PhotoBean {


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
