package com.aoty.matinalnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.aoty.matinalnew.view.daimajia.numberprogressbar.NumberProgressBar;

/**
 * Created by storm on 14-4-15.
 */
public class NewDetailActivity extends BaseActivity {
    public static final String NEW_URL = "new_url";

    private WebView mWebView ;

    private String newUrl ;

    private WebSettings webSettings;

    private NumberProgressBar numberProgressBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_detail_layout);
        Intent data = getIntent() ;
        if(data != null){
            newUrl = data.getStringExtra(NEW_URL) ;
        }
        initView() ;
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.new_detail_wedview);
        numberProgressBar = (NumberProgressBar) findViewById(R.id.new_detail_progress_bar);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        mWebView.loadUrl(newUrl);
        mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    public class MyWebChromeClient extends WebChromeClient
    {
        public MyWebChromeClient(){

        }
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if(newProgress < 100){
                numberProgressBar.setProgress(newProgress);
            }else{
                numberProgressBar.setVisibility(View.GONE);
            }

        }

    }
    @Override
    protected void onResume() {
        if (mWebView != null) {
            mWebView.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

}
