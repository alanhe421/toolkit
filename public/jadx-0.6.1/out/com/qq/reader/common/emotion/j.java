package com.qq.reader.common.emotion;

import android.content.Context;
import android.view.View;
import com.qq.reader.common.emotion.EmoticonLinearLayout.c;
import com.qq.reader.common.emotion.f.a;
import java.util.List;

/* compiled from: SystemEmoticonPanelViewBinder */
public class j extends f {
    public static int e = 6;
    private c f;
    private List<d> g = null;
    private c h;

    public j(Context context, c cVar, int i) {
        super(context, 1, i);
        this.f = cVar;
    }

    public int a() {
        return 6;
    }

    protected int d(int i) {
        return 2007;
    }

    protected void a(View view, int i) {
        if (view != null && d(i) == 2007 && i < a()) {
            if (this.g == null) {
                this.g = i.a();
            }
            if (this.h == null) {
                this.h = new a(this, 2007);
                this.h.a(true);
                this.h.c(false);
                this.h.b(false);
                d dVar = new d();
                dVar.b = "delete";
                this.h.a(dVar);
            }
            EmoticonLinearLayout emoticonLinearLayout = (EmoticonLinearLayout) view;
            emoticonLinearLayout.setCallBack(this.f);
            emoticonLinearLayout.setAdapter(this.h);
            this.h.b(3, 7);
            this.h.a(i);
            this.h.a(this.g);
            this.h.a();
        }
    }
}
