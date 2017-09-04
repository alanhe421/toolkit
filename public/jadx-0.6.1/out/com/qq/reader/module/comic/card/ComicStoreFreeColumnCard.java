package com.qq.reader.module.comic.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.comic.entity.b;
import com.qq.reader.module.comic.entity.q;
import com.qq.reader.module.comic.views.ComicSingleBookView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicStoreFreeColumnCard extends ComicStoreBaseCard {
    private static final String TAG = "ComicStoreFreeColumnCard";
    private static final int[] comicItemResIds = new int[]{R.id.comic_six_card_10item, R.id.comic_six_card_11item, R.id.comic_six_card_12item};
    private b comicColumnInfo;

    public ComicStoreFreeColumnCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_one_plus_three_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.comicColumnInfo = (b) new Gson().fromJson(jSONObject.toString(), b.class);
        return this.comicColumnInfo.g() >= 4;
    }

    public void attachView() {
        int i = 0;
        if (this.comicColumnInfo != null) {
            int i2;
            View rootView = getRootView();
            ((TextView) ap.a(rootView, R.id.tv_comic_card_title_name)).setText(this.comicColumnInfo.b());
            ((TextView) ap.a(rootView, R.id.tv_comic_card_title_face)).setText(this.comicColumnInfo.c());
            ((TextView) ap.a(rootView, R.id.tv_comic_card_title_des)).setText(this.comicColumnInfo.d());
            ImageView imageView = (ImageView) ap.a(rootView, R.id.iv_comic_card_title_icon);
            if (TextUtils.isEmpty(this.comicColumnInfo.e())) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(this.comicColumnInfo.e(), imageView, a.a().j());
            }
            View a = ap.a(rootView, R.id.rl_click_for_more);
            final Object i3 = this.comicColumnInfo.i();
            if (TextUtils.isEmpty(i3) || this.comicColumnInfo.h() <= 4) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            a.setVisibility(i2);
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ComicStoreFreeColumnCard b;

                public void onClick(View view) {
                    this.b.doMoreClick(i3);
                }
            });
            a = ap.a(rootView, R.id.rl_first_comic_book);
            imageView = (ImageView) ap.a(rootView, R.id.iv_first_comic_cover);
            String i4 = ((q) this.comicColumnInfo.f().get(0)).i();
            if (TextUtils.isEmpty(i4)) {
                i4 = ao.a(((q) this.comicColumnInfo.f().get(0)).a(), com.qq.reader.common.c.a.bU, imageView.getLayoutParams().height);
            }
            c.a(getEvnetListener().getFromActivity()).a(i4, imageView, a.a().j());
            ((TextView) ap.a(rootView, R.id.tv_first_comic_title)).setText(((q) this.comicColumnInfo.f().get(0)).b());
            ((TextView) ap.a(rootView, R.id.tv_first_comic_desc)).setText(((q) this.comicColumnInfo.f().get(0)).h());
            final String valueOf = String.valueOf(((q) this.comicColumnInfo.f().get(0)).a());
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ComicStoreFreeColumnCard b;

                public void onClick(View view) {
                    o.b(this.b.getEvnetListener().getFromActivity(), valueOf, null, "1");
                }
            });
            while (i < comicItemResIds.length && i < this.comicColumnInfo.g() - 1) {
                ComicSingleBookView comicSingleBookView = (ComicSingleBookView) ap.a(rootView, comicItemResIds[i]);
                final q qVar = (q) this.comicColumnInfo.f().get(i + 1);
                comicSingleBookView.setAllData(qVar);
                comicSingleBookView.a(qVar.i(), qVar.a(ao.a(93.0f), ao.a(124.0f)));
                final String valueOf2 = String.valueOf(qVar.a());
                comicSingleBookView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ComicStoreFreeColumnCard c;

                    public void onClick(View view) {
                        if (TextUtils.isEmpty(qVar.j())) {
                            o.b(this.c.getEvnetListener().getFromActivity(), valueOf2, null, "1");
                            return;
                        }
                        try {
                            com.qq.reader.qurl.c.a(this.c.getEvnetListener().getFromActivity(), qVar.j());
                            this.c.onClickBookStat(this.c.comicColumnInfo.a());
                        } catch (Exception e) {
                            e.printStackTrace();
                            o.b(this.c.getEvnetListener().getFromActivity(), valueOf2, null, "1");
                        }
                    }
                });
                i++;
            }
            onShowStat(this.comicColumnInfo.a());
        }
    }

    public void onCardShouldDestroy() {
        super.onCardShouldDestroy();
    }

    public b getComicColumnInfo() {
        return this.comicColumnInfo;
    }

    public void setComicColumnInfo(b bVar) {
        this.comicColumnInfo = bVar;
    }

    public void doMoreClick(String str) {
        try {
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), str);
            onClickMoreStat(this.comicColumnInfo.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
