package com.david.springbootboard.controller;

import com.david.springbootboard.dto.ReplyDto;
import com.david.springbootboard.entity.Reply;
import com.david.springbootboard.service.BoardService;
import com.david.springbootboard.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/replies")
@RestController
@Slf4j
@AllArgsConstructor
public class ReplyController {
    private BoardService boardService;
    private ReplyService replyService;

    @PostMapping(value = "/new")
    public ResponseEntity<String> create(@RequestBody Map<String, Object> json) {
        Long boardId = Long.parseLong((String)json.get("boardId"));
        String content = (String)json.get("content");

        Reply reply = new Reply();
        reply.setBoard(boardService.findBoardbyId(boardId));
        reply.setContent(content);
        if (replyService.register(reply) == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }

    @GetMapping("/{replyId}")
    public ReplyDto get(@PathVariable("replyId") Long replyId) {
        return replyService.get(replyId).replyDto();
    }

    @GetMapping("/pages/{boardId}/{pageNum}")
    public Page<ReplyDto> getList(@PathVariable("boardId") Long boardId, @PathVariable("pageNum") int pageNum) {
        pageNum -= 1;
        PageRequest pageRequest = PageRequest.of(pageNum, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<ReplyDto> replyDtos = replyService.getList(boardId, pageRequest).map(Reply::replyDto);
        return replyDtos;
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<String> delete(@PathVariable("replyId") Long replyId) {
        replyService.remove(replyId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
