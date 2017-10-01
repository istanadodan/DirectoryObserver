package com.file.observer.vo;

public class ContentsVO {
	int id;
	String name;
	Object parentId;
	int depth;
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getParentId() {
		return parentId;
	}
	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}
	
}
