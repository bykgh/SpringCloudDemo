
-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
      `optimistic` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
      `parent_id` bigint(20) DEFAULT NULL COMMENT '父权限 ID (0为顶级菜单)',
      `name` varchar(64) NOT NULL COMMENT '权限名称',
      `code` varchar(64) DEFAULT NULL COMMENT '授权标识符',
      `url` varchar(255) DEFAULT NULL COMMENT '授权路径',
      `type` int(2) NOT NULL DEFAULT '1' COMMENT '类型(1菜单，2按钮)',
      `icon` varchar(200) DEFAULT NULL COMMENT '图标',
      `remark` varchar(200) DEFAULT NULL COMMENT '备注',
      `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('11', 1, '0', '首页', 'sys:index', '/', '1', 'fa fa-dashboard', '', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('17', 1, '0', '系统管理', 'sys:manage', null, '1', 'fa fa-cogs', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('18', 1, '17', '用户管理', 'sys:user', '/user', '1', 'fa fa-users', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('19', 1, '18', '列表', 'sys:user:list', '', '2', '', '员工列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_permission` VALUES ('20', 1, '18', '新增', 'sys:user:add', '', '2', '', '新增用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('21', 1, '18', '修改', 'sys:user:edit', '', '2', '', '修改用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('22', 1, '18', '删除', 'sys:user:delete', '', '2', '', '删除用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('23', 1, '17', '角色管理', 'sys:role', '/role', '1', 'fa fa-user-secret', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('24', 1, '23', '列表', 'sys:role:list', null, '2', null, '角色列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_permission` VALUES ('25', 1, '23', '新增', 'sys:role:add', '', '2', '', '新增角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('26', 1, '23', '修改', 'sys:role:edit', '', '2', '', '修改角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('27', 1, '23', '删除', 'sys:role:delete', '', '2', '', '删除角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('28', 1, '17', '权限管理', 'sys:permission', '/permission', '1', 'fa fa-cog', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('29', 1, '28', '列表', 'sys:permission:list', null, '2', null, '权限列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_permission` VALUES ('30', 1, '28', '新增', 'sys:permission:add', '', '2', null, '新增权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('31', 1, '28', '修改', 'sys:permission:edit', '', '2', null, '修改权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
INSERT INTO `sys_permission` VALUES ('32', 1, '28', '删除', 'sys:permission:delete', '', '2', '', '删除权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
