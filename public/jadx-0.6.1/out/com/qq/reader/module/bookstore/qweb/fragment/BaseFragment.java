package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.k;
import android.support.v4.app.m;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.util.WeakReferenceHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public abstract class BaseFragment extends Fragment implements Callback {
    public static final int DELAY = 0;
    private static final String DISPLAY_STATUS = "display_status";
    private static final int LAOD = 1001;
    private HashMap<String, Object> args;
    private int delay = 0;
    private boolean hasDoCreateView = true;
    protected a mFragmentLoadListener;
    protected WeakReferenceHandler mHandler;
    private boolean mHasLoad = false;
    private Handler mLoadHandler = new Handler(this) {
        final /* synthetic */ BaseFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what != 1001) {
                return;
            }
            if (this.a.hasDoCreateView) {
                this.a.mNotExcuteWhenCreate = true;
                if (this.a.mLoadTask.getStatus() != Status.FINISHED) {
                    this.a.mLoadTask.execute(new Void[0]);
                    return;
                }
                return;
            }
            this.a.mNotExcuteWhenCreate = false;
        }
    };
    private b mLoadTask = new b();
    private boolean mNotExcuteWhenCreate = true;
    protected String mTitle = "";
    private long thistime;
    private WeakReference<FragmentActivity> weakMActivity;

    public interface a {
        void a(Bundle bundle);
    }

    private class b extends AsyncTask<Void, Integer, Void> {
        final /* synthetic */ BaseFragment a;

        private b(BaseFragment baseFragment) {
            this.a = baseFragment;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected void a(Void voidR) {
            this.a.onLoadFinished();
            if (com.qq.reader.appconfig.b.c) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.a.thistime > 50) {
                    Toast.makeText(ReaderApplication.getApplicationImp().getApplicationContext(), this.a.getClass().getName() + " : " + this.a.mTitle + " 启动时间  " + (currentTimeMillis - this.a.thistime) + "ms", 0).show();
                }
            }
        }

        protected Void a(Void... voidArr) {
            this.a.onLoading();
            return null;
        }
    }

    public abstract void onLoadFinished();

    public abstract void onLoading();

    public abstract void onPreLoad();

    public Handler getHandler() {
        return this.mHandler;
    }

    public boolean handleMessage(Message message) {
        return handleMessageImp(message);
    }

    protected boolean handleMessageImp(Message message) {
        return false;
    }

    public Context getApplicationContext() {
        return ReaderApplication.getApplicationImp();
    }

    public ReaderBaseActivity getBaseActivity() {
        return (ReaderBaseActivity) getActivity();
    }

    public void onCreate(Bundle bundle) {
        this.thistime = System.currentTimeMillis();
        super.onCreate(bundle);
        this.mHandler = new WeakReferenceHandler(this);
        c.a("fragment", getClass().getSimpleName());
        if (bundle != null) {
            k childFragmentManager;
            boolean z = bundle.getBoolean(DISPLAY_STATUS);
            try {
                childFragmentManager = getChildFragmentManager();
            } catch (Exception e) {
                childFragmentManager = getFragmentManager();
            }
            m a = childFragmentManager.a();
            if (z) {
                a.b(this);
            } else {
                a.c(this);
            }
            a.a();
        }
    }

    public FragmentActivity getActivityAfterDettash() {
        return (FragmentActivity) this.weakMActivity.get();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.weakMActivity = new WeakReference((FragmentActivity) context);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(DISPLAY_STATUS, isHidden());
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onPreLoad();
        this.hasDoCreateView = true;
        if (!(this.mNotExcuteWhenCreate || this.mLoadTask.getStatus() == Status.FINISHED)) {
            this.mLoadTask.execute(new Void[0]);
        }
        return onCreateView;
    }

    public HashMap getHashArguments() {
        return this.args;
    }

    public void setHashArguments(HashMap hashMap) {
        if (hashMap != null) {
            this.args = (HashMap) hashMap.clone();
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void executeLoadDataWithDelay() {
        if (this.mHasLoad) {
            doSomeRefreshThing();
            return;
        }
        this.mHasLoad = true;
        this.mLoadHandler.sendMessageDelayed(this.mLoadHandler.obtainMessage(1001), (long) this.delay);
    }

    public void executeLoadData() {
        if (!this.mHasLoad) {
            this.mHasLoad = true;
            this.mLoadHandler.sendMessage(this.mLoadHandler.obtainMessage(1001));
        }
    }

    public void cancleLoadData() {
        this.mHasLoad = false;
        this.mLoadHandler.removeMessages(1001);
    }

    public void doSomeRefreshThing() {
    }

    public void refreshFragment() {
        new b().execute(new Void[0]);
    }

    public void setFragmentLoadListener(a aVar) {
        this.mFragmentLoadListener = aVar;
    }

    public boolean onBackPress() {
        return false;
    }

    public void onDataNotify() {
    }

    public void onActivityFinish() {
    }
}
