package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.Fragment.SavedState;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* compiled from: FragmentManager */
public abstract class k {

    /* compiled from: FragmentManager */
    public interface a {
        void a();
    }

    public abstract SavedState a(Fragment fragment);

    public abstract Fragment a(int i);

    public abstract Fragment a(String str);

    public abstract m a();

    public abstract void a(int i, int i2);

    public abstract void a(Bundle bundle, String str, Fragment fragment);

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean b();

    public abstract void c();

    public abstract boolean d();

    public abstract List<Fragment> e();
}
