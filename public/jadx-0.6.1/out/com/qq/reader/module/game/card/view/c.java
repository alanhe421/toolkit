package com.qq.reader.module.game.card.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.game.card.b;
import com.tencent.feedback.proguard.R;

/* compiled from: GameHorizontalViewPresenter */
public class c extends d<a> {
    int[] a = new int[2];

    /* compiled from: GameHorizontalViewPresenter */
    public interface a extends b {
        TextView getClassView();

        TextView getDescribeView();

        ImageView getGiftView();

        TextView getHotView();

        ImageView getIconView();

        ImageView getLeftTopTips();

        GameOpenBtn getOpenBtn();

        TextView getTitleView();
    }

    public c(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        super(aVar);
    }

    protected GameOpenBtn a() {
        if (this.b == null || this.c == null) {
            return null;
        }
        a aVar = (a) this.b.get();
        if (aVar == null) {
            return null;
        }
        CharSequence string;
        int color;
        int i;
        int i2;
        ImageView iconView = aVar.getIconView();
        if (iconView != null) {
            com.qq.reader.common.imageloader.c.a(iconView.getContext()).a(this.c.e(), iconView, com.qq.reader.common.imageloader.a.a().r());
        }
        iconView = aVar.getLeftTopTips();
        if (iconView != null) {
            iconView.setVisibility(4);
        }
        TextView titleView = aVar.getTitleView();
        if (!(titleView == null || TextUtils.isEmpty(this.c.c()))) {
            titleView.setText(this.c.c());
        }
        TextView describeView = aVar.getDescribeView();
        if (describeView != null) {
            CharSequence n = this.c.n();
            if (TextUtils.isEmpty(n)) {
                n = this.c.p();
            }
            if (TextUtils.isEmpty(n)) {
                describeView.setVisibility(8);
            } else {
                describeView.setText(n);
                describeView.setVisibility(0);
            }
        }
        View classView = aVar.getClassView();
        View hotView = aVar.getHotView();
        String str = "";
        int a = this.c.a();
        this.a[0] = 0;
        switch (a) {
            case 1:
                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.game_hot);
                this.a[0] = 1;
                color = ReaderApplication.getApplicationImp().getResources().getColor(R.color.game_hot_bg_color);
                i = 8;
                i2 = 0;
                break;
            case 2:
                string = ReaderApplication.getApplicationImp().getResources().getString(R.string.game_fresh);
                this.a[0] = 1;
                color = ReaderApplication.getApplicationImp().getResources().getColor(R.color.game_fresh_bg_color);
                i = 8;
                i2 = 0;
                break;
            case 3:
                str = ReaderApplication.getApplicationImp().getResources().getString(R.string.game_same_name);
                Object obj;
                try {
                    ao.a(classView, ReaderApplication.getApplicationImp().getResources().getColor(R.color.game_same_name_bg_color));
                    obj = str;
                    i = 0;
                    i2 = 8;
                    color = 0;
                    break;
                } catch (Exception e) {
                    if (classView != null) {
                        classView.setVisibility(8);
                    }
                    obj = str;
                    i = 0;
                    i2 = 8;
                    color = 0;
                    break;
                }
            default:
                string = str;
                i = 8;
                i2 = 8;
                color = 0;
                break;
        }
        if (classView != null) {
            classView.setText(string);
            classView.setVisibility(i);
        }
        if (hotView != null) {
            try {
                ao.a(hotView, color);
            } catch (Exception e2) {
                hotView.setVisibility(8);
            }
            hotView.setText(string);
            hotView.setVisibility(i2);
        }
        iconView = aVar.getGiftView();
        if (iconView != null) {
            if (this.c.o() > 0) {
                iconView.setVisibility(0);
                this.a[1] = 1;
            } else {
                iconView.setVisibility(8);
                this.a[1] = 0;
            }
        }
        if (titleView != null) {
            color = (this.a[0] == 0 && this.a[1] == 0) ? ao.a(192.0f) : (this.a[0] == 1 && this.a[1] == 0) ? ao.a(160.0f) : (this.a[0] == 0 && this.a[1] == 1) ? ao.a(160.0f) : (this.a[0] == 1 && this.a[1] == 1) ? ao.a(144.0f) : 0;
            titleView.setMaxWidth(color);
        }
        return aVar.getOpenBtn();
    }
}
