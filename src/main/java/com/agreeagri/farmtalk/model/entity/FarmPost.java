package com.agreeagri.farmtalk.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "farmpost")
@Data
public class FarmPost {

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

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate UUID
    @Column(name = "POST_ID", updatable = false, nullable = false)
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount userAccount;

    @Column(name = "title")
    private String title;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "photo")
    private String photo;

    @Column(name = "likes_count", nullable = false, columnDefinition = "int default 0")
    private int likesCount;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(name = "created_timestamp", nullable = false, updatable = false)
    private Timestamp createdTimestamp;

    @Column(name = "update_timestamp")
    private Timestamp updateTimestamp;

    @PrePersist
    public void prePersist() {
        if (this.createdTimestamp == null) {
            this.createdTimestamp = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp.toLocalDateTime();
    }

}
