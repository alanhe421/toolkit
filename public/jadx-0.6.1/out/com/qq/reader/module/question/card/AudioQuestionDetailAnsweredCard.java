package com.qq.reader.module.question.card;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.AudioQuestionBuyTask;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioListAnswerView;
import com.qq.reader.module.question.card.view.AudioListTopUserBtmTitleView;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.data.AudioData.AnswerData;
import com.qq.reader.module.readpage.q;
import com.qq.reader.plugin.audiobook.core.e;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class AudioQuestionDetailAnsweredCard extends a implements Callback {
    private int autoPlay;
    AudioData data;
    private WeakReference<AudioListAnswerView> mAudioComItemViewWeakReference;
    private WeakReferenceHandler mHandler;
    int ownerType;

    public AudioQuestionDetailAnsweredCard(b bVar, String str) {
        super(bVar, str);
    }

    public AudioQuestionDetailAnsweredCard(b bVar, String str, int i) {
        super(bVar, str);
        this.ownerType = i;
    }

    public int getResLayoutId() {
        return R.layout.audio_question_detail_card_of_answerd;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.data = new AudioData();
        this.data.a(jSONObject);
        return true;
    }

    public void attachView() {
        if (this.mHandler == null) {
            this.mHandler = new WeakReferenceHandler(this);
        }
        ((AudioListTopUserBtmTitleView) ap.a(getRootView(), R.id.title_view)).a(this.data);
        if (this.data.a().i() > 0) {
            ((TextView) ap.a(getRootView(), R.id.tv_price)).setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(this.data.a().i())}));
            ap.a(getRootView(), R.id.ll_price_container).setVisibility(0);
        } else {
            ap.a(getRootView(), R.id.ll_price_container).setVisibility(8);
        }
        final AudioListAnswerView audioListAnswerView = (AudioListAnswerView) ap.a(getRootView(), R.id.answerView);
        this.mAudioComItemViewWeakReference = new WeakReference(audioListAnswerView);
        audioListAnswerView.setType(0);
        audioListAnswerView.setPlayEnable(true);
        audioListAnswerView.setCallBack(new AudioListAnswerView.a(this) {
            final /* synthetic */ AudioQuestionDetailAnsweredCard b;

            public void a(Message message) {
                Bundle bundle;
                switch (message.what) {
                    case 1100407:
                        this.b.getEvnetListener().getFromActivity().sendBroadcast(new Intent(e.f));
                        this.b.getEvnetListener().getFromActivity().getWindow().addFlags(128);
                        bundle = new Bundle();
                        bundle.putString("audio_action", "audio_detail_listen");
                        this.b.getEvnetListener().doFunction(bundle);
                        return;
                    case 1100408:
                        this.b.getEvnetListener().getFromActivity().getWindow().clearFlags(128);
                        return;
                    case 11000000:
                        switch (message.arg1) {
                            case 11000001:
                                this.b.createDialog(606, null).show();
                                return;
                            case 11000002:
                                final ReaderBaseActivity readerBaseActivity = (ReaderBaseActivity) this.b.getEvnetListener().getFromActivity();
                                readerBaseActivity.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass1 b;

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                audioListAnswerView.c();
                                                return;
                                            case 2:
                                            case 3:
                                                readerBaseActivity.mLoginNextTask = null;
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                };
                                readerBaseActivity.startLogin();
                                return;
                            case 11000003:
                                af.a(this.b.getEvnetListener().getFromActivity(), "问题不存在", 0).a();
                                return;
                            case 11000004:
                                af.a(this.b.getEvnetListener().getFromActivity(), this.b.getEvnetListener().getFromActivity().getString(R.string.author_edit_fenda_server_error), 0).a();
                                return;
                            case 11000005:
                                af.a(this.b.getEvnetListener().getFromActivity(), this.b.getEvnetListener().getFromActivity().getString(R.string.net_not_available), 0).a();
                                return;
                            case 11000008:
                                bundle = new Bundle();
                                bundle.putString("confirm", this.b.getEvnetListener().getFromActivity().getString(R.string.audio_question_charge_submit_1));
                                bundle.putString(MessageKey.MSG_CONTENT, this.b.getEvnetListener().getFromActivity().getString(R.string.audio_question_eavesdropping_msg, new Object[]{Integer.valueOf(this.b.data.b().i())}));
                                this.b.createDialog(608, bundle).show();
                                return;
                            default:
                                return;
                        }
                    default:
                        return;
                }
            }
        });
        audioListAnswerView.a(this.data);
        AnswerData b = this.data.b();
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_income_info);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_listencount);
        if (b.l() > 0) {
            textView2.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_listen_count), new Object[]{Integer.valueOf(b.l())}));
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        if (this.ownerType <= 0) {
            textView.setVisibility(8);
        } else if (b.b() > 0) {
            if (this.ownerType == 1) {
                textView.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.usercenter_fenda_questions_state_ticket), new Object[]{Integer.valueOf(b.b()), Integer.valueOf(b.c())}));
            } else if (this.ownerType == 2) {
                textView.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.usercenter_fenda_questions_state_coins), new Object[]{Integer.valueOf(b.b()), Integer.valueOf(b.c())}));
            }
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        if (this.autoPlay == 1) {
            audioListAnswerView.c();
            this.autoPlay = 0;
        }
    }

    private void buyAudio(String str) {
        g.a().a(new AudioQuestionBuyTask(str, new c(this) {
            final /* synthetic */ AudioQuestionDetailAnsweredCard a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Bundle bundle = new Bundle();
                    int optInt = jSONObject.optInt("code");
                    bundle.putString(SocialConstants.PARAM_SEND_MSG, jSONObject.optString(SocialConstants.PARAM_SEND_MSG));
                    JSONArray optJSONArray = jSONObject.optJSONArray("ul");
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("cl");
                    String str2 = "";
                    String str3 = "";
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        jSONObject = optJSONArray2.getJSONObject(0);
                        str2 = jSONObject.optString("cn");
                        str3 = jSONObject.optString("vkey");
                    }
                    if (optJSONArray != null && str2.length() > 0 && str3.length() > 0) {
                        String[] strArr = new String[optJSONArray.length()];
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            if (jSONObject2 != null) {
                                String optString = jSONObject2.optString(SocialConstants.PARAM_URL);
                                if (optString.length() != 0) {
                                    StringBuilder stringBuilder = new StringBuilder(optString);
                                    stringBuilder.append(str2);
                                    stringBuilder.append("?vkey=");
                                    stringBuilder.append(str3);
                                    strArr[i] = stringBuilder.toString();
                                }
                            }
                        }
                        bundle.putStringArray("downloadurls", strArr);
                    }
                    Message obtainMessage = this.a.mHandler.obtainMessage(1100104);
                    obtainMessage.arg1 = optInt;
                    obtainMessage.obj = bundle;
                    this.a.mHandler.sendMessage(obtainMessage);
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("AudioComBaseCardDisablePlay", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                Message obtainMessage = this.a.mHandler.obtainMessage(1100104);
                obtainMessage.arg1 = -1;
                Bundle bundle = new Bundle();
                bundle.putString(SocialConstants.PARAM_SEND_MSG, this.a.getEvnetListener().getFromActivity().getString(R.string.net_not_available));
                obtainMessage.obj = bundle;
                this.a.mHandler.sendMessage(obtainMessage);
            }
        }));
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1100104:
                if (message.obj instanceof Bundle) {
                    Bundle bundle = (Bundle) message.obj;
                    switch (message.arg1) {
                        case -1022:
                            try {
                                bundle = new Bundle();
                                bundle.putString("confirm", getEvnetListener().getFromActivity().getString(R.string.audio_question_charge_submit));
                                bundle.putString(MessageKey.MSG_CONTENT, getEvnetListener().getFromActivity().getString(R.string.audio_question_charge_msg));
                                createDialog(608, bundle).show();
                                break;
                            } catch (Exception e) {
                                break;
                            }
                        case 0:
                            this.data.b().a(bundle.getStringArray("downloadurls"));
                            AudioListAnswerView audioListAnswerView = (AudioListAnswerView) this.mAudioComItemViewWeakReference.get();
                            this.data.b().b(1);
                            if (audioListAnswerView != null) {
                                audioListAnswerView.c();
                                audioListAnswerView.b();
                                break;
                            }
                            break;
                        default:
                            CharSequence string = bundle.getString(SocialConstants.PARAM_SEND_MSG);
                            if (TextUtils.isEmpty(string)) {
                                string = getEvnetListener().getFromActivity().getString(R.string.net_not_available);
                            }
                            af.a(getEvnetListener().getFromActivity(), string, 0).a();
                            break;
                    }
                }
                return true;
        }
        return false;
    }

    private Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(getEvnetListener().getFromActivity(), i, null);
        switch (i) {
            case 606:
                alertDialog.a(getEvnetListener().getFromActivity().getString(R.string.audio_question_eavesdropping_msg, new Object[]{Integer.valueOf(this.data.b().i())}));
                alertDialog.a(R.string.audio_question_eavesdropping_submit, new OnClickListener(this) {
                    final /* synthetic */ AudioQuestionDetailAnsweredCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.buyAudio(this.a.data.a().g());
                    }
                });
                alertDialog.b(R.string.audio_question_eavesdropping_cancel, null);
                break;
            case 608:
                alertDialog.a(bundle.getString(MessageKey.MSG_CONTENT));
                alertDialog.a(bundle.getString("confirm"), new OnClickListener(this) {
                    final /* synthetic */ AudioQuestionDetailAnsweredCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.charge();
                    }
                });
                alertDialog.a(-1, R.drawable.selector_orange_button);
                break;
        }
        return alertDialog;
    }

    private void charge() {
        new JSPay(getEvnetListener().getFromActivity()).startCharge(getEvnetListener().getFromActivity(), 0);
    }

    public AudioData getAudioData() {
        return this.data;
    }

    public void setAutoPlay(int i) {
        this.autoPlay = i;
    }
}
