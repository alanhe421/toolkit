package com.qrcomic.widget;

import android.content.Context;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import b.a.a.a.a.a.h;
import com.qrcomic.manager.b;
import com.qrcomic.util.c.a;

public class LoadingDialog extends BaseDialog {
    private TextView a;

    public LoadingDialog(Context context) {
        super(context, h.LoadingDialogStyle);
        int[] a = b.a().b().f().f().b().a(false);
        View inflate = View.inflate(context, a[0], null);
        this.a = (TextView) inflate.findViewById(a[1]);
        this.a.setText("加载失败，正在退出…");
        setContentView(inflate, new LayoutParams(-2, -2));
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.height = a.a(context, 82);
        attributes.width = a.a(context, 250);
        getWindow().setAttributes(attributes);
    }

    public void a(String str) {
        this.a.setText(str);
    }
}
