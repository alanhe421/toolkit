package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.ad;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.CornerProgressBar;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class EndPageRankCard extends a {
    public EndPageRankCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_endpage_rank;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        s adVar = new ad();
        adVar.parseData(jSONObject);
        getItemList().clear();
        addItem(adVar);
        return true;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.localstore_endpageRankCard_layout);
        if (getItemList().size() != 0) {
            ad.a aVar;
            TextView textView;
            ad adVar = (ad) getItemList().get(0);
            LinearLayout linearLayout2 = (LinearLayout) ap.a(getRootView(), R.id.rank_reward_layout);
            if (adVar.c()) {
                aVar = adVar.c;
                textView = (TextView) ap.a(getRootView(), R.id.rank_reward_gap);
                if (aVar.e() != 1) {
                    textView.setText("距前一名还差" + adVar.b(adVar.c) + "书币");
                } else {
                    textView.setText("再有" + adVar.b(adVar.c) + "书币即被超越");
                }
                ((TextView) ap.a(getRootView(), R.id.rank_reward_num)).setText("" + aVar.e() + "名");
                ((ImageView) ap.a(getRootView(), R.id.rank_reward_img)).setImageResource(adVar.a(aVar));
                ((CornerProgressBar) ap.a(getRootView(), R.id.rank_reward_progress)).setProgress(adVar.c(aVar));
            } else {
                linearLayout2.setVisibility(8);
            }
            linearLayout2 = (LinearLayout) ap.a(getRootView(), R.id.rank_recommend_layout);
            if (adVar.b()) {
                aVar = adVar.d;
                textView = (TextView) ap.a(getRootView(), R.id.rank_recommend_gap);
                if (aVar.e() != 1) {
                    textView.setText("距前一名还差" + adVar.b(aVar) + "推荐票");
                } else {
                    textView.setText("再有" + adVar.b(aVar) + "推荐票即被超越");
                }
                ((TextView) ap.a(getRootView(), R.id.rank_recommend_num)).setText("" + aVar.e() + "名");
                ((ImageView) ap.a(getRootView(), R.id.rank_recommend_img)).setImageResource(adVar.a(aVar));
                ((CornerProgressBar) ap.a(getRootView(), R.id.rank_recommend_progress)).setProgress(adVar.c(aVar));
            } else {
                linearLayout2.setVisibility(8);
            }
            linearLayout2 = (LinearLayout) ap.a(getRootView(), R.id.rank_month_ticket_layout);
            if (adVar.a()) {
                aVar = adVar.e;
                textView = (TextView) ap.a(getRootView(), R.id.rank_month_ticket_gap);
                if (aVar.e() != 1) {
                    textView.setText("距离前一名还差" + adVar.b(aVar) + "月票");
                } else {
                    textView.setText("再有" + adVar.b(aVar) + "月票即被超越");
                }
                ((TextView) ap.a(getRootView(), R.id.rank_month_ticket_num)).setText("" + aVar.e() + "名");
                ((ImageView) ap.a(getRootView(), R.id.rank_month_ticket_img)).setImageResource(adVar.a(aVar));
                ((CornerProgressBar) ap.a(getRootView(), R.id.rank_month_ticket_progress)).setProgress(adVar.c(aVar));
            } else {
                linearLayout2.setVisibility(8);
            }
            if (adVar.a.size() == 0) {
                ((LinearLayout) ap.a(getRootView(), R.id.rank_fans_list)).setVisibility(8);
                ((RelativeLayout) ap.a(getRootView(), R.id.rank_fans_title_layout)).setVisibility(8);
                return;
            }
            TextView textView2;
            ad.b bVar = (ad.b) adVar.a.get(0);
            if (bVar != null) {
                ((RelativeLayout) ap.a(getRootView(), R.id.rank_fans_person1)).setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(bVar.a, (ImageView) ap.a(getRootView(), R.id.rank_fans_person1_head_img), com.qq.reader.common.imageloader.a.a().q());
                textView2 = (TextView) ap.a(getRootView(), R.id.rank_fans_person1_head_level);
                textView2.setText(bVar.b);
                textView2.setBackgroundResource(ad.a(bVar));
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person1_name)).setText(bVar.d);
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person1_num)).setText(String.valueOf(bVar.c));
            }
            bVar = (ad.b) adVar.a.get(1);
            if (bVar != null) {
                ((RelativeLayout) ap.a(getRootView(), R.id.rank_fans_person2)).setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(bVar.a, (ImageView) ap.a(getRootView(), R.id.rank_fans_person2_head_img), com.qq.reader.common.imageloader.a.a().q());
                textView2 = (TextView) ap.a(getRootView(), R.id.rank_fans_person2_head_level);
                textView2.setText(bVar.b);
                textView2.setBackgroundResource(ad.a(bVar));
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person2_name)).setText(bVar.d);
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person2_num)).setText(String.valueOf(bVar.c));
            }
            bVar = (ad.b) adVar.a.get(2);
            if (bVar != null) {
                ((RelativeLayout) ap.a(getRootView(), R.id.rank_fans_person3)).setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(bVar.a, (ImageView) ap.a(getRootView(), R.id.rank_fans_person3_head_img), com.qq.reader.common.imageloader.a.a().q());
                textView2 = (TextView) ap.a(getRootView(), R.id.rank_fans_person3_head_level);
                textView2.setText(bVar.b);
                textView2.setBackgroundResource(ad.a(bVar));
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person3_name)).setText(bVar.d);
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person3_num)).setText(String.valueOf(bVar.c));
            }
            bVar = adVar.b;
            if (!(bVar == null || adVar.d())) {
                ((RelativeLayout) ap.a(getRootView(), R.id.rank_fans_person4)).setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(bVar.a, (ImageView) ap.a(getRootView(), R.id.rank_fans_person4_head_img), com.qq.reader.common.imageloader.a.a().q());
                TextView textView3 = (TextView) ap.a(getRootView(), R.id.rank_fans_person4_head_level);
                textView3.setText(bVar.b);
                textView3.setBackgroundResource(ad.a(bVar));
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person4_name)).setText(bVar.d);
                ((TextView) ap.a(getRootView(), R.id.rank_fans_person4_num)).setText(String.valueOf(bVar.c));
            }
            ((TextView) ap.a(getRootView(), R.id.rank_fans_title_more)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ EndPageRankCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_ACTION", "detail_2_reward");
                    bundle.putInt("PARA_TYPE_REWARD_TAB_INDEX", 3);
                    bundle.putString("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", "");
                    if (this.a.getEvnetListener() != null) {
                        this.a.getEvnetListener().doFunction(bundle);
                    }
                    i.a("event_B21", null, ReaderApplication.getApplicationImp());
                }
            });
            return;
        }
        linearLayout.setVisibility(8);
    }
}
