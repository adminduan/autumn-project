
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(0, '权限管理', '/menu/page/index', '', 1, 1, 1,now(), now());
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(0, '用户管理', '', '', 1, 1, 1,now(), now());
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(0, 'demo1', '/menu/page/index', '', 1, 1, 1,now(), now());
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(1, '角色管理', '/role/page/index', '', 1, 1, 1,now(), now());
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(1, '菜单管理', '/menu/page/index', '', 1, 1, 1,now(), now());
insert into tbl_sys_menu(parent_id,menu_name,menu_url,menu_pic,menu_type,menu_using,status,update_time,create_time)
values(2, '用户信息', '/user/page/index', '', 1, 1, 1,now(), now());


insert into tbl_sys_role(role_name, role_using, role_lock, status, update_time, create_time)values
('超级管理员', 1, 1, 1, now(), now()),
('管理员', 1, 0, 1, now(), now());

-- 123456
insert into tbl_sys_user(user_name, password, salt, name,pic_url, sex, user_using, user_lock, status, update_time, create_time) values
('zhangsan', 'tO3KcDXIegeLLCxdqvb8BvvCvA6s9mv60mxxXIliqDc=', 'KwnnYcDgRozx2J/qHS6XFs6eW8eYWScpgBE9k5FDlIXmXMx6WJu4XXMWJtVgu9IR6Bq5BgQlZ57qKbz6zJdr9g==', '张三', 'http://t.cn/RCzsdCq', 1, 1, 1, 1, now(), now()),
('lisi', 'z1lPoCQAsuuJ6RBxERdA8AR8+LC5udyu1sJbL8pyR7E=', 'QYGhX9x0wxHv7ImwqyITnJ5cTQpwud091eL1EeXLo95WvN4WlzLglKwGaH0L0b4xDwvyILMyMN0JEQ7f4BCUuQ', '李四', 'http://t.cn/RCzsdCq', 2, 1, 0, 1, now(), now()),
('wangwu', 'N3keFJYYWvJqCBFRh58z9gI6qJfdETl7pADtCWoAmJk=', 'V01gffuA7VNApmWT9PGp9ay06ubNNebObeQb+xdJYPKvIfTKzjaifSTyBhHA6AiBsqbskSLKswRn2Opyr46jAA==', '王五', 'http://t.cn/RCzsdCq', 0, 0, 0, 1, now(), now());




insert into tbl_sys_user_role(user_id,role_id,status,update_time,create_time) values
(1,1,1,now(),now());


insert into tbl_sys_role_menu(role_id, menu_id, status, update_time, create_time) values
(1,1,1,now(), now()),
(1,2,1,now(), now()),
(1,3,1,now(), now()),
(1,4,1,now(), now()),
(1,5,1,now(), now()),
(1,6,1,now(), now()),
(2,1,1,now(), now()),
(2,2,1,now(), now());