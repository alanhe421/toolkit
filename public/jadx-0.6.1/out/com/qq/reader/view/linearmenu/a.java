package com.qq.reader.view.linearmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ListView;
import com.qq.reader.view.BaseDialog;
import com.tencent.feedback.proguard.R;

/* compiled from: LinearBaseMenu */
public abstract class a extends BaseDialog {
    private Handler a;
    protected ListView i;
    protected a j;
    protected Context k;
    protected b l;

    /* compiled from: LinearBaseMenu */
    public interface b {
        boolean a(int i, Bundle bundle);
    }

    public a(Activity activity) {
        this.k = activity;
        this.i = (ListView) ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.linear_readermenu, null);
        a(activity, this.i, R.layout.linear_readermenu, true, false, true);
        this.f.getWindow().addFlags(2);
    }

    protected void h() {
        this.j = new a(this, this.k);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new 1(this));
    }

    public void a(b bVar) {
        this.l = bVar;
    }

    public void a(int i, String str, Bundle bundle) {
        a(i, str, false, bundle);
    }

    public String b(int i) {
        return this.j.a(i);
    }

    public void a(int i, String str, boolean z, Bundle bundle) {
        this.j.a(i, str, z, bundle);
    }

    public void i() {
        this.j.a();
    }

    public int j() {
        return this.j.getCount();
    }
}
