package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Reply r where r.board.boardId = :boardId")
    public int deleteByBoardId(@Param("boardId") Long boardId);


    @EntityGraph(attributePaths = {"board"})
    @Query("select r from Reply r where r.board.boardId = :boardId")
    Page<Reply> findByBoardId(@Param("boardId") Long boardId, Pageable pageable);

    @EntityGraph(attributePaths = {"board"})
    Reply findByReplyId(Long replyId);

}
