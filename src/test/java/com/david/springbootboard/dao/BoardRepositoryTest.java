package com.david.springbootboard.dao;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Member;
import com.david.springbootboard.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void CRUDTest() {
        Member member = new Member();
        member.setName("david1403");
        Member member2 = new Member();
        member2.setName("another one");

        memberRepository.save(member);
        memberRepository.save(member2);

        Board board = new Board();
        board.setTitle("Title1");
        board.setWriter(member);

        boardRepository.save(board);

        Reply reply1 = new Reply();
        reply1.setContent("reply1");
        reply1.setBoard(board);
        reply1.setWriter(member2);

        Reply reply2 = new Reply();
        reply2.setContent("reply2");
        reply2.setBoard(board);
        reply2.setWriter(member);
        replyRepository.save(reply1);
        replyRepository.save(reply2);

        em.flush();
        em.clear();

        /*
        List<Reply> replyList = replyRepository.findAll();
        for (Reply reply : replyList) {
            System.out.println(reply.getBoard().getTitle());
        }
        for (Reply reply : replyList) {
            System.out.println(reply.getWriter().getName());
        }*/
        Board findBoard = boardRepository.findByBoardId(1L);

        List<Reply> replyList = findBoard.getReplyList();
        for (Reply reply : replyList) {
            System.out.println("reply = " + reply.getContent());
        }

        em.flush();
        em.clear();
        System.out.println("===================================");
        Board findBoard2 = boardRepository.fetch_FindByBoardId(1L);

        List<Reply> replyLists = findBoard2.getReplyList();
        for (Reply reply : replyLists) {
            System.out.println("reply = " + reply.getContent());
        }
    }
}