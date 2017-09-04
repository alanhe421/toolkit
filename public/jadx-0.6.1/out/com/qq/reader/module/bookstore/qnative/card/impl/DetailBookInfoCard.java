package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.content.d;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.f;
import com.qq.reader.module.bookstore.qnative.item.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.qq.reader.view.JustifyTextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

public class DetailBookInfoCard extends a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int INTRO_MAX_LINES_WITHOUT_DOWN = 100;
    private int INTRO_MAX_LINES_WITH_DOWN = 4;
    private String TAG = "DetailBookInfoCard";
    private final String WORDS_FOLLOWERS = "粉丝数";
    private final String WORDS_MONTHTICKET = "月票数";
    private final String WORDS_RECOMMEND = "推荐票数";
    private final String WORDS_REWARDS = "打赏人次";
    private int[] entryIds = new int[]{R.id.entry_item0, R.id.entry_item1, R.id.entry_item2, R.id.entry_item3, R.id.entry_item4, R.id.entry_item5};
    private boolean hasShow = false;
    private boolean isLayouted = false;
    private boolean isMortIntro = false;
    boolean lock = false;
    private JSONObject mbookInfo = null;

    public DetailBookInfoCard(b bVar, String str) {
        super(bVar, str);
    }

    public boolean isExpired() {
        return true;
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col_3;
    }

    private String getExLine(float f, float f2, float f3, float f4) {
        if (f <= f3 || f2 <= f4) {
            return "";
        }
        return "\n";
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            final o oVar = (o) getItemList().get(0);
            BookInfo4Detail bookInfo4Detail = (BookInfo4Detail) ap.a(getRootView(), R.id.colcard3_bookinfo);
            bookInfo4Detail.setBookInfo(oVar, new e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ DetailBookInfoCard a;

                {
                    this.a = r1;
                }

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    return false;
                }

                public boolean a(final com.bumptech.glide.load.resource.a.b bVar, final String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void run() {
                            d a = d.a(ReaderApplication.getInstance().getApplication());
                            if (bVar instanceof com.bumptech.glide.load.resource.bitmap.j) {
                                Parcelable b = ((com.bumptech.glide.load.resource.bitmap.j) bVar).b();
                                Intent intent = new Intent("detail.loadimg");
                                intent.putExtra("message", str);
                                intent.putExtra("image", b);
                                a.a(intent);
                            }
                        }
                    }, 200);
                    return false;
                }
            });
            View a = ap.a(getRootView(), R.id.detail_statisticsinfo_layout);
            TextView textView = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item1_name);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item1_number);
            TextView textView3 = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item2_name);
            TextView textView4 = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item2_number);
            TextView textView5 = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item3_name);
            TextView textView6 = (TextView) ap.a(getRootView(), R.id.detail_statisticsinfo_item3_number);
            ArrayList A = oVar.A();
            if (A != null && A.size() == 3) {
                if (a != null) {
                    a.setVisibility(0);
                }
                for (int i = 0; i < A.size(); i++) {
                    o.a aVar = (o.a) A.get(i);
                    if (aVar != null) {
                        if (i == 0) {
                            textView.setText(aVar.a);
                            textView2.setText(aVar.b);
                            setTextBold(textView2);
                        } else if (i == 1) {
                            textView3.setText(aVar.a);
                            textView4.setText(aVar.b);
                            setTextBold(textView4);
                        } else if (i == 2) {
                            textView5.setText(aVar.a);
                            textView6.setText(aVar.b);
                            setTextBold(textView6);
                        }
                    }
                }
            } else if (a != null) {
                a.setVisibility(8);
            }
            bookInfo4Detail.setImageClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailBookInfoCard b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.getEvnetListener().getFromActivity(), ReaderPageActivity.class);
                    intent.setFlags(SigType.WLOGIN_QRPUSH);
                    intent.putExtra("com.qq.reader.OnlineTag", null);
                    intent.putExtra("filepath", String.valueOf(oVar.e()));
                    intent.putExtra("com.qq.reader.fromonline", true);
                    i.a("event_C279", null, ReaderApplication.getApplicationImp());
                    this.b.getEvnetListener().getFromActivity().startActivity(intent);
                }
            });
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_editor_comment);
            if (TextUtils.isEmpty(oVar.s()) || oVar.s().toLowerCase().equals("null")) {
                linearLayout.setVisibility(8);
            } else {
                linearLayout.setVisibility(0);
                textView = (TextView) ap.a(getRootView(), R.id.tv_editor_comment);
                textView.setText("小编寄语：" + oVar.s().replace("\n", ""));
                textView.getPaint().setFlags(9);
            }
            textView = bookInfo4Detail.getAction();
            if (oVar.k()) {
                i.a("event_C78", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C78", null);
            }
            textView2 = bookInfo4Detail.getCategory();
            if (textView2 != null) {
                textView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DetailBookInfoCard b;

                    public void onClick(View view) {
                        try {
                            c.a(this.b.getEvnetListener().getFromActivity(), "uniteqqreader://nativepage/category/list?actionId=" + oVar.r() + "&actionTag=,-1,-1,-1,-1,6&pagestamp=1", null);
                        } catch (Exception e) {
                        }
                    }
                });
            }
            bookInfo4Detail.getAuthor().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailBookInfoCard b;

                public void onClick(View view) {
                    if (TextUtils.isEmpty(oVar.c()) || "0".equals(oVar.c())) {
                        com.qq.reader.common.utils.o.a(this.b.getEvnetListener(), null, String.valueOf(oVar.e()));
                        return;
                    }
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{oVar.c(), oVar.g(), oVar.b()}), null);
                        i.a("event_C278", null, ReaderApplication.getApplicationImp());
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "4");
                        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailBookInfoCard b;

                public void onClick(View view) {
                    i.a("event_C79", null, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_C79", null);
                    if (ao.m(oVar.e())) {
                        Bundle bundle = new Bundle();
                        if (oVar.l()) {
                            bundle.putString("KEY_ACTION", "rent_book");
                            bundle.putInt("rent_price", oVar.u());
                            bundle.putInt("rent_days", oVar.v());
                        } else if (oVar.n()) {
                            bundle.putInt("package_id", oVar.t());
                            bundle.putString("KEY_ACTION", "detail_2_open_package_vip");
                        } else if (oVar.k()) {
                            bundle.putString("KEY_ACTION", "detail_2_openvip");
                        } else if (oVar.m()) {
                            bundle.putString("KEY_ACTION", "buy_one_price");
                        }
                        if (this.b.getEvnetListener() != null) {
                            this.b.getEvnetListener().doFunction(bundle);
                        }
                    }
                }
            });
            View a2;
            View a3;
            if (!ao.m(oVar.e())) {
                a2 = ap.a(getRootView(), R.id.entry_view);
                a3 = ap.a(getRootView(), R.id.entry_view_fixed);
                a2.setVisibility(8);
                a3.setVisibility(8);
            } else if (oVar.z() == null || oVar.z().size() <= 0) {
                ap.a(getRootView(), R.id.entry_view).setVisibility(8);
            } else {
                View a4;
                EntryItemView entryItemView;
                if (oVar.z().size() > 4) {
                    a4 = ap.a(getRootView(), R.id.entry_view);
                    View a5 = ap.a(getRootView(), R.id.entry_view_fixed);
                    for (int i2 = 0; i2 < this.entryIds.length; i2++) {
                        entryItemView = (EntryItemView) ap.a(getRootView(), this.entryIds[i2]);
                        if (i2 < oVar.z().size()) {
                            if (i2 == 0) {
                                entryItemView.a(false);
                            }
                            entryItemView.setEntryInfo((f) oVar.z().get(i2));
                            entryItemView.setOnClickListener(getEvnetListener());
                        } else if (i2 >= oVar.z().size()) {
                            entryItemView.setVisibility(8);
                        }
                    }
                    a2 = a5;
                    a3 = a4;
                } else {
                    a3 = ap.a(getRootView(), R.id.entry_view_fixed);
                    a4 = ap.a(getRootView(), R.id.entry_view);
                    for (int i3 = 0; i3 < oVar.z().size(); i3++) {
                        entryItemView = (EntryItemView) ((LinearLayout) a3).getChildAt(i3);
                        if (i3 == 0) {
                            entryItemView.a(false);
                        }
                        entryItemView.setEntryInfo((f) oVar.z().get(i3));
                        entryItemView.setOnClickListener(getEvnetListener());
                    }
                    a2 = a4;
                }
                a3.setVisibility(8);
                a2.setVisibility(8);
            }
            if (!this.hasShow && !ap.b(getRootView(), R.id.colcard3_bookinfo_intro)) {
                this.hasShow = true;
                final ImageView imageView = (ImageView) ap.a(getRootView(), R.id.colcard3_bookinfo_intro_arrow_down);
                final JustifyTextView justifyTextView = (JustifyTextView) ap.a(getRootView(), R.id.colcard3_bookinfo_intro);
                RelativeLayout relativeLayout = (RelativeLayout) ap.a(getRootView(), R.id.rl_book_intro);
                CharSequence i4 = oVar.i();
                this.isLayouted = false;
                this.isMortIntro = false;
                if (TextUtils.isEmpty(i4)) {
                    relativeLayout.setVisibility(8);
                }
                justifyTextView.setText(i4);
                justifyTextView.setEnabled(false);
                justifyTextView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DetailBookInfoCard c;

                    public void onClick(View view) {
                        Exception exception;
                        Exception exception2;
                        CharSequence charSequence;
                        CharSequence charSequence2 = null;
                        int lineCount;
                        if (this.c.isMortIntro) {
                            justifyTextView.setMaxLines(this.c.INTRO_MAX_LINES_WITHOUT_DOWN);
                            Object i = ((o) this.c.getItemList().get(0)).i();
                            justifyTextView.setText(i);
                            imageView.setBackgroundResource(R.drawable.editor_comment_fold);
                            lineCount = justifyTextView.getLayout().getLineCount() - 1;
                            if (lineCount >= 0) {
                                justifyTextView.setText(i + this.c.getExLine(justifyTextView.getLayout().getLineRight(lineCount), (float) justifyTextView.getLayout().getLineBottom(lineCount), (float) imageView.getLeft(), (float) imageView.getTop()));
                            }
                            this.c.isMortIntro = false;
                            i.a("event_B140", charSequence2, ReaderApplication.getApplicationImp());
                            return;
                        }
                        justifyTextView.setMaxLines(this.c.INTRO_MAX_LINES_WITH_DOWN);
                        try {
                            int lineStart = justifyTextView.getLayout().getLineStart(0);
                            lineCount = justifyTextView.getLayout().getLineEnd(Math.min(justifyTextView.getLineCount() - 1, this.c.INTRO_MAX_LINES_WITH_DOWN - 1));
                            String charSequence3 = justifyTextView.getText().toString();
                            if (lineCount > 10) {
                                try {
                                    if (charSequence3.length() > 10) {
                                        charSequence3 = charSequence3.substring(lineStart, lineCount - 10);
                                    }
                                } catch (Exception e) {
                                    exception = e;
                                    i = charSequence3;
                                    exception2 = exception;
                                    exception2.printStackTrace();
                                    justifyTextView.setText(charSequence);
                                    imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                                    this.c.isMortIntro = true;
                                }
                            }
                            charSequence = charSequence3 + "...";
                        } catch (Exception e2) {
                            exception = e2;
                            charSequence = charSequence2;
                            exception2 = exception;
                            exception2.printStackTrace();
                            justifyTextView.setText(charSequence);
                            imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                            this.c.isMortIntro = true;
                        }
                        justifyTextView.setText(charSequence);
                        imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                        this.c.isMortIntro = true;
                    }
                });
                justifyTextView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                    final /* synthetic */ DetailBookInfoCard d;

                    public void onGlobalLayout() {
                        if (!this.d.isLayouted) {
                            if (justifyTextView.getLineCount() > this.d.INTRO_MAX_LINES_WITH_DOWN) {
                                justifyTextView.setMaxLines(this.d.INTRO_MAX_LINES_WITH_DOWN);
                                int lineStart = justifyTextView.getLayout().getLineStart(0);
                                int lineEnd = justifyTextView.getLayout().getLineEnd(this.d.INTRO_MAX_LINES_WITH_DOWN - 1);
                                String charSequence = justifyTextView.getText().toString();
                                if (charSequence.length() > 10) {
                                    charSequence = charSequence.substring(lineStart, lineEnd - 10);
                                }
                                justifyTextView.setText(charSequence + "...");
                                imageView.setVisibility(0);
                                if ("19200".equals(oVar.d())) {
                                    justifyTextView.setMaxLines(this.d.INTRO_MAX_LINES_WITHOUT_DOWN);
                                    Object i = ((o) this.d.getItemList().get(0)).i();
                                    justifyTextView.setText(i);
                                    imageView.setBackgroundResource(R.drawable.editor_comment_fold);
                                    lineStart = justifyTextView.getLayout().getLineCount() - 1;
                                    if (lineStart >= 0) {
                                        justifyTextView.setText(i + this.d.getExLine(justifyTextView.getLayout().getLineRight(lineStart), (float) justifyTextView.getLayout().getLineBottom(lineStart), (float) imageView.getLeft(), (float) imageView.getTop()));
                                    }
                                    this.d.isMortIntro = false;
                                } else {
                                    this.d.isMortIntro = true;
                                }
                                justifyTextView.setEnabled(true);
                            }
                            this.d.isLayouted = true;
                        }
                    }
                });
            }
        }
    }

    private Bundle getRewardBundle(int i, o oVar) {
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ACTION", "detail_2_reward");
        bundle.putLong("URL_BUILD_PERE_BOOK_ID", oVar.e());
        bundle.putInt("PARA_TYPE_REWARD_TAB_INDEX", i);
        bundle.putString("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", "");
        bundle.putString("PARA_TYPE_BOOK_TITLE", oVar.f());
        return bundle;
    }

    private SpannableString getSpanNumTag(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("\n");
        stringBuffer.append(str2);
        SpannableString spannableString = new SpannableString(stringBuffer.toString());
        spannableString.setSpan(new AbsoluteSizeSpan((int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.localstore_textsize_19)), 0, stringBuffer.indexOf("\n"), 17);
        spannableString.setSpan(new AbsoluteSizeSpan((int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.localstore_textsize_12)), stringBuffer.indexOf("\n"), stringBuffer.length(), 33);
        return spannableString;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mbookInfo = jSONObject;
        s oVar = new o();
        oVar.parseData(jSONObject);
        getItemList().clear();
        addItem(oVar);
        return true;
    }

    public JSONObject getBookInfo() {
        return this.mbookInfo;
    }

    public void setTextBold(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFakeBoldText(true);
        }
    }
}
