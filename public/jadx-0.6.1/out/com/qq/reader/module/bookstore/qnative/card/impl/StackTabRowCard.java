package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class StackTabRowCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a[] mDataItems = new a[2];

    private class a {
        final /* synthetic */ StackTabRowCard a;
        private String b;
        private String c;
        private int d;
        private String e;
        private int f;
        private boolean g;
        private long h;

        private a(StackTabRowCard stackTabRowCard) {
            this.a = stackTabRowCard;
        }

        protected boolean a(JSONObject jSONObject) throws Exception {
            this.b = jSONObject.optString("categoryName");
            this.c = jSONObject.optString("level3categoryName");
            this.d = jSONObject.optInt("bookCount", -1);
            this.e = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            this.f = jSONObject.optInt("actionId");
            this.g = jSONObject.optBoolean("recommend");
            this.h = jSONObject.optLong("bid");
            return true;
        }

        public void a(View view) {
            TextView textView = (TextView) ap.a(view, R.id.stack_cell_des);
            ((TextView) ap.a(view, R.id.stack_cell_title)).setText(this.b);
            if (this.d > 0) {
                textView.setText(j.d((long) this.d) + "册");
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            ImageView imageView = (ImageView) ap.a(view, R.id.stack_cell_cover_img);
            if ("comicCategoryList".equalsIgnoreCase(this.a.getType())) {
                c.a(this.a.getEvnetListener().getFromActivity()).a(ao.h(this.h), imageView, com.qq.reader.common.imageloader.a.a().j());
            } else if ("audioCategoryList".equalsIgnoreCase(this.a.getType())) {
                c.a(this.a.getEvnetListener().getFromActivity()).a(ao.a(this.h, true, 90), imageView, com.qq.reader.common.imageloader.a.a().j());
            } else {
                c.a(this.a.getEvnetListener().getFromActivity()).a(ao.g(this.h), imageView, com.qq.reader.common.imageloader.a.a().j());
            }
            view.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    Map hashMap;
                    if ("comicCategoryList".equalsIgnoreCase(this.a.a.getType())) {
                        o.b(this.a.a.getEvnetListener().getFromActivity(), null, String.valueOf(this.a.f), "comicCat", this.a.b, null);
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.f));
                        i.a("event_F301", hashMap, view.getContext());
                    } else if ("audioCategoryList".equalsIgnoreCase(this.a.a.getType())) {
                        o.b(this.a.a.getEvnetListener().getFromActivity(), null, String.valueOf(this.a.f), "audioCat", this.a.b, null);
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.f));
                        i.a("event_B249", hashMap, view.getContext());
                        i.a("event_B284", null, view.getContext());
                    } else {
                        o.a(this.a.a.getEvnetListener().getFromActivity(), null, String.valueOf(this.a.f), null);
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.f));
                        i.a("event_F295", hashMap, view.getContext());
                    }
                }
            });
            Map hashMap;
            if ("comicCategoryList".equalsIgnoreCase(this.a.getType())) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.f));
                i.a("event_F300", hashMap, view.getContext());
            } else if ("audioCategoryList".equalsIgnoreCase(this.a.getType())) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.f));
                i.a("event_B286", hashMap, view.getContext());
            }
        }
    }

    public StackTabRowCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        if ("audioCategoryList".equalsIgnoreCase(this.mType)) {
            return R.layout.stack_tab_row_audio_card;
        }
        return R.layout.stack_tab_row_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        if (jSONArray == null) {
            return false;
        }
        while (i < jSONArray.length()) {
            this.mDataItems[i] = new a();
            this.mDataItems[i].a(jSONArray.getJSONObject(i));
            i++;
        }
        return true;
    }

    public void attachView() {
        View a = ap.a(getRootView(), R.id.stack_tab_cell_1);
        View a2 = ap.a(getRootView(), R.id.stack_tab_cell_2);
        if (this.mDataItems[0] != null) {
            a.setVisibility(0);
            this.mDataItems[0].a(a);
            a.setEnabled(true);
        } else {
            a.setVisibility(4);
            a.setEnabled(false);
        }
        if (this.mDataItems[1] != null) {
            a2.setVisibility(0);
            this.mDataItems[1].a(a2);
            a2.setEnabled(true);
            return;
        }
        a2.setVisibility(4);
        a2.setEnabled(false);
    }
}
