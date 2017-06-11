package com.hensen.teachercontent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.teachercontent.Adapter.TeachAdapter;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;

import java.io.Serializable;
import java.util.List;

public class TeachActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private TeachAdapter adapter;
    private List<Data> dataList;
    private GridView gv_teach;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teach;
    }

    @Override
    public void initViews() {
        gv_teach = findView(R.id.gv_teach);
    }

    @Override
    public void initListener() {
        gv_teach.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("教学团队");
        setTitleCanBack();

        initTeachViews();
    }


    @Override
    public void processClick(View v) {

    }


    /**
     *
     */
    private void initTeachViews() {
        RequestCenter.requestTeachsData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                adapter = new TeachAdapter(TeachActivity.this, dataList);
                gv_teach.setAdapter(adapter);
            }

            @Override
            public void onFailure(OkHttpException exception) {
                Log.e("error", exception.getEmsg().toString());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent  = new Intent(this,TeachDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("teach",dataList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
