package com.qq.reader.module.redpacket.square.card;

import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.redpacket.square.a.d;
import com.qq.reader.module.redpacket.view.RedPacketLoopVerticalViewPager;
import com.qq.reader.module.redpacket.view.RedPacketMessageView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONObject;

public class SquareMessageCard extends a {
    private RedPacketLoopVerticalViewPager mViewPager;
    d textAdapter = new d();
    private ArrayList<RedPacketMessageView> textViews = new ArrayList();

    public SquareMessageCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.redpacket_square_message_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void attachView() {
        this.mViewPager = (RedPacketLoopVerticalViewPager) ap.a(getRootView(), R.id.redpacket_square_message_viewpager);
        RedPacketMessageView redPacketMessageView = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView.setText("aaa");
        RedPacketMessageView redPacketMessageView2 = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView2.setText("bbb");
        RedPacketMessageView redPacketMessageView3 = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView3.setText("ccc");
        this.textViews.add(redPacketMessageView);
        this.textViews.add(redPacketMessageView2);
        this.textViews.add(redPacketMessageView3);
        this.textAdapter.a(this.textViews);
        this.mViewPager.setAdapter(this.textAdapter);
        this.mViewPager.a();
    }

    public void refreshCard() {
        this.mViewPager.b();
        RedPacketMessageView redPacketMessageView = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView.setText("你好漂亮");
        RedPacketMessageView redPacketMessageView2 = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView2.setText("eee");
        RedPacketMessageView redPacketMessageView3 = new RedPacketMessageView(getEvnetListener().getFromActivity());
        redPacketMessageView3.setText("fff");
        this.textViews.clear();
        this.textViews.add(redPacketMessageView);
        this.textViews.add(redPacketMessageView2);
        this.textViews.add(redPacketMessageView3);
        this.textAdapter.a(this.textViews);
        this.mViewPager.setAdapter(this.textAdapter);
        this.textAdapter.c();
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.a();
    }
}
