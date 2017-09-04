package com.qq.reader.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import com.tencent.tesla.soload.SoLoadCore;
import java.lang.ref.WeakReference;

public class AlertController {
    private boolean isCancelOnClickPositiveButton = true;
    private ListAdapter mAdapter;
    OnClickListener mButtonHandler = new OnClickListener(this) {
        final /* synthetic */ AlertController a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            Message message = null;
            if (view == this.a.mButtonPositive && this.a.mButtonPositiveMessage != null) {
                message = Message.obtain(this.a.mButtonPositiveMessage);
            } else if (view == this.a.mButtonNegative && this.a.mButtonNegativeMessage != null) {
                message = Message.obtain(this.a.mButtonNegativeMessage);
            } else if (view == this.a.mButtonNeutral && this.a.mButtonNeutralMessage != null) {
                message = Message.obtain(this.a.mButtonNeutralMessage);
            }
            if (message != null) {
                message.sendToTarget();
            }
            if (view != this.a.mButtonPositive || this.a.mButtonPositiveMessage == null || this.a.isCancelOnClickPositiveButton) {
                this.a.mHandler.obtainMessage(1, this.a.mDialogInterface).sendToTarget();
            }
        }
    };
    private Button mButtonNegative;
    private int mButtonNegativeBackgroundResId = -1;
    private Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    private Button mButtonNeutral;
    private Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private Button mButtonPositive;
    private int mButtonPositiveBackgroundResId = -1;
    private Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private int mCheckedItem = -1;
    private final Context mContext;
    private View mCustomTitleView;
    private final DialogInterface mDialogInterface;
    private boolean mForceInverseBackground;
    private Handler mHandler;
    private Drawable mIcon;
    private int mIconId = -1;
    private ImageView mIconView;
    private ListView mListView;
    private CharSequence mMessage;
    private int mMessageTextViewGravity = -1;
    private TextView mMessageView;
    private DialogInterface.OnClickListener mNegativeListener;
    private DialogInterface.OnClickListener mPositiveListener;
    private ScrollView mScrollView;
    private int mSpecificViewHeight = -1;
    private CharSequence mTitle;
    private LayoutParams mTitleLayoutParam;
    private TextView mTitleView;
    private View mView;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    public final Window mWindow;

    public static class RecycleListView extends ListView {
        boolean a = true;

        public RecycleListView(Context context) {
            super(context);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public RecycleListView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    public static class a {
        public final Context a;
        public final LayoutInflater b;
        public int c = -1;
        public Drawable d;
        public CharSequence e;
        public CharSequence f;
        public CharSequence g;
        public DialogInterface.OnClickListener h;
        public CharSequence i;
        public DialogInterface.OnClickListener j;
        public CharSequence k;
        public DialogInterface.OnClickListener l;
        public boolean m;
        public OnCancelListener n;
        public OnKeyListener o;
        public CharSequence[] p;
        public ListAdapter q;
        public View r;
        public boolean s = false;
        public int t = -1;
        public Cursor u;
        public boolean v;
        public boolean w = true;

        public a(Context context) {
            this.a = context;
            this.m = true;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(AlertController alertController) {
            if (this.e != null) {
                alertController.a(this.e);
            }
            if (this.f != null) {
                alertController.b(this.f);
            }
            if (this.d != null) {
                alertController.a(this.d);
            }
            if (this.c >= 0) {
                alertController.b(this.c);
            }
            if (this.g != null) {
                alertController.a(-1, this.g, this.h, null);
            }
            if (this.i != null) {
                alertController.a(-2, this.i, this.j, null);
            }
            if (this.k != null) {
                alertController.a(-3, this.k, this.l, null);
            }
            if (this.v) {
                alertController.b(true);
            }
            if (!(this.p == null && this.u == null && this.q == null)) {
                b(alertController);
            }
            if (this.r != null) {
                alertController.b(this.r);
            }
        }

        private void b(AlertController alertController) {
            alertController.mListView = (RecycleListView) this.b.inflate(R.layout.lightdialog, null);
        }
    }

    private static final class b extends Handler {
        private WeakReference<DialogInterface> a;

        public b(DialogInterface dialogInterface) {
            this.a = new WeakReference(dialogInterface);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case -3:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.a.get(), message.what);
                        return;
                    case 1:
                        ((DialogInterface) message.obj).dismiss();
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.isCancelOnClickPositiveButton = z;
    }

    public void a() {
        this.mHandler.obtainMessage(1, this.mDialogInterface).sendToTarget();
    }

    public AlertController(Context context, DialogInterface dialogInterface, Window window) {
        this.mContext = context;
        this.mDialogInterface = dialogInterface;
        this.mWindow = window;
        this.mHandler = new b(dialogInterface);
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public View a(int i) {
        if (this.mView != null) {
            return this.mView.findViewById(i);
        }
        return null;
    }

    public void b() {
        this.mWindow.requestFeature(1);
        this.mWindow.setContentView(R.layout.message_dialog);
        if (this.mView == null || !a(this.mView)) {
            this.mWindow.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST, SoLoadCore.IF_SO_CONFIG_EXIST);
        }
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        if ("android".equals("meizu")) {
            attributes.gravity = 17;
            attributes.width = this.mWindow.getWindowManager().getDefaultDisplay().getWidth() - ao.a(40.0f);
        }
        if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
            this.mWindow.setWindowAnimations(R.style.Animation.menuAnim);
        }
        this.mWindow.setAttributes(attributes);
        d();
    }

    public void a(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }

    public void b(CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }

    public void b(View view) {
        this.mView = view;
        this.mViewSpacingSpecified = false;
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.mHandler.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case -3:
                this.mButtonNeutralText = charSequence;
                this.mButtonNeutralMessage = message;
                return;
            case -2:
                this.mButtonNegativeText = charSequence;
                if (this.mButtonNegativeMessage != null) {
                    this.mButtonNegativeMessage.obj = null;
                    this.mButtonNegativeMessage = null;
                }
                this.mButtonNegativeMessage = message;
                this.mNegativeListener = onClickListener;
                return;
            case -1:
                this.mButtonPositiveText = charSequence;
                if (this.mButtonPositiveMessage != null) {
                    this.mButtonPositiveMessage.obj = null;
                    this.mButtonPositiveMessage = null;
                }
                this.mButtonPositiveMessage = message;
                this.mPositiveListener = onClickListener;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void c() {
        if (this.mPositiveListener != null) {
            a(-1, this.mButtonPositiveText, this.mPositiveListener, null);
        }
        if (this.mNegativeListener != null) {
            a(-2, this.mButtonNegativeText, this.mNegativeListener, null);
        }
    }

    public void b(int i) {
        this.mIconId = i;
        if (this.mIconView == null) {
            return;
        }
        if (i > 0) {
            this.mIconView.setImageResource(this.mIconId);
        } else if (i == 0) {
            this.mIconView.setVisibility(8);
        }
    }

    public void a(Drawable drawable) {
        this.mIcon = drawable;
        if (this.mIconView != null && this.mIcon != null) {
            this.mIconView.setImageDrawable(drawable);
        }
    }

    public void c(int i) {
        this.mMessageTextViewGravity = i;
    }

    public void d(int i) {
        this.mSpecificViewHeight = i;
    }

    public void b(boolean z) {
        this.mForceInverseBackground = z;
    }

    public Button e(int i) {
        switch (i) {
            case -3:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case -1:
                return this.mButtonPositive;
            default:
                return null;
        }
    }

    public void a(int i, int i2) {
        switch (i) {
            case -2:
                this.mButtonNegativeBackgroundResId = i2;
                return;
            case -1:
                this.mButtonPositiveBackgroundResId = i2;
                return;
            default:
                return;
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.mScrollView == null || !this.mScrollView.executeKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    public boolean b(int i, KeyEvent keyEvent) {
        if (this.mScrollView == null || !this.mScrollView.executeKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    private void d() {
        this.mTitleView = (TextView) this.mWindow.findViewById(R.id.message_dialog_title);
        if (this.mTitleView != null) {
            this.mTitleView.setText(this.mTitle);
            if (this.mTitleLayoutParam != null) {
                this.mTitleView.setLayoutParams(this.mTitleLayoutParam);
            }
        }
        this.mMessageView = (TextView) this.mWindow.findViewById(R.id.message_dialog_message);
        if (this.mMessage != null) {
            if (this.mMessageTextViewGravity > 0) {
                this.mMessageView.setGravity(this.mMessageTextViewGravity);
            }
            this.mMessageView.setText(this.mMessage);
        }
        if (this.mView != null) {
            if (this.mSpecificViewHeight > 0) {
                ViewGroup viewGroup = (ViewGroup) this.mWindow.findViewById(R.id.body);
                if (viewGroup != null) {
                    viewGroup.setMinimumHeight(0);
                    viewGroup.addView(this.mView, new ViewGroup.LayoutParams(-1, this.mSpecificViewHeight));
                }
            } else {
                ((ViewGroup) this.mWindow.findViewById(R.id.body)).addView(this.mView, new ViewGroup.LayoutParams(-1, -1));
            }
        }
        e();
    }

    private boolean e() {
        Button button;
        int i;
        this.mButtonPositive = (Button) this.mWindow.findViewById(R.id.sureButton);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        if (this.mButtonPositiveBackgroundResId != -1) {
            this.mButtonPositive.setBackgroundResource(this.mButtonPositiveBackgroundResId);
        }
        if (TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
            button = null;
            i = 0;
        } else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            i = 1;
            button = this.mButtonPositive;
        }
        this.mButtonNegative = (Button) this.mWindow.findViewById(R.id.cancelButton);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (this.mButtonNegativeBackgroundResId != -1) {
            this.mButtonNegative.setBackgroundResource(this.mButtonNegativeBackgroundResId);
        }
        if (TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            if (button == null) {
                Button button2 = this.mButtonNegative;
            }
            i |= 2;
        }
        if (i == 1) {
            a(this.mButtonPositive);
        } else if (i == 2) {
            a(this.mButtonNegative);
        } else if (i == 4) {
            a(this.mButtonNeutral);
        } else if (i == 3) {
        }
        if (i != 0) {
            return true;
        }
        return false;
    }

    private void a(Button button) {
    }
}
