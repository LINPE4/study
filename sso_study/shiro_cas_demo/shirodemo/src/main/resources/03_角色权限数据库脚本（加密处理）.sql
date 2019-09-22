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
-- 2、角色数据表
CREATE TABLE role (
	rid					INT 	AUTO_INCREMENT ,
	title				VARCHAR(50),
	flag				VARCHAR(50),
	CONSTRAINT pk_rid PRIMARY KEY (rid)
) type = innodb;
-- 3、用户-角色关系表
CREATE TABLE member_role (
	mid					VARCHAR(50),
	rid 				INT ,
	CONSTRAINT fk_mid1 FOREIGN KEY(mid) REFERENCES member(mid) ,
	CONSTRAINT fk_rid1 FOREIGN KEY(rid) REFERENCES role(rid)
) type = innodb;
-- 4、权限表
CREATE TABLE action (
	actid 				INT 	AUTO_INCREMENT ,
	title 				VARCHAR(50),
	flag 				VARCHAR(50),
	CONSTRAINT pk_actid PRIMARY KEY (actid) 
) type = innodb;
-- 5、角色-权限关系表
CREATE TABLE role_action (
	rid 				INT,
	actid 				INT,
	CONSTRAINT fk_rid6 FOREIGN KEY(rid) REFERENCES role(rid) ,
	CONSTRAINT fk_actid6 FOREIGN KEY(actid) REFERENCES action(actid)
) type = innodb;
-- 五、测试数据
-- 1、增加角色信息
INSERT INTO role(title,flag) VALUES ('管理员','member') ;
INSERT INTO role(title,flag) VALUES ('部门管理','dept') ;
INSERT INTO role(title,flag) VALUES ('雇员管理','emp') ;
-- 2、增加权限信息
INSERT INTO action(title,flag) VALUES ('增加管理员','member:add') ;
INSERT INTO action(title,flag) VALUES ('管理员列表','member:list') ;
INSERT INTO action(title,flag) VALUES ('部门增加','dept:add') ;
INSERT INTO action(title,flag) VALUES ('部门列表','dept:list') ;
INSERT INTO action(title,flag) VALUES ('部门编辑','dept:edit') ;
INSERT INTO action(title,flag) VALUES ('员工入职','emp:add') ;
INSERT INTO action(title,flag) VALUES ('员工列表','emp:list') ;
INSERT INTO action(title,flag) VALUES ('员工编辑','emp:edit') ;
INSERT INTO action(title,flag) VALUES ('员工离职','emp:remove') ;
-- 3、增加角色与权限关系
INSERT INTO role_action(rid,actid) VALUES (1,1) ;
INSERT INTO role_action(rid,actid) VALUES (1,2) ;
INSERT INTO role_action(rid,actid) VALUES (2,3) ;
INSERT INTO role_action(rid,actid) VALUES (2,4) ;
INSERT INTO role_action(rid,actid) VALUES (2,5) ;
INSERT INTO role_action(rid,actid) VALUES (3,6) ;
INSERT INTO role_action(rid,actid) VALUES (3,7) ;
INSERT INTO role_action(rid,actid) VALUES (3,8) ;
INSERT INTO role_action(rid,actid) VALUES (3,9) ;
INSERT INTO member(mid,password,name,locked) VALUES ('admin','375D8EFF3A796C7C662CB0BF8A2E1182','管理员',0) ;
INSERT INTO member(mid,password,name,locked) VALUES ('mermaid','375D8EFF3A796C7C662CB0BF8A2E1182','老李',0) ;
INSERT INTO member(mid,password,name,locked) VALUES ('mldn','D7E88505FB8C22A9662219253A664F73','隔壁老王',0) ;
INSERT INTO member_role(mid,rid) VALUES ('admin',1) ;
INSERT INTO member_role(mid,rid) VALUES ('admin',2) ;
INSERT INTO member_role(mid,rid) VALUES ('mermaid',3) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',2) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',3) ;