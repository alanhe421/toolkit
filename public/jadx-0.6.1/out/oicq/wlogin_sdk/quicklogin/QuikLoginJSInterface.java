package oicq.wlogin_sdk.quicklogin;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class QuikLoginJSInterface {
    Activity a;

    public QuikLoginJSInterface(Activity activity) {
        this.a = activity;
    }

    @JavascriptInterface
    public void ptloginCallBack(String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("uin", str);
        intent.putExtra("sig", str2);
        intent.putExtra("isRetFromWeb", true);
        this.a.setResult(-1, intent);
        this.a.finish();
    }
}
