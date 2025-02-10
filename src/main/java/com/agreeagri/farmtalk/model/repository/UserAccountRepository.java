package com.agreeagri.farmtalk.model.repository;

import com.agreeagri.farmtalk.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    // You can add custom queries here if needed
}
