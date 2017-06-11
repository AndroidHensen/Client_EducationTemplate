package com.hensen.shixiongsdk.Browser.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;

import com.hensen.shixiongsdk.Browser.client.BrowserWebViewClient;
import com.hensen.shixiongsdk.Browser.view.Base64Drawables;
import com.hensen.shixiongsdk.Browser.view.BrowserLayout;
import com.hensen.shixiongsdk.Browser.view.BrowserWebView;

import java.io.ByteArrayInputStream;

public class BrowserActivity extends AppCompatActivity {

    private static final String LOG_TAG = BrowserActivity.class.getSimpleName();

    public static final String COMMON_WEB_URL = "url";
    private BrowserWebView browserWebView;
    private BrowserLayout mLayout;

    private boolean mIsBackFromMarket = false;

    private View mProgress;
    private Button mBackButton;

    private String mUrl;

    private BrowserWebViewClient.Listener mWebClientListener;
    private Base64Drawables mBase64Drawables = new Base64Drawables();

    @Override
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (isValidExtras()) {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mLayout = new BrowserLayout(this.getApplicationContext());
            setContentView(mLayout);

            mProgress = mLayout.getProgressBar();
            mBackButton = mLayout.getBackButton();

            browserWebView = mLayout.getWebView();
            initWebView(browserWebView);

            if (bundle != null) {
                browserWebView.restoreState(bundle);
            } else {
                browserWebView.loadUrl(mUrl);
            }
            initButtonListeners(browserWebView);
        } else {
            finish();
        }
    }

    private boolean isValidExtras() {
        mUrl = getIntent().getStringExtra(COMMON_WEB_URL);
        return !TextUtils.isEmpty(mUrl);
    }

    @Override
    protected final void onPause() {
        super.onPause();
        Log.e(LOG_TAG, "onPause");
        if (browserWebView != null) {
            browserWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        Log.e(LOG_TAG, " onDestroy");
        if (browserWebView != null) {
            browserWebView.clearCache(true);
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "onResume");
        if (mIsBackFromMarket) {
            //finish();
        }
        mIsBackFromMarket = true;
        mLayout.getProgressBar().setVisibility(View.INVISIBLE);
    }

    private void initWebView(BrowserWebView webView) {
        mWebClientListener = initAdBrowserClientListener();
        BrowserWebViewClient client = new BrowserWebViewClient(mWebClientListener);
        webView.setWebViewClient(client);
    }

    private BrowserWebViewClient.Listener initAdBrowserClientListener() {
        return new BrowserWebViewClient.Listener() {

            @Override
            public void onReceiveError() {
                finish();
            }

            @Override
            public void onPageStarted() {
                mProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(boolean canGoBack) {
                mProgress.setVisibility(View.INVISIBLE);
                if (canGoBack) {
                    setImage(mBackButton, mBase64Drawables.getBackActive());
                } else {
                    setImage(mBackButton, mBase64Drawables.getBackInactive());
                }
            }

            @Override
            public void onLeaveApp() {

            }
        };
    }

    @SuppressLint("NewApi")
    private void setImage(Button button, String imageString) {
        if (Build.VERSION.SDK_INT < 16) {
            button.setBackgroundDrawable(decodeImage(imageString));
        } else {
            button.setBackground(decodeImage(imageString));
        }
    }

    private void initButtonListeners(final WebView webView) {

        mLayout.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    mLayout.getProgressBar().setVisibility(View.VISIBLE);
                    webView.goBack();
                }
            }
        });

        mLayout.getCloseButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLayout.getRefreshButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.getProgressBar().setVisibility(View.VISIBLE);
                webView.reload();
            }
        });

        mLayout.getNativeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriString = webView.getUrl();
                if (uriString != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));

                    boolean isActivityResolved = getPackageManager()
                            .resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY) != null
                            ? true : false;
                    if (isActivityResolved) {
                        startActivity(browserIntent);
                    }
                }
            }
        });
    }

    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (browserWebView.canGoBack()) {
                browserWebView.goBack();
                return true;
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mIsBackFromMarket = false;
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        browserWebView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    public BitmapDrawable decodeImage(String base64drawable) {
        byte[] rawImageData = Base64.decode(base64drawable, 0);
        return new BitmapDrawable(null, new ByteArrayInputStream(rawImageData));
    }
}
