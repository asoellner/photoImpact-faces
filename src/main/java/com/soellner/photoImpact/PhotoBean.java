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

    public class geoDegree {
        private boolean valid = false;
        Double Latitude, Longitude;
        geoDegree(ExifInterface exif) {
            String attrLATITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String attrLATITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String attrLONGITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String attrLONGITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            if((attrLATITUDE !=null)
                    && (attrLATITUDE_REF !=null)
                    && (attrLONGITUDE != null)
                    && (attrLONGITUDE_REF !=null))
            {
                valid = true;

                if(attrLATITUDE_REF.equals("N")){
                    Latitude = convertToDegree(attrLATITUDE);
                }
                else{
                    Latitude = 0 - convertToDegree(attrLATITUDE);
                }

                if(attrLONGITUDE_REF.equals("E")){
                    Longitude = convertToDegree(attrLONGITUDE);
                }
                else{
                    Longitude = 0 - convertToDegree(attrLONGITUDE);
                }

            }
        };

        private Double convertToDegree(String stringDMS){
            Float result = null;
            String[] DMS = stringDMS.split(",", 3);

            String[] stringD = DMS[0].split("/", 2);
            Double D0 = new Double(stringD[0]);
            Double D1 = new Double(stringD[1]);
            Double FloatD = D0/D1;

            String[] stringM = DMS[1].split("/", 2);
            Double M0 = new Double(stringM[0]);
            Double M1 = new Double(stringM[1]);
            Double FloatM = M0/M1;

            String[] stringS = DMS[2].split("/", 2);
            Double S0 = new Double(stringS[0]);
            Double S1 = new Double(stringS[1]);
            Double FloatS = S0/S1;

            result = new Float(FloatD + (FloatM/60) + (FloatS/3600));

            return result;


        };

        public boolean isValid()
        {
            return valid;
        }
}
