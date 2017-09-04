package com.qq.reader.liveshow.c;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.dynamicload.Lib.DLConstants;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.e;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.b.c;
import com.qq.reader.liveshow.model.d;
import com.qq.reader.liveshow.utils.IMHelper;
import com.qq.reader.liveshow.utils.IMHelper.NotEnterIMException;
import com.qq.reader.liveshow.utils.LogConstants;
import com.qq.reader.liveshow.utils.LogConstants.STATUS;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.l;
import com.tencent.TIMCallBack;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMGroupSystemElem;
import com.tencent.TIMGroupSystemElemType;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;
import com.tencent.TIMMessagePriority;
import com.tencent.TIMValueCallBack;
import com.tencent.av.TIMAvManager;
import com.tencent.av.TIMAvManager.LiveUrl;
import com.tencent.av.TIMAvManager.RecordParam;
import com.tencent.av.TIMAvManager.RecordType;
import com.tencent.av.TIMAvManager.RoomInfo;
import com.tencent.av.TIMAvManager.SDKType;
import com.tencent.av.TIMAvManager.StreamEncode;
import com.tencent.av.TIMAvManager.StreamParam;
import com.tencent.av.TIMAvManager.StreamRes;
import com.tencent.av.sdk.AVEndpoint;
import com.tencent.av.sdk.AVRoomMulti.RequestViewListCompleteCallback;
import com.tencent.av.sdk.AVVideoCtrl;
import com.tencent.av.sdk.AVVideoCtrl.CameraPreviewChangeCallback;
import com.tencent.av.sdk.AVVideoCtrl.EnableCameraCompleteCallback;
import com.tencent.av.sdk.AVVideoCtrl.SwitchCameraCompleteCallback;
import com.tencent.av.sdk.AVView;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LiveHelper */
public class g {
    private static final String c = g.class.getSimpleName();
    private StreamParam A;
    private RecordParam B;
    private b C;
    public Context a;
    private c b;
    private boolean d = false;
    private AVView[] e = new AVView[4];
    private String[] f = new String[4];
    private Boolean g = Boolean.valueOf(false);
    private boolean h;
    private boolean i;
    private j j;
    private int k;
    private CameraPreviewChangeCallback l = new CameraPreviewChangeCallback(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onCameraPreviewChangeCallback(int i) {
            SxbLog.c(g.c, "mCameraPreviewChangeCallback.onCameraPreviewChangeCallback cameraId = " + i);
            this.a.r();
            com.qq.reader.liveshow.avcontrollers.c.a().a(i == 0);
        }
    };
    private RequestViewListCompleteCallback m = new RequestViewListCompleteCallback(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void OnComplete(String[] strArr, AVView[] aVViewArr, int i, int i2, String str) {
            String str2 = "";
            for (String str3 : strArr) {
                this.a.b.a(false, str3);
                str2 = str2 + " " + str3;
            }
            SxbLog.c(g.c, LogConstants.e + LogConstants.a + com.qq.reader.liveshow.model.c.a().b() + LogConstants.a + "get stream data" + LogConstants.a + STATUS.SUCCEED + LogConstants.a + "ids " + str2);
            SxbLog.c(g.c, "RequestViewListCompleteCallback.OnComplete: " + str);
        }
    };
    private TIMMessageListener n = new TIMMessageListener(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public boolean onNewMessages(List<TIMMessage> list) {
            this.a.b((List) list);
            return true;
        }
    };
    private boolean o = true;
    private SwitchCameraCompleteCallback p = new SwitchCameraCompleteCallback(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        protected void onComplete(int i, int i2) {
            super.onComplete(i, i2);
            if (i2 == 0) {
                this.a.o = !this.a.o;
            }
        }
    };
    private boolean q = false;
    private RoomInfo r;
    private long s;
    private boolean t = false;
    private Timer u;
    private TimerTask v;
    private int w = 3;
    private int x = 3;
    private int y = 3;
    private int z = 3;

