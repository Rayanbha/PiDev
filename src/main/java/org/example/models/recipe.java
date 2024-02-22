package org.example.models;

import javafx.scene.image.Image;

public class recipe {
private int idrecp;
private String name;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
private String ingrs;
private String instrs;

public recipe(){

}
    public recipe(String name, String ingrs, String instrs) {
        this.name = name;
        this.ingrs = ingrs;
        this.instrs = instrs;

    }

    public recipe(int idrecp, String name, String ingrs, String instrs) {
        this.idrecp = idrecp;
        this.name = name;
        this.ingrs = ingrs;
        this.instrs = instrs;

    }

    public int getIdrecp() {
        return idrecp;
    }

    public void setIdrecp(int idrecp) {
        this.idrecp = idrecp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(Image image) {
        this.imageUrl = imageUrl;
    }

    public String getIngrs() {
        return ingrs;
    }

    public void setIngrs(String ingrs) {
        this.ingrs = ingrs;
    }

    public String getInstrs() {
        return instrs;
    }

    public void setInstrs(String instrs) {
        this.instrs = instrs;
    }

    @Override
    public String toString() {
        return "recipe{" +
                "idrecp=" + idrecp +
                ", name='" + name + '\'' +
                ", ingrs='" + ingrs + '\'' +
                ", instrs='" + instrs + '\'' +
                '}';
    }

}
