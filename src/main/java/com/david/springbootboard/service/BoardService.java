package com.david.springbootboard.service;

import com.david.springbootboard.entity.Board;

import java.util.List;

public interface BoardService {
    public void save(Board board);
    public Board findBoardbyId(Long id);
    public boolean remove(Long id);
    public List<Board> findAll();
}
