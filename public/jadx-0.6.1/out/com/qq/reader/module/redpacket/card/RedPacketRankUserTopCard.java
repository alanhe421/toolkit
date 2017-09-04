package com.qq.reader.module.redpacket.card;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
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

public class RedPacketRankUserTopCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private static final int[] img_icon_res_ids = new int[]{R.id.img_user_icon_1, R.id.img_user_icon_2, R.id.img_user_icon_3};
    private static final int[] item_layout_res_ids = new int[]{R.id.ll_first, R.id.ll_second, R.id.ll_third};
    private static final int[] tv_amount_res_ids = new int[]{R.id.tv_amount_1, R.id.tv_amount_2, R.id.tv_amount_3};
    private static final int[] tv_amount_unit_res_ids = new int[]{R.id.tv_amount_1_unit, R.id.tv_amount_2_unit, R.id.tv_amount_3_unit};
    private static final int[] tv_name_res_ids = new int[]{R.id.tv_name_1, R.id.tv_name_2, R.id.tv_name_3};
    private boolean isShowBottomDivider;
    private ArrayList<a> mPersons;
    private int vipDrawablePadding;
    private Drawable vipMonthDrawable;
    private Drawable vipYearDrawable;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public String d;
        public boolean e;
        public int f;
        final /* synthetic */ RedPacketRankUserTopCard g;

        private a(RedPacketRankUserTopCard redPacketRankUserTopCard) {
            this.g = redPacketRankUserTopCard;
        }

        public void parseData(JSONObject jSONObject) {
            boolean z = true;
            this.a = jSONObject.optString(MessageKey.MSG_ICON);
            this.b = jSONObject.optString("name");
            this.c = jSONObject.optString("totalMoney");
            this.d = jSONObject.optString("id");
            if (jSONObject.optInt("type") != 1) {
                z = false;
            }
            this.e = z;
            this.f = jSONObject.optInt("vipStatus", 0);
        }
    }

    public RedPacketRankUserTopCard(b bVar, String str) {
        super(bVar, str);
        this.isShowBottomDivider = false;
        this.vipDrawablePadding = 0;
        this.vipMonthDrawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.bookvip_month);
        this.vipMonthDrawable = this.vipMonthDrawable.mutate().getConstantState().newDrawable();
        this.vipMonthDrawable.setBounds(0, 0, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_height));
        this.vipYearDrawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.bookvip_year);
        this.vipYearDrawable = this.vipYearDrawable.mutate().getConstantState().newDrawable();
        this.vipYearDrawable.setBounds(0, 0, ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_height));
        this.vipDrawablePadding = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.red_packet_rank_user_vip_tag_margin_left);
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
        return R.layout.red_packet_rank_user_top_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mPersons = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return false;
        }
        int i = 0;
        while (i < optJSONArray.length() && i < 3) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            a aVar = new a();
            aVar.parseData(optJSONObject);
            this.mPersons.add(aVar);
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
        i.a("event_D211", hashMap, ReaderApplication.getApplicationImp());
        View a = ap.a(getRootView(), R.id.view_bottom_divider);
        if (this.isShowBottomDivider) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
        if (this.mPersons != null && this.mPersons.size() > 0) {
            int i = 0;
            while (i < this.mPersons.size() && i < img_icon_res_ids.length) {
                ImageView imageView = (ImageView) ap.a(getRootView(), img_icon_res_ids[i]);
                TextView textView = (TextView) ap.a(getRootView(), tv_name_res_ids[i]);
                TextView textView2 = (TextView) ap.a(getRootView(), tv_amount_res_ids[i]);
                View a2 = ap.a(getRootView(), tv_amount_unit_res_ids[i]);
                View a3 = ap.a(getRootView(), item_layout_res_ids[i]);
                final a aVar = (a) this.mPersons.get(i);
                if (aVar != null) {
                    textView.setText(aVar.b);
                    textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c101));
                    a2.setVisibility(0);
                    if (TextUtils.isEmpty(aVar.c)) {
                        textView2.setText("0");
                    } else {
                        textView2.setText(aVar.c);
                    }
                    if (aVar.f == 1 && !aVar.e) {
                        textView.setCompoundDrawables(null, null, this.vipMonthDrawable, null);
                        textView.setCompoundDrawablePadding(this.vipDrawablePadding);
                    } else if (aVar.f != 2 || aVar.e) {
                        textView.setCompoundDrawables(null, null, null, null);
                        textView.setCompoundDrawablePadding(0);
                    } else {
                        textView.setCompoundDrawables(null, null, this.vipYearDrawable, null);
                        textView.setCompoundDrawablePadding(this.vipDrawablePadding);
                    }
                    c.a(getEvnetListener().getFromActivity()).a(aVar.a, imageView, com.qq.reader.common.imageloader.a.a().e());
                    a3.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                        final /* synthetic */ RedPacketRankUserTopCard b;

                        public void a(View view) {
                            if (aVar != null) {
                                o.a(this.b.getEvnetListener().getFromActivity(), aVar.e, aVar.d, aVar.a, aVar.b);
                            }
                        }
                    });
                }
                i++;
            }
        }
    }
}
