create database cug_backend CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE user (
    id INT(32) PRIMARY KEY auto_increment,
    nick_name VARCHAR(64) NOT NULL,
    phone_no VARCHAR(11) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
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


select DATE_FORMAT(create_time,'%Y-%m') mon,sum(num) from leetecode_record
where user_id=1
group by DATE_FORMAT(create_time,'%Y-%m')