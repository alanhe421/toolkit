package com.qq.reader.view.linearmenu;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LinearBaseMenu */
protected class a$a extends BaseAdapter {
    List<String> a;
    List<Integer> b;
    List<Boolean> c;
    List<Bundle> d;
    Context e;
    boolean f = false;
    boolean g = false;
    final /* synthetic */ a h;

    public /* synthetic */ Object getItem(int i) {
        return b(i);
    }

    public a$a(a aVar, Context context) {
        this.h = aVar;
        this.e = context;
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
    }

    public boolean a(int i, String str, boolean z, Bundle bundle) {
        return this.a.add(str) && this.b.add(new Integer(i)) && this.c.add(new Boolean(z)) && this.d.add(bundle);
    }

    public String a(int i) {
        int i2 = 0;
        while (this.a != null && this.a.size() == this.b.size() && this.b != null && i2 < this.b.size()) {
            if (((Integer) this.b.get(i2)).intValue() == i && i2 < this.a.size()) {
                return (String) this.a.get(i2);
            }
            i2++;
        }
        return null;
    }

    public void a() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public int getCount() {
        return this.a.size();
    }

    public String b(int i) {
        return (String) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) ((Integer) this.b.get(i)).intValue();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        boolean z = true;
        LinearBaseMenuItemView linearBaseMenuItemView = (LinearBaseMenuItemView) LayoutInflater.from(this.e).inflate(R.layout.linear_menuitem, null);
        linearBaseMenuItemView.a();
        linearBaseMenuItemView.setText((String) this.a.get(i));
        linearBaseMenuItemView.setNewShow(((Boolean) this.c.get(i)).booleanValue());
        linearBaseMenuItemView.setTag(this.d.get(i));
        Bundle bundle = (Bundle) this.d.get(i);
        if (bundle != null && bundle.getBoolean("setrightview", false)) {
            View inflate = ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(bundle.getInt("resourceid"), null);
            final View findViewById = inflate.findViewById(bundle.getInt("openview"));
            final View findViewById2 = inflate.findViewById(bundle.getInt("closeview"));
            this.f = d.aC(this.e);
            if (!(findViewById == null || findViewById2 == null)) {
                switch (((Integer) this.b.get(i)).intValue()) {
                    case -1:
                        findViewById.setSelected(this.f);
                        findViewById2.setSelected(!this.f);
                        break;
                    case 5:
                        findViewById.setSelected(this.g);
                        if (this.g) {
                            z = false;
                        }
                        findViewById2.setSelected(z);
                        break;
                }
                findViewById.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a$a d;

                    public void onClick(View view) {
                        int i = 0;
                        Message message;
                        switch (((Integer) this.d.b.get(i)).intValue()) {
                            case -1:
                                this.d.f = true;
                                findViewById.setSelected(this.d.f);
                                findViewById2.setSelected(!this.d.f);
                                d.y(this.d.e, this.d.f);
                                if (a.a(this.d.h) != null) {
                                    message = new Message();
                                    message.what = -1;
                                    message.arg1 = 1002;
                                    a.a(this.d.h).sendMessage(message);
                                    return;
                                }
                                return;
                            case 5:
                                this.d.g = true;
                                findViewById.setSelected(this.d.g);
                                findViewById2.setSelected(!this.d.g);
                                Context context = this.d.e;
                                if (!this.d.g) {
                                    i = 1;
                                }
                                d.A(context, i);
                                if (a.a(this.d.h) != null) {
                                    message = new Message();
                                    message.what = 5;
                                    message.arg1 = 1002;
                                    a.a(this.d.h).sendMessage(message);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                });
                findViewById2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a$a d;

                    public void onClick(View view) {
                        boolean z = true;
                        Message message;
                        switch (((Integer) this.d.b.get(i)).intValue()) {
                            case -1:
                                this.d.f = false;
                                findViewById.setSelected(this.d.f);
                                findViewById2.setSelected(!this.d.f);
                                d.y(this.d.e, this.d.f);
                                if (a.a(this.d.h) != null) {
                                    message = new Message();
                                    message.what = -1;
                                    message.arg1 = 1001;
                                    a.a(this.d.h).sendMessage(message);
                                    return;
                                }
                                return;
                            case 5:
                                this.d.g = false;
                                findViewById.setSelected(this.d.g);
                                View view2 = findViewById2;
                                if (this.d.g) {
                                    z = false;
                                }
                                view2.setSelected(z);
                                if (a.a(this.d.h) != null) {
                                    message = new Message();
                                    message.what = 5;
                                    message.arg1 = 1001;
                                    a.a(this.d.h).sendMessage(message);
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
            LinearLayout rightLayout = linearBaseMenuItemView.getRightLayout();
            if (rightLayout.getChildCount() <= 0) {
                rightLayout.addView(inflate);
                rightLayout.setVisibility(0);
            }
        }
        return linearBaseMenuItemView;
    }
}
