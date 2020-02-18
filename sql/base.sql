/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : base

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 14/01/2020 15:24:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_dept
-- ----------------------------
DROP TABLE IF EXISTS `system_dept`;
CREATE TABLE `system_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `pid` bigint(20) NOT NULL COMMENT '上级部门',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_dept
-- ----------------------------
INSERT INTO `system_dept` VALUES (1, 'JX-ADMIN', 0, b'1', '2019-03-01 12:07:37');
INSERT INTO `system_dept` VALUES (2, '研发部', 7, b'1', '2019-03-25 09:15:32');
INSERT INTO `system_dept` VALUES (5, '运维部', 7, b'1', '2019-03-25 09:20:44');
INSERT INTO `system_dept` VALUES (6, '测试部', 8, b'1', '2019-03-25 09:52:18');
INSERT INTO `system_dept` VALUES (7, '华南分部', 1, b'1', '2019-03-25 11:04:50');
INSERT INTO `system_dept` VALUES (8, '华北分部', 1, b'1', '2019-03-25 11:04:53');
INSERT INTO `system_dept` VALUES (11, '人事部', 8, b'1', '2019-03-25 11:07:58');

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_dict
-- ----------------------------
INSERT INTO `system_dict` VALUES (1, 'user_status', '用户状态', '2019-10-27 20:31:36');
INSERT INTO `system_dict` VALUES (4, 'dept_status', '部门状态', '2019-10-27 20:31:36');
INSERT INTO `system_dict` VALUES (5, 'job_status', '岗位状态', '2019-10-27 20:31:36');

-- ----------------------------
-- Table structure for system_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `system_dict_detail`;
CREATE TABLE `system_dict_detail`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `dict_id` bigint(11) NULL DEFAULT NULL COMMENT '字典id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE,
  CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `system_dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_dict_detail
-- ----------------------------
INSERT INTO `system_dict_detail` VALUES (1, '激活', 'true', '1', 1, '2019-10-27 20:31:36');
INSERT INTO `system_dict_detail` VALUES (2, '禁用', 'false', '2', 1, NULL);
INSERT INTO `system_dict_detail` VALUES (3, '启用', 'true', '1', 4, NULL);
INSERT INTO `system_dict_detail` VALUES (4, '停用', 'false', '2', 4, '2019-10-27 20:31:36');
INSERT INTO `system_dict_detail` VALUES (5, '启用', 'true', '1', 5, NULL);
INSERT INTO `system_dict_detail` VALUES (6, '停用', 'false', '2', 5, '2019-10-27 20:31:36');

