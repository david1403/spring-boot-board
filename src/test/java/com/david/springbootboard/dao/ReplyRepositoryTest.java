package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReplyRepositoryTest {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Rollback(false)
    public void GetIDTest() {
        Reply reply = new Reply();
        replyRepository.save(reply);
        System.out.println("-------------------------------------");
        entityManager.flush();
        entityManager.clear();
        Long id = reply.getReplyId();
        System.out.println("id = " + id);
    }

    @Test
    public void deleteTest() {
        Reply reply = replyRepository.findByReplyId(19L);
        Board board = reply.getBoard();
        replyRepository.deleteById((long) 19);
        board.decreaseReplyCount();


        entityManager.flush();
        entityManager.clear();
    }
}