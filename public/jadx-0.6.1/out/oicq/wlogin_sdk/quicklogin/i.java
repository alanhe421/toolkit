package oicq.wlogin_sdk.quicklogin;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONObject;

/* compiled from: QuickLoginWebViewLoader */
final class i extends WebChromeClient {
    final /* synthetic */ QuikLoginJSInterface a;

    i(QuikLoginJSInterface quikLoginJSInterface) {
        this.a = quikLoginJSInterface;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            this.a.ptloginCallBack(jSONObject.getString("uin"), jSONObject.getString("sig"));
        } catch (Exception e) {
            util.LOGI("onJsPrompt failed message " + str2, "");
        }
        return true;
    }
}
