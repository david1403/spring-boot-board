CREATE TABLE USER
(
    `user_pk`        BIGINT         NOT NULL    AUTO_INCREMENT COMMENT 'user_pk', 
    `user_id`        varchar(20)    NOT NULL    COMMENT 'user_id', 
    `name`           VARCHAR(20)    NOT NULL    COMMENT 'name', 
    `age`            INT            NOT NULL    COMMENT 'age', 
    `password`       VARCHAR(20)    NOT NULL    COMMENT 'password', 
    `created_date`   DATETIME       NOT NULL    COMMENT 'created_date' default current_timestamp, 
    `modified_date`  DATETIME       NOT NULL    COMMENT 'modified_date' default current_timestamp on update current_timestamp, 
    PRIMARY KEY (user_pk)
);

ALTER TABLE USER COMMENT 'USER';

ALTER TABLE USER COMMENT 'USER';

CREATE TABLE BOARD
(
    `board_pk`       BIGINT           NOT NULL    AUTO_INCREMENT COMMENT 'board_pk', 
    `title`          VARCHAR(45)      NOT NULL    COMMENT 'title', 
    `content`        VARCHAR(1000)    NOT NULL    COMMENT 'content', 
    `writer_pk`      BIGINT           NOT NULL    COMMENT 'writer_pk', 
    `likes`          INT              NOT NULL    COMMENT 'likes', 
    `created_date`   DATETIME       NOT NULL    COMMENT 'created_date' default current_timestamp, 
    `modified_date`  DATETIME       NOT NULL    COMMENT 'modified_date' default current_timestamp on update current_timestamp,
    PRIMARY KEY (board_pk)
);

ALTER TABLE BOARD COMMENT 'BOARD';

ALTER TABLE BOARD
    ADD CONSTRAINT FK_BOARD_writer_pk_USER_user_pk FOREIGN KEY (writer_pk)
        REFERENCES USER (user_pk) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE REPLY
(
    `reply_pk`       BIGINT          NOT NULL    AUTO_INCREMENT COMMENT 'reply_pk', 
    `content`        VARCHAR(400)    NOT NULL    COMMENT 'content', 
    `board_pk`       BIGINT          NOT NULL    COMMENT 'board_pk', 
    `writer_pk`      BIGINT          NOT NULL    COMMENT 'writer_pk', 
    `created_date`   DATETIME       NOT NULL    COMMENT 'created_date' default current_timestamp, 
    `modified_date`  DATETIME       NOT NULL    COMMENT 'modified_date' default current_timestamp on update current_timestamp,
    PRIMARY KEY (reply_pk)
);

ALTER TABLE REPLY COMMENT 'REPLY';

ALTER TABLE REPLY
    ADD CONSTRAINT FK_REPLY_writer_pk_USER_user_pk FOREIGN KEY (writer_pk)
        REFERENCES USER (user_pk) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE REPLY
    ADD CONSTRAINT FK_REPLY_board_pk_BOARD_board_pk FOREIGN KEY (board_pk)
        REFERENCES BOARD (board_pk) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE USER_LIKE_BOARD
(
    `like_pk`   BIGINT     NOT NULL    AUTO_INCREMENT COMMENT 'like_pk', 
    `board_pk`  BIGINT     NOT NULL    COMMENT 'board_pk', 
    `user_pk`   BIGINT     NOT NULL    COMMENT 'user_pk', 
    `liked`     TINYINT    NOT NULL    COMMENT 'liked',
    `created_date`   DATETIME       NOT NULL    COMMENT 'created_date' default current_timestamp, 
    `modified_date`  DATETIME       NOT NULL    COMMENT 'modified_date' default current_timestamp on update current_timestamp,
    PRIMARY KEY (like_pk)
);

ALTER TABLE USER_LIKE_BOARD COMMENT 'USER_LIKE_BOARD';

ALTER TABLE USER_LIKE_BOARD
    ADD CONSTRAINT FK_USER_LIKE_BOARD_user_pk_USER_user_pk FOREIGN KEY (user_pk)
        REFERENCES USER (user_pk) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE USER_LIKE_BOARD
    ADD CONSTRAINT FK_USER_LIKE_BOARD_board_pk_BOARD_board_pk FOREIGN KEY (board_pk)
        REFERENCES BOARD (board_pk) ON DELETE RESTRICT ON UPDATE RESTRICT;
