package com.qq.reader.view.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.qq.reader.view.BaseDialog;
import com.tencent.feedback.proguard.R;
import com.tencent.widget.XListView;

/* compiled from: QRPopupMenu */
public class n extends BaseDialog {
    private XListView a;
    private Context b;
    private a c;
    private o d = new o(this.b);

    /* compiled from: QRPopupMenu */
    public interface a {
        boolean b(int i, Bundle bundle);
    }

    public n(Activity activity, int i) {
        this.b = activity;
        View inflate = ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(i, null);
        inflate.setOnTouchListener(new 1(this));
        this.a = (XListView) inflate.findViewById(R.id.webpage_popupmenu_listview);
        this.a.setOnItemClickListener(new 2(this));
        this.a.setAdapter(this.d);
        a(activity, inflate, i, 10, true, false, false);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(int i, String str, Bundle bundle) {
        a(i, str, false, bundle);
    }

    public void a(int i, String str, boolean z, Bundle bundle) {
        this.d.a(i, str, z, bundle);
    }

    public void g() {
        this.d.a();
    }

    public int h() {
        if (this.d != null) {
            return this.d.c;
        }
        return 0;
    }

    public void a(boolean z) {
        if (z) {
            super.f_();
        } else {
            this.f.show();
        }
    }

    public void f_() {
        this.f.show();
    }

    public void b(int i) {
        this.d.c = i;
        this.d.notifyDataSetChanged();
    }

    public void a(OnCancelListener onCancelListener) {
        this.f.setOnCancelListener(onCancelListener);
    }

    @TargetApi(8)
    public void a(OnShowListener onShowListener) {
        this.f.setOnShowListener(onShowListener);
    }

    public Window i() {
        if (this.f != null) {
            return this.f.getWindow();
        }
        return null;
    }
}
