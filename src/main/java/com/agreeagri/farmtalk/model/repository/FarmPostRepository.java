package com.agreeagri.farmtalk.model.repository;

import com.agreeagri.farmtalk.model.entity.FarmPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FarmPostRepository extends JpaRepository<FarmPost, UUID> {
    // You can add custom queries here if needed
}