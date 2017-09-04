package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.voucher.NativePageVoucherDetailFragment;
import com.qq.reader.module.bookstore.qnative.card.impl.VoucherDetailItemCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.ConfigurableEmptyCard;
import com.qq.reader.module.question.card.ConfigurableEmptyCard.a;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeLocalVoucherDetailPage */
public class o extends af {
    private String a;
    private int b;
    private String c;

    public o(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("voucher");
            this.a = optJSONObject.optString("comment");
            this.b = optJSONObject.optInt("totalVoucher");
            this.c = optJSONObject.optString("roleUrl");
            JSONArray optJSONArray = optJSONObject.optJSONArray("voucherVoList");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    VoucherDetailItemCard voucherDetailItemCard = new VoucherDetailItemCard(this, VoucherDetailItemCard.class.getSimpleName());
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    voucherDetailItemCard.setEventListener(l());
                    voucherDetailItemCard.fillData(optJSONObject2);
                    this.k.add(voucherDetailItemCard);
                    this.l.put(voucherDetailItemCard.getCardId(), voucherDetailItemCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((o) bVar).a;
        this.b = ((o) bVar).b;
        this.c = ((o) bVar).c;
        if (this.k.size() == 0) {
            ConfigurableEmptyCard configurableEmptyCard = new ConfigurableEmptyCard(this, new a(this) {
                final /* synthetic */ o a;

                {
                    this.a = r1;
                }

                public void a(EmptyView emptyView) {
                    emptyView.b(ReaderApplication.getApplicationImp().getResources().getString(R.string.empty_page_no_voucher_title)).b(R.drawable.empty03).a(ReaderApplication.getApplicationImp().getResources().getString(R.string.empty_page_no_voucher_tips)).a(true).a(1);
                }
            }, l());
            this.l.put(configurableEmptyCard.getType(), configurableEmptyCard);
            this.k.add(configurableEmptyCard);
        }
    }

    public String a(Bundle bundle) {
        return e.bN;
    }

    public String x() {
        return this.a;
    }

    public String y() {
        return this.c;
    }

    public int z() {
        return this.b;
    }

    public Class c() {
        return NativePageVoucherDetailFragment.class;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }
}
