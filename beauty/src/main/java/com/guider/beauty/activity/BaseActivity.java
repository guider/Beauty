package com.guider.beauty.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(arg0);
	}

}
