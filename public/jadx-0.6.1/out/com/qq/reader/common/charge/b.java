package com.qq.reader.common.charge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.MonthVipGiftTask;
import com.qq.reader.common.readertask.protocol.OpenMonthVipTask;
import com.qq.reader.common.readertask.protocol.OpenQQMonthVipTask;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PayProxy */
public class b {
    private WeakReference<Handler> a;

    public void a(int i) {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask();
        readerProtocolJSONTask.setUrl(e.dl + "?month=" + i);
        readerProtocolJSONTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Handler handler;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("code");
                    f.a("vip code", i + "");
                    if (i == 0) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("config");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            com.qq.reader.module.bookstore.charge.b bVar = new com.qq.reader.module.bookstore.charge.b();
                            bVar.a(optJSONArray.optJSONObject(0));
                            bVar.a(jSONObject.optInt("balance"));
                            Message obtain = Message.obtain();
                            obtain.what = 400010;
                            obtain.obj = bVar;
                            if (this.a.a != null) {
                                handler = (Handler) this.a.a.get();
                                if (handler != null) {
                                    handler.sendMessage(obtain);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                    if (this.a.a != null) {
                        handler = (Handler) this.a.a.get();
                        if (handler != null) {
                            handler.sendEmptyMessage(400011);
                        }
                    }
                } catch (Exception e) {
                    if (this.a.a != null) {
                        handler = (Handler) this.a.a.get();
                        if (handler != null) {
                            handler.sendEmptyMessage(400011);
                        }
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.a != null) {
                    Handler handler = (Handler) this.a.a.get();
                    if (handler != null) {
                        handler.sendEmptyMessage(400011);
                    }
                }
            }
        });
        g.a().a(readerProtocolJSONTask);
    }

    public void a(final int i, boolean z) {
        ReaderTask openQQMonthVipTask;
        f.a("MonthVip", "openMonthVipByBookCoin");
        a c = com.qq.reader.common.login.c.c();
        if (c.d() == 1 || c.d() == 50) {
            openQQMonthVipTask = new OpenQQMonthVipTask(i, z);
        } else {
            openQQMonthVipTask = new OpenMonthVipTask(i, z);
        }
        openQQMonthVipTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ b b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Handler handler;
                try {
                    f.a("MonthVip", "openMonthVipByBookCoin success and msg " + str);
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("code");
                    Message obtain = Message.obtain();
                    f.a("vip code", i + "");
                    if (i == 0) {
                        obtain.what = 400003;
                    } else if (i == -1) {
                        obtain.what = 400005;
                    } else if (i == -3) {
                        obtain.what = 400004;
                    } else {
                        obtain.what = 400005;
                    }
                    obtain.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                    obtain.arg1 = i;
                    if (this.b.a != null) {
                        handler = (Handler) this.b.a.get();
                        if (handler != null) {
                            handler.sendMessage(obtain);
                        }
                    }
                } catch (Exception e) {
                    if (this.b.a != null) {
                        handler = (Handler) this.b.a.get();
                        if (handler != null) {
                            handler.sendEmptyMessage(400005);
                        }
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                f.a("MonthVip", "openMonthVipByBookCoin error and msg " + exception.toString());
                if (this.b.a != null) {
                    Handler handler = (Handler) this.b.a.get();
                    if (handler != null) {
                        handler.sendEmptyMessage(400005);
                    }
                }
            }
        });
        g.a().a(openQQMonthVipTask);
    }

    public void a(Handler handler) {
        this.a = new WeakReference(handler);
    }

    public static void a(Activity activity, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("pay_requestcode", 2);
        bundle.putString("uin", d.R(activity));
        bundle.putString("skey", com.qq.reader.common.login.c.c().a(activity.getApplicationContext()));
        if (com.qq.reader.common.c.a.Y.equals(ao.h((Context) activity))) {
            bundle.putString("offerid", "1450007037");
        } else {
            bundle.putString("offerid", "1450000219");
        }
        bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + ao.h((Context) activity) + "-android-areader");
        bundle.putString("saveValue", str);
        bundle.putBoolean("isCanChange", false);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("paychannel", str2);
        }
        PayBridgeActivity.a(activity, bundle, 20001);
    }

    public static void a(Activity activity, boolean z, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("pay_requestcode", 3);
        bundle.putString("uin", d.R(activity));
        bundle.putString("skey", com.qq.reader.common.login.c.c().a(activity.getApplicationContext()));
        if (com.qq.reader.common.c.a.Y.equals(ao.h((Context) activity))) {
            bundle.putString("offerid", "1450012010");
        } else {
            bundle.putString("offerid", "1450011988");
        }
        bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + ao.h((Context) activity) + "-android-areader");
        bundle.putString("saveValue", String.valueOf(1));
        bundle.putBoolean("isCanChange", false);
        bundle.putBoolean("autoPay", z);
        bundle.putString("servicecode", str);
        bundle.putString("productid", str2);
        PayBridgeActivity.b(activity, bundle, 20002);
    }

    public void b(int i) {
        f.a("MonthVip", "giveQQMonthVipGift");
        ReaderTask monthVipGiftTask = new MonthVipGiftTask(i);
        monthVipGiftTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                f.a("MonthVip", "giveQQMonthVipGift success and msg " + str);
                StringBuilder stringBuilder;
                try {
                    int optInt = new JSONObject(str).optInt("code");
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("monthRechargeGift server code : ");
                    stringBuilder.append(optInt);
                    j.b(stringBuilder.toString());
                } catch (Exception e) {
                    f.a("MonthVip", e.getMessage());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("monthRechargeGift exception : ");
                    stringBuilder.append(e.getMessage());
                    j.b(stringBuilder.toString());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                f.a("MonthVip", "giveQQMonthVipGift error and msg " + exception.getMessage());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("monthRechargeGift connectionError error : ");
                stringBuilder.append(exception.getMessage());
                j.b(stringBuilder.toString());
            }
        });
        g.a().a(monthVipGiftTask);
    }
}
