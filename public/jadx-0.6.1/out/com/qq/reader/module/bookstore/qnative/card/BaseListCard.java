package com.qq.reader.module.bookstore.qnative.card;

import android.widget.BaseAdapter;
import com.qq.reader.module.bookstore.qnative.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import org.json.JSONObject;

public class BaseListCard extends a {
    public BaseAdapter mAdapter;
    protected boolean mIsFromCharis = false;

    public BaseListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return 0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void setIsFromCharis(boolean z) {
        this.mIsFromCharis = z;
    }

    public void notifyDataSetChanged() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void attachView() {
    }

    public boolean isAddAble() {
        return true;
    }

    public boolean addMore(a aVar) {
        if (!(aVar instanceof BaseListCard)) {
            return false;
        }
        getItemList().addAll(((BaseListCard) aVar).getItemList());
        notifyDataSetChanged();
        return true;
    }
}
