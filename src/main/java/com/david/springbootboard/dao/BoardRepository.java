package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    public Board findByBoardId(Long boardId);

    @Query("select b from Board b left join fetch b.replyList where b.boardId = :boardId")
    public Board fetch_FindByBoardId(@Param("boardId") Long boardId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Board b where b.boardId = :boardId")
    int deleteBoard(@Param("boardId") Long boardId);

    @Override
    Page<Board> findAll(Pageable pageable);
}
