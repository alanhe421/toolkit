package com.qq.reader.tinker;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.text.TextUtils;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ReaderConfigTask;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.upload.impl.TaskManager;
import org.json.JSONObject;

/* compiled from: HotFixManager */
public class b {
    private static b b;
    private String a = "HotFixManager";
    private long c;
    private Handler d = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 300019:
                    if (System.currentTimeMillis() - this.a.c > TaskManager.IDLE_PROTECT_TIME) {
                        this.a.c = System.currentTimeMillis();
                        if (!TextUtils.isEmpty(BaseBuildInfo.b)) {
                            c.e("Tinker.MainActivity", "request patchversion : " + BaseBuildInfo.b);
                            g.a().a(new ReaderConfigTask(BaseBuildInfo.b, new com.qq.reader.common.readertask.ordinal.c(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                    try {
                                        JSONObject jSONObject = new JSONObject(str);
                                        int optInt = jSONObject.optInt("code", -2);
                                        c.e("Tinker.MainActivity", "patch respon code : " + optInt);
                                        if (optInt == 0) {
                                            jSONObject = jSONObject.optJSONObject("patch");
                                            String optString = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
                                            Object optString2 = jSONObject.optString(SocialConstants.PARAM_URL);
                                            c.e("Tinker.MainActivity", "got patchversion : " + optString);
                                            if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString)) {
                                                if (optString.equals(a.a)) {
                                                    c.e("Tinker.MainActivity", "ignor request. because injectvertion is " + a.a);
                                                    a.b(ReaderApplication.getApplicationImp(), "");
                                                    a.a(ReaderApplication.getApplicationImp(), false);
                                                } else if (optString.equals(a.b)) {
                                                    c.e("Tinker.MainActivity", "ignor request. because DownloadVersion is " + a.b);
                                                } else {
                                                    String d = a.d(ReaderApplication.getApplicationImp());
                                                    if (TextUtils.isEmpty(d) || !d.equals(optString)) {
                                                        j.b("Tinker -- download version :" + optString);
                                                        d.a(this.a.a.d, optString, optString2);
                                                        return;
                                                    }
                                                    c.e("Tinker.MainActivity", "got diableTinkerid : " + d);
                                                    j.b("Tinker -- tinkercrash many times, diableTinkerid :" + d);
                                                }
                                            }
                                        } else if (Tinker.with(ReaderApplication.getApplicationImp()).isTinkerLoaded()) {
                                            j.b("Tinker -- got a revert tinkerid : " + a.d + ", set need clean flag");
                                            a.b(ReaderApplication.getApplicationImp(), a.d);
                                            a aVar = new a(ReaderApplication.getApplicationImp(), new a(this) {
                                                final /* synthetic */ AnonymousClass1 a;

                                                {
                                                    this.a = r1;
                                                }

                                                public void a() {
                                                    d.a(ReaderApplication.getApplicationImp());
                                                }
                                            });
                                        }
                                    } catch (Exception e) {
                                        c.e("MainActivity", e.getMessage());
                                    }
                                }

                                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                    this.a.a.c = 0;
                                }
                            }));
                            break;
                        }
                        c.e("Tinker.MainActivity", "got none patchversion,maybe debug mode ?");
                        return true;
                    }
                    break;
                case 11000601:
                    if (message.arg1 == 1 && message.obj != null) {
                        c.e("Tinker.MainActivity", "TinkerInstaller start to UpgradePatch");
                        j.b("Tinker -- start merge");
                        TinkerInstaller.onReceiveUpgradePatch(ReaderApplication.getApplicationImp(), message.obj.toString());
                        break;
                    }
            }
            return false;
        }
    });

    public void a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Looper.myQueue().addIdleHandler(new IdleHandler(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public boolean queueIdle() {
                    if (this.a.d.hasMessages(300019)) {
                        this.a.d.removeMessages(300019);
                    }
                    this.a.d.sendEmptyMessage(300019);
                    return false;
                }
            });
        }
    }

    public void a(PatchResult patchResult) {
        if (patchResult.isSuccess) {
            if (com.qq.reader.appconfig.b.f) {
                Toast.makeText(ReaderApplication.getApplicationImp(), "patch success, please restart process", 1).show();
            }
            c.e(this.a, "set PATCH_DOWNLOAD_VERSION  is " + d.b);
            a.b = d.b;
            c.e(this.a, "set patchconfig sp , need to restart flag");
            a.a(ReaderApplication.getApplicationImp(), true);
            c.e(this.a, "set patchconfig sp , don't clean flag");
            a.b(ReaderApplication.getApplicationImp(), "");
            j.b("Tinker -- patch merge success");
            return;
        }
        if (com.qq.reader.appconfig.b.f) {
            Toast.makeText(ReaderApplication.getApplicationImp(), "patch fail, please check reason", 1).show();
        }
        d.a();
        j.b("Tinker -- patch merge failed");
    }

    public static b b() {
        if (b == null) {
            synchronized (b.class) {
                b = new b();
            }
        }
        return b;
    }
}
