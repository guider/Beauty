package com.guider.beauty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.guider.beauty.MyApplication;
import com.guider.beauty.R;
import com.guider.beauty.fragment.AllmeinviFragment;
import com.guider.beauty.fragment.BijiniFragment;
import com.guider.beauty.fragment.ChangTuiFragment;
import com.guider.beauty.fragment.ChangfaFragment;
import com.guider.beauty.fragment.ChemoFragment;
import com.guider.beauty.fragment.CollectFragment;
import com.guider.beauty.fragment.DuanfaFragment;
import com.guider.beauty.fragment.FeizhuliuFragment;
import com.guider.beauty.fragment.GaoyayoufanFragment;
import com.guider.beauty.fragment.GudianmeinvFragment;
import com.guider.beauty.fragment.KeaiFragment;
import com.guider.beauty.fragment.LuoliFragment;
import com.guider.beauty.fragment.MainFragment;
import com.guider.beauty.fragment.MenuFragment;
import com.guider.beauty.fragment.QingchunFragment;
import com.guider.beauty.fragment.QizhiFragment;
import com.guider.beauty.fragment.ShishangFragment;
import com.guider.beauty.fragment.SuyanFragment;
import com.guider.beauty.fragment.TiansuchunFragment;
import com.guider.beauty.fragment.WangluomeinvFragment;
import com.guider.beauty.fragment.WeimeiFragment;
import com.guider.beauty.fragment.XiaoHuaFragment;
import com.guider.beauty.fragment.XiaoQingXinFragment;
import com.guider.beauty.fragment.XiezhenFragment;
import com.guider.beauty.fragment.XingGanFragment;
import com.guider.beauty.fragment.YouhuoFragment;
import com.guider.beauty.fragment.ZuqiubaobeiFragment;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity {
	public MenuFragment menuFragment;
	private SlidingPaneLayout slidingPaneLayout;
	private DisplayMetrics displayMetrics = new DisplayMetrics();
	private int maxMargin = 0;
	private FragmentTransaction transaction;
	private MainFragment mainFragment;
	public XiaoQingXinFragment xiaoQingXinFragment;
	public XingGanFragment xingGanFragment;
	public ChangTuiFragment changTuiFragment;
	public XiaoHuaFragment xiaoHuaFragment;
	public QingchunFragment qingchunFragment;
	public XiezhenFragment xiezhenFragment;
	public QizhiFragment qizhiFragment;
	public ShishangFragment shishangFragment;
	public ChangfaFragment changfaFragment;
	public DuanfaFragment duanfaFragment;
	public GaoyayoufanFragment gaoyayoufanFragment;
	public TiansuchunFragment tiansuchunFragment;
	public KeaiFragment keaiFragment;
	public LuoliFragment luoliFragment;
	public WeimeiFragment weimeiFragment;
	public SuyanFragment suyanFragment;
	public YouhuoFragment youhuoFragment;
	public BijiniFragment bijiniFragment;
	public ChemoFragment chemoFragment;
	public ZuqiubaobeiFragment zuqiubaobeiFragment;
	public GudianmeinvFragment gudianmeinvFragment;
	public WangluomeinvFragment wangluomeinvFragment;
	public FeizhuliuFragment feizhuliuFragment;
	public AllmeinviFragment allmeinvFragment;
	public CollectFragment collectFragment;
	// /
	public static Map<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.addActivity(this);
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		setContentView(R.layout.activity_main);
		initData();
		initView();
		initSDK();
	}

	private void initSDK() {

		UmengUpdateAgent.update(this);
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.updateOnlineConfig(this);
	}

	private void initView() {
		slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanellayout);
		menuFragment = new MenuFragment();
		transaction = getSupportFragmentManager().beginTransaction();

		transaction.replace(R.id.slidingpane_menu, menuFragment);
		transaction.replace(R.id.slidingpane_content, mainFragment);
		transaction.commit();
		maxMargin = displayMetrics.heightPixels / 10;
		slidingPaneLayout.setPanelSlideListener(new PanelSlideListener() {
			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				int contentMargin = (int) (slideOffset * maxMargin);

				FrameLayout.LayoutParams contentParams = mainFragment.getCurrentViewParams();
				contentParams.setMargins(0, contentMargin, 0, contentMargin);

				mainFragment.setCurrentViewPararms(contentParams);

				float scale = 1 - ((1 - slideOffset) * maxMargin * 3) / (float) displayMetrics.heightPixels;
				menuFragment.getCurrentView().setScaleX(scale);// 设置缩放的基准点
				menuFragment.getCurrentView().setScaleY(scale);// 设置缩放的基准点
				menuFragment.getCurrentView().setPivotX(0);// 设置缩放和选择的点
				menuFragment.getCurrentView().setPivotY(displayMetrics.heightPixels / 2);
				menuFragment.getCurrentView().setAlpha(slideOffset);
			}

			@Override
			public void onPanelOpened(View arg0) {
			}

			@Override
			public void onPanelClosed(View arg0) {
			}
		});
	}

	private void initData() {
		mainFragment = new MainFragment();
		xiaoQingXinFragment = new XiaoQingXinFragment(XiaoQingXinFragment.TAG);
		xingGanFragment = new XingGanFragment(XingGanFragment.TAG);
		changTuiFragment = new ChangTuiFragment(ChangTuiFragment.TAG);
		xiaoHuaFragment = new XiaoHuaFragment(XiaoHuaFragment.TAG);
		qingchunFragment = new QingchunFragment(QingchunFragment.TAG);
		xiezhenFragment = new XiezhenFragment(XiezhenFragment.TAG);
		qizhiFragment = new QizhiFragment(QizhiFragment.TAG);
		shishangFragment = new ShishangFragment(ShishangFragment.TAG);
		changfaFragment = new ChangfaFragment(ChangfaFragment.TAG);
		duanfaFragment = new DuanfaFragment(DuanfaFragment.TAG);
		gaoyayoufanFragment = new GaoyayoufanFragment(GaoyayoufanFragment.TAG);
		tiansuchunFragment = new TiansuchunFragment(TiansuchunFragment.TAG);
		keaiFragment = new KeaiFragment(KeaiFragment.TAG);
		luoliFragment = new LuoliFragment(LuoliFragment.TAG);
		weimeiFragment = new WeimeiFragment(WeimeiFragment.TAG);
		suyanFragment = new SuyanFragment(SuyanFragment.TAG);
		youhuoFragment = new YouhuoFragment(YouhuoFragment.TAG);
		bijiniFragment = new BijiniFragment(BijiniFragment.TAG);
		chemoFragment = new ChemoFragment(ChemoFragment.TAG);
		zuqiubaobeiFragment = new ZuqiubaobeiFragment(ZuqiubaobeiFragment.TAG);
		gudianmeinvFragment = new GudianmeinvFragment(GudianmeinvFragment.TAG);
		wangluomeinvFragment = new WangluomeinvFragment(WangluomeinvFragment.TAG);
		feizhuliuFragment = new FeizhuliuFragment(FeizhuliuFragment.TAG);
		allmeinvFragment = new AllmeinviFragment(AllmeinviFragment.TAG);
		collectFragment = new CollectFragment();
		//
		fragmentMap.put(MainFragment.TAG, mainFragment);
		fragmentMap.put(XiaoQingXinFragment.TAG, xiaoQingXinFragment);
		fragmentMap.put(XingGanFragment.TAG, xingGanFragment);
		fragmentMap.put(ChangTuiFragment.TAG, changTuiFragment);
		fragmentMap.put(XiaoHuaFragment.TAG, xiaoHuaFragment);
		fragmentMap.put(QingchunFragment.TAG, qingchunFragment);
		fragmentMap.put(XiezhenFragment.TAG, xiezhenFragment);
		fragmentMap.put(QizhiFragment.TAG, qizhiFragment);
		fragmentMap.put(ShishangFragment.TAG, shishangFragment);
		fragmentMap.put(ChangfaFragment.TAG, changfaFragment);
		fragmentMap.put(DuanfaFragment.TAG, duanfaFragment);
		fragmentMap.put(GaoyayoufanFragment.TAG, gaoyayoufanFragment);
		fragmentMap.put(TiansuchunFragment.TAG, tiansuchunFragment);
		fragmentMap.put(KeaiFragment.TAG, keaiFragment);
		fragmentMap.put(LuoliFragment.TAG, luoliFragment);
		fragmentMap.put(WeimeiFragment.TAG, weimeiFragment);
		fragmentMap.put(SuyanFragment.TAG, suyanFragment);
		fragmentMap.put(YouhuoFragment.TAG, youhuoFragment);
		fragmentMap.put(BijiniFragment.TAG, bijiniFragment);
		fragmentMap.put(ChemoFragment.TAG, chemoFragment);
		fragmentMap.put(ZuqiubaobeiFragment.TAG, zuqiubaobeiFragment);
		fragmentMap.put(GudianmeinvFragment.TAG, gudianmeinvFragment);
		fragmentMap.put(WangluomeinvFragment.TAG, wangluomeinvFragment);
		fragmentMap.put(FeizhuliuFragment.TAG, feizhuliuFragment);
		fragmentMap.put(AllmeinviFragment.TAG, allmeinvFragment);
		fragmentMap.put(CollectFragment.TAG, collectFragment);
		//

	}

	public SlidingPaneLayout getSlidingPaneLayout() {
		return slidingPaneLayout;
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPause(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		MobclickAgent.onResume(this);
		super.onResume();
	}

	// 返回键 监听
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (slidingPaneLayout.isOpen()) {
				slidingPaneLayout.closePane();
			} else {
				// slidingPaneLayout.openPane();
				transaction = getSupportFragmentManager().beginTransaction();
				transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
				transaction.replace(R.id.slidingpane_content, mainFragment);
				transaction.commit();
			}
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
}