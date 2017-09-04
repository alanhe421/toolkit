package com.qq.reader.common.web.js;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.RewardCommentTask;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AlertDialog.a;
import com.qq.reader.view.af;
import com.qq.reader.view.ag;
import com.qq.reader.view.o;
import com.qq.reader.view.web.f;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class JSDialog extends b {
    private Activity a;
    private ag b;

    public JSDialog(ReaderBaseActivity readerBaseActivity) {
        this.a = readerBaseActivity;
    }

    public void showSimpleDialog(String str, String str2, String str3) {
        a aVar = new a(this.a);
        aVar.a((CharSequence) str).b((CharSequence) str2).a((CharSequence) str3, new OnClickListener(this) {
            final /* synthetic */ JSDialog a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.a().show();
    }

    public void showDialog(String str, String str2, String str3, final String str4, final String str5) {
        ag a = a();
        a.a(str, str2, str3);
        a.a(new ag.a(this) {
            final /* synthetic */ JSDialog c;

            public void a() {
                if (str4.equals(Constants.DEFAULT_UIN)) {
                    Intent intent = new Intent(this.c.a, WebBrowserForContents.class);
                    intent.putExtra("com.qq.reader.WebContent", str5);
                    this.c.a.startActivity(intent);
                }
            }
        });
        a.f_();
    }

    private ag a() {
        if (this.b == null) {
            this.b = new ag(this.a);
        }
        return this.b;
    }

    public void showBottomDialog(String str) {
        JSONObject jSONObject;
        c.a("dialog", "showBottomDialog " + str);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject != null) {
            int optInt = jSONObject.optInt("pagecode");
            CharSequence optString = jSONObject.optString("title");
            CharSequence optString2 = jSONObject.optString("positiestr");
            CharSequence optString3 = jSONObject.optString("negetivestr");
            CharSequence optString4 = jSONObject.optString(MessageKey.MSG_CONTENT);
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.dialog_layout, null);
            TextView textView = (TextView) inflate.findViewById(R.id.content);
            final EditText editText = (EditText) inflate.findViewById(R.id.edittext);
            if (TextUtils.isEmpty(optString4)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(optString4);
            }
            AlertDialog a;
            switch (optInt) {
                case 1001:
                    a = new a(this.a).a(inflate).a(optString).b(optString4).a(optString2, new OnClickListener(this) {
                        final /* synthetic */ JSDialog a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            new JSPay(this.a.a).startCharge(this.a.a, 0);
                            dialogInterface.dismiss();
                        }
                    }).b(optString3, new OnClickListener(this) {
                        final /* synthetic */ JSDialog a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).a();
                    editText.setVisibility(8);
                    inflate.setVisibility(8);
                    a.setCanceledOnTouchOutside(false);
                    a.show();
                    return;
                case 1002:
                    CharSequence optString5 = jSONObject.optString("commentContent");
                    final String optString6 = jSONObject.optString("cid");
                    final String optString7 = jSONObject.optString("bid");
                    editText.setHint(optString5);
                    editText.setVisibility(0);
                    inflate.setVisibility(0);
                    a = new a(this.a).a(inflate).a(optString).a(optString2, new OnClickListener(this) {
                        final /* synthetic */ JSDialog d;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (editText.getText() != null) {
                                String obj = editText.getText().toString();
                                if (TextUtils.isEmpty(obj)) {
                                    if (editText.getHint() != null) {
                                        Object charSequence = editText.getHint().toString();
                                        if (!TextUtils.isEmpty(charSequence)) {
                                            this.d.addRewardComment(optString7, optString6, charSequence);
                                            dialogInterface.dismiss();
                                            return;
                                        }
                                    }
                                    this.d.a("请输入要评论的内容!");
                                    ((AlertDialog) dialogInterface).d();
                                    return;
                                } else if (obj.length() <= 3) {
                                    this.d.a("内容过短，请再说点什么吧");
                                    ((AlertDialog) dialogInterface).d();
                                    return;
                                } else if (obj.length() > http.Internal_Server_Error) {
                                    this.d.a("请输入500字以内的内容!");
                                    ((AlertDialog) dialogInterface).d();
                                    return;
                                } else {
                                    this.d.addRewardComment(optString7, optString6, obj);
                                    dialogInterface.dismiss();
                                    return;
                                }
                            }
                            this.d.a("请输入要评论的内容!");
                            ((AlertDialog) dialogInterface).d();
                        }
                    }).b(optString3, new OnClickListener(this) {
                        final /* synthetic */ JSDialog a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).a();
                    a.setCanceledOnTouchOutside(false);
                    a.show();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(final String str) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ JSDialog b;

            public void run() {
                af.a(this.b.a, str, 0).a();
            }
        });
    }

    public void addRewardComment(String str, String str2, String str3) {
        g.a().a(new RewardCommentTask(str, str2, str3, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ JSDialog a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                c.a("dialog", "recieve " + str);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        String optString = jSONObject.optString("message");
                        if (optInt == 0) {
                            this.a.a("评论成功！");
                        } else {
                            this.a.a(optString);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                c.a("dialog", "recieve error " + exception);
            }
        }));
    }

    public void showLuckyMoneyDialog(String str) {
        try {
            new o(this.a, new JSONObject(str)).f_();
        } catch (Exception e) {
            c.e("JSDialog_luckymoney", e.getMessage());
        }
    }

    public void setDate(final String str) {
        final StringBuilder stringBuilder = new StringBuilder();
        final StringBuilder stringBuilder2 = new StringBuilder();
        OnDateSetListener anonymousClass9 = new OnDateSetListener(this) {
            final /* synthetic */ JSDialog d;

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                if (stringBuilder.length() <= 0 && i >= 0 && i2 >= 0 && i3 >= 0) {
                    stringBuilder.append(i);
                    stringBuilder.append("-");
                    stringBuilder.append(i2 + 1);
                    stringBuilder.append("-");
                    stringBuilder.append(i3);
                    final Calendar instance = Calendar.getInstance();
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this.d.a, new OnTimeSetListener(this) {
                        final /* synthetic */ AnonymousClass9 a;

                        {
                            this.a = r1;
                        }

                        public void onTimeSet(TimePicker timePicker, int i, int i2) {
                            if (stringBuilder2.length() <= 0) {
                                stringBuilder2.append(" ");
                                stringBuilder2.append(i);
                                stringBuilder2.append(":");
                                stringBuilder2.append(i2);
                                stringBuilder.append(stringBuilder2);
                                ((WebBrowserForContents) this.a.d.a).a(0, str, stringBuilder.toString());
                            }
                        }
                    }, instance.get(11), instance.get(12), true);
                    timePickerDialog.setOnCancelListener(new OnCancelListener(this) {
                        final /* synthetic */ AnonymousClass9 b;

                        public void onCancel(DialogInterface dialogInterface) {
                            if (stringBuilder2.length() <= 0) {
                                stringBuilder2.append(" ");
                                stringBuilder2.append(String.valueOf(instance.get(11)));
                                stringBuilder2.append(":");
                                stringBuilder2.append(String.valueOf(instance.get(12)));
                                stringBuilder.append(stringBuilder2);
                                ((WebBrowserForContents) this.b.d.a).a(0, str, stringBuilder.toString());
                            }
                        }
                    });
                    timePickerDialog.show();
                }
            }
        };
        Calendar instance = Calendar.getInstance();
        new DatePickerDialog(this.a, anonymousClass9, instance.get(1), instance.get(2), instance.get(5)).show();
    }

    public void showWebIntroductionDialog(String str) {
        try {
            f fVar = new f(this.a);
            fVar.a(str);
            fVar.f_();
        } catch (Exception e) {
            c.e("JSDialog_showWebIntroductionDialog", e.getMessage());
        }
    }
}
