package com.qq.reader.view.web;

import android.app.Activity;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.c;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.rookie.a.b;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PopRookieDialog */
public class l extends i {
    public int k;
    private WeakReferenceHandler l;
    private boolean m = false;
    private String n;
    private b o;

    private l(Activity activity, int i) {
        super(activity, i, 17);
        if (this.b != null) {
            this.b.setBackgroundColor(-1);
        }
        k();
        r();
        c.a(this.e).a(this.j, "WEBDIALOG");
    }

    private void r() {
        Resources resources = this.e.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.rookie_web_dialog_width);
        this.n = "var viewPortTag=document.createElement('meta');\nviewPortTag.id='viewport';viewPortTag.name = 'viewport'; \n viewPortTag.content = 'width=" + dimensionPixelSize + "; height=" + resources.getDimensionPixelSize(R.dimen.rookie_web_dialog_height) + ";initial-scale=1.0; maximum-scale=1.0; user-scalable=0;'; \n" + "document.getElementsByTagName('head')[0].appendChild(viewPortTag);";
    }

    @Deprecated
    public void b(a aVar, WeakReferenceHandler weakReferenceHandler) {
    }

    @Deprecated
    public void a(a aVar, WeakReferenceHandler weakReferenceHandler) {
    }

    public void a(b bVar, WeakReferenceHandler weakReferenceHandler, boolean z) {
        com.qq.reader.module.rookie.presenter.a.a().b(true);
        if (bVar != null) {
            this.o = bVar;
        }
        this.l = weakReferenceHandler;
        if (bVar.e == 1) {
            Object a = e.a(bVar.c, ReaderApplication.getApplicationImp(), e.e);
            if (TextUtils.isEmpty(a)) {
                com.qq.reader.module.rookie.presenter.a.a().b(false);
                if (this.a != null) {
                    this.a.onCancel(this.f);
                    return;
                }
                return;
            }
            weakReferenceHandler.post(new 1(this, a));
            if (z) {
                Message obtainMessage = weakReferenceHandler.obtainMessage();
                obtainMessage.obj = bVar;
                obtainMessage.what = 65552;
                weakReferenceHandler.sendMessage(obtainMessage);
                com.qq.reader.module.rookie.presenter.a.a().b(false);
                return;
            }
            return;
        }
        com.qq.reader.common.imageloader.c.a(e()).a(bVar.c, this.c, com.qq.reader.common.imageloader.a.a().d((int) e().getResources().getDimension(R.dimen.game_adv_dialog_img_width), (int) e().getResources().getDimension(R.dimen.game_adv_dialog_img_height)), new 2(this, bVar, weakReferenceHandler));
    }

    private void a(b bVar) {
        Map hashMap = new HashMap();
        hashMap.put("id", bVar.a + "");
        if ("p2".equals(bVar.b)) {
            i.a("event_A267", hashMap, ReaderApplication.getApplicationImp());
        } else if ("p1".equals(bVar.b)) {
            i.a("event_A273", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    private static int b(b bVar) {
        if (bVar.e == 1) {
            return 0;
        }
        return 1;
    }

    public void f_() {
        try {
            if (this.e != null && !this.e.isFinishing() && this.e != null && !this.e.isFinishing() && !com.qq.reader.cservice.adv.b.b && !com.qq.reader.module.rookie.presenter.a.a().e()) {
                p();
                try {
                    this.f.show();
                    com.qq.reader.module.rookie.presenter.a.a().a(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    j();
                    if (this.a != null) {
                        this.a.onCancel(this.f);
                    }
                }
            }
        } catch (Exception e2) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeDailog", e2.getMessage());
        }
    }

    protected void b(int i) {
        super.b(i);
    }

    protected void g() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.o.a));
        i.a("event_F200", hashMap, e());
    }

    public static l a(Activity activity, b bVar, OnDismissListener onDismissListener, int i) {
        try {
            int b = b(bVar);
            l lVar = new l(activity, b);
            lVar.b(false);
            lVar.k = i;
            if (lVar.d != null && b == 0) {
                lVar.d.getLayoutParams().height = activity.getResources().getDimensionPixelSize(R.dimen.rookie_web_dialog_height);
                lVar.d.requestLayout();
            }
            lVar.a(new 3(lVar, onDismissListener, b));
            return lVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void j() {
        com.qq.reader.module.rookie.presenter.a.a().a(false);
        com.qq.reader.common.monitor.debug.c.d("ROOKIE", "rookie dialog dismiss");
        c.a(this.e).a("WEBDIALOG");
    }

    protected Callback l() {
        return new 4(this);
    }

    protected void a(WebView webView, String str) {
        super.a(webView, str);
        com.qq.reader.common.monitor.debug.c.d("WEBVIEW", "your dy onPageFinished");
    }

    protected void a(WebView webView, String str, Bitmap bitmap) {
        super.a(webView, str, bitmap);
        webView.loadUrl("javascript:" + this.n);
    }

    protected com.qq.reader.common.login.a q() {
        return new 5(this);
    }
}
