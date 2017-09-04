package com.qq.reader.module.question.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.qq.reader.activity.ReaderBaseActivity;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.io.IOException;

public class TestAudioActivity extends ReaderBaseActivity {
    Button a;
    Button b;
    boolean c = false;
    MediaRecorder d;
    MediaPlayer e;
    File f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.test_audio_layout);
        this.f = new File(getCacheDir(), "test_audio.amr");
        this.a = (Button) findViewById(R.id.audio_recoder);
        this.b = (Button) findViewById(R.id.audio_play);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TestAudioActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c) {
                    try {
                        if (this.a.d != null && this.a.c) {
                            this.a.c = false;
                            this.a.d.stop();
                            this.a.d.release();
                            this.a.d = null;
                            this.a.a.setText("录制");
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    if (this.a.f != null && this.a.f.exists()) {
                        this.a.f.delete();
                    }
                    this.a.d = new MediaRecorder();
                    this.a.d.setAudioChannels(2);
                    this.a.d.setAudioSource(1);
                    this.a.d.setAudioSamplingRate(8000);
                    this.a.d.setAudioEncodingBitRate(12200);
                    this.a.d.setOutputFormat(0);
                    this.a.d.setAudioEncoder(1);
                    this.a.d.setOutputFile(this.a.f.getAbsolutePath());
                    this.a.d.setOnErrorListener(new OnErrorListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                            this.a.a.d.stop();
                            this.a.a.d.release();
                            this.a.a.d = null;
                            this.a.a.c = false;
                            Toast.makeText(this.a.a, "录音发生错误", 0).show();
                        }
                    });
                    this.a.d.prepare();
                    this.a.d.start();
                    this.a.c = true;
                    this.a.a.setText("录制中...");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TestAudioActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != null && this.a.f.exists()) {
                    this.a.a();
                }
            }
        });
    }

    private void a() {
        try {
            this.e = new MediaPlayer();
            this.e.setDataSource(this.f.getAbsolutePath());
            this.e.prepare();
            this.e.start();
            this.e.setOnCompletionListener(new OnCompletionListener(this) {
                final /* synthetic */ TestAudioActivity a;

                {
                    this.a = r1;
                }

                public void onCompletion(MediaPlayer mediaPlayer) {
                    this.a.b();
                    this.a.b.setEnabled(true);
                }
            });
            this.b.setEnabled(false);
        } catch (IOException e) {
            Toast.makeText(this, "prepare() failed", 0).show();
        }
    }

    private void b() {
        if (this.e != null) {
            this.e.stop();
            this.e.release();
            this.e = null;
        }
    }
}
