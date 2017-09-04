package com.qq.reader.module.feed.mypreference;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a.a.a.a.c;
import com.qq.reader.common.imageloader.a.a.b.e;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.protocol.QueryReadGeneListTask;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.util.EncodingUtils;
import org.json.JSONObject;

/* compiled from: GeneListHandler */
public class a {
    static String a = "genelist";
    private static a b;
    private c c;

    /* compiled from: GeneListHandler */
    public interface a {
        void a(boolean z);
    }

    private a() {
    }

    private void c() {
        try {
            this.c = (c) com.qq.reader.common.imageloader.a.a.b.a.a(ReaderApplication.getApplicationImp(), new e(), 10485760, 0, new File(com.qq.reader.common.imageloader.a.a.a.g).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String d() {
        try {
            if (this.c == null) {
                c();
            }
            if (this.c == null) {
                return null;
            }
            File a = this.c.a(a);
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

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(a aVar) {
        String e = e();
        g.a().a(new QueryReadGeneListTask(new 1(this, aVar, e), e));
    }

    private String e() {
        Object d = d();
        String str = "0";
        if (!TextUtils.isEmpty(d)) {
            try {
                str = new JSONObject(d).optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public String b() {
        return d();
    }

    private void a(String str) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            if (this.c == null) {
                c();
            }
            if (this.c != null) {
                this.c.a(a, byteArrayInputStream, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
