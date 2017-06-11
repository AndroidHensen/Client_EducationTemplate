package com.hensen.shixiongsdk.PhotoView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;

/**
 * @author 许英俊 2017/4/19
 */
public class CommonPhotoView {

    private static CommonPhotoView mCommonPhotoView;
    private CommonImageLoader mCommonImageLoader;
    public Context mContext;

    public CommonPhotoView(Context context) {
        this.mContext = context;
        mCommonImageLoader = CommonImageLoader.getInstance(context);
    }

    /**
     * Returns singleton class instance
     */
    public static CommonPhotoView getInstance(Context context) {
        if (mCommonPhotoView == null) {
            synchronized (CommonPhotoView.class) {
                if (mCommonPhotoView == null) {
                    mCommonPhotoView = new CommonPhotoView(context);
                }
            }
        }
        return mCommonPhotoView;
    }

    public void displayPhotoView(String url, ImageView photoView) {
        mCommonImageLoader.displayImage(url, photoView);
    }

    public ImageView displayPhotoView(String url) {
        ImageView photoView = new PhotoView(mContext);
        displayPhotoView(url, photoView);
        return photoView;
    }

    public View displayPhotoView(String url, ViewGroup viewPager) {
        ImageView photoView = displayPhotoView(url);
        viewPager.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

}
