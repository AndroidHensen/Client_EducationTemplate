package com.hensen.teachercontent.Activity;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.shixiongsdk.Utils.DateUtils;
import com.hensen.shixiongsdk.Utils.DensityUtils;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.R;
import com.hensen.teachercontent.Utils.ImageGetterImpl;

public class ThemeDetailActivity extends BaseActivity {

    private Data data;
    private String title;
    private CommonImageLoader commonImageLoader;

    private TextView tv_title, tv_author, tv_date, tv_content;
    private ImageView iv_theme;

    @Override
    public int getLayoutId() {
        return R.layout.activity_theme_detail;
    }

    @Override
    public void initViews() {
        tv_title = findView(R.id.tv_title);
        tv_author = findView(R.id.tv_author);
        tv_date = findView(R.id.tv_date);
        tv_content = findView(R.id.tv_content);
        iv_theme = findView(R.id.iv_theme);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra("title");
        setTitle(title);
        setTitleCanBack();

        commonImageLoader = CommonImageLoader.getInstance(this);

        initDetailViews();

    }

    @Override
    public void processClick(View v) {

    }

    /**
     *
     */
    private void initDetailViews() {
        data = getIntent().getParcelableExtra("data");
        tv_title.setText(data.getTitle());
        tv_author.setText(data.getAuthor());
        tv_content.setText(Html.fromHtml(data.getContent(),
                new ImageGetterImpl(tv_content, data.getContent(),
                        DensityUtils.getDisplayWidth(this),
                        DensityUtils.getDisplayHeight(this)), null));
        tv_date.setText(DateUtils.convertTime(data.getCreate_time()));
        commonImageLoader.displayImage(data.getThumb(), iv_theme);
    }

}
