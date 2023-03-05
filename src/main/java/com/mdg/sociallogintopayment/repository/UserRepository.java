package com.mdg.sociallogintopayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdg.sociallogintopayment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMemberId(String memberId);
}
