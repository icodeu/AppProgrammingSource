package com.youngheart.utils;

import android.app.ProgressDialog;

import com.infrastructure.activity.BaseActivity;
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
	
	/**
	 * 
	 * @Title: createProgressDialog
	 * @Description: 创建ProgressDialog
	 * @param activity
	 * @param msg
	 * @return ProgressDialog
	 */
    public static ProgressDialog createProgressDialog(final BaseActivity activity, final String msg)
    {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage(msg);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

}
