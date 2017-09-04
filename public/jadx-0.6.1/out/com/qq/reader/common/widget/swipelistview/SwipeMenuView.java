package com.qq.reader.common.widget.swipelistview;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SwipeMenuView extends LinearLayout implements OnClickListener {
    private SwipeMenuLayout a;
    private a b;
    private a c;
    private int d;

    public interface a {
        void a(SwipeMenuView swipeMenuView, a aVar, int i);
    }

    public int getPosition() {
        return this.d;
    }

    public void setPosition(int i) {
        this.d = i;
    }

    public void onClick(View view) {
        if (this.c != null && this.a.a()) {
            this.c.a(this, this.b, view.getId());
        }
    }

    public a getOnSwipeItemClickListener() {
        return this.c;
    }

    public void setOnSwipeItemClickListener(a aVar) {
        this.c = aVar;
    }

    public void setLayout(SwipeMenuLayout swipeMenuLayout) {
        this.a = swipeMenuLayout;
    }
}
