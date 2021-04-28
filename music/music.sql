/*
Navicat MySQL Data Transfer

Source Server         : localmusic
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : music

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-04-28 15:42:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` int(1) DEFAULT NULL COMMENT '收藏类型（0歌曲1歌单）',
  `song_id` int(11) DEFAULT NULL COMMENT '歌曲id',
  `songlist_id` int(11) DEFAULT NULL COMMENT '歌单id',
  `create_time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='收藏';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '17', '0', '36', null, '2021-02-23 00:01:14');
INSERT INTO `collect` VALUES ('2', '17', '0', '24', null, '2021-02-23 00:13:38');
INSERT INTO `collect` VALUES ('4', '17', '0', '29', null, '2021-02-23 14:24:48');
INSERT INTO `collect` VALUES ('5', '17', '0', '13', null, '2021-02-23 14:33:13');
INSERT INTO `collect` VALUES ('7', '17', '0', '37', null, '2021-02-23 14:42:56');
INSERT INTO `collect` VALUES ('9', '18', '0', '15', null, '2021-02-23 14:57:54');
INSERT INTO `collect` VALUES ('11', '18', '0', '20', null, '2021-02-23 15:39:39');
INSERT INTO `collect` VALUES ('16', '17', '0', '19', null, '2021-02-23 15:51:37');
INSERT INTO `collect` VALUES ('17', '17', '0', '15', null, '2021-02-23 15:52:09');
INSERT INTO `collect` VALUES ('18', '17', '0', '39', null, '2021-02-23 15:58:16');
INSERT INTO `collect` VALUES ('19', '17', '0', '4', null, '2021-02-23 16:20:25');
INSERT INTO `collect` VALUES ('21', '1', '0', '12', null, '2021-02-23 16:38:18');
INSERT INTO `collect` VALUES ('24', '1', '0', '15', null, '2021-02-23 22:19:09');
INSERT INTO `collect` VALUES ('25', '1', '0', '25', null, '2021-02-23 22:20:10');
INSERT INTO `collect` VALUES ('26', '1', '0', '26', null, '2021-02-23 22:22:27');
INSERT INTO `collect` VALUES ('27', '1', '0', '23', null, '2021-02-23 22:25:00');
INSERT INTO `collect` VALUES ('28', '1', '0', '40', null, '2021-02-23 22:55:30');
INSERT INTO `collect` VALUES ('29', '1', '0', '13', null, '2021-03-14 21:15:51');
INSERT INTO `collect` VALUES ('30', '1', '0', '14', null, '2021-03-14 21:24:18');
INSERT INTO `collect` VALUES ('31', '19', '0', '20', null, '2021-03-30 20:46:17');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` int(1) DEFAULT NULL COMMENT '评论类型（0歌曲1歌单2动态）',
  `song_id` int(11) DEFAULT NULL COMMENT '评论歌曲id',
  `songlist_id` int(11) DEFAULT NULL COMMENT '评论歌单id',
  `community_id` int(11) DEFAULT NULL COMMENT '动态id',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `up` int(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '17', '1', null, '3', null, '2021-02-18 14:39:00', 'good', '63');
INSERT INTO `comment` VALUES ('2', '17', '1', null, '3', null, '2021-02-18 15:03:07', '非常好', '27');
INSERT INTO `comment` VALUES ('3', '17', '1', null, '3', null, '2021-02-18 15:13:39', '11', '15');
INSERT INTO `comment` VALUES ('4', '17', '1', null, '1', null, '2021-02-18 16:45:29', '不错', '1');
INSERT INTO `comment` VALUES ('5', '17', '1', null, '6', null, '2021-02-18 16:52:04', '好听', '15');
INSERT INTO `comment` VALUES ('6', '18', '2', null, null, '5', '2021-04-27 16:52:04', '111', null);
INSERT INTO `comment` VALUES ('7', '18', '1', null, '3', null, '2021-04-27 23:23:14', 'v', null);
INSERT INTO `comment` VALUES ('8', '18', '2', null, null, '1', '2021-04-27 23:31:00', 'b', '0');
INSERT INTO `comment` VALUES ('9', '18', '2', null, null, '5', '2021-04-28 10:27:36', '45', null);

-- ----------------------------
-- Table structure for comment_son
-- ----------------------------
DROP TABLE IF EXISTS `comment_son`;
CREATE TABLE `comment_son` (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '评论者id',
  `to_id` int(11) DEFAULT NULL COMMENT '被评论者id',
  `up` int(11) DEFAULT NULL COMMENT '点赞数',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_son
-- ----------------------------

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '上传的用户名字',
  `type` int(11) DEFAULT NULL COMMENT '类型(0歌曲1视频2图片3文字)',
  `url` varchar(255) DEFAULT NULL COMMENT '上传的歌曲地址',
  `content` varchar(255) DEFAULT NULL COMMENT '介绍',
  `create_time` datetime DEFAULT NULL COMMENT '发送时间',
  `up` int(11) DEFAULT NULL COMMENT '点赞数',
  `img` varchar(255) DEFAULT NULL COMMENT '用户上传的图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES ('1', '333', '3', null, '333', '2021-03-14 19:42:00', '89', null);
INSERT INTO `community` VALUES ('5', 'a', '1', '/video/1615806676327WeChat_20210315171143.mp4', '图片测试', '2021-03-14 20:25:04', '35', 'null');
INSERT INTO `community` VALUES ('9', 'b', '2', null, '图片测试', '2021-03-14 23:21:37', '12', '/img/communityPic/1615810649043juzi.jpg');
INSERT INTO `community` VALUES ('10', 'b', '0', '/song/1615810679285林宥嘉 - 兜圈.mp3', 'bbbb', '2021-03-14 23:23:25', '18', 'undefined');
INSERT INTO `community` VALUES ('11', '333', '0', '/song/1615810692408五月天 - 温柔.mp3', '333333', '2021-03-14 23:27:17', '2', 'undefined');
INSERT INTO `community` VALUES ('12', '22', '2', 'undefined', '这是图片哦', '2021-03-19 13:13:39', '4', '/img/communityPic/1616130838151T001R300x300M000002qmiDg3pQbUU.jpg');
INSERT INTO `community` VALUES ('13', '尔听°', '3', 'undefined', '这是文字哦', '2021-03-19 13:14:29', '9', 'undefined');
INSERT INTO `community` VALUES ('37', '333', '2', 'undefined', null, '2021-04-18 21:44:31', '6', '/img/communityPic/161875409075718252992533065802.jpg');
INSERT INTO `community` VALUES ('38', '333', '2', 'undefined', null, '2021-04-18 21:46:13', '1', '/img/communityPic/1618755682023300 (2).jpg');
INSERT INTO `community` VALUES ('49', '333', '2', 'undefined', null, '2021-04-18 22:32:32', '3', '/img/communityPic/1618756362564p.jpg');
INSERT INTO `community` VALUES ('54', '333', '2', 'undefined', null, '2021-04-18 22:43:29', '2', '/img/communityPic/1618757017707109951165549520393.jpg');
INSERT INTO `community` VALUES ('60', '333', '2', 'undefined', null, '2021-04-18 23:06:11', '2', '/img/communityPic/1618758381400T001R300x300M0000007sHCz2lllqK.jpg');
INSERT INTO `community` VALUES ('61', '333', '1', '/video/1618758958201杨千嬅 - 可惜我是水瓶座.mp3', null, '2021-04-18 23:09:09', '0', '/img/communityPic/16189941324771.jpg');
INSERT INTO `community` VALUES ('66', '333', '2', null, '789', '2021-04-26 10:28:57', null, '/img/communityPic/16194041372141.jpg');
INSERT INTO `community` VALUES ('67', '333', '2', null, '4', '2021-04-26 17:43:51', null, '/img/communityPic/16194302311201.jpg');
INSERT INTO `community` VALUES ('68', 'b', '2', null, '098', '2021-04-26 17:45:31', null, '/img/communityPic/16194303317371.jpg');
INSERT INTO `community` VALUES ('69', 'b', '2', null, '123', '2021-04-26 18:12:13', null, '/img/communityPic/16194319334331.jpg');
INSERT INTO `community` VALUES ('70', 'b', '2', null, '456', '2021-04-26 18:23:26', null, '/img/communityPic/16194326060031.jpg');
INSERT INTO `community` VALUES ('71', 'b', '2', null, '456', '2021-04-26 18:25:53', null, '/img/communityPic/16194327538061.jpg');
INSERT INTO `community` VALUES ('72', 'b', '2', null, '456', '2021-04-26 18:28:01', null, '/img/communityPic/16194328814271.jpg');
INSERT INTO `community` VALUES ('73', 'b', '2', null, '5', '2021-04-28 11:20:22', null, '/img/communityPic/16195800219951.jpg');
INSERT INTO `community` VALUES ('74', 'b', '2', null, '5', '2021-04-28 11:21:31', null, '/img/communityPic/16195800911601.jpg');
INSERT INTO `community` VALUES ('75', 'b', '2', null, '6', '2021-04-28 11:23:16', null, '/img/communityPic/16195801966551.jpg');
INSERT INTO `community` VALUES ('76', 'b', '2', null, '4', '2021-04-28 11:24:46', '1', '/img/communityPic/16195802864871.jpg');

-- ----------------------------
-- Table structure for community_up
-- ----------------------------
DROP TABLE IF EXISTS `community_up`;
CREATE TABLE `community_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_id` int(11) DEFAULT NULL COMMENT '用户id',
  `community_id` int(11) DEFAULT NULL COMMENT '动态id',
  `like_status` int(11) DEFAULT NULL COMMENT '点赞的状态（0取消赞1已赞）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community_up
-- ----------------------------
INSERT INTO `community_up` VALUES ('2', '2', '1', '0');
INSERT INTO `community_up` VALUES ('7', '1', '5', '1');
INSERT INTO `community_up` VALUES ('38', '1', '9', '1');
INSERT INTO `community_up` VALUES ('92', '1', '54', '0');
INSERT INTO `community_up` VALUES ('124', '1', '60', '1');
INSERT INTO `community_up` VALUES ('125', '1', '11', '1');
INSERT INTO `community_up` VALUES ('126', '1', '1', '1');
INSERT INTO `community_up` VALUES ('132', '1', '12', '0');
INSERT INTO `community_up` VALUES ('133', '1', '13', '0');
INSERT INTO `community_up` VALUES ('134', '17', '1', '1');
INSERT INTO `community_up` VALUES ('135', '17', '5', '0');
INSERT INTO `community_up` VALUES ('136', '17', '9', '0');
INSERT INTO `community_up` VALUES ('137', '17', '11', '1');
INSERT INTO `community_up` VALUES ('138', '17', '10', '0');
INSERT INTO `community_up` VALUES ('139', '17', '12', '1');
INSERT INTO `community_up` VALUES ('140', '17', '54', '1');
INSERT INTO `community_up` VALUES ('141', '17', '13', '1');
INSERT INTO `community_up` VALUES ('142', '17', '38', '1');
INSERT INTO `community_up` VALUES ('143', '17', '49', '0');
INSERT INTO `community_up` VALUES ('144', '18', '1', '0');
INSERT INTO `community_up` VALUES ('145', '18', '9', '0');
INSERT INTO `community_up` VALUES ('146', '18', '5', '0');
INSERT INTO `community_up` VALUES ('147', '18', '10', '0');
INSERT INTO `community_up` VALUES ('148', '18', '11', '0');
INSERT INTO `community_up` VALUES ('149', '18', '13', '0');
INSERT INTO `community_up` VALUES ('150', '18', '12', '1');
INSERT INTO `community_up` VALUES ('151', '18', '61', '0');
INSERT INTO `community_up` VALUES ('152', '18', '60', '1');
INSERT INTO `community_up` VALUES ('153', '18', '54', '0');
INSERT INTO `community_up` VALUES ('154', '18', '49', '1');
INSERT INTO `community_up` VALUES ('155', '18', '37', '0');
INSERT INTO `community_up` VALUES ('156', '18', '76', '1');

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1男0女）',
  `phone_num` char(15) DEFAULT NULL COMMENT '电话',
  `email` char(30) DEFAULT NULL COMMENT '邮箱',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) DEFAULT NULL COMMENT '签名',
  `location` varchar(255) DEFAULT NULL COMMENT '地区',
  `userpic` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '用户创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='前端用户表';

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES ('1', '333', '333', '1', '13103232333', '1233210123@qq.com', '2003-03-03 00:00:00', '33333', '冰岛', '/img/consumerPic/1615271158479p.jpg', '2021-02-25 23:30:14', '2021-02-25 23:30:14');
INSERT INTO `consumer` VALUES ('2', '尔听°', '111', '0', '13123331333', '暂无电子邮箱', '2000-06-07 00:00:00', '这个人很懒，暂无介绍', '火星', '/img/consumerPic/1616074524460300(4).jpg', '2020-12-20 15:20:54', '2020-12-20 15:20:54');
INSERT INTO `consumer` VALUES ('6', '22', '22', '0', '13223331222', '暂无电子邮箱', '2000-02-02 00:00:00', '这个人很懒，暂无介绍', '火星', '/img/consumerPic/consumerPic.png', '2020-12-20 17:27:29', '2020-12-20 17:27:29');
INSERT INTO `consumer` VALUES ('7', '2233', '2233', '0', '13422333322', '暂无电子邮箱', '1992-02-02 00:00:00', '这个人很懒，暂无介绍', '中国', '/img/consumerPic/consumerPic.png', '2020-12-20 17:32:13', '2020-12-20 17:32:13');
INSERT INTO `consumer` VALUES ('8', '44', '44', '1', '14434542444', '144044412@qq.com', '2020-12-01 00:00:00', '这个人很懒，暂无介绍', '中国', '/img/consumerPic/consumerPic.png', '2020-12-20 17:33:23', '2020-12-20 17:33:23');
INSERT INTO `consumer` VALUES ('17', 'a', 'a', '0', '13450879046', '', '1995-03-15 00:00:00', '这个人很懒，暂无介绍', '福建省', '/img/consumerPic/1616075027102juzi.jpg', '2021-03-18 21:45:56', '2021-03-18 21:45:56');
INSERT INTO `consumer` VALUES ('18', 'b', '123', '1', '', '', '2014-06-10 00:00:00', '', '江苏省', '/img/consumerPic/consumerPic.png', '2021-02-13 16:05:56', '2021-02-13 16:05:56');
INSERT INTO `consumer` VALUES ('19', 'c', 'c', '0', '', '', '2021-03-10 00:00:00', 'cccc', '省', '/img/consumerPic/16171082257510069lkcAly1fjt9qi3vn8j305x05xmy2.jpg', '2021-03-30 20:41:20', '2021-03-30 20:41:20');

-- ----------------------------
-- Table structure for list_song
-- ----------------------------
DROP TABLE IF EXISTS `list_song`;
CREATE TABLE `list_song` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `song_id` int(11) DEFAULT NULL COMMENT '歌曲id',
  `songlist_id` int(11) DEFAULT NULL COMMENT '歌单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='歌单包含歌曲列表';

-- ----------------------------
-- Records of list_song
-- ----------------------------
INSERT INTO `list_song` VALUES ('1', '21', '1');
INSERT INTO `list_song` VALUES ('2', '20', '3');
INSERT INTO `list_song` VALUES ('3', '15', '3');
INSERT INTO `list_song` VALUES ('11', '27', '5');
INSERT INTO `list_song` VALUES ('12', '28', '5');
INSERT INTO `list_song` VALUES ('13', '33', '7');
INSERT INTO `list_song` VALUES ('14', '32', '7');
INSERT INTO `list_song` VALUES ('15', '34', '7');
INSERT INTO `list_song` VALUES ('16', '36', '6');
INSERT INTO `list_song` VALUES ('17', '4', '6');
INSERT INTO `list_song` VALUES ('18', '35', '6');
INSERT INTO `list_song` VALUES ('19', '27', '8');
INSERT INTO `list_song` VALUES ('20', '25', '8');
INSERT INTO `list_song` VALUES ('21', '24', '8');
INSERT INTO `list_song` VALUES ('22', '37', '9');
INSERT INTO `list_song` VALUES ('23', '38', '10');
INSERT INTO `list_song` VALUES ('24', '39', '11');
INSERT INTO `list_song` VALUES ('25', '21', '12');

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `songlist_id` int(11) DEFAULT NULL COMMENT '歌单id',
  `consumer_id` int(11) DEFAULT NULL COMMENT '用户id',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `consumer_id` (`songlist_id`,`consumer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='评价';

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES ('1', '6', '18', '7');
INSERT INTO `rank` VALUES ('2', '6', '17', '10');
INSERT INTO `rank` VALUES ('3', '1', '1', '5');

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1男0女）',
  `pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `singer_birth` datetime DEFAULT NULL COMMENT '生日',
  `singer_location` varchar(255) DEFAULT NULL COMMENT '所属地区',
  `singer_intro` text COMMENT '歌手简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='歌手';

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES ('1', '杨丞琳', '0', '/img/singerPic/1608194302542ycl.jpg', '1984-06-04 00:00:00', '中国台湾', '杨丞琳（Rainie Yang），1984年6月4日出生于台湾省台北市，中国台湾女歌手、演员、导演、主持人，是一位多栖发展的艺人。');
INSERT INTO `singer` VALUES ('2', '周杰伦', '1', '/img/singerPic/1608194312907zjl.jpg', '1979-01-18 00:00:00', '中国台湾', '周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，祖籍福建省泉州市永春县，中国台湾流行乐男歌手、原创音乐人、演员、导演、编剧，毕业于淡江中学。');
INSERT INTO `singer` VALUES ('3', '张国荣', '1', '/img/singerPic/1608617522738zgr1.jpg', '1956-09-12 00:00:00', '中国香港', '张国荣是一位在全球华人社会和亚洲地区具有影响力的著名歌手、演员和音乐人；大中华区乐坛和影坛巨星；演艺圈多栖发展最成功的代表之一。张国荣是香港乐坛的殿堂级歌手之一，曾获得香港乐坛最高荣誉金针奖；他是第一位享誉韩国乐坛的华人歌手，亦是华语唱片在韩国销量纪录保持者。香港演艺圈的艺人都喊他为哥哥。2003年4月1日，张国荣因抑郁症病情失控跳楼自杀身亡。');
INSERT INTO `singer` VALUES ('4', '杨千嬅', '0', '/img/singerPic/1608194320965T001R150x150M000000xogLP35ayzS.jpg', '1974-02-03 00:00:00', '中国香港', '杨千嬅，原名杨泽嬅，1974年2月3日出生于中国香港，祖籍广东汕头， [1-2]  中国香港流行乐女歌手、影视演员，毕业于香港理工大学。');
INSERT INTO `singer` VALUES ('5', 'TWICE', '2', '/img/singerPic/1607435043234twice.jpg', '2015-10-20 00:00:00', '韩国', 'TWICE(트와이스)，为韩国JYP娱乐旗下的九人女子组合，成员透过Mnet生存实境节目《SIXTEEN》筛选而出。最终由五名韩国成员娜琏、定延、志效、多贤、彩瑛，三名日本成员Momo、Sana、Mina，以及一名中国成员子瑜所组成，并由志效担任队长。');
INSERT INTO `singer` VALUES ('6', '五月天', '2', '/img/singerPic/1607435448057wyt.jpg', '1997-03-29 00:00:00', '中国台湾', '五月天，台湾摇滚乐团，由乐队团长兼吉他手怪兽（温尚翊）、主唱阿信（陈信宏）、吉他手石头（石锦航）、贝斯玛莎（蔡升晏）和鼓手冠佑（刘谚明）组成，前身为“So Band”乐团，在1997年3月29日更名为“五月天”。至2016年，四次拿下\"台湾金曲奖\"和“最佳乐团奖”。');
INSERT INTO `singer` VALUES ('7', '林宥嘉', '1', '/img/singerPic/1608303211906lyj.jpg', '1987-07-02 00:00:00', '中国台湾', '林宥嘉（英文名：Yoga Lin，1987年7月1日－）是中国台湾著名男歌手。出生于台湾屏东县，是家中长子，有一弟一妹，家里经营建材生意。林宥嘉考上凤山高中后搬到高雄居住，高中毕业后就读于国立东华大学运动与休闲学系。2007年7月6日以20岁大学生的身份，获得台湾中视主办的歌唱选秀节目第一届《超级星光大道》冠军，并与华研国际音乐签约成为歌手。演唱歌曲有《你把我灌醉》《悬崖》《我爱的人》《你是我的眼》等。曾获第十届CCTV-MTV音乐盛典港澳台地区年度最受欢迎潜力歌手奖。林宥嘉拥有独特的声线和极佳的唱功，人称“迷幻唱腔”，“迷幻王子”。代表作包括《说谎》、《伯乐》、《我总是一个人在练习一个人》、《诱》、《自然醒》、《想自由》、《心酸》、《残酷月光》等。2017年5月16日入围金曲奖最佳男歌手奖。');
INSERT INTO `singer` VALUES ('17', 'Jony J', '1', '/img/singerPic/1608616888116109951162932971050.jpg', '1989-12-28 00:00:00', '中国内地', 'Jony J，原名肖佳，1989年出生于福建省，中国内地嘻哈饶舌男歌手，来自SHOOC.Studio。');
INSERT INTO `singer` VALUES ('18', '张震岳', '1', '/img/singerPic/1608307071970zzy.jpg', '1974-05-02 00:00:00', '中国台湾', '张震岳（Chenyue Chang），1974年5月2日出生于台湾省宜兰县苏澳镇，中国台湾男歌手。\n1993年12月，推出个人首张专辑《就是喜欢你》。1994年6月，推出个人第二张专辑《花开了没有》。1999年，主演剧情片《超级公民》。2001年，凭借专辑《有问题》获得第1届华语音乐传媒奖十大华语唱片奖。2004年10月，举行个人北美洲巡回演唱会。2008年7月，张震岳与罗大佑、李宗盛、周华健组成了乐队纵贯线 ；同年，张震岳凭借歌曲《思念是一种病》获得第19届台湾金曲奖最佳作词人奖提名');
INSERT INTO `singer` VALUES ('19', '房东的猫', '2', '/img/singerPic/1608457850736T001R150x150M000000PeZCQ1i4XVs.jpg', '2013-02-05 00:00:00', '中国内地', '房东的猫，中国内地女子双人唱作二人组合，由吉他手吴佩岭和主唱王心怡组成。2013年，房东的猫正式成立。2015年，在网易云音乐发表第一首原创单曲《秋酿》获得了一定的关注度；同年，在网络上发布了翻唱宋冬野的歌曲《斑马斑马》。2017年，推出组合首张同名专辑《房东的猫》；同年，为动漫番剧《我是江小白》演唱片尾曲《云烟成雨》。');
INSERT INTO `singer` VALUES ('21', '毛华锋', '1', '/img/singerPic/1608615356213T001R300x300M000004Ff3Bj0f0vAC.jpg', '1980-07-26 00:00:00', '中国', '毛毛是一位歌手，毕业于上海舞蹈学校。他从小就开始练习舞蹈，他的音乐走的是欧美电子舞曲路线。来自上海的的帅气男孩儿毛毛，正是“新世纪偶像兵团”的第一批成员。');
INSERT INTO `singer` VALUES ('22', 'ボイジャー (voyager)', '2', '/img/singerPic/1608614095602T001R300x300M0000007sHCz2lllqK.jpg', '2009-07-01 00:00:00', '日本', 'Voyager（ボイジャー）是演唱《奥特曼》系列歌曲的2人音乐组合。\n\n从艺历程：2009年7月以《梦比优斯奥特曼》op实现出道。担任圆谷制作的作品主题曲/插曲的演唱，隶属圆谷制作株式会社，出席各大活动、Hero Show以及现场演唱会的音乐组合。');
INSERT INTO `singer` VALUES ('23', '陈奕迅', '1', '/img/singerPic/1608615936275T001R300x300M000003Nz2So3XXYek.jpg', '1974-07-27 00:00:00', '中国香港', '陈奕迅（Eason Chan），1974年7月27日出生于香港，中国香港男歌手、演员，毕业于英国金斯顿大学。');
INSERT INTO `singer` VALUES ('24', '小猪佩奇（Peppa Pig）', '3', '/img/singerPic/1608618370851T001R300x300M000002qmiDg3pQbUU.jpg', '2020-12-01 00:00:00', '美国', '小猪佩奇是一只粉红色的可爱小猪。她和猪爸爸猪妈妈，还有弟弟乔治生活在一座小山坡上。他们最喜欢做的事情就是跳泥坑。');
INSERT INTO `singer` VALUES ('25', '秦基博 (はたもとひろ)', '1', '/img/singerPic/1608618786048T001R300x300M0000022PHem4L6smh.jpg', '1980-10-11 00:00:00', '日本', '秦基博，1980年10月11日出生于日本宫崎县，日本男歌手。\n\n2006年3月，与唱片公司Office Augusta签约。11月8日，发行首张单曲《同步》创下全日本广播电台Power Play 42局的纪录。2008年10月8日，发行单曲《Forever song》，该单曲为秦基博首张进入公信榜前十的单曲。2009年3月6日，首次在日本武道馆举办演唱会。2010年10月6日，发行专辑《Documentary》，并凭借此专辑初次进入公信榜前三位。');
INSERT INTO `singer` VALUES ('26', '林俊杰', '1', '/img/singerPic/1613977855526T3.jpg', '1981-03-27 00:00:00', '新加坡', '林俊杰（JJ Lin），新加坡人，流行歌手、词曲创作者、音乐制作人。\n\n2003年发行首张创作专辑《乐行者》 ，2004年凭《江南》一曲成名 。2014年以专辑《因你而在 Stories Untold》夺得第25届台湾金曲奖最佳国语男歌手奖 。');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `singer_id` int(11) DEFAULT NULL COMMENT '歌手id',
  `song_name` varchar(255) DEFAULT NULL COMMENT '歌名',
  `song_intro` varchar(255) DEFAULT NULL COMMENT '歌曲简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `song_pic` varchar(255) DEFAULT NULL COMMENT '歌曲图片',
  `lyric` text COMMENT '歌词',
  `url` varchar(255) DEFAULT NULL COMMENT '歌曲地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='歌曲';

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('2', '2', '周杰伦-彩虹', '《我很忙》', '2020-12-15 15:57:34', '2020-12-15 16:41:16', '/img/songPic/1608188638058T002R300x300M000002eFUFm2XYZ7z_1.jpg', '[04:15.34][00:01.28]周杰伦 - 彩虹\n[04:16.11][00:02.24]作曲:周杰伦 作词:周杰伦\n[04:17.15][00:03.06]07年创作专辑《我很忙》第二波主打\n[04:19.22][00:11.20]\n[00:14.11]哪里有彩虹告诉我\n[00:19.98]能不能把我的愿望还给我\n[00:26.56]为什么天这么安静\n[00:32.70]所有云都跑到我这里\n[00:38.79]\n[00:39.11]\n[01:57.70][00:39.77]有没有口罩一个给我\n[02:03.38][00:45.53]释怀说了太多就成真不了\n[02:10.30][00:52.43]也许时间是一种解药\n[02:15.94][00:58.22]也是我现在正服下的毒药\n[02:22.06][01:04.33]\n[02:22.52][01:04.59]看不见你的笑 我怎么睡得着\n[02:29.02][01:11.07]你的身影这么近我却抱不到\n[02:35.58][01:17.60]没有地球太阳还是会绕\n[02:41.82][01:24.08]没有理由我也能自己走\n[02:47.99][01:30.28]\n[03:40.56][02:48.19][01:30.60]你要离开 我知道很简单\n[03:46.75][02:54.87][01:37.04]你说依赖 是我们的阻碍\n[03:53.28][03:01.49][01:43.66]就算放开 那能不能别没收我的爱\n[04:04.00][03:08.77][01:50.83]当作我最后才明白\n[03:14.38][01:55.97]\n[03:14.67]RAP\n[03:15.48]看不见你的笑\n[03:16.79]要我怎么睡得着\n[03:19.12]你的身影这么近我却抱不到\n[03:22.21]没有地球太阳开始环绕环绕\n[03:25.41]没有理由我也能自己走掉\n[03:28.65]是我说了太多就成真不了\n[03:31.84]也许时间是一种解药解药\n[03:35.19]也是我现在正服下的毒药\n[03:40.21]', '/song/1608186000559周杰伦 - 彩虹.ogg');
INSERT INTO `song` VALUES ('4', '3', '张国荣-春夏秋冬', '春夏秋冬', '2020-12-15 16:05:44', '2020-12-17 15:07:29', '/img/songPic/1608189078131T002R300x300M000000XJ9QF1AWOoW_1.jpg', '[00:00]张国荣：春夏秋冬\n[00:07]曲 : 叶 良 俊词 : 林 夕编 : Adrian Chan\n[00:09]\n[00:11]\n[00:14]秋天该很好 你若尚在场\n[00:20]秋风即使带凉 亦漂亮\n[00:28]深秋中的你填密我梦想\n[00:35]就像落叶飞 轻敲我窗\n[00:42]冬天该很好 你若尚在场\n[00:49]冬天多灰 我们亦放亮\n[00:56]一起坐坐谈谈来日动向\n[01:03]漠视外间低温 这样唱\n[02:27][01:09]能同途偶遇在这星球上\n[02:33][01:15]燃亮飘渺人生\n[02:37][01:19]我多么够运\n[02:41][01:23]无人如你逗留我思潮上\n[02:47][01:29]从没再疑问\n[02:50][01:33]这个世界好得很\n[01:39]暑天该很好 你若尚在场\n[01:45]火一般的太阳在脸上\n[01:52]烧得肌肤如情 痕极又痒\n[01:59]滴着汗的一双 笑着唱\n[02:24][02:15][02:05]\n[02:55]能同途偶遇在这星球上\n[03:01]是某种缘份\n[03:04]我多么庆幸\n[03:09]如离别 你亦长处心灵上\n[03:15]宁愿有遗憾\n[03:18]亦愿和你远亦近\n[03:25]春天该很好 你若尚 在场\n[03:31]春风仿佛爱情\n[03:35]在蕴酝\n[03:39]初春中的你 撩动我幻想\n[03:46]就像嫩绿草使\n[03:51]春雨香', '/song/1608189088208张国荣 - 春夏秋冬.mp3');
INSERT INTO `song` VALUES ('12', '2', '周杰伦-晴天', '叶惠美', '2020-12-16 23:32:40', '2020-12-17 14:59:03', '/img/songPic/1608188488542T002R90x90M000000MkMni19ClKG.jpg', '[00:00.82]晴天\n[00:05.82]演唱：周杰伦\n[00:14.82]\n[00:29.27]故事的小黄花\n[00:32.30]从出生那年就飘着\n[00:35.83]童年的荡秋千\n[00:39.26]随记忆一直晃到现在\n[00:42.84]rui sou sou xi dou xi la\n[00:45.72]sou la xi xi xi xi la xi la sou\n[00:49.60]吹着前奏望着天空\n[00:52.92]我想起花瓣试着掉落\n[00:56.25]为你翘课的那一天\n[00:58.27]花落的那一天\n[00:59.79]教室的那一间\n[01:01.60]我怎么看不见\n[01:03.67]消失的下雨天\n[01:05.28]我好想再淋一遍\n[01:10.07]没想到失去的勇气我还留着\n[01:15.27]好想再问一遍\n[01:17.49]你会等待还是离开\n[01:24.34]刮风这天我试过握着你手\n[01:30.04]但偏偏雨渐渐大到我看你不见\n[01:38.51]还要多久我才能在你身边\n[01:44.92]等到放晴的那天也许我会比较好一点\n[01:52.43]从前从前有个人爱你很久\n[01:58.07]但偏偏风渐渐把距离吹得好远\n[02:06.40]好不容易又能再多爱一天\n[02:13.01]但故事的最后你好像还是说了拜拜\n[02:34.44]为你翘课的那一天\n[02:36.26]花落的那一天\n[02:38.02]教室的那一间\n[02:39.89]我怎么看不见\n[02:41.75]消失的下雨天\n[02:43.52]我好想再淋一遍\n[02:47.76]没想到失去的勇气我还留着\n[02:54.21]好想再问一遍\n[02:55.77]你会等待还是离开\n[03:02.48]刮风这天我试过握着你手\n[03:08.07]但偏偏雨渐渐大到我看你不见\n[03:16.55]还要多久我才能在你身边\n[03:22.95]等到放晴的那天也许我会比较好一点\n[03:30.42]从前从前有个人爱你很久\n[03:36.72]偏偏风渐渐把距离吹得好远\n[03:44.84]好不容易又能再多爱一天\n[03:51.04]但故事的最后你好像还是说了拜拜\n[03:59.00]刮风这天我试过握着你手\n[04:01.47]但偏偏雨渐渐大到我看你不见\n[04:05.06]还要多久我才能够在你身边\n[04:08.49]等到放晴那天也许我会比较好一点\n[04:12.62]从前从前有个人爱你很久\n[04:15.49]但偏偏雨渐渐把距离吹得好远\n[04:18.81]好不容易又能再多爱一天\n[04:22.60]但故事的最后你好像还是说了拜拜', '/song/1608188744861周杰伦 - 晴天.ogg');
INSERT INTO `song` VALUES ('13', '1', '杨丞琳-匿名的好友', 'Rainie & Love…? 雨爱', '2020-12-17 16:51:39', '2021-01-24 16:33:45', '/img/songPic/1608195441502T002R300x300M000000jcKFG0sQrD0_1.jpg', '[00:00.14]杨丞琳 - 匿名的好友\n[00:07.25]\n[00:09.05]作词：李焯雄 作曲：陈颖见\n[00:20.38]杜松混合茉莉的风\n[00:24.50]回忆里被爱 那股激动\n[00:29.23]天色好红 温柔好浓\n[00:32.77]在胸口浮现你的脸容\n[00:35.79]\n[00:36.45]一起活在这城市迷宫\n[00:40.48]提起你名字 心还跳动 却没重逢\n[00:47.16]只有想碰却又不敢碰的那种悸动\n[00:51.69]\n[00:52.32]也许我们当时年纪真的太小\n[00:56.43]从那懵懵懂懂 走进各自天空\n[01:00.48]该怎么说让彼此选择 但思念还转动\n[02:31.09][01:07.85]\n[02:32.63][01:10.18]不能握的手 从此匿名的朋友\n[02:40.26][01:17.85]其实我的执着依然执着\n[02:44.68][01:22.41]与你无关泪自行吸收\n[02:48.69][01:26.40]不能握的手 却比亲人更亲厚\n[02:55.79][01:33.42]但所有如果都没有如果\n[02:59.99][01:37.89]只有失去的温柔 最温柔\n[01:43.98]\n[01:58.50]当又一次美梦落空\n[02:02.73]回忆里被爱 那股激动\n[02:07.45]天色好红 温柔好浓\n[02:11.00]在胸口浮现你的脸容\n[02:13.94]\n[02:14.59]也许我们当时年纪真的太小\n[02:18.72]从那懵懵懂懂 走进各自天空\n[02:22.60]那是什么 让彼此选择 又不仅是尊重\n[03:07.03]\n[03:20.52]不能握的手 从此匿名的朋友\n[03:28.22]其实我的执着 依然执着\n[03:32.64]却决心和你不再联络\n[03:36.77]不能握的手 却比爱人更长久\n[03:45.56]当所有如果都没有如果\n[03:49.97]只有失去的拥有 最永久\n[03:59.75]', '/song/1608195099240杨丞琳 - 匿名的好友.mp3');
INSERT INTO `song` VALUES ('14', '1', '杨丞琳-暧昧', '暧昧', '2020-12-17 16:55:20', '2020-12-17 16:56:11', '/img/songPic/1608195449922T002R300x300M000003D17OJ0mh5uA_1.jpg', '[00:00.00]杨丞琳－暧昧\n[00:33.02]\n[00:47.65]只能陪你到这里\n[00:53.83]毕竟有些事不可以\n[00:58.80]超过了友情\n[01:01.73]还不到爱情\n[01:04.78]远方就要下雨的风景\n[01:10.68]到底该不该哭泣\n[01:16.26]想太多是我还是你\n[01:21.62]我很不服气\n[01:24.55]也开始怀疑\n[01:27.38]眼前的人\n[01:29.54]是不是同一个 真实的你\n[02:46.04][01:37.61][00:05.68]暧昧让人受尽委屈\n[02:51.81][01:43.36][00:10.95]找不到相爱的证据\n[02:56.72][01:48.48][00:16.18]何时该前进\n[02:59.55][01:51.25][00:18.93]何时该放弃\n[03:03.09][01:54.82][00:22.39]连拥抱都没有勇气\n[03:09.07][02:00.80]暧昧让人变得贪心\n[03:14.69][02:06.17]直到等待失去意义\n[03:19.50][02:11.29]无奈我和你写不出结局\n[03:25.91][02:17.57]放遗憾的美丽\n[03:29.79][02:21.58]停在这里', '/song/1608195320307杨丞琳 - 暧昧.mp3');
INSERT INTO `song` VALUES ('15', '5', 'TWICE-I CAN\'T STOP ME', '《Eyes Wide Open》', '2020-12-17 17:01:22', '2020-12-17 17:02:26', '/img/songPic/1608195891754109951165416882781.jpg', '[00:00.000] 作词 : 朴振荣/심은지\n[00:01.000] 作曲 : Melanie Joy Fontana/Michel \"Lindgren\" Schulz/A Wright\n[00:12.679]알람이 울려대 Ring ring a ling\n[00:15.854]서로의 눈길이 닿을 때마다\n[00:18.971]알면서 빙빙 도는데\n[00:21.817]점점 다가가잖아 I know it’s too late\n[00:25.854]마음속으로는 다 알고 있잖아\n[00:29.219]결국에는 선을 넘게 될 거라는 걸\n[00:31.960]I’m warning to myself\n[00:33.543]너 그러면 안 돼\n[00:34.865]매분, 매초, 내 맘이 내 맘을 추월해\n[00:38.000]Out of control\n[00:39.285]나를 감시하는 저 Spot spot spotlight\n[00:42.188]비출수록 어둠 속으로 빨려 들어\n[00:45.261]끝이 보이는데 I know it’s not right\n[00:48.791]I can’t stop me, can’t stop me\n[00:51.222]내 앞에 놓여진 이 Red red red line\n[00:55.094]건너편의 너와 난 이미 눈을 맞춰\n[00:58.405]느끼고 싶어 짜릿한 Highlight\n[01:01.670]I can’t stop me, can’t stop me\n[01:15.796]I can’t stop me, can’t stop me\n[01:17.548]눈감아 달라구 Ya ya\n[01:19.719]딱 한 번만 No rules ah ah\n[01:22.816]모른 척해 줘 Lights off tonight\n[01:25.973]나 참을 수 없을 거 같아 Losing myself\n[01:30.623]이제는 Turning back\n[01:33.092]불가능해 난 점점 더 깊은 어둠에\n[01:35.609]너무나 짜릿해\n[01:37.595]나 눈을 감을래\n[01:38.720]다시는 돌아갈 수 없을 것만 같애\n[01:41.657]Out of control\n[01:43.227]나를 감시하는 저 Spot spot spotlight\n[01:45.932]비출수록 어둠 속으로 빨려 들어\n[01:49.348]끝이 보이는데 I know it’s not right\n[01:52.704]I can’t stop me, can’t stop me\n[01:54.847]내 앞에 놓여진 이 Red red red line\n[01:59.032]건너편의 너와 난 이미 눈을 맞춰\n[02:02.248]느끼고 싶어 짜릿한 Highlight\n[02:05.305]I can’t stop me, can’t stop me\n[02:07.993]Risky risky wiggy 위기\n[02:10.229]This is an emergency\n[02:11.672]Help me, help me, somebody stop me\n[02:13.783]Cuz I know I can’t stop me\n[02:14.959]답은 알고 있잖아\n[02:17.108]근데 가고 있잖아\n[02:18.485]이러고 싶지 않아\n[02:20.197]내 안에 내가 또 있나 봐\n[02:21.641]나는 원하는데\n[02:24.384]원하는 게 안돼\n[02:27.201]Guilty 난 싫은데\n[02:30.585]I can’t stop me, can’t stop me, can’t stop me\n[02:33.289]나를 감시하는 저 Spot spot spotlight\n[02:37.309]비출수록 어둠 속으로 빨려 들어\n[02:40.361]끝이 보이는데 I know it’s not right\n[02:43.904]I can’t stop me, can’t stop me\n[02:45.933]내 앞에 놓여진 이 Red red red line\n[02:50.021]건너편의 너와 난 이미 눈을 맞춰\n[02:52.936]느끼고 싶어 짜릿한 Highlight\n[02:56.587]I can’t stop me, can’t stop me\n[03:10.385]I can’t stop me, can’t stop me', '/song/1608195682278TWICE (트와이스) - I CAN\'T STOP ME.mp3');
INSERT INTO `song` VALUES ('16', '4', '杨千嬅-少女的祈祷', 'Play It Loud, Kiss Me Soft', '2020-12-17 17:14:44', '2020-12-17 17:15:23', '/img/songPic/1608219468589T002R300x300M000004HMlvp4QOaun_1.jpg', '[00:00.000] 作词 : 林夕\n[00:01.000] 作曲 : 陈辉阳\n[00:02.439]编曲 : 陈辉阳\n[00:04.264]\n[00:07.908]沿途与他车厢中私奔般恋爱\n[00:12.194]再挤逼都不放开\n[00:16.141]祈求在路上没任何的阻碍\n[00:20.366]令愉快旅程变悲哀\n[00:24.870]连气两次绿灯都过渡了\n[00:28.237]与他再爱几公里\n[00:32.842]当这盏灯转红便会别离\n[00:36.251]凭运气决定我生死\n[00:39.774]\n[00:40.164]祈求天地放过一双恋人\n[00:43.459]怕发生的永远别发生\n[00:47.930]从来未顺利遇上好景降临\n[00:52.174]如何能重拾信心\n[00:55.608]祈求天父做十分钟好人\n[00:59.105]赐我他的吻 如怜憫罪人\n[01:03.722]我爱主 同时亦爱一位世人\n[01:07.864]祈求沿途未变心 请给我护荫\n[01:14.655]\n[01:27.284]为了他 不懂祷告都敢祷告\n[01:31.200]谁愿眷顾这种信徒\n[01:35.078]用两手遮掩双眼专心倾诉\n[01:39.007]宁愿答案 望不到\n[01:45.905]唯求与他车厢中可抵达未来\n[01:50.259]到车毁都不放开\n[01:54.029]无论路上历尽任何的伤害\n[01:58.332]任由我决定爱不爱\n[02:01.884]\n[02:02.154]祈求天地放过一双恋人\n[02:05.383]怕发生的永远别发生\n[02:09.795]从来未顺利遇上好景降临\n[02:14.068]如何能重拾信心\n[02:17.625]祈求天父做十分钟好人\n[02:21.157]赐我他的吻 如怜憫罪人\n[02:25.460]我爱主 同时亦爱一位爱人\n[02:29.771]祈求沿途未变心 请给我护荫\n[02:37.256]\n[02:37.492]为了他 不懂祷告都敢祷告\n[02:41.304]谁愿眷顾这种信徒\n[02:45.166]太爱他怎么想到这么恐怖\n[02:49.070]对绿灯 去哀求哭诉\n[02:55.857]\n[02:56.460]然而天父并未体恤好人\n[02:59.701]到我睁开眼 无明灯指引\n[03:04.115]我爱主 为何任我身边爱人\n[03:08.418]离弃了我下了车 你怎可答允\n[03:16.112]\n[03:26.315]监制 : 陈辉阳\n[03:28.239]OP : EMI Music Publishing Hong Kong. / Capital Artists Ltd.', '/song/1608196484487杨千嬅 - 少女的祈祷.ogg');
INSERT INTO `song` VALUES ('17', '4', '杨千嬅-可惜我是水瓶座', 'Miriam\'s Music Box', '2020-12-17 17:17:49', '2020-12-17 17:17:49', '/img/songPic/1608219480128T002R300x300M000003uxp5l1gXYDr_1.jpg', '[00:00.000] 作词 : 黄伟文\n[00:00.045] 作曲 : 雷颂德\n[00:00.91]编曲：Ted Lo/雷颂德\n[00:11.91]原来你这样珍惜我\n[00:17.51]从前在热恋中都未听讲过\n[00:22.86]别说这种行货 哪里留得住我\n[00:28.88]到底是为什么分手你很清楚\n[00:34.48]如何笨到底 但到底还是我\n[00:39.89]谁人待我好 待我差 太清楚\n[00:45.80]想继续装傻 却又无力受折磨\n[00:51.40]心里羡慕那些人 盲目到不计后果\n[00:57.81]我就回去 别引出我泪水\n[01:03.51]尤其明知水瓶座最爱是流泪\n[01:08.47]若然道别是下一句 可以闭上了你的嘴\n[01:14.12]无谓再会要是再会更加心碎\n[01:20.57]要是回去没有止痛药水\n[01:26.28]拿来长岛冰茶换我半晚安睡\n[01:31.18]十年后或现在失去 反正到最尾也唏嘘\n[01:36.89]够绝情我都赶我自己出去\n[01:53.98]犹如最结实的堡垒\n[01:59.49]原来在逐点崩溃逐点粉碎\n[02:04.85]极固执的如我 也会捱不下去\n[02:10.82]每天扮着幸福始终有些心虚\n[02:16.38]如何笨到底 但到底还是我\n[02:21.98]谁人待我好 待我差 太清楚\n[02:27.70]想继续装傻 却又无力受折磨\n[02:33.31]心里羡慕有些人 盲目到不计后果\n[02:39.76]我就回去 别引出我泪水\n[02:45.31]尤其明知水瓶座最爱是流泪\n[02:50.21]若然道别是下一句 可以闭上了你的嘴\n[02:55.87]无谓再会要是再会更加心碎\n[03:02.38]要是回去没有止痛药水\n[03:07.93]拿来长岛冰茶换我半晚安睡\n[03:12.73]十年后或现在失去 反正到最尾也唏嘘\n[03:18.49]够绝情我都赶我自己出去', '/song/1608196669447杨千嬅 - 可惜我是水瓶座.mp3');
INSERT INTO `song` VALUES ('18', '5', 'TWICE-What is Love?', 'What is Love?', '2020-12-17 17:19:55', '2020-12-17 17:49:08', '/img/songPic/1608198722256T002R300x300M000001Ktl7Z1YoITB_1.jpg', '[00:00.000] 作词 : 朴振荣\n[00:01.000] 作曲 : 朴振荣\n[00:02.000] 编曲 : 이우민\n[00:11.57]매일같이 영화 속에서나\n[00:14.07]책 속에서나 드라마 속에서 사랑을 느껴\n[00:18.05]Um- 사랑을 배워\n[00:22.97]내 일처럼 자꾸 가슴이 뛰어\n[00:25.43]두근두근거려 설레임에 부풀어 올라\n[00:29.87]Um- 궁금해서 미칠 것만 같아\n[00:34.25]Ooh 언젠간 내게도\n[00:36.80]이런 일이 실제로 일어날까\n[00:39.54]그게 언제쯤일까?\n[00:42.74]어떤 사람일까?\n[00:44.99]I wanna know 사탕처럼 달콤하다는데\n[00:47.87]I wanna know 하늘을 나는 것 같다는데\n[00:50.63]I wanna know know know know\n[00:52.63]What is love?\n[00:53.51]사랑이 어떤 느낌인지\n[00:56.26]I wanna know 하루 종일 웃고 있다는데\n[00:59.04]I wanna know 세상이 다 아름답다는데\n[01:02.00]I wanna know know know know\n[01:03.72]What is love?\n[01:04.81]언젠간 나에게도 사랑이 올까\n[01:08.58]지금 이런 상상만으로도\n[01:10.48]떠올려만 봐도 가슴이 터질 것 같은데\n[01:14.98]Um- 이렇게 좋은데\n[01:17.87]만일 언젠가 진짜로 내게\n[01:21.71]사랑이 올 때 난 울어버릴지도 몰라\n[01:26.43]Um- 정말 궁금해 미칠 것만 같아\n[01:30.83]Ooh 언젠간 내게도\n[01:33.14]이런 일이 실제로 일어날까\n[01:36.03]그게 언제쯤일까?\n[01:39.12]어떤 사람일까?\n[01:41.37]I wanna know 사탕처럼 달콤하다는데\n[01:44.33]I wanna know 하늘을 나는 것 같다는데\n[01:47.13]I wanna know know know know\n[01:49.05]What is love?\n[01:50.05]사랑이 어떤 느낌인지\n[01:52.69]I wanna know 하루 종일 웃고 있다는데\n[01:55.42]I wanna know 세상이 다 아름답다는데\n[01:58.27]I wanna know know know know\n[02:00.38]What is love?\n[02:01.38]언젠간 나에게도 사랑이 올까\n[02:04.67]지금 세상 어느 곳에 살고 있는지\n[02:07.04]도대체 언제쯤 나와 만나게 될는지\n[02:09.83]언제 어떻게 우리의 인연은\n[02:12.54]시작될는지 모르지만 느낌이 어쩐지\n[02:15.55]진짜 좋을 것 같아 왠지\n[02:18.38]영화 드라마보다도 더 멋진\n[02:21.13]사랑이 올 거야 내 예감 언제나 맞지\n[02:23.85]어서 나타나봐\n[02:25.06]나는 다 준비가 됐지 Ready!\n[02:27.25](어디 있을까) 찾아낼 거야\n[02:30.01](어디 있을까) 보고 싶어 죽겠어\n[02:33.74]더 이상 참을 수 없을 것만 같아\n[02:37.70]사탕처럼 달콤하다는데\n[02:40.61]하늘을 나는 것 같다는데\n[02:43.69]I wanna know know know know\n[02:45.50]What is love? 사랑이 어떤 느낌인지\n[02:49.21]하루 종일 웃고 있다는데\n[02:51.88]세상이 다 아름답다는데\n[02:54.98]I wanna know know know know\n[02:56.69]What is love? 언젠간 나에게도 사랑이 올까\n[03:00.61]I wanna know\n[03:03.19]I wanna know\n[03:06.09]I wanna know know know know\n[03:08.10]What is love?\n[03:09.61]I wanna know I wanna know\n[03:11.77]I wanna know\n[03:14.49]I wanna know\n[03:17.48]I wanna know know know know\n[03:19.48]What is love?\n[03:20.61]I wanna know', '/song/1608198567518TWICE (트와이스) - What is Love_.mp3');
INSERT INTO `song` VALUES ('19', '5', 'TWICE-YES or YES', 'YES or YES', '2020-12-17 17:46:37', '2020-12-17 17:50:58', '/img/songPic/1608198732940T002R300x300M000004aQ3QB1gPs9n_1.jpg', '[00:00.000] 作词 : 심은지\n[00:00.220] 作曲 : David Amber/Andy Love\n[00:00.440] 编曲 : David Amber\n[00:00.660]Hey boy\n[00:02.110]Look I’m gonna make\n[00:04.300]this simple for you\n[00:05.470]you got two choices\n[00:08.190]YES or YES\n[00:17.600]Ah 둘 중에 하나만 골라\n[00:20.790]YES or YES\n[00:22.100]Ah ah 하나만 선택해 어서\n[00:27.340]YES or YES\n[00:29.130]내가 이렇게도 이기적이었던가\n[00:33.100]뭔가 이렇게 갖고 싶던 적 있었나\n[00:36.520]다 놀라 내 뻔뻔함에\n[00:41.230]Come on and tell me yes\n[00:43.440]생각보다 과감해진 나의 시나리오\n[00:47.400]이 정도 Plan이면 완벽해 만족해\n[00:50.660]I don’t care 누가 뭐래도\n[00:56.500]You better tell me yes\n[00:57.270]내 맘은 정했어 YES\n[00:59.580]그럼 이제 네 대답을 들을 차례\n[01:03.100]힘들면 보기를 줄게 넌 고르기만 해\n[01:07.430]고민할 필요도 없게 해줄게\n[01:10.810]뭘 고를지 몰라 준비해봤어\n[01:14.550]둘 중에 하나만 골라 YES or YES\n[01:17.640]네 마음을 몰라 준비해봤어\n[01:21.640]하나만 선택해 어서 YES or YES\n[01:24.960]싫어는 싫어 나 아니면 우리\n[01:28.500]선택을 존중해 거절은 거절해\n[01:31.490]선택지는 하나 자 선택은 네 맘\n[01:35.570]It’s all up to you\n[01:39.320]둘 중에 하나만 골라 YES or YES\n[01:42.390]진심일까 Do not guess\n[01:44.310]진심이니 Do not ask\n[01:45.820]애매한 좌우 말고 확실히 위아래로\n[01:49.520]There’s no letters N & O\n[01:50.890]지워버릴래 오늘부로\n[01:53.170]복잡하게 고민할 필요\n[01:55.100]없어 정답은 YES YES YO\n[01:56.890]없던 이기심도 자극하는\n[01:59.710]너의 눈과\n[02:01.580]널 향한 호기심이 만나서\n[02:03.220]타올라 타오른다\n[02:06.160]My heart burn burn burn\n[02:09.180]조금 쉽게 말하자면\n[02:12.840]넌 뭘 골라도 날 만나게 될 거야\n[02:16.250]뭐 좀 황당하긴 해도\n[02:18.760]억지라고 해도\n[02:20.400]절대 후회하지 않게 해줄게\n[02:23.280]뭘 고를지 몰라 준비해봤어\n[02:27.690]둘 중에 하나만 골라 YES or YES\n[02:30.700]네 마음을 몰라 준비해봤어\n[02:34.590]하나만 선택해 어서 YES or YES\n[02:37.550]싫어는 싫어 나 아니면 우리\n[02:40.960]선택을 존중해 거절은 거절해\n[02:44.620]선택지는 하나 자 선택은 네 맘\n[02:48.750]Now it’s all up to you\n[02:52.580]Maybe not\n[02:55.150]No No\n[02:55.740]Maybe yes\n[02:56.750]No No\n[02:57.330]좀 더 선명하게 네 맘을 내게 보여봐\n[03:02.690]귀 기울여봐\n[03:03.930]무슨 소리가 들리지 않니\n[03:07.980]It\'s Simple Y E S Hey\n[03:11.460]둘 중에 하나만 골라 YES or YES\n[03:14.210]하나만 선택해 어서 YES or YES\n[03:17.680]하나 더 보태서 YES or YES or YES\n[03:21.150]골라봐 자 선택은 네 맘\n[03:24.410]뭘 고를지 몰라 준비해봤어\n[03:28.510]둘 중에 하나만 골라 YES or YES\n[03:31.540]네 마음을 몰라 준비해봤어\n[03:35.430]하나만 선택해 어서 YES or YES\n[03:38.430]싫어는 싫어 나 아니면 우리\n[03:41.740]선택을 존중해 거절은 거절해\n[03:45.440]선택지는 하나 자 선택은 네 맘\n[03:49.340]It’s all up to you\n[03:52.850]하나만 선택해 어서 YES or YES', '/song/1608198397400TWICE (트와이스) - YES or YES.mp3');
INSERT INTO `song` VALUES ('20', '5', 'TWICE-Cheer Up', 'PAGE TWO', '2020-12-17 17:48:06', '2020-12-17 17:52:39', '/img/songPic/1608198779173T002R300x300M0000022XOM20KO6Qj_1.jpg', '[00:00.000] 作词 : Sam Lewis\n[00:01.000] 作曲 : Black Eyed必胜\n[00:02.000] 编曲 : RADO\n[00:05.050]编曲 : Rado\n[00:10.050]매일 울리는 벨벨벨\n[00:11.940]이젠 나를 배려 해줘\n[00:13.800]배터리 낭비하긴 싫어\n[00:16.250]자꾸만 봐 자꾸 자꾸만 와\n[00:18.970]전화가 펑 터질 것만 같아\n[00:21.800]몰라 몰라 숨도 못 쉰대\n[00:24.250]나 때문에 힘들어\n[00:25.810]쿵 심장이 떨어진대 왜\n[00:28.230]걔 말은 나 너무 예쁘대\n[00:30.480]자랑 하는건 아니구\n[00:33.020]아 아까는 못 받아서 미안해\n[00:36.570]친구를 만나느라 shy shy shy\n[00:39.320]만나긴 좀 그렇구 미안해\n[00:42.140]좀 있다 연락할게 later\n[00:44.070]조르지마 얼마 가지 않아\n[00:46.770]부르게 해줄게 Baby\n[00:49.490]아직은 좀 일러 내 맘 같긴 일러\n[00:52.300]하지만 더 보여줄래\n[00:55.040]CHEER UP BABY\n[00:56.430]CHEER UP BABY\n[00:57.750]좀 더 힘을 내\n[01:00.190]여자가 쉽게 맘을 주면 안돼\n[01:02.880]그래야 니가 날 더 좋아하게 될걸\n[01:06.090]태연하게 연기할래 아무렇지 않게\n[01:11.180]내가 널 좋아하는 맘 모르게\n[01:14.120]just get it together\n[01:15.840]and then baby CHEER UP\n[01:18.690]안절부절 목소리가 여기까지 들려\n[01:21.480]땀에 젖은 전화기가 여기서도 보여\n[01:24.240]바로 바로 대답하는 것도 매력 없어\n[01:27.040]메시지만 읽고 확인 안 하는 건 기본\n[01:29.840]어어어 너무 심했나 boy\n[01:32.570]이러다가 지칠까 봐 걱정되긴 하고\n[01:35.400]어어어 안 그러면 내가 더\n[01:38.130]빠질 것만 같어 빠질 것만 같어\n[01:40.860]아 답장을 못해줘서 미안해\n[01:44.450]친구를 만나느라 shy shy shy\n[01:47.390]만나긴 좀 그렇구 미안해\n[01:50.090]좀 있다 연락할게 later\n[01:51.940]조르지마 어디 가지 않아\n[01:54.750]되어줄게 너의 Baby\n[01:57.590]너무 빨린 싫어 성의를 더 보여\n[02:00.360]내가 널 기다려줄게\n[02:03.040]CHEER UP BABY\n[02:04.400]CHEER UP BABY\n[02:05.770]좀 더 힘을 내\n[02:08.060]여자가 쉽게 맘을 주면 안돼\n[02:10.790]그래야 니가 날 더 좋아하게 될걸\n[02:14.170]태연하게 연기할래 아무렇지 않게\n[02:19.150]내가 널 좋아하는 맘 모르게\n[02:22.060]just get it together\n[02:23.720]and then baby CHEER UP\n[02:26.490]나도 니가 좋아 상처 입을까 봐\n[02:33.140]걱정되지만 여자니까 이해해주길\n[02:38.990]속 마음 들킬 까봐 겁이나\n[02:41.580]지금처럼 조금만 더 다가와\n[02:44.090]그리 오래 걸리진 않아\n[02:45.830]just get it together\n[02:47.370]and then baby CHEER UP\n[02:48.590]Be a man, a real man\n[02:51.230]gotta see u love me\n[02:52.220]like a real man\n[02:54.040]Be a man, a real man\n[02:56.840]gotta see u love me\n[02:57.890]like a real man\n[03:00.010]CHEER UP BABY\n[03:01.350]CHEER UP BABY\n[03:02.710]좀 더 힘을 내\n[03:04.990]여자가 쉽게 맘을 주면 안돼\n[03:07.750]그래야 니가 날 더 좋아하게 될걸\n[03:11.070]태연하게 연기할래 아무렇지 않게\n[03:16.020]내가 널 좋아하는 맘 모르게\n[03:19.010]just get it together\n[03:20.710]and then baby CHEER UP', '/song/1608198486452TWICE (트와이스) - CHEER UP (Korean Ver_).mp3');
INSERT INTO `song` VALUES ('21', '7', '林宥嘉-说谎', '感官/世界', '2020-12-18 23:04:53', '2020-12-18 23:05:41', '/img/songPic/1608303910607T002R300x300M000004YodY33zsWTT_1.jpg', '[00:00.85]林宥嘉 - 说谎\n[00:03.35]\n[00:05.32]作词：施人诚 作曲:李双飞\n[00:07.35]\n[00:15.73]是有过几个不错对象\n[00:23.09]说起来并不寂寞孤单\n[00:29.58]可能我浪荡 让人家不安\n[00:36.82]才会 结果都阵亡\n[00:42.77]\n[00:45.70]我没有什麽阴影魔障\n[00:53.44]你千万不要放在心上\n[00:59.57]我又不脆弱 何况那算什麽伤\n[01:06.89]反正爱情不就都这样\n[01:12.73]\n[01:13.67]我没有说谎 我何必说谎\n[01:21.02]你懂我的 我对你从来就不会假装\n[01:28.69]我哪有说谎 请别以为你有多难忘\n[01:36.97]笑是真的不是我逞强\n[01:43.92]\n[02:00.87]我好久没来这间餐厅\n[02:08.19]没想到已经换了装潢\n[02:14.41]角落那窗口 闻得到玫瑰花香\n[02:22.05]被你一说是有些印象\n[02:27.65]\n[02:28.60]我没有说谎 我何必说谎\n[02:36.10]你知道的 我缺点之一就是很健忘\n[02:43.60]我哪有说谎 是很感谢今晚的相伴\n[02:52.05]但我竟然有些不习惯\n[02:57.61]\n[02:58.71]我没有说谎 我何必说谎\n[03:06.15]爱一个人 没爱到难道就会怎麽样\n[03:13.71]别说我说谎 人生已经如此的艰难\n[03:21.94]有些事情就不要拆穿\n[03:27.96]\n[03:28.62]我没有说谎 是爱情说谎\n[03:36.02]它带你来 骗我说 渴望的有可能有希望\n[03:43.72]我没有说谎 祝你做个幸福的新娘\n[03:54.92]我的心事请你就遗忘\n[04:05.30]', '/song/1608303893615林宥嘉 - 说谎.mp3');
INSERT INTO `song` VALUES ('22', '7', '林宥嘉-兜圈', '必娶女人 电视原声带', '2020-12-18 23:08:55', '2020-12-18 23:08:55', '/img/songPic/1608304148009T002R300x300M000003KHv9r3PaIgY_1.jpg', '[00:00.58]林宥嘉 - 兜圈\n[00:03.13]词：林宥嘉\n[00:05.33]曲：林宥嘉\n[00:08.00]歌词搜索www.90lrc.cn\n[00:13.28]玩玩积木 换换座位\n[00:17.38]听听唱片 又轮回了几遍\n[00:21.48]骑骑单车 荡荡秋千\n[00:25.48]看看云堆 还有吹撒几遍\n[00:30.53]喔 不知不觉已走了多远\n[00:37.53]你 打哈欠 我 游花园\n[00:41.19]差一点 多一些\n[00:45.44]路过了学校花店\n[00:48.90]荒野到海边\n[00:50.29]爱错过了太久反而错的完美无缺\n[00:56.29]幸福兜了一个圈\n[00:59.99]歌词编辑：费总QQ1421210858\n[01:05.59]就在原点\n[01:11.84]聊聊是非 吐吐苦水\n[01:15.54]喋喋不休是雪后谈风月\n[01:19.84]懂得误会 闹钟约会\n[01:23.79]重新定位要成为你的谁\n[01:28.89]哦 不知不觉已走了多远\n[01:36.49]你 打哈欠 我 游花园\n[01:39.59]差一点 多一些\n[01:45.58]路过了学校花店\n[01:48.13]荒野到海边\n[01:50.30]有一种浪漫的爱是浪费时间\n[01:55.20]徘徊到繁华世界\n[01:58.30]才发现你背影 平凡的特别\n[02:01.85]绕过了场外 边界还是没告别\n[02:06.50]爱错过了太久反而错的完美无缺\n[02:12.60]幸福兜了一个圈\n[02:17.85]想起来好像昨天\n[02:20.10]我们初次见面\n[02:25.90]想不起很久以前\n[02:28.10]想留你在身边\n[02:34.10]谢谢你陪我迷途乐园\n[02:38.61]绕了一个圈再体会这样的迂回\n[02:46.21]多么可贵（可贵）\n[02:52.31]学校花店 荒野到海边\n[02:56.43]有一种浪漫的爱是浪费时间\n[03:01.28]徘徊到繁华世界\n[03:04.33]才发现你背影 平凡的特别\n[03:07.93]绕过了场外 边界还是没告别\n[03:12.53]爱错过了太久反而错的完美无缺\n[03:18.63]幸福兜了一个圈\n[03:23.78]那些美好的兜圈\n[03:27.34]让回忆值得怀念', '/song/1608304134926林宥嘉 - 兜圈.mp3');
INSERT INTO `song` VALUES ('23', '18', '张震岳-再见', '再见', '2020-12-19 00:01:05', '2020-12-19 00:01:05', '/img/songPic/1608307472536T002R300x300M0000002aZBV0YYOQw_1.jpg', '[00:00.000] 作词 : 张震岳\n[00:01.000] 作曲 : 张震岳\n[00:09.050]我怕我没有机会\n[00:12.860]跟你说一声再见\n[00:16.760]因为也许(就)再也见不到你\n[00:24.770]明天我要离开\n[00:28.950]熟悉的地方和你\n[00:32.440]要分离\n[00:35.360]我眼泪就掉下去\n[00:40.040]我会牢牢记住你的脸\n[00:43.820]我会珍惜你给的思念\n[00:47.790]这些日子在我心中永远都不会抹去\n[00:55.690]我不能答应你\n[00:59.700]我是否会再回来\n[01:03.480]不回头\n[01:05.920]不回头的走下去\n[01:18.930]我怕我没有机会\n[01:22.580]跟你说一声再见\n[01:26.680]因为也许(就)再也见不到你\n[01:34.220]明天我要离开\n[01:38.090]熟悉的地方和你\n[01:42.000]要分离\n[01:44.750]我眼泪就掉下去\n[01:49.850]我会牢牢记住你的脸\n[01:53.630]我会珍惜你给的思念\n[01:57.700]这些日子在我心中永远都不会抹去\n[02:05.370]我不能答应你\n[02:09.450]我是否会再回来\n[02:13.310]不回头\n[02:15.890]不回头的走下去\n[02:20.910]我会牢牢记住你的脸\n[02:24.480]我会珍惜你给的思念\n[02:28.490]这些日子在我心中永远都不会抹去\n[02:36.080]我不能答应你\n[02:40.420]我是否会再回来\n[02:43.940]不回头\n[02:46.550]不回头的走下去\n[02:51.970]不回头     不回头的走下去', '/song/1608307265115张震岳 - 再见.mp3');
INSERT INTO `song` VALUES ('24', '18', '张震岳-爱的初体验', '这个下午很无聊', '2020-12-19 00:01:49', '2020-12-19 00:03:38', '/img/songPic/1608307480218T002R300x300M000000JSJa11tTTmX_1.jpg', '[00:00.000] 作词 : 张震岳\n[00:01.000] 作曲 : 张震岳\n[00:02.000] 编曲 : 张震岳\n[00:03.000] 制作人 : 伍佰\n[00:14.01]如果说你要离开我\n[00:17.75]请诚实点来告诉我\n[00:21.88]不要偷偷摸摸的走\n[00:25.68]像上次一样等半年\n[00:29.72]如果说你真的要走\n[00:33.65]把我的相片还给我\n[00:37.39]在你身上也没有用\n[00:41.33]我可以还给我妈妈\n[00:45.26]什么天长地久\n[00:49.35]只是随便说说\n[00:53.30]你爱我哪一点\n[00:57.11]你也说不出口\n[01:01.05]你认识了帅哥\n[01:04.93]就把我丢一旁\n[01:08.96]天气热的夏天\n[01:12.77]心像寒冷冬夜\n[01:16.53]想要买酒来浇忧愁\n[01:20.28]却懒懒不想出去走\n[01:24.29]想要来一包长寿烟\n[01:28.07]发现我未满十八岁\n[01:47.57]如果说你要离开我\n[01:51.53]请诚实点来告诉我\n[01:55.62]不要偷偷摸摸的走\n[01:59.18]像上次一样等半年\n[02:03.32]如果说你真的要走\n[02:07.24]把我的相片还给我\n[02:11.14]在你身上也没有用\n[02:15.05]我可以还给我妈妈\n[02:19.10]什么天长地久\n[02:23.07]只是随便说说\n[02:26.75]你爱我哪一点\n[02:30.63]你也说不出口\n[02:34.57]你认识了帅哥\n[02:38.50]就把我丢一旁\n[02:42.49]天气热的夏天\n[02:46.21]心像寒冷冬夜\n[02:50.28]什么天长地久\n[02:53.96]只是随便说说\n[02:57.91]你爱我哪一点\n[03:01.97]你也说不出口\n[03:05.70]你认识了帅哥\n[03:09.68]就把我丢一旁\n[03:13.57]天气热的夏天\n[03:17.44]心像寒冷冬夜\n[03:21.24]想要买酒来浇忧愁\n[03:25.09]却懒懒不想出去走\n[03:28.73]想要来一包长寿烟\n[03:32.35]发现我未满十八岁\n[03:36.53]是不是我的十八岁\n[03:40.79]注定要为爱情流泪\n[03:44.52]是不是我的十八岁\n[03:48.45]注定要为爱掉眼泪', '/song/1608307309264张震岳 - 爱的初体验.mp3');
INSERT INTO `song` VALUES ('25', '18', '张震岳-很难', 'OK', '2020-12-19 00:02:33', '2020-12-19 00:02:33', '/img/songPic/1608307488534T002R300x300M0000044oT0A0isMzR_1.jpg', '[00:16.200]又一天的一个晴天\n[00:18.950]阳光印在这张旧沙发\n[00:22.700]躺下来 闭上眼\n[00:25.050]恍然不知寂寞 枕着蔓延\n[00:27.900]昨天的激情 今天的空虚\n[00:30.850]还有一张惨白的脸 慢慢改变\n[00:37.850]一杯水 和一支香烟\n[00:40.790]混合安静 孤独的气味\n[00:44.230]是纯情 是谎言\n[00:46.270]星期天的早晨 别太绝对\n[00:49.680]疯狂的世界 不想这些\n[00:52.670]锁在门之外\n[00:58.180]有时候 想把自己关起来\n[01:03.980]还是学着把心门打开\n[01:09.480]人与人之间的关系\n[01:12.430]变得不理不睬\n[01:15.230]习惯 无关紧要的冷淡\n[01:21.770]又一天的一个晴天\n[01:24.430]阳光印在这张旧沙发\n[01:28.120]躺下来 闭上眼\n[01:30.270]恍然不知寂寞 枕着蔓延\n[01:33.310]昨天的激情 今天的空虚\n[01:36.270]还有一张惨白的脸 慢慢改变\n[01:43.170]一杯水 和一支香烟\n[01:46.170]混合安静 孤独的气味\n[01:49.680]是纯情 是谎言\n[01:51.680]星期天的早晨 别太绝对\n[01:55.090]疯狂的世界 不想这些\n[01:58.140]锁在门之外\n[02:03.540]有时候 想把自己关起来\n[02:09.470]还是学着把心门打开\n[02:14.970]人与人之间的关系\n[02:17.820]变得不理不睬\n[02:20.620]习惯 无关紧要的冷淡\n[02:25.520]有时候 莫名其妙 哭起来\n[02:31.220]难道这就是自愿自挨\n[02:37.020]谁不希望像飞鸟一样 自由自在\n[02:43.170]谁不希望啊\n[02:47.920]谁不希望 只是很难\n[03:09.030]有时候 想把自己关起来\n[03:14.890]还是学着把心门打开\n[03:20.340]人与人之间的关系\n[03:23.340]变得不理不睬\n[03:26.090]习惯 无关紧要的冷淡\n[03:30.830]有时候 莫名其妙 哭起来\n[03:36.390]难道这就是自愿自挨\n[03:42.490]谁不希望像飞鸟一样 自由自在\n[03:48.590]谁不希望啊\n[03:53.340]谁不希望 只是很难', '/song/1608307353158张震岳 - 很难.mp3');
INSERT INTO `song` VALUES ('26', '18', '张震岳-思念是一种病', 'OK', '2020-12-19 00:03:27', '2020-12-19 00:03:27', '/img/songPic/1608307496421T002R300x300M0000044oT0A0isMzR_1.jpg', '[00:00.30]作曲 : 齐秦\n[00:01.30]作词 : 齐秦\n[00:02.30]原唱 : 齐秦\n[00:03.30]改编词曲 : 张震岳\n[00:11.61]当你在穿山越岭的另一边\n[00:16.03]我在孤独的路上没有尽头\n[00:22.23]一辈子有多少的来不及\n[00:24.49]发现已经失去\n[00:25.98]最重要的东西\n[00:27.63]恍然大悟早已远去\n[00:30.26]为何总是在犯错之后\n[00:32.51]才肯相信错的是自己\n[00:34.98]他们说这就是人生\n[00:37.11]试著体会试著忍住眼泪\n[00:40.84]还是躲不开应该有的情绪\n[00:43.69]我不会奢求世界停止转动\n[00:49.06]我知道逃避一点都没有用\n[00:53.23]只是这段时间裡尤其在夜裡\n[00:56.60]还是会想起难忘的事情\n[00:59.64]我想我的思念是一种病\n[01:03.11]久久不能痊癒\n[01:05.28]当你在穿山越岭的另一边\n[01:09.31]我在孤独的路上没有尽头\n[01:14.55]时常感觉你在耳后的呼吸\n[01:19.67]却未曾感觉你在心口的鼻息\n[01:25.78]汲汲营营\n[01:27.62]忘记身边的人需要爱和关心\n[01:31.70]借口总是拉远了距离\n[01:34.42]不知不觉无声无息\n[01:37.15]我们总是在抱怨事与愿违\n[01:39.82]却不愿意回头看看自己\n[01:43.13]想想自己到底做了甚麼蠢事情\n[01:47.83]也许是上帝给我一个试炼\n[01:53.06]只是这伤口需要花点时间\n[01:57.09]只是会想念过去的一切\n[01:59.75]那些人事物会离我远去\n[02:03.70]而我们终究也会远离\n[02:07.51]变成回忆\n[02:09.21]当你在穿山越岭的另一边\n[02:13.32]我在孤独的路上没有尽头\n[02:18.76]时常感觉你在耳后的呼吸\n[02:23.61]却未曾感觉你在心口的鼻息\n[02:29.26]Oh思念是一种病\n[02:34.57]Oh思念是一种病 一种病\n[02:41.25]多久没有说我爱你\n[02:43.04]多久没有拥抱你所爱的人\n[02:45.77]当这个世界不再那麼美好\n[02:48.16]只有爱可以让他更好\n[02:50.39]我相信一切都来得及\n[02:52.58]别管那些纷纷扰扰\n[02:54.11]别让不开心的事停下了脚步\n[02:56.61]就怕你不说就怕你不做\n[02:59.12]别让遗憾继续一切都来得及\n[03:02.34]当你在穿山越岭的另一边\n[03:06.61]我在孤独的路上没有尽头\n[03:11.84]时常感觉你在耳后的呼吸\n[03:17.15]却未曾感觉你在心口的鼻息\n[03:23.91]当你在穿山越岭的另一边\n[03:28.00]我在孤独的路上没有尽头\n[03:33.20]时常感觉你在耳后的呼吸\n[03:38.32]却未曾感觉你在心口的鼻息\n[03:43.94]Oh 思念是一种病\n[03:49.30]Oh 思念是一种病 一种病', '/song/1608307407120张震岳 _ 蔡健雅 - 思念是一种病.mp3');
INSERT INTO `song` VALUES ('27', '17', 'Jony J-不用去猜', '不用去猜', null, '2020-12-22 14:05:42', '/img/songPic/160861720490918523472395121303.jpg', '[00:00.000] 作词 : Jony J\n[00:01.000] 作曲 : Jony J\n[00:07.23]编曲:卡斯\n[00:10.03]混音:郭子敬\n[00:16.86]灯不会在任何时候为我开\n[00:19.74]是好是坏 该不该\n[00:21.90]还没来的不想猜\n[00:23.50]有谁能真的看得清楚\n[00:25.94]要怎么组未来的拼图\n[00:31.00]不会为了限量款排队\n[00:33.86]我都是天亮了才睡\n[00:35.05]时间比LV还贵\n[00:36.41]人生总苦短又怎么能白费\n[00:38.50]没兴趣在乎我在别人眼中的定位\n[00:40.74]普通或另类 讨厌或敬佩\n[00:42.50]不需要多余的应对\n[00:44.04]只想跟懂我的说一声幸会\n[00:45.94]因为 别的都不屑\n[00:47.22]快乐是自己的没人可以借\n[00:49.17]忘记了恨谁 有谁忘了谢\n[00:50.94]偶尔在情绪会泛滥的夜\n[00:52.77]没有人会帮你把你变得更好\n[00:54.58]错过只能怪你没有趁早\n[00:56.42]每天都该保持前进\n[00:57.46]我势必要有强劲的实力\n[00:58.83]再跟全新的自己问好\n[01:00.08]我知道灯 不会在任何时候为我开\n[01:03.84]是好是坏 该不该\n[01:05.93]还没来的不想猜\n[01:07.77]有谁能真的看得清楚\n[01:10.20]哪里是归宿\n[01:11.58]要怎么组未来的拼图\n[01:13.76]先走好每一步\n[01:15.71]昨天已经过去明天还没来\n[01:18.80]今天的一切就算突然我也不奇怪\n[01:22.23]我不再预测未来因为总在意料外\n[01:26.27]该在的都会在\n[01:28.06]不管这世界变得多么快\n[01:30.67]从没想过买个彩票可以让我中奖\n[01:32.39]也不需要不实在的面子\n[01:33.78]我也早就不跟别人谈论什么梦想\n[01:35.54]悄悄把梦想的都变成了现实\n[01:37.54]不管我是试试看\n[01:38.52]还是扑了空\n[01:39.63]直接做到而不是挂在嘴边\n[01:41.44]是个穷光蛋\n[01:41.83]还是大富翁\n[01:43.13]我都尽力的过好我的每天\n[01:44.71]因为灯 不会在 任何时候为我开\n[01:48.12]是好是坏 该不该\n[01:50.28]还没来的不想猜\n[01:52.16]有谁能真的看得清楚 哪里是归宿\n[01:55.76]要怎么组未来的拼图\n[01:58.10]先走好每一步\n[01:59.73]我知道 灯 不会在 任何时候为我开\n[02:02.93]是好是坏 该不该\n[02:05.06]还没来的不想猜\n[02:06.92]有谁能真的看得清楚 哪里是归宿\n[02:10.62]要怎么组未来的拼图\n[02:12.78]先走好每一步\n[02:14.80]不用去猜 不用去猜\n[02:16.82]还没有发生的不用去猜\n[02:18.30]不用去猜 不用去猜\n[02:20.50]明天是什么样不用去猜\n[02:22.29]不用去猜 不用去猜\n[02:24.31]该来的总会来不用去猜\n[02:25.89]不用去猜 不用去猜\n[02:27.84]不用去猜 不用去猜\n[02:29.63]不浪费时间去猜测我未来\n[02:31.42]会碰到的麻烦或惊喜\n[02:32.98]虽然也常会有问号跟感叹号\n[02:34.62]出现但不让它留在我心里\n[02:36.55]曾经我为了去武装我自己\n[02:38.13]很快就学会了冷漠和脏话\n[02:40.18]现在我不想再装\n[02:41.57]我只想更爽快想悠然的活在这当下\n[02:43.94]时间又不会为我赖着不走\n[02:45.77]干嘛停下来为了选择头疼\n[02:48.03]我的新目标就在下个路口\n[02:49.60]现在要做的就是加点油门\n[02:51.25]当我穿过拥挤的人群\n[02:52.83]穿过低谷时被冲洗的人情\n[02:54.69]感谢所有为我亮起的灯\n[02:56.03]在我丧气的时候\n[02:56.93]总是仗义的过来按我门铃\n[02:58.73]因为灯 不会在 任何时候为我开\n[03:01.93]是好是坏 该不该\n[03:04.11]还没来的不想猜\n[03:05.99]有谁能真的看得清楚 哪里是归宿\n[03:09.71]要怎么组未来的拼图\n[03:11.94]先走好每一步\n[03:13.61]我知道 灯 不会在 任何时候为我开\n[03:17.05]是好是坏 该不该\n[03:18.93]还没来的不想猜\n[03:20.65]有谁能真的看得清楚 哪里是归宿\n[03:24.46]要怎么组未来的拼图\n[03:26.71]先走好每一步\n[03:30.73]', '/song/1608617190753不用去猜-Jony J.mp3');
INSERT INTO `song` VALUES ('29', '19', '房东的猫-云烟成雨', '云烟成雨', '2020-12-20 17:53:58', '2020-12-20 17:53:58', '/img/songPic/1608458084542T002R300x300M000004NFJ230yX0Nz_1.jpg', '[00:00.000] 作词 : 墨鱼丝\n[00:01.000] 作曲 : 少年佩\n[00:11.230]制作人：黎偌天\n[00:12.230]编曲：黎偌天\n[00:13.230]监制：李纤橙\n[00:14.230]\n[00:18.210]你的晚安 是下意识的恻隐\n[00:24.870]我留至夜深 治疗失眠梦呓\n[00:30.790]那封手写信 留在行李箱底\n[00:36.880]来不及 赋予它旅途的意义\n[00:43.850]若一切 都已云烟成雨\n[00:49.490]我能否 变成淤泥\n[00:52.490]再一次 沾染你\n[00:55.650]若生命 如过场电影\n[01:01.530]Oh让我再一次 甜梦里惊醒\n[01:13.870]我多想再见你\n[01:15.490]哪怕匆匆一眼就别离\n[01:19.760]路灯下昏黄的剪影\n[01:22.760]越走越漫长的林径\n[01:25.950]我多想再见你\n[01:28.010]至少玩笑话还能说起\n[01:32.230]街巷初次落叶的秋分\n[01:35.370]渐行渐远去的我们\n[01:53.080]若一切 都已云烟成雨\n[01:59.020]我能否 变成淤泥\n[02:01.910]再一次 沾染你\n[02:05.560]若生命 如过场电影\n[02:10.690]Oh让我再一次 甜梦里惊醒\n[02:19.690]我多想再见你\n[02:21.640]哪怕匆匆一眼就别离\n[02:26.030]路灯下昏黄的剪影\n[02:28.770]越走越漫长的林径\n[02:32.140]我多想再见你\n[02:34.410]至少玩笑话还能说起\n[02:38.370]街巷初次落叶的秋分\n[02:41.400]渐行渐远去的我们\n[02:49.410]站台 汽笛响起\n[02:52.490]想念是你的声音\n[02:55.840]我们提着过去 走入人群\n[03:02.160]寻找着一个位置 安放自己\n[03:13.680]我多想再见你\n[03:15.370]哪怕匆匆一眼就别离\n[03:19.500]路灯下昏黄的剪影\n[03:22.530]越走越漫长的林径\n[03:25.980]我多想再见你\n[03:28.200]至少玩笑话还能说起\n[03:31.970]街巷初次落叶的秋分\n[03:35.180]渐行渐远去的我们\n[03:41.980]\n[03:43.420]主唱：红鼻子小黑\n[03:44.670]制作人：黎偌天\n[03:46.200]监制：李纤橙', '/song/1608458038350房东的猫 - 云烟成雨.mp3');
INSERT INTO `song` VALUES ('32', '21', '毛华锋-奇迹再现', '迪迦奥特曼', '2020-12-21 15:24:45', '2020-12-22 13:40:08', '/img/songPic/1608615199909T002R300x300M000003qbwr50QOnya_1.jpg', '[00:00.000] 作词 : 张鹏\n[00:04.06]\n[00:16.03]就像阳光穿过黑夜\n[00:19.08]黎明悄悄划过天边\n[00:21.98]谁的身影穿梭轮回间\n[00:27.96]未来的路就在脚下\n[00:30.95]不要悲伤不要害怕\n[00:34.04]充满信心期盼着明天\n[00:39.22]新的风暴已经出现\n[00:42.02]怎么能够停滞不前\n[00:44.76]穿越时空竭尽全力\n[00:48.15]我会来到你身边\n[00:51.44]微笑面对危险\n[00:54.02]梦想成真不会遥远\n[00:56.71]鼓起勇气坚定向前\n[01:00.11]奇迹一定会出现\n[01:16.06]就像阳光穿过黑夜\n[01:19.10]黎明悄悄划过天边\n[01:21.99]谁的身影穿梭轮回间\n[01:28.02]未来的路就在脚下\n[01:30.97]不要悲伤不要害怕\n[01:34.00]充满信心期盼着明天\n[01:39.24]新的风暴已经出现\n[01:42.13]怎么能够停滞不前\n[01:44.77]穿越时空竭尽全力\n[01:48.01]我会来到你身边\n[01:51.55]微笑面对危险\n[01:54.09]梦想成真不会遥远\n[01:56.78]鼓起勇气坚定向前\n[02:00.12]奇迹一定会出现\n[02:51.33]新的风暴已经出现\n[02:54.22]怎么能够停滞不前\n[02:56.77]穿越时空竭尽全力\n[03:00.20]我会来到你身边\n[03:03.65]微笑面对危险\n[03:06.09]梦想成真不会遥远\n[03:08.77]鼓起勇气坚定向前\n[03:12.17]奇迹一定会出现\n[03:15.30]新的风暴已经出现\n[03:18.19]怎么能够停滞不前\n[03:20.77]穿越时空竭尽全力\n[03:23.96]我会来到你身边\n[03:27.55]微笑面对危险\n[03:30.10]梦想成真不会遥远\n[03:32.79]鼓起勇气坚定向前\n[03:36.18]奇迹一定会出现', '/song/1608615211991毛华锋 - 奇迹再现.mp3');
INSERT INTO `song` VALUES ('33', '22', 'ボイジャー (voyager)-みんな大好きなウルトラマン (大家最喜欢的奥特曼)', 'ULTRAGALAXY (ウルトラギャラクシー)', '2020-12-22 13:16:52', '2020-12-22 13:16:52', '/img/songPic/1608614455415T002R300x300M000003WicUb0aKj6S_1.jpg', '[00:00.000] 作词 : 田靡秀树\n[00:01.000] 作曲 : 田靡秀树\n[00:30.82]笑顔にあいたい時は\n[00:35.51]この場所のこと思い出して\n[00:40.35]一人で寂しいときも\n[00:45.06]またここに来ればみんながいる\n[00:49.73]\n[00:50.30]空を見て星を見て\n[00:54.84]あのヒーローを呼んでみよう\n[00:59.66]きっといつか\n[01:02.04]必ず会えるから光の戦士たち\n[01:09.44]きっとそれは君の心にいるんだ\n[01:14.72]みんな大好きなウルトラマン\n[01:18.78]\n[01:24.35]争うことなんてしたくない\n[01:28.90]でも誰かのために\n[01:31.35]戦ってるんだ\n[01:33.81]今日生まれた１つの命を\n[01:38.64]君は全力で守るだろう\n[01:43.23]\n[01:43.74]空を見て星を見て\n[01:48.35]あのヒーローを呼んでみよう\n[01:53.22]きっといつか\n[01:55.56]君の夢は叶うから輝く子供たち\n[02:02.97]ずっとそれは君の心に生きてる\n[02:08.21]みんな大好きなウルトラマン\n[02:12.29]\n[02:32.32]きっといつか\n[02:34.62]君の夢は叶うから輝く子供たち\n[02:41.90]ずっとそれは\n[02:44.41]君の心に生きてる\n[02:47.27]みんな大好きなウルトラマン\n[02:51.77]きっといつか\n[02:54.28]必ず会えるから光の戦士たち\n[03:01.51]きっとそれは君の心にいるんだ\n[03:06.86]みんな大好きなウルトラマン\n[03:10.85]\n[03:11.40]きっといつか\n[03:13.78]必ず合えるから光の戦士たち\n[03:21.15]きっとそれは君の心にいるんだ\n[03:26.50]ぼくの大好きなウルトラマン\n[03:30.48]\n[03:31.28]みんな大好きなウルトラマン\n[03:36.94]', '/song/1608614212544ボイジャー (voyager) - みんな大好きなウルトラマン (大家最喜欢的奥特曼).mp3');
INSERT INTO `song` VALUES ('34', '21', '毛华锋-永远的奥特曼', '迪迦奥特曼', '2020-12-22 13:37:31', '2020-12-22 13:40:15', '/img/songPic/1608615503319T002R300x300M000003qbwr50QOnya_1.jpg', '[00:00.000] 作词 : 齐放\n[00:00.330]编曲：安栋\n[00:00.430]制作人：缪森\n[00:00.730]蔚蓝的星球是我们不变的守候\n[00:07.360]期待你永远的奥特曼\n[00:31.050]大地渐渐苏醒\n[00:33.470]一丝晨光打破了夜的寂静\n[00:39.830]炙热的心在跳动\n[00:44.350]祈祷和平降临\n[00:49.700]我们一起追寻前途弥漫荆棘和险峻\n[00:58.550]心中呐喊无边天际放射光明\n[01:07.360]看那蔚蓝色的星球\n[01:11.160]是我们永远的守候\n[01:14.860]我伫立在这里倾听风的声音\n[01:20.350]来吧我的热情在飞翔\n[01:24.060]带着那灿烂的希望\n[01:27.480]我们携手并肩穿越时空界限\n[01:30.930]冲破黑暗\n[01:34.760]YOU ARE ALWAYS MY HERO\n[01:39.150]\n[01:51.420]大地渐渐苏醒\n[01:53.820]一丝晨光打破了夜的寂静\n[02:00.070]炙热的心在跳动\n[02:03.590]祈祷和平降临\n[02:10.200]我们一起追寻前途弥漫荆棘和险峻\n[02:19.200]心中呐喊无边天际放射光明\n[02:27.440]看那蔚蓝色的星球\n[02:31.190]是我们永远的守候\n[02:35.280]我伫立在这里倾听风的声音\n[02:41.900]来吧我的热情在飞翔\n[02:45.540]带着那灿烂的希望\n[02:48.960]我们携手并肩穿越时空界限\n[02:52.230]冲破黑暗\n[02:56.380]YOU ARE ALWAYS MY HERO', '/song/1608615451909毛毛 - 永远的奥特曼.mp3');
INSERT INTO `song` VALUES ('35', '3', '张国荣-月亮代表我的心(Live)', 'I Am What I Am', '2020-12-22 13:43:20', '2020-12-22 13:43:20', '/img/songPic/1608615815521T002R300x300M000001muiGQ2UyBl6_1.jpg', '[00:15.460]你问我爱你有多深\n[00:22.190]我爱你有几分\n[00:28.620]我的爱也真\n[00:31.960]我的情也真\n[00:36.430]月亮代表我的心\n[00:42.850]你问我爱你有多深\n[00:49.270]我爱你有几分\n[00:55.620]我的情不移\n[00:58.560]我的爱不变\n[01:01.870]月亮代表我的心\n[01:08.450]轻轻的一个吻\n[01:15.320]已经打动我的心\n[01:21.680]深深的一段情\n[01:28.570]教我思念到如今\n[01:32.720]\n[01:35.950]你问我爱你有多深\n[01:42.450]我爱你有几分\n[01:48.790]你去想一想\n[01:51.930]你去看一看\n[01:55.220]月亮代表我的心\n[01:59.330]\n[02:28.800]轻轻的一个吻\n[02:35.510]已经打动我的心\n[02:42.000]深深的一段情\n[02:48.590]教我思念到如今\n[02:56.750]你问我爱你有多深\n[03:02.610]我爱你有几分\n[03:08.810]你去想一想\n[03:11.860]你去看一看\n[03:15.200]月亮代表我的心\n[03:19.300]\n[03:22.000]你去想一想\n[03:25.160]你去看一看\n[03:28.450]月亮代表我的心\n[03:32.780]', '/song/1608615800218张国荣 - 月亮代表我的心 (Live).mp3');
INSERT INTO `song` VALUES ('36', '23', '陈奕迅-葡萄成熟时', 'U 87', '2020-12-22 13:46:36', '2020-12-22 13:46:36', '/img/songPic/1608616025162T002R300x300M000003J6fvc0bVJon_1.jpg', '[00:00.000] 作词 : 黄伟文\n[00:01.000] 作曲 : Vincent Chow/Anfernee Cheung\n[00:02.000]编曲 : Adrian Chan 陈伟文\n[00:03.000]监制 : Alvin Leong 梁荣骏\n[00:04.000]\n[00:15.982]差不多冬至一早一晚还是有雨\n[00:19.952]当初的坚持现已令你很怀疑\n[00:25.625]很怀疑 你最尾等到 只有这枯枝\n[00:31.776]苦恋几多次悉心栽种 全力灌注\n[00:35.547]所得竟不如别个后辈 收成时\n[00:41.153]这一次 你真的很介意\n[00:46.008]但见旁人谈情何引诱\n[00:50.368]问到何时葡萄先熟透\n[00:54.491]你要静候 再静候\n[00:58.103]就算失收始终要守\n[01:03.410]日后 尽量别教今天的泪白流\n[01:10.354]留低 击伤你的石头\n[01:14.110]从错误里吸收\n[01:17.763]也许 丰收月份尚未到你也得接受\n[01:25.027]或者要到你将爱酿成醇酒\n[01:31.443]时机先至熟透\n[01:34.792]\n[01:49.098]应该怎么爱可惜书里从没记载\n[01:52.940]终于摸出来但岁月却不回来\n[01:58.434]不回来 错过了春天可会再花开\n[02:04.402]一千种恋爱一些需要情泪灌溉\n[02:08.266]枯萎的温柔在最后会长回来\n[02:13.733]错的爱乃必经的配菜\n[02:18.555]但见旁人谈情何引诱\n[02:22.827]问到何时葡萄先熟透\n[02:26.921]你要静候 再静候\n[02:30.476]就算失收始终要守\n[02:35.284]日后 尽量别教今天的泪白流\n[02:42.173]留低 击伤你的石头\n[02:45.950]从错误里吸收\n[02:49.529]也许 丰收月份尚未到你也得接受\n[02:56.687]或者要到你将爱酿成醇酒\n[03:03.087]时机先至熟透\n[03:09.528]想想天的一边\n[03:12.305]亦有个某某 在等候\n[03:24.564]一心只等葡萄熟透 尝杯酒\n[03:39.555]别让 寂寞害你想得一夜白头\n[03:46.441]赢得不需要的自由\n[03:50.215]和最耀眼伤口\n[03:53.770]我知 日后路上或没有更美的邂逅\n[04:00.954]但当你智慧都酝酿成红酒\n[04:07.306]仍可一醉自救\n[04:13.043]谁都辛酸过哪个没有\n[04:23.432]', '/song/1608615996461陈奕迅 - 葡萄成熟时.mp3');
INSERT INTO `song` VALUES ('37', '19', '房东的猫-美好事物', '美好事物', '2020-12-22 14:20:50', '2020-12-22 14:20:50', '/img/songPic/160861806753518252992533065802.jpg', '[00:00.000] 作词 : 一只然\n[00:00.000] 作曲 : 少年佩\n[00:00.000]作曲 : 少年佩\n[00:00.033]作词 : 一只然\n[00:00.99]热夏 你归来 听蝉\n[00:06.26]再游于 北方 知寒\n[00:13.11]沿途 不枉为 少年\n[00:18.90]终有个 结局圆满\n[00:25.78]你看 顽皮细雨招摇过远帆\n[00:31.30]修理过小店某处忽明忽暗的灯盏\n[00:37.89]你听 江水流过人家吵着要上岸\n[00:44.14]你去过烟花三月的江南\n[00:50.75]你看 秋月温柔撕破了花瓣\n[00:56.02]却只为迎着暮冬大雪纷飞时贪玩\n[01:02.80]你说 要忘却所有不愉快的片段\n[01:09.11]把美好事物纯真地走完\n[01:41.32]把疲乏往期 装进朴质的长街\n[01:47.59]把失败恋爱 藏进路人的详谈\n[01:53.93]把起舞的今日 写成诗篇\n[01:59.86]多年后 也不遗憾\n[02:06.38]把无味春风 融进街边的早餐\n[02:12.63]把仰头月色 化为潇洒的释然\n[02:18.69]把漫长的故事 变成短暂\n[02:24.82]才配得起勇敢\n[02:43.68]别忧愁聚散\n[02:50.43]又何惧放胆\n[02:56.20]让幽邃夜晚\n[03:02.46]静躺入空山', '/song/1608618050489美好事物 - 房东的猫.mp3');
INSERT INTO `song` VALUES ('38', '24', '小猪佩奇（Peppa Pig）-Peppa\'s Lullaby', 'My First Album', '2020-12-22 14:27:39', '2020-12-22 14:27:39', '/img/songPic/1608618493500T002R300x300M000000fMKkY3F33AL_2.jpg', '[00:01.765] Okay, Peppa, let\'s get you to bed\n[00:04.992] I am a bit of a sleepyhead\n[00:08.719] George, you have to go to sleep because it\'s nighttime now\n[00:12.755] Oh\n[00:14.215] I will sing you a song to make you sleepy\n[00:20.721] Time for bed, sleepyhead, the moon is in the sky\n[00:27.938] Time for bed, sleepyhead, the stars are floating by\n[00:35.890] Comfy and cozy and room and snuggly\n[00:39.844] Tucked up in your bed\n[00:47.641] So close your eyes \'til morning comes\n[00:51.296] And good night, sleepyhead\n[00:54.636] Yes, good night, sleepyhead\n[01:00.071] Good night, Peppa, good night, George\n[01:07.137] Dream together, one and all\n[01:23.086] Time for bed, sleepyhead, daddy tucks us in\n[01:30.287] Time for bed, sleepyhead, the nighttime can begin\n[01:38.385] George and his dinosaur, me and Teddy\n[01:42.043] Are tucked up in our beds\n[01:50.144] So close our eyes \'til morning comes\n[01:53.772] And good night, sleepyheads\n[01:57.139] Yes, good night, sleepyheads\n[02:03.832] Good night, George\n[02:05.498] Night, night\n[02:06.607] Sleep tight\n[02:08.652] And night, night, Mummy, and Daddy, and everyone\n[02:17.979] Good night, Peppa, good night, George\n[02:25.154] Dream together, one and all\n[02:34.258] Night, night, my little piggies\n[02:35.882] Sweet dreams', '/song/1608618459596Peppa\'s Lullaby - Peppa Pig.mp3');
INSERT INTO `song` VALUES ('39', '25', '秦基博 (はたもとひろ)-Rain (《言叶之庭》动画电影片尾曲)', '言ノ葉', '2020-12-22 14:35:51', '2020-12-22 14:35:51', '/img/songPic/1608618970792T002R300x300M000002HDtrl04xqKd_1.jpg', '[00:00.000] 作词 : 大江千里\n[00:01.000] 作曲 : 大江千里\n[00:15.19]言葉にできず冻えたままで\n[00:20.31]人前ではやさしく生きていた\n[00:24.92]しわよせで こんなふうに雑に\n[00:29.42]雨の夜にきみを抱きしめてた\n[00:36.04]道路わきのビラと壊れた常夜灯\n[00:41.87]街角ではそう だれもが急いでた\n[00:45.57]きみじゃない 悪いのは自分の激しさを\n[00:51.06]かくせないぼくのほうさ\n[00:56.81]Ladyきみは雨にけむる\n[01:01.36]すいた駅を少し走った\n[01:07.92]どしゃぶりでもかまわないと\n[01:09.82]ずぶぬれでもかまわないと\n[01:12.65]しぶきあげるきみが消えてく\n[01:17.76]路地裏では朝が早いから\n[01:20.25]今のうちにきみをつかまえ\n[01:22.88]行かないで 行かないで そう言うよ\n[01:25.19]\n[01:28.74]\n[01:49.27]别々に暮らす 泣きだしそうな空を\n[01:54.19]にぎりしめる強さは今はもうない\n[01:58.75]変わらずいる心のすみだけで\n[02:03.43]傷つくようなきみならもういらない\n[02:09.92]Lady きみは雨にぬれて\n[02:14.42]ぼくの眼を少し見ていた\n[02:20.23]どしゃぶりでもかまわないと\n[02:22.86]ずぶぬれでもかまわないと\n[02:25.48]口笛ふくぼくがついてく\n[02:30.67]ずいぶんきみを知りすぎたのに\n[02:33.29]初めて争った夜のように\n[02:35.91]行かないで 行かないで そう言うよ\n[02:38.16]\n[02:41.84]\n[03:02.14]肩が乾いたシャツ改札を出る顷\n[03:07.19]きみの町じゃもう雨は小降りになる\n[03:11.90]今日だけが明日に続いてる\n[03:16.24]こんなふうに きみとは终われない\n[03:20.91]\n[03:22.85]Lady きみは今もこうして\n[03:27.42]小さめの伞もささずに\n[03:33.21]\n[03:35.98]どしゃぶりでもかまわないと\n[03:38.50]ずぶぬれでもかまわないと\n[03:41.06]しぶきあげるきみが消えてく\n[03:46.40]路地裏では朝が早いから\n[03:48.95]今のうちにきみをつかまえ\n[03:51.51]行かないで 行かないで\n[03:53.89]そう言うよ\n[03:56.75]どしゃぶりでもかまわないと\n[03:59.38]ずぶぬれでもかまわないと\n[04:01.94]口笛ふくぼくがついてく\n[04:07.21]ずいぶんきみを知りすぎたのに\n[04:09.83]初めて争った夜のように\n[04:12.45]行かないで 行かないで そう言うよ\n[04:14.76]\n[04:19.19]\n[04:45.56]', '/song/1608618951428秦基博 - Rain.ogg');
INSERT INTO `song` VALUES ('40', '5', 'TWICE-Cry For Me', 'Cry For Me', '2020-12-23 16:14:19', '2020-12-23 16:14:19', '/img/songPic/1608711305765109951165549338844.jpg', '[00:00.000] 作词 : Heize/朴振荣\n[00:01.000] 作曲 : Ryan Tedder/Melanie Joy Fontana/Michel \"Lindgren\" Schulz/A Wright\n[00:13.420]I know 고쳐 쓸 가치도 없다는 걸\n[00:18.440]하지만 그녀와 달리 난\n[00:20.700]널 쉽게 놔줄 맘이 없거든\n[00:23.436]Never let go\n[00:25.509]You don\'t know me\n[00:28.639]L O V E or hatred\n[00:31.310]이별 대신 난 순진한 미소만\n[00:34.910]오늘도 네 품에 안길래\n[00:39.770]아무것도 모르는 척\n[00:41.940]Baby no more real love\n[00:42.630]너의 곁에 있어줄게\n[00:44.860]마지막엔 break your heart\n[00:46.913]Bad boy bad boy\n[00:48.536]Yeah you really make me\n[00:49.706]A mad girl mad girl\n[00:53.527]I want you to cry cry for me\n[00:56.778]내가 울었던 것처럼 cry for me\n[01:00.005]Make your rain fall\n[01:01.717]Cry for me\n[01:04.152]But again\n[01:06.047]조금씩 조금씩 또 빠져가\n[01:09.062]사랑에 내 결심이 또 무너져가\n[01:12.673]용서할 핑계를 만들어가\n[01:15.901]I want you to\n[01:16.873]I want you to\n[01:17.764]I want you to\n[01:18.638]Cry for me\n[01:22.271]I don’t know 너란 놈\n[01:25.150]미워질 줄 모르고\n[01:26.744]친구들한텐 또 너를 감싸주는 중\n[01:31.210]바보가 돼 버렸군\n[01:34.360]너 왜 자꾸 나를 보며 웃는데\n[01:40.170]딱 한 번의 눈물이면 되는데\n[01:44.396]Cry for me let me please forgive you\n[01:49.350]아무것도 모르는 척\n[01:51.310]Baby just like real love\n[01:52.610]마지막 기회야 어서\n[01:54.147]보여줘봐 your true love\n[01:56.387]Bad boy bad boy\n[01:58.295]Yeah you really make me\n[01:59.568]A sad girl sad girl\n[02:03.037]I want you to cry cry for me\n[02:05.932]내가 울었던 것처럼 cry for me\n[02:09.337]Make your rain fall\n[02:11.286]Cry for me\n[02:13.708]But again\n[02:16.170]조금씩 조금씩 또 빠져가\n[02:18.680]사랑에 내 결심이 또 무너져가\n[02:22.008]용서할 핑계를 만들어가\n[02:25.587]I want you to\n[02:26.391]I want you to\n[02:27.282]I want you to\n[02:28.098]Cry for me\n[02:30.440]사랑이란 게 너무 독해\n[02:32.360]미운 마음도 다 녹아버리게 해\n[02:35.690]또 다시 원해 널 내 곁에 yeah yeah\n[02:43.200]I want you to cry cry for me\n[02:45.300]너 연기라도 해 빨리 cry for me\n[02:48.990]Make your rain fall\n[02:51.190]Fall and fall now\n[02:55.500]I want you to cry cry for me\n[02:58.630]내가 울었던 것처럼 cry for me\n[03:03.139]Make your rain fall\n[03:04.491]Cry for me\n[03:06.682]But again\n[03:09.820]조금씩 조금씩 또 빠져가\n[03:10.420]사랑에 내 결심이 또 무너져가\n[03:15.260]용서할 핑계를 만들어가\n[03:18.591]I want you to\n[03:19.544]I want you to\n[03:20.363]I want you to\n[03:21.197]Die for me', '/song/1608711258864CRY FOR ME - TWICE.mp3');
INSERT INTO `song` VALUES ('41', '6', '五月天-温柔', '《爱情万岁》', '2021-03-19 14:43:56', '2021-03-19 14:43:56', '/img/songPic/1616136359992T002R300x300M0000042miPf28nzum_1.jpg', '[00:00.000] 作词 : 阿信\n[00:01.000] 作曲 : 阿信\n[00:09.82]编曲：五月天\n[00:12.30]\n[00:13.11]走在风中 今天阳光 突然好温柔\n[00:19.60]天的温柔 地的温柔 像你抱着我\n[00:26.10]然后发现你的改变 孤单的今后\n[00:31.75]如果冷 该怎么度过\n[00:39.75]\n[00:42.30]天边风光 身边的我 都不在你眼中\n[00:48.76]你的眼中藏着什么 我从来都不懂\n[00:55.22]没有关系 你的世界 就让你拥有\n[01:00.94]不打扰 是我的温柔\n[01:09.03]\n[01:11.03]不知道 不明了 不想要\n[01:13.41]为什么我的心\n[01:17.56]明明是想靠近 却孤单到黎明\n[01:24.01]不知道 不明了 不想要\n[01:26.40]为什么我的心\n[01:30.50]那爱情的绮丽 总是在孤单里\n[01:36.96]再把我的最好的爱给你\n[01:42:94]\n[01:43.91]不知不觉 不情不愿 又到巷子口\n[01:50.34]我没有哭 也没有笑 因为这是梦\n[01:56.85]没有预兆 没有理由 你真的有说过\n[02:02.54]如果有 就让你自由\n[02:09.88]\n[02:32.09]不知道 不明了 不想要\n[02:34.51]为什么我的心\n[02:38.58]明明是想靠近 却孤单到黎明\n[02:45.08]不知道 不明了 不想要\n[02:47.46]为什么我的心\n[02:51.56]那爱情的绮丽 总是在孤单里\n[02:58.05]再把我的最好的爱给你\n[03:03.96]\n[03:04.96]不知不觉 不情不愿 又到巷子口\n[03:11.40]我没有哭 也没有笑 因为这是梦\n[03:17.89]没有预兆 没有理由 你真的有说过\n[03:23.64]如果有 就让你自由\n[03:30.93]\n[03:33.36]自由\n[03:37.37]\n[03:38.56]这是我的温柔\n[03:41.81]这是我的温柔\n[03:45.01]这是我的温柔\n[03:48.26]这是我的温柔\n[03:54.33]\n[04:03.34]让你自由', '/song/1616136236628五月天 - 温柔.mp3');

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `pic` varchar(255) DEFAULT NULL COMMENT '歌单图片',
  `songlist_intro` text COMMENT '歌单简介',
  `style` varchar(255) DEFAULT NULL COMMENT '歌单风格',
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='歌单';

-- ----------------------------
-- Records of song_list
-- ----------------------------
INSERT INTO `song_list` VALUES ('1', '以丧治丧：每日“不开心”，拯救不快乐的你', '/img/songListPic/1608288328177109951165160147504.jpg', '最近看微博热搜，似乎大家都在玩一个新梗\n\n本期歌单，我也和大家聊聊吧\n\n大家口中的这一现象分为两种\n一种为“丧文化”，一种为“伪抑郁症”\n\n何为“丧文化”\n\n这是90，00后的年轻人在现实生活中\n因为生活、学习、事业、情感等的不顺\n在网络上、生活中表达或表现出自己的沮丧，以形成的一种文化趋势\n\n“网抑云”的出现也是青年亚文化在新媒体时代的一个缩影\n\n我看到一些评论有人觉得一些听众的所说所想\n过于的悲观，夸张，甚至不切实际\n\n其实我保有主观意见\n\n我们经历了升学压力、就业困难、经济转型，情感失败等种种压力\n云村成为了我们的一个宣泄口\n将这些平日面对父母，面对同事，面对朋友无法言表的话\n通过评论的方式来抒发自己内心的想法\n\n在听歌的时候\n我们不再需要扮演别人心目中三好青年的完美形象\n接受自己的不完美、接受自己的缺陷，并为自己而活\n接受自己是一个这样的人，卸下伪装，与自己和解\n\n正因为有“网抑云”，你才会知道，其实大家都过得不快乐，并不只有你一个\n\n这个梗在微博走红，我并不意外\n因为是我们勇于去转发、去分享、去倾述、去再创作\n\n去告诉别人“其实，我就是一个这样的人”\n\n平日你很少看到这样的人，是因为大家都在努力积极向上的生活着\n不愿意让别人看到自己其实很脆弱的一面\n\n但说到这里，有一些听众在评论区另类的消极和悲观，就不得不点出“抑郁症”了\n\n那何为“抑郁症”\n\n抑郁症是以显著而持久的心境低落为主要临床特征，\n是心境障碍的主要类型\n可以从闷闷不乐到悲痛欲绝，自卑抑郁，甚至悲观厌世来确定\n\n抑郁症的病发也与我们的学习工作生活的不顺等等因素息息相关\n\n但实则讽刺了那些“伪抑郁症”的虚假评论\n明明没有抑郁症，为了点赞和热度，博大家的同情，装自己患病\n甚者还有无病呻吟的听众\n\n虚假编造的内容来博得我们同情，骗赞的行为既好笑又好气\n\n面对很多人的不理解，真正的抑郁症患者往往很难得到有效的治疗\n但若我们之中真的有希望通过“音乐的力量”去治疗的听众\n希望大家多给ta一点关爱和理解\n\n生活不会事事如意，没有人会永远保持乐观\n\n以丧治丧，听完这些歌，讲完这些故事，拯救不快乐的你\n\n明天，用更多的热情，去迎接全新的生活\n\n注 | 部分文段节选自百度百科“丧文化”、“抑郁症”', '华语 夜晚 治愈', 'admin');
INSERT INTO `song_list` VALUES ('3', '韩语节奏 | 清晨的一支强心剂！', '/img/songListPic/1608307636416300.jpg', '音乐就像是强心剂，总能在我们疲惫的时候注入能量，唤醒我们的活力。只要节奏感强，不管什么类型的音乐，都能有一种让人想跟着一起动起来的魔力。节奏强劲但是不刺耳，这样的音乐总能轻易地捕获人心，让我们一起来体验这场耳膜冲击的洗礼吧，什么都别说了，戴上耳机动起来，就现在！', '韩语', '尔听°');
INSERT INTO `song_list` VALUES ('5', '要努力呀，用自己喜欢的方式让生活和颜悦色', '/img/songListPic/1608617277684109951165549520393.jpg', '我要把所有的夜归还给星河，\n把所有的春光归还给疏疏篱落，\n把所有的慵慵沉迷与不前，\n归还给过去的我。\n明日之我，\n胸中有丘壑，\n立马振山河。', '华语 流行 治愈', '柠檬木有枝');
INSERT INTO `song_list` VALUES ('6', '精选粤语歌 怀念旧时光', '/img/songListPic/1608616092833300 (1).jpg', '昔日经典，再度重温，喜欢听歌，且偏爱老歌，怀念以前的旧时光。', '粤语 流行 经典', '小Q');
INSERT INTO `song_list` VALUES ('7', '初代到泽塔经典BGM和主题曲', '/img/songListPic/1608613745277300 (2).jpg', '全部一个一个找一个一个认真排列的，持续更新中，已更新到泰迦剧场版，等泽塔音乐集出了会更新泽塔', '日语 流行 BGM', '圣地亚哥老蝙蝠');
INSERT INTO `song_list` VALUES ('8', 'Jony J,Tizzy T和更多好听的|乐迷雷达', '/img/songListPic/16086178067451383185640161008.jpg', '你最爱的歌手在这里', '华语', '私人音乐雷达');
INSERT INTO `song_list` VALUES ('9', '神秘歌友推荐你听《美好事物》|神秘雷达', '/img/songListPic/160861809343718252992533065802.jpg', '雷达星球上的神秘歌友\n带你进入TA的音乐世界\n猜猜看 神秘歌友 TA是谁', '治愈', '私人音乐雷达');
INSERT INTO `song_list` VALUES ('10', '睡前治愈：小猪佩奇为你唱晚安曲', '/img/songListPic/1608618574412300 (3).jpg', '晚安佩琪，晚安爸爸，晚安妈妈，晚安乔治，晚安猪猪世界。\n该歌单除了收录小猪佩奇首张个人专辑《My First Album》的歌曲之外，还收录了多首欧美经典摇篮曲，轻轻柔柔的旋律与歌声，十分适合安抚宝贝入睡。', '欧美 治愈', '亲子专区官方账号');
INSERT INTO `song_list` VALUES ('11', '日系治愈 ：惊心动魄动漫原声带', '/img/songListPic/1608619037882T002R300x300M000002HDtrl04xqKd_1.jpg', '日漫之所以有很多人关注，那是因为剧情很精彩，赶紧收藏吧', '日语 治愈', '笙箫旧梦');
INSERT INTO `song_list` VALUES ('12', '【华语伤感】夜晚我终于可以哭了', '/img/songListPic/songList.png', '曾经也有一个笑容出现在我的生命里，可是最后还是如雾般消散，而那个笑容，就成为我心中深深埋藏的一条湍急河流，无法泅渡，那河流的声音，就成为我每日每夜绝望的歌唱。', '华语', '小T');
INSERT INTO `song_list` VALUES ('13', '11', '/img/songListPic/songList.png', '11', '11', '11');
INSERT INTO `song_list` VALUES ('14', '22', '/img/songListPic/songList.png', '22', '22', '22');
INSERT INTO `song_list` VALUES ('15', '33', '/img/songListPic/songList.png', '33', '33', '33');
INSERT INTO `song_list` VALUES ('16', '44', '/img/songListPic/songList.png', '44', '44', '44');
INSERT INTO `song_list` VALUES ('17', '55', '/img/songListPic/songList.png', '55', '55', '55');
INSERT INTO `song_list` VALUES ('18', '说唱｜甜度爆表的旋律说唱', '/img/songListPic/1613744259589300(4).jpg', '遇见你之前 我是朝菌 不知晦朔 遇见你之后 我是大椿 以八千岁为春 八千岁为秋.', '国语 情歌 嘻哈', '神仙妹妹');
INSERT INTO `song_list` VALUES ('19', '66', '/img/songListPic/songList.png', '666', '华语', 'admin');
INSERT INTO `song_list` VALUES ('20', '77', '/img/songListPic/songList.png', '777', '华语', 'admin');
INSERT INTO `song_list` VALUES ('21', '888', '/img/songListPic/songList.png', '88888', '华语', 'admin');
INSERT INTO `song_list` VALUES ('22', '999', '/img/songListPic/songList.png', '9999', '华语', 'admin');
INSERT INTO `song_list` VALUES ('23', '1010', '/img/songListPic/songList.png', '1010', '华语', 'admin');

-- ----------------------------
-- Table structure for up
-- ----------------------------
DROP TABLE IF EXISTS `up`;
CREATE TABLE `up` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `consumer_id` int(11) DEFAULT NULL COMMENT '用户id',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `like_status` int(11) DEFAULT NULL COMMENT '点赞的状态（0取消赞1已赞）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up
-- ----------------------------
INSERT INTO `up` VALUES ('1', '2', '2', '0');
INSERT INTO `up` VALUES ('2', '2', '1', '0');
INSERT INTO `up` VALUES ('3', '1', '1', '1');
INSERT INTO `up` VALUES ('4', '1', '2', '0');
INSERT INTO `up` VALUES ('5', '1', '3', '1');
INSERT INTO `up` VALUES ('6', '1', '4', '0');
INSERT INTO `up` VALUES ('7', '1', '5', '1');
INSERT INTO `up` VALUES ('8', '17', '1', '0');
INSERT INTO `up` VALUES ('9', '18', '1', '0');
INSERT INTO `up` VALUES ('10', '18', '2', '1');
INSERT INTO `up` VALUES ('11', '18', '3', '0');
INSERT INTO `up` VALUES ('12', '18', '8', '0');
