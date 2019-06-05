/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : oms

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 05/06/2019 15:18:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_srole
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_srole`;
CREATE TABLE `sys_user_srole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sys_user_id` int(11) NOT NULL COMMENT '用户id',
  `sys_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

SET FOREIGN_KEY_CHECKS = 1;
