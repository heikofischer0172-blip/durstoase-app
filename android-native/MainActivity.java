package de.durstoase.shop;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
  private WebView webView;
  private static final String SHOP = "https://www.durstoase.de/";

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    webView = new WebView(this);
    setContentView(webView);
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDomStorageEnabled(true);
    settings.setAllowFileAccess(false);
    settings.setAllowContentAccess(false);
    CookieManager.getInstance().setAcceptCookie(true);
    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Uri uri = request.getUrl();
        String host = uri.getHost();
        if (("https".equalsIgnoreCase(uri.getScheme())) &&
            host != null && (host.equalsIgnoreCase("durstoase.de") || host.equalsIgnoreCase("www.durstoase.de"))) {
          return false;
        }
        try { startActivity(new Intent(Intent.ACTION_VIEW, uri)); }
        catch (Exception ex) { Toast.makeText(MainActivity.this, "Link kann nicht geöffnet werden", Toast.LENGTH_SHORT).show(); }
        return true;
      }
    });
    if (savedInstanceState == null) webView.loadUrl(SHOP);
    else webView.restoreState(savedInstanceState);
  }

  @Override protected void onSaveInstanceState(Bundle state) {
    webView.saveState(state);
    super.onSaveInstanceState(state);
  }

  @Override public void onBackPressed() {
    if (webView != null && webView.canGoBack()) webView.goBack();
    else super.onBackPressed();
  }

  @Override protected void onDestroy() {
    if (webView != null) { webView.destroy(); webView = null; }
    super.onDestroy();
  }
}
