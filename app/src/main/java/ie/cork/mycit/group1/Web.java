package ie.cork.mycit.group1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String url = intent.getExtras().getString("url");

        setTitle(title);



        WebView webView = (WebView)findViewById(R.id.webViewURL);

        WebSettings settings = webView.getSettings();

        //setup auto zoom for embedded-browser
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        //allow zoom in and out for embedded-browser
        webView.getSettings().setBuiltInZoomControls(true);

        //allow Java Script
        webView.getSettings().setJavaScriptEnabled(true);

        //setup to use only embedded-browser
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

}
