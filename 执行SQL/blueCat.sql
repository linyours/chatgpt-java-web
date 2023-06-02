/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : driver

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2023-06-02 08:26:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `login_user_info`;
CREATE TABLE `login_user_info` (
  `id` bigint(22) NOT NULL,
  `session_id` varchar(64) NOT NULL COMMENT 'session_key',
  `user_info` varchar(512) NOT NULL COMMENT '用户登录信息的json串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录的id';

-- ----------------------------
-- Records of login_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for `popup_info`
-- ----------------------------
DROP TABLE IF EXISTS `popup_info`;
CREATE TABLE `popup_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `popupLocation` varchar(255) DEFAULT NULL,
  `isShow` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of popup_info
-- ----------------------------
INSERT INTO `popup_info` VALUES ('11', '蓝猫AI', '蓝猫AI目前已开源，<a href=\"https://gitee.com/lixinjiuhao/chatgpt-web-java\" style=\"color: #58a6ff\">源码地址</a> ，<br>ChatGpt3.5功能会一直免费下去，不限速，不限制次数。', '2023-05-30 23:57:42', 'login', '1');
INSERT INTO `popup_info` VALUES ('40', '蓝猫AI第1版公告', '蓝猫AI目前已开源，源码地址：<a href=\"https://gitee.com/lixinjiuhao/chatgpt-web-java\" style=\"color: #58a6ff\">点击联系我们</a>', '2023-05-30 23:55:16', 'index', '1');

-- ----------------------------
-- Table structure for `prompt_model`
-- ----------------------------
DROP TABLE IF EXISTS `prompt_model`;
CREATE TABLE `prompt_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL COMMENT '分类',
  `title` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '标题',
  `introduce` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '简介',
  `demo` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '示例',
  `content` varchar(1000) CHARACTER SET utf8mb4 NOT NULL COMMENT '提示内容',
  `state` tinyint(4) NOT NULL COMMENT '0，无效；1，有效',
  `sort` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prompt_model
-- ----------------------------
INSERT INTO `prompt_model` VALUES ('1', 'Java', 'Java代码优化', '只需要将你想要的优化的代码复制到输入框，AI将会自动帮你优化它', '请输入/粘贴你想要优化的代码', '假如你是高级Java开发工程师，有着丰富的代码编写能力，那么请使用JDK8新语法将下面的代码进行优化并且将优化后的代码能够被markdowm渲染的代码出来：', '1', '0', '2023-05-21 22:42:46', '2023-05-21 22:56:34');
INSERT INTO `prompt_model` VALUES ('7', 'Java', '使用JDK8新特性优化代码', '只需要将你想要的优化的代码复制到输入框，AI将会自动使用JDK8新语法帮你优化它', '请输入/粘贴你想要优化的代码', '假如你是高级Java开发工程师，非常擅长使用JDK8新语法，那么请使用JDK8新语法将下面的代码进行优化并且将优化后的代码能够被markdowm渲染的代码出来：', '1', '0', '2023-05-21 22:47:14', '2023-05-21 22:55:46');
INSERT INTO `prompt_model` VALUES ('8', 'Java', 'Java面试打分工具', '只需要将你的问题和回答复制到输入框，AI将会自动根据你的问题和回答进行分析和打分', '问题：在synchronized代码块中调用wait方法进入等待的线程和因为拿不到锁而等待线程是否同一种状态？blocking？waiting？\r\n回答：1. 因为拿不到锁会处于 blocking状态；\r\n2. 调用wait方法，会处于 waiting状态，不会释放锁。\r\n', '假如你是Java面试官，有着非常丰富的面试经验，那么请基于下面的问题和回答进行系统的分析，给出相应的分数，并且以markdown的格式分点输出回答中不足的地方并且加以补充：', '1', '0', '2023-05-21 22:58:02', '2023-05-21 23:15:45');

-- ----------------------------
-- Table structure for `user_access_rule`
-- ----------------------------
DROP TABLE IF EXISTS `user_access_rule`;
CREATE TABLE `user_access_rule` (
  `id` bigint(22) NOT NULL COMMENT '用户id',
  `user_id` bigint(22) NOT NULL,
  `service_type` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '模型标识',
  `use_number` tinyint(4) NOT NULL DEFAULT '10' COMMENT '使用次数, 如果 = -2 代表不限制次数',
  `start_effective_time` datetime NOT NULL COMMENT '开始生效时间',
  `end_effective_time` datetime NOT NULL COMMENT '有效结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户访问规则';

-- ----------------------------
-- Records of user_access_rule
-- ----------------------------
INSERT INTO `user_access_rule` VALUES ('1664427904947597313', '1664427904784019457', 'chat_gpt_model3.5', '30', '2023-06-02 08:25:11', '2023-06-03 08:25:11', '2023-06-02 08:25:11', null, null);

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(22) NOT NULL,
  `username` varchar(64) DEFAULT '小可爱！' COMMENT '用户昵称',
  `open_id` varchar(128) DEFAULT NULL COMMENT '微信授权的openId',
  `avatar` varchar(128) DEFAULT '/src/assets/avatar.jpg' COMMENT '用户头像',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话信息',
  `account` varchar(32) NOT NULL COMMENT '用户账号',
  `user_level` varchar(16) DEFAULT 'common_user' COMMENT '用户等级',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '账号状态，0正常，1禁用',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_index` (`account`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1664427904784019457', '小可爱！', null, '/src/assets/avatar.jpg', null, '18230675983', 'common_user', '0', 'acaaf694427dcb42423626d4aa74c4ae', '2023-06-02 08:25:11', null);
