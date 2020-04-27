package com.david.springbootboard.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="MEMBER_LIKE_BOARD")
@Getter @Setter @ToString
@DynamicInsert
@DynamicUpdate
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLikeBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    private Integer liked;

    public void addLike() {
        this.liked += 1;
        this.board.increaseLikeCount();
    }

    public void disLike() {
        this.liked -= 1;
        this.board.decreaseLikeCount();
    }
}
