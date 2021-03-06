package com.david.springbootboard.dto;

import com.david.springbootboard.entity.Board;
import com.david.springbootboard.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString(exclude = {"writer"})
@Setter
public class ReplyDto {
    private Long replyId;
    private String content;
    // change to memberdto
    private Member writer;
    private MainPageBoardDto board;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
