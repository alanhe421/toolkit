package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: NoteDialog */
public class q extends BaseDialog {
    a a;
    private Activity b = null;
    private Context c;
    private EditText d = null;
    private ImageView e;
    private Button i;

    /* compiled from: NoteDialog */
    public interface a {
        void a();

        void a(String str);
    }

    public q(Activity activity) {
        this.b = activity;
        this.c = activity;
        if (this.f == null) {
            a(activity, null, R.layout.remark_dialog, 0, true);
            this.f.getWindow().setSoftInputMode(16);
            this.f.getWindow().setFlags(1024, 1024);
            ((TextView) this.f.findViewById(R.id.profile_header_title)).setText("笔记");
            this.d = (EditText) this.f.findViewById(R.id.remark_edit_text);
            this.d.setHint(R.string.note_no_date_in_edit);
            this.i = (Button) this.f.findViewById(R.id.profile_header_right_button);
            this.i.setVisibility(0);
            this.i.setText("保存");
            this.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a.a(this.a.d.getText().toString().trim());
                    this.a.g();
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.cancel();
                        }
                    }, 500);
                }
            });
            this.e = (ImageView) this.f.findViewById(R.id.profile_header_left_back);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a.a();
                    this.a.g();
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.cancel();
                        }
                    }, 500);
                }
            });
        }
    }

    public void a(String str) {
        if (this.d != null) {
            this.d.setText(str);
            this.d.setSelection(str.length());
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void g() {
        ((InputMethodManager) this.c.getSystemService("input_method")).hideSoftInputFromWindow(this.d.getWindowToken(), 2);
    }
}
