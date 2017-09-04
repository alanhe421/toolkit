package com.qq.reader.readengine.fileparse;

import com.qq.reader.readengine.kernel.c.b;
import com.qq.reader.readengine.kernel.c.d;
import com.qq.reader.readengine.kernel.e;
import com.qq.reader.readengine.kernel.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: TextMixedLineList */
public class i {
    public int a;
    public int b;
    private List<d> c = Collections.synchronizedList(new ArrayList());
    private List<d> d = Collections.synchronizedList(new ArrayList());
    private c e;
    private c f;

    public void a(c cVar) {
        a();
        if (cVar != null) {
            int k = cVar.k();
            int i = k / 2;
            for (int i2 = 0; i2 < k; i2++) {
                Object obj = null;
                try {
                    obj = a(cVar, i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    if (i2 < i) {
                        this.c.add(obj);
                    } else {
                        this.d.add(obj);
                    }
                }
            }
            this.f = cVar;
            this.e = cVar;
        }
    }

    private d a(c cVar, int i) {
        return new d(cVar, (e) cVar.j().get(i), cVar.e(i), cVar.f(i), (long[]) cVar.j.get(i), cVar.m);
    }

    public void a() {
        this.c.clear();
        this.d.clear();
        this.a = 0;
        this.b = 0;
    }

    public int b() {
        return this.c.size() + this.d.size();
    }

    public boolean a(b bVar) {
        if (this.b + 1 >= b()) {
            return false;
        }
        this.b++;
        bVar.a(a(this.b));
        return true;
    }

    public boolean a(boolean z, b bVar) {
        if (z) {
            if (this.b + 1 >= b()) {
                return false;
            }
            this.a++;
            bVar.d();
            this.b++;
            bVar.a(a(this.b));
        } else if (this.a <= 0) {
            return false;
        } else {
            this.b--;
            this.a--;
            bVar.c();
            bVar.c(a(this.a));
        }
        return true;
    }

    public g c() {
        g gVar = new g();
        d a = a(this.a);
        if (a != null) {
            if (a.c > 0) {
                gVar.a(a.c, a.b[0]);
            } else {
                gVar.a(a.b[0]);
            }
        }
        return gVar;
    }

    public g d() {
        g gVar = new g();
        d a = a(this.b);
        if (a != null) {
            if (a.c > 0) {
                gVar.a(a.c, a.b[a.b.length - 1]);
            } else {
                gVar.a(a.b[a.b.length - 1]);
            }
        }
        return gVar;
    }

    public void b(b bVar) {
        bVar.e();
        for (int i = this.a; i <= this.b; i++) {
            d a = a(i);
            if (a != null) {
                bVar.a(a);
            }
        }
    }

    public d a(int i) {
        int size = this.c.size();
        if (i < size) {
            return (d) this.c.get(i);
        }
        size = i - size;
        if (size < this.d.size()) {
            return (d) this.d.get(size);
        }
        return null;
    }

    public int b(c cVar) {
        if (this.a >= this.c.size()) {
            this.b -= this.c.size();
            this.a -= this.c.size();
            List list = this.c;
            this.c = this.d;
            this.d = list;
            this.d.clear();
        }
        int k = cVar.k();
        for (int i = 0; i < k; i++) {
            this.d.add(a(cVar, i));
        }
        this.e = this.f;
        this.f = cVar;
        return this.b;
    }

    public int c(c cVar) {
        int k = cVar.k();
        int i;
        if (this.b < this.c.size()) {
            List list = this.d;
            this.d = this.c;
            this.c = list;
            this.c.clear();
            for (i = 0; i < k; i++) {
                this.c.add(a(cVar, i));
            }
            this.a = this.c.size();
            this.b += this.c.size();
        } else {
            int size = this.c.size();
            for (i = k - 1; i >= 0; i--) {
                this.c.add(0, a(cVar, i));
            }
            i = this.c.size() - size;
            this.a += i - 1;
            this.b = i + this.b;
        }
        this.f = this.e;
        this.e = cVar;
        return this.a;
    }

    public boolean a(boolean z, d dVar) {
        if (z) {
            if (this.b >= this.c.size()) {
                dVar.a(z, this.f);
            }
        } else if (this.a < this.c.size()) {
            dVar.a(z, this.e);
        }
        return false;
    }

    private List<d> e() {
        List<d> synchronizedList = Collections.synchronizedList(new ArrayList());
        synchronizedList.addAll(this.c);
        synchronizedList.addAll(this.d);
        return synchronizedList;
    }

    public String a(g gVar, g gVar2) {
        List e = e();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < e.size(); i3++) {
            d dVar = (d) e.get(i3);
            if (dVar.d() != null) {
                g e2 = dVar.e();
                g f = dVar.f();
                int i4;
                g gVar3;
                if (i2 == 0) {
                    if (gVar.b(e2) >= 0 && gVar.b(f) <= 0) {
                        i4 = -1;
                        int i5 = -1;
                        for (i2 = 0; i2 < dVar.d().length; i2++) {
                            gVar3 = dVar.d()[i2];
                            if (gVar3.b(gVar) >= 0 && gVar3.b(gVar2) <= 0) {
                                if (i5 == -1) {
                                    i4 = i2;
                                    i5 = i2;
                                } else {
                                    i4 = i2;
                                }
                            }
                        }
                        if (i4 != -1) {
                            stringBuffer.append(dVar.g().substring(i5, i4 + 1));
                            if (dVar.d()[i4].b(gVar2) == 0) {
                                i = 1;
                            }
                        } else {
                            stringBuffer.append(dVar.g().substring(i5));
                        }
                        i2 = 1;
                    }
                } else if (i2 != 0 && r3 == 0) {
                    if (gVar2.b(e2) < 0 || gVar2.b(f) > 0) {
                        stringBuffer.append(dVar.g());
                    } else {
                        i4 = -1;
                        for (i = 0; i < dVar.d().length; i++) {
                            gVar3 = dVar.d()[i];
                            if (gVar3.b(gVar) >= 0 && gVar3.b(gVar2) <= 0) {
                                i4 = i;
                            } else if (i4 != -1) {
                                break;
                            }
                        }
                        stringBuffer.append(dVar.g().substring(0, i4 + 1));
                        i = 1;
                    }
                    if (gVar2.b(f) == 0) {
                        i = 1;
                    }
                }
                if (!(i2 == 0 || r3 == 0)) {
                    return stringBuffer.toString();
                }
            }
        }
        return "";
    }
}
