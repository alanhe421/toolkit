package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.item.af;
import com.qq.reader.module.bookstore.qnative.item.ag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookclubTopCommentListCard extends BaseCommentCard {
    private static final int ICON_1 = 36;
    private static final int ICON_2 = 37;
    private static final int ICON_3 = 38;
    private static final int ICON_4 = 39;
    public ArrayList<af> mReplylist = new ArrayList(3);
    private LinearLayout mRootview;

    class a {
        int a;
        int b;
        String c;
        String d;
        ag e;
        int f;
        int g;
        int h;
        long i;
        long j;
        int k;
        int l;
        String m;
        long n;
        int o;
        int p = -1;
        boolean q = false;
        int r;
        final /* synthetic */ BookclubTopCommentListCard s;

        a(BookclubTopCommentListCard bookclubTopCommentListCard) {
            this.s = bookclubTopCommentListCard;
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optInt("type");
                this.b = jSONObject.optInt(MessageKey.MSG_ICON);
                this.c = jSONObject.optString("title");
                this.d = jSONObject.optString(MessageKey.MSG_CONTENT);
                this.e = new ag(jSONObject.optJSONObject("user"));
                this.f = jSONObject.optInt("subtype");
                try {
                    this.d = Html.fromHtml(this.d).toString();
                    this.c = Html.fromHtml(this.c).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(this.c)) {
                    this.c = this.d;
                }
                this.k = jSONObject.optInt("better");
                this.g = jSONObject.optInt("authortag");
                this.l = jSONObject.optInt("agree");
                this.h = jSONObject.optInt("replycount");
                this.m = jSONObject.optString("commentid");
                this.n = jSONObject.optLong("bid");
                this.i = jSONObject.optLong("createtime");
                this.j = jSONObject.optLong("lastreplytime");
                this.o = jSONObject.optInt("status");
                this.p = jSONObject.optInt("agreestatus");
                if (jSONObject.has("reward")) {
                    this.r = jSONObject.optInt("reward");
                    if (this.r > 0) {
                        this.q = true;
                        return;
                    } else {
                        this.q = false;
                        return;
                    }
                }
                this.q = false;
                this.r = 0;
            }
        }
    }

    class b extends s {
        int a;
        int b;
        a c;
        final /* synthetic */ BookclubTopCommentListCard d;

        b(BookclubTopCommentListCard bookclubTopCommentListCard) {
            this.d = bookclubTopCommentListCard;
        }

        public void parseData(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("comment");
            if (optJSONObject != null) {
                this.c = new a(this.d);
                this.c.a(optJSONObject);
            }
            this.a = jSONObject.optInt("type");
            this.b = jSONObject.optInt(MessageKey.MSG_ICON);
        }
    }

    public BookclubTopCommentListCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str, int i) {
        super(bVar, str, i);
        setCardId(str);
    }

    public int getResLayoutId() {
        return R.layout.bookclubspecailcommentlayout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        getItemList().clear();
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                b bVar = new b(this);
                bVar.parseData(optJSONObject);
                getItemList().add(bVar);
            }
            i++;
        }
        return true;
    }

    public void attachView() {
        this.mRootview = (LinearLayout) ap.a(getRootView(), R.id.container);
        this.mRootview.removeAllViews();
        int i = 0;
        for (s sVar : getItemList()) {
            View item = getItem((b) sVar);
            item.findViewById(R.id.bottomline).setVisibility(i == getItemList().size() + -1 ? 0 : 8);
            this.mRootview.addView(item);
            i++;
        }
    }

    private View getItem(final b bVar) {
        View inflate = LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.bookclub_speciallist_item, null);
        if (inflate != null) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.speciialcommenticon);
            TextView textView = (TextView) inflate.findViewById(R.id.speciialcommenttitle);
            switch (bVar.b) {
                case 36:
                    imageView.setImageResource(R.drawable.bookclub_reward_tag);
                    break;
                case 37:
                    imageView.setImageResource(R.drawable.bookclub_hotcoment_tag);
                    break;
                case 39:
                    imageView.setImageResource(R.drawable.bookclub_top_tag);
                    break;
            }
            if (!(bVar.c == null || TextUtils.isEmpty(bVar.c.c))) {
                textView.setMaxWidth(ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - dip2px(52.0f));
                textView.setText(bVar.c.c);
            }
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookclubTopCommentListCard b;

                public void onClick(View view) {
                    long j = bVar.c.n;
                    String str = bVar.c.m;
                    String str2 = bVar.c.e.g;
                    Bundle bundle = new Bundle();
                    c cVar = new c(bundle);
                    bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
                    if (j < 4) {
                        bundle.putInt("CTYPE", 4);
                    } else {
                        bundle.putInt("CTYPE", this.b.getCtype());
                    }
                    bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
                    bundle.putString("COMMENT_ID", str);
                    bundle.putString("PARA_TYPE_COMMENT_UID", str2);
                    bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubdetail));
                    cVar.a(this.b.getEvnetListener());
                }
            });
        }
        return inflate;
    }

    private static int dip2px(float f) {
        return (int) ((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
