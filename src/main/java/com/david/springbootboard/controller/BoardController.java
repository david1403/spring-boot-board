package com.david.springbootboard.controller;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;

@Controller
@Slf4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private EntityManager entityManager;

    @GetMapping("/list")
    public String list(Model model) {

        log.info("----- list -----");
        model.addAttribute("list", boardService.findAll());
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestParam("title") String title,
                           @RequestParam("content") String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);
        return "redirect:/board/list/";
    }

    @GetMapping("/read")
    public String read(@RequestParam(name = "boardId") Long boardId, Model model) {
        model.addAttribute("board", boardService.findBoardbyId(boardId));
        return "read";
    }

    @PostMapping("/modify")
    public String modify(@RequestParam("boardId") Long boardId,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content) {
        Board board = boardService.findBoardbyId(boardId);
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);
        return "redirect:/board/read?boardId=" + boardId;
    }
}
