package com.qq.reader.module.feed.mypreference;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.a.a.b.e;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.protocol.QueryUserReadGeneTask;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mm.performance.WxPerformanceHandle;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReadingGeneCache */
public class c extends b {
    private static volatile c a;
    private com.qq.reader.common.imageloader.a.a.a.a.c b;
    private a c;
    private d d = null;

    /* compiled from: ReadingGeneCache */
    public interface a {
        void a(d dVar);
    }

    private c() {
        e();
    }

    public static c b() {
        if (a == null) {
            synchronized (com.qq.reader.cservice.cloud.b.class) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    private void a(String str) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        String R = d.R(ReaderApplication.getApplicationImp());
        try {
            if (this.b == null) {
                e();
            }
            if (this.b != null && !this.b.a(R, byteArrayInputStream, null)) {
                com.qq.reader.common.monitor.debug.c.a("ReadingGeneCache", "PerferenceCache save Data Error ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String d() {
        String R = d.R(ReaderApplication.getApplicationImp());
        try {
            if (this.b == null) {
                e();
            }
            if (this.b == null) {
                return null;
            }
            File a = this.b.a(R);
            if (a == null || !a.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(a);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String string = EncodingUtils.getString(bArr, "UTF-8");
            fileInputStream.close();
            return string;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void e() {
        try {
            this.b = (com.qq.reader.common.imageloader.a.a.a.a.c) com.qq.reader.common.imageloader.a.a.b.a.a(ReaderApplication.getApplicationImp(), new e(), 10485760, 0, new File(ReaderApplication.getApplicationImp().getExternalCacheDir(), com.qq.reader.common.c.a.ag).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public d c() {
        Object d = d();
        if (!TextUtils.isEmpty(d)) {
            try {
                JSONObject jSONObject = new JSONObject(d);
                d.F(ReaderApplication.getApplicationImp(), jSONObject.optInt("sex"));
                return new d(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void f() {
        Object d = d();
        if (!TextUtils.isEmpty(d)) {
            try {
                JSONObject jSONObject = new JSONObject(d);
                d.F(ReaderApplication.getApplicationImp(), jSONObject.optInt("sex"));
                this.d = new d(jSONObject);
                if (this.c != null) {
                    this.c.a(this.d);
                    this.c = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(a aVar) {
        this.c = aVar;
        g.a().a(new QueryUserReadGeneTask(new 1(this)));
    }

    public void a(ArrayList<String> arrayList) {
        try {
            d c = c();
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            while (arrayList != null && i < arrayList.size()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(WxPerformanceHandle.MESSAGE_TAG, arrayList.get(i));
                jSONObject.put(Constants.FLAG_TAG_NAME, b.a((String) arrayList.get(i)).a);
                arrayList2.add(new d.d(jSONObject));
                i++;
            }
            c.a = arrayList2;
            a(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        synchronized (d.class) {
            this.d = null;
            com.qq.reader.common.monitor.debug.c.a("ReadingGeneCache", "onChangeAccount");
            a = null;
        }
    }
}
