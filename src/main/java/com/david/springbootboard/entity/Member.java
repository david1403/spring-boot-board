package com.david.springbootboard.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @ToString
@DynamicInsert
@DynamicUpdate
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_pk")
    private Long memberPk;

    private String id;
    private String name;
    private String password;
    private int age;

}
