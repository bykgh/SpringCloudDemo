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

 Date: 21/07/2019 21:48:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_srole
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_srole`;
CREATE TABLE `sys_user_srole`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sys_user_id` int(11) NOT NULL COMMENT '用户id',
  `sys_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_srole
-- ----------------------------
INSERT INTO `sys_user_srole` VALUES (1, 1, 1);
INSERT INTO `sys_user_srole` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
