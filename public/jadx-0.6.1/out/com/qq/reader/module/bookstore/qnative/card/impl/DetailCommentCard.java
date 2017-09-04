package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
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
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.d.c;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailCommentCard extends a {
    private final int INTRO_MAX_LINES = 4;
    private final int MAX_COMMENT = 3;
    public String mBookName = null;
    private int mCommentCount = 0;
    private int mCommentTorCount = 0;
    private boolean mDataIsReady = false;
    private int mHowWeek = 0;

    public DetailCommentCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_comment_0;
    }

    public void attachView() {
        TextView textView;
        final int size = getItemList().size();
        View a = ap.a(getRootView(), R.id.detail_common_title);
        if (a != null) {
            ((TextView) a.findViewById(R.id.title_name)).setText("书评区");
            textView = (TextView) a.findViewById(R.id.title_intro);
            textView.setVisibility(0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mCommentCount);
            stringBuilder.append("评论");
            if (this.mCommentTorCount > 0) {
                stringBuilder.append("，").append(this.mCommentTorCount).append("人参与");
            }
            textView.setText(stringBuilder.toString());
            a.findViewById(R.id.title_divider).setVisibility(8);
        }
        ((ImageView) ap.a(getRootView(), R.id.comment_0_title_sendcomment)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailCommentCard b;

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                try {
                    bundle.putString("KEY_ACTION", "detail_2_topic");
                    bundle.putInt("DETAIL_COMMENT_INDEX_COUNT", size);
                    this.b.getEvnetListener().doFunction(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (this.b.getBindPage() instanceof c) {
                    i.a("event_F335", null, ReaderApplication.getApplicationImp());
                }
            }
        });
        textView = (TextView) ap.a(getRootView(), R.id.comment_0_title_hotweek);
        if (this.mHowWeek > 0) {
            textView.setVisibility(0);
            textView.setText("周活跃" + this.mHowWeek);
        } else {
            textView.setVisibility(4);
        }
        ((TextView) ap.a(getRootView(), R.id.comment_0_title_commentcount)).setText("书评" + this.mCommentCount);
        int[] iArr = new int[]{R.id.comment_0_comment_0, R.id.comment_0_comment_1, R.id.comment_0_comment_2};
        if (size <= 0) {
            View findViewById = getRootView().findViewById(R.id.comment_0_none);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailCommentCard b;

                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    try {
                        bundle.putString("KEY_ACTION", "detail_2_topic");
                        bundle.putInt("DETAIL_COMMENT_INDEX_COUNT", size);
                        this.b.getEvnetListener().doFunction(bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.b.getBindPage() instanceof c) {
                        i.a("event_F335", null, ReaderApplication.getApplicationImp());
                    }
                }
            });
            for (int size2 : iArr) {
                getRootView().findViewById(size2).setVisibility(8);
            }
            ((TextView) ap.a(getRootView(), R.id.comment_0_more)).setVisibility(8);
        } else {
            getRootView().findViewById(R.id.comment_0_none).setVisibility(8);
            for (int i = 0; i < 3; i++) {
                l lVar = null;
                if (i < size2) {
                    lVar = (l) getItemList().get(i);
                }
                showComentPart(iArr[i], lVar, getRootView());
            }
            textView = (TextView) ap.a(getRootView(), R.id.comment_0_more);
            textView.setVisibility(0);
            if (this.mMoreAction == null) {
                textView.setVisibility(8);
            } else {
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DetailCommentCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        l lVar = (l) this.a.getItemList().get(0);
                        com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
                        bundle.putLong("URL_BUILD_PERE_BOOK_ID", lVar.i);
                        bundle.putString("PARA_TYPE_BOOK_NAME", this.a.mBookName);
                        bundle.putString("KEY_JUMP_PAGENAME", "bookclubmain");
                        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
                        bundle.putInt("function_type", 0);
                        bundle.putBoolean("newactivitywithresult", true);
                        if (this.a.getBindPage() instanceof c) {
                            bundle.putInt("CTYPE", 9);
                        } else {
                            bundle.putInt("CTYPE", 0);
                        }
                        cVar.a(this.a.getEvnetListener());
                        i.a("event_C56", null, ReaderApplication.getApplicationImp());
                        i.a("event_C77", null, ReaderApplication.getApplicationImp());
                        if (this.a.getBindPage() instanceof c) {
                            i.a("event_F335", null, ReaderApplication.getApplicationImp());
                        }
                    }
                });
            }
        }
        if (getBindPage() instanceof c) {
            ap.a(getRootView(), R.id.top_divider).setVisibility(8);
            ap.a(getRootView(), R.id.bottom_divider).setVisibility(0);
            i.a("event_F334", null, ReaderApplication.getApplicationImp());
        }
    }

    public boolean isDataReady() {
        return this.mDataIsReady;
    }

    private void showComentPart(int i, l lVar, View view) {
        if (lVar != null) {
            View a = ap.a(view, i);
            a.setVisibility(0);
            showComment(a, lVar);
            return;
        }
        view.findViewById(i).setVisibility(8);
    }

    private void showComment(View view, final l lVar) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailCommentCard b;

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
                bundle.putLong("URL_BUILD_PERE_BOOK_ID", lVar.i);
                bundle.putString("COMMENT_ID", lVar.g);
                bundle.putString("PARA_TYPE_COMMENT_UID", lVar.a.g);
                bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
                bundle.putBoolean("newactivitywithresult", true);
                bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubdetail));
                if (this.b.getBindPage() instanceof c) {
                    bundle.putInt("CTYPE", 9);
                } else {
                    bundle.putInt("CTYPE", 0);
                }
                cVar.a(this.b.getEvnetListener());
                if (this.b.getBindPage() instanceof c) {
                    i.a("event_F335", null, ReaderApplication.getApplicationImp());
                }
            }
        });
        ImageView imageView = (ImageView) ap.a(view, R.id.avatar_img_mask);
        setAvatarImage((UserCircleImageView) ap.a(view, R.id.avatar_img), lVar.a.b, lVar.a.n, null);
        imageView.setBackgroundResource(R.drawable.bookclub_avatar_small_bg_selector);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailCommentCard b;

            public void onClick(View view) {
                if (lVar.a == null || lVar.a.m <= 0 || TextUtils.isEmpty(lVar.a.n)) {
                    o.d(this.b.getEvnetListener().getFromActivity(), lVar.a.g, lVar.a.a, lVar.a.b, null);
                } else {
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{lVar.a.n, lVar.a.a, lVar.a.b}), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (this.b.getBindPage() instanceof c) {
                    i.a("event_F335", null, ReaderApplication.getApplicationImp());
                }
            }
        });
        ((TextView) ap.a(view, R.id.tv_comment_publish_time)).setVisibility(8);
        ((TextView) ap.a(view, R.id.tv_reply_source)).setText("来自" + lVar.o);
        ImageView imageView2 = (ImageView) ap.a(view, R.id.img_author_footprint);
        if (lVar.e()) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        imageView2 = (ImageView) ap.a(view, R.id.img_excellent_comment);
        if (lVar.d()) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        imageView2 = (ImageView) ap.a(view, R.id.avatar_text);
        if (lVar.a.f >= 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(getFanLevelIconId(lVar.a.f));
        } else {
            imageView2.setVisibility(8);
        }
        if (lVar.a.h != 0) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(R.drawable.bookclub_comment_user_tag_author);
        }
        TextView textView = (TextView) ap.a(view, R.id.username);
        textView.setText(lVar.a.a);
        textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColorStateList(R.color.usename_textcoolor_selector));
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailCommentCard b;

            public void onClick(View view) {
                if (lVar.a == null || lVar.a.m <= 0 || TextUtils.isEmpty(lVar.a.n)) {
                    o.d(this.b.getEvnetListener().getFromActivity(), lVar.a.g, lVar.a.a, lVar.a.b, null);
                } else {
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{lVar.a.n, lVar.a.a, lVar.a.b}), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (this.b.getBindPage() instanceof c) {
                    i.a("event_F335", null, ReaderApplication.getApplicationImp());
                }
            }
        });
        textView = (TextView) ap.a(view, R.id.title);
        final TextView textView2 = (TextView) ap.a(view, R.id.content);
        if (TextUtils.isEmpty(lVar.c)) {
            textView.setVisibility(8);
            textView2.setMaxLines(5);
        } else {
            textView.setText(lVar.c);
            textView.setVisibility(0);
            textView2.setMaxLines(4);
        }
        if (!TextUtils.isEmpty(lVar.b)) {
            lVar.b = ao.a(lVar.b);
        }
        textView2.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), lVar.b, textView2.getTextSize()));
        if (com.qq.reader.common.c.a.w) {
            textView2.setMaxLines(Integer.MAX_VALUE);
            textView2.setEllipsize(null);
            textView2.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ DetailCommentCard b;

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
        ((TextView) ap.a(view, R.id.agree_text)).setText(s.countTransform2((long) lVar.m));
        ((TextView) ap.a(view, R.id.reply_text)).setText(s.countTransform2((long) lVar.l));
        View a = ap.a(view, R.id.rating_container);
        if (lVar.g() < 1.0f) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
            ((RatingBar) ap.a(view, R.id.bookclub_ratingbar)).setRating(lVar.g());
            ((TextView) ap.a(view, R.id.bookclub_ratingbar_text)).setText(lVar.f());
        }
        ao.a(lVar.b().j, (ImageView) ap.a(view, R.id.month_icon), false);
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mHowWeek = (int) jSONObject.optDouble("score", 0.0d);
        this.mCommentCount = (int) jSONObject.optDouble("commentcount", 0.0d);
        this.mCommentTorCount = jSONObject.optInt("commentorCount", 0);
        JSONArray optJSONArray = jSONObject.optJSONArray("commentlist");
        getItemList().clear();
        if (this.mCommentCount <= 0) {
            return false;
        }
        if (optJSONArray != null) {
            if (optJSONArray.length() <= 0) {
                this.mDataIsReady = false;
            } else {
                this.mDataIsReady = true;
            }
            while (i < optJSONArray.length()) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                s lVar = new l();
                lVar.parseData(jSONObject2);
                addItem(lVar);
                i++;
            }
        } else {
            this.mDataIsReady = false;
        }
        this.mBookName = jSONObject.optString("bookname", null);
        return true;
    }
}