-- ----------------------------
-- Table structure for system_job
-- ----------------------------
DROP TABLE IF EXISTS `system_job`;
CREATE TABLE `system_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `sort` bigint(20) NOT NULL COMMENT '岗位排序',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmvhj0rogastlctflsxf1d6k3i`(`dept_id`) USING BTREE,
  CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `system_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_job
-- ----------------------------
INSERT INTO `system_job` VALUES (8, '人事专员', b'1', 3, 11, '2019-03-29 14:52:28');
INSERT INTO `system_job` VALUES (10, '产品经理', b'1', 4, 2, '2019-03-29 14:55:51');
INSERT INTO `system_job` VALUES (11, '全栈开发', b'1', 2, 2, '2019-03-31 13:39:30');
INSERT INTO `system_job` VALUES (12, '软件测试', b'1', 5, 2, '2019-03-31 13:39:43');


-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 411426 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '隐藏',
  `component_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-' COMMENT '组件名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqcf9gem97gqa5qjm4d3elcqt5`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, b'0', '系统管理', NULL, 0, 1, 'system', 'system', b'0', b'0', NULL, '2018-12-18 15:11:29', NULL, 0);
INSERT INTO `system_menu` VALUES (2, b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user', b'0', b'0', 'User', '2018-12-18 15:14:44', 'user:list', 1);
INSERT INTO `system_menu` VALUES (3, b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role', b'0', b'0', 'Role', '2018-12-18 15:16:07', 'roles:list', 1);
INSERT INTO `system_menu` VALUES (4, b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu', b'0', b'0', 'Menu', '2018-12-18 15:17:28', 'menu:list', 1);
INSERT INTO `system_menu` VALUES (5, b'0', '操作日志', 'monitor/log/index', 1, 11, 'log', 'logs', b'0', b'0', 'Log', '2018-12-18 15:18:26', NULL, 1);
INSERT INTO `system_menu` VALUES (6, b'0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept', b'0', b'0', 'Dept', '2019-03-25 09:46:00', 'dept:list', 1);
INSERT INTO `system_menu` VALUES (7, b'0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job', b'0', b'0', 'Job', '2019-03-29 13:51:18', 'job:list', 1);
INSERT INTO `system_menu` VALUES (8, b'0', '接口文档', 'tools/swagger/index', 1, 36, 'swagger', 'swagger2', b'0', b'0', 'Swagger', '2019-03-29 19:57:53', NULL, 1);
INSERT INTO `system_menu` VALUES (9, b'0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict', b'0', b'0', 'Dict', '2019-04-10 11:49:04', 'dict:list', 1);
INSERT INTO `system_menu` VALUES (10, b'0', '用户新增', '', 2, 2, '', '', b'0', b'0', '', '2019-10-29 10:59:46', 'user:add', 2);
INSERT INTO `system_menu` VALUES (11, b'0', '用户编辑', '', 2, 3, '', '', b'0', b'0', '', '2019-10-29 11:00:08', 'user:edit', 2);
INSERT INTO `system_menu` VALUES (12, b'0', '用户删除', '', 2, 4, '', '', b'0', b'0', '', '2019-10-29 11:00:23', 'user:del', 2);
INSERT INTO `system_menu` VALUES (13, b'0', '角色创建', '', 3, 2, '', '', b'0', b'0', '', '2019-10-29 12:45:34', 'roles:add', 2);
INSERT INTO `system_menu` VALUES (14, b'0', '角色修改', '', 3, 3, '', '', b'0', b'0', '', '2019-10-29 12:46:16', 'roles:edit', 2);
INSERT INTO `system_menu` VALUES (15, b'0', '角色删除', '', 3, 4, '', '', b'0', b'0', '', '2019-10-29 12:46:51', 'roles:del', 2);
INSERT INTO `system_menu` VALUES (16, b'0', '菜单新增', '', 4, 2, '', '', b'0', b'0', '', '2019-10-29 12:55:07', 'menu:add', 2);
INSERT INTO `system_menu` VALUES (17, b'0', '菜单编辑', '', 4, 3, '', '', b'0', b'0', '', '2019-10-29 12:55:40', 'menu:edit', 2);
INSERT INTO `system_menu` VALUES (18, b'0', '菜单删除', '', 4, 4, '', '', b'0', b'0', '', '2019-10-29 12:56:00', 'menu:del', 2);
INSERT INTO `system_menu` VALUES (19, b'0', '部门新增', '', 6, 2, '', '', b'0', b'0', '', '2019-10-29 12:57:09', 'dept:add', 2);
INSERT INTO `system_menu` VALUES (20, b'0', '部门编辑', '', 6, 3, '', '', b'0', b'0', '', '2019-10-29 12:57:27', 'dept:edit', 2);
INSERT INTO `system_menu` VALUES (21, b'0', '部门删除', '', 6, 4, '', '', b'0', b'0', '', '2019-10-29 12:57:41', 'dept:del', 2);
INSERT INTO `system_menu` VALUES (22, b'0', '岗位新增', '', 7, 2, '', '', b'0', b'0', '', '2019-10-29 12:58:27', 'job:add', 2);
INSERT INTO `system_menu` VALUES (23, b'0', '岗位编辑', '', 7, 3, '', '', b'0', b'0', '', '2019-10-29 12:58:45', 'job:edit', 2);
INSERT INTO `system_menu` VALUES (24, b'0', '岗位删除', '', 7, 4, '', '', b'0', b'0', '', '2019-10-29 12:59:04', 'job:del', 2);
INSERT INTO `system_menu` VALUES (25, b'0', '字典新增', '', 9, 2, '', '', b'0', b'0', '', '2019-10-29 13:00:17', 'dict:add', 2);
INSERT INTO `system_menu` VALUES (26, b'0', '字典编辑', '', 9, 3, '', '', b'0', b'0', '', '2019-10-29 13:00:42', 'dict:edit', 2);
INSERT INTO `system_menu` VALUES (27, b'0', '字典删除', '', 9, 4, '', '', b'0', b'0', '', '2019-10-29 13:00:59', 'dict:del', 2);


-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `level` int(255) NULL DEFAULT NULL COMMENT '角色级别',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, '超级管理员', '-', '全部', 1, '2018-11-23 11:04:37', 'admin');
INSERT INTO `system_role` VALUES (2, '普通用户', '-', '本级', 2, '2018-11-23 13:09:06', 'common');

-- ----------------------------
-- Table structure for system_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `system_roles_depts`;
CREATE TABLE `system_roles_depts`  (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE,
  CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `system_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色部门关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `system_roles_menus`;
