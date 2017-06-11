package com.hensen.teachercontent.Activity;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.shixiongsdk.Utils.DensityUtils;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.R;
import com.hensen.teachercontent.Utils.ImageGetterImpl;

public class TeachDetailActivity extends BaseActivity {

    private Data data;
    private TextView tv_name, tv_small_name, tv_content;
    private ImageView iv_teach;
    private CommonImageLoader commonImageLoader;


    @Override
    public int getLayoutId() {
        return R.layout.activity_teach_detail;
    }

    @Override
    public void initViews() {
        tv_name = findView(R.id.tv_name);
        tv_small_name = findView(R.id.tv_small_name);
        tv_content = findView(R.id.tv_content);
        iv_teach = findView(R.id.iv_teach);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("教师简介");
        setTitleCanBack();

        commonImageLoader = CommonImageLoader.getInstance(this);

        initTeachDetailViews();
    }


    @Override
    public void processClick(View v) {

    }

    /**
     *
     */
    private void initTeachDetailViews() {
        data = getIntent().getParcelableExtra("teach");
        tv_name.setText(data.getTitle());
        tv_small_name.setText(data.getSmall_title());
        tv_content.setText(Html.fromHtml(data.getContent(),
                new ImageGetterImpl(tv_content, data.getContent(),
                        DensityUtils.getDisplayWidth(this),
                        DensityUtils.getDisplayHeight(this)), null));
        commonImageLoader.displayImage(data.getThumb(), iv_teach);
    }
}
