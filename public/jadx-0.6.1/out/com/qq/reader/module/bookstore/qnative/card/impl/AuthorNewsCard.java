package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class AuthorNewsCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private final int MAX_COUNT = 3;
    private String authorId;
    private int isOwn;
    private int newsCount;

    public static class a extends s {
        public String a;
        public String b;
        public long c;
        public String d;
        public String e;
        public long f;
        public String g;

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString("imageUrl");
                this.b = jSONObject.optString("newsId");
                this.c = jSONObject.optLong("publishTime");
                this.d = jSONObject.optString(MessageKey.MSG_CONTENT);
                this.e = jSONObject.optString("title");
                this.f = jSONObject.optLong("readTimes");
                this.g = jSONObject.optString("articleUrl");
            }
        }
    }

    public AuthorNewsCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_news_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        if (jSONObject != null) {
            this.isOwn = jSONObject.optInt("owner");
            JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
                if (optJSONObject2 != null) {
                    this.newsCount = optJSONObject2.optInt("dynamicListCount");
                    this.authorId = optJSONObject2.optString("authorId");
                }
                JSONArray optJSONArray = optJSONObject.optJSONArray("dynamiclist");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return false;
                }
                while (optJSONArray != null && i < optJSONArray.length()) {
                    optJSONObject2 = optJSONArray.optJSONObject(i);
                    s aVar = new a();
                    aVar.parseData(optJSONObject2);
                    addItem(aVar);
                    i++;
                }
            }
        }
        return true;
    }

    public void attachView() {
        Map hashMap = new HashMap();
        hashMap.put("isOwn", String.valueOf(this.isOwn));
        i.a("event_D156", hashMap, ReaderApplication.getApplicationImp());
        List itemList = getItemList();
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(37, this.mShowTitle, this.newsCount + "条", null);
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_news_container);
        linearLayout.removeAllViews();
        int i = 0;
        while (itemList != null && i < itemList.size()) {
            a aVar = (a) itemList.get(i);
            View inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.author_news_item_layout, null);
            fillItemUI(inflate, aVar);
            linearLayout.addView(inflate, new LayoutParams(-1, -2));
            i++;
        }
        Button button = (Button) ap.a(getRootView(), R.id.btn_more);
        if (this.newsCount > 3) {
            button.setVisibility(0);
        } else {
            button.setVisibility(8);
        }
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AuthorNewsCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", String.valueOf(this.a.isOwn));
                i.a("event_D157", hashMap, ReaderApplication.getApplicationImp());
                Intent intent = new Intent(this.a.getEvnetListener().getFromActivity(), NativeBookStoreTwoLevelActivity.class);
                intent.putExtra("KEY_JUMP_PAGENAME", "authorAllNews");
                intent.putExtra("LOCAL_STORE_IN_TITLE", "全部动态");
                intent.putExtra("AUTHORPAGE_KEY_AUTHORID", this.a.authorId);
                intent.setFlags(SigType.TLS);
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                this.a.getEvnetListener().getFromActivity().startActivity(intent);
            }
        });
    }

    protected void fillItemUI(View view, final a aVar) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AuthorNewsCard b;

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), aVar.g, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageView imageView = (ImageView) ap.a(view, R.id.img_news_cover);
        Rect coverRect = getCoverRect();
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(coverRect.width(), coverRect.height()));
        TextView textView = (TextView) ap.a(view, R.id.tv_title);
        TextView textView2 = (TextView) ap.a(view, R.id.tv_content);
        TextView textView3 = (TextView) ap.a(view, R.id.tv_publishtime);
        TextView textView4 = (TextView) ap.a(view, R.id.tv_readtimes);
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(aVar.a, imageView, com.qq.reader.common.imageloader.a.a().j());
        textView2.setText(aVar.d);
        textView.setText(aVar.e);
        textView4.setText("阅读" + j.a(aVar.f));
        textView3.setText(k.c(aVar.c));
    }

    private Rect getCoverRect() {
        int dimensionPixelOffset = (com.qq.reader.common.c.a.bU - ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_news_card_item_cover_padding_left)) - ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_news_card_item_cover_padding_right);
        int i = (dimensionPixelOffset * Opcodes.FLOAT_TO_INT) / ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT;
        Rect rect = new Rect();
        rect.left = 0;
        rect.right = dimensionPixelOffset;
        rect.top = 0;
        rect.bottom = i;
        return rect;
    }
}
