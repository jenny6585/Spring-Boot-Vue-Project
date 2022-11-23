package com.ssafy.vue.model.service;

import java.util.List;
import com.ssafy.vue.model.BikeDto;
import com.ssafy.vue.model.BikeParameterDto;

public interface BikeService {
	public int getTotalCount(BikeParameterDto bikeParameterDto)throws Exception;
	public List<BikeDto> listBike() throws Exception;
	public BikeDto getBike(int index) throws Exception;
}


