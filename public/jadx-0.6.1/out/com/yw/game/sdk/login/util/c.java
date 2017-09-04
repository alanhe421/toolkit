package com.yw.game.sdk.login.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.a;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Utils */
public class c {
    public static boolean a = true;
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d("YW_GAME_SDK", str);
        }
    }

    public static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            Log.i(str, str2);
        }
    }

    public static NetResult b(String str) {
        a(str);
        NetResult netResult = new NetResult();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("ReturnCode");
                String optString = jSONObject.optString("ReturnMessage");
                String optString2 = jSONObject.optString("ReturnData");
                netResult.setReturnCode(optInt);
                netResult.setReturnMessage(optString);
                netResult.setReturnData(optString2);
            } catch (JSONException e) {
                e.printStackTrace();
                a(e.toString());
            }
        }
        return netResult;
    }

    public static JSONObject c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a(str);
        try {
            return new JSONObject(new JSONObject(str).optString("data"));
        } catch (JSONException e) {
            e.printStackTrace();
            a(e.toString());
            return null;
        }
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "网络异常";
        }
        Toast.makeText(context, str, 0).show();
    }

    public static boolean a(Context context) {
        try {
            if (context instanceof Activity) {
                context = context.getApplicationContext();
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            a(e.toString());
            return false;
        }
    }

    public static String d(String str) {
        return b(str, "_8F0&24FD69Jh2_145DAB304ah2Hd)Jq_7HinAB304a_sdk_game_qidian_com");
    }

    public static String b(String str, String str2) {
        String substring = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        String str3 = "";
        try {
            str3 = URLEncoder.encode(b.a(String.valueOf(substring), str2), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            a(e.toString());
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("?")) {
            return str + "&sign=" + str3 + GetVoteUserIconsTask.TIME + substring;
        }
        return str + "?sign=" + str3 + GetVoteUserIconsTask.TIME + substring;
    }

    public static String a() {
        return String.valueOf(System.currentTimeMillis()).substring(0, 10);
    }

    public static String e(String str) {
        String str2 = "";
        try {
            str2 = URLEncoder.encode(b.a(str, "_8F0&24FD69Jh2_145DAB304ah2Hd)Jq_7HinAB304a_sdk_game_qidian_com"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            a(e.toString());
        }
        return str2;
    }

    public static String c(String str, String str2) {
        String str3 = "";
        try {
            str3 = URLEncoder.encode(b.a(String.valueOf(str), "_8F0&24FD69Jh2_145DAB304ah2Hd)Jq_7HinAB304a_sdk_game_qidian_com", str2), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            a(e.toString());
        }
        return str3;
    }

    public static boolean a(Activity activity) {
        return a.a(activity, "android.permission.READ_PHONE_STATE") == 0;
    }

    public static boolean b(Activity activity) {
        return a.a(activity, "android.permission.ACCESS_NETWORK_STATE") == 0;
    }

    public static String b(Context context, String str) {
        Signature c = c(context, str);
        if (c == null) {
            return "";
        }
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            instance = null;
        }
        if (instance == null) {
            return "";
        }
        String a = a(instance.digest(c.toByteArray()));
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        a(" packagename:" + str + " signature:" + a.toUpperCase());
        return a.toUpperCase();
    }

    public static Signature c(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0];
            }
        }
        return null;
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[i * 2] = b[(b >>> 4) & 15];
            cArr[(i * 2) + 1] = b[b & 15];
        }
        return new String(cArr);
    }
}
