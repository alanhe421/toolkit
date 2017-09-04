package com.tencent.theme;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etrump.jni.ETConverter;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.b;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SkinEngine {
    public static final String ACTION_THEME_INVALIDATE = "com.qq.reader.THEME_INVALIDATE";
    public static final String ACTION_THEME_UPDATE = "com.qq.reader.THEME_UPDATE";
    public static final String ASSET_PATH = "/asset/";
    public static boolean DEBUG = false;
    static int DENSITY_HDPI_INDEX = 1;
    static int DENSITY_LDPI_INDEX = 3;
    static int DENSITY_MDPI_INDEX = 2;
    static final int[][] DENSITY_ORDER = new int[][]{new int[]{ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, 160, 160}, new int[]{ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, 160, 160}, new int[]{160, 160, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE}, new int[]{120, 160, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, 160}, new int[]{480, ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, 160, 160}, new int[]{640, 480, ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK, 160}};
    static final String[][] DENSITY_PATH_ORDER;
    static int DENSITY_XHDPI_INDEX = 0;
    static int DENSITY_XXHDPI_INDEX = 4;
    static int DENSITY_XXXHDPI_INDEX = 5;
    private static final int DENSITY_XXXHIGH = 640;
    static boolean IS_MNC_PREVIEW = false;
    public static boolean IS_NOUGAT = false;
    public static boolean IS_PROBLEM_CM11 = false;
    public static boolean IS_PROBLEM_MIUI = false;
    public static final String KEY_PENDING_THEME = "pending_theme_root";
    public static final String KEY_PENDING_THEME_RESOURCES_IS_COMPLIED = "pending_theme_resources_complied";
    public static final String KEY_RESOURCES_IS_COMPLIED = "complied";
    public static final String KEY_THEME = "theme_root";
    public static final String PREFERENCE_NAME = "theme";
    public static boolean SWITCH_DEBUG = b.a;
    public static final String TAG = "SkinEngine";
    public static final String TAG_SWITCH = "SkinEngine.switch";
    public static final int TYPE_FILE = 16777215;
    public static final int TYPE_RESOURCES = 3;
    private static SkinEngine instances;
    public static Field mComposedIconInfoOfCM = null;
    public static Integer mIconResourceID = null;
    public static Field mIconsOfCM = null;
    public HashMap<Integer, BackupForOOMData> backupForOOMData;
    private ColorStateListPreloadIntercepter colorStateListPreloadIntercepter;
    private ColorStateListPreloadIntercepter15 colorStateListPreloadIntercepter15;
    private DrawableLoader drawableIntercepter;
    private final LongSparseArray<WeakReference<SkinnableColorStateList>> mColorStateListCache = new LongSparseArray();
    private HashMap<String, Object> mConfigs;
    private HashMap<String, Object> mDefaultConfigs;
    private int mDensityIndex = DENSITY_MDPI_INDEX;
    private final LongSparseArray<WeakReference<BaseConstantState>> mDrawableCache = new LongSparseArray();
    private boolean mIsResourcesComplied = true;
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (SkinEngine.SWITCH_DEBUG) {
                Log.d(SkinEngine.TAG_SWITCH, "UpdateReceiver.onReceive ACTION_THEME_UPDATE");
            }
            try {
                new UpdateTask().execute(new Context[]{context});
            } catch (RejectedExecutionException e) {
            }
        }
    };
    public Resources mResources;
    SkinEngineHandler mSkinEngineHandler;
    private String mSkinRootPath;
    private boolean mSkinUpdating = false;
    private String mTempSkinRootPath;
    final TypedValue mTmpValue = new TypedValue();

    public static class BackupForOOMData {
        public int backFileResId;
        public Config inPreferredConfig;
        public int orgkFileResId;

        public BackupForOOMData(int i, int i2, Config config) {
            this.orgkFileResId = i;
            this.backFileResId = i2;
            this.inPreferredConfig = config;
        }
    }

    private class UpdateTask extends AsyncTask<Context, Void, Context> {
        private UpdateTask() {
        }

        protected Context doInBackground(Context... contextArr) {
            if (SkinEngine.SWITCH_DEBUG) {
                Log.d(SkinEngine.TAG_SWITCH, "UpdateTask.doInBackground start");
            }
            SkinEngine.this.update(contextArr[0]);
            System.gc();
            Thread.yield();
            System.gc();
            if (SkinEngine.SWITCH_DEBUG) {
                Log.d(SkinEngine.TAG_SWITCH, "UpdateTask.doInBackground done");
            }
            return contextArr[0];
        }

        protected void onPostExecute(Context context) {
            if (SkinEngine.SWITCH_DEBUG) {
                Log.d(SkinEngine.TAG_SWITCH, "UpdateTask.onPostExecute start");
            }
            SkinEngine.this.mConfigs = null;
            SkinEngine.this.ensureConfigLoaded(SkinEngine.this.mIsResourcesComplied);
            Intent intent = new Intent(SkinEngine.ACTION_THEME_INVALIDATE);
            intent.putExtra("pid", Process.myPid());
            context.sendBroadcast(intent, "com.qq.reader.theme.permission");
            if (SkinEngine.SWITCH_DEBUG) {
                Log.d(SkinEngine.TAG_SWITCH, "UpdateTask.onPostExecute sendBroadcast(ACTION_THEME_INVALIDATE)");
                Log.d(SkinEngine.TAG_SWITCH, "UpdateTask.onPostExecute done");
            }
        }
    }

    static {
        boolean z = "MNC".equals(VERSION.CODENAME) || VERSION.SDK_INT >= 23;
        IS_MNC_PREVIEW = z;
        if (VERSION.SDK_INT >= 24) {
            z = true;
        } else {
            z = false;
        }
        IS_NOUGAT = z;
        r0 = new String[6][];
        r0[0] = new String[]{"drawable-xhdpi/", "drawable-hdpi/", "drawable-mdpi/", "drawable/"};
        r0[1] = new String[]{"drawable-hdpi/", "drawable-xhdpi/", "drawable-mdpi/", "drawable/"};
        r0[2] = new String[]{"drawable-mdpi/", "drawable/", "drawable-hdpi/", "drawable-xhdpi/"};
        r0[3] = new String[]{"drawable-ldpi/", "drawable-mdpi/", "drawable-hdpi/", "drawable-xhdpi/", "drawable/"};
        r0[4] = new String[]{"drawable-xxhdpi/", "drawable-xhdpi/", "drawable-hdpi/", "drawable-mdpi/", "drawable/"};
        r0[5] = new String[]{"drawable-xxxhdpi/", "drawable-xxhdpi/", "drawable-xhdpi/", "drawable-hdpi/", "drawable/"};
        DENSITY_PATH_ORDER = r0;
    }

    public boolean isEnable() {
        return !this.mSkinUpdating;
    }

    public static synchronized SkinEngine getInstances() {
        SkinEngine skinEngine;
        synchronized (SkinEngine.class) {
            if (instances == null) {
                instances = new SkinEngine();
            }
            skinEngine = instances;
        }
        return skinEngine;
    }

    @TargetApi(16)
    private SkinEngine() {
    }

    public static void init(Context context, int[] iArr, Class cls, int i, File file) throws UnSupportPlatformException {
        SkinEngine instances = getInstances();
        Resources resources = context.getResources();
        instances.mResources = resources;
        isSupportPlatform(context, resources);
        instances.mDensityIndex = getDensityIndex(instances.mResources);
        context.getApplicationContext().registerReceiver(instances.mReceiver, new IntentFilter(ACTION_THEME_UPDATE), "com.qq.reader.theme.permission", null);
        instances.initIntercepter(resources, null, iArr, 0, cls, i, file);
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 4);
        instances.mSkinRootPath = sharedPreferences.getString(KEY_THEME, null);
        instances.mIsResourcesComplied = sharedPreferences.getBoolean(KEY_RESOURCES_IS_COMPLIED, true);
    }

    public void setSkinEngineHandler(SkinEngineHandler skinEngineHandler) {
        this.mSkinEngineHandler = skinEngineHandler;
    }

    public void unInit() {
        Field declaredField;
        try {
            if (this.drawableIntercepter != null) {
                declaredField = Resources.class.getDeclaredField("sPreloadedDrawables");
                declaredField.setAccessible(true);
                if (declaredField.getDeclaringClass().isArray()) {
                    declaredField.set(null, this.drawableIntercepter.mOldPreloadCache);
                } else {
                    declaredField.set(null, this.drawableIntercepter.mOldPreloadCache[0]);
                }
            }
            try {
                Field declaredField2 = Resources.class.getDeclaredField("sPreloadedColorStateLists");
                declaredField2.setAccessible(true);
                LongSparseArray longSparseArray = (LongSparseArray) declaredField2.get(this.mResources);
                declaredField2.set(null, this.colorStateListPreloadIntercepter.mOldPreloadCache);
            } catch (Exception e) {
                declaredField = Resources.class.getDeclaredField("mPreloadedColorStateLists");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this.mResources);
                if (obj instanceof SparseArray) {
                    declaredField.set(null, this.colorStateListPreloadIntercepter15.mOldPreloadCache);
                } else if (obj instanceof LongSparseArray) {
                    declaredField.set(null, this.colorStateListPreloadIntercepter.mOldPreloadCache);
                }
            }
        } catch (Throwable e2) {
            if (DEBUG) {
                Log.e(TAG, "resotre SkinEngine failed", e2);
            }
        }
    }

    public final void addDrawableResource(int i) {
        this.drawableIntercepter.addResource(this.mResources, i);
    }

    public Object getThemeConfig(String str, Object obj) {
        synchronized (this.mTmpValue) {
            ensureConfigLoaded(this.mIsResourcesComplied);
            Object obj2 = this.mConfigs.get(str);
            if (obj2 == null) {
                obj2 = this.mDefaultConfigs.get(str);
            }
            if (obj2 != null) {
                obj = obj2;
            }
        }
        return obj;
    }

    private void ensureConfigLoaded(boolean z) {
        if (this.mDefaultConfigs == null) {
            try {
                Object openXmlResourceParser = this.mResources.getAssets().openXmlResourceParser("res/xml/theme_config.xml");
                this.mDefaultConfigs = parseXml(openXmlResourceParser);
                openXmlResourceParser.close();
            } catch (Throwable e) {
                NotFoundException notFoundException = new NotFoundException("wrong xml config file: res/xml/theme_config.xml");
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (IOException e2) {
                this.mDefaultConfigs = new HashMap();
            } catch (Exception e3) {
                this.mDefaultConfigs = new HashMap();
            }
        }
        if (this.mConfigs != null) {
            return;
        }
        if (this.mSkinRootPath == null) {
            this.mConfigs = new HashMap();
            return;
        }
        XmlPullParser androidXmlResourceParser;
        if (z) {
            try {
                androidXmlResourceParser = new AndroidXmlResourceParser();
            } catch (Throwable e4) {
                notFoundException = new NotFoundException("wrong theme xml config file: xml/theme_config.xml");
                notFoundException.initCause(e4);
                throw notFoundException;
            } catch (IOException e5) {
                this.mConfigs = new HashMap();
                return;
            } catch (Exception e6) {
                this.mConfigs = new HashMap();
                return;
            }
        }
        androidXmlResourceParser = Xml.newPullParser();
        InputStream fileInputStream = new FileInputStream(new File(this.mSkinRootPath, "xml/theme_config.xml"));
        androidXmlResourceParser.setInput(fileInputStream, "UTF-8");
        this.mConfigs = parseXml(androidXmlResourceParser);
        fileInputStream.close();
    }

    private HashMap<String, Object> parseXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xmlPullParser.getName();
        HashMap<String, Object> hashMap = new HashMap();
        if (name.equals("resources")) {
            next = xmlPullParser.getDepth() + 1;
            while (true) {
                int next2 = xmlPullParser.next();
                if (next2 == 1 || (xmlPullParser.getDepth() < next && next2 == 3)) {
                    break;
                } else if (next2 == 2) {
                    String name2 = xmlPullParser.getName();
                    String attributeValue = xmlPullParser.getAttributeValue(null, "name");
                    if ("color".equals(name2)) {
                        hashMap.put(attributeValue, Integer.valueOf(Color.parseColor(xmlPullParser.nextText())));
                    } else if ("dimen".equals(name2)) {
                        name2 = xmlPullParser.nextText();
                        if (name2.endsWith("dp")) {
                            hashMap.put(attributeValue, Float.valueOf(TypedValue.applyDimension(1, (float) Integer.parseInt(name2.substring(0, name2.length() - 2)), this.mResources.getDisplayMetrics())));
                        } else if (name2.endsWith("dip")) {
                            hashMap.put(attributeValue, Float.valueOf(TypedValue.applyDimension(1, (float) Integer.parseInt(name2.substring(0, name2.length() - 3)), this.mResources.getDisplayMetrics())));
                        } else if (name2.endsWith("sp")) {
                            hashMap.put(attributeValue, Float.valueOf(TypedValue.applyDimension(2, (float) Integer.parseInt(name2.substring(0, name2.length() - 2)), this.mResources.getDisplayMetrics())));
                        } else if (name2.endsWith("px")) {
                            hashMap.put(attributeValue, Float.valueOf(TypedValue.applyDimension(0, (float) Integer.parseInt(name2.substring(0, name2.length() - 2)), this.mResources.getDisplayMetrics())));
                        }
                    } else if ("bool".equals(name2)) {
                        hashMap.put(attributeValue, Boolean.valueOf("true".equalsIgnoreCase(xmlPullParser.nextText())));
                    } else if ("integer".equals(name2)) {
                        hashMap.put(attributeValue, Integer.valueOf(Integer.parseInt(xmlPullParser.nextText())));
                    }
                }
            }
        }
        return hashMap;
    }

    public String getSkinRootPath() {
        if (this.mSkinUpdating) {
            return this.mTempSkinRootPath;
        }
        return this.mSkinRootPath;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initIntercepter(android.content.res.Resources r16, java.lang.Class r17, int[] r18, int r19, java.lang.Class r20, int r21, java.io.File r22) throws com.tencent.theme.UnSupportPlatformException {
        /*
        r15 = this;
        r2 = 0;
        if (r18 != 0) goto L_0x0007;
    L_0x0003:
        if (r17 == 0) goto L_0x0060;
    L_0x0005:
        if (r19 == 0) goto L_0x0060;
    L_0x0007:
        r1 = IS_NOUGAT;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r1 == 0) goto L_0x0139;
    L_0x000b:
        r1 = android.content.res.Resources.class;
        r2 = "mResourcesImpl";
        r1 = r1.getDeclaredField(r2);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r14 = r1.get(r0);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = r14.getClass();	 Catch:{ NoSuchFieldException -> 0x0127, IllegalArgumentException -> 0x016b, IllegalAccessException -> 0x0232 }
        r2 = "sPreloadedDrawables";
        r1 = r1.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x0127, IllegalArgumentException -> 0x016b, IllegalAccessException -> 0x0232 }
    L_0x0029:
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r1.get(r14);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r8 = r1;
    L_0x0032:
        r1 = r2 instanceof android.util.LongSparseArray;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r1 == 0) goto L_0x0182;
    L_0x0036:
        if (r18 == 0) goto L_0x0151;
    L_0x0038:
        r1 = new com.tencent.theme.DrawableLoader;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r3 = 1;
        r6 = new android.util.LongSparseArray[r3];	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r3 = 0;
        r2 = (android.util.LongSparseArray) r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r6[r3] = r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r15;
        r3 = r16;
        r4 = r18;
        r5 = r22;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.drawableIntercepter = r1;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
    L_0x004e:
        r1 = IS_NOUGAT;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r1 == 0) goto L_0x0172;
    L_0x0052:
        if (r14 == 0) goto L_0x0172;
    L_0x0054:
        r1 = new com.tencent.theme.DrawablePreloadIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = 0;
        r3 = r15.drawableIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1.<init>(r2, r3);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r8.set(r14, r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r14;
    L_0x0060:
        if (r20 == 0) goto L_0x00b0;
    L_0x0062:
        if (r21 == 0) goto L_0x00b0;
    L_0x0064:
        r1 = IS_NOUGAT;	 Catch:{ Exception -> 0x0201 }
        if (r1 == 0) goto L_0x01d7;
    L_0x0068:
        r1 = android.content.res.Resources.class;
        r2 = "mResourcesImpl";
        r1 = r1.getDeclaredField(r2);	 Catch:{ Exception -> 0x0201 }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0201 }
        r0 = r16;
        r2 = r1.get(r0);	 Catch:{ Exception -> 0x0201 }
        r1 = r2.getClass();	 Catch:{ NoSuchFieldException -> 0x01c5, IllegalArgumentException -> 0x016b, IllegalAccessException -> 0x0232 }
        r3 = "sPreloadedComplexColors";
        r3 = r1.getDeclaredField(r3);	 Catch:{ NoSuchFieldException -> 0x01c5, IllegalArgumentException -> 0x016b, IllegalAccessException -> 0x0232 }
    L_0x0086:
        r1 = 1;
        r3.setAccessible(r1);	 Catch:{ Exception -> 0x0201 }
        r1 = r3.get(r2);	 Catch:{ Exception -> 0x0201 }
        r1 = (android.util.LongSparseArray) r1;	 Catch:{ Exception -> 0x0201 }
        r4 = r1;
        r7 = r2;
        r8 = r3;
    L_0x0093:
        r1 = IS_MNC_PREVIEW;	 Catch:{ Exception -> 0x0201 }
        if (r1 == 0) goto L_0x01f1;
    L_0x0097:
        r1 = new com.tencent.theme.ColorStateListPreloadIntercepterMNC;	 Catch:{ Exception -> 0x0201 }
        r2 = r15;
        r3 = r16;
        r5 = r20;
        r6 = r21;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0201 }
        r15.colorStateListPreloadIntercepter = r1;	 Catch:{ Exception -> 0x0201 }
    L_0x00a5:
        r1 = IS_NOUGAT;	 Catch:{ Exception -> 0x0201 }
        if (r1 == 0) goto L_0x0239;
    L_0x00a9:
        if (r7 == 0) goto L_0x0239;
    L_0x00ab:
        r1 = r15.colorStateListPreloadIntercepter;	 Catch:{ Exception -> 0x0201 }
        r8.set(r7, r1);	 Catch:{ Exception -> 0x0201 }
    L_0x00b0:
        r1 = r16.getClass();	 Catch:{ Exception -> 0x0265 }
        r2 = r1.getName();	 Catch:{ Exception -> 0x0265 }
        r3 = "android.content.res.MiuiResources";
        r2 = r2.equals(r3);	 Catch:{ Exception -> 0x0265 }
        if (r2 == 0) goto L_0x00e1;
    L_0x00c1:
        r2 = "sPreloadDrawableSources";
        r1 = r1.getDeclaredField(r2);	 Catch:{ Exception -> 0x0265 }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0265 }
        r0 = r16;
        r2 = r1.get(r0);	 Catch:{ Exception -> 0x0265 }
        if (r2 != 0) goto L_0x00e1;
    L_0x00d4:
        r2 = new android.util.SparseArray;	 Catch:{ Exception -> 0x0265 }
        r2.<init>();	 Catch:{ Exception -> 0x0265 }
        r0 = r16;
        r1.set(r0, r2);	 Catch:{ Exception -> 0x0265 }
        r1 = 1;
        IS_PROBLEM_MIUI = r1;	 Catch:{ Exception -> 0x0265 }
    L_0x00e1:
        r1 = r16.getClass();	 Catch:{ Exception -> 0x0278 }
        r2 = "mIcons";
        r1 = r1.getDeclaredField(r2);	 Catch:{ Exception -> 0x0278 }
        mIconsOfCM = r1;	 Catch:{ Exception -> 0x0278 }
        r1 = mIconsOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0278 }
        r1 = mIconsOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 0;
        r0 = r16;
        r1.set(r0, r2);	 Catch:{ Exception -> 0x0278 }
        r1 = mIconsOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 0;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0278 }
        r1 = r16.getClass();	 Catch:{ Exception -> 0x0278 }
        r2 = "mComposedIconInfo";
        r1 = r1.getDeclaredField(r2);	 Catch:{ Exception -> 0x0278 }
        mComposedIconInfoOfCM = r1;	 Catch:{ Exception -> 0x0278 }
        r1 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0278 }
        r1 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 0;
        r0 = r16;
        r1.set(r0, r2);	 Catch:{ Exception -> 0x0278 }
        r1 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0278 }
        r2 = 0;
        r1.setAccessible(r2);	 Catch:{ Exception -> 0x0278 }
        r1 = 1;
        IS_PROBLEM_CM11 = r1;	 Catch:{ Exception -> 0x0278 }
    L_0x0126:
        return;
    L_0x0127:
        r1 = move-exception;
        r1 = r14.getClass();	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = r1.getSuperclass();	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = "sPreloadedDrawables";
        r1 = r1.getDeclaredField(r2);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        goto L_0x0029;
    L_0x0139:
        r1 = android.content.res.Resources.class;
        r3 = "sPreloadedDrawables";
        r3 = r1.getDeclaredField(r3);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = 1;
        r3.setAccessible(r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r1 = r3.get(r0);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r14 = r2;
        r8 = r3;
        r2 = r1;
        goto L_0x0032;
    L_0x0151:
        r1 = new com.tencent.theme.DrawableLoader;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r3 = 1;
        r7 = new android.util.LongSparseArray[r3];	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r3 = 0;
        r2 = (android.util.LongSparseArray) r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r7[r3] = r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r19;
        r6 = r22;
        r1.<init>(r2, r3, r4, r5, r6, r7);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.drawableIntercepter = r1;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        goto L_0x004e;
    L_0x016b:
        r1 = move-exception;
        r2 = new com.tencent.theme.UnSupportPlatformException;
        r2.<init>(r1);
        throw r2;
    L_0x0172:
        r1 = new com.tencent.theme.DrawablePreloadIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = 0;
        r3 = r15.drawableIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1.<init>(r2, r3);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r8.set(r0, r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r14;
        goto L_0x0060;
    L_0x0182:
        r1 = r2 instanceof android.util.LongSparseArray[];	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r1 == 0) goto L_0x0281;
    L_0x0186:
        r2 = (android.util.LongSparseArray[]) r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r2;
        r0 = (android.util.LongSparseArray[]) r0;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r6 = r0;
        if (r18 == 0) goto L_0x01ac;
    L_0x018e:
        r1 = new com.tencent.theme.DrawableLoader;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r15;
        r3 = r16;
        r4 = r18;
        r5 = r22;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.drawableIntercepter = r1;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
    L_0x019c:
        r1 = 0;
    L_0x019d:
        r2 = r6.length;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r1 >= r2) goto L_0x0281;
    L_0x01a0:
        r2 = new com.tencent.theme.DrawablePreloadIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r3 = r15.drawableIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2.<init>(r1, r3);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r6[r1] = r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = r1 + 1;
        goto L_0x019d;
    L_0x01ac:
        r7 = new com.tencent.theme.DrawableLoader;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r8 = r15;
        r9 = r16;
        r10 = r17;
        r11 = r19;
        r12 = r22;
        r13 = r6;
        r7.<init>(r8, r9, r10, r11, r12, r13);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.drawableIntercepter = r7;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        goto L_0x019c;
    L_0x01be:
        r1 = move-exception;
        r2 = new com.tencent.theme.UnSupportPlatformException;
        r2.<init>(r1);
        throw r2;
    L_0x01c5:
        r1 = move-exception;
        r1 = r2.getClass();	 Catch:{ Exception -> 0x0201 }
        r1 = r1.getSuperclass();	 Catch:{ Exception -> 0x0201 }
        r3 = "sPreloadedComplexColors";
        r3 = r1.getDeclaredField(r3);	 Catch:{ Exception -> 0x0201 }
        goto L_0x0086;
    L_0x01d7:
        r1 = android.content.res.Resources.class;
        r3 = "sPreloadedColorStateLists";
        r3 = r1.getDeclaredField(r3);	 Catch:{ Exception -> 0x0201 }
        r1 = 1;
        r3.setAccessible(r1);	 Catch:{ Exception -> 0x0201 }
        r0 = r16;
        r1 = r3.get(r0);	 Catch:{ Exception -> 0x0201 }
        r1 = (android.util.LongSparseArray) r1;	 Catch:{ Exception -> 0x0201 }
        r4 = r1;
        r7 = r2;
        r8 = r3;
        goto L_0x0093;
    L_0x01f1:
        r1 = new com.tencent.theme.ColorStateListPreloadIntercepter;	 Catch:{ Exception -> 0x0201 }
        r2 = r15;
        r3 = r16;
        r5 = r20;
        r6 = r21;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0201 }
        r15.colorStateListPreloadIntercepter = r1;	 Catch:{ Exception -> 0x0201 }
        goto L_0x00a5;
    L_0x0201:
        r1 = move-exception;
        r2 = android.content.res.Resources.class;
        r3 = "mPreloadedColorStateLists";
        r7 = r2.getDeclaredField(r3);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = 1;
        r7.setAccessible(r2);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r4 = r7.get(r0);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r4 instanceof android.util.SparseArray;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r2 == 0) goto L_0x0242;
    L_0x0219:
        r1 = new com.tencent.theme.ColorStateListPreloadIntercepter15;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r4 = (android.util.SparseArray) r4;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r15;
        r3 = r16;
        r5 = r20;
        r6 = r21;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.colorStateListPreloadIntercepter15 = r1;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = r15.colorStateListPreloadIntercepter15;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r7.set(r0, r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        goto L_0x00b0;
    L_0x0232:
        r1 = move-exception;
        r2 = new com.tencent.theme.UnSupportPlatformException;
        r2.<init>(r1);
        throw r2;
    L_0x0239:
        r1 = r15.colorStateListPreloadIntercepter;	 Catch:{ Exception -> 0x0201 }
        r0 = r16;
        r8.set(r0, r1);	 Catch:{ Exception -> 0x0201 }
        goto L_0x00b0;
    L_0x0242:
        r2 = r4 instanceof android.util.LongSparseArray;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        if (r2 == 0) goto L_0x025f;
    L_0x0246:
        r1 = new com.tencent.theme.ColorStateListPreloadIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r4 = (android.util.LongSparseArray) r4;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2 = r15;
        r3 = r16;
        r5 = r20;
        r6 = r21;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r15.colorStateListPreloadIntercepter = r1;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r1 = r15.colorStateListPreloadIntercepter;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r0 = r16;
        r7.set(r0, r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        goto L_0x00b0;
    L_0x025f:
        r2 = new com.tencent.theme.UnSupportPlatformException;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        r2.<init>(r1);	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
        throw r2;	 Catch:{ IllegalArgumentException -> 0x016b, NoSuchFieldException -> 0x01be, IllegalAccessException -> 0x0232 }
    L_0x0265:
        r1 = move-exception;
        r2 = DEBUG;
        if (r2 == 0) goto L_0x0273;
    L_0x026a:
        r2 = "SkinEngine";
        r3 = "";
        android.util.Log.e(r2, r3, r1);
    L_0x0273:
        r1 = 0;
        IS_PROBLEM_MIUI = r1;
        goto L_0x00e1;
    L_0x0278:
        r1 = move-exception;
        r1 = 0;
        mIconsOfCM = r1;
        r1 = 0;
        mComposedIconInfoOfCM = r1;
        goto L_0x0126;
    L_0x0281:
        r2 = r14;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.theme.SkinEngine.initIntercepter(android.content.res.Resources, java.lang.Class, int[], int, java.lang.Class, int, java.io.File):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void isSupportPlatform(android.content.Context r3, android.content.res.Resources r4) throws com.tencent.theme.UnSupportPlatformException {
        /*
        r0 = IS_NOUGAT;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        if (r0 == 0) goto L_0x0051;
    L_0x0004:
        r0 = android.content.res.Resources.class;
        r1 = "mResourcesImpl";
        r0 = r0.getDeclaredField(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1 = r0.get(r4);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r0 = r1.getClass();	 Catch:{ NoSuchFieldException -> 0x0040, IllegalArgumentException -> 0x0039, IllegalAccessException -> 0x00fb }
        r2 = "sPreloadedDrawables";
        r0 = r0.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x0040, IllegalArgumentException -> 0x0039, IllegalAccessException -> 0x00fb }
    L_0x0020:
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r0 = r0.get(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
    L_0x0028:
        r1 = r0 instanceof android.util.LongSparseArray;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        if (r1 != 0) goto L_0x0063;
    L_0x002c:
        r0 = r0 instanceof android.util.LongSparseArray[];	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        if (r0 != 0) goto L_0x0063;
    L_0x0030:
        r0 = new com.tencent.theme.UnSupportPlatformException;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1 = "sPreloadedDrawables is not LongSparseArray";
        r0.<init>(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        throw r0;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
    L_0x0039:
        r0 = move-exception;
        r1 = new com.tencent.theme.UnSupportPlatformException;
        r1.<init>(r0);
        throw r1;
    L_0x0040:
        r0 = move-exception;
        r0 = r1.getClass();	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r0 = r0.getSuperclass();	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r2 = "sPreloadedDrawables";
        r0 = r0.getDeclaredField(r2);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        goto L_0x0020;
    L_0x0051:
        r0 = android.content.res.Resources.class;
        r1 = "sPreloadedDrawables";
        r0 = r0.getDeclaredField(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r0 = r0.get(r4);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        goto L_0x0028;
    L_0x0063:
        r0 = IS_NOUGAT;	 Catch:{ Exception -> 0x0098 }
        if (r0 == 0) goto L_0x00d8;
    L_0x0067:
        r0 = android.content.res.Resources.class;
        r1 = "mResourcesImpl";
        r0 = r0.getDeclaredField(r1);	 Catch:{ Exception -> 0x0098 }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ Exception -> 0x0098 }
        r1 = r0.get(r4);	 Catch:{ Exception -> 0x0098 }
        r0 = r1.getClass();	 Catch:{ NoSuchFieldException -> 0x00c7, IllegalArgumentException -> 0x0039, IllegalAccessException -> 0x00fb }
        r2 = "sPreloadedComplexColors";
        r0 = r0.getDeclaredField(r2);	 Catch:{ NoSuchFieldException -> 0x00c7, IllegalArgumentException -> 0x0039, IllegalAccessException -> 0x00fb }
    L_0x0083:
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0098 }
        r0 = r0.get(r1);	 Catch:{ Exception -> 0x0098 }
    L_0x008b:
        r0 = r0 instanceof android.util.LongSparseArray;	 Catch:{ Exception -> 0x0098 }
        if (r0 != 0) goto L_0x00ae;
    L_0x008f:
        r0 = new com.tencent.theme.UnSupportPlatformException;	 Catch:{ Exception -> 0x0098 }
        r1 = "sPreloadedColorStateLists is not LongSparseArray";
        r0.<init>(r1);	 Catch:{ Exception -> 0x0098 }
        throw r0;	 Catch:{ Exception -> 0x0098 }
    L_0x0098:
        r0 = move-exception;
        r1 = android.content.res.Resources.class;
        r2 = "mPreloadedColorStateLists";
        r1 = r1.getDeclaredField(r2);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r2 = 1;
        r1.setAccessible(r2);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1 = r1.get(r4);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r2 = r1 instanceof android.util.SparseArray;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        if (r2 == 0) goto L_0x00ea;
    L_0x00ae:
        r0 = "ro.lewa.version";
        r0 = com.tencent.theme.SystemPropertiesProxy.get(r3, r0);
        r1 = "LeWa_OS6.0_14.10.22";
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 == 0) goto L_0x0102;
    L_0x00be:
        r0 = new com.tencent.theme.UnSupportPlatformException;
        r1 = "lewa is not supported";
        r0.<init>(r1);
        throw r0;
    L_0x00c7:
        r0 = move-exception;
        r0 = r1.getClass();	 Catch:{ Exception -> 0x0098 }
        r0 = r0.getSuperclass();	 Catch:{ Exception -> 0x0098 }
        r2 = "sPreloadedComplexColors";
        r0 = r0.getDeclaredField(r2);	 Catch:{ Exception -> 0x0098 }
        goto L_0x0083;
    L_0x00d8:
        r0 = android.content.res.Resources.class;
        r1 = "sPreloadedColorStateLists";
        r0 = r0.getDeclaredField(r1);	 Catch:{ Exception -> 0x0098 }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ Exception -> 0x0098 }
        r0 = r0.get(r4);	 Catch:{ Exception -> 0x0098 }
        goto L_0x008b;
    L_0x00ea:
        r1 = r1 instanceof android.util.LongSparseArray;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        if (r1 != 0) goto L_0x00ae;
    L_0x00ee:
        r1 = new com.tencent.theme.UnSupportPlatformException;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        r1.<init>(r0);	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
        throw r1;	 Catch:{ IllegalArgumentException -> 0x0039, NoSuchFieldException -> 0x00f4, IllegalAccessException -> 0x00fb }
    L_0x00f4:
        r0 = move-exception;
        r1 = new com.tencent.theme.UnSupportPlatformException;
        r1.<init>(r0);
        throw r1;
    L_0x00fb:
        r0 = move-exception;
        r1 = new com.tencent.theme.UnSupportPlatformException;
        r1.<init>(r0);
        throw r1;
    L_0x0102:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.theme.SkinEngine.isSupportPlatform(android.content.Context, android.content.res.Resources):void");
    }

    void update(Context context) {
        boolean z = true;
        if (SWITCH_DEBUG) {
            Log.d(TAG_SWITCH, "update start");
        }
        this.mSkinUpdating = true;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 4);
        String string = sharedPreferences.getString(KEY_PENDING_THEME, null);
        if (string != null) {
            z = sharedPreferences.getBoolean(KEY_PENDING_THEME_RESOURCES_IS_COMPLIED, true);
        }
        synchronized (this.mTmpValue) {
            this.mTempSkinRootPath = this.mSkinRootPath;
            this.mSkinRootPath = string;
            this.mIsResourcesComplied = z;
            updateImage(context);
            updateColorState(context);
        }
        sharedPreferences.edit().putString(KEY_THEME, string).putBoolean(KEY_RESOURCES_IS_COMPLIED, this.mIsResourcesComplied).commit();
        this.mSkinUpdating = false;
        this.mTempSkinRootPath = null;
        if (SWITCH_DEBUG) {
            Log.d(TAG_SWITCH, "update done");
        }
    }

    @TargetApi(16)
    private void updateColorState(Context context) {
        synchronized (this.mTmpValue) {
            for (int i = 0; i < this.mColorStateListCache.size(); i++) {
                SkinnableColorStateList skinnableColorStateList = (SkinnableColorStateList) ((WeakReference) this.mColorStateListCache.valueAt(i)).get();
                if (skinnableColorStateList != null) {
                    SkinData skinData = skinnableColorStateList.skinData;
                    String str = skinData.mFilePath;
                    if (this.mSkinRootPath != null) {
                        String grabColorFile = grabColorFile(this.mResources, skinData.mFileName);
                        if (grabColorFile != null) {
                            skinData.mFilePath = grabColorFile;
                        } else {
                            skinData.mFilePath = null;
                        }
                    } else {
                        skinData.mFilePath = null;
                    }
                    if (str != null || skinData.mFilePath != null) {
                        skinnableColorStateList.update(loadColor(skinData.mResourcesID, this.mResources, skinData));
                    }
                }
            }
        }
    }

    @TargetApi(16)
    private void updateImage(Context context) {
        synchronized (this.mTmpValue) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.mDrawableCache.size(); i++) {
                BaseConstantState baseConstantState = (BaseConstantState) ((WeakReference) this.mDrawableCache.valueAt(i)).get();
                if (baseConstantState != null) {
                    if (baseConstantState.skinData.mFileName.endsWith(".xml")) {
                        arrayList.add(baseConstantState);
                    } else {
                        reloadImage(baseConstantState);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                reloadImage((BaseConstantState) it.next());
            }
        }
    }

    private void reloadImage(BaseConstantState baseConstantState) {
        SkinData skinData = baseConstantState.skinData;
        String str = skinData.mFilePath;
        if (this.mSkinRootPath != null) {
            grabImageFile(this.mResources, this.mTmpValue, skinData.mFileName, DENSITY_PATH_ORDER[this.mDensityIndex], DENSITY_ORDER[this.mDensityIndex]);
            if (this.mTmpValue.string != null) {
                skinData.mFilePath = this.mTmpValue.string.toString();
                skinData.mInDensity = this.mTmpValue.density;
            } else {
                skinData.mFilePath = null;
            }
        } else {
            skinData.mFilePath = null;
        }
        if (str != null || skinData.mFilePath != null || skinData.mFileName.endsWith(".xml")) {
            BaseConstantState loadImage;
            if (baseConstantState instanceof BitmapState) {
                BitmapState bitmapState = (BitmapState) baseConstantState;
                loadImage = loadImage(skinData.mResourcesID, this.mResources, skinData);
                if (loadImage == null) {
                    if (DEBUG) {
                        throw new NullPointerException("loadImage failed, file path: " + skinData.mFilePath + " , resource name:" + skinData.mFileName);
                    }
                } else if (DEBUG && (loadImage instanceof NinePatchState)) {
                    throw new IllegalArgumentException("error image, the resource Image is Bitmap, but the skin Image is 9-path, file path: " + skinData.mFilePath + " , resource name:" + skinData.mFileName);
                } else {
                    BitmapState bitmapState2 = (BitmapState) loadImage;
                    bitmapState.mBitmap = bitmapState2.mBitmap;
                    bitmapState.bitmapType = bitmapState2.bitmapType;
                    bitmapState.hasProblem = bitmapState2.hasProblem;
                    bitmapState.mImageSizeWhenOOM = bitmapState2.mImageSizeWhenOOM;
                    if (bitmapState.mBuildFromXml || bitmapState2.mBuildFromXml) {
                        bitmapState.mGravity = bitmapState2.mGravity;
                        bitmapState.mPaint = bitmapState2.mPaint;
                        bitmapState.mTileModeX = bitmapState2.mTileModeX;
                        bitmapState.mTileModeY = bitmapState2.mTileModeY;
                        bitmapState.mBuildFromXml = true;
                    }
                }
            } else if (baseConstantState instanceof NinePatchState) {
                NinePatchState ninePatchState = (NinePatchState) baseConstantState;
                loadImage = loadImage(skinData.mResourcesID, this.mResources, skinData);
                if (loadImage == null) {
                    if (DEBUG) {
                        throw new NullPointerException("loadImage failed, file path: " + skinData.mFilePath + " , resource name:" + skinData.mFileName);
                    }
                } else if (DEBUG && (loadImage instanceof BitmapState)) {
                    throw new IllegalArgumentException("error image, the resource Image is 9-path, but the skin Image is Bitmap, did you compiled the 9-pathflie to apk and un-zip it? file path: " + skinData.mFilePath + " , resource name:" + skinData.mFileName);
                } else {
                    NinePatchState ninePatchState2 = (NinePatchState) loadImage;
                    ninePatchState.mImageSizeWhenOOM = ninePatchState2.mImageSizeWhenOOM;
                    ninePatchState.mNinePatch = ninePatchState2.mNinePatch;
                    ninePatchState.mOldPadding = ninePatchState.mPadding;
                    ninePatchState.mPadding = ninePatchState2.mPadding;
                    ninePatchState.mBitmap = ninePatchState2.mBitmap;
                    ninePatchState.hasProblem = ninePatchState2.hasProblem;
                }
            }
        }
    }

    public boolean setSkinRootPath(Context context, String str, boolean z) {
        if (this.mSkinUpdating) {
            if (SWITCH_DEBUG) {
                Log.d(TAG_SWITCH, "[setSkinRootPath] mSkinUpdating is true");
            }
            return false;
        }
        if (SWITCH_DEBUG) {
            Log.d(TAG_SWITCH, "[setSkinRootPath] start");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 4);
        if (str == null) {
            sharedPreferences.edit().remove(KEY_PENDING_THEME).remove(KEY_PENDING_THEME_RESOURCES_IS_COMPLIED).commit();
        } else {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            if (str.startsWith(ASSET_PATH)) {
                sharedPreferences.edit().putString(KEY_PENDING_THEME, str).putBoolean(KEY_PENDING_THEME_RESOURCES_IS_COMPLIED, z).commit();
            } else {
                File file = new File(str);
                if (file.exists() && file.isDirectory()) {
                    sharedPreferences.edit().putString(KEY_PENDING_THEME, str).putBoolean(KEY_PENDING_THEME_RESOURCES_IS_COMPLIED, z).commit();
                } else {
                    if (DEBUG) {
                        Log.w(TAG, "[setSkinRootPath] wrong skinPath: " + str);
                    }
                    sharedPreferences.edit().remove(KEY_PENDING_THEME).putBoolean(KEY_PENDING_THEME_RESOURCES_IS_COMPLIED, z).commit();
                }
            }
        }
        context.sendBroadcast(new Intent(ACTION_THEME_UPDATE), "com.qq.reader.theme.permission");
        if (SWITCH_DEBUG) {
            Log.d(TAG_SWITCH, "[setSkinRootPath] sendBroadcast(ACTION_THEME_UPDATE)");
            Log.d(TAG_SWITCH, "[setSkinRootPath] done");
        }
        return true;
    }

    private static int getDensityIndex(Resources resources) {
        int i = resources.getDisplayMetrics().densityDpi;
        if (i < 120) {
            return DENSITY_LDPI_INDEX;
        }
        if (i >= 120 && i < 160) {
            return DENSITY_LDPI_INDEX;
        }
        if (i >= 160 && i < ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) {
            return DENSITY_MDPI_INDEX;
        }
        if (i >= ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK && i < ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE) {
            return DENSITY_HDPI_INDEX;
        }
        if (i >= ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE && i < 480) {
            return DENSITY_XHDPI_INDEX;
        }
        if (i >= 480 && i < 640) {
            return DENSITY_XXHDPI_INDEX;
        }
        if (i >= 640) {
            return DENSITY_XXXHDPI_INDEX;
        }
        return DENSITY_MDPI_INDEX;
    }

    @TargetApi(16)
    public void getValue(int i, TypedValue typedValue) {
        this.mResources.getValue(i, typedValue, true);
        if (this.mSkinRootPath != null) {
            WeakReference weakReference = (WeakReference) this.mDrawableCache.get((long) i);
            if (weakReference != null) {
                BaseConstantState baseConstantState = (BaseConstantState) weakReference.get();
                if (baseConstantState == null) {
                    this.mDrawableCache.delete((long) i);
                } else if (baseConstantState.skinData.mFilePath != null) {
                    typedValue.type = TYPE_FILE;
                    typedValue.string = baseConstantState.skinData.mFilePath;
                    return;
                }
            }
            String charSequence = typedValue.string.toString();
            String substring = charSequence.substring(charSequence.lastIndexOf("/") + 1);
            TypedValue typedValue2 = new TypedValue();
            grabImageFile(this.mResources, typedValue2, substring, DENSITY_PATH_ORDER[this.mDensityIndex], DENSITY_ORDER[this.mDensityIndex]);
            if (typedValue2.string != null) {
                typedValue.type = TYPE_FILE;
                typedValue.string = typedValue2.string;
            }
        }
    }

    private void grabImageFile(Resources resources, TypedValue typedValue, String str, String[] strArr, int[] iArr) {
        int i = 0;
        while (i < iArr.length) {
            try {
                CharSequence charSequence = this.mSkinRootPath + strArr[i] + str;
                if (charSequence.startsWith(ASSET_PATH)) {
                    resources.getAssets().open(charSequence.substring(ASSET_PATH.length())).close();
                    typedValue.string = charSequence;
                    typedValue.density = iArr[i];
                    return;
                } else if (new File(charSequence).exists()) {
                    typedValue.string = charSequence;
                    typedValue.density = iArr[i];
                    return;
                } else {
                    i++;
                }
            } catch (Throwable e) {
                if (DEBUG) {
                    Log.w(TAG, e);
                }
            }
        }
        typedValue.string = null;
        typedValue.density = 0;
    }

    private String grabColorFile(Resources resources, String str) {
        String str2 = this.mSkinRootPath + "color/" + str;
        try {
            if (str2.startsWith(ASSET_PATH)) {
                resources.getAssets().open(str2.substring(ASSET_PATH.length())).close();
                return str2;
            }
            if (new File(str2).exists()) {
                return str2;
            }
            return null;
        } catch (Throwable e) {
            if (DEBUG) {
                Log.w(TAG, e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(16)
    android.graphics.drawable.Drawable.ConstantState loadConstantState(int r9) {
        /*
        r8 = this;
        r1 = 0;
        r6 = r8.mTmpValue;
        monitor-enter(r6);
        r0 = r8.mDrawableCache;	 Catch:{ all -> 0x00d8 }
        r2 = (long) r9;	 Catch:{ all -> 0x00d8 }
        r0 = r0.get(r2);	 Catch:{ all -> 0x00d8 }
        r0 = (java.lang.ref.WeakReference) r0;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x001f;
    L_0x000f:
        r0 = r0.get();	 Catch:{ all -> 0x00d8 }
        r0 = (android.graphics.drawable.Drawable.ConstantState) r0;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x0019;
    L_0x0017:
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r8.mDrawableCache;	 Catch:{ all -> 0x00d8 }
        r2 = (long) r9;	 Catch:{ all -> 0x00d8 }
        r0.delete(r2);	 Catch:{ all -> 0x00d8 }
    L_0x001f:
        r0 = IS_PROBLEM_CM11;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x006a;
    L_0x0023:
        r0 = mIconsOfCM;	 Catch:{ Exception -> 0x0062 }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0062 }
        r0 = mIconsOfCM;	 Catch:{ Exception -> 0x0062 }
        r2 = r8.mResources;	 Catch:{ Exception -> 0x0062 }
        r3 = 0;
        r0.set(r2, r3);	 Catch:{ Exception -> 0x0062 }
        r0 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0062 }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0062 }
        r0 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0062 }
        r2 = r8.mResources;	 Catch:{ Exception -> 0x0062 }
        r3 = 0;
        r0.set(r2, r3);	 Catch:{ Exception -> 0x0062 }
        r0 = mIconsOfCM;	 Catch:{ Exception -> 0x0066 }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0066 }
        r0 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0066 }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ Exception -> 0x0066 }
        r0 = mIconsOfCM;	 Catch:{ Exception -> 0x0066 }
        r2 = r8.mResources;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x0066 }
        if (r0 == 0) goto L_0x006a;
    L_0x0055:
        r0 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0066 }
        r2 = r8.mResources;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x0066 }
        if (r0 == 0) goto L_0x006a;
    L_0x005f:
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0018;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0018;
    L_0x0066:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0018;
    L_0x006a:
        r0 = r8.mResources;	 Catch:{ all -> 0x00d8 }
        r1 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r2 = 1;
        r0.getValue(r9, r1, r2);	 Catch:{ all -> 0x00d8 }
        r0 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r0 = r0.string;	 Catch:{ all -> 0x00d8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00d8 }
        r7 = new com.tencent.theme.SkinData;	 Catch:{ all -> 0x00d8 }
        r7.<init>();	 Catch:{ all -> 0x00d8 }
        r7.mResourcesID = r9;	 Catch:{ all -> 0x00d8 }
        r1 = "/";
        r1 = r0.lastIndexOf(r1);	 Catch:{ all -> 0x00d8 }
        r1 = r1 + 1;
        r0 = r0.substring(r1);	 Catch:{ all -> 0x00d8 }
        r7.mFileName = r0;	 Catch:{ all -> 0x00d8 }
        r0 = r8.mSkinRootPath;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x00c0;
    L_0x0094:
        r1 = r8.mResources;	 Catch:{ all -> 0x00d8 }
        r2 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r3 = r7.mFileName;	 Catch:{ all -> 0x00d8 }
        r0 = DENSITY_PATH_ORDER;	 Catch:{ all -> 0x00d8 }
        r4 = r8.mDensityIndex;	 Catch:{ all -> 0x00d8 }
        r4 = r0[r4];	 Catch:{ all -> 0x00d8 }
        r0 = DENSITY_ORDER;	 Catch:{ all -> 0x00d8 }
        r5 = r8.mDensityIndex;	 Catch:{ all -> 0x00d8 }
        r5 = r0[r5];	 Catch:{ all -> 0x00d8 }
        r0 = r8;
        r0.grabImageFile(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x00d8 }
        r0 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r0 = r0.string;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x00c0;
    L_0x00b0:
        r0 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r0 = r0.string;	 Catch:{ all -> 0x00d8 }
        r0 = r0.toString();	 Catch:{ all -> 0x00d8 }
        r7.mFilePath = r0;	 Catch:{ all -> 0x00d8 }
        r0 = r8.mTmpValue;	 Catch:{ all -> 0x00d8 }
        r0 = r0.density;	 Catch:{ all -> 0x00d8 }
        r7.mInDensity = r0;	 Catch:{ all -> 0x00d8 }
    L_0x00c0:
        r0 = r8.mResources;	 Catch:{ all -> 0x00d8 }
        r0 = r8.loadImage(r9, r0, r7);	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x00d5;
    L_0x00c8:
        r0.skinData = r7;	 Catch:{ all -> 0x00d8 }
        r1 = r8.mDrawableCache;	 Catch:{ all -> 0x00d8 }
        r2 = (long) r9;	 Catch:{ all -> 0x00d8 }
        r4 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x00d8 }
        r4.<init>(r0);	 Catch:{ all -> 0x00d8 }
        r1.put(r2, r4);	 Catch:{ all -> 0x00d8 }
    L_0x00d5:
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
        goto L_0x0018;
    L_0x00d8:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00d8 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.theme.SkinEngine.loadConstantState(int):android.graphics.drawable.Drawable$ConstantState");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tencent.theme.BaseConstantState loadImage(int r21, android.content.res.Resources r22, com.tencent.theme.SkinData r23) {
        /*
        r20 = this;
        r0 = r20;
        r0 = r0.mTmpValue;
        r18 = r0;
        monitor-enter(r18);
        r8 = new android.graphics.Rect;	 Catch:{ all -> 0x01bd }
        r8.<init>();	 Catch:{ all -> 0x01bd }
        r0 = r23;
        r0 = r0.mFilePath;	 Catch:{ all -> 0x01bd }
        r19 = r0;
        if (r19 == 0) goto L_0x0177;
    L_0x0014:
        r3 = ".xml";
        r0 = r19;
        r3 = r0.endsWith(r3);	 Catch:{ all -> 0x01bd }
        if (r3 != 0) goto L_0x0177;
    L_0x001f:
        r0 = r20;
        r1 = r22;
        r2 = r19;
        r5 = r0.getInputStream(r1, r2);	 Catch:{ IOException -> 0x014f }
        r7 = new android.graphics.BitmapFactory$Options;	 Catch:{ IOException -> 0x014f }
        r7.<init>();	 Catch:{ IOException -> 0x014f }
        r0 = r23;
        r3 = r0.mInDensity;	 Catch:{ IOException -> 0x014f }
        r7.inDensity = r3;	 Catch:{ IOException -> 0x014f }
        r11 = 0;
        if (r19 == 0) goto L_0x005c;
    L_0x0037:
        r3 = ".9.png";
        r0 = r19;
        r3 = r0.endsWith(r3);	 Catch:{ OutOfMemoryError -> 0x006c }
        if (r3 == 0) goto L_0x005c;
    L_0x0042:
        r4 = 0;
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ OutOfMemoryError -> 0x006c }
        r0 = r20;
        r9 = r0.mIsResourcesComplied;	 Catch:{ OutOfMemoryError -> 0x006c }
        r0 = r23;
        r10 = r0.mResourcesID;	 Catch:{ OutOfMemoryError -> 0x006c }
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ OutOfMemoryError -> 0x006c }
    L_0x0055:
        r5.close();	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x0177;
    L_0x005a:
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
    L_0x005b:
        return r3;
    L_0x005c:
        r4 = 0;
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ OutOfMemoryError -> 0x006c }
        r0 = r20;
        r9 = r0.mIsResourcesComplied;	 Catch:{ OutOfMemoryError -> 0x006c }
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ OutOfMemoryError -> 0x006c }
        goto L_0x0055;
    L_0x006c:
        r3 = move-exception;
        r0 = r20;
        r4 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x014f }
        if (r4 == 0) goto L_0x033f;
    L_0x0073:
        r0 = r20;
        r4 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x014f }
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ IOException -> 0x014f }
        r9 = 1;
        r3 = r4.onDecodeOOM(r3, r6, r9);	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x033f;
    L_0x0082:
        r5.close();	 Catch:{ IOException -> 0x014f }
        r0 = r20;
        r1 = r22;
        r2 = r19;
        r5 = r0.getInputStream(r1, r2);	 Catch:{ IOException -> 0x014f }
        r4 = 0;
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ OutOfMemoryError -> 0x009f }
        r0 = r20;
        r9 = r0.mIsResourcesComplied;	 Catch:{ OutOfMemoryError -> 0x009f }
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ OutOfMemoryError -> 0x009f }
        goto L_0x0055;
    L_0x009f:
        r3 = move-exception;
        r0 = r20;
        r4 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x014f }
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ IOException -> 0x014f }
        r9 = 1;
        r4 = r4.onSecondDecodeOOM(r3, r6, r9);	 Catch:{ IOException -> 0x014f }
        if (r4 == 0) goto L_0x014e;
    L_0x00af:
        r3 = 1;
        r7.inJustDecodeBounds = r3;	 Catch:{ IOException -> 0x014f }
        r5.close();	 Catch:{ IOException -> 0x014f }
        r0 = r20;
        r1 = r22;
        r2 = r19;
        r5 = r0.getInputStream(r1, r2);	 Catch:{ IOException -> 0x014f }
        r4 = 0;
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ IOException -> 0x014f }
        r0 = r20;
        r9 = r0.mIsResourcesComplied;	 Catch:{ IOException -> 0x014f }
        r3 = r22;
        r4 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ IOException -> 0x014f }
        r0 = r20;
        r3 = r0.backupForOOMData;	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x0149;
    L_0x00d4:
        r0 = r20;
        r3 = r0.backupForOOMData;	 Catch:{ IOException -> 0x014f }
        r6 = java.lang.Integer.valueOf(r21);	 Catch:{ IOException -> 0x014f }
        r3 = r3.containsKey(r6);	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x0149;
    L_0x00e2:
        r0 = r20;
        r3 = r0.backupForOOMData;	 Catch:{ IOException -> 0x014f }
        r6 = java.lang.Integer.valueOf(r21);	 Catch:{ IOException -> 0x014f }
        r3 = r3.get(r6);	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x0149;
    L_0x00f0:
        r3 = 1;
    L_0x00f1:
        if (r3 == 0) goto L_0x0146;
    L_0x00f3:
        r3 = 0;
        r0 = r20;
        r6 = r0.backupForOOMData;	 Catch:{ IOException -> 0x014f }
        r9 = java.lang.Integer.valueOf(r21);	 Catch:{ IOException -> 0x014f }
        r15 = r6.get(r9);	 Catch:{ IOException -> 0x014f }
        r15 = (com.tencent.theme.SkinEngine.BackupForOOMData) r15;	 Catch:{ IOException -> 0x014f }
        r6 = r7.outWidth;	 Catch:{ IOException -> 0x014f }
        r6 = r6 / 8;
        r9 = r7.outHeight;	 Catch:{ IOException -> 0x014f }
        r9 = r9 / 8;
        r6 = java.lang.Math.min(r6, r9);	 Catch:{ IOException -> 0x014f }
        r9 = 8;
        r6 = java.lang.Math.max(r6, r9);	 Catch:{ IOException -> 0x014f }
        r16 = 2;
    L_0x0116:
        r0 = r16;
        if (r0 > r6) goto L_0x012e;
    L_0x011a:
        r0 = r20;
        r14 = r0.mIsResourcesComplied;	 Catch:{ IOException -> 0x014f }
        r17 = 0;
        r9 = r20;
        r10 = r22;
        r11 = r23;
        r12 = r7;
        r13 = r8;
        r3 = r9.createSmallImageFromResourceStream(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ IOException -> 0x014f }
        if (r3 == 0) goto L_0x014b;
    L_0x012e:
        if (r3 != 0) goto L_0x0144;
    L_0x0130:
        r0 = r20;
        r14 = r0.mIsResourcesComplied;	 Catch:{ IOException -> 0x014f }
        r16 = 1;
        r17 = 1;
        r9 = r20;
        r10 = r22;
        r11 = r23;
        r12 = r7;
        r13 = r8;
        r3 = r9.createSmallImageFromResourceStream(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ IOException -> 0x014f }
    L_0x0144:
        if (r3 != 0) goto L_0x0055;
    L_0x0146:
        r3 = r4;
        goto L_0x0055;
    L_0x0149:
        r3 = 0;
        goto L_0x00f1;
    L_0x014b:
        r16 = r16 * 2;
        goto L_0x0116;
    L_0x014e:
        throw r3;	 Catch:{ IOException -> 0x014f }
    L_0x014f:
        r3 = move-exception;
        r3 = DEBUG;	 Catch:{ all -> 0x01bd }
        if (r3 == 0) goto L_0x0177;
    L_0x0154:
        r3 = "SkinEngine";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bd }
        r4.<init>();	 Catch:{ all -> 0x01bd }
        r5 = "Bitmap File:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01bd }
        r0 = r19;
        r4 = r4.append(r0);	 Catch:{ all -> 0x01bd }
        r5 = " is not a bitmap or error bitmap";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01bd }
        r4 = r4.toString();	 Catch:{ all -> 0x01bd }
        android.util.Log.w(r3, r4);	 Catch:{ all -> 0x01bd }
    L_0x0177:
        r0 = r23;
        r3 = r0.mFileName;	 Catch:{ all -> 0x01bd }
        r4 = ".xml";
        r3 = r3.endsWith(r4);	 Catch:{ all -> 0x01bd }
        if (r3 == 0) goto L_0x020f;
    L_0x0184:
        r0 = r20;
        r3 = r0.mTmpValue;	 Catch:{ Exception -> 0x01d6 }
        r0 = r20;
        r1 = r21;
        r4 = r0.getXml(r1, r3);	 Catch:{ Exception -> 0x01d6 }
        if (r19 == 0) goto L_0x01c5;
    L_0x0192:
        r0 = r20;
        r3 = r0.mIsResourcesComplied;	 Catch:{ Exception -> 0x01d6 }
        if (r3 == 0) goto L_0x01c0;
    L_0x0198:
        r3 = new com.tencent.theme.AndroidXmlResourceParser;	 Catch:{ Exception -> 0x01d6 }
        r3.<init>();	 Catch:{ Exception -> 0x01d6 }
    L_0x019d:
        r5 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x01d6 }
        r0 = r19;
        r5.<init>(r0);	 Catch:{ Exception -> 0x01d6 }
        r6 = "UTF-8";
        r3.setInput(r5, r6);	 Catch:{ Exception -> 0x01d6 }
        r0 = r20;
        r6 = r0.mIsResourcesComplied;	 Catch:{ Exception -> 0x01d6 }
        r0 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromXml(r0, r4, r3, r6);	 Catch:{ Exception -> 0x01d6 }
        r5.close();	 Catch:{ Exception -> 0x01d6 }
        r4.close();	 Catch:{ Exception -> 0x01d6 }
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x01bd:
        r3 = move-exception;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        throw r3;
    L_0x01c0:
        r3 = android.util.Xml.newPullParser();	 Catch:{ Exception -> 0x01d6 }
        goto L_0x019d;
    L_0x01c5:
        r3 = 0;
        r0 = r20;
        r5 = r0.mIsResourcesComplied;	 Catch:{ Exception -> 0x01d6 }
        r0 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromXml(r0, r4, r3, r5);	 Catch:{ Exception -> 0x01d6 }
        r4.close();	 Catch:{ Exception -> 0x01d6 }
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x01d6:
        r3 = move-exception;
        r4 = DEBUG;	 Catch:{ all -> 0x01bd }
        if (r4 == 0) goto L_0x020b;
    L_0x01db:
        r4 = new android.content.res.Resources$NotFoundException;	 Catch:{ all -> 0x01bd }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bd }
        r5.<init>();	 Catch:{ all -> 0x01bd }
        r6 = "File ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01bd }
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ all -> 0x01bd }
        r5 = r5.append(r6);	 Catch:{ all -> 0x01bd }
        r6 = " from drawable resource ID #0x";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01bd }
        r6 = java.lang.Integer.toHexString(r21);	 Catch:{ all -> 0x01bd }
        r5 = r5.append(r6);	 Catch:{ all -> 0x01bd }
        r5 = r5.toString();	 Catch:{ all -> 0x01bd }
        r4.<init>(r5);	 Catch:{ all -> 0x01bd }
        r4.initCause(r3);	 Catch:{ all -> 0x01bd }
        throw r4;	 Catch:{ all -> 0x01bd }
    L_0x020b:
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x020f:
        r3 = IS_PROBLEM_CM11;	 Catch:{ all -> 0x01bd }
        if (r3 == 0) goto L_0x0265;
    L_0x0213:
        r3 = mIconsOfCM;	 Catch:{ Exception -> 0x025b }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x025b }
        r3 = mIconsOfCM;	 Catch:{ Exception -> 0x025b }
        r0 = r20;
        r4 = r0.mResources;	 Catch:{ Exception -> 0x025b }
        r5 = 0;
        r3.set(r4, r5);	 Catch:{ Exception -> 0x025b }
        r3 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x025b }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x025b }
        r3 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x025b }
        r0 = r20;
        r4 = r0.mResources;	 Catch:{ Exception -> 0x025b }
        r5 = 0;
        r3.set(r4, r5);	 Catch:{ Exception -> 0x025b }
        r3 = mIconsOfCM;	 Catch:{ Exception -> 0x0260 }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x0260 }
        r3 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0260 }
        r4 = 1;
        r3.setAccessible(r4);	 Catch:{ Exception -> 0x0260 }
        r3 = mIconsOfCM;	 Catch:{ Exception -> 0x0260 }
        r0 = r20;
        r4 = r0.mResources;	 Catch:{ Exception -> 0x0260 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0260 }
        if (r3 == 0) goto L_0x0265;
    L_0x024b:
        r3 = mComposedIconInfoOfCM;	 Catch:{ Exception -> 0x0260 }
        r0 = r20;
        r4 = r0.mResources;	 Catch:{ Exception -> 0x0260 }
        r3 = r3.get(r4);	 Catch:{ Exception -> 0x0260 }
        if (r3 == 0) goto L_0x0265;
    L_0x0257:
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x025b:
        r3 = move-exception;
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x0260:
        r3 = move-exception;
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x0265:
        r0 = r20;
        r4 = r0.mTmpValue;	 Catch:{ all -> 0x01bd }
        r0 = r22;
        r1 = r21;
        r5 = r0.openRawResource(r1, r4);	 Catch:{ Exception -> 0x029e }
        r3 = r4.string;	 Catch:{ all -> 0x01bd }
        r6 = r3.toString();	 Catch:{ all -> 0x01bd }
        r10 = 0;
        r7 = new android.graphics.BitmapFactory$Options;	 Catch:{ IOException -> 0x02f0 }
        r7.<init>();	 Catch:{ IOException -> 0x02f0 }
        r9 = 1;
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ OutOfMemoryError -> 0x02a3 }
    L_0x0284:
        r5.close();	 Catch:{ IOException -> 0x02f0 }
        if (r3 != 0) goto L_0x029b;
    L_0x0289:
        r0 = r20;
        r4 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x02f0 }
        if (r4 == 0) goto L_0x029b;
    L_0x028f:
        r0 = r20;
        r4 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x02f0 }
        r0 = r23;
        r6 = r0.mFileName;	 Catch:{ IOException -> 0x02f0 }
        r7 = 0;
        r4.onDecodeReturnNullBitmap(r6, r7);	 Catch:{ IOException -> 0x02f0 }
    L_0x029b:
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x029e:
        r3 = move-exception;
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x02a3:
        r3 = move-exception;
        r0 = r20;
        r9 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x02f0 }
        if (r9 == 0) goto L_0x033c;
    L_0x02aa:
        r0 = r20;
        r9 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x02f0 }
        r0 = r23;
        r11 = r0.mFileName;	 Catch:{ IOException -> 0x02f0 }
        r12 = 0;
        r3 = r9.onDecodeOOM(r3, r11, r12);	 Catch:{ IOException -> 0x02f0 }
        if (r3 == 0) goto L_0x033c;
    L_0x02b9:
        r5.close();	 Catch:{ IOException -> 0x02f0 }
        r0 = r22;
        r1 = r21;
        r5 = r0.openRawResource(r1, r4);	 Catch:{ IOException -> 0x02f0 }
        r9 = 1;
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ OutOfMemoryError -> 0x02cc }
        goto L_0x0284;
    L_0x02cc:
        r3 = move-exception;
        r0 = r20;
        r9 = r0.mSkinEngineHandler;	 Catch:{ IOException -> 0x02f0 }
        r0 = r23;
        r10 = r0.mFileName;	 Catch:{ IOException -> 0x02f0 }
        r11 = 0;
        r9 = r9.onSecondDecodeOOM(r3, r10, r11);	 Catch:{ IOException -> 0x02f0 }
        if (r9 == 0) goto L_0x02ef;
    L_0x02dc:
        r3 = 1;
        r7.inJustDecodeBounds = r3;	 Catch:{ IOException -> 0x02f0 }
        r0 = r22;
        r1 = r21;
        r5 = r0.openRawResource(r1, r4);	 Catch:{ IOException -> 0x02f0 }
        r9 = 1;
        r3 = r22;
        r3 = com.tencent.theme.ResourcesFactory.createImageFromResourceStream(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ IOException -> 0x02f0 }
        goto L_0x0284;
    L_0x02ef:
        throw r3;	 Catch:{ IOException -> 0x02f0 }
    L_0x02f0:
        r3 = move-exception;
        r4 = DEBUG;	 Catch:{ all -> 0x01bd }
        if (r4 == 0) goto L_0x0338;
    L_0x02f5:
        r4 = new android.content.res.Resources$NotFoundException;	 Catch:{ all -> 0x01bd }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bd }
        r6.<init>();	 Catch:{ all -> 0x01bd }
        r7 = "File ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01bd }
        r0 = r23;
        r7 = r0.mFileName;	 Catch:{ all -> 0x01bd }
        r6 = r6.append(r7);	 Catch:{ all -> 0x01bd }
        r7 = " from drawable resource ID #0x";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01bd }
        r7 = java.lang.Integer.toHexString(r21);	 Catch:{ all -> 0x01bd }
        r6 = r6.append(r7);	 Catch:{ all -> 0x01bd }
        r7 = ", stream type: ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x01bd }
        r5 = r5.getClass();	 Catch:{ all -> 0x01bd }
        r5 = r5.getName();	 Catch:{ all -> 0x01bd }
        r5 = r6.append(r5);	 Catch:{ all -> 0x01bd }
        r5 = r5.toString();	 Catch:{ all -> 0x01bd }
        r4.<init>(r5);	 Catch:{ all -> 0x01bd }
        r4.initCause(r3);	 Catch:{ all -> 0x01bd }
        throw r4;	 Catch:{ all -> 0x01bd }
    L_0x0338:
        r3 = 0;
        monitor-exit(r18);	 Catch:{ all -> 0x01bd }
        goto L_0x005b;
    L_0x033c:
        r3 = r10;
        goto L_0x0284;
    L_0x033f:
        r3 = r11;
        goto L_0x0055;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.theme.SkinEngine.loadImage(int, android.content.res.Resources, com.tencent.theme.SkinData):com.tencent.theme.BaseConstantState");
    }

    private InputStream getInputStream(Resources resources, String str) throws IOException, FileNotFoundException {
        if (!str.startsWith(ASSET_PATH)) {
            return new FileInputStream(new File(str));
        }
        return resources.getAssets().open(str.substring(ASSET_PATH.length()));
    }

    private XmlResourceParser getXml(int i, TypedValue typedValue) throws IOException {
        this.mResources.getValue(i, typedValue, true);
        if (typedValue.type == 3) {
            return this.mResources.getAssets().openXmlResourceParser(typedValue.assetCookie, typedValue.string.toString());
        }
        throw new NotFoundException("Resource ID #0x" + Integer.toHexString(i) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
    }

    public int getColor(int i) {
        int i2;
        synchronized (this.mTmpValue) {
            TypedValue typedValue = this.mTmpValue;
            this.mResources.getValue(i, typedValue, true);
            if (typedValue.type >= 16 && typedValue.type <= 31) {
                i2 = typedValue.data;
            } else if (typedValue.type == 3) {
                i2 = loadColorStateList(i).getDefaultColor();
            } else {
                throw new NotFoundException("Resource ID #0x" + Integer.toHexString(i) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
            }
        }
        return i2;
    }

    @TargetApi(16)
    SkinnableColorStateList loadColorStateList(int i) {
        SkinnableColorStateList skinnableColorStateList;
        synchronized (this.mTmpValue) {
            WeakReference weakReference = (WeakReference) this.mColorStateListCache.get((long) i);
            if (weakReference != null) {
                skinnableColorStateList = (SkinnableColorStateList) weakReference.get();
                if (skinnableColorStateList != null) {
                } else {
                    this.mColorStateListCache.delete((long) i);
                }
            }
            this.mResources.getValue(i, this.mTmpValue, true);
            String charSequence = this.mTmpValue.string.toString();
            if (charSequence.endsWith(".xml")) {
                SkinData skinData = new SkinData();
                skinData.mResourcesID = i;
                skinData.mFileName = charSequence.substring(charSequence.lastIndexOf("/") + 1);
                if (this.mSkinRootPath != null) {
                    skinData.mFilePath = grabColorFile(this.mResources, skinData.mFileName);
                }
                skinnableColorStateList = loadColor(i, this.mResources, skinData);
                skinnableColorStateList.skinData = skinData;
                this.mColorStateListCache.put((long) i, new WeakReference(skinnableColorStateList));
            } else {
                skinnableColorStateList = null;
            }
        }
        return skinnableColorStateList;
    }

    private SkinnableColorStateList loadColor(int i, Resources resources, SkinData skinData) {
        SkinnableColorStateList createColorStateListFromXmlFile;
        synchronized (this.mTmpValue) {
            String str = skinData.mFilePath;
            if (str != null && str.endsWith(".xml")) {
                try {
                    createColorStateListFromXmlFile = ResourcesFactory.createColorStateListFromXmlFile(this, resources, new File(str), this.mIsResourcesComplied);
                } catch (Throwable e) {
                    r3 = new NotFoundException("File " + str + " from Skin ColorList list resource ID #0x" + Integer.toHexString(i));
                    r3.initCause(e);
                    throw r3;
                } catch (Throwable e2) {
                    if (DEBUG) {
                        NotFoundException notFoundException;
                        notFoundException = new NotFoundException("File " + str + " from Skin ColorState resource ID #0x" + Integer.toHexString(i));
                        notFoundException.initCause(e2);
                        throw notFoundException;
                    }
                }
            }
            str = skinData.mFileName;
            if (str.endsWith(".xml")) {
                Object xml = getXml(i, this.mTmpValue);
                createColorStateListFromXmlFile = SkinnableColorStateList.createFromXml(this, resources, xml, true);
                xml.close();
            } else {
                throw new NotFoundException("File " + str + " from Skin ColorList resource ID #0x" + Integer.toHexString(i) + ": .xml extension required");
            }
        }
        return createColorStateListFromXmlFile;
    }

    public void writeCacheFile(File file) throws IOException {
        this.drawableIntercepter.writeCacheFile(file);
    }

    public static void invalidateAll(View view) {
        if (view != null) {
            int childCount;
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    invalidateAll(viewGroup.getChildAt(i));
                }
            }
            Drawable background = view.getBackground();
            if (background != null) {
                int paddingTop;
                int paddingLeft;
                int paddingRight;
                if (background instanceof DrawableContainer) {
                    Rect rect = new Rect();
                    background.getPadding(rect);
                    if (rect.left == view.getPaddingLeft() && rect.right == view.getPaddingRight() && rect.top == view.getPaddingTop() && rect.bottom == view.getPaddingBottom()) {
                        SkinnableActivityProcesser.updateDrawableContainerPadding(background);
                        view.setBackgroundDrawable(null);
                        view.setBackgroundDrawable(background);
                    } else {
                        paddingTop = view.getPaddingTop();
                        childCount = view.getPaddingBottom();
                        paddingLeft = view.getPaddingLeft();
                        paddingRight = view.getPaddingRight();
                        SkinnableActivityProcesser.updateDrawableContainerPadding(background);
                        view.setBackgroundDrawable(null);
                        view.setBackgroundDrawable(background);
                        view.setPadding(paddingLeft, paddingTop, paddingRight, childCount);
                    }
                } else if (background instanceof SkinnableNinePatchDrawable) {
                    Rect rect2 = new Rect();
                    if (((SkinnableNinePatchDrawable) background).getOldPadding(rect2) && rect2.left == view.getPaddingLeft() && rect2.right == view.getPaddingRight() && rect2.top == view.getPaddingTop() && rect2.bottom == view.getPaddingBottom()) {
                        view.setBackgroundDrawable(null);
                        view.setBackgroundDrawable(background);
                    } else {
                        paddingTop = view.getPaddingTop();
                        childCount = view.getPaddingBottom();
                        paddingLeft = view.getPaddingLeft();
                        paddingRight = view.getPaddingRight();
                        view.setBackgroundDrawable(null);
                        view.setBackgroundDrawable(background);
                        view.setPadding(paddingLeft, paddingTop, paddingRight, childCount);
                    }
                } else if (background instanceof SkinnableBitmapDrawable) {
                    view.setBackgroundDrawable(null);
                    view.setBackgroundDrawable(background);
                }
            }
            if (view instanceof SkinnableView) {
                ((SkinnableView) view).onThemeChanged();
            }
            if (view instanceof ImageView) {
                background = ((ImageView) view).getDrawable();
                SkinnableActivityProcesser.updateDrawableContainerPadding(background);
                ((ImageView) view).setImageDrawable(null);
                ((ImageView) view).setImageDrawable(background);
            } else if (view instanceof TextView) {
                Drawable[] compoundDrawables = ((TextView) view).getCompoundDrawables();
                if (!(compoundDrawables[0] == null && compoundDrawables[1] == null && compoundDrawables[2] == null && compoundDrawables[3] == null)) {
                    ((TextView) view).setCompoundDrawables(null, null, null, null);
                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
                }
            }
            view.destroyDrawingCache();
            view.refreshDrawableState();
            view.invalidate();
            view.requestLayout();
        }
    }

    public void addBackupForOOMData(Integer num, BackupForOOMData backupForOOMData) {
        if (this.backupForOOMData == null) {
            this.backupForOOMData = new HashMap();
        }
        this.backupForOOMData.put(num, backupForOOMData);
    }

    BaseConstantState createSmallImageFromResourceStream(Resources resources, SkinData skinData, Options options, Rect rect, boolean z, BackupForOOMData backupForOOMData, int i, boolean z2) {
        BaseConstantState createImageFromResourceStream;
        InputStream inputStream;
        if (z2) {
            try {
                TypedValue typedValue = new TypedValue();
                this.mResources.getValue(backupForOOMData.backFileResId, typedValue, true);
                String charSequence = typedValue.string.toString();
                SkinData skinData2 = new SkinData();
                skinData2.mResourcesID = backupForOOMData.backFileResId;
                skinData2.mFileName = charSequence.substring(charSequence.lastIndexOf("/") + 1);
                if (this.mSkinRootPath != null) {
                    grabImageFile(this.mResources, typedValue, skinData2.mFileName, DENSITY_PATH_ORDER[this.mDensityIndex], DENSITY_ORDER[this.mDensityIndex]);
                    if (typedValue.string != null) {
                        skinData2.mFilePath = typedValue.string.toString();
                        skinData2.mInDensity = typedValue.density;
                    }
                }
                if (skinData2.mFilePath != null) {
                    Rect rect2 = new Rect();
                    Options options2 = new Options();
                    options2.inDensity = skinData2.mInDensity;
                    options2.inPreferredConfig = backupForOOMData.inPreferredConfig;
                    inputStream = getInputStream(resources, skinData2.mFilePath);
                    try {
                        createImageFromResourceStream = ResourcesFactory.createImageFromResourceStream(resources, null, inputStream, skinData2.mFileName, options2, rect2, this.mIsResourcesComplied);
                    } catch (OutOfMemoryError e) {
                        if (DEBUG) {
                            Log.w(TAG, "backup Bitmap File:" + skinData2.mFilePath + " is OOM");
                        }
                        createImageFromResourceStream = null;
                    }
                    try {
                        inputStream.close();
                        return createImageFromResourceStream;
                    } catch (IOException e2) {
                    }
                } else {
                    try {
                        InputStream openRawResource = resources.openRawResource(backupForOOMData.backFileResId, typedValue);
                        try {
                            try {
                                createImageFromResourceStream = ResourcesFactory.createImageFromResourceStream(resources, typedValue, openRawResource, typedValue.string.toString(), new Options(), rect, true);
                            } catch (OutOfMemoryError e3) {
                                createImageFromResourceStream = null;
                            }
                            try {
                                openRawResource.close();
                                return createImageFromResourceStream;
                            } catch (IOException e4) {
                                if (DEBUG) {
                                    return createImageFromResourceStream;
                                }
                                Log.w(TAG, "BACK Bitmap File:  is OOM");
                                return createImageFromResourceStream;
                            }
                        } catch (IOException e5) {
                            createImageFromResourceStream = null;
                            if (DEBUG) {
                                return createImageFromResourceStream;
                            }
                            Log.w(TAG, "BACK Bitmap File:  is OOM");
                            return createImageFromResourceStream;
                        }
                    } catch (Exception e6) {
                        return null;
                    }
                }
            } catch (IOException e7) {
                createImageFromResourceStream = null;
                if (!DEBUG) {
                    return createImageFromResourceStream;
                }
                Log.w(TAG, "createSmallImageFromResourceStream: io error");
                return createImageFromResourceStream;
            }
        }
        inputStream = getInputStream(resources, skinData.mFilePath);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = backupForOOMData.inPreferredConfig;
        options.inSampleSize = i;
        try {
            createImageFromResourceStream = ResourcesFactory.createImageFromResourceStream(resources, null, inputStream, skinData.mFileName, options, rect, this.mIsResourcesComplied);
        } catch (OutOfMemoryError e8) {
            if (DEBUG) {
                Log.w(TAG, "small Bitmap File:" + skinData.mFilePath + " is OOM");
            }
            createImageFromResourceStream = null;
        }
        inputStream.close();
        return createImageFromResourceStream;
    }

    public String[] getDensityPathArray() {
        int densityIndex = getDensityIndex(ReaderApplication.getApplicationImp().getResources());
        if (densityIndex < DENSITY_PATH_ORDER.length) {
            return DENSITY_PATH_ORDER[densityIndex];
        }
        return null;
    }

    public int[] getStandardDensityArray() {
        int densityIndex = getDensityIndex(ReaderApplication.getApplicationImp().getResources());
        if (densityIndex < DENSITY_ORDER.length) {
            return DENSITY_ORDER[densityIndex];
        }
        return null;
    }
}
