package com.qq.reader.module.game.view;

import android.app.Activity;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.t;
import com.qq.reader.module.game.data.b;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.RoundImageView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.List;

/* compiled from: GameCouponDialog */
public class a extends BaseDialog {
    private RoundImageView a;
    private ImageView b;
    private View c;
    private View d;
    private Activity e;
    private TextView i;
    private View j;
    private LinearLayout k;

    public a(Activity activity) {
        this.e = activity;
        if (this.f == null) {
            a(activity, null, R.layout.game_coupon_dialog, 0, false);
            this.f.setCancelable(true);
            this.f.findViewById(R.id.click_layout).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.dismiss();
                }
            });
            this.f.findViewById(R.id.content_layout).setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            this.a = (RoundImageView) this.f.findViewById(R.id.header_img);
            this.a.setRadius((float) ao.a(3.0f));
            this.a.setType(3);
            t.a(this.a);
            this.c = this.f.findViewById(R.id.more_btn);
            this.i = (TextView) this.f.findViewById(R.id.notice);
            this.d = this.f.findViewById(R.id.place_holder);
            this.k = (LinearLayout) this.f.findViewById(R.id.coupon_layout);
            this.j = this.f.findViewById(R.id.mask);
            this.b = (ImageView) this.f.findViewById(R.id.close_btn);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.dismiss();
                }
            });
            this.f.setCanceledOnTouchOutside(true);
        }
        this.f.getWindow().setAttributes(this.f.getWindow().getAttributes());
        this.f.getWindow().addFlags(2);
    }

    public void a(List<b> list, String str, final String str2, WeakReferenceHandler weakReferenceHandler) {
        c.a(e()).a(str, this.a, com.qq.reader.common.imageloader.a.a().c((int) this.e.getResources().getDimension(R.dimen.game_adv_dialog_img_width), R.drawable.game_coupon_dialog_header), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                return false;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                this.a.a.setVisibility(0);
                return false;
            }
        });
        this.i.setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.game_coupon_notice_text, new Object[]{Integer.valueOf(list.size())}));
        a((List) list);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.a(str2);
            }
        });
        Message obtainMessage = weakReferenceHandler.obtainMessage();
        obtainMessage.arg1 = 1;
        obtainMessage.what = 65555;
        weakReferenceHandler.sendMessage(obtainMessage);
    }

    private void a(List<b> list) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (list.size() == 1) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size != 0) {
                layoutParams.bottomMargin = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_14);
            }
            View gameCouponItemView = new GameCouponItemView(this.e);
            gameCouponItemView.a((b) list.get(size));
            gameCouponItemView.setLayoutParams(layoutParams);
            this.k.addView(gameCouponItemView);
        }
    }

    private void a(String str) {
        if (com.qq.reader.qurl.c.a(str)) {
            try {
                com.qq.reader.qurl.c.a(this.e, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.f.isShowing() && !this.e.isFinishing()) {
            this.f.dismiss();
        }
    }

    public void f_() {
        try {
            if (!com.qq.reader.cservice.adv.b.b && !com.qq.reader.module.rookie.presenter.a.a().e() && this.e != null && !this.e.isFinishing()) {
                t.a(this.a);
                if (d.n) {
                    this.j.setVisibility(0);
                } else {
                    this.j.setVisibility(8);
                }
                this.f.show();
                com.qq.reader.module.game.presenter.a.a().b();
                com.qq.reader.cservice.adv.b.b = true;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeGameDialog", e.getMessage());
        }
    }

    public void dismiss() {
        try {
            if (!this.e.isFinishing()) {
                super.dismiss();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("PopNativeDialog", e.getMessage());
        }
    }

    public void cancel() {
        if (!this.e.isFinishing()) {
            super.cancel();
        }
    }
}
