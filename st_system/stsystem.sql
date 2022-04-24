/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : stsystem

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 11/06/2020 14:09:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `ACCOUNT_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ACCOUNT_USERID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ACCOUNT_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ACCOUNT_MONEY` double(255, 0) NULL DEFAULT 0,
  PRIMARY KEY (`ACCOUNT_KEY`) USING BTREE,
  INDEX `ACCOUNT_USERID`(`ACCOUNT_USERID`) USING BTREE,
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`ACCOUNT_USERID`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('3752498198734347', '201600001', 'e3ceb5881a0a1fdaad01296d7554868d', 2320);
INSERT INTO `account` VALUES ('8100661655339592', '201600004', '96e79218965eb72c92a549dd5a330112', 0);
INSERT INTO `account` VALUES ('9470060822634926', '201800012', '96e79218965eb72c92a549dd5a330112', 0);

-- ----------------------------
-- Table structure for active
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active`  (
  `AC_kEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AC_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AC_CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `AC_MAXPEOPLENUM` int(0) NULL DEFAULT NULL,
  `AC_PLACE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AC_TIME` time(0) NULL DEFAULT NULL,
  `AC_DATE` date NULL DEFAULT NULL,
  `AC_OTHERINF` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AC_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `AC_CREATEST` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AC_CREATEPERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AC_kEY`) USING BTREE,
  INDEX `AC_CREATEST`(`AC_CREATEST`) USING BTREE,
  CONSTRAINT `active_ibfk_1` FOREIGN KEY (`AC_CREATEST`) REFERENCES `stinf` (`ST_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('AC05143363', '摄影展', '展示照片', 100, '土木楼大厅', '00:00:00', '2020-05-31', '投稿邮箱', '2', '1', 'ST00000013', '201600001');
INSERT INTO `active` VALUES ('AC18459037', '摄影教程', '摄影教程', 30, '土木教学馆C302', '14:00:00', '2020-05-31', '请提前报名', '2', '1', 'ST00000013', '201600001');
INSERT INTO `active` VALUES ('AC46572036', '土豪们来秀你们的相机', '器材交流会，分享器材使用心得', 20, '网络信息中心活动室', '13:00:00', '2020-05-11', '请携带器材参加', '2', '1', 'ST00000013', '201600001');
INSERT INTO `active` VALUES ('AC82709783', '新手剪辑，如何速成', '是否羡慕up主们的高超剪辑手法，来这里带你入门剪辑，只需要五分钟', 30, '土木教学馆', '15:00:00', '2020-05-31', '请携带笔记本电脑参加', '3', '1', 'ST00000001', '201600004');
INSERT INTO `active` VALUES ('AC_00000001', '《拯救大兵瑞恩》电影赏析', '好的电影能够改变你的认知，对于好的电影，你会从什么角度来赏析他嫩,我们将从不同的角度带你来赏析一部电影，本周将举行新的电影放映机会和其他的活动', 30, '土木教学楼303', '17:00:00', '2020-05-13', '参加活动请提前预约', '2', '1', 'ST00000001', '201600004');
INSERT INTO `active` VALUES ('AC_00000002', '如何正确的健身，欢迎来', '健身的正确不走', 30, '文体中心健身房', '17:00:00', '2020-05-22', '参与活动提前预约', '2', '1', 'ST00000014', '201600029');

