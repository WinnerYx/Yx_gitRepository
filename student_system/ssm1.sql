/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : ssm1

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 22/02/2021 16:40:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_announcement
-- ----------------------------
DROP TABLE IF EXISTS `tb_announcement`;
CREATE TABLE `tb_announcement`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publishtime` datetime(5) NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_announcement
-- ----------------------------
INSERT INTO `tb_announcement` VALUES (14, '放假了1112334', '已审批', '通过', '2021-02-14 00:00:00.00000', 8, '放假通知');
INSERT INTO `tb_announcement` VALUES (15, '今天开会', '已审批', '通过', '2020-02-18 00:00:00.00000', 8, '开会通知');
INSERT INTO `tb_announcement` VALUES (16, '今天开会', '已审批', '通过', '2020-02-18 00:00:00.00000', 8, '开会通知');
INSERT INTO `tb_announcement` VALUES (17, '今天开会', '已审批', '通过', '2020-02-18 00:00:00.00000', 8, '开会通知');
INSERT INTO `tb_announcement` VALUES (18, '放假了1112334', '已审批', '通过', '2021-02-14 00:00:00.00000', 8, '放假通知');
INSERT INTO `tb_announcement` VALUES (19, '放假了123456', '已审批', '通过', '2021-02-21 00:00:00.00000', 8, '放假通知');

-- ----------------------------
-- Table structure for tb_clazz
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject_id` int(0) NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_1`(`subject_id`) USING BTREE,
  INDEX `FK_Reference_2`(`teacher_id`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`subject_id`) REFERENCES `tb_subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES (15, '编程', 17, '', 4);
INSERT INTO `tb_clazz` VALUES (16, '软件工程', 17, '', 5);
INSERT INTO `tb_clazz` VALUES (17, '网络通信', 17, '', 6);
INSERT INTO `tb_clazz` VALUES (18, '英语', 18, '', 8);
INSERT INTO `tb_clazz` VALUES (19, '英语2', 18, '', 8);
INSERT INTO `tb_clazz` VALUES (20, '电子商务', 19, '', 8);

-- ----------------------------
-- Table structure for tb_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_image`;
CREATE TABLE `tb_image`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_image
-- ----------------------------
INSERT INTO `tb_image` VALUES (4, 'http://localhost:8080/images/f8327d345872427bbb7950ec3bfa8451微信图片_20210114175607.jpg', 24);

-- ----------------------------
-- Table structure for tb_job
-- ----------------------------
DROP TABLE IF EXISTS `tb_job`;
CREATE TABLE `tb_job`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `workplace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `worktime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salary` double(10, 2) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `job_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `result1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_job
-- ----------------------------
INSERT INTO `tb_job` VALUES (8, '宿舍', '南', '每天12点', 160.00, 24, '暂未审批', '暂未通过');

