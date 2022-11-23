package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.BikeDto;
import com.ssafy.vue.model.BikeParameterDto;
import com.ssafy.vue.model.mapper.BikeMapper;


@Service
public class BikeServiceImpl implements BikeService{
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<BikeDto> listBike() throws Exception {
		return sqlSession.getMapper(BikeMapper.class).listBike();
	}

	@Override
	public BikeDto getBike(int index) throws Exception {
		return sqlSession.getMapper(BikeMapper.class).getBike(index);
	}

	@Override
	public int getTotalCount(BikeParameterDto bikeParameterDto) throws Exception {
		return sqlSession.getMapper(BikeMapper.class).getTotalCount(bikeParameterDto);
	}


	
}
