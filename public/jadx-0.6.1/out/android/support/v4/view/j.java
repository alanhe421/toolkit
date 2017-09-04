package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

/* compiled from: LayoutInflaterCompatBase */
class j {

    /* compiled from: LayoutInflaterCompatBase */
    static class a implements Factory {
        final m a;

        a(m mVar) {
            this.a = mVar;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.a.a(null, str, context, attributeSet);
        }

        public String toString() {
            return getClass().getName() + "{" + this.a + "}";
        }
    }

    static void a(LayoutInflater layoutInflater, m mVar) {
        layoutInflater.setFactory(mVar != null ? new a(mVar) : null);
    }
}
