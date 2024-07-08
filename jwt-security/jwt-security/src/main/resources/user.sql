 -- USER : 회원테이블

CREATE TABLE `user` (
    `NO` int NOT NULL AUTO_INCREMENT,
    `USER_ID` varchar(100) NOT NULL,
    `USER_PW` varchar(200) NOT NULL,
    `NAME` varchar(100) NOT NULL,
    `EMAIL` varchar(200)DEFAULT NULL,
    `REG_DATE` timestamp not null default current_timestamp,
    `UPD_DATE` timestamp not null default current_timestamp,
    `ENABLED` int default 1,
    PRIMARY KEY (`NO`)
) COMMENT='회원';

 -- BCryptPasswordEncoder -암호화 시
 -- 사용자

INSERT INTO user ( user_id, user_pw, name, email)
VALUES ('user','$2a$12$TrN..kcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc0hmC7BxQ92', '사용자','user@mail.com');

-- 관리자

INSERT INTO user (user_id,user_pw,name,email)
VALUES  ('user','$2a$12$TrN..kcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc0hmC7BxQ92', '관리자','admin@mail.com');