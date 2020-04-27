package com.david.springbootboard.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@DynamicInsert
@DynamicUpdate
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends  BaseEntity {
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



    public void setBoard(Board board) {
        board.getReplyList().add(this);
        board.increaseReplyCount();
        this.board = board;
    }
}
