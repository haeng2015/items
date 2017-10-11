/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : pbms

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-05-07 20:17:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bo_auth
-- ----------------------------
DROP TABLE IF EXISTS `bo_auth`;
CREATE TABLE `bo_auth` (
  `AUTH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTH_NAME` varchar(32) DEFAULT NULL,
  `AUTH_URL` varchar(200) DEFAULT NULL,
  `AUTH_TYPE` char(2) DEFAULT NULL,
  `P_ID` int(11) DEFAULT NULL COMMENT '父主键id',
  `IS_LEAFNODE` char(2) DEFAULT NULL COMMENT '是否叶子节点(0否，1是)',
  `EXTEND1` varchar(200) DEFAULT NULL,
  `EXTEND2` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`AUTH_ID`),
  KEY `P_ID` (`P_ID`),
  CONSTRAINT `bo_auth_ibfk_1` FOREIGN KEY (`P_ID`) REFERENCES `bo_auth` (`AUTH_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_auth
-- ----------------------------
INSERT INTO `bo_auth` VALUES ('9', '查看用户列表', '/user/toUserList.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('10', '加载用户列表', '/user/loadUserList.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('11', '编辑用户信息', '/user/editUser.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('12', '保存用户信息', '/user/saveOrUpdateUser.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('13', '检查用户登录名', '/user/checkUserLoginName.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('14', '删除用户', '/user/deleteUser.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('15', '编辑用户角色', '/user/editUserRole.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('16', '保存用户角色', '/user/saveUserRole.do', '4', '11101', '1', null, null);
INSERT INTO `bo_auth` VALUES ('17', '加载角色列表', '/user/loadRoleList.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('18', '编辑角色', '/user/toEditRole.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('19', '保存角色', '/user/addUpdateRole.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('20', '删除角色', '/user/deleteRole.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('21', '编辑角色权限', '/user/toRoleAuth.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('22', '保存角色权限', '/user/addAuthForRole.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('23', '查看角色列表', '/user/toRoleList.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('24', '加载权限树', '/user/loadAuthTree.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('25', '加载角色权限', '/user/getRoleAuthByRole.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('26', '保存角色权限', '/user/saveOrUpdateRoleAuth.do', '4', '11102', '1', null, null);
INSERT INTO `bo_auth` VALUES ('27', '保存楼栋', '/build/saveOrUpdateBuilding.do', '4', '11103', '1', null, null);
INSERT INTO `bo_auth` VALUES ('28', '查看楼栋信息', '/build/toBuildingList.do', '4', '11104', '1', null, null);
INSERT INTO `bo_auth` VALUES ('29', '加载楼栋信息', '/build/loadBuildingList.do', '4', '11104', '1', null, null);
INSERT INTO `bo_auth` VALUES ('30', '删除楼栋信息', '/build/deleteBuilding.do', '4', '11104', '1', null, null);
INSERT INTO `bo_auth` VALUES ('31', '下载楼栋附件', '/build/uploadAttachmentForBuild.do', '4', '11104', '1', null, null);
INSERT INTO `bo_auth` VALUES ('32', '删除楼栋附件', '/build/deleteAttachmentForBuild.do', '4', '11104', '1', null, null);
INSERT INTO `bo_auth` VALUES ('34', '保存房间信息', '/room/saveOrUpdateRoom.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('35', '查看房间信息', '/room/toRoomList.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('36', '加载房间信息', '/room/loadRoomingList.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('37', '删除房间信息', '/room/deleteRoom.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('38', '下载房间附件', '/room/uploadAttachmentForRoom.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('39', '删除房间附件', '/room/deleteAttachmentForRoom.do', '4', '11105', '1', null, null);
INSERT INTO `bo_auth` VALUES ('10000', '超级管理中心', null, '1', null, '0', null, null);
INSERT INTO `bo_auth` VALUES ('11001', '管理员模块', null, '2', '10000', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11002', '楼栋管理模块', null, '2', '10000', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11003', '房间管理模块', null, '2', '10000', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11101', '用户管理', null, '3', '11001', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11102', '角色管理', null, '3', '11001', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11103', '增加楼栋', '/build/editBuilding.do', '3', '11002', '1', null, null);
INSERT INTO `bo_auth` VALUES ('11104', '查询楼栋', '', '3', '11002', '0', null, null);
INSERT INTO `bo_auth` VALUES ('11105', '增加房间', '/room/editRoom.do', '3', '11003', '1', null, null);
INSERT INTO `bo_auth` VALUES ('11106', '查询房间', '', '3', '11003', '0', null, null);
