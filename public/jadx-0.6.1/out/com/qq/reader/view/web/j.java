package com.qq.reader.view.web;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.t;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.game.card.a;
import com.qq.reader.module.game.card.view.GameOpenBigBtn;
import com.qq.reader.module.game.data.c;
import com.qq.reader.module.game.data.d;
import com.qq.reader.view.BaseDialog;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PopNativeGameDialog */
public class j extends BaseDialog {
    protected c a;
    protected a b = new a();
    protected com.qq.reader.cservice.download.game.a c;
    protected OnClickListener d = new 3(this);
    private ImageView e;
    private ImageView i;
    private GameOpenBigBtn j;
    private Activity k;
    private WeakReference<Activity> l;

    public j(Activity activity) {
        this.k = activity;
        this.l = new WeakReference(activity);
        if (this.f == null) {
            a(activity, null, R.layout.native_gameadv_window, 0, true);
            this.f.setCancelable(true);
            this.e = (ImageView) this.f.findViewById(R.id.adv_img);
            t.a(this.e);
            this.i = (ImageView) this.f.findViewById(R.id.close_btn);
            this.i.setOnClickListener(new 1(this));
            this.j = (GameOpenBigBtn) this.f.findViewById(R.id.action_btn);
            this.f.setCanceledOnTouchOutside(false);
        }
        LayoutParams attributes = this.f.getWindow().getAttributes();
        attributes.height = activity.getWindow().getAttributes().height;
        this.f.getWindow().setAttributes(attributes);
        this.f.getWindow().addFlags(2);
    }

    public void a(d dVar, WeakReferenceHandler weakReferenceHandler) {
        this.a = dVar.d();
        if (this.c == null) {
            this.c = (com.qq.reader.cservice.download.game.a) l.d(1006);
            this.c.a(TaskStateEnum.values(), this.b);
        }
        if (this.j != null) {
            if (this.a.h()) {
                this.j.setTag(R.string.obj_tag, this.a);
                this.b.a(this.a, this.j);
                com.qq.reader.module.game.a.b().a(b());
                com.qq.reader.module.game.a.b().a(this.j, this.a);
                com.qq.reader.cservice.download.game.d a = this.c.a(this.c.a(), this.a.k());
                if (!(a == null || this.a.g())) {
                    this.j.setProgress(100, a.getProgress());
                }
                this.j.setGameBtnStatus(this.a.d());
                this.j.setButtonTextVisible(true);
                this.j.setButtonTextColor(dVar.c());
            } else {
                this.j.setButtonTextVisible(false);
            }
            this.j.setOnClickListener(this.d);
            this.j.setButtonImg(dVar.b());
        }
        com.qq.reader.common.imageloader.c.a(e()).a(dVar.a(), this.e, com.qq.reader.common.imageloader.a.a().c(com.qq.reader.common.c.a.bU), new 2(this, weakReferenceHandler));
    }

    private void a(String str) {
        if (com.qq.reader.qurl.c.a(str)) {
            try {
                com.qq.reader.qurl.c.a(this.k, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.f.isShowing() && !this.k.isFinishing()) {
            this.f.dismiss();
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, "1");
        i.a("event_A208", hashMap, ReaderApplication.getApplicationImp());
    }

    public void f_() {
        try {
            if (!b.b && !com.qq.reader.module.rookie.presenter.a.a().e() && this.k != null && !this.k.isFinishing()) {
                t.a(this.e);
                this.f.show();
                i.a("event_A207", null, ReaderApplication.getApplicationImp());
                b.b = true;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeGameDialog", e.getMessage());
        }
    }

    public void dismiss() {
        try {
            if (!this.k.isFinishing()) {
                super.dismiss();
            }
            com.qq.reader.module.game.a.b().b(b());
            com.qq.reader.module.game.a.b().e(this.a);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeDialog", e.getMessage());
        }
    }

    public void cancel() {
        if (!this.k.isFinishing()) {
            super.cancel();
        }
    }

    protected boolean a(Activity activity, c cVar) {
        return false;
    }
}
