package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.NotificationDto;
import com.ssafy.vue.model.NotificationParameterDto;
import com.ssafy.vue.model.service.NotificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/notification")
@Api("게시판 컨트롤러  API V1")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NotificationService NotificationService;

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNotification(@RequestBody @ApiParam(value = "게시글 정보.", required = true) NotificationDto NotificationDto) throws Exception {
		logger.info("writenotification - 호출");
		if (NotificationService.writeNotification(NotificationDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NotificationDto>> listNotification(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) NotificationParameterDto NotificationParameterDto) throws Exception {
		logger.info("listnotification - 호출");
		return new ResponseEntity<List<NotificationDto>>(NotificationService.listNotification(NotificationParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NotificationDto.class)
	@GetMapping("/{notificationno}")
	public ResponseEntity<NotificationDto> getNotification(@PathVariable("notificationno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int notificationno) throws Exception {
		logger.info("getnotification - 호출 : " + notificationno);
		NotificationService.updateHit(notificationno);
		return new ResponseEntity<NotificationDto>(NotificationService.getNotification(notificationno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyNotification(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NotificationDto NotificationDto) throws Exception {
		logger.info("modifynotification - 호출 {}", NotificationDto);
		
		if (NotificationService.modifyNotification(NotificationDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{notificationno}")
	public ResponseEntity<String> deleteNotification(@PathVariable("Notificationno") @ApiParam(value = "살제할 글의 글번호.", required = true) int notificationno) throws Exception {
		logger.info("deletenotification - 호출");
		if (NotificationService.deleteNotification(notificationno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 전체 글 수", notes = "글의 수를 반환한다.", response = int.class)
    @GetMapping("/total")
    // 리스트 수를 보내주기 위한 메소드
    public int getTotalCount(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) NotificationParameterDto NotificationParameterDto) throws Exception {
        logger.info("totlacount - 호출:   "+NotificationService.getTotalCount(NotificationParameterDto));
        return NotificationService.getTotalCount(NotificationParameterDto);
    }
}