package com.david.springbootboard.service;

import com.david.springbootboard.dao.ReplyRepository;
import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Rollback(false)
    @Transactional
    public void test() {

        for (int i= 0 ; i < 3 ; i++) {
            Board b = new Board();
            b.setTitle(Integer.toString(i));
            b.setContent(Integer.toString(i));
            boardService.save(b);
        }
        for (int i = 1 ; i <= 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                Reply reply = new Reply();
                reply.setContent("hey jude " + j);
                reply.setBoard(boardService.findBoardbyId((long) i));
                replyRepository.save(reply);
            }
        }

        Board b = boardService.findBoardbyId(2L);
        System.out.println(b);
        for (Reply reply : b.getReplyList()) {
            System.out.println(reply);
        }
        boardService.remove(1L);
        for (Reply reply : replyRepository.findAll()) {
            System.out.println(reply);
        }
        Board b1 = boardService.findBoardbyId(2L);
    }
}