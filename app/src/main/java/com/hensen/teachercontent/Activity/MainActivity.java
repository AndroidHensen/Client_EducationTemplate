package com.hensen.teachercontent.Activity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.shixiongsdk.Widght.BannerView;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private LinearLayout ly_content, ly_teach, ly_course, ly_video, ly_career, ly_share, ly_theme, ly_job, ly_contact;
    private List<Data> dataList;

    private BannerView bannerView;
    private List<String> banner_url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        bannerView = findView(R.id.bannerView);
        ly_content = findView(R.id.ly_content);
        ly_teach = findView(R.id.ly_teach);
        ly_course = findView(R.id.ly_course);
        ly_video = findView(R.id.ly_video);
        ly_career = findView(R.id.ly_career);
        ly_share = findView(R.id.ly_share);
        ly_theme = findView(R.id.ly_theme);
        ly_job = findView(R.id.ly_job);
        ly_contact = findView(R.id.ly_contact);
    }

    @Override
    public void initListener() {
        setOnClick(ly_content);
        setOnClick(ly_teach);
        setOnClick(ly_course);
        setOnClick(ly_video);
        setOnClick(ly_career);
        setOnClick(ly_share);
        setOnClick(ly_theme);
        setOnClick(ly_job);
        setOnClick(ly_contact);
    }

    @Override
    public void initData() {
        setTitle("首页");

        initBannerViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_content:
                startActivity(ContentActivity.class);
                break;
            case R.id.ly_teach:
                startActivity(TeachActivity.class);
                break;
            case R.id.ly_course:
                startActivity(CourseActivity.class);
                break;
            case R.id.ly_video:
                startActivity(VideoActivity.class);
                break;
            case R.id.ly_career:
                startActivity(CareerActivity.class);
                break;
            case R.id.ly_share:
                startActivity(ShareActivity.class);
                break;
            case R.id.ly_theme:
                startActivity(ThemeActivity.class);
                break;
            case R.id.ly_job:
                startActivity(JobActivity.class);
                break;
            case R.id.ly_contact:
                startActivity(AboutActivity.class);
                break;
        }
    }

    /**
     *
     */
    private void initBannerViews() {
        banner_url = new ArrayList<>();
        RequestCenter.requestBannerData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                for (int i = 0; i < dataList.size(); i++) {
                    banner_url.add(dataList.get(i).getThumb());
                }
                bannerView.initBannerImageForNet(MainActivity.this, banner_url);
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }
}
