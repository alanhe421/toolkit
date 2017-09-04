package com.tencent.smtt.sdk;

import android.content.ContentResolver;
import android.graphics.Bitmap;

@Deprecated
public class WebIconDatabase {
    private static WebIconDatabase a;

    @Deprecated
    public interface a {
        void a(String str, Bitmap bitmap);
    }

    private WebIconDatabase() {
    }

    private static synchronized WebIconDatabase a() {
        WebIconDatabase webIconDatabase;
        synchronized (WebIconDatabase.class) {
            if (a == null) {
                a = new WebIconDatabase();
            }
            webIconDatabase = a;
        }
        return webIconDatabase;
    }

    public static WebIconDatabase getInstance() {
        return a();
    }

    public void bulkRequestIconForPageUrl(ContentResolver contentResolver, String str, a aVar) {
    }

    public void close() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().close();
        } else {
            a.c().m();
        }
    }

    public void open(String str) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().open(str);
        } else {
            a.c().b(str);
        }
    }

    public void releaseIconForPageUrl(String str) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().releaseIconForPageUrl(str);
        } else {
            a.c().d(str);
        }
    }

    public void removeAllIcons() {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().removeAllIcons();
        } else {
            a.c().l();
        }
    }

    public void requestIconForPageUrl(String str, a aVar) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().requestIconForPageUrl(str, new ai(this, aVar));
        } else {
            a.c().a(str, new ah(this, aVar));
        }
    }

    public void retainIconForPageUrl(String str) {
        aq a = aq.a();
        if (a == null || !a.b()) {
            android.webkit.WebIconDatabase.getInstance().retainIconForPageUrl(str);
        } else {
            a.c().c(str);
        }
    }
}
