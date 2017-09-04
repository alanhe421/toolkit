package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.activity.NativeNewTabTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.n;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserCenterCommentCard extends a {
    private final int INTRO_MAX_LINES = 4;
    private final int MAX_COMMENT = 3;
    private String authorId;
    private int mBookCount = 0;
    private int mCommentCount = 0;
    private boolean mDataIsReady = false;
    private int mHowWeek = 0;
    private int mInterActionCount = 0;
    private int mIsOwn;
    private int mPriStatus = 0;
    private int mReplyCount = 0;
    private int mTotalCommentCount = 0;
    private String mUserId;

    public UserCenterCommentCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_comment_card_layout;
    }

    public void attachView() {
        int size = getItemList().size();
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title);
        if (this.mIsOwn == 1) {
            cardTitle.setCardTitle(37, "我的评论", this.mTotalCommentCount + "条", null);
        } else {
            cardTitle.setCardTitle(37, "TA的评论", this.mTotalCommentCount + "条", null);
        }
        if (size <= 0) {
            getRootView().setVisibility(8);
        } else {
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.comment_container);
            linearLayout.removeAllViews();
            for (int i = 0; i < 3; i++) {
                n nVar;
                if (i < size) {
                    nVar = (n) getItemList().get(i);
                } else {
                    nVar = null;
                }
                showCommentAndReply(linearLayout, nVar, getRootView());
            }
            TextView textView = (TextView) ap.a(getRootView(), R.id.comment_0_more);
            textView.setVisibility(0);
            textView.setText("查看全部评论");
            if (this.mTotalCommentCount <= 3) {
                textView.setVisibility(8);
            } else {
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ UserCenterCommentCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Map hashMap = new HashMap();
                        hashMap.put("isOwn", this.a.mIsOwn + "");
                        i.a("event_D132", hashMap, ReaderApplication.getApplicationImp());
                        Intent intent = new Intent(this.a.getEvnetListener().getFromActivity(), NativeNewTabTwoLevelActivity.class);
                        if (this.a.mCommentCount > 0) {
                            intent.putExtra("KEY_ACTIONTAG", "0");
                        } else if (this.a.mReplyCount > 0) {
                            intent.putExtra("KEY_ACTIONTAG", "1");
                        }
                        intent.putExtra("userId", this.a.mUserId);
                        intent.putExtra("KEY_JUMP_PAGENAME", "user_center_more_comment");
                        this.a.getEvnetListener().getFromActivity().startActivity(intent);
                    }
                });
            }
        }
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        a.setVisibility(8);
        if ((this.mPriStatus == 0 || this.mIsOwn == 1) && this.mTotalCommentCount > 0 && this.mInterActionCount > 0) {
            a.setVisibility(0);
        }
        Map hashMap = new HashMap();
        hashMap.put("isOwn", String.valueOf(this.mIsOwn));
        i.a("event_C282", hashMap, ReaderApplication.getApplicationImp());
    }

    public boolean isDataReady() {
        return this.mDataIsReady;
    }

    private void showCommentAndReply(LinearLayout linearLayout, n nVar, View view) {
        int i;
        int i2 = nVar.y;
        if (i2 > 0) {
            i = R.layout.message_interaction_layout;
        } else {
            i = R.layout.comment_card_1_item;
        }
        View inflate = View.inflate(ReaderApplication.getApplicationImp(), i, null);
        if (nVar != null) {
            ao.a(nVar.b().j, (ImageView) ap.a(inflate, R.id.month_icon), false);
            if (i2 > 0) {
                showReply(inflate, nVar);
            } else {
                showComment(inflate, nVar);
            }
            linearLayout.addView(inflate, new LayoutParams(-1, -2));
        }
    }

    protected void showReply(View view, final n nVar) {
        if (nVar != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ UserCenterCommentCard b;

                public void onClick(View view) {
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), nVar.w, null);
                        Map hashMap = new HashMap();
                        hashMap.put("isOwn", this.b.mIsOwn + "");
                        i.a("event_D131", hashMap, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ((TextView) ap.a(view, R.id.source)).setText(nVar.x);
            ((TextView) ap.a(view, R.id.time)).setText(k.c(nVar.d));
            if (nVar.a != null) {
                setAvatarImage((ImageView) ap.a(view, R.id.icon), nVar.a.b, nVar.a.n, null);
                ((TextView) ap.a(view, R.id.title)).setText(nVar.a.a);
                View a = ap.a(view, R.id.img_author_tag);
                if (nVar.a.h != 0) {
                    a.setVisibility(0);
                } else {
                    a.setVisibility(8);
                }
            }
            TextView textView = (TextView) ap.a(view, R.id.content);
            textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), nVar.b, textView.getTextSize()));
            textView = (TextView) ap.a(view, R.id.referred_text);
            if (TextUtils.isEmpty(nVar.v)) {
                textView.setText("很抱歉，内容不存在或已删除");
            } else {
                nVar.v = ao.a(nVar.v);
                textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), nVar.v, textView.getTextSize()));
            }
            ap.a(view, R.id.localstore_adv_divider).setVisibility(8);
            ap.a(view, R.id.localstore_bottom_divider).setVisibility(0);
        }
    }

    protected void showComment(View view, final n nVar) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterCommentCard b;

            public void onClick(View view) {
                try {
                    c.a(this.b.getEvnetListener().getFromActivity(), nVar.w, null);
                    Map hashMap = new HashMap();
                    hashMap.put("isOwn", this.b.mIsOwn + "");
                    i.a("event_D131", hashMap, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setAvatarImage((ImageView) ap.a(view, R.id.avatar_img), nVar.a.b, nVar.a.n, null);
        ((ImageView) ap.a(view, R.id.avatar_img_mask)).setImageResource(R.drawable.translucent);
        ((TextView) ap.a(view, R.id.tv_comment_publish_time)).setText(k.c(nVar.d));
        ((TextView) ap.a(view, R.id.tv_reply_source)).setText(nVar.x);
        ImageView imageView = (ImageView) ap.a(view, R.id.img_author_footprint);
        if (nVar.e()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        imageView = (ImageView) ap.a(view, R.id.img_excellent_comment);
        if (nVar.d()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        imageView = (ImageView) ap.a(view, R.id.avatar_text);
        ImageView imageView2 = (ImageView) ap.a(view, R.id.avatar_admin);
        ImageView imageView3 = (ImageView) ap.a(view, R.id.img_comment_topuser);
        if (nVar.a.k > 0) {
            imageView3.setVisibility(0);
        } else {
            imageView3.setVisibility(8);
        }
        if (nVar.a.f >= 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(getFanLevelIconId(nVar.a.f));
        } else {
            imageView.setVisibility(8);
        }
        if (nVar.a.h != 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.bookclub_comment_user_tag_author);
        }
        if (nVar.a.i > 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(getAdminIconId(nVar.a.i - 1));
        } else {
            imageView2.setVisibility(8);
        }
        imageView = (ImageView) ap.a(view, R.id.img_myz_icon);
        if (nVar.a.l > 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(getMYZLevelIconId(nVar.a.l - 1));
        } else {
            imageView.setVisibility(8);
            imageView.setImageDrawable(null);
        }
        ((TextView) ap.a(view, R.id.username)).setText(nVar.a.a);
        TextView textView = (TextView) ap.a(view, R.id.title);
        final TextView textView2 = (TextView) ap.a(view, R.id.content);
        if (TextUtils.isEmpty(nVar.c)) {
            textView.setVisibility(8);
            textView2.setMaxLines(5);
        } else {
            textView.setText(nVar.c);
            textView.setVisibility(0);
            textView2.setMaxLines(4);
        }
        if (!TextUtils.isEmpty(nVar.b)) {
            nVar.b = ao.a(nVar.b);
        }
        textView2.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), nVar.b, textView2.getTextSize()));
        if (com.qq.reader.common.c.a.w) {
            textView2.setMaxLines(Integer.MAX_VALUE);
            textView2.setEllipsize(null);
            textView2.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ UserCenterCommentCard b;

                public void onGlobalLayout() {
                    try {
                        if (textView2.getLineCount() > 4) {
                            textView2.setMaxLines(4);
                            textView2.setText(textView2.getText().toString().substring(textView2.getLayout().getLineStart(0), textView2.getLayout().getLineEnd(3) - 10) + "...");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ((TextView) ap.a(view, R.id.agree_text)).setText(s.countTransform2((long) nVar.m));
        ((TextView) ap.a(view, R.id.reply_text)).setText(s.countTransform2((long) nVar.l));
        View a = ap.a(view, R.id.rating_container);
        if (nVar.g() < 1.0f) {
            a.setVisibility(8);
            return;
        }
        a.setVisibility(0);
        ((RatingBar) ap.a(view, R.id.bookclub_ratingbar)).setRating(nVar.g());
        ((TextView) ap.a(view, R.id.bookclub_ratingbar_text)).setText(nVar.f());
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        if (jSONObject != null) {
            this.mBookCount = jSONObject.optInt("shelfCount");
            this.mTotalCommentCount = jSONObject.optInt("totalCount");
            this.mInterActionCount = jSONObject.optInt("contentCount");
            this.mCommentCount = jSONObject.optInt("commentCount");
            this.mReplyCount = jSONObject.optInt("replyCount");
            this.mUserId = jSONObject.optString("userId");
            this.mIsOwn = jSONObject.optInt("isOwn");
            this.mPriStatus = jSONObject.optInt("priStatus");
            if (this.mTotalCommentCount <= 0) {
                return false;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("commentList");
            if (optJSONArray == null) {
                this.mDataIsReady = false;
            } else if (optJSONArray.length() <= 0) {
                return false;
            } else {
                this.mDataIsReady = true;
                while (i < optJSONArray.length()) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s nVar = new n();
                    nVar.parseData(jSONObject2);
                    addItem(nVar);
                    i++;
                }
            }
        }
        return true;
    }
}
