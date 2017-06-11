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
import com.hensen.teachercontent.Adapter.ThemeAdapter;
import com.hensen.teachercontent.Api.RequestCenter;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;
import com.hensen.teachercontent.R;

import java.util.List;

public class ThemeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv_theme;
    private ThemeAdapter adapter;
    private List<Data> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_theme;
    }

    @Override
    public void initViews() {
        lv_theme = findView(R.id.lv_theme);
    }

    @Override
    public void initListener() {
        lv_theme.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("专题介绍");
        setTitleCanBack();

        initThemeViews();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     *
     */
    private void initThemeViews() {
        RequestCenter.requestThemeData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DataList data = (DataList) responseObj;
                dataList = data.getDataList();

                if (dataList.isEmpty() || dataList == null) {
                    showToast("获取不到数据");
                    return;
                }

                adapter = new ThemeAdapter(ThemeActivity.this, dataList);
                lv_theme.setAdapter(adapter);
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
