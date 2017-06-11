package com.hensen.teachercontent.Activity;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.Utils.DensityUtils;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.R;
import com.hensen.teachercontent.Utils.ImageGetterImpl;

public class CourseDetailActivity extends BaseActivity {

    private Data data;
    private TextView tv_name, tv_title, tv_content;
    private ImageView iv_type;
    private Button bt_download;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    public void initViews() {
        tv_name = findView(R.id.tv_name);
        tv_title = findView(R.id.tv_title);
        tv_content = findView(R.id.tv_content);
        iv_type = findView(R.id.iv_type);
        bt_download = findView(R.id.bt_download);
    }

    @Override
    public void initListener() {
        setOnClick(bt_download);
    }

    @Override
    public void initData() {
        setTitle("课件下载");
        setTitleCanBack();

        initCourseDetailViews();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_download:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getThumb()));
                startActivity(intent);
                break;
        }
    }

    /**
     *
     */
    private void initCourseDetailViews() {
        data = getIntent().getParcelableExtra("course");
        tv_name.setText(data.getSmall_title());
        tv_title.setText(data.getTitle());
        tv_content.setText(Html.fromHtml(data.getContent(),
                new ImageGetterImpl(tv_content, data.getContent(),
                        DensityUtils.getDisplayWidth(this),
                        DensityUtils.getDisplayHeight(this)), null));
        if (data.getTitle().endsWith(".doc") || data.getTitle().endsWith(".docx")) {
            iv_type.setBackgroundResource(R.drawable.ic_course_word);
        } else if (data.getTitle().endsWith(".xls") || data.getTitle().endsWith(".xlsx")) {
            iv_type.setBackgroundResource(R.drawable.ic_course_excel);
        } else if (data.getTitle().endsWith(".pdf")) {
            iv_type.setBackgroundResource(R.drawable.ic_course_pdf);
        } else if (data.getTitle().endsWith(".swf")) {
            iv_type.setBackgroundResource(R.drawable.ic_course_flash);
        } else if (data.getTitle().endsWith(".ppt") || data.getTitle().endsWith(".pptx")) {
            iv_type.setBackgroundResource(R.drawable.ic_course_ppt);
        } else {
            iv_type.setBackgroundResource(R.drawable.ic_course_notype);
        }
    }
}
