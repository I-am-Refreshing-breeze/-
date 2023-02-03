/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 127.0.0.1:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 19/06/2022 19:53:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'wjq', '4QrcOUm6Wau+VuBX8g+IPg==');

-- ----------------------------
-- Table structure for checkin
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checkin
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `start` timestamp NOT NULL,
  `end` timestamp NOT NULL,
  `in_time` timestamp NULL DEFAULT NULL,
  `out` timestamp NULL DEFAULT NULL,
  `deposit` decimal(10, 2) NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `room_id` int NOT NULL,
  `user_id` int NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '2022-06-27 00:00:00', '2022-06-29 00:00:00', '2022-06-19 19:36:08', NULL, 1111.00, 100.00, 2, 2, 2);
INSERT INTO `order` VALUES (2, '2022-06-19 00:00:00', '2022-06-20 00:00:00', '2022-06-19 19:42:36', '2022-06-19 19:42:46', 1000.00, 100.00, 1, 3, 3);
INSERT INTO `order` VALUES (3, '2022-06-24 00:00:00', '2022-06-25 00:00:00', NULL, NULL, NULL, NULL, 1, 3, 1);
INSERT INTO `order` VALUES (4, '2022-06-29 00:00:00', '2022-06-30 00:00:00', NULL, NULL, NULL, NULL, 1, 6, 1);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int NOT NULL,
  `dscp` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `pic` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '301', 1, '有窗', 288.00, NULL, 0);
INSERT INTO `room` VALUES (2, '101', 0, '无窗', 278.00, NULL, 0);
INSERT INTO `room` VALUES (3, '303', 1, '无窗', 288.00, 'e39405c2-4a3f-4558-a6ca-94833e5dd4b2.png', 0);
INSERT INTO `room` VALUES (4, '304', 2, '有窗', 288.00, 'dcf4bfd9-6a02-413f-83b3-cbe87d739919.png', 0);
INSERT INTO `room` VALUES (5, '305', 1, '无窗', 288.00, 'd708decd-69a2-487c-88cb-ff110284aab8.png', 1);
INSERT INTO `room` VALUES (6, '306', 0, '无窗', 299.00, '18794fc2-55dc-4a7e-8479-92a571a08202.png', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idcard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'tom1', '123456789012345600', '12346578900', '4QrcOUm6Wau+VuBX8g+IPg==', 0);
INSERT INTO `user` VALUES (3, 'tom2', '123456789012345601', '12346578901', '4QrcOUm6Wau+VuBX8g+IPg==', 0);
INSERT INTO `user` VALUES (4, 'tom3', '123456789012345602', '12346578902', 'SWA8bBEIUwLix9BCI4Q/JA==', 1);
INSERT INTO `user` VALUES (5, 'tom4', '123456789012345603', '12346578904', 'DyiFEYfnj41Vp/7sXZpgrQ==', 0);
INSERT INTO `user` VALUES (6, 'tom5', '123456789012345605', '12346578905', '4QrcOUm6Wau+VuBX8g+IPg==', 0);

SET FOREIGN_KEY_CHECKS = 1;
