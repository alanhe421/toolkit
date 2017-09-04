package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.RoundImageView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class StackTabRecommendCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private static final int[] AUDIO_ITEM_BG_RES = new int[]{R.color.stack_recommend_bg_2, R.color.stack_recommend_bg_5, R.color.stack_recommend_bg_1, R.color.stack_recommend_bg_3};
    private static final int[] BOY_ITEM_BG_RES = new int[]{R.color.stack_recommend_bg_1, R.color.stack_recommend_bg_5, R.color.stack_recommend_bg_3, R.color.stack_recommend_bg_2};
    private static final int[] COMIC_ITEM_BG_RES = new int[]{R.color.stack_recommend_bg_5, R.color.stack_recommend_bg_2, R.color.stack_recommend_bg_8, R.color.stack_recommend_bg_4};
    private static final int[] GIRL_ITEM_BG_RES = new int[]{R.color.stack_recommend_bg_1, R.color.stack_recommend_bg_6, R.color.stack_recommend_bg_2, R.color.stack_recommend_bg_4};
    private static final int[] PUBLIC_ITEM_BG_RES = new int[]{R.color.stack_recommend_bg_3, R.color.stack_recommend_bg_1, R.color.stack_recommend_bg_8, R.color.stack_recommend_bg_7};
    private int[] itemId = new int[]{R.id.recommend_item1, R.id.recommend_item2, R.id.recommend_item3, R.id.recommend_item4};
    private List<a> mDataList;

    class a {
        final /* synthetic */ StackTabRecommendCard a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;

        a(StackTabRecommendCard stackTabRecommendCard) {
            this.a = stackTabRecommendCard;
        }

        public void a(JSONObject jSONObject) throws Exception {
            this.b = jSONObject.optString("type");
            this.c = jSONObject.optString("image");
            this.d = jSONObject.optString("title");
            this.e = jSONObject.optString("intro");
            this.f = jSONObject.optString("qurl");
        }
    }

    public StackTabRecommendCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookstore_stackrecommend_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("recmd");
        if (optJSONArray != null) {
            if (this.mDataList == null) {
                this.mDataList = new ArrayList();
            } else {
                this.mDataList.clear();
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                a aVar = new a(this);
                aVar.a(jSONObject2);
                this.mDataList.add(aVar);
            }
        }
        return true;
    }

    public void attachView() {
        for (int i = 0; i < this.mDataList.size(); i++) {
            View a = ap.a(getRootView(), this.itemId[i]);
            a.setVisibility(0);
            setItemData(a, i);
            if (i > 1) {
                ap.a(getRootView(), R.id.item_layout1).setVisibility(0);
            }
        }
        i.a("event_C59", null, ReaderApplication.getApplicationImp());
    }

    public void setItemData(View view, final int i) {
        ImageView imageView = (RoundImageView) view.findViewById(R.id.img);
        TextView textView = (TextView) view.findViewById(R.id.title);
        imageView.setRadius((float) ao.a(4.0f));
        textView.setText(((a) this.mDataList.get(i)).d);
        if (!TextUtils.isEmpty(((a) this.mDataList.get(i)).c)) {
            c.a(getEvnetListener().getFromActivity()).a(((a) this.mDataList.get(i)).c, imageView, "BookLibCategory_comic".equals(getBindPage().j().getString("KEY_JUMP_PAGENAME")) ? R.drawable.book_stack_recommend_comic_default_icon : R.drawable.book_stack_recommend_default_icon);
        }
        imageView.setBackgroundResource(getBGColorByPageName(getBindPage().j().getString("KEY_JUMP_PAGENAME"))[i]);
        view.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ StackTabRecommendCard b;

            public void a(View view) {
                try {
                    com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), ((a) this.b.mDataList.get(i)).f);
                    Map hashMap;
                    if ("BookLibCategory_comic".equals(this.b.getBindPage().j().getString("KEY_JUMP_PAGENAME"))) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, ((a) this.b.mDataList.get(i)).b);
                        i.a("event_F299", hashMap, ReaderApplication.getApplicationImp());
                    } else if ("BookLibCategory_audio".equals(this.b.getBindPage().j().getString("KEY_JUMP_PAGENAME"))) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, ((a) this.b.mDataList.get(i)).b);
                        i.a("event_B285", hashMap, ReaderApplication.getApplicationImp());
                        i.a("event_B284", null, view.getContext());
                    } else {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, ((a) this.b.mDataList.get(i)).b);
                        i.a("event_C61", hashMap, ReaderApplication.getApplicationImp());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int[] getBGColorByPageName(String str) {
        if ("BookLibCategory_boy".equals(str)) {
            return BOY_ITEM_BG_RES;
        }
        if ("BookLibCategory_comic".equals(str)) {
            return COMIC_ITEM_BG_RES;
        }
        if ("BookLibCategory_girl".equals(str)) {
            return GIRL_ITEM_BG_RES;
        }
        if ("BookLibCategory_publish".equals(str)) {
            return PUBLIC_ITEM_BG_RES;
        }
        if ("BookLibCategory_audio".equals(str)) {
            return AUDIO_ITEM_BG_RES;
        }
        return COMIC_ITEM_BG_RES;
    }
}
