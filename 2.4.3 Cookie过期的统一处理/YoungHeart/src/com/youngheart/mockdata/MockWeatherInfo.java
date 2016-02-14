package com.youngheart.mockdata;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.Response;
import com.youngheart.entity.WeatherInfo;

public class MockWeatherInfo extends MockService {	
	@Override
	public String getJsonData() {
		WeatherInfo weather = new WeatherInfo();
		weather.setCity("Beijing");
		weather.setCityid("10000");

		Response response = getSuccessResponse();		
		response.setResult(JSON.toJSONString(weather));
		return JSON.toJSONString(response);
	}
}
