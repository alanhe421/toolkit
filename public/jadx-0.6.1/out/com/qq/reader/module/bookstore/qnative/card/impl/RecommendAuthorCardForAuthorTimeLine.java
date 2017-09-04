package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FocusAuthorTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;
import org.json.JSONObject;

public class RecommendAuthorCardForAuthorTimeLine extends a implements a {
    private String mAuthorIcon;
    private long mAuthorId;
    private String mAuthorName;
    private String mAuthorTitle;
    private long mBid;
    private String mBookName;
    private int mBookNum;
    private long mBookTotalWord;
    private String mCover;
    private boolean mIsFollowed;
    private boolean mIsHasBook;
    private boolean mIsShowDivider = true;
    private String mRecommendCategory;
    private String mRecommendReason;
    private String mTag1;
    private String mTag2;
    private String mWordCount;

    public RecommendAuthorCardForAuthorTimeLine(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_time_line_recommend_author_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            this.mRecommendCategory = jSONObject.optString("authorCategory");
            this.mAuthorIcon = jSONObject.optString("authorIcon");
            this.mAuthorName = jSONObject.optString("authorName");
            this.mAuthorTitle = jSONObject.optString("authorTitle");
            this.mRecommendReason = jSONObject.optString("recommendReason");
            this.mIsFollowed = jSONObject.optInt("isFocus") == 1;
            this.mBookNum = jSONObject.optInt("bookNum");
            this.mWordCount = jSONObject.optString("words");
            this.mAuthorId = jSONObject.optLong("authorId");
            JSONObject optJSONObject = jSONObject.optJSONObject("book");
            if (optJSONObject != null) {
                this.mIsHasBook = true;
                this.mBid = optJSONObject.optLong("bid");
                this.mCover = optJSONObject.optString("cover");
                this.mTag2 = optJSONObject.optString("categoryName");
                this.mTag1 = optJSONObject.optString("categoryShortName");
                this.mBookName = optJSONObject.optString("title");
                this.mBookTotalWord = optJSONObject.optLong("totalWords");
            }
        }
        return true;
    }

    public void setShowDivider(Boolean bool) {
        this.mIsShowDivider = bool.booleanValue();
    }

    public void attachView() {
        i.a("event_F76", null, ReaderApplication.getApplicationImp());
        View rootView = getRootView();
        TextView textView = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_recommend_type);
        View a = ap.a(rootView, R.id.author_tl_recommend_author_recommend_type_layout);
        ImageView imageView = (ImageView) ap.a(rootView, R.id.icon);
        ImageView imageView2 = (ImageView) ap.a(rootView, R.id.icon_mask);
        TextView textView2 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_name);
        TextView textView3 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_book_count);
        TextView textView4 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_word_count);
        final TextView textView5 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_follow);
        TextView textView6 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_recommend_reason);
        View a2 = ap.a(rootView, R.id.author_tl_recommend_author_book_container);
        ImageView imageView3 = (ImageView) ap.a(rootView, R.id.author_tl_recommend_author_book_cover);
        TextView textView7 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_book_name);
        TextView textView8 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_book_tag1);
        TextView textView9 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_book_tag2);
        TextView textView10 = (TextView) ap.a(rootView, R.id.author_tl_recommend_author_book_tag3);
        if (TextUtils.isEmpty(this.mRecommendCategory)) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
            textView.setText("猜你感兴趣的" + this.mRecommendCategory + "作者");
        }
        c.a(getEvnetListener().getFromActivity()).a(this.mAuthorIcon, imageView, com.qq.reader.common.imageloader.a.a().f());
        imageView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecommendAuthorCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F77", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.mAuthorId), this.a.mAuthorName, this.a.mAuthorIcon, null);
            }
        });
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecommendAuthorCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F77", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.mAuthorId), this.a.mAuthorName, this.a.mAuthorIcon, null);
            }
        });
        a2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecommendAuthorCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F77", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.mBid), null, null, null);
            }
        });
        textView2.setText(this.mAuthorName);
        textView3.setText(String.valueOf(this.mBookNum));
        textView4.setText(this.mWordCount);
        setupFollowBottomState(textView5, this.mIsFollowed);
        if (this.mIsFollowed) {
            textView5.setOnClickListener(null);
        } else {
            textView5.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RecommendAuthorCardForAuthorTimeLine b;

                public void onClick(View view) {
                    i.a("event_F77", null, ReaderApplication.getApplicationImp());
                    this.b.followWriter(textView5, this.b.mAuthorId);
                }
            });
        }
        if (TextUtils.isEmpty(this.mRecommendReason)) {
            textView6.setVisibility(8);
        } else {
            textView6.setVisibility(0);
            textView6.setText(this.mRecommendReason);
        }
        if (this.mIsHasBook) {
            a2.setVisibility(0);
            c.a(getEvnetListener().getFromActivity()).a(this.mCover, imageView3, com.qq.reader.common.imageloader.a.a().j());
            textView7.setText(this.mBookName);
            textView8.setText(this.mTag1);
            textView9.setText(this.mTag2);
            if (this.mBookTotalWord > TracerConfig.LOG_FLUSH_DURATION) {
                textView10.setText((this.mBookTotalWord / TracerConfig.LOG_FLUSH_DURATION) + "万字");
            } else {
                textView10.setText(this.mBookTotalWord + "字");
            }
        } else {
            a2.setVisibility(8);
        }
        View a3 = ap.a(getRootView(), R.id.card_divider);
        if (this.mIsShowDivider) {
            a3.setVisibility(0);
        } else {
            a3.setVisibility(8);
        }
    }

    public void followWriter(final TextView textView, final long j) {
        if (!ao.d(getEvnetListener().getFromActivity())) {
            Toast.makeText(getEvnetListener().getFromActivity(), R.string.network_unavailable, 0).show();
        } else if (com.qq.reader.common.login.c.b()) {
            g.a().a(new FocusAuthorTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ RecommendAuthorCardForAuthorTimeLine b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        final int optInt = new JSONObject(str).optInt("code");
                        this.b.getRootView().post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 b;

                            public void run() {
                                if (optInt == 0) {
                                    this.b.b.setupFollowBottomState(textView, true);
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.followed, 0).a();
                                } else if (optInt == -2) {
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.followed, 0).a();
                                } else {
                                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.author_edit_fenda_server_error, 0).a();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.getRootView().post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(ReaderApplication.getApplicationImp(), (int) R.string.author_edit_fenda_server_error, 0).a();
                        }
                    });
                }
            }, String.valueOf(j)));
        } else if (getEvnetListener() != null) {
            ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) getEvnetListener().getFromActivity();
            if (readerBaseActivity != null) {
                readerBaseActivity.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ RecommendAuthorCardForAuthorTimeLine c;

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.c.followWriter(textView, j);
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

    private void setupFollowBottomState(TextView textView, boolean z) {
        textView.setVisibility(0);
        if (z) {
            textView.setText(R.string.followed);
            textView.setBackgroundResource(R.drawable.shap_disable_round_white_button);
            textView.setEnabled(false);
            return;
        }
        textView.setBackgroundResource(R.drawable.selector_round_blue_button);
        textView.setText(R.string.follow);
        textView.setEnabled(true);
    }
}
