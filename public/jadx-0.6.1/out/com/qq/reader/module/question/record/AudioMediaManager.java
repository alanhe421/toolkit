package com.qq.reader.module.question.record;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.data.AudioData.AnswerData;
import com.qq.reader.module.question.loader.f;
import com.tencent.util.VersionUtils;
import com.tencent.util.WeakReferenceHandler;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AudioMediaManager implements SensorEventListener {
    private static com.qq.reader.module.question.a[] C = new com.qq.reader.module.question.a[]{com.qq.reader.module.question.a.e(), com.qq.reader.module.question.a.d()};
    public static int g = 1;
    public static int h = 2;
    public static int i = 3;
    public static int j = 4;
    static volatile int m = -2;
    private static final String r = AudioMediaManager.class.getSimpleName();
    private static volatile AudioMediaManager s;
    private Sensor A;
    private float B;
    private long D;
    private int[] E;
    private int F;
    private a G;
    MediaRecorder a;
    MediaPlayer b;
    AudioData c;
    int d;
    WeakReferenceHandler e;
    Handler f;
    int k;
    public int l;
    float n;
    float o;
    float p;
    boolean q;
    private String t;
    private File u;
    private int v;
    private PlayerState w;
    private RecorderState x;
    private SensorManager y;
    private Sensor z;

    enum PlayerState {
        NO_INIT,
        IDLE,
        PREPARED,
        PLAYING
    }

    enum RecorderState {
        NO_INIT,
        IDLE,
        PREPARED,
        RECORDING
    }

    class a implements SensorEventListener {
        final /* synthetic */ AudioMediaManager a;

        a(AudioMediaManager audioMediaManager) {
            this.a = audioMediaManager;
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 1) {
                float f = sensorEvent.values[0];
                float f2 = sensorEvent.values[1];
                float f3 = sensorEvent.values[2];
                if (AudioMediaManager.p()) {
                    f *= 10.0f;
                    f2 *= 10.0f;
                    f3 *= 10.0f;
                }
                int i = (int) (this.a.o - f2);
                int i2 = (int) (this.a.p - f3);
                if (((int) (this.a.n - f)) == 0 && i == 0 && i2 == 0) {
                    this.a.q = false;
                } else {
                    this.a.q = true;
                }
                this.a.n = f;
                this.a.o = f2;
                this.a.p = f3;
            }
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }

    public static AudioMediaManager a() {
        if (s == null) {
            synchronized (AudioMediaManager.class) {
                if (s == null) {
                    s = new AudioMediaManager();
                }
            }
        }
        return s;
    }

    public void a(AudioData audioData, WeakReferenceHandler weakReferenceHandler) {
        if (audioData != this.c) {
            g();
            c();
        }
        this.c = audioData;
        this.e = weakReferenceHandler;
        this.t = f.b().a(audioData.a().g());
        this.k = !w() ? g : i;
        if (this.k == i) {
            r();
        }
    }

    private void r() {
        g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                try {
                    if (!AudioMediaManager.this.A()) {
                        float a = ((float) AudioMediaManager.a(AudioMediaManager.this.k())) / 1000.0f;
                        Message obtainMessage = AudioMediaManager.this.e.obtainMessage();
                        obtainMessage.what = 1100401;
                        obtainMessage.obj = AudioMediaManager.this.c;
                        AudioMediaManager.this.v = (int) Math.ceil((double) a);
                        obtainMessage.arg1 = AudioMediaManager.this.v;
                        AudioMediaManager.this.c.b().b((long) AudioMediaManager.this.v);
                        AudioMediaManager.this.e.sendMessage(obtainMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private AudioMediaManager() {
        this.d = 60;
        this.t = null;
        this.u = null;
        this.w = PlayerState.NO_INIT;
        this.x = RecorderState.NO_INIT;
        this.y = null;
        this.z = null;
        this.A = null;
        this.l = 0;
        this.E = new int[10];
        this.F = 0;
        this.G = new a(this);
        this.y = (SensorManager) ReaderApplication.getApplicationImp().getSystemService("sensor");
        this.z = this.y.getDefaultSensor(8);
        this.A = this.y.getDefaultSensor(1);
        this.w = PlayerState.NO_INIT;
        this.x = RecorderState.NO_INIT;
        if (this.z != null) {
            this.B = a(this.z);
            this.B = this.B > 10.0f ? 10.0f : this.B;
        }
    }

    private float a(Sensor sensor) {
        String str = Build.MODEL;
        if (str.equals("ZTE U880s") || str.equals("ZTE U807")) {
            return 97.0f;
        }
        if (str.equals("Coolpad 5890") || str.equals("Coolpad 5891") || str.equals("Coolpad 8720L") || str.equals("Coolpad 5879") || str.equals("Coolpad 5891Q")) {
            return 5.0f;
        }
        if (str.equals("HUAWEI Y320-T00") || str.equals("Lenovo A658t") || str.equals("Lenovo A788t")) {
            return 10.0f;
        }
        if (str.equals("ME860")) {
            return 99.0f;
        }
        if (str.equals("ZTE U930HD") || str.equals("ZTE-T U960s")) {
            return 100.0f;
        }
        if (sensor != null) {
            return sensor.getMaximumRange();
        }
        return 0.0f;
    }

    private synchronized void s() {
        try {
            if (!(this.b == null || this.w == PlayerState.IDLE)) {
                this.b.stop();
                this.b.reset();
                this.b.release();
                this.b = null;
                this.w = PlayerState.NO_INIT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void t() {
        try {
            if (!(this.a == null || this.x == RecorderState.IDLE)) {
                this.a.reset();
                this.a.release();
                this.a = null;
                this.x = RecorderState.NO_INIT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void u() {
        t();
        this.a = new MediaRecorder();
        this.a.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ AudioMediaManager a;

            {
                this.a = r1;
            }

            public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                this.a.F();
            }
        });
        this.x = RecorderState.PREPARED;
    }

    private void v() throws PlayException {
        try {
            s();
            this.b = new MediaPlayer();
            this.b.setOnErrorListener(new MediaPlayer.OnErrorListener(this) {
                final /* synthetic */ AudioMediaManager a;

                {
                    this.a = r1;
                }

                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    c.e(AudioMediaManager.r, "on error extra =  " + i2 + "  what = " + i);
                    return false;
                }
            });
            this.b.setOnCompletionListener(new OnCompletionListener(this) {
                final /* synthetic */ AudioMediaManager a;

                {
                    this.a = r1;
                }

                public void onCompletion(MediaPlayer mediaPlayer) {
                    this.a.g();
                }
            });
            com.qq.reader.module.question.a aVar = C[this.l];
            this.b.setAudioStreamType(aVar.b());
            AudioManager audioManager = (AudioManager) ReaderApplication.getApplicationImp().getSystemService("audio");
            audioManager.setMode(aVar.a());
            audioManager.setSpeakerphoneOn(aVar.c());
            File z = z();
            if (z != null) {
                this.b.setDataSource(z.getAbsolutePath());
                this.b.prepare();
            }
            this.w = PlayerState.PREPARED;
        } catch (Throwable e) {
            a(ReaderApplication.getApplicationImp(), false);
            s();
            throw new PlayException(0, e);
        }
    }

    private boolean w() {
        try {
            File z = z();
            if (z == null || z.length() <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void b() throws RecordException {
        if (o() && !x() && !A() && !j()) {
            a(ReaderApplication.getApplicationImp(), true);
            try {
                u();
                this.a.reset();
                this.a.setAudioSource(1);
                this.a.setAudioSamplingRate(8000);
                this.a.setAudioEncodingBitRate(12200);
                this.a.setOutputFormat(3);
                this.a.setAudioEncoder(1);
                File k = k();
                File parentFile = k.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    b(k);
                    this.x = RecorderState.RECORDING;
                    y();
                } catch (RecordException e) {
                    a(ReaderApplication.getApplicationImp(), false);
                    t();
                    throw e;
                }
            } catch (Throwable e2) {
                a(ReaderApplication.getApplicationImp(), false);
                t();
                throw new RecordException(0, e2);
            }
        }
    }

    private boolean x() {
        if (android.support.v4.content.a.a(ReaderApplication.getApplicationImp(), "android.permission.RECORD_AUDIO") == 0) {
            return false;
        }
        F();
        return true;
    }

    private void y() {
        if (this.e != null) {
            this.e.post(new Runnable(this) {
                final /* synthetic */ AudioMediaManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.e != null) {
                        long uptimeMillis;
                        if (this.a.i()) {
                            if (this.a.b != null) {
                                float currentPosition = (float) this.a.b.getCurrentPosition();
                                float duration = currentPosition / ((float) this.a.b.getDuration());
                                Message obtainMessage = this.a.e.obtainMessage();
                                obtainMessage.obj = this.a.c;
                                obtainMessage.arg1 = (int) (duration * 1000.0f);
                                obtainMessage.arg2 = (int) Math.ceil((double) (currentPosition / 1000.0f));
                                obtainMessage.what = 1100403;
                                this.a.e.sendMessage(obtainMessage);
                                uptimeMillis = SystemClock.uptimeMillis();
                                this.a.e.postAtTime(this, uptimeMillis + (1000 - (uptimeMillis % 1000)));
                            }
                        } else if (this.a.j()) {
                            if (this.a.v <= this.a.d - 1) {
                                Message obtainMessage2 = this.a.e.obtainMessage();
                                obtainMessage2.obj = this.a.c;
                                obtainMessage2.arg1 = this.a.v;
                                obtainMessage2.what = 1100404;
                                this.a.e.sendMessage(obtainMessage2);
                                if (this.a.v < this.a.d - 1) {
                                    uptimeMillis = SystemClock.uptimeMillis();
                                    this.a.e.postAtTime(this, uptimeMillis + (1000 - (uptimeMillis % 1000)));
                                } else if (this.a.v == this.a.d - 1) {
                                    uptimeMillis = SystemClock.uptimeMillis();
                                    this.a.e.postAtTime(this, uptimeMillis + (500 - (uptimeMillis % 500)));
                                }
                            } else {
                                this.a.c();
                            }
                            this.a.v = this.a.v + 1;
                        }
                    }
                }
            });
            this.e.post(new Runnable(this) {
                final /* synthetic */ AudioMediaManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.i() && this.a.e != null && this.a.b != null) {
                        Message obtainMessage = this.a.e.obtainMessage(1100410);
                        obtainMessage.obj = this.a.c;
                        Bundle bundle = new Bundle();
                        int duration = this.a.b.getDuration();
                        int currentPosition = this.a.b.getCurrentPosition();
                        bundle.putInt("duration", 1000);
                        bundle.putInt("current", (currentPosition * 1000) / duration);
                        obtainMessage.setData(bundle);
                        this.a.e.sendMessage(obtainMessage);
                        if (currentPosition < duration) {
                            this.a.e.postDelayed(this, 100);
                        }
                    }
                }
            });
        }
    }

    private void b(File file) throws RecordException {
        try {
            this.a.setOutputFile(new FileOutputStream(file).getFD());
            this.a.prepare();
            this.a.start();
            this.k = h;
            if (this.e != null) {
                Message obtainMessage = this.e.obtainMessage();
                obtainMessage.obj = this.c;
                obtainMessage.what = 1100405;
                obtainMessage.arg1 = this.d;
                this.e.sendMessage(obtainMessage);
            }
            this.v = 0;
            this.D = System.currentTimeMillis();
            E();
        } catch (Throwable e) {
            throw new RecordException(1, e);
        } catch (Throwable e2) {
            throw new RecordException(0, e2);
        } catch (Throwable e22) {
            throw new RecordException(2, e22);
        }
    }

    public void c() {
        a(true);
    }

    public void a(boolean z) {
        C();
        if (!A()) {
            a(ReaderApplication.getApplicationImp(), false);
            if (j()) {
                try {
                    this.k = i;
                    int ceil = (int) Math.ceil((double) (((float) (System.currentTimeMillis() - this.D)) / 1000.0f));
                    if (this.e != null) {
                        Message obtainMessage = this.e.obtainMessage(1100406);
                        obtainMessage.obj = this.c;
                        obtainMessage.arg1 = ceil;
                        if (z) {
                            obtainMessage.arg2 = 1;
                        }
                        this.e.sendMessage(obtainMessage);
                    }
                    this.c.b().a(System.currentTimeMillis());
                    this.c.b().b((long) ceil);
                    this.D = 0;
                    this.v = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    t();
                }
            }
        }
    }

    public void d() {
        f.b().a(k(), this.c, new com.qq.reader.module.question.loader.d.a(this) {
            final /* synthetic */ AudioMediaManager a;

            {
                this.a = r1;
            }

            public void a(AudioData audioData) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100205;
                    this.a.e.sendMessage(obtainMessage);
                }
            }

            public void b(AudioData audioData) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100201;
                    this.a.e.sendMessage(obtainMessage);
                }
            }

            public void a(AudioData audioData, long j, int i) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100204;
                    this.a.e.sendMessage(obtainMessage);
                }
            }

            public void a(AudioData audioData, long j) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100203;
                    this.a.e.sendMessage(obtainMessage);
                }
            }

            public void a(AudioData audioData, long j, long j2) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100202;
                    obtainMessage.arg1 = (int) ((100 * j) / j2);
                    this.a.e.sendMessage(obtainMessage);
                }
            }

            public void b(AudioData audioData, long j) {
                if (this.a.e != null) {
                    Message obtainMessage = this.a.e.obtainMessage();
                    obtainMessage.obj = this.a.c;
                    obtainMessage.what = 1100206;
                    this.a.e.sendMessage(obtainMessage);
                }
            }
        });
    }

    public void e() {
        if (!A()) {
            File k = k();
            if (k.exists() && k.delete()) {
                this.k = g;
                if (this.e != null) {
                    this.e.sendEmptyMessage(1100409);
                }
            }
        }
    }

    public void f() throws PlayException {
        try {
            if (o() && !A() && !i()) {
                a(ReaderApplication.getApplicationImp(), true);
                v();
                this.b.start();
                this.k = j;
                if (this.e != null) {
                    Message obtainMessage = this.e.obtainMessage(1100407);
                    obtainMessage.obj = this.c;
                    obtainMessage.arg1 = (int) Math.ceil((double) (((float) this.b.getDuration()) / 1000.0f));
                    this.e.sendMessage(obtainMessage);
                }
                y();
                this.w = PlayerState.PLAYING;
                m();
            }
        } catch (PlayException e) {
            s();
            a(ReaderApplication.getApplicationImp(), false);
            throw e;
        }
    }

    public void g() {
        b(false);
    }

    public void b(boolean z) {
        try {
            if (!A()) {
                if (i()) {
                    if (!(this.e == null || z)) {
                        Message obtainMessage = this.e.obtainMessage(1100408);
                        obtainMessage.obj = this.c;
                        obtainMessage.arg1 = (int) Math.ceil((double) (((float) this.b.getDuration()) / 1000.0f));
                        this.e.sendMessage(obtainMessage);
                    }
                    this.k = i;
                }
                n();
                a(ReaderApplication.getApplicationImp(), false);
                s();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            n();
            a(ReaderApplication.getApplicationImp(), false);
            s();
        }
    }

    public void a(AudioData audioData) {
        try {
            if (audioData == this.c) {
                a(ReaderApplication.getApplicationImp(), false);
                if (!(this.x.equals(RecorderState.NO_INIT) || this.x.equals(RecorderState.IDLE))) {
                    c();
                }
                if (!(this.w.equals(PlayerState.NO_INIT) || this.w.equals(PlayerState.IDLE))) {
                    g();
                }
                this.c = null;
                this.e = null;
                this.f = null;
                this.t = null;
                this.k = g;
                this.x = RecorderState.NO_INIT;
                this.w = PlayerState.NO_INIT;
                this.u = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int h() {
        return this.k;
    }

    public void a(int i) throws AudioException {
        if (i() || j()) {
            if (i()) {
                g();
            } else {
                c();
            }
        } else if (w()) {
            f();
        } else {
            this.d = i;
            int i2 = 0;
            while (true) {
                try {
                    b();
                    break;
                } catch (RecordException e) {
                    if (i2 >= 1) {
                        throw e;
                    }
                    i2++;
                }
            }
        }
    }

    public boolean i() {
        return PlayerState.PLAYING.equals(this.w);
    }

    public boolean j() {
        return RecorderState.RECORDING.equals(this.x);
    }

    public File k() {
        if (this.u != null && this.u.getAbsolutePath().equals(this.t)) {
            return this.u;
        }
        this.u = new File(this.t);
        return this.u;
    }

    private File z() {
        if (this.c == null) {
            return null;
        }
        AnswerData b = this.c.b();
        if (b != null) {
            File q = b.q();
            if (q != null && q.exists() && q.length() > 0) {
                return q;
            }
        }
        return k();
    }

    private boolean A() {
        if (s == null || this.c == null || this.e == null) {
            return true;
        }
        return false;
    }

    public AudioData l() {
        return this.c;
    }

    public static long a(File file) throws IOException {
        int[] iArr = new int[]{13, 14, 16, 18, 20, 21, 27, 32, 0, 0, 0, 0, 0, 0, 0, 0};
        long j = 0;
        try {
            long length = file.length();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            bufferedInputStream.close();
            byteArrayOutputStream.close();
            j = ((length - 6) / ((long) iArr[(byteArrayOutputStream.toByteArray()[6] >> 3) & 15])) * 20;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(j);
        return j;
    }

    private int B() {
        try {
            if (this.a != null) {
                int maxAmplitude = this.a.getMaxAmplitude();
                if (this.F >= 10) {
                    Set hashSet = new HashSet();
                    for (int i = 0; i < this.F; i++) {
                        hashSet.add(Integer.valueOf(this.E[i]));
                    }
                    if (hashSet.size() == 1) {
                        this.F = 0;
                        this.E = null;
                        this.E = new int[10];
                        F();
                    }
                } else {
                    this.E[this.F] = maxAmplitude;
                    this.F++;
                }
            }
        } catch (Exception e) {
        }
        return this.F;
    }

    private void C() {
        if (this.f != null) {
            this.f.removeCallbacks(null);
            this.f = null;
        }
    }

    private void D() {
        File k = k();
        if (k == null || !k.exists() || k.length() <= 0) {
            F();
        }
    }

    private void E() {
        if (this.f == null) {
            this.f = new Handler();
        }
        this.f.postDelayed(new Runnable(this) {
            final /* synthetic */ AudioMediaManager a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.B() >= 10) {
                    this.a.D();
                } else if (this.a.j() && this.a.f != null) {
                    this.a.f.postDelayed(this, 100);
                }
            }
        }, 100);
    }

    private void F() {
        if (this.e != null) {
            a(false);
            Message obtainMessage = this.e.obtainMessage();
            obtainMessage.obj = this.c;
            obtainMessage.what = 1100402;
            this.e.sendMessage(obtainMessage);
        }
    }

    public void m() {
        try {
            if (this.z != null) {
                this.y.registerListener(this, this.z, 3);
            }
            if (this.A != null) {
                this.y.registerListener(this.G, this.A, 3);
            }
        } catch (Exception e) {
            c.e(r, e.getMessage());
        }
    }

    public void n() {
        try {
            if (this.z != null) {
                this.y.unregisterListener(this, this.z);
            }
        } catch (Exception e) {
            c.e(r, e.getMessage());
        }
        try {
            if (this.A != null) {
                this.y.unregisterListener(this.G, this.A);
            }
        } catch (Exception e2) {
            c.e(r, e2.getMessage());
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] >= this.B) {
            c.e(r, "远距离");
            if (this.l != 0) {
                c.e(r, "扬声器");
                this.l = 0;
                G();
                return;
            }
            return;
        }
        c.e(r, "近距离");
        if (this.l == 1) {
            return;
        }
        if (com.qq.reader.module.question.a.f() || this.A == null || this.q) {
            c.e(r, "听筒");
            this.l = 1;
            G();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void G() {
        try {
            b(true);
            f();
        } catch (Exception e) {
            g();
            e.printStackTrace();
        }
    }

    public boolean o() {
        if (com.qq.reader.common.login.c.b()) {
            return true;
        }
        if (this.e != null) {
            Message obtain = Message.obtain();
            obtain.what = 11000000;
            obtain.arg1 = 11000002;
            obtain.obj = this.c;
            this.e.sendMessage(obtain);
        }
        return false;
    }

    @TargetApi(8)
    public static boolean a(Context context, boolean z) {
        boolean z2 = true;
        if (context == null) {
            return false;
        }
        if (z) {
            if (m == 1) {
                return true;
            }
        } else if (m == -2) {
            return false;
        }
        if (!VersionUtils.isrFroyo()) {
            return false;
        }
        boolean z3;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (z) {
            z3 = audioManager.requestAudioFocus(null, 3, 2) == 1;
            m = 1;
        } else {
            if (audioManager.abandonAudioFocus(null) != 1) {
                z2 = false;
            }
            m = -2;
            z3 = z2;
        }
        return z3;
    }

    public static boolean p() {
        return Build.MODEL.equals("ZTE U930");
    }
}
