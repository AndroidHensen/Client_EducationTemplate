package com.hensen.shixiongsdk.PhotoView.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.hensen.shixiongsdk.PhotoView.CommonPhotoView;

import java.util.ArrayList;

/**
 * @author 许英俊 2017/4/14
 */
public class PhotoViewAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> list;
    private CommonPhotoView mCommonPhotoView;

    public PhotoViewAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.list = list;
        this.mCommonPhotoView = CommonPhotoView.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        return mCommonPhotoView.displayPhotoView(list.get(position), (ViewPager) container);
    }

}
