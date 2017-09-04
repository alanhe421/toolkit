package com.qq.reader.cservice.download.game;

import android.graphics.Bitmap;
import com.qq.reader.common.c.a;
import com.qq.reader.common.download.task.f;

/* compiled from: DownloadGameTask */
public class d extends f {
    private String a;
    private Bitmap b;
    private String c;
    private long d;
    private String e;

    public d(String str, String str2, String str3, String str4) {
        super(str, str2, a.bj);
        this.a = str3;
        this.c = str4;
    }

    public String a() {
        return this.c;
    }

    public Bitmap b() {
        return this.b;
    }

    public void a(Bitmap bitmap) {
        this.b = bitmap;
    }

    public String c() {
        return this.a;
    }

    public int getTaskType() {
        return 106;
    }

    public String getFullName() {
        return getName();
    }

    public void a(long j) {
        this.d = j;
    }

    public String d() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }
}
