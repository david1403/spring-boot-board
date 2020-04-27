package com.david.springbootboard.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

    @Column(name="created_date", columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createdDate;
    @Column(name="modified_date", columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime modifiedDate;
}
