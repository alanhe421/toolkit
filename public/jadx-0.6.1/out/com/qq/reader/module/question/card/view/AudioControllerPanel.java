package com.qq.reader.module.question.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.view.RoundProgressView;
import com.tencent.feedback.proguard.R;

public class AudioControllerPanel extends LinearLayout implements OnClickListener {
    private View a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private RoundProgressView f;
    private a g;

    public interface a {
        void onClick(int i);
    }

    public AudioControllerPanel(Context context) {
        super(context);
        a(context);
    }

    public AudioControllerPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioControllerPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = LayoutInflater.from(context).inflate(R.layout.audio_controller_panel, this, true);
        this.b = (TextView) this.a.findViewById(R.id.audio_controller_timelong);
        this.c = (TextView) this.a.findViewById(R.id.audio_controller_state);
        this.d = (TextView) this.a.findViewById(R.id.audio_controller_send);
        this.e = (TextView) this.a.findViewById(R.id.audio_controller_re_record);
        this.f = (RoundProgressView) this.a.findViewById(R.id.audio_controller_play);
        this.f.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void setOnButtonClickListener(a aVar) {
        this.g = aVar;
    }

    public void setControllerState(int i) {
        switch (i) {
            case 0:
                this.d.setEnabled(false);
                this.e.setEnabled(false);
                this.f.setProgress(0);
                this.f.setStyle(1);
                this.f.setImageResource(R.drawable.audio_controller_idle);
                this.c.setText(R.string.audio_controller_state_idle);
                this.b.setText("0\"");
                return;
            case 1:
                this.d.setEnabled(true);
                this.e.setEnabled(true);
                this.f.setProgress(this.f.getMax());
                this.f.setStyle(0);
                this.f.setImageResource(R.drawable.audio_controller_play_selector);
                this.c.setText(R.string.audio_controller_state_ready);
                return;
            case 2:
                this.d.setEnabled(false);
                this.e.setEnabled(false);
                this.f.setProgress(0);
                this.f.setStyle(0);
                this.f.setImageResource(R.drawable.audio_controller_stop_selector);
                this.c.setText(R.string.audio_controller_state_recording);
                return;
            case 3:
                this.d.setEnabled(false);
                this.e.setEnabled(false);
                this.f.setProgress(0);
                this.f.setImageResource(R.drawable.audio_controller_stop_selector);
                this.f.setStyle(0);
                this.c.setText(R.string.audio_controller_state_playing);
                return;
            default:
                return;
        }
    }

    public void setProcessByPercent(float f) {
        this.f.setProgressByPercent(f);
    }

    public void setProgress(int i) {
        this.f.setProgress(i);
    }

    public void setMaxProgress(int i) {
        this.f.setMax(i);
    }

    public void setTimelong(int i) {
        if (i < 0) {
            this.b.setText("0\"");
        } else {
            this.b.setText(i + "\"");
        }
    }

    public void onClick(View view) {
        if (this.g != null) {
            switch (view.getId()) {
                case R.id.audio_controller_play:
                    this.g.onClick(0);
                    return;
                case R.id.audio_controller_send:
                    this.g.onClick(1);
                    return;
                case R.id.audio_controller_re_record:
                    this.g.onClick(2);
                    return;
                default:
                    return;
            }
        }
    }
}
