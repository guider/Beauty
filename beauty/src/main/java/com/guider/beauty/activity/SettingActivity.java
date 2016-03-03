package com.guider.beauty.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guider.beauty.Config;
import com.guider.beauty.MyApplication;
import com.guider.beauty.R;
import com.guider.beauty.utils.CacheTools;
import com.guider.beauty.utils.MySharePreference;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;


public class SettingActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener {
	private static final int ClearAppCache = 100;
	public final String TAG = "SettingActivity";

	private LinearLayout item_clear_cache, wifi_settings;
	private TextView cache_size, item_rc_app, item_rc_game, item_feedback, item_checkupdate, item_out, item_about;
	private ImageView m_toggle, m_setting;
	private CheckBox wifi_checkBox;
	private MySharePreference appPreference;
	private ProgressDialog mDialog;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		MyApplication.addActivity(this);
		setContentView(R.layout.activity_setting_layout);
		appPreference = new MySharePreference(this);
		initView();
		initExitAD();
	}

	private void initView() {
		item_clear_cache = (LinearLayout) findViewById(R.id.item_clear_cache);
		item_clear_cache.setOnClickListener(this);
		wifi_settings = (LinearLayout) findViewById(R.id.wifi_settings);
		wifi_settings.setOnClickListener(this);
		cache_size = (TextView) findViewById(R.id.cache_size);
		cache_size.setText(CacheTools.getHttpCacheSize(this));
		m_toggle = (ImageView) findViewById(R.id.m_toggle);
		m_toggle.setOnClickListener(this);
		m_setting = (ImageView) findViewById(R.id.m_setting);
		// m_setting.setOnClickListener(this);
		m_setting.setVisibility(View.GONE);
		wifi_checkBox = (CheckBox) findViewById(R.id.wifi_checkBox);
		wifi_checkBox.setOnCheckedChangeListener(this);
		int p = appPreference.getInt(Config.TYPE_CONN, 0);
		if (p == Config.TYPE_ALL) {
			wifi_checkBox.setChecked(false);
		} else if (p == Config.TYPE_WIFI) {
			wifi_checkBox.setChecked(true);
		} else {
			wifi_checkBox.setChecked(false);
		}
		item_rc_game = (TextView) findViewById(R.id.item_rc_game);
		item_rc_app = (TextView) findViewById(R.id.item_rc_app);
		item_rc_app.setOnClickListener(this);
		item_rc_game.setOnClickListener(this);
		item_feedback = (TextView) findViewById(R.id.item_feedback);
		item_feedback.setOnClickListener(this);
		item_checkupdate = (TextView) findViewById(R.id.item_checkupdate);
		item_checkupdate.setOnClickListener(this);
		item_about = (TextView) findViewById(R.id.item_about);
		item_about.setOnClickListener(this);
		mDialog = new ProgressDialog(this);
		mDialog.setMessage("姝ｅ湪娓呴櫎缂撳瓨...");
		item_out = (TextView) findViewById(R.id.item_out);
		item_out.setTextColor(Color.parseColor("#EE6363"));
		item_out.setOnClickListener(this);
	}

	// 閫�鍑烘椂鐨勫箍鍛�
	private void initExitAD() {
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.updateOnlineConfig(this);

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case ClearAppCache:
				cache_size.setText(CacheTools.getHttpCacheSize(SettingActivity.this));
				mDialog.cancel();
				mDialog.dismiss();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	Runnable mRunnable = new Runnable() {

		@Override
		public void run() {
			CacheTools.clearAppCache(getApplicationContext());
			Message msg = new Message();
			msg.arg1 = ClearAppCache;
			mHandler.sendMessage(msg);
		}
	};

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.item_clear_cache:
			mDialog.show();
			new Thread(mRunnable).start();
			break;
		case R.id.m_toggle:
			finish();
			break;
		case R.id.wifi_settings:
			if (wifi_checkBox.isChecked()) {
				wifi_checkBox.setChecked(false);
			} else {
				wifi_checkBox.setChecked(true);
			}
			break;

		case R.id.item_feedback:
			FeedbackAgent agent = new FeedbackAgent(SettingActivity.this);
			agent.startFeedbackActivity();
			break;
		case R.id.item_checkupdate:
			UmengUpdateAgent.forceUpdate(SettingActivity.this);
			umUpdata();
			break;
		case R.id.item_about:
//			Intent in = new Intent();
//			in.setClass(this, AboutUsActivity.class);
//			startActivity(in);
			break;
		case R.id.item_out:
			item_out.setTextColor(Color.parseColor("#636363"));

			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			appPreference.commitInt(Config.TYPE_CONN, Config.TYPE_WIFI);
		} else {
			appPreference.commitInt(Config.TYPE_CONN, Config.TYPE_ALL);
		}

	}


	private void umUpdata() {
		UmengUpdateAgent.setUpdateAutoPopup(true);
		UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
			@Override
			public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
				switch (updateStatus) {
				case UpdateStatus.Yes: // has update
					UmengUpdateAgent.showUpdateDialog(SettingActivity.this, updateInfo);
					break;
				case UpdateStatus.No: // has no update
					break;
				case UpdateStatus.NoneWifi: // none wifi
					break;
				case UpdateStatus.Timeout: // time out
					break;
				}
			}
		});
		UmengUpdateAgent.update(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		MobclickAgent.onPause(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		MobclickAgent.onPageStart(TAG);
		MobclickAgent.onResume(this);
		super.onResume();
	}

}
