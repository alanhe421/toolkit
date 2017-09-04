package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.UpdateUserInteractivePrivacyStateTask;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserCenterInterActionCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private int mBookCount = 0;
    private int mInterActionCount = 0;
    private ArrayList<a> mInterActionList;
    private int mIsOwn;
    private int mLastCheckedId;
    private int mPriStatus;
    private int mTotalCommentCount = 0;
    private String mUserIcon;
    private String mUserId;

    private class a {
        final /* synthetic */ UserCenterInterActionCard a;
        private long b;
        private String c;
        private String d;

        private a(UserCenterInterActionCard userCenterInterActionCard) {
            this.a = userCenterInterActionCard;
        }
    }

    public UserCenterInterActionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_interaction_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mUserIcon = jSONObject.optString("userIcon");
        this.mBookCount = jSONObject.optInt("shelfCount");
        this.mTotalCommentCount = jSONObject.optInt("totalCount");
        this.mInterActionCount = jSONObject.optInt("contentCount");
        this.mUserId = jSONObject.optString("userId");
        this.mIsOwn = jSONObject.optInt("isOwn");
        this.mPriStatus = jSONObject.optInt("priStatus");
        JSONArray optJSONArray = jSONObject.optJSONArray("contentList");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return false;
        }
        this.mInterActionList = new ArrayList();
        while (i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.b = optJSONObject.optLong("createTime");
                aVar.c = optJSONObject.optString("message");
                aVar.d = optJSONObject.optString("qurl");
                this.mInterActionList.add(aVar);
            }
            i++;
        }
        return true;
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.user_center_interaction_icon_1);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.user_center_interaction_icon_2);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.user_center_interaction_icon_3);
        TextView textView = (TextView) ap.a(getRootView(), R.id.user_center_interaction_message_1);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_message_2);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_message_3);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_time_1);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_time_2);
        TextView textView6 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_time_3);
        View a = ap.a(getRootView(), R.id.user_center_interaction_dot_1_rl);
        View a2 = ap.a(getRootView(), R.id.user_center_interaction_dot_2_rl);
        View a3 = ap.a(getRootView(), R.id.user_center_interaction_dot_3_rl);
        View a4 = ap.a(getRootView(), R.id.user_center_interaction_detail_1);
        View a5 = ap.a(getRootView(), R.id.user_center_interaction_detail_2);
        View a6 = ap.a(getRootView(), R.id.user_center_interaction_detail_3);
        ImageView imageView4 = (ImageView) ap.a(getRootView(), R.id.user_center_interaction_line_1);
        ImageView imageView5 = (ImageView) ap.a(getRootView(), R.id.user_center_interaction_line_2);
        c.a(getEvnetListener().getFromActivity()).a(this.mUserIcon, imageView, com.qq.reader.common.imageloader.a.a().b());
        c.a(getEvnetListener().getFromActivity()).a(this.mUserIcon, imageView2, com.qq.reader.common.imageloader.a.a().b());
        c.a(getEvnetListener().getFromActivity()).a(this.mUserIcon, imageView3, com.qq.reader.common.imageloader.a.a().b());
        int size = this.mInterActionList.size();
        if (size == 1) {
            a.setVisibility(8);
            a2.setVisibility(8);
            a3.setVisibility(0);
            a4.setVisibility(8);
            a5.setVisibility(8);
            a6.setVisibility(0);
            imageView4.setVisibility(8);
            imageView5.setVisibility(8);
            textView3.setText(((a) this.mInterActionList.get(0)).c);
            textView6.setText(k.c(((a) this.mInterActionList.get(0)).b));
        } else if (size == 2) {
            a.setVisibility(8);
            a2.setVisibility(0);
            a3.setVisibility(0);
            a4.setVisibility(8);
            a5.setVisibility(0);
            a6.setVisibility(0);
            imageView4.setVisibility(8);
            imageView5.setVisibility(0);
            textView2.setText(((a) this.mInterActionList.get(0)).c);
            textView5.setText(k.c(((a) this.mInterActionList.get(0)).b));
            textView3.setText(((a) this.mInterActionList.get(1)).c);
            textView6.setText(k.c(((a) this.mInterActionList.get(1)).b));
        } else if (size == 3) {
            a.setVisibility(0);
            a2.setVisibility(0);
            a3.setVisibility(0);
            a4.setVisibility(0);
            a5.setVisibility(0);
            a6.setVisibility(0);
            imageView4.setVisibility(0);
            imageView5.setVisibility(0);
            textView.setText(((a) this.mInterActionList.get(0)).c);
            textView4.setText(k.c(((a) this.mInterActionList.get(0)).b));
            textView2.setText(((a) this.mInterActionList.get(1)).c);
            textView5.setText(k.c(((a) this.mInterActionList.get(1)).b));
            textView3.setText(((a) this.mInterActionList.get(2)).c);
            textView6.setText(k.c(((a) this.mInterActionList.get(2)).b));
        }
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(37, "互动记录", this.mInterActionCount + "条", null);
        Button button = (Button) ap.a(getRootView(), R.id.btn_more);
        if (this.mInterActionCount > 3) {
            button.setVisibility(0);
            Map hashMap = new HashMap();
            hashMap.put("isOwn", String.valueOf(this.mIsOwn));
            i.a("event_C283", hashMap, ReaderApplication.getApplicationImp());
        } else {
            button.setVisibility(8);
        }
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", this.a.mIsOwn + "");
                i.a("event_D133", hashMap, ReaderApplication.getApplicationImp());
                Bundle bundle = new Bundle();
                bundle.putString("KEY_JUMP_PAGENAME", "user_center_more_interaction");
                bundle.putString("LOCAL_STORE_IN_TITLE", "互动记录");
                bundle.putString("userId", this.a.mUserId);
                new com.qq.reader.module.bookstore.qnative.c(bundle).a(this.a.getEvnetListener());
            }
        });
        a4.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mInterActionList != null && this.a.mInterActionList.size() > 0) {
                    a aVar = (a) this.a.mInterActionList.get(0);
                    if (aVar != null && !TextUtils.isEmpty(aVar.d)) {
                        try {
                            com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), aVar.d, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a.uploadClickInterAction();
                    }
                }
            }
        });
        a5.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a aVar = null;
                if (this.a.mInterActionList != null && this.a.mInterActionList.size() > 0) {
                    int size = this.a.mInterActionList.size();
                    if (size == 2) {
                        aVar = (a) this.a.mInterActionList.get(0);
                    } else if (size == 3) {
                        aVar = (a) this.a.mInterActionList.get(1);
                    }
                    if (aVar != null && !TextUtils.isEmpty(aVar.d)) {
                        try {
                            com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), aVar.d, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a.uploadClickInterAction();
                    }
                }
            }
        });
        a6.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a aVar = null;
                if (this.a.mInterActionList != null && this.a.mInterActionList.size() > 0) {
                    int size = this.a.mInterActionList.size();
                    if (size == 1) {
                        aVar = (a) this.a.mInterActionList.get(0);
                    } else if (size == 2) {
                        aVar = (a) this.a.mInterActionList.get(1);
                    } else if (size == 3) {
                        aVar = (a) this.a.mInterActionList.get(2);
                    }
                    if (aVar != null && !TextUtils.isEmpty(aVar.d)) {
                        try {
                            com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), aVar.d, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a.uploadClickInterAction();
                    }
                }
            }
        });
        Map hashMap2 = new HashMap();
        hashMap2.put("isOwn", String.valueOf(this.mIsOwn));
        i.a("event_C284", hashMap2, ReaderApplication.getApplicationImp());
        final RadioGroup radioGroup = (RadioGroup) ap.a(getRootView(), R.id.rg_show_interactive);
        RadioButton radioButton = (RadioButton) ap.a(getRootView(), R.id.rb_show_interactive_public);
        RadioButton radioButton2 = (RadioButton) ap.a(getRootView(), R.id.rb_show_interactive_private);
        if (this.mIsOwn == 1) {
            radioGroup.setVisibility(0);
            i.a("event_C290", null, ReaderApplication.getApplicationImp());
            if (this.mPriStatus == 0) {
                radioButton.setChecked(true);
                radioButton2.setChecked(false);
            } else if (this.mPriStatus == 1) {
                radioButton.setChecked(false);
                radioButton2.setChecked(true);
            }
        } else {
            radioGroup.setVisibility(8);
        }
        this.mLastCheckedId = radioGroup.getCheckedRadioButtonId();
        radioButton2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard b;

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, "1");
                i.a("event_C291", hashMap, ReaderApplication.getApplicationImp());
                this.b.setPrivacyStatus(radioGroup, view.getId());
            }
        });
        radioButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterInterActionCard b;

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, "0");
                i.a("event_C291", hashMap, ReaderApplication.getApplicationImp());
                this.b.setPrivacyStatus(radioGroup, view.getId());
            }
        });
    }

    private void uploadClickInterAction() {
        Map hashMap = new HashMap();
        hashMap.put("isOwn", String.valueOf(this.mIsOwn));
        i.a("event_C285", hashMap, ReaderApplication.getApplicationImp());
    }

    private void solvePriStatusUpdate(final int i) {
        g.a().a(new UpdateUserInteractivePrivacyStateTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ UserCenterInterActionCard b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("code") == 0) {
                        this.b.handleThingsInMainThread(10002514, Integer.valueOf(jSONObject.optInt("priStatus")));
                        return;
                    }
                    this.b.handleThingsInMainThread(10002515, Integer.valueOf(i));
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Err", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.b.handleThingsInMainThread(10002516, Integer.valueOf(i));
            }
        }, i));
    }

    private String getPrivacyStr(int i) {
        if (i == 1) {
            return "私密";
        }
        return "公开";
    }

    private void failSetBackCheckOption(int i) {
        RadioGroup radioGroup = (RadioGroup) ap.a(getRootView(), R.id.rg_show_interactive);
        int i2 = i == 0 ? R.id.rb_show_interactive_private : R.id.rb_show_interactive_public;
        radioGroup.check(i2);
        this.mLastCheckedId = i2;
    }

    private void handleThingsInMainThread(int i, Object obj) {
        Handler anonymousClass9 = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ UserCenterInterActionCard a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10002514:
                        RadioGroup radioGroup = (RadioGroup) ap.a(this.a.getRootView(), R.id.rg_show_interactive);
                        if (radioGroup != null) {
                            this.a.mLastCheckedId = radioGroup.getCheckedRadioButtonId();
                        }
                        af.a(ReaderApplication.getApplicationImp(), String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.user_interactive_privacy_update_succ_tip), new Object[]{this.a.getPrivacyStr(((Integer) message.obj).intValue())}), 0).a();
                        return;
                    case 10002515:
                        this.a.failSetBackCheckOption(((Integer) message.obj).intValue());
                        af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.user_interactive_privacy_update_server_error), 0).a();
                        return;
                    case 10002516:
                        this.a.failSetBackCheckOption(((Integer) message.obj).intValue());
                        af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.net_not_available), 0).a();
                        return;
                    default:
                        return;
                }
            }
        };
        anonymousClass9.sendMessage(anonymousClass9.obtainMessage(i, obj));
    }

    private void setPrivacyStatus(final RadioGroup radioGroup, int i) {
        int i2 = 0;
        if (radioGroup.getCheckedRadioButtonId() != this.mLastCheckedId) {
            if (i != R.id.rb_show_interactive_public && i == R.id.rb_show_interactive_private) {
                i2 = 1;
            }
            if (i2 != 1 || d.bR(ReaderApplication.getApplicationImp())) {
                solvePriStatusUpdate(i2);
                return;
            }
            AlertDialog a = new com.qq.reader.view.AlertDialog.a(getEvnetListener().getFromActivity()).a((int) R.string.exit).b((int) R.string.user_interactive_privacy_tip).a((int) R.string.confirm, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ UserCenterInterActionCard a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.solvePriStatusUpdate(1);
                }
            }).b((int) R.string.cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ UserCenterInterActionCard b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    radioGroup.check(R.id.rb_show_interactive_public);
                }
            }).a();
            d.L(ReaderApplication.getApplicationImp(), true);
            if (a != null) {
                a.show();
            }
        }
    }
}
