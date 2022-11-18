package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfoDto : 아파트정보", description = "아파트의 상세 정보를 나타낸다.")
public class HouseInfoDto {

	@ApiModelProperty(value = "아파트일련번호")
	private String aptCode;
	@ApiModelProperty(value = "아파트이름")
	private String apartmentName;
	@ApiModelProperty(value = "동코드")
	private String dongCode;
	@ApiModelProperty(value = "동이름")
	private String dong;
	@ApiModelProperty(value = "시도이름")
	private String sidoName;
	@ApiModelProperty(value = "구군이름")
	private String gugunName;
	@ApiModelProperty(value = "건축년도")
	private int buildYear;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "아파트이미지")
	private String img;
	@ApiModelProperty(value = "매매가격")
	private String dealAmount;
	@ApiModelProperty(value = "거래년도")
	private String dealYear;
	@ApiModelProperty(value = "거래월")
	private String dealMonth;
	@ApiModelProperty(value = "층")
	private String floor;
	
	public HouseInfoDto() {
		super();
	}
	
	public HouseInfoDto(String dongCode, String dealYear, String dealMonth) {
		this.dongCode=dongCode;
		this.dealYear=dealYear;
		this.dealMonth=dealMonth;
	}

	public HouseInfoDto(String aptCode, String apartmentName, String dongCode, String dong, String sidoName,
			String gugunName, int buildYear, String jibun, String lat, String lng, String img, String dealAmount,
			String dealYear, String dealMonth, String floor) {
		super();
		this.aptCode = aptCode;
		this.apartmentName = apartmentName;
		this.dongCode = dongCode;
		this.dong = dong;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.floor=floor;
	}

	String getApartmentName() {
		return apartmentName;
	}

	void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	String getDong() {
		return dong;
	}

	void setDong(String dong) {
		this.dong = dong;
	}

	String getFloor() {
		return floor;
	}

	void setFloor(String floor) {
		this.floor = floor;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getapartmentName() {
		return apartmentName;
	}

	public void setapartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getdong() {
		return dong;
	}

	public void setdong(String dong) {
		this.dong = dong;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getdealAmount() {
		return dealAmount;
	}

	public void setdealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	
	
}
