package com.qq.reader.view;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: ReaderWebPopDialog */
public class ag extends BaseDialog implements OnClickListener {
    private TextView a;
    private TextView b;
    private View c;
    private Button d;
    private a e;

    /* compiled from: ReaderWebPopDialog */
    public interface a {
        void a();
    }

    public ag(Activity activity) {
        a(activity, null, R.layout.readerwebpopdialog, 0, false);
        g();
        h();
    }

    private void g() {
        this.a = (TextView) this.f.findViewById(R.id.reader_popwebdialog_title);
        this.b = (TextView) this.f.findViewById(R.id.reader_popwebdialog_content);
        this.c = this.f.findViewById(R.id.reader_popwebdialog_close);
        this.d = (Button) this.f.findViewById(R.id.reader_popwebdialog_confirm);
    }

    private void h() {
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public void a(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.a.setText(charSequence);
        this.b.setText(charSequence2);
        this.d.setText(charSequence3);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.reader_popwebdialog_confirm && this.e != null) {
            this.e.a();
        }
        dismiss();
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
