package com.qq.reader.filebrowser.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class IconifiedTextView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private ImageView f;

    public ImageView getIcon() {
        return this.e;
    }

    public IconifiedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IconifiedTextView(Context context) {
        super(context);
    }

    public void a() {
        this.f = (ImageView) findViewById(R.id.fileicon);
        this.e = (ImageView) findViewById(R.id.instand_icone);
        this.a = (TextView) findViewById(R.id.filename);
        this.b = (TextView) findViewById(R.id.filetip);
        this.c = (TextView) findViewById(R.id.filetype_text);
        this.d = (TextView) findViewById(R.id.file_imported);
    }

    public void setDate(a aVar, Drawable drawable, Drawable drawable2) {
        if (drawable != null) {
            this.e.setVisibility(0);
            this.e.setImageDrawable(drawable);
            this.d.setVisibility(8);
        } else if (aVar.e() == 2 || aVar.e() == 4) {
            this.d.setVisibility(4);
            this.e.setVisibility(4);
        } else {
            this.d.setVisibility(0);
            this.e.setVisibility(4);
        }
        this.f.setImageDrawable(drawable2);
        this.a.setText(aVar.d());
        this.b.setText(aVar.f());
        String g = aVar.g();
        if (g == null || g.length() <= 0) {
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        this.c.setText(aVar.g());
    }
}
