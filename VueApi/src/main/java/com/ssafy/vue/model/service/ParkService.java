package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.ParkDto;

public interface ParkService {
	public List<ParkDto> listPark() throws Exception;
}
