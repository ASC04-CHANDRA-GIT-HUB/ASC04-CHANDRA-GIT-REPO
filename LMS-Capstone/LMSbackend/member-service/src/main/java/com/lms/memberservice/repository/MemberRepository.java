package com.lms.memberservice.repository;

import com.lms.memberservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByDeletedFalse();
}
