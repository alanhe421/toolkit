package com.qq.reader.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;

/* compiled from: ShareDialog */
class aj$6 implements OnCancelListener {
    final /* synthetic */ ReaderNetTask a;
    final /* synthetic */ aj b;

    aj$6(aj ajVar, ReaderNetTask readerNetTask) {
        this.b = ajVar;
        this.a = readerNetTask;
    }

    public void onCancel(DialogInterface dialogInterface) {
        g.a().b(this.a);
    }
}
