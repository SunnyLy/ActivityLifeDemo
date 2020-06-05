package ext.sunny.com.activitylifedemo;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/5/30
 * @Version V1.0.0
 */
public class WebviewActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebview = findViewById(R.id.webview);
        initWebView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebview.loadUrl("file:////android_asset/demo.html");
    }

    private void initWebView() {
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(false);
        settings.setAllowFileAccess(false);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }
}
