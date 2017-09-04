package com.qq.reader.plugin.audiobook.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.plugin.tts.n;

public class TtsMediaButtonReceiver extends BroadcastReceiver {
    private static final String a = MediaButtonIntentReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        c.a(a, "TtsMediaButtonReceiver onReceive intent =" + intent);
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            if (n.f().h() && n.f().i()) {
                n.f().r();
            }
        } else if (keyEvent == null || keyEvent.getKeyCode() != 79 || keyEvent.getAction() != 1 || !n.f().h()) {
        } else {
            if (n.f().i()) {
                n.f().r();
            } else {
                n.f().s();
            }
        }
    }
}
