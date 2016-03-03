package com.guider.beauty.http;


public interface HttpResponseCallBack {

	public void onSuccess(String url, String result);

	public void onFailure(int httpResponseCode, int errCode, String err);

}
