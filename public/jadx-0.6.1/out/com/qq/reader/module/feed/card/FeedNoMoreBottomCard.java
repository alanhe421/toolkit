package com.qq.reader.module.feed.card;

import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.d;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class FeedNoMoreBottomCard extends FeedBaseCard {
    public static final d[] feedCmds = new d[]{new d("goExplore", "去发现，随便逛逛"), new d("goRank", "口碑排行榜，大家都在追"), new d("goClassify", "去书库，发现海量好书"), new d("goClassicTopic", "十年口碑热书神作，等你来阅读")};
    private Map<String, String> mStatMap = new HashMap();

    public FeedNoMoreBottomCard(b bVar, String str) {
        super(bVar, str);
        int random = (int) (Math.random() * 4.0d);
        setCmd(feedCmds[random]);
        this.mStatMap.put("type", "" + (random + 1));
        i.a("event_C70", this.mStatMap, ReaderApplication.getApplicationImp());
    }

    public int getResLayoutId() {
        return R.layout.no_book_layout;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.no_book_txt_cloud_one)).setText("已显示全部");
        ((TextView) ap.a(getRootView(), R.id.bookshelf_footer_btn)).setText(getCmd().b());
    }

    public boolean swipeEnable() {
        return false;
    }

    public void doOnFeedClicked(boolean z) {
        super.doOnFeedClicked(z);
        i.a("event_C71", this.mStatMap, ReaderApplication.getApplicationImp());
    }

    public boolean isLongClickEnable() {
        return false;
    }
}
