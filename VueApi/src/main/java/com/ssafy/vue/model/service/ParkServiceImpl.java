package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.ParkDto;
import com.ssafy.vue.model.mapper.ParkMapper;



@Service
public class ParkServiceImpl implements ParkService{
	@Autowired
	private SqlSession sqlSession;

	public List<ParkDto> listPark() throws Exception {
		return sqlSession.getMapper(ParkMapper.class).listPark();
	}
}
