package com.guider.beauty;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
	public static String CACHE_DIR = "";
	public static Context AppContext;
	private static List<Activity> activitys = new LinkedList<Activity>();
	public List<Activity> getActivitys() {
		return activitys;
	}
	public static void addActivity(Activity a) {
		activitys.add(a);
	}

	public static void exit() {
		for (Activity a : activitys) {
			a.finish();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

}
