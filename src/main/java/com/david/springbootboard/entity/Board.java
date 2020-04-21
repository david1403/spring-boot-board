package com.david.springbootboard.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(exclude = {"writer"})
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long boardId;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer_id")
    private Member writer;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name="reply_count")
    private Integer replyCount;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "board")
    private List<Reply> replyList = new ArrayList<>();

    public Integer getReplyCount() {
        return this.replyList.size();
    }
    public void increaseLikeCount() {
        this.likeCount += 1;
    }
    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }
}
