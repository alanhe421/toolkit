package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.a;
import android.support.v4.view.a.c;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: RecyclerViewAccessibilityDelegate */
public class g extends a {
    final RecyclerView b;
    final a c = new a(this) {
        final /* synthetic */ g b;

        {
            this.b = r1;
        }

        public void a(View view, c cVar) {
            super.a(view, cVar);
            if (!this.b.c() && this.b.b.getLayoutManager() != null) {
                this.b.b.getLayoutManager().a(view, cVar);
            }
        }

        public boolean a(View view, int i, Bundle bundle) {
            if (super.a(view, i, bundle)) {
                return true;
            }
            if (this.b.c() || this.b.b.getLayoutManager() == null) {
                return false;
            }
            return this.b.b.getLayoutManager().a(view, i, bundle);
        }
    };

    public g(RecyclerView recyclerView) {
        this.b = recyclerView;
    }

    private boolean c() {
        return this.b.p();
    }

    public boolean a(View view, int i, Bundle bundle) {
        if (super.a(view, i, bundle)) {
            return true;
        }
        if (c() || this.b.getLayoutManager() == null) {
            return false;
        }
        return this.b.getLayoutManager().a(i, bundle);
    }

    public void a(View view, c cVar) {
        super.a(view, cVar);
        cVar.b(RecyclerView.class.getName());
        if (!c() && this.b.getLayoutManager() != null) {
            this.b.getLayoutManager().a(cVar);
        }
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        super.d(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if ((view instanceof RecyclerView) && !c()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().a(accessibilityEvent);
            }
        }
    }

    a b() {
        return this.c;
    }
}
