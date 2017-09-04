package com.tencent.mid.util;

import android.content.Context;
import com.tencent.mid.b.d;

public class b {
    private static f c = Util.getLogger();
    private static b d = null;
    private static int e = 0;
    private static String f = null;
    private static String g = null;
    private static String h = null;
    private static String i = null;
    private static String j = null;
    private static String k = null;
    private d a = null;
    private Context b = null;

    private b(Context context) {
        this.b = context;
        this.a = new d(context, 0);
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context);
            }
            bVar = d;
        }
        return bVar;
    }

    public int a() {
        try {
            if (e == 0) {
                e = Integer.parseInt(this.a.b("teg_mid_key_version"));
            }
            return e;
        } catch (Exception e) {
            return 2;
        }
    }

    public String b() {
        try {
            if (Util.isEmpty(f)) {
                f = this.a.b("teg_mid_rsa_pk");
            }
            return Util.isEmpty(f) ? "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5zQz+I9s/DqreFr8dkd6wSdYDngK9T36rtlDPM6VJHjWQHv6FK83xbDoX6hgcZMPYIIawcwRCVPZNetRlsAnztAt7b71z9NvPaF24+fhHe8Sy3Z/Z2JxvGXsjDnejZzdiuHTS+FGUSjcX+CzyqB30yX0AV+LgxXtQe9aRpT5yo5W6jc2UpEhBYCjpGlmW1mksAwWbyvWSEUTkUD7n9uP7C8eFEh5DHnaTwzxAQtzSxQVC1ZopnC3ly/LhMRl8GFXsFlRzg4VTkSdwS/amyWtkKjfHXp7qh4ySBqY9HEGaoZIHrXGv3VtpXoTgGraj+5HPArW0wqQroUOYVx48xRs6QIDAQAB" : f;
        } catch (Exception e) {
            return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5zQz+I9s/DqreFr8dkd6wSdYDngK9T36rtlDPM6VJHjWQHv6FK83xbDoX6hgcZMPYIIawcwRCVPZNetRlsAnztAt7b71z9NvPaF24+fhHe8Sy3Z/Z2JxvGXsjDnejZzdiuHTS+FGUSjcX+CzyqB30yX0AV+LgxXtQe9aRpT5yo5W6jc2UpEhBYCjpGlmW1mksAwWbyvWSEUTkUD7n9uP7C8eFEh5DHnaTwzxAQtzSxQVC1ZopnC3ly/LhMRl8GFXsFlRzg4VTkSdwS/amyWtkKjfHXp7qh4ySBqY9HEGaoZIHrXGv3VtpXoTgGraj+5HPArW0wqQroUOYVx48xRs6QIDAQAB";
        }
    }

    public String c() {
        try {
            if (Util.isEmpty(g)) {
                g = this.a.b("teg_mid_http_service");
            }
            return Util.isEmpty(g) ? "pingmid.qq.com:80" : g;
        } catch (Exception e) {
            return "pingmid.qq.com:80";
        }
    }

    public String d() {
        return "/request";
    }

    public String e() {
        return "/request_new";
    }

    public String f() {
        return "/verify";
    }
}
