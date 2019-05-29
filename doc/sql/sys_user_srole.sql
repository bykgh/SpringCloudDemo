/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : oms

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/05/2019 21:35:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_srole
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_srole`;
CREATE TABLE `sys_user_srole`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `optimistic` int(11) NOT NULL COMMENT '乐观锁',
  `sys_user_id` int(11) NOT NULL COMMENT '用户id',
  `sys_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
