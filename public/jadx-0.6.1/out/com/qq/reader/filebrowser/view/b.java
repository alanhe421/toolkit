package com.qq.reader.filebrowser.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.filebrowser.a;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: IconifiedTextListAdapter */
public class b extends BaseAdapter {
    private Context a;
    private List<a> b;
    private a c;
    private c d;
    private WeakReference<Drawable> e;
    private WeakReference<Drawable> f;
    private WeakReference<Drawable> g;
    private WeakReference<Drawable> h;
    private WeakReference<Drawable> i;
    private af j = null;

    public b(Context context, List<a> list) {
        this.a = context;
        this.b = list;
        this.c = new a(this, " 123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public af a() {
        return this.j;
    }

    public void a(String str, boolean z) {
        if (getCount() > 0) {
            for (a aVar : this.b) {
                if ((aVar.a() != null && aVar.a().equals(str)) || (aVar.b() != null && str.equals(aVar.b().getPath()))) {
                    break;
                }
            }
            a aVar2 = null;
            if (aVar2 != null) {
                this.a.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.ch));
                if (z) {
                    aVar2.b(3);
                } else {
                    aVar2.b(0);
                }
                notifyDataSetChanged();
            }
        }
    }

    public void a(List<a> list) {
        this.b = list;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        final a aVar = (a) this.b.get(i);
        if (view == null) {
            view2 = (IconifiedTextView) LayoutInflater.from(this.a).inflate(R.layout.localbook_list_item, viewGroup, false);
            view2.a();
            view2.setDate(aVar, b(aVar.e()), a(aVar.e()));
        } else {
            view = (IconifiedTextView) view;
            view.setDate(aVar, b(aVar.e()), a(aVar.e()));
            view2 = view;
        }
        final int e = aVar.e();
        if (e == 0 || e == 1) {
            view2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b c;

                public void onClick(View view) {
                    boolean z = true;
                    if (e == 0) {
                        aVar.b(1);
                    } else {
                        aVar.b(0);
                        z = false;
                    }
                    this.c.notifyDataSetChanged();
                    if (this.c.d != null) {
                        this.c.d.a(aVar, z);
                    }
                    i.a("event_A83", null, ReaderApplication.getApplicationImp());
                }
            });
        } else if (view2.getIcon() != null) {
            view2.setOnClickListener(null);
            view2.setClickable(false);
        }
        return view2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable a(int r3) {
        /*
        r2 = this;
        switch(r3) {
            case 0: goto L_0x0005;
            case 1: goto L_0x0005;
            case 2: goto L_0x0039;
            case 3: goto L_0x0005;
            case 4: goto L_0x006d;
            default: goto L_0x0003;
        };
    L_0x0003:
        r0 = 0;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = r2.h;
        if (r0 == 0) goto L_0x001a;
    L_0x0009:
        r0 = r2.h;
        r0 = r0.get();
        if (r0 == 0) goto L_0x001a;
    L_0x0011:
        r0 = r2.h;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
    L_0x001a:
        r0 = r2.a;
        r0 = r0.getResources();
        r1 = 2130838869; // 0x7f020555 float:1.7282732E38 double:1.052774282E-314;
        r0 = r0.getDrawable(r1);
        if (r0 == 0) goto L_0x0039;
    L_0x0029:
        r1 = new java.lang.ref.WeakReference;
        r1.<init>(r0);
        r2.h = r1;
        r0 = r2.h;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
    L_0x0039:
        r0 = r2.g;
        if (r0 == 0) goto L_0x004e;
    L_0x003d:
        r0 = r2.g;
        r0 = r0.get();
        if (r0 == 0) goto L_0x004e;
    L_0x0045:
        r0 = r2.g;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
    L_0x004e:
        r0 = r2.a;
        r0 = r0.getResources();
        r1 = 2130838870; // 0x7f020556 float:1.7282735E38 double:1.0527742825E-314;
        r0 = r0.getDrawable(r1);
        if (r0 == 0) goto L_0x006d;
    L_0x005d:
        r1 = new java.lang.ref.WeakReference;
        r1.<init>(r0);
        r2.g = r1;
        r0 = r2.g;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
    L_0x006d:
        r0 = r2.i;
        if (r0 == 0) goto L_0x0082;
    L_0x0071:
        r0 = r2.i;
        r0 = r0.get();
        if (r0 == 0) goto L_0x0082;
    L_0x0079:
        r0 = r2.i;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
    L_0x0082:
        r0 = r2.a;
        r0 = r0.getResources();
        r1 = 2130838868; // 0x7f020554 float:1.728273E38 double:1.0527742815E-314;
        r0 = r0.getDrawable(r1);
        if (r0 == 0) goto L_0x0003;
    L_0x0091:
        r1 = new java.lang.ref.WeakReference;
        r1.<init>(r0);
        r2.i = r1;
        r0 = r2.i;
        r0 = r0.get();
        r0 = (android.graphics.drawable.Drawable) r0;
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.filebrowser.view.b.a(int):android.graphics.drawable.Drawable");
    }

    public Drawable b(int i) {
        Drawable drawable;
        switch (i) {
            case 0:
                if (this.f != null && this.f.get() != null) {
                    return (Drawable) this.f.get();
                }
                drawable = this.a.getResources().getDrawable(R.drawable.checkbox_off);
                if (drawable != null) {
                    this.f = new WeakReference(drawable);
                    return (Drawable) this.f.get();
                }
                break;
            case 1:
                if (this.e != null && this.e.get() != null) {
                    return (Drawable) this.e.get();
                }
                drawable = this.a.getResources().getDrawable(R.drawable.checkbox_on);
                if (drawable != null) {
                    this.e = new WeakReference(drawable);
                    return (Drawable) this.e.get();
                }
                break;
        }
        return null;
    }

    public void a(c cVar) {
        this.d = cVar;
    }
}
