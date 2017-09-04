package com.qq.reader.liveshow.views.roomdialog.a.a;

import android.app.Activity;
import android.view.ViewGroup;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.liveshow.c.b.e;
import com.qq.reader.liveshow.c.k;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a;

/* compiled from: RoomDialogLoginHelper */
public class c extends a implements e {
    private final k d;

    public c(a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.d = new k(activity, this);
    }

    public void b() {
        SxbLog.e("START", "start getsign and login im");
        this.a.a("正在加载…");
        this.d.a(false);
    }

    public void c() {
    }

    public void k() {
    }

    public void l() {
        if (this.a != null) {
            this.a.a(-1, null);
        }
    }

    public void c(int i) {
        if (this.a != null) {
            switch (i) {
                case -201:
                    this.a.a(-1, null);
                    return;
                case DLConstants.LOAD_ERR_IO_FAIL /*-103*/:
                    this.a.a(5, null);
                    return;
                case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                    this.a.a(3, null);
                    return;
                case -100:
                    this.a.a(-1, null);
                    return;
                case 0:
                case 3:
                    this.a.a(6, null);
                    return;
                default:
                    return;
            }
        }
    }

    public void d() {
        super.d();
    }
}
