/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50037
Source Host           : localhost:3306
Source Database       : jeewx-os

Target Server Type    : MYSQL
Target Server Version : 50037
File Encoding         : 65001

Date: 2018-07-11 18:25:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alipay_account
-- ----------------------------
DROP TABLE IF EXISTS `alipay_account`;
CREATE TABLE `alipay_account` (
  `id` varchar(50) NOT NULL,
  `accont_name` varchar(100) default NULL COMMENT '名称',
  `appid` varchar(255) default NULL COMMENT '企业号标识CorpID',
  `rsa_private_key` varchar(2000) default NULL COMMENT '管理组凭证密钥Secret',
  `alipay_public_key` varchar(2000) default NULL COMMENT 'AccessToken',
  `account_desc` varchar(300) default NULL COMMENT '描述',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  `conversation_secret` varchar(255) default NULL,
  `PUBLIC_KEY` varchar(2000) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_corpid` (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号信息表';

-- ----------------------------
-- Records of alipay_account
-- ----------------------------
INSERT INTO `alipay_account` VALUES ('402880a94742e24e014742e8942b0002', 'jeewx支付窗', '2015121500981210', '?', '?', '1', null, null, null, null, null, null, null, '?');

-- ----------------------------
-- Table structure for alipay_autoresponse
-- ----------------------------
DROP TABLE IF EXISTS `alipay_autoresponse`;
CREATE TABLE `alipay_autoresponse` (
  `id` varchar(100) NOT NULL,
  `key_word` varchar(200) default NULL COMMENT '关键字',
  `res_content` varchar(500) default NULL COMMENT '回复内容',
  `msg_type` varchar(100) default NULL COMMENT '消息类型',
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `accountid` varchar(100) default NULL COMMENT '微信账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`),
  KEY `idx_accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键字管理';

-- ----------------------------
-- Records of alipay_autoresponse
-- ----------------------------
INSERT INTO `alipay_autoresponse` VALUES ('15314E56DCEF4D4BB2583B52E3B3C22F', '你好', 'F65B42281D1E46E69FBEAEEC43587F6D', 'text', 'nihao', '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 16:24:29', null, null, null);
INSERT INTO `alipay_autoresponse` VALUES ('4E08CEFC43A94084A4E5146420E4CDBC', 'nihao', 'F65B42281D1E46E69FBEAEEC43587F6D', 'text', 'nihao', '402880a94742e24e014742e8942b0002', null, null, '2016-07-07 12:05:46', null, null, null);
INSERT INTO `alipay_autoresponse` VALUES ('B1488D84B451472980425E65E1A9D2D1', 'jeecg', '7A1B95B8B5144E3B8D97BDB29C973538', 'news', 'JEECG支付宝图文', '402880a94742e24e014742e8942b0002', null, null, '2016-06-29 23:08:26', null, null, null);

-- ----------------------------
-- Table structure for alipay_autoresponse_default
-- ----------------------------
DROP TABLE IF EXISTS `alipay_autoresponse_default`;
CREATE TABLE `alipay_autoresponse_default` (
  `id` varchar(50) NOT NULL COMMENT '主键Id',
  `templatename` varchar(200) NOT NULL COMMENT '模板名称',
  `templateid` varchar(50) NOT NULL COMMENT '模板Id',
  `msgtype` varchar(40) default NULL COMMENT '消息类型',
  `accountid` varchar(40) default NULL COMMENT '微信账号Id',
  `iswork` varchar(10) default NULL COMMENT '是否启用',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='默认关键字回复';

-- ----------------------------
-- Records of alipay_autoresponse_default
-- ----------------------------
INSERT INTO `alipay_autoresponse_default` VALUES ('8a792db34fdf51b3014fdfb22e87004f', '默认回复语', '8a792db34fdf51b3014fdfb1fdb3004c', 'text', '402880a94742e24e014742e8942b0002', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for alipay_gzentity
-- ----------------------------
DROP TABLE IF EXISTS `alipay_gzentity`;
CREATE TABLE `alipay_gzentity` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `template_id` varchar(100) default NULL COMMENT '模板id',
  `template_type` varchar(100) default NULL COMMENT '类型 文本_text,图文_news',
  `is_work` varchar(100) default NULL COMMENT '是否启用 未启用_0,启用_1',
  `accountid` varchar(100) default NULL COMMENT '微信账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注回复';

-- ----------------------------
-- Records of alipay_gzentity
-- ----------------------------
INSERT INTO `alipay_gzentity` VALUES ('402880a94936f3ee0149370671400001', '关注回复语', '8a792db34fdf51b3014fdfb1fdb3004c', 'text', '1', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `alipay_gzentity` VALUES ('4B535E499E3342E5AFC94C2D72CA082E', '支付宝测试图文', 'F47A1A912AE345589CB5E1E87202FF7B', 'news', '0', '402880a94742e24e014742e8942b0002', null, null, '2016-06-29 23:09:15', null, null, null);

-- ----------------------------
-- Table structure for alipay_gzuserinfo
-- ----------------------------
DROP TABLE IF EXISTS `alipay_gzuserinfo`;
CREATE TABLE `alipay_gzuserinfo` (
  `id` varchar(100) character set gbk NOT NULL COMMENT '主键',
  `userid` varchar(100) default NULL COMMENT '账号',
  `name` varchar(100) default NULL COMMENT '姓名',
  `department` varchar(200) default NULL COMMENT '部门',
  `position` varchar(100) default NULL COMMENT '职位',
  `mobile` varchar(100) default NULL COMMENT '电话',
  `province` varchar(100) default NULL COMMENT '省份',
  `gender` varchar(100) default NULL COMMENT '性别gender=1表示男，=0表示女',
  `email` varchar(400) default NULL COMMENT '邮箱',
  `weixinid` varchar(100) default NULL COMMENT '微信号',
  `avatar` varchar(100) default NULL COMMENT '头像url',
  `subscribe_status` varchar(100) default NULL COMMENT '关注状态: 1=已关注，2=已冻结，4=未关注',
  `subscribe_time` datetime default NULL COMMENT '关注时间',
  `accountid` varchar(100) default NULL COMMENT '微信账号ID',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户';

-- ----------------------------
-- Records of alipay_gzuserinfo
-- ----------------------------
INSERT INTO `alipay_gzuserinfo` VALUES ('1A7FF1B75285465DBA40F0C70F5A71E8', '20881031842092627793459920019496', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('348D4981D9BE45679BEEF80C4B4143F7', '20880083282343625743082342419456', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('3E728B2D1CD34970BDE02BF2CDE90A51', '20881020743943595881371201819418', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('4AD072326053422FBF8C3386EDCC8CAA', '20881020743943883608363651819482', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('609C15B2CD9847EDBD39316AC68DC2A9', '20880020502862901156333911019474', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('676FA11526F343B6B3D0F640502864DF', '20880059502513400130289910719407', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('680F8B6DB78B435D9BB13C27F37D804E', '20880062477199306806543630119465', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('7697E50079684FB0A5285CCDEF6724C5', '20880017519788123843699093119431', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('8324E98277C042FA8331540DA145B2EE', '20880054849663244665282921619448', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('881691286CB84D58B696F3D641E22EEE', '20880054197230107528206912619490', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('952A264667154F2EB1ED849F15C08B2E', '20880074330486915983138131419414', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('AA5AD0868B0949CD803EC39F02B8F02F', '20880083002078558077811642819460', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('AC2DCF611F8D470CB7E0F342E9BB42D9', '20880074692262166946381440319403', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('BF2E07F76B814341854E5070D7482180', '20881062565070899752701612719459', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('CD10799649434381AA4292AAC67B50D0', '20880074330493508920562121419446', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('E131E911FF434ABDAB50B85C015063A9', '20880020502872897336717011019410', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('F3139DD1BC6D4D5794707B780CD096FC', '20881011548320625084538750519437', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:01', null, null, null);
INSERT INTO `alipay_gzuserinfo` VALUES ('F641D6DF5CCA42B3835F4EB7F832D73E', '20880006335185776137228431719449', null, null, null, null, null, null, null, null, null, '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 23:11:02', null, null, null);

-- ----------------------------
-- Table structure for alipay_menu
-- ----------------------------
DROP TABLE IF EXISTS `alipay_menu`;
CREATE TABLE `alipay_menu` (
  `ID` varchar(32) NOT NULL,
  `agent_id` varchar(255) default NULL COMMENT '应用主键ID',
  `menu_name` varchar(255) default NULL COMMENT '菜单标题',
  `menu_type` varchar(255) default NULL COMMENT '菜单类型',
  `menu_key` varchar(255) default NULL COMMENT '菜单KEY',
  `orders` varchar(10) default NULL COMMENT '菜单位置',
  `msg_type` varchar(255) default NULL COMMENT '响应消息类型',
  `template_id` varchar(255) default NULL COMMENT '关联素材ID',
  `url` varchar(1000) default NULL COMMENT '网页链接',
  `father_id` varchar(32) default NULL COMMENT '父ID',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `menu_key` (`menu_key`),
  UNIQUE KEY `order` (`orders`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义菜单表';

-- ----------------------------
-- Records of alipay_menu
-- ----------------------------
INSERT INTO `alipay_menu` VALUES ('050DD02B4E8A4B06BDE8E6FE1CECCF0F', null, '百度', 'link', '13', '13', 'text', 'F214C903F8444BADA9F974327B726342', 'http://www.baidu.com', '1907D8112FD7453BBCDDDEF1C7FF7020', null, null, '2016-07-14 11:04:21', null, null, null);
INSERT INTO `alipay_menu` VALUES ('1907D8112FD7453BBCDDDEF1C7FF7020', null, '北京国炬', 'out', '1', '1', 'text', 'F214C903F8444BADA9F974327B726342', '', '', null, null, '2016-07-14 09:34:33', null, null, null);
INSERT INTO `alipay_menu` VALUES ('7D395F8A0BA140E7A810A90724AEA1B4', null, '触发文本', 'out', '21', '21', 'text', 'ff808081538e81b40153a172bfcc0070', '', 'B9AAA5D38079466DA112080B221970CF', null, null, '2016-07-07 11:54:10', null, null, null);
INSERT INTO `alipay_menu` VALUES ('9B6E04A2D594443383A3036E5A4FE6B4', null, '触发图文', 'out', '22', '22', 'news', '7A1B95B8B5144E3B8D97BDB29C973538', '', 'B9AAA5D38079466DA112080B221970CF', null, null, '2016-07-07 11:54:42', null, null, null);
INSERT INTO `alipay_menu` VALUES ('B9AAA5D38079466DA112080B221970CF', null, '触发菜单', 'out', '2', '2', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', '', '', null, null, null, null, null, null);
INSERT INTO `alipay_menu` VALUES ('EFF7B7D2662946AFA2DC9E230D8D5208', null, '捷微产品', 'out', '11', '11', 'text', 'B1AB9EE54A4542B5954F389A01113F53', 'http://www.jeewx.com', '1907D8112FD7453BBCDDDEF1C7FF7020', null, null, '2016-07-14 09:37:05', null, null, null);

-- ----------------------------
-- Table structure for alipay_messagelog
-- ----------------------------
DROP TABLE IF EXISTS `alipay_messagelog`;
CREATE TABLE `alipay_messagelog` (
  `id` varchar(200) NOT NULL,
  `message_type` varchar(50) default NULL,
  `content_id` varchar(500) default NULL,
  `receive_message` varchar(200) default NULL,
  `create_date` datetime default NULL,
  `message_content` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alipay_messagelog
-- ----------------------------

-- ----------------------------
-- Table structure for alipay_newsitem
-- ----------------------------
DROP TABLE IF EXISTS `alipay_newsitem`;
CREATE TABLE `alipay_newsitem` (
  `id` varchar(100) NOT NULL,
  `title` varchar(300) default NULL COMMENT '标题',
  `author` varchar(100) default NULL COMMENT '作者',
  `image_path` varchar(100) default NULL COMMENT '图片路径',
  `content` longtext COMMENT '内容',
  `templateid` varchar(100) default NULL COMMENT '图文模板id',
  `description` varchar(400) default NULL COMMENT '摘要',
  `order_no` varchar(100) default NULL COMMENT '新闻顺序',
  `url` varchar(100) default NULL COMMENT '消息内容的url',
  `hdid` varchar(100) default NULL COMMENT '活动id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文素材新闻';

-- ----------------------------
-- Records of alipay_newsitem
-- ----------------------------
INSERT INTO `alipay_newsitem` VALUES ('18342D4E58884D55B70D93C1D3F2A24A', '12', '12', 'upload/files/1348502772_8826.gif', '<p>121212</p>', '0A1776E6FE6F4AFB9CF558175AAF495D', '12', '1', '', null, null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('7F2A47FF49684BD29E51339C4EFDFF05', 'jeecg图文1', 'jeecg', 'upload/files/jeecg_scott.jpg', '<p>JEECG是一款基于代码生成器的J2EE快速开发平</p>', 'F47A1A912AE345589CB5E1E87202FF7B', 'jeecg支付服务窗图文', '1', '', null, null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('832F2A63469A4B438C7A2280BA8E9973', ' jeecg', ' jeecg', 'upload/files/ppx01814elfynejrpjjhm47.png', '<p><span style=\"color: rgb(65, 131, 196); font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; font-size: 24px; line-height: 31.92px; background-color: rgb(250, 250, 250);\">&nbsp;</span><a href=\"http://git.oschina.net/jeecg/jeecg\" class=\"repository\" title=\"jeecg\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; display: inline-block; max-width: 450px; vertical-align: text-bottom; text-overflow: ellipsis; overflow: hidden; font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; font-size: 24px; line-height: 31.92px; padding-bottom: 0px; cursor: pointer !important; background: rgb(250, 250, 250);\">jeecg</a></p><p><img src=\"http://localhost/jeewx/upload/ueditor/20161216/27671481901651392.png\" title=\"ppx01814elfynejrpjjhm47.png\"/></p>', '7A1B95B8B5144E3B8D97BDB29C973538', ' jeecg jeecg', '1', 'http://www.baidu.com', null, null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('8a792db34fdf51b3014fdfa41353002e', 'test', 'test', 'upload/files/2008822103119443_2.jpg', '<ul style=\"padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\" class=\" list-paddingleft-2\"><li><p><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;;\"><strong>相册页面</strong></span><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;;\">&nbsp;&nbsp;</span><span style=\"margin: 0px; padding: 0px; font-size: 12px; font-family: &#39;Microsoft YaHei&#39;;\">http://localhost/jeecg-p3-web/p3test.do?page&amp;url=oa/test/basic_gallery.html</span></p></li></ul><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\"><br/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\"><br/></p><p><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;;\"><img src=\"http://img.blog.csdn.net/20160412171725458?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center\" alt=\"\" style=\"border: none; max-width: 100%;\"/><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\"><br/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\"><br/></p><ul style=\"padding: 0px; color: rgb(51, 51, 51); font-family: Arial; font-size: 14px; line-height: 26px; white-space: normal; background-color: rgb(255, 255, 255);\" class=\" list-paddingleft-2\"><li><p><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;;\"><strong>联系人</strong>&nbsp;&nbsp;</span><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;;\"><span style=\"margin: 0px; padding: 0px; font-size: 12px;\">http://localhost/jeecg-p3-web/p3test.do?page&amp;url=oa/test/contacts.html</span></span></p></li></ul><p><span style=\"margin: 0px; padding: 0px; font-family: &#39;Microsoft YaHei&#39;; font-size: 12px;\"><img src=\"http://img.blog.csdn.net/20160412171812709?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center\" alt=\"\" style=\"border: none; max-width: 100%;\"/></span></p><p><br/></p>', '8a792db34fdf51b3014fdfa3c6aa002c', '111', '1', '', '', null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('BC89EDABF05E4678A7DC46087A8B1FBD', '1231', '123123', 'upload/files/70g58PICeGn.jpg', '<p>123<br/></p>', '3D4ABC0B6B5F46EA9A53311FD9E871DA', '123', '1', '', null, null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('ff808081538e81b40153a1602d3c006b', '2323', '23', 'upload/files/20160323104906i8kA3KPF.jpg', '<p>2323</p>', 'ff808081538e81b401539bb4b1c80034', '23', '23', '23', '', null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('ff808081538e81b40153a17b319a0076', 'sss', 'ddddd', 'upload/files/20160323111823UttemyYU.png', '<p>ssssss</p>', 'ff808081538e81b40153a1787a020074', 'jjjjjj', '11', '	http://www.zstobacco.com.cn:9091/tobacco-marketing/f/wxcp?verify&a=37', '', null, null, null, null, null, null);
INSERT INTO `alipay_newsitem` VALUES ('FFF442C7C80D4052B08E37FF4F96F3C0', 'jeewx', 'jeewx', 'upload/files/profile_small.jpg', '<p>jeewxjeewxjeewx</p><p><img src=\"http://localhost/jeewx/upload/ueditor/20161216/97461481901685451.jpg\" title=\"profile_small.jpg\"/></p>', '7A1B95B8B5144E3B8D97BDB29C973538', 'jeewx', '2', '', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for alipay_newstemplate
-- ----------------------------
DROP TABLE IF EXISTS `alipay_newstemplate`;
CREATE TABLE `alipay_newstemplate` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(200) default NULL COMMENT '模板名称',
  `template_type` varchar(100) default NULL COMMENT '模板类型',
  `accountid` varchar(100) default NULL COMMENT '微信企业号账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文素材模板';

-- ----------------------------
-- Records of alipay_newstemplate
-- ----------------------------
INSERT INTO `alipay_newstemplate` VALUES ('7A1B95B8B5144E3B8D97BDB29C973538', '支付窗图文', null, '402880a94742e24e014742e8942b0002', null, null, '2016-06-16 21:49:50', null, null, null);

-- ----------------------------
-- Table structure for alipay_receivetext
-- ----------------------------
DROP TABLE IF EXISTS `alipay_receivetext`;
CREATE TABLE `alipay_receivetext` (
  `id` varchar(100) NOT NULL,
  `tousername` varchar(100) NOT NULL COMMENT '开发者微信号',
  `fromusername` varchar(100) default NULL COMMENT '发送方帐号（一个OpenID）',
  `createtime` timestamp NULL default CURRENT_TIMESTAMP COMMENT '消息创建时间 （整型）',
  `msgtype` varchar(100) default NULL COMMENT '消息类型（text/image/location/link）',
  `msgid` varchar(100) default NULL COMMENT '消息id，64位整型',
  `content` varchar(400) default NULL COMMENT '消息内容',
  `response` varchar(10) default NULL COMMENT '是否回复',
  `rescontent` varchar(400) default NULL COMMENT '回复内容',
  `nickname` varchar(100) default NULL COMMENT '用户昵称',
  `accountid` varchar(40) default NULL COMMENT '微信账号Id',
  `agent_id` varchar(200) default NULL COMMENT '应用ID(微信)',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本消息';

-- ----------------------------
-- Records of alipay_receivetext
-- ----------------------------
INSERT INTO `alipay_receivetext` VALUES ('15E1740AC7CD4932A585D954BFBD62FA', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:32:25', '文本消息', null, 'jeewx', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('1B87F1CB34AB4404B89D24270652E11D', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:22:59', '文本消息', null, 'nihao', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('1D95511136D446F4ADADCAF0269A50ED', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:34:11', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('2BEB7D3014BB45788426426E00E4F826', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:31:38', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('456FADF59BA9426E918774244B5A6271', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:34:13', '文本消息', null, '触发文本', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('4EBF4B4B9E5D48979CED14133A0084A7', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:23:04', '文本消息', null, 'jeecg', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('5875496DAEC349469B1527B99E24AEC6', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 18:29:49', '文本消息', null, '你好', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('688BE88D5C9D4338B462C0E9C2EED79F', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 16:24:16', '文本消息', null, '你好', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('705AF89B7AC74C9AA7186C1FB5F0A191', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:32:07', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('7EECFCEBBF094C75B14C15FBB5C9C47C', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:32:09', '文本消息', null, '捷微官网', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('80C5E7B0FCAF4388BC6D044478A2D8E5', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:32:21', '文本消息', null, 'jeecg', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('A25BAB80B918413480817CD454788D12', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:34:08', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('A2765C2E61F1485AA5BD90B0D9E7A29B', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 16:25:28', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('A5D8A523E34E4CAEB341861230AE403E', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:36:13', '文本消息', null, '捷微产品', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('AB93899B9B1C4BCCA75D5224F06EA626', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:34:19', '文本消息', null, '捷微官网', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('C52F59EAE52F46DD961A6F3E0CED4190', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 16:26:03', '文本消息', null, '用户关注', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('C81412D555D748CDA1E1942490AD9D67', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:32:18', '文本消息', null, '捷微官网', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('CA7368B20029427C96316C4AB191C19B', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 16:26:37', '文本消息', null, '你好', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('D1701D132BA64AF298C4A125F42B82A9', 'jeecg服务窗', '20880062477199306806543630119465', '2016-12-16 23:34:16', '文本消息', null, '触发图文', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);
INSERT INTO `alipay_receivetext` VALUES ('F23306EEFF6A48F0BC7F9311B5477BC0', 'jeewx 演示服务窗', '20880062477199306806543630119465', '2016-12-20 16:24:36', '文本消息', null, '你好', '回复成功', null, null, '402880a94742e24e014742e8942b0002', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for alipay_receivetext_content
-- ----------------------------
DROP TABLE IF EXISTS `alipay_receivetext_content`;
CREATE TABLE `alipay_receivetext_content` (
  `id` varchar(100) NOT NULL,
  `receivetext_id` varchar(100) NOT NULL COMMENT '用户消息表 id',
  `content` varchar(400) default NULL COMMENT '消息内容',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息详情';

-- ----------------------------
-- Records of alipay_receivetext_content
-- ----------------------------
INSERT INTO `alipay_receivetext_content` VALUES ('B558A45827A6444797B6CB380D9655B2', '1B87F1CB34AB4404B89D24270652E11D', '9999', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for alipay_texttemplate
-- ----------------------------
DROP TABLE IF EXISTS `alipay_texttemplate`;
CREATE TABLE `alipay_texttemplate` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(200) NOT NULL COMMENT '模板名称',
  `template_content` varchar(500) NOT NULL COMMENT '模板内容',
  `accountid` varchar(100) default NULL COMMENT '微信企业账户id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本模板';

-- ----------------------------
-- Records of alipay_texttemplate
-- ----------------------------
INSERT INTO `alipay_texttemplate` VALUES ('8a792db34fdf51b3014fdfa391fd002a', '素材一句话冠亚军', '测试素材很干净', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `alipay_texttemplate` VALUES ('8a792db34fdf51b3014fdfb196200049', '验证回复语', '验证回复语', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `alipay_texttemplate` VALUES ('8a792db34fdf51b3014fdfb1fdb3004c', '关注回复语', '关注回复语，欢迎你关注jeecg支付服务窗', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `alipay_texttemplate` VALUES ('B1AB9EE54A4542B5954F389A01113F53', 'JEEWX产品介绍', 'Jeewx是一款开源、免费的微信管家系统（多触点管理平台）。采用JAVA语言，支持微信公众号、微信企业号、支付宝服务窗、QQ公众号、微博账号等多触点管理。Jeewx实现了微信、支付窗、微信企业号、微博等触点的基础管理功能，便于用户二次开发。\n官网：www.jeewx.com\nQQ群：289709451', '402880a94742e24e014742e8942b0002', null, null, '2016-12-16 23:34:57', null, null, null);
INSERT INTO `alipay_texttemplate` VALUES ('F65B42281D1E46E69FBEAEEC43587F6D', 'nihao', 'nihao你好，欢迎你关注 Jeewx 支付宝服务窗 ！！！', '402880a94742e24e014742e8942b0002', null, null, '2016-03-25 15:46:48', null, null, null);
INSERT INTO `alipay_texttemplate` VALUES ('ff808081538e81b40153a172bfcc0070', 'JEECG产品介绍', 'JEECG是一款基于代码生成器的J2EE快速开发平台，开源界“小普元”超越传统商业企业级开发平台。引领新的开发模式(Online Coding模式(自定义表单);代码生成器模式;手工MERGE智能开发)， 可以帮助解决Java项目60%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省人力成本，同时又不失灵活性。具备：表单配置能力（无需编码）、移动配置能力、工作流配置能力、报表配置能力（支持移动端）、插件开发能力（可插拔） http://www.guojusoft.com ', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for cgform_button
-- ----------------------------
DROP TABLE IF EXISTS `cgform_button`;
CREATE TABLE `cgform_button` (
  `ID` varchar(32) NOT NULL,
  `BUTTON_CODE` varchar(50) default NULL,
  `button_icon` varchar(20) default NULL,
  `BUTTON_NAME` varchar(50) default NULL,
  `BUTTON_STATUS` varchar(2) default NULL,
  `BUTTON_STYLE` varchar(20) default NULL,
  `EXP` varchar(255) default NULL,
  `FORM_ID` varchar(32) default NULL,
  `OPT_TYPE` varchar(20) default NULL,
  `order_num` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_button
-- ----------------------------

-- ----------------------------
-- Table structure for cgform_button_sql
-- ----------------------------
DROP TABLE IF EXISTS `cgform_button_sql`;
CREATE TABLE `cgform_button_sql` (
  `ID` varchar(32) NOT NULL,
  `BUTTON_CODE` varchar(50) default NULL,
  `CGB_SQL` tinyblob,
  `CGB_SQL_NAME` varchar(50) default NULL,
  `CONTENT` longtext,
  `FORM_ID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_button_sql
-- ----------------------------

