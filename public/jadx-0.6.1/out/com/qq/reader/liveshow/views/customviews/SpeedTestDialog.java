package com.qq.reader.liveshow.views.customviews;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.av.PingResult;
import com.tencent.av.ServerInfo;
import com.tencent.av.ServerInfo.IDC;
import com.tencent.av.ServerInfo.ServerType;
import com.tencent.av.TIMAvManager;
import com.tencent.av.TIMPingCallBack;
import com.tencent.open.SocialConstants;
import java.text.NumberFormat;
import java.util.List;

public class SpeedTestDialog {
    NumberFormat a;
    private ProgressDialog b;
    private Context c;
    private List<PingResult> d;
    private List<ServerInfo> e;
    private List<ServerInfo> f;
    private Handler g;

    class AnonymousClass1 extends Handler {
        final /* synthetic */ SpeedTestDialog a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.b = new ProgressDialog(this.a.c);
                    this.a.b.setTitle(this.a.c.getString(h.ping_ing));
                    this.a.b.setCancelable(false);
                    this.a.b.setMessage(this.a.c.getString(h.ping_start));
                    this.a.b.setButton(-2, this.a.c.getString(h.ping_cancel), new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.a();
                        }
                    });
                    this.a.b.show();
                    return;
                case 2:
                    this.a.b.setMessage(message.getData().getString(SocialConstants.PARAM_SEND_MSG));
                    return;
                case 3:
                    this.a.b.dismiss();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (PingResult pingResult : this.a.d) {
                        stringBuilder.append(this.a.a(pingResult.getServer()));
                        stringBuilder.append(this.a.c.getString(h.ping_time) + pingResult.getUseTime() + "ms ");
                        stringBuilder.append(this.a.c.getString(h.ping_miss) + this.a.a.format(((double) (pingResult.getTotalPkg() - pingResult.getReceivePkg())) / ((double) pingResult.getTotalPkg())) + "\n");
                    }
                    new Builder(this.a.c).setMessage(stringBuilder.toString()).show();
                    return;
                case 4:
                    this.a.b.dismiss();
                    return;
                default:
                    return;
            }
        }
    }

    class AnonymousClass2 implements TIMPingCallBack {
        final /* synthetic */ SpeedTestDialog a;

        public void onError(int i, String str) {
            SxbLog.e("SpeedTestDialog", "ping failed. code: " + i + " desc: " + str);
        }

        public void onSuccess(PingResult pingResult) {
            SxbLog.c("SpeedTestDialog", "end test " + pingResult.getServer().ip + " avg timeuse:" + pingResult.getUseTime());
            this.a.d.add(pingResult);
        }

        public void onProgress(ServerInfo serverInfo, int i, int i2) {
            Object obj;
            for (ServerInfo serverInfo2 : this.a.f) {
                if (serverInfo.ip.equals(serverInfo2.ip)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                this.a.f.add(serverInfo);
            }
            Message message = new Message();
            message.what = 2;
            Bundle bundle = new Bundle();
            bundle.putString(SocialConstants.PARAM_SEND_MSG, this.a.a(serverInfo) + "(" + this.a.f.size() + "/" + this.a.e.size() + ")\n" + this.a.c.getString(h.ping_progress) + " " + i2 + "/" + i);
            message.setData(bundle);
            this.a.g.sendMessage(message);
        }

        public void onStart(List<ServerInfo> list) {
            SxbLog.c("SpeedTestDialog", "start test " + list.size() + " ip");
            if (list.size() > 0) {
                this.a.e.addAll(list);
                Message message = new Message();
                message.what = 1;
                this.a.g.sendMessage(message);
                return;
            }
            m.a(this.a.c, this.a.c.getString(h.ping_no_server), 0);
        }

        public void onFinish() {
            Message message = new Message();
            message.what = 3;
            this.a.g.sendMessage(message);
        }
    }

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[IDC.values().length];
        static final /* synthetic */ int[] b = new int[ServerType.values().length];

        static {
            try {
                b[ServerType.TEL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[ServerType.CNC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[ServerType.CMCC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[IDC.SH.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[IDC.SZ.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[IDC.CD.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[IDC.TJ.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[IDC.NJ.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[IDC.HZ.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[IDC.GZ.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public void a() {
        SxbLog.c("SpeedTestDialog", "stop speed test");
        TIMAvManager.getInstance().requestSpeedTestStop();
        Message message = new Message();
        message.what = 4;
        this.g.sendMessage(message);
    }

    public List<PingResult> getResults() {
        return this.d;
    }

    private String a(ServerInfo serverInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (AnonymousClass3.a[serverInfo.idc.ordinal()]) {
            case 1:
                stringBuilder.append(this.c.getString(h.ping_SH));
                break;
            case 2:
                stringBuilder.append(this.c.getString(h.ping_SZ));
                break;
            case 3:
                stringBuilder.append(this.c.getString(h.ping_CD));
                break;
            case 4:
                stringBuilder.append(this.c.getString(h.ping_TJ));
                break;
            case 5:
                stringBuilder.append(this.c.getString(h.ping_NJ));
                break;
            case 6:
                stringBuilder.append(this.c.getString(h.ping_HZ));
                break;
            case 7:
                stringBuilder.append(this.c.getString(h.ping_GZ));
                break;
        }
        stringBuilder.append(" ");
        switch (AnonymousClass3.b[serverInfo.isp.ordinal()]) {
            case 1:
                stringBuilder.append(this.c.getString(h.ping_TEL));
                break;
            case 2:
                stringBuilder.append(this.c.getString(h.ping_CNC));
                break;
            case 3:
                stringBuilder.append(this.c.getString(h.ping_CMCC));
                break;
        }
        return stringBuilder.toString();
    }
}
