package com.mdg.sociallogintopaymentspring_bootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdg.sociallogintopaymentspring_bootjpa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
