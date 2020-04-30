package com.david.springbootboard.service;

import com.david.springbootboard.dao.BoardRepository;
import com.david.springbootboard.dao.ReplyRepository;
import com.david.springbootboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
    BoardRepository boardRepository;
    ReplyRepository replyRepository;

    @Override
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board findBoardbyId(Long id) {
        return boardRepository.fetch_FindByBoardId(id);
    }
    @Override
    @Transactional
    public boolean remove(Long boardId) {
        replyRepository.deleteByBoardId(boardId);
        boardRepository.deleteBoard(boardId);
        return true;
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> findByTitleContaining(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(keyword, pageable);
    }

    @Override
    public Page<Board> findByContentContaining(String keyword, Pageable pageable) {
        return boardRepository.findByContentContaining(keyword, pageable);
    }
}
