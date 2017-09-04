package com.qq.reader.module.redpacket.card;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RedPacketRankBookTopCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private static final int[] img_icon_res_ids = new int[]{R.id.img_book_icon_1, R.id.img_book_icon_2, R.id.img_book_icon_3};
    private static final int[] item_layout_res_ids = new int[]{R.id.rl_first, R.id.rl_second, R.id.rl_third};
    private static final int[] tv_amount_res_ids = new int[]{R.id.tv_amount_1, R.id.tv_amount_2, R.id.tv_amount_3};
    private static final int[] tv_amount_unit_res_ids = new int[]{R.id.tv_amount_1_unit, R.id.tv_amount_2_unit, R.id.tv_amount_3_unit};
    private static final int[] tv_name_res_ids = new int[]{R.id.tv_name_1, R.id.tv_name_2, R.id.tv_name_3};
    private boolean isShowBottomDivider = false;
    private ArrayList<a> items;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public long d;
        public String e;
        final /* synthetic */ RedPacketRankBookTopCard f;

        private a(RedPacketRankBookTopCard redPacketRankBookTopCard) {
            this.f = redPacketRankBookTopCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optString(MessageKey.MSG_ICON);
            this.b = jSONObject.optString("title");
            this.c = jSONObject.optString("authorName");
            this.d = jSONObject.optLong("bid");
            this.e = jSONObject.optString("totalMoney");
        }
    }

    public RedPacketRankBookTopCard(b bVar, String str) {
        super(bVar, str);
    }

    private String getStaticsOrigin() {
        b bindPage = getBindPage();
        if (bindPage != null && (bindPage instanceof com.qq.reader.module.redpacket.a.b)) {
            Bundle y = ((com.qq.reader.module.redpacket.a.b) bindPage).y();
            if (y != null) {
                return y.getString("KEY_ACTIONTAG");
            }
        }
        return null;
    }

    public int getResLayoutId() {
        return R.layout.red_packet_rank_book_top_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.items = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return false;
        }
        int i = 0;
        while (i < optJSONArray.length() && i < 3) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            a aVar = new a();
            aVar.parseData(optJSONObject);
            this.items.add(aVar);
            i++;
        }
        if (optJSONArray.length() > 3) {
            this.isShowBottomDivider = true;
        } else {
            this.isShowBottomDivider = false;
        }
        return true;
    }

    public void attachView() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getStaticsOrigin());
        i.a("event_D210", hashMap, ReaderApplication.getApplicationImp());
        View a = ap.a(getRootView(), R.id.view_bottom_divider);
        if (this.isShowBottomDivider) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
        if (this.items != null && this.items.size() > 0) {
            int i = 0;
            while (i < this.items.size() && i < img_icon_res_ids.length) {
                ImageView imageView = (ImageView) ap.a(getRootView(), img_icon_res_ids[i]);
                TextView textView = (TextView) ap.a(getRootView(), tv_name_res_ids[i]);
                TextView textView2 = (TextView) ap.a(getRootView(), tv_amount_res_ids[i]);
                View a2 = ap.a(getRootView(), tv_amount_unit_res_ids[i]);
                View a3 = ap.a(getRootView(), item_layout_res_ids[i]);
                final a aVar = (a) this.items.get(i);
                if (aVar != null) {
                    textView.setText(aVar.b);
                    textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c101));
                    if (TextUtils.isEmpty(aVar.e)) {
                        textView2.setText("0");
                    } else {
                        textView2.setText(aVar.e);
                    }
                    a2.setVisibility(0);
                }
                c.a(getEvnetListener().getFromActivity()).a(ao.g(aVar.d), imageView, com.qq.reader.common.imageloader.a.a().j());
                a3.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ RedPacketRankBookTopCard b;

                    public void a(View view) {
                        if (aVar != null) {
                            o.a(this.b.getEvnetListener().getFromActivity(), String.valueOf(aVar.d), null, null, null);
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, this.b.getStaticsOrigin());
                            i.a("event_D212", hashMap, ReaderApplication.getApplicationImp());
                        }
                    }
                });
                i++;
            }
        }
    }
}
