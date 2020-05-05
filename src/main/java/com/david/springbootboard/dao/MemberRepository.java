package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
     // returns null if no information
     Member findByIdAndPassword(String id, String password);
}
