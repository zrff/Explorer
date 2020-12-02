create database cug_backend CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE user (
    id INT(32) PRIMARY KEY auto_increment,
    nick_name VARCHAR(64) NOT NULL,
    phone_no VARCHAR(11) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    dingtalk_id VARCHAR(64) default '' NOT NULL,
    create_time timestamp default CURRENT_TIMESTAMP,
    update_time timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE leetecode_record (
    id INT(64) PRIMARY KEY auto_increment,
    num INT(32) DEFAULT 0 COMMENT '完成的题数',
    user_id INT(32),
    create_time timestamp default CURRENT_TIMESTAMP,
    update_time timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT '打卡数量';

# 新增记录
insert into cug_backend.user(nick_name, phone_no, password) value('cug',13177436107,'123456');


create table num(
    id int PRIMARY KEY auto_increment
);
INSERT INTO cug_backend.num (id) VALUES (0);
INSERT INTO cug_backend.num (id) VALUES (1);
INSERT INTO cug_backend.num (id) VALUES (2);
INSERT INTO cug_backend.num (id) VALUES (3);
INSERT INTO cug_backend.num (id) VALUES (4);
INSERT INTO cug_backend.num (id) VALUES (5);
INSERT INTO cug_backend.num (id) VALUES (6);
INSERT INTO cug_backend.num (id) VALUES (7);
INSERT INTO cug_backend.num (id) VALUES (8);
INSERT INTO cug_backend.num (id) VALUES (9);


-- 实验室基金表
CREATE TABLE lab_finance (
    id INT(32) PRIMARY KEY auto_increment,
    item VARCHAR(256) default '' NOT NULL,
    amount double default 0 NOT NULL,
    user_id INT(32) NOT NULL,
    create_time timestamp default CURRENT_TIMESTAMP,
    update_time timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    is_delete tinyint default 0
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;