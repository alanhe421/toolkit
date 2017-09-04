package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ah;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.ReaderRatingBar;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.item.ag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.model.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class BookClubTopicCard extends BaseCommentCard {
    public static final int STATE_DETAILPAGE = 1002;
    public static final int STATE_REWARDMORE_CARD = 1003;
    public static final int STATE_TOPPAGE = 1001;
    public static final String[] mRatingTextArray = ReaderApplication.getApplicationImp().getResources().getStringArray(R.array.rating_score_intro);
    public ag contentUserNode;
    private byte[] lock = new byte[0];
    public int mAgree;
    private Animation mAgreeAnimaiton;
    public int mAgreeStatus = -1;
    public int mAuthortag;
    public int mBetter;
    public long mBookid;
    public String mContent;
    private OnClickListener mContentListener;
    public String mId;
    public ArrayList<a> mImgList = new ArrayList();
    private boolean mIsMaxReward = false;
    public boolean mIsReward = false;
    private LayoutInflater mLayoutInflater;
    private OnClickListener mParaiseListener;
    private OnClickListener mReplyListener;
    public int mReplycount;
    public int mReward;
    public float mScore;
    public String mScore_intro;
    private int mState;
    public int mStatus;
    public String mTitle;
    private Animation mUnAgreeAnimaiton;
    public long mcreatetime;
    public long mlastreplytime;
    public int mtype;
    public String platformname = "";
    public int subtype;

    public BookClubTopicCard(b bVar, String str, int i, int i2) {
        super(bVar, str, i2);
        this.mState = i;
        this.mLayoutInflater = (LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater");
        this.mAgreeAnimaiton = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.agreescale_out);
        this.mUnAgreeAnimaiton = AnimationUtils.loadAnimation(ReaderApplication.getApplicationImp(), R.anim.hasagree_shake);
        this.mContentListener = new OnClickListener(this) {
            final /* synthetic */ BookClubTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mId == null || this.a.mId.length() <= 0) {
                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getResources().getString(R.string.comment_sending), 0).a();
                    return;
                }
                Bundle bundle = new Bundle();
                c cVar = new c(bundle);
                bundle.putLong("URL_BUILD_PERE_BOOK_ID", this.a.mBookid);
                bundle.putString("COMMENT_ID", this.a.mId);
                bundle.putString("PARA_TYPE_COMMENT_UID", this.a.contentUserNode.g);
                bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
                bundle.putBoolean("newactivitywithresult", true);
                bundle.putInt("function_type", 0);
                bundle.putInt("CTYPE", this.a.mtype);
                bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubdetail));
                if (this.a.mBookid < 4) {
                    bundle.putInt("CTYPE", 4);
                }
                bundle.putBoolean("ISMAXREWARD", this.a.mIsMaxReward);
                cVar.a(this.a.getEvnetListener());
            }
        };
        this.mParaiseListener = new OnClickListener(this) {
            final /* synthetic */ BookClubTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.isLogin()) {
                    this.a.doAgreeOnMainThread();
                } else if (this.a.getEvnetListener() != null) {
                    ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.a.getEvnetListener().getFromActivity();
                    if (readerBaseActivity != null) {
                        readerBaseActivity.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass2 a;

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
        this.mReplyListener = new OnClickListener(this) {
            final /* synthetic */ BookClubTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        };
    }

    public void setIsMaxReward(boolean z) {
        this.mIsMaxReward = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doAgreeOnMainThread() {
        /*
        r8 = this;
        r6 = r8.lock;
        monitor-enter(r6);
        r0 = r8.getRootView();	 Catch:{ all -> 0x005d }
        r1 = 2131493406; // 0x7f0c021e float:1.8610291E38 double:1.053097666E-314;
        r0 = com.qq.reader.common.utils.ap.a(r0, r1);	 Catch:{ all -> 0x005d }
        r0 = (android.widget.TextView) r0;	 Catch:{ all -> 0x005d }
        r1 = r8.getRootView();	 Catch:{ all -> 0x005d }
        r2 = 2131493405; // 0x7f0c021d float:1.861029E38 double:1.0530976657E-314;
        r1 = com.qq.reader.common.utils.ap.a(r1, r2);	 Catch:{ all -> 0x005d }
        r1 = (android.widget.ImageView) r1;	 Catch:{ all -> 0x005d }
        r2 = "event_C177";
        r3 = 0;
        r4 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x005d }
        com.qq.reader.common.monitor.i.a(r2, r3, r4);	 Catch:{ all -> 0x005d }
        r2 = r8.mId;	 Catch:{ all -> 0x005d }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x005d }
        if (r2 == 0) goto L_0x0032;
    L_0x0030:
        monitor-exit(r6);	 Catch:{ all -> 0x005d }
    L_0x0031:
        return;
    L_0x0032:
        r2 = r8.mAgreeStatus;	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x0060;
    L_0x0036:
        if (r1 == 0) goto L_0x0047;
    L_0x0038:
        r2 = r8.mUnAgreeAnimaiton;	 Catch:{ all -> 0x005d }
        r1.startAnimation(r2);	 Catch:{ all -> 0x005d }
        r2 = r8.mUnAgreeAnimaiton;	 Catch:{ all -> 0x005d }
        r3 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard$4;	 Catch:{ all -> 0x005d }
        r3.<init>(r8, r1);	 Catch:{ all -> 0x005d }
        r2.setAnimationListener(r3);	 Catch:{ all -> 0x005d }
    L_0x0047:
        if (r0 == 0) goto L_0x005b;
    L_0x0049:
        r1 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x005d }
        r1 = r1.getResources();	 Catch:{ all -> 0x005d }
        r2 = 2131427370; // 0x7f0b002a float:1.8476354E38 double:1.05306504E-314;
        r1 = r1.getColor(r2);	 Catch:{ all -> 0x005d }
        r0.setTextColor(r1);	 Catch:{ all -> 0x005d }
    L_0x005b:
        monitor-exit(r6);	 Catch:{ all -> 0x005d }
        goto L_0x0031;
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x005d }
        throw r0;
    L_0x0060:
        r2 = 0;
        r8.mAgreeStatus = r2;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0075;
    L_0x0065:
        r2 = r8.mAgree;	 Catch:{ all -> 0x005d }
        r2 = r2 + 1;
        r8.mAgree = r2;	 Catch:{ all -> 0x005d }
        r2 = r8.mAgree;	 Catch:{ all -> 0x005d }
        if (r2 > 0) goto L_0x00e8;
    L_0x006f:
        r2 = "赞";
    L_0x0072:
        r0.setText(r2);	 Catch:{ all -> 0x005d }
    L_0x0075:
        if (r1 == 0) goto L_0x008c;
    L_0x0077:
        r2 = 2130837727; // 0x7f0200df float:1.7280416E38 double:1.052773718E-314;
        r1.setImageResource(r2);	 Catch:{ all -> 0x005d }
        r2 = r8.mAgreeAnimaiton;	 Catch:{ all -> 0x005d }
        r1.startAnimation(r2);	 Catch:{ all -> 0x005d }
        r2 = r8.mAgreeAnimaiton;	 Catch:{ all -> 0x005d }
        r3 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard$5;	 Catch:{ all -> 0x005d }
        r3.<init>(r8, r1);	 Catch:{ all -> 0x005d }
        r2.setAnimationListener(r3);	 Catch:{ all -> 0x005d }
    L_0x008c:
        if (r0 == 0) goto L_0x00a0;
    L_0x008e:
        r1 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ all -> 0x005d }
        r1 = r1.getResources();	 Catch:{ all -> 0x005d }
        r2 = 2131427370; // 0x7f0b002a float:1.8476354E38 double:1.05306504E-314;
        r1 = r1.getColor(r2);	 Catch:{ all -> 0x005d }
        r0.setTextColor(r1);	 Catch:{ all -> 0x005d }
    L_0x00a0:
        r7 = com.qq.reader.common.readertask.g.a();	 Catch:{ all -> 0x005d }
        r0 = new com.qq.reader.common.readertask.protocol.ParaiseTask;	 Catch:{ all -> 0x005d }
        r1 = new com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard$6;	 Catch:{ all -> 0x005d }
        r1.<init>(r8);	 Catch:{ all -> 0x005d }
        r2 = r8.mBookid;	 Catch:{ all -> 0x005d }
        r4 = r8.mId;	 Catch:{ all -> 0x005d }
        r5 = r8.getCtype();	 Catch:{ all -> 0x005d }
        r0.<init>(r1, r2, r4, r5);	 Catch:{ all -> 0x005d }
        r7.a(r0);	 Catch:{ all -> 0x005d }
        r0 = r8.mState;	 Catch:{ all -> 0x005d }
        r1 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        if (r0 != r1) goto L_0x005b;
    L_0x00bf:
        r0 = new android.os.Bundle;	 Catch:{ all -> 0x005d }
        r0.<init>();	 Catch:{ all -> 0x005d }
        r1 = new com.qq.reader.module.bookstore.qnative.c;	 Catch:{ all -> 0x005d }
        r1.<init>(r0);	 Catch:{ all -> 0x005d }
        r2 = "REPLY_STATUS";
        r3 = 5;
        r0.putInt(r2, r3);	 Catch:{ all -> 0x005d }
        r2 = "REPLY_FROM";
        r3 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        r0.putInt(r2, r3);	 Catch:{ all -> 0x005d }
        r2 = "function_type";
        r3 = 4;
        r0.putInt(r2, r3);	 Catch:{ all -> 0x005d }
        r0 = r8.getEvnetListener();	 Catch:{ all -> 0x005d }
        r1.a(r0);	 Catch:{ all -> 0x005d }
        goto L_0x005b;
    L_0x00e8:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r2.<init>();	 Catch:{ all -> 0x005d }
        r3 = "";
        r2 = r2.append(r3);	 Catch:{ all -> 0x005d }
        r3 = r8.mAgree;	 Catch:{ all -> 0x005d }
        r4 = (long) r3;	 Catch:{ all -> 0x005d }
        r3 = com.qq.reader.common.utils.j.a(r4);	 Catch:{ all -> 0x005d }
        r2 = r2.append(r3);	 Catch:{ all -> 0x005d }
        r2 = r2.toString();	 Catch:{ all -> 0x005d }
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard.doAgreeOnMainThread():void");
    }

    public void doUnAgreeOnMainThread() {
        synchronized (this.lock) {
            af.a(ReaderApplication.getApplicationImp(), (CharSequence) "点赞失败", 0).a();
            if (TextUtils.isEmpty(this.mId)) {
                return;
            }
            TextView textView = (TextView) ap.a(getRootView(), R.id.agree);
            if (this.mAgreeStatus == 0 && textView != null && this.mAgree > 1) {
                this.mAgree--;
                textView.setText(this.mAgree <= 0 ? "赞" : "" + j.a((long) this.mAgree));
            }
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.agree_tag);
            if (imageView != null) {
                imageView.setImageResource(R.drawable.bookclub_agree_nor);
            }
            if (textView != null) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textlightgray));
            }
            this.mAgreeStatus = -1;
        }
    }

    public int getResLayoutId() {
        return R.layout.bookclubitem;
    }

    public String getCommentId() {
        return this.mId;
    }

    public void addReplyCount() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.reply);
        if (textView != null) {
            this.mReplycount++;
            textView.setText("回复 " + j.a((long) this.mReplycount));
        }
    }

    public void addAgreeCount() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.agree);
        if (textView != null) {
            this.mAgree++;
            textView.setText("" + j.a((long) this.mAgree));
        }
    }

    protected boolean parseData(JSONObject jSONObject) {
        this.contentUserNode = new ag(jSONObject.optJSONObject("user"));
        this.platformname = jSONObject.optString("platformname");
        this.mtype = jSONObject.optInt("type");
        this.subtype = jSONObject.optInt("subtype");
        this.mTitle = jSONObject.optString("title");
        try {
            this.mTitle = Html.fromHtml(this.mTitle).toString();
        } catch (Exception e) {
        }
        this.mContent = jSONObject.optString(MessageKey.MSG_CONTENT);
        try {
            this.mContent = Html.fromHtml(this.mContent).toString();
            ah ahVar = new ah();
            Iterable<ah.b> a = ah.a(this.mContent);
            StringBuilder stringBuilder = new StringBuilder();
            this.mImgList.clear();
            for (ah.b bVar : a) {
                switch (bVar.f) {
                    case 0:
                        stringBuilder.append(bVar.d);
                        break;
                    case 1:
                        a aVar = new a();
                        aVar.a = bVar.d;
                        aVar.b = ao.u(bVar.d);
                        this.mImgList.add(aVar);
                        break;
                    default:
                        break;
                }
            }
            this.mContent = stringBuilder.toString();
        } catch (Exception e2) {
        }
        this.mBetter = jSONObject.optInt("better");
        this.mAuthortag = jSONObject.optInt("authortag");
        this.mAgree = jSONObject.optInt("agree");
        this.mReplycount = jSONObject.optInt("replycount");
        this.mId = jSONObject.optString("commentid");
        this.mBookid = jSONObject.optLong("bid");
        this.mcreatetime = jSONObject.optLong("createtime");
        this.mlastreplytime = jSONObject.optLong("lastreplytime");
        this.mStatus = jSONObject.optInt("status");
        this.mAgreeStatus = jSONObject.optInt("agreestatus");
        if (jSONObject.has("reward")) {
            this.mReward = jSONObject.optInt("reward");
            if (this.mReward > 0) {
                this.mIsReward = true;
            } else {
                this.mIsReward = false;
            }
        } else {
            this.mIsReward = false;
            this.mReward = 0;
        }
        setCardId(this.mId);
        JSONObject optJSONObject = jSONObject.optJSONObject("scoreInfo");
        if (optJSONObject != null) {
            try {
                this.mScore = Float.valueOf(optJSONObject.optString("score", "0")).floatValue();
            } catch (Exception e3) {
                com.qq.reader.common.monitor.debug.c.e("BookClubTopicCard", e3.getMessage());
            }
            this.mScore_intro = optJSONObject.optString("intro");
        } else {
            this.mScore = -1.0f;
        }
        return true;
    }

    public void addFakeReply(String str, String str2, String str3, String str4, String str5, int i) {
        if (this.mState == 1001) {
            com.qq.reader.module.bookstore.qnative.item.af afVar = new com.qq.reader.module.bookstore.qnative.item.af();
            afVar.a = new ag();
            com.qq.reader.common.login.b.a loginUser = getLoginUser();
            afVar.b = str;
            afVar.d = System.currentTimeMillis();
            afVar.a.a = loginUser.a();
            afVar.h = str3;
            afVar.f = str5;
            afVar.g = i;
            addReplyCount();
        }
    }

    private String getUserLevel() {
        return "";
    }

    public void attachView() {
        CharSequence charSequence;
        int i;
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.avatar_img_mask);
        setAvatarImage((UserCircleImageView) ap.a(getRootView(), R.id.avatar_img), this.contentUserNode.b, this.contentUserNode.n, null);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookClubTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.contentUserNode == null || this.a.contentUserNode.m <= 0 || TextUtils.isEmpty(this.a.contentUserNode.n)) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "3");
                    i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                    o.d(this.a.getEvnetListener().getFromActivity(), this.a.contentUserNode.g, this.a.contentUserNode.a, this.a.contentUserNode.b, null);
                    return;
                }
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.a.contentUserNode.n, this.a.contentUserNode.a, this.a.contentUserNode.b}), null);
                    hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.avatar_text);
        imageView = (ImageView) ap.a(getRootView(), R.id.avatar_admin);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.img_comment_topuser);
        if (this.contentUserNode.k > 0) {
            imageView3.setVisibility(0);
        } else {
            imageView3.setVisibility(8);
        }
        if (this.contentUserNode.f >= 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(getFanLevelIconId(this.contentUserNode.f));
        } else {
            imageView2.setVisibility(8);
        }
        if (this.contentUserNode.h != 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(R.drawable.bookclub_comment_user_tag_author);
        }
        if (this.contentUserNode.i > 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(getAdminIconId(this.contentUserNode.i - 1));
        } else {
            imageView.setVisibility(8);
        }
        imageView2 = (ImageView) ap.a(getRootView(), R.id.img_myz_icon);
        if (this.contentUserNode.l > 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(getMYZLevelIconId(this.contentUserNode.l - 1));
        } else {
            imageView2.setVisibility(8);
            imageView2.setImageDrawable(null);
        }
        TextView textView = (TextView) ap.a(getRootView(), R.id.username);
        textView.setText(this.contentUserNode.a);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookClubTopicCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.contentUserNode == null || this.a.contentUserNode.m <= 0 || TextUtils.isEmpty(this.a.contentUserNode.n)) {
                    o.d(this.a.getEvnetListener().getFromActivity(), this.a.contentUserNode.g, this.a.contentUserNode.a, this.a.contentUserNode.b, null);
                    return;
                }
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.a.contentUserNode.n, this.a.contentUserNode.a, this.a.contentUserNode.b}), null);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        textView = (TextView) ap.a(getRootView(), R.id.publishtime);
        if (this.mState == 1002) {
            textView.setText(k.c(this.mcreatetime));
        } else if (this.mlastreplytime > 0) {
            textView.setText(k.c(this.mlastreplytime));
        } else {
            textView.setText(k.c(this.mcreatetime));
        }
        textView = (TextView) ap.a(getRootView(), R.id.title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.content);
        View a = ap.a(getRootView(), R.id.title_container);
        if (TextUtils.isEmpty(this.mTitle)) {
            a.setVisibility(8);
        } else {
            if (this.mIsMaxReward) {
                CharSequence stringBuilder = new StringBuilder();
                stringBuilder.append("打赏").append(this.mReward).append("书币");
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textreb));
                textView.setText(stringBuilder);
            } else {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textblack));
                textView.setText(this.mTitle);
            }
            a.setVisibility(0);
        }
        textView2.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), Html.fromHtml(this.mContent), textView2.getTextSize()));
        textView = (TextView) ap.a(getRootView(), R.id.agree);
        imageView = (ImageView) ap.a(getRootView(), R.id.agree_tag);
        if (this.mAgree <= 0) {
            charSequence = "赞";
        } else {
            charSequence = "" + j.a((long) this.mAgree);
        }
        textView.setText(charSequence);
        ap.a(getRootView(), R.id.agree_clicklayout).setOnClickListener(this.mParaiseListener);
        ((TextView) ap.a(getRootView(), R.id.reply)).setText("回复 " + j.a((long) this.mReplycount));
        imageView3 = (ImageView) ap.a(getRootView(), R.id.bookclub_author_comment_tag);
        if (this.mAuthortag == 1) {
            imageView3.setVisibility(0);
        } else {
            imageView3.setVisibility(8);
        }
        imageView3 = (ImageView) ap.a(getRootView(), R.id.bookclub_theselected_comment_tag);
        if (this.mBetter == 1) {
            imageView3.setVisibility(0);
        } else {
            imageView3.setVisibility(8);
        }
        if (this.mState == 1001 || this.mState == 1003) {
            getRootView().setOnClickListener(this.mContentListener);
        } else {
            Bundle bundle = new Bundle();
            c cVar = new c(bundle);
            bundle.putInt("REPLY_FROM", 1001);
            bundle.putInt("function_type", 4);
            cVar.a(getEvnetListener());
            if (this.mAgreeStatus == 0) {
                bundle.putInt(BookClubReplyCard.REPLY_STATUS, 5);
            } else if (this.mAgreeStatus == -1) {
                bundle.putInt(BookClubReplyCard.REPLY_STATUS, 6);
            }
            cVar.a(getEvnetListener());
            getRootView().setBackgroundDrawable(null);
        }
        imageView.setOnClickListener(this.mParaiseListener);
        if (this.mAgreeStatus == 0) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.bookclub_agree_press);
            }
            if (textView != null) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textorange));
            }
        } else if (this.mAgreeStatus == -1) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.bookclub_agree_nor);
            }
            if (textView != null) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.bookclub_textcontent));
            }
        }
        imageView2 = (ImageView) ap.a(getRootView(), R.id.title_tag);
        if (this.mIsReward) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        View a2 = ap.a(getRootView(), R.id.title_tag_2);
        if (this.mState == 1003) {
            i = 0;
        } else {
            i = 8;
        }
        a2.setVisibility(i);
        ((TextView) ap.a(getRootView(), R.id.platformname)).setText("来自" + this.platformname);
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_pic_container);
        linearLayout.setVisibility(8);
        if (this.mImgList != null && this.mImgList.size() > 0) {
            linearLayout.setVisibility(0);
            int[] iArr = new int[]{R.id.img_pic1, R.id.img_pic2, R.id.img_pic3};
            for (int a3 : iArr) {
                imageView2 = (ImageView) ap.a(getRootView(), a3);
                imageView2.setImageDrawable(null);
                imageView2.setVisibility(4);
            }
            int a32 = 0;
            while (a32 < this.mImgList.size() && a32 < iArr.length) {
                imageView2 = (ImageView) ap.a(getRootView(), iArr[a32]);
                imageView2.setVisibility(0);
                com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(((a) this.mImgList.get(a32)).b, imageView2, com.qq.reader.common.imageloader.a.a().a((int) R.drawable.comment_pic_default_bg));
                a32++;
            }
        }
        View a4 = ap.a(getRootView(), R.id.rating_container);
        if (this.mScore < 1.0f) {
            a4.setVisibility(8);
        } else {
            a4.setVisibility(0);
            ReaderRatingBar readerRatingBar = (ReaderRatingBar) ap.a(getRootView(), R.id.bookclub_ratingbar);
            readerRatingBar.setRatingText((TextView) ap.a(getRootView(), R.id.bookclub_ratingbar_text), mRatingTextArray);
            readerRatingBar.setRating(this.mScore);
        }
        ao.a(this.contentUserNode.j, (ImageView) ap.a(getRootView(), R.id.month_icon), false);
    }
}
