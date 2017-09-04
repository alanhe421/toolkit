package com.qq.reader.view;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.SplashActivity;
import com.qq.reader.activity.b;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.adv.a;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: SpalshNativeUI */
public class al implements b {
    private long a = 0;
    private long b = 2000;
    private boolean c = false;
    private View d = null;
    private View e = null;
    private ImageView f;
    private SplashActivity g;

    public int a() {
        return R.layout.splash;
    }

    public void a(SplashActivity splashActivity, Handler handler) {
        this.g = splashActivity;
        this.d = this.g.findViewById(R.id.splash_bottom_area);
        this.d.setOnClickListener(new 1(this, handler));
        this.e = this.g.findViewById(R.id.splash_top_area);
        this.e.setOnClickListener(new 2(this, handler));
        this.f = (ImageView) this.g.findViewById(R.id.bg_image);
    }

    public void b() {
        ReaderApplication.timeLog.addSplit("showDefaultSplash readBitMap begin");
        Bitmap c = ao.c(this.g.getApplicationContext(), (int) R.drawable.splash_bg);
        ReaderApplication.timeLog.addSplit("showDefaultSplash readBitMap end");
        if (c != null) {
            Bitmap a = ao.a(c);
            ReaderApplication.timeLog.addSplit("showDefaultSplash createFixtScreenBitmap");
            this.f.setImageBitmap(a);
            ReaderApplication.timeLog.addSplit("showDefaultSplash setImageBitmap");
            if (a != c) {
                c.recycle();
            }
        }
    }

    public void c() {
        if (this.f != null) {
            this.c = false;
            this.a = System.currentTimeMillis();
            if (ReaderApplication.isFirstInstall) {
                b();
                return;
            }
            ReaderApplication.timeLog.addSplit("showDefaultSplash readAdvDB begin");
            List b = com.qq.reader.cservice.adv.b.a(this.g.getApplicationContext()).b("100100");
            ReaderApplication.timeLog.addSplit("showDefaultSplash readAdvDB end");
            if (b == null || b.size() <= 0) {
                b();
                return;
            }
            a aVar = (a) b.get(0);
            if (aVar.y() != 5) {
                this.e.setTag(aVar);
            }
            this.d.setTag(aVar);
            ReaderApplication.timeLog.addSplit("setSplashImage setTag");
            ReaderApplication.timeLog.addSplit("setSplashImage getAdvImage_");
            c.a(this.g).a(aVar.g(), com.qq.reader.common.c.a.bU, com.qq.reader.common.c.a.bT, new 3(this, aVar));
        }
    }

    public long d() {
        long currentTimeMillis = System.currentTimeMillis() - this.a;
        if (!this.c) {
            currentTimeMillis = ReaderApplication.isFirstInstall ? 250 - currentTimeMillis : 450 - currentTimeMillis;
            if (currentTimeMillis >= 0) {
                return currentTimeMillis;
            }
            return 0;
        } else if (currentTimeMillis < this.b) {
            return this.b - currentTimeMillis;
        } else {
            return 0;
        }
    }

    public void e() {
        this.f.setDrawingCacheEnabled(true);
        Bitmap bitmap = null;
        try {
            bitmap = this.f.getDrawingCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.qq.reader.common.d.a.a(this.g);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }
}
