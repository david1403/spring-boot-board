package com.david.springbootboard.service;

import com.david.springbootboard.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyService {
    public Reply register(Reply reply);
    public void remove(Long replyId);
    public Reply get(Long replyId);
    public Page<Reply> getList(Long boardId, Pageable pageable);
}
