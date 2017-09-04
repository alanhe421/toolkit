package com.qq.reader.liveshow.c;

import android.os.Message;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.a.b.a;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.d.b;
import com.qq.reader.liveshow.model.d.f;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.IMHelper;
import com.qq.reader.liveshow.utils.IMHelper.NotEnterIMException;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.l;
import com.tencent.TIMElemType;
import com.tencent.TIMMessage;
import com.tencent.TIMMessagePriority;
import com.tencent.TIMValueCallBack;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.imsdk.BaseConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: LiveCustomLayerHelper */
public class e extends i {
    private a b;
    private List<okhttp3.e> c = new ArrayList();
    private int d = 0;
    private long e = 0;
    private long f = 0;

    public e(a aVar) {
        this.b = aVar;
    }

    protected boolean a(Message message) {
        switch (message.what) {
            case 1003:
                switch (((com.qq.reader.liveshow.model.im.a.a.a) message.obj).a()) {
                    case 1012:
                        d();
                        break;
                    default:
                        break;
                }
            case 2001:
                this.a.post(new Runnable(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b != null) {
                            this.a.b.c();
                        }
                    }
                });
                break;
        }
        return false;
    }

    public void a(final int i, int i2) {
        if (!c.a().k()) {
            try {
                com.qq.reader.liveshow.b.e c = n.a().c();
                Map b = c != null ? c.b() : new HashMap();
                if (i2 <= 0) {
                    i2 = 1;
                    SxbLog.e("LiveCustomLayerHelper", "发送礼物个数为0，已纠正为1个");
                }
                this.c.add(l.a().a(l.a(com.qq.reader.liveshow.model.a.i(), i, i2), new m<f>(this) {
                    final /* synthetic */ e c;

                    public void a(int i, f fVar) {
                        if (fVar != null) {
                            switch (fVar.a) {
                                case -300:
                                    if (this.c.b != null) {
                                        GiftItem giftItem = null;
                                        for (GiftItem giftItem2 : com.qq.reader.liveshow.model.a.m()) {
                                            GiftItem giftItem22;
                                            if (giftItem22.mId != i) {
                                                giftItem22 = giftItem;
                                            }
                                            giftItem = giftItem22;
                                        }
                                        this.c.b.b(giftItem, i2);
                                        return;
                                    }
                                    return;
                                case -204:
                                    if (this.c.b != null) {
                                        this.c.b.b(h.toast_shutup);
                                        return;
                                    }
                                    return;
                                case 0:
                                    SxbLog.e("LiveCustomLayerHelper", "礼物发送成功 ");
                                    if (this.c.b != null) {
                                        this.c.b.a(fVar.b);
                                    }
                                    try {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("giftid", i);
                                        jSONObject.put("num", fVar.b);
                                        com.qq.reader.liveshow.model.im.a.a.a a = IMHelper.a(2, jSONObject.toString());
                                        Message obtain = Message.obtain();
                                        obtain.obj = a;
                                        obtain.what = 1002;
                                        obtain.arg1 = 1;
                                        this.c.b(obtain);
                                        return;
                                    } catch (Exception e) {
                                        SxbLog.e("LiveCustomLayerHelper", e.getMessage());
                                        return;
                                    }
                            }
                        }
                        SxbLog.e("LiveCustomLayerHelper", "发送礼物失败,code = " + (fVar != null ? Integer.valueOf(fVar.a) : "未知"));
                        if (this.c.b != null) {
                            this.c.b.b(h.error_happen_try_later);
                        }
                    }

                    public void a(int i, String str) {
                        SxbLog.e("LiveCustomLayerHelper", "发送礼物失败,code = " + i);
                        if (this.c.b != null) {
                            this.c.b.b(h.network_unavailable);
                        }
                    }

                    public void a(Exception exception) {
                        SxbLog.e("LiveCustomLayerHelper", "发送礼物失败,msg = " + exception.getMessage());
                        if (this.c.b != null) {
                            this.c.b.b(h.network_unavailable);
                        }
                    }
                }, b));
            } catch (IOException e) {
                SxbLog.e("LiveCustomLayerHelper", "礼物发送失败  " + e.getMessage());
            }
        } else if (this.b != null) {
            this.b.b();
        }
    }

    public void a(String str, com.qq.reader.liveshow.b.a<Boolean> aVar) {
        if (!c.a().k()) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                final com.qq.reader.liveshow.b.a<Boolean> aVar2 = aVar;
                final String str2 = str;
                IMHelper.a(-1000, str, TIMElemType.Text, TIMMessagePriority.Lowest, new TIMValueCallBack<TIMMessage>(this) {
                    final /* synthetic */ e d;

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((TIMMessage) obj);
                    }

                    public void onError(int i, String str) {
                        SxbLog.e("LiveCustomLayerHelper", "发送失败：code = " + i + "  msg = " + str);
                        switch (i) {
                            case BaseConstants.ERR_REQUEST_NO_NET_ONREQ /*6200*/:
                                if (this.d.b != null) {
                                    this.d.b.b(h.network_unavailable);
                                    break;
                                }
                                break;
                            case 10006:
                                if (this.d.b != null) {
                                    this.d.b.b(h.toast_operation_toofrequent);
                                    break;
                                }
                                break;
                            case 10017:
                                if (this.d.b != null) {
                                    this.d.b.b(h.toast_shutup);
                                    break;
                                }
                                break;
                            case 80001:
                                if (this.d.b != null) {
                                    this.d.b.b(h.toast_dirtyword);
                                    break;
                                }
                                break;
                            default:
                                if (this.d.b != null) {
                                    this.d.b.b(h.error_happen_try_later);
                                    break;
                                }
                                break;
                        }
                        aVar2.a(Boolean.valueOf(false));
                    }

                    public void a(TIMMessage tIMMessage) {
                        SxbLog.c("LiveCustomLayerHelper", "fake msg cost time : " + (System.currentTimeMillis() - currentTimeMillis));
                        this.d.b(str2, aVar2);
                    }
                });
            } catch (NotEnterIMException e) {
                e.printStackTrace();
                if (this.b != null) {
                    this.b.a("发送失败，请尝试重新进入直播间后重试");
                }
            }
        } else if (this.b != null) {
            this.b.b();
        }
    }

    private void b(final String str, final com.qq.reader.liveshow.b.a<Boolean> aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MessageKey.MSG_CONTENT, str);
                com.qq.reader.liveshow.b.e c = n.a().c();
                Map hashMap = new HashMap();
                if (c != null) {
                    hashMap = c.b();
                }
                this.c.add(l.a().a(l.c(com.qq.reader.liveshow.model.a.i()), jSONObject.toString(), true, new m<f>(this) {
                    final /* synthetic */ e c;

                    public void a(int i, f fVar) {
                        if (fVar != null) {
                            switch (fVar.a) {
                                case -300:
                                    if (this.c.b != null) {
                                        this.c.b.a(com.qq.reader.liveshow.model.a.v(), 1);
                                    }
                                    aVar.a(Boolean.valueOf(false));
                                    return;
                                case -204:
                                    if (this.c.b != null) {
                                        this.c.b.b(h.toast_shutup);
                                    }
                                    aVar.a(Boolean.valueOf(false));
                                    return;
                                case 0:
                                    aVar.a(Boolean.valueOf(true));
                                    SxbLog.e("LiveCustomLayerHelper", "弹幕发送成功");
                                    try {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("text", str);
                                        com.qq.reader.liveshow.model.im.a.a.a a = IMHelper.a(1, jSONObject.toString());
                                        Message obtain = Message.obtain();
                                        obtain.obj = a;
                                        obtain.what = 1001;
                                        obtain.arg1 = 1;
                                        this.c.b(obtain);
                                        return;
                                    } catch (Exception e) {
                                        SxbLog.e("LiveCustomLayerHelper", e.getMessage());
                                        return;
                                    }
                                default:
                                    SxbLog.e("LiveCustomLayerHelper", "弹幕发送失败 code = " + fVar.a);
                                    if (this.c.b != null) {
                                        this.c.b.b(h.error_happen_try_later);
                                    }
                                    aVar.a(Boolean.valueOf(false));
                                    return;
                            }
                        }
                    }

                    public void a(int i, String str) {
                        SxbLog.e("LiveCustomLayerHelper", "弹幕发送失败 code = " + i + " msg = " + str);
                        if (this.c.b != null) {
                            this.c.b.b(h.error_happen_try_later);
                        }
                        aVar.a(Boolean.valueOf(false));
                    }

                    public void a(Exception exception) {
                        SxbLog.e("LiveCustomLayerHelper", "弹幕发送失败 msg = " + exception.getMessage());
                        if (this.c.b != null) {
                            this.c.b.b(h.error_happen_try_later);
                        }
                        aVar.a(Boolean.valueOf(false));
                    }
                }, hashMap));
            } catch (Exception e) {
                SxbLog.e("LiveCustomLayerHelper", "弹幕发送失败 msg = " + e.getMessage());
            }
        } catch (IOException e2) {
            SxbLog.e("LiveCustomLayerHelper", "弹幕发送失败  " + e2.getMessage());
        }
    }

    public void a() {
        this.b = null;
        for (okhttp3.e b : this.c) {
            b.b();
        }
        this.c.clear();
    }

    public void a(final int i, final com.qq.reader.liveshow.b.a<Integer> aVar) {
        com.qq.reader.liveshow.b.e c = n.a().c();
        Map hashMap = new HashMap();
        if (c != null) {
            hashMap = c.b();
        }
        try {
            l.a().a(l.c(), new m<b>(this) {
                final /* synthetic */ e c;

                public void a(int i, b bVar) {
                    if (i != 200 || bVar == null || bVar.a != 0) {
                        aVar.a(Integer.valueOf(2));
                    } else if (bVar.b >= i) {
                        aVar.a(Integer.valueOf(0));
                    } else {
                        aVar.a(Integer.valueOf(1));
                    }
                }

                public void a(int i, String str) {
                    aVar.a(Integer.valueOf(2));
                }

                public void a(Exception exception) {
                    aVar.a(Integer.valueOf(3));
                }
            }, hashMap);
        } catch (IOException e) {
            SxbLog.b("LiveCustomLayerHelper", e.getMessage());
        }
    }

    public void b() {
        Message obtain = Message.obtain();
        obtain.what = -1;
        b(obtain);
        if (c()) {
            try {
                int i = this.d + 1;
                this.d = i;
                IMHelper.a(1012, String.valueOf(i), TIMMessagePriority.Low, new TIMValueCallBack<TIMMessage>(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((TIMMessage) obj);
                    }

                    public void onError(int i, String str) {
                        SxbLog.b("LiveCustomLayerHelper", "点赞失败");
                    }

                    public void a(TIMMessage tIMMessage) {
                        SxbLog.b("LiveCustomLayerHelper", "点赞成功");
                    }
                });
            } catch (NotEnterIMException e) {
                e.printStackTrace();
                if (this.b != null) {
                    this.b.a("点赞异常，请重新进入直播间重试");
                }
            }
            this.d = 0;
        }
    }

    private boolean c() {
        if (System.currentTimeMillis() - this.e > 1000) {
            this.e = System.currentTimeMillis();
            return true;
        }
        this.d++;
        return false;
    }

    private void d() {
        if (System.currentTimeMillis() - this.f > 100) {
            this.f = System.currentTimeMillis();
            this.a.post(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.b != null) {
                        this.a.b.a();
                    }
                }
            });
        }
    }
}
