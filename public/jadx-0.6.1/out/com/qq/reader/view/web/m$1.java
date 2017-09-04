package com.qq.reader.view.web;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: PopWebDialog */
class m$1 implements e<String, b> {
    final /* synthetic */ a a;
    final /* synthetic */ Handler b;
    final /* synthetic */ m c;

    m$1(m mVar, a aVar, Handler handler) {
        this.c = mVar;
        this.a = aVar;
        this.b = handler;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        return false;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        m.b(this.c).setVisibility(0);
        m.h(this.c).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ m$1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if ("102668".equalsIgnoreCase(this.a.a.f())) {
                    Message obtainMessage = this.a.b.obtainMessage();
                    obtainMessage.obj = this.a.a;
                    obtainMessage.what = 116;
                    this.a.b.sendMessage(obtainMessage);
                    if (m.c(this.a.c).isShowing() && !m.d(this.a.c).isFinishing()) {
                        m.e(this.a.c).dismiss();
                        return;
                    }
                    return;
                }
                String h = this.a.a.h();
                Intent intent = new Intent();
                intent.setClass(m.d(this.a.c), WebBrowserForContents.class);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                intent.putExtra("com.qq.reader.WebContent", h);
                m.d(this.a.c).startActivity(intent);
                if (m.f(this.a.c).isShowing() && !m.d(this.a.c).isFinishing()) {
                    m.g(this.a.c).dismiss();
                }
                com.qq.reader.common.monitor.j.a(Opcodes.NOT_LONG, 0);
                i.a("event_C108", null, ReaderApplication.getApplicationImp());
            }
        });
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.obj = this.a;
        obtainMessage.what = 65538;
        this.b.sendMessage(obtainMessage);
        i.a("event_A125", null, ReaderApplication.getApplicationImp());
        return false;
    }
}
