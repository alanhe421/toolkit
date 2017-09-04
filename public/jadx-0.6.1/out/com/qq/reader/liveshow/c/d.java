package com.qq.reader.liveshow.c;

import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.liveshow.model.filter.queue.impl.MessageBlockingQueue;
import com.qq.reader.liveshow.model.im.message.a.b;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LiveCommonMessageHelper */
public class d extends i {
    private static String b = "LiveCommonMessageHelper";
    private com.qq.reader.liveshow.c.a.a.a c;
    private a d = new a();
    private com.qq.reader.liveshow.model.filter.a e;
    private com.qq.reader.liveshow.model.filter.a f;
    private com.qq.reader.liveshow.model.filter.a g;

    /* compiled from: LiveCommonMessageHelper */
    private class a {
        final /* synthetic */ d a;
        private final String b;
        private final int c;
        private final int d;
        private ArrayList<b> e;

        private a(d dVar) {
            this.a = dVar;
            this.b = "CommonMessageCache";
            this.c = 200;
            this.d = 100;
        }

        private synchronized boolean a() {
            boolean z;
            if (this.e == null) {
                z = false;
            } else {
                z = true;
            }
            return z;
        }

        private synchronized int b() {
            int i;
            if (this.e == null) {
                i = 0;
            } else {
                i = this.e.size();
            }
            return i;
        }

        private synchronized void c() {
            if (this.e == null) {
                this.e = new ArrayList(200);
            }
        }

        private synchronized ArrayList<b> d() {
            ArrayList<b> arrayList;
            try {
                if (this.e == null) {
                    arrayList = new ArrayList();
                    if (this.e != null) {
                        this.e.clear();
                        this.e = null;
                    }
                } else {
                    arrayList = (ArrayList) this.e.clone();
                    if (this.e != null) {
                        this.e.clear();
                        this.e = null;
                    }
                }
            } catch (Exception e) {
                SxbLog.c("CommonMessageCache", e.toString());
                arrayList = new ArrayList();
                if (this.e != null) {
                    this.e.clear();
                    this.e = null;
                }
            } catch (Throwable th) {
                if (this.e != null) {
                    this.e.clear();
                    this.e = null;
                }
            }
            return arrayList;
        }

        private synchronized void a(b bVar) {
            if (this.e.size() >= 200) {
                Iterator it = this.e.iterator();
                int i = 0;
                while (it.hasNext() && i < 100) {
                    if (!((b) it.next()).d()) {
                        int i2 = i + 1;
                        it.remove();
                        i = i2;
                    }
                }
            }
            this.e.add(bVar);
        }
    }

    public d(com.qq.reader.liveshow.c.a.a.a aVar) {
        this.c = aVar;
        d();
        e();
        f();
    }

