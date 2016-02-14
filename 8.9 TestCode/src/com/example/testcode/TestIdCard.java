package com.example.testcode;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestIdCard extends TestCase {

	public void testIdCard() throws Exception {		
		// 测试长度为0或者输入为空的情况
		Assert.assertEquals(AppConstants.IDCARD_LENGTH_SHOULD_NOT_BE_NULL,
				Utils.isIdCardNumberValid("").getIdCardDesc());
		Assert.assertEquals(AppConstants.IDCARD_LENGTH_SHOULD_NOT_BE_NULL,
				Utils.isIdCardNumberValid(null).getIdCardDesc());

		// 测试长度不为15或者18的情况
		StringBuilder idCard = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			idCard.append("1");

			if (idCard.length() == 15 || idCard.length() == 18)
				continue;

			Assert.assertEquals(
					AppConstants.IDCARD_LENGTH_SHOULD_BE_MORE_THAN_15_OR_18,
					Utils.isIdCardNumberValid(idCard.toString()).getIdCardDesc());
		}

		// 测试18位身份证，前17位必须为数字的情况
		String idCardNO = "1234567890123456XX";
		Assert.assertEquals(
				AppConstants.IDCARD_SHOULD_BE_17_DIGITS_EXCEPT_LASTONE,
				Utils.isIdCardNumberValid(idCardNO).getIdCardDesc());

		// 测试15位身份证，必须全都为数字的情况
		String idCardNO2 = "12345678901234X";
		Assert.assertEquals(AppConstants.IDCARD_SHOULD_BE_15_DIGITS,
				Utils.isIdCardNumberValid(idCardNO2).getIdCardDesc());

		// 测试18位身份证年月日不能大于当前日期
		String realCardNO = "120107201501142413";
		Assert.assertEquals(
				AppConstants.IDCARD_BIRTHDAY_SHOULD_NOT_LARGER_THAN_NOW,
				Utils.isIdCardNumberValid(realCardNO).getIdCardDesc());

		// 测试18位身份证年月日必须是有效的
		String realCardNO2 = "120107198201322413";
		Assert.assertEquals(AppConstants.IDCARD_BIRTHDAY_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO2).getIdCardDesc());

		// 测试18位身份证，前6位不能大于659004，不能小于110000
		String realCardNO5 = "659005198201142413";
		Assert.assertEquals(AppConstants.IDCARD_REGION_ENCODE_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO5).getIdCardDesc());
		String realCardNO6 = "100000198201142413";
		Assert.assertEquals(AppConstants.IDCARD_REGION_ENCODE_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO6).getIdCardDesc());

		// 测试15位身份证年月日必须是有效的
		String realCardNO4 = "422823051232022";
		Assert.assertEquals(AppConstants.IDCARD_BIRTHDAY_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO4).getIdCardDesc());

		// 测试15位身份证，前6位不能大于659004，不能小于110000
		String realCardNO7 = "659005051230022";
		Assert.assertEquals(AppConstants.IDCARD_REGION_ENCODE_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO7).getIdCardDesc());
		String realCardNO8 = "100000051230022";
		Assert.assertEquals(AppConstants.IDCARD_REGION_ENCODE_IS_INVALID,
				Utils.isIdCardNumberValid(realCardNO8).getIdCardDesc());

		// 15位身份证年月日肯定小于当前日期（都是19xx年的）
		String realCardNO3 = "422823151202022";
		Assert.assertEquals(AppConstants.IDCARD_IS_VALID,
				Utils.isIdCardNumberValid(realCardNO3).getIdCardDesc());

		
		//测试18位身份证最后一位是否合法
		Assert.assertEquals(AppConstants.IDCARD_IS_INVALID,
				Utils.isIdCardNumberValid("110000191501010000").getIdCardDesc());
		Assert.assertEquals(AppConstants.IDCARD_IS_VALID,
				Utils.isIdCardNumberValid("120107198201142413").getIdCardDesc());

	}

}
