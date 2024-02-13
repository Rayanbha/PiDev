package com.example.pidevv.models;


import javax.swing.text.AbstractDocument;

public class comment {

    private int commentId ;
    private String commentContent;

    private int postId;

    public comment() {
    };

    public comment(int commentId, int postId, String commentContent) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentContent = commentContent;
    }

    public comment(String commentContent) {
        this.commentContent = commentContent;
    }

    public comment(int commentId, String commentContent) {
        this.commentId = commentId;
        this.commentContent = commentContent;
    }

    public comment(int postId) {
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", postId=" + postId +
                '}';
    }
}
