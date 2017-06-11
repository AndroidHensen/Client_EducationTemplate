package com.hensen.teachercontent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.teachercontent.Adapter.SimpleListAdapter;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;

import java.util.List;

public class CourseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv_course;
    private SimpleListAdapter adapter;
    private List<Data> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    public void initViews() {
        lv_course = findView(R.id.lv_course);
    }

    @Override
    public void initListener() {
        lv_course.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("教学课件");
        setTitleCanBack();

        initCourseViews();
    }


    @Override
    public void processClick(View v) {

    }


    /**
     *
     */
    private void initCourseViews() {
        RequestCenter.requestCourseData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                adapter = new SimpleListAdapter(CourseActivity.this, dataList);
                lv_course.setAdapter(adapter);
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CourseDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("course", dataList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
