package com.agreeagri.farmtalk.model.entity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;



@Entity
@Table(name = "farmpost")
public class FarmPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private UserAccount userAccount;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DETAILS", nullable = false)
    private String details;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "LIKES_COUNT")
    private Integer likesCount;

    @Column(name = "DELETED")
    private Boolean deleted;

    @Column(name = "CREATED_TIMESTAMP", nullable = false)
    private Timestamp createdTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updateTimestamp;

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    @PrePersist
    public void prePersist() {
        if (createdTimestamp == null) {
            createdTimestamp = Timestamp.valueOf(LocalDateTime.now());
        }
    }
    // Getters and Setters
}
