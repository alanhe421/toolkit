package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailListBookCard_4 extends a {
    private static final String TAG = "ListBookCard_1";
    private int[] authorResIds = new int[]{R.id.tv_author_1, R.id.tv_author_2, R.id.tv_author_3, R.id.tv_author_4};
    private String authorid;
    private int[] bookLayoutResIds = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3, R.id.ll_book_4};
    private int[] bookNameResIds = new int[]{R.id.tv_book_name_1, R.id.tv_book_name_2, R.id.tv_book_name_3, R.id.tv_book_name_4};
    private View booklistView = null;
    private int changeStartPos = 0;
    private int[] coverResIds = new int[]{R.id.img_book_cover_1, R.id.img_book_cover_2, R.id.img_book_cover_3, R.id.img_book_cover_4};
    private String mAuthorName = "";
    private String mAvatarUrl;
    private int mFansCount = 0;
    private String mIntro = "";
    private int mLevel = 0;
    private int mStatusCount = 0;

    static /* synthetic */ int access$612(DetailListBookCard_4 detailListBookCard_4, int i) {
        int i2 = detailListBookCard_4.changeStartPos + i;
        detailListBookCard_4.changeStartPos = i2;
        return i2;
    }

    public DetailListBookCard_4(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookcardinfolist_author_rec;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            this.authorid = jSONObject.optString("authorid");
            JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
            if (optJSONArray != null) {
                getItemList().clear();
                int length = optJSONArray.length();
                if (length > 0 && getItemList() != null && getItemList().size() <= 0) {
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        s gVar = new g();
                        gVar.b(this.mFromBid);
                        gVar.parseData(jSONObject2);
                        addItem(gVar);
                    }
                }
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("authorInfo");
            if (optJSONObject != null) {
                this.mStatusCount = optJSONObject.optInt("commentCount", 0);
                this.mFansCount = optJSONObject.optInt("fansCount", 0);
                this.mAvatarUrl = optJSONObject.optJSONObject("author").optString("avatar");
                this.mIntro = optJSONObject.optJSONObject("author").optString("intro");
                this.mLevel = optJSONObject.optJSONObject("author").optInt("levelType");
                this.mAuthorName = optJSONObject.optJSONObject("author").optString("penName");
            }
        } catch (Exception e) {
        }
        return true;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) getRootView();
        View a = ap.a(getRootView(), R.id.ll_author);
        View a2 = ap.a(getRootView(), R.id.detail_common_title);
        TextView textView = (TextView) a2.findViewById(R.id.title_name);
        a2 = a2.findViewById(R.id.title_divider);
        i.a("event_B242", null, ReaderApplication.getApplicationImp());
        if (TextUtils.isEmpty(this.authorid) || this.authorid.equals("0") || (TextUtils.isEmpty(this.mAuthorName) && TextUtils.isEmpty(this.mIntro))) {
            a.setVisibility(8);
            textView.setText("作者还写过");
            a2.setVisibility(8);
        } else {
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_4 a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    try {
                        c.a(this.a.getEvnetListener().getFromActivity(), String.format("uniteqqreader://nativepage/authors/mainpage?authorId=%s&realname=%s&iconUrl=%s", new Object[]{this.a.authorid, this.a.mAuthorName, this.a.mAvatarUrl}), null);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "4");
                        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            textView.setText(this.mConfigTitle);
        }
        if (linearLayout.getChildCount() <= 3) {
            List itemList = getItemList();
            if (itemList != null && itemList.size() != 0) {
                if (itemList.size() <= 1) {
                    linearLayout.addView(getSingleItemView((g) ((s) itemList.get(0))));
                } else {
                    this.booklistView = getMultiItemView(itemList);
                    linearLayout.addView(this.booklistView);
                }
            } else {
                return;
            }
        }
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.icon);
        ImageView imageView2 = (ImageView) linearLayout.findViewById(R.id.author_label);
        if (ao.e(this.mLevel) != 0) {
            imageView2.setBackgroundResource(ao.e(this.mLevel));
        } else {
            imageView2.setVisibility(8);
        }
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.author_name);
        TextView textView3 = (TextView) linearLayout.findViewById(R.id.fans_count);
        TextView textView4 = (TextView) linearLayout.findViewById(R.id.status_count);
        TextView textView5 = (TextView) linearLayout.findViewById(R.id.author_intro);
        if (this.mFansCount == 0) {
            textView3.setVisibility(8);
        } else {
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append("粉丝").append(" " + j.a((long) this.mFansCount));
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c103)), 0, 2, 33);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c401)), 2, spannableStringBuilder.length(), 33);
            textView3.setText(spannableStringBuilder);
        }
        if (this.mStatusCount == 0) {
            textView4.setVisibility(8);
        } else {
            CharSequence spannableStringBuilder2 = new SpannableStringBuilder();
            spannableStringBuilder2.append("评论").append(" " + j.a((long) this.mStatusCount));
            spannableStringBuilder2.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c103)), 0, 2, 33);
            spannableStringBuilder2.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c401)), 2, spannableStringBuilder2.length(), 33);
            textView4.setText(spannableStringBuilder2);
        }
        textView2.setText(this.mAuthorName);
        if (TextUtils.isEmpty(this.mIntro.trim())) {
            textView5.setVisibility(8);
        } else {
            textView5.setText(this.mIntro);
        }
        try {
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.mAvatarUrl, imageView, com.qq.reader.common.imageloader.a.a().t());
        } catch (Exception e) {
        }
    }

    private View getSingleItemView(final g gVar) {
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.localstore_detail_listcard_item_new, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_name);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.book_cover);
        TextView textView2 = (TextView) inflate.findViewById(R.id.book_author);
        TextView textView3 = (TextView) inflate.findViewById(R.id.book_info);
        TextView textView4 = (TextView) inflate.findViewById(R.id.book_popularity);
        f.d("listbook", " getview " + gVar + "  name " + gVar.n());
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(gVar.f(), imageView, com.qq.reader.common.imageloader.a.a().j());
        textView.setText(gVar.n());
        if (!TextUtils.isEmpty(gVar.r()) && gVar.i() != -1) {
            String str = gVar.i() == 1 ? "完结共" + gVar.e() + "章" : "连载至" + gVar.e() + "章";
            if (gVar != null && gVar.e() == 0) {
                str = gVar.i() == 1 ? "完结" : "连载";
            }
            textView2.setText(gVar.r() + " | " + str);
        } else if (!TextUtils.isEmpty(gVar.r())) {
            textView2.setText(gVar.r());
        } else if (gVar.i() != -1) {
            CharSequence charSequence = gVar.i() == 1 ? "完结共" + gVar.e() + "章" : "连载至" + gVar.e() + "章";
            if (gVar != null && gVar.e() == 0) {
                charSequence = gVar.i() == 1 ? "完结" : "连载";
            }
            textView2.setText(charSequence);
        } else {
            textView2.setText("");
        }
        textView3.setText(gVar.s());
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailListBookCard_4 b;

            public void onClick(View view) {
                i.a("event_C131", null, ReaderApplication.getApplicationImp());
                gVar.a(this.b.getEvnetListener());
            }
        });
        return inflate;
    }

    private View getMultiItemView(List<s> list) {
        CharSequence charSequence = null;
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.localstore_card_horizon_singleline_booklist, null);
        hideAllBookLayout(inflate);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.divider_top);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        TextView textView = (TextView) inflate.findViewById(R.id.tv_show_all);
        if (this.mMoreAction != null) {
            charSequence = this.mMoreAction.e;
        }
        textView.setText(this.mMoreAction.e);
        if (charSequence == null) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(charSequence);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_4 a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mMoreAction != null) {
                        String string = this.a.mMoreAction.a().a().getString("KEY_ACTION");
                        if ("columbooks".equals(string)) {
                            LinearLayout linearLayout = (LinearLayout) this.a.getRootView();
                            linearLayout.removeView(this.a.booklistView);
                            DetailListBookCard_4.access$612(this.a, 4);
                            this.a.booklistView = this.a.getMultiItemView(this.a.getItemList());
                            linearLayout.addView(this.a.booklistView);
                            i.a("event_C83", null, ReaderApplication.getApplicationImp());
                        } else if ("sameauthorbooks".equals(string) || "sameauthorallbooks".equals(string)) {
                            o.a(this.a.getEvnetListener(), this.a.authorid, String.valueOf(((g) this.a.getItemList().get(0)).m()));
                        }
                    }
                }
            });
        }
        int i = 0;
        while (i < list.size() && i < this.coverResIds.length) {
            final g gVar = (g) list.get((this.changeStartPos + i) % list.size());
            TextView textView2 = (TextView) inflate.findViewById(this.bookNameResIds[i]);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(this.bookLayoutResIds[i]);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(gVar.f(), (ImageView) inflate.findViewById(this.coverResIds[i]), com.qq.reader.common.imageloader.a.a().j());
            textView2.setText(gVar.n());
            relativeLayout.setVisibility(0);
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_4 b;

                public void onClick(View view) {
                    i.a("event_C131", null, view.getContext());
                    gVar.a(this.b.getEvnetListener());
                }
            });
            i++;
        }
        if (list.size() > this.coverResIds.length) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        return inflate;
    }

    private void hideAllBookLayout(View view) {
        for (int findViewById : this.bookLayoutResIds) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(findViewById);
            relativeLayout.setVisibility(4);
            relativeLayout.setOnClickListener(null);
        }
    }
}
