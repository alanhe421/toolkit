package com.yuewen.ywlogin;

import com.tencent.android.tpush.common.Constants;
import java.util.HashMap;

public class g {
    public static String a = "";
    private static HashMap<String, String> b = new HashMap();
    private static boolean c;

    public static void a(boolean z) {
        try {
            c = z;
            a = d.a();
            b.clear();
            String str = c ? "https://oaptlogin.qidian.com/" : "https://ptlogin.qidian.com/";
            Object obj = c ? "https://devpassport.qidian.com/js/phoneArea.js" : "https://sta.book.qq.com/js/phoneArea.js";
            b.put("staticlogin", str + "sdk/staticlogin");
            b.put("checkcodelogin", str + "sdk/checkcodelogin");
            b.put("sendphonecheckcode", str + "sdk/sendphonecheckcode");
            b.put("phonecodelogin", str + "sdk/phonecodelogin");
            b.put("visitorlogin", str + "sdk/visitorlogin");
            b.put("qqwtcallback", str + "sdk/qqwtcallback");
            b.put("qqconnectcallback", str + "sdk/qqconnectcallback");
            b.put("weixincallback", str + "sdk/weixincallback");
            b.put("qqconnectlogin", str + "login/qqconnectlogin?auto=%1$d&autotime=%2$d");
            b.put("sinalogin", str + "login/sinalogin?auto=%1$d&autotime=%2$d");
            b.put("baidulogin", str + "login/baidulogin?force_login=1&type=mobile&auto=%1$d&autotime=%2$d");
            b.put("alipaylogin", str + "login/alipaylogin?type=wap&auto=%1$d&autotime=%2$d");
            b.put(Constants.SHARED_PREFS_KEY_REGISTER, str + "sdk/reg");
            b.put("getvalidatecode", str + "sdk/getvalidatecode");
            b.put("checkaccount", str + "sdk/checkaccount");
            b.put("confirmemail", str + "sdk/confirmemail");
            b.put("resendregemail", str + "sdk/resendregemail");
            b.put("phonearea", obj);
            b.put("checkStatus", str + "sdk/checkstatus");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a() {
        return (String) b.get("staticlogin");
    }

    public static String b() {
        return (String) b.get("checkcodelogin");
    }

    public static String c() {
        return (String) b.get("qqwtcallback");
    }

    public static String d() {
        return (String) b.get("weixincallback");
    }

    public static String e() {
        return (String) b.get("checkStatus");
    }
}
