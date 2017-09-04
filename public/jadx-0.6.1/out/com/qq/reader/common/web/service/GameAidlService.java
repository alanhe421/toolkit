package com.qq.reader.common.web.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.login.c;
import com.qq.reader.cservice.download.game.a;

public class GameAidlService extends Service {
    private a a = ((a) l.d(1006));
    private final com.qq.reader.common.web.a.a.a b = new com.qq.reader.common.web.a.a.a(this) {
        final /* synthetic */ GameAidlService a;

        {
            this.a = r1;
        }

        public int a(String str) throws RemoteException {
            g a = this.a.a.a(this.a.a.a(), (long) str.hashCode());
            if (a == null) {
                return 0;
            }
            return a.getProgress();
        }

        public int b(String str) throws RemoteException {
            g a = this.a.a.a(this.a.a.a(), (long) str.hashCode());
            if (a == null) {
                return 0;
            }
            return com.qq.reader.module.game.a.a(a.getState());
        }

        public String a() throws RemoteException {
            return d.bS(ReaderApplication.getApplicationImp());
        }

        public String b() throws RemoteException {
            return this.a.a().a(this.a);
        }

        public String c() throws RemoteException {
            return this.a.a().c();
        }

        public String d() throws RemoteException {
            return this.a.a().a();
        }

        public String e() throws RemoteException {
            return this.a.a().b();
        }

        public String f() throws RemoteException {
            return com.qq.reader.common.login.define.a.r(this.a);
        }

        public int g() throws RemoteException {
            return this.a.a().g(this.a);
        }

        public int h() throws RemoteException {
            return this.a.a().i(this.a);
        }

        public int i() throws RemoteException {
            return this.a.a().d();
        }

        public void a(int i) throws RemoteException {
            this.a.a().e(this.a, i);
        }

        public void b(int i) throws RemoteException {
            this.a.a().f(this.a, i);
        }

        public boolean j() throws RemoteException {
            return c.b();
        }

        public void k() throws RemoteException {
            c.a();
        }
    };

    public boolean onUnbind(Intent intent) {
        this.a = null;
        stopSelf();
        return super.onUnbind(intent);
    }

    public IBinder onBind(Intent intent) {
        return this.b;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private com.qq.reader.common.login.b.a a() {
        return c.c();
    }
}
