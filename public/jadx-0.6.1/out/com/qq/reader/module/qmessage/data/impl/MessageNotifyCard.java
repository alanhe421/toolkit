package com.qq.reader.module.qmessage.data.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

public class MessageNotifyCard extends MessageBaseCard {
    String mAuthorId;
    String mContent;
    String mIconUrl;
    String mImageUrl;
    boolean mIsAuthor;
    String mNickname;
    String mTitle;
    String mUserId;

    public MessageNotifyCard(b bVar) {
        super(bVar);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        boolean z = false;
        super.parseData(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.mContent = optJSONObject.optString(MessageKey.MSG_CONTENT);
        this.mTitle = optJSONObject.optJSONObject("sender").optString("title");
        this.mNickname = optJSONObject.optJSONObject("sender").optString("nickname");
        this.mIconUrl = optJSONObject.optJSONObject("sender").optString(MessageKey.MSG_ICON);
        this.mImageUrl = optJSONObject.optString(SocialConstants.PARAM_IMG_URL);
        if (optJSONObject.optJSONObject("sender").optInt("isManito", 0) == 1) {
            z = true;
        }
        this.mIsAuthor = z;
        this.mAuthorId = optJSONObject.optJSONObject("sender").optString("authorid");
        this.mUserId = optJSONObject.optJSONObject("sender").optString("userid");
        return true;
    }

    public int getResLayoutId() {
        return R.layout.message_notification_layout;
    }

    public void attachView() {
        View rootView = getRootView();
        if (rootView != null) {
            final Object url = getURL();
            if (!TextUtils.isEmpty(url)) {
                rootView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MessageNotifyCard b;

                    public void onClick(View view) {
                        try {
                            c.a(this.b.getEvnetListener().getFromActivity(), url);
                            new HashMap().put(s.ORIGIN, String.valueOf(this.b.getMessageId()));
                            i.a("event_C159", null, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            rootView.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ MessageNotifyCard a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    a evnetListener = this.a.getEvnetListener();
                    if (evnetListener != null) {
                        Bundle bundle = new Bundle();
                        bundle.putLong("MessageID", this.a.getMessageId());
                        evnetListener.doFunction(bundle);
                    }
                    return true;
                }
            });
            TextView textView = (TextView) ap.a(getRootView(), R.id.content);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.time);
            final ImageView imageView = (ImageView) ap.a(getRootView(), R.id.image);
            ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.icon);
            ((TextView) ap.a(getRootView(), R.id.title)).setText(this.mNickname);
            textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), this.mContent, textView.getTextSize()));
            long currentTimeMillis = (System.currentTimeMillis() - getMessageTime()) / 1000;
            Date date = new Date(getMessageTime());
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            int i = instance.get(6);
            Date date2 = new Date(System.currentTimeMillis());
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(date2);
            int i2 = instance2.get(6);
            if (currentTimeMillis < 60) {
                textView2.setText("刚刚");
            } else if (currentTimeMillis >= 60 && currentTimeMillis < 3600) {
                textView2.setText((currentTimeMillis / 60) + "分钟前");
            } else if (i2 == i) {
                textView2.setText("今天" + new SimpleDateFormat("HH:mm").format(date));
            } else if (i2 - i == 1) {
                textView2.setText("昨天" + new SimpleDateFormat("HH:mm").format(date));
            } else if (i2 - i == 2) {
                textView2.setText("前天" + new SimpleDateFormat("HH:mm").format(date));
            } else {
                textView2.setText(new SimpleDateFormat("MM-dd HH:mm").format(date));
            }
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mIconUrl, imageView2, com.qq.reader.common.imageloader.a.a().h());
            if (TextUtils.isEmpty(this.mImageUrl)) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mImageUrl, imageView, com.qq.reader.common.imageloader.a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ MessageNotifyCard b;

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    imageView.setVisibility(8);
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    return false;
                }
            });
        }
    }
}
