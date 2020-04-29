package com.david.springbootboard.dto;

import com.david.springbootboard.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = "writer")
public class MainPageBoardDto {
    private Long boardId;
    private String title;
    private String content;
    // have to change to MemberDTO
    private Member writer;
    private Integer likeCount;
    private Integer replyCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
