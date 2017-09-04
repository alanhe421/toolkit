package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class StackTabCard extends a {
    static final String tag = "stackcard";
    private int actionId;
    private String[] bids = null;
    private int bookCount;
    private String categoryName;
    private String img;
    private String level3categoryName;
    private c mAction;
    private boolean recommend;

    public StackTabCard(b bVar, String str) {
        super(bVar, str);
        com.qq.reader.common.monitor.debug.c.a(tag, "new StackTabCard ");
    }

    public int getResLayoutId() {
        com.qq.reader.common.monitor.debug.c.a(tag, "getReslayoutId ");
        return R.layout.localbookstore_stacklist_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.categoryName = jSONObject.optString("categoryName");
        this.level3categoryName = jSONObject.optString("level3categoryName");
        this.bookCount = jSONObject.optInt("bookCount");
        this.img = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        this.actionId = jSONObject.optInt("actionId");
        this.recommend = jSONObject.optBoolean("recommend");
        Object optString = jSONObject.optString("bids");
        if (!TextUtils.isEmpty(optString)) {
            this.bids = optString.split(",");
        }
        return true;
    }

    public void attachView() {
        com.qq.reader.common.monitor.debug.c.a(tag, "attachView ");
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        if (a != null) {
            if (this.mLastCardName.equals("StackTabLineCard") || this.mLastCardName.equals("StackTabRecommendCard")) {
                a.setVisibility(8);
            } else {
                a.setVisibility(0);
            }
        }
        TextView textView = (TextView) ap.a(getRootView(), R.id.count);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.categoryName);
        textView.setText(String.format(ReaderApplication.getApplicationImp().getString(R.string.bookstore_book_count), new Object[]{Integer.valueOf(this.bookCount)}));
        if (this.bids != null) {
            ImageView[] imageViewArr = new ImageView[]{(ImageView) ap.a(getRootView(), R.id.classify_cover), (ImageView) ap.a(getRootView(), R.id.classify_cover_left), (ImageView) ap.a(getRootView(), R.id.classify_cover_right)};
            int i = 0;
            while (i < 3 && i < this.bids.length) {
                com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(Long.valueOf(this.bids[i]).longValue()), imageViewArr[i], com.qq.reader.common.imageloader.a.a().n());
                i++;
            }
        }
        getRootView().setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ StackTabCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                i.a("event_C12", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), null, String.valueOf(this.a.actionId), null);
            }
        });
    }
}
