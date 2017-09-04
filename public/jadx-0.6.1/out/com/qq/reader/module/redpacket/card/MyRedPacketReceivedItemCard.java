package com.qq.reader.module.redpacket.card;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class MyRedPacketReceivedItemCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a item;

    private class a extends s {
        public String a;
        public String b;
        public long c;
        public int d;
        public String e;
        public int f;
        final /* synthetic */ MyRedPacketReceivedItemCard g;

        private a(MyRedPacketReceivedItemCard myRedPacketReceivedItemCard) {
            this.g = myRedPacketReceivedItemCard;
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString("intro");
                this.b = jSONObject.optString("amount");
                this.c = jSONObject.optLong(MessageKey.MSG_DATE);
                this.d = jSONObject.optInt("type");
                this.e = jSONObject.optString("detailUrl");
                this.f = jSONObject.optInt("status");
            }
        }
    }

    public MyRedPacketReceivedItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.my_red_packet_list_item_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.item = new a();
        this.item.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_intro);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_date);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_info);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.tv_amount);
        if (this.item != null) {
            if (!TextUtils.isEmpty(this.item.a)) {
                textView.setText(this.item.a);
            }
            if (!TextUtils.isEmpty(this.item.b)) {
                textView4.setText(this.item.b);
            }
            if (this.item.d == 4 && this.item.f == 1) {
                textView3.setText(R.string.redpacket_processing);
                textView3.setVisibility(0);
            } else {
                textView3.setText("");
                textView3.setVisibility(8);
            }
            textView2.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(this.item.c)));
            Drawable drawable;
            if (this.item.d == 1) {
                drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.my_red_packet_item_tag_month_ticket);
                textView.setCompoundDrawablePadding(ao.a(6.0f));
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            } else if (this.item.d == 2) {
                drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.my_red_packet_item_tag_recommend_ticket);
                textView.setCompoundDrawablePadding(ao.a(6.0f));
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            } else {
                textView.setCompoundDrawablePadding(0);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            getRootView().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MyRedPacketReceivedItemCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.item != null && !TextUtils.isEmpty(this.a.item.e)) {
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), (com.qq.reader.module.redpacket.b.a.c + this.a.item.e) + "&timi=" + d.z(ReaderApplication.getApplicationImp()) + "&skey=" + com.qq.reader.common.login.c.c().a(ReaderApplication.getApplicationImp()), null);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }
    }
}
