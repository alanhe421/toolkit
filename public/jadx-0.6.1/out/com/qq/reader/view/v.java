package com.qq.reader.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QRBaseTabMenuAdapter */
public class v extends BaseAdapter {
    private Context a;
    private List<String> b = new ArrayList();
    private List<Drawable> c = new ArrayList();
    private List<Integer> d = new ArrayList();
    private List<Boolean> e = new ArrayList();
    private List<Integer> f = new ArrayList();
    private com.qq.reader.view.u.a g;
    private int h;
    private int i = 0;

    /* compiled from: QRBaseTabMenuAdapter */
    private class a {
        final /* synthetic */ v a;
        private TextView b;
        private ImageView c;

        public a(v vVar, View view) {
            this.a = vVar;
            this.b = (TextView) view.findViewById(R.id.menu_name);
            this.c = (ImageView) view.findViewById(R.id.menu_new);
        }

        public void a(String str) {
            this.b.setText(str);
            this.b.setTextColor(this.a.a.getResources().getColor(R.color.textcolor_black));
        }

        public void a(int i) {
        }

        public void a(boolean z) {
            this.b.setSelected(z);
            if (z) {
                this.b.setTextColor(this.a.a.getResources().getColor(R.color.textcolor_white));
            } else {
                this.b.setTextColor(this.a.a.getResources().getColor(R.color.textcolor_black));
            }
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return b(i);
    }

    public v(Context context, int i) {
        this.a = context;
        this.h = i;
    }

    public void a() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.i = 0;
    }

    public boolean a(int i, String str, Drawable drawable, boolean z) {
        return this.b.add(str) && this.c.add(drawable) && this.d.add(Integer.valueOf(i)) && this.e.add(Boolean.valueOf(z)) && this.f.add(Integer.valueOf(-1));
    }

    public void a(int i) {
        this.i = i;
    }

    public int b() {
        return this.i;
    }

    public int getCount() {
        return this.b.size();
    }

    public String b(int i) {
        return (String) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) ((Integer) this.d.get(i)).intValue();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(this.h, null);
        }
        view.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ v b;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 1:
                    case 3:
                        if (this.b.g != null) {
                            this.b.a(i);
                            this.b.g.a((int) this.b.getItemId(i));
                            break;
                        }
                        break;
                }
                return true;
            }
        });
        a aVar = new a(this, view);
        aVar.a((String) this.b.get(i));
        aVar.a(this.i == i);
        a(aVar, i);
        return view;
    }

    private void a(a aVar, int i) {
        if (i == 0) {
            aVar.a((int) R.drawable.bookshelf_tab_left_btn);
        } else if (i == this.b.size() - 1) {
            aVar.a((int) R.drawable.bookshelf_tab_right_btn);
        } else {
            aVar.a((int) R.drawable.bookshelf_tab_mid_btn);
        }
    }

    public void a(com.qq.reader.view.u.a aVar) {
        this.g = aVar;
    }
}
