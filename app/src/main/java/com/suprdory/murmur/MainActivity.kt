package com.suprdory.murmur

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
//        myWebView.loadUrl("https://suprdory.github.io/murmur/")

        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .addPathHandler("/res/", WebViewAssetLoader.ResourcesPathHandler(this))
            .build()
        myWebView.webViewClient = LocalContentWebViewClient(assetLoader)
        myWebView.loadUrl("https://appassets.androidplatform.net/assets/index.html")
    }

}

private class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) : WebViewClientCompat() {
    @RequiresApi(21)
    override fun shouldInterceptRequest(
        view: WebView,
        request: WebResourceRequest
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(request.url)
    }

    // to support API < 21
    override fun shouldInterceptRequest(
        view: WebView,
        url: String
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(Uri.parse(url))
    }
}



