package com.example.testcode;

public class AppConstants {
	public final static String IDCARD_LENGTH_SHOULD_NOT_BE_NULL = "身份证号码不能为空";
	public final static String IDCARD_LENGTH_SHOULD_BE_MORE_THAN_15_OR_18 = "身份证号码长度应该为15位或18位";
	public final static String IDCARD_SHOULD_BE_15_DIGITS = "身份证15位号码都应为数字";
	public final static String IDCARD_SHOULD_BE_17_DIGITS_EXCEPT_LASTONE = "身份证18位号码除最后一位外，都应为数字";
	public final static String IDCARD_BIRTHDAY_SHOULD_NOT_LARGER_THAN_NOW = "身份证出生年月日不能大于当前日期";
	public final static String IDCARD_BIRTHDAY_IS_INVALID = "身份证出生年月日不是合法日期";
	public final static String IDCARD_REGION_ENCODE_IS_INVALID = "输入的身份证号码地域编码不符合大陆和港澳台规则";
	public final static String IDCARD_IS_INVALID = "身份证无效，不是合法的身份证号码";
	public final static String IDCARD_PARSER_ERROR =  "解析身份证发生错误";
	public final static String IDCARD_IS_VALID =  "合法身份证";
}
