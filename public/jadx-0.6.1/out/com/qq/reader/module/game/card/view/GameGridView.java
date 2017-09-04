package com.qq.reader.module.game.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.module.game.card.view.a.a;
import com.tencent.feedback.proguard.R;

public class GameGridView extends LinearLayout implements a {
    private ImageView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private GameOpenBtn e;
    private ImageView f;

    public GameGridView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public GameGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public GameGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        LayoutInflater.from(context).inflate(R.layout.game_grid_view, this);
        setGravity(1);
        setOrientation(1);
        this.a = (ImageView) findViewById(R.id.game_left_tips);
        this.b = (ImageView) findViewById(R.id.iv_game_icon);
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (TextView) findViewById(R.id.tv_describe);
        this.e = (GameOpenBtn) findViewById(R.id.game_open_btn);
        this.f = (ImageView) findViewById(R.id.gift_icon);
    }

    public ImageView getLeftIconTipsView() {
        return this.a;
    }

    public ImageView getIconView() {
        return this.b;
    }

    public TextView getTitleView() {
        return this.c;
    }

    public TextView getDescribeView() {
        return this.d;
    }

    public GameOpenBtn getGameBtnView() {
        return this.e;
    }

    public ImageView getGiftView() {
        return this.f;
    }

    public void setVisiable(int i) {
        setVisibility(i);
    }
}
