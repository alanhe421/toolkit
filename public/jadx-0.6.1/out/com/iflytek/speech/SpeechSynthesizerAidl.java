package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.a;
import com.iflytek.cloud.e;
import com.iflytek.speech.aidl.ISpeechSynthesizer;
import com.tencent.android.tpush.common.Constants;

public class SpeechSynthesizerAidl extends SpeechModuleAidl<ISpeechSynthesizer> {
    public SpeechSynthesizerAidl(Context context, a aVar) {
        super(context, aVar, UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
    }

    public boolean destory() {
        this.mService = null;
        return super.destory();
    }

    public /* bridge */ /* synthetic */ Intent getIntent() {
        return super.getIntent();
    }

    public String getParameter(String str) {
        if (!str.equals("local_speakers")) {
            return super.getParameter(str);
        }
        try {
            if (e.a() == null) {
                return null;
            }
            int e = e.a().e();
            return e >= 44 ? (e < Constants.ERRORCODE_UNKNOWN || e >= 10013) ? ((ISpeechSynthesizer) this.mService).getLocalSpeakerList() : null : null;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return "20999";
        }
    }

    public /* bridge */ /* synthetic */ boolean isActionInstalled(Context context, String str) {
        return super.isActionInstalled(context, str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public boolean isSpeaking() {
        try {
            return this.mService != null ? ((ISpeechSynthesizer) this.mService).isSpeaking() : false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int pauseSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).pauseSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int resumeSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).resumeSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startSpeaking(String str, SynthesizerListener synthesizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("text", str);
            return ((ISpeechSynthesizer) this.mService).startSpeaking(intent, synthesizerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopSpeaking(SynthesizerListener synthesizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            return ((ISpeechSynthesizer) this.mService).stopSpeaking(synthesizerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int synthesizeToUrl(String str, SynthesizerListener synthesizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (synthesizerListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("text", str);
            return ((ISpeechSynthesizer) this.mService).synthesizeToUrl(intent, synthesizerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }
}
