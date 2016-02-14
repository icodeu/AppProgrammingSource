package com.youngheart.utils;

import com.infrastructure.utils.BaseUtils;

public class Utils extends BaseUtils {
	/**
	 * 
	 * @Title: convertToInt
	 * @Description: 对象转化为整数数字类型
	 * @param value
	 * @param defaultValue
	 * @return integer
	 * @throws
	 */
	public final static int convertToInt(Object value, int defaultValue) {
		if (value == null || "".equals(value.toString().trim())) {
			return defaultValue;
		}
		try {
			return Integer.valueOf(value.toString());
		} catch (Exception e) {
			try {
				return Double.valueOf(value.toString()).intValue();
			} catch (Exception e1) {
				return defaultValue;
			}
		}
	}
}
