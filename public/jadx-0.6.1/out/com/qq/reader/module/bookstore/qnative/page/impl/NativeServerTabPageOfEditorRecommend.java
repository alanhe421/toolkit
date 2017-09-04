package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.an;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendCard;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendEditorInfoCard;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendFourBookCard;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendOneBookCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForEditorRecommend;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class NativeServerTabPageOfEditorRecommend extends af {
    private String a;
    private d b;
    private boolean c = false;

    private class EditorRecommendDividerCard extends a {
        static final String TYPE_EDITOR_BOY = "boy";
        static final String TYPE_EDITOR_GIRL = "girl";
        static final String TYPE_EDITOR_PUBLISH = "publish";

        EditorRecommendDividerCard(b bVar, String str) {
            super(bVar, str);
        }

        public int getResLayoutId() {
            return R.layout.localstore_card_recommend_divider;
        }

        protected boolean parseData(JSONObject jSONObject) throws Exception {
            return true;
        }

        public void attachView() {
            TextView textView = (TextView) ap.a(getRootView(), R.id.tv_editor_sort);
            if (TYPE_EDITOR_PUBLISH.equals(this.mType)) {
                textView.setText("出版频道主编");
            } else if (TYPE_EDITOR_BOY.equals(this.mType)) {
                textView.setText("男生频道主编");
            } else if (TYPE_EDITOR_GIRL.equals(this.mType)) {
                textView.setText("女生频道主编");
            } else {
                ap.a(getRootView(), R.id.fl_editor_divider).setVisibility(8);
            }
        }
    }

    public NativeServerTabPageOfEditorRecommend(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        this.a = bundle.getString("KEY_ACTIONTAG");
        return cVar.a(e.a, "common/" + this.a + "?sex=" + com.qq.reader.appconfig.a.d.aS(ReaderApplication.getApplicationImp()));
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        super.b(jSONObject);
        x();
        int length;
        if ("editorrec".equals(this.a)) {
            JSONArray optJSONArray = jSONObject.optJSONArray("list");
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i).optJSONObject("recommend");
                if (optJSONObject != null) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("bookList");
                    if (optJSONArray2 != null) {
                        length = optJSONArray2.length();
                        VirtualRecommendCard virtualRecommendCard = null;
                        if (length == 1) {
                            virtualRecommendCard = new VirtualRecommendOneBookCard(this, length, true);
                        } else if (length >= 2) {
                            virtualRecommendCard = new VirtualRecommendFourBookCard(this, length, true);
                        }
                        if (virtualRecommendCard != null) {
                            virtualRecommendCard.fillData(optJSONArray.optJSONObject(i));
                            virtualRecommendCard.setEventListener(l());
                            this.k.add(virtualRecommendCard);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
                return;
            }
        } else if ("editorList".equals(this.a)) {
            String[] strArr = new String[]{"pubEditors", "boyEditors", "girlEditors"};
            String[] strArr2 = new String[]{"publish", "boy", "girl"};
            int[] iArr = new int[]{0, 1, 2};
            int aS = com.qq.reader.appconfig.a.d.aS(ReaderApplication.getApplicationImp());
            if (aS == 1) {
                iArr = new int[]{1, 0, 2};
            } else if (aS == 2) {
                iArr = new int[]{2, 0, 1};
            } else if (aS == 3) {
                iArr = new int[]{0, 1, 2};
            }
            for (aS = 0; aS < strArr.length; aS++) {
                JSONArray optJSONArray3 = jSONObject.optJSONArray(strArr[iArr[aS]]);
                EditorRecommendDividerCard editorRecommendDividerCard = new EditorRecommendDividerCard(this, strArr2[iArr[aS]]);
                if (optJSONArray3.length() > 0) {
                    editorRecommendDividerCard.fillData(new JSONObject());
                    this.k.add(editorRecommendDividerCard);
                    for (length = 0; length < optJSONArray3.length(); length++) {
                        boolean z;
                        if (length != optJSONArray3.length() - 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        VirtualRecommendEditorInfoCard virtualRecommendEditorInfoCard = new VirtualRecommendEditorInfoCard(this, z);
                        virtualRecommendEditorInfoCard.fillData(optJSONArray3.optJSONObject(length));
                        virtualRecommendEditorInfoCard.setEventListener(l());
                        this.k.add(virtualRecommendEditorInfoCard);
                    }
                }
            }
        }
    }

    private void x() {
        try {
            if (this.b == null) {
                JSONObject jSONObject = new JSONObject(an.a().a(5));
                this.b = new d();
                this.b.a(jSONObject);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = this.b.g();
            for (int i = 0; i < g.size(); i++) {
                d.b bVar = (d.b) g.get(i);
                if (bVar != null) {
                    bVar.c = bVar.b.equals(string);
                }
            }
        }
        this.n = this.b;
    }

    public boolean a() {
        return false;
    }

    public void b(int i) {
        if (i == 1 && !this.c) {
            i.a("event_F142", null, l().getFromActivity());
            this.c = true;
        }
    }

    public Class c() {
        return NativePageFragmentForEditorRecommend.class;
    }
}
