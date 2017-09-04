package com.sina.weibo.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;

/* compiled from: WbSdk */
public class b {
    private static boolean a = false;
    private static AuthInfo b;

    public static void a(Context context, AuthInfo authInfo) {
        if (!a) {
            if (authInfo == null || TextUtils.isEmpty(authInfo.a()) || TextUtils.isEmpty(authInfo.c()) || TextUtils.isEmpty(authInfo.b())) {
                throw new RuntimeException("please set right app info (appKey,Scope,redirect");
            }
            b = authInfo;
            com.sina.weibo.sdk.b.b.a(context).a(b.a());
            a = true;
        }
    }

    public static void a() {
        if (!a) {
            throw new RuntimeException("weibo sdk was not initall! please use: WbSdk.install() in your app Application or your main Activity. when you want to use weibo sdk function, make sure call WbSdk.install() before this function");
        }
    }

    public static AuthInfo b() {
        a();
        return b;
    }
}
