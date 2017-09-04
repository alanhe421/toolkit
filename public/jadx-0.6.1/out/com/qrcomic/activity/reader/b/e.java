package com.qrcomic.activity.reader.b;

import android.os.Bundle;
import com.qrcomic.manager.b;
import com.qrcomic.manager.c;

/* compiled from: QRStep */
public abstract class e {
    protected com.qrcomic.activity.reader.a f;
    protected int g;
    protected c h = ((c) b.a().b().a(3));

    /* compiled from: QRStep */
    private static class a extends e {
        private a() {
        }

        public void a(Bundle bundle) {
        }
    }

    public abstract void a(Bundle bundle);

    public static e a(int i, com.qrcomic.activity.reader.a aVar) {
        e bVar;
        switch (i) {
            case 0:
                bVar = new b();
                break;
            case 1:
            case 2:
                bVar = new a();
                break;
            case 3:
                bVar = new c();
                break;
            case 4:
                bVar = new d();
                break;
            default:
                bVar = new a();
                break;
        }
        bVar.g = i;
        bVar.f = aVar;
        return bVar;
    }

    public static void b(int i, com.qrcomic.activity.reader.a aVar) {
        boolean z = true;
        switch (i) {
            case 0:
                Bundle bundle = new Bundle();
                String str = "need_load_comic_data";
                if (aVar.J || aVar.Q) {
                    z = false;
                }
                bundle.putBoolean(str, z);
                a(0, aVar).a(bundle);
                return;
            case 1:
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("requestComicData", true);
                a(1, aVar).a(bundle2);
                return;
            case 2:
                a(2, aVar).a(new Bundle());
                return;
            case 3:
                a(3, aVar).a(null);
                return;
            case 4:
                a(4, aVar).a(null);
                return;
            default:
                return;
        }
    }
}
