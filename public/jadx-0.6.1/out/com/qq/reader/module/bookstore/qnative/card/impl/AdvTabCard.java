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
import com.qq.reader.module.bookstore.qnative.item.i;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvTabCard extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private static final int TYPE_BOY = 1;
    private static final int TYPE_GIRL = 2;
    private static final int TYPE_PUBLISH = 3;
    private List<h> mBoyGridItemList = new ArrayList();
    private List<i> mBoyKeysItemList = new ArrayList();
    private String mBoyTitleExt;
    private Context mContext = ReaderApplication.getApplicationImp();
    private List<h> mGirlGridItemList = new ArrayList();
    private List<i> mGirlKeysItemList = new ArrayList();
    private String mGirlTitleExt;
    private List<h> mPublishGridItemList = new ArrayList();
    private List<i> mPublishKeysItemList = new ArrayList();
    private String mPublishTitleExt;

    class a extends BaseAdapter {
        final /* synthetic */ AdvTabCard a;
        private List<i> b = new ArrayList();
        private int c;

        public a(AdvTabCard advTabCard, List<i> list, int i) {
            this.a = advTabCard;
            for (i add : list) {
                this.b.add(add);
            }
            this.c = i;
        }

        public int getCount() {
            if (this.b.size() > 4) {
                return 4;
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
                view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_advtab_firstline_item, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            bVar.a(this.c, ((i) getItem(i)).b());
            int i2 = 0;
            switch (i) {
                case 0:
                    i2 = R.drawable.localstore_category_1;
                    break;
                case 1:
                    i2 = R.drawable.localstore_category_2;
                    break;
                case 2:
                    i2 = R.drawable.localstore_category_3;
                    break;
                case 3:
                    i2 = R.drawable.localstore_category_4;
                    break;
            }
            bVar.a(this.c, i2);
            return view;
        }
    }

    class b {
        final /* synthetic */ AdvTabCard a;
        private ImageView b;
        private TextView c;

        public b(AdvTabCard advTabCard, View view) {
            this.a = advTabCard;
            this.b = (ImageView) view.findViewById(R.id.advcard_category_img);
            this.c = (TextView) view.findViewById(R.id.advcard_category_text);
        }

        public void a(int i, int i2) {
            switch (i) {
                case 1:
                    this.b.setBackgroundResource(R.drawable.category_boy);
                    break;
                case 2:
                    this.b.setBackgroundResource(R.drawable.category_girl);
                    break;
                case 3:
                    this.b.setBackgroundResource(R.drawable.category_publish);
                    break;
            }
            this.b.setImageResource(i2);
        }

        public void a(int i, String str) {
            int i2 = 0;
            switch (i) {
                case 1:
                    i2 = this.a.mContext.getResources().getColor(R.color.localstore_category_boy_textcolor);
                    break;
                case 2:
                    i2 = this.a.mContext.getResources().getColor(R.color.localstore_category_girl_textcolor);
                    break;
                case 3:
                    i2 = this.a.mContext.getResources().getColor(R.color.localstore_category_publish_textcolor);
                    break;
            }
            this.c.setTextColor(i2);
            this.c.setText(str);
        }
    }

    class c extends BaseAdapter {
        final /* synthetic */ AdvTabCard a;
        private List<h> b = new ArrayList();

        public c(AdvTabCard advTabCard, List<h> list) {
            this.a = advTabCard;
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
            d dVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_advtab_otherline_item, null);
                d dVar2 = new d(this.a, view);
                view.setTag(dVar2);
                dVar = dVar2;
            } else {
                dVar = (d) view.getTag();
            }
            h hVar = (h) getItem(i);
            if (hVar == null) {
                dVar.a("");
                dVar.b("");
            } else {
                dVar.a(hVar.c());
                dVar.b(hVar.d());
                if (hVar.b()) {
                    dVar.a(true);
                } else {
                    dVar.a(false);
                }
            }
            return view;
        }
    }

    class d {
        final /* synthetic */ AdvTabCard a;
        private TextView b;
        private TextView c;
        private ImageView d;

        public d(AdvTabCard advTabCard, View view) {
            this.a = advTabCard;
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

    public AdvTabCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_advtab_new;
    }

    public void attachView() {
        View a;
        View a2;
        View view;
        switch (this.mSex) {
            case 1:
                a = ap.a(getRootView(), R.id.card_one);
                a2 = ap.a(getRootView(), R.id.card_two);
                view = a;
                a = ap.a(getRootView(), R.id.card_three);
                break;
            case 2:
                View a3 = ap.a(getRootView(), R.id.card_one);
                a2 = ap.a(getRootView(), R.id.card_two);
                view = ap.a(getRootView(), R.id.card_three);
                a = a3;
                break;
            case 3:
                View a4 = ap.a(getRootView(), R.id.card_one);
                a2 = a4;
                view = ap.a(getRootView(), R.id.card_two);
                a = ap.a(getRootView(), R.id.card_three);
                break;
            default:
                a2 = null;
                a = null;
                view = null;
                break;
        }
        GridView gridView = (GridView) ap.a(view, R.id.otherGridView_1);
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mBoyKeysItemList.size()) {
                    i iVar = (i) this.a.mBoyKeysItemList.get(i);
                    if (iVar.a() != null) {
                        iVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
        GridView gridView2 = (GridView) ap.a(view, R.id.otherGridView_2);
        gridView2.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

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
        gridView.setAdapter(new a(this, this.mBoyKeysItemList, 1));
        gridView2.setAdapter(new c(this, this.mBoyGridItemList));
        ((CardTitle) ap.a(view, R.id.title_layout)).setCardTitle(0, "男生分类", this.mBoyTitleExt, null);
        gridView = (GridView) ap.a(a, R.id.otherGridView_1);
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mGirlKeysItemList.size()) {
                    i iVar = (i) this.a.mGirlKeysItemList.get(i);
                    if (iVar.a() != null) {
                        iVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
        gridView2 = (GridView) ap.a(a, R.id.otherGridView_2);
        gridView2.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

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
        CardTitle cardTitle = (CardTitle) ap.a(a, R.id.title_layout);
        gridView.setAdapter(new a(this, this.mGirlKeysItemList, 2));
        gridView2.setAdapter(new c(this, this.mGirlGridItemList));
        cardTitle.setCardTitle(0, "女生分类", this.mGirlTitleExt, null);
        gridView = (GridView) ap.a(a2, R.id.otherGridView_1);
        gridView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mPublishKeysItemList.size()) {
                    i iVar = (i) this.a.mPublishKeysItemList.get(i);
                    if (iVar.a() != null) {
                        iVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
        gridView2 = (GridView) ap.a(a2, R.id.otherGridView_2);
        gridView2.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AdvTabCard a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mPublishGridItemList.size()) {
                    h hVar = (h) this.a.mPublishGridItemList.get(i);
                    if (hVar.a() != null) {
                        hVar.a().a(this.a.getEvnetListener());
                    }
                }
            }
        });
        gridView.setAdapter(new a(this, this.mPublishKeysItemList, 3));
        gridView2.setAdapter(new c(this, this.mPublishGridItemList));
        ((CardTitle) ap.a(a2, R.id.title_layout)).setCardTitle(0, "出版分类", this.mPublishTitleExt, null);
    }

    public int getSex() {
        return 0;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("boy");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("girl");
        JSONObject optJSONObject3 = jSONObject.optJSONObject("publish");
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
        this.mPublishKeysItemList.clear();
        this.mPublishGridItemList.clear();
        if (optJSONObject3 != null) {
            parseCardData(optJSONObject3, this.mPublishKeysItemList, this.mPublishGridItemList);
            this.mPublishTitleExt = optJSONObject3.optString("ext");
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
