package com.ssafy.vue.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BikeDto {
	@ApiModelProperty(value = "대여소 이름")
	private String description;
	
	@ApiModelProperty(value = "대여소 id 정보")
	private String id;
	
	@ApiModelProperty(value = "대여소 index")
	private int index;
	
	@ApiModelProperty(value = "위도")
	private double latitude;
	
	@ApiModelProperty(value = "경도")
	private double longitude;
	
	@ApiModelProperty(value = "대여소 구")
	private String gugunRaw;

	public BikeDto() {
		super();
	}

	public BikeDto(String description, String id, int index, double latitude, double longitude, String gugunRaw) {
		super();
		this.description = description;
		this.id = id;
		this.index = index;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gugunRaw = gugunRaw;
	}

	
	
	@Override
	public String toString() {
		return "BikeDto [description=" + description + ", id=" + id + ", index=" + index + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", gugunRaw=" + gugunRaw + "]";
	}

	

	public String getGugunRaw() {
		return gugunRaw;
	}

	public void setGugunRaw(String gugunRaw) {
		this.gugunRaw = gugunRaw;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
	
	
	

}
