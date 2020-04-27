package com.david.springbootboard.controller;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.annotation.ExceptionProxy;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap()
                .toString()
        );
    }

    @Test
    @Rollback(false)
    public void testRegister() throws Exception {
        Board board = new Board();
        Member member = new Member();
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("content", "content4")
                .param("title", "title4")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRead() throws Exception {
        String string = mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
                .param("boardId", "2"))
                .andReturn()
                .getModelAndView()
                .getModelMap()
                .toString();

        log.info(string);
    }

    @Test
    @Rollback(false)
    public void testModify() throws Exception {
        String resultPage = mockMvc
                .perform(MockMvcRequestBuilders.post("/board/modify")
                        .param("title", "new_title")
                        .param("content", "new_content")
                        .param("boardId", "2"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }
}