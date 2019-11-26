/*
Navicat MySQL Data Transfer

Source Server         : 本机MySql
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : double_live

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-11-26 23:50:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `global_id` varchar(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`global_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
