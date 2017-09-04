package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.b.f;
import com.tencent.beacon.c.a.b;
import com.tencent.beacon.c.d.d;
import com.tencent.beacon.upload.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class c extends a {
    private Context f = null;
    private Long[] g = null;
    private List<Long> h = null;
    private byte[] i = null;
    private boolean j = false;

    public c(Context context) {
        super(context, 1, 4);
        this.f = context;
    }

    private synchronized boolean g() {
        return this.j;
    }

    public final synchronized void a(boolean z) {
        this.j = z;
    }

    public final synchronized b a() {
        b bVar = null;
        synchronized (this) {
            o d = o.d();
            if (d == null || !d.h()) {
                com.tencent.beacon.e.a.c(" imposiable! ua not ready!", new Object[0]);
            } else {
                g j = d.j();
                if (j == null) {
                    com.tencent.beacon.e.a.c(" imposiable! ua S not ready!", new Object[0]);
                } else {
                    try {
                        Object obj;
                        Context context;
                        int e;
                        List a;
                        if (g()) {
                            List a2 = com.tencent.beacon.b.a.a.a(this.f);
                            if (a2 != null && a2.size() > 0) {
                                Object obj2 = (byte[]) a2.get(3);
                                this.d = String.valueOf(a2.get(1));
                                this.e = ((Integer) a2.get(4)).intValue();
                                obj = obj2;
                                if (obj == null) {
                                    context = this.c;
                                    bVar = a.a(this.a, obj);
                                } else {
                                    e = j.e();
                                    if (!f.n(this.f)) {
                                        e /= 2;
                                    }
                                    if (e < 0) {
                                        a = com.tencent.beacon.net.a.a(this.f, e);
                                    } else {
                                        a = null;
                                    }
                                    if (a != null || a.size() <= 0) {
                                        com.tencent.beacon.e.a.h(" no up datas", new Object[0]);
                                    } else {
                                        com.tencent.beacon.c.a.a a3;
                                        b a4;
                                        int size = a.size();
                                        com.tencent.beacon.e.a.h(" size:%d", Integer.valueOf(size));
                                        this.e = size;
                                        try {
                                            a3 = a(a);
                                        } catch (Exception e2) {
                                            b();
                                            a3 = null;
                                        }
                                        this.g = new Long[size];
                                        for (int i = 0; i < this.g.length; i++) {
                                            this.g[i] = Long.valueOf(((k) a.get(i)).a());
                                        }
                                        a.clear();
                                        if (a3 != null) {
                                            obj2 = a3.a();
                                        } else {
                                            obj2 = obj;
                                        }
                                        this.i = new byte[obj2.length];
                                        System.arraycopy(obj2, 0, this.i, 0, obj2.length);
                                        this.d = com.tencent.beacon.net.a.b(this.c, 4);
                                        com.tencent.beacon.e.a.a("comm rid:%s", this.d);
                                        try {
                                            Context context2 = this.c;
                                            a4 = a.a(this.a, obj2);
                                        } catch (Exception e3) {
                                            b();
                                            a4 = null;
                                        }
                                        bVar = a4;
                                    }
                                }
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            e = j.e();
                            if (f.n(this.f)) {
                                e /= 2;
                            }
                            if (e < 0) {
                                a = null;
                            } else {
                                a = com.tencent.beacon.net.a.a(this.f, e);
                            }
                            if (a != null) {
                            }
                            com.tencent.beacon.e.a.h(" no up datas", new Object[0]);
                        } else {
                            context = this.c;
                            bVar = a.a(this.a, obj);
                        }
                    } catch (Throwable th) {
                        com.tencent.beacon.e.a.a(th);
                        com.tencent.beacon.e.a.d(" get req datas error: %s", th.toString());
                    }
                }
            }
        }
        return bVar;
    }

    private com.tencent.beacon.c.a.a a(List<k> list) {
        int i = 1;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        ArrayList arrayList2 = new ArrayList(5);
        ArrayList arrayList3 = new ArrayList(5);
        ArrayList arrayList4 = new ArrayList();
        int size = list.size();
        this.h = new ArrayList();
        int i2 = 0;
        while (i2 < size) {
            try {
                k kVar = (k) list.get(i2);
                Map e = kVar.e();
                com.tencent.beacon.e.a.a(" bean.getTP: " + kVar.b(), new Object[0]);
                if (e != null) {
                    if ("IP".equals(kVar.b())) {
                        com.tencent.beacon.c.d.b b = b.b(kVar);
                        if (b != null) {
                            arrayList.add(b);
                        } else {
                            this.h.add(Long.valueOf(kVar.a()));
                        }
                    } else if ("DN".equals(kVar.b())) {
                        com.tencent.beacon.c.d.a c = b.c(kVar);
                        if (c != null) {
                            arrayList2.add(c);
                        } else {
                            this.h.add(Long.valueOf(kVar.a()));
                        }
                    } else if ("HO".equals(kVar.b())) {
                        d d = b.d(kVar);
                        if (d != null) {
                            arrayList3.add(d);
                        } else {
                            this.h.add(Long.valueOf(kVar.a()));
                        }
                    } else if ("UA".equals(kVar.b())) {
                        com.tencent.beacon.e.a.f(" Pack2Upload eventName:}%s ", kVar.d());
                        com.tencent.beacon.c.b.a e2 = b.e(kVar);
                        if (e2 != null) {
                            arrayList4.add(e2);
                        } else {
                            this.h.add(Long.valueOf(kVar.a()));
                        }
                    }
                }
                i2++;
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d(" CommonRecordUploadDatas.encode2MixPackage() error1", new Object[0]);
            }
        }
        if (this.h.size() > 0) {
            com.tencent.beacon.net.a.a(this.f, (Long[]) this.h.toArray(new Long[this.h.size()]));
        }
        com.tencent.beacon.e.a.b(" up hmList:" + arrayList3.size(), new Object[0]);
        com.tencent.beacon.e.a.b(" up dmList:" + arrayList2.size(), new Object[0]);
        com.tencent.beacon.e.a.b(" up ipList:" + arrayList.size(), new Object[0]);
        com.tencent.beacon.e.a.b(" up erList:" + arrayList4.size(), new Object[0]);
        try {
            com.tencent.beacon.c.d.c cVar;
            com.tencent.beacon.c.b.b bVar;
            com.tencent.beacon.c.d.c cVar2 = new com.tencent.beacon.c.d.c();
            if (arrayList3.size() > 0) {
                cVar2.c = arrayList3;
                i = 0;
            }
            if (arrayList2.size() > 0) {
                cVar2.b = arrayList2;
                i = 0;
            }
            if (arrayList.size() > 0) {
                cVar2.a = arrayList;
                i = 0;
            }
            if (i != 0) {
                cVar = null;
            } else {
                cVar = cVar2;
            }
            if (arrayList4.size() > 0) {
                bVar = new com.tencent.beacon.c.b.b();
                bVar.a = arrayList4;
            } else {
                bVar = null;
            }
            if (cVar == null && bVar == null) {
                return null;
            }
            Map hashMap = new HashMap();
            if (cVar != null) {
                hashMap.put(Integer.valueOf(3), cVar.a());
            }
            if (bVar != null) {
                hashMap.put(Integer.valueOf(1), bVar.a());
            }
            com.tencent.beacon.c.a.a aVar = new com.tencent.beacon.c.a.a();
            aVar.a = hashMap;
            return aVar;
        } catch (Throwable th2) {
            com.tencent.beacon.e.a.a(th2);
            com.tencent.beacon.e.a.d(" CommonRecordUploadDatas.encode2MixPackage() error2", new Object[0]);
            b();
            return null;
        }
    }

    public final synchronized void b() {
        com.tencent.beacon.e.a.c(" encode failed, clear db data", new Object[0]);
        if (this.g != null && this.g.length > 0) {
            com.tencent.beacon.e.a.b(" remove num :" + com.tencent.beacon.net.a.a(this.f, this.g), new Object[0]);
            this.g = null;
        }
    }

    public final synchronized void b(boolean z) {
        int i = 0;
        synchronized (this) {
            if (this.g != null) {
                i = this.g.length;
            }
            if (i > 0) {
                com.tencent.beacon.e.a.b(" t_event remove num :" + com.tencent.beacon.net.a.a(this.f, this.g), new Object[0]);
            }
            this.g = null;
            if (z && g()) {
                com.tencent.beacon.b.a.a.a(this.f, this.d);
            } else if (!z) {
                if (this.i != null) {
                    com.tencent.beacon.e.a.a("comm rid2:%s", this.d);
                    com.tencent.beacon.b.a.a.a(this.f, this.i, this.d, i);
                }
            }
            this.i = null;
        }
    }
}
