package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MailDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class MailDto {
	@ApiModelProperty(value = "보내는 이메일")
	private String sendMail;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "아이디")
	private String userid;
	public String getSendMail() {
		return sendMail;
	}
	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "MailDto [sendMail=" + sendMail + ", content=" + content + ", userid=" + userid + "]";
	}
	
	
	

}