package com.hensen.teachercontent.Activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.Controller.ActivityController;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.teachercontent.Adapter.VideoAdapter;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;

import java.util.List;

public class VideoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<Data> dataList;
    private ListView lv_video;
    private VideoAdapter adapter;
    private ActivityController activityController;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void initViews() {
        lv_video = findView(R.id.lv_video);
    }

    @Override
    public void initListener() {
        lv_video.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("微视频");
        setTitleCanBack();

        activityController = ActivityController.getInstance(this);

        initVideoViews();
    }


    @Override
    public void processClick(View v) {

    }

    /**
     *
     */
    private void initVideoViews() {
        RequestCenter.requestVideoData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                adapter = new VideoAdapter(VideoActivity.this, dataList);
                lv_video.setAdapter(adapter);
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }

    //http://218.15.154.154:8081/upload/2017/06/06/59367da1af5ba.rmvb
    //http://218.15.154.154/teacher/upload/2017/06/06/59367da1af5ba.rmvb
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Data data = dataList.get(position);
        activityController.startVideoActivity(data.getThumb(), data.getTitle());
    }
}
