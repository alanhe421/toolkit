package com.qq.reader.module.bookshelf.a.a;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.feedback.proguard.R;

/* compiled from: CategoryRemoveDialog */
public class f extends BaseDialog {
    TextView a;
    Button b;
    Button c;
    a d;

    /* compiled from: CategoryRemoveDialog */
    public interface a {
        void a(MetroItem metroItem);
    }

    public f(Activity activity, final MetroItem metroItem) {
        if (this.f == null) {
            a(activity, null, R.layout.category_dialog_remove, true, false, true);
            this.f.getWindow().addFlags(2);
            this.a = (TextView) this.f.findViewById(R.id.text_op_name);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(activity.getResources().getString(R.string.txt_catgory_one_remove_1));
            stringBuffer.append(metroItem.getDisplayName());
            stringBuffer.append(activity.getResources().getString(R.string.txt_catgory_one_remove_2));
            this.a.setText(stringBuffer.toString());
            this.b = (Button) this.f.findViewById(R.id.sureButton);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f b;

                public void onClick(View view) {
                    this.b.d.a(metroItem);
                    this.b.cancel();
                }
            });
            this.c = (Button) this.f.findViewById(R.id.cancelButton);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.cancel();
                }
            });
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
