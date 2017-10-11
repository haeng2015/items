package com.pbms.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年4月30日
 * @说明：树结构专用vo类（easyui树结构插件）
 */
public class TreeNode {
    
    private Integer id; // 显示节点的id
    private String text; // node text to show
    private String state = "open"; // node state, 'open' or 'closed', default is
				   // 'open'. When set to 'closed', the node
				   // have children nodes and will load them
				   // from remote site
    private boolean checked = false; // : Indicate whether the node is checked
				     // selected.
    private Object attributes; // custom attributes can be added to a node
    private Integer parentId; // 显示节点的父节点
    private List<TreeNode> children;// 显示节点的子节点集合
    private String iconCls; // 显示节点的图标
    
    public Integer getId() {
	return id;
    }
    
    public void setId(Integer id) {
	this.id = id;
    }
    
    public String getText() {
	return text;
    }
    
    public void setText(String text) {
	this.text = text;
    }
    
    public String getState() {
	return state;
    }
    
    public void setState(String state) {
	this.state = state;
    }
    
    public boolean isChecked() {
	return checked;
    }
    
    public void setChecked(boolean checked) {
	this.checked = checked;
    }
    
    public Object getAttributes() {
	return attributes;
    }
    
    public void setAttributes(Object attributes) {
	this.attributes = attributes;
    }
    
    public Integer getParentId() {
	return parentId;
    }
    
    public void setParentId(Integer parentId) {
	this.parentId = parentId;
    }
    
    public List<TreeNode> getChildren() {
	return children;
    }
    
    public void setChildren(List<TreeNode> children) {
	this.children = children;
    }
    
    public String getIconCls() {
	return iconCls;
    }
    
    public void setIconCls(String iconCls) {
	this.iconCls = iconCls;
    }
    
    /**
     * 添加子节点的方法
     * 
     * @param node
     *            树节点实体
     */
    public void addChild(TreeNode node) {
	if (this.children == null) {
	    children = new ArrayList<TreeNode>();
	    children.add(node);
	} else {
	    children.add(node);
	}
    }
}
