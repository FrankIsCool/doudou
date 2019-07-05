/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : doudou

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/07/2019 16:22:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '作者id',
  `labels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签，json字符串',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `browse_num` int(11) NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `like_num` int(11) NOT NULL DEFAULT 0 COMMENT '点赞次数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 0, NULL, '11', '11', 12, 2, '2019-06-05 15:41:23');

-- ----------------------------
-- Table structure for balance
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `balance` decimal(8, 2) NOT NULL COMMENT '可用余额',
  `freeze` decimal(8, 2) NOT NULL COMMENT '冻结金额',
  `total_balance` decimal(8, 2) NOT NULL COMMENT '总余额',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '余额加密串',
  `state` tinyint(3) NOT NULL COMMENT '账户状态：1正常  2异常  3冻结',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户账户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balance
-- ----------------------------
INSERT INTO `balance` VALUES (2, 0, 120.90, 0.00, 120.90, '3f394a0859bd3a9f44c2e53a3b57a3ce', 1, '2019-05-23 16:16:15');
INSERT INTO `balance` VALUES (3, 1, 0.00, 0.00, 0.00, '19180784208d327f8554f96800447800', 1, '2019-07-01 10:03:12');

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `state` tinyint(4) NOT NULL COMMENT '1:正常  2：删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_label`(`label`) USING BTREE COMMENT '标签名称，防重复'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, '测试', 1, NULL, '2019-06-10 15:27:14');
INSERT INTO `label` VALUES (2, '生产', 1, NULL, '2019-06-28 15:17:57');
INSERT INTO `label` VALUES (3, '开发', 2, NULL, '2019-06-28 15:18:03');
INSERT INTO `label` VALUES (6, '生活', 1, NULL, '2019-06-28 16:02:32');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '创建人id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
  `state` tinyint(3) NOT NULL DEFAULT 1 COMMENT '店铺状态 1：申请 2：正常  :3：异常  4：冻结  5：审核不通过',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
