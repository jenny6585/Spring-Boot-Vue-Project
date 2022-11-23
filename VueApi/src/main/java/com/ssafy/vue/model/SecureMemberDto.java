package com.ssafy.vue.model;



public class SecureMemberDto {
	private String id, salt, seckey;

	
	
	public SecureMemberDto(String id, String salt, String seckey) {
		setId(id);
		setSalt(salt);
		setSeckey(seckey);
	}

	public SecureMemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSeckey() {
		return seckey;
	}

	public void setSeckey(String seckey) {
		this.seckey = seckey;
	}

	@Override
	public String toString() {
		return "SecureMemberDto [id=" + id + ", salt=" + salt + ", seckey=" + seckey + "]";
	}

	
	
	
}
