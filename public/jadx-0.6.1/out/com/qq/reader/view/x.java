package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QRTopBarPopupMenu */
public class x extends BaseDialog {
    public Context a;
    public ListView b;
    public a c = new a(this);
    public com.qq.reader.view.a.a d;
    private View e;

    /* compiled from: QRTopBarPopupMenu */
    public class a extends BaseAdapter {
        List<Integer> a = new ArrayList();
        List<Integer> b = new ArrayList();
        final /* synthetic */ x c;
        private ArrayList<String> d = new ArrayList();

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(x xVar) {
            this.c = xVar;
        }

        public boolean a(String str, int i, int i2) {
            return this.d.add(str) && this.a.add(Integer.valueOf(i2)) && this.b.add(Integer.valueOf(i));
        }

        public int getCount() {
            return this.d.size();
        }

        public String a(int i) {
            return (String) this.d.get(i);
        }

        public long getItemId(int i) {
            return (long) ((Integer) this.a.get(i)).intValue();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.c.a).inflate(R.layout.readpage_topbar_popupmenuitem, viewGroup, false);
                view.setTag(view.findViewById(R.id.popupMenuItemName));
            }
            TextView textView = (TextView) view.getTag();
            textView.setText(a(i));
            Drawable drawable = this.c.a.getResources().getDrawable(((Integer) this.b.get(i)).intValue());
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.c.d != null) {
                        this.b.c.d.a((int) this.b.getItemId(i));
                    }
                    this.b.c.cancel();
                }
            });
            return view;
        }
    }

    public x(Activity activity, int i, int i2, int i3, int i4) {
        this.a = activity.getApplicationContext();
        a(activity, null, i, i4, true);
        this.e = this.f.findViewById(i2);
        this.b = (ListView) this.f.findViewById(i3);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ x a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.d != null) {
                    this.a.d.a((int) j);
                }
                this.a.cancel();
            }
        });
    }

    public void f_() {
        this.f.show();
    }

    public boolean a(String str, int i, int i2) {
        return this.c.a(str, i, i2);
    }

    public void a(com.qq.reader.view.a.a aVar) {
        this.d = aVar;
    }
}
