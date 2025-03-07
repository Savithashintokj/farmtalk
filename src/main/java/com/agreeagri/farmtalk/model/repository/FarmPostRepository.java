package com.agreeagri.farmtalk.model.repository;

import com.agreeagri.farmtalk.model.entity.FarmPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FarmPostRepository extends JpaRepository<FarmPost, UUID> {

    // Custom query to find all posts by a specific user
    List<FarmPost> findByUserAccount_UserId(Long userId);

    // Custom query to find posts by a title (for example)
    List<FarmPost> findByTitleContaining(String title);
}