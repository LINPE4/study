DROP DATABASE IF EXISTS shirodb ;
CREATE DATABASE shirodb CHARACTER SET UTF8 ;
USE shirodb ;
CREATE TABLE member (
	mid					VARCHAR(50),
	password			VARCHAR(32),
	name				VARCHAR(20),
	locked				INT,
	CONSTRAINT pk_mid PRIMARY KEY (mid)
) type = innodb;
INSERT INTO member(mid,password,name,locked) VALUES ('admin','hello','管理员',0) ;
INSERT INTO member(mid,password,name,locked) VALUES ('mermaid','hello','老李',0) ;
INSERT INTO member(mid,password,name,locked) VALUES ('mldn','java','隔壁老王',0) ;
