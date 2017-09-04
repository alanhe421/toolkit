package com.qq.reader.liveshow.c;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.model.a;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.LogConstants.STATUS;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.TIMCallBack;
import com.tencent.TIMConversationType;
import com.tencent.TIMGroupManager;
import com.tencent.TIMManager;
import com.tencent.TIMValueCallBack;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVRoomMulti.EnterParam.Builder;
import com.tencent.av.sdk.AVRoomMulti.EventListener;
import java.util.ArrayList;

/* compiled from: EnterLiveHelper */
public class b {
    private static final String d = b.class.getSimpleName();
    private static boolean e = false;
    private static boolean f = false;
    public int a = 0;
    private com.qq.reader.liveshow.c.b.b b;
    private Context c;
    private ArrayList<String> g = new ArrayList();
    private EventListener h = new EventListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onEnterRoomComplete(int i, String str) {
            SxbLog.c(b.d, "onEnterRoomComplete: " + str);
            if (i == 0) {
                b.f = true;
                SxbLog.a(b.d, "enterAVRoom", "" + STATUS.SUCCEED, "room id" + c.a().f());
                com.qq.reader.liveshow.avcontrollers.c.a().a(com.qq.reader.liveshow.avcontrollers.c.a().h().getRoom());
                this.a.i();
                this.a.a = 1;
                if (this.a.b != null) {
                    this.a.b.a(c.a().h(), true);
                    return;
                }
                return;
            }
            if (this.a.c != null) {
                m.a(this.a.c, this.a.c.getString(h.live_enter_error) + i, 0);
            }
            this.a.a = 0;
            this.a.h();
            SxbLog.a(b.d, "enterAVRoom", "" + STATUS.FAILED, "result " + i);
        }

        public void onExitRoomComplete() {
            SxbLog.c(b.d, "onAVExitRoomComplete");
            b.f = false;
            this.a.e(0);
            a.a(0);
            this.a.j();
        }

        public void onRoomDisconnect(int i, String str) {
            SxbLog.e(b.d, "onRoomDisconnect: " + str);
            b.f = false;
            this.a.e(1001);
            a.a(0);
            this.a.j();
        }

        public void onSwitchRoomComplete(int i, String str) {
        }

