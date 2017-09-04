package com.qq.reader.module.redpacket.square.a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.module.redpacket.view.RedPacketMessageView;
import java.util.ArrayList;

/* compiled from: RedPacketSquareMessageAdapter */
public class d extends PagerAdapter {
    private ArrayList<RedPacketMessageView> a;

    public void a(ArrayList<RedPacketMessageView> arrayList) {
        this.a = arrayList;
    }

    public boolean a(View view, Object obj) {
        return view == obj;
    }

    public int a() {
        return this.a.size();
    }

    public Object a(ViewGroup viewGroup, int i) {
        View view = (View) this.a.get(i);
        if (view.getParent() == null) {
            viewGroup.addView(view);
        } else {
            ((ViewGroup) view.getParent()).removeView(view);
            viewGroup.addView(view);
        }
        return view;
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
