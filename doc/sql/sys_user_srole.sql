-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
     `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
     `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '9', '9');
INSERT INTO `sys_user_role` VALUES ('2', '10', '10');