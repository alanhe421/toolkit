package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.activity.NativeBackRefreshTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SubscribedAuthorListCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private int mIsOwn;
    private int[] resIds = new int[]{R.id.item_0, R.id.item_1, R.id.item_2, R.id.item_3};
    private int subscribeCount;
    private String uid;

    private class a extends s {
        public String a;
        public int b;
        public String c;
        public String d;
        final /* synthetic */ SubscribedAuthorListCard e;
        private final String f;
        private final String g;
        private final String h;
        private final String i;

        private a(SubscribedAuthorListCard subscribedAuthorListCard) {
            this.e = subscribedAuthorListCard;
            this.f = MessageKey.MSG_ICON;
            this.g = "labelName";
            this.h = "nickname";
            this.i = "authorId";
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString(MessageKey.MSG_ICON);
                this.b = jSONObject.optInt("labelName");
                this.c = jSONObject.optString("nickname");
                this.d = jSONObject.optString("authorId");
            }
        }
    }

    public SubscribedAuthorListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.subscribed_author_list_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        List itemList = getItemList();
        if (itemList != null) {
            itemList.clear();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("followManitoList");
        this.subscribeCount = jSONObject.optInt("followManitoCount");
        this.uid = jSONObject.optString("userId");
        this.mIsOwn = jSONObject.optInt("isOwn");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            s aVar = new a();
            aVar.parseData(optJSONArray.optJSONObject(i));
            addItem(aVar);
            i++;
        }
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return false;
        }
        return true;
    }

    public void attachView() {
        initAllAuthors();
        List itemList = getItemList();
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_more_btn);
        ((CardTitle) ap.a(getRootView(), R.id.title)).setCardTitle(37, this.mShowTitle, this.subscribeCount + "人", null);
        int i = 0;
        while (itemList != null && i < itemList.size() && i < this.resIds.length) {
            setAuthorInfo(i, (a) itemList.get(i));
            i++;
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SubscribedAuthorListCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", String.valueOf(this.a.mIsOwn));
                i.a("event_C289", hashMap, ReaderApplication.getApplicationImp());
                Intent intent = new Intent(this.a.getEvnetListener().getFromActivity(), NativeBackRefreshTwoLevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEY_JUMP_PAGENAME", "user_center_subscribed_authors");
                bundle.putString("userId", this.a.uid);
                bundle.putString("LOCAL_STORE_IN_TITLE", "关注的作者");
                bundle.putInt(NativeBackRefreshTwoLevelActivity.k, 1);
                intent.putExtras(bundle);
                this.a.getEvnetListener().getFromActivity().startActivityForResult(intent, 1);
            }
        });
        if (this.subscribeCount > 4) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        Map hashMap = new HashMap();
        hashMap.put("isOwn", this.mIsOwn + "");
        i.a("event_C287", hashMap, ReaderApplication.getApplicationImp());
    }

    private void setAuthorInfo(int i, final a aVar) {
        if (i < this.resIds.length && aVar != null) {
            View a = ap.a(getRootView(), this.resIds[i]);
            ImageView imageView = (ImageView) a.findViewById(R.id.img_author_avatar);
            final ImageView imageView2 = (ImageView) a.findViewById(R.id.avatar_img_mask);
            if (imageView != null) {
                c.a(getEvnetListener().getFromActivity()).a(aVar.a, imageView, com.qq.reader.common.imageloader.a.a().o());
                imageView2.setBackgroundResource(R.drawable.bookclub_avatar_small_bg_selector);
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ SubscribedAuthorListCard b;

                    public void onClick(View view) {
                        if (aVar != null && !TextUtils.isEmpty(aVar.d)) {
                            Map hashMap = new HashMap();
                            hashMap.put("isOwn", String.valueOf(this.b.mIsOwn));
                            i.a("event_C288", hashMap, ReaderApplication.getApplicationImp());
                            JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
                            jumpActivityParameter.a(1);
                            o.c(this.b.getEvnetListener().getFromActivity(), aVar.d, aVar.c, aVar.a, jumpActivityParameter);
                        }
                    }
                });
            }
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SubscribedAuthorListCard b;

                public void onClick(View view) {
                    imageView2.performClick();
                }
            });
            imageView = (ImageView) a.findViewById(R.id.img_author_tag);
            if (imageView != null) {
                imageView.setImageResource(ao.e(aVar.b));
            }
            TextView textView = (TextView) a.findViewById(R.id.tv_author_name);
            if (textView != null) {
                textView.setText(aVar.c);
            }
        }
    }

    private void initAllAuthors() {
        for (int a : this.resIds) {
            View a2 = ap.a(getRootView(), a);
            ImageView imageView = (ImageView) a2.findViewById(R.id.img_author_avatar);
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
            imageView = (ImageView) a2.findViewById(R.id.img_author_tag);
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
            TextView textView = (TextView) a2.findViewById(R.id.tv_author_name);
            if (textView != null) {
                textView.setText("");
            }
        }
    }
}
