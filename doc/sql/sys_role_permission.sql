-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
   `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
   `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '9', '11');
INSERT INTO `sys_role_permission` VALUES ('2', '9', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '9', '18');
INSERT INTO `sys_role_permission` VALUES ('4', '9', '19');
INSERT INTO `sys_role_permission` VALUES ('5', '9', '20');
INSERT INTO `sys_role_permission` VALUES ('6', '9', '21');
INSERT INTO `sys_role_permission` VALUES ('7', '9', '22');
INSERT INTO `sys_role_permission` VALUES ('8', '9', '23');
INSERT INTO `sys_role_permission` VALUES ('9', '9', '24');
INSERT INTO `sys_role_permission` VALUES ('10', '9', '25');
INSERT INTO `sys_role_permission` VALUES ('11', '9', '26');
INSERT INTO `sys_role_permission` VALUES ('12', '9', '27');
INSERT INTO `sys_role_permission` VALUES ('13', '9', '28');
INSERT INTO `sys_role_permission` VALUES ('14', '9', '29');
INSERT INTO `sys_role_permission` VALUES ('15', '9', '30');
INSERT INTO `sys_role_permission` VALUES ('16', '9', '31');
INSERT INTO `sys_role_permission` VALUES ('17', '9', '32');
INSERT INTO `sys_role_permission` VALUES ('18', '10', '11');
INSERT INTO `sys_role_permission` VALUES ('19', '10', '17');
INSERT INTO `sys_role_permission` VALUES ('20', '10', '18');
INSERT INTO `sys_role_permission` VALUES ('21', '10', '19');
INSERT INTO `sys_role_permission` VALUES ('22', '10', '23');
INSERT INTO `sys_role_permission` VALUES ('23', '10', '24');
INSERT INTO `sys_role_permission` VALUES ('24', '10', '28');
INSERT INTO `sys_role_permission` VALUES ('25', '10', '29');
