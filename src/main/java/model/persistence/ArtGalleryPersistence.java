package model.persistence;

import model.ArtGallery;
import model.ArtWork;

import java.util.ArrayList;

public class ArtGalleryPersistence extends AbstractPersistence<ArtGallery> {
    public ArtGallery findGalleryByName(String name){
        ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
        ArrayList<ArtGallery> artGalleries= (ArrayList<ArtGallery>) artGalleryPersistence.readAll();
        for(ArtGallery art: artGalleries){
            if(art.getName().equals(name))
                return art;
        }
        return null;
    }

}
