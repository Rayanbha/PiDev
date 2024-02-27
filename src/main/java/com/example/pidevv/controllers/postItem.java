package com.example.pidevv.controllers;

import com.example.pidevv.models.forumpost;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class postItem {
    @FXML
    private Label title;

    @FXML
    private ImageView content;


    public void setData(forumpost fp) {
        title.setText(fp.getTitle());
        String imageUrl =  fp.getContent();
        try {
            Image image1 = new Image(imageUrl);
            content.setImage(image1);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
