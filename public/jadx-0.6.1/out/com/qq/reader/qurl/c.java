package com.qq.reader.qurl;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.j;
import com.qq.reader.qurl.a.a;
import com.qq.reader.qurl.a.b;
import com.qq.reader.qurl.a.d;
import com.qq.reader.qurl.a.f;
import com.qq.reader.qurl.a.g;
import com.qq.reader.qurl.a.h;
import com.qq.reader.qurl.a.i;
import com.qq.reader.qurl.a.k;
import com.qq.reader.qurl.a.l;
import com.qq.reader.qurl.a.m;
import com.qq.reader.qurl.a.n;
import com.qq.reader.qurl.a.o;
import com.qq.reader.qurl.a.p;
import com.qq.reader.qurl.a.q;
import com.qq.reader.qurl.a.r;
import com.qq.reader.qurl.a.s;
import com.qq.reader.qurl.a.t;
import com.qq.reader.qurl.a.u;
import com.qq.reader.qurl.a.v;
import com.tencent.android.tpush.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLCenter */
public class c {
    private static final String a = e.h;

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("uniteqqreader://") || str.startsWith("http://") || str.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("uniteqqreader://");
    }

    public static void a(Activity activity, String str, JumpActivityParameter jumpActivityParameter) throws Exception {
        a(activity, str, null, jumpActivityParameter);
    }

    public static void a(Activity activity, String str) throws Exception {
        a(activity, str, null, null);
    }

    public static void a(Activity activity, String str, b bVar, JumpActivityParameter jumpActivityParameter) throws Exception {
        String str2 = null;
        if (str != null && str.length() != 0 && activity != null) {
            String substring;
            try {
                if (str.startsWith("uniteqqreader://")) {
                    String substring2;
                    substring = str.substring("uniteqqreader://".length());
                    String[] split = substring.split("\\?");
                    String str3 = split[0];
                    if (split.length > 1) {
                        substring2 = substring.substring(str3.length() + 1);
                    } else {
                        substring2 = null;
                    }
                    String[] split2 = str3.split("/");
                    if (split2 != null && split2.length > 0) {
                        d a;
                        str3 = "http://";
                        if ("webpage".equals(split2[0])) {
                            boolean contains = substring.contains("https://");
                            if (contains) {
                                str3 = "https://";
                            } else {
                                contains = substring.contains("http://");
                            }
                            if (contains) {
                                split2 = substring.split(str3);
                                split = split2[0].split("/");
                                String str4 = split[0];
                                if (split.length > 1) {
                                    str2 = split[1];
                                }
                                a = a(activity, str4, str2, substring2);
                                str2 = str3 + split2[1];
                            } else {
                                a = a(activity, "webpage", null, null);
                                str2 = substring.substring("webpage".length() + 1);
                            }
                        } else if ("nativepage".equals(split2[0])) {
                            String str5;
                            if (split2.length > 1) {
                                str5 = split2[1];
                            } else {
                                str5 = null;
                            }
                            if (split2.length > 2) {
                                str2 = split2[2];
                            }
                            a = a(activity, str5, str2, substring2);
                            if (substring.contains("&statInfo")) {
                                str2 = e.h + substring.substring(0, substring.indexOf("&statInfo"));
                            } else {
                                str2 = e.h + substring;
                            }
                        } else {
                            a = null;
                        }
                        if (a != null) {
                            a.a(jumpActivityParameter);
                            a.a(bVar);
                            a.a(str2);
                            a.f();
                        }
                    }
                } else if (str.startsWith("http://") || str.startsWith("https://")) {
                    Intent intent = new Intent();
                    intent.setClass(activity, WebBrowserForContents.class);
                    intent.putExtra("com.qq.reader.WebContent", str);
                    intent.setFlags(SigType.WLOGIN_QRPUSH);
                    activity.startActivityForResult(intent, Constants.ERRORCODE_UNKNOWN);
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("URLCenter", e.getMessage());
                j.c("[URL :" + substring + "] : " + e.toString());
                throw e;
            } catch (Exception e2) {
                com.qq.reader.common.monitor.debug.c.e("URLCenter", "excuteURL :  qurl = " + str + " \n" + e2.toString());
                throw e2;
            }
        }
    }

    private static final d a(Activity activity, String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        switch (a.a(str)) {
            case 1:
                return new d(activity, str2, str3);
            case 2:
                return new s(activity, str2, str3);
            case 3:
                return new g(activity, str2, str3);
            case 4:
                return new t(activity, str2, str3);
            case 5:
                return new i(activity, str2, str3);
            case 6:
                return new f(activity, str2, str3);
            case 7:
                return new m(activity, str2, str3);
            case 8:
                return new n(activity, str2, str3);
            case 9:
                return new com.qq.reader.qurl.a.e(activity, str2, str3);
            case 10:
                return new com.qq.reader.qurl.a.j(activity, str2, str3);
            case 11:
                return new p(activity, str2, str3);
            case 12:
                return new a(activity, str2, str3);
            case 13:
                return new k(activity, str2, str3);
            case 14:
                return new com.qq.reader.qurl.a.c(activity, str2, str3);
            case 15:
                return new u(activity, str2, str3);
            case 16:
                return new q(activity, str2, str3);
            case 17:
                return new r(activity, str2, str3);
            case 18:
                return new o(activity, str2, str3);
            case 19:
                return new b(activity, str2, str3);
            case 20:
                return new l(activity, str2, str3);
            case 21:
                return new v(activity, str2, str3);
            case 22:
                return new h(activity, str2, str3);
            default:
                return null;
        }
    }

    public static Map<String, String> c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            Map<String, String> hashMap;
            String[] split = str.split("&");
            if (split != null) {
                hashMap = new HashMap(split.length);
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3 != null && split3.length > 1) {
                        a(split3);
                        hashMap.put(split3[0], split3[1]);
                    }
                }
            } else {
                hashMap = null;
            }
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static void a(String[] strArr) {
        if (strArr != null && strArr.length == 2 && strArr[0] != null && strArr[1] != null) {
            String str = "encode_";
            if (strArr[0].startsWith(str)) {
                strArr[0] = strArr[0].substring(str.length());
                try {
                    strArr[1] = URLDecoder.decode(strArr[1], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
