package com.david.springbootboard.dao;

import com.david.springbootboard.entity.MemberLikeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikeBoardRepository extends JpaRepository<MemberLikeBoard, Long> {
}
