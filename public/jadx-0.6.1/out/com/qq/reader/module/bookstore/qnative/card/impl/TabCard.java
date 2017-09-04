package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.h;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class TabCard extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private Context mContext = ReaderApplication.getApplicationImp();

    class a extends BaseAdapter {
        final /* synthetic */ TabCard a;

        a(TabCard tabCard) {
            this.a = tabCard;
        }

        public int getCount() {
            int size = this.a.getItemList().size() % 3;
            if (size > 0) {
                return (this.a.getItemList().size() + 3) - size;
            }
            return this.a.getItemList().size();
        }

        public Object getItem(int i) {
            if (i >= this.a.getItemList().size()) {
                return null;
            }
            return this.a.getItemList().get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_tab_item, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            h hVar = (h) getItem(i);
            if (hVar == null) {
                bVar.a("");
            } else {
                bVar.a(hVar.c());
                if (hVar.b()) {
                    bVar.a(true);
                } else {
                    bVar.a(false);
                }
            }
            return view;
        }
    }

    class b {
        final /* synthetic */ TabCard a;
        private TextView b;
        private ImageView c;

        public b(TabCard tabCard, View view) {
            this.a = tabCard;
            this.b = (TextView) view.findViewById(R.id.text);
            this.c = (ImageView) view.findViewById(R.id.hot);
        }

        public void a(String str) {
            this.b.setText(str);
        }

        public void a(boolean z) {
            if (z) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
        }
    }

    public TabCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_tab_0;
    }

    public void attachView() {
        GridView gridView = (GridView) ap.a(getRootView(), R.id.otherGridView_1);
        gridView.setAdapter(new a(this));
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ TabCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.getItemList().size()) {
                    h hVar = (h) this.a.getItemList().get(i);
                    hVar.a().a().putString("KEY_ACTIONTAG", this.a.mValue);
                    hVar.a().a(this.a.getEvnetListener());
                }
            }
        });
        ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(this.mIconIndex, this.mShowTitle, null, null);
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        JSONObject jSONObject2 = null;
        switch (this.mSex) {
            case 1:
                jSONObject2 = jSONObject.optJSONObject("boy");
                break;
            case 2:
                jSONObject2 = jSONObject.optJSONObject("girl");
                break;
            case 3:
                jSONObject2 = jSONObject.optJSONObject("publish");
                break;
        }
        JSONArray jSONArray = jSONObject2.getJSONArray("categorys");
        for (int i = 0; i < jSONArray.length(); i++) {
            s hVar = new h();
            hVar.parseData(jSONArray.getJSONObject(i));
            addItem(hVar);
        }
        return true;
    }
}
