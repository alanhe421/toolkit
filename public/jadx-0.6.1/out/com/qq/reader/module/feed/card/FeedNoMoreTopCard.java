package com.qq.reader.module.feed.card;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.z;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.d;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class FeedNoMoreTopCard extends FeedBaseCard {
    public static final String FEED_NODATA = "FEED_NODATA";
    public static final String FEED_NOLOGIN = "FEED_NOLOGIN";
    public static final String FEED_NOUSERACTION = "FEED_NOUSERACTION";
    public static final d[] feedCmds = new d[]{new d("goExplore", "去发现，随便逛逛"), new d("goClassify", "去书库，发现海量好书"), new d("goRank", "口碑排行榜，大家都在追"), new d("goClassicTopic", "十年口碑热书神作，等你来阅读"), new d("goGuide", "没有喜欢的书？调整你的阅读基因"), new d("goFame", "免费专区，畅读海量精品书"), new d("goHallOfFame", "顶级大神权威名家，就在名人堂"), new d("goLgoin", "登录后，向你推荐更多优质内容")};
    public static final int[] icons = new int[]{R.drawable.card_discover_icon, R.drawable.card_sort_icon, R.drawable.card_ranking_icon, R.drawable.card_topic_icon, R.drawable.card_fav_icon, R.drawable.card_free_icon, R.drawable.card_frame_icon, R.drawable.feed_float_bar_icon_login};
    private static z randomNoRepeat4 = new z(4);
    private static z randomNoRepeat7 = new z(7);
    private Drawable mArrow;
    private int mBackgroundDrawableRes;
    private Drawable mIconDrawable;
    private Map<String, String> mStatMap = new HashMap();
    private int mTitleTextColor;

    public FeedNoMoreTopCard(b bVar, String str) {
        int a;
        super(bVar, str);
        if (FEED_NODATA.equals(str)) {
            a = randomNoRepeat4.a();
        } else if (FEED_NOUSERACTION.equals(str)) {
            a = randomNoRepeat7.a();
        } else if (FEED_NOLOGIN.equals(str)) {
            a = feedCmds.length - 1;
        } else {
            a = (int) (Math.random() * ((double) (feedCmds.length - 1)));
        }
        setCmd(feedCmds[a]);
        this.mIconDrawable = ReaderApplication.getApplicationImp().getResources().getDrawable(icons[a]);
        this.mIconDrawable.setBounds(0, 0, this.mIconDrawable.getMinimumWidth(), this.mIconDrawable.getMinimumHeight());
        if (a == 4 || a == 7) {
            this.mArrow = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.list_item_enter_icon_white);
            this.mBackgroundDrawableRes = R.drawable.feed_adv_background_no_login_selector;
            this.mTitleTextColor = ReaderApplication.getApplicationImp().getResources().getColor(17170443);
        } else {
            this.mArrow = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.list_item_enter_icon);
            this.mBackgroundDrawableRes = R.drawable.book_shelf_adv_background_selector;
            this.mTitleTextColor = ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_common_textcolor);
        }
        this.mArrow.setBounds(0, 0, this.mArrow.getMinimumWidth(), this.mArrow.getMinimumHeight());
        this.mStatMap.put("type", "" + (a + 1));
        i.a("event_C68", this.mStatMap, ReaderApplication.getApplicationImp());
    }

    public int getResLayoutId() {
        return R.layout.bookshelf_advheader_layout;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.bookshelf_advheader_title);
        textView.setTextColor(this.mTitleTextColor);
        textView.setText(getCmd().b());
        if ("goLgoin".equals(getCmd().a())) {
            i.a("event_C64", null, ReaderApplication.getApplicationImp());
        }
        int paddingLeft = textView.getPaddingLeft();
        int paddingTop = textView.getPaddingTop();
        int paddingRight = textView.getPaddingRight();
        int paddingBottom = textView.getPaddingBottom();
        textView.setBackgroundResource(this.mBackgroundDrawableRes);
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        ((ImageView) ap.a(getRootView(), R.id.bookshelf_advheader_righticon)).setImageDrawable(this.mArrow);
        ((ImageView) ap.a(getRootView(), R.id.book_shelf_adv_icon)).setImageDrawable(this.mIconDrawable);
        setEventListener((a) textView.getContext());
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedNoMoreTopCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.doOnFeedClicked(true);
            }
        });
    }

    public boolean swipeEnable() {
        return false;
    }

    public void doOnFeedClicked(boolean z) {
        super.doOnFeedClicked(z);
        i.a("event_C69", this.mStatMap, ReaderApplication.getApplicationImp());
    }

    public boolean isLongClickEnable() {
        return false;
    }
}
