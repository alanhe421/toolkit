package com.qq.reader.module.comic.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.comic.entity.b;
import com.qq.reader.module.comic.entity.q;
import com.qq.reader.module.comic.views.ComicStripBookView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicStripItemCard extends ComicStoreBaseCard {
    private static final String TAG = "ComicStripItemCard";
    private static final int[] comicItemResIds = new int[]{R.id.comic_six_card_0item, R.id.comic_six_card_1item, R.id.comic_six_card_2item};
    private b comicColumnInfo;

    public ComicStripItemCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_strip_item_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.comicColumnInfo = (b) new Gson().fromJson(jSONObject.toString(), b.class);
        return this.comicColumnInfo.g() >= 3;
    }

    public void attachView() {
        if (this.comicColumnInfo != null) {
            int i;
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
            final Object i2 = this.comicColumnInfo.i();
            if (TextUtils.isEmpty(i2) || this.comicColumnInfo.h() <= 3) {
                i = 8;
            } else {
                i = 0;
            }
            a.setVisibility(i);
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ComicStripItemCard b;

                public void onClick(View view) {
                    this.b.doMoreClick(i2);
                }
            });
            int i3 = 0;
            while (i3 < comicItemResIds.length && i3 < this.comicColumnInfo.g()) {
                ComicStripBookView comicStripBookView = (ComicStripBookView) ap.a(rootView, comicItemResIds[i3]);
                final q qVar = (q) this.comicColumnInfo.f().get(i3);
                comicStripBookView.setAllData(qVar);
                comicStripBookView.a(qVar.i(), qVar.a(com.qq.reader.common.c.a.bU, comicStripBookView.getLayoutParams().height));
                final String valueOf = String.valueOf(qVar.a());
                comicStripBookView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ComicStripItemCard c;

                    public void onClick(View view) {
                        if (TextUtils.isEmpty(qVar.j())) {
                            com.qq.reader.module.comic.a.a().a(this.c.getEvnetListener().getFromActivity(), valueOf);
                            return;
                        }
                        try {
                            com.qq.reader.qurl.c.a(this.c.getEvnetListener().getFromActivity(), qVar.j());
                            this.c.onClickBookStat(this.c.comicColumnInfo.a());
                        } catch (Exception e) {
                            e.printStackTrace();
                            com.qq.reader.module.comic.a.a().a(this.c.getEvnetListener().getFromActivity(), valueOf);
                        }
                    }
                });
                i3++;
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
