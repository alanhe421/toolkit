package com.qq.reader.module.qmessage.data.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class MessageInterActiveCard extends MessageBaseCard {
    String mAuthorId;
    String mBookName;
    int mChapter;
    String mContentContext;
    String mIconUrl;
    boolean mIsAuthor;
    String mNickname;
    String mReplyContent;
    String mTitle;
    String mUserId;
    int mVipStatus;

    public MessageInterActiveCard(b bVar) {
        super(bVar);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        boolean z = false;
        super.parseData(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.mContentContext = optJSONObject.optString("context");
        this.mBookName = optJSONObject.optString("bname");
        this.mReplyContent = optJSONObject.optString("reply");
        this.mChapter = optJSONObject.optInt("chapter");
        this.mTitle = optJSONObject.optJSONObject("sender").optString("title");
        this.mNickname = optJSONObject.optJSONObject("sender").optString("nickname");
        this.mIconUrl = optJSONObject.optJSONObject("sender").optString(MessageKey.MSG_ICON);
        this.mVipStatus = optJSONObject.optJSONObject("sender").optInt("vipStatus", 0);
        if (optJSONObject.optJSONObject("sender").optInt("isManito", 0) == 1) {
            z = true;
        }
        this.mIsAuthor = z;
        this.mAuthorId = optJSONObject.optJSONObject("sender").optString("authorid");
        this.mUserId = optJSONObject.optJSONObject("sender").optString("userid");
        return true;
    }

    public int getResLayoutId() {
        return R.layout.message_interaction_layout;
    }

    public void attachView() {
        final View rootView = getRootView();
        if (rootView != null) {
            final Object url = getURL();
            if (!TextUtils.isEmpty(url)) {
                rootView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MessageInterActiveCard c;

                    public void onClick(View view) {
                        if (this.c.getMessageSubType() == 9) {
                            af.a(rootView.getContext(), R.string.no_comment_text, 0).a();
                            return;
                        }
                        try {
                            c.a(this.c.getEvnetListener().getFromActivity(), url + "&" + "lcoate" + "=1&from=1");
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, String.valueOf(this.c.getMessageId()));
                            i.a("event_C159", hashMap, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            rootView.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ MessageInterActiveCard a;

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
            TextView textView = (TextView) ap.a(getRootView(), R.id.title);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.content);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.time);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.icon);
            ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.level);
            TextView textView4 = (TextView) ap.a(getRootView(), R.id.source);
            CharSequence stringBuilder = new StringBuilder();
            stringBuilder.append("《").append(this.mBookName).append("》");
            if (this.mChapter > 0) {
                stringBuilder.append("第").append(this.mChapter).append("章");
            }
            textView4.setText(stringBuilder);
            ap.a(getRootView(), R.id.audio_containner).setVisibility(8);
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
                textView3.setText("刚刚");
            } else if (currentTimeMillis >= 60 && currentTimeMillis < 3600) {
                textView3.setText((currentTimeMillis / 60) + "分钟前");
            } else if (i2 == i) {
                textView3.setText("今天" + new SimpleDateFormat("HH:mm").format(date));
            } else if (i2 - i == 1) {
                textView3.setText("昨天" + new SimpleDateFormat("HH:mm").format(date));
            } else if (i2 - i == 2) {
                textView3.setText("前天" + new SimpleDateFormat("HH:mm").format(date));
            } else {
                textView3.setText(new SimpleDateFormat("MM-dd HH:mm").format(date));
            }
            textView3 = (TextView) ap.a(getRootView(), R.id.referred_text);
            textView3.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), this.mContentContext, textView3.getTextSize()));
            switch (getMessageSubType()) {
                case 5:
                case 10:
                    textView2.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), "回复了你:" + this.mReplyContent, textView2.getTextSize()));
                    break;
                case 6:
                    textView2.setText("置顶了你的书评");
                    break;
                case 7:
                    textView2.setText("将你的书评设置为精华书评");
                    break;
                case 8:
                    textView2.setText("赞了你的书评");
                    break;
                case 9:
                    textView2.setText("删除了你的书评");
                    break;
                case 11:
                    textView2.setText("将你的回复设为神回复");
                    break;
                default:
                    textView2.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), this.mReplyContent, textView2.getTextSize()));
                    break;
            }
            textView.setText(this.mNickname);
            com.qq.reader.common.imageloader.b h = com.qq.reader.common.imageloader.a.a().h();
            if (this.mIsAuthor) {
                h = com.qq.reader.common.imageloader.a.a().f();
            }
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mIconUrl, imageView, h);
            ((ImageView) ap.a(getRootView(), R.id.icon_mask)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MessageInterActiveCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Map hashMap = new HashMap();
                    if (this.a.mIsAuthor) {
                        hashMap.put(s.ORIGIN, "9");
                        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    } else {
                        hashMap.put(s.ORIGIN, "2");
                        i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                    }
                    o.a(this.a.getEvnetListener().getFromActivity(), this.a.mIsAuthor, this.a.mUserId, this.a.mAuthorId, this.a.mIconUrl, this.a.mNickname);
                }
            });
            String[] split = this.mTitle.split(",");
            if (Integer.valueOf(split[1]).intValue() == 1) {
                imageView2.setVisibility(0);
                imageView2.setImageResource(R.drawable.bookclub_comment_user_tag_author);
            } else {
                int intValue = Integer.valueOf(split[0]).intValue();
                if (intValue < 0 || intValue > 10) {
                    imageView2.setVisibility(8);
                } else {
                    imageView2.setVisibility(0);
                    imageView2.setImageResource(getFanLevelIconId(intValue));
                }
            }
            ao.a(this.mVipStatus, (ImageView) ap.a(getRootView(), R.id.month_icon), false);
        }
    }
}
