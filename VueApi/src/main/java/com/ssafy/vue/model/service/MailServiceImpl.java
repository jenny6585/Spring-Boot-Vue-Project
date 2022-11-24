package com.ssafy.vue.model.service;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MailDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MailMapper;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeMail(MailDto mailDto) throws Exception {
		String to = mailDto.getSendMail();

		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setTo(to);
			simpleMessage.setSubject("[메일 인증] BikeMap에서 초대장을 보냈습니다.");
			simpleMessage.setText("[초대장]\n" + mailDto.getContent());
			System.out.println("메일 발송");

			javaMailSender.send(simpleMessage);
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean findId(String userid) throws Exception {
		return sqlSession.getMapper(MailMapper.class).findId(userid)==1;
	}

}
