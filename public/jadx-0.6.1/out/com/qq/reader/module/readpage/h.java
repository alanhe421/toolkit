package com.qq.reader.module.readpage;

import android.os.Handler.Callback;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.view.animation.AnimationProvider;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: OnlinePayPage */
public class h implements Callback {
    private b a;
    private c b = null;
    private a c = null;
    private OnlinePayPageInfoView d;
    private WeakReferenceHandler e = new WeakReferenceHandler(this);

    /* compiled from: OnlinePayPage */
    public interface a {
        void a(int i);
    }

    /* compiled from: OnlinePayPage */
    public static class b {
        private String a;
        private int b;
        private String c = "";
        private int d;
        private int e = TbsLog.TBSLOG_CODE_SDK_INIT;
        private String f = "";
        private String g = "";
        private ReadOnlineResult h;
        private PageIndex i = PageIndex.current;
        private int j = TbsLog.TBSLOG_CODE_SDK_INIT;

        public b() {
            c((int) TbsLog.TBSLOG_CODE_SDK_INIT);
        }

        public boolean a() {
            if (this.h == null || !this.h.a()) {
                return false;
            }
            return true;
        }

        public boolean b() {
            if (this.h == null || !this.h.b()) {
                return false;
            }
            return true;
        }

        public boolean c() {
            if (this.h == null || !this.h.c()) {
                return false;
            }
            return true;
        }

        public boolean d() {
            if (this.h == null || this.h.l() <= 0) {
                return false;
            }
            return true;
        }

        public String e() {
            if (this.h == null || this.h.l() <= 0) {
                return null;
            }
            return this.h.m();
        }

        public boolean f() {
            if (this.h == null || this.h.n() <= 0) {
                return false;
            }
            return true;
        }

        public String g() {
            if (this.h != null) {
                return this.h.C();
            }
            return null;
        }

        public String h() {
            if (this.h == null || this.h.n() <= 0) {
                return null;
            }
            return this.h.q();
        }

        public int i() {
            return this.h.o();
        }

        public int j() {
            return this.h.p();
        }

        public String k() {
            if (this.h != null) {
                return this.h.r();
            }
            return null;
        }

        public String l() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public int m() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public String n() {
            return this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public int o() {
            return this.d;
        }

        public void b(int i) {
            this.d = i;
        }

        public int p() {
            return this.e;
        }

        protected void c(int i) {
            this.j = this.e;
            this.e = i;
        }

        public int q() {
            return this.j;
        }

        public String r() {
            return this.f;
        }

        public void c(String str) {
            this.f = str;
        }

        public String s() {
            return this.g;
        }

        public void d(String str) {
            this.g = str;
        }

        public ReadOnlineResult t() {
            return this.h;
        }

        public void a(ReadOnlineResult readOnlineResult) {
            this.h = readOnlineResult;
        }

        public PageIndex u() {
            return this.i;
        }

        public void a(PageIndex pageIndex) {
            this.i = pageIndex;
        }
    }

    public h() {
        b();
    }

    public WeakReferenceHandler a() {
        return this.e;
    }

    public void a(OnlinePayPageInfoView onlinePayPageInfoView) {
        this.d = onlinePayPageInfoView;
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.setTextColor(i);
        }
    }

    public void b() {
        this.a = new b();
    }

    public b c() {
        return this.a;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public boolean d() {
        return f() != TbsLog.TBSLOG_CODE_SDK_INIT;
    }

    public void e() {
        if (this.a.e == TbsLog.TBSLOG_CODE_SDK_INIT) {
            this.a.e = 1007;
        }
    }

    public void b(int i) {
        if (this.a.e != i) {
            this.a.c(i);
            if (this.b != null) {
                this.b.K();
            }
        }
    }

    public int f() {
        return this.a.p();
    }

    public int g() {
        return this.a.q();
    }

    public void onClick() {
        switch (this.a.p()) {
            case 1001:
                if (this.b != null) {
                    this.b.C();
                    return;
                }
                return;
            case 1003:
                if (this.b != null) {
                    this.b.B();
                    return;
                }
                return;
            case 1004:
                if (this.b != null) {
                    this.b.D();
                    return;
                }
                return;
            case 1005:
                if (this.b != null) {
                    this.b.E();
                    return;
                }
                return;
            case 1006:
                if (this.b != null) {
                    this.b.G();
                    return;
                }
                return;
            case 1009:
                if (this.b != null) {
                    this.b.A();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean a(MotionEvent motionEvent, AnimationProvider animationProvider) {
        int i = -1;
        switch (this.a.p()) {
            case TbsLog.TBSLOG_CODE_SDK_INIT /*999*/:
                return false;
            case 1000:
                if (animationProvider.e()) {
                    return false;
                }
                return true;
            case 1001:
            case 1004:
            case 1005:
            case 1006:
            case 1008:
                break;
            case 1003:
            case 1009:
                if (this.d != null) {
                    int a = this.d.a(motionEvent);
                    int action = motionEvent.getAction();
                    if (a != -1) {
                        if (action == 1 && this.b != null) {
                            switch (a) {
                                case 1000:
                                    i = 1000;
                                    break;
                                case 1001:
                                    i = 1001;
                                    break;
                                case 1002:
                                    i = 1002;
                                    break;
                                case 1003:
                                    i = 1003;
                                    break;
                                case 1004:
                                    i = 1004;
                                    break;
                                case 1005:
                                    i = 1005;
                                    break;
                            }
                            this.b.g(i);
                        }
                        return true;
                    }
                }
                break;
            default:
                return true;
        }
        return false;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.a.p() == TbsLog.TBSLOG_CODE_SDK_INIT) {
            return false;
        }
        switch (this.a.p()) {
            case 1000:
                switch (i) {
                    case 4:
                        if (this.b != null) {
                            return this.b.J();
                        }
                        break;
                    case 24:
                    case 25:
                        if (!d.am(ReaderApplication.getApplicationImp())) {
                            return false;
                        }
                        break;
                }
                return true;
            default:
                return false;
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 65543:
                if (this.c != null) {
                    this.c.a(message.arg1);
                    break;
                }
                break;
        }
        return false;
    }
}
