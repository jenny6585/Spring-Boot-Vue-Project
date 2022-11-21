package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.TodoDto;

@Mapper
public interface TodoMapper {
	public int writeTodo(TodoDto todoDto) throws SQLException;
	public List<TodoDto> listTodo() throws SQLException;
	public int deleteTodo(int todono) throws SQLException;
	public int modifyTodo(TodoDto todoDto) throws SQLException;
	
	
}
