package com.xiaomi.push.b;

import com.google.protobuf.micro.a;
import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;

public final class b {

    public static final class b extends e {
        private boolean a;
        private boolean b = false;
        private boolean c;
        private int d = 0;
        private boolean e;
        private int f = 0;
        private boolean g;
        private int h = 0;
        private int i = -1;

        public static b b(byte[] bArr) {
            return (b) new b().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public b a(int i) {
            this.c = true;
            this.d = i;
            return this;
        }

        public b a(boolean z) {
            this.a = true;
            this.b = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(3, f());
            }
            if (i()) {
                cVar.a(4, h());
            }
            if (k()) {
                cVar.a(5, j());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.c(3, f());
            }
            if (i()) {
                i += c.c(4, h());
            }
            if (k()) {
                i += c.c(5, j());
            }
            this.i = i;
            return i;
        }

        public b b(int i) {
            this.e = true;
            this.f = i;
            return this;
        }

        public b b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.f());
                        continue;
                    case 24:
                        a(bVar.e());
                        continue;
                    case 32:
                        b(bVar.e());
                        continue;
                    case 40:
                        c(bVar.e());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public b c(int i) {
            this.g = true;
            this.h = i;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public int f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public int h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public int j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class d extends e {
        private boolean a;
        private boolean b = false;
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private int i = -1;

        public static d b(byte[] bArr) {
            return (d) new d().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public d a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public d a(boolean z) {
            this.a = true;
            this.b = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            if (i()) {
                i += c.b(3, h());
            }
            if (k()) {
                i += c.b(4, j());
            }
            this.i = i;
            return i;
        }

        public d b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.f());
                        continue;
                    case 18:
                        a(bVar.g());
                        continue;
                    case 26:
                        b(bVar.g());
                        continue;
                    case 34:
                        c(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public d b(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public d c(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class g extends e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private int g = -1;

        public static g b(byte[] bArr) {
            return (g) new g().a(bArr);
        }

        public int a() {
            if (this.g < 0) {
                b();
            }
            return this.g;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public g a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            if (i()) {
                i += c.b(3, h());
            }
            this.g = i;
            return i;
        }

        public g b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 26:
                        c(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public g b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public g c(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }
    }

    public static final class h extends e {
        private boolean a;
        private int b = 0;
        private boolean c;
        private String d = "";
        private int e = -1;

        public static h b(byte[] bArr) {
            return (h) new h().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public h a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public h a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.c(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            this.e = i;
            return i;
        }

        public h b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.e());
                        continue;
                    case 18:
                        a(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public int d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }
    }

    public static final class i extends e {
        private boolean a;
        private a b = a.a;
        private int c = -1;

        public static i b(byte[] bArr) {
            return (i) new i().a(bArr);
        }

        public int a() {
            if (this.c < 0) {
                b();
            }
            return this.c;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public i a(a aVar) {
            this.a = true;
            this.b = aVar;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            this.c = i;
            return i;
        }

        public i b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.h());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public a d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }
    }

    public static final class j extends e {
        private boolean a;
        private a b = a.a;
        private boolean c;
        private b d = null;
        private int e = -1;

        public static j b(byte[] bArr) {
            return (j) new j().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public j a(a aVar) {
            this.a = true;
            this.b = aVar;
            return this;
        }

        public j a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.c = true;
            this.d = bVar;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (f()) {
                cVar.a(2, g());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (f()) {
                i += c.b(2, g());
            }
            this.e = i;
            return i;
        }

        public j b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.h());
                        continue;
                    case 18:
                        b bVar2 = new b();
                        bVar.a((e) bVar2);
                        a(bVar2);
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public a d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public boolean f() {
            return this.c;
        }

        public b g() {
            return this.d;
        }
    }

    public static final class k extends e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private long f = 0;
        private boolean g;
        private long h = 0;
        private boolean i;
        private boolean j = false;
        private boolean k;
        private int l = 0;
        private int m = -1;

        public static k b(byte[] bArr) {
            return (k) new k().a(bArr);
        }

        public int a() {
            if (this.m < 0) {
                b();
            }
            return this.m;
        }

        public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public k a(int i) {
            this.k = true;
            this.l = i;
            return this;
        }

        public k a(long j) {
            this.e = true;
            this.f = j;
            return this;
        }

        public k a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public k a(boolean z) {
            this.i = true;
            this.j = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            if (i()) {
                i += c.c(3, h());
            }
            if (k()) {
                i += c.c(4, j());
            }
            if (m()) {
                i += c.b(5, l());
            }
            if (o()) {
                i += c.c(6, n());
            }
            this.m = i;
            return i;
        }

        public k b(long j) {
            this.g = true;
            this.h = j;
            return this;
        }

        public k b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 24:
                        a(bVar.c());
                        continue;
                    case 32:
                        b(bVar.c());
                        continue;
                    case 40:
                        a(bVar.f());
                        continue;
                    case 48:
                        a(bVar.e());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public k b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public long h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public long j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public boolean l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public int n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }
    }
}
