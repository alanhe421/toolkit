package com.qq.reader.module.bookshelf.a.a;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: CategoryIndexAdapter */
public class d extends BaseAdapter {
    private int[] a = new int[]{R.drawable.profile_account_icon, R.drawable.profile_shoppinglist_icon, R.drawable.profile_notes_icon, R.drawable.profile_cloudshelf_icon, R.drawable.profile_plugin_icon, R.drawable.profile_about_icon};
    private Context b;
    private Handler c;
    private ArrayList<MetroItem> d;

    public d(Context context, Handler handler, ArrayList<MetroItem> arrayList) {
        this.b = context;
        this.c = handler;
        this.d = arrayList;
    }

    public int getCount() {
        if (this.d != null) {
            return this.d.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.d == null || i < 0 || i >= this.d.size()) {
            return null;
        }
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.category_index_list_item, null);
            e eVar2 = new e(view);
            view.setTag(eVar2);
            eVar = eVar2;
        } else {
            eVar = (e) view.getTag();
        }
        MetroItem metroItem = (MetroItem) getItem(i);
        if (metroItem != null) {
            if (metroItem.getId() == 10001) {
                eVar.a((int) R.drawable.category_icon_cloud);
            } else if (metroItem.getId() == Constants.CODE_LOGIC_REGISTER_IN_PROCESS) {
                eVar.a((int) R.drawable.category_icon_local);
            } else {
                eVar.a((int) R.drawable.category_icon_default);
            }
            eVar.a(metroItem.getDisplayName());
            eVar.b(metroItem.getCount());
        }
        return view;
    }

    public synchronized void a(ArrayList<MetroItem> arrayList) {
        this.d = arrayList;
    }
}
