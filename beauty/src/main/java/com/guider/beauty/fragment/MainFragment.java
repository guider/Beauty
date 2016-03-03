package com.guider.beauty.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.guider.beauty.Config;
import com.guider.beauty.MyApplication;
import com.guider.beauty.R;
import com.guider.beauty.activity.MainActivity;
import com.guider.beauty.activity.SettingActivity;
import com.guider.beauty.bean.ColumnImages;
import com.guider.beauty.bean.Params;
import com.guider.beauty.bean.RowImage;
import com.guider.beauty.http.HttpGetClient;
import com.guider.beauty.http.HttpResponseCallBack;
import com.guider.beauty.utils.CacheTools;
import com.guider.beauty.utils.DateTools;
import com.yanyuanquan.android.bannerlibrary.AutoBannerLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 主导航 界面
 */
public class MainFragment extends Fragment implements OnClickListener {
    public static final String TAG = "MainFragment";
    public static final String Fliper = "fliper";

    private View currentView = null;
    private ImageButton m_toggle, m_setting;
    private TextView top_bar_title;
    private Context context;
    AutoBannerLayout autoBanner;

    Map<String, String> tagsMap = new HashMap<String, String>();
    // 高雅
    private LinearLayout item_gaoyaoyoufan, item_xiezhen, item_qizhi,
            item_shishang, item_changfa, item_duanfa;
    FragmentTransaction transaction;
    // 小清新
    private LinearLayout item_xiaoqingxin, item_tiansuchun, item_qingchun,
            item_xiaohua, item_keai, item_luoli, item_weimei, item_suyan;
    // 性感
    private LinearLayout item_xingan, item_youhuo, item_chuangtui, item_bijini,
            item_chemo, item_zuqiubaobei;
    // others
    private LinearLayout item_gudianmeinv, item_wangluomeinv, item_feizhuliu,
            item_quanbu;

