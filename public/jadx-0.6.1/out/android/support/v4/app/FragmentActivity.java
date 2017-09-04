package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.util.h;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.tencent.av.mediacodec.HWColorFormat;
import com.tencent.smtt.sdk.WebView;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class FragmentActivity extends g implements android.support.v4.app.a.a, android.support.v4.app.c.a {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final i mFragments = i.a(new a(this));
    final Handler mHandler = new Handler(this) {
        final /* synthetic */ FragmentActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.a.mStopped) {
                        this.a.doReallyStop(false);
                        return;
                    }
                    return;
                case 2:
                    this.a.onResumeFragments();
                    this.a.mFragments.p();
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };
    android.support.v4.media.session.a mMediaController;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;

    class a extends j<FragmentActivity> {
        final /* synthetic */ FragmentActivity a;

        public /* synthetic */ Object g() {
            return c();
        }

        public a(FragmentActivity fragmentActivity) {
            this.a = fragmentActivity;
            super(fragmentActivity);
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            this.a.dump(str, fileDescriptor, printWriter, strArr);
        }

        public boolean a(Fragment fragment) {
            return !this.a.isFinishing();
        }

        public LayoutInflater b() {
            return this.a.getLayoutInflater().cloneInContext(this.a);
        }

        public FragmentActivity c() {
            return this.a;
        }

        public void d() {
            this.a.supportInvalidateOptionsMenu();
        }

        public void a(Fragment fragment, Intent intent, int i) {
            this.a.startActivityFromFragment(fragment, intent, i);
        }

        public void a(Fragment fragment, String[] strArr, int i) {
            this.a.requestPermissionsFromFragment(fragment, strArr, i);
        }

        public boolean a(String str) {
            return a.a(this.a, str);
        }

        public boolean e() {
            return this.a.getWindow() != null;
        }

        public int f() {
            Window window = this.a.getWindow();
            return window == null ? 0 : window.getAttributes().windowAnimations;
        }

        public void b(Fragment fragment) {
            this.a.onAttachFragment(fragment);
        }

        public View a(int i) {
            return this.a.findViewById(i);
        }

        public boolean a() {
            Window window = this.a.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    static final class b {
        Object a;
        List<Fragment> b;
        h<String, o> c;

        b() {
        }
    }

    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.d();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            int c = this.mFragments.c();
            if (c == 0 || i3 < 0 || i3 >= c) {
                Log.w(TAG, "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.mFragments.a(new ArrayList(c)).get(i3);
            if (fragment == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.onActivityResult(65535 & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.mFragments.a().d()) {
            supportFinishAfterTransition();
        }
    }

    public final void setSupportMediaController(android.support.v4.media.session.a aVar) {
        this.mMediaController = aVar;
        if (VERSION.SDK_INT >= 21) {
            b.a((Activity) this, aVar.a());
        }
    }

    public final android.support.v4.media.session.a getSupportMediaController() {
        return this.mMediaController;
    }

    public void supportFinishAfterTransition() {
        a.a((Activity) this);
    }

    public void setEnterSharedElementCallback(ag agVar) {
        a.a((Activity) this, agVar);
    }

    public void setExitSharedElementCallback(ag agVar) {
        a.b(this, agVar);
    }

    public void supportPostponeEnterTransition() {
        a.b(this);
    }

    public void supportStartPostponedEnterTransition() {
        a.c(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.a(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.mFragments.a(null);
        super.onCreate(bundle);
        b bVar = (b) getLastNonConfigurationInstance();
        if (bVar != null) {
            this.mFragments.a(bVar.c);
        }
        if (bundle != null) {
            List list;
            Parcelable parcelable = bundle.getParcelable(FRAGMENTS_TAG);
            i iVar = this.mFragments;
            if (bVar != null) {
                list = bVar.b;
            } else {
                list = null;
            }
            iVar.a(parcelable, list);
        }
        this.mFragments.g();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu) | this.mFragments.a(menu, getMenuInflater());
        if (VERSION.SDK_INT >= 11) {
            return onCreatePanelMenu;
        }
        return true;
    }

    final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.a(view, str, context, attributeSet);
    }

    protected void onDestroy() {
        super.onDestroy();
        doReallyStop(false);
        this.mFragments.n();
        this.mFragments.r();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.o();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case 0:
                return this.mFragments.a(menuItem);
            case 6:
                return this.mFragments.b(menuItem);
            default:
                return false;
        }
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case 0:
                this.mFragments.b(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            onResumeFragments();
        }
        this.mFragments.k();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.d();
    }

    public void onStateNotSaved() {
        this.mFragments.d();
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.p();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        onResumeFragments();
        this.mFragments.p();
    }

    protected void onResumeFragments() {
        this.mFragments.j();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.mOptionsMenuInvalidated) {
            this.mOptionsMenuInvalidated = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return onPrepareOptionsPanel(view, menu) | this.mFragments.a(menu);
    }

    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            doReallyStop(true);
        }
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        List f = this.mFragments.f();
        h t = this.mFragments.t();
        if (f == null && t == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        Object bVar = new b();
        bVar.a = onRetainCustomNonConfigurationInstance;
        bVar.b = f;
        bVar.c = t;
        return bVar;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable e = this.mFragments.e();
        if (e != null) {
            bundle.putParcelable(FRAGMENTS_TAG, e);
        }
    }

    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.h();
        }
        this.mFragments.d();
        this.mFragments.p();
        this.mFragments.q();
        this.mFragments.i();
        this.mFragments.s();
    }

    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.l();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public Object getLastCustomNonConfigurationInstance() {
        b bVar = (b) getLastNonConfigurationInstance();
        return bVar != null ? bVar.a : null;
    }

    public void supportInvalidateOptionsMenu() {
        if (VERSION.SDK_INT >= 11) {
            d.a(this);
        } else {
            this.mOptionsMenuInvalidated = true;
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= 11) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.mReallyStopped);
            this.mFragments.a(str2, fileDescriptor, printWriter, strArr);
            this.mFragments.a().a(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.println("View Hierarchy:");
            dumpViewHierarchy(str + "  ", printWriter, getWindow().getDecorView());
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.mReallyStopped);
            this.mFragments.a(str2, fileDescriptor, printWriter, strArr);
            this.mFragments.a().a(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.println("View Hierarchy:");
            dumpViewHierarchy(str + "  ", printWriter, getWindow().getDecorView());
        }
    }

    private static String viewToString(View view) {
        char c;
        char c2 = 'F';
        char c3 = '.';
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case 0:
                stringBuilder.append('V');
                break;
            case 4:
                stringBuilder.append('I');
                break;
            case 8:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        if (view.isFocusable()) {
            c = 'F';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isEnabled()) {
            c = 'E';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        if (view.isHorizontalScrollBarEnabled()) {
            c = 'H';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isVerticalScrollBarEnabled()) {
            c = 'V';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isClickable()) {
            c = 'C';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isLongClickable()) {
            c = 'L';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c2 = '.';
        }
        stringBuilder.append(c2);
        if (view.isSelected()) {
            c = 'S';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isPressed()) {
            c3 = 'P';
        }
        stringBuilder.append(c3);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (WebView.NIGHT_MODE_COLOR & id) {
                    case SigType.WLOGIN_PF /*16777216*/:
                        str = "android";
                        break;
                    case HWColorFormat.COLOR_FormatVendorStartUnused /*2130706432*/:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void dumpViewHierarchy(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(viewToString(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i++) {
                    dumpViewHierarchy(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    void doReallyStop(boolean z) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = z;
            this.mHandler.removeMessages(1);
            onReallyStop();
        }
    }

    void onReallyStop() {
        this.mFragments.a(this.mRetaining);
        this.mFragments.m();
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public k getSupportFragmentManager() {
        return this.mFragments.a();
    }

    public o getSupportLoaderManager() {
        return this.mFragments.b();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (-65536 & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    public final void validateRequestPermissionsRequestCode(int i) {
        if (this.mRequestedPermissionsFromFragment) {
            this.mRequestedPermissionsFromFragment = false;
        } else if ((i & -256) != 0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        int i2 = (i >> 8) & 255;
        if (i2 != 0) {
            i2--;
            int c = this.mFragments.c();
            if (c == 0 || i2 < 0 || i2 >= c) {
                Log.w(TAG, "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.mFragments.a(new ArrayList(c)).get(i2);
            if (fragment == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
            } else {
                fragment.onRequestPermissionsResult(i & 255, strArr, iArr);
            }
        }
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        if (i == -1) {
            super.startActivityForResult(intent, -1);
        } else if ((-65536 & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else {
            super.startActivityForResult(intent, ((fragment.mIndex + 1) << 16) + (65535 & i));
        }
    }

    private void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i) {
        if (i == -1) {
            a.a(this, strArr, i);
        } else if ((i & -256) != 0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        } else {
            this.mRequestedPermissionsFromFragment = true;
            a.a(this, strArr, ((fragment.mIndex + 1) << 8) + (i & 255));
        }
    }
}
