package com.shirwa.guidebook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shirwa.guidebook.R;

/**
 * Created by shirwamohamed on 2/10/15.
 */
public class GuideWebViewFragment extends Fragment {
    String url;
    WebView webView;

    public GuideWebViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_web_view_fragment, null);
        webView = (WebView) view;
        webView.setWebViewClient(new WebViewClient());
        return view;
    }

    public void setUrl(String url) {
        this.url = url;
        webView.loadUrl(url);
    }
}
