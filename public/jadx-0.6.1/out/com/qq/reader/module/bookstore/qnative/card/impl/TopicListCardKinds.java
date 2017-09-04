package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.a.h;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.d;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class TopicListCardKinds extends BaseListCard {

    public class a extends s {
        public String a;
        public String b;
        public String c;
        public d d;
        final /* synthetic */ TopicListCardKinds e;
        private ArrayList<String> f = new ArrayList();
        private ArrayList<s> g = new ArrayList();
        private int[] h = new int[]{R.id.concept_img_0, R.id.concept_img_1, R.id.concept_img_2};
        private ImageView[] i = new ImageView[3];
        private int[] j = new int[]{R.id.img_top1, R.id.img_top2, R.id.img_top3, R.id.img_top4};
        private int[] k = new int[]{R.id.img_top1_marker, R.id.img_top2_marker, R.id.img_top3_marker};

        public a(TopicListCardKinds topicListCardKinds, String str) {
            this.e = topicListCardKinds;
            this.c = str;
        }

        public int a() {
            if ("pic1".equals(this.c)) {
                return 0;
            }
            if ("pic2".equals(this.c)) {
                return 1;
            }
            if ("pic3".equals(this.c)) {
                return 2;
            }
            if ("booklist".equals(this.c)) {
                return 3;
            }
            return 0;
        }

        public int b() {
            switch (a()) {
                case 0:
                    return R.layout.listcard_topic_piclist1_layout;
                case 1:
                    return R.layout.listcard_topic_piclist2_layout;
                case 2:
                    return R.layout.listcard_topic_piclist3_layout;
                case 3:
                    return R.layout.concept_hot_rank_layout;
                default:
                    return 0;
            }
        }

        public void parseData(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("info");
            if (optJSONObject != null) {
                this.a = optJSONObject.optString("title");
                this.b = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("cmd");
            if (optJSONObject2 != null) {
                this.d = new d(optJSONObject2.optString("cmd"), optJSONObject2.optString("cmdvalue"));
                this.d.a(optJSONObject.optJSONObject(s.STATPARAM_KEY));
            }
            if (this.c == null) {
                return;
            }
            if (this.c.equals("pic1") || this.c.equals("pic2") || this.c.equals("pic3")) {
                a(optJSONObject);
            } else if (this.c.equals("booklist")) {
                b(optJSONObject);
            }
        }

        public void a(View view) {
            if ("booklist".equals(this.c)) {
                b(view);
            } else {
                c(view);
            }
        }

        private void b(View view) {
            a(view, false);
            ((TextView) ap.a(view, R.id.concept_title)).setText(this.a);
            ((TextView) ap.a(view, R.id.concept_content)).setText(this.b);
            if (this.g != null && this.g.size() > 0 && this.g.size() <= this.j.length) {
                for (int i = 0; i < this.g.size(); i++) {
                    ImageView imageView = (ImageView) ap.a(view, this.j[i]);
                    c.a(this.e.getEvnetListener().getFromActivity()).a(ao.g(((g) this.g.get(i)).m()), imageView, com.qq.reader.common.imageloader.a.a().j());
                }
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.qq.reader.module.feed.activity.a.a(this.a.d, this.a.e.getEvnetListener());
                }
            });
        }

        private void a(View view, boolean z) {
            for (int a : this.k) {
                ImageView imageView = (ImageView) ap.a(view, a);
                if (z) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
            }
        }

        private void c(View view) {
            d(view);
            ((TextView) ap.a(view, R.id.concept_title)).setText(this.a);
            ((TextView) ap.a(view, R.id.concept_content)).setText(this.b);
            if (this.f != null) {
                int i = 0;
                while (i < this.f.size() && i < this.i.length) {
                    if (this.i[i] != null) {
                        c.a(this.e.getEvnetListener().getFromActivity()).a((String) this.f.get(i), this.i[i], com.qq.reader.common.imageloader.a.a().j());
                    }
                    i++;
                }
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.qq.reader.module.feed.activity.a.a(this.a.d, this.a.e.getEvnetListener());
                }
            });
        }

        private void d(View view) {
            for (int i = 0; i < this.i.length; i++) {
                this.i[i] = (ImageView) ap.a(view, this.h[i]);
            }
        }

        private void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    JSONArray optJSONArray = jSONObject.optJSONArray(SocialConstants.PARAM_IMAGE);
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            CharSequence optString = optJSONArray.getJSONObject(i).optString(SocialConstants.PARAM_URL);
                            if (!TextUtils.isEmpty(optString)) {
                                this.f.add(optString);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void b(JSONObject jSONObject) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("books");
                int i = 0;
                while (optJSONArray != null && i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    g gVar = new g();
                    gVar.a(optJSONObject.optLong("bid", 0));
                    gVar.a(optJSONObject.optString("title"));
                    this.g.add(gVar);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TopicListCardKinds(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookcardlistlayout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("infos");
        if (optJSONArray == null) {
            return false;
        }
        getItemList().clear();
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            String typeByTopicStyle = getTypeByTopicStyle(jSONObject2.optInt("style"));
            if (typeByTopicStyle != null) {
                s aVar = new a(this, typeByTopicStyle);
                aVar.parseData(jSONObject2);
                addItem(aVar);
            }
            i++;
        }
        return true;
    }

    public void attachView(View view) {
        try {
            ListView listView = (ListView) view;
            listView.setDivider(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.feed_listview_divider));
            listView.setDividerHeight((int) TypedValue.applyDimension(1, 8.0f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics()));
            this.mAdapter = new h(ReaderApplication.getApplicationImp(), getItemList());
            ((h) this.mAdapter).a(getEvnetListener());
            listView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTypeByTopicStyle(int i) {
        switch (i) {
            case 2:
                return "pic1";
            case 3:
                return "pic2";
            case 4:
                return "pic3";
            case 5:
                return "booklist";
            default:
                return null;
        }
    }
}
