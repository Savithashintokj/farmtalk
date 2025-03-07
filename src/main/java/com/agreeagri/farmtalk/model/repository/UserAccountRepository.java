package com.agreeagri.farmtalk.model.repository;

import com.agreeagri.farmtalk.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    // You can define custom queries if necessary
    UserAccount findByEmail(String email);
}
