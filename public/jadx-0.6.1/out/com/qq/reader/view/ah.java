package com.qq.reader.view;

import android.app.Activity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.tencent.feedback.proguard.R;

/* compiled from: SearchReleaseOrderDialog */
public class ah extends BaseDialog {
    private Button a = ((Button) a(R.id.sureButton));
    private Button b = ((Button) a(R.id.cancelButton));
    private EditText c = ((EditText) a(R.id.input_bookname_edittext));
    private EditText d;

    public ah(Activity activity, String str) {
        a(activity, null, R.layout.dialog_search_releaseorder, 1, true);
        this.c.setText(str);
        Selection.setSelection(this.c.getText(), this.c.getText().length());
        this.d = (EditText) a(R.id.input_authorname_edittext);
        this.c.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ ah a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    this.a.a.setEnabled(false);
                } else {
                    this.a.a.setEnabled(true);
                }
            }
        });
        this.f.getWindow().setSoftInputMode(16);
    }

    public String g() {
        return this.c.getText().toString().trim();
    }

    public String h() {
        return this.d.getText().toString().trim();
    }

    public void a(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void b(OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }
}
