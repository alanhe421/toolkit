package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.widget.RatingBar;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.UploadBookUserScoreTask;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.widget.ReaderRatingBar;
import com.qq.reader.common.widget.ReaderRatingBar.a;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONObject;

public class BookScoreCard extends BaseCommentCard implements Callback {
    public static final int MSG_UPDATERATINGSCORE = 2;
    public static final int MSG_UPLOADUSERSCORE = 0;
    public static final int MSG_UPLOADUSERSCORE_RESPON = 1;
    private long mBookid;
    private WeakReferenceHandler mHandler = new WeakReferenceHandler(Looper.getMainLooper(), this);
    private float mRatingScore;

    public BookScoreCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_bookscore;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mRatingScore = (float) jSONObject.optDouble("score", 0.0d);
        this.mBookid = (long) jSONObject.optDouble("bid", 0.0d);
        i.a("event_A177", null, ReaderApplication.getApplicationImp());
        return true;
    }

    public void attachView() {
        ReaderRatingBar readerRatingBar = (ReaderRatingBar) ap.a(getRootView(), R.id.bookscore_ratingbar);
        readerRatingBar.setRating(this.mRatingScore);
        readerRatingBar.setRatingChangedDelaytime(1000);
        readerRatingBar.setOnRatingBarDelayChangedListener(new a(this) {
            final /* synthetic */ BookScoreCard a;

            {
                this.a = r1;
            }

            public boolean a(RatingBar ratingBar, float f) {
                this.a.mRatingScore = f;
                if (c.b()) {
                    return false;
                }
                ((ReaderBaseActivity) this.a.getEvnetListener().getFromActivity()).setLoginNextTask(new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        Message obtainMessage = this.a.a.mHandler.obtainMessage();
                        switch (i) {
                            case 1:
                                obtainMessage.what = 0;
                                obtainMessage.obj = Float.valueOf(this.a.a.mRatingScore);
                                break;
                            case 2:
                            case 3:
                                obtainMessage.what = 2;
                                obtainMessage.obj = Float.valueOf(0.0f);
                                break;
                        }
                        this.a.a.mHandler.sendMessage(obtainMessage);
                    }
                });
                ((ReaderBaseActivity) this.a.getEvnetListener().getFromActivity()).startLogin();
                return true;
            }

            public void b(RatingBar ratingBar, float f) {
                com.qq.reader.common.monitor.debug.c.e("onRatingBarDelay", f + "");
                this.a.mRatingScore = f;
                this.a.uploadChapterScore(this.a.mRatingScore);
            }
        });
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                uploadChapterScore(((Float) message.obj).floatValue());
                break;
            case 1:
                com.qq.reader.module.bookstore.qnative.c.a evnetListener = getEvnetListener();
                if (evnetListener != null) {
                    Activity fromActivity = evnetListener.getFromActivity();
                    if (!(fromActivity == null || fromActivity.isFinishing())) {
                        af.a(ReaderApplication.getApplicationImp(), message.obj.toString(), 0).a();
                        break;
                    }
                }
                break;
            case 2:
                if (getRootView() != null) {
                    ((ReaderRatingBar) ap.a(getRootView(), R.id.bookscore_ratingbar)).setRating(((Float) message.obj).floatValue());
                    break;
                }
                break;
        }
        return true;
    }

    private void uploadChapterScore(float f) {
        if (f >= 1.0f) {
            i.a("event_A178", null, ReaderApplication.getApplicationImp());
            g.a().a(new UploadBookUserScoreTask(this.mBookid, f, new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ BookScoreCard a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        Message obtainMessage = this.a.mHandler.obtainMessage();
                        obtainMessage.what = 1;
                        obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        this.a.mHandler.sendMessage(obtainMessage);
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("BookScoreCard", e.getMessage());
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    Message obtainMessage = this.a.mHandler.obtainMessage();
                    obtainMessage.what = 1;
                    obtainMessage.obj = ReaderApplication.getApplicationImp().getString(R.string.login_net_exception);
                    this.a.mHandler.sendMessage(obtainMessage);
                }
            }));
        }
    }
}