-- ----------------------------
-- Table structure for activeperson
-- ----------------------------
DROP TABLE IF EXISTS `activeperson`;
CREATE TABLE `activeperson`  (
  `AP_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AP_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AP_ACTIVEKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AP_TIME` datetime(0) NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`AP_KEY`) USING BTREE,
  INDEX `AP_PERSON`(`AP_PERSON`) USING BTREE,
  INDEX `activeperson_ibfk_2`(`AP_ACTIVEKEY`) USING BTREE,
  CONSTRAINT `activeperson_ibfk_1` FOREIGN KEY (`AP_PERSON`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activeperson_ibfk_2` FOREIGN KEY (`AP_ACTIVEKEY`) REFERENCES `active` (`AC_kEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activeperson
-- ----------------------------
INSERT INTO `activeperson` VALUES ('015569ef271145f78656459d30429f40', '201600001', 'AC05143363', '2020-06-03 00:03:40', '2');
INSERT INTO `activeperson` VALUES ('15b624b51d1a4ef7aea57f5c32005153', '201600001', 'AC_00000001', '2020-05-19 16:06:12', '2');
INSERT INTO `activeperson` VALUES ('2ca73c91af754ba3a96e35593032486f', '201600001', 'AC18459037', '2020-05-17 13:15:32', '2');
INSERT INTO `activeperson` VALUES ('47a5953b61b6426e8c022603e5929d47', '201700004', 'AC18459037', '2020-05-17 23:26:54', '1');
INSERT INTO `activeperson` VALUES ('626f101e0daf4d74a779af81edc4d0d6', '201600001', 'AC05143363', '2020-06-03 12:25:11', '2');
INSERT INTO `activeperson` VALUES ('6c79f14744f64d4aac323fece5cbc93e', '201600001', 'AC46572036', '2020-06-02 23:56:57', '2');
INSERT INTO `activeperson` VALUES ('6f2a4e44106e419f88a4505452b95f7f', '201600001', 'AC_00000002', '2020-05-17 13:15:36', '2');
INSERT INTO `activeperson` VALUES ('94d5912c652d476e88c9551da51c994a', '201600001', 'AC_00000002', '2020-05-18 17:51:06', '2');
INSERT INTO `activeperson` VALUES ('a6199878e12441bab3b405ddc6258b35', '201600001', 'AC05143363', '2020-05-19 15:49:59', '2');
INSERT INTO `activeperson` VALUES ('b6526a0faed24ae79281499f98c4c11e', '201600004', 'AC_00000002', '2020-05-12 00:10:00', '1');
INSERT INTO `activeperson` VALUES ('c8d14bc6aa474f7f85d57e50ad23ebfc', '201600001', 'AC05143363', '2020-06-03 00:02:08', '2');
INSERT INTO `activeperson` VALUES ('d6168bba340545f582296332e65a6600', '201600001', 'AC18459037', '2020-06-08 00:21:40', '1');
INSERT INTO `activeperson` VALUES ('e270d0d37d8c4ef9ace4b4be9f2b4c44', '201600004', 'AC_00000001', '2020-05-12 00:07:46', '1');
INSERT INTO `activeperson` VALUES ('e437ccf19283433eb0147375c559a27e', '201600004', 'AC18459037', '2020-05-12 00:10:06', '1');
INSERT INTO `activeperson` VALUES ('fb9d67d9937447409ca454dc0fd34f48', '201800002', 'AC_00000001', '2020-05-12 00:08:05', '1');
INSERT INTO `activeperson` VALUES ('fe8236a4c40344018587680d6997cb53', '201600001', 'AC_00000001', '2020-05-18 17:48:37', '2');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `AT_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AT_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AT_MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AT_CREATETIME` datetime(0) NOT NULL,
  `AT_CREATEPERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AT_LOOKPERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AT_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`AT_KEY`) USING BTREE,
  INDEX `AT_CREATEPERSON`(`AT_CREATEPERSON`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('AT00000002', '管理员公告', '各位用户大家好，记得修改密码,不然密码让那个王八犊子盗走了我可是不管熬，到时候可别来找我，具体修改密码的方法如下，第一么么哒，第二么么哒', '2020-05-04 11:45:01', 'admin', 'all', '1', '1');
INSERT INTO `announcement` VALUES ('AT00000003', 'memeda', 'memedaadaaadaa', '2020-05-04 22:38:52', 'admin', 'all', '2', '1');
INSERT INTO `announcement` VALUES ('AT03560063', '测试', '测试', '2020-05-17 22:49:13', '201600004', 'ST00000001', '1', '1');
INSERT INTO `announcement` VALUES ('AT05392451', '测试', '测试', '2020-06-03 12:55:16', 'admin', 'all', '1', '1');
INSERT INTO `announcement` VALUES ('AT48693126', '请大家及时支付社团资金', '支付方式 财务系统 账单', '2020-05-11 02:29:59', '201600001', 'ST00000013', '2', '1');
INSERT INTO `announcement` VALUES ('AT86966476', '333', '333', '2020-05-19 16:13:57', '201600001', 'ST00000013', '2', '1');
INSERT INTO `announcement` VALUES ('AT90347571', '111', '1111', '2020-06-08 00:19:00', '201600001', 'ST00000013', '1', '1');
INSERT INTO `announcement` VALUES ('AT97247431', '管理员测试', '管理元测试内容', '2020-05-19 12:12:50', 'admin', 'all', '1', '1');

-- ----------------------------
-- Table structure for collegeinf
-- ----------------------------
DROP TABLE IF EXISTS `collegeinf`;
CREATE TABLE `collegeinf`  (
  `COLLEGE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COLLEGE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`COLLEGE_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collegeinf
-- ----------------------------
INSERT INTO `collegeinf` VALUES ('CE1', '电气与计算机学院', '1');
INSERT INTO `collegeinf` VALUES ('CE2', '材料科学与工程学院', '1');
INSERT INTO `collegeinf` VALUES ('CE3', '交通科学与工程学院', '1');
INSERT INTO `collegeinf` VALUES ('CE4', '艺术与设计学院', '1');
INSERT INTO `collegeinf` VALUES ('CE5', '土木工程学院', '1');
INSERT INTO `collegeinf` VALUES ('CE6', '市政与环境工程学院', '1');
INSERT INTO `collegeinf` VALUES ('CE7', '经济与管理学院', '1');
INSERT INTO `collegeinf` VALUES ('CE8', '书法学院', '1');

-- ----------------------------
-- Table structure for joinstapplication
-- ----------------------------
DROP TABLE IF EXISTS `joinstapplication`;
CREATE TABLE `joinstapplication`  (
  `APPLICATION_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `APPLICATION_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `APPLICATION_STKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `APPLICATION_INTRODUCTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `APPLICATION_MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `APPLICATION_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  `APPLICATION_TIME` date NOT NULL,
  `APPLICATION_FLAG` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`APPLICATION_KEY`) USING BTREE,
  INDEX `APPLICATION_PERSON`(`APPLICATION_PERSON`) USING BTREE,
  INDEX `APPLICATION_STKEY`(`APPLICATION_STKEY`) USING BTREE,
  CONSTRAINT `joinstapplication_ibfk_1` FOREIGN KEY (`APPLICATION_PERSON`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `joinstapplication_ibfk_2` FOREIGN KEY (`APPLICATION_STKEY`) REFERENCES `stinf` (`ST_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of joinstapplication
-- ----------------------------
INSERT INTO `joinstapplication` VALUES ('1508a52bee4241d4b710f571edaed577', '201600001', 'ST00000001', '测试', '测试', '2', '2020-05-18', NULL);
INSERT INTO `joinstapplication` VALUES ('2131cd5217c64d619aacac81ff3c2ef5', '201600008', 'ST00000001', '我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入我就是要加入', '你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办你能拿我怎么办', '3', '2020-05-12', NULL);
INSERT INTO `joinstapplication` VALUES ('307ed61b0ae9443f92cb081fbc1bd6d0', '201800001', 'ST00000013', '测试', '测试', '2', '2020-05-19', NULL);
INSERT INTO `joinstapplication` VALUES ('36a304c546204646b4e7112abe39795f', '201600001', 'ST00000014', '我也是肌肉猛男', '我也是肌肉猛男', '1', '2020-05-17', NULL);
INSERT INTO `joinstapplication` VALUES ('41d7daf1de15485d9778035fdfb91a2e', '201600001', 'ST34713211', '你好', '你好', '1', '2020-05-19', NULL);
INSERT INTO `joinstapplication` VALUES ('50064c58fa3842e9861bc8779bd2395e', '201600004', 'ST00000016', '测试', '测试', '1', '2020-05-17', NULL);
INSERT INTO `joinstapplication` VALUES ('5402e4b7b1e64a7ca5e7c2f851769831', '201600001', 'ST00000014', '测试', '测试', '1', '2020-05-19', NULL);
INSERT INTO `joinstapplication` VALUES ('748024935efd4170a25e4f28d74e7aea', '201700002', 'ST00000013', '你好啊', '测试啊', '3', '2020-06-03', '不让你来哈哈哈');
INSERT INTO `joinstapplication` VALUES ('76f349073f894aaeb7d7b4dd6d5b5337', '201600001', 'ST00000010', '我叫陈韵如', '申请加入', '3', '2020-05-12', NULL);
INSERT INTO `joinstapplication` VALUES ('83ddfead80754cf08fd80ab0a66777ed', '201800006', 'ST00000013', '你好测试', '你好测试', '3', '2020-06-03', '拒绝你');
INSERT INTO `joinstapplication` VALUES ('894a80e3b22a4c438448685475d9ec98', '201700004', 'ST00000001', 'ceshi ', 'ceshi', '2', '2020-05-17', NULL);
INSERT INTO `joinstapplication` VALUES ('e8917dfcb31a4216a96177fd60ba434e', '201700002', 'ST00000001', '加入社团', '加入社团', '2', '2020-05-12', NULL);

-- ----------------------------
-- Table structure for leavemessage
-- ----------------------------
DROP TABLE IF EXISTS `leavemessage`;
CREATE TABLE `leavemessage`  (
  `LM_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LM_SENDPERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LM_RECEIVERPERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LM_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LM_MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `LM_CREATETIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `LM_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`LM_KEY`) USING BTREE,
  INDEX `LM_RECEIVERPERSON`(`LM_RECEIVERPERSON`) USING BTREE,
  INDEX `LM_SENDPERSON`(`LM_SENDPERSON`) USING BTREE,
  CONSTRAINT `leavemessage_ibfk_1` FOREIGN KEY (`LM_SENDPERSON`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leavemessage
-- ----------------------------
INSERT INTO `leavemessage` VALUES ('033fa55314c84d189da0a582844cc213', '201600001', '201800013', '哈哈哈哈', '呱呱呱呱呱呱呱呱呱呱呱呱呱呱呱', '2020-05-04 11:05:21', '1', '1');
INSERT INTO `leavemessage` VALUES ('4a0d495c396e4e5491946c279170990f', '201600001', '201600013', '你好', '我是唐成', '2020-05-19 16:05:24', '1', '1');
INSERT INTO `leavemessage` VALUES ('63e3ac97b61c4044af73dbdf53a21a4e', '201600001', '201600004', '回复你好呀', '好的', '2020-05-17 22:10:58', '1', '1');
INSERT INTO `leavemessage` VALUES ('764df34f237847c79cb57eb29c16ca0a', '201600001', '201800013', '宁诗琪关于吃不吃宵夜的讨论', '宁诗琪个人认为今天不吃烤串了吧', '2020-05-04 10:55:24', '1', '1');
INSERT INTO `leavemessage` VALUES ('8053a84ec00c41c4a60f6821fe52c531', '201600001', '201700013', '么么哒', '么么么么哒', '2020-05-04 11:07:01', '1', '1');
INSERT INTO `leavemessage` VALUES ('8d4f88437cb94a1b9554259bf1afe385', '201600004', '201600001', '你好呀', '哈哈哈', '2020-05-17 22:10:58', '1', '2');
INSERT INTO `leavemessage` VALUES ('aa94d54f70b042baa693ced0391ead77', '201600004', '201600001', '你好我是社长', '请按时缴纳费用', '2020-05-17 13:08:31', '1', '1');
INSERT INTO `leavemessage` VALUES ('cf7e062ff43446d9a869e91a384218d1', '201600004', '201800004', '憨憨 你是不是', '我是你的小可爱咩', '2020-05-05 23:21:56', '1', '1');
INSERT INTO `leavemessage` VALUES ('d94514bee91b46379e76c46bffa2cbc5', '201600001', '201800013', '请联系我', '你好', '2020-05-04 11:03:04', '1', '1');
INSERT INTO `leavemessage` VALUES ('e962575b67e049a8ba8510295d257266', '201600001', '201700002', '小天天你好，可不可以给个联系方式', '有要事需要与你确认', '2020-05-17 13:15:12', '1', '1');
INSERT INTO `leavemessage` VALUES ('f81e0b6231eb49a89eaa98c606a10cb3', '201600001', '201700001', '憨憨大傻吊', '么么哒宝贝', '2020-05-09 22:10:55', '1', '1');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `ORDER_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ORDER_TOLLKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORDER_PAYPEOPLEKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORDER_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ORDER_PAYTIME` datetime(0) NULL DEFAULT NULL,
  `ORDER_PAYMONEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ORDER_KEY`) USING BTREE,
  INDEX `ORDER_TOLLKEY`(`ORDER_TOLLKEY`) USING BTREE,
  INDEX `ORDER_PAYPEOPLEKEY`(`ORDER_PAYPEOPLEKEY`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`ORDER_TOLLKEY`) REFERENCES `toll` (`TOLL_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`ORDER_PAYPEOPLEKEY`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('0050af7953fe469b83e65054675061ad', 'TOLL0110307600', '201600004', '1', NULL, '100');
INSERT INTO `order` VALUES ('018eea122ae94b6e8c1e4f056690d8ae', 'TOLL1406259808', '201600001', '1', NULL, '200');
INSERT INTO `order` VALUES ('086f6bc0e2b64d069c6f040bcf3ccea6', 'TOLL8831944710', '201600013', '1', NULL, '5000');
INSERT INTO `order` VALUES ('110bdfa6d3f849a799f050b5102992c7', 'TOLL0110307600', '201700002', '1', NULL, '100');
INSERT INTO `order` VALUES ('14cd11cb31cf47e6abb6e1b4fe391461', 'TOLL0110307600', '201800001', '1', NULL, '100');
INSERT INTO `order` VALUES ('376485a62c7549faba4c920c46e3eeb5', 'TOLL0110307600', '201700001', '1', NULL, '100');
INSERT INTO `order` VALUES ('4d846ca0dd074f1bad24903e41bc29f4', 'TOLL1406259808', '201600004', '1', NULL, '200');
INSERT INTO `order` VALUES ('4db07f8733b5495d81e927a1f0d72091', 'TOLL8831944710', '201800013', '1', NULL, '5000');
INSERT INTO `order` VALUES ('64170a9a3d094ccb96acfd2e5925f064', 'TOLL0110307600', '201600001', '2', NULL, '100');
INSERT INTO `order` VALUES ('80ca0f2891764c109c56a88c38ad972d', 'TOLL8831944710', '201800001', '1', NULL, '5000');
INSERT INTO `order` VALUES ('9fedebf96638470e9890d3d0972c9586', 'TOLL1406259808', '201700001', '1', NULL, '200');
INSERT INTO `order` VALUES ('a3a38c86598544f5b555aa21a498ea69', 'TOLL1406259808', '201700002', '1', NULL, '200');
INSERT INTO `order` VALUES ('a5d914e2d160406793c3bb7e9e16923e', 'TOLL8831944710', '201600001', '1', NULL, '5000');
INSERT INTO `order` VALUES ('f6ac0b925ab34348ad24d90db95afa49', 'TOLL1406259808', '201800001', '1', NULL, '200');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `PROFESSION_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PROFESSION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PROFESSION_COLLEGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`PROFESSION_KEY`) USING BTREE,
  INDEX `PROFESSION_COLLEGE`(`PROFESSION_COLLEGE`) USING BTREE,
  CONSTRAINT `profession_ibfk_1` FOREIGN KEY (`PROFESSION_COLLEGE`) REFERENCES `collegeinf` (`COLLEGE_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES ('PN1', '计算机科学与技术', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN10', '桥梁工程', 'CE3', '1');
INSERT INTO `profession` VALUES ('PN11', '美术学', 'CE4', '1');
INSERT INTO `profession` VALUES ('PN12', '视觉传达设计', 'CE4', '1');
INSERT INTO `profession` VALUES ('PN13', '土木工程', 'CE5', '1');
INSERT INTO `profession` VALUES ('PN14', '机械工程', 'CE5', '1');
INSERT INTO `profession` VALUES ('PN15', '给水排水工程', 'CE6', '1');
INSERT INTO `profession` VALUES ('PN16', '环境工程', 'CE6', '1');
INSERT INTO `profession` VALUES ('PN17', '建筑环境与设备工程', 'CE6', '1');
INSERT INTO `profession` VALUES ('PN18', '房地产管理', 'CE7', '1');
INSERT INTO `profession` VALUES ('PN19', '物流管理', 'CE7', '1');
INSERT INTO `profession` VALUES ('PN2', '软件工程', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN20', '工程造价', 'CE7', '1');
INSERT INTO `profession` VALUES ('PN21', '工程管理', 'CE7', '1');
INSERT INTO `profession` VALUES ('PN22', '书法学', 'CE8', '1');
INSERT INTO `profession` VALUES ('PN3', '网络工程', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN4', '电气工程及其自动化', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN5', '智能化', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN6', '自动化', 'CE1', '1');
INSERT INTO `profession` VALUES ('PN7', '高分子材料科学', 'CE2', '1');
INSERT INTO `profession` VALUES ('PN8', '无机非金属材料科学', 'CE2', '1');
INSERT INTO `profession` VALUES ('PN9', '交通工程', 'CE3', '1');

-- ----------------------------
-- Table structure for rootuser
-- ----------------------------
DROP TABLE IF EXISTS `rootuser`;
CREATE TABLE `rootuser`  (
  `USERKEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERPASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERFLAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  `USERPHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DELFLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`USERKEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rootuser
-- ----------------------------
INSERT INTO `rootuser` VALUES ('1', '201613559', '111111', '1', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg', '1');

-- ----------------------------
-- Table structure for staccounnt
-- ----------------------------
DROP TABLE IF EXISTS `staccounnt`;
CREATE TABLE `staccounnt`  (
  `STAT_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STAT_MONEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STAT_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`STAT_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staccounnt
-- ----------------------------
INSERT INTO `staccounnt` VALUES ('ST00000001', '200', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000002', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000003', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000004', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000005', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000006', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000007', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000008', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000009', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000010', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000011', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000012', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000013', '100', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000014', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000015', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000016', '0', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `staccounnt` VALUES ('ST00000017', '0', '96e79218965eb72c92a549dd5a330112');

-- ----------------------------
-- Table structure for stinf
-- ----------------------------
DROP TABLE IF EXISTS `stinf`;
CREATE TABLE `stinf`  (
  `ST_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ST_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PRESIDENTID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ST_KIND` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ST_STATUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `ST_CREATETIME` date NOT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  `ST_INTF` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ST_LOGO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ST_KEY`) USING BTREE,
  INDEX `PRESIDENTID`(`PRESIDENTID`) USING BTREE,
  INDEX `ST_KIND`(`ST_KIND`) USING BTREE,
  CONSTRAINT `stinf_ibfk_1` FOREIGN KEY (`PRESIDENTID`) REFERENCES `studentinf` (`STUDENT_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stinf_ibfk_3` FOREIGN KEY (`ST_KIND`) REFERENCES `stkind` (`STKIND_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stinf
-- ----------------------------
INSERT INTO `stinf` VALUES ('ST00000001', '媒来演去工作室', '201600004', 'SD0001', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/stlogo/5fe4aefde1544ee7b2d85d87c77a322d.jpg');
INSERT INTO `stinf` VALUES ('ST00000002', '网络信息中心', '201700002', 'SD0005', '2', '2020-04-03', '1', '哈哈哈哈哈哈烦烦烦烦烦烦烦烦烦方法', '/Files/stlogo/16e6b6242cc1494abf51907d662849dc.jpg');
INSERT INTO `stinf` VALUES ('ST00000003', '教学信息管理协会', '201600005', 'SD0002', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000004', 'ACM协会', '201600007', 'SD0002', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000005', '英语协会', '201600009', 'SD0002', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000006', '春花志愿者协会', '201600011', 'SD0003', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000007', '翼亦支教协会', '201600013', 'SD0003', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000008', 'GDS舞团', '201700008', 'SD0004', '2', '2020-04-03', '1', 'GDS舞团是一个专注于舞蹈的社团，成立于2008年，是建筑大学唯一的舞蹈社团，我们主要是街舞', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000009', '酷我音乐协会', '201600017', 'SD0004', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000010', '草帽文学社', '201600019', 'SD0004', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000011', '汉文化协会', '201600021', 'SD0004', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000012', '墨韵民乐团', '201600023', 'SD0004', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000013', 'LAS摄影协会', '201600001', 'SD0004', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000014', '肌肉猛男健身协会', '201600029', 'SD0005', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000015', '骑迹单车协会', '201600003', 'SD0005', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST00000016', '定向越野协会', '201600025', 'SD0005', '1', '2020-04-03', '1', '哈哈哈哈哈哈么么哒', '/Files/stlogo/602aa98e1bf64465822b3eabe737a004.jpeg');
INSERT INTO `stinf` VALUES ('ST00000017', '唐成烤蛋糕协会', '201600008', 'SD0002', '1', '2020-04-03', '1', '哈哈哈哈哈哈', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg');
INSERT INTO `stinf` VALUES ('ST34713211', '周杰伦音乐社', '201600009', 'SD0002', '1', '2020-05-19', '1', '我爱周杰伦', '');
INSERT INTO `stinf` VALUES ('ST43930502', '测试', '201700004', 'SD0003', '1', '2020-05-19', '1', '测试', '');

-- ----------------------------
-- Table structure for stkind
-- ----------------------------
DROP TABLE IF EXISTS `stkind`;
CREATE TABLE `stkind`  (
  `STKIND_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STKIND_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`STKIND_KEY`, `DEL_FLAG`) USING BTREE,
  INDEX `STKIND_KEY`(`STKIND_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stkind
-- ----------------------------
INSERT INTO `stkind` VALUES ('SD0001', '校园服务类', '1');
INSERT INTO `stkind` VALUES ('SD0002', '学术科技类', '1');
INSERT INTO `stkind` VALUES ('SD0003', '社会公益类', '1');
INSERT INTO `stkind` VALUES ('SD0004', '文化文艺类', '1');
INSERT INTO `stkind` VALUES ('SD0005', '体育健身类', '1');

-- ----------------------------
-- Table structure for stpersoninf
-- ----------------------------
DROP TABLE IF EXISTS `stpersoninf`;
CREATE TABLE `stpersoninf`  (
  `STPE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STPE_PERSONID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STPE_STID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `IS_SZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2',
  `JOIN_TIME` date NOT NULL,
  PRIMARY KEY (`STPE_KEY`) USING BTREE,
  INDEX `STPE_PERSONID`(`STPE_PERSONID`) USING BTREE,
  INDEX `STPE_STID`(`STPE_STID`) USING BTREE,
  CONSTRAINT `stpersoninf_ibfk_1` FOREIGN KEY (`STPE_PERSONID`) REFERENCES `user` (`USER_STID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stpersoninf_ibfk_2` FOREIGN KEY (`STPE_STID`) REFERENCES `stinf` (`ST_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stpersoninf
-- ----------------------------
INSERT INTO `stpersoninf` VALUES ('STPE2020000001', '201600002', 'ST00000002', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000002', '201600003', 'ST00000003', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000003', '201600004', 'ST00000004', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000004', '201600005', 'ST00000005', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000005', '201600006', 'ST00000006', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000006', '201600007', 'ST00000007', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000007', '201600008', 'ST00000008', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000008', '201600009', 'ST00000009', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000009', '201600010', 'ST00000010', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000010', '201600011', 'ST00000011', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000011', '201600012', 'ST00000012', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000012', '201600013', 'ST00000013', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000013', '201600014', 'ST00000014', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000014', '201600015', 'ST00000015', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000015', '201600016', 'ST00000016', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000016', '201600017', 'ST00000017', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000017', '201700001', 'ST00000001', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000018', '201700002', 'ST00000002', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000019', '201700003', 'ST00000003', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000021', '201700005', 'ST00000005', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000022', '201700006', 'ST00000006', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000023', '201700007', 'ST00000007', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000024', '201700008', 'ST00000008', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000025', '201700009', 'ST00000009', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000026', '201700010', 'ST00000010', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000027', '201700011', 'ST00000011', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000028', '201700012', 'ST00000012', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000030', '201700014', 'ST00000014', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000031', '201700015', 'ST00000015', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000032', '201700016', 'ST00000016', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000033', '201700017', 'ST00000017', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000034', '201800001', 'ST00000001', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000035', '201800002', 'ST00000002', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000036', '201800003', 'ST00000003', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000037', '201800004', 'ST00000004', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000038', '201800005', 'ST00000005', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000039', '201800006', 'ST00000006', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000040', '201800007', 'ST00000007', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000041', '201800008', 'ST00000008', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000042', '201800009', 'ST00000009', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000043', '201800010', 'ST00000010', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000044', '201800011', 'ST00000011', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000045', '201800012', 'ST00000012', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000046', '201800013', 'ST00000013', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000047', '201800014', 'ST00000014', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000048', '201800015', 'ST00000015', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000049', '201800016', 'ST00000016', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000050', '201800017', 'ST00000017', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000051', '201600004', 'ST00000001', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000053', '201600005', 'ST00000003', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000054', '201600007', 'ST00000004', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000055', '201600009', 'ST00000005', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000056', '201600011', 'ST00000006', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000057', '201600013', 'ST00000007', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000058', '201600015', 'ST00000008', '1', '2', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000059', '201600017', 'ST00000009', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000060', '201600019', 'ST00000010', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000061', '201600021', 'ST00000011', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000062', '201600023', 'ST00000012', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000063', '201600001', 'ST00000013', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000064', '201600029', 'ST00000014', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000065', '201600003', 'ST00000015', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000066', '201600025', 'ST00000016', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE2020000067', '201600008', 'ST00000017', '1', '1', '2020-04-29');
INSERT INTO `stpersoninf` VALUES ('STPE4824782361', '201700002', 'ST00000001', '1', '2', '2020-05-13');
INSERT INTO `stpersoninf` VALUES ('STPE6751919077', '201800001', 'ST00000013', '1', '2', '2020-05-19');

-- ----------------------------
-- Table structure for studentinf
-- ----------------------------
DROP TABLE IF EXISTS `studentinf`;
CREATE TABLE `studentinf`  (
  `STUDENT_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STUDENT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STUDENT_PROFESSION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`STUDENT_KEY`) USING BTREE,
  INDEX `STUDENT_PROFESSION`(`STUDENT_PROFESSION`) USING BTREE,
  INDEX `STUDENT_NAME`(`STUDENT_NAME`) USING BTREE,
  CONSTRAINT `studentinf_ibfk_1` FOREIGN KEY (`STUDENT_PROFESSION`) REFERENCES `profession` (`PROFESSION_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studentinf
-- ----------------------------
INSERT INTO `studentinf` VALUES ('201600001', '陈韵如', 'PN1', '1');
INSERT INTO `studentinf` VALUES ('201600002', '黄雨萱', 'PN2', '1');
INSERT INTO `studentinf` VALUES ('201600003', '李子维', 'PN3', '1');
INSERT INTO `studentinf` VALUES ('201600004', '王诠胜', 'PN4', '1');
INSERT INTO `studentinf` VALUES ('201600005', '莫俊杰', 'PN5', '1');
INSERT INTO `studentinf` VALUES ('201600006', '许光汉', 'PN6', '1');
INSERT INTO `studentinf` VALUES ('201600007', '柯佳嬿', 'PN7', '1');
INSERT INTO `studentinf` VALUES ('201600008', '施柏宇', 'PN8', '1');
INSERT INTO `studentinf` VALUES ('201600009', '周杰伦', 'PN9', '1');
INSERT INTO `studentinf` VALUES ('201600010', '华晨宇', 'PN10', '1');
INSERT INTO `studentinf` VALUES ('201600011', '许嵩', 'PN11', '1');
INSERT INTO `studentinf` VALUES ('201600012', '邓紫棋', 'PN12', '1');
INSERT INTO `studentinf` VALUES ('201600013', '周深', 'PN13', '1');
INSERT INTO `studentinf` VALUES ('201600014', '仝卓', 'PN14', '1');
INSERT INTO `studentinf` VALUES ('201600015', '高天鹤', 'PN15', '1');
INSERT INTO `studentinf` VALUES ('201600016', '王宝强', 'PN16', '1');
INSERT INTO `studentinf` VALUES ('201600017', '陈思成', 'PN17', '1');
INSERT INTO `studentinf` VALUES ('201600018', '周冬雨', 'PN18', '1');
INSERT INTO `studentinf` VALUES ('201600019', '易烊千玺', 'PN19', '1');
INSERT INTO `studentinf` VALUES ('201600020', '王俊凯', 'PN20', '1');
INSERT INTO `studentinf` VALUES ('201600021', '王源', 'PN21', '1');
INSERT INTO `studentinf` VALUES ('201600022', '刘昊然', 'PN22', '1');
INSERT INTO `studentinf` VALUES ('201600023', '肖战', 'PN1', '1');
INSERT INTO `studentinf` VALUES ('201600024', '范闲', 'PN10', '1');
INSERT INTO `studentinf` VALUES ('201600025', '迪迦', 'PN13', '1');
INSERT INTO `studentinf` VALUES ('201600026', '洛天依', 'PN14', '1');
INSERT INTO `studentinf` VALUES ('201600027', '言和', 'PN16', '1');
INSERT INTO `studentinf` VALUES ('201600028', '龙牙', 'PN17', '1');
INSERT INTO `studentinf` VALUES ('201600029', '禾木', 'PN7', '1');
INSERT INTO `studentinf` VALUES ('201600030', '野比大雄', 'PN20', '1');
INSERT INTO `studentinf` VALUES ('201600031', '静香', 'PN14', '1');
INSERT INTO `studentinf` VALUES ('201600032', '胖虎', 'PN11', '1');
INSERT INTO `studentinf` VALUES ('201600033', '管虎', 'PN2', '1');
INSERT INTO `studentinf` VALUES ('201600034', '朱一旦', 'PN1', '1');
INSERT INTO `studentinf` VALUES ('201600035', '林之相', 'PN3', '1');
INSERT INTO `studentinf` VALUES ('201600036', '梦龙', 'PN5', '1');
INSERT INTO `studentinf` VALUES ('201611618', '大帅哥', 'PN2', '1');
INSERT INTO `studentinf` VALUES ('201700001', '肖央', 'PN1', '1');
INSERT INTO `studentinf` VALUES ('201700002', '张天', 'PN2', '1');
INSERT INTO `studentinf` VALUES ('201700003', '黄生南', 'PN3', '1');
INSERT INTO `studentinf` VALUES ('201700004', '黄智贤', 'PN4', '1');
INSERT INTO `studentinf` VALUES ('201700005', '全智贤', 'PN5', '1');
INSERT INTO `studentinf` VALUES ('201700006', '孔刘', 'PN6', '1');
INSERT INTO `studentinf` VALUES ('201700007', '刘德华', 'PN7', '1');
INSERT INTO `studentinf` VALUES ('201700008', '张学友', 'PN8', '1');
INSERT INTO `studentinf` VALUES ('201700009', '伍佰', 'PN9', '1');
INSERT INTO `studentinf` VALUES ('201700010', '黄豆豆', 'PN10', '1');
INSERT INTO `studentinf` VALUES ('201700011', '吕子乔', 'PN11', '1');
INSERT INTO `studentinf` VALUES ('201700012', '佟湘玉', 'PN12', '1');
INSERT INTO `studentinf` VALUES ('201700013', '李大嘴', 'PN13', '1');
INSERT INTO `studentinf` VALUES ('201700014', '郭芙蓉', 'PN14', '1');
INSERT INTO `studentinf` VALUES ('201700015', '吕秀才', 'PN15', '1');
INSERT INTO `studentinf` VALUES ('201700016', '燕小六', 'PN16', '1');
INSERT INTO `studentinf` VALUES ('201700017', '雷军', 'PN17', '1');
INSERT INTO `studentinf` VALUES ('201700018', '罗永浩', 'PN18', '1');
INSERT INTO `studentinf` VALUES ('201700019', '黄章', 'PN19', '1');
INSERT INTO `studentinf` VALUES ('201700020', '余承东', 'PN20', '1');
INSERT INTO `studentinf` VALUES ('201700021', '赵明', 'PN21', '1');
INSERT INTO `studentinf` VALUES ('201700022', '赵红', 'PN22', '1');
INSERT INTO `studentinf` VALUES ('201800001', '张三', 'PN1', '1');
INSERT INTO `studentinf` VALUES ('201800002', '徐佳莹', 'PN2', '1');
INSERT INTO `studentinf` VALUES ('201800003', '徐良', 'PN3', '1');
INSERT INTO `studentinf` VALUES ('201800004', '吉克隽逸', 'PN4', '1');
INSERT INTO `studentinf` VALUES ('201800005', '莫文蔚', 'PN5', '1');
INSERT INTO `studentinf` VALUES ('201800006', '孙燕姿', 'PN6', '1');
INSERT INTO `studentinf` VALUES ('201800007', '李克勤', 'PN7', '1');
INSERT INTO `studentinf` VALUES ('201800008', '韩红', 'PN8', '1');
INSERT INTO `studentinf` VALUES ('201800009', '黄景瑜', 'PN9', '1');
INSERT INTO `studentinf` VALUES ('201800010', '花少北', 'PN10', '1');
INSERT INTO `studentinf` VALUES ('201800011', '老番茄', 'PN11', '1');
INSERT INTO `studentinf` VALUES ('201800012', '唐成', 'PN12', '1');
INSERT INTO `studentinf` VALUES ('201800013', '宁诗琪', 'PN13', '1');
INSERT INTO `studentinf` VALUES ('201800014', '曹应腾', 'PN14', '1');
INSERT INTO `studentinf` VALUES ('201800015', '马赫', 'PN15', '1');
INSERT INTO `studentinf` VALUES ('201800016', '王言', 'PN16', '1');
INSERT INTO `studentinf` VALUES ('201800017', '樊秒', 'PN17', '1');
INSERT INTO `studentinf` VALUES ('201800018', '李成磊', 'PN18', '1');
INSERT INTO `studentinf` VALUES ('201800019', '高朝凤', 'PN19', '1');
INSERT INTO `studentinf` VALUES ('201800020', '曹鹏宇', 'PN20', '1');
INSERT INTO `studentinf` VALUES ('201800021', '刘佳琪', 'PN21', '1');
INSERT INTO `studentinf` VALUES ('201800022', '李佳奇', 'PN22', '1');

-- ----------------------------
-- Table structure for toll
-- ----------------------------
DROP TABLE IF EXISTS `toll`;
CREATE TABLE `toll`  (
  `TOLL_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TOLL_ST` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOLL_TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOLL_MESSAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOTLL_CREATETIME` date NULL DEFAULT NULL,
  `TOTLL_LASTPAYTIME` date NULL DEFAULT NULL,
  `TOTLL_PAYMMONEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOLL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`TOLL_KEY`) USING BTREE,
  INDEX `TOLL_ST`(`TOLL_ST`) USING BTREE,
  CONSTRAINT `toll_ibfk_1` FOREIGN KEY (`TOLL_ST`) REFERENCES `stinf` (`ST_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of toll
-- ----------------------------
INSERT INTO `toll` VALUES ('TOLL0110307600', 'ST00000013', '摄影展经费', '交钱', '2020-05-17', '2020-05-30', '100', '2');
INSERT INTO `toll` VALUES ('TOLL1406259808', 'ST00000001', '请参与本周末聚餐的同学支付餐费', '吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂吃饭地点荷春堂', '2020-05-14', '2020-05-23', '200', '2');
INSERT INTO `toll` VALUES ('TOLL8831944710', 'ST00000013', '活动费用', '活动费用', '2020-05-19', '2020-05-30', '5000', '1');

-- ----------------------------
-- Table structure for topup
-- ----------------------------
DROP TABLE IF EXISTS `topup`;
CREATE TABLE `topup`  (
  `TOPUP_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TOPUP_MONEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOPUP_TIME` datetime(0) NULL DEFAULT NULL,
  `TOPUP_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TOPUP_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topup
-- ----------------------------
INSERT INTO `topup` VALUES ('0a6aa1c0bf7a4be2a47afbd8fbaffc95', '200', '2020-05-19 15:51:45', '201600001');
INSERT INTO `topup` VALUES ('1291b663e419412ab33f11c65c2aa9df', '200', '2020-05-21 23:30:47', '201600001');
INSERT INTO `topup` VALUES ('54443486b8d045d4ab9446ec6433c79f', '200', '2020-05-16 00:06:17', '201600001');
INSERT INTO `topup` VALUES ('a01ba77d2e214dcb84efc157092f107f', '1000', '2020-06-08 22:28:04', '201600001');
INSERT INTO `topup` VALUES ('a5c86f6383f1481aad1f9f7b27cd3377', '20', '2020-05-15 02:21:38', '201600001');
INSERT INTO `topup` VALUES ('f33b6a97e2c5447797e67354a5812622', '1000', '2020-05-19 16:10:45', '201600001');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `USER_TEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_STID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_PHOTO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DEL_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`USER_TEL`) USING BTREE,
  INDEX `USER_STID`(`USER_STID`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`USER_STID`) REFERENCES `studentinf` (`STUDENT_KEY`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13331760001', '201600002', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760002', '201600003', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760004', '201600005', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760005', '201600006', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760006', '201600007', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760007', '201600008', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760008', '201600009', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760009', '201600010', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760010', '201600011', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760011', '201600012', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760012', '201600013', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760013', '201600014', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760014', '201600015', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760015', '201600016', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760016', '201600017', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760017', '201600018', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760018', '201600019', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760019', '201600020', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760020', '201700001', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760021', '201700002', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760022', '201700003', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760024', '201700005', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760025', '201700006', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760026', '201700007', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760027', '201700008', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760028', '201700009', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760029', '201700010', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760030', '201700011', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760031', '201700012', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760032', '201700013', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760033', '201700014', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760034', '201700015', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760035', '201700016', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760036', '201700017', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760037', '201700018', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760038', '201700019', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760039', '201700020', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760040', '201800001', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760041', '201800002', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760042', '201800003', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760043', '201800004', '111111', '/Files/touxiang/290c206754234831a019f1a60a625f54.jpeg', '1');
INSERT INTO `user` VALUES ('13331760044', '201800005', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760045', '201800006', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760046', '201800007', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760047', '201800008', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760048', '201800009', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760049', '201800010', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760050', '201800011', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760051', '201800012', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760052', '201800013', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760053', '201800014', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760054', '201800015', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760055', '201800016', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760056', '201800017', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760057', '201800018', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760058', '201800019', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760059', '201800020', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760060', '201600021', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760061', '201600022', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760062', '201600023', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760063', '201600024', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760064', '201600025', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760065', '201600026', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760066', '201600027', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760067', '201600028', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760068', '201600029', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760069', '201600030', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760070', '201600031', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760071', '201600032', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760072', '201600033', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760073', '201600034', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331760074', '201600035', '111111', ' ', '1');
INSERT INTO `user` VALUES ('13331762987', '201700004', '111111', '/Files/touxiang/ddf579da82704778b2dcf98e7452dba9.jpg', '1');
INSERT INTO `user` VALUES ('13331762995', '201600004', '123456', '/Files/touxiang/90739fb5b2024693adda6744f65209fc.jpg', '1');
INSERT INTO `user` VALUES ('13331762996', '201600001', '111111', '/Files/touxiang/7fa395e45bff48f6a7cca685da1dbaf6.jpg', '1');

SET FOREIGN_KEY_CHECKS = 1;
