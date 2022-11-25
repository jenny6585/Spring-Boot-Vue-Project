package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.BikeDto;
import com.ssafy.vue.model.BikeParameterDto;
import com.ssafy.vue.model.LandDto;
import com.ssafy.vue.model.mapper.BikeMapper;
import com.ssafy.vue.model.mapper.LandMapper;


@Service
public class LandServiceImpl implements LandService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<LandDto> listLand() throws Exception {
		return sqlSession.getMapper(LandMapper.class).listLand();
	}
}
