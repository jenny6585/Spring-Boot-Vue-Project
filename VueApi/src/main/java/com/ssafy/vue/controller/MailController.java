package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.MailDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/email")
@Api("이메일 컨트롤러  API V1")
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MailService mailService;

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeMail(@RequestBody @ApiParam(value = "이메일 정보.", required = true) MailDto mailDto) throws Exception {
		logger.info("writeMail - 호출");
		
		try {
			boolean check = mailService.writeMail(mailDto);
			if(check)return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		
	}
	
}
	
	