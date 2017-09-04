package oicq.wlogin_sdk.quicklogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.android.tpush.common.Constants;
import java.lang.reflect.Method;
import oicq.wlogin_sdk.tools.util;

public class QuickLoginWebViewLoader {
    static boolean sslErrorProcessed = false;

    public static void disableAccessibility(Context context) {
        if (VERSION.SDK_INT == 17) {
            try {
                AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
                if (accessibilityManager.isEnabled()) {
                    Method declaredMethod = accessibilityManager.getClass().getDeclaredMethod("setState", new Class[]{Integer.TYPE});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(accessibilityManager, new Object[]{Integer.valueOf(0)});
                }
            } catch (Exception e) {
            } catch (Error e2) {
            }
        }
    }

    public static void load(Activity activity, Intent intent) {
        int i;
        QuikLoginJSInterface quikLoginJSInterface = new QuikLoginJSInterface(activity);
        sslErrorProcessed = false;
        long longExtra = intent.getLongExtra("appid", 0);
        String stringExtra = intent.getStringExtra(Constants.FLAG_ACCOUNT);
        int i2 = true == intent.getBooleanExtra("isUserAccountLocked", false) ? 1 : 0;
        if (true == intent.getBooleanExtra("forceWebLogin", false)) {
            i = 1;
        } else {
            i = 0;
        }
        if (0 == longExtra) {
            util.LOGI("QuickLoginWebViewActivity get appid failed");
            return;
        }
        View relativeLayout = new RelativeLayout(activity);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        int i3 = (int) (0.07d * ((double) height));
        View textView = new TextView(activity);
        textView.setBackgroundColor(Color.parseColor("#3F51B5"));
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setText("");
        textView.setLayoutParams(new LayoutParams(-1, i3));
        relativeLayout.addView(textView);
        textView = new Button(activity);
        textView.setBackgroundColor(Color.parseColor("#3F51B5"));
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setText("关闭");
        textView.setPadding(15, textView.getPaddingTop(), 15, textView.getPaddingBottom());
        textView.setLayoutParams(new LayoutParams(-2, i3));
        relativeLayout.addView(textView);
        textView.setOnClickListener(new h(activity));
        try {
            String str;
            String str2;
            textView = new WebView(activity);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, height - i3);
            layoutParams.setMargins(0, i3, 0, 0);
            textView.setLayoutParams(layoutParams);
            relativeLayout.addView(textView);
            activity.setContentView(relativeLayout);
            WebSettings settings = textView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            if (17 <= VERSION.SDK_INT) {
                textView.addJavascriptInterface(new QuikLoginJSInterface(activity), "WTLogin");
                str = "javascript:function wtCB(uin, sig) { WTLogin.ptloginCallBack(uin, sig); }";
            } else {
                str = "javascript:function wtCB(uin, sig) { return prompt(JSON.stringify({ uin:''+uin, sig:''+sig})); }";
            }
            disableAccessibility(activity);
            settings.setJavaScriptEnabled(true);
            if (VERSION.SDK_INT >= 11) {
                textView.removeJavascriptInterface("searchBoxJavaBridge_");
                textView.removeJavascriptInterface("accessibility");
                textView.removeJavascriptInterface("accessibilityTraversal");
            }
            if (17 > VERSION.SDK_INT) {
                textView.setWebChromeClient(new i(quikLoginJSInterface));
            }
            textView.setWebViewClient(new j(activity, textView));
            String str3 = "";
            if (stringExtra == null || stringExtra.length() == 0) {
                str2 = str3;
            } else {
                str2 = "&default_uin=" + stringExtra + "&pt_lock_uin=" + i2;
            }
            activity.runOnUiThread(new m(textView, "https://xui.ptlogin2.qq.com/cgi-bin/xlogin?appid=" + longExtra + "&style=42&s_url=http://wtlogin.qq.com/&pt_no_onekey=1&pt_no_auth=1&daid=499&wt_force_pwd=" + i + str2, str));
        } catch (Exception e) {
            util.LOGI("create webview failed " + e.getMessage(), "");
        }
    }
}
