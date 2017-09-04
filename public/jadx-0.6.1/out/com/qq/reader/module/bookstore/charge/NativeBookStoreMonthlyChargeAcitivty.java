package com.qq.reader.module.bookstore.charge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.charge.PayBridgeActivity;
import com.qq.reader.common.charge.b;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class NativeBookStoreMonthlyChargeAcitivty extends NativeBookStoreChargeBaseAcitivty {
    private final String n = "NativeBookStoreMonthlyChargeAcitivty";
    private int o;
    private boolean p = true;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.putString("KEY_JUMP_PAGENAME", "charge_openvip");
        a(this.b);
        if (c.c().d() == 1) {
            i.a("event_D91", null, ReaderApplication.getApplicationImp());
        } else {
            i.a("event_D75", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void f() {
        setContentView(R.layout.native_charge_layout);
        super.f();
    }

    protected boolean handleMessageImp(Message message) {
        return super.handleMessageImp(message);
    }

    public void doFunction(Bundle bundle) {
        try {
            String string = bundle.getString("charge_action");
            if ("charge_action_charge".equals(string)) {
                if (this.isOnResume) {
                    d dVar = (d) this.j;
                    if (dVar.c == 1) {
                        af.a((Context) this, (int) R.string.charge_month_onpen_error_bymessage, 0).a();
                        return;
                    }
                    int d = c.c().d();
                    int i = dVar.d;
                    int i2 = dVar.a;
                    this.o = bundle.getInt("chargenum");
                    int i3 = bundle.getInt("chargebookcoincost");
                    bundle.getString("chargenumintro");
                    String string2 = bundle.getString("servicecode");
                    String string3 = bundle.getString("productid");
                    if (d == 1) {
                        switch (i) {
                            case 0:
                                if (i2 >= i3) {
                                    a(this.o, this.p);
                                    return;
                                } else {
                                    b.a(this, this.p, string2, string3);
                                    return;
                                }
                            case 1:
                                a(this.o, this.p);
                                return;
                            case 2:
                                b.a(this, this.p, string2, string3);
                                return;
                            default:
                                return;
                        }
                    }
                    a(this.o, this.p);
                }
            } else if ("charge_action_autopay".equals(string)) {
                this.p = bundle.getBoolean("chargeautopay", true);
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("NativeBookStoreMonthlyChargeAcitivty", e.getMessage());
        }
    }

    private void a(int i, boolean z) {
        o.a((Activity) this, i, z);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        f.d("MonthVip", "bookcoinchage requestCode is " + i + " and resultCode is " + i2);
        if (i == 20002) {
            switch (i2) {
                case 0:
                    if (!(intent == null || intent.getBooleanExtra("charge_openfrombookcoin", false))) {
                        this.a.b(this.o);
                        break;
                    }
                case 2:
                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.charge_month_onpen_cancel, 0).a();
                    return;
                case 20003:
                    break;
                default:
                    af.a(ReaderApplication.getApplicationImp(), PayBridgeActivity.a(intent), 0).a();
                    return;
            }
            a(this.b);
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.charge_month_onpen_success, 0).a();
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.o));
            i.a("event_F207", hashMap, ReaderApplication.getApplicationImp());
        } else if (i != 20001) {
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
}
