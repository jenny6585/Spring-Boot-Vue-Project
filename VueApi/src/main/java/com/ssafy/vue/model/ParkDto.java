package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ParkDto : 공원정보", description = "게시글의 상세 정보를 나타낸다.")
public class ParkDto {
	@ApiModelProperty(value = "공원 이름")
	private String name;
	
	@ApiModelProperty(value = "위도")
	private double latitude;
	
	@ApiModelProperty(value = "경도")
	private double longitude;

	@ApiModelProperty(value = "경도")
	private String region;
	
	
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "ParkDto [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", region=" + region
				+ "]";
	}

	
	
	
}
