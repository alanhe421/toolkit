package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
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

public class CommentCard_1 extends a {
    private final int INTRO_MAX_LINES = 4;
    private final int MAX_COMMENT = 3;
    private String authorId;
    private int[] commentAndReplyLayoutResId = new int[]{R.id.rl_comment_0, R.id.rl_comment_1, R.id.rl_comment_2};
    private int isOwn;
    private int mCommentCount = 0;
    private boolean mDataIsReady = false;
    private int mHowWeek = 0;
    private int mReplyCount = 0;

    public CommentCard_1(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_comment_1;
    }

    public void attachView() {
        int size = getItemList().size();
        int i = this.mReplyCount + this.mCommentCount;
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(37, this.mShowTitle, i > 0 ? i + "条" : "", null);
        if (isInAuthorMainActivity()) {
            Map hashMap = new HashMap();
            hashMap.put("isOwn", String.valueOf(this.isOwn));
            i.a("event_D150", hashMap, ReaderApplication.getApplicationImp());
        }
        if (size <= 0) {
            getRootView().setVisibility(8);
            return;
        }
        initCommentArea();
        int i2 = 0;
        while (i2 < size && i2 < 3 && i2 < this.commentAndReplyLayoutResId.length) {
            n nVar;
            if (i2 < size) {
                nVar = (n) getItemList().get(i2);
            } else {
                nVar = null;
            }
            View a = ap.a(getRootView(), this.commentAndReplyLayoutResId[i2]);
            a.setVisibility(0);
            showCommentAndReply(nVar, a);
            i2++;
        }
        TextView textView = (TextView) ap.a(getRootView(), R.id.comment_0_more);
        textView.setVisibility(0);
        textView.setText("查看全部评论");
        if (this.mMoreAction == null || i <= 3) {
            textView.setVisibility(8);
            return;
        }
        Map hashMap2 = new HashMap();
        hashMap2.put("isOwn", String.valueOf(this.isOwn));
        i.a("event_D152", hashMap2, ReaderApplication.getApplicationImp());
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentCard_1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", String.valueOf(this.a.isOwn));
                i.a("event_D153", hashMap, ReaderApplication.getApplicationImp());
                String str = "0";
                if (this.a.mCommentCount == 0 && this.a.mReplyCount > 0) {
                    str = "1";
                }
                Intent intent = new Intent(this.a.getEvnetListener().getFromActivity(), NativeNewTabTwoLevelActivity.class);
                intent.putExtra("KEY_ACTIONTAG", str);
                intent.putExtra("AUTHORPAGE_KEY_AUTHORID", this.a.authorId);
                intent.putExtra("KEY_JUMP_PAGENAME", "userAllComment");
                this.a.getEvnetListener().getFromActivity().startActivity(intent);
            }
        });
    }

    private void initCommentArea() {
        for (int a : this.commentAndReplyLayoutResId) {
            ap.a(getRootView(), a).setVisibility(8);
        }
    }

    public boolean isDataReady() {
        return this.mDataIsReady;
    }

    private void showCommentAndReply(n nVar, View view) {
        System.currentTimeMillis();
        View findViewById = view.findViewById(R.id.comment_layout);
        View findViewById2 = view.findViewById(R.id.reply_layout);
        int i = nVar.y;
        if (i > 0) {
            findViewById2.setVisibility(0);
            findViewById.setVisibility(8);
        } else {
            findViewById2.setVisibility(8);
            findViewById.setVisibility(0);
        }
        if (nVar == null) {
            return;
        }
        if (i > 0) {
            showReply(findViewById2, nVar);
        } else {
            showComment(findViewById, nVar);
        }
    }

    protected void showReply(View view, final n nVar) {
        if (nVar != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommentCard_1 b;

                public void onClick(View view) {
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), nVar.w, null);
                        if (this.b.isInAuthorMainActivity()) {
                            i.a("event_D151", null, ReaderApplication.getApplicationImp());
                        } else {
                            i.a("event_D155", null, ReaderApplication.getApplicationImp());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ((TextView) ap.a(view, R.id.source)).setText(nVar.x);
            ((TextView) ap.a(view, R.id.time)).setText(k.c(nVar.d));
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
            if (nVar.a != null) {
                ((TextView) ap.a(view, R.id.title)).setText(nVar.a.a);
                View a = ap.a(view, R.id.img_author_tag);
                if (nVar.a.h != 0) {
                    a.setVisibility(0);
                } else {
                    a.setVisibility(8);
                }
                setAvatarImage((ImageView) ap.a(view, R.id.icon), nVar.a.b, nVar.a.n, null);
            }
        }
    }

    protected void showComment(View view, final n nVar) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentCard_1 b;

            public void onClick(View view) {
                try {
                    c.a(this.b.getEvnetListener().getFromActivity(), nVar.w, null);
                    if (this.b.isInAuthorMainActivity()) {
                        Map hashMap = new HashMap();
                        hashMap.put("isOwn", String.valueOf(this.b.isOwn));
                        i.a("event_D151", hashMap, ReaderApplication.getApplicationImp());
                        return;
                    }
                    i.a("event_D155", null, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setAvatarImage((ImageView) ap.a(view, R.id.avatar_img), nVar.a.b, nVar.a.n, null);
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
        if (nVar.a.h != 0) {
            imageView.setImageResource(R.drawable.bookclub_comment_user_tag_author);
        } else if (nVar.a.f >= 0) {
            imageView.setImageResource(ReaderApplication.getApplicationImp().getResources().getIdentifier("bookclub_user_tag_" + nVar.a.f, "drawable", ReaderApplication.getApplicationImp().getPackageName()));
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
                final /* synthetic */ CommentCard_1 b;

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
        this.isOwn = jSONObject.optInt("owner");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        if (optJSONObject != null) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
            if (optJSONObject2 != null) {
                this.mCommentCount = optJSONObject2.optInt("commentCount");
                this.mReplyCount = optJSONObject2.optInt("replyCount");
                this.authorId = optJSONObject2.optString("authorId");
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray("commentlist");
            if (optJSONArray != null) {
                if (optJSONArray.length() <= 0) {
                    this.mDataIsReady = false;
                } else {
                    this.mDataIsReady = true;
                }
                while (i < optJSONArray.length()) {
                    optJSONObject2 = optJSONArray.getJSONObject(i);
                    s nVar = new n();
                    nVar.parseData(optJSONObject2);
                    addItem(nVar);
                    i++;
                }
            } else {
                this.mDataIsReady = false;
            }
        }
        return true;
    }

    private boolean isInAuthorMainActivity() {
        return "作家评论".equals(this.mShowTitle);
    }
}
