package com.mdg.sociallogintopaymentspring_bootjpa.repository;

import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
