package co.conexionmetropolitana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Console;

public class MainActivity extends AppCompatActivity {
    /* Variable declaration */
    public ImageView imageView;
    public static final String urlBase = "http://conexionmetropolitana.co/node/4860";
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
                /* Sitio principal de las salas virtuales (BigBlueButton) */
                if(Uri.parse(url).getHost().endsWith("salasvp.cetiia.com.co")){
                    MainActivity.this.finish();//cerrar la app principal
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);//lanzar el navegador
                }else{
                    /* Los dominios principales de esta app */
                    if(Uri.parse(url).getHost().endsWith("mitiendametropolitana.com")||Uri.parse(url).getHost().endsWith("cetiia.com.co")){
                        imageView.setVisibility(View.VISIBLE);
                        Snackbar.make(webView, "Cargando...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        return false;
                    }else{
                        /* Control de salida hacia redes sociales y acopi, amva */
                        if(Uri.parse(url).getHost().endsWith("facebook.com")||Uri.parse(url).getHost().endsWith("instagram.com")||Uri.parse(url).getHost().endsWith("twitter.com")||Uri.parse(url).getHost().endsWith("wa.me")||Uri.parse(url).getHost().endsWith("acopiantioquia.org")||Uri.parse(url).getHost().endsWith("metropol.gov.co")){
                            confirmarSalir(view,url);
                        }
                    }
                }



                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                imageView.setVisibility(View.INVISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
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

    /*  */
    public void confirmarSalir(View view, String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Saliendo de conexion metropolitana");
        builder.setMessage("Desea salir de la app?");
        builder.setPositiveButton("Salir", (dialog, which) -> {
            /* Se sale de la app */

        });
        builder.setNegativeButton("Quedarme", (dialog, which) -> {
            /*  */
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}