-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
    `optimistic` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
    `name` varchar(64) NOT NULL COMMENT '角色名称',
    `remark` varchar(200) DEFAULT NULL COMMENT '角色说明',
    `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('9', 1, '超级管理员', '拥有所有的权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_role` VALUES ('10', 1, '普通管理员', '拥有查看权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
