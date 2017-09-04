package com.qq.reader.module.game.card.view;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.game.card.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: GameGridViewPresenter */
public class a extends d<a> {
    private String a;

    /* compiled from: GameGridViewPresenter */
    public interface a extends b {
        TextView getDescribeView();

        GameOpenBtn getGameBtnView();

        ImageView getGiftView();

        ImageView getIconView();

        ImageView getLeftIconTipsView();

        TextView getTitleView();
    }

    public a(com.qq.reader.module.bookstore.qnative.c.a aVar, String str) {
        super(aVar);
        this.a = str;
    }

    protected GameOpenBtn a() {
        int i = 0;
        if (this.b == null || this.c == null) {
            return null;
        }
        a aVar = (a) this.b.get();
        if (aVar == null) {
            return null;
        }
        int a = this.c.a();
        ImageView leftIconTipsView = aVar.getLeftIconTipsView();
        if (leftIconTipsView != null) {
            switch (a) {
                case 1:
                    leftIconTipsView.setImageResource(R.drawable.game_hot);
                    leftIconTipsView.setVisibility(0);
                    break;
                case 2:
                    leftIconTipsView.setImageResource(R.drawable.game_new);
                    leftIconTipsView.setVisibility(0);
                    break;
                case 3:
                    leftIconTipsView.setVisibility(8);
                    break;
                default:
                    leftIconTipsView.setVisibility(8);
                    break;
            }
        }
        leftIconTipsView = aVar.getIconView();
        if (leftIconTipsView != null) {
            c.a(leftIconTipsView.getContext()).a(this.c.e(), leftIconTipsView, com.qq.reader.common.imageloader.a.a().r());
        }
        TextView titleView = aVar.getTitleView();
        if (titleView != null) {
            titleView.setText(this.c.c());
        }
        TextView describeView = aVar.getDescribeView();
        if (describeView != null) {
            if (a == 3) {
                describeView.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.game_same_name));
                describeView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c201));
                a = 0;
            } else {
                describeView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102));
                CharSequence m = this.c.m();
                if (TextUtils.isEmpty(m)) {
                    a = 8;
                } else {
                    describeView.setText(m);
                    a = 0;
                }
            }
            describeView.setVisibility(a);
        }
        ImageView giftView = aVar.getGiftView();
        if (giftView != null) {
            if (this.c.o() > 0) {
                giftView.setVisibility(0);
                i = ao.a(89.0f);
            } else {
                giftView.setVisibility(8);
                i = ao.a(114.0f);
            }
        }
        if (titleView != null) {
            titleView.setMaxWidth(i);
        }
        return aVar.getGameBtnView();
    }

    protected boolean a(Activity activity, com.qq.reader.module.game.data.c cVar) {
        com.qq.reader.module.game.a.b().a(true);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.a);
        i.a("event_A224", hashMap, ReaderApplication.getApplicationImp());
        return super.a(activity, cVar);
    }

    protected boolean b(Activity activity, com.qq.reader.module.game.data.c cVar) {
        com.qq.reader.module.game.a.b().a(true);
        if (cVar.g() || cVar.d() == 5) {
            i.a("event_A232", null, ReaderApplication.getApplicationImp());
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, this.a);
            i.a("event_A223", hashMap, ReaderApplication.getApplicationImp());
        }
        return super.b(activity, cVar);
    }
}
