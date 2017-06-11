package com.hensen.shixiongsdk.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hensen.shixiongsdk.Browser.app.BrowserActivity;
import com.hensen.shixiongsdk.PhotoView.app.PhotoViewActivity;
import com.hensen.shixiongsdk.Video.app.VideoActivity;
import com.hensen.shixiongsdk.Zxing.app.CaptureActivity;

import java.util.ArrayList;

/**
 * @author 许英俊 2017/4/20
 */
public class ActivityController {

    private Intent intent;
    public static final int REQUEST_CODE = 0x01;

    private Context mContext;
    public static ActivityController controller;

    public ActivityController(Context context) {
        this.mContext = context;
    }

    public static ActivityController getInstance(Context context) {
        if (controller == null) {
            synchronized (ActivityController.class) {
                if (controller == null) {
                    controller = new ActivityController(context);
                }
            }
        }
        return controller;
    }

    /**
     * Open the scan page and return the results
     */
    public void startCaptureActivity() {
        intent = new Intent(mContext, CaptureActivity.class);
        ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Open the diagram page according to the current location
     *
     * @param photo_urls
     * @param position
     */
    public void startPhotoViewActivity(ArrayList<String> photo_urls, int position) {
        intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putStringArrayListExtra(PhotoViewActivity.COMMON_PHOTO_URLS, photo_urls);
        intent.putExtra("position", position);
        mContext.startActivity(intent);
    }

    /**
     * The default location is 1, which opens the view page
     *
     * @param photo_urls
     */
    public void startPhotoViewActivity(ArrayList<String> photo_urls) {
        startPhotoViewActivity(photo_urls, 1);
    }

    /**
     * Open the browser page
     *
     * @param url
     */
    public void startWebViewActivity(String url) {
        intent = new Intent(mContext, BrowserActivity.class);
        intent.putExtra(BrowserActivity.COMMON_WEB_URL, url);
        mContext.startActivity(intent);
    }


    /**
     * Open the video player page
     *
     * @param url
     * @param title
     */
    public void startVideoActivity(String url, String title) {
        intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra(VideoActivity.COMMON_VIDEO_URL, url);
        intent.putExtra(VideoActivity.COMMON_VIDEO_TITLE, title);
        mContext.startActivity(intent);
    }


}
