package com.qq.reader.module.bookshelf.signup.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: ForgetSignAdapter */
public class a extends BaseAdapter {
    private Context a;
    private List<com.qq.reader.module.bookshelf.signup.b.a> b;
    private int c = 0;

    /* compiled from: ForgetSignAdapter */
    class a {
        TextView a;
        ImageView b;
        final /* synthetic */ a c;

        a(a aVar) {
            this.c = aVar;
        }
    }

    public a(Context context, List<com.qq.reader.module.bookshelf.signup.b.a> list, int i) {
        this.a = context;
        this.b = list;
        this.c = i;
    }

    public int a() {
        return this.c;
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
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.bookshelf_sign_make_up_tip_pop_window_item, null);
            aVar = new a(this);
            aVar.a = (TextView) view.findViewById(R.id.sign_mis_name);
            aVar.b = (ImageView) view.findViewById(R.id.sigh_mis_icon);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((com.qq.reader.module.bookshelf.signup.b.a) this.b.get(i)).a());
        aVar.b.setImageResource(((com.qq.reader.module.bookshelf.signup.b.a) this.b.get(i)).b());
        return view;
    }
}