    /* compiled from: LiveHelper */
    private class a extends TimerTask {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        public void run() {
            if (com.qq.reader.liveshow.utils.g.a(n.a().f())) {
                SxbLog.b(g.c, "HeartBeatTask " + com.qq.reader.liveshow.model.a.c());
                e c = n.a().c();
                Map map = null;
                if (c != null) {
                    map = c.b();
                }
                try {
                    l.a().a(l.a(this.a.k, com.qq.reader.liveshow.model.a.g(), com.qq.reader.liveshow.model.a.f(), com.qq.reader.liveshow.model.a.k()), new m<d.c>(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void a(int i, d.c cVar) {
                            if (cVar != null) {
                                switch (cVar.a) {
                                    case 0:
                                        if (((long) cVar.c) > com.qq.reader.liveshow.model.a.k()) {
                                            com.qq.reader.liveshow.model.a.a((long) cVar.c);
                                        }
                                        if (((long) cVar.b) > com.qq.reader.liveshow.model.a.l()) {
                                            com.qq.reader.liveshow.model.a.b((long) cVar.b);
                                        }
                                        if (cVar.d > com.qq.reader.liveshow.model.a.g()) {
                                            com.qq.reader.liveshow.model.a.c(cVar.d);
                                        }
                                        if (this.a.a.C != null) {
                                            this.a.a.C.a(cVar);
                                            return;
                                        }
                                        return;
                                    default:
                                        return;
                                }
                            }
                        }

                        public void a(int i, String str) {
                        }

                        public void a(Exception exception) {
                        }
                    }, map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: LiveHelper */
    public interface b {
        void a(d.c cVar);
    }

    public g(Context context, c cVar, j jVar, int i) {
        this.a = context;
        this.b = cVar;
        this.j = jVar;
        this.k = i;
    }

    public void a() {
        if (com.qq.reader.liveshow.avcontrollers.c.a() != null && com.qq.reader.liveshow.avcontrollers.c.a().h() != null) {
            AVVideoCtrl videoCtrl = com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl();
            if (videoCtrl != null) {
                videoCtrl.setCameraPreviewChangeCallback(this.l);
            }
        }
    }

    public void b() {
        y();
        com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableMic(true);
        this.d = true;
    }

    private void y() {
        if (this.o) {
            a(0, true);
        } else {
            a(1, true);
        }
    }

    public void c() {
        d();
        e();
    }

    public void d() {
        if (this.o) {
            a(0, false);
        } else {
            a(1, false);
        }
    }

    public void e() {
        com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableMic(false);
        this.d = false;
    }

    private void a(final int i, boolean z) {
        if (z) {
            this.g = Boolean.valueOf(true);
        } else {
            this.g = Boolean.valueOf(false);
        }
        SxbLog.b(c, "createlive enableCamera camera " + i + "  isEnable " + z);
        SxbLog.b(c, "enableCamera " + com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl().enableCamera(i, z, new EnableCameraCompleteCallback(this) {
            final /* synthetic */ g b;

            protected void onComplete(boolean z, int i) {
                super.onComplete(z, i);
                SxbLog.b(g.c, "createlive enableCamera result " + i);
                if (i != 0) {
                    return;
                }
                if (i == 0) {
                    this.b.o = true;
                } else {
                    this.b.o = false;
                }
            }
        }));
    }

    public void a(ArrayList<String> arrayList) {
        int i = 0;
        SxbLog.b(c, "requestViewList " + arrayList);
        if (arrayList.size() != 0) {
            AVEndpoint endpointById = com.qq.reader.liveshow.avcontrollers.c.a().h().getRoom().getEndpointById((String) arrayList.get(0));
            SxbLog.c(c, "requestViewList hostIdentifier " + arrayList + " endpoint " + endpointById);
            if (endpointById != null) {
                String str;
                ArrayList b = com.qq.reader.liveshow.avcontrollers.c.a().b();
                SxbLog.b(c, "requestViewList identifiers : " + arrayList.size());
                SxbLog.b(c, "requestViewList alreadyIds : " + b.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    str = (String) it.next();
                    if (!b.contains(str)) {
                        b.add(str);
                    }
                }
                Iterator it2 = b.iterator();
                while (it2.hasNext()) {
                    str = (String) it2.next();
                    if (i >= 4) {
                        break;
                    }
                    AVView aVView = new AVView();
                    aVView.videoSrcType = 1;
                    aVView.viewSizeType = 1;
                    this.e[i] = aVView;
                    this.f[i] = str;
                    i++;
                }
                com.qq.reader.liveshow.avcontrollers.c.a().i().requestViewList(this.f, this.e, i, this.m);
            } else if (this.a != null) {
                Toast.makeText(this.a, "Wrong Room!!!! Live maybe close already!", 0).show();
            }
        }
    }

    public void b(ArrayList<String> arrayList) {
        int i = 0;
        SxbLog.b(c, "requestViewList " + arrayList);
        if (arrayList.size() != 0) {
            AVEndpoint endpointById = com.qq.reader.liveshow.avcontrollers.c.a().h().getRoom().getEndpointById((String) arrayList.get(0));
            SxbLog.c(c, "requestViewList hostIdentifier " + arrayList + " endpoint " + endpointById);
            if (endpointById != null) {
                String str;
                ArrayList b = com.qq.reader.liveshow.avcontrollers.c.a().b();
                SxbLog.b(c, "requestViewList identifiers : " + arrayList.size());
                SxbLog.b(c, "requestViewList alreadyIds : " + b.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    str = (String) it.next();
                    if (!b.contains(str)) {
                        b.add(str);
                    }
                }
                Iterator it2 = b.iterator();
                while (it2.hasNext()) {
                    str = (String) it2.next();
                    if (i >= 4) {
                        break;
                    }
                    AVView aVView = new AVView();
                    aVView.videoSrcType = 2;
                    aVView.viewSizeType = 1;
                    this.e[i] = aVView;
                    this.f[i] = str;
                    i++;
                }
                com.qq.reader.liveshow.avcontrollers.c.a().i().requestViewList(this.f, this.e, i, this.m);
            } else if (this.a != null) {
                Toast.makeText(this.a, "Wrong Room!!!! Live maybe close already!", 0).show();
            }
        }
    }

    public void a(String str) {
        SxbLog.a(c, "initTIMListener->current room id: " + str);
        TIMManager.getInstance().addMessageListener(this.n);
        IMHelper.a(this.k + "");
    }

    private void z() {
        TIMManager.getInstance().removeMessageListener(this.n);
        IMHelper.a();
        com.qq.reader.liveshow.utils.a.a.a().f().a(true, this, "");
        if (this.b != null) {
            this.b.h();
        }
        SxbLog.e("OUT", "end send quit message");
    }

    public void a(boolean z) {
        if (z) {
            SxbLog.e("OUT", "start send quit message");
            if (!IMHelper.b()) {
                SxbLog.e("OUT", "start send quit isConversationReady = false");
                z();
                return;
            } else if (com.qq.reader.liveshow.model.c.a().b() == null || !com.qq.reader.liveshow.model.c.a().b().equals(com.qq.reader.liveshow.model.a.c())) {
                z();
                return;
            } else {
                try {
                    IMHelper.a(1007, "", TIMMessagePriority.High, new TIMValueCallBack<TIMMessage>(this) {
                        final /* synthetic */ g a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onSuccess(Object obj) {
                            a((TIMMessage) obj);
                        }

                        public void onError(int i, String str) {
                            SxbLog.e("OUT", "主播关闭直播间失败。 code =" + i + " msg = " + str);
                            this.a.z();
                        }

                        public void a(TIMMessage tIMMessage) {
                            SxbLog.e("OUT", "主播关闭直播间成功");
                            this.a.z();
                        }
                    });
                    return;
                } catch (NotEnterIMException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        SxbLog.e("OUT", "start send quit " + com.qq.reader.liveshow.model.c.a().b() + " =? " + com.qq.reader.liveshow.model.a.c());
        z();
    }

    public void f() {
        this.h = this.g.booleanValue();
        this.i = this.d;
        if (this.h || this.i) {
            c();
        }
    }

    public void g() {
        if (this.h || this.i) {
            if (this.h) {
                y();
            }
            if (this.i) {
                k();
            }
        }
    }

    private void b(List<TIMMessage> list) {
        if (list.size() > 0 && ((TIMMessage) list.get(0)).getConversation() != null) {
            ((TIMMessage) list.get(0)).getConversation().setReadMessage();
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            TIMMessage tIMMessage = (TIMMessage) list.get(size);
            if (!tIMMessage.isSelf() && (tIMMessage.getConversation() == null || tIMMessage.getConversation().getPeer() == null || com.qq.reader.liveshow.model.a.j().equals(tIMMessage.getConversation().getPeer()))) {
                TIMElem element = tIMMessage.getElement(0);
                if (element != null) {
                    TIMElemType type = element.getType();
                    if (type == TIMElemType.GroupSystem && TIMGroupSystemElemType.TIM_GROUP_SYSTEM_DELETE_GROUP_TYPE == ((TIMGroupSystemElem) element).getSubtype()) {
                        if (this.a != null) {
                            this.a.sendBroadcast(new Intent("com.qq.reader.liveshow.ACTION_HOST_LEAVE"));
                        }
                    } else if (type == TIMElemType.Custom || type == TIMElemType.Text) {
                        a(element);
                    }
                }
            }
        }
    }

    private void a(TIMElem tIMElem) {
        try {
            com.qq.reader.liveshow.model.im.a.a.a a = IMHelper.a(tIMElem);
            Message obtain;
            if (a.a() == -1) {
                if (!com.qq.reader.liveshow.model.c.a().b().equals(a.c().getId())) {
                    obtain = Message.obtain();
                    obtain.what = 1000;
                    obtain.obj = a;
                    this.j.a(obtain);
                }
            } else if (a.a() > 1000 && a.a() < 1999) {
                if (a.a() == 1011) {
                    if (b(a.b())) {
                        return;
                    }
                } else if (a.a() == 1007) {
                    SxbLog.e("OUT", " receive the msg , host end live ");
                    if (this.b != null) {
                        this.b.j();
                        return;
                    }
                    return;
                }
                obtain = Message.obtain();
                obtain.what = 1003;
                obtain.obj = a;
                this.j.a(obtain);
            } else if (a.a() == 1) {
                if (!com.qq.reader.liveshow.model.c.a().b().equals(a.c().getId())) {
                    obtain = Message.obtain();
                    obtain.obj = a;
                    obtain.what = 1001;
                    this.j.a(obtain);
                }
            } else if (a.a() == 2) {
                if (!com.qq.reader.liveshow.model.c.a().b().equals(a.c().getId())) {
                    obtain = Message.obtain();
                    obtain.obj = a;
                    obtain.what = 1002;
                    this.j.a(obtain);
                }
            } else if (a.a() <= APPluginErrorCode.ERROR_APP_SYSTEM || a.a() >= 2999) {
                SxbLog.e(c, "未处理 message ，action id = " + a.a());
            } else {
                obtain = Message.obtain();
                obtain.obj = a;
                obtain.what = 1004;
                this.j.a(obtain);
            }
        } catch (Exception e) {
            SxbLog.e(c, e.getMessage());
        }
    }

    private boolean b(String str) {
        try {
            Object optString = new JSONObject(str).optString("uid", "");
            if (TextUtils.isEmpty(optString) || !optString.equals(com.qq.reader.liveshow.model.c.a().b())) {
                return false;
            }
            if (this.b != null) {
                this.b.b(h.be_kicked);
                this.b.i();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean h() {
        return this.o;
    }

    public int i() {
        return com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl().switchCamera(this.o ? 1 : 0, this.p);
    }

    public boolean j() {
        return this.d;
    }

    public void k() {
        com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableMic(true);
        this.d = true;
    }

    public void l() {
        com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableMic(false);
        this.d = false;
    }

    public void m() {
        AVVideoCtrl videoCtrl = com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl();
        if (videoCtrl != null) {
            final Object camera = videoCtrl.getCamera();
            if (camera != null && (camera instanceof Camera)) {
                final Parameters parameters = ((Camera) camera).getParameters();
                if (parameters != null) {
                    Object cameraHandler = videoCtrl.getCameraHandler();
                    if (cameraHandler != null && (cameraHandler instanceof Handler)) {
                        if (this.q) {
                            ((Handler) cameraHandler).post(new Runnable(this) {
                                final /* synthetic */ g c;

                                public void run() {
                                    try {
                                        parameters.setFlashMode("off");
                                        ((Camera) camera).setParameters(parameters);
                                        this.c.q = false;
                                    } catch (RuntimeException e) {
                                        SxbLog.c("setParameters", "RuntimeException");
                                    }
                                }
                            });
                        } else {
                            ((Handler) cameraHandler).post(new Runnable(this) {
                                final /* synthetic */ g c;

                                public void run() {
                                    try {
                                        parameters.setFlashMode("torch");
                                        ((Camera) camera).setParameters(parameters);
                                        this.c.q = true;
                                    } catch (RuntimeException e) {
                                        SxbLog.c("setParameters", "RuntimeException");
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public void a(StreamParam streamParam) {
        if (com.qq.reader.liveshow.avcontrollers.c.a().h() == null || com.qq.reader.liveshow.avcontrollers.c.a().h().getRoom() == null) {
            SxbLog.e(c, "Push roomid error room not created");
            return;
        }
        int roomId = com.qq.reader.liveshow.avcontrollers.c.a().h().getRoom().getRoomId();
        SxbLog.b(c, "Push roomid: " + roomId);
        TIMAvManager instance = TIMAvManager.getInstance();
        instance.getClass();
        this.r = new RoomInfo(instance);
        this.r.setRoomId(roomId);
        this.r.setRelationId(this.k);
        TIMAvManager.getInstance().requestMultiVideoStreamerStart(this.r, streamParam, new TIMValueCallBack<StreamRes>(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((StreamRes) obj);
            }

            public void onError(int i, String str) {
                SxbLog.e(g.c, "url error " + i + " : " + str);
                if (this.a.a != null) {
                    Toast.makeText(this.a.a, "start stream error,try again " + i + " : " + str, 0).show();
                }
                if (!this.a.s() && this.a.b != null) {
                    this.a.b.a(null);
                }
            }

            public void a(StreamRes streamRes) {
                SxbLog.b(g.c, "push stream success ");
                this.a.s = streamRes.getChnlId();
                this.a.a(streamRes);
                if (this.a.b != null) {
                    this.a.b.a(streamRes);
                }
            }
        });
    }

    private void a(StreamRes streamRes) {
        SxbLog.b(c, "uploadStreamLink");
        if (streamRes != null) {
            List<LiveUrl> urls = streamRes.getUrls();
            if (urls != null) {
                try {
                    String str = "";
                    String str2 = "";
                    for (LiveUrl url : urls) {
                        String url2 = url.getUrl();
                        if (url2.endsWith(".m3u8")) {
                            String str3 = str2;
                            str2 = url2;
                            url2 = str3;
                        } else if (url2.startsWith("rtmp://")) {
                            str2 = str;
                        } else {
                            url2 = str2;
                            str2 = str;
                        }
                        str = str2;
                        str2 = url2;
                    }
                    com.qq.reader.liveshow.model.a.g(str2);
                    com.qq.reader.liveshow.model.a.h(str);
                    Map map = null;
                    e c = n.a().c();
                    if (c != null) {
                        map = c.b();
                    }
                    str2 = l.a(this.k, URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8"));
                    SxbLog.b(c, "upload stream " + str2);
                    l.a().a(str2, new m<d.e>(this) {
                        final /* synthetic */ g a;

                        {
                            this.a = r1;
                        }

                        public void a(int i, d.e eVar) {
                            SxbLog.b(g.c, "uploadStreamLink onSuccess ");
                            if (eVar != null && eVar.a != 0) {
                                SxbLog.b(g.c, "uploadStreamLink fail code = " + eVar.a);
                            }
                        }

                        public void a(int i, String str) {
                            SxbLog.b(g.c, "uploadStreamLink onError");
                        }

                        public void a(Exception exception) {
                            SxbLog.b(g.c, "uploadStreamLink onFailure");
                        }
                    }, map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void n() {
        try {
            if (this.r == null) {
                com.qq.reader.liveshow.utils.a.a.a().d().a(true, this, "结束推流失败3次了");
                if (this.b != null) {
                    this.b.a(false, -1000);
                    return;
                }
                return;
            }
            SxbLog.c(c, "Push stop Id " + this.s);
            List arrayList = new ArrayList();
            arrayList.add(Long.valueOf(this.s));
            TIMAvManager.getInstance().requestMultiVideoStreamerStop(this.r, arrayList, new TIMCallBack(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onError(int i, String str) {
                    SxbLog.e(g.c, "stop  push error " + i + " : " + str);
                    if (this.a.a != null) {
                    }
                    if (!this.a.v()) {
                        com.qq.reader.liveshow.utils.a.a.a().d().a(true, this, "结束推流失败3次了");
                        if (this.a.b != null) {
                            this.a.b.a(false, i);
                        }
                    }
                }

                public void onSuccess() {
                    SxbLog.b(g.c, "stop push success ");
                    com.qq.reader.liveshow.utils.a.a.a().d().a(true, this, "结束推流已经成功了");
                    if (this.a.b != null) {
                        this.a.b.a(true, 0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            com.qq.reader.liveshow.utils.a.a.a().d().a(true, this, "结束推流失败3次了");
            if (this.b != null) {
                this.b.a(false, -1000);
            }
        }
    }

    public void a(RecordParam recordParam) {
        TIMAvManager instance = TIMAvManager.getInstance();
        instance.getClass();
        RoomInfo roomInfo = new RoomInfo(instance);
        roomInfo.setRelationId(this.k);
        roomInfo.setRoomId(this.k);
        TIMAvManager.getInstance().requestMultiVideoRecorderStart(roomInfo, recordParam, new TIMCallBack(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onError(int i, String str) {
                SxbLog.e(g.c, "start record error " + i + "  " + str);
                if (!this.a.t() && this.a.b != null) {
                    this.a.b.b(false, i);
                }
            }

            public void onSuccess() {
                this.a.t = true;
                SxbLog.b(g.c, "start record success ");
                if (this.a.b != null) {
                    this.a.b.b(true, 0);
                }
            }
        });
    }

    public void o() {
        try {
            if (this.t) {
                TIMAvManager instance = TIMAvManager.getInstance();
                instance.getClass();
                RoomInfo roomInfo = new RoomInfo(instance);
                roomInfo.setRelationId(this.k);
                roomInfo.setRoomId(this.k);
                TIMAvManager.getInstance().requestMultiVideoRecorderStop(roomInfo, new TIMValueCallBack<List<String>>(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((List) obj);
                    }

                    public void onError(int i, String str) {
                        SxbLog.e(g.c, "stop record error " + i + " : " + str);
                        if (!this.a.u()) {
                            com.qq.reader.liveshow.utils.a.a.a().e().a(true, this, "停止录制三次失败");
                            if (this.a.b != null) {
                                this.a.b.a(false, null, i);
                            }
                        }
                    }

                    public void a(List<String> list) {
                        this.a.t = false;
                        SxbLog.b(g.c, "stop record success ");
                        this.a.a((List) list);
                        com.qq.reader.liveshow.utils.a.a.a().e().a(true, this, "停止录制成功");
                        if (this.a.b != null) {
                            this.a.b.a(true, list, 0);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.qq.reader.liveshow.utils.a.a.a().e().a(true, this, "停止录制失败");
            if (this.b != null) {
                this.b.a(true, null, 0);
            }
        }
    }

    public void p() {
        this.b = null;
        this.a = null;
        this.C = null;
        B();
        IMHelper.a();
    }

    public void q() {
        TIMManager.getInstance().removeMessageListener(this.n);
    }

    private void A() {
        this.u = new Timer();
        this.v = new a();
        this.u.schedule(this.v, new Date(), 3000);
    }

    private void B() {
        if (this.v != null) {
            this.v.cancel();
            this.v = null;
        }
        if (this.u != null) {
            this.u.cancel();
            this.u = null;
        }
    }

    public void r() {
        try {
            AVVideoCtrl videoCtrl = com.qq.reader.liveshow.avcontrollers.c.a().h().getVideoCtrl();
            if (videoCtrl != null) {
                Camera camera = (Camera) videoCtrl.getCamera();
                camera.getParameters();
                Parameters parameters = (Parameters) videoCtrl.getCameraPara();
                List supportedFocusModes = parameters.getSupportedFocusModes();
                if (supportedFocusModes != null && supportedFocusModes.contains("continuous-picture")) {
                    parameters.setFocusMode("continuous-picture");
                    parameters.setFocusMode("continuous-picture");
                    camera.setParameters(parameters);
                    camera.cancelAutoFocus();
                    if (camera != null && (camera instanceof Camera)) {
                        camera.autoFocus(new AutoFocusCallback(this) {
                            final /* synthetic */ g a;

                            {
                                this.a = r1;
                            }

                            public void onAutoFocus(boolean z, Camera camera) {
                                if (z) {
                                    camera.cancelAutoFocus();
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(List<String> list) {
        SxbLog.b(c, "uploadRecordFiles2Server");
        try {
            Map b;
            e c = n.a().c();
            if (c != null) {
                b = c.b();
            } else {
                b = null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : list) {
                stringBuilder.append(append).append(",");
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }
            l.a().a(l.b(this.k, stringBuilder.toString()), new m<com.qq.reader.liveshow.model.d.a>(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a(int i, com.qq.reader.liveshow.model.d.a aVar) {
                    if (aVar == null) {
                        SxbLog.b(g.c, "uploadRecordFiles2Server fail  ");
                    } else if (aVar.a != 0) {
                        SxbLog.b(g.c, "uploadRecordFiles2Server fail code = " + aVar.a);
                    }
                }

                public void a(int i, String str) {
                    SxbLog.b(g.c, "uploadRecordFiles2Server onError");
                }

                public void a(Exception exception) {
                    SxbLog.b(g.c, "uploadRecordFiles2Server onFailure");
                }
            }, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(final m<d.d> mVar) {
        SxbLog.b(c, "notifyServerLiveStart");
        try {
            String a = l.a(this.k, true);
            e c = n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            SxbLog.b(c, "notify server create url = " + a);
            l.a().a(a, new m<d.d>(this) {
                final /* synthetic */ g b;

                public void a(int i, d.d dVar) {
                    if (dVar != null) {
                        switch (dVar.a) {
                            case qalsdk.n.b /*-200*/:
                                if (this.b.a != null) {
                                    m.a(this.b.a, h.tip_not_the_live_host, 0);
                                    return;
                                }
                                return;
                            case DLConstants.LOAD_ERR_IO_FAIL /*-103*/:
                                if (this.b.a != null) {
                                    m.a(this.b.a, h.tip_live_has_end_can_not_restart, 0);
                                    return;
                                }
                                return;
                            case DLConstants.LOAD_ERR_NAME_NOT_FOUND /*-102*/:
                            case 0:
                                mVar.a(i, (Object) dVar);
                                this.b.A();
                                g gVar = this.b;
                                TIMAvManager instance = TIMAvManager.getInstance();
                                instance.getClass();
                                gVar.A = new StreamParam(instance);
                                this.b.A.setEncode(StreamEncode.HLS_AND_RTMP);
                                this.b.A.setChannelName(com.qq.reader.liveshow.model.a.h());
                                this.b.A.setChannelDescr(com.qq.reader.liveshow.model.a.h());
                                gVar = this.b;
                                instance = TIMAvManager.getInstance();
                                instance.getClass();
                                gVar.B = new RecordParam(instance);
                                this.b.B.setFilename(com.qq.reader.liveshow.model.a.h());
                                this.b.B.setClassId(this.b.k);
                                this.b.B.setRecordType(RecordType.VIDEO);
                                this.b.B.setSreenShot(true);
                                this.b.B.setTransCode(true);
                                this.b.B.setWaterMark(true);
                                this.b.B.setSdkType(SDKType.Normal);
                                this.b.s();
                                this.b.t();
                                return;
                            default:
                                return;
                        }
                    }
                }

                public void a(int i, String str) {
                    mVar.a(i, str);
                }

                public void a(Exception exception) {
                    mVar.a(exception);
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean s() {
        SxbLog.a(c, "start startPushStreamMultiTimes = " + this.w);
        this.w--;
        if (this.w < 0 || this.A == null) {
            return false;
        }
        a(this.A);
        return true;
    }

    public boolean t() {
        SxbLog.a(c, "start record mStartRecordCount = " + this.x);
        this.x--;
        if (this.x < 0 || this.B == null) {
            return false;
        }
        a(this.B);
        return true;
    }

    public boolean u() {
        SxbLog.b(c, "stopRecordMultiTimes");
        this.y--;
        if (this.y < 0 || !this.t) {
            return false;
        }
        o();
        return true;
    }

    public boolean v() {
        SxbLog.b(c, "stopStreamMultiTimes");
        this.z--;
        if (this.z < 0) {
            return false;
        }
        n();
        return true;
    }

    public void b(final m<Boolean> mVar) {
        SxbLog.b(c, "notifyServerLiveEnd");
        B();
        String a = l.a(this.k, false);
        try {
            e c = n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            l.a().a(a, new m<d.d>(this) {
                final /* synthetic */ g b;

                public void a(int i, d.d dVar) {
                    if (dVar != null) {
                        switch (dVar.a) {
                            case 0:
                                if (mVar != null) {
                                    mVar.a(200, Boolean.valueOf(true));
                                    return;
                                }
                                return;
                        }
                    }
                    if (mVar != null) {
                        mVar.a(200, Boolean.valueOf(true));
                    }
                }

                public void a(int i, String str) {
                    mVar.a(200, Boolean.valueOf(false));
                }

                public void a(Exception exception) {
                    mVar.a(200, Boolean.valueOf(false));
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
            mVar.a(200, Boolean.valueOf(false));
        }
    }

    public void a(b bVar) {
        this.C = bVar;
    }

    public void w() {
        if (!com.qq.reader.liveshow.model.c.a().k()) {
            int i;
            Object a;
            if (com.qq.reader.liveshow.model.c.a().n() >= com.qq.reader.liveshow.model.a.q()) {
                i = 1005;
            } else {
                i = 1001;
            }
            try {
                a = IMHelper.a(i, "", TIMMessagePriority.Normal, new TIMValueCallBack<TIMMessage>(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((TIMMessage) obj);
                    }

                    public void onError(int i, String str) {
                        SxbLog.b(g.c, str);
                    }

                    public void a(TIMMessage tIMMessage) {
                        SxbLog.b(g.c, "观众进入");
                    }
                });
            } catch (NotEnterIMException e) {
                e.printStackTrace();
                a = null;
            }
            if (a != null) {
                Message obtain = Message.obtain();
                obtain.what = 1003;
                obtain.obj = a;
                this.j.a(obtain);
            }
        }
    }
}
