package com.hoppenichu.roughs.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hoppenichu.roughs.R
import com.hoppenichu.roughs.Service.Model.Project

/**
 * Created by Takeru on 11/9/15.
 */
class ProjectActivity : AppCompatActivity() {

    companion object {
        val INTENT_EXTRA_ROJECT = "PROJECT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        val project = intent.getSerializableExtra(INTENT_EXTRA_ROJECT) as Project
        val webView = findViewById(R.id.web_view) as WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setAppCacheEnabled(true)
        webView.setWebViewClient(object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript("$(\"#install\").hide()", null)
                view?.evaluateJavascript("new flviewer.Viewer(flviewerPrototypeBootstrapData, $(\"#flviewer\"), !0)", null)
                view?.evaluateJavascript("$(\"#flviewer_device_wrap\").show()", null)
            }
        })
        webView.loadUrl(project.project_url)

    }
}