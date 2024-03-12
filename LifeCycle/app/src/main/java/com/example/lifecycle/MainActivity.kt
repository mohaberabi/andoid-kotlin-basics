package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        val editText: EditText = findViewById(R.id.urlEditText)
        val btn: Button = findViewById(R.id.navBtn)

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true

        loadUrl(webView)

        btn.setOnClickListener {
            val url: String = editText.text.toString()
            loadUrl(webView, url = url)
        }

    }


    private fun loadUrl(webView: WebView, url: String = "https://google.com"): Unit {
        webView.loadUrl(url)

    }

    override fun onStart() {
        super.onStart()
        Log.i("lifeCycle", "onStart()")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("lifeCycle", "onRestart()")

    }

    override fun onResume() {

        super.onResume()
        Log.i("lifeCycle", "onResume()")

    }


    override fun onPause() {
        super.onPause()
        Log.i("lifeCycle", "onPause()")

    }

    override fun onStop() {
        Log.i("lifeCycle", "onStop()")

        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("lifeCycle", "onRestart()")

    }

}