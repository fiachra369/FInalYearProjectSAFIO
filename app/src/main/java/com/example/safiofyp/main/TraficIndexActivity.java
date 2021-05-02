package com.example.safiofyp.main;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safiofyp.R;

public class TraficIndexActivity extends AppCompatActivity {

    WebView webView;
    Boolean isLoaded = false;

    TextView last_update_txtView,congestion_level,morning_rush,evening_rush,time_to_avoid,safe_or_not;
    ProgressBar progressBar1;
    ScrollView scrollView_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_index);

        last_update_txtView = findViewById(R.id.last_update_txtView);
        congestion_level = findViewById(R.id.congestion_level);
        morning_rush = findViewById(R.id.morning_rush);
        evening_rush = findViewById(R.id.evening_rush);
        time_to_avoid = findViewById(R.id.location);
        safe_or_not = findViewById(R.id.safe_or_not);

        scrollView_root = findViewById(R.id.scrollView_root);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }

        progressBar1 = findViewById(R.id.progressBar1);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        String ua = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        webView.getSettings().setUserAgentString(ua);
        webView.addJavascriptInterface(new MyJavaScriptInterface(this, this), "HtmlViewer");
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebChromeClient(new MyWebChromeClient(getApplicationContext(), this));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                return super.shouldInterceptRequest(view, request);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:(function() { var z=document.getElementsByClassName('CityPage__last-updated text-noway'); " +
                        "var cong_live=document.getElementsByClassName('live-number');" +
                        "var morning_evening_rush=document.getElementsByClassName('PeakCongestion__congestion');" +
                        "var peak_hour=document.getElementsByClassName(`StatWeekHours__worst`)[0].textContent;" +
//                        "console.log(document.getElementsByClassName('StatWeekHours__worst')[0].textContent);" +
                        "window.HtmlViewer.setAllData(z[0].textContent,cong_live[0].textContent,morning_evening_rush[0].textContent,morning_evening_rush[1].textContent,peak_hour);})()");

            }
        });

        getDetails();

    }

    public void getDetails(){
        webView.loadUrl("https://www.tomtom.com/en_gb/traffic-index/dublin-traffic/");
    }

}

class MyJavaScriptInterface {
    private Context ctx;
    TraficIndexActivity mainActivity;


    MyJavaScriptInterface(Context ctx, TraficIndexActivity mainActivity) {
        this.ctx = ctx;
        this.mainActivity = mainActivity;
    }

    @JavascriptInterface
    public void setAllData(String last_update,String cong_live,String morning_rush,String evening_rush,String time_avoid) {
//        final String html_ = html;

        Log.d("last_update : ", time_avoid);


        mainActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                mainActivity.progressBar1.setVisibility(View.GONE);
                mainActivity.scrollView_root.setVisibility(View.VISIBLE);
                // Stuff that updates the UI
                mainActivity.last_update_txtView.setText(last_update.replace("Last updated: ", "Last Updated : " + ""));
                mainActivity.congestion_level.setText(cong_live);
                mainActivity.morning_rush.setText(morning_rush);
                mainActivity.evening_rush.setText(evening_rush);
                mainActivity.time_to_avoid.setText(time_avoid);


                int cong_val = Integer.parseInt(cong_live.replace("%",""));
                if(cong_val >= 30){
                    mainActivity.safe_or_not.setText("Roads are not safe");
                }
                else{
                    mainActivity.safe_or_not.setText("Roads are safe");
                }
            }
        });



    }

    @JavascriptInterface
    public void wrongID() {
        Toast.makeText(ctx, "You entered wrong ID", Toast.LENGTH_LONG).show();

    }

}

class MyWebChromeClient extends WebChromeClient {

    Context context;
    TraficIndexActivity mainActivity;

    MyWebChromeClient(Context context, TraficIndexActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
    }


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.d("LogTag", message);
        String id = message;
        result.confirm();

        return true;
    }
}
