package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BikeDto;
import com.ssafy.vue.model.BikeParameterDto;
import com.ssafy.vue.model.service.BikeService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/bike")
@Api("바이크 컨트롤러  API V1")
public class BikeMapController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BikeService bikeService;

	
	@ApiOperation(value = "따릉이 위치목록", notes = "모든 따릉이 대여소 정보들을 리스트 형으로 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BikeDto>> listBike() throws Exception {
		logger.info("listBike - 호출");
		return new ResponseEntity<List<BikeDto>>(bikeService.listBike(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "특정 따릉이 대여소 정보보기", notes = "특정 대여소의 정보 객체를 반환한다.", response = BikeDto.class)
	@GetMapping("/{index}")
	public ResponseEntity<BikeDto> getBike(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int index) throws Exception {
		logger.info("getBike - 호출 : " + index);
		return new ResponseEntity<BikeDto>(bikeService.getBike(index), HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "대여소의 수", notes = "따릉이 대여소의 수를 전수형으로 반환한다.", response = int.class)
    @GetMapping("/total")
    // 리스트 수를 보내주기 위한 메소드
    public int getTotalCount(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BikeParameterDto bikeParameterDto) throws Exception {
        logger.info("totlacount - 호출:   "+bikeService.getTotalCount(bikeParameterDto));
        return bikeService.getTotalCount(bikeParameterDto);
    }
}