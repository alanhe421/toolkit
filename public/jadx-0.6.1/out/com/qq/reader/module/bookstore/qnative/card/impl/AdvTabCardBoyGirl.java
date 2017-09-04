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
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.h;
import com.qq.reader.module.bookstore.qnative.item.i;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvTabCardBoyGirl extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private static final int TYPE_BOY = 1;
    private static final int TYPE_GIRL = 2;
    private List<h> mBoyGridItemList = new ArrayList();
    private List<i> mBoyKeysItemList = new ArrayList();
    private String mBoyTitleExt;
    private Context mContext;
    private List<h> mGirlGridItemList = new ArrayList();
    private List<i> mGirlKeysItemList = new ArrayList();
    private String mGirlTitleExt;
    private String mPublishTitleExt;

    class a extends BaseAdapter {
        final /* synthetic */ AdvTabCardBoyGirl a;
        private List<h> b = new ArrayList();

        public a(AdvTabCardBoyGirl advTabCardBoyGirl, List<h> list) {
            this.a = advTabCardBoyGirl;
            for (h add : list) {
                this.b.add(add);
            }
        }

        public int getCount() {
            int size = this.b.size() % 3;
            if (size > 0) {
                return (this.b.size() + 3) - size;
            }
            return this.b.size();
        }

        public Object getItem(int i) {
            if (i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_advtab_otherline_item, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            h hVar = (h) getItem(i);
            if (hVar == null) {
                bVar.a("");
                bVar.b("");
            } else {
                bVar.a(hVar.c());
                bVar.b(hVar.d());
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
        final /* synthetic */ AdvTabCardBoyGirl a;
        private TextView b;
        private TextView c;
        private ImageView d;

        public b(AdvTabCardBoyGirl advTabCardBoyGirl, View view) {
            this.a = advTabCardBoyGirl;
            this.b = (TextView) view.findViewById(R.id.text_up);
            this.c = (TextView) view.findViewById(R.id.text_down);
            this.d = (ImageView) view.findViewById(R.id.hot);
        }

        public void a(String str) {
            this.b.setText(str);
        }

        public void b(String str) {
            this.c.setText(str);
        }

        public void a(boolean z) {
            if (z) {
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(8);
            }
        }
    }

    public AdvTabCardBoyGirl(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
        c.c("stack", "AdvTabCardBoyGirl ");
        this.mContext = ReaderApplication.getApplicationImp();
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_advtab_boygirl;
    }

    public void attachView() {
        View a;
        View view;
        switch (this.mSex) {
            case 2:
                View a2 = ap.a(getRootView(), R.id.card_one);
                a = ap.a(getRootView(), R.id.card_two);
                view = a2;
                break;
            default:
                a = ap.a(getRootView(), R.id.card_one);
                view = ap.a(getRootView(), R.id.card_two);
                break;
        }
        ((CardTitle) ap.a(a, R.id.title_layout)).setCardTitle(19, "男生分类", this.mBoyTitleExt, null);
        GridView gridView = (GridView) ap.a(a, R.id.otherGridView_2);
        gridView.setAdapter(new a(this, this.mBoyGridItemList));
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCardBoyGirl a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mBoyGridItemList.size()) {
                    h hVar = (h) this.a.mBoyGridItemList.get(i);
                    if (hVar.a() != null) {
                        hVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
        ((CardTitle) ap.a(view, R.id.title_layout)).setCardTitle(20, "女生分类", this.mGirlTitleExt, null);
        gridView = (GridView) ap.a(view, R.id.otherGridView_2);
        gridView.setAdapter(new a(this, this.mGirlGridItemList));
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCardBoyGirl a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mGirlGridItemList.size()) {
                    h hVar = (h) this.a.mGirlGridItemList.get(i);
                    if (hVar.a() != null) {
                        hVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
    }

    public int getSex() {
        return 0;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("boy");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("girl");
        this.mBoyKeysItemList.clear();
        this.mBoyGridItemList.clear();
        if (optJSONObject != null) {
            parseCardData(optJSONObject, this.mBoyKeysItemList, this.mBoyGridItemList);
            this.mBoyTitleExt = optJSONObject.optString("ext");
        }
        this.mGirlKeysItemList.clear();
        this.mGirlGridItemList.clear();
        if (optJSONObject2 != null) {
            parseCardData(optJSONObject2, this.mGirlKeysItemList, this.mGirlGridItemList);
            this.mGirlTitleExt = optJSONObject2.optString("ext");
        }
        return true;
    }

    private void parseCardData(JSONObject jSONObject, List<i> list, List<h> list2) throws JSONException {
        int i = 0;
        JSONArray jSONArray = jSONObject.getJSONArray("keys");
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                i iVar = new i();
                iVar.parseData(jSONArray.getJSONObject(i2));
                list.add(iVar);
            }
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray("categorys");
        if (jSONArray2 != null) {
            while (i < jSONArray2.length()) {
                h hVar = new h();
                hVar.parseData(jSONArray2.getJSONObject(i));
                list2.add(hVar);
                i++;
            }
        }
    }

    public void refresh() {
        if (getRootView() != null) {
            attachView(getRootView());
        }
    }
}
