package com.hmt.analytics;

import android.webkit.WebView;
import com.hmt.analytics.common.CommonUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class HMTHttpFilter {
    public static URLConnection openConnection(URLConnection uRLConnection) {
        try {
            HMTAgent.onReq(uRLConnection.getURL().toString(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uRLConnection;
    }

    public static URLConnection openConnectionWithProxy(URLConnection uRLConnection) {
        try {
            HMTAgent.onReq(uRLConnection.getURL().toString(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uRLConnection;
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {
        HttpClient(httpUriRequest);
        return httpClient.execute(httpUriRequest);
    }

    public static void HttpClient(HttpRequest httpRequest) {
        if (httpRequest instanceof HttpGet) {
            HttpGet httpGet = (HttpGet) httpRequest;
            HMTAgent.onReq(httpGet.getURI().toString(), httpGet.getMethod());
        } else if (httpRequest instanceof HttpPost) {
            HttpPost httpPost = (HttpPost) httpRequest;
            HMTAgent.onReq(httpPost.getURI().toString(), httpPost.getMethod());
        }
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws ClientProtocolException, IOException {
        HttpClient(httpRequest);
        return httpClient.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws ClientProtocolException, IOException {
        HttpClient(httpUriRequest);
        return httpClient.execute(httpUriRequest, httpContext);
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws ClientProtocolException, IOException {
        HttpClient(httpRequest);
        return httpClient.execute(httpHost, httpRequest);
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws ClientProtocolException, IOException {
        HttpClient(httpUriRequest);
        return httpClient.execute(httpUriRequest, responseHandler, httpContext);
    }

    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws ClientProtocolException, IOException {
        HttpClient(httpRequest);
        return httpClient.execute(httpHost, httpRequest, httpContext);
    }

    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws ClientProtocolException, IOException {
        HttpClient(httpRequest);
        return httpClient.execute(httpHost, httpRequest, responseHandler);
    }

    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws ClientProtocolException, IOException {
        HttpClient(httpUriRequest);
        return httpClient.execute(httpUriRequest, responseHandler);
    }

    public static void loadUrl(WebView webView, String str, Map<String, String> map) {
        if (!CommonUtil.isSetWebViewClient(webView)) {
            webView.setWebViewClient(new HMTWebViewClient());
        }
        webView.loadUrl(str, map);
    }

    public static void loadUrl(WebView webView, String str) {
        if (!CommonUtil.isSetWebViewClient(webView)) {
            webView.setWebViewClient(new HMTWebViewClient());
        }
        webView.loadUrl(str);
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        HMTAgent.onReq(request.urlString(), request.method());
        return okHttpClient.newCall(request);
    }
}
