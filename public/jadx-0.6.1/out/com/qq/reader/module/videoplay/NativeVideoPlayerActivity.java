package com.qq.reader.module.videoplay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.module.videoplay.NativeMediaController.a;
import com.qq.reader.module.videoplay.NativeMediaController.b;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class NativeVideoPlayerActivity extends Activity implements OnPreparedListener, Callback, a, b {
    protected MediaPlayer a;
    protected NativeMediaController b;
    protected Intent c;
    private SurfaceView d;
    private View e;
    private SurfaceHolder f;
    private int g;
    private String h;
    private boolean i;
    private boolean j;
    private int k;
    private float l;
    private WeakReferenceHandler m = new WeakReferenceHandler(new Handler.Callback(this) {
        final /* synthetic */ NativeVideoPlayerActivity a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    this.a.i = true;
                    this.a.n();
                    break;
                case 200:
                    this.a.j = true;
                    this.a.n();
                    break;
            }
            return true;
        }
    });

    private static class VideoEXtractTask extends ReaderShortTask {
        private String extractPath;
        private WeakReference<Handler> handlerWeakReference;
        private String path;

        public VideoEXtractTask(String str, Handler handler, String str2) {
            this.path = str;
            this.handlerWeakReference = new WeakReference(handler);
            this.extractPath = str2;
        }

        public void run() {
            IOException e;
            Throwable th;
            super.run();
            if (this.path != null) {
                format.epub.common.b.b b = format.epub.common.b.b.b(this.path);
                String c = b.f().c();
                if (com.qq.reader.readengine.model.a.j(c)) {
                    com.qq.reader.common.drm.teb.a.b(c);
                }
                c = this.extractPath;
                File file = new File(c);
                File file2 = new File(c + f.DOWNLOAD_FILE_TMP);
                OutputStream outputStream = null;
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    if (file2.exists()) {
                        file2.delete();
                    }
                    ab.c(file2);
                    byte[] bArr = new byte[153600];
                    OutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        InputStream i = b.i();
                        while (true) {
                            int read = i.read(bArr);
                            if (read < 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        file2.renameTo(file);
                        Handler handler = (Handler) this.handlerWeakReference.get();
                        if (handler != null) {
                            handler.sendEmptyMessage(200);
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        outputStream = fileOutputStream;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = fileOutputStream;
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    try {
                        e2.printStackTrace();
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.video_native_activity);
        this.c = getIntent();
        if (bundle != null) {
            this.g = bundle.getInt("videoPosition", 0);
        }
        this.d = (SurfaceView) findViewById(R.id.video_surface);
        this.e = findViewById(R.id.video_surface_container);
        this.f = this.d.getHolder();
        this.f.addCallback(this);
        this.b = new NativeMediaController(this);
        this.b.setUIGenerator(this);
        com.qq.reader.common.drm.a.a = this.c.getStringExtra("zipkey");
        String stringExtra = this.c.getStringExtra("path");
        if (stringExtra != null) {
            this.k = this.c.getIntExtra("file_type", -1);
            if (this.k == 2) {
                this.h = ReaderApplication.getApplicationImp().getExternalCacheDir().getPath() + "/" + stringExtra.hashCode() + stringExtra.substring(stringExtra.lastIndexOf("/") + 1);
                if (new File(this.h).exists()) {
                    this.m.sendEmptyMessage(200);
                } else {
                    g.a().a(new VideoEXtractTask(stringExtra, this.m, this.h));
                }
            } else if (this.k == 1 || this.k == 3) {
                this.h = stringExtra;
                this.m.sendEmptyMessage(200);
            }
        }
    }

    public a a() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.video_native_media_controler_custom, null);
        a aVar = new a();
        aVar.a = inflate;
        aVar.b = (ImageButton) inflate.findViewById(R.id.video_native_media_controller_custom_btn_start);
        aVar.d = (TextView) inflate.findViewById(R.id.video_native_media_controller_custom_currenttime);
        aVar.c = (TextView) inflate.findViewById(R.id.video_native_media_controller_custom_totaltime);
        aVar.e = (SeekBar) inflate.findViewById(R.id.video_native_media_controller_custom_seekbar);
        aVar.f = (ImageButton) inflate.findViewById(R.id.video_native_media_controller_custom_btn_unfullscreen);
        aVar.l = R.drawable.selector_video_btn_pause;
        aVar.k = R.drawable.selector_video_btn_start;
        aVar.m = R.drawable.selector_video_btn_fullscreen;
        aVar.n = R.drawable.selector_video_btn_unfullscreen;
        return aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.b.b();
        return false;
    }

    private void n() {
        if (this.j && this.i && this.a == null) {
            try {
                this.a = new MediaPlayer();
                this.a.setOnInfoListener(new OnInfoListener(this) {
                    final /* synthetic */ NativeVideoPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                        switch (i) {
                            case 701:
                                this.a.findViewById(R.id.video_play_loading).setVisibility(0);
                                break;
                            case 702:
                                this.a.findViewById(R.id.video_play_loading).setVisibility(8);
                                break;
                        }
                        return false;
                    }
                });
                this.a.setOnVideoSizeChangedListener(new OnVideoSizeChangedListener(this) {
                    final /* synthetic */ NativeVideoPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                        this.a.l = ((float) i) / ((float) i2);
                        this.a.o();
                    }
                });
                if (this.k == 3) {
                    this.a.setDataSource(this, Uri.parse(this.h));
                } else {
                    this.a.setDataSource(this.h);
                }
                this.a.setAudioStreamType(3);
                this.a.setDisplay(this.f);
                this.a.prepareAsync();
                this.a.setOnPreparedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void o() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        LayoutParams layoutParams = this.e.getLayoutParams();
        if (k()) {
            layoutParams.height = -1;
            this.e.setLayoutParams(layoutParams);
            a((int) (((float) height) * this.l), height);
            return;
        }
        layoutParams.height = (int) (((float) width) / this.l);
        this.e.setLayoutParams(layoutParams);
        a(width, layoutParams.height);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.a != null) {
            this.a.setDisplay(this.f);
            return;
        }
        this.i = true;
        this.m.sendEmptyMessage(100);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    private void a(int i, int i2) {
        LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.d.setLayoutParams(layoutParams);
    }

    public void b() {
        if (this.a != null) {
            this.a.start();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.pause();
        }
    }

    public int d() {
        if (this.a != null) {
            return this.a.getDuration();
        }
        return 0;
    }

    public int e() {
        if (this.a != null) {
            return this.a.getCurrentPosition();
        }
        return 0;
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.seekTo(i);
        }
    }

    public boolean f() {
        return this.a != null && this.a.isPlaying();
    }

    public int g() {
        return 0;
    }

    public boolean h() {
        return true;
    }

    public boolean i() {
        return true;
    }

    public boolean j() {
        return true;
    }

    public boolean k() {
        return getRequestedOrientation() == 0;
    }

    public void l() {
        if (getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        m();
        o();
    }

    public void onBackPressed() {
        if (getRequestedOrientation() == 0) {
            l();
        } else {
            super.onBackPressed();
        }
    }

    public void m() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
        if (VERSION.SDK_INT >= 14) {
            systemUiVisibility ^= 2;
        }
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility ^= 4;
        }
        if (VERSION.SDK_INT >= 19) {
            systemUiVisibility ^= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        findViewById(R.id.video_play_loading).setVisibility(8);
        this.b.setMediaPlayer(this);
        this.b.setAnchorView((FrameLayout) this.e);
        this.b.b();
        this.a.start();
        a(this.g);
        this.b.d();
    }

    protected void onPause() {
        super.onPause();
        this.g = e();
        if (this.a != null) {
            this.a.pause();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("videoPosition", this.g);
    }

    protected void onDestroy() {
        super.onDestroy();
        p();
    }

    private void p() {
        if (this.a != null) {
            this.a.stop();
            this.a.release();
            this.a = null;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
