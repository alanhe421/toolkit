package com.qq.reader.module.game.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class GameOpenBtn extends RelativeLayout {
    protected int a;
    protected int b = 0;
    protected int c = 0;
    protected TextView d;
    private ProgressBar e;

    public GameOpenBtn(Context context) {
        super(context);
        a(context, null, 0);
    }

    public GameOpenBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public GameOpenBtn(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    protected void a(Context context, AttributeSet attributeSet, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.game_open_btn, this);
        this.e = (ProgressBar) inflate.findViewById(R.id.pb_game_progress);
        this.d = (TextView) inflate.findViewById(R.id.tv_game_status);
    }

    public void setGameBtnStatus(int i) {
        int i2 = 4;
        int i3 = R.drawable.selector_round_blue_button;
        CharSequence string = getResources().getString(R.string.game_already_install);
        this.a = i;
        switch (i) {
            case 0:
                string = getResources().getString(R.string.game_start_download);
                break;
            case 1:
                string = String.format(getResources().getString(R.string.game_downloading), new Object[]{Integer.valueOf(this.c)});
                i2 = 0;
                break;
            case 2:
                string = getResources().getString(R.string.game_download_waiting);
                if (this.c != this.b) {
                    i2 = 0;
                    break;
                }
                break;
            case 3:
                string = getResources().getString(R.string.game_already_download);
                i3 = R.drawable.selector_round_yellow_button;
                break;
            case 4:
                string = getResources().getString(R.string.game_download_pause);
                i2 = 0;
                break;
            case 5:
                string = getResources().getString(R.string.game_already_install);
                break;
            case 6:
                string = getResources().getString(R.string.game_already_install);
                break;
        }
        this.d.setBackgroundResource(i3);
        this.d.setText(string);
        this.e.setVisibility(i2);
    }

    public int getGameBtnStatus() {
        return this.a;
    }

    public void setProgress(int i, int i2) {
        if (this.e == null) {
            return;
        }
        if (i < 0 || i2 < 0 || i2 > i) {
            this.e.setVisibility(4);
        } else if (this.c != i2 || i != this.b) {
            this.c = i2;
            this.b = i;
            this.e.setMax(this.b);
            this.e.setProgress(this.c);
            this.d.setText(String.format(getResources().getString(R.string.game_downloading), new Object[]{Integer.valueOf(i2)}));
            if (i2 == i) {
                setGameBtnStatus(3);
            }
        }
    }

    public void setBackgroud(String str) {
    }

    protected void onMeasure(int i, int i2) {
        this.d.measure(i, i2);
        int measuredWidth = this.d.getMeasuredWidth();
        int measuredHeight = this.d.getMeasuredHeight();
        measuredWidth = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        measuredHeight = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        if (this.e != null) {
            this.e.measure(measuredWidth, measuredHeight);
        }
        super.onMeasure(measuredWidth, measuredHeight);
    }
}
