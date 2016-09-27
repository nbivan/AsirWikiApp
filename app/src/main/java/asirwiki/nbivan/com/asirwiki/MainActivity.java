package asirwiki.nbivan.com.asirwiki;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.System.currentTimeMillis;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 *
 * @author nbivan
 * App para el proyecto Asir Wiki
 * Para mas informacion visite asirwiki.hol.es
 *
 */
public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.myWebView = (WebView) this.findViewById(R.id.webview);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        // Provide a WebViewClient for your WebView
        myWebView.setWebViewClient(new MyWebViewClient());

        // Web de la que queremos hacer la Webapp
        myWebView.loadUrl("http://asirwiki.hol.es/");
    }

    @Override
    public void onBackPressed() {

        if (this.myWebView.canGoBack())
            this.myWebView.goBack();
        else
            super.onBackPressed();
    }


    private class MyWebViewClient extends WebViewClient {

        private long loadTime;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (Uri.parse(url).getHost().equals("mega.nz")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;

            }
            if (Uri.parse(url).getHost().equals("mailto:upload@asirwiki.hol.es")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;

            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            this.loadTime = currentTimeMillis();

            // Mostrar toast
            Toast.makeText(getApplicationContext(),
                    "Cargando Web...", LENGTH_SHORT).show();
        }


    }
}
