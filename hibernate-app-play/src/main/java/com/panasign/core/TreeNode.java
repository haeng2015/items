package com.panasign.core;

import java.util.List;

/**
 * easyui中树的节点格式实体类
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-15
 */
public class TreeNode {
	private String id;	// which is important to load remote data
	private String text;	//node text to show
	private String state="open";	//node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
	private boolean checked=false;	//: Indicate whether the node is checked selected.
	private Object attributes;	// custom attributes can be added to a node
	private List<TreeNode> children;// = new ArrayList<TreeNode>();	// an array nodes defines some children nodes
	private String iconCls;	//="icon-typeParent";
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}
