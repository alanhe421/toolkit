package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONObject;

public class LimitTimeDiscountBuyCountDownCard extends a implements Callback {
    private int checkLimit;
    private int checkedCount;
    private long endTime;
    private LinearLayout ll_time;
    private WeakReferenceHandler mHandler;
    private boolean shouldStop = false;
    private long startTime;
    private TextView tv_checked_desc;
    private TextView tv_count_down_desc;
    private TextView tv_hour;
    private TextView tv_minute;
    private TextView tv_second;

    public LimitTimeDiscountBuyCountDownCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.limit_time_discount_buy_count_down_layout;
    }

    public void onCardShouldDestroy() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.shouldStop = false;
        this.startTime = jSONObject.optLong("startTime");
        this.endTime = jSONObject.optLong("endTime");
        this.checkedCount = jSONObject.optInt("totalCount");
        this.checkLimit = jSONObject.optInt("freeLimit");
        return true;
    }

    public boolean handleMessage(Message message) {
        long longValue = ((Long) message.obj).longValue();
        Activity fromActivity = getEvnetListener().getFromActivity();
        if (!(fromActivity == null || fromActivity.isFinishing())) {
            if (longValue > 1000) {
                longValue -= 1000;
                setCountDownTime(longValue);
                Message obtain = Message.obtain();
                obtain.obj = Long.valueOf(longValue);
                if (this.mHandler != null) {
                    this.mHandler.sendMessageDelayed(obtain, 1000);
                }
            } else {
                longValue = System.currentTimeMillis();
                Intent intent = new Intent("BROADCAST_ACTION_RELOAD_DATA");
                if (longValue > this.startTime) {
                    getBindPage().j().putString("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", String.valueOf(this.startTime));
                    intent.putExtra("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", String.valueOf(this.startTime));
                } else if (longValue > this.endTime) {
                    getBindPage().j().putString("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", String.valueOf(this.endTime));
                    intent.putExtra("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME", String.valueOf(this.endTime));
                }
                getEvnetListener().getFromActivity().sendBroadcast(intent);
            }
        }
        return true;
    }

    public void attachView() {
        this.tv_checked_desc = (TextView) ap.a(getRootView(), R.id.tv_checked_desc);
        this.tv_count_down_desc = (TextView) ap.a(getRootView(), R.id.tv_count_down_desc);
        this.tv_hour = (TextView) ap.a(getRootView(), R.id.tv_hour);
        this.tv_minute = (TextView) ap.a(getRootView(), R.id.tv_minute);
        this.tv_second = (TextView) ap.a(getRootView(), R.id.tv_second);
        this.ll_time = (LinearLayout) ap.a(getRootView(), R.id.ll_time);
        long currentTimeMillis = System.currentTimeMillis();
        getRootView().setVisibility(0);
        if (this.mHandler != null) {
            return;
        }
        Message obtain;
        if (currentTimeMillis < this.startTime) {
            currentTimeMillis = this.startTime - currentTimeMillis;
            setCountDownTime(currentTimeMillis);
            createHandler();
            if (this.mHandler != null) {
                obtain = Message.obtain();
                obtain.obj = Long.valueOf(currentTimeMillis);
                this.mHandler.sendMessageDelayed(obtain, 1000);
            }
        } else if (currentTimeMillis < this.endTime) {
            currentTimeMillis = this.endTime - currentTimeMillis;
            setCountDownTime(currentTimeMillis);
            createHandler();
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
                obtain = Message.obtain();
                obtain.obj = Long.valueOf(currentTimeMillis);
                this.mHandler.sendMessageDelayed(obtain, 1000);
            }
        } else {
            getRootView().setVisibility(8);
        }
    }

    private void createHandler() {
        this.mHandler = new WeakReferenceHandler(this);
    }

    private void setCountDownTime(long j) {
        if (this.tv_hour != null && this.tv_checked_desc != null && this.tv_count_down_desc != null && this.tv_minute != null && this.tv_second != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.startTime) {
                this.ll_time.setVisibility(0);
                setTime(this.startTime - currentTimeMillis);
                this.tv_count_down_desc.setText("开场倒计时");
                this.tv_checked_desc.setText("");
            } else if (currentTimeMillis < this.endTime) {
                this.ll_time.setVisibility(0);
                setTime(this.endTime - currentTimeMillis);
                this.tv_count_down_desc.setText("");
                if (this.checkedCount <= 0) {
                    this.tv_checked_desc.setText("每场每人限领" + this.checkLimit + "本");
                } else {
                    this.tv_checked_desc.setText("每场每人限领" + this.checkLimit + "本，" + "已有" + this.checkedCount + "人抢到");
                }
            } else {
                this.tv_count_down_desc.setText("");
                this.tv_checked_desc.setText("");
                this.ll_time.setVisibility(8);
            }
        }
    }

    private void setTime(long j) {
        if (j > 0) {
            int i = (int) ((j / BuglyBroadcastRecevier.UPLOADLIMITED) % 60);
            int i2 = (int) ((j / 3600000) % 100);
            setSingleNumber((int) ((j / 1000) % 60), this.tv_second);
            setSingleNumber(i, this.tv_minute);
            setSingleNumber(i2, this.tv_hour);
        }
    }

    private void setSingleNumber(int i, TextView textView) {
        if (i < 10) {
            textView.setText("0" + i);
        } else {
            textView.setText("" + i);
        }
    }
}
