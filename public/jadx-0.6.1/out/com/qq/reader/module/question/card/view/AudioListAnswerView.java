package com.qq.reader.module.question.card.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.question.b;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.data.AudioData.AnswerData;
import com.qq.reader.module.question.loader.f;
import com.qq.reader.module.question.record.AudioMediaManager;
import com.qq.reader.module.question.record.PlayException;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;

public class AudioListAnswerView extends RelativeLayout implements Callback, OnClickListener {
    a a;
    boolean b;
    LayoutInflater c;
    TextView d;
    TextView e;
    ImageView f;
    ImageView g;
    TextView h;
    TextView i;
    View j;
    View k;
    View l;
    ImageView m;
    AudioData n;
    boolean o;
    ProgressBar p;
    View q;
    boolean r;
    int s = R.drawable.audio_answer_palying;
    int t = R.anim.audio_answer_downloading;
    int u = R.drawable.audio_play_c;
    Animation v;
    WeakReferenceHandler w = new WeakReferenceHandler(this);
    private int x = -1;
    private OnClickListener y;

    public interface a {
        void a(Message message);
    }

    public AudioListAnswerView(Context context) {
        super(context);
        a(context);
    }

    public AudioListAnswerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioListAnswerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.audio_com_list_answer_layout, this);
        d();
    }

    private void d() {
        this.d = (TextView) findViewById(R.id.tv_answer_tips);
        this.e = (TextView) findViewById(R.id.tv_answer_duration);
        this.f = (ImageView) findViewById(R.id.iv_answer);
        this.g = (ImageView) findViewById(R.id.iv_answer_play_btn);
        this.q = findViewById(R.id.rl_answer_bg_btn);
        this.h = (TextView) findViewById(R.id.right_lis_count);
        this.i = (TextView) findViewById(R.id.tv_answer_author_info);
        this.j = findViewById(R.id.rl_answer_container);
        this.k = findViewById(R.id.avatar_container);
        this.l = findViewById(R.id.rl_answer);
        this.m = (ImageView) findViewById(R.id.user_attr);
        this.p = (ProgressBar) findViewById(R.id.qa_pb);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_answer:
                if ((getContext() instanceof Activity) && this.n != null && this.n.b().f() != 0) {
                    o.c((Activity) getContext(), String.valueOf(this.n.b().f()), this.n.b().a(), this.n.b().g(), null);
                    return;
                }
                return;
            case R.id.rl_answer_bg_btn:
                if (!this.b) {
                    return;
                }
                if (c.b()) {
                    if (!(this.n == null || this.n == AudioMediaManager.a().l())) {
                        AudioMediaManager.a().a(this.n, this.w);
                    }
                    if (!this.o) {
                        if (AudioMediaManager.a().i()) {
                            AudioMediaManager.a().g();
                            return;
                        }
                        f.b().a(this.n, this.w);
                        this.o = true;
                        Drawable background = this.g.getBackground();
                        if (background instanceof AnimationDrawable) {
                            ((AnimationDrawable) background).stop();
                        }
                        this.g.setBackgroundResource(0);
                        if (this.v == null) {
                            this.v = AnimationUtils.loadAnimation(getContext(), this.t);
                        }
                        this.g.setBackgroundResource(R.drawable.audio_loading);
                        this.g.startAnimation(this.v);
                        return;
                    }
                    return;
                }
                e();
                return;
            default:
                return;
        }
    }

    private void e() {
        Message obtain = Message.obtain();
        obtain.what = 11000000;
        obtain.arg1 = 11000002;
        obtain.obj = this.n;
        if (this.a != null) {
            this.a.a(obtain);
        }
    }

    private void f() {
        this.g.clearAnimation();
        Drawable background = this.g.getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).stop();
        }
        this.g.setBackgroundResource(this.u);
    }

    public void a() {
        AudioMediaManager.a().a(this.n);
        if (this.n != null && !this.b) {
        }
    }

    private void g() {
        try {
            AudioMediaManager.a().f();
        } catch (PlayException e) {
            AudioMediaManager.a(ReaderApplication.getApplicationImp(), false);
            e.printStackTrace();
            com.qq.reader.common.monitor.debug.c.e("TAG", "media_play_fail" + e.toString());
            af.a(getContext(), "播放失败", 0).a();
            f();
        } catch (Exception e2) {
            AudioMediaManager.a(ReaderApplication.getApplicationImp(), false);
            af.a(getContext(), "未知错误", 0).a();
            f();
        }
    }

    public boolean handleMessage(Message message) {
        try {
            if (this.r && ((AudioData) message.obj).a().g().equals(this.n.a().g())) {
                if (this.a != null) {
                    this.a.a(message);
                }
                switch (message.what) {
                    case 1100403:
                        break;
                    case 1100407:
                        this.g.clearAnimation();
                        this.g.setBackgroundResource(this.s);
                        ((AnimationDrawable) this.g.getBackground()).start();
                        this.o = false;
                        this.d.setVisibility(4);
                        this.p.setVisibility(0);
                        break;
                    case 1100408:
                        f();
                        this.o = false;
                        this.d.setVisibility(0);
                        this.p.setVisibility(4);
                        AudioMediaManager.a().a(this.n);
                        break;
                    case 1100410:
                        try {
                            Bundle data = message.getData();
                            this.p.setMax(data.getInt("duration", 0));
                            this.p.setProgress(data.getInt("current", 0));
                            break;
                        } catch (Exception e) {
                            break;
                        }
                    case 11000000:
                        f();
                        this.o = false;
                        AudioMediaManager.a().a(this.n);
                        break;
                    case 11000006:
                    case 11000007:
                        this.w.postDelayed(new Runnable(this) {
                            final /* synthetic */ AudioListAnswerView a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if ((this.a.getContext() instanceof Activity) && !((Activity) this.a.getContext()).isFinishing()) {
                                    this.a.g();
                                }
                            }
                        }, 100);
                        this.o = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e2) {
        }
        return true;
    }

    public void a(AudioData audioData) {
        this.n = audioData;
        this.r = true;
        if (this.n.b() != null) {
            setVisibility(0);
            this.o = false;
            h();
            b(audioData);
            return;
        }
        setVisibility(8);
        a();
    }

    private void b(AudioData audioData) {
        f();
        AnswerData b = audioData.b();
        if (b.j() > 0) {
            this.e.setText(String.valueOf(b.j()) + "\"");
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(4);
        }
        com.qq.reader.common.imageloader.c.a(getContext()).a(b.g(), this.f, com.qq.reader.common.imageloader.a.a().f());
        this.g.setBackgroundResource(this.u);
        if (b.l() <= 0 || this.x != 1) {
            this.h.setVisibility(4);
        } else {
            this.h.setVisibility(0);
            this.h.setText(b.b((long) b.l()));
        }
        if (TextUtils.isEmpty(b.o()) || this.x != 1) {
            this.i.setVisibility(8);
            setAvatarTopMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_14));
        } else {
            this.i.setText(b.o());
            this.i.setVisibility(0);
            setAvatarTopMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_10));
        }
        b();
        this.m.setImageResource(ao.e(b.p()));
        if (audioData.b().f() == 0) {
            this.f.setOnClickListener(null);
            this.f.setClickable(false);
        } else {
            this.f.setClickable(true);
            this.f.setOnClickListener(this);
        }
        if (AudioMediaManager.a().l() == this.n && AudioMediaManager.a().i()) {
            this.g.clearAnimation();
            this.g.setBackgroundResource(this.s);
            ((AnimationDrawable) this.g.getBackground()).start();
        }
    }

    public void b() {
        com.qq.reader.module.question.b.a a = b.a(this.n);
        if (TextUtils.isEmpty(a.a)) {
            this.d.setText("");
            this.d.setVisibility(4);
        } else {
            this.d.setVisibility(0);
            this.d.setText(a.a);
        }
        this.q.setBackgroundResource(a.b);
    }

    public void setOnPlayBtnClickListener(OnClickListener onClickListener) {
        this.y = onClickListener;
        this.q.setOnClickListener(this.y);
    }

    private void h() {
        if (this.b) {
            this.q.setOnClickListener(this);
        } else {
            this.q.setOnClickListener(this.y);
        }
    }

    public void setType(int i) {
        if (this.x != i) {
            this.x = i;
            switch (this.x) {
                case 0:
                    this.m.setVisibility(8);
                    setAvatarTopMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_14));
                    this.i.setVisibility(8);
                    return;
                case 1:
                    this.m.setVisibility(0);
                    setAvatarTopMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_10));
                    this.i.setVisibility(0);
                    return;
                case 2:
                    this.m.setVisibility(8);
                    setAvatarTopMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_14));
                    this.i.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    public void setPlayEnable(boolean z) {
        this.b = z;
        h();
    }

    public void c() {
        if (this.q != null) {
            this.q.performClick();
        }
    }

    public void setCallBack(a aVar) {
        this.a = aVar;
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }

    private void setAvatarTopMargin(int i) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.k.getLayoutParams();
        marginLayoutParams.setMargins(0, i, 0, 0);
        this.k.setLayoutParams(marginLayoutParams);
    }

    public void a(int i) {
        this.e.setTextColor(i);
    }
}
