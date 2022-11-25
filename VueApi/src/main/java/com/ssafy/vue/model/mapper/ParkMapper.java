package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.ParkDto;

public interface ParkMapper {

	public List<ParkDto> listPark()  throws SQLException;

}
