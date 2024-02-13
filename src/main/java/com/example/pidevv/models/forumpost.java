package com.example.pidevv.models;

import javax.swing.text.AbstractDocument;

public class forumpost {
    private int postId ;
    private String title ;
    private String content;

    public forumpost(){};

    public forumpost(int postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    //constructeur pour linsertion
    public forumpost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "forumpost{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
