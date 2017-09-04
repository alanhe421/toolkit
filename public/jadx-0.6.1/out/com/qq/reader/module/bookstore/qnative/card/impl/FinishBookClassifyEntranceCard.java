package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.search.SearchTabInfo.SearchActionTagLv3InitialDataModel;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONObject;

public class FinishBookClassifyEntranceCard extends a {
    private int finishCount = 0;

    public FinishBookClassifyEntranceCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.finish_book_classify_entrance_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.finishCount = jSONObject.optInt("finishCount", 0);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_count);
        ((TextView) ap.a(getRootView(), R.id.tv_title)).setText(this.mShowTitle);
        textView.setText("共" + this.finishCount + "册");
        ap.a(getRootView(), R.id.ll_content).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FinishBookClassifyEntranceCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    String value = this.a.getValue();
                    ArrayList arrayList = new ArrayList();
                    SearchActionTagLv3InitialDataModel searchActionTagLv3InitialDataModel = new SearchActionTagLv3InitialDataModel();
                    searchActionTagLv3InitialDataModel.selectedSubId = 1;
                    searchActionTagLv3InitialDataModel.selectedItemIds = new int[]{1};
                    searchActionTagLv3InitialDataModel.itemShouldInvisible = true;
                    arrayList.add(searchActionTagLv3InitialDataModel);
                    String access$000 = this.a.mShowTitle;
                    o.a(this.a.getEvnetListener().getFromActivity(), ",1,-1,-1,-1,6", value, access$000, arrayList, null);
                    this.a.clickStatics();
                } catch (Exception e) {
                    c.e("Error", e.getMessage());
                }
            }
        });
        showStatics();
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
