package com.qrcomic.c;

import android.content.Context;
import android.os.Handler;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.entity.e;
import com.qrcomic.entity.f;

/* compiled from: IBookShelf */
public interface c {

    /* compiled from: IBookShelf */
    public interface a {
        void a(e eVar);
    }

    /* compiled from: IBookShelf */
    public interface b {
        void a(boolean z);
    }

    void a(Context context, String str, a aVar);

    void a(com.qrcomic.activity.reader.a aVar, Context context);

    void a(com.qrcomic.entity.a aVar, Context context);

    void a(f fVar, com.qrcomic.entity.a aVar);

    void a(String str, boolean z, Handler handler, QRComicReadingBaseActivity qRComicReadingBaseActivity, b bVar);

    boolean a(String str, Context context);

    boolean a(String str, e eVar);

    void b(com.qrcomic.activity.reader.a aVar, Context context);

    boolean b(String str, Context context);

    boolean c(String str, Context context);

    boolean d(String str, Context context);

    boolean e(String str, Context context);
}
