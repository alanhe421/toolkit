package com.qq.reader.module.redpacket.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.redpacket.model.RedPacketMessage;
import com.tencent.feedback.proguard.R;

public class RedPacketMessageView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private RelativeLayout c;
    private RedPacketMessage d;
    private a e;

    public RedPacketMessageView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.redpacket_square_message_view_layout, this, true);
        a();
    }

    public RedPacketMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.redpacket_square_message_view_layout, this, true);
        a();
    }

    public void setEventListener(a aVar) {
        this.e = aVar;
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.redpacket_square_message_icon);
        this.b = (TextView) findViewById(R.id.redpacket_square_message_text);
        this.c = (RelativeLayout) findViewById(R.id.root_view);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketMessageView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.d != null) {
                    if (this.a.d.e() == 1) {
                        o.a(this.a.e.getFromActivity(), this.a.d.f(), 4);
                    } else {
                        o.a(this.a.e.getFromActivity(), this.a.d.d(), this.a.d.c(), -1, false, null);
                    }
                    i.a("event_D225", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            }
        });
    }

    public void a(String str, String str2) {
        this.d = null;
        setText(str);
        a(this.a, str2);
    }

    public void a(RedPacketMessage redPacketMessage) {
        this.d = redPacketMessage;
        setText(redPacketMessage.b());
        a(this.a, redPacketMessage.a());
    }

    public void setText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.setVisibility(8);
            return;
        }
        this.b.setVisibility(0);
        if (this.b != null) {
            this.b.setText(str);
        }
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        c.a(getContext()).a(str, imageView, com.qq.reader.common.imageloader.a.a().b());
    }
}
