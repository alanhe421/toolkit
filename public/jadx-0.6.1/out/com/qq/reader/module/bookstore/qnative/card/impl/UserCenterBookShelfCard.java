package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserCenterBookShelfCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private int[] bookContainerIds = new int[]{R.id.ll_left, R.id.ll_center, R.id.ll_right};
    private int hisQuestionCount;
    private int mBookCount = 0;
    private int mBookSecretCount = 0;
    private ArrayList<a> mBookShelfList;
    private int mInterActionCount = 0;
    private int mIsOwn;
    private int mListenCount = 0;
    private int mQuestionCount = 0;
    private int mTotalCommentCount = 0;
    private String mUserId;

    private class a {
        final /* synthetic */ UserCenterBookShelfCard a;
        private String b;
        private long c;
        private String d;
        private String e;
        private int f;
        private int g;
        private String h;
        private int i;

        private a(UserCenterBookShelfCard userCenterBookShelfCard) {
            this.a = userCenterBookShelfCard;
        }
    }

    public UserCenterBookShelfCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_bookshelf_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (jSONObject == null) {
            return false;
        }
        this.hisQuestionCount = jSONObject.optInt("hisQuestionCount");
        this.mBookSecretCount = jSONObject.optInt("secretCount");
        this.mBookCount = jSONObject.optInt("shelfCount");
        this.mTotalCommentCount = jSONObject.optInt("totalCount");
        this.mInterActionCount = jSONObject.optInt("contentCount");
        this.mUserId = jSONObject.optString("userId");
        this.mIsOwn = jSONObject.optInt("isOwn");
        this.mQuestionCount = jSONObject.optInt("mQuestionCount");
        this.mListenCount = jSONObject.optInt("mListenCount");
        JSONArray optJSONArray = jSONObject.optJSONArray("shelfList");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return false;
        }
        this.mBookShelfList = new ArrayList();
        while (i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.b = optJSONObject.optString("author");
                aVar.c = optJSONObject.optLong("bid");
                aVar.d = optJSONObject.optString("bookCover");
                aVar.e = optJSONObject.optString("bookName");
                aVar.f = optJSONObject.optInt("isListen");
                aVar.g = optJSONObject.optInt("isSecret");
                aVar.i = optJSONObject.optInt("isComic");
                aVar.h = optJSONObject.optString("qurl");
                this.mBookShelfList.add(aVar);
            }
            i++;
        }
        return true;
    }

    public void attachView() {
        String h;
        ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.ll_container);
        int i = 0;
        while (i < 3) {
            a aVar;
            if (this.mBookShelfList.size() <= 0 || this.mBookShelfList.size() <= i) {
                aVar = null;
            } else {
                aVar = (a) this.mBookShelfList.get(i);
            }
            ViewGroup viewGroup2 = (ViewGroup) ap.a(viewGroup, this.bookContainerIds[i]);
            ImageView imageView = (ImageView) ap.a(viewGroup2, R.id.iv_cover);
            TextView textView = (TextView) ap.a(viewGroup2, R.id.tv_bk_name);
            TextView textView2 = (TextView) ap.a(viewGroup2, R.id.tv_bk_des);
            ImageView imageView2 = (ImageView) ap.a(viewGroup2, R.id.iv_bk_type);
            ImageView imageView3 = (ImageView) ap.a(viewGroup2, R.id.iv_bk_secret);
            if (aVar != null) {
                viewGroup2.setVisibility(0);
                textView.setText(aVar.e);
                textView2.setText(aVar.b);
                if (aVar.i == 1) {
                    imageView2.setImageResource(R.drawable.comic_book_icon);
                    imageView2.setVisibility(0);
                    h = ao.h(aVar.c);
                } else if (aVar.f == 1) {
                    imageView2.setImageResource(R.drawable.listen_book_icon);
                    imageView2.setVisibility(0);
                    h = ao.a(aVar.c, false, (int) Opcodes.OR_INT);
                } else {
                    imageView2.setVisibility(8);
                    h = ao.g(aVar.c);
                }
                c.a(getEvnetListener().getFromActivity()).a(h, imageView, com.qq.reader.common.imageloader.a.a().j());
                if (aVar.g == 0) {
                    imageView3.setVisibility(0);
                } else {
                    imageView3.setVisibility(8);
                }
            } else {
                viewGroup2.setVisibility(8);
            }
            i++;
        }
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title);
        h = "";
        if (this.mBookSecretCount > 0) {
            h = "私密阅读" + this.mBookSecretCount + "本";
        } else {
            h = this.mBookCount + "本";
        }
        if (this.mIsOwn == 1) {
            cardTitle.setCardTitle(37, BookShelfFragment.CATEGORY_BOOKSTAND, h, "");
        } else {
            cardTitle.setCardTitle(37, "TA的书架", h, "");
        }
        Button button = (Button) ap.a(getRootView(), R.id.btn_more);
        if (this.mBookCount > 3) {
            button.setVisibility(0);
        } else {
            button.setVisibility(8);
        }
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterBookShelfCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", this.a.mIsOwn + "");
                i.a("event_D130", hashMap, ReaderApplication.getApplicationImp());
                Bundle bundle = new Bundle();
                bundle.putString("KEY_JUMP_PAGENAME", "user_center_more_book");
                bundle.putString("LOCAL_STORE_IN_TITLE", "全部书籍");
                bundle.putString("userId", this.a.mUserId);
                new com.qq.reader.module.bookstore.qnative.c(bundle).a(this.a.getEvnetListener());
            }
        });
        ap.a(getRootView(), R.id.ll_left).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterBookShelfCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", this.a.mIsOwn + "");
                i.a("event_D129", hashMap, ReaderApplication.getApplicationImp());
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), ((a) this.a.mBookShelfList.get(0)).h, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ap.a(getRootView(), R.id.ll_center).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterBookShelfCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", this.a.mIsOwn + "");
                i.a("event_D129", hashMap, ReaderApplication.getApplicationImp());
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), ((a) this.a.mBookShelfList.get(1)).h, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ap.a(getRootView(), R.id.ll_right).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterBookShelfCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", this.a.mIsOwn + "");
                i.a("event_D129", hashMap, ReaderApplication.getApplicationImp());
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), ((a) this.a.mBookShelfList.get(2)).h, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        if (this.mIsOwn != 1 && this.hisQuestionCount == 0 && this.mTotalCommentCount == 0 && this.mInterActionCount == 0) {
            a.setVisibility(8);
        }
        Map hashMap = new HashMap();
        hashMap.put("isOwn", String.valueOf(this.mIsOwn));
        i.a("event_C281", hashMap, ReaderApplication.getApplicationImp());
    }

    private void setImage(ImageView imageView, String str) {
        c.a(getEvnetListener().getFromActivity()).a(ao.g(Long.valueOf(str).longValue()), imageView, com.qq.reader.common.imageloader.a.a().j());
    }
}
