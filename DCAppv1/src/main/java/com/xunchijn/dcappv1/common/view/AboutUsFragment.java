package com.xunchijn.dcappv1.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xunchijn.dcappv1.R;
import com.xunchijn.dcappv1.base.BaseConfig;

public class AboutUsFragment extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mWebView = view.findViewById(R.id.web_view);
        mWebView.loadUrl(BaseConfig.WEBSITE);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setSupportZoom(true);
    }

    public WebView getWebView() {
        return mWebView;
    }
}
