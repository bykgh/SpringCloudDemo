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

 Date: 29/05/2019 21:34:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `optimistic` int(11) NOT NULL COMMENT '乐观锁',
  `sys_role_id` int(11) NOT NULL COMMENT '角色id',
  `sys_permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表(对角色和权限表的一个包装)' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
