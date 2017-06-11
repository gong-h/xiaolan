/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : xiaolan

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-06-11 18:12:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for beacon_info
-- ----------------------------
DROP TABLE IF EXISTS `beacon_info`;
CREATE TABLE `beacon_info` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `major` double DEFAULT NULL,
  `measure_power` double DEFAULT NULL,
  `minor` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of beacon_info
-- ----------------------------
INSERT INTO `beacon_info` VALUES ('1', '1', '1', '1', '1', '1', '1');
INSERT INTO `beacon_info` VALUES ('2', '2', '2', '2', '2', '2', '2');
