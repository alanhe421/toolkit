package com.qq.reader.module.findhome.c;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.module.findhome.a.d;
import com.qq.reader.module.findhome.base.b;
import com.tencent.feedback.proguard.R;

/* compiled from: FindHomeExpandLiveVH */
public class c extends b<d> {
    private TextView q;
    private ImageView r;
    private TextView s;
    private ImageView t;

    public c(View view) {
        super(view);
        this.s = (TextView) view.findViewById(R.id.findhome_playtimes);
        this.t = (ImageView) view.findViewById(R.id.findhome_playtimes_icon);
        this.q = (TextView) view.findViewById(R.id.findhome_videolength);
        this.r = (ImageView) view.findViewById(R.id.findhome_videolength_mask);
    }

    public void a(d dVar) {
        super.a(dVar);
        if (this.p != null) {
            this.p.setVisibility(0);
        }
        switch (dVar.k()) {
            case 0:
                this.q.setText(dVar.j());
                this.q.setBackgroundResource(0);
                this.r.setImageResource(R.drawable.findhome_expand_cover_mask);
                this.t.setImageResource(R.drawable.findhome_expand_live_icon_end);
                this.s.setText(dVar.i());
                return;
            case 1:
                this.q.setText("");
                this.q.setBackgroundResource(R.drawable.findhome_expand_live_living);
                this.r.setImageResource(0);
                this.t.setImageResource(R.drawable.findhome_expand_live_icon_living);
                this.s.setText(dVar.i());
                return;
            case 2:
                this.q.setText("");
                this.q.setBackgroundResource(R.drawable.findhome_expand_live_ready);
                this.r.setImageResource(0);
                this.t.setImageResource(0);
                this.s.setText("");
                return;
            default:
                if (this.p != null) {
                    this.p.setVisibility(8);
                    return;
                }
                return;
        }
    }
}
