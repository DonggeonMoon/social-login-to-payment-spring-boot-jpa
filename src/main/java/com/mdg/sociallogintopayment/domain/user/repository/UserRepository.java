package com.mdg.sociallogintopayment.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdg.sociallogintopayment.domain.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMemberId(String memberId);
    void deleteByMemberId(String memberId);
}
