package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.GuideActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.animation.f;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;

public class GuidFragment_5 extends GuidFragmentBase implements Callback, OnClickListener {
    private Animation mAnimation;
    private ImageView mBoyImage;
    private View mBoyLayout;
    private a mFinishAnimation;
    private ImageView mGirlImage;
    private View mGirlLayout;
    private WeakReferenceHandler mHandler = null;
    int mIndex = 0;
    private View mSexLayout;
    private ImageView mSlogn;
    private ImageView mSlogn1;
    private View root;

    private class a extends Animation {
        final /* synthetic */ GuidFragment_5 a;
        private float b;
        private float c;
        private float d;
        private float e;
        private float f;
        private float g;
        private float h = 0.0f;
        private float i = 0.0f;
        private View j;

        public a(GuidFragment_5 guidFragment_5, float f, float f2, View view) {
            this.a = guidFragment_5;
            this.b = f;
            this.c = 1.0f;
            this.d = 1.0f;
            this.e = (this.b - 1.0f) * this.c;
            this.f = (this.b - 1.0f) * this.d;
            this.g = f2;
            c.a("guide", " startScaleX  " + this.c + " startScaleY " + this.d);
            this.j = view;
            setFillAfter(true);
        }

        protected void applyTransformation(float f, Transformation transformation) {
            transformation.getMatrix().setScale(this.c + (this.e * f), this.d + (this.f * f), (float) (this.j.getWidth() / 2), (float) this.j.getTop());
            float f2 = this.g * f;
            this.h += f2 - this.i;
            c.a("guide", "applyTransFromaction " + f + "   leftoffset  " + this.h);
            if (Math.abs(this.h) >= 1.0f) {
                this.j.offsetLeftAndRight((int) this.h);
                this.h = 0.0f;
            }
            this.i = f2;
        }
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public boolean handleMessage(Message message) {
        switch (this.mIndex) {
            case 0:
                startAnimation(this.mBoyImage);
                break;
            case 1:
                startAnimation(this.mGirlImage);
                break;
        }
        this.mIndex++;
        this.mIndex %= 3;
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bitmap a;
        i.a("event_C98", null, ReaderApplication.getApplicationImp());
        this.mHandler = new WeakReferenceHandler(this);
        this.root = layoutInflater.inflate(R.layout.guide_5, null);
        addRadioButton((LinearLayout) this.root.findViewById(R.id.guide_group));
        try {
            a = ao.a(ReaderApplication.getApplicationImp(), getGuideImgResId(), getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        } catch (Throwable th) {
            c.e("guide", th.toString());
            a = null;
        }
        if (a != null) {
            this.root.setBackgroundDrawable(new BitmapDrawable(getResources(), a));
        }
        this.mBoyImage = (ImageView) this.root.findViewById(R.id.boy);
        this.mGirlImage = (ImageView) this.root.findViewById(R.id.girl);
        this.mBoyLayout = this.root.findViewById(R.id.boy_layout);
        this.mGirlLayout = this.root.findViewById(R.id.girl_layout);
        this.mSexLayout = this.root.findViewById(R.id.sex_layout);
        this.mSlogn = (ImageView) this.root.findViewById(R.id.guide_slogan);
        this.mSlogn1 = (ImageView) this.root.findViewById(R.id.guide_slogan1);
        this.mBoyImage.setImageDrawable(ao.a(getActivity(), (int) R.drawable.guide_gender_male_normal, (int) R.drawable.guide_gender_male_selected));
        this.mGirlImage.setImageDrawable(ao.a(getActivity(), (int) R.drawable.guide_gender_female_normal, (int) R.drawable.guide_gender_female_selected));
        this.mBoyImage.setOnClickListener(this);
        this.mGirlImage.setOnClickListener(this);
        return this.root;
    }

    private void startAnimation(ImageView imageView) {
        this.mAnimation = new f(0.0f, 360.0f, (float) (imageView.getWidth() / 2), (float) (imageView.getTop() + (imageView.getHeight() / 2)), 0.0f, false);
        this.mAnimation.setFillAfter(true);
        this.mAnimation.setDuration(2000);
        imageView.startAnimation(this.mAnimation);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), 3000);
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeMessages(0);
    }

    public void onResume() {
        super.onResume();
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), 3000);
    }

    public void onDataNotify() {
        if (this.mBoyImage != null) {
            this.mBoyImage.clearAnimation();
        }
        if (this.mGirlImage != null) {
            this.mGirlImage.clearAnimation();
        }
    }

    public void onClick(View view) {
        View view2;
        int i;
        cleanSelected();
        view.setSelected(true);
        view.clearAnimation();
        Animation animation;
        switch (view.getId()) {
            case R.id.boy:
                d.F(ReaderApplication.getApplicationImp(), 1);
                if (d.aV(ReaderApplication.getApplicationImp()) == 0) {
                    d.H(ReaderApplication.getApplicationImp(), 4);
                }
                animation = this.mGirlImage.getAnimation();
                if (animation != null) {
                    animation.reset();
                }
                this.mGirlImage.clearAnimation();
                this.mGirlLayout.setVisibility(4);
                view2 = this.mBoyLayout;
                j.a(86, 1);
                i.a("event_B87", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_B87", null);
                i = 1;
                break;
            case R.id.girl:
                d.F(ReaderApplication.getApplicationImp(), 2);
                if (d.aV(ReaderApplication.getApplicationImp()) == 0) {
                    d.H(ReaderApplication.getApplicationImp(), 5);
                }
                animation = this.mBoyImage.getAnimation();
                if (animation != null) {
                    animation.reset();
                }
                this.mBoyImage.clearAnimation();
                this.mBoyLayout.setVisibility(4);
                view2 = this.mGirlLayout;
                j.a(87, 1);
                i.a("event_B88", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_B88", null);
                i = 2;
                break;
            default:
                view2 = null;
                i = 1;
                break;
        }
        d.G(getActivity(), i);
        if (this.mFinishAnimation == null && view2 != null) {
            int width = this.root.getWidth();
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            int width2 = view2.getWidth();
            int i2 = (int) ((((float) width2) * 1.25f) - ((float) width2));
            this.mFinishAnimation = new a(this, 1.25f, (float) ((width / 2) - (iArr[0] + (width2 / 2))), view2);
            this.mFinishAnimation.setDuration(200);
            view2.startAnimation(this.mFinishAnimation);
            this.mSlogn.setVisibility(8);
            this.mSlogn1.setVisibility(0);
        }
        copyInternalBooks(i);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ GuidFragment_5 a;

            {
                this.a = r1;
            }

            public void run() {
                Activity activity = this.a.getActivity();
                if (activity != null && !activity.isFinishing()) {
                    ((GuideActivity) activity).a();
                }
            }
        }, 200);
    }

    private void cleanSelected() {
        this.mBoyImage.setSelected(false);
        this.mGirlImage.setSelected(false);
    }

    private void copyInternalBooks(final int i) {
        g.a().a(new ReaderShortTask() {
            public void run() {
                super.run();
                ao.a(i, (GuideActivity) GuidFragment_5.this.getActivity());
            }
        });
        com.qq.reader.common.c.a.O = false;
    }
}
