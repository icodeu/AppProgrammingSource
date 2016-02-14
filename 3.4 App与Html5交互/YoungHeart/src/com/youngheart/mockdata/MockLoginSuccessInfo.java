package com.youngheart.mockdata;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.Response;
import com.youngheart.entity.UserInfo;

public class MockLoginSuccessInfo extends MockService {
	@Override
	public String getJsonData() {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginName("jianqiang.bao");
		userInfo.setUserName("包建强");
		userInfo.setScore(100);
		
		Response response = getSuccessResponse();		
		response.setResult(JSON.toJSONString(userInfo));
		return JSON.toJSONString(response);
	}
}
