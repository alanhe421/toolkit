package com.qq.reader.plugin.audiobook.core;

import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.cservice.download.audio.d;

/* compiled from: OnlinePlayer */
class i$1 implements d {
    final /* synthetic */ i a;

    i$1(i iVar) {
        this.a = iVar;
    }

    public void a(long j, long j2) {
        i.a(this.a, j2);
        i.b(this.a, j);
    }

    public void a(int i) {
        if (i.a(this.a) <= 0 && this.a.f != 2 && this.a.f != 1) {
            switch (i) {
                case -9:
                case -8:
                case 15:
                case 16:
                    this.a.f = 2;
                    return;
                case 11:
                    this.a.f = 4;
                    return;
                case 12:
                case 13:
                    this.a.f = 5;
                    return;
                default:
                    return;
            }
        }
    }

    public boolean a(Bundle bundle, long j, long j2) {
        i.c(this.a, j);
        i.d(this.a, j2);
        return true;
    }

    public void a(int i, Bundle bundle) {
        if (i == 0) {
            i.a(this.a, true);
        } else {
            i.a(this.a, false);
        }
    }

    public void b(int i, Bundle bundle) {
        if (i == -8) {
            Intent intent = new Intent(e.n);
            intent.putExtras(bundle);
            this.a.d.sendBroadcast(intent);
        }
        if (i == -9) {
            intent = new Intent(e.r);
            intent.putExtras(bundle);
            this.a.d.sendBroadcast(intent);
        }
        if (i == 16) {
            intent = new Intent(e.q);
            intent.putExtras(bundle);
            this.a.d.sendBroadcast(intent);
        }
        if (i == 15) {
            intent = new Intent(e.p);
            intent.putExtras(bundle);
            this.a.d.sendBroadcast(intent);
        } else if (i == -2) {
            this.a.a(2, 1, null);
        }
        this.a.h();
    }
}
