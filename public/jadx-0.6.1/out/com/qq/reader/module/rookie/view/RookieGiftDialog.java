package com.qq.reader.module.rookie.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.rookie.dataloader.RookieGiftDialogTask;
import com.qq.reader.qurl.b;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RookieGiftDialog extends AlertDialog implements Callback {
    String a;
    int b;
    String c;
    int d;
    String e;
    private final String f = "RookieGiftDialog";
    private Activity g = null;
    private WeakReferenceHandler h;
    private int i = 0;

    public RookieGiftDialog(Activity activity, int i) {
        super(activity);
        this.g = activity;
        this.h = new WeakReferenceHandler(this);
        this.i = i;
    }

    public void a() {
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.rookie_gift_open_monthly, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_origin_price);
        TextView textView2 = (TextView) inflate.findViewById(R.id.price_desc);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_user_balance);
        a(inflate);
        setTitle(this.a);
        textView.setText(this.b + this.g.getString(R.string.coin_name));
        textView2.setText(this.c);
        textView3.setText(this.g.getString(R.string.buy_book_balance, new Object[]{this.d + this.g.getString(R.string.coin_name)}));
        a(-1, R.drawable.selector_orange_button);
        if (this.d < this.b) {
            a(R.string.alert_dialog_not_enough_balance, new OnClickListener(this) {
                final /* synthetic */ RookieGiftDialog a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    new JSPay(this.a.g).startCharge(this.a.g, this.a.b);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "1");
                    i.a("event_F201", hashMap, this.a.getContext());
                }
            });
        } else {
            a(R.string.alert_dialog_open_confirm, new OnClickListener(this) {
                final /* synthetic */ RookieGiftDialog a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                    this.a.f();
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "2");
                    i.a("event_F201", hashMap, this.a.getContext());
                }
            });
        }
    }

    public void b() {
        g.a().a(new RookieGiftDialogTask(new c(this) {
            final /* synthetic */ RookieGiftDialog a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    com.qq.reader.common.monitor.debug.c.e("RookieGiftDialog", str);
                    JSONObject jSONObject = new JSONObject(str);
                    this.a.a = jSONObject.optString("monthDesc");
                    this.a.b = jSONObject.optInt("price");
                    this.a.c = jSONObject.optString("priceDesc");
                    this.a.d = jSONObject.optInt("balance");
                    this.a.e = jSONObject.optString("openMonthUrl");
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    this.a.h.sendMessage(obtain);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }));
    }

    private void f() {
        try {
            com.qq.reader.qurl.c.a(this.g, this.e, new b(this) {
                final /* synthetic */ RookieGiftDialog a;

                {
                    this.a = r1;
                }

                public boolean a(Message message) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        int optInt = jSONObject.optInt("code");
                        final String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        this.a.h.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 b;

                            public void run() {
                                if (!TextUtils.isEmpty(optString)) {
                                    af.a(ReaderApplication.getApplicationImp().getApplicationContext(), optString, 0).a();
                                }
                            }
                        });
                        switch (optInt) {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                a();
                show();
                return true;
            default:
                return false;
        }
    }
}
