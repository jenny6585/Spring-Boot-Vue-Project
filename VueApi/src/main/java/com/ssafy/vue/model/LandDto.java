package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LandDto : 명소 정보", description = "명소의 정보를 나타낸다.")
public class LandDto {
	@ApiModelProperty(value = "명소 이름")
	private String name;
	
	@ApiModelProperty(value = "위도")
	private double latitude;
	
	
	@ApiModelProperty(value = "경도")
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LandDto [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
	
}
