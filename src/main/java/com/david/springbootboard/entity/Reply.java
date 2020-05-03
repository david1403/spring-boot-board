package com.david.springbootboard.entity;

import com.david.springbootboard.dto.ReplyDto;
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

    public ReplyDto replyDto() {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setBoard(this.board.mainPageBoardDto());
        replyDto.setContent(this.content);
        replyDto.setReplyId(this.replyId);
        replyDto.setWriter(this.writer);
        return replyDto;
    }
}
