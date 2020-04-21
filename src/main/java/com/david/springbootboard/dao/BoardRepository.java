package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    public Board findByBoardId(Long boardId);

    @Query("select b from Board b join fetch b.replyList where b.boardId = :boardId")
    public Board fetch_FindByBoardId(@Param("boardId") Long boardId);
}
