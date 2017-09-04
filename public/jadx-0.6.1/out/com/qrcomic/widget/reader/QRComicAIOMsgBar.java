package com.qrcomic.widget.reader;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qrcomic.a.h;
import com.qrcomic.manager.b;

public class QRComicAIOMsgBar extends TextView implements OnClickListener {
    private Context a;
    private Intent b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private boolean k = false;
    private h l;

    public QRComicAIOMsgBar(Context context) {
        super(context);
        this.a = context;
        setOnClickListener(this);
        this.l = b.a().b();
    }

    public QRComicAIOMsgBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        setOnClickListener(this);
        this.l = b.a().b();
    }

    public void setComicInfo(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        this.e = str;
        this.d = str2;
        this.f = str3;
        this.g = str4;
        this.h = i;
        this.i = i2;
        this.j = i3;
    }

    public void setPlayerLockState(boolean z) {
        this.k = z;
    }

    public void onClick(View view) {
        if (this.e == null) {
            this.e = "";
        }
        this.b.putExtra("comicName", this.e);
        this.b.putExtra(ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, this.d);
        this.b.putExtra("type", this.j);
        this.b.putExtra("fromMessage", true);
        if (this.c == 1) {
            this.a.sendBroadcast(this.b);
        } else {
            this.a.startActivity(this.b);
        }
    }
}
