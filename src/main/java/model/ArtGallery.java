package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artGallery")
public class ArtGallery {
    @Id
    @Column(unique = true, nullable = false)
    private int idGallery;
    private String name;
    private String location;

    public ArtGallery(){}
    public ArtGallery(int idGallery, String name, String location) {
        this.idGallery = idGallery;
        this.name = name;
        this.location = location;
    }

    public int getIdGallery() {
        return idGallery;
    }

    public void setIdGallery(int idGallery) {
        this.idGallery = idGallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
