package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.charge.voucher.a.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class VoucherDetailItemCard extends a {
    private c item;

    public VoucherDetailItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.voucher_item_detail_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (c) new Gson().fromJson(jSONObject.toString(), c.class);
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.tv_left_class)).setText(this.item.b());
        ((TextView) ap.a(getRootView(), R.id.voucher_available)).setText(com.qq.reader.common.charge.voucher.b.a(getEvnetListener().getFromActivity(), (int) this.item.e()));
        ((TextView) ap.a(getRootView(), R.id.voucher_limit)).setText(com.qq.reader.common.charge.voucher.b.a(getEvnetListener().getFromActivity(), this.item.a()));
        ((TextView) ap.a(getRootView(), R.id.txt_timeline)).setText(this.item.c() + ReaderApplication.getApplicationImp().getResources().getString(R.string.voucher_use_time_limit));
        ap.a(getRootView(), R.id.btn_use).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VoucherDetailItemCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F162", null, ReaderApplication.getApplicationImp());
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.item.d());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
