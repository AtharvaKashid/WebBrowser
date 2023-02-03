package com.example.webbrowser

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var webView:WebView
    private lateinit var editText: EditText;
    private lateinit var back_btn:ImageButton
    private lateinit var search:ImageButton
    private lateinit var input:String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView=findViewById<WebView>(R.id.webView)
        editText=findViewById<EditText>(R.id.edt1)
        back_btn=findViewById<ImageButton>(R.id.back_btn)
        search=findViewById<ImageButton>(R.id.search)

       search.setOnClickListener {
           input=editText.text.toString()
           if(input.isEmpty())
           {
               Toast.makeText(this,"Enter the URl",Toast.LENGTH_SHORT).show()
           }else{
               webView.loadUrl(input)
               webView.settings.javaScriptEnabled=true
               webView.canGoBack()
               webView.webViewClient=WebClient(this)
           }
       }
        back_btn.setOnClickListener {
            webView.goBack()
        }

    }
    class WebClient internal constructor(private  val activity:Activity):WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }
}