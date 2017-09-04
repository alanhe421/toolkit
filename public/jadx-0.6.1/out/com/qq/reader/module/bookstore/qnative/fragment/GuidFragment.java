package com.qq.reader.module.bookstore.qnative.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.GuideActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class GuidFragment extends GuidFragmentBase {
    private View root;

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.root = layoutInflater.inflate(R.layout.guide_img, null);
        addRadioButton((LinearLayout) this.root.findViewById(R.id.guide_group));
        ImageView imageView = (ImageView) this.root.findViewById(R.id.guide_img);
        Bitmap e = ao.e(ReaderApplication.getApplicationImp(), getGuideImgResId());
        if (e != null) {
            imageView.setImageBitmap(e);
        }
        imageView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ GuidFragment a;

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
        return this.root;
    }

    public void onResume() {
        super.onResume();
        i.a("event_F327", null, ReaderApplication.getApplicationImp().getApplicationContext());
    }
}
