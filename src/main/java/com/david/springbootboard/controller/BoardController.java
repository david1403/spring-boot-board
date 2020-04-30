package com.david.springbootboard.controller;

import com.david.springbootboard.dto.MainPageBoardDto;
import com.david.springbootboard.dto.PageDto;
import com.david.springbootboard.entity.Board;
import com.david.springbootboard.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import javax.persistence.EntityManager;

@Controller
@Slf4j
@RequestMapping("/board/*")
@SessionAttributes("pageDto")
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private EntityManager entityManager;

    @GetMapping("/list")
    public String list(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "type", required = false, defaultValue = "") String type,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        pageNum -= 1;
        PageRequest pageRequest = PageRequest.of(pageNum, amount, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<MainPageBoardDto> page = null;
        if (type.equals("T")) {
            page = boardService.findByTitleContaining(keyword, pageRequest).map(Board::mainPageBoardDto);
        }
        else if (type.equals("C")) {
            page =boardService.findByContentContaining(keyword, pageRequest).map(Board::mainPageBoardDto);
        }
        else {
            page = boardService.findAll(pageRequest).map(Board::mainPageBoardDto);
        }
        PageDto pageDto = new PageDto(page, type, keyword);
        model.addAttribute("list", page.getContent());
        model.addAttribute("pageDto", pageDto);
        return "/board/list";
    }
    @GetMapping("/register")
    public void register(){

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

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam(name = "boardId") Long boardId, Model model) {
        model.addAttribute("board", boardService.findBoardbyId(boardId));
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

    @PostMapping("remove")
    public String remove(@RequestParam("boardId") Long boardId) {
        boardService.remove(boardId);
        return "redirect:/board/list";
    }
}
