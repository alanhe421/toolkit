package com.qq.reader.module.videoplay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

public class NativeMediaController extends FrameLayout {
    private int A;
    private Handler B;
    private a C;
    private OnClickListener D;
    private OnClickListener E;
    private OnSeekBarChangeListener F;
    private OnClickListener G;
    private OnClickListener H;
    StringBuilder a;
    Formatter b;
    private b c;
    private Context d;
    private ViewGroup e;
    private View f;
    private ProgressBar g;
    private TextView h;
    private TextView i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private OnClickListener o;
    private OnClickListener p;
    private ImageButton q;
    private ImageButton r;
    private ImageButton s;
    private ImageButton t;
    private ImageButton u;
    private ImageButton v;
    private View w;
    private int x;
    private int y;
    private int z;

    public interface a {
        a a();
    }

    public interface b {
        void a(int i);

        void b();

        void c();

        int d();

        int e();

        boolean f();

        int g();

        boolean h();

        boolean i();

        boolean j();

        boolean k();

        void l();
    }

    private static class c extends Handler {
        private final WeakReference<NativeMediaController> a;

        c(NativeMediaController nativeMediaController) {
            this.a = new WeakReference(nativeMediaController);
        }

        public void handleMessage(Message message) {
            NativeMediaController nativeMediaController = (NativeMediaController) this.a.get();
            if (nativeMediaController != null && nativeMediaController.c != null) {
                switch (message.what) {
                    case 1:
                        nativeMediaController.c();
                        return;
                    case 2:
                        int f = nativeMediaController.g();
                        if (!nativeMediaController.k && nativeMediaController.j && nativeMediaController.c.f()) {
                            sendMessageDelayed(obtainMessage(2), (long) (1000 - (f % 1000)));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void setUIGenerator(a aVar) {
        this.C = aVar;
    }

    public NativeMediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = new c(this);
        this.D = new OnClickListener(this) {
            final /* synthetic */ NativeMediaController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h();
                this.a.a((int) APPluginErrorCode.ERROR_APP_TENPAY);
            }
        };
        this.E = new OnClickListener(this) {
            final /* synthetic */ NativeMediaController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.i();
                this.a.a((int) APPluginErrorCode.ERROR_APP_TENPAY);
            }
        };
        this.F = new OnSeekBarChangeListener(this) {
            final /* synthetic */ NativeMediaController a;

            {
                this.a = r1;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.a(3600000);
                this.a.k = true;
                this.a.B.removeMessages(2);
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (this.a.c != null && z) {
                    long d = (((long) this.a.c.d()) * ((long) i)) / 1000;
                    this.a.c.a((int) d);
                    if (this.a.i != null) {
                        this.a.i.setText(this.a.b((int) d));
                    }
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.k = false;
                this.a.g();
                this.a.d();
                this.a.a((int) APPluginErrorCode.ERROR_APP_TENPAY);
                this.a.B.sendEmptyMessage(2);
            }
        };
        this.G = new OnClickListener(this) {
            final /* synthetic */ NativeMediaController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.a(this.a.c.e() - 5000);
                    this.a.g();
                    this.a.a((int) APPluginErrorCode.ERROR_APP_TENPAY);
                }
            }
        };
        this.H = new OnClickListener(this) {
            final /* synthetic */ NativeMediaController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.a(this.a.c.e() + 15000);
                    this.a.g();
                    this.a.a((int) APPluginErrorCode.ERROR_APP_TENPAY);
                }
            }
        };
        this.f = null;
        this.d = context;
        this.l = true;
        this.m = true;
        Log.i("VideoControllerView", "VideoControllerView");
    }

    public NativeMediaController(Context context, boolean z) {
        super(context);
        this.B = new c(this);
        this.D = /* anonymous class already generated */;
        this.E = /* anonymous class already generated */;
        this.F = /* anonymous class already generated */;
        this.G = /* anonymous class already generated */;
        this.H = /* anonymous class already generated */;
        this.d = context;
        this.l = z;
        Log.i("VideoControllerView", "VideoControllerView");
    }

    public NativeMediaController(Context context) {
        this(context, true);
        Log.i("VideoControllerView", "VideoControllerView");
    }

    public void onFinishInflate() {
        if (this.f != null) {
            a(this.f);
        }
    }

    public void setMediaPlayer(b bVar) {
        this.c = bVar;
        d();
        e();
    }

    public void setAnchorView(ViewGroup viewGroup) {
        this.e = viewGroup;
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        removeAllViews();
        addView(a(), layoutParams);
    }

    protected View a() {
        if (this.C != null) {
            a(this.C.a());
        } else {
            this.f = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.video_native_media_controller, null);
            a(this.f);
        }
        return this.f;
    }

    private void a(a aVar) {
        int i = 0;
        this.f = aVar.a;
        this.x = aVar.l;
        this.y = aVar.k;
        this.z = aVar.m;
        this.A = aVar.n;
        this.q = aVar.b;
        if (this.q != null) {
            this.q.requestFocus();
            this.q.setOnClickListener(this.D);
        }
        this.v = aVar.f;
        if (this.v != null) {
            this.v.requestFocus();
            this.v.setOnClickListener(this.E);
        }
        this.w = aVar.a;
        if (this.w != null) {
            this.w.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeMediaController a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c();
                }
            });
        }
        this.r = aVar.i;
        if (this.r != null) {
            this.r.setOnClickListener(this.H);
            if (!this.m) {
                this.r.setVisibility(this.l ? 0 : 8);
            }
        }
        this.s = aVar.j;
        if (this.s != null) {
            this.s.setOnClickListener(this.G);
            if (!this.m) {
                ImageButton imageButton = this.s;
                if (!this.l) {
                    i = 8;
                }
                imageButton.setVisibility(i);
            }
        }
        this.t = aVar.g;
        if (!(this.t == null || this.m || this.n)) {
            this.t.setVisibility(8);
        }
        this.u = aVar.h;
        if (!(this.u == null || this.m || this.n)) {
            this.u.setVisibility(8);
        }
        this.g = aVar.e;
        if (this.g != null) {
            if (this.g instanceof SeekBar) {
                ((SeekBar) this.g).setOnSeekBarChangeListener(this.F);
            }
            this.g.setMax(1000);
        }
        this.h = aVar.c;
        this.i = aVar.d;
        this.a = new StringBuilder();
        this.b = new Formatter(this.a, Locale.getDefault());
        j();
        b();
    }

    private void a(View view) {
        int i = 0;
        this.q = (ImageButton) view.findViewById(R.id.pause);
        if (this.q != null) {
            this.q.requestFocus();
            this.q.setOnClickListener(this.D);
        }
        this.v = (ImageButton) view.findViewById(R.id.fullscreen);
        if (this.v != null) {
            this.v.requestFocus();
            this.v.setOnClickListener(this.E);
        }
        this.r = (ImageButton) view.findViewById(R.id.ffwd);
        if (this.r != null) {
            this.r.setOnClickListener(this.H);
            if (!this.m) {
                this.r.setVisibility(this.l ? 0 : 8);
            }
        }
        this.s = (ImageButton) view.findViewById(R.id.rew);
        if (this.s != null) {
            this.s.setOnClickListener(this.G);
            if (!this.m) {
                ImageButton imageButton = this.s;
                if (!this.l) {
                    i = 8;
                }
                imageButton.setVisibility(i);
            }
        }
        this.t = (ImageButton) view.findViewById(R.id.next);
        if (!(this.t == null || this.m || this.n)) {
            this.t.setVisibility(8);
        }
        this.u = (ImageButton) view.findViewById(R.id.prev);
        if (!(this.u == null || this.m || this.n)) {
            this.u.setVisibility(8);
        }
        this.g = (SeekBar) view.findViewById(R.id.mediacontroller_progress);
        if (this.g != null) {
            if (this.g instanceof SeekBar) {
                ((SeekBar) this.g).setOnSeekBarChangeListener(this.F);
            }
            this.g.setMax(1000);
        }
        this.h = (TextView) view.findViewById(R.id.time);
        this.i = (TextView) view.findViewById(R.id.time_current);
        this.a = new StringBuilder();
        this.b = new Formatter(this.a, Locale.getDefault());
        j();
        b();
    }

    public void b() {
        a((int) APPluginErrorCode.ERROR_APP_TENPAY);
    }

    private void f() {
        if (this.c != null) {
            try {
                if (!(this.q == null || this.c.h())) {
                    this.q.setEnabled(false);
                }
                if (!(this.s == null || this.c.i())) {
                    this.s.setEnabled(false);
                }
                if (this.r != null && !this.c.j()) {
                    this.r.setEnabled(false);
                }
            } catch (IncompatibleClassChangeError e) {
            }
        }
    }

    public void a(int i) {
        if (!(this.j || this.e == null)) {
            g();
            if (this.q != null) {
                this.q.requestFocus();
            }
            f();
            this.e.addView(this, new FrameLayout.LayoutParams(-1, -2, 80));
            this.j = true;
        }
        d();
        e();
        this.B.sendEmptyMessage(2);
        Message obtainMessage = this.B.obtainMessage(1);
        if (i != 0) {
            this.B.removeMessages(1);
            this.B.sendMessageDelayed(obtainMessage, (long) i);
        }
    }

    public void c() {
        if (this.e != null) {
            try {
                this.e.removeView(this);
                this.B.removeMessages(2);
            } catch (IllegalArgumentException e) {
                Log.w("MediaController", "already removed");
            }
            this.j = false;
        }
    }

    private String b(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        i2 /= 3600;
        this.a.setLength(0);
        if (i2 > 0) {
            return this.b.format("%d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i3)}).toString();
        }
        return this.b.format("%02d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)}).toString();
    }

    private int g() {
        if (this.c == null || this.k) {
            return 0;
        }
        int e = this.c.e();
        int d = this.c.d();
        if (this.g != null) {
            if (d > 0) {
                this.g.setProgress((int) ((1000 * ((long) e)) / ((long) d)));
            }
            this.g.setSecondaryProgress(this.c.g() * 10);
        }
        if (this.h != null) {
            this.h.setText(b(d));
        }
        if (this.i == null) {
            return e;
        }
        this.i.setText(b(e));
        return e;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a((int) APPluginErrorCode.ERROR_APP_TENPAY);
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        a((int) APPluginErrorCode.ERROR_APP_TENPAY);
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.c == null) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
            if (!z) {
                return true;
            }
            h();
            a((int) APPluginErrorCode.ERROR_APP_TENPAY);
            if (this.q == null) {
                return true;
            }
            this.q.requestFocus();
            return true;
        } else if (keyCode == Opcodes.NOT_LONG) {
            if (!z || this.c.f()) {
                return true;
            }
            this.c.b();
            d();
            a((int) APPluginErrorCode.ERROR_APP_TENPAY);
            return true;
        } else if (keyCode == 86 || keyCode == Opcodes.NEG_FLOAT) {
            if (!z || !this.c.f()) {
                return true;
            }
            this.c.c();
            d();
            a((int) APPluginErrorCode.ERROR_APP_TENPAY);
            return true;
        } else if (keyCode == 25 || keyCode == 24 || keyCode == Opcodes.SHR_LONG) {
            return super.dispatchKeyEvent(keyEvent);
        } else {
            if (keyCode != 4 && keyCode != 82) {
                a((int) APPluginErrorCode.ERROR_APP_TENPAY);
                return super.dispatchKeyEvent(keyEvent);
            } else if (!z) {
                return true;
            } else {
                c();
                return true;
            }
        }
    }

    public void d() {
        if (this.f != null && this.q != null && this.c != null) {
            if (this.c.f()) {
                this.q.setImageResource(this.x);
            } else {
                this.q.setImageResource(this.y);
            }
        }
    }

    public void e() {
        if (this.f != null && this.v != null && this.c != null) {
            if (this.c.k()) {
                this.v.setImageResource(this.A);
            } else {
                this.v.setImageResource(this.z);
            }
        }
    }

    private void h() {
        if (this.c != null) {
            if (this.c.f()) {
                this.c.c();
            } else {
                this.c.b();
            }
            d();
        }
    }

    private void i() {
        if (this.c != null) {
            this.c.l();
        }
    }

    public void setEnabled(boolean z) {
        boolean z2 = true;
        if (this.q != null) {
            this.q.setEnabled(z);
        }
        if (this.r != null) {
            this.r.setEnabled(z);
        }
        if (this.s != null) {
            this.s.setEnabled(z);
        }
        if (this.t != null) {
            ImageButton imageButton = this.t;
            boolean z3 = z && this.o != null;
            imageButton.setEnabled(z3);
        }
        if (this.u != null) {
            ImageButton imageButton2 = this.u;
            if (!z || this.p == null) {
                z2 = false;
            }
            imageButton2.setEnabled(z2);
        }
        if (this.g != null) {
            this.g.setEnabled(z);
        }
        f();
        super.setEnabled(z);
    }

    private void j() {
        boolean z = true;
        if (this.t != null) {
            this.t.setOnClickListener(this.o);
            this.t.setEnabled(this.o != null);
        }
        if (this.u != null) {
            this.u.setOnClickListener(this.p);
            ImageButton imageButton = this.u;
            if (this.p == null) {
                z = false;
            }
            imageButton.setEnabled(z);
        }
    }

    public void setPrevNextListeners(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.o = onClickListener;
        this.p = onClickListener2;
        this.n = true;
        if (this.f != null) {
            j();
            if (!(this.t == null || this.m)) {
                this.t.setVisibility(0);
            }
            if (this.u != null && !this.m) {
                this.u.setVisibility(0);
            }
        }
    }
}
