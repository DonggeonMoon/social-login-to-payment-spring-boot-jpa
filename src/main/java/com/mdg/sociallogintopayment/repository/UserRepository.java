package com.mdg.sociallogintopayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdg.sociallogintopayment.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
