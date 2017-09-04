package com.qq.reader.module.bookstore.charge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.charge.PayBridgeActivity;
import com.qq.reader.common.charge.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.charge.card.ChargeCustomInputLayout;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class NativeBookStoreChargeAcitivty extends NativeBookStoreChargeBaseAcitivty {
    private final String n = "NativeBookStoreChargeAcitivty";
    private int o = 0;
    private String p = "";
    private int q = 0;
    private int r;
    private final int s = 2;
    private ChargeCustomInputLayout t;
    private View u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.putString("KEY_JUMP_PAGENAME", "charge_bookcoin");
        this.o = this.b.getInt("charge_prevalue", 0);
        this.p = this.b.getString("chargetype");
        this.q = this.b.getInt("charge_redPacketValue", 0);
        Map hashMap = new HashMap();
        hashMap.put("type", "1");
        i.a("event_D114", hashMap, ReaderApplication.getApplicationImp());
        a(this.b);
    }

    protected void f() {
        setContentView(R.layout.native_charge_layout);
        super.f();
        this.u = findViewById(R.id.charge_shadow);
        this.t = (ChargeCustomInputLayout) findViewById(R.id.charge_custominput);
    }

    protected boolean handleMessageImp(Message message) {
        c cVar;
        switch (message.what) {
            case 500000:
            case 500001:
                try {
                    if (message.obj != null && (message.obj instanceof c)) {
                        cVar = (c) message.obj;
                        this.t.setUnitPrice(cVar.b);
                        this.t.setChargeItemList(cVar.a);
                        this.t.setInputCount(this.q);
                        this.t.setVisibility(0);
                        this.u.setVisibility(0);
                        break;
                    }
                } catch (Exception e) {
                    c.e("NativeBookStoreChargeAcitivty", e.getMessage());
                    this.mHandler.sendEmptyMessage(500004);
                    return true;
                }
            case 500004:
                if (this.r >= 3) {
                    this.r = 0;
                    break;
                }
                cVar = c.a((a) this);
                Message obtainMessage = this.mHandler.obtainMessage(500001);
                obtainMessage.obj = cVar;
                this.mHandler.sendMessage(obtainMessage);
                this.r++;
                return true;
        }
        return super.handleMessageImp(message);
    }

    protected void b() {
        super.b();
        this.t.setVisibility(8);
        this.u.setVisibility(8);
    }

    protected void d() {
        super.d();
        this.t.setVisibility(8);
        this.u.setVisibility(8);
    }

    public void doFunction(Bundle bundle) {
        if ("charge_action_charge".equals(bundle.getString("charge_action")) && this.isOnResume) {
            Map hashMap;
            int i = bundle.getInt("chargenum", 0);
            if (i > 0) {
                b.a(this, String.valueOf(i), null);
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(i));
                hashMap.put("type", "2");
                i.a("event_D115", hashMap, ReaderApplication.getApplicationImp());
            }
            hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(i));
            if ("danmaku".equals(this.p)) {
                i.a("event_Z18", hashMap, ReaderApplication.getApplicationImp());
            } else if ("gift".equals(this.p)) {
                i.a("event_Z7", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        f.d("MonthVip", "bookcoinchage requestCode is " + i + " and resultCode is " + i2);
        if (i == 20001) {
            switch (i2) {
                case 0:
                    this.t.setInputCount(0);
                    return;
                case 2:
                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.charge_cancel, 0).a();
                    return;
                default:
                    af.a(ReaderApplication.getApplicationImp(), PayBridgeActivity.a(intent), 0).a();
                    return;
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("charge_prevalue", this.o);
        bundle.putString("chargetype", this.p);
        bundle.putInt("charge_redPacketValue", this.q);
    }

    public Activity getFromActivity() {
        return this;
    }
}
