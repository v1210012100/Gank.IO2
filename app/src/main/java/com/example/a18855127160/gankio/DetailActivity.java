package com.example.a18855127160.gankio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.squareup.picasso.Picasso;

import Net.AllResult;
import Net.GankUrl;


/**
 * 显示文章的Activity
 */
public class DetailActivity extends AppCompatActivity {
   private WebView webView;
   private NumberProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        AllResult.Results meiziResults = (AllResult.Results) intent.getSerializableExtra("girls");
        final AllResult.Results dataResults = (AllResult.Results) intent.getSerializableExtra("articles");

        //设置Activity 切换动画
        switch (dataResults.getType()){
            case GankUrl.FLAG_Expand:
                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                getWindow().setEnterTransition(new Fade());
                break;
            case GankUrl.FLAG_Recommend:
                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));
                getWindow().setReenterTransition(new Slide(Gravity.START)) ;
                break;
        }
        setContentView(R.layout.detail_layout);

        //设置ToolsBar 和collapsingToolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView mImage = (ImageView) findViewById(R.id.Image);
        collapsingToolbar.setTitle(dataResults.getType());
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.accent));
        String url = meiziResults.getUrl();

        //加载头部图片
        Picasso.with(this)
                .load(url)
                .error(R.drawable.error)
                .centerCrop()
                .fit()
                .into(mImage);

        //设置内容webView 和加载进度条
        webView= (WebView) findViewById(R.id.webView);
        progressBar= (NumberProgressBar) findViewById(R.id.progressbar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(dataResults.getUrl());

        //设置返回导航按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                webView.setVisibility(View.GONE);

            }
        });

        //点击FloatingActionButtoun  在浏览器内打开
        FloatingActionButton button= (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri content_url=  Uri.parse(dataResults.getUrl()) ;
                Intent ie = new Intent(Intent.ACTION_VIEW,content_url);
                startActivity(ie);
            }
        });
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
            return;
        }
        webView.setVisibility(View.GONE);
        super.onBackPressed();
    }


}

