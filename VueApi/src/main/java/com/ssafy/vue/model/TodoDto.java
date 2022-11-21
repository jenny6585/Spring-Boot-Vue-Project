package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "TodoDto : Todo정보", description = "Todo의 상세 정보를 나타낸다.")
public class TodoDto {
	@ApiModelProperty(value= "Todo번호")
	private int todono;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	@ApiModelProperty(value = "Todo내용")
	private String content;
	@ApiModelProperty(value = "Todo상태")
	private boolean status;
	public int getTodono() {
		return todono;
	}
	public void setTodono(int todono) {
		this.todono = todono;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TodoDto [todono=" + todono + ", userid=" + userid + ", content=" + content + ", status=" + status + "]";
	}
	
	
}
