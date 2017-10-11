package com.hibernate._11pk;

public class Boy_11pk {

	private int bId;
	private String bName;
	private int bAge;
	
	public Boy_11pk() {
		super();
	}
	public Boy_11pk(int bId) {
		super();
		this.bId = bId;
	}
	public Boy_11pk(String bName, int bAge) {
		super();
		this.bName = bName;
		this.bAge = bAge;
	}
	public Boy_11pk(int bId, String bName, int bAge) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bAge = bAge;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public int getbAge() {
		return bAge;
	}
	public void setbAge(int bAge) {
		this.bAge = bAge;
	}
	
}
