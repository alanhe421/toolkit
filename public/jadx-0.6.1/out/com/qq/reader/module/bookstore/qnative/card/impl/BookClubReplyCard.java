package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NativeBookStoreSearchActivity;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.qq.reader.view.n;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class BookClubReplyCard extends BaseCommentCard {
    public static final String BID = "BID";
    public static final String IS_TOPREPLY = "IS_TOPREPLY";
    public static final String REPLY_ID = "REPLY_ID";
    public static final String REPLY_STATUS = "REPLY_STATUS";
    public static final String REPLY_UID = "REPLY_UID";
    public static final String REPLY_USER_BLACK = "black";
    public static final String REPLY_USER_NAME = "REPLY_USER_NAME";
    public static final int STATUS_AGREE = 5;
    public static final int STATUS_AUTHOR_REPLY = 7;
    public static final int STATUS_AUTHOR_SELF_REPLY = 3;
    public static final int STATUS_COMMENT_REPLY = 4;
    public static final int STATUS_REPLY_A2B = 1;
    public static final int STATUS_REPLY_SELF = 2;
    public static final int STATUS_UNAGREE = 6;
    private boolean isPlaceholder;
    private boolean isTopReply;
    public String mCommentUid;
    protected OnClickListener mContentListener = new OnClickListener(this) {
        final /* synthetic */ BookClubReplyCard a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            c cVar = new c(null);
            Bundle a = cVar.a();
            if (this.a.isLogin()) {
                a.putInt("function_type", 4);
                a.putInt(BookClubReplyCard.REPLY_STATUS, this.a.getReplyStatus());
                a.putBoolean("SHOWKEYBOARD", true);
                l lVar = (l) this.a.getItemList().get(0);
                a.putString(BookClubReplyCard.REPLY_ID, lVar.f);
                if (lVar.f.contains("client_fake")) {
                    af.a(ReaderApplication.getApplicationImp().getApplicationContext(), (CharSequence) "正在发送回复，请稍后再试", 0).a();
                    return;
                }
                a.putBoolean(BookClubReplyCard.IS_TOPREPLY, lVar.c());
                a.putString(BookClubReplyCard.REPLY_USER_NAME, lVar.a.a);
                a.putString(BookClubReplyCard.REPLY_UID, lVar.a.g);
                a.putInt(BookClubReplyCard.REPLY_USER_BLACK, lVar.a.o);
                a.putString(BookClubReplyCard.BID, String.valueOf(lVar.i));
                a.putString("COMMENT_ID", lVar.g);
                a.putString("PARA_TYPE_COMMENT_UID", this.a.mCommentUid);
                int[] iArr = new int[2];
                if (this.a.getRootView() != null) {
                    this.a.getRootView().getLocationInWindow(iArr);
                    a.putInt("PARA_TYPE_REPLY_CARD_POSITION", iArr[1] + this.a.getRootView().getHeight());
                }
                a.putInt("CTYPE", this.a.getCtype());
                cVar.a(this.a.getEvnetListener());
                return;
            }
            a.putInt("function_type", 3);
            cVar.a(this.a.getEvnetListener());
        }
    };
    protected String mPageType = "";

    public BookClubReplyCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public int getResLayoutId() {
        return R.layout.bookclub_reply;
    }

    protected boolean parseData(JSONObject jSONObject) {
        this.isPlaceholder = jSONObject.optBoolean("placeholder");
        String optString = jSONObject.optString("replyid");
        getItemList().clear();
        s lVar = new l();
        lVar.parseData(jSONObject);
        if (this.isTopReply) {
            setCardId(optString + IS_TOPREPLY);
        } else {
            setCardId(optString);
        }
        addItem(lVar);
        return !this.isPlaceholder;
    }

    public String getReplyid() {
        return ((l) getItemList().get(0)).f;
    }

    protected int getReplyStatus() {
        String c;
        a loginUser = getLoginUser();
        if (loginUser != null) {
            c = loginUser.c();
        } else {
            c = null;
        }
        l lVar = (l) getItemList().get(0);
        if (c.equalsIgnoreCase(this.mCommentUid)) {
            if (c.equalsIgnoreCase(lVar.a.g)) {
                return 3;
            }
            return 7;
        } else if (c.equalsIgnoreCase(lVar.a.g)) {
            return 2;
        } else {
            return 1;
        }
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            TextView textView;
            String a;
            CharSequence spanBookName;
            final l lVar = (l) getItemList().get(0);
            View rootView = getRootView();
            rootView.setOnClickListener(this.mContentListener);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.avatar_img_mask);
            setAvatarImage((UserCircleImageView) ap.a(rootView, R.id.avatar_img), lVar.a.b, lVar.a.n, null);
            OnClickListener anonymousClass2 = new OnClickListener(this) {
                final /* synthetic */ BookClubReplyCard b;

                public void onClick(View view) {
                    if (lVar.a == null || lVar.a.m <= 0 || TextUtils.isEmpty(lVar.a.n)) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "3");
                        i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                        o.d(this.b.getEvnetListener().getFromActivity(), lVar.a.g, lVar.a.a, lVar.a.b, null);
                        return;
                    }
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{lVar.a.n, lVar.a.a, lVar.a.b}), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            imageView.setOnClickListener(anonymousClass2);
            if ("bookclubdiscusslist".equals(this.mPageType)) {
                imageView = (ImageView) ap.a(getRootView(), R.id.support_icon);
                if (lVar.s != -1) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(lVar.s == 1 ? R.drawable.support_red : R.drawable.support_blue);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            } else {
                imageView = (ImageView) ap.a(rootView, R.id.avatar_text);
                ImageView imageView2 = (ImageView) ap.a(rootView, R.id.img_comment_topuser);
                if (lVar.a.k > 0) {
                    imageView2.setVisibility(0);
                } else {
                    imageView2.setVisibility(8);
                }
                if (lVar.a.f >= 0) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(getFanLevelIconId(lVar.a.f));
                } else {
                    imageView.setVisibility(8);
                }
                if (lVar.a.h != 0) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(R.drawable.bookclub_comment_user_tag_author);
                }
                imageView = (ImageView) ap.a(getRootView(), R.id.avatar_admin);
                if (lVar.a.i > 0) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(getAdminIconId(lVar.a.i - 1));
                } else {
                    imageView.setVisibility(8);
                }
                if (lVar.a.k > 0) {
                    imageView = (ImageView) ap.a(getRootView(), R.id.img_myz_icon);
                } else {
                    imageView = (ImageView) ap.a(getRootView(), R.id.img_myz_icon);
                }
                if (lVar.a.l > 0) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(getMYZLevelIconId(lVar.a.l - 1));
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
                textView = (TextView) ap.a(rootView, R.id.bookclub_reply_index);
                a = lVar.a();
                if (ao.s(a)) {
                    textView.setVisibility(8);
                } else {
                    textView.setText(a);
                    textView.setVisibility(0);
                }
                ao.a(lVar.b().j, (ImageView) ap.a(getRootView(), R.id.month_icon), false);
            }
            textView = (TextView) ap.a(rootView, R.id.username);
            textView.setText(lVar.a.a);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookClubReplyCard b;

                public void onClick(View view) {
                    if (lVar.a == null || lVar.a.m <= 0 || TextUtils.isEmpty(lVar.a.n)) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "3");
                        i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                        o.d(this.b.getEvnetListener().getFromActivity(), lVar.a.g, lVar.a.a, lVar.a.b, null);
                        return;
                    }
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{lVar.a.n, lVar.a.a, lVar.a.b}), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            textView.requestLayout();
            textView.setOnClickListener(anonymousClass2);
            textView = (TextView) ap.a(rootView, R.id.publishtime);
            if (ao.s(lVar.t)) {
                textView.setText(k.c(lVar.d));
            } else {
                textView.setText(lVar.t);
            }
            textView = (TextView) ap.a(rootView, R.id.content);
            a = "";
            Object spannableStringBuilder;
            if (lVar.c()) {
                spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append("[神回复]");
                if (lVar.p == 1) {
                    spannableStringBuilder.append("回复");
                    spannableStringBuilder.append(lVar.q);
                    spannableStringBuilder.append(": ");
                    spannableStringBuilder.append(lVar.b);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-5002854), 7, lVar.q.length() + 7, 18);
                } else {
                    spannableStringBuilder.append(lVar.b);
                }
                spannableStringBuilder.setSpan(new ForegroundColorSpan(-5032384), 0, 5, 18);
                spanBookName = spanBookName(spannableStringBuilder);
            } else {
                spannableStringBuilder = new SpannableStringBuilder();
                if (lVar.p == 1) {
                    spannableStringBuilder.append("回复");
                    spannableStringBuilder.append(lVar.q);
                    spannableStringBuilder.append(": ");
                    spannableStringBuilder.append(lVar.b);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-5002854), 2, lVar.q.length() + 2, 18);
                } else {
                    spannableStringBuilder.append(lVar.b);
                }
                spanBookName = spanBookName(spannableStringBuilder);
            }
            textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), spanBookName, textView.getTextSize()));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    protected CharSequence spanBookName(CharSequence charSequence) {
        int i = 0;
        CharSequence spannableString = new SpannableString(charSequence);
        int i2 = 0;
        while (i > -1 && i2 < 100) {
            i = TextUtils.indexOf(spannableString, '《', i);
            if (i >= 0) {
                try {
                    int indexOf = TextUtils.indexOf(spannableString, '》', i);
                    if (indexOf > 0) {
                        String charSequence2 = spannableString.subSequence(i + 1, indexOf).toString();
                        n anonymousClass4 = new n(this) {
                            final /* synthetic */ BookClubReplyCard a;

                            {
                                this.a = r1;
                            }

                            public void onClick(final View view) {
                                this.b = true;
                                view.postInvalidate();
                                Intent intent = new Intent();
                                intent.setClass(this.a.getRootView().getContext(), NativeBookStoreSearchActivity.class);
                                intent.setFlags(SigType.TLS);
                                intent.putExtra("searchkey", this.c);
                                this.a.getRootView().getContext().startActivity(intent);
                                new Handler().postDelayed(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass4 b;

                                    public void run() {
                                        this.b.b = false;
                                        view.postInvalidate();
                                    }
                                }, 100);
                            }
                        };
                        anonymousClass4.b = false;
                        anonymousClass4.c = charSequence2;
                        spannableString.setSpan(anonymousClass4, i, indexOf + 1, 18);
                        i++;
                    } else {
                        i = -1;
                    }
                } catch (Exception e) {
                    i = -1;
                }
            }
            i2++;
        }
        return spannableString;
    }

    public boolean isTopReply() {
        return this.isTopReply;
    }

    public void setTopRrply(boolean z) {
        this.isTopReply = z;
    }

    public boolean isBestReply() {
        return ((l) getItemList().get(0)).h == 1;
    }

    public void setBestReply(int i) {
        ((l) getItemList().get(0)).h = i;
    }

    public void setPageType(String str) {
        this.mPageType = str;
    }

    public float getUILevel() {
        if (this.mUILevel < 0.0f) {
            return this.mUILevel;
        }
        return (float) ((l) getItemList().get(0)).h();
    }

    public boolean isFakeCard() {
        l lVar = (l) getItemList().get(0);
        return !TextUtils.isEmpty(lVar.f) && lVar.f.contains("client_fake");
    }

    public boolean isPlaceholder() {
        return this.isPlaceholder;
    }
}
