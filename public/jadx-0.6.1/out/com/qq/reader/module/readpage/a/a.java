package com.qq.reader.module.readpage.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.readpage.j;
import com.qq.reader.module.readpage.s;
import com.qq.reader.qurl.c;
import com.qq.reader.readengine.kernel.c.d;
import com.qq.reader.readengine.kernel.e;
import com.tencent.feedback.proguard.R;

/* compiled from: ReaderPageLayerAdv */
public class a extends s {
    private TextView f = ((TextView) this.a.findViewById(R.id.bookshelf_advheader_title));
    private TextView g = ((TextView) this.a.findViewById(R.id.bookshelf_advheader_lefticon));
    private View h = this.a.findViewById(R.id.readerpage_adv_area);

    public a(Context context) {
        this.a = LayoutInflater.from(context).inflate(R.layout.readerpage_adv_layer, null);
        this.a.setLayoutParams(new LayoutParams(-1, -2));
        this.a.setVisibility(8);
    }

    protected boolean a(int i) {
        return i == 104;
    }

    protected void a(d dVar) {
        if (this.a != null) {
            e h = dVar.h();
            float f = h.f() + ((float) j.k());
            if (h instanceof com.qq.reader.readengine.kernel.b.a) {
                final com.qq.reader.cservice.adv.a k = ((com.qq.reader.readengine.kernel.b.a) h).k();
                if (k != null) {
                    this.f.setText(k.e());
                    CharSequence u = k.u();
                    if (!TextUtils.isEmpty(u)) {
                        this.g.setText(u);
                    }
                    this.h.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ a b;

                        public void onClick(View view) {
                            if (c.a(k.h()) && this.b.f() != null) {
                                try {
                                    c.a(this.b.f(), k.h());
                                    i.a("event_B151", null, ReaderApplication.getApplicationImp());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
            this.a.setPadding(j.j(), (int) f, j.i(), 0);
            this.a.requestLayout();
        }
    }
}
