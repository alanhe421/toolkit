package com.qq.reader.view.metro;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.view.BaseDialog;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: MetroAbsBaseDialog */
public abstract class b extends BaseDialog {
    protected TextView b;
    protected ListView c;
    protected a d = null;
    protected ArrayList<MetroItem> e;

    /* compiled from: MetroAbsBaseDialog */
    public interface a {
        void a(MetroItem metroItem);
    }

    protected abstract void a(Activity activity, int i, String str);

    public b(Activity activity, int i, String str, String str2, ArrayList<MetroItem> arrayList) {
        a(activity, null, R.layout.category_dialog, true, false, true);
        this.f.getWindow().addFlags(2);
        this.b = (TextView) this.f.findViewById(R.id.category_op_name);
        this.c = (ListView) this.f.findViewById(R.id.category_list);
        this.e = arrayList;
        a(activity, i, str2);
        this.b.setText(str);
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
