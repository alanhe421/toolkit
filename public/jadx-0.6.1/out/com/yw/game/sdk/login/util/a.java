package com.yw.game.sdk.login.util;

/* compiled from: ApiConfig */
public class a {
    public static final String a = (a() + "Home/YwSDK/verifyApp");
    public static final String b = (a() + "Home/YwSDK/getAccessToken");
    public static final String c = (a() + "Home/YwSDK/getGameInfoById");

    public static String a() {
        return c.a ? "http://tmp.sdk.game.qidian.com/" : "http://sdk.game.qidian.com/";
    }
}
