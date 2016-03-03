package com.guider.beauty.utils;

import com.guider.beauty.bean.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author ving
 *
 */
public class URLParamsTools {
	public static final String BASE_URL = "http://image.baidu.com/data/imgs?";
	public static final String TAG = "URLParamsTools";

	public static String getEncoderURL(Params params) {
		String url = "";
		if (null != params) {
			url = BASE_URL + "col=" + EncodeString(params.getCol()) + "&tag="
					+ EncodeString(params.getTag()) + "&sort=0&tag3=&pn="
					+ params.getPn() + "&rn=" + params.getRn()
					+ "&p=channel&from=1";
		} else {
			url = "http://image.baidu.com/data/imgs?col=%E6%90%9E%E7%AC%91&tag=%E5%85%A8%E9%83%A8&sort=0&tag3=a&pn=17&rn=1&p=channel&from=1";
		}

		return url;
	}

	public static String EncodeString(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}

	}
}
