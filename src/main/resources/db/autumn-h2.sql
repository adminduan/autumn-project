CREATE TABLE if not exists `tbl_sys_user` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_name` varchar(100) NOT NULL comment '用户名',
      `password` varchar(100) NOT NULL comment '密码',
      `salt` varchar(100) NOT NULL comment '盐',
      `name` varchar(100) NOT NULL comment '显示名',
      `pic_url` varchar(100) NOT NULL comment '头像地址',
      `sex` tinyint(4) not null comment '性别 0 未知 1 男 2 女',
      `user_using` bit(1) NOT NULL  default 1 comment '0 禁用 1启用',
      `user_lock` bit(1) NOT NULL  default 1 comment '0 锁定 1 无锁',
      `status` bit(1) NOT NULL  default 1 comment '0 无效 1有效',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
) comment='系统用户信息';



CREATE TABLE if not exists `tbl_sys_role` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `role_name` varchar(100) NOT NULL comment '角色名',
      `role_using` bit(1) NOT NULL  default 1 comment '0 禁用 1启用',
      `role_lock` bit(1) NOT NULL  default 1 comment '0 锁定 1 无锁',
      `status` bit(1) NOT NULL  default 1 comment '0 无效 1有效',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
) comment='系统角色信息';


CREATE TABLE if not exists `tbl_sys_user_role` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `user_id` int(11) NOT NULL comment '用户id',
      `role_id` int(11) NOT NULL comment '角色id',
      `status` bit(1) NOT NULL  default 1 comment '0 无效 1有效',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
) comment='系统用户角色关联信息';


CREATE TABLE if not exists `tbl_sys_menu` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `parent_id` int(11) NOT NULL comment '父id',
      `menu_name` varchar(100) NOT NULL comment '菜单名',
      `menu_url` varchar(100) NOT NULL comment '菜单地址',
      `menu_pic` varchar(100) NOT NULL comment '菜单图标',
      `menu_type` tinyint(4) NOT NULL comment '菜单类型 1 父级菜单 2子集菜单 3 请求地址',
      `menu_using` bit(1) NOT NULL  default 1 comment '0 禁用 1启用',
      `status` bit(1) NOT NULL  default 1 comment '0 无效 1有效',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
) comment='系统菜单表';


CREATE TABLE if not exists `tbl_sys_role_menu` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `role_id` int(11) NOT NULL comment '角色id',
      `menu_id` int(11) NOT NULL comment '菜单id',
      `status` bit(1) NOT NULL  default 1 comment '0 无效 1有效',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
)  comment='系统角色菜单表';













