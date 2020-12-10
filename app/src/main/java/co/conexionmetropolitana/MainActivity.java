package co.conexionmetropolitana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    /* Variable declaration */
    public ImageView imageView;
    public static final String urlBase = "http://mitiendametropolitana.com/app";
    public WebView webView;
    /* Start app */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Start custom code */
        imageView = findViewById(R.id.splash);
        webView = findViewById(R.id.web_view);
        //imageView.setVisibility(View.INVISIBLE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(Uri.parse(url).getHost().endsWith("mitiendametropolitana.com")||Uri.parse(url).getHost().endsWith("cetiia.com.co")){
                    imageView.setVisibility(View.VISIBLE);
                    Snackbar.make(webView, "Cargando...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return false;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                imageView.setVisibility(View.INVISIBLE);
            }
        });
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        Snackbar.make(webView, "Cargando...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        webView.loadUrl(urlBase);
    }
    /* Navigate Up */
    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        //finish();
        onBackPressed();
        return true;
    }
    /* Back Pressed */
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    /* Start custom methods */
}