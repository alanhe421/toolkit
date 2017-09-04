package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.feed.mypreference.b.a;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

class MyFeedPreferenceActivity$a extends BaseAdapter implements e {
    final /* synthetic */ MyFeedPreferenceActivity a;
    private Context b;
    private b c;
    private int d = 10;
    private ArrayList<MyFeedPreferenceActivity$b> e = null;
    private MyFeedPreferenceActivity$c f;

    public void a(MyFeedPreferenceActivity$c myFeedPreferenceActivity$c) {
        this.f = myFeedPreferenceActivity$c;
    }

    public MyFeedPreferenceActivity$a(MyFeedPreferenceActivity myFeedPreferenceActivity, Context context, List<Long> list) {
        this.a = myFeedPreferenceActivity;
        this.b = context;
        if (MyFeedPreferenceActivity.k(myFeedPreferenceActivity)) {
            MyFeedPreferenceActivity.a(myFeedPreferenceActivity, true);
        }
        this.c = new b(context, list, MyFeedPreferenceActivity.n(myFeedPreferenceActivity));
        this.e = new ArrayList();
        this.e.clear();
        this.e.add(new MyFeedPreferenceActivity$b(myFeedPreferenceActivity, 0, 0));
        for (Entry entry : this.c.f().entrySet()) {
            this.e.add(new MyFeedPreferenceActivity$b(myFeedPreferenceActivity, ((Integer) entry.getKey()).intValue(), ((Integer) entry.getValue()).intValue()));
        }
        this.e.add(new MyFeedPreferenceActivity$b(myFeedPreferenceActivity, 4, 0));
    }

    public int getCount() {
        return this.c.b();
    }

    public Object getItem(int i) {
        return this.c.a(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.b, R.layout.select_pref_grid_layout, null);
            view.setLayoutParams(new LayoutParams(-1, MyFeedPreferenceActivity.o(this.a)));
        }
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_select);
        a aVar = (a) getItem(i);
        checkBox.setText(aVar.a);
        checkBox.setChecked(aVar.e);
        if (this.c == null || this.c.e() != this.d) {
            checkBox.setTextColor(this.a.getResources().getColorStateList(R.color.select_pref_checkbox_text_color));
        } else {
            checkBox.setTextColor(this.a.getResources().getColorStateList(R.color.select_pref_checkbox_text_color_full));
        }
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyFeedPreferenceActivity$a c;

            public void onClick(View view) {
                int i = 0;
                if (!TextUtils.isEmpty(MyFeedPreferenceActivity.p(this.c.a))) {
                    i.a("event_F192", null, ReaderApplication.getApplicationImp());
                }
                boolean z = !checkBox.isChecked();
                if (!z || this.c.c == null || this.c.c.e() < this.c.d) {
                    checkBox.setChecked(z);
                    if (!(this.c.c == null || this.c.c.a(i) == null)) {
                        this.c.c.a(i).e = z;
                    }
                    if (this.c.f != null) {
                        MyFeedPreferenceActivity$c c = this.c.f;
                        if (this.c.c != null) {
                            i = this.c.c.e();
                        }
                        c.a(i);
                    }
                    MyFeedPreferenceActivity.q(this.c.a).notifyDataSetChanged();
                    return;
                }
                af.a(this.c.a, "最多可选择10个基因", 0).a();
            }
        });
        return view;
    }

    public ArrayList<String> b() {
        if (this.c != null) {
            return this.c.c();
        }
        return null;
    }

    public ArrayList<String> c() {
        if (this.c != null) {
            return this.c.d();
        }
        return null;
    }

    public int a(int i) {
        if (this.e == null || i >= this.e.size() || this.e.get(i) == null) {
            return 0;
        }
        return ((MyFeedPreferenceActivity$b) this.e.get(i)).b();
    }

    public int a() {
        if (this.e != null) {
            return this.e.size();
        }
        return 0;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        View view2;
        if (((MyFeedPreferenceActivity$b) this.e.get(i)).a() == 0) {
            View inflate = View.inflate(this.a.getContext(), R.layout.select_pref_grid_header, null);
            if (MyFeedPreferenceActivity.k(this.a)) {
                ((TextView) inflate.findViewById(R.id.tv_header_text)).setText(R.string.select_rookie_pref_tip);
                view2 = inflate;
            } else {
                view2 = inflate;
            }
        } else {
            view2 = View.inflate(this.a.getContext(), R.layout.myfeedperference_option_header_ui, null);
        }
        view2.setVisibility(0);
        view2.setLayoutParams(new LayoutParams(-1, -2));
        TextView textView = (TextView) view2.findViewById(R.id.tv_header_text);
        if (((MyFeedPreferenceActivity$b) this.e.get(i)).a() == 1) {
            textView.setText("以下推荐男生选择");
        } else if (((MyFeedPreferenceActivity$b) this.e.get(i)).a() == 2) {
            textView.setText("以下推荐女生选择");
        } else if (((MyFeedPreferenceActivity$b) this.e.get(i)).a() == 3) {
            textView.setText("以下推荐出版用户选择");
        } else if (((MyFeedPreferenceActivity$b) this.e.get(i)).a() == 4) {
            view2.setVisibility(4);
            view2.setLayoutParams(new LayoutParams(-1, 10));
        }
        return view2;
    }
}