-- ----------------------------
-- Table structure for tb_power
-- ----------------------------
DROP TABLE IF EXISTS `tb_power`;
CREATE TABLE `tb_power`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `value` int(0) NULL DEFAULT 0,
  `teacher_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_power
-- ----------------------------
INSERT INTO `tb_power` VALUES (1, 0, 8);
INSERT INTO `tb_power` VALUES (2, 0, 5);
INSERT INTO `tb_power` VALUES (3, 0, 6);
INSERT INTO `tb_power` VALUES (4, 0, 9);
INSERT INTO `tb_power` VALUES (5, 0, 11);
INSERT INTO `tb_power` VALUES (6, 0, 12);

-- ----------------------------
-- Table structure for tb_request
-- ----------------------------
DROP TABLE IF EXISTS `tb_request`;
CREATE TABLE `tb_request`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `days` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_count` int(0) NULL DEFAULT NULL,
  `user_count` int(0) NULL DEFAULT NULL,
  `result1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_request
-- ----------------------------
INSERT INTO `tb_request` VALUES (13, '生病请假', '1', 0, 0, '通过', 24, '已审批');
INSERT INTO `tb_request` VALUES (14, '生病请假', '133', 0, 0, '通过', 24, '已审批');

-- ----------------------------
-- Table structure for tb_section
-- ----------------------------
DROP TABLE IF EXISTS `tb_section`;
CREATE TABLE `tb_section`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `year` int(0) NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clazz_id` int(0) NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `course_id` int(0) NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_2`(`clazz_id`) USING BTREE,
  INDEX `FK_Reference_3`(`teacher_id`) USING BTREE,
  INDEX `FK_Reference_4`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_section
-- ----------------------------
INSERT INTO `tb_section` VALUES (11, 2020, '春季', 15, 4, 4, '');
INSERT INTO `tb_section` VALUES (12, 2020, '春季', 15, 5, 5, '');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `stu_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stu_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `card_no` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` datetime(5) NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `join_date` datetime(0) NULL DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clazz_id` int(0) NULL DEFAULT NULL,
  `subject_id` int(0) NOT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `flag_poor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_5`(`clazz_id`) USING BTREE,
  INDEX `FK_Reference_6`(`subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (24, '0020', 'aa', '93a9ded8a9ab7cb69dba0c0575665204', '123123123123123123', '男', NULL, '18533333333', '', '', '', '2020-07-06 00:00:00', '正常', 17, 17, 8, '是');
INSERT INTO `tb_student` VALUES (50, '1122222111', 'qqqqqqq', '93a9ded8a9ab7cb69dba0c0575665204', '123123123123123123', '男', NULL, '18533333333', '', '', '', '2021-01-13 00:00:00', '正常', 15, 17, 8, '是');

-- ----------------------------
-- Table structure for tb_studentaction
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentaction`;
CREATE TABLE `tb_studentaction`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `days` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_count` int(0) NULL DEFAULT NULL,
  `user_count` int(0) NULL DEFAULT NULL,
  `result1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_studentaction
-- ----------------------------
INSERT INTO `tb_studentaction` VALUES (12, 'aaaa', '1', 0, 0, '暂未通过', 24, '暂未审批');

-- ----------------------------
-- Table structure for tb_studentcount
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentcount`;
CREATE TABLE `tb_studentcount`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `request_count` int(0) NULL DEFAULT 0,
  `studentAction_count` int(0) NULL DEFAULT 0,
  `studyStatus_count` int(0) NULL DEFAULT 0,
  `student_id` int(0) NULL DEFAULT NULL,
  `job_count` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_studentcount
-- ----------------------------
INSERT INTO `tb_studentcount` VALUES (7, 4, 0, 0, 24, 0);

-- ----------------------------
-- Table structure for tb_studystatus
-- ----------------------------
DROP TABLE IF EXISTS `tb_studystatus`;
CREATE TABLE `tb_studystatus`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `days` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_count` int(0) NULL DEFAULT NULL,
  `user_count` int(0) NULL DEFAULT NULL,
  `result1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_studystatus
-- ----------------------------
INSERT INTO `tb_studystatus` VALUES (5, '生病请假', '133', 0, 0, '暂未通过', 24, '暂未审批', '休学');

-- ----------------------------
-- Table structure for tb_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------
INSERT INTO `tb_subject` VALUES (17, '计算机应用', '电子信息工程系', ' 1');
INSERT INTO `tb_subject` VALUES (18, '英语', '外语系', '');
INSERT INTO `tb_subject` VALUES (19, '电子商务', '工商管理系', '');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `joinDate` datetime(0) NULL DEFAULT NULL,
  `power` int(0) NULL DEFAULT 0,
  `needPower` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (5, 'shaoming', 'yxx', 'e3b8e995c8a055b8713710e179a82c04', NULL, NULL, 1, NULL);
INSERT INTO `tb_teacher` VALUES (6, 'hanmeimei', NULL, '93a9ded8a9ab7cb69dba0c0575665204', NULL, NULL, 1, NULL);
INSERT INTO `tb_teacher` VALUES (8, 'aaa', NULL, '93a9ded8a9ab7cb69dba0c0575665204', NULL, NULL, 1, NULL);
INSERT INTO `tb_teacher` VALUES (9, 'qqqw', 'yx', '93a9ded8a9ab7cb69dba0c0575665204', '', '2021-02-20 00:00:00', 0, NULL);
INSERT INTO `tb_teacher` VALUES (12, 'qqq', 'yx', '93a9ded8a9ab7cb69dba0c0575665204', '', '2021-02-22 00:00:00', 0, NULL);

-- ----------------------------
-- Table structure for tb_teachercount
-- ----------------------------
DROP TABLE IF EXISTS `tb_teachercount`;
CREATE TABLE `tb_teachercount`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `request_count` int(0) NULL DEFAULT 0,
  `studentAction_count` int(0) NULL DEFAULT 0,
  `studyStatus_count` int(0) NULL DEFAULT 0,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `job_count` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teachercount
-- ----------------------------
INSERT INTO `tb_teachercount` VALUES (5, 1, 1, 0, 8, 0);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '93a9ded8a9ab7cb69dba0c0575665204', '管理员', '备注信息');
INSERT INTO `tb_user` VALUES (2, 'ww', '123456', 'ww', 'ww');

-- ----------------------------
-- Table structure for tb_usercount
-- ----------------------------
DROP TABLE IF EXISTS `tb_usercount`;
CREATE TABLE `tb_usercount`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `request_count` int(0) NULL DEFAULT 0,
  `studentAction_count` int(0) NULL DEFAULT 0,
  `studyStatus_count` int(0) NULL DEFAULT 0,
  `job_count` int(0) NULL DEFAULT 0,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_usercount
-- ----------------------------
INSERT INTO `tb_usercount` VALUES (2, 1, 0, 1, 1, 1);

-- ----------------------------
-- Table structure for tb_workhistory
-- ----------------------------
DROP TABLE IF EXISTS `tb_workhistory`;
CREATE TABLE `tb_workhistory`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `workhistory` date NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL,
  `poorStudentCount` int(0) NULL DEFAULT NULL,
  `workStudentCount` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_workhistory
-- ----------------------------

-- ----------------------------
-- Table structure for tb_workstatus
-- ----------------------------
DROP TABLE IF EXISTS `tb_workstatus`;
CREATE TABLE `tb_workstatus`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `worktime` datetime(5) NULL DEFAULT NULL,
  `salary` double(10, 2) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `student_id_2`(`student_id`, `id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_workstatus
-- ----------------------------
INSERT INTO `tb_workstatus` VALUES (2, 'qw', '2021-01-02 00:00:00.00000', 170.00, 24);
INSERT INTO `tb_workstatus` VALUES (3, 't', '2021-01-16 14:47:31.00000', 150.00, 50);
INSERT INTO `tb_workstatus` VALUES (4, 'qq', '2021-01-16 14:47:52.00000', 180.00, 3);

SET FOREIGN_KEY_CHECKS = 1;
