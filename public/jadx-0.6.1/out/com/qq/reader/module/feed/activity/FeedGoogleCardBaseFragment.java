package com.qq.reader.module.feed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseFragment;
import com.qq.reader.common.a.b;
import com.qq.reader.common.monitor.i;
import java.util.HashMap;
import java.util.Map;

public abstract class FeedGoogleCardBaseFragment extends ReaderBaseFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        doRDM();
    }

    public static FeedGoogleCardBaseFragment witchActivityWithABTest() {
        return new FeedGoogleCardsFragment();
    }

    public void doRDM() {
        try {
            String str;
            Object obj;
            String str2 = "";
            str2 = "";
            if (b.a() == 1) {
                str = "abtest_B";
                obj = "1";
            } else {
                str = "abtest_A";
                obj = "0";
            }
            Map hashMap = new HashMap();
            hashMap.put("abcode", obj);
            i.a(str, hashMap, ReaderApplication.getApplicationImp());
        } catch (Exception e) {
        }
    }

    public void doFunction(FeedGoogleCardBaseFragment feedGoogleCardBaseFragment, Bundle bundle) {
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsFragment) {
            ((FeedGoogleCardsFragment) feedGoogleCardBaseFragment).doFunction(bundle);
        } else if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsStyleBFragment) {
            ((FeedGoogleCardsStyleBFragment) feedGoogleCardBaseFragment).doFunction(bundle);
        }
    }

    public Handler getHandler(FeedGoogleCardBaseFragment feedGoogleCardBaseFragment) {
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsFragment) {
            return ((FeedGoogleCardsFragment) feedGoogleCardBaseFragment).getHandler();
        }
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsStyleBFragment) {
            return ((FeedGoogleCardsStyleBFragment) feedGoogleCardBaseFragment).getHandler();
        }
        return null;
    }

    public boolean onKeyDown(FeedGoogleCardBaseFragment feedGoogleCardBaseFragment, int i, KeyEvent keyEvent) {
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsFragment) {
            return ((FeedGoogleCardsFragment) feedGoogleCardBaseFragment).onKeyDown(i, keyEvent);
        }
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsStyleBFragment) {
            return ((FeedGoogleCardsStyleBFragment) feedGoogleCardBaseFragment).onKeyDown(i, keyEvent);
        }
        return true;
    }

    public void onActivityResult(FeedGoogleCardBaseFragment feedGoogleCardBaseFragment, int i, int i2, Intent intent) {
        if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsFragment) {
            ((FeedGoogleCardsFragment) feedGoogleCardBaseFragment).onActivityResult(i, i2, intent);
        } else if (feedGoogleCardBaseFragment instanceof FeedGoogleCardsStyleBFragment) {
            ((FeedGoogleCardsStyleBFragment) feedGoogleCardBaseFragment).onActivityResult(i, i2, intent);
        }
    }
}
