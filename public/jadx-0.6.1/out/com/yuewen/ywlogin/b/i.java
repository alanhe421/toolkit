package com.yuewen.ywlogin.b;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import com.yuewen.ywlogin.a.k;
import com.yuewen.ywlogin.e;
import com.yuewen.ywlogin.f;
import org.json.JSONObject;

public class i {
    private static i a;
    private boolean b = true;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private String j;
    private Context k;
    private a l = new d(this);

    public static i a() {
        if (a == null) {
            a = new i();
        }
        return a;
    }

    public void a(Context context, int i, int i2, String str, String str2, String str3, int i3, int i4, String str4) {
        this.k = context;
        this.c = i;
        this.d = i2;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = i3;
        this.i = i4;
        this.j = str4;
    }

    private ContentValues b() {
        ContentValues contentValues = new ContentValues();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(this.f);
            stringBuffer.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuffer.append(this.g);
            stringBuffer.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuffer.append(String.valueOf(System.currentTimeMillis() / 1000));
            String a = e.a(stringBuffer.toString());
            Log.d("YWLoginSDK", "signature :" + a);
            contentValues.put("signature", Uri.encode(a));
            contentValues.put("appid", Integer.valueOf(this.c));
            contentValues.put("areaid", Integer.valueOf(this.d));
            contentValues.put("auto", Integer.valueOf(this.h));
            contentValues.put("autotime", Integer.valueOf(this.i));
            contentValues.put("returnurl", "http://www.qidian.com");
            contentValues.put("format", "json");
            contentValues.put(SocialConstants.PARAM_SOURCE, this.e);
            contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, this.j);
            contentValues.put("referer", "http://android.qidian.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    public void a(String str, String str2, b bVar) {
        Handler handler = new Handler();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            k.a(0).submit(new j(this, str, str2, handler, bVar));
        }
    }

    public void a(String str, String str2, String str3, String str4, b bVar) {
        Handler handler = new Handler();
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            k.a(0).submit(new n(this, str3, str4, str, str2, handler, bVar));
        }
    }

    public void a(String str, String str2, int i, b bVar) {
        k.a(0).submit(new k(this, str, str2, i, new Handler(), bVar));
    }

    public void b(String str, String str2, b bVar) {
        k.a(0).submit(new l(this, str, str2, new Handler(), bVar));
    }

    public void a(long j, String str, b bVar) {
        k.a(0).submit(new m(this, j, str, new Handler(), bVar));
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("autoLoginSessionKey");
            long optLong = jSONObject.optLong("autoLoginExpiredTime");
            f.a(this.k, "YWLogin_AutoLoginSessionKey", optString);
            f.a(this.k, "YWLogin_AutoLoginExpiredTime", Long.valueOf(optLong));
        }
    }

    public void a(String str, Object obj) {
        f.a(this.k, str, obj);
    }

    public Object b(String str, Object obj) {
        return f.b(this.k, str, obj);
    }
}
