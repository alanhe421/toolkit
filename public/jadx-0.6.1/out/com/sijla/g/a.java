package com.sijla.g;

import android.content.Context;
import com.sijla.g.a.b;
import com.sijla.g.a.c;
import com.sijla.g.a.d;
import java.util.ArrayList;
import java.util.List;

public class a {
    private Context a;
    private List<b> b = new ArrayList();

    public a(Context context) {
        this.a = context;
        a();
    }

    private void a() {
        if (this.b != null) {
            this.b.add(new d());
            this.b.add(new com.sijla.g.a.a());
            this.b.add(new c());
        }
    }

    public void a(final String str) {
        if (this.b != null) {
            com.sijla.c.c.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    for (b a : this.b.b) {
                        a.a(this.b.a, str);
                    }
                }
            });
        }
    }
}
