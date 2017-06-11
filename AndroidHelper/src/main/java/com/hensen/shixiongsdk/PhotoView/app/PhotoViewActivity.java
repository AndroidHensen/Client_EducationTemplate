package com.hensen.shixiongsdk.PhotoView.app;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.R;

import java.util.ArrayList;

public class PhotoViewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static final String COMMON_PHOTO_URLS = "photo_urls";

    private TextView tv_title;
    private Button bt_back;
    private int position;

    private ViewPager vp_photo;
    private PhotoViewAdapter adapter;
    private ArrayList<String> photo_urls;

    @Override
    public int getLayoutId() {
        return R.layout.sdk_activity_photo_view;
    }

    @Override
    public void initViews() {
        vp_photo = findView(R.id.vp_photo);
        tv_title = findView(R.id.tv_title);
        bt_back = findView(R.id.bt_back);
    }

    @Override
    public void initListener() {
        setOnClick(bt_back);
        vp_photo.setOnPageChangeListener(this);
    }

    @Override
    public void initData() {
        photo_urls = getIntent().getStringArrayListExtra(COMMON_PHOTO_URLS);
        position = getIntent().getIntExtra("position", 0);
        tv_title.setText((position + 1) + "/" + photo_urls.size());
        adapter = new PhotoViewAdapter(this, photo_urls);
        vp_photo.setAdapter(adapter);
        vp_photo.setCurrentItem(position);
    }

    @Override
    public void processClick(View v) {
        if (v.getId() == R.id.bt_back) {
            finish();
        }
    }

    /**
     * Solve the problem of multi-touch bug for viewpager
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int pos) {
        tv_title.setText((pos + 1) + "/" + photo_urls.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
