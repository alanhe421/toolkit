package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.GuideActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.tencent.feedback.proguard.R;

public class UpgradeGuidFragment extends GuidFragmentBase implements OnClickListener {
    private String[] mBtnTextArray = new String[]{"下次再说", "听我想听"};
    private View mRootView;

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.upgrade_guide_layout, null);
        Button button = (Button) this.mRootView.findViewById(R.id.guide_btn_left);
        button.setOnClickListener(this);
        button.setText(this.mBtnTextArray[0]);
        button = (Button) this.mRootView.findViewById(R.id.guide_btn_right);
        button.setOnClickListener(this);
        button.setText(this.mBtnTextArray[1]);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.guide_img);
        Bitmap e = ao.e(ReaderApplication.getApplicationImp(), getGuideImgResId());
        if (e != null) {
            imageView.setImageBitmap(e);
        }
        imageView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ UpgradeGuidFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                switch (motionEvent.getAction() & 255) {
                    case 1:
                    case 3:
                        if (x <= (view.getMeasuredWidth() * 2) / 3) {
                            if (x < view.getMeasuredWidth() / 3) {
                                ((GuideActivity) this.a.getActivity()).a(this.a.getPosition() - 1, true);
                                break;
                            }
                        }
                        ((GuideActivity) this.a.getActivity()).a(this.a.getPosition() + 1, true);
                        break;
                        break;
                }
                return true;
            }
        });
        return this.mRootView;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_btn_left:
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ UpgradeGuidFragment a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Activity activity = this.a.getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            ((GuideActivity) activity).a();
                        }
                    }
                });
                i.a("event_F328", null, ReaderApplication.getApplicationImp().getApplicationContext());
                return;
            case R.id.guide_btn_right:
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ UpgradeGuidFragment a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Activity activity = this.a.getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            o.a(this.a.getActivity(), true, null);
                            ((GuideActivity) activity).finish();
                        }
                    }
                });
                i.a("event_F329", null, ReaderApplication.getApplicationImp().getApplicationContext());
                return;
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        i.a("event_F327", null, ReaderApplication.getApplicationImp().getApplicationContext());
    }
}
