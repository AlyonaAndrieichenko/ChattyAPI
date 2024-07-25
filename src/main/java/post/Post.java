package post;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class Post {
    private String id;
    private String title;
    private String description;
    private String body;
    private String imageUrl;
    private String publishDate;
    private String updatedAt;
    @Expose(serialize = false, deserialize = true)
    private boolean draft;
    private String userId;

    public Post(String title, String description, String body, String imageUrl, boolean draft) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.imageUrl = imageUrl;
        this.draft = draft;
    }

    public Post(String title, String description, String body, String imageUrl, String publishDate, boolean draft) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.draft = draft;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return draft == post.draft && Objects.equals(id, post.id) && Objects.equals(title, post.title)
                && Objects.equals(description, post.description) && Objects.equals(body, post.body)
                && Objects.equals(imageUrl, post.imageUrl) && Objects.equals(publishDate, post.publishDate)
                && Objects.equals(updatedAt, post.updatedAt) && Objects.equals(userId, post.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, body, imageUrl, publishDate, updatedAt, draft, userId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", draft=" + draft +
                ", userId='" + userId + '\'' +
                '}';
    }
}
