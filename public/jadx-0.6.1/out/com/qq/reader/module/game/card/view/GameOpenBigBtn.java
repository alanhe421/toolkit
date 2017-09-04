package com.qq.reader.module.game.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.tencent.feedback.proguard.R;

public class GameOpenBigBtn extends GameOpenBtn {
    private ImageView e;

    public GameOpenBigBtn(Context context) {
        super(context);
    }

    public GameOpenBigBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GameOpenBigBtn(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Context context, AttributeSet attributeSet, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.game_open_big_btn, this);
        this.e = (ImageView) inflate.findViewById(R.id.button_img);
        this.d = (TextView) inflate.findViewById(R.id.game_status);
    }

    public void setGameBtnStatus(int i) {
        CharSequence string = getResources().getString(R.string.game_already_install);
        this.a = i;
        switch (i) {
            case 0:
                string = getResources().getString(R.string.game_dialog_start_download);
                break;
            case 1:
                string = String.format(getResources().getString(R.string.game_dialog_downloading), new Object[]{Integer.valueOf(this.c)});
                break;
            case 2:
                string = getResources().getString(R.string.game_download_waiting);
                break;
            case 3:
                string = getResources().getString(R.string.game_already_download);
                break;
            case 4:
                string = getResources().getString(R.string.game_download_pause);
                break;
            case 5:
                string = getResources().getString(R.string.game_dialog_already_install);
                break;
            case 6:
                string = getResources().getString(R.string.game_already_install);
                break;
        }
        this.d.setText(string);
    }

    public int getGameBtnStatus() {
        return this.a;
    }

    public void setProgress(int i, int i2) {
        if (i >= 0 && i2 >= 0 && i2 <= i) {
            if (this.c != i2 || i != this.b) {
                this.c = i2;
                this.b = i;
                this.d.setText(String.format(getResources().getString(R.string.game_dialog_downloading), new Object[]{Integer.valueOf(i2)}));
                if (i2 == i) {
                    setGameBtnStatus(3);
                }
            }
        }
    }

    public void setButtonImg(String str) {
        c.a(getContext()).a(str, this.e, a.a().c(com.qq.reader.common.c.a.bU - ((int) (2.0f * getContext().getResources().getDimension(R.dimen.common_dp_16)))), new e<String, b>(this) {
            final /* synthetic */ GameOpenBigBtn a;

            {
                this.a = r1;
            }

            public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
                return false;
            }

            public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
                return false;
            }
        });
    }

    public void setButtonTextColor(int i) {
        this.d.setTextColor(i);
    }

    public void setButtonTextVisible(boolean z) {
        if (z) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }
}
