package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.card.impl.TopicListCardKinds;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.List;

/* compiled from: NativeTopicListAdapterKinds */
public class h extends BaseAdapter {
    Context a;
    List<s> b;
    private a c = null;

    public h(Context context, List<s> list) {
        this.a = context;
        this.b = list;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public Object getItem(int i) {
        if (this.b == null) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getItemViewType(int i) {
        return ((TopicListCardKinds.a) getItem(i)).a();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TopicListCardKinds.a aVar = (TopicListCardKinds.a) getItem(i);
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(aVar.b(), null);
        }
        aVar.a(view);
        return view;
    }
}
