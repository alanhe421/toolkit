package com.qq.reader.view;

import android.app.Activity;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.q;
import com.qq.reader.plugin.audiobook.core.l;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

/* compiled from: TimerSelectDialog */
public class ao extends BaseDialog {
    public int a = -1;
    private LinearLayout b;
    private b c;
    private TextView d;
    private a e;

    /* compiled from: TimerSelectDialog */
    private static class a implements com.qq.reader.common.utils.q.a {
        private WeakReference<ao> a;

        public a(ao aoVar) {
            this.a = new WeakReference(aoVar);
        }

        public void a(long j) {
            if (this.a != null) {
                ao aoVar = (ao) this.a.get();
                if (aoVar != null) {
                    aoVar.a(j);
                    if (aoVar.c != null) {
                        aoVar.c.a(j);
                    }
                }
            }
        }

        public void a() {
            if (this.a != null) {
                ao aoVar = (ao) this.a.get();
                if (aoVar != null) {
                    aoVar.h();
                    aoVar.a(0);
                    com.qq.reader.module.c.a.a(aoVar.a);
                    if (aoVar.c != null) {
                        aoVar.c.a();
                    }
                    if (aoVar.a == 1) {
                        if (l.a != null) {
                            try {
                                l.a.c();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (aoVar.a == 2) {
                        com.qq.reader.common.utils.ao.u();
                    }
                }
            }
        }
    }

    /* compiled from: TimerSelectDialog */
    public interface b extends com.qq.reader.common.utils.q.a {
        void b();

        void c();
    }

    private void a(long j) {
        if (this.d == null) {
            return;
        }
        if (j == 0) {
            this.d.setText("");
        } else {
            this.d.setText(com.qq.reader.module.c.a.a(j));
        }
    }

    public ao(Activity activity, b bVar, int i) {
        int i2 = 0;
        this.a = i;
        if (this.f == null) {
            a(activity, null, R.layout.listen_book_timer_dlg, true, false, true);
            this.f.getWindow().addFlags(2);
        }
        this.d = (TextView) a(R.id.tv_remainningtime);
        this.b = (LinearLayout) a(R.id.timing_ctrl);
        this.e = new a(this);
        h();
        while (i2 < this.b.getChildCount()) {
            ((TextView) this.b.getChildAt(i2)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ao a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!view.isSelected()) {
                        this.a.a(view);
                    }
                }
            });
            i2++;
        }
        this.c = bVar;
    }

    public void dismiss() {
        super.dismiss();
        if (this.e != null) {
            this.e.a = null;
            this.e = null;
        }
        com.qq.reader.module.c.a.b(this.a).a(this.c);
    }

    private void g() {
        q b = com.qq.reader.module.c.a.b(this.a);
        if (b != null) {
            b.a(this.e);
            a(b.b());
            if (this.c != null) {
                this.c.a(b.b());
            }
            int c = b.c();
            for (int i = 0; i < this.b.getChildCount(); i++) {
                View childAt = this.b.getChildAt(i);
                if (c == i) {
                    childAt.setSelected(true);
                } else {
                    childAt.setSelected(false);
                }
            }
            return;
        }
        this.b.getChildAt(0).setSelected(true);
        a(0);
    }

    public void f_() {
        super.f_();
        g();
    }

    private void h() {
        for (int i = 0; i < this.b.getChildCount(); i++) {
            TextView textView = (TextView) this.b.getChildAt(i);
            int intValue = Integer.valueOf((String) textView.getTag()).intValue();
            if (intValue > 0) {
                textView.setText(intValue + "分钟");
                textView.setSelected(false);
            } else {
                textView.setText("不开启");
                textView.setSelected(true);
            }
        }
    }

    private void a(View view) {
        h();
        i();
        for (int i = 0; i < this.b.getChildCount(); i++) {
            View childAt = this.b.getChildAt(i);
            if (view.equals(childAt)) {
                ((TextView) view).setSelected(true);
                com.qq.reader.module.c.a.a(this.a, Integer.valueOf((String) view.getTag()).intValue() * 60000, this.e, i);
                this.c.b();
            } else {
                childAt.setSelected(false);
            }
        }
    }

    private void i() {
        com.qq.reader.module.c.a.a(this.a);
        if (this.c != null) {
            this.c.c();
        }
        if (this.d != null) {
            this.d.setText("");
        }
    }
}
