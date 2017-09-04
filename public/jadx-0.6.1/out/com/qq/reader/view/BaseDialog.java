package com.qq.reader.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.qq.reader.common.d.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.t;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public class BaseDialog {
    private Activity a;
    private t b = null;
    private boolean c = true;
    private int d = R.style.popBottomDialog;
    protected ReaderDialog f;
    public boolean g = true;
    Object h = null;

    public class ReaderDialog extends Dialog {
        final /* synthetic */ BaseDialog a;
        private h b = null;
        private boolean c = false;
        private OnShowListener d;
        private OnCancelListener e;
        private OnDismissListener f;

        public void setOnShowListener(OnShowListener onShowListener) {
            this.d = onShowListener;
        }

        public void setOnCancelListener(OnCancelListener onCancelListener) {
            this.e = onCancelListener;
        }

        public void setOnDismissListener(OnDismissListener onDismissListener) {
            this.f = onDismissListener;
        }

        public void show() {
            if (this.d != null) {
                super.setOnShowListener(this.d);
            }
            if (this.e != null) {
                super.setOnCancelListener(this.e);
            }
            if (this.f != null) {
                super.setOnDismissListener(this.f);
            }
            try {
                super.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void dismiss() {
            try {
                super.dismiss();
                this.a.d();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                a.a((Dialog) this);
            }
        }

        public ReaderDialog(BaseDialog baseDialog, Context context, int i) {
            this.a = baseDialog;
            super(context, i);
        }

        public void a(h hVar) {
            this.b = hVar;
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (this.b != null && this.b.a(i, keyEvent)) {
                return true;
            }
            switch (i) {
                case Opcodes.APUT_BYTE /*79*/:
                    if (this.c && this.a.g && isShowing()) {
                        cancel();
                        return true;
                    }
                case Opcodes.IGET /*82*/:
                    if (this.a.g && isShowing()) {
                        cancel();
                        return true;
                    }
            }
            return super.onKeyDown(i, keyEvent);
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                if (this.b != null) {
                    this.b.a(motionEvent);
                }
                return super.dispatchTouchEvent(motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public void a(boolean z) {
            this.c = true;
        }
    }

    public BaseDialog(int i) {
        this.d = i;
    }

    public void a(Activity activity, View view, int i, boolean z, boolean z2, boolean z3) {
        this.f = new ReaderDialog(this, activity, R.style.popBottomDialog);
        this.a = activity;
        this.b = new t(this.f, true);
        if (view == null) {
            this.f.setContentView(i);
        } else {
            this.f.setContentView(view);
        }
        this.f.setCanceledOnTouchOutside(z);
        this.f.setOnDismissListener(new r(this) {
            final /* synthetic */ BaseDialog a;

            {
                this.a = r1;
            }

            public t a() {
                return this.a.c();
            }
        });
        LayoutParams attributes = this.f.getWindow().getAttributes();
        if (z3) {
            attributes.width = activity.getWindow().getAttributes().width;
        }
        attributes.flags &= -3;
        if (z2) {
            attributes.flags |= 32;
        }
        attributes.gravity = 80;
        if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
            this.f.getWindow().setWindowAnimations(R.style.Animation.menuAnim);
        }
        this.f.getWindow().setAttributes(attributes);
    }

    public Activity b() {
        return this.a;
    }

    public void a(Activity activity, View view, int i, int i2, boolean z) {
        this.f = new ReaderDialog(this, activity, this.d);
        if (view == null) {
            this.f.setContentView(i);
        } else {
            this.f.setContentView(view);
        }
        this.f.setCanceledOnTouchOutside(true);
        this.a = activity;
        this.b = new t(this.f, true);
        this.f.setOnDismissListener(new r(this) {
            final /* synthetic */ BaseDialog a;

            {
                this.a = r1;
            }

            public t a() {
                return this.a.c();
            }
        });
        LayoutParams attributes = this.f.getWindow().getAttributes();
        if (z) {
            attributes.width = activity.getWindow().getAttributes().width;
            attributes.flags |= 1024;
        }
        switch (i2) {
            case 1:
                attributes.flags &= 2;
                attributes.gravity = 80;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.menuAnim);
                    break;
                }
            case 2:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 53;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.lampcordAnim);
                    break;
                }
            case 3:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 17;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.orientationLockAnim);
                    break;
                }
            case 4:
                this.g = false;
                break;
            case 5:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 51;
                break;
            case 6:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 49;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.topbarAnim);
                    break;
                }
            case 7:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 53;
                attributes.y = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48);
                attributes.x = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_8);
                attributes.height = -2;
                attributes.width = -2;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.scalepointAnim);
                    break;
                }
            case 8:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 49;
                attributes.y = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48);
                attributes.height = -2;
                attributes.width = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_196);
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.dropdownAnim);
                    break;
                }
            case 9:
                attributes.flags &= -3;
                attributes.gravity = 53;
                attributes.y = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48);
                attributes.height = -2;
                attributes.width = -2;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.scalepointAnim);
                    break;
                }
            case 10:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 49;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() <= 10) {
                    attributes.y = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48);
                }
                attributes.height = -2;
                attributes.width = activity.getWindow().getAttributes().width;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.dropdownAnim);
                    break;
                }
            case 11:
                attributes.flags = (attributes.flags & -3) | 8;
                attributes.gravity = 49;
                LayoutParams attributes2 = activity.getWindow().getAttributes();
                attributes.height = -2;
                attributes.width = attributes2.width;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.dropdownAnim);
                    break;
                }
            case 12:
                attributes.flags &= -3;
                attributes.gravity = 49;
                attributes.height = -2;
                attributes.width = activity.getWindow().getAttributes().width;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.dropdownAnim);
                    break;
                }
            case 13:
                attributes.gravity = 53;
                attributes.y = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48);
                attributes.x = e().getResources().getDimensionPixelOffset(R.dimen.common_dp_8);
                attributes.height = -2;
                attributes.width = -2;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.scalepointAnim);
                    break;
                }
            case 14:
                this.b = new t(this.f, false);
                attributes.flags &= 2;
                attributes.gravity = 80;
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.f.getWindow().setWindowAnimations(R.style.Animation.menuAnim);
                    break;
                }
        }
        this.f.getWindow().setAttributes(attributes);
    }

    public void a(Activity activity, View view, int i, int i2, boolean z, boolean z2, boolean z3) {
        a(activity, view, i, i2, z3);
        this.f.setCanceledOnTouchOutside(z);
        LayoutParams attributes = this.f.getWindow().getAttributes();
        attributes.flags &= 2;
        if (z2) {
            attributes.flags |= 32;
        }
        this.f.getWindow().setAttributes(attributes);
        this.b = new t(this.f, true);
        this.f.setOnDismissListener(new r(this) {
            final /* synthetic */ BaseDialog a;

            {
                this.a = r1;
            }

            public t a() {
                return this.a.c();
            }
        });
    }

    public View a(int i) {
        return this.f.findViewById(i);
    }

    public void a(OnShowListener onShowListener) {
        this.f.setOnShowListener(onShowListener);
    }

    public void a(OnCancelListener onCancelListener) {
        this.f.setOnCancelListener(onCancelListener);
    }

    public void a(r rVar) {
        this.f.setOnDismissListener(rVar);
    }

    public void b(boolean z) {
        this.f.setCanceledOnTouchOutside(z);
    }

    public void c(boolean z) {
        this.f.setCancelable(z);
    }

    public t c() {
        return this.b;
    }

    public void f_() {
        try {
            if (!this.a.isFinishing()) {
                this.f.show();
                if (this.c) {
                    this.b.b();
                }
            }
        } catch (Throwable th) {
            c.e("BaseDialog show", th.getMessage());
        }
    }

    public void dismiss() {
        try {
            if (this.f != null) {
                this.f.dismiss();
            }
        } catch (Throwable th) {
            c.e("BaseDialog Dissmiss", th.getMessage());
        }
    }

    public void cancel() {
        if (this.f != null && this.f.isShowing()) {
            try {
                this.f.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void d() {
    }

    public Context e() {
        return this.f.getContext();
    }

    public boolean f() {
        return this.f.isShowing();
    }

    public void d(boolean z) {
        this.c = z;
    }

    public void e(boolean z) {
        this.f.a(z);
    }
}