-- ----------------------------
-- Table structure for cgform_enhance_js
-- ----------------------------
DROP TABLE IF EXISTS `cgform_enhance_js`;
CREATE TABLE `cgform_enhance_js` (
  `ID` varchar(32) NOT NULL,
  `CG_JS` blob,
  `CG_JS_TYPE` varchar(20) default NULL,
  `CONTENT` longtext,
  `FORM_ID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_enhance_js
-- ----------------------------

-- ----------------------------
-- Table structure for cgform_field
-- ----------------------------
DROP TABLE IF EXISTS `cgform_field`;
CREATE TABLE `cgform_field` (
  `id` varchar(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `create_by` varchar(255) default NULL,
  `create_date` datetime default NULL,
  `create_name` varchar(32) default NULL,
  `dict_field` varchar(100) default NULL,
  `dict_table` varchar(100) default NULL,
  `dict_text` varchar(100) default NULL,
  `field_default` varchar(20) default NULL,
  `field_href` varchar(200) default NULL,
  `field_length` int(11) default NULL,
  `field_name` varchar(32) NOT NULL,
  `field_valid_type` varchar(10) default NULL,
  `is_key` varchar(2) default NULL,
  `is_null` varchar(5) default NULL,
  `is_query` varchar(5) default NULL,
  `is_show` varchar(5) default NULL,
  `is_show_list` varchar(5) default NULL,
  `length` int(11) NOT NULL,
  `main_field` varchar(100) default NULL,
  `main_table` varchar(100) default NULL,
  `old_field_name` varchar(32) default NULL,
  `order_num` int(11) default NULL,
  `point_length` int(11) default NULL,
  `query_mode` varchar(10) default NULL,
  `show_type` varchar(10) default NULL,
  `type` varchar(32) NOT NULL,
  `update_by` varchar(32) default NULL,
  `update_date` datetime default NULL,
  `update_name` varchar(32) default NULL,
  `table_id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_iwtkke1oigq9ukafldrovslx6` (`table_id`),
  CONSTRAINT `FK_iwtkke1oigq9ukafldrovslx6` FOREIGN KEY (`table_id`) REFERENCES `cgform_head` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_field
-- ----------------------------
INSERT INTO `cgform_field` VALUES ('402881e446676a0601466770878c0002', '主键', 'admin', '2014-06-04 23:12:46', '管理员', '', '', '', '', '', '0', 'id', '', 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e446676a060146677087df0003', '关键字', 'admin', '2014-06-04 23:12:47', '管理员', '', '', '', '', '', '120', 'keyword', '', 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'key', '2', '0', 'single', 'text', 'string', 'admin', '2014-06-04 23:18:27', '管理员', '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e446676a060146677087e20004', '类长名', 'admin', '2014-06-04 23:12:47', '管理员', '', '', '', '', '', '120', 'classname', '', 'N', 'N', 'N', 'Y', 'Y', '100', '', '', 'classname', '3', '0', 'single', 'text', 'string', null, null, null, '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e446676a060146677087e50005', '微信公众帐号', 'admin', '2014-06-04 23:12:47', '管理员', '', '', '', '', '', '120', 'accountid', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accountid', '4', '0', 'single', 'text', 'string', null, null, null, '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e446676a0601466775b8fc0007', '功能名称', 'admin', '2014-06-04 23:18:27', '管理员', '', '', '', '', '', '120', 'name', '', 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'name', '5', '0', 'single', 'text', 'string', null, null, null, '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e446676a0601466775b9000008', '功能描述', 'admin', '2014-06-04 23:18:27', '管理员', '', '', '', '', '', '120', 'content', '', 'N', 'Y', 'N', 'Y', 'Y', '300', '', '', 'content', '6', '0', 'single', 'text', 'string', null, null, null, '402881e446676a060146677087880001');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445120008', '主键', 'admin', '2014-05-04 21:02:39', '管理员', '', '', '', '', '', '0', 'id', '', 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445ae0009', '公众帐号名称', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accountname', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accountname', '2', '0', 'single', 'text', 'string', null, null, null, '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445b5000b', '公众帐号TOKEN', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accounttoken', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accounttoken', '3', '0', 'single', 'text', 'string', 'admin', '2014-05-06 20:10:22', '管理员', '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445b8000c', '公众微信号', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accountnumber', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accountnumber', '4', '0', 'single', 'text', 'string', 'admin', '2014-05-06 20:10:22', '管理员', '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445c3000d', '公众号类型', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accounttype', '', 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'accounttype', '5', '0', 'single', 'text', 'string', 'admin', '2014-05-06 20:10:22', '管理员', '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445c7000e', '电子邮箱', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accountemail', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accountemail', '6', '0', 'single', 'text', 'string', 'admin', '2014-05-06 20:10:22', '管理员', '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845c73cb00145c75445cb000f', '公众帐号描述', 'admin', '2014-05-04 21:02:40', '管理员', '', '', '', '', '', '120', 'accountdesc', '', 'N', 'Y', 'N', 'Y', 'Y', '500', '', '', 'accountdesc', '7', '0', 'single', 'text', 'string', 'admin', '2014-05-06 20:10:22', '管理员', '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845d16cb10145d1711e730002', '公众帐号APPID', 'admin', '2014-05-06 20:10:22', '管理员', '', '', '', '', '', '120', 'accountappid', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'accountappid', '8', '0', 'single', 'text', 'string', null, null, null, '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845d16cb10145d1711e790003', '公众帐号APPSECRET', 'admin', '2014-05-06 20:10:22', '管理员', '', '', '', '', '', '120', 'accountappsecret', '', 'N', 'Y', 'N', 'Y', 'Y', '500', '', '', 'accountappsecret', '9', '0', 'single', 'text', 'string', null, null, null, '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e845d16cb10145d1711e7e0004', 'ACCESS_TOKEN', 'admin', '2014-05-06 20:10:22', '管理员', '', '', '', '', '', '120', 'accountaccesstoken', '', 'N', 'Y', 'N', 'Y', 'Y', '1000', '', '', 'accountaccesstoken', '10', '0', 'single', 'text', 'string', null, null, null, '402881e845c73cb00145c754450c0007');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f74632020009', '主键', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'id', '', 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f7463216000a', '创建人名称', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'create_name', '', 'N', 'Y', 'N', 'N', 'N', '50', '', '', 'create_name', '2', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f746321c000b', '创建日期', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'create_date', '', 'N', 'Y', 'N', 'N', 'Y', '20', '', '', 'create_date', '3', '0', 'single', 'text', 'Date', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f7463224000c', '修改人名称', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'update_name', '', 'N', 'Y', 'N', 'N', 'N', '50', '', '', 'update_name', '4', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f7463246000d', '修改日期', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'update_date', '', 'N', 'Y', 'N', 'N', 'N', '20', '', '', 'update_date', '5', '0', 'single', 'text', 'Date', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f7463250000e', '分类名称', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'name', '', 'N', 'Y', 'N', 'Y', 'Y', '50', '', '', 'name', '6', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f746326b000f', '图片路径', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'imgurl', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'imgurl', '7', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f74632720010', '分类上级ID', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'parentid', '', 'N', 'Y', 'N', 'Y', 'Y', '36', '', '', 'parentid', '8', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e946f72f8e0146f746327b0011', '卖家ID', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', '', '', '', '', '', '120', 'seller_id', '', 'N', 'Y', 'N', 'N', 'N', '36', '', '', 'seller_id', '9', '0', 'single', 'text', 'string', null, null, null, '402881e946f72f8e0146f74632010008');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed3c0002', '主键', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'id', '', 'Y', 'N', 'N', 'N', 'N', '36', '', '', 'id', '1', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed620003', '创建人名称', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'create_name', '', 'N', 'Y', 'N', 'N', 'N', '50', '', '', 'create_name', '2', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed6a0004', '创建日期', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'create_date', '', 'N', 'Y', 'N', 'N', 'N', '20', '', '', 'create_date', '3', '0', 'single', 'text', 'Date', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed880005', '修改人名称', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'update_name', '', 'N', 'Y', 'N', 'N', 'N', '50', '', '', 'update_name', '4', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed920006', '修改日期', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'update_date', '', 'N', 'Y', 'N', 'N', 'N', '20', '', '', 'update_date', '5', '0', 'single', 'text', 'Date', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ed9c0007', '标题信息', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'title', '', 'N', 'Y', 'N', 'Y', 'Y', '200', '', '', 'title', '6', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6eda50008', '标题图片', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'title_img', '', 'N', 'Y', 'N', 'Y', 'Y', '100', '', '', 'title_img', '7', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edbd0009', '商品详情', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'descriptions', '', 'N', 'Y', 'N', 'Y', 'Y', '5000', '', '', 'descriptions', '8', '0', 'single', 'text', 'Text', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edc5000a', '商品原价', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'price', '', 'N', 'Y', 'N', 'Y', 'Y', '18', '', '', 'price', '9', '2', 'single', 'text', 'double', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edcc000b', '商品现价', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'real_price', '', 'N', 'Y', 'N', 'Y', 'Y', '18', '', '', 'real_price', '10', '2', 'single', 'text', 'double', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edd4000c', '折扣', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'sale', '', 'N', 'Y', 'N', 'Y', 'Y', '18', '', '', 'sale', '11', '2', 'single', 'text', 'double', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edde000d', '销售数量', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'sell_count', '', 'N', 'Y', 'N', 'Y', 'Y', '11', '', '', 'sell_count', '12', '0', 'single', 'text', 'int', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ede5000e', '评价数量', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'discuss_count', '', 'N', 'Y', 'N', 'N', 'Y', '11', '', '', 'discuss_count', '13', '0', 'single', 'text', 'int', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edec000f', '好评数量', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'good_count', '', 'N', 'Y', 'N', 'N', 'Y', '11', '', '', 'good_count', '14', '0', 'single', 'text', 'int', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edf40010', '差评数量', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'bad_count', '', 'N', 'Y', 'N', 'N', 'Y', '11', '', '', 'bad_count', '15', '0', 'single', 'text', 'int', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6edff0011', '状态', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'statement', '', 'N', 'Y', 'N', 'N', 'Y', '32', '', '', 'statement', '16', '0', 'single', 'text', 'string', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ee1a0012', '上架时间', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'shelve_time', '', 'N', 'Y', 'N', 'N', 'Y', '32', '', '', 'shelve_time', '17', '0', 'single', 'text', 'Date', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ee220013', '下架时间', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'remove_time', '', 'N', 'Y', 'N', 'N', 'Y', '32', '', '', 'remove_time', '18', '0', 'single', 'text', 'Date', '4028d881436d514601436d5215ac0043', '2014-07-04 22:16:50', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ee280014', '快递名称', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'express_name', '', 'N', 'Y', 'N', 'Y', 'Y', '32', '', '', 'express_name', '19', '0', 'single', 'text', 'string', null, null, null, '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ee2f0015', '快递费用', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'express_price', '', 'N', 'Y', 'N', 'Y', 'Y', '18', '', '', 'express_price', '20', '2', 'single', 'text', 'double', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('402881e94701b0bc014701b6ee360016', '卖家ID', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', '', '', '', '', '', '120', 'seller_id', '', 'N', 'Y', 'N', 'Y', 'Y', '36', '', '', 'seller_id', '21', '0', 'single', 'text', 'string', '4028d881436d514601436d5215ac0043', '2014-07-04 22:14:48', 'admin', '402881e94701b0bc014701b6ed380001');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52193b012f', '主键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'id', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', null, '0', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521928012a');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219410130', '订单号', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'order_code', null, 'N', 'Y', 'Y', 'Y', 'Y', '50', '', '', null, '1', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521928012a');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219460131', '订单日期', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'order_date', null, 'N', 'Y', 'Y', 'Y', 'Y', '20', '', '', null, '2', '0', 'single', 'date', 'Date', null, null, null, '4028d881436d514601436d521928012a');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219480132', '订单金额', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'order_money', null, 'N', 'Y', 'Y', 'Y', 'Y', '10', '', '', null, '3', '3', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521928012a');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52194a0133', '备注', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'content', null, 'N', 'Y', 'Y', 'Y', 'Y', '255', '', '', null, '4', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521928012a');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219520134', '主键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'id', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', null, '0', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219550135', '请假标题', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'title', null, 'N', 'N', 'N', 'Y', 'Y', '50', '', '', null, '1', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219570136', '请假人', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'people', null, 'N', 'N', 'Y', 'Y', 'Y', '20', '', '', null, '2', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219580137', '性别', 'admin', '2014-01-07 23:28:53', '管理员', 'sex', '', null, null, null, '0', 'sex', null, 'N', 'N', 'Y', 'Y', 'Y', '10', '', '', null, '3', '0', 'single', 'list', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52195a0138', '请假开始时间', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'begindate', null, 'N', 'N', 'N', 'Y', 'Y', '0', '', '', null, '4', '0', 'group', 'date', 'Date', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52195b0139', '请假结束时间', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'enddate', null, 'N', 'N', 'N', 'Y', 'Y', '0', '', '', null, '5', '0', 'group', 'datetime', 'Date', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52195d013a', '所属部门', 'admin', '2014-01-07 23:28:53', '管理员', 'id', 't_s_depart', 'departname', null, null, '0', 'hol_dept', null, 'N', 'N', 'N', 'Y', 'Y', '32', '', '', null, '7', '0', 'single', 'list', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52195e013b', '请假原因', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'hol_reson', null, 'N', 'N', 'N', 'Y', 'Y', '255', '', '', null, '8', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521960013c', '部门审批人', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'dep_leader', null, 'N', 'N', 'N', 'Y', 'Y', '20', '', '', null, '9', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521961013d', '部门审批意见', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'content', null, 'N', 'N', 'N', 'Y', 'Y', '255', '', '', null, '10', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521963013e', '请假天数', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'day_num', null, 'N', 'Y', 'N', 'Y', 'Y', '10', '', '', null, '6', '0', 'single', 'text', 'int', null, null, null, '4028d881436d514601436d52192d012b');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52196e013f', '主键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'id', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', null, '0', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219700140', '客户名', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'name', null, 'N', 'Y', 'Y', 'Y', 'Y', '32', '', '', null, '1', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219720141', '单价', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'money', null, 'N', 'Y', 'Y', 'Y', 'Y', '10', '', '', null, '2', '2', 'group', 'text', 'double', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219740142', '外键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'fk_id', null, 'N', 'N', 'Y', 'N', 'N', '36', 'id', 'jform_order_main', null, '5', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219750143', '电话', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'telphone', null, 'N', 'Y', 'Y', 'Y', 'Y', '32', '', '', null, '4', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219770144', '性别', 'admin', '2014-01-07 23:28:53', '管理员', 'sex', '', null, null, null, '120', 'sex', null, 'N', 'Y', 'Y', 'Y', 'Y', '4', '', '', null, '3', '0', 'single', 'radio', 'string', null, null, null, '4028d881436d514601436d52192f012c');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52197b0145', '主键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'id', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', null, '0', '0', 'single', 'checkbox', 'string', null, null, null, '4028d881436d514601436d521931012d');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52197d0146', '航班号', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'ticket_code', null, 'N', 'N', 'Y', 'Y', 'Y', '100', '', '', null, '1', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521931012d');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52197f0147', '航班时间', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'tickect_date', null, 'N', 'N', 'Y', 'Y', 'Y', '10', '', '', null, '2', '0', 'single', 'datetime', 'Date', null, null, null, '4028d881436d514601436d521931012d');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219810148', '外键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '120', 'fck_id', null, 'N', 'N', 'N', 'N', 'N', '36', 'id', 'jform_order_main', null, '3', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521931012d');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219860149', '主键', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'id', null, 'Y', 'N', 'N', 'N', 'N', '36', '', '', null, '0', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521989014a', '机构合计', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'a', null, 'N', 'N', 'Y', 'Y', 'Y', '10', '', '', null, '1', '2', 'group', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52198a014b', '行政小计', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b1', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '2', '2', 'group', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52198d014c', '行政省', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b11', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '3', '0', 'group', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52198f014d', '行政市', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b12', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '4', '0', 'group', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521991014e', '行政县', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b13', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '5', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d521994014f', '事业合计', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b2', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '6', '2', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219970150', '参公小计', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b3', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '7', '2', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219990151', '参公省', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b31', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '8', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52199c0152', '参公市', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b32', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '9', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d52199e0153', '参公县', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'b33', null, 'N', 'N', 'N', 'Y', 'Y', '100', '', '', null, '10', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219a00154', '全额拨款', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'c1', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '11', '2', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219a30155', '差额拨款', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'c2', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '12', '2', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219a60156', '自收自支', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'c3', null, 'N', 'N', 'N', 'Y', 'Y', '10', '', '', null, '13', '2', 'single', 'text', 'double', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219a80157', '经费合计', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'd', null, 'N', 'N', 'Y', 'Y', 'Y', '10', '', '', null, '14', '2', 'single', 'text', 'int', null, null, null, '4028d881436d514601436d521933012e');
INSERT INTO `cgform_field` VALUES ('4028d881436d514601436d5219aa0158', '机构资质', 'admin', '2014-01-07 23:28:53', '管理员', '', '', null, null, null, '0', 'd1', null, 'N', 'N', 'N', 'Y', 'Y', '1000', '', '', null, '15', '0', 'single', 'text', 'string', null, null, null, '4028d881436d514601436d521933012e');

-- ----------------------------
-- Table structure for cgform_ftl
-- ----------------------------
DROP TABLE IF EXISTS `cgform_ftl`;
CREATE TABLE `cgform_ftl` (
  `ID` varchar(32) NOT NULL,
  `CGFORM_ID` varchar(36) NOT NULL,
  `CGFORM_NAME` varchar(100) NOT NULL,
  `CREATE_BY` varchar(36) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(32) default NULL,
  `FTL_CONTENT` longtext,
  `FTL_STATUS` varchar(50) default NULL,
  `FTL_VERSION` int(11) NOT NULL,
  `FTL_WORD_URL` varchar(200) default NULL,
  `UPDATE_BY` varchar(36) default NULL,
  `UPDATE_DATE` datetime default NULL,
  `UPDATE_NAME` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_ftl
-- ----------------------------

-- ----------------------------
-- Table structure for cgform_head
-- ----------------------------
DROP TABLE IF EXISTS `cgform_head`;
CREATE TABLE `cgform_head` (
  `id` varchar(32) NOT NULL,
  `content` varchar(200) NOT NULL,
  `create_by` varchar(32) default NULL,
  `create_date` datetime default NULL,
  `create_name` varchar(32) default NULL,
  `is_checkbox` varchar(5) NOT NULL,
  `is_dbsynch` varchar(20) NOT NULL,
  `is_pagination` varchar(5) NOT NULL,
  `is_tree` varchar(5) NOT NULL,
  `jform_type` int(11) NOT NULL,
  `jform_version` varchar(10) NOT NULL,
  `querymode` varchar(10) NOT NULL,
  `relation_type` int(11) default NULL,
  `sub_table_str` longtext,
  `table_name` varchar(20) NOT NULL,
  `update_by` varchar(32) default NULL,
  `update_date` datetime default NULL,
  `update_name` varchar(32) default NULL,
  `jform_pk_sequence` varchar(200) default NULL,
  `jform_pk_type` varchar(100) default NULL,
  `tab_order` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_head
-- ----------------------------
INSERT INTO `cgform_head` VALUES ('402881e446676a060146677087880001', '扩展接口管理', 'admin', '2014-06-04 23:12:46', '管理员', 'N', 'Y', 'Y', 'N', '1', '2', 'single', '0', null, 'weixin_expandconfig', 'admin', '2014-06-04 23:18:31', '管理员', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881e845c73cb00145c754450c0007', '微信公众帐号信息', 'admin', '2014-05-04 21:02:39', '管理员', 'Y', 'Y', 'Y', 'N', '1', '2', 'single', '0', null, 'weixin_account', 'admin', '2014-05-06 20:10:30', '管理员', null, null, null);
INSERT INTO `cgform_head` VALUES ('402881e946f72f8e0146f74632010008', '商品分类', '4028d881436d514601436d5215ac0043', '2014-07-02 21:31:51', 'admin', 'N', 'Y', 'Y', 'Y', '1', '1', 'single', '0', null, 'WEIXIN_MALL_CATEGORY', '4028d881436d514601436d5215ac0043', '2014-07-02 21:45:35', 'admin', '', 'UUID', null);
INSERT INTO `cgform_head` VALUES ('402881e94701b0bc014701b6ed380001', '商品信息', '4028d881436d514601436d5215ac0043', '2014-07-04 22:11:11', 'admin', 'Y', 'Y', 'Y', 'N', '1', '3', 'group', '0', null, 'weixin_shop_goods', '4028d881436d514601436d5215ac0043', '2014-07-04 22:23:37', 'admin', '', 'UUID', null);
INSERT INTO `cgform_head` VALUES ('4028d881436d514601436d521928012a', '订单主信息', 'admin', '2014-01-07 23:28:53', '管理员', 'N', 'Y', 'Y', 'N', '2', '57', 'group', '0', 'jform_order_ticket,jform_order_customer', 'jform_order_main', '4028d881436d514601436d5215ac0043', '2014-07-24 15:39:44', 'admin', null, null, null);
INSERT INTO `cgform_head` VALUES ('4028d881436d514601436d52192d012b', '请假单', 'admin', '2014-01-07 23:28:53', '管理员', 'N', 'Y', 'Y', 'N', '1', '51', 'group', '0', null, 'jform_leave', 'admin', '2014-01-19 10:08:17', '管理员', null, null, null);
INSERT INTO `cgform_head` VALUES ('4028d881436d514601436d52192f012c', '订单客户信息', 'admin', '2014-01-07 23:28:53', '管理员', 'Y', 'N', 'Y', 'N', '3', '16', 'single', '0', null, 'jform_order_customer', null, null, null, null, null, null);
INSERT INTO `cgform_head` VALUES ('4028d881436d514601436d521931012d', '订单机票信息', 'admin', '2014-01-07 23:28:53', '管理员', 'N', 'N', 'Y', 'N', '3', '20', 'single', '0', null, 'jform_order_ticket', null, null, null, null, null, null);
INSERT INTO `cgform_head` VALUES ('4028d881436d514601436d521933012e', '价格认证机构统计表', 'admin', '2014-01-07 23:28:53', '管理员', 'N', 'N', 'Y', 'N', '1', '3', 'group', '0', null, 'jform_price1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for cgform_uploadfiles
-- ----------------------------
DROP TABLE IF EXISTS `cgform_uploadfiles`;
CREATE TABLE `cgform_uploadfiles` (
  `CGFORM_FIELD` varchar(100) NOT NULL,
  `CGFORM_ID` varchar(36) NOT NULL,
  `CGFORM_NAME` varchar(100) NOT NULL,
  `id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_qwig8sn3okhvh4wye8nn8gdeg` (`id`),
  CONSTRAINT `FK_qwig8sn3okhvh4wye8nn8gdeg` FOREIGN KEY (`id`) REFERENCES `t_s_attachment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cgform_uploadfiles
-- ----------------------------

-- ----------------------------
-- Table structure for ck_editor
-- ----------------------------
DROP TABLE IF EXISTS `ck_editor`;
CREATE TABLE `ck_editor` (
  `ID` varchar(32) NOT NULL,
  `CONTENTS` blob,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ck_editor
-- ----------------------------
INSERT INTO `ck_editor` VALUES ('4028d881436d514601436d5219250129', 0x3C68746D6C3E3C686561643E3C7469746C653E3C2F7469746C653E3C6C696E6B20687265663D27706C75672D696E2F6561737975692F7468656D65732F64656661756C742F6561737975692E637373272069643D276561737975695468656D65272072656C3D277374796C6573686565742720747970653D27746578742F63737327202F3E3C6C696E6B20687265663D27706C75672D696E2F6561737975692F7468656D65732F69636F6E2E637373272072656C3D277374796C6573686565742720747970653D27746578742F63737327202F3E3C6C696E6B20687265663D27706C75672D696E2F6163636F7264696F6E2F6373732F6163636F7264696F6E2E637373272072656C3D277374796C6573686565742720747970653D27746578742F63737327202F3E3C6C696E6B20687265663D27706C75672D696E2F56616C6964666F726D2F6373732F7374796C652E637373272072656C3D277374796C6573686565742720747970653D27746578742F63737327202F3E3C6C696E6B20687265663D27706C75672D696E2F56616C6964666F726D2F6373732F7461626C6566726F6D2E637373272072656C3D277374796C6573686565742720747970653D27746578742F63737327202F3E3C7374796C6520747970653D27746578742F637373273E626F64797B666F6E742D73697A653A313270783B7D7461626C65207B626F726465723A31707820736F6C696420233030303030303B626F726465722D636F6C6C617073653A20636F6C6C617073653B7D7464207B626F726465723A31707820736F6C696420233030303030303B6261636B67726F756E643A77686974653B666F6E742D73697A653A313270783B666F6E742D66616D696C793A20D0C2CBCECCE53B636F6C6F723A20233333333B3C2F7374796C653E3C2F686561643E3C626F64793E3C6469763E3C703EB8BDBCFE32A3BA3C2F703E3C6831207374796C653D27746578742D616C69676E3A63656E746572273E3C7370616E207374796C653D27666F6E742D73697A653A32347078273E3C7374726F6E673EBCDBB8F1C8CFD6A4C8CBD4B1CDB3BCC6B1ED3C2F7374726F6E673E3C2F7370616E3E3C2F68313E3C703ECCEEB1A8B5A5CEBBA3A8B8C7D5C2A3A9A3BA3C696E707574206E616D653D276F72675F6E616D652720747970653D2774657874272076616C75653D27247B6F72675F6E616D653F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C703EB5A5CEBBB4FAC2EBBAC5A3BA3C696E707574206E616D653D276E756D2720747970653D2774657874272076616C75653D27247B6E756D3F69665F6578697374733F68746D6C7D27202F3E20266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703BB5A5CEBBA3BAC8CBCCEE266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B20B1A8C8D5C6DAA3BA266E6273703B266E6273703B266E6273703B266E6273703B266E6273703B20C4EA266E6273703B266E6273703B20D4C2266E6273703B266E6273703B20C8D53C2F703E3C666F726D20616374696F6E3D276367466F726D4275696C64436F6E74726F6C6C65722E646F3F736176654F72557064617465272069643D27666F726D6F626A27206D6574686F643D27706F737427206E616D653D27666F726D6F626A273E3C696E707574206E616D653D277461626C654E616D652720747970653D2768696464656E272076616C75653D27247B7461626C654E616D653F69665F6578697374733F68746D6C7D27202F3E203C696E707574206E616D653D2769642720747970653D2768696464656E272076616C75653D27247B69643F69665F6578697374733F68746D6C7D27202F3E237B6A666F726D5F68696464656E5F6669656C647D3C696E70757420747970653D2768696464656E27202F3E3C703E266E6273703B3C2F703E3C7461626C6520626F726465723D2731272063656C6C70616464696E673D2730272063656C6C73706163696E673D273027207374796C653D2777696474683A313031367078273E3C74626F64793E3C74723E3C746420726F777370616E3D2734273E3C703E266E6273703B3C2F703E3C703E266E6273703B3C2F703E3C703EBACFBCC63C2F703E3C703E266E6273703B3C2F703E3C2F74643E3C746420636F6C7370616E3D27362720726F777370616E3D2732273E3C703E266E6273703B3C2F703E3C703EC8CBCAFD3C2F703E3C2F74643E3C746420636F6C7370616E3D27352720726F777370616E3D2732273E3C703E266E6273703B3C2F703E3C703ED1A7C0FA3C2F703E3C2F74643E3C746420636F6C7370616E3D27342720726F777370616E3D2732273E3C703E266E6273703B3C2F703E3C703EC8A1B5C3B5C4A3A8C9CFB8DAA3A9D6B4D2B5D7CAB8F13C2F703E3C2F74643E3C746420636F6C7370616E3D2734273E3C703ED7A8D2B5BCBCCAF5D6B0B3C63C2F703E3C2F74643E3C2F74723E3C74723E3C746420636F6C7370616E3D2734273E3C703EA3A8BEADBCC3CFB5C1D0A1A2B9A4B3CCCFB5C1D0A3A93C2F703E3C2F74643E3C2F74723E3C74723E3C746420636F6C7370616E3D2733273E3C703ED4DAB1E0C8CBD4B13C2F703E3C2F74643E3C746420636F6C7370616E3D2732273E3C703EC6B8D3C3C8CBD4B13C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EC1D9CAB128BDE8D3C329C8CBD4B13C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB8DFD6D03C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB4F3D7A83C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB1BEBFC63C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703ED1D0BEBFC9FA3C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EC6E4CBFC3C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EBCDBB8F13C2F703E3C703EBCF8D6A4D4B13C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EBCDBB8F13C2F703E3C703EBCF8D6A4CAA63C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB8B4BACB3C2F703E3C703EB2C3B6A8D4B13C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EC6E4CBFC3C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB3F5BCB63C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703ED6D0BCB63C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EB8DFBCB63C2F703E3C2F74643E3C746420726F777370616E3D2732273E3C703EC6E4CBFC3C2F703E3C2F74643E3C2F74723E3C74723E3C74643E3C703EB1BEB5A5CEBB3C2F703E3C2F74643E3C746420636F6C7370616E3D2732273E3C703EC6E4CBFC3C2F703E3C2F74643E3C74643E3C703EB3A4C6DA3C2F703E3C2F74643E3C74643E3C703EB6CCC6DA3C2F703E3C2F74643E3C2F74723E3C74723E3C74643E3C703E41313C2F703E3C2F74643E3C74643E3C703E42313C2F703E3C2F74643E3C74643E3C703E42323C2F703E3C2F74643E3C746420636F6C7370616E3D2732273E3C703E42333C2F703E3C2F74643E3C74643E3C703E42343C2F703E3C2F74643E3C74643E3C703E42353C2F703E3C2F74643E3C74643E3C703E43313C2F703E3C2F74643E3C74643E3C703E43323C2F703E3C2F74643E3C74643E3C703E43333C2F703E3C2F74643E3C74643E3C703E43343C2F703E3C2F74643E3C74643E3C703E43353C2F703E3C2F74643E3C74643E3C703E44313C2F703E3C2F74643E3C74643E3C703E44323C2F703E3C2F74643E3C74643E3C703E44333C2F703E3C2F74643E3C74643E3C703E44343C2F703E3C2F74643E3C74643E3C703E45313C2F703E3C2F74643E3C74643E3C703E45323C2F703E3C2F74643E3C74643E3C703E45333C2F703E3C2F74643E3C74643E3C703E45343C2F703E3C2F74643E3C2F74723E3C74723E3C74643E3C703E3C696E707574206E616D653D276131272073697A653D27342720747970653D2774657874272076616C75653D27247B61313F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276231272073697A653D27342720747970653D2774657874272076616C75653D27247B62313F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276232272073697A653D27342720747970653D2774657874272076616C75653D27247B62323F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C746420636F6C7370616E3D2732273E3C703E3C696E707574206E616D653D276233272073697A653D27342720747970653D2774657874272076616C75653D27247B62333F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276234272073697A653D27342720747970653D2774657874272076616C75653D27247B62343F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276235272073697A653D27342720747970653D2774657874272076616C75653D27247B62353F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276331272073697A653D27342720747970653D2774657874272076616C75653D27247B63313F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276332272073697A653D27342720747970653D2774657874272076616C75653D27247B63323F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276333272073697A653D27342720747970653D2774657874272076616C75653D27247B63333F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276334272073697A653D27342720747970653D2774657874272076616C75653D27247B63343F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276335272073697A653D27342720747970653D2774657874272076616C75653D27247B63353F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276431272073697A653D27342720747970653D2774657874272076616C75653D27247B64313F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276432272073697A653D27342720747970653D2774657874272076616C75653D27247B64323F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276433272073697A653D27342720747970653D2774657874272076616C75653D27247B64333F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276434272073697A653D27342720747970653D2774657874272076616C75653D27247B64343F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276531272073697A653D27342720747970653D2774657874272076616C75653D27247B65313F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276532272073697A653D27342720747970653D2774657874272076616C75653D27247B65323F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276533272073697A653D27342720747970653D2774657874272076616C75653D27247B65333F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C74643E3C703E3C696E707574206E616D653D276534272073697A653D27342720747970653D2774657874272076616C75653D27247B65343F69665F6578697374733F68746D6C7D27202F3E3C2F703E3C2F74643E3C2F74723E3C74723E3C746420636F6C7370616E3D273230273E3C703E266E6273703B3C2F703E3C703ECCEEB1A8CBB5C3F7A3BA3C2F703E3C703ED2BBA1A2BACFBCC6A3A841A3A9A3BACCEEB1A8D6C1CDB3BCC6BDD8D6B9C6DAB5C4B1BEBBFAB9B9B5C4C8CBD4B1D7DCCAFDA1A33C2F703E3C703EB6FEA1A2C8CBCAFDA3BA3C2F703E3C703ED4DAB1E0C8CBD4B1A3BAB7D6B1F0B0B4D5D5BCDBB8F1C8CFD6A4BBFAB9B9B1E0D6C6C4DABCB0C6E4CBFCBEDFD3D0BCDBB8F1D6F7B9DCB2BFC3C5B1E0D6C6B5C4CAB5D3D0C8CBCAFDCCEEB1A8D4DA4231A1A24232C0B8C4DAA1A33C2F703E3C703EC6B8D3C3C8CBD4B1A3BAB0B4D5D5BEADBCDBB8F1D6F7B9DCB2BFC3C5BBF2BCDBB8F1C8CFD6A4BBFAB9B9C8CBCAC2B2BFC3C5C8CFBFC9B5C4B2A2C7A9B6A9C8FDC4EAD2D4C9CFB5C4B9A4D7F7BACFCDACB5C4C8CBD4B1A3A84233A3A9A3BBD2D4BCB0C3BBD3D0BEADB9FDBCDBB8F1D6F7B9DCB2BFC3C5BBF2BCDBB8F1C8CFD6A4BBFAB9B9C8CBCAC2B2BFC3C5C8CFBFC9B5C4C7A9B6A9BACFCDACC9D9D3DAC8FDC4EAB5C4C8CBD4B1A3A84234A3A9B7D6B1F0C0B4BDF8D0D0CDB3BCC6A1A33C2F703E3C703EC1D9CAB1A3A8BDE8D3C3A3A9C8CBD4B1A3A84235A3A9A3BACCD8D6B8CDE2C6B8B5C4C1D9CAB1B9A4A3ACBBF2D5DFB9A4D7F7B9D8CFB5B2BBD4DAB1BEB5A5CEBBC7D2CEDEB3A4C6DAC6B8D3C3BACFCDACB5C4BDE8B5F7C8CBD4B1B5C8A1A33C2F703E3C703EC8FDA1A2B1EDC4DAB8F7C0B8C4BFB9D8CFB53C2F703E3C703E413D42312B42322B42332B42342B42353D43312B43322B43332B43342B43353D44312B44322B44332B44343D45312B45322B45332B45343C2F703E3C2F74643E3C2F74723E3C2F74626F64793E3C2F7461626C653E3C2F666F726D3E3C2F6469763E3C2F626F64793E3C2F68746D6C3E);

-- ----------------------------
-- Table structure for ck_finder
-- ----------------------------
DROP TABLE IF EXISTS `ck_finder`;
CREATE TABLE `ck_finder` (
  `ID` varchar(32) NOT NULL,
  `ATTACHMENT` varchar(255) default NULL,
  `IMAGE` varchar(255) default NULL,
  `REMARK` longtext,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ck_finder
-- ----------------------------

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `ID` int(11) NOT NULL auto_increment,
  `DOCCONTENT` longblob,
  `DOCDATE` datetime default NULL,
  `DOCID` varchar(255) default NULL,
  `DOCTITLE` varchar(255) default NULL,
  `DOCTYPE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doc
-- ----------------------------

-- ----------------------------
-- Table structure for jeecg_demo
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_demo`;
CREATE TABLE `jeecg_demo` (
  `ID` varchar(32) NOT NULL,
  `AGE` int(11) default NULL,
  `BIRTHDAY` datetime default NULL,
  `content` varchar(255) default NULL,
  `CREATE_TIME` datetime default NULL,
  `DEP_ID` varchar(255) default NULL,
  `EMAIL` varchar(255) default NULL,
  `MOBILE_PHONE` varchar(255) default NULL,
  `OFFICE_PHONE` varchar(255) default NULL,
  `SALARY` decimal(19,2) default NULL,
  `SEX` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  `USER_NAME` varchar(255) NOT NULL,
  `create_date` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_demo
-- ----------------------------
INSERT INTO `jeecg_demo` VALUES ('4028d881436d514601436d521ad4015e', '12', '2014-01-07 23:28:53', null, null, '123', 'demo@jeecg.com', '13111111111', '66666666', '111111.00', '1', null, '小明', null);

-- ----------------------------
-- Table structure for jeecg_demo_course
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_demo_course`;
CREATE TABLE `jeecg_demo_course` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(25) default NULL,
  `teacher_ID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_g3jn8mfod69i7jfv5gnrcvgbx` (`teacher_ID`),
  CONSTRAINT `FK_g3jn8mfod69i7jfv5gnrcvgbx` FOREIGN KEY (`teacher_ID`) REFERENCES `jeecg_demo_teacher` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_demo_course
-- ----------------------------
INSERT INTO `jeecg_demo_course` VALUES ('4028d881436d514601436d5219b0015b', '海贼王', '4028d881436d514601436d5219ae015a');

-- ----------------------------
-- Table structure for jeecg_demo_student
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_demo_student`;
CREATE TABLE `jeecg_demo_student` (
  `ID` varchar(32) NOT NULL,
  `BIRTHDAY` datetime default NULL,
  `NAME` varchar(32) default NULL,
  `SEX` varchar(1) default NULL,
  `COURSE_ID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_r86q81koyocgod3cx6529hbpw` (`COURSE_ID`),
  CONSTRAINT `FK_r86q81koyocgod3cx6529hbpw` FOREIGN KEY (`COURSE_ID`) REFERENCES `jeecg_demo_course` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_demo_student
-- ----------------------------
INSERT INTO `jeecg_demo_student` VALUES ('4028d881436d514601436d5219b4015c', null, '卓洛', '0', '4028d881436d514601436d5219b0015b');
INSERT INTO `jeecg_demo_student` VALUES ('4028d881436d514601436d5219b7015d', null, '山治 ', '0', '4028d881436d514601436d5219b0015b');

-- ----------------------------
-- Table structure for jeecg_demo_teacher
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_demo_teacher`;
CREATE TABLE `jeecg_demo_teacher` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(12) default NULL,
  `pic` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_demo_teacher
-- ----------------------------
INSERT INTO `jeecg_demo_teacher` VALUES ('4028d881436d514601436d5219ae015a', '路飞', 'upload/Teacher/pic3345280233.PNG');

-- ----------------------------
-- Table structure for jeecg_matter_bom
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_matter_bom`;
CREATE TABLE `jeecg_matter_bom` (
  `ID` varchar(32) NOT NULL,
  `address` varchar(255) default NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(21,6) NOT NULL,
  `productionDate` datetime default NULL,
  `quantity` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `unit` varchar(50) default NULL,
  `weight` varchar(50) default NULL,
  `parent_ID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_fldfyrevj0li4hej5b2gu2q7w` (`parent_ID`),
  CONSTRAINT `FK_fldfyrevj0li4hej5b2gu2q7w` FOREIGN KEY (`parent_ID`) REFERENCES `jeecg_matter_bom` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_matter_bom
-- ----------------------------
INSERT INTO `jeecg_matter_bom` VALUES ('4028d881436d514601436d521ae40163', '广东深圳', '001', '电脑', '5000.000000', '2014-01-07 23:28:53', '5', '10', '台', '100', null);
INSERT INTO `jeecg_matter_bom` VALUES ('4028d881436d514601436d521ae70164', '上海', '001001', '主板', '800.000000', '2014-01-07 23:28:53', '6', '18', '个', '60', '4028d881436d514601436d521ae40163');

-- ----------------------------
-- Table structure for jeecg_minidao
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_minidao`;
CREATE TABLE `jeecg_minidao` (
  `ID` varchar(32) NOT NULL,
  `AGE` int(11) default NULL,
  `BIRTHDAY` datetime default NULL,
  `CONTENT` varchar(255) default NULL,
  `CREATE_TIME` datetime default NULL,
  `DEP_ID` varchar(255) default NULL,
  `EMAIL` varchar(255) default NULL,
  `MOBILE_PHONE` varchar(255) default NULL,
  `OFFICE_PHONE` varchar(255) default NULL,
  `SALARY` decimal(19,2) default NULL,
  `SEX` int(11) default NULL,
  `STATUS` varchar(255) default NULL,
  `USER_NAME` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_minidao
-- ----------------------------

-- ----------------------------
-- Table structure for jeecg_order_custom
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_order_custom`;
CREATE TABLE `jeecg_order_custom` (
  `ID` varchar(32) NOT NULL,
  `CREATE_DT` datetime default NULL,
  `CRTUSER` varchar(12) default NULL,
  `CRTUSER_NAME` varchar(10) default NULL,
  `DEL_DT` datetime default NULL,
  `DELFLAG` int(11) default NULL,
  `GO_ORDER_CODE` varchar(12) NOT NULL,
  `GOC_BUSS_CONTENT` varchar(33) default NULL,
  `GOC_CONTENT` varchar(66) default NULL,
  `GOC_CUS_NAME` varchar(16) default NULL,
  `GOC_IDCARD` varchar(18) default NULL,
  `GOC_PASSPORT_CODE` varchar(10) default NULL,
  `GOC_SEX` varchar(255) default NULL,
  `MODIFIER` varchar(12) default NULL,
  `MODIFIER_NAME` varchar(10) default NULL,
  `MODIFY_DT` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_order_custom
-- ----------------------------
INSERT INTO `jeecg_order_custom` VALUES ('4028d881436d514601436d521ae20162', null, null, null, null, null, '11111AAA', null, null, '小明', null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for jeecg_order_main
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_order_main`;
CREATE TABLE `jeecg_order_main` (
  `ID` varchar(32) NOT NULL,
  `CREATE_DT` datetime default NULL,
  `CRTUSER` varchar(12) default NULL,
  `CRTUSER_NAME` varchar(10) default NULL,
  `DEL_DT` datetime default NULL,
  `DELFLAG` int(11) default NULL,
  `GO_ALL_PRICE` decimal(10,2) default NULL,
  `GO_CONTACT_NAME` varchar(16) default NULL,
  `GO_CONTENT` varchar(66) default NULL,
  `GO_ORDER_CODE` varchar(12) NOT NULL,
  `GO_ORDER_COUNT` int(11) default NULL,
  `GO_RETURN_PRICE` decimal(10,2) default NULL,
  `GO_TELPHONE` varchar(11) default NULL,
  `GODER_TYPE` varchar(255) default NULL,
  `MODIFIER` varchar(12) default NULL,
  `MODIFIER_NAME` varchar(10) default NULL,
  `MODIFY_DT` datetime default NULL,
  `USERTYPE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_order_main
-- ----------------------------
INSERT INTO `jeecg_order_main` VALUES ('4028d881436d514601436d521adb0160', null, null, null, null, null, '1111111.00', 'alex', '别放辣椒', '11111AAA', '1', '100.00', null, '1', null, null, null, '1');

-- ----------------------------
-- Table structure for jeecg_order_product
-- ----------------------------
DROP TABLE IF EXISTS `jeecg_order_product`;
CREATE TABLE `jeecg_order_product` (
  `ID` varchar(32) NOT NULL,
  `CREATE_DT` datetime default NULL,
  `CRTUSER` varchar(12) default NULL,
  `CRTUSER_NAME` varchar(10) default NULL,
  `DEL_DT` datetime default NULL,
  `DELFLAG` int(11) default NULL,
  `GO_ORDER_CODE` varchar(12) NOT NULL,
  `GOP_CONTENT` varchar(66) default NULL,
  `GOP_COUNT` int(11) default NULL,
  `GOP_ONE_PRICE` decimal(10,2) default NULL,
  `GOP_PRODUCT_NAME` varchar(33) default NULL,
  `GOP_PRODUCT_TYPE` varchar(1) NOT NULL,
  `GOP_SUM_PRICE` decimal(10,2) default NULL,
  `MODIFIER` varchar(12) default NULL,
  `MODIFIER_NAME` varchar(10) default NULL,
  `MODIFY_DT` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jeecg_order_product
-- ----------------------------
INSERT INTO `jeecg_order_product` VALUES ('4028d881436d514601436d521adf0161', null, null, null, null, null, '11111AAA', null, '1', '100.00', '最最美味的地锅鸡', '1', '100.00', null, null, null);

-- ----------------------------
-- Table structure for jform_cgreport_head
-- ----------------------------
DROP TABLE IF EXISTS `jform_cgreport_head`;
CREATE TABLE `jform_cgreport_head` (
  `ID` varchar(36) NOT NULL,
  `CGR_SQL` longtext NOT NULL,
  `CODE` varchar(36) NOT NULL,
  `CONTENT` longtext NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jform_cgreport_head
-- ----------------------------
INSERT INTO `jform_cgreport_head` VALUES ('402889f847666ae401476670bb72000a', 'select * from  weixin_cms_menu', 'A001', '查询微网站栏目', '查询栏目');

-- ----------------------------
-- Table structure for jform_cgreport_item
-- ----------------------------
DROP TABLE IF EXISTS `jform_cgreport_item`;
CREATE TABLE `jform_cgreport_item` (
  `ID` varchar(36) NOT NULL,
  `S_FLAG` varchar(2) default NULL,
  `S_MODE` varchar(10) default NULL,
  `CGRHEAD_ID` varchar(36) default NULL,
  `DICT_CODE` varchar(36) default NULL,
  `FIELD_HREF` varchar(120) default NULL,
  `FIELD_NAME` varchar(36) default NULL,
  `FIELD_TXT` longtext,
  `FIELD_TYPE` varchar(10) default NULL,
  `IS_SHOW` varchar(5) default NULL,
  `ORDER_NUM` int(11) default NULL,
  `REPLACE_VA` varchar(36) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jform_cgreport_item
-- ----------------------------
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb74000b', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'ID', 'ID', 'String', 'Y', '0', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb78000c', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'ACCOUNTID', 'ACCOUNTID', 'String', 'Y', '1', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb79000d', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'CREATE_BY', 'CREATE_BY', 'String', 'Y', '2', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb7a000e', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'CREATE_DATE', 'CREATE_DATE', 'String', 'Y', '3', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb7b000f', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'CREATE_NAME', 'CREATE_NAME', 'String', 'Y', '4', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb7e0010', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'IMAGE_HREF', 'IMAGE_HREF', 'String', 'Y', '5', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb7f0011', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'IMAGE_NAME', 'IMAGE_NAME', 'String', 'Y', '6', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb800012', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'NAME', 'NAME', 'String', 'Y', '7', '');
INSERT INTO `jform_cgreport_item` VALUES ('402889f847666ae401476670bb810013', 'Y', 'single', '402889f847666ae401476670bb72000a', '', '', 'TYPE', 'TYPE', 'String', 'Y', '8', '');

-- ----------------------------
-- Table structure for jform_leave
-- ----------------------------
DROP TABLE IF EXISTS `jform_leave`;
CREATE TABLE `jform_leave` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) NOT NULL COMMENT '请假标题',
  `people` varchar(20) NOT NULL COMMENT '请假人',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `begindate` datetime NOT NULL COMMENT '请假开始时间',
  `enddate` datetime NOT NULL COMMENT '请假结束时间',
  `day_num` int(11) default NULL COMMENT '请假天数',
  `hol_dept` varchar(32) NOT NULL COMMENT '所属部门',
  `hol_reson` varchar(255) NOT NULL COMMENT '请假原因',
  `dep_leader` varchar(20) NOT NULL COMMENT '部门审批人',
  `content` varchar(255) NOT NULL COMMENT '部门审批意见',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jform_leave
-- ----------------------------

-- ----------------------------
-- Table structure for jform_order_main
-- ----------------------------
DROP TABLE IF EXISTS `jform_order_main`;
CREATE TABLE `jform_order_main` (
  `id` varchar(36) NOT NULL,
  `order_code` varchar(50) default NULL COMMENT '订单号',
  `order_date` datetime default NULL COMMENT '订单日期',
  `order_money` double default NULL COMMENT '订单金额',
  `content` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jform_order_main
-- ----------------------------

-- ----------------------------
-- Table structure for jg_person
-- ----------------------------
DROP TABLE IF EXISTS `jg_person`;
CREATE TABLE `jg_person` (
  `ID` varchar(32) NOT NULL,
  `AGE` int(11) default NULL,
  `BIRTHDAY` datetime default NULL,
  `CREATEDT` datetime default NULL,
  `NAME` varchar(255) NOT NULL,
  `SALARY` decimal(19,2) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jg_person
-- ----------------------------
INSERT INTO `jg_person` VALUES ('4028d881436d514601436d521ad9015f', '10', '2014-01-07 23:28:53', '2014-01-07 23:28:53', '小红', '1000.00');

-- ----------------------------
-- Table structure for jp_demo_activity
-- ----------------------------
DROP TABLE IF EXISTS `jp_demo_activity`;
CREATE TABLE `jp_demo_activity` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '活动名称',
  `begin_time` datetime default NULL COMMENT '活动开始时间',
  `end_time` datetime default NULL COMMENT ' 活动结束时间',
  `hdurl` varchar(300) default NULL COMMENT '入口地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包活动表';

-- ----------------------------
-- Records of jp_demo_activity
-- ----------------------------
INSERT INTO `jp_demo_activity` VALUES ('CC7E3FE24C3843289A7D8E4124F53FF6', '2', '2016-08-22 13:02:41', '2016-08-22 13:02:42', '3');
INSERT INTO `jp_demo_activity` VALUES ('DEBCE74A2E87406EBA557748BE0FA700', '1', '2016-04-10 14:33:50', '2016-04-10 14:33:52', '1');
INSERT INTO `jp_demo_activity` VALUES ('F4EAEF41A7DF4B59AE9A41455E1643F9', '3', '2016-06-30 23:52:20', '2016-06-30 23:52:21', '3');

-- ----------------------------
-- Table structure for jp_demo_auth
-- ----------------------------
DROP TABLE IF EXISTS `jp_demo_auth`;
CREATE TABLE `jp_demo_auth` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `auth_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '权限编码',
  `auth_name` varchar(100) collate utf8_bin default NULL COMMENT '权限名称',
  `auth_type` varchar(2) collate utf8_bin default NULL COMMENT '权限类型 0:菜单;1:功能',
  `auth_contr` varchar(256) collate utf8_bin default NULL COMMENT '权限控制',
  `parent_auth_id` char(12) collate utf8_bin default NULL COMMENT '上一级权限编码',
  `leaf_ind` char(2) collate utf8_bin default NULL COMMENT '是否叶子节点',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_authid` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='运营系统权限表';

-- ----------------------------
-- Records of jp_demo_auth
-- ----------------------------
INSERT INTO `jp_demo_auth` VALUES ('1', '21', '系统管理', '0', null, null, 'N');
INSERT INTO `jp_demo_auth` VALUES ('2', '2101', '用户管理', '0', '/system/back/jwSystemUser/list.do', '21', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('3', '210101', '新增用户', '1', '/system/back/jwSystemUser/doAdd.do', '2101', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('4', '210102', '编辑用户', '1', '/system/back/jwSystemUser/doEdit.do', '2101', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('5', '2102', '角色管理', '0', '/system/back/jwSystemRole/list.do', '21', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('6', '210201', '新增角色', '1', '/system/back/jwSystemRole/doAdd.do', '2102', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('7', '210202', '编辑角色', '1', '/system/back/jwSystemRole/doEdit.do', '2102', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('8', '210203', '角色授权', '1', '/system/back/jwSystemRole/editRoleAuth.do', '2102', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('9', '210204', '删除角色', '1', '/system/back/jwSystemRole/doDelete.do', '2102', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('10', '2103', '权限管理', '0', '/system/back/jwSystemAuth/list.do', '21', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('11', '210301', '新增权限', '1', '/system/back/jwSystemAuth/doAdd.do', '2103', 'Y');
INSERT INTO `jp_demo_auth` VALUES ('12', '210302', '编辑权限', '1', '/system/back/jwSystemAuth/doEdit.do1', '2103', 'Y');

-- ----------------------------
-- Table structure for optimistic_locking
-- ----------------------------
DROP TABLE IF EXISTS `optimistic_locking`;
CREATE TABLE `optimistic_locking` (
  `ID` varchar(32) NOT NULL,
  `ACCOUNT` int(11) default NULL,
  `AGE` int(11) default NULL,
  `NAME` varchar(85) default NULL,
  `VER` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of optimistic_locking
-- ----------------------------

-- ----------------------------
-- Table structure for qywx_account
-- ----------------------------
DROP TABLE IF EXISTS `qywx_account`;
CREATE TABLE `qywx_account` (
  `id` varchar(50) NOT NULL,
  `accont_name` varchar(100) default NULL COMMENT '名称',
  `corpid` varchar(255) default NULL COMMENT '企业号标识CorpID',
  `secret` varchar(255) default NULL COMMENT '管理组凭证密钥Secret',
  `access_token` varchar(512) default NULL COMMENT 'AccessToken',
  `account_desc` varchar(300) default NULL COMMENT '描述',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  `conversation_secret` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_corpid` (`corpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号信息表';

-- ----------------------------
-- Records of qywx_account
-- ----------------------------
INSERT INTO `qywx_account` VALUES ('402880a94742e24e014742e8942b0002', '捷微企业号', '?', '?', null, '', null, null, null, null, null, null, '?');

-- ----------------------------
-- Table structure for qywx_agent
-- ----------------------------
DROP TABLE IF EXISTS `qywx_agent`;
CREATE TABLE `qywx_agent` (
  `id` varchar(50) NOT NULL COMMENT '主键Id',
  `account_id` varchar(200) NOT NULL COMMENT '企业号ID',
  `wx_agentid` varchar(200) default NULL COMMENT '应用ID(微信)',
  `agent_name` varchar(200) default NULL COMMENT '应用名称',
  `token` varchar(50) default NULL COMMENT '回调token',
  `encodingAESKey` varchar(100) default NULL COMMENT '回调EncodingAESKey',
  `square_logo_url` varchar(200) default NULL COMMENT '方形头像',
  `round_logo_url` varchar(200) default NULL COMMENT '圆形头像',
  `logo_mediaid` varchar(100) default NULL COMMENT '头像mediaid',
  `description` varchar(200) default NULL COMMENT '描述',
  `close_status` varchar(200) default NULL COMMENT '是否被禁用',
  `redirect_domain` varchar(200) default NULL COMMENT '可信域名',
  `report_location_flag` varchar(2) default NULL COMMENT '是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报',
  `isreportuser` varchar(2) default NULL COMMENT '是否接收用户变更通知。0：不接收；1：接收',
  `isreportenter` varchar(2) default NULL COMMENT '是否上报用户进入应用事件。0：不接收；1：接收',
  `app_type` varchar(2) default NULL COMMENT '应用类型。1：消息型；2：主页型',
  `home_url` varchar(200) default NULL COMMENT '主页型应用url',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_accountid_appid` (`account_id`,`wx_agentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用信息表';

-- ----------------------------
-- Records of qywx_agent
-- ----------------------------
INSERT INTO `qywx_agent` VALUES ('1', '402880a94742e24e014742e8942b0002', '1', '微信考勤', 'jeewx', '?', '111url', '11', null, '', '1', '', '2', '1', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `qywx_agent` VALUES ('DC46552CD5AE4DF2AE12680C4636C9BA', '402880a94742e24e014742e8942b0002', '4', '公司公告', 'jeewx', '?', '', null, null, '', '1', 'http://fly1206.xicp.net/weixinCoreController.do?wechat&corpid=wx967db4406788c64c&appid=4', '0', '0', '0', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_autoresponse
-- ----------------------------
DROP TABLE IF EXISTS `qywx_autoresponse`;
CREATE TABLE `qywx_autoresponse` (
  `id` varchar(100) NOT NULL,
  `key_word` varchar(200) default NULL COMMENT '关键字',
  `res_content` varchar(500) default NULL COMMENT '回复内容',
  `msg_type` varchar(100) default NULL COMMENT '消息类型',
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `accountid` varchar(100) default NULL COMMENT '微信账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`),
  KEY `idx_accountid` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键字管理';

-- ----------------------------
-- Records of qywx_autoresponse
-- ----------------------------
INSERT INTO `qywx_autoresponse` VALUES ('43232F1A56E94BD099139FADD1EFD53F', '01', '8a792db34fdf51b3014fdfa391fd002a', 'text', '素材一句话冠亚军', '402880a94742e24e014742e8942b0002', null, null, '2016-07-13 14:23:03', null, null, null);
INSERT INTO `qywx_autoresponse` VALUES ('694835F2933C4410A639906639F18D77', 'nihao', 'F65B42281D1E46E69FBEAEEC43587F6D', 'text', 'nihao', '402880a94742e24e014742e8942b0002', null, null, '2016-04-18 12:43:00', null, null, null);
INSERT INTO `qywx_autoresponse` VALUES ('8a792db34fdf51b3014fdfa937e90038', 'jeecg', 'ff808081538e81b401539bb4b1c80034', 'news', 'QQQ', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_autoresponse` VALUES ('CCA4FFD6B26E481C878FF715C708CB55', '你好', 'F65B42281D1E46E69FBEAEEC43587F6D', 'text', 'nihao', '402880a94742e24e014742e8942b0002', null, null, '2016-12-20 16:47:48', null, null, null);

-- ----------------------------
-- Table structure for qywx_autoresponse_default
-- ----------------------------
DROP TABLE IF EXISTS `qywx_autoresponse_default`;
CREATE TABLE `qywx_autoresponse_default` (
  `id` varchar(50) NOT NULL COMMENT '主键Id',
  `templatename` varchar(200) NOT NULL COMMENT '模板名称',
  `templateid` varchar(50) NOT NULL COMMENT '模板Id',
  `msgtype` varchar(40) default NULL COMMENT '消息类型',
  `accountid` varchar(40) default NULL COMMENT '微信账号Id',
  `iswork` varchar(10) default NULL COMMENT '是否启用',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='默认关键字回复';

-- ----------------------------
-- Records of qywx_autoresponse_default
-- ----------------------------
INSERT INTO `qywx_autoresponse_default` VALUES ('8a792db34fdf51b3014fdfb22e87004f', '默认回复语', '8a792db34fdf51b3014fdfb1fdb3004c', 'text', '402880a94742e24e014742e8942b0002', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_conversation
-- ----------------------------
DROP TABLE IF EXISTS `qywx_conversation`;
CREATE TABLE `qywx_conversation` (
  `ID` varchar(50) NOT NULL,
  `TITLE` varchar(200) default NULL,
  `USERNAMELIST` varchar(2000) default NULL,
  `USERIDLIST` varchar(2000) default NULL,
  `STATUS` int(2) default NULL,
  `MANAGERID` varchar(50) default NULL,
  `CHATID` varchar(100) default NULL,
  `MANAGERNAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qywx_conversation
-- ----------------------------
INSERT INTO `qywx_conversation` VALUES ('725105D1D71948499B1CB7FFE465011A', '会话测试', 'scott,周俊峰,邢磊,', 'scott,zhoujf,xinglei,', '1', 'xinglei', '1465721359150', '邢磊');

-- ----------------------------
-- Table structure for qywx_group
-- ----------------------------
DROP TABLE IF EXISTS `qywx_group`;
CREATE TABLE `qywx_group` (
  `id` varchar(100) NOT NULL COMMENT '部门Id',
  `name` varchar(200) default NULL COMMENT '部门名称',
  `parentid` varchar(100) default NULL COMMENT '上级部门Id',
  `accountid` varchar(100) default NULL COMMENT '微信账号ID',
  `orders` varchar(11) default NULL,
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户组';

-- ----------------------------
-- Records of qywx_group
-- ----------------------------
INSERT INTO `qywx_group` VALUES ('1', '北京国炬', '0', '402880a94742e24e014742e8942b0002', '200', null, null, null, null, null, null);
INSERT INTO `qywx_group` VALUES ('101B71F46D8A49DAA08357BC501493AF', '软件开发部', '1', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:17:31', null, null, null);
INSERT INTO `qywx_group` VALUES ('8C3E0E85D3134B99BB15D4E16F5AFF36', '财务室', '1', '402880a94742e24e014742e8942b0002', '2', null, null, '2016-12-17 00:17:41', null, null, null);

-- ----------------------------
-- Table structure for qywx_gzentity
-- ----------------------------
DROP TABLE IF EXISTS `qywx_gzentity`;
CREATE TABLE `qywx_gzentity` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `template_id` varchar(100) default NULL COMMENT '模板id',
  `template_type` varchar(100) default NULL COMMENT '类型 文本_text,图文_news',
  `is_work` varchar(100) default NULL COMMENT '是否启用 未启用_0,启用_1',
  `accountid` varchar(100) default NULL COMMENT '微信账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注回复';

-- ----------------------------
-- Records of qywx_gzentity
-- ----------------------------
INSERT INTO `qywx_gzentity` VALUES ('402880a94936f3ee0149370671400001', '关注回复', '4028098246b78d570146b7915d020004', 'text', '1', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_gzentity` VALUES ('8a792db34fdf51b3014fdfa74adf0034', '图文素材1', '8a792db34fdf51b3014fdfa3c6aa002c', 'news', '0', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_gzuserinfo
-- ----------------------------
DROP TABLE IF EXISTS `qywx_gzuserinfo`;
CREATE TABLE `qywx_gzuserinfo` (
  `id` varchar(100) character set gbk NOT NULL COMMENT '主键',
  `userid` varchar(100) default NULL COMMENT '账号',
  `name` varchar(100) default NULL COMMENT '姓名',
  `department` varchar(200) default NULL COMMENT '部门',
  `position` varchar(100) default NULL COMMENT '职位',
  `mobile` varchar(100) default NULL COMMENT '电话',
  `province` varchar(100) default NULL COMMENT '省份',
  `gender` varchar(100) default NULL COMMENT '性别gender=1表示男，=0表示女',
  `email` varchar(400) default NULL COMMENT '邮箱',
  `weixinid` varchar(100) default NULL COMMENT '微信号',
  `avatar` varchar(100) default NULL COMMENT '头像url',
  `subscribe_status` varchar(100) default NULL COMMENT '关注状态: 1=已关注，2=已冻结，4=未关注',
  `subscribe_time` datetime default NULL COMMENT '关注时间',
  `accountid` varchar(100) default NULL COMMENT '微信账号ID',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户';

-- ----------------------------
-- Records of qywx_gzuserinfo
-- ----------------------------
INSERT INTO `qywx_gzuserinfo` VALUES ('6A36781865A349359AADABE81C42D3AF', 'qinfeng', '秦风', '1', null, '18600000000', null, '1', null, null, 'http://shp.qpic.cn/bizmp/UBykN6XtpLRw6ibU7nBJUzEaicdO55PfUTic6XWqxIPbiaiciclQiav3DD01A/', '1', null, '402880a94742e24e014742e8942b0002', null, null, '2016-12-17 00:16:30', null, null, null);

-- ----------------------------
-- Table structure for qywx_location
-- ----------------------------
DROP TABLE IF EXISTS `qywx_location`;
CREATE TABLE `qywx_location` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `corpid` varchar(64) NOT NULL COMMENT '企业号corpId',
  `userid` varchar(64) default NULL COMMENT ' 	成员UserID ',
  `createtime` datetime default NULL COMMENT '创建时间',
  `latitude` varchar(64) default NULL COMMENT '地理位置纬度',
  `longitude` varchar(64) default NULL COMMENT '地理位置径度',
  `precision` varchar(64) default NULL COMMENT '地理位置精度',
  `agentid` int(32) default NULL COMMENT '企业应用id',
  `location_precision` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地理位置表';

-- ----------------------------
-- Records of qywx_location
-- ----------------------------
INSERT INTO `qywx_location` VALUES ('4065B9C7598C41168B07BC09FEC72757', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:27:00', '40.056004', '116.404671', null, '1', '30.000000');
INSERT INTO `qywx_location` VALUES ('BF3219EA3F2840938BDA4FDE703A5E36', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 01:29:17', '40.067616', '116.405525', null, '4', '30.000000');

-- ----------------------------
-- Table structure for qywx_menu
-- ----------------------------
DROP TABLE IF EXISTS `qywx_menu`;
CREATE TABLE `qywx_menu` (
  `ID` varchar(32) NOT NULL,
  `agent_id` varchar(255) default NULL COMMENT '应用主键ID',
  `menu_name` varchar(255) default NULL COMMENT '菜单标题',
  `menu_type` varchar(255) default NULL COMMENT '菜单类型',
  `menu_key` varchar(255) default NULL COMMENT '菜单KEY',
  `orders` varchar(10) default NULL COMMENT '菜单位置',
  `msg_type` varchar(255) default NULL COMMENT '响应消息类型',
  `template_id` varchar(255) default NULL COMMENT '关联素材ID',
  `url` varchar(1000) default NULL COMMENT '网页链接',
  `father_id` varchar(32) default NULL COMMENT '父ID',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`ID`),
  KEY `menu_key` (`menu_key`),
  KEY `order` (`orders`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义菜单表';

-- ----------------------------
-- Records of qywx_menu
-- ----------------------------
INSERT INTO `qywx_menu` VALUES ('114BC83AA7AB4C548AAFFE515AB793CB', '1', '官网', 'click', '2', '2', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', '', null, null, null, null, null, null, null);
INSERT INTO `qywx_menu` VALUES ('1FF5FCEA7C71477DA133CA0195EC2A07', '1', 'jeecg', 'view', '21', '21', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', 'http://www.jeecg.org', '114BC83AA7AB4C548AAFFE515AB793CB', null, null, null, null, null, null);
INSERT INTO `qywx_menu` VALUES ('27ADD6192C04415FA7FC2CD1C3350E07', '1', '百度', 'view', '12', '11', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', 'http://www.baidu.com', 'D7837CB701E845F6B5557AFACCDB2B98', null, null, null, null, null, null);
INSERT INTO `qywx_menu` VALUES ('4EF79061542E4DC59278BA9F6269E2CF', '4', '111', 'click', '11', '11', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', '', 'CCABCD43380F4DA59E5C6987B88EF921', null, null, null, null, null, null);
INSERT INTO `qywx_menu` VALUES ('CCABCD43380F4DA59E5C6987B88EF921', '4', '11', 'click', '1', '1', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', '', null, null, null, null, null, null, null);
INSERT INTO `qywx_menu` VALUES ('D7837CB701E845F6B5557AFACCDB2B98', '1', 'OA办公', 'click', '1', '1', 'text', 'F65B42281D1E46E69FBEAEEC43587F6D', '', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_messagelog
-- ----------------------------
DROP TABLE IF EXISTS `qywx_messagelog`;
CREATE TABLE `qywx_messagelog` (
  `id` varchar(32) NOT NULL,
  `wx_agent_id` int(11) default NULL COMMENT '应用id',
  `topartys_id` varchar(64) default NULL COMMENT '部门id',
  `message_type` varchar(32) default NULL COMMENT '消息类型',
  `message_content` text COMMENT '文本内容',
  `content_id` varchar(64) default NULL COMMENT '内容id',
  `receive_message` varchar(300) default NULL COMMENT '消息状态',
  `create_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qywx_messagelog
-- ----------------------------
INSERT INTO `qywx_messagelog` VALUES ('41EEAB4A2C0D4E9B95228A526D50CEAA', '4', '', 'news', null, 'ff808081538e81b401539bb4b1c80034', '{\"errcode\":0,\"errmsg\":\"ok\"}', '2016-12-17 01:29:04');

-- ----------------------------
-- Table structure for qywx_newsitem
-- ----------------------------
DROP TABLE IF EXISTS `qywx_newsitem`;
CREATE TABLE `qywx_newsitem` (
  `id` varchar(100) NOT NULL,
  `title` varchar(300) default NULL COMMENT '标题',
  `author` varchar(100) default NULL COMMENT '作者',
  `image_path` varchar(100) default NULL COMMENT '图片路径',
  `content` longtext COMMENT '内容',
  `templateid` varchar(100) default NULL COMMENT '图文模板id',
  `description` varchar(400) default NULL COMMENT '摘要',
  `order_no` varchar(100) default NULL COMMENT '新闻顺序',
  `url` varchar(100) default NULL COMMENT '消息内容的url',
  `hdid` varchar(100) default NULL COMMENT '活动id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文素材新闻';

-- ----------------------------
-- Records of qywx_newsitem
-- ----------------------------
INSERT INTO `qywx_newsitem` VALUES ('18342D4E58884D55B70D93C1D3F2A24A', '12', '12', 'upload/files/1348502772_8826.gif', '<p>121212</p>', '0A1776E6FE6F4AFB9CF558175AAF495D', '12', '1', '', null, null, null, null, null, null, null);
INSERT INTO `qywx_newsitem` VALUES ('3C35D5FC5A2C4C74BAA9113EA7151740', 'jeewx', 'jeewx', 'upload/files/ppx01814elfynejrpjjhm47.png', '<p>jeewxjeewx</p><p><img src=\"http://localhost/jeewx/upload/ueditor/20161216/91871481903127549.jpg\" title=\"profile_small.jpg\"/></p>', 'ff808081538e81b401539bb4b1c80034', 'jeewx', '2', '', null, null, null, null, null, null, null);
INSERT INTO `qywx_newsitem` VALUES ('8a792db34fdf51b3014fdfa41353002e', 'qq', 'qq', 'upload/files/e1fe9925bc315c60addea1b98eb1cb1349547719.jpg', '<p><img src=\"http://localhost/jeewx/upload/ueditor/20161216/91501481903058267.jpg\" title=\"e1fe9925bc315c60addea1b98eb1cb1349547719.jpg\"/></p>', '8a792db34fdf51b3014fdfa3c6aa002c', 'qqqq', '1', '', '', null, null, null, null, null, null);
INSERT INTO `qywx_newsitem` VALUES ('BC89EDABF05E4678A7DC46087A8B1FBD', '1231', '123123', 'upload/files/70g58PICeGn.jpg', '<p>123<br/></p>', '3D4ABC0B6B5F46EA9A53311FD9E871DA', '123', '1', '', null, null, null, null, null, null, null);
INSERT INTO `qywx_newsitem` VALUES ('ff808081538e81b40153a1602d3c006b', '庆祝jeecg企业微信发布', 'jeecg开源社区', 'upload/files/profile_small.jpg', '<p>庆祝jeecg企业微信发布庆祝jeecg企业微信发布</p><p><img src=\"http://localhost/jeewx/upload/ueditor/20161216/56741481903099897.jpg\" title=\"profile_small.jpg\"/></p>', 'ff808081538e81b401539bb4b1c80034', '庆祝jeecg企业微信发布', '1', '', '', null, null, null, null, null, null);
INSERT INTO `qywx_newsitem` VALUES ('ff808081538e81b40153a17b319a0076', 'sss', 'ddddd', 'upload/files/20160323111823UttemyYU.png', '<p>ssssss</p>', 'ff808081538e81b40153a1787a020074', 'jjjjjj', '11', '	http://www.zstobacco.com.cn:9091/tobacco-marketing/f/wxcp?verify&a=37', '', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_newstemplate
-- ----------------------------
DROP TABLE IF EXISTS `qywx_newstemplate`;
CREATE TABLE `qywx_newstemplate` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(200) default NULL COMMENT '模板名称',
  `template_type` varchar(100) default NULL COMMENT '模板类型',
  `accountid` varchar(100) default NULL COMMENT '微信企业号账号id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文素材模板';

-- ----------------------------
-- Records of qywx_newstemplate
-- ----------------------------
INSERT INTO `qywx_newstemplate` VALUES ('8a792db34fdf51b3014fdfa3c6aa002c', 'QQ图文', 'common', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_newstemplate` VALUES ('ff808081538e81b401539bb4b1c80034', 'JEEWX产品介绍', 'common', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for qywx_receivetext
-- ----------------------------
DROP TABLE IF EXISTS `qywx_receivetext`;
CREATE TABLE `qywx_receivetext` (
  `id` varchar(100) NOT NULL,
  `tousername` varchar(100) NOT NULL COMMENT '开发者微信号',
  `fromusername` varchar(100) default NULL COMMENT '发送方帐号（一个OpenID）',
  `createtime` timestamp NULL default CURRENT_TIMESTAMP COMMENT '消息创建时间 （整型）',
  `msgtype` varchar(100) default NULL COMMENT '消息类型（text/image/location/link）',
  `msgid` varchar(100) default NULL COMMENT '消息id，64位整型',
  `content` varchar(400) default NULL COMMENT '消息内容',
  `response` varchar(10) default NULL COMMENT '是否回复',
  `rescontent` varchar(400) default NULL COMMENT '回复内容',
  `nickname` varchar(100) default NULL COMMENT '用户昵称',
  `accountid` varchar(40) default NULL COMMENT '微信账号Id',
  `agent_id` varchar(200) default NULL COMMENT '应用ID(微信)',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本消息';

-- ----------------------------
-- Records of qywx_receivetext
-- ----------------------------
INSERT INTO `qywx_receivetext` VALUES ('0356C7F17AD64D0CBCD2E706DD1B94F2', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:22:04', 'text', '2938593396750063237', 'nihao', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:27:00', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('28D7E805A9E94F3A8A31E521BEC477AB', 'wx967db4406788c64c', 'qinfeng', '2016-12-20 18:29:19', 'text', '2938593396750063242', '你好', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-20 18:29:20', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('2BAA99FFE8AC434B8AD5C9D434678630', 'wx967db4406788c64c', 'qinfeng', '2016-12-20 16:48:36', 'text', '2938593396750063241', '你好', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-20 16:48:36', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('5EB24BDFAC51430C80607E4F3442C8D0', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:28:33', 'text', '2938593396750063239', 'jeecg', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:28:29', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('A7B14A39A72D4476B5D402232552A72C', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:22:04', 'text', '2938593396750063237', 'nihao', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:26:59', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('C57E74A3402E433F9EF7AFA672AAC540', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:29:24', 'text', '2938593396750063240', 'jeecg', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:29:21', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('C6434CDE401B49D0BA8AE2F8BA8D6F51', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:27:20', 'text', '2938593396750063238', 'nihao', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:27:17', null, null, null);
INSERT INTO `qywx_receivetext` VALUES ('E2E5456B445844FFB95B5473585D8F9A', 'wx967db4406788c64c', 'qinfeng', '2016-12-17 00:22:04', 'text', '2938593396750063237', 'nihao', '0', null, '', '402880a94742e24e014742e8942b0002', '1', null, null, '2016-12-17 00:27:00', null, null, null);

-- ----------------------------
-- Table structure for qywx_texttemplate
-- ----------------------------
DROP TABLE IF EXISTS `qywx_texttemplate`;
CREATE TABLE `qywx_texttemplate` (
  `id` varchar(100) NOT NULL,
  `template_name` varchar(200) NOT NULL COMMENT '模板名称',
  `template_content` varchar(500) NOT NULL COMMENT '模板内容',
  `accountid` varchar(100) default NULL COMMENT '微信企业账户id',
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本模板';

-- ----------------------------
-- Records of qywx_texttemplate
-- ----------------------------
INSERT INTO `qywx_texttemplate` VALUES ('8a792db34fdf51b3014fdfa391fd002a', '素材01', '素材01，测试素材文本功能', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_texttemplate` VALUES ('8a792db34fdf51b3014fdfb196200049', '验证回复语', '验证回复语', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_texttemplate` VALUES ('8a792db34fdf51b3014fdfb1fdb3004c', '默认回复语', '默认回复语', '402880a94742e24e014742e8942b0002', null, null, null, null, null, null);
INSERT INTO `qywx_texttemplate` VALUES ('F65B42281D1E46E69FBEAEEC43587F6D', 'nihao', '你好，欢迎你关注JEEWX产品，我是微信企业号触点！！！', '402880a94742e24e014742e8942b0002', null, null, '2016-03-25 15:46:48', null, null, null);

-- ----------------------------
-- Table structure for t_finance
-- ----------------------------
DROP TABLE IF EXISTS `t_finance`;
CREATE TABLE `t_finance` (
  `ID` varchar(32) NOT NULL,
  `APPROFILETYPE` varchar(255) default NULL,
  `BUYMONEY` float default NULL,
  `BUYPROJECTNO` varchar(255) default NULL,
  `BUYPROJECTORG` varchar(255) default NULL,
  `BUYUSE` varchar(255) default NULL,
  `BUYYEAR` varchar(255) default NULL,
  `CATEGORY` varchar(255) default NULL,
  `COLLECTORG` varchar(255) default NULL,
  `EXPENSEACCOUNT` varchar(255) default NULL,
  `HAPPENYEAR` int(11) default NULL,
  `MONEYAREA` varchar(255) default NULL,
  `MONEYSOURCE` varchar(255) default NULL,
  `MONEYTOTAL` float default NULL,
  `MONEYUSE` varchar(255) default NULL,
  `PAYTIME` datetime default NULL,
  `ZBWNO` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_finance
-- ----------------------------
INSERT INTO `t_finance` VALUES ('402880e6478c6d8201478c72e8ff0008', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_finance` VALUES ('402880e6478c6d8201478c74a6000010', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_finance` VALUES ('402880f259074906015907542ecc0011', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_finance` VALUES ('402881e547bb946e0147bb9834db0013', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_finance` VALUES ('40289481515cf0fe01515d076d6f003e', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_finance` VALUES ('4028948151dc770f0151dc7e8b100003', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_finance_files
-- ----------------------------
DROP TABLE IF EXISTS `t_finance_files`;
CREATE TABLE `t_finance_files` (
  `id` varchar(32) NOT NULL,
  `financeId` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_ij2p74feypwcda4n0n96pyd10` (`financeId`),
  KEY `FK_28m7vvi0cy5r5keke68b6f7rt` (`id`),
  CONSTRAINT `FK_28m7vvi0cy5r5keke68b6f7rt` FOREIGN KEY (`id`) REFERENCES `t_s_attachment` (`ID`),
  CONSTRAINT `FK_ij2p74feypwcda4n0n96pyd10` FOREIGN KEY (`financeId`) REFERENCES `t_finance` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_finance_files
-- ----------------------------

-- ----------------------------
-- Table structure for t_s_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_s_attachment`;
CREATE TABLE `t_s_attachment` (
  `ID` varchar(32) NOT NULL,
  `attachmentcontent` longblob,
  `attachmenttitle` varchar(100) default NULL,
  `businesskey` varchar(32) default NULL,
  `createdate` datetime default NULL,
  `extend` varchar(32) default NULL,
  `note` longtext,
  `realpath` varchar(100) default NULL,
  `subclassname` longtext,
  `swfpath` longtext,
  `BUSENTITYNAME` varchar(100) default NULL,
  `INFOTYPEID` varchar(32) default NULL,
  `USERID` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_mnq23hlc835n4ufgjl7nkn3bd` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_attachment
-- ----------------------------
INSERT INTO `t_s_attachment` VALUES ('402880f2590749060159075431330013', null, '4afbfbedab64034f9015f1bca8c379310b551dab', null, null, 'jpg', null, 'upload/files/20161216191115J7j8Dw1N.jpg', null, 'upload/files/20161216191115J7j8Dw1N.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402880f25907490601590754343a0014', null, 'e1fe9925bc315c60addea1b98eb1cb1349547719', null, null, 'jpg', null, 'upload/files/20161216191116rMyqV3XX.jpg', null, 'upload/files/20161216191116rMyqV3XX.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402880f2590749060159075436400015', null, 'ppx01814elfynejrpjjhm47', null, null, 'png', null, 'upload/files/20161216191116LdpGOmwr.png', null, 'upload/files/20161216191116LdpGOmwr.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402880f2590749060159075438570016', null, 'profile_small', null, null, 'jpg', null, 'upload/files/201612161911174ruvdxws.jpg', null, 'upload/files/201612161911174ruvdxws.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881865907db74015908069bf6000c', null, '4afbfbedab64034f9015f1bca8c379310b551dab', null, '2016-12-16 22:26:07', 'jpg', null, 'upload/files/20161216222607YvbDnX3S.jpg', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20161216222607YvbDnX3S.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e86223f2de016223f48bb20004', null, '20170823190022AS4zjxbQ', null, '2018-03-14 18:00:51', 'png', null, 'upload/files/20180314180051BzZiGwyV.png', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314180051BzZiGwyV.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c101622407a4f20001', null, '20170823190022AS4zjxbQ', null, '2018-03-14 18:21:43', 'png', null, 'upload/files/20180314182143NbxL6IkH.png', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182143NbxL6IkH.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c1016224083f0f0003', null, '20180314111124jUmrvtaw', null, '2018-03-14 18:22:22', 'jpg', null, 'upload/files/20180314182222ADWKc2dN.jpg', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182222ADWKc2dN.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c101622408a0600005', null, '20180314112305d7mI1I7k', null, '2018-03-14 18:22:47', 'jpg', null, 'upload/files/20180314182247ZR3HVe9B.jpg', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182247ZR3HVe9B.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c101622408ce030007', null, '20180314112305d7mI1I7k', null, '2018-03-14 18:22:59', 'jpg', null, 'upload/files/20180314182259bWOKOVH5.jpg', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182259bWOKOVH5.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c101622408fbb70009', null, '20180314110154ZkWNdEWE', null, '2018-03-14 18:23:11', 'png', null, 'upload/files/20180314182311zTY0A5TW.png', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182311zTY0A5TW.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('402881e8622405c101622409d84d000b', null, '20180314110154ZkWNdEWE', null, '2018-03-14 18:24:07', 'png', null, 'upload/files/20180314182407ZiEC8Cug.png', 'org.jeecgframework.web.system.pojo.base.TSDocument', 'upload/files/20180314182407ZiEC8Cug.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('4028948151dc770f0151dc7e8c5a0005', null, 'u=2838363326,1449558984&fm=21&gp=0', null, null, 'jpg', null, 'upload/files/20151226121419XByRRIAP.jpg', null, 'upload/files/20151226121419XByRRIAP.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('4028948151dc770f0151dc7e8d050006', null, 'u=1363640605,4248245426&fm=21&gp=0', null, null, 'jpg', null, 'upload/files/20151226121419g57TQ4dt.jpg', null, 'upload/files/20151226121419g57TQ4dt.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('4028948151dc770f0151dc7e8d7e0007', null, 'u=3142339483,1901057718&fm=21&gp=0', null, null, 'jpg', null, 'upload/files/20151226121420U1FbymIQ.jpg', null, 'upload/files/20151226121420U1FbymIQ.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('4028948151dc770f0151dc7e8e020008', null, 'u=175527439,2098210574&fm=21&gp=0', null, null, 'jpg', null, 'upload/files/20151226121420DtR5hB67.jpg', null, 'upload/files/20151226121420DtR5hB67.swf', null, null, null);
INSERT INTO `t_s_attachment` VALUES ('4028948151dc770f0151dc7e8e8b0009', null, 'u=4125376057,1493775393&fm=21&gp=0', null, null, 'jpg', null, 'upload/files/20151226121420FiCmHjrJ.jpg', null, 'upload/files/20151226121420FiCmHjrJ.swf', null, null, null);

-- ----------------------------
-- Table structure for t_s_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_base_user`;
CREATE TABLE `t_s_base_user` (
  `ID` varchar(32) NOT NULL,
  `activitiSync` smallint(6) default NULL,
  `browser` varchar(20) default NULL,
  `password` varchar(100) default NULL,
  `realname` varchar(50) default NULL,
  `signature` blob,
  `status` smallint(6) default NULL,
  `userkey` varchar(200) default NULL,
  `username` varchar(10) NOT NULL,
  `departid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_15jh1g4iem1857546ggor42et` (`departid`),
  CONSTRAINT `FK_15jh1g4iem1857546ggor42et` FOREIGN KEY (`departid`) REFERENCES `t_s_depart` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_base_user
-- ----------------------------
INSERT INTO `t_s_base_user` VALUES ('402881e4461f9c6401461fa2e6f50002', null, null, '4be1dd049390623054515c6d433c4e3c', 'tingfeng', null, '1', null, 'tingfeng', '4028d881436d514601436d5214d70015');
INSERT INTO `t_s_base_user` VALUES ('402881e44648134a014648174a45000c', null, null, 'e0d5cae5376f58ac', 'jeecg测试', null, '1', null, 'ceshi', '4028d881436d514601436d5214d70015');
INSERT INTO `t_s_base_user` VALUES ('4028d881436d514601436d5215ac0043', null, null, 'c44b01947c9e6e3f', '管理员', null, '1', null, 'admin', '4028d881436d514601436d5214d70015');
INSERT INTO `t_s_base_user` VALUES ('4028d881436d514601436d5215b20044', null, null, '97c07a884bf272b5', '张代浩', null, '1', null, 'scott', '4028d881436d514601436d5214f30017');
INSERT INTO `t_s_base_user` VALUES ('4028d881436d514601436d5215b80045', '0', null, 'f2322ec2fb9f40d1', '采购员', null, '1', null, 'cgy', '4028d881436d514601436d5214d70015');
INSERT INTO `t_s_base_user` VALUES ('4028d881436d514601436d5215bc0046', '1', null, 'a324509dc1a3089a', '采购审批员', null, '1', null, 'cgspy', '4028d881436d514601436d5214d70015');

-- ----------------------------
-- Table structure for t_s_config
-- ----------------------------
DROP TABLE IF EXISTS `t_s_config`;
CREATE TABLE `t_s_config` (
  `ID` varchar(32) NOT NULL,
  `code` varchar(100) default NULL,
  `content` longtext,
  `name` varchar(100) NOT NULL,
  `note` longtext,
  `userid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_m3q8r50ror4fl7fjkvd82tqgn` (`userid`),
  CONSTRAINT `FK_m3q8r50ror4fl7fjkvd82tqgn` FOREIGN KEY (`userid`) REFERENCES `t_s_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_config
-- ----------------------------

-- ----------------------------
-- Table structure for t_s_demo
-- ----------------------------
DROP TABLE IF EXISTS `t_s_demo`;
CREATE TABLE `t_s_demo` (
  `ID` varchar(32) NOT NULL,
  `democode` longtext,
  `demoorder` smallint(6) default NULL,
  `demotitle` varchar(200) default NULL,
  `demourl` varchar(200) default NULL,
  `demopid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_fni8e3v88wcf2sahhlv57u4nm` (`demopid`),
  CONSTRAINT `FK_fni8e3v88wcf2sahhlv57u4nm` FOREIGN KEY (`demopid`) REFERENCES `t_s_demo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_demo
-- ----------------------------
INSERT INTO `t_s_demo` VALUES ('4028d881436d514601436d52186900c4', '<div class=\"form\">\r\n   <label class=\"Validform_label\">\r\n     非空验证：\r\n    </label>\r\n    <input name=\"demotitle\" id=\"demotitle\" datatype=\"*\" errormsg=\"该字段不为空\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     URL验证：\r\n    </label>\r\n    <input name=\"demourl\" id=\"demourl\" datatype=\"url\" errormsg=\"必须是URL\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     至少选择2项：\r\n    </label>\r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite21\" type=\"checkbox\" value=\"1\" datatype=\"need2\" nullmsg=\"请选择您的爱好！\" />\r\n  \r\n     阅读\r\n  \r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite22\" type=\"checkbox\" value=\"2\" />\r\n    \r\n     音乐\r\n  \r\n    <input name=\"shoppingsite1\" class=\"rt2\" id=\"shoppingsite23\" type=\"checkbox\" value=\"3\" />\r\n  \r\n     运动\r\n   \r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     邮箱：\r\n    </label>\r\n    <input name=\"demoorder\" id=\"demoorder\" datatype=\"e\" errormsg=\"邮箱非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     手机号：\r\n    </label>\r\n    <input name=\"phone\" id=\"phone\" datatype=\"m\" errormsg=\"手机号非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     金额：\r\n    </label>\r\n    <input name=\"money\" id=\"money\" datatype=\"d\" errormsg=\"金额非法\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     日期：\r\n    </label>\r\n    <input name=\"date\" id=\"date\" class=\"easyui-datebox\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>\r\n   <div class=\"form\">\r\n     <label class=\"Validform_label\">\r\n     时间：\r\n    </label>\r\n    <input name=\"time\" id=\"time\" class=\"easyui-datetimebox\">\r\n    <span class=\"Validform_checktip\"></span>\r\n   </div>', null, '表单验证', null, null);

-- ----------------------------
-- Table structure for t_s_depart
-- ----------------------------
DROP TABLE IF EXISTS `t_s_depart`;
CREATE TABLE `t_s_depart` (
  `ID` varchar(32) NOT NULL,
  `departname` varchar(100) NOT NULL,
  `description` longtext,
  `parentdepartid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_knnm3wb0bembwvm0il7tf6686` (`parentdepartid`),
  CONSTRAINT `FK_knnm3wb0bembwvm0il7tf6686` FOREIGN KEY (`parentdepartid`) REFERENCES `t_s_depart` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_depart
-- ----------------------------
INSERT INTO `t_s_depart` VALUES ('4028d881436d514601436d5214d70015', '信息部', '12', null);
INSERT INTO `t_s_depart` VALUES ('4028d881436d514601436d5214ef0016', '设计部', null, null);
INSERT INTO `t_s_depart` VALUES ('4028d881436d514601436d5214f30017', '研发室', '研发技术难题', '4028d881436d514601436d5214ef0016');

-- ----------------------------
-- Table structure for t_s_document
-- ----------------------------
DROP TABLE IF EXISTS `t_s_document`;
CREATE TABLE `t_s_document` (
  `documentstate` smallint(6) default NULL,
  `documenttitle` varchar(100) default NULL,
  `pictureindex` blob,
  `showhome` smallint(6) default NULL,
  `id` varchar(32) NOT NULL,
  `typeid` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_qr3qlmgkflj35m5ci1xv0vvg3` (`typeid`),
  KEY `FK_f2mc12eu0umghp2i70apmtxjl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_document
-- ----------------------------
INSERT INTO `t_s_document` VALUES (null, null, null, null, '297e7eb6469a4a8901469a5d96ea000d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '297e7eb6469a4a8901469a5ebac20010', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '297e7eb6469a9a0f01469a9b59d30001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '297e7eb6469a9a0f01469a9bc74b0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d04e001477d0a31040002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d04e001477d0a53150005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d04e001477d0dfcd80009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d04e001477d0e143d000a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d04e001477d0e1d31000d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402880e6477d5c2601477d5d81de0001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881865907db74015908069bf6000c', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446394419014639453ce90002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e4463cc88701463ccef5340001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44643207c01464333b1f9000c', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e4464381350146438691940002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44643813501464389b02a000b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44643a1cd014643a510060003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44643a1cd014643a55d6f0006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a014695210478000f', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a0146952144320012', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a0146952187bc0015', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a01469522f1ed0018', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a01469523431e001b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a01469523eb7f001e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695183a0146952422130021', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695970aeb0014', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c01469597394f0016', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c0146959757810018', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695977560001a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a1de0a001d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a38b470020', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a3cdea0022', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a4383a0024', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a4913b0026', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a4dda10027', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a726df0028', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a7705a002b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a7b814002e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a7ecf10031', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e446955c7c014695a831d70034', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695ed3bdc0001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695ed79fa0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695ef476c0005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f2c6280007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f3176d0009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f339a7000b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f3a3b3000d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f3f89f000f', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f443990011', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f45e1a0013', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f494290015', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e44695ebe3014695f4d5570017', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, '11111', null, null, '402881e845d1723e0145d18fef940019', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e86223f2de016223f48bb20004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c101622407a4f20001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c1016224083f0f0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c101622408a0600005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c101622408ce030007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c101622408fbb70009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e8622405c101622409d84d000b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146ddf061b50001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146ddf730350003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146ddf991610005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146de02f8ba0008', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146de0522e3000a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146de09f959000e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e946ddea480146de0f340b0010', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e947015a7001470160f9290001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e947015a70014701619f490004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e947015a700147016449650007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e94701df96014701eb22d30006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e94701df96014701f3f7160007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e94701df960147020b82d1000d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e9470273d20147028adf830019', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e9470273d20147028afde6001a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402881e94702c764014702ceee2e0004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889ed472e852301472eaee6f80009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889ed472e852301472eb0270b000d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889ed472ec2a801472ec3997c0001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889ed472ee23401472ee59ccf0001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f346dc66c30146dc7c11630001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f346dc66c30146dc7ccb410005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f346dc66c30146dc7cf66b0008', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f346dc8a220146dc933f720007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747048401474709372d0009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474704840147470a1ca7000e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474704840147470d48ab000f', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474704840147470d62570012', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474704840147470e7b920014', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747048401474710097e0017', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f647470484014747108feb001a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f647470484014747129c15001d', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f647470484014747133201001f', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f647470484014747137f230022', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747182f01474718ed750001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747182f01474719193e0004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747182f01474719c42b0007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747182f01474720ef4c000c', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f64747182f014747211b11000f', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474722ad01474723d3400001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474722ad01474723f78e0004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474722ad014747248d0f0007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f6474722ad01474724bdc2000a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae40147666df6eb0002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae40147666e16ff0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae40147671e80cf0017', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae401476729806f0018', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae401476729c6df001b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae401476730b187001e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f847666ae401476730f1240021', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f84767413b0147674352d30001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477ad29d01477ad8e51b0004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b0c1f01477b0e31e30003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b0c1f01477b0e44790006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b5b5b01477b5cb2c40002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b5b5b01477b5cc3e50003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b5b5b01477b5d02730006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b5b5b01477b5ef0a90009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b5b5b01477b5efd31000c', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b681801477b69179c0002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b681801477b692d7b0005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b6c5401477b6d39dc0002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b6c5401477b6d4ee40005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b6e4e01477b6efba10001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b6e4e01477b6f0ae10004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b70aa01477b71e16e0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b70aa01477b71ee4a0006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b70aa01477b75b3200009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477b70aa01477b75c54a000c', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c29665c0002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c298a7e0005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2a71d10007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2a81de000a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2b8bb4000b', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2b9bfd000e', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2e96f50012', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '402889f8477c283701477c2eb1300015', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '40289481515cf0fe01515cf8bb930017', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '40289481515cf0fe01515cf98a0c0018', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '40289481515cf0fe01515cfabbd1001a', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028bc064763571d0147635828440003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f0d8101472f14e0010002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f0d8101472f1ff82a0005', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f0d8101472f218b350008', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f356e01472f3695840002', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f356e01472f3f428e0006', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f356e01472f4256e00007', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f356e01472f42fcc40009', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f451c01472f481df30001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f451c01472f4cd0dc0003', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d881472f451c01472f4d0a920004', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d8814734ee0d0147351e604e0001', '4028d881436d514601436d5216190053');
INSERT INTO `t_s_document` VALUES (null, null, null, null, '4028d88147359f51014735a006fc0001', '4028d881436d514601436d5216190053');

-- ----------------------------
-- Table structure for t_s_fileno
-- ----------------------------
DROP TABLE IF EXISTS `t_s_fileno`;
CREATE TABLE `t_s_fileno` (
  `ID` varchar(32) NOT NULL,
  `filenobefore` varchar(32) default NULL,
  `filenonum` int(11) default NULL,
  `filenotype` varchar(32) default NULL,
  `filenoYear` date default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_fileno
-- ----------------------------

-- ----------------------------
-- Table structure for t_s_function
-- ----------------------------
DROP TABLE IF EXISTS `t_s_function`;
CREATE TABLE `t_s_function` (
  `ID` varchar(32) NOT NULL,
  `functioniframe` smallint(6) default NULL,
  `functionlevel` smallint(6) default NULL,
  `functionname` varchar(50) NOT NULL,
  `functionorder` varchar(255) default NULL,
  `functionurl` varchar(100) default NULL,
  `parentfunctionid` varchar(32) default NULL,
  `iconid` varchar(32) default NULL,
  `desk_iconid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_brd7b3keorj8pmxcv8bpahnxp` (`parentfunctionid`),
  KEY `FK_q5tqo3v4ltsp1pehdxd59rccx` (`iconid`),
  KEY `FK_gbdacaoju6d5u53rp4jo4rbs9` (`desk_iconid`),
  CONSTRAINT `FK_brd7b3keorj8pmxcv8bpahnxp` FOREIGN KEY (`parentfunctionid`) REFERENCES `t_s_function` (`ID`),
  CONSTRAINT `FK_gbdacaoju6d5u53rp4jo4rbs9` FOREIGN KEY (`desk_iconid`) REFERENCES `t_s_icon` (`ID`),
  CONSTRAINT `FK_q5tqo3v4ltsp1pehdxd59rccx` FOREIGN KEY (`iconid`) REFERENCES `t_s_icon` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_function
-- ----------------------------
INSERT INTO `t_s_function` VALUES ('297e7eb6469a4a8901469a54eff60006', null, '2', '文章管理', '3', 'cmsArticleController.do?cmsArticle', '402881e44695183a0146951af2b70004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('297edb62621d0e3d01621d42e2da0004', null, '2', '长链接转短链接', '6', 'weixinLong2shortController.do?weixinLong2short', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880e6477d61da01477d71430f001a', null, '1', 'JqueryFile示例', '10', 'fileUploadController.do?fileUploadSample', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880e6478c6d8201478c71c5460001', null, '2', '微相册', '03', 'weixinPhotoAlbumController.do?weixinPhotoAlbum', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f25906117601590614ca94000a', null, '0', '支付窗平台', '3', '', null, '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062581630001', null, '1', '基础设置', '1', '', '402880f25906117601590614ca94000a', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402880f25906234201590626596e0004', null, '1', '消息素材', '2', '', '402880f25906117601590614ca94000a', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062692fc0006', null, '1', '用户管理', '3', '', '402880f25906117601590614ca94000a', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402880f25906234201590626c28e0008', null, '1', '群发管理', '4', '', '402880f25906117601590614ca94000a', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062783c4000a', null, '2', '支付窗账号管理', '1', 'alipay/alipayAccount.do?list', '402880f2590623420159062581630001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f25906234201590627ef06000c', null, '2', '支付窗关键字管理', '2', 'alipay/alipayAutoresponse.do?list', '402880f2590623420159062581630001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f259062342015906288369000f', null, '2', '支付窗菜单管理', '3', 'alipay/alipayMenu.do?list', '402880f2590623420159062581630001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f25906234201590628d62c0011', null, '2', '支付窗关注回复', '4', 'alipay/alipayGzentity.do?list', '402880f2590623420159062581630001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062d921c0013', null, '2', '支付窗文本消息', '1', 'alipay/alipayTexttemplate.do?list', '402880f25906234201590626596e0004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062df7fe0016', null, '2', '支付窗图文消息', '2', 'alipay/alipayNewstemplate.do?list', '402880f25906234201590626596e0004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062ea59f0018', null, '2', '支付窗用户管理', '1', 'alipay/alipayGzuserinfo.do?list', '402880f2590623420159062692fc0006', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f2590623420159062f3b4a001a', null, '2', '支付窗用户消息', '2', 'alipay/alipayReceivetext.do?list', '402880f2590623420159062692fc0006', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f25906234201590630a5cc001c', null, '2', '支付窗消息群发', '1', 'alipay/groupMsg.do?toGroupMsgSend', '402880f25906234201590626c28e0008', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402880f259062342015906312d27001f', null, '2', '支付窗群发消息记录', '2', 'alipay/alipayMessagelog.do?list', '402880f25906234201590626c28e0008', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('40288186590896200159089bc6510002', null, '2', '用户地理位置', '4', 'qywx/qywxLocation.do?list', '4028ef8156b0c68f0156b0ca9d800015', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('40288186590896200159089c30620004', null, '1', '高级功能', '5', '', '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('40288186590896200159089c8a360006', null, '2', '企业号消息群发', '1', 'qywx/groupMsg.do?toGroupMsgSend', '40288186590896200159089c30620004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('40288186590896200159089ccf5b0008', null, '2', '企业号群发日志', '2', 'qywx/qywxMessagelog.do?list', '40288186590896200159089c30620004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e446677b700146677d7b600001', null, '2', '扩展接口', '09', 'weixinExpandconfigController.do?weixinExpandconfig', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e44695183a0146951af2b70004', null, '1', '微信网站', '4', '', '4028ef8156b0c68f0156b0d4cf390045', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402881e44695183a0146951b66050006', null, '2', '广告管理', '0', 'adController.do?ad', '402881e44695183a0146951af2b70004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e44695183a0146951ed79e0008', null, '2', '栏目管理', '1', 'cmsMenuController.do?menu', '402881e44695183a0146951af2b70004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e545f5dd1a0145f5de9bb60001', null, '1', '消息素材', '2', '', '4028ef8156b0c68f0156b0d4cf390045', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('402881e545f5dd1a0145f5f32cf00005', null, '2', '文本消息', '1', 'textTemplateController.do?list', '402881e545f5dd1a0145f5de9bb60001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e545f5f4780145f61198810004', null, '2', '图文消息', '2', 'newsTemplateController.do?list', '402881e545f5dd1a0145f5de9bb60001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402881e8460d7e5301460d81c7a60001', null, '2', '关键字管理', '3', 'autoResponseController.do?list', '4028d881436d514601436d52c2fb0181', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('402889f64747048401474706d2a50001', null, '2', '网站信息', '5', 'weixinCmsSiteController.do?weixinCmsSite', '402881e44695183a0146951af2b70004', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028948151d756d80151d758c25e0001', null, '2', '素材链接', '6', 'weixinLinksucaiController.do?privateList', '402881e545f5dd1a0145f5de9bb60001', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028948151dc6b1f0151dc6fdf05000c', null, '2', '用户消息', '08', 'receiveTextController.do?list', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215070018', null, '0', 'Online 开发', '12', '', null, '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52150a0019', null, '0', '系统管理', '11', '', null, '4028d881436d514601436d5214b80005', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52150b001a', null, '0', '统计查询', '5', '', null, '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52150d001b', null, '0', '常用示例', '8', '', null, '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52150f001c', null, '0', '系统监控', '7', '', null, '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521513001d', null, '1', '用户管理', '5', 'userController.do?user', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521516001e', null, '1', '角色管理', '6', 'roleController.do?role', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52151a001f', null, '1', '菜单管理', '7', 'functionController.do?function', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52151f0020', null, '1', '数据字典', '200', 'systemController.do?typeGroupList', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215220021', null, '1', '图标管理', '201', 'iconController.do?icon', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215260023', null, '1', '部门管理', '22', 'departController.do?depart', '4028d881436d514601436d52150a0019', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52152c0025', null, '1', '用户分析', '17', 'logController.do?statisticTabs&isIframe', '4028d881436d514601436d52150b001a', '4028d881436d514601436d5214b10001', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215300026', null, '1', '表单配置', '1', 'cgFormHeadController.do?cgFormHeadList', '4028d881436d514601436d5215070018', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215340027', null, '1', '动态报表配置', '2', 'cgreportConfigHeadController.do?cgreportConfigHead', '4028d881436d514601436d5215070018', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215380028', null, '1', '数据监控', '11', 'dataSourceController.do?goDruid&isIframe', '4028d881436d514601436d52150f001c', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52153b0029', null, '1', '系统日志', '21', 'logController.do?log', '4028d881436d514601436d52150f001c', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521540002a', null, '1', '定时任务', '21', 'timeTaskController.do?timeTask', '4028d881436d514601436d52150f001c', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521545002b', null, '1', '表单验证', '1', 'demoController.do?formTabs', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521549002c', null, '1', 'Demo示例', '2', 'jeecgDemoController.do?jeecgDemo', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52154d002d', null, '1', '单表模型', '3', 'jeecgNoteController.do?jeecgNote', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521552002e', null, '1', '一对多模型', '4', 'jeecgOrderMainController.do?jeecgOrderMain', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521557002f', null, '1', 'Excel导入导出', '5', 'courseController.do?course', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52155a0030', null, '1', '上传下载', '6', 'commonController.do?listTurn&turn=system/document/filesList', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52155f0031', null, '1', '无分页列表', '7', 'userNoPageController.do?user', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215640032', null, '1', 'jdbc示例', '8', 'jeecgJdbcController.do?jeecgJdbc', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215670033', null, '1', 'SQL分离', '9', 'jeecgJdbcController.do?dictParameter', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52156b0034', null, '1', '字典标签', '10', 'demoController.do?dictSelect', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52156f0035', null, '1', '表单弹出风格', '11', 'demoController.do?demoList', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215730036', null, '1', '特殊布局', '12', 'demoController.do?demoIframe', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215770037', null, '1', '单表例子(无Tag)', '13', 'jeecgEasyUIController.do?jeecgEasyUI', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52157a0038', null, '1', '一对多例子(无Tag)', '14', 'jeecgOrderMainNoTagController.do?jeecgOrderMainNoTag', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52157e0039', null, '1', 'HTML编辑器', '15', 'jeecgDemoController.do?ckeditor&isIframe', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521581003a', null, '1', '在线word(IE)', '16', 'webOfficeController.do?webOffice', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521584003b', null, '1', 'WebOffice官方例子', '17', 'webOfficeController.do?webOfficeSample&isIframe', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521587003c', null, '1', '多附件管理', '18', 'tFinanceController.do?tFinance', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52158a003d', null, '1', 'Datagrid手工Html', '19', 'userController.do?userDemo', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52158f003e', null, '1', '物料Bom', '20', 'jeecgMatterBomController.do?goList', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d521593003f', null, '1', '报表示例', '21', 'reportDemoController.do?studentStatisticTabs&isIframe', '4028d881436d514601436d52150b001a', '4028d881436d514601436d5214b10001', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5215990040', null, '1', 'ckfinder例子', '100', 'jeecgDemoCkfinderController.do?jeecgDemoCkfinder', '4028d881436d514601436d52150d001b', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d52c2fb0181', null, '1', '基础设置', '1', '', '4028ef8156b0c68f0156b0d4cf390045', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5449330189', null, '1', '微活动', '3', '', '4028ef8156b0c68f0156b0d4cf390045', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5618400191', null, '2', '关注欢迎语', '2', 'subscribeController.do?list', '4028d881436d514601436d52c2fb0181', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5884be019d', null, '2', '自定义菜单', '4', 'menuManagerController.do?list', '4028d881436d514601436d52c2fb0181', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d59984601a5', null, '2', '大转盘', '01', 'huodongController.do?list&type=2', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d59d6d901a7', null, '2', '刮刮乐', '02', 'huodongController.do?list&type=1', '4028d881436d514601436d5449330189', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028d881436d514601436d5af95501b1', null, '2', '公众帐号管理', '1', 'weixinAccountController.do?weixinAccount', '4028d881436d514601436d52c2fb0181', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b07fa50156b082a1100005', null, '0', '插件演示', '6', '', null, '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b07fa50156b082d9310007', null, '1', '普通列表', '1', 'p3/wxActInvite.do?list', '4028ef8156b07fa50156b082a1100005', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b07fa50156b08315c20009', null, '1', '树列表', '2', 'p3/auth.do?list', '4028ef8156b07fa50156b082a1100005', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0c88eb10001', null, '0', '微信企业号平台', '2', '', null, '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0c8c0c60003', null, '1', '账号管理', '1', '', '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0c920440005', null, '2', '企业号管理', '1', 'qywx/qywxAccount.do?list', '4028ef8156b0c68f0156b0c8c0c60003', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0c956120007', null, '2', '应用管理', '2', 'qywx/qywxAgent.do?list', '4028ef8156b0c68f0156b0c8c0c60003', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0ca47730011', null, '1', '基础管理', '2', '', '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0ca69760013', null, '1', '微信素材', '3', '', '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0ca9d800015', null, '1', '消息管理', '4', '', '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d5214ba0007', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cadace0017', null, '2', '企业号关键字管理', '1', 'qywx/qywxAutoresponse.do?list', '4028ef8156b0c68f0156b0ca47730011', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cb1d7c0019', null, '2', '企业号关注回复', '2', 'qywx/qywxGzentity.do?list', '4028ef8156b0c68f0156b0ca47730011', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cb5675001b', null, '2', '企业号菜单管理', '3', 'qywx/qywxMenu.do?list', '4028ef8156b0c68f0156b0ca47730011', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cbdb2f001d', null, '2', '企业号文本消息', '1', 'qywx/qywxTexttemplate.do?list', '4028ef8156b0c68f0156b0ca69760013', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cc0dc7001f', null, '2', '企业号图文消息', '2', 'qywx/qywxNewstemplate.do?list', '4028ef8156b0c68f0156b0ca69760013', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0cc5c280021', null, '2', '企业号用户消息', '1', 'qywx/qywxReceivetext.do?list', '4028ef8156b0c68f0156b0ca9d800015', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0ccab2e0023', null, '2', '企业号关注用户', '2', 'qywx/qywxGzuserinfo.do?list', '4028ef8156b0c68f0156b0ca9d800015', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0ccefde0025', null, '2', '企业号通讯录', '3', 'qywx/qywxGroup.do?list', '4028ef8156b0c68f0156b0ca9d800015', '4028d881436d514601436d5214b30002', null);
INSERT INTO `t_s_function` VALUES ('4028ef8156b0c68f0156b0d4cf390045', null, '0', '微信公众平台', '1', '', null, '4028d881436d514601436d5214b30002', null);

-- ----------------------------
-- Table structure for t_s_icon
-- ----------------------------
DROP TABLE IF EXISTS `t_s_icon`;
CREATE TABLE `t_s_icon` (
  `ID` varchar(32) NOT NULL,
  `extend` varchar(255) default NULL,
  `iconclas` varchar(200) default NULL,
  `content` blob,
  `name` varchar(100) NOT NULL,
  `path` longtext,
  `type` smallint(6) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_icon
-- ----------------------------
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214650000', 'png', 'back', null, '返回', 'plug-in/accordion/images/back.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b10001', 'png', 'pie', null, '饼图', 'plug-in/accordion/images/pie.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b30002', 'png', 'pictures', null, '图片', 'plug-in/accordion/images/pictures.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b40003', 'png', 'pencil', null, '笔', 'plug-in/accordion/images/pencil.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b50004', 'png', 'map', null, '地图', 'plug-in/accordion/images/map.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b80005', 'png', 'group_add', null, '组', 'plug-in/accordion/images/group_add.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214b90006', 'png', 'calculator', null, '计算器', 'plug-in/accordion/images/calculator.png', '1');
INSERT INTO `t_s_icon` VALUES ('4028d881436d514601436d5214ba0007', 'png', 'folder', null, '文件夹', 'plug-in/accordion/images/folder.png', '1');

-- ----------------------------
-- Table structure for t_s_log
-- ----------------------------
DROP TABLE IF EXISTS `t_s_log`;
CREATE TABLE `t_s_log` (
  `ID` varchar(32) NOT NULL,
  `broswer` varchar(100) default NULL,
  `logcontent` longtext NOT NULL,
  `loglevel` smallint(6) default NULL,
  `note` longtext,
  `operatetime` datetime NOT NULL,
  `operatetype` smallint(6) default NULL,
  `userid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_oe64k4852uylhyc5a00rfwtay` (`userid`),
  CONSTRAINT `FK_oe64k4852uylhyc5a00rfwtay` FOREIGN KEY (`userid`) REFERENCES `t_s_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_log
-- ----------------------------
INSERT INTO `t_s_log` VALUES ('402880f158f1393c0158f13b298d0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.113', '2016-12-12 12:12:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f158f609210158f6097fce0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.113', '2016-12-13 10:36:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f158f60d6f0158f6118e370000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.113', '2016-12-13 10:44:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f158f60d6f0158f6b516240001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.113', '2016-12-13 13:43:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb12550158fb13a8d50000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-14 10:05:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb3e7f0158fb3ee72f0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-14 10:52:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb3e7f0158fb3f2fb80001', 'Chrome', '更新开源项目1的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:52:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb3e7f0158fb3f41550002', 'Chrome', '更新Jeewx微信管家的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:52:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb3e7f0158fb3f53fa0003', 'Chrome', '更新Jeewx微信管家1的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:53:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb3e7f0158fb3f60d50004', 'Chrome', '更新开源项目11的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:53:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb402b0158fb4076800000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-14 10:54:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb402b0158fb40a0b90001', 'Chrome', '更新开源项目的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:54:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f258fb402b0158fb40b0870002', 'Chrome', '更新开源项目1的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-14 10:54:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590125720159012603180000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-15 14:23:05', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d01590609698a0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:09:57', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060c3a060001', 'Chrome', '权限: 微信公众号被更新成功', '5', '192.168.0.114', '2016-12-16 13:13:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060dd39e0002', 'Chrome', '权限: 微信企业号被更新成功', '5', '192.168.0.114', '2016-12-16 13:14:46', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060df4090003', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:14:54', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060e18490004', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:15:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060eb4a60005', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:15:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060ee0230006', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:15:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060f4cd50007', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:16:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906090d0159060f64ff0008', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:16:29', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906117601590611f03d0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:19:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906117601590612666e0001', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:19:46', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590611760159061282380002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:19:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259061176015906132f680003', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:20:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259061176015906134ff30004', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:20:46', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259061176015906142e5d0006', 'Chrome', '权限: 支付窗平台被添加成功', '3', '192.168.0.114', '2016-12-16 13:21:43', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590611760159061464550007', 'Chrome', '权限: 支付窗平台被删除成功', '4', '192.168.0.114', '2016-12-16 13:21:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906117601590614911b0008', 'Chrome', '权限: 微信公众平台被更新成功', '5', '192.168.0.114', '2016-12-16 13:22:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906117601590614af4a0009', 'Chrome', '权限: 微信企业号平台被更新成功', '5', '192.168.0.114', '2016-12-16 13:22:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906117601590614ca9d000b', 'Chrome', '权限: 支付窗平台被添加成功', '3', '192.168.0.114', '2016-12-16 13:22:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259061d6e0159061df0520000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:32:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590624ae000000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:39:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062581750002', 'Chrome', '权限: 账号管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:40:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590625d8890003', 'Chrome', '权限: 基础设置被更新成功', '5', '192.168.0.114', '2016-12-16 13:41:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062659790005', 'Chrome', '权限: 消息素材被添加成功', '3', '192.168.0.114', '2016-12-16 13:41:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062693070007', 'Chrome', '权限: 用户管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:41:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590626c2990009', 'Chrome', '权限: 群发管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:42:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062783ce000b', 'Chrome', '权限: 支付窗账号管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:42:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590627ef10000d', 'Chrome', '权限: 关键字管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:43:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259062342015906281271000e', 'Chrome', '权限: 支付窗关键字管理被更新成功', '5', '192.168.0.114', '2016-12-16 13:43:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062883740010', 'Chrome', '权限: 支付窗菜单管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:43:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590628d6350012', 'Chrome', '权限: 支付窗关注回复被添加成功', '3', '192.168.0.114', '2016-12-16 13:44:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062d92230014', 'Chrome', '权限: 文本消息被添加成功', '3', '192.168.0.114', '2016-12-16 13:49:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062dbd560015', 'Chrome', '权限: 支付窗文本消息被更新成功', '5', '192.168.0.114', '2016-12-16 13:49:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062df8060017', 'Chrome', '权限: 支付窗图文消息被添加成功', '3', '192.168.0.114', '2016-12-16 13:49:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062ea5ab0019', 'Chrome', '权限: 支付窗用户管理被添加成功', '3', '192.168.0.114', '2016-12-16 13:50:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159062f3b53001b', 'Chrome', '权限: 支付窗用户消息被添加成功', '3', '192.168.0.114', '2016-12-16 13:51:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590630a5e0001d', 'Chrome', '权限: 消息群发被添加成功', '3', '192.168.0.114', '2016-12-16 13:52:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590630e2b6001e', 'Chrome', '权限: 支付窗消息群发被更新成功', '5', '192.168.0.114', '2016-12-16 13:53:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259062342015906312d320020', 'Chrome', '权限: 支付窗群发消息记录被添加成功', '3', '192.168.0.114', '2016-12-16 13:53:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259062342015906319cfa0030', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:53:51', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590631ba2e0031', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:53:59', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590632cfe00032', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:55:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590632e98b0033', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:55:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633418f0034', 'Chrome', '权限: 统计查询被更新成功', '5', '192.168.0.114', '2016-12-16 13:55:39', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590623420159063362ab0035', 'Chrome', '权限: 插件演示被更新成功', '5', '192.168.0.114', '2016-12-16 13:55:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633789b0036', 'Chrome', '权限: 系统监控被更新成功', '5', '192.168.0.114', '2016-12-16 13:55:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633a4f20037', 'Chrome', '权限: 系统管理被更新成功', '5', '192.168.0.114', '2016-12-16 13:56:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633b0a70038', 'Chrome', '权限: Online 开发被更新成功', '5', '192.168.0.114', '2016-12-16 13:56:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633ddcb0039', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 13:56:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590633f74b003a', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 13:56:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906234201590638b9e1003b', 'Chrome', '用户admin已退出', '2', '192.168.0.114', '2016-12-16 14:01:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590673d70159067425320000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 15:06:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906ae47015906aed3e10000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 16:10:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906b6ce015906b76c510000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 16:20:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906ba42015906bbea5f0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 16:24:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906c4b1015906c50a280000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 16:34:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906d097015906d0f7df0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 16:47:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906d097015906d2a4140001', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.0.114', '2016-12-16 16:49:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25906d48f015906e41e900000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 17:08:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259073c360159074493bc0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 18:54:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259073c3601590746b1950001', 'Chrome', '修改关文本模板成功！', '5', '192.168.0.114', '2016-12-16 18:56:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259073c36015907483a720002', 'Chrome', '修改关文本模板成功！', '5', '192.168.0.114', '2016-12-16 18:58:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074a2ab70000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.114', '2016-12-16 19:00:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074a8c430001', 'Chrome', '修改关文本模板成功！', '5', '192.168.0.114', '2016-12-16 19:00:43', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074aef1c0002', 'Chrome', '修改关文本模板成功！', '5', '192.168.0.114', '2016-12-16 19:01:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074b13370003', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.0.114', '2016-12-16 19:01:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074bc5ed0005', 'Chrome', '修改关文本模板成功！', '5', '192.168.0.114', '2016-12-16 19:02:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074e1b3b0008', 'Chrome', '更新微商城的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-16 19:04:36', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074eb9c9000a', 'Chrome', '添加微相册的信息成功！', '3', '192.168.0.114', '2016-12-16 19:05:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074ef324000b', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.0.114', '2016-12-16 19:05:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f2590749060159074f42a2000c', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.0.114', '2016-12-16 19:05:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590753b9da000e', 'Chrome', '微相册更新成功', '5', '192.168.0.114', '2016-12-16 19:10:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590753f99c0010', 'Chrome', '微相册添加成功', '3', '192.168.0.114', '2016-12-16 19:11:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259074906015907542edc0012', 'Chrome', '添加成功', '3', '192.168.0.114', '2016-12-16 19:11:14', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259074906015907555b2b0017', 'Chrome', '活动更新成功', '5', '192.168.0.114', '2016-12-16 19:12:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590757811e001a', 'Chrome', '添加触发图文的信息成功！', '3', '192.168.0.114', '2016-12-16 19:14:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590757f34e001b', 'Chrome', '更新关于我们的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-16 19:15:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259074906015907586847001c', 'Chrome', '更新社区培训的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-16 19:15:51', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590758e1f6001d', 'Chrome', '更新联系我们的菜单信息信息成功！', '5', '192.168.0.114', '2016-12-16 19:16:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f259074906015907593698001f', 'Chrome', '添加联系我们的信息成功！', '3', '192.168.0.114', '2016-12-16 19:16:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f25907490601590759a05b0020', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.0.114', '2016-12-16 19:17:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a355e01591a361c4a0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 11:11:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a58f101591a5a0fb40000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 11:50:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a5be201591a72f8020000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 12:17:39', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a770001591a77423c0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 12:22:20', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a8ef001591a8f61c80000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 12:48:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a8ef001591a93d5170001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 12:53:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591a8ef001591abeef620002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 13:40:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591b2a3001591b2ace540000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 15:38:27', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591b2a3001591b40b08a0002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 16:02:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591ba19b01591ba1ed970000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 17:48:34', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591bc49901591bc5c6440000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 18:27:43', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591bf0d301591bf135bb0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 19:15:09', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591bf0d301591bf1fec60001', 'Chrome', '用户admin已退出', '2', '192.168.0.115', '2016-12-20 19:16:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402880f3591bf0d301591bf2bbcb0002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.0.115', '2016-12-20 19:16:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db74015907e281b30000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 21:46:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db74015907edcd160001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 21:59:02', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db74015907f004250002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 22:01:27', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db74015907f39ddd0003', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 22:05:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db74015907fd21b30004', 'Chrome', '用户: admin[信息部]登录成功', '1', '127.0.0.1', '2016-12-16 22:15:46', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db7401590805ee18000b', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 22:25:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865907db7401590806c52e000e', 'Chrome', '微信单图消息添加成功', '3', '192.168.1.6', '2016-12-16 22:26:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590834a30159083731a30000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 23:19:12', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028818659083e2a0159083ece250000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-16 23:27:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028818659085f530159085fe20f0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 00:03:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590878be0159087987e70000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 00:31:39', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590880a70159088142fc0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 00:40:06', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590887bc0159088826100000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 00:47:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028818659089620015908967ced0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:03:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028818659089620015908986d790001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:05:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089bc6610003', 'Chrome', '权限: 用户地理位置被添加成功', '3', '192.168.1.6', '2016-12-17 01:09:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089c306e0005', 'Chrome', '权限: 高级功能被添加成功', '3', '192.168.1.6', '2016-12-17 01:09:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089c8a460007', 'Chrome', '权限: 企业号消息群发被添加成功', '3', '192.168.1.6', '2016-12-17 01:09:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089ccf6f0009', 'Chrome', '权限: 企业号群发日志被添加成功', '3', '192.168.1.6', '2016-12-17 01:10:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089cfa5c000d', 'Chrome', '用户admin已退出', '2', '192.168.1.6', '2016-12-17 01:10:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089d2b0e000e', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:10:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089d9017000f', 'Chrome', '权限: 高级功能被更新成功', '5', '192.168.1.6', '2016-12-17 01:11:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089e98610010', 'Chrome', '用户admin已退出', '2', '192.168.1.6', '2016-12-17 01:12:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089eb41a0011', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:12:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089efbfe0013', 'Chrome', '用户admin已退出', '2', '192.168.1.6', '2016-12-17 01:12:34', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590896200159089f16000014', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:12:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881865908ad73015908adcceb0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 01:28:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac509c80000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 11:13:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac62c310001', 'Chrome', '权限: 基础设置被更新成功', '5', '192.168.1.6', '2016-12-17 11:14:36', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac643080002', 'Chrome', '权限: 消息素材被更新成功', '5', '192.168.1.6', '2016-12-17 11:14:42', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac65c880003', 'Chrome', '权限: 用户管理被更新成功', '5', '192.168.1.6', '2016-12-17 11:14:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac673930004', 'Chrome', '权限: 群发管理被更新成功', '5', '192.168.1.6', '2016-12-17 11:14:54', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac681460005', 'Chrome', '用户admin已退出', '2', '192.168.1.6', '2016-12-17 11:14:58', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590ac45401590ac699860006', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 11:15:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590b0be801590b0f02600000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 12:34:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186590b208c01590b2156310000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-17 12:54:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591caaf2bf0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.6', '2016-12-20 22:38:02', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591cc6a3650001', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.1.6', '2016-12-20 23:08:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591cc6b31f0002', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.1.6', '2016-12-20 23:08:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591cc774de0004', 'Chrome', '用户: scott更新成功', '5', '192.168.1.6', '2016-12-20 23:09:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591cc793650006', 'Chrome', '用户: scott更新成功', '5', '192.168.1.6', '2016-12-20 23:09:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('40288186591caa1001591cc7c4990008', 'Chrome', '用户: scott更新成功', '5', '192.168.1.6', '2016-12-20 23:09:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223f332f80000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.104', '2018-03-14 17:59:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223f432510002', 'Chrome', '用户admin已退出', '2', '192.168.1.104', '2018-03-14 18:00:29', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223f44b6b0003', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.104', '2018-03-14 18:00:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223f4f36b0006', 'Chrome', '微站点信息添加成功', '3', '192.168.1.104', '2018-03-14 18:01:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223f6537f0008', 'Chrome', '添加小程序的信息成功！', '3', '192.168.1.104', '2018-03-14 18:02:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fa8b1e0009', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.1.104', '2018-03-14 18:07:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fb2c28000a', 'Chrome', '微信单图消息更新成功', '5', '192.168.1.104', '2018-03-14 18:08:06', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fb4f54000b', 'Chrome', '微信单图消息更新成功', '5', '192.168.1.104', '2018-03-14 18:08:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fbdc28000d', 'Chrome', '长连接转短链接添加成功', '3', '192.168.1.104', '2018-03-14 18:08:51', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fbe4ae000e', 'Chrome', '链接转换成功', '5', '192.168.1.104', '2018-03-14 18:08:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fce18d000f', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.1.104', '2018-03-14 18:09:58', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e86223f2de016223fd64c50011', 'Chrome', '回复信息成功！', '5', '192.168.1.104', '2018-03-14 18:10:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c10162240622140000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.104', '2018-03-14 18:20:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c101622407ab730002', 'Chrome', '信息更新成功', '5', '192.168.1.104', '2018-03-14 18:21:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c10162240843570004', 'Chrome', '信息更新成功', '5', '192.168.1.104', '2018-03-14 18:22:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c101622408a67d0006', 'Chrome', '信息更新成功', '5', '192.168.1.104', '2018-03-14 18:22:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c101622408d48b0008', 'Chrome', '信息更新成功', '5', '192.168.1.104', '2018-03-14 18:23:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c101622409077f000a', 'Chrome', '信息更新成功', '5', '192.168.1.104', '2018-03-14 18:23:14', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c101622409ddbd000c', 'Chrome', '微站点信息更新成功', '5', '192.168.1.104', '2018-03-14 18:24:09', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c10162240be831000d', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.1.104', '2018-03-14 18:26:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c10162240eb8e1000e', 'Chrome', '信息删除成功', '4', '192.168.1.104', '2018-03-14 18:29:27', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('402881e8622405c10162242f71c6000f', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.1.104', '2018-03-14 19:05:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151d85f5c0151d86164480000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-25 17:04:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc628d0151dc643c080000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 11:45:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc6dfe7b0005', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 11:56:14', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc6f1ff70008', 'Chrome', '权限: 高级功能被添加成功', '3', '192.168.20.1', '2015-12-26 11:57:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc6f51990009', 'Chrome', '权限: 高级功能被删除成功', '4', '192.168.20.1', '2015-12-26 11:57:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc6f7002000b', 'Chrome', '权限: 高级功能被添加成功', '3', '192.168.20.1', '2015-12-26 11:57:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc6fdf55000d', 'Chrome', '权限: 接受消息管理被添加成功', '3', '192.168.20.1', '2015-12-26 11:58:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc701bc10010', 'Chrome', '用户admin已退出', '2', '192.168.20.1', '2015-12-26 11:58:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc7035020011', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 11:58:39', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc70fc890012', 'Chrome', '回复信息成功！', '5', '192.168.20.1', '2015-12-26 11:59:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc7123180013', 'Chrome', '删除信息数据成功！', '4', '192.168.20.1', '2015-12-26 11:59:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc72aee00014', 'Chrome', '权限: 扩展接口被更新成功', '5', '192.168.20.1', '2015-12-26 12:01:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc72c03f0015', 'Chrome', '用户admin已退出', '2', '192.168.20.1', '2015-12-26 12:01:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc72d98e0016', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 12:01:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc7368030017', 'Chrome', '权限: 接受消息被更新成功', '5', '192.168.20.1', '2015-12-26 12:02:09', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc7397430018', 'Chrome', '用户admin已退出', '2', '192.168.20.1', '2015-12-26 12:02:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc6b1f0151dc73bbd90019', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 12:02:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc770f0151dc7783fd0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 12:06:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc770f0151dc7d6f5e0002', 'Chrome', '微相册添加成功', '3', '192.168.20.1', '2015-12-26 12:13:06', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc770f0151dc7e8b5c0004', 'Chrome', '添加成功', '3', '192.168.20.1', '2015-12-26 12:14:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dc770f0151dc817a4d000a', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.20.1', '2015-12-26 12:17:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dcf9c20151dcfb621a0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 14:30:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd0a7f0151dd0c39c50000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 14:49:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd196a870000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 15:03:29', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd19b3be0001', 'Chrome', '活动更新成功', '5', '192.168.20.1', '2015-12-26 15:03:47', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd19d6f40002', 'Chrome', '活动更新成功', '5', '192.168.20.1', '2015-12-26 15:03:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd19f9f50003', 'Chrome', '活动更新成功', '5', '192.168.20.1', '2015-12-26 15:04:05', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd1a27ba0004', 'Chrome', '活动更新成功', '5', '192.168.20.1', '2015-12-26 15:04:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd1a42380005', 'Chrome', '活动更新成功', '5', '192.168.20.1', '2015-12-26 15:04:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd1bd8710006', 'Chrome', '更新开源项目的菜单信息信息成功！', '5', '192.168.20.1', '2015-12-26 15:06:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd17b50151dd1de3660007', 'Chrome', '更新开源项目的菜单信息信息成功！', '5', '192.168.20.1', '2015-12-26 15:08:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd1f1d0151dd1fa71a0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 15:10:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd1f1d0151dd202d4d0001', 'Chrome', '更新开源项目的菜单信息信息成功！', '5', '192.168.20.1', '2015-12-26 15:10:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151dd1f1d0151dd203c730002', 'Chrome', '更新开源项目的菜单信息信息成功！', '5', '192.168.20.1', '2015-12-26 15:10:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151de698b0151de6a00f50000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-26 21:11:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028948151e769b20151e76a901c0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.20.1', '2015-12-28 15:08:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd3ae850000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-16 22:28:12', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd4297a0001', 'Chrome', '微信公众帐号信息更新成功', '5', '192.168.111.1', '2016-03-16 22:28:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd464820002', 'Chrome', '同步菜单信息数据成功！', '4', '192.168.111.1', '2016-03-16 22:28:59', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd55e210005', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-03-16 22:30:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd57bb40006', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-16 22:30:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81537fd2f001537fd9fd8e0007', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-16 22:35:05', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153a1f1400153a1f595040000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-23 13:32:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153a207100153a21506370000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-23 14:06:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153a207100153a22978960001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-23 14:29:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153a253ef0153a254a10e0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-23 15:16:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153ad63b70153ad642e540000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-25 18:48:57', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153c7ae960153c7af88270000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-03-30 21:21:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153c7ae960153c7b06e880001', 'Chrome', '修改图文模板成功！', '5', '192.168.111.1', '2016-03-30 21:22:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153c7ae960153c7b09c900002', 'Chrome', '修改关文本模板成功！', '5', '192.168.111.1', '2016-03-30 21:22:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153eb2aba0153eb2b6eff0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-06 18:43:25', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153eb35cb0153eb3637c10000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-06 18:55:12', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153eb38710153eb390b4b0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-06 18:58:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153eb3c530153eb3cc8190000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-06 19:02:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f0672f0153f06794ad0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-07 19:07:13', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f0672f0153f0749fd50001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-07 19:21:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f4158e620000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:16:06', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f41625430001', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:16:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f416406e0002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:16:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f4181bbc0003', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:18:54', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f41866200004', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:19:13', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f41879000005', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:19:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f418a12a0006', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:19:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f418b0650007', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:19:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f4196e6e0008', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:20:20', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f421fd9d0009', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:29:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f42350c8000a', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:31:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f423b252000c', 'Chrome', '用户: admin更新成功', '5', '192.168.111.1', '2016-04-08 12:31:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f423bac3000d', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:31:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f423ef08000e', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-08 12:31:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8153f4151f0153f42e0dc2000f', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-08 12:42:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef815408341701540834a6440000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-12 10:02:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81540834170154083bd5020003', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-04-12 10:10:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef815408341701540847bd430004', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-12 10:23:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef81545764900154576557df0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-04-27 19:05:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b060180156b0609e950000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 11:52:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b060180156b060a6170001', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 11:52:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b063ccdd0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 11:55:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b0648cc50001', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 11:56:34', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b065df370002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 11:58:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b06717670003', 'Chrome', '权限: 用户消息被更新成功', '5', '192.168.111.1', '2016-08-22 11:59:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b0673c9f0004', 'Chrome', '权限: 扩展接口被更新成功', '5', '192.168.111.1', '2016-08-22 11:59:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067751a0005', 'Chrome', '权限: 用户消息被更新成功', '5', '192.168.111.1', '2016-08-22 11:59:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067954f0006', 'Chrome', '权限: 扩展接口被更新成功', '5', '192.168.111.1', '2016-08-22 11:59:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067b2c50007', 'Chrome', '权限: 微相册被更新成功', '5', '192.168.111.1', '2016-08-22 12:00:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067bed30008', 'Chrome', '权限: 高级功能被删除成功', '4', '192.168.111.1', '2016-08-22 12:00:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067cb110009', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 12:00:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b067ed3d000a', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 12:00:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b06a939e000b', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 12:03:09', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b061e10156b06ac77b000c', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 12:03:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b08059c00000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 12:26:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b081371a0001', 'Chrome', '权限: 消息素材被更新成功', '5', '192.168.111.1', '2016-08-22 12:27:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b082369b0003', 'Chrome', '权限: 插件demo被添加成功', '3', '192.168.111.1', '2016-08-22 12:28:58', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b082744a0004', 'Chrome', '权限: 插件demo被删除成功', '4', '192.168.111.1', '2016-08-22 12:29:14', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b082a1660006', 'Chrome', '权限: 插件演示被添加成功', '3', '192.168.111.1', '2016-08-22 12:29:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b082d9850008', 'Chrome', '权限: 普通列表被添加成功', '3', '192.168.111.1', '2016-08-22 12:29:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b083173f000a', 'Chrome', '权限: 树列表被添加成功', '3', '192.168.111.1', '2016-08-22 12:29:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b0833f00000e', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 12:30:06', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b07fa50156b08363e9000f', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 12:30:16', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b099ed0156b09ab7cd0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 12:55:44', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0a0560156b0a0d8660000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:02:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0bc690156b0bcc34b0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:32:56', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0bc690156b0be12a90001', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:34:21', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0bc690156b0beb6440002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:35:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c8421c0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:45:29', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c88efd0002', 'Chrome', '权限: 企业号平台被添加成功', '3', '192.168.111.1', '2016-08-22 13:45:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c8c10e0004', 'Chrome', '权限: 账号管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:46:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c920860006', 'Chrome', '权限: 企业号管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:46:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c956a20008', 'Chrome', '权限: 应用管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:46:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c961c90009', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:46:43', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c98582000a', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:46:52', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c9b3c3000f', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:47:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0c9cd820010', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:47:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ca47ad0012', 'Chrome', '权限: 基础管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:47:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ca6aa00014', 'Chrome', '权限: 微信素材被添加成功', '3', '192.168.111.1', '2016-08-22 13:47:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ca9dcf0016', 'Chrome', '权限: 消息管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:48:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cadbec0018', 'Chrome', '权限: 关键字管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:48:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cb1db7001a', 'Chrome', '权限: 关注回复被添加成功', '3', '192.168.111.1', '2016-08-22 13:48:36', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cb56ac001c', 'Chrome', '权限: 菜单管理被添加成功', '3', '192.168.111.1', '2016-08-22 13:48:51', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cbdb77001e', 'Chrome', '权限: 文本消息被添加成功', '3', '192.168.111.1', '2016-08-22 13:49:25', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cc0e0a0020', 'Chrome', '权限: 图文消息被添加成功', '3', '192.168.111.1', '2016-08-22 13:49:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cc5c680022', 'Chrome', '权限: 用户消息被添加成功', '3', '192.168.111.1', '2016-08-22 13:49:58', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ccab5e0024', 'Chrome', '权限: 关注用户被添加成功', '3', '192.168.111.1', '2016-08-22 13:50:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ccf0200026', 'Chrome', '权限: 通讯录被添加成功', '3', '192.168.111.1', '2016-08-22 13:50:36', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cd8b6c0027', 'Chrome', '权限: 文本消息被更新成功', '5', '192.168.111.1', '2016-08-22 13:51:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ce0ddb0033', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:51:49', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0ce2c5d0034', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:51:57', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cfa2040035', 'Chrome', '权限: 账号管理被更新成功', '5', '192.168.111.1', '2016-08-22 13:53:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cfc6d60036', 'Chrome', '权限: 基础管理被更新成功', '5', '192.168.111.1', '2016-08-22 13:53:42', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cfdd2e0037', 'Chrome', '权限: 微信素材被更新成功', '5', '192.168.111.1', '2016-08-22 13:53:47', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cff3980038', 'Chrome', '权限: 消息管理被更新成功', '5', '192.168.111.1', '2016-08-22 13:53:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0cffbf30039', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:53:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d02f2c003a', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:54:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d0bdb3003b', 'Chrome', '权限: 企业号关键字管理被更新成功', '5', '192.168.111.1', '2016-08-22 13:54:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d0dcc4003c', 'Chrome', '权限: 企业号关注回复被更新成功', '5', '192.168.111.1', '2016-08-22 13:54:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d0fd59003d', 'Chrome', '权限: 企业号菜单管理被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d1157c003e', 'Chrome', '权限: 企业号文本消息被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d13179003f', 'Chrome', '权限: 企业号图文消息被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:14', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d15ce60040', 'Chrome', '权限: 企业号用户消息被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:26', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d1768d0041', 'Chrome', '权限: 企业号关注用户被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d198bd0042', 'Chrome', '权限: 企业号通讯录被更新成功', '5', '192.168.111.1', '2016-08-22 13:55:41', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d1a23b0043', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 13:55:43', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d1bda80044', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 13:55:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d4cf820046', 'Chrome', '权限: 公众号平台被添加成功', '3', '192.168.111.1', '2016-08-22 13:59:12', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d4fe780047', 'Chrome', '权限: 基础设置被更新成功', '5', '192.168.111.1', '2016-08-22 13:59:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d5233a0048', 'Chrome', '权限: 消息素材被更新成功', '5', '192.168.111.1', '2016-08-22 13:59:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d53f380049', 'Chrome', '权限: 微活动被更新成功', '5', '192.168.111.1', '2016-08-22 13:59:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d55de4004a', 'Chrome', '权限: 微信网站被更新成功', '5', '192.168.111.1', '2016-08-22 13:59:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d59765004b', 'Chrome', '权限: 微信公众号平台被更新成功', '5', '192.168.111.1', '2016-08-22 14:00:03', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d5b8f5004c', 'Chrome', '权限: 基础设置被更新成功', '5', '192.168.111.1', '2016-08-22 14:00:11', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d5cf2c004d', 'Chrome', '权限: 消息素材被更新成功', '5', '192.168.111.1', '2016-08-22 14:00:17', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d5e57e004e', 'Chrome', '权限: 微活动被更新成功', '5', '192.168.111.1', '2016-08-22 14:00:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d5fad8004f', 'Chrome', '权限: 微信网站被更新成功', '5', '192.168.111.1', '2016-08-22 14:00:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d627650052', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:00:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d650dc0053', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:00:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d6c2cf0054', 'Chrome', '权限: 公众号平台被更新成功', '5', '192.168.111.1', '2016-08-22 14:01:19', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d6fde40055', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:01:34', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d728c20056', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:01:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d769e6006d', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:02:02', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0c68f0156b0d781b0006e', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:02:08', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0d944ec0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:04:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0d9d2740001', 'Chrome', '权限: 公众帐号管理被更新成功', '5', '192.168.111.1', '2016-08-22 14:04:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0d9f0d80002', 'Chrome', '权限: 关注欢迎语被更新成功', '5', '192.168.111.1', '2016-08-22 14:04:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0d9f9c20003', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:04:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0da1ca70004', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:04:59', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0da4fca0005', 'Chrome', '权限: 关键字管理被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:12', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0da6f5b0006', 'Chrome', '权限: 自定义菜单被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:20', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0da83ba0007', 'Chrome', '权限: 文本消息被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:25', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0da97400008', 'Chrome', '权限: 图文消息被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:30', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0daaa6f0009', 'Chrome', '权限: 素材链接被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dabdc3000a', 'Chrome', '权限: 大转盘被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:40', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dad102000b', 'Chrome', '权限: 刮刮乐被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:45', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dae386000c', 'Chrome', '权限: 微相册被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:50', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0daf911000d', 'Chrome', '权限: 用户消息被更新成功', '5', '192.168.111.1', '2016-08-22 14:05:55', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db10cd000e', 'Chrome', '权限: 扩展接口被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db2715000f', 'Chrome', '权限: 广告管理被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:07', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db3c270010', 'Chrome', '权限: 栏目管理被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:13', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db4fd00011', 'Chrome', '权限: 文章管理被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:18', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db62400012', 'Chrome', '权限: 网站信息被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db79920013', 'Chrome', '权限: 网站模板被更新成功', '5', '192.168.111.1', '2016-08-22 14:06:28', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db83760014', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:06:31', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0db9fed0015', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:06:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dc504c0016', 'Chrome', '权限: 企业号平台被更新成功', '5', '192.168.111.1', '2016-08-22 14:07:23', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dc746a0017', 'Chrome', '权限: 统计查询被更新成功', '5', '192.168.111.1', '2016-08-22 14:07:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dc892d0018', 'Chrome', '权限: 插件演示被更新成功', '5', '192.168.111.1', '2016-08-22 14:07:38', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dcaf760019', 'Chrome', '权限: 系统监控被更新成功', '5', '192.168.111.1', '2016-08-22 14:07:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dce3ed0021', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:08:01', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0dd05a40022', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:08:10', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0fb9e100023', 'Chrome', '权限: 网站模板被删除成功', '4', '192.168.111.1', '2016-08-22 14:41:35', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0fbe9970024', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 14:41:54', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b0fc108a0025', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 14:42:04', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b1002b2b0027', 'Chrome', '定时任务管理添加成功', '3', '192.168.111.1', '2016-08-22 14:46:33', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b10066af0028', 'Chrome', '定时任务管理更新成功', '5', '192.168.111.1', '2016-08-22 14:46:48', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b100960e0029', 'Chrome', '定时任务管理删除成功', '4', '192.168.111.1', '2016-08-22 14:47:00', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b10119f0002a', 'Chrome', '定时任务管理更新成功', '5', '192.168.111.1', '2016-08-22 14:47:34', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b101fa24002b', 'Chrome', '停止任务autoResetWeixinTokenCronTrigger', '5', '192.168.111.1', '2016-08-22 14:48:32', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b1020d94002c', 'Chrome', '开启任务autoResetWeixinTokenCronTrigger', '5', '192.168.111.1', '2016-08-22 14:48:37', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b0d8e00156b10338d5002d', 'Chrome', '停止任务autoResetWeixinTokenCronTrigger', '5', '192.168.111.1', '2016-08-22 14:49:53', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b12ab90156b12f9cab0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 15:38:22', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b12ab90156b1332a480001', 'Chrome', '用户admin已退出', '2', '192.168.111.1', '2016-08-22 15:42:15', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b12ab90156b13521d90002', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 15:44:24', '1', '4028d881436d514601436d5215ac0043');
INSERT INTO `t_s_log` VALUES ('4028ef8156b143e40156b1446e0c0000', 'Chrome', '用户: admin[信息部]登录成功', '1', '192.168.111.1', '2016-08-22 16:01:07', '1', '4028d881436d514601436d5215ac0043');

-- ----------------------------
-- Table structure for t_s_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_s_operation`;
CREATE TABLE `t_s_operation` (
  `ID` varchar(32) NOT NULL,
  `operationcode` varchar(50) default NULL,
  `operationicon` varchar(100) default NULL,
  `operationname` varchar(50) default NULL,
  `status` smallint(6) default NULL,
  `functionid` varchar(32) default NULL,
  `iconid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_pceuy41wr2fjbcilyc7mk3m1f` (`functionid`),
  KEY `FK_ny5de7922l39ta2pkhyspd5f` (`iconid`),
  CONSTRAINT `FK_ny5de7922l39ta2pkhyspd5f` FOREIGN KEY (`iconid`) REFERENCES `t_s_icon` (`ID`),
  CONSTRAINT `FK_pceuy41wr2fjbcilyc7mk3m1f` FOREIGN KEY (`functionid`) REFERENCES `t_s_function` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_operation
-- ----------------------------
INSERT INTO `t_s_operation` VALUES ('402881e44648134a0146482093940049', 'add', null, '新增', null, '4028d881436d514601436d52154d002d', '4028d881436d514601436d5214650000');
INSERT INTO `t_s_operation` VALUES ('402881e44648134a0146482152c2004b', 'edit', null, '编辑', null, '4028d881436d514601436d52154d002d', '4028d881436d514601436d5214650000');
INSERT INTO `t_s_operation` VALUES ('4028d881436d514601436d5216730069', 'add', null, '录入', null, '4028d881436d514601436d521549002c', '4028d881436d514601436d5214650000');
INSERT INTO `t_s_operation` VALUES ('4028d881436d514601436d521678006a', 'edit', null, '编辑', null, '4028d881436d514601436d521549002c', '4028d881436d514601436d5214650000');
INSERT INTO `t_s_operation` VALUES ('4028d881436d514601436d52167c006b', 'del', null, '删除', null, '4028d881436d514601436d521549002c', '4028d881436d514601436d5214650000');
INSERT INTO `t_s_operation` VALUES ('4028d881436d514601436d52167f006c', 'szqm', null, '审核', null, '4028d881436d514601436d521549002c', '4028d881436d514601436d5214650000');

-- ----------------------------
-- Table structure for t_s_opintemplate
-- ----------------------------
DROP TABLE IF EXISTS `t_s_opintemplate`;
CREATE TABLE `t_s_opintemplate` (
  `ID` varchar(32) NOT NULL,
  `descript` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_opintemplate
-- ----------------------------

-- ----------------------------
-- Table structure for t_s_role
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role`;
CREATE TABLE `t_s_role` (
  `ID` varchar(32) NOT NULL,
  `rolecode` varchar(10) default NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_role
-- ----------------------------
INSERT INTO `t_s_role` VALUES ('402881e44648134a014648163a6d0001', 'ceshi', '测试角色');
INSERT INTO `t_s_role` VALUES ('4028d881436d514601436d52159c0041', 'admin', '管理员');
INSERT INTO `t_s_role` VALUES ('4028d881436d514601436d5215a00042', 'manager', '普通用户');

-- ----------------------------
-- Table structure for t_s_role_function
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_function`;
CREATE TABLE `t_s_role_function` (
  `ID` varchar(32) NOT NULL,
  `operation` varchar(100) default NULL,
  `functionid` varchar(32) default NULL,
  `roleid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_fvsillj2cxyk5thnuu625urab` (`functionid`),
  KEY `FK_9dww3p4w8jwvlrgwhpitsbfif` (`roleid`),
  CONSTRAINT `FK_9dww3p4w8jwvlrgwhpitsbfif` FOREIGN KEY (`roleid`) REFERENCES `t_s_role` (`ID`),
  CONSTRAINT `FK_fvsillj2cxyk5thnuu625urab` FOREIGN KEY (`functionid`) REFERENCES `t_s_function` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_role_function
-- ----------------------------
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a660021', null, '402880f2590623420159062ea59f0018', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690022', null, '402880f25906234201590626c28e0008', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690023', null, '402880f25906234201590626596e0004', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690024', null, '402880f259062342015906312d27001f', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690025', null, '402880f259062342015906288369000f', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690026', null, '402880f2590623420159062f3b4a001a', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690027', null, '402880f2590623420159062581630001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690028', null, '402880f25906234201590630a5cc001c', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a690029', null, '402880f25906234201590627ef06000c', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a69002a', null, '402880f25906234201590628d62c0011', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a6a002b', null, '402880f2590623420159062692fc0006', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a6a002c', null, '402880f2590623420159062783c4000a', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a6a002d', null, '402880f25906117601590614ca94000a', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a6a002e', null, '402880f2590623420159062d921c0013', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402880f259062342015906318a6a002f', null, '402880f2590623420159062df7fe0016', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('40288186590896200159089cf152000a', null, '40288186590896200159089c30620004', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('40288186590896200159089cf155000b', null, '40288186590896200159089c8a360006', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('40288186590896200159089cf155000c', null, '40288186590896200159089bc6510002', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('40288186590896200159089ef47e0012', null, '40288186590896200159089ccf5b0008', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402881e86223f2de016223f410680001', null, '297edb62621d0e3d01621d42e2da0004', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('402889ff4728a293014728b3fab70021', null, '4028d881436d514601436d52151f0020', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d521699006f', null, '4028d881436d514601436d52150a0019', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d52169e0070', null, '4028d881436d514601436d52150a0019', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216bb0077', null, '4028d881436d514601436d521513001d', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216bf0078', null, '4028d881436d514601436d521513001d', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216c30079', null, '4028d881436d514601436d521516001e', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216c7007a', null, '4028d881436d514601436d521516001e', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216ca007b', null, '4028d881436d514601436d52151a001f', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216cc007c', null, '4028d881436d514601436d52151a001f', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216d4007e', null, '4028d881436d514601436d52151f0020', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216d7007f', null, '4028d881436d514601436d5215220021', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216dc0080', null, '4028d881436d514601436d5215220021', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881436d514601436d5216eb0084', null, '4028d881436d514601436d5215260023', '4028d881436d514601436d5215a00042');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904000c', null, '4028d881436d514601436d59984601a5', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904000d', null, '4028d881436d514601436d5884be019d', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904000e', null, '4028d881436d514601436d59d6d901a7', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040011', null, '402881e446677b700146677d7b600001', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040012', null, '297e7eb6469a4a8901469a54eff60006', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040013', null, '4028d881436d514601436d5618400191', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040014', null, '402881e545f5dd1a0145f5f32cf00005', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040015', null, '402881e545f5f4780145f61198810004', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040016', null, '4028d881436d514601436d5449330189', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040017', null, '402881e44695183a0146951ed79e0008', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859040018', null, '402881e8460d7e5301460d81c7a60001', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904001b', null, '4028d881436d514601436d52c2fb0181', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904001c', null, '4028d881436d514601436d5af95501b1', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904001d', null, '402881e545f5dd1a0145f5de9bb60001', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d7014744985904001f', null, '402881e44695183a0146951af2b70004', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028d881474489d70147449859140021', null, '402881e44695183a0146951b66050006', '402881e44648134a014648163a6d0001');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0c9ace8000b', null, '4028ef8156b0c68f0156b0c920440005', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0c9acec000c', null, '4028ef8156b0c68f0156b0c88eb10001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0c9acec000d', null, '4028ef8156b0c68f0156b0c8c0c60003', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0c9acec000e', null, '4028ef8156b0c68f0156b0c956120007', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce03920028', null, '4028ef8156b0c68f0156b0ca47730011', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce03940029', null, '4028ef8156b0c68f0156b0cc5c280021', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002a', null, '4028ef8156b0c68f0156b0cc0dc7001f', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002b', null, '4028ef8156b0c68f0156b0ca69760013', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002c', null, '4028ef8156b0c68f0156b0ccab2e0023', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002d', null, '4028ef8156b0c68f0156b0cbdb2f001d', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002e', null, '4028ef8156b0c68f0156b0ccefde0025', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce0394002f', null, '4028ef8156b0c68f0156b0ca9d800015', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce03950030', null, '4028ef8156b0c68f0156b0cb5675001b', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce03950031', null, '4028ef8156b0c68f0156b0cb1d7c0019', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0ce03950032', null, '4028ef8156b0c68f0156b0cadace0017', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fc90057', null, '4028948151dc6b1f0151dc6fdf05000c', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcb0059', null, '4028d881436d514601436d5618400191', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcb005a', null, '402881e545f5dd1a0145f5f32cf00005', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcb005b', null, '402881e8460d7e5301460d81c7a60001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcb005c', null, '4028d881436d514601436d52c2fb0181', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc005d', null, '4028d881436d514601436d5af95501b1', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc005e', null, '4028ef8156b0c68f0156b0d4cf390045', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc005f', null, '402881e44695183a0146951b66050006', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0060', null, '4028d881436d514601436d59984601a5', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0061', null, '4028948151d756d80151d758c25e0001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0062', null, '4028d881436d514601436d5884be019d', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0063', null, '4028d881436d514601436d59d6d901a7', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0064', null, '402880e6478c6d8201478c71c5460001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0065', null, '402881e446677b700146677d7b600001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0066', null, '297e7eb6469a4a8901469a54eff60006', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcc0067', null, '402889f64747048401474706d2a50001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcd0068', null, '402881e545f5f4780145f61198810004', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcd0069', null, '4028d881436d514601436d5449330189', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcd006a', null, '402881e44695183a0146951ed79e0008', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fcd006b', null, '402881e545f5dd1a0145f5de9bb60001', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0c68f0156b0d75fde006c', null, '402881e44695183a0146951af2b70004', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd6e001a', null, '4028d881436d514601436d52153b0029', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd71001b', null, '4028d881436d514601436d52150f001c', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd71001c', null, '4028d881436d514601436d5215380028', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd71001d', null, '4028d881436d514601436d521540002a', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd71001e', null, '4028d881436d514601436d521593003f', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd72001f', null, '4028d881436d514601436d52152c0025', '4028d881436d514601436d52159c0041');
INSERT INTO `t_s_role_function` VALUES ('4028ef8156b0d8e00156b0dcdd720020', null, '4028d881436d514601436d52150b001a', '4028d881436d514601436d52159c0041');

-- ----------------------------
-- Table structure for t_s_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_user`;
CREATE TABLE `t_s_role_user` (
  `ID` varchar(32) NOT NULL,
  `roleid` varchar(32) default NULL,
  `userid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_n2ucxeorvpjy7qhnmuem01kbx` (`roleid`),
  KEY `FK_d4qb5xld2pfb0bkjx9iwtolda` (`userid`),
  CONSTRAINT `FK_d4qb5xld2pfb0bkjx9iwtolda` FOREIGN KEY (`userid`) REFERENCES `t_s_user` (`id`),
  CONSTRAINT `FK_n2ucxeorvpjy7qhnmuem01kbx` FOREIGN KEY (`roleid`) REFERENCES `t_s_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_role_user
-- ----------------------------
INSERT INTO `t_s_role_user` VALUES ('40288186591caa1001591cc7c4920007', '4028d881436d514601436d5215a00042', '4028d881436d514601436d5215b20044');
INSERT INTO `t_s_role_user` VALUES ('402881e4461f9c6401461fa3956d0009', '4028d881436d514601436d52159c0041', '402881e4461f9c6401461fa2e6f50002');
INSERT INTO `t_s_role_user` VALUES ('402881e44648134a014648174ab3000d', '402881e44648134a014648163a6d0001', '402881e44648134a014648174a45000c');
INSERT INTO `t_s_role_user` VALUES ('4028d881436d514601436d5217aa00c2', '4028d881436d514601436d52159c0041', '4028d881436d514601436d5215b80045');
INSERT INTO `t_s_role_user` VALUES ('4028d881436d514601436d5217ad00c3', '4028d881436d514601436d52159c0041', '4028d881436d514601436d5215bc0046');
INSERT INTO `t_s_role_user` VALUES ('4028ef8153f4151f0153f423b228000b', '4028d881436d514601436d52159c0041', '4028d881436d514601436d5215ac0043');

-- ----------------------------
-- Table structure for t_s_student
-- ----------------------------
DROP TABLE IF EXISTS `t_s_student`;
CREATE TABLE `t_s_student` (
  `ID` varchar(32) NOT NULL,
  `classname` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_student
-- ----------------------------
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521ae80165', '1班', '张三', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aea0166', '1班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aeb0167', '1班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aec0168', '1班', '赵六', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aec0169', '2班', '张三', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aee016a', '2班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521af0016b', '2班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521af1016c', '2班', '赵六', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521af3016d', '3班', '张三', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521af4016e', '3班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521af7016f', '3班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521afa0170', '3班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521afe0171', '3班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521aff0172', '3班', '赵六', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b000173', '4班', '张三', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b010174', '4班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b030175', '4班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b040176', '4班', '赵六', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b040177', '5班', '张三', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b050178', '5班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b060179', '5班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b07017a', '5班', '赵六', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b08017b', '5班', '赵六', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b09017c', '5班', '李四', 'f');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b0a017d', '5班', '王五', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b0b017e', '5班', '赵六', 'm');
INSERT INTO `t_s_student` VALUES ('4028d881436d514601436d521b0c017f', '5班', '赵六', 'm');

-- ----------------------------
-- Table structure for t_s_territory
-- ----------------------------
DROP TABLE IF EXISTS `t_s_territory`;
CREATE TABLE `t_s_territory` (
  `ID` varchar(32) NOT NULL,
  `territorycode` varchar(10) NOT NULL,
  `territorylevel` smallint(6) NOT NULL,
  `territoryname` varchar(50) NOT NULL,
  `territory_pinyin` varchar(40) default NULL,
  `territorysort` varchar(3) NOT NULL,
  `x_wgs84` double NOT NULL,
  `y_wgs84` double NOT NULL,
  `territoryparentid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_territory
-- ----------------------------
INSERT INTO `t_s_territory` VALUES ('1', '100', '1', '全国', 'qg', '0', '106.258754', '38.471318', '0');
INSERT INTO `t_s_territory` VALUES ('10', '00000016', '2', '河南省', 'HNS', '0', '113.687228', '34.76819', '1');
INSERT INTO `t_s_territory` VALUES ('100', '371300', '3', '临沂市', 'LYS', '0', '118.356448', '35.104672', '18');
INSERT INTO `t_s_territory` VALUES ('1000', '370903', '4', '岱岳区', 'DYQ', '0', '117.041582', '36.18799', '96');
INSERT INTO `t_s_territory` VALUES ('1001', '370921', '4', '宁阳县', 'NYX', '0', '116.805797', '35.758787', '96');
INSERT INTO `t_s_territory` VALUES ('1002', '370923', '4', '东平县', 'DPX', '0', '116.470304', '35.937102', '96');
INSERT INTO `t_s_territory` VALUES ('1003', '370982', '4', '新泰市', 'XTS', '0', '117.767953', '35.909032', '96');
INSERT INTO `t_s_territory` VALUES ('1004', '370983', '4', '肥城市', 'FCS', '0', '116.768358', '36.182571', '96');
INSERT INTO `t_s_territory` VALUES ('1005', '371002', '4', '环翠区', 'HCQ', '0', '122.123444', '37.501991', '97');
INSERT INTO `t_s_territory` VALUES ('1006', '371081', '4', '文登市', 'WDS', '0', '122.058128', '37.1939129', '97');
INSERT INTO `t_s_territory` VALUES ('1007', '371082', '4', '荣成市', 'RCS', '0', '122.486676', '37.165249', '97');
INSERT INTO `t_s_territory` VALUES ('1008', '371083', '4', '乳山市', 'RSS', '0', '121.539764', '36.919816', '97');
INSERT INTO `t_s_territory` VALUES ('1009', '371102', '4', '东港区', 'DGQ', '0', '119.462228', '35.425475', '98');
INSERT INTO `t_s_territory` VALUES ('101', '371400', '3', '德州市', 'DZS', '0', '116.357465', '37.434093', '18');
INSERT INTO `t_s_territory` VALUES ('1010', '371103', '4', '岚山区', 'LSQ', '0', '119.318813', '35.121816', '98');
INSERT INTO `t_s_territory` VALUES ('1011', '371121', '4', '五莲县', 'WLX', '0', '119.208744', '35.750095', '98');
INSERT INTO `t_s_territory` VALUES ('1012', '371122', '4', '莒县', 'JX', '0', '118.837131', '35.579868', '98');
INSERT INTO `t_s_territory` VALUES ('1013', '371202', '4', '莱城区', 'LCQ', '0', '117.659864', '36.203208', '99');
INSERT INTO `t_s_territory` VALUES ('1014', '371203', '4', '钢城区', 'GCQ', '0', '117.811355', '36.058572', '99');
INSERT INTO `t_s_territory` VALUES ('1015', '371302', '4', '兰山区', 'LSQ', '0', '118.347707', '35.051729', '100');
INSERT INTO `t_s_territory` VALUES ('1016', '371311', '4', '罗庄区', 'LZQ', '0', '118.284786', '34.996741', '100');
INSERT INTO `t_s_territory` VALUES ('1017', '371312', '4', '河东区', 'HDQ', '0', '118.402893', '35.089917', '100');
INSERT INTO `t_s_territory` VALUES ('1018', '371321', '4', '沂南县', 'YNX', '0', '118.465213', '35.549976', '100');
INSERT INTO `t_s_territory` VALUES ('1019', '371322', '4', '郯城县', 'TCX', '0', '118.367263', '34.613557', '100');
INSERT INTO `t_s_territory` VALUES ('102', '371500', '3', '聊城市', 'LCS', '0', '115.985371', '36.4567039', '18');
INSERT INTO `t_s_territory` VALUES ('1020', '371323', '4', '沂水县', 'YSX', '0', '118.627918', '35.79045', '100');
INSERT INTO `t_s_territory` VALUES ('1021', '371324', '4', '苍山县', 'CSX', '0', '118.07065', '34.857149', '100');
INSERT INTO `t_s_territory` VALUES ('1022', '371325', '4', '费县', 'FX', '0', '117.977868', '35.26634', '100');
INSERT INTO `t_s_territory` VALUES ('1023', '371326', '4', '平邑县', 'PYX', '0', '117.640352', '35.5059429', '100');
INSERT INTO `t_s_territory` VALUES ('1024', '371327', '4', '莒南县', 'JNX', '0', '118.835163', '35.174846', '100');
INSERT INTO `t_s_territory` VALUES ('1025', '371328', '4', '蒙阴县', 'MYX', '0', '117.945085', '35.710032', '100');
INSERT INTO `t_s_territory` VALUES ('1026', '371329', '4', '临沭县', 'LSX', '0', '118.650782', '34.919852', '100');
INSERT INTO `t_s_territory` VALUES ('1027', '371402', '4', '德城区', 'DCQ', '0', '116.299434', '37.451272', '101');
INSERT INTO `t_s_territory` VALUES ('1028', '371421', '4', '陵县', 'LX', '0', '116.576176', '37.33579', '101');
INSERT INTO `t_s_territory` VALUES ('1029', '371422', '4', '宁津县', 'NJX', '0', '116.800324', '37.652329', '101');
INSERT INTO `t_s_territory` VALUES ('103', '371600', '3', '滨州市', 'BZS', '0', '117.970703', '37.38199', '18');
INSERT INTO `t_s_territory` VALUES ('1030', '371423', '4', '庆云县', 'QYX', '0', '117.385123', '37.77539', '101');
INSERT INTO `t_s_territory` VALUES ('1031', '371424', '4', '临邑县', 'LYX', '0', '116.86665', '37.189864', '101');
INSERT INTO `t_s_territory` VALUES ('1032', '371425', '4', '齐河县', 'QHX', '0', '116.758917', '36.795011', '101');
INSERT INTO `t_s_territory` VALUES ('1033', '371426', '4', '平原县', 'PYX', '0', '116.434187', '37.165419', '101');
INSERT INTO `t_s_territory` VALUES ('1034', '371427', '4', '夏津县', 'XJX', '0', '116.001726', '36.948371', '101');
INSERT INTO `t_s_territory` VALUES ('1035', '371428', '4', '武城县', 'WCX', '0', '116.069302', '37.213311', '101');
INSERT INTO `t_s_territory` VALUES ('1036', '371481', '4', '乐陵市', 'LLS', '0', '117.231935', '37.729907', '101');
INSERT INTO `t_s_territory` VALUES ('1037', '371482', '4', '禹城市', 'YCS', '0', '116.638387', '36.934259', '101');
INSERT INTO `t_s_territory` VALUES ('1038', '371502', '4', '东昌府区', 'DCFQ', '0', '115.988484', '36.434697', '102');
INSERT INTO `t_s_territory` VALUES ('1039', '371521', '4', '阳谷县', 'YGX', '0', '115.79182', '36.114392', '102');
INSERT INTO `t_s_territory` VALUES ('104', '371700', '3', '菏泽市', 'HZS', '0', '115.480656', '35.23375', '18');
INSERT INTO `t_s_territory` VALUES ('1040', '371522', '4', '莘县', 'SX', '0', '115.671191', '36.233599', '102');
INSERT INTO `t_s_territory` VALUES ('1041', '371523', '4', '茌平县', 'CPX', '0', '116.255282', '36.5807639', '102');
INSERT INTO `t_s_territory` VALUES ('1042', '371524', '4', '东阿县', 'DAX', '0', '116.24758', '36.334917', '102');
INSERT INTO `t_s_territory` VALUES ('1043', '371525', '4', '冠县', 'GX', '0', '115.44274', '36.484009', '102');
INSERT INTO `t_s_territory` VALUES ('1044', '371526', '4', '高唐县', 'GTX', '0', '116.231478', '36.866062', '102');
INSERT INTO `t_s_territory` VALUES ('1045', '371581', '4', '临清市', 'LQS', '0', '115.704881', '36.838277', '102');
INSERT INTO `t_s_territory` VALUES ('1046', '371602', '4', '滨城区', 'BCQ', '0', '118.019146', '37.43206', '103');
INSERT INTO `t_s_territory` VALUES ('1047', '371621', '4', '惠民县', 'HMX', '0', '117.510451', '37.489769', '103');
INSERT INTO `t_s_territory` VALUES ('1048', '371622', '4', '阳信县', 'YXX', '0', '117.578262', '37.641106', '103');
INSERT INTO `t_s_territory` VALUES ('1049', '371623', '4', '无棣县', 'WDX', '0', '117.625696', '37.770261', '103');
INSERT INTO `t_s_territory` VALUES ('1050', '371624', '4', '沾化县', 'ZHX', '0', '118.132199', '37.698281', '103');
INSERT INTO `t_s_territory` VALUES ('1051', '371625', '4', '博兴县', 'BXX', '0', '118.131815', '37.150226', '103');
INSERT INTO `t_s_territory` VALUES ('1052', '371626', '4', '邹平县', 'ZPX', '0', '117.743109', '36.862989', '103');
INSERT INTO `t_s_territory` VALUES ('1053', '371702', '4', '牡丹区', 'MDQ', '0', '115.417827', '35.252512', '104');
INSERT INTO `t_s_territory` VALUES ('1054', '371721', '4', '曹县', 'CX', '0', '115.542328', '34.825508', '104');
INSERT INTO `t_s_territory` VALUES ('1055', '371722', '4', '单县', 'DX', '0', '116.107428', '34.778808', '104');
INSERT INTO `t_s_territory` VALUES ('1056', '371723', '4', '成武县', 'CWX', '0', '115.889765', '34.952459', '104');
INSERT INTO `t_s_territory` VALUES ('1057', '371724', '4', '巨野县', 'JYX', '0', '116.094674', '35.396261', '104');
INSERT INTO `t_s_territory` VALUES ('1058', '371725', '4', '郓城县', 'YCX', '0', '115.943613', '35.599758', '104');
INSERT INTO `t_s_territory` VALUES ('1059', '371726', '4', '鄄城县', 'JCX', '0', '115.510193', '35.563408', '104');
INSERT INTO `t_s_territory` VALUES ('1060', '371727', '4', '定陶县', 'DTX', '0', '115.573094', '35.071', '104');
INSERT INTO `t_s_territory` VALUES ('1061', '371728', '4', '东明县', 'DMX', '0', '115.089905', '35.289368', '104');
INSERT INTO `t_s_territory` VALUES ('11', '00000008', '2', '黑龙江省', 'HLJS', '0', '126.662507', '45.7421699', '1');
INSERT INTO `t_s_territory` VALUES ('12', '20000000', '2', '上海市', 'SHS', '0', '121.473704', '31.230393', '1');
INSERT INTO `t_s_territory` VALUES ('13', '00000010', '2', '江苏省', 'JSS', '0', '118.763232', '32.061707', '1');
INSERT INTO `t_s_territory` VALUES ('14', '00000011', '2', '浙江省', 'ZJS', '0', '120.153676', '30.26586', '1');
INSERT INTO `t_s_territory` VALUES ('15', '00000012', '2', '安徽省', 'AHS', '0', '117.284923', '31.861184', '1');
INSERT INTO `t_s_territory` VALUES ('16', '00000013', '2', '福建省', 'FJS', '0', '119.296506', '26.099933', '1');
INSERT INTO `t_s_territory` VALUES ('17', '00000014', '2', '江西省', 'JXS', '0', '115.909175', '28.674424', '1');
INSERT INTO `t_s_territory` VALUES ('1739', '360102', '4', '东湖区', 'DHQ', '0', '115.89901', '28.6849159', '184');
INSERT INTO `t_s_territory` VALUES ('1740', '360103', '4', '西湖区', 'XHQ', '0', '115.877287', '28.656887', '184');
INSERT INTO `t_s_territory` VALUES ('1741', '360104', '4', '青云谱区', 'QYPQ', '0', '115.925749', '28.621169', '184');
INSERT INTO `t_s_territory` VALUES ('1742', '360105', '4', '湾里区', 'WLQ', '0', '115.730994', '28.714869', '184');
INSERT INTO `t_s_territory` VALUES ('1743', '360111', '4', '青山湖区', 'QSHQ', '0', '115.962144', '28.682985', '184');
INSERT INTO `t_s_territory` VALUES ('1744', '360121', '4', '南昌县', 'NCX', '0', '115.944162', '28.545459', '184');
INSERT INTO `t_s_territory` VALUES ('1745', '360122', '4', '新建县', 'XJX', '0', '115.815233', '28.692437', '184');
INSERT INTO `t_s_territory` VALUES ('1746', '360123', '4', '安义县', 'AYX', '0', '115.549199', '28.844429', '184');
INSERT INTO `t_s_territory` VALUES ('1747', '360124', '4', '进贤县', 'JXX', '0', '116.240924', '28.376918', '184');
INSERT INTO `t_s_territory` VALUES ('1748', '360202', '4', '昌江区', 'CJQ', '0', '117.183688', '29.27342', '185');
INSERT INTO `t_s_territory` VALUES ('1749', '360203', '4', '珠山区', 'ZSQ', '0', '117.202336', '29.301272', '185');
INSERT INTO `t_s_territory` VALUES ('1750', '360222', '4', '浮梁县', 'FLX', '0', '117.215061', '29.351739', '185');
INSERT INTO `t_s_territory` VALUES ('1751', '360281', '4', '乐平市', 'LPS', '0', '117.129169', '28.961902', '185');
INSERT INTO `t_s_territory` VALUES ('1752', '360302', '4', '安源区', 'AYQ', '0', '113.87073', '27.615202', '186');
INSERT INTO `t_s_territory` VALUES ('1753', '360313', '4', '湘东区', 'XDQ', '0', '113.733059', '27.640075', '186');
INSERT INTO `t_s_territory` VALUES ('1754', '360321', '4', '莲花县', 'LHX', '0', '113.961465', '27.127669', '186');
INSERT INTO `t_s_territory` VALUES ('1755', '360322', '4', '上栗县', 'SLX', '0', '113.795219', '27.880567', '186');
INSERT INTO `t_s_territory` VALUES ('1756', '360323', '4', '芦溪县', 'LXX', '0', '114.029595', '27.631145', '186');
INSERT INTO `t_s_territory` VALUES ('1757', '360402', '4', '庐山区', 'LSQ', '0', '115.989212', '29.671775', '187');
INSERT INTO `t_s_territory` VALUES ('1758', '360403', '4', '浔阳区', 'XYQ', '0', '115.990399', '29.72746', '187');
INSERT INTO `t_s_territory` VALUES ('1759', '360421', '4', '九江县', 'JJX', '0', '115.911314', '29.608456', '187');
INSERT INTO `t_s_territory` VALUES ('1760', '360423', '4', '武宁县', 'WNX', '0', '115.100579', '29.256323', '187');
INSERT INTO `t_s_territory` VALUES ('1761', '360424', '4', '修水县', 'XSX', '0', '114.547356', '29.025707', '187');
INSERT INTO `t_s_territory` VALUES ('1762', '360425', '4', '永修县', 'YXX', '0', '115.809111', '29.021824', '187');
INSERT INTO `t_s_territory` VALUES ('1763', '360426', '4', '德安县', 'DAX', '0', '115.756883', '29.314348', '187');
INSERT INTO `t_s_territory` VALUES ('1764', '360427', '4', '星子县', 'XZX', '0', '116.044893', '29.448037', '187');
INSERT INTO `t_s_territory` VALUES ('1765', '360428', '4', '都昌县', 'DCX', '0', '116.204099', '29.273194', '187');
INSERT INTO `t_s_territory` VALUES ('1766', '360429', '4', '湖口县', 'HKX', '0', '116.220266', '29.73944', '187');
INSERT INTO `t_s_territory` VALUES ('1767', '360430', '4', '彭泽县', 'PZX', '0', '116.549359', '29.896061', '187');
INSERT INTO `t_s_territory` VALUES ('1768', '360481', '4', '瑞昌市', 'RCS', '0', '115.681504', '29.673795', '187');
INSERT INTO `t_s_territory` VALUES ('1769', '360499', '4', '共青城', 'GQC', '0', '115.774', '29.2417', '187');
INSERT INTO `t_s_territory` VALUES ('1770', '360502', '4', '渝水区', 'YSQ', '0', '114.944574', '27.80038', '188');
INSERT INTO `t_s_territory` VALUES ('1771', '360521', '4', '分宜县', 'FYX', '0', '114.692039', '27.814628', '188');
INSERT INTO `t_s_territory` VALUES ('1772', '360602', '4', '月湖区', 'YHQ', '0', '117.036676', '28.238797', '189');
INSERT INTO `t_s_territory` VALUES ('1773', '360622', '4', '余江县', 'YJX', '0', '116.818871', '28.204174', '189');
INSERT INTO `t_s_territory` VALUES ('1774', '360681', '4', '贵溪市', 'GXS', '0', '117.245497', '28.292519', '189');
INSERT INTO `t_s_territory` VALUES ('1775', '360702', '4', '章贡区', 'ZGQ', '0', '114.941826', '25.862827', '190');
INSERT INTO `t_s_territory` VALUES ('1776', '360721', '4', '赣县', 'GX', '0', '115.011561', '25.860691', '190');
INSERT INTO `t_s_territory` VALUES ('1777', '360722', '4', '信丰县', 'XFX', '0', '114.922963', '25.386278', '190');
INSERT INTO `t_s_territory` VALUES ('1778', '360723', '4', '大余县', 'DYX', '0', '114.362094', '25.401283', '190');
INSERT INTO `t_s_territory` VALUES ('1779', '360724', '4', '上犹县', 'SYX', '0', '114.551371', '25.784978', '190');
INSERT INTO `t_s_territory` VALUES ('1780', '360725', '4', '崇义县', 'CYX', '0', '114.308273', '25.681879', '190');
INSERT INTO `t_s_territory` VALUES ('1781', '360726', '4', '安远县', 'AYX', '0', '115.393922', '25.136925', '190');
INSERT INTO `t_s_territory` VALUES ('1782', '360727', '4', '龙南县', 'LNX', '0', '114.789811', '24.911107', '190');
INSERT INTO `t_s_territory` VALUES ('1783', '360728', '4', '定南县', 'DNX', '0', '115.027845', '24.78441', '190');
INSERT INTO `t_s_territory` VALUES ('1784', '360729', '4', '全南县', 'QNX', '0', '114.530125', '24.742401', '190');
INSERT INTO `t_s_territory` VALUES ('1785', '360730', '4', '宁都县', 'NDX', '0', '116.009472', '26.470116', '190');
INSERT INTO `t_s_territory` VALUES ('1786', '360731', '4', '于都县', 'YDX', '0', '115.41551', '25.952066', '190');
INSERT INTO `t_s_territory` VALUES ('1787', '360732', '4', '兴国县', 'XGX', '0', '115.36319', '26.337937', '190');
INSERT INTO `t_s_territory` VALUES ('1788', '360733', '4', '会昌县', 'HCX', '0', '115.786057', '25.600272', '190');
INSERT INTO `t_s_territory` VALUES ('1789', '360734', '4', '寻乌县', 'YWX', '0', '115.646525', '24.963322', '190');
INSERT INTO `t_s_territory` VALUES ('1790', '360735', '4', '石城县', 'SCX', '0', '116.354201', '26.32686', '190');
INSERT INTO `t_s_territory` VALUES ('1791', '360781', '4', '瑞金市', 'RJS', '0', '116.02713', '25.885561', '190');
INSERT INTO `t_s_territory` VALUES ('1792', '360782', '4', '南康市', 'NKS', '0', '114.765238', '25.661356', '190');
INSERT INTO `t_s_territory` VALUES ('1793', '360802', '4', '吉州区', 'JZQ', '0', '114.994307', '27.129975', '191');
INSERT INTO `t_s_territory` VALUES ('1794', '360803', '4', '青原区', 'QYQ', '0', '115.01424', '27.081719', '191');
INSERT INTO `t_s_territory` VALUES ('1795', '360821', '4', '吉安县', 'JAX', '0', '114.907659', '27.040142', '191');
INSERT INTO `t_s_territory` VALUES ('1796', '360822', '4', '吉水县', 'JSX', '0', '115.135507', '27.229632', '191');
INSERT INTO `t_s_territory` VALUES ('1797', '360823', '4', '峡江县', 'XJX', '0', '115.316566', '27.582901', '191');
INSERT INTO `t_s_territory` VALUES ('1798', '360824', '4', '新干县', 'XGX', '0', '115.393043', '27.740809', '191');
INSERT INTO `t_s_territory` VALUES ('1799', '360825', '4', '永丰县', 'YFX', '0', '115.441477', '27.317869', '191');
INSERT INTO `t_s_territory` VALUES ('18', '00000015', '2', '山东省', 'SDS', '0', '117.020411', '36.668627', '1');
INSERT INTO `t_s_territory` VALUES ('1800', '360826', '4', '泰和县', 'THX', '0', '114.908861', '26.790231', '191');
INSERT INTO `t_s_territory` VALUES ('1801', '360827', '4', '遂川县', 'SCX', '0', '114.52098', '26.311894', '191');
INSERT INTO `t_s_territory` VALUES ('1802', '360828', '4', '万安县', 'WAX', '0', '114.786256', '26.458257', '191');
INSERT INTO `t_s_territory` VALUES ('1803', '360829', '4', '安福县', 'AFX', '0', '114.619893', '27.392874', '191');
INSERT INTO `t_s_territory` VALUES ('1804', '360830', '4', '永新县', 'YXX', '0', '114.242675', '26.945233', '191');
INSERT INTO `t_s_territory` VALUES ('1805', '360881', '4', '井冈山市', 'JGSS', '0', '114.289182', '26.748186', '191');
INSERT INTO `t_s_territory` VALUES ('1806', '360902', '4', '袁州区', 'YZQ', '0', '114.424657', '27.798846', '192');
INSERT INTO `t_s_territory` VALUES ('1807', '360921', '4', '奉新县', 'FXX', '0', '115.384904', '28.700806', '192');
INSERT INTO `t_s_territory` VALUES ('1808', '360922', '4', '万载县', 'WZX', '0', '114.447551', '28.10455', '192');
INSERT INTO `t_s_territory` VALUES ('1809', '360923', '4', '上高县', 'SGX', '0', '114.924494', '28.232827', '192');
INSERT INTO `t_s_territory` VALUES ('1810', '360924', '4', '宜丰县', 'YFX', '0', '114.803542', '28.393613', '192');
INSERT INTO `t_s_territory` VALUES ('1811', '360925', '4', '靖安县', 'JAX', '0', '115.362629', '28.861475', '192');
INSERT INTO `t_s_territory` VALUES ('1812', '360926', '4', '铜鼓县', 'TGX', '0', '114.37098', '28.520747', '192');
INSERT INTO `t_s_territory` VALUES ('1813', '360981', '4', '丰城市', 'FCS', '0', '115.771195', '28.159325', '192');
INSERT INTO `t_s_territory` VALUES ('1814', '360982', '4', '樟树市', 'ZSS', '0', '115.546063', '28.055796', '192');
INSERT INTO `t_s_territory` VALUES ('1815', '360983', '4', '高安市', 'GAS', '0', '115.375618', '28.417261', '192');
INSERT INTO `t_s_territory` VALUES ('1816', '361002', '4', '临川区', 'LCQ', '0', '116.31136', '27.934529', '193');
INSERT INTO `t_s_territory` VALUES ('1817', '361021', '4', '南城县', 'NCX', '0', '116.644658', '27.552748', '193');
INSERT INTO `t_s_territory` VALUES ('1818', '361022', '4', '黎川县', 'LCX', '0', '116.907508', '27.282382', '193');
INSERT INTO `t_s_territory` VALUES ('1819', '361023', '4', '南丰县', 'NFX', '0', '116.525725', '27.218445', '193');
INSERT INTO `t_s_territory` VALUES ('1820', '361024', '4', '崇仁县', 'CRX', '0', '116.061164', '27.764681', '193');
INSERT INTO `t_s_territory` VALUES ('1821', '361025', '4', '乐安县', 'LAX', '0', '115.837895', '27.420441', '193');
INSERT INTO `t_s_territory` VALUES ('1822', '361026', '4', '宜黄县', 'YHX', '0', '116.222128', '27.546146', '193');
INSERT INTO `t_s_territory` VALUES ('1823', '361027', '4', '金溪县', 'JXX', '0', '116.775435', '27.908337', '193');
INSERT INTO `t_s_territory` VALUES ('1824', '361028', '4', '资溪县', 'ZXX', '0', '117.060264', '27.706102', '193');
INSERT INTO `t_s_territory` VALUES ('1825', '361029', '4', '东乡县', 'DXX', '0', '116.590465', '28.236118', '193');
INSERT INTO `t_s_territory` VALUES ('1826', '361030', '4', '广昌县', 'GCX', '0', '116.325757', '26.837267', '193');
INSERT INTO `t_s_territory` VALUES ('1827', '361102', '4', '信州区', 'XZQ', '0', '117.966823', '28.43121', '194');
INSERT INTO `t_s_territory` VALUES ('1828', '361121', '4', '上饶县', 'SRX', '0', '117.90785', '28.448983', '194');
INSERT INTO `t_s_territory` VALUES ('1829', '361122', '4', '广丰县', 'GFX', '0', '118.19124', '28.436286', '194');
INSERT INTO `t_s_territory` VALUES ('1830', '361123', '4', '玉山县', 'YSX', '0', '118.245124', '28.682055', '194');
INSERT INTO `t_s_territory` VALUES ('1831', '361124', '4', '铅山县', 'QSX', '0', '117.709451', '28.315217', '194');
INSERT INTO `t_s_territory` VALUES ('1832', '361125', '4', '横峰县', 'HFX', '0', '117.596452', '28.407118', '194');
INSERT INTO `t_s_territory` VALUES ('1833', '361126', '4', '弋阳县', 'YYX', '0', '117.449588', '28.378044', '194');
INSERT INTO `t_s_territory` VALUES ('1834', '361127', '4', '余干县', 'YGX', '0', '116.695647', '28.702302', '194');
INSERT INTO `t_s_territory` VALUES ('1835', '361128', '4', '鄱阳县', 'PYX', '0', '116.699746', '29.011699', '194');
INSERT INTO `t_s_territory` VALUES ('1836', '361129', '4', '万年县', 'WNX', '0', '117.058445', '28.694582', '194');
INSERT INTO `t_s_territory` VALUES ('1837', '361130', '4', '婺源县', 'WYX', '0', '117.861911', '29.2480249', '194');
INSERT INTO `t_s_territory` VALUES ('1838', '361181', '4', '德兴市', 'DXS', '0', '117.578713', '28.946464', '194');
INSERT INTO `t_s_territory` VALUES ('1839', '370102', '4', '历下区', 'LXQ', '0', '117.07653', '36.666344', '195');
INSERT INTO `t_s_territory` VALUES ('184', '360100', '3', '南昌市', 'NCS', '0', '115.858089', '28.68316', '17');
INSERT INTO `t_s_territory` VALUES ('1840', '370103', '4', '市中区', 'SZQ', '0', '116.997475', '36.6511749', '195');
INSERT INTO `t_s_territory` VALUES ('1841', '370104', '4', '槐荫区', 'HYQ', '0', '116.90113', '36.651301', '195');
INSERT INTO `t_s_territory` VALUES ('1842', '370105', '4', '天桥区', 'TQQ', '0', '116.987492', '36.678016', '195');
INSERT INTO `t_s_territory` VALUES ('1843', '370112', '4', '历城区', 'LCQ', '0', '117.065222', '36.680171', '195');
INSERT INTO `t_s_territory` VALUES ('185', '360200', '3', '景德镇市', 'JDZS', '0', '117.17842', '29.268836', '17');
INSERT INTO `t_s_territory` VALUES ('186', '360300', '3', '萍乡市', 'PXS', '0', '113.854676', '27.622865', '17');
INSERT INTO `t_s_territory` VALUES ('187', '360400', '3', '九江市', 'JJS', '0', '116.001951', '29.705103', '17');
INSERT INTO `t_s_territory` VALUES ('188', '360500', '3', '新余市', 'XYS', '0', '114.91741', '27.817819', '17');
INSERT INTO `t_s_territory` VALUES ('189', '360600', '3', '鹰潭市', 'YTS', '0', '117.069202', '28.260189', '17');
INSERT INTO `t_s_territory` VALUES ('19', '00000017', '2', '湖北省', 'HBS', '0', '114.341921', '30.545861', '1');
INSERT INTO `t_s_territory` VALUES ('190', '360700', '3', '赣州市', 'GZS', '0', '114.935025', '25.831925', '17');
INSERT INTO `t_s_territory` VALUES ('191', '360800', '3', '吉安市', 'JAS', '0', '114.992912', '27.113039', '17');
INSERT INTO `t_s_territory` VALUES ('192', '360900', '3', '宜春市', 'YCS', '0', '114.416778', '27.815619', '17');
INSERT INTO `t_s_territory` VALUES ('193', '361000', '3', '抚州市', 'FZS', '0', '116.358176', '27.9492', '17');
INSERT INTO `t_s_territory` VALUES ('194', '361100', '3', '上饶市', 'SRS', '0', '117.943433', '28.454863', '17');
INSERT INTO `t_s_territory` VALUES ('195', '370100', '3', '济南市', 'JNS', '0', '116.994917', '36.665282', '18');
INSERT INTO `t_s_territory` VALUES ('20', '00000018', '2', '湖南省', 'HNS', '0', '112.98381', '28.112444', '1');
INSERT INTO `t_s_territory` VALUES ('21', '40000000', '2', '重庆市', 'ZQS', '0', '106.551557', '29.56301', '1');
INSERT INTO `t_s_territory` VALUES ('22', '00000022', '2', '四川省', 'SCS', '0', '104.075931', '30.651652', '1');
INSERT INTO `t_s_territory` VALUES ('23', '00000019', '2', '广东省', 'GDS', '0', '113.266531', '23.132191', '1');
INSERT INTO `t_s_territory` VALUES ('24', '00000020', '2', '广西壮族自治区', 'GXZZZZQ', '0', '108.327546', '22.815478', '1');
INSERT INTO `t_s_territory` VALUES ('25', '00000021', '2', '海南省', 'HNS', '0', '110.349229', '20.017378', '1');
INSERT INTO `t_s_territory` VALUES ('26', '810000', '2', '香港特别行政区', 'XGTBXZQ', '0', '114.109497', '22.396428', '1');
INSERT INTO `t_s_territory` VALUES ('27', '820000', '2', '澳门特别行政区', 'AMTBXZQ', '0', '113.543873', '22.198745', '1');
INSERT INTO `t_s_territory` VALUES ('28', '00000023', '2', '贵州省', 'GZS', '0', '106.707116', '26.598026', '1');
INSERT INTO `t_s_territory` VALUES ('29', '00000024', '2', '云南省', 'YNS', '0', '102.709812', '25.045359', '1');
INSERT INTO `t_s_territory` VALUES ('3', '00000006', '2', '辽宁省', 'LNS', '0', '123.42944', '41.835441', '1');
INSERT INTO `t_s_territory` VALUES ('30', '00000025', '2', '西藏自治区', 'XCZZQ', '0', '91.1170059', '29.647951', '1');
INSERT INTO `t_s_territory` VALUES ('31', '00000026', '2', '陕西省', 'SXS', '0', '108.954239', '34.265472', '1');
INSERT INTO `t_s_territory` VALUES ('32', '00000027', '2', '甘肃省', 'GSS', '0', '103.826308', '36.059421', '1');
INSERT INTO `t_s_territory` VALUES ('33', '00000028', '2', '青海省', 'QHS', '0', '101.780199', '36.620901', '1');
INSERT INTO `t_s_territory` VALUES ('34', '00000029', '2', '宁夏回族自治区', 'NXHZZZQ', '0', '106.258754', '38.471318', '1');
INSERT INTO `t_s_territory` VALUES ('35', '00000030', '2', '新疆维吾尔自治区', 'XJWWEZZQ', '0', '87.6278119', '43.793028', '1');
INSERT INTO `t_s_territory` VALUES ('4', '00000007', '2', '吉林省', 'JLS', '0', '125.326065', '43.896082', '1');
INSERT INTO `t_s_territory` VALUES ('5', '10000000', '2', '北京市', 'BJS', '0', '116.407413', '39.904214', '1');
INSERT INTO `t_s_territory` VALUES ('6', '30000000', '2', '天津市', 'TJS', '0', '117.200983', '39.084158', '1');
INSERT INTO `t_s_territory` VALUES ('7', '00000003', '2', '河北省', 'HBS', '0', '114.468665', '38.037057', '1');
INSERT INTO `t_s_territory` VALUES ('8', '00000004', '2', '山西省', 'SXS', '0', '112.562569', '37.873376', '1');
INSERT INTO `t_s_territory` VALUES ('89', '370200', '3', '青岛市', 'QDS', '0', '120.382504', '36.06722', '18');
INSERT INTO `t_s_territory` VALUES ('9', '00000005', '2', '内蒙古自治区', 'NMGZZQ', '0', '111.765618', '40.817498', '1');
INSERT INTO `t_s_territory` VALUES ('90', '370300', '3', '淄博市', 'ZBS', '0', '118.055007', '36.813497', '18');
INSERT INTO `t_s_territory` VALUES ('91', '370400', '3', '枣庄市', 'ZZS', '0', '117.323725', '34.810488', '18');
INSERT INTO `t_s_territory` VALUES ('92', '370500', '3', '东营市', 'DYS', '0', '118.674767', '37.434751', '18');
INSERT INTO `t_s_territory` VALUES ('926', '370113', '4', '长清区', 'CQQ', '0', '116.751959', '36.553691', '195');
INSERT INTO `t_s_territory` VALUES ('927', '370124', '4', '平阴县', 'PYX', '0', '116.456187', '36.289265', '195');
INSERT INTO `t_s_territory` VALUES ('928', '370125', '4', '济阳县', 'JYX', '0', '117.173529', '36.978547', '195');
INSERT INTO `t_s_territory` VALUES ('929', '370126', '4', '商河县', 'SHX', '0', '117.157183', '37.309045', '195');
INSERT INTO `t_s_territory` VALUES ('93', '370600', '3', '烟台市', 'YTS', '0', '121.447926', '37.463819', '18');
INSERT INTO `t_s_territory` VALUES ('930', '370181', '4', '章丘市', 'ZQS', '0', '117.534326', '36.714015', '195');
INSERT INTO `t_s_territory` VALUES ('931', '370202', '4', '市南区', 'SNQ', '0', '120.412392', '36.075651', '89');
INSERT INTO `t_s_territory` VALUES ('932', '370203', '4', '市北区', 'SBQ', '0', '120.374801', '36.087661', '89');
INSERT INTO `t_s_territory` VALUES ('933', '370205', '4', '四方区', 'SFQ', '0', '120.366454', '36.103993', '89');
INSERT INTO `t_s_territory` VALUES ('934', '370211', '4', '黄岛区', 'HDQ', '0', '120.198054', '35.960935', '89');
INSERT INTO `t_s_territory` VALUES ('935', '370212', '4', '崂山区', 'LSQ', '0', '120.468956', '36.107538', '89');
INSERT INTO `t_s_territory` VALUES ('936', '370213', '4', '李沧区', 'LCQ', '0', '120.432864', '36.145476', '89');
INSERT INTO `t_s_territory` VALUES ('937', '370214', '4', '城阳区', 'CYQ', '0', '120.396529', '36.307061', '89');
INSERT INTO `t_s_territory` VALUES ('938', '370281', '4', '胶州市', 'JZS', '0', '120.033345', '36.264664', '89');
INSERT INTO `t_s_territory` VALUES ('939', '370282', '4', '即墨市', 'JMS', '0', '120.447162', '36.389401', '89');
INSERT INTO `t_s_territory` VALUES ('94', '370700', '3', '潍坊市', 'WFS', '0', '119.16193', '36.706691', '18');
INSERT INTO `t_s_territory` VALUES ('940', '370283', '4', '平度市', 'PDS', '0', '119.960014', '36.7867', '89');
INSERT INTO `t_s_territory` VALUES ('941', '370284', '4', '胶南市', 'JNS', '0', '120.04643', '35.8725', '89');
INSERT INTO `t_s_territory` VALUES ('942', '370285', '4', '莱西市', 'LXS', '0', '120.51769', '36.889084', '89');
INSERT INTO `t_s_territory` VALUES ('943', '370302', '4', '淄川区', 'ZCQ', '0', '117.966842', '36.643449', '90');
INSERT INTO `t_s_territory` VALUES ('944', '370303', '4', '张店区', 'ZDQ', '0', '118.017656', '36.806773', '90');
INSERT INTO `t_s_territory` VALUES ('945', '370304', '4', '博山区', 'BSQ', '0', '117.861698', '36.494752', '90');
INSERT INTO `t_s_territory` VALUES ('946', '370305', '4', '临淄区', 'LZQ', '0', '118.308977', '36.827343', '90');
INSERT INTO `t_s_territory` VALUES ('947', '370306', '4', '周村区', 'ZCQ', '0', '117.869877', '36.803109', '90');
INSERT INTO `t_s_territory` VALUES ('948', '370321', '4', '桓台县', 'HTX', '0', '118.097955', '36.959623', '90');
INSERT INTO `t_s_territory` VALUES ('949', '370322', '4', '高青县', 'GQX', '0', '117.826916', '37.171063', '90');
INSERT INTO `t_s_territory` VALUES ('95', '370800', '3', '济宁市', 'JNS', '0', '116.587099', '35.414921', '18');
INSERT INTO `t_s_territory` VALUES ('950', '370323', '4', '沂源县', 'YYX', '0', '118.170979', '36.184827', '90');
INSERT INTO `t_s_territory` VALUES ('951', '370402', '4', '市中区', 'SZQ', '0', '117.556124', '34.864114', '91');
INSERT INTO `t_s_territory` VALUES ('952', '370403', '4', '薛城区', 'YCQ', '0', '117.263157', '34.795206', '91');
INSERT INTO `t_s_territory` VALUES ('953', '370404', '4', '峄城区', 'YCQ', '0', '117.590819', '34.772236', '91');
INSERT INTO `t_s_territory` VALUES ('954', '370405', '4', '台儿庄区', 'TEZQ', '0', '117.733832', '34.562528', '91');
INSERT INTO `t_s_territory` VALUES ('955', '370406', '4', '山亭区', 'STQ', '0', '117.461343', '35.099549', '91');
INSERT INTO `t_s_territory` VALUES ('956', '370481', '4', '滕州市', 'TZS', '0', '117.164388', '35.084021', '91');
INSERT INTO `t_s_territory` VALUES ('957', '370502', '4', '东营区', 'DYQ', '0', '118.582184', '37.448964', '92');
INSERT INTO `t_s_territory` VALUES ('958', '370503', '4', '河口区', 'HKQ', '0', '118.525579', '37.886138', '92');
INSERT INTO `t_s_territory` VALUES ('959', '370521', '4', '垦利县', 'KLX', '0', '118.547627', '37.58754', '92');
INSERT INTO `t_s_territory` VALUES ('960', '370522', '4', '利津县', 'LJX', '0', '118.255273', '37.49026', '92');
INSERT INTO `t_s_territory` VALUES ('961', '370523', '4', '广饶县', 'GRX', '0', '118.407045', '37.0537', '92');
INSERT INTO `t_s_territory` VALUES ('962', '370602', '4', '芝罘区', 'ZFQ', '0', '121.400031', '37.540687', '93');
INSERT INTO `t_s_territory` VALUES ('963', '370611', '4', '福山区', 'FSQ', '0', '121.267697', '37.498051', '93');
INSERT INTO `t_s_territory` VALUES ('964', '370612', '4', '牟平区', 'MPQ', '0', '121.600512', '37.386901', '93');
INSERT INTO `t_s_territory` VALUES ('965', '370613', '4', '莱山区', 'LSQ', '0', '121.445304', '37.511305', '93');
INSERT INTO `t_s_territory` VALUES ('966', '370614', '4', '开发区', 'KFQ', '0', '121.251001', '37.554683', '93');
INSERT INTO `t_s_territory` VALUES ('967', '370634', '4', '长岛县', 'CDX', '0', '120.736584', '37.921417', '93');
INSERT INTO `t_s_territory` VALUES ('968', '370681', '4', '龙口市', 'LKS', '0', '120.477836', '37.646064', '93');
INSERT INTO `t_s_territory` VALUES ('969', '370682', '4', '莱阳市', 'LYS', '0', '120.711607', '36.97891', '93');
INSERT INTO `t_s_territory` VALUES ('970', '370683', '4', '莱州市', 'LZS', '0', '119.942327', '37.177017', '93');
INSERT INTO `t_s_territory` VALUES ('971', '370684', '4', '蓬莱市', 'PLS', '0', '120.758848', '37.810661', '93');
INSERT INTO `t_s_territory` VALUES ('972', '370685', '4', '招远市', 'ZYS', '0', '120.434072', '37.355469', '93');
INSERT INTO `t_s_territory` VALUES ('973', '370686', '4', '栖霞市', 'QXS', '0', '120.849675', '37.335123', '93');
INSERT INTO `t_s_territory` VALUES ('974', '370687', '4', '海阳市', 'HYS', '0', '121.158477', '36.776425', '93');
INSERT INTO `t_s_territory` VALUES ('975', '370702', '4', '潍城区', 'WCQ', '0', '119.024836', '36.7281', '94');
INSERT INTO `t_s_territory` VALUES ('976', '370703', '4', '寒亭区', 'HTQ', '0', '119.219734', '36.775491', '94');
INSERT INTO `t_s_territory` VALUES ('977', '370704', '4', '坊子区', 'FZQ', '0', '119.166485', '36.654448', '94');
INSERT INTO `t_s_territory` VALUES ('978', '370705', '4', '奎文区', 'KWQ', '0', '119.132486', '36.707676', '94');
INSERT INTO `t_s_territory` VALUES ('979', '370724', '4', '临朐县', 'LQX', '0', '118.542982', '36.5125059', '94');
INSERT INTO `t_s_territory` VALUES ('980', '370725', '4', '昌乐县', 'CLX', '0', '118.829914', '36.706945', '94');
INSERT INTO `t_s_territory` VALUES ('981', '370781', '4', '青州市', 'QZS', '0', '118.479622', '36.684528', '94');
INSERT INTO `t_s_territory` VALUES ('982', '370782', '4', '诸城市', 'ZCS', '0', '119.410103', '35.995654', '94');
INSERT INTO `t_s_territory` VALUES ('983', '370783', '4', '寿光市', 'SGS', '0', '118.790652', '36.85548', '94');
INSERT INTO `t_s_territory` VALUES ('984', '370784', '4', '安丘市', 'AQS', '0', '119.218978', '36.478494', '94');
INSERT INTO `t_s_territory` VALUES ('985', '370785', '4', '高密市', 'GMS', '0', '119.755597', '36.3825949', '94');
INSERT INTO `t_s_territory` VALUES ('986', '370786', '4', '昌邑市', 'CYS', '0', '119.398525', '36.85882', '94');
INSERT INTO `t_s_territory` VALUES ('987', '370802', '4', '市中区', 'SZQ', '0', '116.596614', '35.40819', '95');
INSERT INTO `t_s_territory` VALUES ('988', '370811', '4', '任城区', 'RCQ', '0', '116.628562', '35.433727', '95');
INSERT INTO `t_s_territory` VALUES ('989', '370826', '4', '微山县', 'WSX', '0', '117.128946', '34.8071', '95');
INSERT INTO `t_s_territory` VALUES ('990', '370827', '4', '鱼台县', 'YTX', '0', '116.650608', '35.012749', '95');
INSERT INTO `t_s_territory` VALUES ('991', '370828', '4', '金乡县', 'JXX', '0', '116.311532', '35.06662', '95');
INSERT INTO `t_s_territory` VALUES ('992', '370829', '4', '嘉祥县', 'JXX', '0', '116.342442', '35.407829', '95');
INSERT INTO `t_s_territory` VALUES ('993', '370830', '4', '汶上县', 'WSX', '0', '116.489043', '35.732799', '95');
INSERT INTO `t_s_territory` VALUES ('994', '370831', '4', '泗水县', 'SSX', '0', '117.251195', '35.664323', '95');
INSERT INTO `t_s_territory` VALUES ('995', '370832', '4', '梁山县', 'LSX', '0', '116.096044', '35.802306', '95');
INSERT INTO `t_s_territory` VALUES ('996', '370881', '4', '曲阜市', 'QFS', '0', '116.986532', '35.581137', '95');
INSERT INTO `t_s_territory` VALUES ('997', '370882', '4', '兖州市', 'YZS', '0', '116.783834', '35.553144', '95');
INSERT INTO `t_s_territory` VALUES ('998', '370883', '4', '邹城市', 'ZCS', '0', '117.003743', '35.405185', '95');
INSERT INTO `t_s_territory` VALUES ('999', '370902', '4', '泰山区', 'TSQ', '0', '117.135354', '36.192084', '96');

-- ----------------------------
-- Table structure for t_s_timetask
-- ----------------------------
DROP TABLE IF EXISTS `t_s_timetask`;
CREATE TABLE `t_s_timetask` (
  `ID` varchar(32) NOT NULL,
  `CREATE_BY` varchar(32) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(32) default NULL,
  `CRON_EXPRESSION` varchar(100) NOT NULL,
  `IS_EFFECT` varchar(1) NOT NULL,
  `IS_START` varchar(1) NOT NULL,
  `TASK_DESCRIBE` varchar(50) NOT NULL,
  `TASK_ID` varchar(100) NOT NULL,
  `UPDATE_BY` varchar(32) default NULL,
  `UPDATE_DATE` datetime default NULL,
  `UPDATE_NAME` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_timetask
-- ----------------------------
INSERT INTO `t_s_timetask` VALUES ('4028ef8156b0d8e00156b1002aea0026', '4028d881436d514601436d5215ac0043', '2016-08-22 14:46:33', 'admin', '0 0/6 * * * ?', '1', '0', '重置微信超过2小时失效Token', 'autoResetWeixinTokenCronTrigger', '4028d881436d514601436d5215ac0043', '2016-08-22 14:49:53', 'admin');

-- ----------------------------
-- Table structure for t_s_type
-- ----------------------------
DROP TABLE IF EXISTS `t_s_type`;
CREATE TABLE `t_s_type` (
  `ID` varchar(32) NOT NULL,
  `typecode` varchar(50) default NULL,
  `typename` varchar(50) default NULL,
  `typepid` varchar(32) default NULL,
  `typegroupid` varchar(32) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_nw2b22gy7plh7pqows186odmq` (`typepid`),
  KEY `FK_3q40mr4ebtd0cvx79matl39x1` (`typegroupid`),
  CONSTRAINT `FK_3q40mr4ebtd0cvx79matl39x1` FOREIGN KEY (`typegroupid`) REFERENCES `t_s_typegroup` (`ID`),
  CONSTRAINT `FK_nw2b22gy7plh7pqows186odmq` FOREIGN KEY (`typepid`) REFERENCES `t_s_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_type
-- ----------------------------
INSERT INTO `t_s_type` VALUES ('402889f8476650de01476666acbf0005', '01', '多文章', null, '402889f8476650de01476665dfe70002');
INSERT INTO `t_s_type` VALUES ('402889f8476650de01476666d5c30007', '02', '单文章', null, '402889f8476650de01476665dfe70002');
INSERT INTO `t_s_type` VALUES ('402889ff4728f2370147290a0f82001d', '1', '服务号', null, '402889ff4728f2370147290995f80018');
INSERT INTO `t_s_type` VALUES ('402889ff4728f2370147290a2d63001f', '2', '订阅号', null, '402889ff4728f2370147290995f80018');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216130051', '2', '菜单图标', null, '4028d881436d514601436d5215c30047');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216160052', '1', '系统图标', null, '4028d881436d514601436d5215c30047');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216190053', 'files', '附件', null, '4028d881436d514601436d5215e4004f');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52161c0054', '1', '优质订单', null, '4028d881436d514601436d5215c90048');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52161f0055', '2', '普通订单', null, '4028d881436d514601436d5215c90048');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216220056', '1', '签约客户', null, '4028d881436d514601436d5215cc0049');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216250057', '2', '普通客户', null, '4028d881436d514601436d5215cc0049');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216290058', '1', '特殊服务', null, '4028d881436d514601436d5215cf004a');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52162e0059', '2', '普通服务', null, '4028d881436d514601436d5215cf004a');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d521633005a', 'single', '单条件查询', null, '4028d881436d514601436d5215d4004b');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d521638005b', 'group', '范围查询', null, '4028d881436d514601436d5215d4004b');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52163d005c', 'Y', '是', null, '4028d881436d514601436d5215d7004c');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d521640005d', 'N', '否', null, '4028d881436d514601436d5215d7004c');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d521643005e', 'Integer', 'Integer', null, '4028d881436d514601436d5215db004d');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d521646005f', 'Date', 'Date', null, '4028d881436d514601436d5215db004d');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52164b0060', 'String', 'String', null, '4028d881436d514601436d5215db004d');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52164f0061', 'Long', 'Long', null, '4028d881436d514601436d5215db004d');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216530062', 'act', '工作流引擎表', null, '4028d881436d514601436d5215df004e');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216570063', 't_s', '系统基础表', null, '4028d881436d514601436d5215df004e');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52165c0064', 't_b', '业务表', null, '4028d881436d514601436d5215df004e');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216600065', 't_p', '自定义引擎表', null, '4028d881436d514601436d5215df004e');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216640066', 'news', '新闻', null, '4028d881436d514601436d5215e4004f');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d5216670067', '0', '男性', null, '4028d881436d514601436d5215e90050');
INSERT INTO `t_s_type` VALUES ('4028d881436d514601436d52166a0068', '1', '女性', null, '4028d881436d514601436d5215e90050');

-- ----------------------------
-- Table structure for t_s_typegroup
-- ----------------------------
DROP TABLE IF EXISTS `t_s_typegroup`;
CREATE TABLE `t_s_typegroup` (
  `ID` varchar(32) NOT NULL,
  `typegroupcode` varchar(50) default NULL,
  `typegroupname` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_typegroup
-- ----------------------------
INSERT INTO `t_s_typegroup` VALUES ('402889f8476650de01476665dfe70002', 'cms_menu', 'CMS菜单类型');
INSERT INTO `t_s_typegroup` VALUES ('402889ff4728f2370147290995f80018', 'weixintype', '微信公众账号类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215c30047', 'icontype', '图标类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215c90048', 'order', '订单类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215cc0049', 'custom', '客户类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215cf004a', 'service', '服务项目类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215d4004b', 'searchmode', '查询模式');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215d7004c', 'yesorno', '逻辑条件');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215db004d', 'fieldtype', '字段类型');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215df004e', 'database', '数据表');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215e4004f', 'fieltype', '文档分类');
INSERT INTO `t_s_typegroup` VALUES ('4028d881436d514601436d5215e90050', 'sex', '性别类');

-- ----------------------------
-- Table structure for t_s_user
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user`;
CREATE TABLE `t_s_user` (
  `email` varchar(50) default NULL,
  `mobilePhone` varchar(30) default NULL,
  `officePhone` varchar(20) default NULL,
  `signatureFile` varchar(100) default NULL,
  `id` varchar(32) NOT NULL,
  `accountid` varchar(36) default NULL,
  `type` varchar(2) default '1',
  PRIMARY KEY  (`id`),
  KEY `FK_2cuji5h6yorrxgsr8ojndlmal` (`id`),
  CONSTRAINT `FK_2cuji5h6yorrxgsr8ojndlmal` FOREIGN KEY (`id`) REFERENCES `t_s_base_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_user
-- ----------------------------
INSERT INTO `t_s_user` VALUES ('', '', '', null, '402881e4461f9c6401461fa2e6f50002', '', null);
INSERT INTO `t_s_user` VALUES ('', '', '', null, '402881e44648134a014648174a45000c', '', null);
INSERT INTO `t_s_user` VALUES ('', '', '', 'images/renfang/qm/licf.gif', '4028d881436d514601436d5215ac0043', '', null);
INSERT INTO `t_s_user` VALUES ('zhangdaiscott@163.com', '', '', null, '4028d881436d514601436d5215b20044', '', null);
INSERT INTO `t_s_user` VALUES (null, null, null, null, '4028d881436d514601436d5215b80045', '', null);
INSERT INTO `t_s_user` VALUES (null, null, null, null, '4028d881436d514601436d5215bc0046', '', null);

-- ----------------------------
-- Table structure for t_s_version
-- ----------------------------
DROP TABLE IF EXISTS `t_s_version`;
CREATE TABLE `t_s_version` (
  `ID` varchar(32) NOT NULL,
  `loginpage` varchar(100) default NULL,
  `versioncode` varchar(50) default NULL,
  `versionname` varchar(30) default NULL,
  `versionnum` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_version
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_accesstoken
-- ----------------------------
DROP TABLE IF EXISTS `weixin_accesstoken`;
CREATE TABLE `weixin_accesstoken` (
  `ID` varchar(32) NOT NULL,
  `access_token` varchar(255) default NULL,
  `addtime` datetime default NULL,
  `expires_ib` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_accesstoken
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_account
-- ----------------------------
DROP TABLE IF EXISTS `weixin_account`;
CREATE TABLE `weixin_account` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `accountname` varchar(200) default NULL COMMENT '公众帐号名称',
  `accounttoken` varchar(200) default NULL COMMENT '公众帐号TOKEN',
  `accountnumber` varchar(200) default NULL COMMENT '公众微信号',
  `accounttype` varchar(50) default NULL COMMENT '公众号类型',
  `accountemail` varchar(200) default NULL COMMENT '电子邮箱',
  `accountdesc` varchar(500) default NULL COMMENT '公众帐号描述',
  `accountaccesstoken` varchar(1000) default NULL COMMENT 'ACCESS_TOKEN',
  `accountappid` varchar(200) default NULL COMMENT '公众帐号APPID',
  `accountappsecret` varchar(500) default NULL COMMENT '公众帐号APPSECRET',
  `ADDTOEKNTIME` datetime default NULL,
  `USERNAME` varchar(50) default NULL,
  `WEIXIN_ACCOUNTID` varchar(100) default NULL,
  `apiticket` varchar(200) default NULL,
  `apiticketttime` datetime default NULL,
  `jsapiticket` varchar(200) default NULL,
  `jsapitickettime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_account
-- ----------------------------
INSERT INTO `weixin_account` VALUES ('402881e8461795c201461795c2e90000', 'Jeewx演示账号', 'jeewx', 'guojusoft', '1', 'jeecg@sina.com', '无', '7_0Ihe5B-WTF0P07L2IfxX3yNZcthPkLTrsiSyC1jXRJi8ozYht4VJ-pHxMxs_mYkltpQ7Iejm8Y1qj2qc8tkMm0MTeTtNjbuOjQvmbqUBNoJ95YLQmfMexPm9HB-5IXTO-C0BSCdTGe-FZBLTPXLlCAABJX', 'wxc60a4d9cbac8092d', '?', '2018-03-14 18:07:27', 'admin', '?', 'IpK_1T69hDhZkLQTlwsAXyjkZaTEYbX_kE6IkQ8b5Waw7uX8zbeFp34lurpnPaSKLMQZZ1RsvceEiMn6_ft3cA', '2016-08-22 14:48:38', 'kgt8ON7yVITDhtdwci0qeeC2RWfOAQ1Sc_HMdjrzSlrCrMnOPzBTmb9VWOz9dMwnZ_jpSYfOnKEppyjOl0YKKg', '2018-03-14 18:07:27');

-- ----------------------------
-- Table structure for weixin_autoresponse
-- ----------------------------
DROP TABLE IF EXISTS `weixin_autoresponse`;
CREATE TABLE `weixin_autoresponse` (
  `ID` varchar(32) NOT NULL,
  `addtime` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `msgtype` varchar(255) default NULL,
  `rescontent` varchar(255) default NULL,
  `templatename` varchar(255) default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_autoresponse
-- ----------------------------
INSERT INTO `weixin_autoresponse` VALUES ('402889ff4728a293014728b0c7da001b', '2014-07-12 11:49:39', '01', 'text', 'f7a8a3a946e55a940146e5a3e498004d', '微译使用指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_autoresponse` VALUES ('402889ff4728a293014728b155c8001c', '2014-07-12 11:50:16', '你好', 'text', '4028d8814734ee0d0147356b4c730010', '你好', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_autoresponse` VALUES ('f7a8a3a946e249c20146e26a10ca0019', '2014-06-28 20:19:00', '?', 'text', 'f7a8a3a946e249c20146e25c4dc7000c', '欢迎关注语', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_autoresponse` VALUES ('f7a8a3a946e55a940146e5a47b890050', '2014-06-29 11:21:40', '02', 'text', 'f7a8a3a946e55a940146e5a429cf004e', '天气使用指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_autoresponse` VALUES ('f7a8a3a946e55a940146e5a632220054', '2014-06-29 11:23:32', '03', 'text', 'f7a8a3a946e55a940146e5a53e6b0051', '大转盘指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_autoresponse` VALUES ('f7a8a3a946e55a940146e5a656150055', '2014-06-29 11:23:41', '04', 'text', 'f7a8a3a946e55a940146e5a584da0052', '刮刮乐指南', '402881e8461795c201461795c2e90000');

-- ----------------------------
-- Table structure for weixin_cms_ad
-- ----------------------------
DROP TABLE IF EXISTS `weixin_cms_ad`;
CREATE TABLE `weixin_cms_ad` (
  `ID` varchar(36) NOT NULL,
  `ACCOUNTID` varchar(100) default NULL,
  `CREATE_BY` varchar(255) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(255) default NULL,
  `IMAGE_HREF` varchar(255) default NULL,
  `IMAGE_NAME` varchar(255) default NULL,
  `TITLE` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_cms_ad
-- ----------------------------
INSERT INTO `weixin_cms_ad` VALUES ('402881e44695183a014695214a510013', '402881e8461795c201461795c2e90000', 'admin', '2014-06-13 20:08:45', '管理员', 'upload/files/20140627233502FEzyc38j.jpg', '19300001024098134717639977056.jpg', 'IPHONE7');
INSERT INTO `weixin_cms_ad` VALUES ('402881e44695183a014695219fe70016', '402881e8461795c201461795c2e90000', 'admin', '2014-06-13 20:09:07', '管理员', 'upload/files/20140627232736z3SvfVEj.jpg', '634490825089531250.jpg', 'IPHONE');
INSERT INTO `weixin_cms_ad` VALUES ('402881e946ddea480146de053265000b', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2014-06-27 23:50:21', 'admin', 'upload/files/20140627235534VJM1pkx3.jpg', 'Img305735292.jpg', '黑莓');
INSERT INTO `weixin_cms_ad` VALUES ('402881e946ddea480146de0f3a810011', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2014-06-28 00:01:18', 'admin', 'upload/files/20140628000116OF07rm7E.jpg', '30_1i$1JxIOB.jpg', 'Lumia930 王者归来');

-- ----------------------------
-- Table structure for weixin_cms_article
-- ----------------------------
DROP TABLE IF EXISTS `weixin_cms_article`;
CREATE TABLE `weixin_cms_article` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `title` varchar(50) default NULL COMMENT '标题',
  `image_name` varchar(255) default NULL COMMENT '图片名称',
  `image_href` varchar(255) default NULL COMMENT '图片地址',
  `summary` varchar(255) default NULL,
  `content` text COMMENT '内容',
  `column_id` varchar(36) default NULL COMMENT '栏目id',
  `accountid` varchar(100) default NULL COMMENT '微信账户',
  `create_name` varchar(255) default NULL COMMENT '创建人',
  `create_by` varchar(255) default NULL COMMENT '创建人id',
  `create_date` datetime default NULL COMMENT '创建日期',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_cms_article
-- ----------------------------
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa01622287183c0027', 'JEEWX微信管家系统', '20180314110154ZkWNdEWE.png', 'upload/files/20180314182311zTY0A5TW.png', 'JeeWx  捷微管家系统，是一个高效，敏捷，智能的微信管家系统，采用JAVA语言，业内独一份java版本。\r\n    JeeWx的目标是优化微信应用开发的流程，使开发者能把更多的精力放到业务逻辑的设计而不是淹没在卷帙浩繁的代码海洋中，打造一个高效敏捷的微信应用开发平台。JeeWx采用插件的方式实现微信各类功能，如微网站，微商城，微活动，为公众账号提供一站式微信开发、管理服务。\r\n', '<p style=\"white-space: normal; line-height: 33px;\"><span style=\"color: rgb(255, 0, 0); letter-spacing: 0px; font-family: 微软雅黑; font-weight: bold;\">[产品简介]</span></p><p style=\"white-space: normal; line-height: 33px;\"><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JEEWX是一个开源，高效，敏捷的微信开发平台，采用JAVA语言，基于jeecg企业级快速开发框架实现。&nbsp;jeewx的目的是最大化的简化微信开发的流程，使用开发者能把最好的精力放到微信具体业务开发，并能以最快的时间完成。把一些常规而频繁的工作交由jeewx来处理即可，平台兼备的代码生成器，在线开发，可以快速的完成企业应用。jeewx采用插件的方式实现微信功能，不同的插件实现不同的微信功能。</span></p><p style=\"white-space: normal; line-height: 33px;\"><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">&nbsp;&nbsp;</span><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px; font-weight: bold;\">&nbsp;&nbsp;JEEWX独有集团化管理模式：&nbsp;</span><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">随着微信运营的普及发展，逐渐出现不同的领域需求。对于集团化公司和连锁公司，分布于全国省市县的每个分公司运营着自己的公众号，每个公众号都有自己的运营需求，就这样形成了一个树状网的微信公众号集群（全国几百甚至上万个公众号）。集团公司迫切需要统一集中管理微信公众号集群：通过统一权限管控（类似组织机构上下级）、统一素材中心、统一群发机制、集中报表分析，形成集团化营销模式和公司战略布局。</span></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"color: rgb(255, 0, 0); font-weight: 700;\"></span></p><p style=\"white-space: normal; line-height: 33px;\"><span style=\"color: rgb(255, 0, 0); letter-spacing: 0px; font-weight: bold;\">[产品功能]</span></p><p style=\"white-space: normal; line-height: 33px;\"><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">1</span><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">.&nbsp;微信营销活动</span></p><p style=\"white-space: normal; line-height: 33px;\"><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">&nbsp; &nbsp;抽奖活动（幸运水果机、砸金蛋、大转盘、刮刮乐、摇一摇等）、调研问卷、竞选投票、微信墙、微社区、微签到、微视频、微相册、二维码推广等</span></p><ul class=\" list-paddingleft-2\" style=\"width: 504.638px; white-space: normal;\"><li><p style=\"margin-top: auto; margin-bottom: auto;\"><span style=\"letter-spacing: 0px; font-family: 微软雅黑; font-size: 15px;\">&nbsp;幸运水果机—动动手指，好礼送不停！</span></p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"水果机.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768086012596.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>&nbsp;砸金蛋，赢好礼</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"砸金蛋.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768128031441.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>&nbsp;&nbsp;快乐大转盘，幸运落谁家</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"大转盘.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768158036708.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>&nbsp;&nbsp;好运赢好礼，乐享刮不停</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"刮刮乐.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768199035116.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>全民摇一摇，好礼大派送！</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"摇一摇.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768231071568.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>实时统计数据，方便获取民意</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"调研.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768251011985.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p><span style=\"outline: 0px; color: rgb(255, 0, 0); box-sizing: border-box;\"><span style=\"outline: 0px; color: rgb(51, 51, 51); box-sizing: border-box;\">微信墙，有效在线交流平台</span></span></p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"160\" alt=\"微信墙.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768269052683.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p><span style=\"outline: 0px; color: rgb(255, 0, 0); box-sizing: border-box;\"><span style=\"outline: 0px; color: rgb(51, 51, 51); box-sizing: border-box;\">微社区便于扩展</span></span></p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"203\" alt=\"微社区.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768300091184.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p style=\"margin-top: 8px; margin-bottom: 8px; outline: 0px; line-height: 22px; letter-spacing: 0.5px; font-size: 0.87rem; box-sizing: border-box;\">微签到</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"210\" alt=\"微信签到.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768336085396.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>微相册</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"534\" alt=\"微相册.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768391007669.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"outline: 0px; font-weight: 700; box-sizing: border-box;\">2、微网站</span></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"outline: 0px; box-sizing: border-box;\">&nbsp; &nbsp; 三网通智能建站系统，一个平台发布所有站点(PC站、微信网站、手机网站），响应式网站实现PC端和移动终端完美兼容。后台系统包括广告管理、栏目管理、文章管理、网站信息、友情链接等菜单。</span></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"outline: 0px; box-sizing: border-box;\"><img width=\"300\" height=\"491\" alt=\"微网站1.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768437001858.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/><img width=\"300\" height=\"533\" alt=\"微网站2.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768480022322.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/><br style=\"outline: 0px; box-sizing: border-box;\"/></span></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"473\" alt=\"微网站3.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768525061163.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"437\" alt=\"微网站4.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768577096882.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"outline: 0px; box-sizing: border-box;\"><span style=\"outline: 0px; font-weight: 700; box-sizing: border-box;\">3、微商城</span></span></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>店铺商品整合</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"401\" alt=\"微商城1.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768627041714.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><ul class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>商品属性自定义</p></li></ul><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><img width=\"300\" height=\"428\" alt=\"微商城2.jpg\" src=\"http://www.jeewx.com/jeewx/upload/ueditor/upload/image/20151030/1446192768676074559.jpg\" style=\"margin: 0px 5px; outline: 0px; border: currentcolor; width: 300px; height: auto; vertical-align: middle; cursor: pointer; box-sizing: border-box;\"/></p><p style=\"white-space: normal;\"><br/></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><span style=\"outline: 0px; color: rgb(255, 0, 0); box-sizing: border-box;\"><span style=\"outline: 0px; font-weight: 700; box-sizing: border-box;\">【运行环境】</span></span></p><ol class=\" list-paddingleft-2\" hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"width: 504.638px; padding: 0px 0px 0px 40px; white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 1.6;\"><li><p>java</p></li><li><p>window/linux</p></li></ol><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\"><a href=\"http://xxxxx.xxxx/\" rel=\"nofollow\" style=\"outline: 0px; border: currentcolor; color: rgb(66, 152, 186); text-decoration-line: none; box-sizing: border-box;\"><span style=\"outline: 0px; font-weight: 700; box-sizing: border-box;\"><span style=\"outline: 0px; color: rgb(255, 0, 0); box-sizing: border-box;\">【演示地址】</span></span></a></p><p hiragino=\"\" sans=\"\" microsoft=\"\" wenquanyi=\"\" micro=\"\" segoe=\"\" ui=\"\" font-size:=\"\" margin-top:=\"\" margin-bottom:=\"\" white-space:=\"\" -ms-word-wrap:=\"\" box-sizing:=\"\" background-color:=\"\" style=\"white-space: normal; outline: 0px; color: rgb(51, 51, 51); line-height: 22px; letter-spacing: 0.5px;\">&nbsp; &nbsp;&nbsp;<a href=\"http://www.jeewx.com/jeewx\" target=\"_self\">http://www.jeewx.com/jeewx</a></p><p><br/></p>', '402881e8622272aa0162227f55760013', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:21:41');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa01622288e620002a', 'JeeWx多触点管理平台（支持微信、支付窗、企业号等）', '20180314112305d7mI1I7k.jpg', 'upload/files/20180314182259bWOKOVH5.jpg', 'JeeWx 微信管家平台，简称“捷微”.\r\n（一款免费开源的JAVA多触点管理平台）', '<p>http://git.oschina.net/jeecg/jeewx</p>', '402881e8622272aa016222805b74001a', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:23:39');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa01622289a59e002c', 'JEEWX-API 微信极速SDK', '20180314112305d7mI1I7k.jpg', 'upload/files/20180314182247ZR3HVe9B.jpg', 'JEEWX-API 微信极速SDK', '<p>http://git.oschina.net/jeecg/jeewx-api</p>', '402881e8622272aa016222805b74001a', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:24:28');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa0162228a2d3b002e', 'JEEWX又双叒有新亮点！升级大盘点', '20180302114506uahy2mJM.png', 'upload/files/20180314112643vjuuLxaE.png', 'JEEWX又双叒有新亮点！升级大盘点', '<p>http://mp.weixin.qq.com/s/s0MrtXM6l14Ay1gvEZer-A</p>', '402881e8622272aa0162228028330018', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:25:03');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa0162228b61cc0033', '微信企业号 Java SDK', '20180314111124jUmrvtaw.jpg', 'upload/files/20180314182222ADWKc2dN.jpg', '微信企业号 Java SDK', '<p>http://git.oschina.net/jeecg/jeewx-qywx-api</p>', '402881e8622272aa016222805b74001a', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:26:22');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa0162228caeaa003c', 'jeewx经典案例', '20170823190022AS4zjxbQ.png', 'upload/files/20180314182143NbxL6IkH.png', 'jeewx经典案例', '<p>http://jeewx.com/works.html</p>', '402881e8622272aa0162227fcad70015', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:27:47');
INSERT INTO `weixin_cms_article` VALUES ('402881e8622272aa0162228d5888003e', '关于我们', '', '', '我们是JEECG开源社区', '<p>我们是JEECG开源社区，捷微管家平台</p>', '402881e8622272aa0162228419a50021', '402881e8461795c201461795c2e90000', 'admin', '4028d881436d514601436d5215ac0043', '2018-03-14 11:28:31');

-- ----------------------------
-- Table structure for weixin_cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `weixin_cms_menu`;
CREATE TABLE `weixin_cms_menu` (
  `ID` varchar(36) NOT NULL,
  `ACCOUNTID` varchar(100) default NULL,
  `CREATE_BY` varchar(255) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(255) default NULL,
  `IMAGE_HREF` varchar(255) default NULL,
  `IMAGE_NAME` varchar(255) default NULL,
  `NAME` varchar(20) default NULL,
  `TYPE` varchar(20) default NULL COMMENT '类型',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_cms_menu
-- ----------------------------
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa0162227f55760013', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:13:13', 'admin', 'upload/files/20180314111309hCiF0491.jpg', '20180302114759Trx1Z5k4.jpg', '产品介绍', '01');
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa0162227fcad70015', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:13:43', 'admin', 'upload/files/20180314111556OKjDvbGp.jpg', '20140613235852gBTueAWO.jpg', '经典案例', '01');
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa0162228028330018', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:14:07', 'admin', 'upload/files/20180314111405ku5BgntL.png', '20151105205236CUF2Dwwv.png', '新闻动态', '01');
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa016222805b74001a', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:14:20', 'admin', 'upload/files/20180314111712GE7YGfdf.png', '20140628131752uHYw9KLU.png', '开源项目', '01');
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa0162228419a50021', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:18:25', 'admin', 'upload/files/20180314111823PUSdqYu7.jpg', '20140613235911chsAs0Md.jpg', '关于我们', '01');
INSERT INTO `weixin_cms_menu` VALUES ('402881e8622272aa01622284c6b90024', '402881e8461795c201461795c2e90000', '4028d881436d514601436d5215ac0043', '2018-03-14 11:19:09', 'admin', 'upload/files/20180314111908wIvem8Wk.jpg', '20151201175716Q7tIXj7S.jpg', '其他', '01');

-- ----------------------------
-- Table structure for weixin_cms_site
-- ----------------------------
DROP TABLE IF EXISTS `weixin_cms_site`;
CREATE TABLE `weixin_cms_site` (
  `ID` varchar(36) NOT NULL,
  `ACCOUNTID` varchar(32) default NULL,
  `COMPANY_TEL` varchar(50) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(50) default NULL,
  `SITE_LOGO` varchar(200) default NULL,
  `SITE_NAME` varchar(100) default NULL,
  `SITE_TEMPLATE_STYLE` varchar(50) default NULL,
  `UPDATE_DATE` datetime default NULL,
  `UPDATE_NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_cms_site
-- ----------------------------
INSERT INTO `weixin_cms_site` VALUES ('402881e86223f2de016223f4f33e0005', '402881e8461795c201461795c2e90000', '010-64808099', '2018-03-14 18:01:18', 'admin', 'upload/files/20180314182407ZiEC8Cug.png', 'JEEWX官网', '', '2018-03-14 18:24:09', 'admin');

-- ----------------------------
-- Table structure for weixin_cms_style
-- ----------------------------
DROP TABLE IF EXISTS `weixin_cms_style`;
CREATE TABLE `weixin_cms_style` (
  `ID` varchar(36) NOT NULL,
  `ACCOUNTID` varchar(50) default NULL,
  `CREATE_DATE` datetime default NULL,
  `CREATE_NAME` varchar(50) default NULL,
  `REVIEW_IMG_URL` varchar(100) default NULL,
  `TEMPLATE_NAME` varchar(100) default NULL,
  `TEMPLATE_URL` varchar(200) default NULL,
  `UPDATE_DATE` datetime default NULL,
  `UPDATE_NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_cms_style
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_expandconfig
-- ----------------------------
DROP TABLE IF EXISTS `weixin_expandconfig`;
CREATE TABLE `weixin_expandconfig` (
  `ID` varchar(36) NOT NULL,
  `ACCOUNTID` varchar(200) default NULL,
  `CLASSNAME` varchar(100) NOT NULL,
  `CONTENT` longtext,
  `KEYWORD` varchar(100) NOT NULL,
  `NAME` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_expandconfig
-- ----------------------------
INSERT INTO `weixin_expandconfig` VALUES ('402881e44667912a0146679390450001', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.FanYiKeyService', '翻译', '翻译', '翻译');
INSERT INTO `weixin_expandconfig` VALUES ('402881e44667912a014667947a3a0003', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.TianQiKeyService', '天气预报', '天气', '天气预报');
INSERT INTO `weixin_expandconfig` VALUES ('402881e446950f86014695120f810001', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.DaZhuanPanService', '大转盘', '大转盘', '大转盘');
INSERT INTO `weixin_expandconfig` VALUES ('402881e446950f8601469513ec670003', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.CmsService', 'CMS', 'cms', 'CMS,cms');
INSERT INTO `weixin_expandconfig` VALUES ('402881e4469ffe7a0146a000090d0001', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.GuagualeService', '刮刮乐', '刮刮乐', '刮刮乐');
INSERT INTO `weixin_expandconfig` VALUES ('402881e747baf1ea0147bb79d2330007', '402881e8461795c201461795c2e90000', 'weixin.idea.extend.function.impl.WeixinPhotoService', '微相册', '微相册,相册', '微相册');

-- ----------------------------
-- Table structure for weixin_hdrecord
-- ----------------------------
DROP TABLE IF EXISTS `weixin_hdrecord`;
CREATE TABLE `weixin_hdrecord` (
  `ID` varchar(100) NOT NULL,
  `ADDTIME` datetime default NULL,
  `HDID` varchar(100) default NULL,
  `NICKNAME` varchar(200) default NULL,
  `OPENDID` varchar(100) default NULL,
  `TOTAL` int(11) default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_hdrecord
-- ----------------------------
INSERT INTO `weixin_hdrecord` VALUES ('402881865907db7401590826e3d00030', '2016-12-16 23:01:23', '402881e6469a13b901469a1e9e420001', '', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '3', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_hdrecord` VALUES ('402881865907db7401590828ca6b0031', '2016-12-16 23:03:28', '402881e6469a13b901469a1e9e420001', '', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '1', null);
INSERT INTO `weixin_hdrecord` VALUES ('402881865907db74015908295ef70032', '2016-12-16 23:04:06', '402881e6469a13b901469a1e9e420001', '', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '1', null);

-- ----------------------------
-- Table structure for weixin_huodong
-- ----------------------------
DROP TABLE IF EXISTS `weixin_huodong`;
CREATE TABLE `weixin_huodong` (
  `id` varchar(100) NOT NULL COMMENT '键主',
  `title` varchar(400) default NULL COMMENT '活动名称',
  `description` text COMMENT '活动描述',
  `priceone` varchar(400) default NULL COMMENT '一等奖奖品',
  `onetotal` int(4) default NULL COMMENT '一等奖数量',
  `pricetwo` varchar(400) default NULL COMMENT '二等奖奖品',
  `twototal` int(4) default NULL COMMENT '二等奖数量',
  `pricethree` varchar(400) default NULL COMMENT '三等奖奖品',
  `threetotal` int(4) default NULL COMMENT '三等奖数量',
  `starttime` timestamp NULL default NULL COMMENT '开始时间',
  `endtime` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '结束时间',
  `type` varchar(100) default NULL COMMENT '活动类型',
  `gl` varchar(100) default NULL,
  `count` varchar(10) default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_huodong
-- ----------------------------
INSERT INTO `weixin_huodong` VALUES ('402880a9468a0bef01468a0e72840001', '国庆乐欢天', '活动期间参加抽奖，就有计划获得精美礼品一份！', '联想Thinkpad', '1', '苹果6', '1', '捷安特自行车', '5', '2014-06-10 04:32:00', '2018-07-28 02:59:51', '1', '90/100', '5', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_huodong` VALUES ('402881e6469a13b901469a1e9e420001', '微信大转盘摇奖', '活动期间参与摇奖，就送好礼！', '500万', '1', '200万', '2', '300万', '3', '2014-06-14 07:23:36', '2018-07-12 02:59:49', '2', '90/100', '3', '402881e8461795c201461795c2e90000');

-- ----------------------------
-- Table structure for weixin_linksucai
-- ----------------------------
DROP TABLE IF EXISTS `weixin_linksucai`;
CREATE TABLE `weixin_linksucai` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '修改人名称',
  `update_date` datetime default NULL COMMENT '修改日期',
  `name` varchar(100) default NULL COMMENT '链接名称',
  `outer_link` longtext COMMENT '外部链接',
  `content` longtext COMMENT '功能描述',
  `inner_link` longtext COMMENT '内部链接',
  `transfer_sign` int(11) default NULL COMMENT '转换标志',
  `accountid` varchar(100) default NULL COMMENT '微信账户id',
  `post_code` varchar(200) default NULL COMMENT '账号邮编',
  `share_status` varchar(10) default 'N' COMMENT '分享状态',
  `is_encrypt` int(2) default '0' COMMENT '是否加密（0：不加密，1：加密）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_linksucai
-- ----------------------------
INSERT INTO `weixin_linksucai` VALUES ('4028948151d769f20151d76c11e10002', 'admin', '2015-12-25 12:36:02', 'admin', '2018-03-14 18:01:39', '九宫格', 'http://www.h5huodong.com/jiugongge/toIndex.do?openid=${openid}&subscribe=${subscribe}&appid=${appid}', '九宫格', 'http://ileida.xicp.net/jeewx/weixinLinksucaiController.do?link&id=4028948151d769f20151d76c11e10002', null, '402881e8461795c201461795c2e90000', null, null, null);

-- ----------------------------
-- Table structure for weixin_long2short
-- ----------------------------
DROP TABLE IF EXISTS `weixin_long2short`;
CREATE TABLE `weixin_long2short` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '修改人名称',
  `update_date` datetime default NULL COMMENT '修改日期',
  `long_url` longtext COMMENT '长链接',
  `short_url` varchar(255) default NULL COMMENT '短链接',
  `account_id` varchar(50) default NULL COMMENT '公众号ID',
  `wx_name` varchar(32) default NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_long2short
-- ----------------------------
INSERT INTO `weixin_long2short` VALUES ('402881e86223f2de016223fbdc18000c', 'admin', '2018-03-14 18:08:51', 'admin', '2018-03-14 18:08:53', 'http://www.jeewx.com', 'https://w.url.cn/s/AqpptRa', null, 'JEEWX官网');

-- ----------------------------
-- Table structure for weixin_menuentity
-- ----------------------------
DROP TABLE IF EXISTS `weixin_menuentity`;
CREATE TABLE `weixin_menuentity` (
  `ID` varchar(32) NOT NULL,
  `menukey` varchar(255) default NULL,
  `msgtype` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `orders` varchar(11) default NULL,
  `templateid` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `url` varchar(2000) default NULL,
  `fatherid` varchar(32) default NULL,
  `accountid` varchar(255) default NULL,
  `appid` varchar(255) default NULL COMMENT 'appid',
  `pagepath` varchar(255) default NULL COMMENT 'pagepath',
  PRIMARY KEY  (`ID`),
  KEY `FK_astulwpsla864at9igbas3eic` (`fatherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_menuentity
-- ----------------------------
INSERT INTO `weixin_menuentity` VALUES ('402880f2590749060159074eb9ab0009', 'c05', 'expand', '微相册', '5', '402881e747baf1ea0147bb79d2330007', 'click', '', '402881e4464d026901464d05ab600001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402880f25907490601590757810f0019', 'd01', 'news', '触发图文', '1', '4028d881472f356e01472f36461b0001', 'click', '', '402889ff4728a293014728bc62250036', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402880f259074906015907593688001e', 'a01', 'text', '联系我们', '4', 'f7a8a3a946e55a940146e5c317f6005f', 'click', '', '402889ff4728f237014728f2d39f0001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402881e4464d026901464d05ab600001', 'c02', 'text', '微活动', '2', 'f7a8a3a946e249c20146e25c4dc7000c', 'click', null, null, '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402881e4464df45f01464df707760004', 'c0202', 'expand', '大转盘', '0', '402881e446950f86014695120f810001', 'click', 'http://123.165.130.173/jeecg-weixin-steward/webpage/weixin/guanjia/other/dazhuanpan/index.html', '402881e4464d026901464d05ab600001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402881e446a4f9400146a50183620008', 'c0203', 'expand', '微网站', '1', '402881e446950f8601469513ec670003', 'click', null, '402881e4464d026901464d05ab600001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402881e86223f2de016223f6536e0007', '', null, '小程序', '3', null, 'miniprogram', 'http://www.jeecg.org', '402889ff4728f237014728f2d39f0001', '402881e8461795c201461795c2e90000', 'wx313490c64bc53c00', 'pages/index/index');
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728a293014728bc62250036', 'c03', 'text', '触发功能', '3', 'f7a8a3a946e55a940146e5c317f6005f', 'click', '', null, '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728a293014728bcb3020038', 'c0302', 'text', '访问链接', '1', 'f7a8a3a946e55a940146e5c317f6005f', 'view', 'http://www.jeewx.com', '402889ff4728a293014728bc62250036', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728f237014728f2d39f0001', 'c01', null, '开源项目', '1', null, 'click', '', null, '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728f237014728f52c0b0008', 'c0102', null, 'Jeecg快速开发平台', '2', null, 'view', 'http://mp.weixin.qq.com/s?__biz=MjM5NjA2OTkxMg==&mid=200275454&idx=2&sn=a4a4bd237aff404b8044e59105b74c69#rd', '402889ff4728f237014728f2d39f0001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728f237014728f60f0f000b', 'c0301', 'text', '触发文本', '1', 'f7a8a3a946e55a940146e5cfa5660070', 'click', 'http://mp.weixin.qq.com/s?__biz=MjM5NjA2OTkxMg==&mid=200211928&idx=1&sn=18baf4ad4076f2f42c7db938c1277b08#rd', '402889ff4728a293014728bc62250036', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728f237014729029d940010', 'c0204', 'expand', '刮刮乐', '1', '402881e4469ffe7a0146a000090d0001', 'click', null, '402881e4464d026901464d05ab600001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('402889ff4728f237014729039c7c0014', 'c0201', 'text', '营销工具', '', 'f7a8a3a946e55a940146e5cfa5660070', 'click', null, '402881e4464d026901464d05ab600001', '402881e8461795c201461795c2e90000', null, null);
INSERT INTO `weixin_menuentity` VALUES ('40289481515d5e1201515d6009020005', 'jeewx', null, 'Jeewx微信管家', '1', null, 'view', 'http://mp.weixin.qq.com/s?__biz=MjM5NjA2OTkxMg==&mid=201250688&idx=4&sn=6aa38b2e0a3cd1bc770e6fca732cf04f#rd', '402889ff4728f237014728f2d39f0001', '402881e8461795c201461795c2e90000', null, null);

-- ----------------------------
-- Table structure for weixin_newsitem
-- ----------------------------
DROP TABLE IF EXISTS `weixin_newsitem`;
CREATE TABLE `weixin_newsitem` (
  `ID` varchar(32) NOT NULL,
  `new_type` varchar(255) default NULL COMMENT '图文类型：图文还是外部url',
  `author` varchar(255) default NULL,
  `content` text,
  `description` varchar(255) default NULL,
  `imagepath` varchar(255) default NULL,
  `orders` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `templateid` varchar(32) default NULL,
  `url` varchar(255) default NULL COMMENT '外部URL',
  `create_date` date default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_m8qs8ufeqkk5cx17budto66r0` (`templateid`),
  CONSTRAINT `FK_m8qs8ufeqkk5cx17budto66r0` FOREIGN KEY (`templateid`) REFERENCES `weixin_newstemplate` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_newsitem
-- ----------------------------
INSERT INTO `weixin_newsitem` VALUES ('402881865907db7401590806c526000d', null, '33', '<p><img src=\"http://localhost/jeewx/plug-in/ueditor/jsp/upload1/20161216/4211481898373334.jpg\" title=\"4afbfbedab64034f9015f1bca8c379310b551dab.jpg\"/></p>', '33', 'upload/files/20161216222607YvbDnX3S.jpg', '1', '333', '4028d881472f356e01472f36461b0001', '', '2016-12-16', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_newsitem` VALUES ('40289481515cf0fe01515cfb24c5001b', null, 'jeecg', '<p>JEEWX介绍</p>', 'Jeewx商业版本介绍', 'upload/files/20151201175834RttNvMaW.jpg', '2', 'Jeewx商业版本介绍', '4028d881472f356e01472f36461b0001', 'http://mp.weixin.qq.com/s?__biz=MjM5NjA2OTkxMg==&mid=201250688&idx=4&sn=6aa38b2e0a3cd1bc770e6fca732cf04f#rd', '2015-12-01', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_newsitem` VALUES ('4028d881472f356e01472f36d0a00003', null, 'Jeecg', '<h2 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 24px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(204, 204, 204); white-space: normal; background-color: rgb(250, 250, 250);\">一、简介</h2><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 1.33; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">jeewx是一个开源，高效，敏捷的微信开发平台采用JAVA语言，它是基于jeecg这个企业级快速开发框架实现的。 jeewx的目的是最大化的简化微信开发的流程，使用开发者能把最好的精力放到微信具体业务开发，并能以最快的时间完成。把一些常规而频繁的工作交由jeewx来处理即可，平台兼备的代码生成器，在线开发，可以快速的完成企业应用。为此jeewx提供了详细的二次开发文档，关键代码里还是相关的注释说明。jeewx采用插件的方式实现微信功能，不同的插件实现不同的微信功能。</p><h2 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 24px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(204, 204, 204); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"主要特性\" href=\"http://git.oschina.net/jeecg/jeewx#主要特性\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; background-position: initial initial; background-repeat: initial initial;\"></a>主要特性</h2><ul class=\"task-list list-paddingleft-2\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin-bottom: 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li><p>1、基于快速开发平台jeecg 3.4.4最新版本，采用SpringMVC+Hibernate4+UI库+代码生成器+Jquery+Ehcache等主流架构技术</p></li><li><p>2、支持企业快速开发，完善的用户组织机构，报表，强大的代码生成器快速有效的提高开发效率</p></li><li><p>3、开源免费，jeewx遵循Apache2开源协议,免费提供使用。</p></li><li><p>4、支持多用户多公众号管理</p></li><li><p>5、详细的二次开发文档，并不断更新增加相关开发案例提供学习参考</p></li><li><p>6、微信功能插件化开发，更易于定制和二次开发</p></li><li><p>7、提供丰富的微信插件下载安装使用，总有一些是符合或接近你的需求</p></li></ul><h2 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 24px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(204, 204, 204); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"主要功能\" href=\"http://git.oschina.net/jeecg/jeewx#主要功能\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; background-position: initial initial; background-repeat: initial initial;\"></a>主要功能</h2><ul class=\"task-list list-paddingleft-2\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin-bottom: 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, &#39;Microsoft YaHei&#39;, Arial, Helvetica, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li><p>1，微信接口认证</p></li><li><p>2，菜单自定义</p></li><li><p>3，文本管理和回复</p></li><li><p>4，关注欢迎语</p></li><li><p>5，关键字管理</p></li><li><p>6，文本模板管理</p></li><li><p>7，图文模板管理</p></li><li><p>8，账号管理</p></li><li><p>9，用户管理</p></li><li><p>10，角色管理</p></li><li><p>11，菜单管理</p></li><li><p>12, 微信支持多用户多公众号</p></li><li><p>13，微信大转盘</p></li><li><p>14，微信刮刮乐</p></li><li><p>15，微信CMS</p></li><li><p>16，自定义接口回复</p></li><li><p>17，翻译</p></li><li><p>18，天气 更多功能参考官方：www.jeecg.org</p></li></ul><p><br/></p>', 'Jeewx管家介绍', 'upload/files/20151201175716Q7tIXj7S.jpg', '1', 'Jeewx管家介绍', '4028d881472f356e01472f36461b0001', '', '2014-07-13', '402881e8461795c201461795c2e90000');

-- ----------------------------
-- Table structure for weixin_newstemplate
-- ----------------------------
DROP TABLE IF EXISTS `weixin_newstemplate`;
CREATE TABLE `weixin_newstemplate` (
  `ID` varchar(32) NOT NULL,
  `addtime` varchar(255) default NULL,
  `tempatename` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `accountid` varchar(100) default NULL,
  `mediaid` varchar(100) default NULL COMMENT 'mediaid',
  `isup` varchar(3) default NULL COMMENT '是否上传',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_newstemplate
-- ----------------------------
INSERT INTO `weixin_newstemplate` VALUES ('4028d881472f356e01472f36461b0001', '2018-03-14 18:08:25', 'jeecg图文', 'common', '402881e8461795c201461795c2e90000', 'H_uPGqjRnC8O9yXEwfd7eK8SM95My-Ma0PqMG_bArkI', '1');

-- ----------------------------
-- Table structure for weixin_open_account
-- ----------------------------
DROP TABLE IF EXISTS `weixin_open_account`;
CREATE TABLE `weixin_open_account` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `appid` varchar(200) default NULL,
  `ticket` varchar(200) default NULL COMMENT '第三方平台推送 : ticket',
  `get_ticket_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_open_account
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_photo
-- ----------------------------
DROP TABLE IF EXISTS `weixin_photo`;
CREATE TABLE `weixin_photo` (
  `ID` varchar(32) NOT NULL,
  `PHOTO_ALBUM_ID` varchar(32) default NULL COMMENT '对应相册',
  `CONTENT` varchar(255) default NULL COMMENT '相片描述',
  `CREATE_BY` varchar(32) default NULL,
  `CREATE_DATE` datetime default NULL,
  `NAME` varchar(100) default NULL COMMENT '相片名字',
  `UPDATE_BY` varchar(32) default NULL,
  `UPDATE_DATE` datetime default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_photo
-- ----------------------------
INSERT INTO `weixin_photo` VALUES ('402880f2590749060159075431330013', '402880f25907490601590753f98c000f', null, '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:15', '未命名', '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:15');
INSERT INTO `weixin_photo` VALUES ('402880f25907490601590754343a0014', '402880f25907490601590753f98c000f', null, '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:16', '未命名', '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:16');
INSERT INTO `weixin_photo` VALUES ('402880f2590749060159075436400015', '402880f25907490601590753f98c000f', null, '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:16', '未命名', '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:16');
INSERT INTO `weixin_photo` VALUES ('402880f2590749060159075438570016', '402880f25907490601590753f98c000f', null, '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:17', '未命名', '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:17');
INSERT INTO `weixin_photo` VALUES ('4028948151dc770f0151dc7e8c5a0005', '4028948151dc770f0151dc7d6f0f0001', null, '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:19', '未命名', '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:19');
INSERT INTO `weixin_photo` VALUES ('4028948151dc770f0151dc7e8d050006', '4028948151dc770f0151dc7d6f0f0001', null, '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:19', '未命名', '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:19');
INSERT INTO `weixin_photo` VALUES ('4028948151dc770f0151dc7e8d7e0007', '4028948151dc770f0151dc7d6f0f0001', null, '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20', '未命名', '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20');
INSERT INTO `weixin_photo` VALUES ('4028948151dc770f0151dc7e8e020008', '4028948151dc770f0151dc7d6f0f0001', null, '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20', '未命名', '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20');
INSERT INTO `weixin_photo` VALUES ('4028948151dc770f0151dc7e8e8b0009', '4028948151dc770f0151dc7d6f0f0001', null, '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20', '未命名', '4028d881436d514601436d5215ac0043', '2015-12-26 12:14:20');

-- ----------------------------
-- Table structure for weixin_photo_album
-- ----------------------------
DROP TABLE IF EXISTS `weixin_photo_album`;
CREATE TABLE `weixin_photo_album` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) default NULL COMMENT '相册名称',
  `CONTENT` varchar(255) default NULL COMMENT '相册描述',
  `ACCOUNTID` varchar(100) default NULL,
  `CREATE_DATE` datetime default NULL COMMENT '创建时间',
  `CREATE_BY` varchar(32) default NULL COMMENT '创建人',
  `UPDATE_DATE` datetime default NULL COMMENT '修改时间',
  `UPDATE_BY` varchar(32) default NULL COMMENT '修改人',
  `PHOTO_ID` varchar(32) default NULL COMMENT '封面相片',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_photo_album
-- ----------------------------
INSERT INTO `weixin_photo_album` VALUES ('402880f25907490601590753f98c000f', '相册2', '相册2', '402881e8461795c201461795c2e90000', '2016-12-16 19:11:01', '4028d881436d514601436d5215ac0043', '2016-12-16 19:11:36', '4028d881436d514601436d5215ac0043', '402880f2590749060159075438570016');
INSERT INTO `weixin_photo_album` VALUES ('4028948151dc770f0151dc7d6f0f0001', '相册one', '相册one', '402881e8461795c201461795c2e90000', '2015-12-26 12:13:06', '4028d881436d514601436d5215ac0043', '2016-12-16 19:10:44', '4028d881436d514601436d5215ac0043', '4028948151dc770f0151dc7e8e020008');

-- ----------------------------
-- Table structure for weixin_prizerecord
-- ----------------------------
DROP TABLE IF EXISTS `weixin_prizerecord`;
CREATE TABLE `weixin_prizerecord` (
  `ID` varchar(100) NOT NULL,
  `ADDTIME` datetime default NULL,
  `HDID` varchar(100) default NULL,
  `MOBILE` varchar(100) default NULL,
  `openid` varchar(255) default NULL,
  `PRIZE` varchar(100) default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_prizerecord
-- ----------------------------
INSERT INTO `weixin_prizerecord` VALUES ('402881865907db740159082a0e690033', '2016-12-16 23:04:51', '402881e6469a13b901469a1e9e420001', '18677877878', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '2', '402881e8461795c201461795c2e90000');

-- ----------------------------
-- Table structure for weixin_receivetext
-- ----------------------------
DROP TABLE IF EXISTS `weixin_receivetext`;
CREATE TABLE `weixin_receivetext` (
  `ID` varchar(32) NOT NULL,
  `content` varchar(255) default NULL,
  `createtime` datetime default NULL,
  `fromusername` varchar(255) default NULL,
  `msgid` varchar(255) default NULL,
  `msgtype` varchar(255) default NULL,
  `rescontent` varchar(255) default NULL,
  `response` varchar(255) default NULL,
  `tousername` varchar(255) default NULL,
  `accountid` varchar(100) default NULL,
  `nickname` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_receivetext
-- ----------------------------
INSERT INTO `weixin_receivetext` VALUES ('402880f2590749060159074b49580004', '01', '2016-12-16 19:01:31', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '6364652314832334913', 'text', null, '0', 'gh_20419b74f848', 'gh_20419b74f848', null);
INSERT INTO `weixin_receivetext` VALUES ('402880f2590749060159074be5b40006', '你好', '2016-12-16 19:02:11', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '6364652486631026762', 'text', null, '0', 'gh_20419b74f848', 'gh_20419b74f848', null);
INSERT INTO `weixin_receivetext` VALUES ('402880f2590749060159074d69490007', '大转盘', '2016-12-16 19:03:50', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '6364652911832789078', 'text', null, '0', 'gh_20419b74f848', 'gh_20419b74f848', null);
INSERT INTO `weixin_receivetext` VALUES ('402880f3591b2a3001591b32e7c50001', '你好', '2016-12-20 15:47:18', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '6366086537556470254', 'text', null, '0', 'gh_20419b74f848', 'gh_20419b74f848', null);
INSERT INTO `weixin_receivetext` VALUES ('402880f3591bc49901591bc76e220001', '你好', '2016-12-20 18:29:31', 'o94BGv4wkjYm7GV6tnh1IHC_F0Z4', '6366128383422835970', 'text', null, '0', 'gh_20419b74f848', 'gh_20419b74f848', null);
INSERT INTO `weixin_receivetext` VALUES ('402881e86223f2de016223fd02410010', '你好', '2018-03-14 18:10:06', 'on8V9jg-se_b0JgU5Q3V23pgU0wQ', '1234567890123456', 'text', '欢迎关注jeewx！', '1', 'gh_6f548a0d82d4', 'gh_6f548a0d82d4', null);

-- ----------------------------
-- Table structure for weixin_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `weixin_subscribe`;
CREATE TABLE `weixin_subscribe` (
  `ID` varchar(32) NOT NULL,
  `accountid` varchar(255) default NULL,
  `addTime` varchar(255) default NULL,
  `msgType` varchar(255) default NULL,
  `templateId` varchar(255) default NULL,
  `templateName` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_subscribe
-- ----------------------------
INSERT INTO `weixin_subscribe` VALUES ('402889ff472978290147297b54350004', '402881e8461795c201461795c2e90000', '2014-07-12 15:30:54', 'text', 'f7a8a3a946e249c20146e25c4dc7000c', '欢迎关注语');

-- ----------------------------
-- Table structure for weixin_texttemplate
-- ----------------------------
DROP TABLE IF EXISTS `weixin_texttemplate`;
CREATE TABLE `weixin_texttemplate` (
  `ID` varchar(32) NOT NULL,
  `addtime` varchar(255) default NULL,
  `content` varchar(255) default NULL,
  `templatename` varchar(255) default NULL,
  `accountid` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_texttemplate
-- ----------------------------
INSERT INTO `weixin_texttemplate` VALUES ('4028d8814734ee0d0147356b4c730010', '2014-07-14 23:08:50', '你好，我是Jeewx捷微管家系统，如果想了解更多功能，请关注公众号“jeecg”', '你好', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e249c20146e25c4dc7000c', '2014-06-28 20:03:58', ' 欢迎您关注JEEWX管家，捷微是一款基于JAVA语言的，多触点管理系统，支持微信公众号、微信企业号、支付服务窗，同时代码完全开源，方便二次开发。程序自身提供部分行业解决方案及丰富的营销功能。更多演示功能请查看下方菜单。\r\n官网：www.jeewx.com\r\nQQ群：289709451\r\n论坛：www.jeecg.org', '欢迎关注语', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5a3e498004d', '2014-06-29 11:21:01', '微译使用指南\r\n\r\n微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：\r\n    中 -> 英\r\n    英 -> 中\r\n    日 -> 中\r\n\r\n使用示例：\r\n    翻译我是中国人\r\n    翻译dream\r\n    翻译さようなら\r\n\r\n回复“?”显示主菜单', '微译使用指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5a429cf004e', '2014-06-29 11:21:19', '发送天气+城市，例如\'天气广州\'\r\n', '天气使用指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5a53e6b0051', '2014-06-29 11:22:30', '请输入\"大转盘\"，参与抽奖活动', '大转盘指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5a584da0052', '2014-06-29 11:22:48', '请输入\"刮刮乐\"，参与抽奖活动', '刮刮乐指南', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5c317f6005f', '2014-06-29 11:55:06', '官方网站：www.jeewx.com\r\n商务购买联系QQ: 418799587', '联系我们', '402881e8461795c201461795c2e90000');
INSERT INTO `weixin_texttemplate` VALUES ('f7a8a3a946e55a940146e5cfa5660070', '2014-06-29 12:08:49', '01  翻译\r\n02  天气\r\n03  大转盘\r\n04  刮刮乐', '营销工具', '402881e8461795c201461795c2e90000');
