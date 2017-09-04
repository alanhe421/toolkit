package com.qrcomic.screenshot.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import com.qrcomic.widget.HorizontalListView;

public class QRDoodleHorizontalListView extends HorizontalListView {
    public QRDoodleHorizontalListView(Context context) {
        super(context);
    }

    public QRDoodleHorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @TargetApi(9)
    protected void a() {
        super.a();
        setOverScrollMode(2);
    }

    public int getOverScrollMode() {
        if (VERSION.SDK_INT >= 9) {
            return super.getOverScrollMode();
        }
        return 2;
    }
}
