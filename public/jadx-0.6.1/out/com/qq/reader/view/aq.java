package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: TopBarPopupMenu */
public class aq extends BaseDialog {
    public Context a;
    public ListView b;
    public a c;
    public com.qq.reader.view.a.a d;
    private View e = this.f.findViewById(R.id.readpage_topbar_popup);
    private boolean i;

    /* compiled from: TopBarPopupMenu */
    public class a extends BaseAdapter {
        final /* synthetic */ aq a;
        private ArrayList<b> b = new ArrayList();

        /* compiled from: TopBarPopupMenu */
        public class a {
            TextView a;
            TextView b;
            View c;
            final /* synthetic */ a d;

            public a(a aVar) {
                this.d = aVar;
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return b(i);
        }

        public a(aq aqVar) {
            this.a = aqVar;
        }

        public boolean a(String str, int i, int i2, boolean z) {
            return this.b.add(new b(this.a, str, i, i2, z));
        }

        public boolean a(String str, int i, int i2, int i3, boolean z) {
            return this.b.add(new b(this.a, str, i, i2, i3, z));
        }

        public boolean b(String str, int i, int i2, boolean z) {
            int size = this.b.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (((b) this.b.get(i3)).c() == i2) {
                    b bVar = (b) this.b.get(i3);
                    bVar.a(str);
                    bVar.b(i2);
                    bVar.c(i);
                    bVar.a(z);
                    return true;
                }
            }
            return false;
        }

        public boolean b(String str, int i, int i2, int i3, boolean z) {
            int size = this.b.size();
            for (int i4 = 0; i4 < size; i4++) {
                if (((b) this.b.get(i4)).c() == i3) {
                    b bVar = (b) this.b.get(i4);
                    bVar.a(str);
                    bVar.b(i3);
                    bVar.c(i);
                    bVar.a(z);
                    bVar.a(i2);
                    return true;
                }
            }
            return false;
        }

        public boolean a(int i) {
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((b) this.b.get(i2)).c() == i) {
                    this.b.remove(i2);
                    return true;
                }
            }
            return false;
        }

        public int getCount() {
            return this.b.size();
        }

        public b b(int i) {
            return (b) this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) ((b) this.b.get(i)).c();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            a aVar;
            Drawable drawable;
            if (view == null) {
                view = LayoutInflater.from(this.a.a).inflate(R.layout.top_bar_popup_menu_item, viewGroup, false);
                a aVar2 = new a(this);
                aVar2.a = (TextView) view.findViewById(R.id.popupMenuItemName);
                aVar2.b = (TextView) view.findViewById(R.id.popupMenuItemInfo);
                aVar2.c = view.findViewById(R.id.popupMenuItemDivider);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            if (this.a.i) {
                view.setBackgroundResource(R.drawable.popup_menu_item);
                aVar.a.setTextColor(view.getContext().getResources().getColorStateList(R.color.text_color_c304));
                aVar.b.setTextColor(view.getContext().getResources().getColor(R.color.text_color_c102));
                aVar.c.setBackgroundColor(Color.parseColor("#000000"));
            } else {
                view.setBackgroundResource(R.drawable.bookshelf_popup_menu_item_bg_selector);
                aVar.a.setTextColor(view.getContext().getResources().getColorStateList(R.color.text_color_c301));
                aVar.b.setTextColor(view.getContext().getResources().getColor(R.color.text_color_c801));
                aVar.c.setBackgroundColor(Color.parseColor("#e6e6e6"));
            }
            aVar.a.setText(b(i).b());
            if (this.a.i) {
                drawable = this.a.a.getResources().getDrawable(b(i).a());
            } else {
                drawable = this.a.a.getResources().getDrawable(b(i).d());
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            aVar.a.setCompoundDrawables(drawable, null, null, null);
            if (b(i).e()) {
                aVar.b.setVisibility(0);
            } else {
                aVar.b.setVisibility(4);
            }
            if (i == getCount() - 1) {
                aVar.c.setVisibility(8);
            } else {
                aVar.c.setVisibility(0);
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.a.d != null) {
                        this.b.a.d.a((int) this.b.getItemId(i));
                    }
                    this.b.a.cancel();
                }
            });
            return view;
        }
    }

    /* compiled from: TopBarPopupMenu */
    public class b {
        final /* synthetic */ aq a;
        private String b;
        private int c;
        private int d;
        private int e;
        private boolean f;

        public b(aq aqVar, String str, int i, int i2, boolean z) {
            this.a = aqVar;
            this.b = str;
            this.c = i2;
            this.d = i;
            this.f = z;
        }

        public b(aq aqVar, String str, int i, int i2, int i3, boolean z) {
            this.a = aqVar;
            this.b = str;
            this.c = i3;
            this.d = i;
            this.f = z;
            this.e = i2;
        }

        public int a() {
            return this.e;
        }

        public void a(int i) {
            this.e = i;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public int c() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public int d() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public boolean e() {
            return this.f;
        }

        public void a(boolean z) {
            this.f = z;
        }
    }

    public aq(Activity activity, int i, int i2, int i3) {
        this.a = activity.getApplicationContext();
        a(activity, null, R.layout.top_bar_popup_menu, i, true);
        LayoutParams layoutParams = this.e.getLayoutParams();
        layoutParams.width = i2;
        this.f.getWindow().getAttributes();
        this.e.setLayoutParams(layoutParams);
        this.b = (ListView) this.f.findViewById(R.id.menulist);
        this.c = new a(this);
        this.b.setAdapter(this.c);
        this.f.setCanceledOnTouchOutside(true);
        if (i3 == 1) {
            this.e.setBackgroundResource(R.drawable.readpage_popmenu_bg);
        } else {
            this.e.setBackgroundResource(R.drawable.bookshefl_popmenu_bg);
        }
    }

    public void a(boolean z) {
        if (z != this.i) {
            if (z) {
                this.e.setBackgroundResource(R.drawable.readpage_topbar_more_popup);
            } else {
                this.e.setBackgroundResource(R.drawable.readpage_popmenu_bg);
            }
            this.i = z;
        }
    }

    public void f_() {
        super.f_();
        this.f.show();
    }

    public boolean a(String str, int i, int i2) {
        return this.c.a(str, i, i2, false);
    }

    public boolean a(String str, int i, int i2, boolean z) {
        return this.c.a(str, i, i2, z);
    }

    public boolean a(String str, int i, int i2, int i3, boolean z) {
        return this.c.a(str, i, i2, i3, z);
    }

    public boolean b(String str, int i, int i2, boolean z) {
        return this.c.b(str, i, i2, z);
    }

    public boolean b(String str, int i, int i2, int i3, boolean z) {
        return this.c.b(str, i, i2, i3, z);
    }

    public boolean b(int i) {
        return this.c.a(i);
    }

    public void g() {
        this.c.notifyDataSetChanged();
    }

    public void a(com.qq.reader.view.a.a aVar) {
        this.d = aVar;
    }
}
