package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MailDto;

public interface MailService {
	public boolean writeMail(MailDto mailDto) throws Exception;
	public boolean findId(String userid) throws Exception;
}
