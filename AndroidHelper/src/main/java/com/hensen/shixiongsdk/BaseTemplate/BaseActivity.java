package com.hensen.shixiongsdk.BaseTemplate;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hensen.shixiongsdk.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private SparseArray<View> mViews;
    private Intent intent;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        if (v.getId() == R.id.sdk_finish) {
            finish();
        }
        processClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
    }

    /**
     * Encapsulation findViewById
     *
     * @param viewId
     * @param <E>
     * @return
     */
    public <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * Encapsulation setOnClickListener
     *
     * @param view
     * @param <E>
     */
    public <E extends View> void setOnClick(E view) {
        view.setOnClickListener(this);
    }

    /**
     * Popup toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Open the interface
     *
     * @param cls
     */
    public void startActivity(Class cls) {
        intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * Request permission
     *
     * @param permissions
     */
    public void requestPermissions(String permissions) {
        if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions)) {

            }
            ActivityCompat.requestPermissions(this, new String[]{permissions}, 0);
        }
    }

    /**
     * Set the title bar title
     *
     * @param title
     */
    public void setTitle(String title) {
        TextView tv_title = findView(R.id.sdk_title);
        tv_title.setText(title);
    }

    /**
     * Set the title bar to display the closing button
     */
    public void setTitleCanBack() {
        ImageView iv_finish = findView(R.id.sdk_finish);
        iv_finish.setVisibility(View.VISIBLE);
        setOnClick(iv_finish);
    }
}
