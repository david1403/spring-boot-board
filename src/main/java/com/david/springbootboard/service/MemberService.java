package com.david.springbootboard.service;

import com.david.springbootboard.entity.Member;

public interface MemberService {
    Member login(String id, String password);
}
