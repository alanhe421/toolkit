package com.yuewen.ywlogin;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class d {
    public static String a() {
        String toLowerCase = Locale.getDefault().getCountry().toLowerCase();
        if ("tw".equals(toLowerCase) || "hk".equals(toLowerCase)) {
            return "tw";
        }
        return "cn";
    }

    public static Map<String, String> a(String str) {
        Map<String, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
            if (split != null && split.length > 0) {
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3 != null && split3.length == 2) {
                        hashMap.put(split3[0].trim(), split3[1].trim());
                    }
                }
            }
        }
        return hashMap;
    }
}
