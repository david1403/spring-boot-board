package com.david.springbootboard.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void TestEntity() {
        Member member = new Member();
        member.setId("david1403");
        member.setPassword("1111");
        em.persist(member);

        Board board1 = new Board();
        board1.setTitle("t1");
        board1.setContent("c1");
        board1.setWriter(member);

        em.persist(board1);
    }
}