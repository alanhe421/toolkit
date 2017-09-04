package com.qrcomic.widget.reader;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
public class QRComicScrollReaderPager extends View implements Callback {
    public boolean handleMessage(Message message) {
        int i = message.what;
        return true;
    }

    public QRComicScrollReaderPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
