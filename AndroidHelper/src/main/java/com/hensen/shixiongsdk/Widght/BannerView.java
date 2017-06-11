package com.hensen.shixiongsdk.Widght;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hensen.shixiongsdk.ImageLoader.CommonImageLoader;
import com.hensen.shixiongsdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许英俊 2017/4/14
 */
public class BannerView extends RelativeLayout implements View.OnTouchListener, ViewPager.OnPageChangeListener {

    //BannerView
    private ViewPager targetVp;
    private ArrayList<View> bannerList;
    private ArrayList<View> indicationList;

    //ImageLoader
    private CommonImageLoader mImageLoader;

    private Context context;
    private int currentPosition;

    private final static int BANNER_CHANGE = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BANNER_CHANGE:
                    targetVp.setCurrentItem(currentPosition + 1);
                    mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                    break;
            }
        }
    };

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initBannerViews(context, attrs, defStyleAttr);
    }

    private void initBannerViews(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        targetVp = new ViewPager(context);
        targetVp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        targetVp.setOnTouchListener(this);
        targetVp.setOnPageChangeListener(this);
        addView(targetVp);

        mImageLoader = CommonImageLoader.getInstance(context);
    }

    public void initBannerImageForLocal(Activity activity, int[] img_urls) {
        //Initialize the indication Layout
        LinearLayout ly_indication = new LinearLayout(activity);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        addView(ly_indication, params);
        //Initialize the banner view and indication view
        bannerList = new ArrayList<View>();
        indicationList = new ArrayList<View>();
        for (int i = 0; i < img_urls.length; i++) {
            //Initialize the ImageView
            ImageView iv = new ImageView(activity);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundResource(img_urls[i]);
            bannerList.add(iv);
            //Initialize the indication
            ImageView iv2 = new ImageView(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 0, 0);
            iv2.setLayoutParams(lp);
            //Indication point highLight
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.sdk_banner_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.sdk_banner_point_off);
            }
            indicationList.add(iv2);
            ly_indication.addView(iv2);
        }
        //Initialize the Banner Adapter
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(bannerList, activity);
        targetVp.setAdapter(bannerAdapter);
        //Initialize the Banner position
        targetVp.setCurrentItem(bannerList.size() * 1000);
        currentPosition = bannerList.size() * 1000;
        //Auto startBanner
        startBanner();
    }

    public void initBannerImageForNet(Activity activity, List<String> img_urls) {
        //Initialize the indication Layout
        LinearLayout ly_indication = new LinearLayout(activity);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        addView(ly_indication, params);
        //Initialize the banner view and indication view
        bannerList = new ArrayList<View>();
        indicationList = new ArrayList<View>();
        for (int i = 0; i < img_urls.size(); i++) {
            //Initialize the ImageView
            ImageView iv = new ImageView(activity);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageLoader.displayImage(img_urls.get(i), iv);
            bannerList.add(iv);
            //Initialize the indication
            ImageView iv2 = new ImageView(activity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 0, 0);
            iv2.setLayoutParams(lp);
            //Indication point highLight
            if (i == 0) {
                iv2.setBackgroundResource(R.drawable.sdk_banner_point_on);
            } else {
                iv2.setBackgroundResource(R.drawable.sdk_banner_point_off);
            }
            indicationList.add(iv2);
            ly_indication.addView(iv2);
        }
        //Initialize the Banner Adapter
        HomeBannerAdapter bannerAdapter = new HomeBannerAdapter(bannerList, activity);
        targetVp.setAdapter(bannerAdapter);
        //Initialize the Banner position
        targetVp.setCurrentItem(bannerList.size() * 1000);
        currentPosition = bannerList.size() * 1000;
        //Auto startBanner
        startBanner();
    }

    private void bannerPointLight(int currentPoint) {
        for (int i = 0; i < indicationList.size(); i++) {
            if (currentPoint == i) {
                indicationList.get(i).setBackgroundResource(R.drawable.sdk_banner_point_on);
            } else {
                indicationList.get(i).setBackgroundResource(R.drawable.sdk_banner_point_off);
            }
        }
    }

    public void startBanner() {
        mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
    }

    public void endBanner() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        bannerPointLight(position % indicationList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                break;
            case MotionEvent.ACTION_CANCEL:
                mHandler.sendEmptyMessageDelayed(BANNER_CHANGE, 3000);
                break;
        }
        return false;
    }

    public class HomeBannerAdapter extends PagerAdapter {

        private List<View> views;
        private Context context;

        public HomeBannerAdapter(List<View> views, Context context) {
            this.context = context;
            this.views = views;
        }

        public Object instantiateItem(View container, int position) {
            final int currentItem = position % views.size();
            ((ViewPager) container).addView(views.get(currentItem));
            return views.get(currentItem);
        }

        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }
    }
}
