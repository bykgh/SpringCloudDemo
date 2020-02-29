-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
        `optimistic` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
        `username` varchar(50) NOT NULL COMMENT '用户名',
        `password` varchar(64) NOT NULL COMMENT '密码，加密存储, admin/1234',
        `is_account_non_expired` int(2) DEFAULT '1' COMMENT '帐户是否过期(1 未过期，0已过期)',
        `is_account_non_locked` int(2) DEFAULT '1' COMMENT '帐户是否被锁定(1 未过期，0已过期)',
        `is_credentials_non_expired` int(2) DEFAULT '1' COMMENT '密码是否过期(1 未过期，0已过期)',
        `is_enabled` int(2) DEFAULT '1' COMMENT '帐户是否可用(1 可用，0 删除用户)',
        `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
        `mobile` varchar(20) DEFAULT NULL COMMENT '注册手机号',
        `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
        `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`),
        UNIQUE KEY `username` (`username`) USING BTREE,
        UNIQUE KEY `mobile` (`mobile`) USING BTREE,
        UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('9', 1, 'admin', '$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm', '1', '1', '1', '1', '梦学谷', '16888888888', 'yikai888@163.com', '2023-08-08 11:11:11', '2019-12-16 10:25:53');
INSERT INTO `sys_user` VALUES ('10', 1, 'test', '$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm', '1', '1', '1', '1', '测试', '16888886666', 'test11@qq.com', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
