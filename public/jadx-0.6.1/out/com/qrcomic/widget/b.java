package com.qrcomic.widget;

import android.view.View;
import java.util.ArrayList;

/* compiled from: PanelRecycleBin */
public class b {
    private ArrayList<View> a = new ArrayList();

    public void a(View view) {
        if (this.a.size() < 3) {
            this.a.add(view);
        }
    }

    public View a() {
        if (this.a.size() > 0) {
            return (View) this.a.remove(0);
        }
        return null;
    }
}
