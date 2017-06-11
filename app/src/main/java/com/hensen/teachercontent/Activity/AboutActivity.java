package com.hensen.teachercontent.Activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.shixiongsdk.Utils.DensityUtils;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;
import com.hensen.teachercontent.Utils.ImageGetterImpl;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class AboutActivity extends BaseActivity {

    private ImageView iv_content;
    private TextView tv_content;

    private List<Data> dataList;
    private Data mData;

    private CommonImageLoader commonImageLoader;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {
        iv_content = findView(R.id.iv_content);
        tv_content = findView(R.id.tv_content);
    }

    @Override
    public void initData() {
        setTitle("关于我们");
        setTitleCanBack();

        commonImageLoader = CommonImageLoader.getInstance(this);
        initAboutViews();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     *
     */
    private void initAboutViews() {
        RequestCenter.requestAboutData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                mData = dataList.get(0);
                commonImageLoader.displayImage(mData.getThumb(), iv_content);

                tv_content.setText(Html.fromHtml(mData.getContent(),
                        new ImageGetterImpl(tv_content, mData.getContent(),
                                DensityUtils.getDisplayWidth(AboutActivity.this),
                                DensityUtils.getDisplayHeight(AboutActivity.this)), null));
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }
}
