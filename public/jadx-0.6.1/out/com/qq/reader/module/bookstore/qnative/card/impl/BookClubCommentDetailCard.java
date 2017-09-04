package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NativeBookStoreSearchActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ah;
import com.qq.reader.common.utils.ah.b;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.item.ag;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.view.n;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class BookClubCommentDetailCard extends BaseCommentCard {
    public ag contentUserNode;
    public int mAuthorTag;
    public int mBetter;
    public String mBookName;
    public long mBookid;
    public String mContent;
    public String mId;
    public boolean mIsMaxReward = false;
    public boolean mIsReward = false;
    private float mScore;
    private String mScore_intro;
    public StringBuilder mSharedStringBuilder = new StringBuilder();
    public String mTitle;
    Iterable<b> mViewNodes;
    public long mcreateTime;
    private Bundle statiscBundle = null;

    public BookClubCommentDetailCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str, Bundle bundle, int i) {
        super(bVar, str, i);
        this.statiscBundle = bundle;
    }

    public int getResLayoutId() {
        return R.layout.bookclubcommentdetail;
    }

    public String getCommentId() {
        return this.mId;
    }

    public String getCommentUid() {
        return this.contentUserNode.g;
    }

    protected boolean parseData(JSONObject jSONObject) {
        this.contentUserNode = new ag(jSONObject.optJSONObject("user"));
        this.mTitle = jSONObject.optString("title");
        this.mContent = jSONObject.optString(MessageKey.MSG_CONTENT);
        try {
            this.mContent = Html.fromHtml(this.mContent).toString();
        } catch (Exception e) {
        }
        this.mBetter = jSONObject.optInt("better");
        this.mAuthorTag = jSONObject.optInt("authortag");
        this.mId = jSONObject.optString("commentid");
        this.mBookid = jSONObject.optLong("bid");
        this.mcreateTime = jSONObject.optLong("createtime");
        this.mBookName = jSONObject.optString("btitle");
        boolean z = jSONObject.has("reward") && jSONObject.optInt("reward") > 0;
        this.mIsReward = z;
        JSONObject optJSONObject = jSONObject.optJSONObject("scoreInfo");
        if (optJSONObject != null) {
            try {
                this.mScore = Float.valueOf(optJSONObject.optString("score", "0")).floatValue();
            } catch (Exception e2) {
                c.e("DetailPageBookItem", e2.getMessage());
            }
            this.mScore_intro = optJSONObject.optString("intro");
            i.a("event_A180", null, ReaderApplication.getApplicationImp());
        } else {
            this.mScore = -1.0f;
        }
        setCardId(this.mId);
        this.mViewNodes = ah.a(this.mContent);
        this.mSharedStringBuilder.setLength(0);
        for (b bVar : this.mViewNodes) {
            if (bVar.f == 0) {
                this.mSharedStringBuilder.append(bVar.d);
            }
        }
        return true;
    }

    private void goToAuthorOrUserCenterPage() {
        if (this.contentUserNode == null || this.contentUserNode.m <= 0 || TextUtils.isEmpty(this.contentUserNode.n)) {
            o.d(getEvnetListener().getFromActivity(), this.contentUserNode.g, this.contentUserNode.a, this.contentUserNode.b, null);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, Constants.VIA_SHARE_TYPE_INFO);
        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
        try {
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.contentUserNode.n, this.contentUserNode.a, this.contentUserNode.b}), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.avatar_img_mask);
        setAvatarImage((UserCircleImageView) ap.a(getRootView(), R.id.avatar_img), this.contentUserNode.b, this.contentUserNode.n, null);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BookClubCommentDetailCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.goToAuthorOrUserCenterPage();
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
            final /* synthetic */ BookClubCommentDetailCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.goToAuthorOrUserCenterPage();
            }
        });
        ((TextView) ap.a(getRootView(), R.id.publishtime)).setText(k.c(this.mcreateTime));
        View a = ap.a(getRootView(), R.id.title_container);
        textView = (TextView) ap.a(getRootView(), R.id.title);
        if (TextUtils.isEmpty(this.mTitle)) {
            a.setVisibility(8);
        } else {
            int i;
            a.setVisibility(0);
            textView.setText(this.mTitle);
            a = ap.a(getRootView(), R.id.title_tag);
            if (this.mIsReward) {
                i = 0;
            } else {
                i = 8;
            }
            a.setVisibility(i);
            a = ap.a(getRootView(), R.id.title_tag_2);
            if (this.mIsMaxReward) {
                i = 0;
            } else {
                i = 8;
            }
            a.setVisibility(i);
        }
        createRichTextContentView();
        imageView2 = (ImageView) ap.a(getRootView(), R.id.bookclub_author_comment_tag);
        if (this.mAuthorTag == 1) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        imageView2 = (ImageView) ap.a(getRootView(), R.id.bookclub_theselected_comment_tag);
        if (this.mBetter == 1) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        View a2 = ap.a(getRootView(), R.id.rating_container);
        if (this.mScore < 1.0f) {
            a2.setVisibility(8);
        } else {
            a2.setVisibility(0);
            ((RatingBar) ap.a(getRootView(), R.id.bookclub_ratingbar)).setRating(this.mScore);
            ((TextView) ap.a(getRootView(), R.id.bookclub_ratingbar_text)).setText(this.mScore_intro);
        }
        ao.a(this.contentUserNode.j, (ImageView) ap.a(getRootView(), R.id.month_icon), false);
    }

    private SpannableStringBuilder trimSpannable(SpannableStringBuilder spannableStringBuilder) {
        if (spannableStringBuilder == null) {
            return null;
        }
        String spannableStringBuilder2 = spannableStringBuilder.toString();
        int i = 0;
        while (spannableStringBuilder2.length() > 0 && spannableStringBuilder2.startsWith("\n")) {
            spannableStringBuilder2 = spannableStringBuilder2.substring(1);
            i++;
        }
        int i2 = 0;
        while (spannableStringBuilder2.length() > 0 && spannableStringBuilder2.endsWith("\n")) {
            spannableStringBuilder2 = spannableStringBuilder2.substring(0, spannableStringBuilder2.length() - 1);
            i2++;
        }
        return spannableStringBuilder.delete(0, i).delete(spannableStringBuilder.length() - i2, spannableStringBuilder.length());
    }

    private void createRichTextContentView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.content_container);
        if (linearLayout.getChildCount() <= 0) {
            Object obj = null;
            for (b bVar : this.mViewNodes) {
                SpannedString spannedString;
                switch (bVar.f) {
                    case 0:
                        if (obj == null) {
                            obj = new SpannedString("");
                        }
                        spannedString = (SpannedString) TextUtils.concat(new CharSequence[]{obj, createSpannableText(bVar)});
                        break;
                    case 1:
                        if (!TextUtils.isEmpty(obj)) {
                            linearLayout.addView(createTextViewNode(obj));
                        }
                        String str = bVar.d;
                        final ImageView imageView = new ImageView(getRootView().getContext());
                        linearLayout.addView(imageView);
                        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, imageView, a.a().j(), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                            final /* synthetic */ BookClubCommentDetailCard b;

                            public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                                return false;
                            }

                            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                                if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                                    com.bumptech.glide.load.resource.bitmap.j jVar2 = (com.bumptech.glide.load.resource.bitmap.j) bVar;
                                    if (!(jVar2 == null || jVar2.b() == null)) {
                                        imageView.setLayoutParams(new LayoutParams(-1, (jVar2.b().getHeight() * (com.qq.reader.common.c.a.bU - (ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.comment_detail_content_pic_padding) * 2))) / jVar2.b().getWidth()));
                                    }
                                }
                                return false;
                            }
                        });
                        spannedString = null;
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(obj)) {
                            linearLayout.addView(createTextViewNode(obj));
                        }
                        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                        layoutParams.setMargins(0, 10, 0, 10);
                        linearLayout.addView(createBookViewNode(bVar.d, linearLayout), layoutParams);
                        spannedString = null;
                        break;
                    default:
                        spannedString = obj;
                        break;
                }
                SpannedString spannedString2 = spannedString;
            }
            if (!TextUtils.isEmpty(obj)) {
                linearLayout.addView(createTextViewNode(obj));
            }
        }
    }

    private SpannableStringBuilder createSpannableText(final b bVar) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(bVar.d);
        if (bVar.a != 0) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(bVar.a), 0, bVar.d.length(), 18);
        } else {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c102)), 0, bVar.d.length(), 18);
        }
        if (bVar.b > 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) TypedValue.applyDimension(1, (float) bVar.b, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics())), 0, bVar.d.length(), 18);
        } else {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.text_size_class_3)), 0, bVar.d.length(), 18);
        }
        if (bVar.c) {
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, bVar.d.length(), 18);
        }
        if (!TextUtils.isEmpty(bVar.e)) {
            spannableStringBuilder.setSpan(new URLSpan(this, bVar.e) {
                final /* synthetic */ BookClubCommentDetailCard b;

                public void onClick(View view) {
                    try {
                        com.qq.reader.qurl.c.a((Activity) this.b.getEvnetListener(), bVar.e, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, bVar.d.length(), 18);
        }
        return spannableStringBuilder;
    }

    private CharSequence spanBookName(CharSequence charSequence) {
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
                        n anonymousClass5 = new n(this) {
                            final /* synthetic */ BookClubCommentDetailCard a;

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
                                intent.putExtra("searchbackstate", 0);
                                this.a.getRootView().getContext().startActivity(intent);
                                i.a("event_C276", null, ReaderApplication.getApplicationImp());
                                new Handler().postDelayed(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass5 b;

                                    public void run() {
                                        this.b.b = false;
                                        view.postInvalidate();
                                    }
                                }, 100);
                            }
                        };
                        anonymousClass5.b = false;
                        anonymousClass5.c = charSequence2;
                        spannableString.setSpan(anonymousClass5, i, indexOf + 1, 18);
                        i.a("event_C275", null, ReaderApplication.getApplicationImp());
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

    private TextView createTextViewNode(Spanned spanned) {
        TextView textView = new TextView(getRootView().getContext());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(spanned);
        textView.setText(com.qq.reader.common.emotion.b.a(getRootView().getContext(), spanBookName(trimSpannable(spannableStringBuilder)), TypedValue.applyDimension(1, 15.0f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics()), 1.0f, 0));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setTextSize((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
        textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102));
        textView.setLineSpacing(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.bookclub_detail_card_content_line_space), 1.0f);
        return textView;
    }

    private View createBookViewNode(String str, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getRootView().getContext()).inflate(R.layout.localstore_card_selected_comment_bookinfo, viewGroup, false);
        try {
            View findViewById = inflate.findViewById(R.id.bookinfo_view);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            findViewById.setLayoutParams(layoutParams);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String optString;
            JSONObject jSONObject = new JSONObject(str);
            CharSequence optString2 = jSONObject.optString("title");
            CharSequence optString3 = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
            CharSequence optString4 = jSONObject.optString("author");
            final long optLong = jSONObject.optLong("bid");
            JSONObject optJSONObject = jSONObject.optJSONObject("scoreInfo");
            String optString5 = optJSONObject != null ? optJSONObject.optString("score", "0") : "0";
            if (optJSONObject != null) {
                optString = optJSONObject.optString("scoretext");
            } else {
                optString = null;
            }
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookClubCommentDetailCard b;

                public void onClick(View view) {
                    ao.a((Activity) this.b.getEvnetListener(), optLong, this.b.statiscBundle);
                    i.a("event_C73", null, ReaderApplication.getApplicationImp());
                }
            });
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(optLong), (ImageView) inflate.findViewById(R.id.bookinfo_cover), a.a().j());
            ((TextView) inflate.findViewById(R.id.bookinfo_name)).setText(optString2);
            ((RatingBar) inflate.findViewById(R.id.bookinfo_ratingbar)).setRating(Float.valueOf(optString5).floatValue());
            TextView textView = (TextView) inflate.findViewById(R.id.bookinfo_ratingbar_text);
            if (Float.valueOf(optString5).floatValue() > 0.0f) {
                textView.setText(optString + "分");
            } else {
                textView.setText("");
            }
            ((TextView) inflate.findViewById(R.id.bookinfo_type)).setText(optString3);
            ((TextView) inflate.findViewById(R.id.bookinfo_author)).setText(optString4);
            ((TextView) inflate.findViewById(R.id.bookinfo_read)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookClubCommentDetailCard b;

                public void onClick(View view) {
                    this.b.gotoReaderPage((Activity) this.b.getEvnetListener(), optLong);
                    i.a("event_C247", null, ReaderApplication.getApplicationImp());
                }
            });
            i.a("event_C246", null, ReaderApplication.getApplicationImp());
            return inflate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoReaderPage(Activity activity, long j) {
        Intent intent = new Intent();
        intent.setClass(activity, ReaderPageActivity.class);
        intent.setFlags(WtloginHelper.SigType.WLOGIN_QRPUSH);
        intent.putExtra("com.qq.reader.OnlineTag", null);
        intent.putExtra("filepath", String.valueOf(j));
        intent.putExtra("com.qq.reader.fromonline", true);
        activity.startActivity(intent);
    }
}
