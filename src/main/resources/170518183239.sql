/*
MySQL Backup
Source Server Version: 5.6.21
Source Database: smile
Date: 2017/5/18 18:32:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `auth_menu`
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display` int(11) DEFAULT NULL,
  `handle` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `pname` varchar(255) DEFAULT NULL,
  `psn` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_role_menus`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_menus`;
CREATE TABLE `auth_role_menus` (
  `role_id` int(11) NOT NULL,
  `menus_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menus_id`),
  KEY `FKk8xoqq7p0aslwmaqvx6kru9k5` (`menus_id`),
  CONSTRAINT `FK9ej18ysqpyq2x0f3rvopitd49` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
  CONSTRAINT `FKk8xoqq7p0aslwmaqvx6kru9k5` FOREIGN KEY (`menus_id`) REFERENCES `auth_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `auth_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_roles`;
CREATE TABLE `auth_user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKsftdre91o487l8ujc2uqxngbm` (`roles_id`),
  CONSTRAINT `FKdqg6kvdl78t6ugm3c3ir107sl` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `FKsftdre91o487l8ujc2uqxngbm` FOREIGN KEY (`roles_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) DEFAULT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`),
  CONSTRAINT `FKd0ut7sloes191bygyf7a3pk52` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKdpvc6d7xqpqr43dfuk1s27cqh` FOREIGN KEY (`roles_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `auth_menu` VALUES ('1',NULL,NULL,'/hello',NULL,'权限1',NULL,NULL,'12',NULL,NULL,NULL), ('2',NULL,NULL,'/hello1',NULL,'权限2',NULL,NULL,'32',NULL,NULL,NULL), ('5',NULL,NULL,'/hello2',NULL,'权限5',NULL,NULL,'1213',NULL,NULL,NULL);
INSERT INTO `auth_role` VALUES ('1','12','ROLE_ADMIN',NULL), ('2','13','ROLE_USER',NULL);
INSERT INTO `auth_role_menus` VALUES ('1','1'), ('2','1'), ('2','2'), ('1','5');
INSERT INTO `auth_user` VALUES ('1',NULL,NULL,NULL,'111111',NULL,'smile'), ('2',NULL,NULL,NULL,'111111',NULL,'ranq'), ('3',NULL,NULL,NULL,'$2a$04$AJy0gPiyEPDv4xDggXY6..FkIGuWKkaWQKSzgJP5suPFmyrz.Ssi6',NULL,'admin'), ('4',NULL,NULL,NULL,NULL,NULL,'用户4'), ('8',NULL,NULL,NULL,'111111',NULL,'用户1'), ('9',NULL,NULL,NULL,'111111',NULL,'用户2'), ('10',NULL,NULL,NULL,'111111',NULL,'用户3');
INSERT INTO `auth_user_roles` VALUES ('1','1'), ('2','1'), ('1','2'), ('3','2');
INSERT INTO `sys_role` VALUES ('1','ROLE_ADMIN'), ('2','ROLE_USER');
INSERT INTO `sys_user` VALUES ('1','smile','111111'), ('2','ranq','111111'), ('3','admin','111111');
INSERT INTO `sys_user_roles` VALUES ('1','1'), ('2','2');