    private HttpGetClient mHttpClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_main_layout,
                container, false);
        context = this.getActivity();
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        initData();
        return currentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }
    private void initView() {
        m_toggle = (ImageButton) currentView.findViewById(R.id.m_toggle);
        m_toggle.setOnClickListener(this);
        m_setting = (ImageButton) currentView.findViewById(R.id.m_setting);
        m_setting.setOnClickListener(this);
        top_bar_title = (TextView) currentView.findViewById(R.id.top_bar_title);
        top_bar_title.setText(R.string.home_title);
        autoBanner = (AutoBannerLayout) currentView
                .findViewById(R.id.auto_banner);

        item_gaoyaoyoufan = (LinearLayout) currentView
                .findViewById(R.id.item_gaoyaoyoufan);
        item_gaoyaoyoufan.setOnClickListener(this);
        item_xiezhen = (LinearLayout) currentView
                .findViewById(R.id.item_xiezhen);
        item_xiezhen.setOnClickListener(this);
        item_qizhi = (LinearLayout) currentView.findViewById(R.id.item_qizhi);
        item_qizhi.setOnClickListener(this);
        item_shishang = (LinearLayout) currentView
                .findViewById(R.id.item_shishang);
        item_shishang.setOnClickListener(this);
        item_changfa = (LinearLayout) currentView
                .findViewById(R.id.item_changfa);
        item_duanfa = (LinearLayout) currentView.findViewById(R.id.item_duanfa);
        item_changfa.setOnClickListener(this);
        item_duanfa.setOnClickListener(this);
        // item 1
        item_xiaoqingxin = (LinearLayout) currentView
                .findViewById(R.id.item_xiaoqingxin);
        item_xiaoqingxin.setOnClickListener(this);
        item_tiansuchun = (LinearLayout) currentView
                .findViewById(R.id.item_tiansuchun);
        item_tiansuchun.setOnClickListener(this);
        item_qingchun = (LinearLayout) currentView
                .findViewById(R.id.item_qingchun);
        item_qingchun.setOnClickListener(this);
        item_xiaohua = (LinearLayout) currentView
                .findViewById(R.id.item_xiaohua);
        item_xiaohua.setOnClickListener(this);
        item_keai = (LinearLayout) currentView.findViewById(R.id.item_keai);
        item_keai.setOnClickListener(this);
        item_luoli = (LinearLayout) currentView.findViewById(R.id.item_luoli);
        item_luoli.setOnClickListener(this);
        item_weimei = (LinearLayout) currentView.findViewById(R.id.item_weimei);
        item_weimei.setOnClickListener(this);
        item_suyan = (LinearLayout) currentView.findViewById(R.id.item_suyan);
        item_suyan.setOnClickListener(this);
        // item 2
        item_xingan = (LinearLayout) currentView.findViewById(R.id.item_xingan);
        item_xingan.setOnClickListener(this);
        item_youhuo = (LinearLayout) currentView.findViewById(R.id.item_youhuo);
        item_youhuo.setOnClickListener(this);
        item_chuangtui = (LinearLayout) currentView
                .findViewById(R.id.item_chuangtui);
        item_chuangtui.setOnClickListener(this);
        item_bijini = (LinearLayout) currentView.findViewById(R.id.item_bijini);
        item_bijini.setOnClickListener(this);
        item_chemo = (LinearLayout) currentView.findViewById(R.id.item_chemo);
        item_chemo.setOnClickListener(this);
        item_zuqiubaobei = (LinearLayout) currentView
                .findViewById(R.id.item_zuqiubaobei);
        item_zuqiubaobei.setOnClickListener(this);
        // others
        item_gudianmeinv = (LinearLayout) currentView
                .findViewById(R.id.item_gudianmeinv);
        item_gudianmeinv.setOnClickListener(this);
        item_wangluomeinv = (LinearLayout) currentView
                .findViewById(R.id.item_wangluomeinv);
        item_wangluomeinv.setOnClickListener(this);
        item_feizhuliu = (LinearLayout) currentView
                .findViewById(R.id.item_feizhuliu);
        item_feizhuliu.setOnClickListener(this);
        item_quanbu = (LinearLayout) currentView.findViewById(R.id.item_quanbu);
        item_quanbu.setOnClickListener(this);

    }


    int position = 1001;

    private void initData() {
        mHttpClient = HttpGetClient.getInstance();

        while (tagsMap.size() < 4) {
            int r = (int) (Config.TAGS.length * Math.random());
            if (!tagsMap.containsKey(Config.TAGS[r])) {
                tagsMap.put(Config.TAGS[r], Config.TAGS[r]);
            }
        }
        for (Map.Entry<String, String> entry : tagsMap.entrySet()) {
            initViewFlipper(entry.getValue(), position);
            position = position + 1;
        }
    }

    public FrameLayout.LayoutParams getCurrentViewParams() {
        return (LayoutParams) currentView.getLayoutParams();
    }

    public void setCurrentViewPararms(FrameLayout.LayoutParams layoutParams) {
        currentView.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View view) {
        transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in,
                R.anim.push_left_out);

        switch (view.getId()) {
            case R.id.m_toggle:
                ((MainActivity) getActivity()).getSlidingPaneLayout().openPane();

                break;
            case R.id.m_setting:
                Intent intent = new Intent();
                intent.setClass(context, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.item_gaoyaoyoufan:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(GaoyayoufanFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_xiezhen:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(XiezhenFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_qizhi:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(QizhiFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_shishang:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(ShishangFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_changfa:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(ChangfaFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_duanfa:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(DuanfaFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_xiaoqingxin:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(XiaoQingXinFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_tiansuchun:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(TiansuchunFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_qingchun:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(QingchunFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_xiaohua:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(XiaoHuaFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_keai:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(KeaiFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_luoli:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(LuoliFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_weimei:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(WeimeiFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_suyan:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(SuyanFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_xingan:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(XingGanFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_youhuo:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(YouhuoFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_chuangtui:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(ChangTuiFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_bijini:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(BijiniFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_chemo:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(ChemoFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_zuqiubaobei:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(ZuqiubaobeiFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_gudianmeinv:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(GudianmeinvFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_wangluomeinv:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(WangluomeinvFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_feizhuliu:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(FeizhuliuFragment.TAG));
                transaction.commit();
                break;
            case R.id.item_quanbu:
                ((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
                transaction.replace(R.id.slidingpane_content,
                        MainActivity.fragmentMap.get(AllmeinviFragment.TAG));
                transaction.commit();
                break;
            default:
                break;
        }

    }



    private void initViewFlipper(String tag, int position) {
        Params p = new Params();
        p.setCol(Config.APP_COL);
        p.setTag(tag);
        p.setPn("1");
        p.setRn("1");
        mHttpClient.asyHttpGetRequest(p.toString(),
                new FilpperHttpResponseCallBack(position));

    }
    List<String> list = new ArrayList<>();
    volatile int i =0;
    private Handler handler = new Handler();
    class FilpperHttpResponseCallBack implements HttpResponseCallBack {

        public FilpperHttpResponseCallBack(int position) {
            super();

        }

        @Override
        public void onSuccess(String url, String result) {
            ColumnImages colImages = JSON.parseObject(result,
                    ColumnImages.class);
            i++;
            String imgurl = colImages.getImgs().get(0).getThumbLargeUrl();
            if (!TextUtils.isEmpty(imgurl))
                list.add(imgurl);
            if (i == 4) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        autoBanner.setData(list);
                    }
                });
            }
        }

        @Override
        public void onFailure(int httpResponseCode, int errCode, String err) {
        }

    }
}
