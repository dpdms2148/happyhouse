package com.ssafy.vue.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		logger.info("dong - 호출");
		List<HouseInfoDto> list=haHouseMapService.getDongInGugun(gugun);
		for(HouseInfoDto dto:list)
		{
			System.out.println(dto.getDongCode()+" "+dto.getdong());
		}
		System.out.println("gugun: "+gugun);
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "연도 정보", notes = "DB에 저장된 연도를 반환한다.", response = List.class)
	@GetMapping("/year")
	public ResponseEntity<List<String>> year(@RequestParam("dong") String dongCode) throws Exception {
		logger.info("year - 호출");
		List<String> list=haHouseMapService.getYear(dongCode);
		for(String year:list)
		{
			System.out.println(year);
		}
		return new ResponseEntity<List<String>>(haHouseMapService.getYear(dongCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "월 정보", notes = "연도에 저장된 월을 반환한다.", response = List.class)
	@GetMapping("/month")
	public ResponseEntity<List<String>> month(@RequestParam("dong") String dongCode,@RequestParam("deal_year") String dealYear) throws Exception {
		logger.info("month - 호출");
		HouseInfoDto dto=new HouseInfoDto(dongCode,dealYear,"0");
		List<String> list=haHouseMapService.getMonth(dto);
		for(String month:list)
		{
			System.out.println(month);
		}
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}

	@GetMapping(value="/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("lawd_cd") String dongCode,@RequestParam("deal_year") String dealYear,@RequestParam("deal_month") String dealMonth) throws Exception {
		logger.info("apt - 호출");
		System.out.println(dongCode+" "+dealYear+" "+dealMonth);
		HouseInfoDto dto=new HouseInfoDto(dongCode,dealYear,dealMonth);
		List<HouseInfoDto> list=haHouseMapService.getAptInDong(dto);
		System.out.println("list Size: "+list.size());
		for(HouseInfoDto d:list)
		{
			System.out.println(d.getAptCode()+" "+d.getDealYear()+" "+d.getDealMonth()+" "+d.getFloor());
		}
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}

//	@ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
//	@GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
//	public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd, @PathVariable("deal_ymd") String dealYmd) throws IOException {
//		logger.info("sido - 호출");
//		String serviceKey = "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
//		StringBuilder urlBuilder = new StringBuilder(
//				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); 
//																															 
//		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
//				+ "=" + serviceKey);
//		urlBuilder
//				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
//		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
//		urlBuilder.append(
//				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
//		URL url = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
//		BufferedReader rd;
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println(sb.toString());
//		JSONObject json = XML.toJSONObject(sb.toString());
//		String jsonStr = json.toString();
//
//		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
//	}

}
