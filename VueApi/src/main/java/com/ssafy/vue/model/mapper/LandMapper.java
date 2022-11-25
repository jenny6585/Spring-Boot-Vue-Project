package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.LandDto;

public interface LandMapper {

	public List<LandDto>listLand() throws SQLException; 

}
