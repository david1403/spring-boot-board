package com.david.springbootboard.service;

import com.david.springbootboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    public void save(Board board);
    public Board findBoardbyId(Long id);
    public boolean remove(Long id);
    public Page<Board> findAll(Pageable pageable);
}
