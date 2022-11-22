package com.ssafy.vue.model;

import java.util.Arrays;

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
	private int checked;
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
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "TodoDto [todono=" + todono + ", userid=" + userid + ", content=" + content + ", checked=" + checked
				+ "]";
	}
	
	
	
	
}
