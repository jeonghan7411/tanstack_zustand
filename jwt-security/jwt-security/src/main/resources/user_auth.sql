 -- user_auth : 권한 테이블
 CREATE TABLE `user_auth` (
        auth_no int NOT NULL AUTO_INCREMENT             -- 권한번호
    ,   user_id varchar(100) not null                   -- 아이디
    ,   auth varchar(100) not null                      -- 권한 (USER,ADMIN, ...)
    ,   PRIMARY KEY(auth_no)
 );

-- 기본데이터
-- 사용자
-- * 권한 : USER

INSERT INTO user_auth( user_id, auth)
values ( 'user', 'ROLE_USER');

-- 관리자
-- * 권한 : USER, ADMIN

INSERT INTO user_auth(user_id,auth)
VALUES ('admin', 'ROLE_USER');

 INSERT INTO user_auth(user_id,auth)
 VALUES ('admin', 'ROLE_ADMIN');