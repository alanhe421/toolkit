package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.login.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class BookClubChapterEndCard extends BookClubReplyCard {
    l item;
    private Animation mAgreeAnimaiton;
    private OnClickListener mParaiseListener;
    private Animation mUnAgreeAnimaiton;

    public BookClubChapterEndCard(b bVar, String str, int i) {
        super(bVar, str, i);
        this.mAgreeAnimaiton = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.agreescale_out);
        this.mUnAgreeAnimaiton = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.hasagree_shake);
        this.mParaiseListener = new OnClickListener(this) {
            final /* synthetic */ BookClubChapterEndCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B367", null, ReaderApplication.getApplicationImp());
                if (this.a.isLogin()) {
                    this.a.doAgreeOnMainThread();
                } else if (this.a.getEvnetListener() != null) {
                    ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.a.getEvnetListener().getFromActivity();
                    if (readerBaseActivity != null) {
                        readerBaseActivity.setLoginNextTask(new a(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.a.doAgreeOnMainThread();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                        readerBaseActivity.startLogin();
                    }
                }
            }
        };
        this.mContentListener = new OnClickListener(this) {
            final /* synthetic */ BookClubChapterEndCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B363", null, ReaderApplication.getApplicationImp());
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
                    a.putInt("REPLY_FROM", 1001);
                    cVar.a(this.a.getEvnetListener());
                    return;
                }
                a.putInt("function_type", 3);
                cVar.a(this.a.getEvnetListener());
            }
        };
    }

    public int getResLayoutId() {
        return R.layout.bookclub_chapterend;
    }

    protected boolean parseData(JSONObject jSONObject) {
        return super.parseData(jSONObject);
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            CharSequence charSequence;
            this.item = (l) getItemList().get(0);
            View rootView = getRootView();
            rootView.setOnClickListener(this.mContentListener);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.avatar_img_mask);
            setAvatarImage((UserCircleImageView) ap.a(rootView, R.id.avatar_img), this.item.a.b, this.item.a.n, null);
            OnClickListener anonymousClass3 = new OnClickListener(this) {
                final /* synthetic */ BookClubChapterEndCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.item.a == null || this.a.item.a.m <= 0 || TextUtils.isEmpty(this.a.item.a.n)) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "3");
                        i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                        o.d(this.a.getEvnetListener().getFromActivity(), this.a.item.a.g, this.a.item.a.a, this.a.item.a.b, null);
                        return;
                    }
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    try {
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.a.item.a.n, this.a.item.a.a, this.a.item.a.b}), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            imageView.setOnClickListener(anonymousClass3);
            TextView textView = (TextView) ap.a(rootView, R.id.username);
            textView.setText(this.item.a.a);
            textView.setOnClickListener(anonymousClass3);
            imageView = (ImageView) ap.a(rootView, R.id.avatar_text);
            ImageView imageView2 = (ImageView) ap.a(rootView, R.id.img_comment_topuser);
            if (this.item.a.k > 0) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            if (this.item.a.f >= 0) {
                imageView.setVisibility(0);
                imageView.setImageResource(getFanLevelIconId(this.item.a.f));
            } else {
                imageView.setVisibility(8);
            }
            if (this.item.a.h != 0) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.bookclub_comment_user_tag_author);
            }
            imageView = (ImageView) ap.a(getRootView(), R.id.avatar_admin);
            if (this.item.a.i > 0) {
                imageView.setVisibility(0);
                imageView.setImageResource(getAdminIconId(this.item.a.i - 1));
            } else {
                imageView.setVisibility(8);
            }
            if (this.item.a.k > 0) {
                imageView = (ImageView) ap.a(getRootView(), R.id.img_myz_icon);
            } else {
                imageView = (ImageView) ap.a(getRootView(), R.id.img_myz_icon);
            }
            if (this.item.a.l > 0) {
                imageView.setVisibility(0);
                imageView.setImageResource(getMYZLevelIconId(this.item.a.l - 1));
            } else {
                imageView.setVisibility(8);
                imageView.setImageDrawable(null);
            }
            ao.a(this.item.b().j, (ImageView) ap.a(getRootView(), R.id.month_icon), false);
            textView = (TextView) ap.a(rootView, R.id.publishtime);
            if (ao.s(this.item.t)) {
                textView.setText(k.c(this.item.d));
            } else {
                textView.setText(this.item.t);
            }
            textView = (TextView) ap.a(rootView, R.id.content);
            String str = "";
            Object spannableStringBuilder = new SpannableStringBuilder();
            if (this.item.p == 1) {
                spannableStringBuilder.append("回复");
                spannableStringBuilder.append(this.item.q);
                spannableStringBuilder.append(": ");
                spannableStringBuilder.append(this.item.b);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(-5002854), 2, this.item.q.length() + 2, 18);
            } else {
                spannableStringBuilder.append(this.item.b);
            }
            textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), spanBookName(spannableStringBuilder), textView.getTextSize()));
            ap.a(getRootView(), R.id.agree_clicklayout).setOnClickListener(this.mParaiseListener);
            textView = (TextView) ap.a(getRootView(), R.id.agree);
            imageView2 = (ImageView) ap.a(getRootView(), R.id.agree_tag);
            if (this.item.m <= 0) {
                charSequence = "赞";
            } else {
                charSequence = "" + j.a((long) this.item.m);
            }
            textView.setText(charSequence);
            imageView2.setOnClickListener(this.mParaiseListener);
            if (this.item.n == 0) {
                if (imageView2 != null) {
                    imageView2.setImageResource(R.drawable.bookclub_agree_press);
                }
                if (textView != null) {
                    textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textorange));
                }
            } else if (this.item.n == -1) {
                if (imageView2 != null) {
                    imageView2.setImageResource(R.drawable.bookclub_agree_nor);
                }
                if (textView != null) {
                    textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textcontent));
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doAgreeOnMainThread() {
        /*
        r8 = this;
        r6 = com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard.class;
        monitor-enter(r6);
        r0 = r8.getRootView();	 Catch:{ all -> 0x0065 }
        r1 = 2131493406; // 0x7f0c021e float:1.8610291E38 double:1.053097666E-314;
        r0 = com.qq.reader.common.utils.ap.a(r0, r1);	 Catch:{ all -> 0x0065 }
        r0 = (android.widget.TextView) r0;	 Catch:{ all -> 0x0065 }
        r1 = r8.getRootView();	 Catch:{ all -> 0x0065 }
        r2 = 2131493405; // 0x7f0c021d float:1.861029E38 double:1.0530976657E-314;
        r1 = com.qq.reader.common.utils.ap.a(r1, r2);	 Catch:{ all -> 0x0065 }
        r1 = (android.widget.ImageView) r1;	 Catch:{ all -> 0x0065 }
        r2 = "event_C177";
        r3 = 0;
        r4 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x0065 }
        com.qq.reader.common.monitor.i.a(r2, r3, r4);	 Catch:{ all -> 0x0065 }
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        if (r2 == 0) goto L_0x0036;
    L_0x002c:
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r2 = r2.f;	 Catch:{ all -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x0065 }
        if (r2 == 0) goto L_0x0038;
    L_0x0036:
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
    L_0x0037:
        return;
    L_0x0038:
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r2 = r2.n;	 Catch:{ all -> 0x0065 }
        if (r2 != 0) goto L_0x0068;
    L_0x003e:
        if (r1 == 0) goto L_0x004f;
    L_0x0040:
        r2 = r8.mUnAgreeAnimaiton;	 Catch:{ all -> 0x0065 }
        r1.startAnimation(r2);	 Catch:{ all -> 0x0065 }
        r2 = r8.mUnAgreeAnimaiton;	 Catch:{ all -> 0x0065 }
        r3 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard$4;	 Catch:{ all -> 0x0065 }
        r3.<init>(r8, r1);	 Catch:{ all -> 0x0065 }
        r2.setAnimationListener(r3);	 Catch:{ all -> 0x0065 }
    L_0x004f:
        if (r0 == 0) goto L_0x0063;
    L_0x0051:
        r1 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x0065 }
        r1 = r1.getResources();	 Catch:{ all -> 0x0065 }
        r2 = 2131427370; // 0x7f0b002a float:1.8476354E38 double:1.05306504E-314;
        r1 = r1.getColor(r2);	 Catch:{ all -> 0x0065 }
        r0.setTextColor(r1);	 Catch:{ all -> 0x0065 }
    L_0x0063:
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
        goto L_0x0037;
    L_0x0065:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0065 }
        throw r0;
    L_0x0068:
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r3 = 0;
        r2.n = r3;	 Catch:{ all -> 0x0065 }
        if (r0 == 0) goto L_0x0083;
    L_0x006f:
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r3 = r2.m;	 Catch:{ all -> 0x0065 }
        r3 = r3 + 1;
        r2.m = r3;	 Catch:{ all -> 0x0065 }
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r2 = r2.m;	 Catch:{ all -> 0x0065 }
        if (r2 > 0) goto L_0x00cc;
    L_0x007d:
        r2 = "赞";
    L_0x0080:
        r0.setText(r2);	 Catch:{ all -> 0x0065 }
    L_0x0083:
        if (r1 == 0) goto L_0x009a;
    L_0x0085:
        r2 = 2130837727; // 0x7f0200df float:1.7280416E38 double:1.052773718E-314;
        r1.setImageResource(r2);	 Catch:{ all -> 0x0065 }
        r2 = r8.mAgreeAnimaiton;	 Catch:{ all -> 0x0065 }
        r1.startAnimation(r2);	 Catch:{ all -> 0x0065 }
        r2 = r8.mAgreeAnimaiton;	 Catch:{ all -> 0x0065 }
        r3 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard$5;	 Catch:{ all -> 0x0065 }
        r3.<init>(r8, r1);	 Catch:{ all -> 0x0065 }
        r2.setAnimationListener(r3);	 Catch:{ all -> 0x0065 }
    L_0x009a:
        if (r0 == 0) goto L_0x00ae;
    L_0x009c:
        r1 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x0065 }
        r1 = r1.getResources();	 Catch:{ all -> 0x0065 }
        r2 = 2131427370; // 0x7f0b002a float:1.8476354E38 double:1.05306504E-314;
        r1 = r1.getColor(r2);	 Catch:{ all -> 0x0065 }
        r0.setTextColor(r1);	 Catch:{ all -> 0x0065 }
    L_0x00ae:
        r7 = com.qq.reader.common.readertask.g.a();	 Catch:{ all -> 0x0065 }
        r0 = new com.qq.reader.common.readertask.protocol.ChapterEndParaiseTask;	 Catch:{ all -> 0x0065 }
        r1 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard$6;	 Catch:{ all -> 0x0065 }
        r1.<init>(r8);	 Catch:{ all -> 0x0065 }
        r2 = r8.item;	 Catch:{ all -> 0x0065 }
        r2 = r2.i;	 Catch:{ all -> 0x0065 }
        r4 = r8.item;	 Catch:{ all -> 0x0065 }
        r4 = r4.f;	 Catch:{ all -> 0x0065 }
        r5 = r8.getCtype();	 Catch:{ all -> 0x0065 }
        r0.<init>(r1, r2, r4, r5);	 Catch:{ all -> 0x0065 }
        r7.a(r0);	 Catch:{ all -> 0x0065 }
        goto L_0x0063;
    L_0x00cc:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0065 }
        r2.<init>();	 Catch:{ all -> 0x0065 }
        r3 = "";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0065 }
        r3 = r8.item;	 Catch:{ all -> 0x0065 }
        r3 = r3.m;	 Catch:{ all -> 0x0065 }
        r4 = (long) r3;	 Catch:{ all -> 0x0065 }
        r3 = com.qq.reader.common.utils.j.a(r4);	 Catch:{ all -> 0x0065 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0065 }
        r2 = r2.toString();	 Catch:{ all -> 0x0065 }
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard.doAgreeOnMainThread():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doUnAgreeOnMainThread() {
        /*
        r6 = this;
        r2 = com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard.class;
        monitor-enter(r2);
        r0 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x0082 }
        r1 = "点赞失败";
        r3 = 0;
        r0 = com.qq.reader.view.af.a(r0, r1, r3);	 Catch:{ all -> 0x0082 }
        r0.a();	 Catch:{ all -> 0x0082 }
        r0 = r6.item;	 Catch:{ all -> 0x0082 }
        if (r0 == 0) goto L_0x0020;
    L_0x0016:
        r0 = r6.item;	 Catch:{ all -> 0x0082 }
        r0 = r0.f;	 Catch:{ all -> 0x0082 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0082 }
        if (r0 == 0) goto L_0x0022;
    L_0x0020:
        monitor-exit(r2);	 Catch:{ all -> 0x0082 }
    L_0x0021:
        return;
    L_0x0022:
        r0 = r6.getRootView();	 Catch:{ all -> 0x0082 }
        r1 = 2131493406; // 0x7f0c021e float:1.8610291E38 double:1.053097666E-314;
        r0 = com.qq.reader.common.utils.ap.a(r0, r1);	 Catch:{ all -> 0x0082 }
        r0 = (android.widget.TextView) r0;	 Catch:{ all -> 0x0082 }
        r1 = r6.item;	 Catch:{ all -> 0x0082 }
        r1 = r1.n;	 Catch:{ all -> 0x0082 }
        if (r1 != 0) goto L_0x0052;
    L_0x0035:
        if (r0 == 0) goto L_0x0052;
    L_0x0037:
        r1 = r6.item;	 Catch:{ all -> 0x0082 }
        r1 = r1.m;	 Catch:{ all -> 0x0082 }
        r3 = 1;
        if (r1 <= r3) goto L_0x0052;
    L_0x003e:
        r1 = r6.item;	 Catch:{ all -> 0x0082 }
        r3 = r1.m;	 Catch:{ all -> 0x0082 }
        r3 = r3 + -1;
        r1.m = r3;	 Catch:{ all -> 0x0082 }
        r1 = r6.item;	 Catch:{ all -> 0x0082 }
        r1 = r1.m;	 Catch:{ all -> 0x0082 }
        if (r1 > 0) goto L_0x0085;
    L_0x004c:
        r1 = "赞";
    L_0x004f:
        r0.setText(r1);	 Catch:{ all -> 0x0082 }
    L_0x0052:
        r1 = r6.getRootView();	 Catch:{ all -> 0x0082 }
        r3 = 2131493405; // 0x7f0c021d float:1.861029E38 double:1.0530976657E-314;
        r1 = com.qq.reader.common.utils.ap.a(r1, r3);	 Catch:{ all -> 0x0082 }
        r1 = (android.widget.ImageView) r1;	 Catch:{ all -> 0x0082 }
        if (r1 == 0) goto L_0x0067;
    L_0x0061:
        r3 = 2130837726; // 0x7f0200de float:1.7280414E38 double:1.0527737173E-314;
        r1.setImageResource(r3);	 Catch:{ all -> 0x0082 }
    L_0x0067:
        if (r0 == 0) goto L_0x007b;
    L_0x0069:
        r1 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x0082 }
        r1 = r1.getResources();	 Catch:{ all -> 0x0082 }
        r3 = 2131427369; // 0x7f0b0029 float:1.8476352E38 double:1.0530650396E-314;
        r1 = r1.getColor(r3);	 Catch:{ all -> 0x0082 }
        r0.setTextColor(r1);	 Catch:{ all -> 0x0082 }
    L_0x007b:
        r0 = r6.item;	 Catch:{ all -> 0x0082 }
        r1 = -1;
        r0.n = r1;	 Catch:{ all -> 0x0082 }
        monitor-exit(r2);	 Catch:{ all -> 0x0082 }
        goto L_0x0021;
    L_0x0082:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0082 }
        throw r0;
    L_0x0085:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0082 }
        r1.<init>();	 Catch:{ all -> 0x0082 }
        r3 = "";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0082 }
        r3 = r6.item;	 Catch:{ all -> 0x0082 }
        r3 = r3.m;	 Catch:{ all -> 0x0082 }
        r4 = (long) r3;	 Catch:{ all -> 0x0082 }
        r3 = com.qq.reader.common.utils.j.a(r4);	 Catch:{ all -> 0x0082 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0082 }
        r1 = r1.toString();	 Catch:{ all -> 0x0082 }
        goto L_0x004f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard.doUnAgreeOnMainThread():void");
    }

    public boolean equals(Object obj) {
        if (super.equals(obj) && getUILevel() == ((BookClubChapterEndCard) obj).getUILevel()) {
            return true;
        }
        return false;
    }
}