CREATE TABLE `system_roles_menus`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE,
  CONSTRAINT `FKo7wsmlrrxb2osfaoavp46rv2r` FOREIGN KEY (`menu_id`) REFERENCES `system_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtag324maketmxffly3pdyh193` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_roles_menus
-- ----------------------------
INSERT INTO `system_roles_menus` VALUES (1, 1);
INSERT INTO `system_roles_menus` VALUES (2, 1);
INSERT INTO `system_roles_menus` VALUES (3, 1);
INSERT INTO `system_roles_menus` VALUES (5, 1);
INSERT INTO `system_roles_menus` VALUES (6, 1);
INSERT INTO `system_roles_menus` VALUES (7, 1);
INSERT INTO `system_roles_menus` VALUES (9, 1);
INSERT INTO `system_roles_menus` VALUES (10, 1);
INSERT INTO `system_roles_menus` VALUES (11, 1);
INSERT INTO `system_roles_menus` VALUES (14, 1);
INSERT INTO `system_roles_menus` VALUES (15, 1);
INSERT INTO `system_roles_menus` VALUES (16, 1);
INSERT INTO `system_roles_menus` VALUES (18, 1);
INSERT INTO `system_roles_menus` VALUES (19, 1);
INSERT INTO `system_roles_menus` VALUES (21, 1);
INSERT INTO `system_roles_menus` VALUES (22, 1);
INSERT INTO `system_roles_menus` VALUES (23, 1);
INSERT INTO `system_roles_menus` VALUES (24, 1);
INSERT INTO `system_roles_menus` VALUES (27, 1);
INSERT INTO `system_roles_menus` VALUES (1, 2);
INSERT INTO `system_roles_menus` VALUES (2, 2);
INSERT INTO `system_roles_menus` VALUES (3, 2);


-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar_id` bigint(20) NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enabled` bigint(20) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `job_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `last_password_reset_time` datetime NULL DEFAULT NULL COMMENT '最后修改密码的日期',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
  INDEX `FKfftoc2abhot8f2wu6cl9a5iky`(`job_id`) USING BTREE,
  INDEX `FKpq2dhypk2qgt68nauh2by22jb`(`avatar_id`) USING BTREE,
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `system_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `system_job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpq2dhypk2qgt68nauh2by22jb` FOREIGN KEY (`avatar_id`) REFERENCES `system_user_avatar` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, NULL, 'admin@jxadmin.com', 1, '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'admin', 2, '15888888888', 11, '2019-08-23 09:11:56', '2019-05-18 17:34:21', '管理员', '男');
INSERT INTO `system_user` VALUES (2, NULL, 'test@jxadmin.com', 1, '$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.', 'test', 2, '15666666666', 12, '2019-12-27 20:05:26', '2019-04-01 09:15:24', '测试', '男');

-- ----------------------------
-- Table structure for system_user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `system_user_avatar`;
CREATE TABLE `system_user_avatar`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实文件名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大小',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户头像' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `system_users_roles`;
CREATE TABLE `system_users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_users_roles
-- ----------------------------
INSERT INTO `system_users_roles` VALUES (1, 1);
INSERT INTO `system_users_roles` VALUES (2, 2);

