package com.dynamicload.Lib;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;

public interface IDLPluginActivity {
    void attach(IDLProxyActivity iDLProxyActivity, DLPluginPackage dLPluginPackage, Theme theme);

    void disAttach();

    void finish();

    DLPluginPackage getPackage();

    boolean isGesture();

    void peformOnActivityResult(int i, int i2, Intent intent);

    void peformOnBackPressed();

    void peformOnConfigurationChanged(Configuration configuration);

    void peformOnCreate(Bundle bundle);

    boolean peformOnCreateOptionsMenu(Menu menu);

    void peformOnDestroy();

    boolean peformOnKeyDown(int i, KeyEvent keyEvent);

    boolean peformOnKeyUp(int i, KeyEvent keyEvent);

    void peformOnNewIntent(Intent intent);

    boolean peformOnOptionsItemSelected(MenuItem menuItem);

    void peformOnPause();

    void peformOnRestart();

    void peformOnRestoreInstanceState(Bundle bundle);

    void peformOnResume();

    void peformOnSaveInstanceState(Bundle bundle);

    void peformOnStart();

    void peformOnStop();

    boolean peformOnTouchEvent(MotionEvent motionEvent);

    void peformOnWindowAttributesChanged(LayoutParams layoutParams);

    void peformOnWindowFocusChanged(boolean z);

    void saveContentView(View view);

    void targetActivity();
}
