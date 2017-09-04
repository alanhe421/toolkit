package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.j;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderMenu */
public class ab extends BaseDialog implements l {
    private GridView a = ((GridView) this.f.findViewById(R.id.gridview));
    private a b = new a(this, this.c);
    private Context c;
    private b d;
    private c e;
    private LinearLayout i;
    private k j;

    /* compiled from: ReaderMenu */
    private class a extends BaseAdapter {
        List<String> a = new ArrayList();
        List<Drawable> b = new ArrayList();
        List<Drawable> c = new ArrayList();
        List<Integer> d = new ArrayList();
        List<Boolean> e = new ArrayList();
        List<Integer> f = new ArrayList();
        Context g;
        final /* synthetic */ ab h;

        /* compiled from: ReaderMenu */
        private class a {
            public TextView a;
            final /* synthetic */ a b;
            private ImageView c;

            public a(a aVar, View view) {
                this.b = aVar;
                this.a = (TextView) view.findViewById(R.id.menu_name);
                this.c = (ImageView) view.findViewById(R.id.menu_new);
            }

            public void a(Drawable drawable) {
                this.a.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            }

            public void a(String str) {
                this.a.setText(str);
            }

            public void a(boolean z) {
                this.c.setVisibility(z ? 0 : 8);
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(ab abVar, Context context) {
            this.h = abVar;
            this.g = context;
        }

        public boolean a(int i, String str, Drawable drawable, boolean z, int i2) {
            return this.a.add(str) && this.b.add(drawable) && this.d.add(Integer.valueOf(i)) && this.e.add(Boolean.valueOf(z)) && this.f.add(Integer.valueOf(i2));
        }

        public boolean a(int i, String str, Drawable drawable, Drawable drawable2, boolean z, int i2) {
            return this.a.add(str) && this.b.add(drawable) && this.d.add(Integer.valueOf(i)) && this.e.add(Boolean.valueOf(z)) && this.f.add(Integer.valueOf(i2)) && this.c.add(drawable2);
        }

        public void b(int i, String str, Drawable drawable, boolean z, int i2) {
            for (int i3 = 0; i3 < this.d.size(); i3++) {
                if (getItemId(i3) == ((long) i)) {
                    this.a.set(i3, str);
                    this.b.set(i3, drawable);
                    this.e.set(i3, Boolean.valueOf(z));
                    this.f.set(i3, Integer.valueOf(i2));
                    return;
                }
            }
        }

        public int getCount() {
            return this.a.size();
        }

        public String a(int i) {
            return (String) this.a.get(i);
        }

        public long getItemId(int i) {
            return (long) ((Integer) this.d.get(i)).intValue();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.g).inflate(R.layout.menuitem, null);
                view.setTag(new a(this, view));
            }
            a aVar = (a) view.getTag();
            if (d.n) {
                aVar.a((Drawable) this.c.get(i));
                aVar.a.setTextColor(this.g.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_nightmode_selector));
            } else {
                aVar.a((Drawable) this.b.get(i));
                aVar.a.setTextColor(this.g.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
            }
            aVar.a(((Boolean) this.e.get(i)).booleanValue());
            if (((Integer) this.f.get(i)).intValue() <= 0) {
                aVar.a((String) this.a.get(i));
            } else {
                aVar.a("" + j.a((long) ((Integer) this.f.get(i)).intValue()));
            }
            aVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.h.d != null) {
                        this.b.h.d.a((int) this.b.getItemId(i));
                        this.b.h.cancel();
                    }
                }
            });
            return view;
        }
    }

    /* compiled from: ReaderMenu */
    public interface b {
        boolean a(int i);
    }

    /* compiled from: ReaderMenu */
    public interface c {
        k a(int i);
    }

    public ab(Activity activity, int i) {
        this.c = activity;
        View inflate = ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.readermenu, null);
        ((GridView) inflate.findViewById(R.id.gridview)).setNumColumns(i);
        this.i = (LinearLayout) inflate.findViewById(R.id.ll_ext_container);
        a(activity, inflate, R.layout.readermenu, true, false, true);
        this.a.setAdapter(this.b);
        a(d.n);
    }

    public LinearLayout g() {
        return this.i;
    }

    public void f_() {
        this.f.show();
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public boolean a(int i, String str, int i2) {
        if (this.b == null) {
            return false;
        }
        return this.b.a(i, str, this.c.getResources().getDrawable(i2), false, 0);
    }

    public boolean a(int i, String str, int i2, int i3, boolean z, int i4) {
        if (this.b == null) {
            return false;
        }
        return this.b.a(i, str, this.c.getResources().getDrawable(i2), this.c.getResources().getDrawable(i3), z, i4);
    }

    public boolean a(int i, String str, int i2, int i3, boolean z) {
        if (this.b == null) {
            return false;
        }
        return this.b.a(i, str, this.c.getResources().getDrawable(i2), this.c.getResources().getDrawable(i3), z, 0);
    }

    public void a(int i, String str, int i2, boolean z, int i3) {
        if (this.b != null) {
            this.b.b(i, str, this.c.getResources().getDrawable(i2), z, i3);
        }
    }

    public void a(int i, String str, int i2, boolean z) {
        if (this.b != null) {
            this.b.b(i, str, this.c.getResources().getDrawable(i2), z, 0);
        }
    }

    public void h() {
        this.b.notifyDataSetChanged();
    }

    public void i() {
        this.a.setNumColumns(this.b.getCount());
    }

    public void a(boolean z) {
        if (z) {
            this.f.findViewById(R.id.top_shadow).setVisibility(8);
            this.a.setBackgroundResource(R.color.commonsetting_bg_color_night);
            return;
        }
        this.f.findViewById(R.id.top_shadow).setVisibility(0);
        this.a.setBackgroundResource(R.color.commonsetting_bg_color);
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public void dismiss(int i) {
    }

    public k getHighLightArea(int i) {
        if (this.j == null && this.e != null) {
            this.j = this.e.a(i);
        }
        return this.j;
    }
}
