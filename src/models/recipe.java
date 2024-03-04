package models;

import java.util.ArrayList;
import java.util.List;

public class recipe {
    private int idrecp;
    private String name;
    private String imageUrl;
    private String ingrs;
    private String instrs;
    private List<review> reviews; // Collection of reviews associated with the recipe

    public recipe() {
        this.reviews = new ArrayList<>();
    }

    public recipe(String name, String ingrs, String instrs) {
        this.name = name;
        this.ingrs = ingrs;
        this.instrs = instrs;
        this.reviews = new ArrayList<>();
    }

    public recipe(int idrecp, String name, String ingrs, String instrs) {
        this.idrecp = idrecp;
        this.name = name;
        this.ingrs = ingrs;
        this.instrs = instrs;
        this.reviews = new ArrayList<>();
    }

    public recipe(String name, String imageUrl, String ingrs, String instrs) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingrs = ingrs;
        this.instrs = instrs;
        this.reviews = new ArrayList<>();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
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

    public List<review> getReviews() {
        return reviews;
    }

    public void setReviews(List<review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "recipe{" +
                "idrecp=" + idrecp +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingrs='" + ingrs + '\'' +
                ", instrs='" + instrs + '\'' +
                '}';
    }
}
