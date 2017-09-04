package com.qq.reader.module.bookstore.qnative.card.impl;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class VirtualRecommendCard extends a {
    private boolean avatarCanClick = false;
    private int bookSize = 100;
    ArrayList<g> mBookItems = new ArrayList();
    private String mDesc;
    private String mEditorAvatarUrl;
    private long mEditorId;
    private String mEditorName;
    long mPublishTime;
    private String mRecommendText;

    VirtualRecommendCard(b bVar, int i, boolean z) {
        super(bVar, "");
        this.bookSize = i;
        this.avatarCanClick = z;
    }

    VirtualRecommendCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (jSONObject == null) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("editor");
        this.mEditorId = optJSONObject.optLong("id");
        this.mEditorName = optJSONObject.optString("name");
        this.mDesc = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mEditorAvatarUrl = optJSONObject.optString("avatar");
        optJSONObject = jSONObject.optJSONObject("recommend");
        this.mRecommendText = optJSONObject.optString("reason");
        this.mPublishTime = optJSONObject.optLong(MessageKey.MSG_DATE);
        JSONArray optJSONArray = optJSONObject.optJSONArray("bookList");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length < this.bookSize) {
            return false;
        }
        if (this.mBookItems.size() > 0) {
            this.mBookItems.clear();
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            g gVar = new g();
            gVar.b(this.mFromBid);
            gVar.parseData(jSONObject2);
            this.mBookItems.add(gVar);
            i++;
        }
        return true;
    }

    protected void setCardTitle() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_editor_des);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.iv_editor_avatar);
        ((TextView) ap.a(getRootView(), R.id.tv_editor_name)).setText(this.mEditorName);
        textView.setText(this.mDesc);
        setFormatText(this.mRecommendText);
        c.a(getEvnetListener().getFromActivity()).a(this.mEditorAvatarUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        if (this.avatarCanClick) {
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VirtualRecommendCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.a(this.a.getEvnetListener().getFromActivity(), this.a.mEditorName, this.a.mEditorId);
                }
            });
        }
        showTitleDivider(true);
    }

    private void setFormatText(String str) {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_recommend_text);
        textView.setVisibility(0);
        int textSize = (int) textView.getTextSize();
        int paddingLeft = (((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - textView.getPaddingLeft()) - textView.getPaddingRight()) * 3) - (textSize * 6);
        if (((int) textView.getPaint().measureText(str)) > paddingLeft) {
            str = str.substring(0, paddingLeft / textSize) + "..";
        }
        String str2 = "\" " + str + " \"";
        CharSequence spannableString = new SpannableString(str2);
        Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_start);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.qq.reader.common.emotion.b.b bVar = new com.qq.reader.common.emotion.b.b(drawable);
        drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_end);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new com.qq.reader.common.emotion.b.b(drawable), str2.length() - 1, str2.length(), 33);
        spannableString.setSpan(bVar, 0, 1, 33);
        textView.setText(spannableString);
    }

    private void showTitleDivider(boolean z) {
        View a = ap.a(getRootView(), R.id.divider_line);
        if (z) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
    }

    protected void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put("sex", d.aS(ReaderApplication.getApplicationImp()) + "");
        i.a("event_F141", hashMap, ReaderApplication.getApplicationImp());
    }
}
