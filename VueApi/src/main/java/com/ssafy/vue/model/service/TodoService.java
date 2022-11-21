package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.TodoDto;

public interface TodoService {
	
	public boolean writeTodo(TodoDto todoDto) throws Exception;
	public List<TodoDto> listTodo() throws Exception;
	public boolean deleteTodo(int todono) throws Exception;
	public boolean modifyTodo(TodoDto todoDto) throws Exception;

}
