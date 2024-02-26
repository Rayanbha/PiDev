package org.example.models;

public class review {
    private int idrevw;
    private String rating;
    private String com;
    private String imageUrl; // URL of the image associated with the review

    public review(){
    }

    public review(String rating, String com) {
        this.rating = rating;
        this.com = com;
    }

    public review(int idrevw, String rating, String com) {
        this.idrevw = idrevw;
        this.rating = rating;
        this.com = com;
    }

    public int getIdrevw() {
        return idrevw;
    }

    public void setIdrevw(int idrevw) {
        this.idrevw = idrevw;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "review{" +
                "idrevw=" + idrevw +
                ", rating='" + rating + '\'' +
                ", com='" + com + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