        public void onEndpointsUpdateInfo(int i, String[] strArr) {
            int i2 = 0;
            SxbLog.c(b.d, "onEndpointsUpdateInfo. eventid = " + i);
            int length;
            String str;
            Intent intent;
            ArrayList arrayList;
            String str2;
            int length2;
            String str3;
            switch (i) {
                case 1:
                    SxbLog.b(b.d, "stepin id  " + strArr.length);
                    if (this.a.b != null) {
                        this.a.b.c(strArr);
                        return;
                    }
                    return;
                case 2:
                    if (this.a.b != null) {
                        this.a.b.b(strArr);
                        return;
                    }
                    return;
                case 3:
                    if (strArr != null) {
                        this.a.g.clear();
                        length = strArr.length;
                        while (i2 < length) {
                            str = strArr[i2];
                            this.a.g.add(str);
                            SxbLog.b(b.d, "camera id " + str);
                            i2++;
                        }
                        intent = new Intent("com.qq.reader.liveshow.ACTION_CAMERA_OPEN_IN_LIVE");
                        intent.putStringArrayListExtra("ids", this.a.g);
                        if (this.a.c != null) {
                            this.a.c.sendBroadcast(intent);
                            return;
                        }
                        return;
                    }
                    return;
                case 4:
                    arrayList = new ArrayList();
                    str2 = "";
                    length2 = strArr.length;
                    while (i2 < length2) {
                        str3 = strArr[i2];
                        arrayList.add(str3);
                        str2 = str2 + " " + str3;
                        i2++;
                    }
                    SxbLog.c(b.d, "close camera callback", "" + STATUS.SUCCEED, "close ids " + str2);
                    intent = new Intent("com.qq.reader.liveshow.ACTION_CAMERA_CLOSE_IN_LIVE");
                    intent.putStringArrayListExtra("ids", arrayList);
                    if (this.a.c != null) {
                        this.a.c.sendBroadcast(intent);
                        return;
                    }
                    return;
                case 7:
                    this.a.g.clear();
                    length = strArr.length;
                    while (i2 < length) {
                        str = strArr[i2];
                        this.a.g.add(str);
                        SxbLog.b(b.d, "camera id " + str);
                        i2++;
                    }
                    intent = new Intent("com.qq.reader.liveshow.ACTION_SCREEN_SHARE_IN_LIVE");
                    intent.putStringArrayListExtra("ids", this.a.g);
                    if (this.a.c != null) {
                        this.a.c.sendBroadcast(intent);
                        return;
                    }
                    return;
                case 8:
                    arrayList = new ArrayList();
                    str2 = "";
                    length2 = strArr.length;
                    while (i2 < length2) {
                        str3 = strArr[i2];
                        arrayList.add(str3);
                        str2 = str2 + " " + str3;
                        i2++;
                    }
                    SxbLog.c(b.d, "close camera callback", "" + STATUS.SUCCEED, "close ids " + str2);
                    intent = new Intent("com.qq.reader.liveshow.ACTION_CAMERA_CLOSE_IN_LIVE");
                    intent.putStringArrayListExtra("ids", arrayList);
                    if (this.a.c != null) {
                        this.a.c.sendBroadcast(intent);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void onPrivilegeDiffNotify(int i) {
        }

        public void onSemiAutoRecvCameraVideo(String[] strArr) {
            if (this.a.b != null) {
                this.a.b.d(strArr);
            }
        }

        public void onCameraSettingNotify(int i, int i2, int i3) {
        }

        public void onRoomEvent(int i, int i2, Object obj) {
        }

        public void onDisableAudioIssue() {
        }

        public void onHwStateChangeNotify(boolean z, boolean z2, boolean z3, String str) {
        }
    };

    public b(Context context, com.qq.reader.liveshow.c.b.b bVar) {
        this.c = context;
        this.b = bVar;
    }

    public void a() {
        SxbLog.e("START", "start room ");
        if (c.a().j()) {
            SxbLog.e("START", "create room ");
            f();
            return;
        }
        SxbLog.e("START", "join room ");
        b(a.i());
    }

    private void f() {
        g();
    }

    private void g() {
        TIMGroupManager.getInstance().createGroup("AVChatRoom", new ArrayList(), String.valueOf(System.currentTimeMillis()), String.valueOf(c.a().f()), new TIMValueCallBack<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onError(int i, String str) {
                SxbLog.b(b.d, "onError " + i + "   " + str);
                if (i == 10025) {
                    b.e = true;
                    this.a.c(a.i());
                    return;
                }
                SxbLog.a(b.d, "create live im group", "" + STATUS.FAILED, "code：" + i + " msg:" + str);
                if (this.a.c != null) {
                    m.a(this.a.c, this.a.c.getString(h.live_create_error) + " " + i + " " + str, 0);
                }
                this.a.a = 0;
                this.a.b();
            }

            public void a(String str) {
                SxbLog.a(b.d, "create live im group", "" + STATUS.SUCCEED, "group id " + c.a().f());
                b.e = true;
                this.a.a(c.a().f());
            }
        });
    }

    private void a(int i) {
        SxbLog.a(d, "create av room", "", "room id " + c.a().f());
        f(i);
    }

    public void a(View view) {
        if (com.qq.reader.liveshow.avcontrollers.c.a().h() != null && this.c != null) {
            com.qq.reader.liveshow.avcontrollers.c.a().a(this.c.getApplicationContext(), view);
        }
    }

    private void b(int i) {
        c(i);
    }

    private void c(final int i) {
        SxbLog.a(d, "join im chat room", "", "room id " + i);
        TIMGroupManager.getInstance().applyJoinGroup("" + i, "申请加入" + i, new TIMCallBack(this) {
            final /* synthetic */ b b;

            public void onError(int i, String str) {
                if (i == 10013) {
                    SxbLog.b(b.d, "joinLiveRoom joinIMChatRoom callback succ ");
                    this.b.d(a.i());
                    b.e = true;
                    return;
                }
                SxbLog.a(b.d, "join im chat room", "" + STATUS.FAILED, "code:" + i + " msg:" + str);
                if (this.b.c != null) {
                    m.a(this.b.c, this.b.c.getResources().getString(h.live_enter_error) + i, 0);
                }
                this.b.a = 0;
                this.b.b();
            }

            public void onSuccess() {
                SxbLog.a(b.d, "join im chat room", "" + STATUS.FAILED, "room id " + i);
                b.e = true;
                this.b.d(a.i());
            }
        });
    }

    private void d(int i) {
        SxbLog.a(d, "join av room", "", "AV room id " + i);
        f(i);
    }

    public void b() {
        h();
    }

    public void c() {
        if (f) {
            this.b = null;
            this.c = null;
        } else {
            this.b = null;
            this.c = null;
        }
    }

    private void h() {
        SxbLog.b(d, "quit av room", "", "");
        SxbLog.e(d, "quitAVRoom function start");
        if (f) {
            SxbLog.e(d, "quitAVRoom in AVRoom");
            AVContext h = com.qq.reader.liveshow.avcontrollers.c.a().h();
            if (h != null) {
                h.exitRoom();
            }
        } else {
            e(0);
            a.a(0);
            j();
        }
        SxbLog.e(d, "quitAVRoom function end");
    }

    private void e(final int i) {
        if (!e) {
            if (this.b != null) {
                this.b.a(c.a().h(), true, i);
            }
            com.qq.reader.liveshow.utils.a.a.a().b().a(true, this, "");
        } else if (c.a().h() == 1 && a.u()) {
            TIMGroupManager.getInstance().deleteGroup("" + a.i(), new TIMCallBack(this) {
                final /* synthetic */ b b;

                public void onError(int i, String str) {
                    SxbLog.b(b.d, "delete im room", "" + STATUS.FAILED, "code:" + i + " msg:" + str);
                    if (this.b.b != null) {
                        this.b.b.a(c.a().h(), false, i);
                    }
                    com.qq.reader.liveshow.utils.a.a.a().b().a(false, this.b, "  code=" + i + "msg=" + str);
                }

                public void onSuccess() {
                    SxbLog.b(b.d, "delete im room", "" + STATUS.SUCCEED, "room id " + a.i());
                    b.e = false;
                    if (this.b.b != null) {
                        this.b.b.a(c.a().h(), true, i);
                    }
                    com.qq.reader.liveshow.utils.a.a.a().b().a(true, this.b, "" + b.e);
                }
            });
            TIMManager.getInstance().deleteConversation(TIMConversationType.Group, "" + c.a().f());
        } else {
            TIMGroupManager.getInstance().quitGroup("" + a.i(), new TIMCallBack(this) {
                final /* synthetic */ b b;

                public void onError(int i, String str) {
                    SxbLog.b(b.d, "quit im room", "" + STATUS.FAILED, "code:" + i + " msg:" + str);
                    if (this.b.b != null) {
                        this.b.b.a(c.a().h(), false, i);
                    }
                    com.qq.reader.liveshow.utils.a.a.a().b().a(false, this.b, "  code=" + i + "msg=" + str);
                }

                public void onSuccess() {
                    SxbLog.b(b.d, "quit im room", "" + STATUS.SUCCEED, "room id " + a.i());
                    b.e = false;
                    if (this.b.b != null) {
                        this.b.b.a(c.a().h(), true, i);
                    }
                    com.qq.reader.liveshow.utils.a.a.a().b().a(true, this.b, "" + b.e);
                }
            });
        }
    }

    private void f(int i) {
        SxbLog.b(d, "createlive joinLiveRoom enterAVRoom " + i);
        AVContext h = com.qq.reader.liveshow.avcontrollers.c.a().h();
        Builder builder = new Builder(i);
        if (c.a().h() == 1) {
            builder.auth(-1, null).avControlRole("host").autoCreateRoom(true).isEnableMic(true).isEnableSpeaker(true);
        } else {
            builder.auth(170, null).avControlRole("user").autoCreateRoom(false).isEnableSpeaker(true);
        }
        builder.audioCategory(0).videoRecvMode(1);
        if (h != null) {
            h.enterRoom(this.h, builder.build());
        }
    }

    private void i() {
        if (com.qq.reader.liveshow.avcontrollers.c.a() != null && com.qq.reader.liveshow.avcontrollers.c.a().h() != null && com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl() != null) {
            com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().startTRAEService();
        }
    }

    private void j() {
        if (com.qq.reader.liveshow.avcontrollers.c.a() != null && com.qq.reader.liveshow.avcontrollers.c.a().h() != null && com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl() != null) {
            com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().stopTRAEService();
        }
    }
}
