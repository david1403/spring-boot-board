package com.david.springbootboard.service;

import com.david.springbootboard.dao.ReplyRepository;
import com.david.springbootboard.entity.Reply;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private ReplyRepository replyRepository;

    @Override
    public Reply register(Reply reply) {
        try {
            return replyRepository.save(reply);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void remove(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public Reply get(Long replyId) {
        return replyRepository.findById(replyId).get();
    }

    @Override
    public Page<Reply> getList(Long boardId, Pageable pageable) {
        return replyRepository.findByBoardId(boardId, pageable);
    }
}
