package com.david.springbootboard.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_id")
    private Long replyId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer_id")
    private Member writer;

    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;


    public void setBoard(Board board) {
        board.getReplyList().add(this);
        this.board = board;
    }
}
