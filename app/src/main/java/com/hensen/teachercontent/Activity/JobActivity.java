package com.hensen.teachercontent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class JobActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv_job;
    private SimpleListAdapter adapter;
    private List<Data> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_job;
    }

    @Override
    public void initViews() {
        lv_job = findView(R.id.lv_job);
    }

    @Override
    public void initListener() {
        lv_job.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("就业知识");
        setTitleCanBack();

        initJobViews();
    }

    @Override
    public void processClick(View v) {
        lv_job.setOnItemClickListener(this);
    }


    /**
     *
     */
    private void initJobViews() {
        RequestCenter.requestJobData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                adapter = new SimpleListAdapter(JobActivity.this, dataList);
                lv_job.setAdapter(adapter);
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ThemeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", dataList.get(position));
        intent.putExtras(bundle);
        intent.putExtra("title", dataList.get(position).getTitle());
        startActivity(intent);
    }
}
