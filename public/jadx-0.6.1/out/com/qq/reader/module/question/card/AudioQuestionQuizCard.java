package com.qq.reader.module.question.card;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.activity.NativeAudioQuestionQuizActivity;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class AudioQuestionQuizCard extends com.qq.reader.module.bookstore.qnative.card.a {
    public static final int MAX_BOOKCOIN_COUNT = 999999;
    public static final int MAX_INPUT_COUNT = 60;
    public static final int MIN_INPUT_COUNT = 10;
    private final String TAG = AudioQuestionQuizCard.class.getName();
    String mAuthorImg;
    String mAuthorName;
    int mAuthorTag;
    private a mContentTextWatcher;
    int mDefaultPay;
    String mIntro;
    String mPayHelpTips;
    private a mPay_addTextWatcher;
    int mUserAddPayNum = 0;
    String mUserInputContent = null;
    private int state = 0;

    private class a implements TextWatcher {
        private boolean a;
        final /* synthetic */ AudioQuestionQuizCard e;

        private a(AudioQuestionQuizCard audioQuestionQuizCard) {
            this.e = audioQuestionQuizCard;
        }

        public boolean a() {
            return this.a;
        }

        public void a(boolean z) {
            this.a = z;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public AudioQuestionQuizCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_question_quiz_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mAuthorTag = jSONObject.optInt("authortag");
        this.mIntro = jSONObject.optString("authorintro");
        this.mAuthorImg = jSONObject.optString("authorimg");
        this.mDefaultPay = jSONObject.optInt(OpenConstants.API_NAME_PAY);
        this.state = jSONObject.optInt("state", 0);
        this.mAuthorName = jSONObject.optString("authorname");
        this.mPayHelpTips = jSONObject.optString("eavesDroppingMsg");
        return true;
    }

    public void attachView() {
        c.a(getEvnetListener().getFromActivity()).a(this.mAuthorImg, (ImageView) ap.a(getRootView(), R.id.img_author_avatar), com.qq.reader.common.imageloader.a.a().o());
        ((ImageView) ap.a(getRootView(), R.id.img_author_level)).setImageResource(getAuthorLevelResId(this.mAuthorTag));
        TextView textView = (TextView) ap.a(getRootView(), R.id.quiz_intro);
        View a = ap.a(getRootView(), R.id.quiz_edit_pannel);
        if (this.state == 1) {
            if (TextUtils.isEmpty(this.mIntro)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(this.mIntro);
            }
            a.setVisibility(0);
            final Button button = (Button) ap.a(getRootView(), R.id.quiz_submit);
            final TextView textView2 = (TextView) ap.a(getRootView(), R.id.quiz_pay_num);
            textView2.setText(String.valueOf(this.mDefaultPay + this.mUserAddPayNum));
            ((TextView) ap.a(getRootView(), R.id.audio_quiz_tip)).setText(this.mPayHelpTips);
            final EditText editText = (EditText) ap.a(getRootView(), R.id.quiz_pay_addnum);
            final TextView textView3 = (TextView) ap.a(getRootView(), R.id.quiz_pay_addinfo);
            if (this.mPay_addTextWatcher == null) {
                this.mPay_addTextWatcher = new a(this) {
                    final /* synthetic */ AudioQuestionQuizCard d;

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        if (editable.length() > 0) {
                            textView3.setVisibility(0);
                            editText.setHint("");
                            editText.setTextSize(0, (float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_4));
                        } else {
                            textView3.setVisibility(8);
                            editText.setHint(R.string.audio_quiz_pay_addmoney);
                            editText.setTextSize(0, (float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_2));
                        }
                        try {
                            this.d.mUserAddPayNum = Integer.parseInt(editable.toString());
                        } catch (Exception e) {
                            this.d.mUserAddPayNum = 0;
                        }
                        if (this.d.mUserAddPayNum > AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                            af.a(this.d.getEvnetListener().getFromActivity(), R.string.audio_quiz_maxbookcoin_toast, 0).a();
                            editable.replace(0, editable.length(), String.valueOf(AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT));
                            return;
                        }
                        textView2.setText(String.valueOf(this.d.mDefaultPay + this.d.mUserAddPayNum));
                    }
                };
            }
            if (!this.mPay_addTextWatcher.a()) {
                editText.addTextChangedListener(this.mPay_addTextWatcher);
                this.mPay_addTextWatcher.a(true);
            }
            editText.setFilters(new InputFilter[]{new InputFilter(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                    return TextUtils.isDigitsOnly(charSequence) ? charSequence : "";
                }
            }});
            if (this.mUserAddPayNum > 0) {
                editText.setText(String.valueOf(this.mUserAddPayNum));
            } else {
                editText.setText("");
            }
            final EditText editText2 = (EditText) ap.a(getRootView(), R.id.quiz_content);
            if (this.mContentTextWatcher == null) {
                this.mContentTextWatcher = new a(this) {
                    CharSequence a = null;
                    final /* synthetic */ AudioQuestionQuizCard d;

                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        this.a = charSequence.toString();
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        this.d.mUserInputContent = editable.toString().trim();
                        if (this.d.mUserInputContent.length() <= 0) {
                            button.setEnabled(false);
                            return;
                        }
                        button.setEnabled(true);
                        if (this.d.mUserInputContent.length() > 60) {
                            editText2.setText(this.a);
                            af.a(this.d.getEvnetListener().getFromActivity(), R.string.audio_quiz_maxinput_toast, 0).a();
                        }
                    }
                };
            }
            if (!this.mContentTextWatcher.a()) {
                editText2.addTextChangedListener(this.mContentTextWatcher);
                this.mContentTextWatcher.a(true);
            }
            AnonymousClass4 anonymousClass4 = new InputFilter(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                    if (Pattern.compile("[\n\r]").matcher(charSequence).find()) {
                        return "";
                    }
                    return null;
                }
            };
            AnonymousClass5 anonymousClass5 = new InputFilter(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                    if (Pattern.compile("[🀀-🏿]|[🐀-🟿]|[☀-⟿]", 66).matcher(charSequence).find()) {
                        return "";
                    }
                    return null;
                }
            };
            editText2.setFilters(new InputFilter[]{anonymousClass4, anonymousClass5});
            editText2.setOnEditorActionListener(new OnEditorActionListener(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    return keyEvent.getKeyCode() == 66;
                }
            });
            if (TextUtils.isEmpty(this.mUserInputContent)) {
                editText2.setText("");
            } else {
                editText2.setText(this.mUserInputContent);
            }
            OnFocusChangeListener anonymousClass7 = new OnFocusChangeListener(this) {
                final /* synthetic */ AudioQuestionQuizCard c;

                public void onFocusChange(View view, boolean z) {
                    InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
                    NativeAudioQuestionQuizActivity nativeAudioQuestionQuizActivity = (NativeAudioQuestionQuizActivity) this.c.getEvnetListener().getFromActivity();
                    if (z) {
                        if (inputMethodManager.isActive(editText2) || inputMethodManager.isActive(editText)) {
                            try {
                                nativeAudioQuestionQuizActivity.a(0, ((ViewGroup) editText2.getParent()).getTop() * -1);
                            } catch (Exception e) {
                            }
                        }
                        if (view == editText) {
                            i.a("event_D186", null, ReaderApplication.getApplicationImp());
                        }
                    }
                }
            };
            editText2.setOnFocusChangeListener(anonymousClass7);
            editText.setOnFocusChangeListener(anonymousClass7);
            ap.a(getRootView(), R.id.audio_quiz_tip).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.h(this.a.getEvnetListener().getFromActivity(), "helpDetail.html?id=1128&tf=1", null);
                }
            });
            button.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioQuestionQuizCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_D187", null, ReaderApplication.getApplicationImp());
                    if (this.a.mUserInputContent.length() < 10) {
                        af.a(this.a.getEvnetListener().getFromActivity(), R.string.audio_quiz_mininput_toast, 0).a();
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("audio_action", "audio_quiz_submit");
                    bundle.putString("audio_content", this.a.mUserInputContent);
                    bundle.putInt("audio_price", this.a.mDefaultPay + this.a.mUserAddPayNum);
                    this.a.getEvnetListener().doFunction(bundle);
                }
            });
            return;
        }
        textView.setVisibility(0);
        textView.setText("很抱歉，暂时无法提问");
        a.setVisibility(8);
    }

    private int getAuthorLevelResId(int i) {
        switch (i) {
            case 4:
                return R.drawable.card_platinum;
            case 5:
                return R.drawable.card_god;
            case 6:
                return R.drawable.card_star;
            case 7:
                return R.drawable.card_auther;
            default:
                return 0;
        }
    }

    public String getAuthorName() {
        return this.mAuthorName;
    }

    public int getState() {
        return this.state;
    }

    public void resetCard() {
        this.mUserAddPayNum = 0;
        this.mUserInputContent = "";
        attachView();
    }
}
