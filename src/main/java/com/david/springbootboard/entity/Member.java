package com.david.springbootboard.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @ToString
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_pk")
    private Long memberPk;

    private String id;
    private String name;
    private String password;
    private int age;

    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
}
