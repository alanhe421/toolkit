package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.k;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class CollectCard_0 extends a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";

    public CollectCard_0(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localcollectcard_0_new;
    }

    public void attachView() {
        View a = ap.a(getRootView(), R.id.first_book);
        View a2 = ap.a(getRootView(), R.id.second_book);
        ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
        attacthBook(a, 0);
        attacthBook(a2, 1);
        showStatics();
    }

    private void attacthBook(View view, int i) {
        if (getItemList().size() > i) {
            ImageView imageView = (ImageView) view.findViewById(R.id.book_cover);
            TextView textView = (TextView) view.findViewById(R.id.book_author);
            TextView textView2 = (TextView) view.findViewById(R.id.book_jzcount);
            TextView textView3 = (TextView) view.findViewById(R.id.book_info);
            final k kVar = (k) getItemList().get(i);
            ((TextView) view.findViewById(R.id.book_name)).setText(kVar.d());
            textView.setText(kVar.e());
            String a = j.a((long) kVar.g());
            try {
                if (kVar.g() > 0) {
                    textView2.setText("集赞" + a);
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
            CharSequence f = kVar.f();
            if (!(TextUtils.isEmpty(kVar.a()) || kVar.a().equals("null"))) {
                f = kVar.a();
            }
            f.d("collect0", " info " + f);
            textView3.setText(f);
            setImage(imageView, kVar.b(), null);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CollectCard_0 b;

                public void onClick(View view) {
                    kVar.a(this.b.getEvnetListener());
                    this.b.clickStatics();
                }
            });
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        getItemList().clear();
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s kVar = new k();
            kVar.parseData(jSONObject2);
            addItem(kVar);
            i++;
        }
        return true;
    }

    public boolean selfPrepareData() {
        return false;
    }

    public boolean isNeedCacheOnDisk() {
        return false;
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
