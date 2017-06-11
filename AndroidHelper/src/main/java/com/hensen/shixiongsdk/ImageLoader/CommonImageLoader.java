package com.hensen.shixiongsdk.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @author 许英俊 2017/4/13
 */
public class CommonImageLoader {

    private static final int THREAD_COUNT = 2;
    private static final int PRIORITY = 2;
    private static final int MEMORY_CACHE_SIZE = 2 * 1024 * 1024;
    private static final int DISK_CACHE_SIZE = 30 * 1024 * 1024;
    private static final int CONNECT_TIME_OUT = 5 * 1000;
    private static final int READ_TIME_OUT = 30 * 1000;

    private static CommonImageLoader commonImageLoader;
    private ImageLoader imageLoader = null;

    public CommonImageLoader(Context context) {
        initDefaultConfiguration(context);
    }

    /**
     * Returns singleton class instance
     */
    public static CommonImageLoader getInstance(Context context) {
        if (commonImageLoader == null) {
            synchronized (CommonImageLoader.class) {
                if (commonImageLoader == null) {
                    commonImageLoader = new CommonImageLoader(context);
                }
            }
        }
        return commonImageLoader;
    }

    /**
     * Initializes ImageLoader instance with configuration
     *
     * @param context
     */
    private void initDefaultConfiguration(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(MEMORY_CACHE_SIZE)
                .diskCacheSize(DISK_CACHE_SIZE)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(getDefaultOptions())
                .imageDownloader(new BaseImageDownloader(context, CONNECT_TIME_OUT, READ_TIME_OUT))
                .build();
        ImageLoader.getInstance().init(configuration);
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * Returns the default ImageLoader displayImageOptions
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .decodingOptions(new BitmapFactory.Options())
                .resetViewBeforeLoading(true)
                .build();
        return defaultOptions;
    }

    /**
     * Load the display image
     *
     * @param url
     * @param imageView
     * @param options
     * @param loadingListener
     */
    public void displayImage(String url, ImageView imageView,
                             DisplayImageOptions options,
                             ImageLoadingListener loadingListener) {
        imageLoader.displayImage(url, imageView, options, loadingListener);
    }

    /**
     * Load the display image
     *
     * @param url
     * @param imageView
     * @param loadingListener
     */
    public void displayImage(String url, ImageView imageView,
                             ImageLoadingListener loadingListener) {
        displayImage(url, imageView, null, loadingListener);
    }

    /**
     * Load the display image
     *
     * @param url
     * @param imageView
     */
    public void displayImage(String url, ImageView imageView) {
        displayImage(url, imageView, null);
    }


}
