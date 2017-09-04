package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.buy.a.c;
import com.qq.reader.cservice.buy.a.d;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.download.book.g;
import com.qq.reader.cservice.download.book.h;
import com.qq.reader.module.bookstore.qnative.b.a;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PayHelper */
public class b implements Callback, com.qq.reader.cservice.buy.a.b, f {
    int a = 0;
    private e b = null;
    private a c;
    private WeakReferenceHandler d;
    private String e = "";
    private Activity f;
    private WebView g;
    private AlertDialog h = null;
    private final com.qq.reader.common.charge.voucher.a.a i = new com.qq.reader.common.charge.voucher.a.a();

    public b(Activity activity, WebView webView) {
        this.f = activity;
        this.g = webView;
        this.d = new WeakReferenceHandler(this);
    }

    public void a(String str, String str2) {
        this.b = (e) l.d(1001);
        this.c = new a(this.f, this.d);
        try {
            this.c.b(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((ReaderBaseActivity) this.f).mLoginNextTask = this.c.s();
        this.e = str2;
    }

    public void a(String str) {
        a(str, "");
    }

    private void a(String str, String str2, String str3) {
        g gVar = new g(str);
        gVar.e(str2);
        gVar.f(str3);
        h hVar = new h(this.f.getApplicationContext(), gVar);
        hVar.a(this);
        hVar.start();
    }

    private void a() {
        this.c.u();
        ((ReaderBaseActivity) this.f).mLoginNextTask = this.c.w();
    }

    public void a(g gVar) {
        if (this.c != null) {
            this.c.a(gVar.c());
            if (this.c.d() == Long.parseLong(gVar.e())) {
                a();
                this.g.post(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.g.loadUrl("javascript:" + this.a.e + "(0)");
                    }
                });
            }
        }
    }

    public void b(g gVar) {
        ((ReaderBaseActivity) this.f).getHandler().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                af.a(this.a.f.getApplicationContext(), this.a.f.getString(R.string.download_faile), 0).a();
            }
        });
    }

    public void c(g gVar) {
        ((ReaderBaseActivity) this.f).getHandler().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h = this.a.b();
                this.a.h.show();
            }
        });
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1217:
                a(String.valueOf(this.c.d()), this.c.c(), String.valueOf(message.obj));
                break;
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (!this.c.o()) {
                    c cVar = (c) message.obj;
                    try {
                        if (this.c != null) {
                            this.c.a(cVar.b());
                            if (this.c.d() == Long.parseLong(cVar.c())) {
                                a();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                a();
                break;
                break;
            case 8000011:
                a(this.h);
                break;
        }
        return false;
    }

    private AlertDialog b() {
        int i;
        CharSequence charSequence;
        final AlertDialog a = new AlertDialog.a(this.f).a();
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.book_buy_view_new, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_price);
        final String valueOf = String.valueOf(this.c.d());
        String k = this.c.k();
        int e = this.c.e();
        int g = this.c.g();
        String h = this.c.h();
        int i2 = this.c.i();
        Object j = this.c.j();
        final String c = this.c.c();
        if (g < 100 || i2 > 0) {
            g = (g * e) / 100;
            if (i2 <= 0 || i2 >= g) {
                String str = h;
                i = g;
            } else {
                i = i2;
            }
            if (TextUtils.isEmpty(j)) {
                textView.setVisibility(8);
            } else {
                textView.setText("(" + j + ")");
                textView.setVisibility(0);
            }
            textView3.setText(String.valueOf(i));
            Object obj = String.valueOf(e) + "书币";
            CharSequence spannableString = new SpannableString(obj);
            spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
            textView2.setText(spannableString);
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setText(String.valueOf(e));
            i = e;
        }
        textView = (TextView) inflate.findViewById(R.id.book_name);
        textView.setText(String.format(this.f.getResources().getString(R.string.buy_book_name), new Object[]{k}));
        int a2 = this.i.a();
        String b = this.i.b();
        if (a2 < 0) {
            progressBar.setVisibility(0);
            charSequence = "";
        } else {
            progressBar.setVisibility(8);
            Object obj2 = b;
        }
        textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
        textView.setText(charSequence);
        if (this.i.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this.f, textView, new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.i.g);
        }
        a.a(inflate);
        a.setTitle(this.f.getString(R.string.alert_dialog_buy));
        if (a2 < 0 || a2 >= i) {
            a.a((int) R.string.alert_dialog_buy_confirm, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ b d;

                public void onClick(DialogInterface dialogInterface, int i) {
                    a.c();
                    d dVar = new d(this.d.f, valueOf);
                    dVar.a(c);
                    dVar.a(this.d);
                    dVar.start();
                }
            });
            a.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    a.c();
                }
            });
        } else {
            this.a = i;
            a.a((int) R.string.alert_dialog_buy_balance_insufficient, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    a.c();
                    new JSPay(this.b.f).startCharge(this.b.f, this.b.a);
                }
            });
        }
        a.a(-1, (int) R.drawable.selector_orange_button);
        a.a(-2, (int) R.drawable.selector_white_button);
        b(a);
        return a;
    }

    public void a(final AlertDialog alertDialog) {
        if (alertDialog != null && alertDialog.isShowing()) {
            TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
            if (textView != null) {
                textView.setText(this.i.b());
            }
            if (this.i.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this.f, textView, new OnClickListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.i.g);
            }
            ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            Button c = alertDialog.c(-1);
            Button c2 = alertDialog.c(-2);
            if (c != null && c2 != null) {
                int e = (this.c.e() * this.c.g()) / 100;
                int i = this.c.i();
                if (i <= 0 || i >= e) {
                    i = e;
                }
                e = this.i.a();
                if (e < 0 || e >= i) {
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            alertDialog.c();
                            d dVar = new d(this.b.f, String.valueOf(this.b.c.d()));
                            dVar.a(this.b.c.c());
                            dVar.a(this.b);
                            dVar.start();
                        }
                    });
                    c.setText(this.f.getString(R.string.alert_dialog_buy_confirm));
                    c2.setVisibility(0);
                    c2.setText(this.f.getString(R.string.alert_dialog_cancel));
                    c2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            alertDialog.c();
                        }
                    });
                    return;
                }
                this.a = i;
                if (!ao.s(this.i.e)) {
                    View findViewById;
                    if (this.f.getResources().getConfiguration().orientation == 2) {
                        findViewById = alertDialog.findViewById(R.id.activity_info_land);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                    } else {
                        findViewById = alertDialog.findViewById(R.id.activity_info);
                        textView = (TextView) alertDialog.findViewById(R.id.activity_text);
                    }
                    findViewById.setVisibility(0);
                    textView.setText(this.i.e);
                    textView.setVisibility(0);
                }
                c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        alertDialog.c();
                        new JSPay(this.b.f).startCharge(this.b.f, this.b.a);
                    }
                });
                c.setText(this.f.getString(R.string.alert_dialog_buy_balance_insufficient));
                c2.setVisibility(8);
            }
        }
    }

    public void b(final AlertDialog alertDialog) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ b b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.i.a(aVar);
                Message obtainMessage = this.b.d.obtainMessage(8000011);
                obtainMessage.obj = alertDialog;
                this.b.d.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.c.d()), 0));
    }

    public void b(c cVar) {
        this.g.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.loadUrl("javascript:" + this.a.e + "(0)");
            }
        });
    }

    public void c(c cVar) {
        this.g.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.loadUrl("javascript:" + this.a.e + "(1)");
            }
        });
    }
}
