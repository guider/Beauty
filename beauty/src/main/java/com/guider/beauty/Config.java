package com.guider.beauty;

import android.os.Environment;

public class Config {
	/**
	 * 是否只在wifi 下使用 的key
	 * 
	 * values 1 表示 仅在wifi下使用 values 0 表示 都可以使用
	 */
	public static String TYPE_CONN = "TYPE_CONN";
	public static int TYPE_ALL = 0;
	public static int TYPE_WIFI = 1;
	//
	public static String COUNT = "count";

	public static final String BASE_URL = "http://image.baidu.com/data/imgs?";
	public static String DIR_PATH = Environment.getExternalStorageDirectory()
			.toString() + "/girls/images/";
	public static final String APP_COL = "美女";

	
	public static final String[] TAGS = { "比基尼", "长发", "长腿", "车模", "短发", "非主流",
		"高雅大气很有范", "古典美女", "可爱", "嫩萝莉", "清纯", "气质", "时尚", "素颜", "甜素纯",
		"网络美女", "唯美", "校花", "写真", "性感美女", "诱惑", "足球宝贝" };

}
