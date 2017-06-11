package com.hensen.teachercontent.Utils;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.hensen.teachercontent.Api.RequestCenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 许英俊 2017/6/7
 */
public class ImageGetterImpl implements Html.ImageGetter {

    private int width, height;
    private TextView tv;
    private String html;
    private File file;

    public ImageGetterImpl(TextView tv, String html, int width, int height) {
        this.tv = tv;
        this.html = html;
        this.width = width - 80;
        this.height = height;
    }

    @Override
    public Drawable getDrawable(String source) {
        Drawable drawable = null;
        // 区分网络图片和自己服务器图片，如果是服务器图片，则加个根目录
        if (!source.startsWith("http")) {
            source = RequestCenter.ROOT_URL + source;
        }
        //获取图片后缀名作为文件名
        String[] fileName = source.split("/");
        file = new File(Environment.getExternalStorageDirectory(), fileName[fileName.length - 1]);
        // 判断是否以http开头
        if (source.startsWith("http")) {
            // 判断路径是否存在
            if (file.exists()) {
                // 存在即获取drawable
                drawable = Drawable.createFromPath(file.getAbsolutePath());
                // 根据屏幕的宽高比等于图片的宽高比
                height = (width) * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                drawable.setBounds(0, 0, width, height);
            } else {
                // 不存在即开启异步任务加载网络图片
                AsyncLoadNetworkPic networkPic = new AsyncLoadNetworkPic();
                networkPic.execute(source);
            }
        }
        return drawable;
    }

    public class AsyncLoadNetworkPic extends AsyncTask<String, Integer, Void> {
        @Override
        protected Void doInBackground(String... params) {
            // 加载网络图片
            loadNetPic(params);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // 当执行完成后再次为其设置一次
            tv.setText(Html.fromHtml(html, new ImageGetterImpl(tv, html, width, height), null));
        }
        /**
         * 加载网络图片
         */
        private void loadNetPic(String... params) {
            String path = params[0];
            InputStream in = null;
            FileOutputStream out = null;
            try {
                URL url = new URL(path);
                HttpURLConnection connUrl = (HttpURLConnection) url.openConnection();
                connUrl.setConnectTimeout(10000);
                connUrl.setRequestMethod("GET");
                if (connUrl.getResponseCode() == 200) {
                    in = connUrl.getInputStream();
                    out = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                } else {
                    Log.e("TAG", connUrl.getResponseCode() + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
