/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : xx_run

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 16/04/2020 09:52:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xx_user
-- ----------------------------
DROP TABLE IF EXISTS `xx_user`;
CREATE TABLE `xx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `memberShipLevel` int(11) NOT NULL,
  `growthValue` int(11) NOT NULL,
  `integral` int(11) NOT NULL,
  `areaCode` varchar(64) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `freezeIntegral` int(11) NOT NULL,
  `unfreezeIntegral` int(11) NOT NULL,
  `gender` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

