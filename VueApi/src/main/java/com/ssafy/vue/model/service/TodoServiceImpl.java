package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.TodoDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeTodo(TodoDto todoDto) throws Exception {
		if(todoDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(TodoMapper.class).writeTodo(todoDto) == 1;
	}

	@Override
	public List<TodoDto> listTodo() throws Exception {
		return sqlSession.getMapper(TodoMapper.class).listTodo();
	}
	
	@Override
	@Transactional
	public boolean deleteTodo(int todono) throws Exception {
		return sqlSession.getMapper(TodoMapper.class).deleteTodo(todono) == 1;
	}

	@Override
	@Transactional
	public boolean modifyTodo(TodoDto todoDto) throws Exception {
		return sqlSession.getMapper(TodoMapper.class).modifyTodo(todoDto) == 1;
	}
	
}
