package com.xiaomi.c;

import android.os.Build;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.b.b$e;
import com.xiaomi.push.b.b.b;
import com.xiaomi.push.service.v;
import com.xiaomi.smack.a;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

public class d {
    ByteBuffer a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);
    private Adler32 c = new Adler32();
    private a d;
    private OutputStream e;
    private int f;
    private int g;

    d(OutputStream outputStream, a aVar) {
        this.e = new BufferedOutputStream(outputStream);
        this.d = aVar;
        TimeZone timeZone = TimeZone.getDefault();
        this.f = timeZone.getRawOffset() / 3600000;
        this.g = timeZone.useDaylightTime() ? 1 : 0;
    }

    public int a(b bVar) {
        int l = bVar.l();
        if (l > 32768) {
            c.a("Blob size=" + l + " should be less than " + 32768 + " Drop blob chid=" + bVar.c() + " id=" + bVar.h());
            return 0;
        }
        if (this.a.capacity() > 4096) {
            this.a = ByteBuffer.allocate(2048);
        }
        this.a.clear();
        this.a = bVar.a(this.a);
        this.c.reset();
        this.c.update(this.a.array(), 0, this.a.position());
        this.b.putInt(0, (int) this.c.getValue());
        this.e.write(this.a.array(), 0, this.a.position());
        this.e.write(this.b.array(), 0, 4);
        this.e.flush();
        int position = this.a.position() + 4;
        c.c("[Slim] Wrote {cmd=" + bVar.a() + ";chid=" + bVar.c() + ";len=" + position + "}");
        return position;
    }

    public void a() {
        b$e com_xiaomi_push_b_b_e = new b$e();
        com_xiaomi_push_b_b_e.a(106);
        com_xiaomi_push_b_b_e.a(Build.MODEL);
        com_xiaomi_push_b_b_e.b(VERSION.INCREMENTAL);
        com_xiaomi_push_b_b_e.c(v.e());
        com_xiaomi_push_b_b_e.b(27);
        com_xiaomi_push_b_b_e.d(this.d.e());
        com_xiaomi_push_b_b_e.e(this.d.d());
        com_xiaomi_push_b_b_e.f(Locale.getDefault().toString());
        com_xiaomi_push_b_b_e.c(VERSION.SDK_INT);
        byte[] a = this.d.c().a();
        if (a != null) {
            com_xiaomi_push_b_b_e.a(b.b(a));
        }
        b bVar = new b();
        bVar.a(0);
        bVar.a("CONN", null);
        bVar.a(0, "xiaomi.com", null);
        bVar.a(com_xiaomi_push_b_b_e.c(), null);
        a(bVar);
        c.a("[slim] open conn: andver=" + VERSION.SDK_INT + " sdk=" + 27 + " hash=" + v.e() + " tz=" + this.f + ":" + this.g + " Model=" + Build.MODEL + " os=" + VERSION.INCREMENTAL);
    }

    public void b() {
        b bVar = new b();
        bVar.a("CLOSE", null);
        a(bVar);
        this.e.close();
    }
}
