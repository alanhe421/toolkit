package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.j;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class DetailChapterCard extends a {
    public DetailChapterCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            j jVar = (j) getItemList().get(0);
            int d = jVar.d();
            View a = ap.a(getRootView(), R.id.chapter_0_root);
            if (this.mMoreAction != null && d == 0) {
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DetailChapterCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        try {
                            bundle.putString("KEY_ACTION", "detail_2_chapter");
                            this.a.getEvnetListener().doFunction(bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            TextView textView = (TextView) ap.a(getRootView(), R.id.chapter_0_title);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.chapter_0_bookfrom);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.chapter_0_updatetime_right_arrow);
            TextView textView3 = (TextView) a.findViewById(R.id.chapter_0_content);
            textView3.setText(jVar.a());
            if (jVar.d() == 1 || jVar.d() == 2) {
                getRootView().setBackgroundResource(R.drawable.common_backgraound_white);
            } else {
                getRootView().setBackgroundResource(R.drawable.concept_card_bg_selector);
            }
            TextView textView4 = (TextView) a.findViewById(R.id.chapter_0_updatetime);
            if (d == 1) {
                textView3.setVisibility(8);
                textView.setVisibility(8);
                imageView.setVisibility(8);
                textView2.setVisibility(0);
                textView2.setText(jVar.a());
            } else if (d == 2) {
                imageView.setVisibility(4);
                textView3.setText("暂不支持");
            }
            CharSequence b = jVar.b();
            if (b != null && !jVar.c()) {
                textView4.setText(b);
            }
        }
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_chapter_0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        s jVar = new j();
        jVar.parseData(jSONObject);
        getItemList().clear();
        addItem(jVar);
        return true;
    }
}
