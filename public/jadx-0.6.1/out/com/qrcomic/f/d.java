package com.qrcomic.f;

import android.app.Activity;
import android.content.Intent;
import com.qrcomic.a.f;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: QRComicUrlHelper */
public class d {
    private static Map<String, String> a;
    private static Map<String, String> b = new HashMap();
    private static String c = "";
    private static boolean d = false;

    static {
        String str;
        if (f.a) {
            str = "http://ptcartoon.reader.qq.com/";
        } else {
            str = "http://cartoon.reader.qq.com/";
        }
        a(str);
    }

    public static synchronized void a(String str) {
        synchronized (d.class) {
            b.put("comicCdn", "http://imgcache.gtimg.cn/club/mobile/profile/comic_center/");
            if (f.a) {
                b.put("comicPicPublic", "http://public-1253533261.picsh.myqcloud.com/");
            } else {
                b.put("comicPicPublic", "http://public-1252317822.image.myqcloud.com/");
            }
            b.put("comicPayRead", str + "nativepage/buy/queryUserBuyInfo");
            b.put("sectionPicList", str + "nativepage/cartoon/section/info");
            b.put("sectionBuy", str + "nativepage/buy/pay");
            b.put("comicDetail", str + "nativepage/cartoon/info");
            b.put("sectionPreview", str + "nativepage/cartoon/section/preview");
        }
    }

    public static synchronized void a(Activity activity) {
        synchronized (d.class) {
            if (!(d || activity == null)) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Serializable serializableExtra = intent.getSerializableExtra("urlMap");
                    if (serializableExtra instanceof HashMap) {
                        HashMap hashMap = (HashMap) serializableExtra;
                        if (!hashMap.isEmpty()) {
                            if (a == null) {
                                a = new HashMap();
                            }
                            a.clear();
                            a.putAll(hashMap);
                            d = true;
                        }
                    }
                }
            }
        }
    }

    public static String b(String str) {
        String str2 = null;
        if (a != null) {
            str2 = (String) a.get(str);
        }
        if (str2 == null) {
            return (String) b.get(str);
        }
        return str2;
    }
}
