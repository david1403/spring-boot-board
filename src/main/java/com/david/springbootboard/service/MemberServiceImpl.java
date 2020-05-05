package com.david.springbootboard.service;

import com.david.springbootboard.dao.MemberRepository;
import com.david.springbootboard.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;

    @Override
    public Member login(String id, String password) {
        return memberRepository.findByIdAndPassword(id, password);
    }
}
