package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.vue.model.TodoDto;
import com.ssafy.vue.model.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/todo")
@Api("할일 컨트롤러  API V1")
public class TodoController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private TodoService todoService;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@ApiOperation(value = "할일 글작성", notes = "새로운 할일 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeTodo(HttpServletRequest request) throws Exception {
		logger.info("writeTodo - 호출");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		TodoDto todoDto = objectMapper.readValue(request.getReader(), new TypeReference<TodoDto>(){});
		if (todoService.writeTodo(todoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	
	@ApiOperation(value = "Todo 글목록", notes = "모든 Todo 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<TodoDto>> listTodo() throws Exception {
		logger.info("listTodo - 호출");
		return new ResponseEntity<List<TodoDto>>(todoService.listTodo(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/delete")
	public ResponseEntity<String> deleteTodo(HttpServletRequest request) throws Exception {
		
		logger.info("deleteTodo - 호출");
		ObjectMapper objectMapper = new ObjectMapper();

        // JSON -> Java Object
        //String json = "{\"name\":\"zooneon\",\"age\":25,\"address\":\"seoul\"}";
       
        List<TodoDto> todo = objectMapper.readValue(request.getReader(), new TypeReference<List<TodoDto>>(){});
        for(TodoDto dto:todo) {
        	if(todoService.deleteTodo(dto.getTodono())) {
        		continue;
        	}else {
        		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        	}
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
		
	}
	
	
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyTodo(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) TodoDto todoDto) throws Exception {
		logger.info("modifyTodo - 호출 {}", todoDto);
		
		if (todoService.modifyTodo(todoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
