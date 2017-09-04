package com.dynamicload.Lib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

public interface IDLProxyActivity {
    void addContentView(View view, LayoutParams layoutParams);

    View findViewById(int i);

    void finish();

    Context getApplicationContext();

    ClassLoader getClassLoader();

    ComponentName getComponentName();

    Context getContext();

    LayoutInflater getHostLayoutInflater();

    Object getHostSystemService(String str);

    Intent getIntent();

    MenuInflater getMenuInflater();

    int getRequestedOrientation();

    Resources getResources();

    SharedPreferences getSharedPreferences(String str, int i);

    Window getWindow();

    WindowManager getWindowManager();

    boolean onSuperKeyDown(int i, KeyEvent keyEvent);

    boolean onSuperKeyUp(int i, KeyEvent keyEvent);

    void overridePendingTransition(int i, int i2);

    void setContentView(int i);

    void setContentView(View view);

    void setContentView(View view, LayoutParams layoutParams);

    void setRequestedOrientation(int i);

    void setResult(int i, Intent intent);
}
