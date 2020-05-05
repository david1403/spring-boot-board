package com.david.springbootboard.service;

import com.david.springbootboard.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    public void testLogin() {
        String id = "david1403";
        String password = "1234";
        Member member = memberService.login(id, password);
        Member login = memberService.login(id, password + "5");
        System.out.println(member);
        System.out.println(login);
    }
}