package com.xunchijn.sichuan.common.view;

import android.webkit.WebView;

import com.xunchijn.sichuan.R;
import com.xunchijn.sichuan.base.AbsBaseActivity;
import com.xunchijn.sichuan.util.TitleFragment;

public class AboutUsActivity extends AbsBaseActivity {
    private AboutUsFragment mAboutUsFragment;

    @Override
    public void initTitle() {
        TitleFragment titleFragment = TitleFragment.newInstance("关于我们", true, false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_title, titleFragment)
                .show(titleFragment)
                .commit();
    }

    @Override
    public void initContent() {
        mAboutUsFragment = new AboutUsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_container, mAboutUsFragment)
                .show(mAboutUsFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        WebView webView = mAboutUsFragment.getWebView();
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
