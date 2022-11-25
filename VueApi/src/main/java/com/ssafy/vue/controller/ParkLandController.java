package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.LandDto;
import com.ssafy.vue.model.ParkDto;
import com.ssafy.vue.model.service.LandService;
import com.ssafy.vue.model.service.ParkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/park")
@Api("명소 컨트롤러  API V1")
public class ParkLandController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private LandService landService;
	@Autowired
	private ParkService parkService;
	
	@ApiOperation(value = "공원 위치목록", notes = "모든 공원 정보들을 리스트 형으로 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<ParkDto>> listPark() throws Exception {
		logger.info("listPark - 호출");
		return new ResponseEntity<List<ParkDto>>(parkService.listPark(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "명소 위치목록", notes = "모든 명소 정보들을 리스트 형으로 반환한다.", response = List.class)
	@GetMapping("/land")
	public ResponseEntity<List<LandDto>> listLand() throws Exception {
		logger.info("listLand - 호출");
		return new ResponseEntity<List<LandDto>>(landService.listLand(), HttpStatus.OK);
	}
	
	
	
	
	
	
}