    private void d() {
        this.e = new com.qq.reader.liveshow.model.filter.a("CommonMessagePool");
        this.e.a(new MessageBlockingQueue(50)).a(new com.qq.reader.liveshow.model.filter.a.a.a(200));
        this.e.a(new com.qq.reader.liveshow.model.filter.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
                this.a.a((b) bVar);
            }
        });
    }

    private void e() {
        this.f = new com.qq.reader.liveshow.model.filter.a("VipEnterPool").a(new MessageBlockingQueue(50)).a(new com.qq.reader.liveshow.model.filter.a.a.a(1000)).a(this.c.b());
        this.f.a(new com.qq.reader.liveshow.model.filter.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
                b bVar2 = (b) bVar;
                bVar2.a(3);
                this.a.b(bVar2);
            }
        });
    }

    private void f() {
        this.g = new com.qq.reader.liveshow.model.filter.a("DanmukuPool").a(new MessageBlockingQueue(50)).a(new com.qq.reader.liveshow.model.filter.a.a.a(1000)).a(this.c.c());
        this.g.a(new com.qq.reader.liveshow.model.filter.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
                this.a.c((b) bVar);
            }
        });
    }

    protected boolean a(Message message) {
        int optInt;
        int i = -1;
        com.qq.reader.liveshow.model.im.a.a.a aVar;
        com.qq.reader.liveshow.model.filter.a.b bVar;
        if (message.what == 1000) {
            aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
            bVar = new b(aVar.c());
            bVar.a(aVar.b());
            if (message.arg1 == 1) {
                this.e.c(bVar);
            } else {
                this.e.b(bVar);
            }
        } else if (message.what == 1002) {
            int i2;
            aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
            com.qq.reader.liveshow.model.filter.a.b bVar2 = new b(aVar.c());
            try {
                JSONObject jSONObject = new JSONObject(aVar.b());
                i = jSONObject.optInt("giftid", -1);
                optInt = jSONObject.optInt("num");
                i2 = i;
            } catch (JSONException e) {
                JSONException jSONException = e;
                optInt = i;
                SxbLog.e(b, jSONException.getMessage());
                i2 = optInt;
                optInt = 0;
            }
            GiftItem f = com.qq.reader.liveshow.model.a.f(i2);
            if (f != null) {
                bVar2.b(optInt);
                bVar2.a(f);
                bVar2.a(4);
                if (message.arg1 == 1) {
                    this.e.c(bVar2);
                } else {
                    this.e.b(bVar2);
                }
            }
        } else if (message.what == 1001) {
            Object optString;
            aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
            com.qq.reader.liveshow.model.filter.a.b bVar3 = new b(aVar.c());
            try {
                optString = new JSONObject(aVar.b()).optString("text", "");
            } catch (JSONException e2) {
                SxbLog.b("LiveCommonMessageHelper", e2.getMessage());
                optString = null;
            }
            if (!TextUtils.isEmpty(optString)) {
                bVar3.a((String) optString);
                if (message.arg1 == 1) {
                    this.g.c(bVar3);
                } else {
                    this.g.b(bVar3);
                }
            }
        } else if (message.what == 1003) {
            aVar = (com.qq.reader.liveshow.model.im.a.a.a) message.obj;
            bVar = new b(aVar.c());
            switch (aVar.a()) {
                case 1001:
                    if (bVar.d()) {
                        bVar.a(2);
                        this.e.b(bVar);
                        break;
                    }
                    break;
                case 1005:
                    if (bVar.d() || (bVar.b() != null && bVar.b().getVipLevel() >= com.qq.reader.liveshow.model.a.q())) {
                        bVar.a(3);
                        this.f.b(bVar);
                        break;
                    }
                case com.tencent.qalsdk.base.a.c /*1013*/:
                    bVar.a(5);
                    if (message.arg1 != 1) {
                        this.e.b(bVar);
                        break;
                    }
                    this.e.c(bVar);
                    break;
                default:
                    break;
            }
        } else if (message.what == -2) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        this.a.c.d();
                    }
                }
            });
        }
        return true;
    }

    public void a() {
        this.e.a();
        this.f.a();
        this.g.a();
        this.c = null;
    }

    public void b() {
        this.d.c();
    }

    public ArrayList<b> c() {
        return this.d.d();
    }

    public void a(com.qq.reader.liveshow.model.filter.a.b bVar) {
        this.f.a(bVar);
    }

    public void b(com.qq.reader.liveshow.model.filter.a.b bVar) {
        this.g.a(bVar);
    }

    private void a(final b bVar) {
        if (this.d.a()) {
            g();
            this.d.a(bVar);
            return;
        }
        this.a.post(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                if (this.b.c != null) {
                    this.b.c.a(bVar);
                }
            }
        });
    }

    private void b(final b bVar) {
        bVar.a(3);
        this.a.post(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                if (this.b.c != null) {
                    this.b.c.b(bVar);
                }
            }
        });
        this.a.post(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                if (this.b.c != null) {
                    this.b.c.a(bVar);
                }
            }
        });
    }

    private void c(final b bVar) {
        bVar.a(1);
        this.a.post(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                if (this.b.c != null) {
                    this.b.c.c(bVar);
                }
            }
        });
        this.a.post(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                if (this.b.c != null) {
                    this.b.c.a(bVar);
                }
            }
        });
    }

    private void g() {
        if (this.d.b() == 0 && this.c != null) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        this.a.c.a();
                    }
                }
            });
        }
    }
}
