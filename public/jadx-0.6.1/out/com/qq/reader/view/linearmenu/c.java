package com.qq.reader.view.linearmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: LinearMenuOfCommentReport */
public class c extends a {
    private View a = LayoutInflater.from(this.k).inflate(R.layout.comment_report_linearmenu_header_ui, null);
    private TextView b = ((TextView) this.a.findViewById(R.id.textView9));

    /* compiled from: LinearMenuOfCommentReport */
    private class a extends a$a {
        final /* synthetic */ c i;

        public a(c cVar, Context context) {
            this.i = cVar;
            super(cVar, context);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.i.e(), R.layout.comment_report_listview_item_ui, null);
            }
            if (this.a != null && i < this.a.size()) {
                Button button = (Button) view.findViewById(R.id.btn_report);
                button.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        if (this.b.i.l != null && this.b.b != null && i < this.b.b.size()) {
                            this.b.i.l.a(((Integer) this.b.b.get(i)).intValue(), null);
                        }
                    }
                });
                button.setText((CharSequence) this.a.get(i));
            }
            return view;
        }
    }

    public c(Activity activity) {
        super(activity);
        this.i.addHeaderView(this.a);
        h();
    }

    public void c(int i) {
        this.b.setText(i);
    }

    public void h() {
        this.j = new a(this, this.k);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (j >= 0) {
                    if (this.a.l != null) {
                        this.a.l.a((int) j, (Bundle) view.getTag());
                    }
                    this.a.cancel();
                }
            }
        });
    }
}
