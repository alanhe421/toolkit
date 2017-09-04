package com.qq.reader.module.bookstore.qnative.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.CommitCommentActivity;
import com.qq.reader.activity.WebBookRewardActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;

public class NativeBookStoreEndPageActivity extends NativeBookStoreTwoLevelActivity {
    private Context k;
    private OnTouchListener w;

    protected void onCreate(Bundle bundle) {
        setRequestedOrientation(d.Y(getApplicationContext()));
        super.onCreate(bundle);
        this.k = getApplicationContext();
        this.w = new OnTouchListener(this) {
            final /* synthetic */ NativeBookStoreEndPageActivity a;
            private float b;
            private float c;
            private float d;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.b = 0.0f;
                        this.d = 0.0f;
                        this.c = motionEvent.getX();
                        break;
                    case 1:
                    case 3:
                        this.c = 0.0f;
                        this.b = 0.0f;
                        this.d = 0.0f;
                        break;
                    case 2:
                        float x = motionEvent.getX();
                        if (this.c == 0.0f) {
                            this.c = x;
                            break;
                        }
                        this.b = x - this.c;
                        if (this.b <= 0.0f) {
                            this.d = 0.0f;
                        } else {
                            this.d += this.b;
                        }
                        this.c = x;
                        if (this.d >= 50.0f) {
                            this.a.finish();
                            return true;
                        }
                        break;
                }
                return false;
            }
        };
        this.b.setOnTouchListener(this.w);
        this.u.setOnTouchListener(this.w);
        if ("bookclubchapter".equals(this.r.getString("KEY_JUMP_PAGENAME"))) {
            this.s.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
            this.s.setVisibility(0);
            this.s.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreEndPageActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.a(this.a, Long.valueOf(this.a.r.getLong("URL_BUILD_PERE_BOOK_ID", 0)), null, 0, new JumpActivityParameter().a(50000));
                }
            });
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.w.onTouch(null, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        String string = bundle.getString("KEY_ACTION");
        if ("detail_2_reward".equals(string)) {
            Intent intent = new Intent();
            long j = this.r.getLong("URL_BUILD_PERE_BOOK_ID");
            int i = bundle.getInt("PARA_TYPE_REWARD_TAB_INDEX");
            String string2 = bundle.getString("PARA_TYPE_REWARD_EXTRA_URL_PARAMS");
            String string3 = this.r.getString("PARA_TYPE_BOOK_TITLE");
            intent.setClass(this.k, WebBookRewardActivity.class);
            intent.putExtra("URL_BUILD_PERE_BOOK_ID", j);
            intent.putExtra("PARA_TYPE_REWARD_TAB_INDEX", i);
            intent.putExtra("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", string2);
            intent.putExtra("PARA_TYPE_BOOK_TITLE", string3);
            startActivity(intent);
        } else if ("show_share_dialog".equals(string)) {
            a(String.valueOf(this.r.getLong("URL_BUILD_PERE_BOOK_ID")), this.r.getString("PARA_TYPE_BOOK_TITLE"));
        } else if (!"detail_2_topic".equals(string)) {
        } else {
            if (c.b()) {
                x();
                return;
            }
            setLoginNextTask(new a(this) {
                final /* synthetic */ NativeBookStoreEndPageActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.x();
                                }
                            });
                            return;
                        default:
                            return;
                    }
                }
            });
            startLogin();
        }
    }

    public void a(String str, String str2) {
        getShareDialog().a(str, str2);
        getShareDialog().f_();
    }

    public void a(b bVar) {
        if (bVar != null && (bVar instanceof ao)) {
            com.qq.reader.module.bookstore.qnative.model.a.a y = ((ao) bVar).y();
            this.r.putString("COMMENT_ID", y.a());
            this.r.putString("PARA_TYPE_COMMENT_UID", y.b());
        }
    }

    private void x() {
        Intent intent = new Intent();
        intent.setClass(this, CommitCommentActivity.class);
        intent.putExtras(this.r);
        startActivityForResult(intent, 1002);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 50000) {
            q();
        }
    }
}
