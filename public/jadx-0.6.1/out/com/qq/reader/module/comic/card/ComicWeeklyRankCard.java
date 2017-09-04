package com.qq.reader.module.comic.card;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.item.aa;
import com.qq.reader.module.comic.entity.b;
import com.qq.reader.module.comic.entity.q;
import com.qrcomic.c.e;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicWeeklyRankCard extends ListCardCommon {
    private b comicColumnInfo;
    private boolean istenYearsRankBoard = false;
    private int[] rankBgId = new int[]{R.drawable.rank_list_item_bg1, R.drawable.rank_list_item_bg2, R.drawable.rank_list_item_bg3, R.drawable.rank_list_item_bg4, R.drawable.rank_list_item_bg5, R.drawable.rank_list_item_bg6, R.drawable.rank_list_item_bg7, R.drawable.rank_list_item_bg8, R.drawable.rank_list_item_bg9, R.drawable.rank_list_item_bg10};

    public ComicWeeklyRankCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_weekly_rank_card_layout;
    }

    public aa createListItem() {
        return new aa(this.istenYearsRankBoard);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.comicColumnInfo = (b) new Gson().fromJson(jSONObject.toString(), b.class);
        return this.comicColumnInfo.g() >= 10;
    }

    public void attachView(View view) {
        if (this.comicColumnInfo != null && (view instanceof LinearLayout)) {
            ((TextView) ap.a(view, R.id.tv_comic_card_title_name)).setText(this.comicColumnInfo.b());
            ((TextView) ap.a(view, R.id.tv_comic_card_title_face)).setText(this.comicColumnInfo.c());
            ((TextView) ap.a(view, R.id.tv_comic_card_title_des)).setText(this.comicColumnInfo.d());
            ImageView imageView = (ImageView) ap.a(view, R.id.iv_comic_card_title_icon);
            if (TextUtils.isEmpty(this.comicColumnInfo.e())) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(this.comicColumnInfo.e(), imageView, a.a().j());
            }
            LinearLayout linearLayout = (LinearLayout) view;
            if (linearLayout.getChildCount() < this.comicColumnInfo.g() + 2) {
                addItemView(linearLayout);
            }
            linearLayout.addView(LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.localstore_card_divider, null));
            onShowStat(this.comicColumnInfo.a());
        }
    }

    private void addItemView(LinearLayout linearLayout) {
        int i = 0;
        for (final q qVar : this.comicColumnInfo.f()) {
            View inflate = LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.comic_store_ranklist_item, null);
            ((LinearLayout) inflate.findViewById(R.id.top_adv_divider)).setVisibility(8);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.concept_cover_img);
            c.a(getEvnetListener().getFromActivity()).a(qVar.a(imageView.getLayoutParams().width, imageView.getLayoutParams().height), imageView, a.a().j());
            TextView textView = (TextView) inflate.findViewById(R.id.concept_title);
            if (i < 9) {
                textView.setText("0" + (i + 1) + "." + qVar.b());
            } else {
                textView.setText((i + 1) + "." + qVar.b());
            }
            if (i == 0) {
                textView.setTextColor(Color.parseColor("#F23D3D"));
            } else if (i == 1) {
                textView.setTextColor(Color.parseColor("#FF860D"));
            } else if (i == 2) {
                textView.setTextColor(Color.parseColor("#FFAA00"));
            }
            ((TextView) inflate.findViewById(R.id.concept_content)).setText(qVar.d());
            textView = (TextView) inflate.findViewById(R.id.concept_author);
            if (TextUtils.isEmpty(qVar.c())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(qVar.c());
            }
            textView = (TextView) inflate.findViewById(R.id.concept_category);
            TextView textView2 = (TextView) inflate.findViewById(R.id.concept_second_category);
            String e = qVar.e();
            if (TextUtils.isEmpty(e)) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
            } else if (e.contains(",")) {
                String[] split = e.split(",");
                if (!TextUtils.isEmpty(split[0])) {
                    textView.setVisibility(0);
                    textView.setText(split[0]);
                }
                if (!TextUtils.isEmpty(split[1])) {
                    textView2.setVisibility(0);
                    textView2.setText(split[1]);
                }
            } else {
                textView.setVisibility(0);
                textView2.setVisibility(8);
                textView.setText(qVar.e());
            }
            textView = (TextView) inflate.findViewById(R.id.concept_order);
            if (qVar.f() == 0 || TextUtils.isEmpty(qVar.g())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(qVar.f() + qVar.g());
            }
            imageView = (ImageView) inflate.findViewById(R.id.rank_list_bg);
            if (i < 0 || i >= this.rankBgId.length) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                imageView.setImageResource(this.rankBgId[i]);
            }
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ComicWeeklyRankCard b;

                public void onClick(View view) {
                    if (TextUtils.isEmpty(qVar.j())) {
                        o.l(this.b.getEvnetListener().getFromActivity(), String.valueOf(qVar.a()), null);
                    } else {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), qVar.j());
                        } catch (Exception e) {
                            e.printStackTrace();
                            o.l(this.b.getEvnetListener().getFromActivity(), String.valueOf(qVar.a()), null);
                        }
                    }
                    this.b.onClickBookStat(this.b.comicColumnInfo.a());
                }
            });
            linearLayout.addView(inflate);
            i++;
        }
    }

    public int getCardItemLayoutId() {
        return R.layout.plan2_rankboard_item;
    }

    public void setIsTenYearsRankBoard(boolean z) {
        this.istenYearsRankBoard = z;
    }

    private void onShowStat(int i) {
        i.a(e.a.a.a(i, 1), null, ReaderApplication.getApplicationImp());
    }

    private void onClickBookStat(int i) {
        i.a(e.a.a.a(i, 2), null, ReaderApplication.getApplicationImp());
    }
}
