package com.tencent.midas.jsbridge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.LinearLayout.LayoutParams;
import com.pay.tool.APMidasCommMethod;
import com.pay.tool.APMidasTools;
import com.tencent.connect.common.Constants;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.comm.APProgressDialog;
import com.tencent.midas.data.APPluginDataInterface;
import java.util.HashMap;

public class APWebJSBridgeActivity extends Activity {
    private final String a = "http://youxi.vip.qq.com/m/act/";
    private final String b = "/index.html";
    private IAPWebViewCallback c = new IAPWebViewCallback(this) {
        final /* synthetic */ APWebJSBridgeActivity a;

        {
            this.a = r1;
        }

        public boolean WebChromeClientJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return true;
        }

        public boolean WebChromeClientJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return true;
        }

        public void WebViewClientPageFinished(WebView webView, String str) {
            if (!this.a.isFinishing() && this.a.waitDialog != null && this.a.waitDialog.isShowing()) {
                this.a.waitDialog.dismiss();
            }
        }

        public void WebViewClientPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.a.waitDialog.show();
        }

        public void WebViewClientReceivedError(WebView webView, int i, String str, String str2) {
            if (!this.a.isFinishing() && this.a.waitDialog != null && this.a.waitDialog.isShowing()) {
                this.a.waitDialog.dismiss();
            }
        }
    };
    protected APWebView mWebView = null;
    protected ProgressDialog waitDialog;

    private String a() {
        String str = "";
        if (TextUtils.isEmpty(APPluginDataInterface.singleton().getDiscountUrl())) {
            str = "http://youxi.vip.qq.com/m/act/" + APPluginDataInterface.singleton().getOfferId() + "/index.html";
            a(this.mWebView.getWebView(), str);
        } else {
            a(this.mWebView.getWebView(), APPluginDataInterface.singleton().getDiscountUrl());
            str = APPluginDataInterface.singleton().getDiscountUrl();
        }
        APLog.e("constructUrl", str);
        HashMap hashMap = new HashMap();
        hashMap.put("offerId", APPluginDataInterface.singleton().getOfferId());
        hashMap.put("openId", APPluginDataInterface.singleton().getOpenId());
        hashMap.put("openKey", APPluginDataInterface.singleton().getOpenKey());
        hashMap.put("sessionId", APPluginDataInterface.singleton().getSessionId());
        hashMap.put("sessionType", APPluginDataInterface.singleton().getSessionType());
        hashMap.put(Constants.PARAM_PLATFORM_ID, APPluginDataInterface.singleton().getPf());
        hashMap.put("pfKey", APPluginDataInterface.singleton().getPfKey());
        hashMap.put("zoneId", APPluginDataInterface.singleton().getZoneId());
        if (!str.contains("?")) {
            str = str + "?";
        } else if (!str.endsWith("?")) {
            str = str + "&";
        }
        str = str + APMidasTools.map2UrlParams(hashMap);
        APLog.i("constructUrl", str);
        return str;
    }

    private void a(WebView webView, String str) {
        int i = 0;
        LayoutParams layoutParams = (LayoutParams) webView.getLayoutParams();
        APLog.i("webviewclient == ", "updateWebViewSize ");
        Object urlParamsValue = APMidasTools.getUrlParamsValue(str, "mpwidth");
        int intValue = !TextUtils.isEmpty(urlParamsValue) ? Integer.valueOf(urlParamsValue).intValue() : 0;
        Object urlParamsValue2 = APMidasTools.getUrlParamsValue(str, "mpheight");
        if (!TextUtils.isEmpty(urlParamsValue2)) {
            i = Integer.valueOf(urlParamsValue2).intValue();
        }
        if (i != 0 && intValue != 0) {
            layoutParams.width = APMidasCommMethod.dip2px(this, (float) intValue);
            layoutParams.height = APMidasCommMethod.dip2px(this, (float) i);
            webView.setLayoutParams(layoutParams);
        }
    }

    protected ProgressDialog createDialog() {
        ProgressDialog aPProgressDialog = new APProgressDialog(this);
        aPProgressDialog.setMessage("请稍候...");
        return aPProgressDialog;
    }

    public void initUI() {
        setContentView(APMidasCommMethod.getLayoutId(this, "unipay_layout_activity_web"));
        this.mWebView = new APWebView(this, (WebView) findViewById(APMidasCommMethod.getId(this, "unipay_id_WebView")), this.c);
        this.mWebView.loadUrl(a());
        this.waitDialog = createDialog();
        this.waitDialog.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ APWebJSBridgeActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        APMidasCommMethod.pushActivity(this);
        initUI();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            APMidasCommMethod.payErrorCallback(100, "返回");
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }
}
