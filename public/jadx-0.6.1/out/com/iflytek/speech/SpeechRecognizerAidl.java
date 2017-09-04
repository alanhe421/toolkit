package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.iflytek.cloud.a;
import com.iflytek.cloud.e;
import com.iflytek.speech.aidl.ISpeechRecognizer;

public class SpeechRecognizerAidl extends SpeechModuleAidl<ISpeechRecognizer> {
    public SpeechRecognizerAidl(Context context, a aVar) {
        super(context, aVar, UtilityConfig.ACTION_SPEECH_RECOGNIZER);
    }

    public int buildGrammar(String str, String str2, GrammarListener grammarListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (grammarListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("grammar_type", str);
            intent.putExtra("grammar_content", str2);
            ((ISpeechRecognizer) this.mService).buildGrammar(intent, grammarListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int cancel(RecognizerListener recognizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).cancel(recognizerListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public boolean destory() {
        this.mService = null;
        return super.destory();
    }

    public /* bridge */ /* synthetic */ Intent getIntent() {
        return super.getIntent();
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public /* bridge */ /* synthetic */ boolean isActionInstalled(Context context, String str) {
        return super.isActionInstalled(context, str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public boolean isListening() {
        try {
            return this.mService != null ? ((ISpeechRecognizer) this.mService).isListening() : false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startListening(RecognizerListener recognizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).startListening(getIntent(), recognizerListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopListening(RecognizerListener recognizerListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (recognizerListener == null) {
            return 20012;
        }
        try {
            ((ISpeechRecognizer) this.mService).stopListening(recognizerListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int updateLexicon(String str, String str2, LexiconListener lexiconListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (lexiconListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra("lexicon_name", str);
            intent.putExtra("lexicon_content", str2);
            ((ISpeechRecognizer) this.mService).updateLexicon(intent, lexiconListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (e.a().e() < 44) {
            return 20018;
        }
        if (this.mService == null) {
            return 21003;
        }
        try {
            ((ISpeechRecognizer) this.mService).writeAudio(getIntent(), bArr, i, i2);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
