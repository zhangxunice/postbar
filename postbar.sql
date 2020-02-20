/*
 Navicat Premium Data Transfer

 Source Server         : zhangxu
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : postbar

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 20/02/2020 23:12:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题id',
  `type` int(64) NULL DEFAULT NULL COMMENT '回复类别（一级回复、二级回复）',
  `comment_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人id',
  `comment` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人内容',
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `like_count` bigint(255) NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '12442', 1, '1', '成功吧', 1567577543858, 1567577543858, 0, 2);
INSERT INTO `comment` VALUES ('16d389b3-dd1f-436e-ae13-0c69e06df8f8', '1ad28b03-ddfb-4594-b234-95f588e31b8d', 2, '1', '你也是这么觉得的？', 1567858428945, 1567858428945, 0, 0);
INSERT INTO `comment` VALUES ('1ad28b03-ddfb-4594-b234-95f588e31b8d', '12442', 1, '1', '黄鑫乘是傻逼', 1567578557083, 1567578557083, 0, 1);
INSERT INTO `comment` VALUES ('31594b21-5687-4bc2-af38-e02d8fd8f7ca', '12442', 1, '1', '测试回复', 1567819784030, 1567819784030, 0, 0);
INSERT INTO `comment` VALUES ('36ecda50-4e2c-4a43-9f5e-810c503f8cd2', '1', 2, '1', '在试一下', 1567913853080, 1567913853080, 0, 0);
INSERT INTO `comment` VALUES ('3fce67bc-78e4-44f1-aa1b-9b3df5d1bded', 'df4c39a6-144e-4a3e-9c44-e9761add84bd', 2, '1', '测试二级回复', 1567846350050, 1567846350050, 0, 0);
INSERT INTO `comment` VALUES ('5174ddfa-9d88-453e-a05b-ee51c8666677', '12442', 1, '1', '黄鑫乘大傻逼', 1567577669266, 1567577669266, 0, 0);
INSERT INTO `comment` VALUES ('8c598f99-5653-4414-b058-b05211952644', '1', 2, '1', '跟着黄鑫乘学python', 1567851422922, 1567851422922, 0, 0);
INSERT INTO `comment` VALUES ('b3449708-2ecf-4694-9e10-9d907deb17cc', '12442', 1, '1', '分隔符', 1567651683591, 1567651683591, 0, 0);
INSERT INTO `comment` VALUES ('df4c39a6-144e-4a3e-9c44-e9761add84bd', '12442', 1, '1', '重构后的评论', 1567839425041, 1567839425041, 0, 0);
INSERT INTO `comment` VALUES ('ed5f44a2-de9a-44c2-a7dc-06de63448b96', '1', 2, '1', '第一条二级回复', 1567839489374, 1567839489374, 0, 0);
INSERT INTO `comment` VALUES ('ede6cd05-deae-4c23-beed-f453669a24d8', '1', 2, '1', '测试二级评论数', 1567913831522, 1567913831522, 0, 0);
INSERT INTO `comment` VALUES ('f8fc49bc-7672-41f6-8fd6-0544917e66fa', '1ad28b03-ddfb-4594-b234-95f588e31b8d', 2, '1', '看来我们都是一致的想法', 1567914282969, 1567914282969, 0, 0);
INSERT INTO `comment` VALUES ('f94bc8a5-da8e-4b46-96e6-b4c7c0e2dd31', '12442', 1, '1', '评论一下', 1567819434501, 1567819434501, 0, 0);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT NULL COMMENT '评论数',
  `view_count` int(255) NULL DEFAULT NULL COMMENT '浏览数',
  `like_count` int(255) NULL DEFAULT NULL COMMENT '点赞数',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('12442', 'java怎么学', 'hxc', '1', 53, 321, 21, 'java', 0, 0);
INSERT INTO `question` VALUES ('12452', 'spring怎么学', 'hxc', '2', 45, 89, 21, 'java,spring', 0, 0);
INSERT INTO `question` VALUES ('2352', 'phython怎么学啊', '馒头馅的包子', '1', 45, 60, 21, 'python', 0, 0);
INSERT INTO `question` VALUES ('235635', 'springmvc怎么学', 'hxc', '2', 45, 58, 21, 'java,spring,springmvc', 0, 0);
INSERT INTO `question` VALUES ('245', 'jsp怎么学', 'hxc', '1', 45, 59, 21, 'java', 0, 0);
INSERT INTO `question` VALUES ('25425', 'web怎么学', 'hxc', '2', 45, 59, 21, 'html/css', 0, 0);
INSERT INTO `question` VALUES ('3978582f-34db-48fe-89bd-77218bc8666b', '帮我看下这段代码的问题', ' public Object comment(@RequestBody CommentDto commentDto, HttpServletRequest request){\r\n        User user=(User) request.getSession().getAttribute(\"user\");\r\n        if (user==null){\r\n            throw new CustomException(CustomEnum.NO_LOGIN);\r\n        }\r\n        if (commentDto==null|| StringUtils.isBlank(commentDto.getComment())){\r\n            throw new CustomException(CustomEnum.COMMENT_IS_EMPTY);\r\n        }\r\n        Comment comment=new Comment();\r\n        comment.setId(String.valueOf(UUID.randomUUID()));\r\n        comment.setParentId(commentDto.getParentId());\r\n        comment.setCommentId(String.valueOf(1));\r\n        comment.setComment(commentDto.getComment());\r\n        comment.setGmtCreate(System.currentTimeMillis());\r\n        comment.setGmtModified(comment.getGmtCreate());\r\n        comment.setType(commentDto.getType());\r\n        comment.setCommentCount(0);\r\n        commentService.insertcomment(comment);\r\n        return ResultDto.ok(200,\"请求成功\");\r\n    }', '1', 0, 1, 0, 'Java,SpringBoot', 1568098950921, 1568098950921);
INSERT INTO `question` VALUES ('525', 'mysql怎么学', 'hxc', '1', 45, 58, 21, 'mysql', 0, 0);
INSERT INTO `question` VALUES ('5256', 'springboot怎么学', 'hxc', '2', 45, 59, 21, 'java,spring,springboot', 0, 0);
INSERT INTO `question` VALUES ('535', 'c++怎么学', 'hxc', '1', 45, 58, 21, 'c++', 0, 0);
INSERT INTO `question` VALUES ('68419720-c5f6-412a-bc5b-0c09605278f0', 'ssm怎么学', '黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm黄鑫乘教你如何学习ssm', '1', 0, 3, 0, 'SSM', 1568024699998, 1568024699998);
INSERT INTO `question` VALUES ('c4e318a8-0eed-454b-ad9b-566234c88619', '微信小程序怎么学', 'hxc', '2', 21, 48, 8, 'web', 0, 0);
INSERT INTO `question` VALUES ('z205', 'Android怎么学', 'hxc', '1', 45, 58, 21, 'android', 0, 0);
INSERT INTO `question` VALUES ('zsfdgjhjh12254462', 'c语言怎么学', 'hxc', '2', 45, 58, 21, 'c', 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '53535', '小明', '123', '2d15df15', NULL, NULL, '/images/faceurl.jpg');
INSERT INTO `user` VALUES ('2', '568', '小黄', '123', '5d1f2d', NULL, NULL, '/images/faceurl.jpg');

SET FOREIGN_KEY_CHECKS = 1;
