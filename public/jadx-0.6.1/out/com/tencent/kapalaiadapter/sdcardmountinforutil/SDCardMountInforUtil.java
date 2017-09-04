package com.tencent.kapalaiadapter.sdcardmountinforutil;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SDCardMountInforUtil implements ISDCardMountInfor {
    private static SDCardMountInforUtil mSelf;
    private final String HEAD_CONF = "mount_point";
    private final String HEAD_FSTAB = "dev_mount";
    private final int PATH = 2;
    public final String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final int SDK_VERSION_LEVEL_1 = 0;
    private final int SDK_VERSION_LEVEL_2 = 1;
    private final int SDK_VERSION_LEVEL_3 = 2;
    private final File VOLD_CONF = new File(Environment.getRootDirectory().getAbsoluteFile() + File.separator + "etc" + File.separator + "vold.conf");
    private final File VOLD_FSTAB = new File(Environment.getRootDirectory().getAbsoluteFile() + File.separator + "etc" + File.separator + "vold.fstab");
    private ArrayList<String> allPath = new ArrayList();
    private ArrayList<String> cache = new ArrayList();
    private Context mContext;
    private final BroadcastReceiver sdcardListener = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.MEDIA_MOUNTED".equals(action) || "android.intent.action.MEDIA_SCANNER_STARTED".equals(action) || "android.intent.action.MEDIA_SCANNER_FINISHED".equals(action) || "android.intent.action.MEDIA_REMOVED".equals(action) || "android.intent.action.MEDIA_UNMOUNTED".equals(action) || "android.intent.action.MEDIA_BAD_REMOVAL".equals(action)) {
                SDCardMountInforUtil.this.initVoldFstabORVoldConfToCache(SDCardMountInforUtil.this.sdk_level);
            }
        }
    };
    private int sdk_level = 0;

    public static synchronized SDCardMountInforUtil getSelf(Context context) {
        SDCardMountInforUtil sDCardMountInforUtil;
        synchronized (SDCardMountInforUtil.class) {
            if (mSelf == null) {
                mSelf = new SDCardMountInforUtil(context);
            }
            sDCardMountInforUtil = mSelf;
        }
        return sDCardMountInforUtil;
    }

    public int version() {
        return VERSION.SDK_INT;
    }

    private SDCardMountInforUtil(Context context) {
        this.mContext = context;
        registerSDCardListener(context);
        if (version() < 8) {
            this.sdk_level = 0;
        } else if (version() < 11) {
            this.sdk_level = 1;
        } else {
            this.sdk_level = 2;
        }
        initVoldFstabORVoldConfToCache(this.sdk_level);
    }

    private void initVoldFstabORVoldConfToCache(int i) {
        switch (i) {
            case 0:
                initVoldConf();
                return;
            case 1:
                initVoldFstab();
                return;
            default:
                reflectGetVolumePaths();
                return;
        }
    }

    private void initVoldConf() {
        this.cache.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.VOLD_CONF));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.startsWith("mount_point")) {
                    this.cache.add(readLine);
                }
            }
            bufferedReader.close();
            this.cache.trimToSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadSdCards();
    }

    private void initVoldFstab() {
        this.cache.clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.VOLD_FSTAB));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.startsWith("dev_mount")) {
                    this.cache.add(readLine);
                }
            }
            bufferedReader.close();
            this.cache.trimToSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadSdCards();
    }

    @TargetApi(9)
    private void reflectGetVolumePaths() {
        if (this.sdk_level == 2) {
            this.allPath.clear();
            StorageManager storageManager = (StorageManager) this.mContext.getSystemService("storage");
            try {
                String[] strArr = (String[]) storageManager.getClass().getMethod("getVolumePaths", null).invoke(storageManager, null);
                int i = 0;
                int length = strArr.length;
                while (i < length && !isIgnore(strArr[i])) {
                    this.allPath.add(i, strArr[i]);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadSdCards() {
        int i;
        this.allPath.clear();
        int i2 = 0;
        for (i = 0; i < this.cache.size(); i++) {
            if (this.cache.get(i) != null) {
                String[] split = ((String) this.cache.get(i)).split(" ");
                if (!(split == null || isIgnore(split[2]))) {
                    int i3 = i2 + 1;
                    this.allPath.add(i2, split[2]);
                    i2 = i3;
                }
            }
        }
        if (!this.allPath.contains(this.ROOT)) {
            i = i2 + 1;
            this.allPath.add(i2, this.ROOT);
        }
    }

    private boolean isIgnore(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.list() == null) {
            return true;
        }
        for (String str2 : file.list()) {
            Log.i("kapalai", "--------" + str2);
        }
        return false;
    }

    private void registerSDCardListener(Context context) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentFilter.addDataScheme("file");
        context.registerReceiver(this.sdcardListener, intentFilter);
    }

    public void removeSDCardListener(Context context) {
        context.unregisterReceiver(this.sdcardListener);
    }

    public boolean isExSdcard(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (str.endsWith(this.ROOT) || !file.exists()) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getAllPath() {
        if (this.allPath.size() < 1) {
            initVoldFstabORVoldConfToCache(this.sdk_level);
        }
        return this.allPath;
    }
}
