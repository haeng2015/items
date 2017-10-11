/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : pbms

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-05-07 20:17:32
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


-- 删除数据(删除顺序)
	delete from bo_auth where auth_type = '1';
		delete from bo_auth where auth_type = '2';
			delete from bo_auth where auth_type = '3';
				delete from bo_auth where auth_type = '4';


-- ----------------------------
-- Records of bo_auth
-- ----------------------------
INSERT INTO `bo_auth` VALUES ('10000', '超级管理中心', null, '1', null, '0', null, null);
-- 管理员模块
	INSERT INTO `bo_auth` VALUES ('11001', '管理员模块', null, '2', '10000', '0', null, null);
		INSERT INTO `bo_auth` VALUES ('11101', '用户管理', null, '3', '11001', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('110', '查看用户列表', '/user/toUserList.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('100', '加载用户列表', '/user/loadUserList.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('101', '编辑用户信息', '/user/editUser.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('102', '保存用户信息', '/user/saveOrUpdateUser.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('103', '检查用户登录名', '/user/checkUserLoginName.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('104', '删除用户', '/user/deleteUser.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('105', '编辑用户角色', '/user/editUserRole.do', '4', '11101', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('106', '保存用户角色', '/user/saveUserRole.do', '4', '11101', '1', null, null);
			
		INSERT INTO `bo_auth` VALUES ('11102', '角色管理', null, '3', '11001', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('107', '加载角色列表', '/user/loadRoleList.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('108', '编辑角色', '/user/toEditRole.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('109', '保存角色', '/user/addUpdateRole.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('111', '删除角色', '/user/deleteRole.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('112', '编辑角色权限', '/user/toRoleAuth.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('113', '保存角色权限', '/user/addAuthForRole.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('114', '查看角色列表', '/user/toRoleList.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('115', '加载角色权限', '/user/getRoleAuthByRole.do', '4', '11102', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('116', '保存角色权限', '/user/saveOrUpdateRoleAuth.do', '4', '11102', '1', null, null);
		INSERT INTO `bo_auth` VALUES ('11103', '权限管理', null, '3', '11001', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('117', '查看权限列表', '/user/toAuthList.do', '4', '11103', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('118', '加载权限树', '/user/loadAuthTree.do', '4', '11103', '1', null, null);
			
		
-- 楼栋管理模块		
	INSERT INTO `bo_auth` VALUES ('11002', '楼栋管理模块', null, '2', '10000', '0', null, null);
		INSERT INTO `bo_auth` VALUES ('11104', '增加楼栋', '/build/editBuilding.do', '3', '11002', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('200', '保存楼栋', '/build/saveOrUpdateBuilding.do', '4', '11104', '1', null, null);
			
		INSERT INTO `bo_auth` VALUES ('11105', '查询楼栋', '', '3', '11002', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('201', '查看楼栋信息', '/build/toBuildingList.do', '4', '11105', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('202', '加载楼栋信息', '/build/loadBuildingList.do', '4', '11105', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('203', '删除楼栋信息', '/build/deleteBuilding.do', '4', '11105', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('204', '下载楼栋附件', '/build/uploadAttachmentForBuild.do', '4', '11105', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('205', '删除楼栋附件', '/build/deleteAttachmentForBuild.do', '4', '11105', '1', null, null);
	
	
-- 房间管理模块
	INSERT INTO `bo_auth` VALUES ('11003', '房间管理模块', null, '2', '10000', '0', null, null);
		INSERT INTO `bo_auth` VALUES ('11106', '增加房间', '/room/editRoom.do', '3', '11003', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('300', '保存房间信息', '/room/saveOrUpdateRoom.do', '4', '11106', '1', null, null);
		
		INSERT INTO `bo_auth` VALUES ('11107', '查询房间', '', '3', '11003', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('301', '查看房间信息', '/room/toRoomList.do', '4', '11107', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('302', '加载房间信息', '/room/loadRoomingList.do', '4', '11107', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('303', '删除房间信息', '/room/deleteRoom.do', '4', '11107', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('304', '下载房间附件', '/room/uploadAttachmentForRoom.do', '4', '11107', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('305', '删除房间附件', '/room/deleteAttachmentForRoom.do', '4', '11107', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('306', '通过楼栋获得房间', '/room/getRoomsByBuild.do', '4', '11107', '1', null, null);
			
	
-- 业主管理模块		
	INSERT INTO `bo_auth` VALUES ('11004', '业主管理模块', null, '2', '10000', '0', null, null);	
		INSERT INTO `bo_auth` VALUES ('11108', '增加业主', '/owner/editOwner.do', '3', '11004', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('400', '保存业主信息', '/owner/saveOrUpdateOwner.do', '4', '11108', '1', null, null);
	
		INSERT INTO `bo_auth` VALUES ('11109', '查询业主', '', '3', '11004', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('401', '查看业主信息', '/owner/toOwnerList.do', '4', '11109', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('402', '加载业主信息', '/owner/loadOwnerList.do', '4', '11109', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('403', '删除业主信息', '/owner/deleteOwner.do', '4', '11109', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('404', '下载业主附件', '/owner/uploadAttachmentForOwner.do', '4', '11109', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('405', '删除业主附件', '/owner/deleteAttachmentForOwner.do', '4', '11109', '1', null, null);
			
	
-- 收费项目管理模块		
	INSERT INTO `bo_auth` VALUES ('11005', '收费项目模块', null, '2', '10000', '0', null, null);	
		INSERT INTO `bo_auth` VALUES ('11110', '增加项目', '/charge/editCharge.do', '3', '11005', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('500', '保存项目信息', '/charge/saveOrUpdateCharge.do', '4', '11110', '1', null, null);
	
		INSERT INTO `bo_auth` VALUES ('11111', '查询项目', '', '3', '11004', '0', null, null);
			INSERT INTO `bo_auth` VALUES ('501', '查看项目信息', '/charge/toChargeList.do', '4', '11111', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('502', '加载项目信息', '/charge/loadChargeList.do', '4', '11111', '1', null, null);
			INSERT INTO `bo_auth` VALUES ('503', '删除项目信息', '/charge/deleteCharge.do', '4', '11111', '1', null, null);



		
			
			
