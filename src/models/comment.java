package models;


public class comment {

    private int commentId ;
    private String commentContent;

    private int postId;

    private int likes;

    public comment() {
    };

    public comment(int commentId, int postId, String commentContent) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentContent = commentContent;
    }

    public comment(int commentId, String commentContent, int likes) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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
