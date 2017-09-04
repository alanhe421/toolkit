package com.qq.reader.module.bookstore.qnative.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.widget.cloudtag.CloudSurfaceView;
import com.tencent.feedback.proguard.R;

public class NativeSearchToolMainActivity extends NativeBookStoreTwoLevelActivity {
    public static CloudSurfaceView k = null;
    private Button w;
    private CloudSurfaceView x;
    private XListView y;
    private View z;

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                this.mHandler.postDelayed(new Runnable(this) {
                    final /* synthetic */ NativeSearchToolMainActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.y();
                    }
                }, 1000);
                break;
        }
        return super.handleMessageImp(message);
    }

    private void y() {
        try {
            NativePageFragmentforOther nativePageFragmentforOther = (NativePageFragmentforOther) d();
            if (nativePageFragmentforOther != null) {
                this.y = nativePageFragmentforOther.getXListView();
                if (this.y != null) {
                    this.y.setOnScrollListener(new OnScrollListener(this) {
                        final /* synthetic */ NativeSearchToolMainActivity a;

                        {
                            this.a = r1;
                        }

                        public void onScrollStateChanged(AbsListView absListView, int i) {
                        }

                        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                            if (this.a.y != null && this.a.y.getChildCount() > 0) {
                                View childAt = this.a.y.getChildAt(0);
                                if (childAt == null) {
                                    return;
                                }
                                if (childAt.getTop() < -32) {
                                    if (this.a.h.getVisibility() == 0) {
                                        this.a.h.setVisibility(4);
                                    }
                                } else if (this.a.h.getVisibility() == 4) {
                                    this.a.h.startAnimation(this.a.x());
                                }
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onPause() {
        super.onPause();
        if (k != null) {
            k.onPause();
        }
        if (this.x != null) {
            this.x.onPause();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        k = null;
        this.x = null;
    }

    protected void onResume() {
        super.onResume();
        if (k != null) {
            k.onResume();
        }
        if (this.x != null) {
            this.x.onResume();
        }
    }

    public int f() {
        return R.layout.localstore_search_tool_main;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.z = findViewById(R.id.common_titler);
        this.z.setBackgroundDrawable(null);
        this.w = (Button) findViewById(R.id.btn_search_option);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSearchToolMainActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.s(this.a, null);
                i.a("event_C120", null, this.a);
            }
        });
        this.x = (CloudSurfaceView) findViewById(R.id.surfaceview_test);
    }

    public Animation x() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 255.0f);
        alphaAnimation.setDuration(100);
        alphaAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ NativeSearchToolMainActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.h.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        return alphaAnimation;
    }
}
