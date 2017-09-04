package android.support.v4.view;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

/* compiled from: PagerTitleStripIcs */
class v {

    /* compiled from: PagerTitleStripIcs */
    private static class a extends SingleLineTransformationMethod {
        private Locale a;

        public a(Context context) {
            this.a = context.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            return transformation != null ? transformation.toString().toUpperCase(this.a) : null;
        }
    }

    public static void a(TextView textView) {
        textView.setTransformationMethod(new a(textView.getContext()));
    }
}
