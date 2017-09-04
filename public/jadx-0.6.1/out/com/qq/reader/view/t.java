package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.qq.reader.common.monitor.f;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PopupMenu */
public class t {
    public Context a;
    public ListView b;
    public a c;
    public com.qq.reader.view.a.a d;
    private PopupWindow e;
    private int f;

    /* compiled from: PopupMenu */
    public class a extends BaseAdapter {
        List<Integer> a = new ArrayList();
        final /* synthetic */ t b;
        private ArrayList<String> c = new ArrayList();

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(t tVar) {
            this.b = tVar;
        }

        public boolean a(String str, int i) {
            return this.c.add(str) && this.a.add(new Integer(i));
        }

        public int getCount() {
            return this.c.size();
        }

        public String a(int i) {
            return (String) this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) ((Integer) this.a.get(i)).intValue();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b.a).inflate(R.layout.popupmenuitem, viewGroup, false);
                view.setTag(view.findViewById(R.id.popupMenuItemName));
            }
            ((TextView) view.getTag()).setText(a(i));
            return view;
        }
    }

    public t(Activity activity, int i) {
        this.a = activity.getApplicationContext();
        this.f = i;
        View inflate = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.popup_menu, null);
        int i2 = (com.qq.reader.common.c.a.bU * 2) / 3;
        int i3 = com.qq.reader.common.c.a.bT / 2;
        if (i == 5) {
            inflate.findViewById(R.id.popup).setBackgroundResource(R.drawable.readpage_topbar_more_popup);
            i2 = com.qq.reader.common.c.a.bU / 3;
            i3 = -2;
        }
        this.e = new PopupWindow(inflate, i2, i3);
        this.e.setOutsideTouchable(true);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                this.a.e.setFocusable(false);
            }
        });
        this.b = (ListView) inflate.findViewById(R.id.menulist);
        this.c = new a(this);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.d != null) {
                    this.a.d.a((int) j);
                }
                this.a.e.dismiss();
            }
        });
    }

    public void a(View view) {
        int width = this.f == 17 ? (view.getWidth() / 2) - (this.e.getWidth() / 2) : this.f == 5 ? view.getWidth() - this.e.getWidth() : 0;
        f.b("coder", "xPos:" + width);
        this.e.showAsDropDown(view, width, 0);
        this.e.setFocusable(true);
        this.e.update();
    }

    public boolean a(String str, int i) {
        return this.c.a(str, i);
    }

    public void a(com.qq.reader.view.a.a aVar) {
        this.d = aVar;
    }
}
