package com.qq.reader.module.game.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.module.game.card.view.c.a;
import com.tencent.feedback.proguard.R;

public class GameHorizontalView extends LinearLayout implements a {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private GameOpenBtn e;
    private TextView f;
    private TextView g;
    private ImageView h;

    public GameHorizontalView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public GameHorizontalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public GameHorizontalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(context).inflate(R.layout.game_horizontal_view, this);
        setGravity(16);
        setOrientation(0);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.common_dp_16);
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.a = (ImageView) findViewById(R.id.iv_game_icon);
        this.b = (ImageView) findViewById(R.id.game_left_tips);
        this.c = (TextView) findViewById(R.id.game_title);
        this.d = (TextView) findViewById(R.id.game_describe);
        this.e = (GameOpenBtn) findViewById(R.id.game_open_btn);
        this.f = (TextView) findViewById(R.id.game_same_name);
        this.g = (TextView) findViewById(R.id.game_tag);
        this.h = (ImageView) findViewById(R.id.game_gift);
    }

    public ImageView getIconView() {
        return this.a;
    }

    public ImageView getLeftTopTips() {
        return this.b;
    }

    public TextView getTitleView() {
        return this.c;
    }

    public TextView getDescribeView() {
        return this.d;
    }

    public GameOpenBtn getOpenBtn() {
        return this.e;
    }

    public TextView getClassView() {
        return this.f;
    }

    public TextView getHotView() {
        return this.g;
    }

    public ImageView getGiftView() {
        return this.h;
    }

    public void setVisiable(int i) {
        setVisibility(i);
    }
}
