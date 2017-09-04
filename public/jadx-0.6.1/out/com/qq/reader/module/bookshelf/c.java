package com.qq.reader.module.bookshelf;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import com.qq.reader.qurl.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: BookCouponHandler */
public class c implements b {
    static boolean d = false;
    Activity a;
    WeakReferenceHandler b;
    AlertDialog c;
    AlertDialog e;

    public void a() {
        this.b.post(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.c != null) {
                    this.a.c.dismiss();
                    this.a.c = null;
                }
            }
        });
    }

    protected void a(final String str, boolean z) {
        if (this.b != null && !this.a.isFinishing()) {
            if (z) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ c b;

                    public void run() {
                        View inflate = this.b.a.getLayoutInflater().inflate(R.layout.bookcoupon_success_layout, null);
                        ((TextView) inflate.findViewById(R.id.toast_text)).setText(str);
                        this.b.e = new Builder(this.b.a).create();
                        this.b.e.setCanceledOnTouchOutside(true);
                        this.b.e.getWindow().setWindowAnimations(R.style.sign_pop_anim_style);
                        this.b.e.show();
                        this.b.e.setContentView(inflate);
                        inflate.setOnTouchListener(new OnTouchListener(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                this.a.b.e.dismiss();
                                return false;
                            }
                        });
                    }
                });
                this.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            if (this.a.e != null) {
                                this.a.e.dismiss();
                            }
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("BookCoupon", e.getMessage());
                        }
                    }
                }, 3000);
                return;
            }
            this.b.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    af.a(this.b.a, str, 0).a();
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.os.Message r5) {
        /*
        r4 = this;
        r3 = 1;
        r0 = "BookCoupon";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "qURLJumpResult msg is   ";
        r1 = r1.append(r2);
        r2 = r5.obj;
        r2 = r2.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.reader.common.monitor.debug.c.a(r0, r1);
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0048 }
        r0 = r5.obj;	 Catch:{ JSONException -> 0x0048 }
        r0 = (java.lang.String) r0;	 Catch:{ JSONException -> 0x0048 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0048 }
        r0 = "code";
        r0 = r1.optInt(r0);	 Catch:{ JSONException -> 0x0048 }
        r2 = "msg";
        r1 = r1.optString(r2);	 Catch:{ JSONException -> 0x0048 }
        switch(r0) {
            case -400: goto L_0x004d;
            case -101: goto L_0x004d;
            case 0: goto L_0x0040;
            case 1: goto L_0x004d;
            default: goto L_0x003b;
        };	 Catch:{ JSONException -> 0x0048 }
    L_0x003b:
        r0 = 0;
        r4.a(r1, r0);	 Catch:{ JSONException -> 0x0048 }
    L_0x003f:
        return r3;
    L_0x0040:
        r4.a();	 Catch:{ JSONException -> 0x0048 }
        r0 = 1;
        r4.a(r1, r0);	 Catch:{ JSONException -> 0x0048 }
        goto L_0x003f;
    L_0x0048:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x003f;
    L_0x004d:
        r4.a();	 Catch:{ JSONException -> 0x0048 }
        r0 = 0;
        r4.a(r1, r0);	 Catch:{ JSONException -> 0x0048 }
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookshelf.c.a(android.os.Message):boolean");
    }
}
