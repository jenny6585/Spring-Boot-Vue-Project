package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.BikeDto;
import com.ssafy.vue.model.BikeParameterDto;

public interface BikeMapper {

	public List<BikeDto> listBike()  throws SQLException; 

	public BikeDto getBike(int index)  throws SQLException;

	public int getTotalCount(BikeParameterDto bikeParameterDto) throws SQLException;

}
