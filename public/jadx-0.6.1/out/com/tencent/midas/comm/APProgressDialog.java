package com.tencent.midas.comm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.pay.tool.APMidasCommMethod;

public class APProgressDialog extends ProgressDialog {
    private String a = "请稍候...";
    private TextView b = null;
    private Context c = null;

    public APProgressDialog(Context context) {
        super(context);
        this.c = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(APMidasCommMethod.getLayoutId(this.c, "unipay_layout_loadding"));
        ProgressBar progressBar = (ProgressBar) findViewById(APMidasCommMethod.getId(this.c, "unipay_progress"));
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.2f);
        alphaAnimation.setDuration(600);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        progressBar.setAnimation(alphaAnimation);
        alphaAnimation.start();
        this.b = (TextView) findViewById(APMidasCommMethod.getId(this.c, "unipay_id_LoadingTxt"));
        this.b.setText(this.a);
        setCancelable(false);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        cancel();
        return false;
    }

    public void setMessage(CharSequence charSequence) {
        super.setMessage(charSequence);
        this.a = String.valueOf(charSequence);
    }
}
