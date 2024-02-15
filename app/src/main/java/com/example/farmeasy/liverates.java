package com.example.farmeasy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class liverates extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liverates);

        webView = findViewById(R.id.wbv);

        // Enable JavaScript (optional)
        webView.getSettings().setJavaScriptEnabled(true);

        // Set WebViewClient to handle events inside the WebView (e.g., page navigation)
        webView.setWebViewClient(new WebViewClient());

        // Load the URL
        webView.loadUrl("https://vegetablemarketprice.com/market/telangana/today");
    }

    // Override onBackPressed to handle back navigation in WebView
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}