package com.qq.reader.module.bookstore.qnative.card;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.b;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.item.ac;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseCard */
public abstract class a implements com.qq.reader.module.bookstore.qnative.a, b {
    public static int ADAPTER_VIEW_TYPE_COUNT = -1;
    public static final int DATA_NONE = 1000;
    public static final int DATA_READY = 1001;
    protected static final String JSON_KEY_EXPIREDTIME = "expireTime";
    public static int MESSAGETYPE_NOMORE_BOTTOM = -1;
    public static int MESSAGETYPE_NOMORE_TOP = -1;
    public static int MESSAGETYPE_ROOKIE_CARD = -1;
    private int[] adminIconID = new int[]{R.drawable.admin_1, R.drawable.admin_2};
    private com.qq.reader.module.bookstore.qnative.page.b bindPage;
    public boolean hasattach = false;
    public int hashcode = 0;
    private int[] iconId = com.qq.reader.common.c.a.dh;
    private boolean mBidsFixFirstTime = false;
    protected String mCardId = getClass().getSimpleName();
    private int mCategoryNameType;
    protected String mConfigTitle = "";
    private long mCurrentPageIndex = -1;
    protected int mDataState = 1000;
    protected int mDispaly = 1;
    private long mExpiredTime = -1;
    public long mFromBid = 0;
    protected int mIconIndex = 0;
    private List<s> mItemList = new ArrayList();
    protected String mLastCardName = "";
    private int mLastRandomEndPos = -1;
    private WeakReference<com.qq.reader.module.bookstore.qnative.c.a> mListener;
    protected ac mMoreAction = null;
    protected Object mOrginCardJsonOjb = null;
    private int mPageCacheKey = 0;
    protected String mPara = "";
    private int mPosition;
    private int mPositionInCurrentPage = -1;
    protected String mPromotionName = "";
    protected String mServerTitle = "";
    protected int mSex = 0;
    protected int mShowIndexOnPage = -1;
    protected String mShowTitle = "";
    protected String mType = "";
    protected float mUILevel;
    protected String mValue = "";
    private int[] myzLevelIconID = new int[]{R.drawable.myz_icon_level_1, R.drawable.myz_icon_level_2, R.drawable.myz_icon_level_3};
    private WeakReference<View> weakRootView = null;

    public abstract void attachView();

    public abstract int getResLayoutId();

    protected abstract boolean parseData(JSONObject jSONObject) throws Exception;

    public int getCategoryType() {
        return this.mCategoryNameType;
    }

    public void setBidFixFirstTime(boolean z) {
        this.mBidsFixFirstTime = z;
    }

    public boolean getBidFixFirstTime() {
        return this.mBidsFixFirstTime;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setPositionInCurrentPage(int i) {
        this.mPositionInCurrentPage = i;
    }

    public int getPositionInCurrentPage() {
        return this.mPositionInCurrentPage;
    }

    public void setCurrentPageIndex(long j) {
        this.mCurrentPageIndex = j;
    }

    public long getCurrentPageIndex() {
        return this.mCurrentPageIndex;
    }

    public a(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        this.bindPage = bVar;
        if (str != null) {
            this.mType = str;
        }
        c.a("card", "Construct Card " + getClass().getSimpleName());
    }

    public com.qq.reader.module.bookstore.qnative.page.b getBindPage() {
        return this.bindPage;
    }

    public View getRootView() {
        if (this.weakRootView == null) {
            return null;
        }
        return (View) this.weakRootView.get();
    }

    public int getMYZLevelIconId(int i) {
        if (i < 0) {
            return 0;
        }
        return i < this.myzLevelIconID.length ? this.myzLevelIconID[i] : this.myzLevelIconID[this.myzLevelIconID.length - 1];
    }

    public int getFanLevelIconId(int i) {
        if (i < 0) {
            return this.iconId[0];
        }
        return i < this.iconId.length ? this.iconId[i] : this.iconId[this.iconId.length - 1];
    }

    public int getAdminIconId(int i) {
        if (i < 0) {
            return this.adminIconID[0];
        }
        return i < this.adminIconID.length ? this.adminIconID[i] : this.adminIconID[this.adminIconID.length - 1];
    }

    public String getType() {
        return this.mType;
    }

    public void setPageCacheKey(int i) {
        this.mPageCacheKey = i;
    }

    public int getPageCacheKey() {
        return this.mPageCacheKey;
    }

    public int getSex() {
        return this.mSex;
    }

    public String getValue() {
        return this.mValue;
    }

    public String getCardId() {
        return this.mCardId;
    }

    protected void addItem(s sVar) {
        this.mItemList.add(sVar);
    }

    public List<s> getItemList() {
        return this.mItemList;
    }

    public boolean selfPrepareData() {
        return false;
    }

    public boolean isDataReady() {
        return this.mDataState == 1001;
    }

    public void setInvalidData() {
        this.mDataState = 1000;
    }

    public String getUri() {
        return this.mCardId + "-" + this.mPageCacheKey;
    }

    public void setIndexOnPage(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mShowIndexOnPage = i;
    }

    public void setLastCardName(String str) {
        this.mLastCardName = str;
    }

    public void build(JSONObject jSONObject) {
        boolean z = false;
        String optString = jSONObject.optString("cid");
        if (optString == null || optString.length() <= 0) {
            this.mCardId = "";
        } else {
            this.mCardId = optString;
        }
        this.mType = jSONObject.optString("type");
        this.mPara = jSONObject.optString("para");
        this.mConfigTitle = jSONObject.optString("title");
        this.mDispaly = jSONObject.optInt("display");
        this.mSex = jSONObject.optInt("sex");
        this.mValue = jSONObject.optString("value");
        this.mIconIndex = jSONObject.optInt(MessageKey.MSG_ICON);
        this.mCategoryNameType = jSONObject.optInt("catetype");
        if (jSONObject.optInt("fix_bids_first_time", 0) > 0) {
            z = true;
        }
        this.mBidsFixFirstTime = z;
        JSONObject optJSONObject = jSONObject.optJSONObject("more");
        if (optJSONObject != null) {
            this.mMoreAction = new ac(this.mCardId);
            this.mMoreAction.parseData(optJSONObject);
        }
    }

    public void setCardId(String str) {
        this.mCardId = str;
    }

    public final boolean fillData(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean parseData;
        this.mOrginCardJsonOjb = obj;
        try {
            if (obj instanceof JSONObject) {
                obj = (JSONObject) obj;
                setExpiredTime(obj.optLong(JSON_KEY_EXPIREDTIME) * 1000);
            } else {
                JSONArray jSONArray = (JSONArray) obj;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("data", jSONArray);
                JSONObject jSONObject2 = jSONObject;
            }
            parseData = parseData(obj);
            if (parseData) {
                this.mDataState = 1001;
            }
        } catch (Exception e) {
            f.d("native", "fillData Error " + e);
            e.printStackTrace();
            parseData = false;
        }
        return parseData;
    }

    public View inflateView(Context context) {
        return inflateView(context, null);
    }

    public View inflateView(Context context, ViewGroup viewGroup) {
        View inflate;
        if (context == null) {
            context = ReaderApplication.getApplicationImp();
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (viewGroup == null || !isInflateViewWithParent()) {
            inflate = layoutInflater.inflate(getResLayoutId(), null);
        } else {
            inflate = layoutInflater.inflate(getResLayoutId(), viewGroup, false);
        }
        try {
            if (com.qq.reader.appconfig.b.a && com.qq.reader.appconfig.b.m) {
                inflate.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ a b;

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            af.a(ReaderApplication.getApplicationImp(), this.getClass().getSimpleName() + "\n " + ReaderApplication.getApplicationImp().getResources().getResourceEntryName(this.b.getResLayoutId()), 0).a();
                        }
                        return false;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    protected boolean isInflateViewWithParent() {
        return false;
    }

    public void attachView(View view) {
        this.hasattach = true;
        this.hashcode = hashCode();
        this.weakRootView = new WeakReference(view);
        c.a("attach", "attachView  " + view + "  " + this.weakRootView);
        String str = (this.mServerTitle == null || this.mServerTitle.length() <= 0) ? this.mConfigTitle : this.mServerTitle;
        this.mShowTitle = str;
        View a = ap.a(view, R.id.localstore_adv_divider);
        if (a != null) {
            if (this.mShowIndexOnPage == 0 || this.mLastCardName.equals("AdvCard_Circle") || this.mLastCardName.equals("ColumnCard_0") || this.mLastCardName.equals("LimitTimeDiscountBuyCountDownCard")) {
                a.setVisibility(8);
            } else {
                a.setVisibility(0);
            }
        }
        attachView();
    }

    public void refresh() {
    }

    public void serialize(OutputStream outputStream) {
        if (this.mOrginCardJsonOjb != null) {
            try {
                outputStream.write(this.mOrginCardJsonOjb.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public Object getOrginCardJsonOjb() {
        return this.mOrginCardJsonOjb;
    }

    public boolean isNeedCacheOnDisk() {
        return true;
    }

    public void setExpiredTime(long j) {
        long currentTimeMillis = System.currentTimeMillis() + 172800000;
        if (this.mExpiredTime > currentTimeMillis) {
            this.mExpiredTime = currentTimeMillis;
            return;
        }
        if (j <= 0) {
            j = BuglyBroadcastRecevier.UPLOADLIMITED;
        }
        this.mExpiredTime = j;
    }

    public boolean isExpired() {
        if (this.mExpiredTime > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public com.qq.reader.module.bookstore.qnative.c.a getEvnetListener() {
        return (com.qq.reader.module.bookstore.qnative.c.a) this.mListener.get();
    }

    public void setEventListener(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.mListener = new WeakReference(aVar);
    }

    protected int[] getRandomListIndex(int i, int i2, boolean z) {
        int i3 = 0;
        if (i >= i2) {
            int[] iArr = new int[i2];
            while (i3 < i2) {
                iArr[i3] = i3;
                i3++;
            }
            return iArr;
        }
        int nextInt;
        int[] iArr2 = new int[i];
        Random random = new Random();
        if (z) {
            if (this.mLastRandomEndPos == -1) {
                nextInt = random.nextInt(i2);
            } else if (this.mLastRandomEndPos >= i2 - 1) {
                nextInt = 0;
            } else {
                nextInt = 0;
            }
        } else if (this.mLastRandomEndPos == -1) {
            nextInt = random.nextInt(i2);
        } else if (this.mLastRandomEndPos >= i2 - 1) {
            nextInt = 0;
        } else {
            nextInt = this.mLastRandomEndPos + 1;
        }
        int i4 = i2 - nextInt;
        int i5 = 0;
        while (i5 < i4 && i5 < i) {
            iArr2[i5] = nextInt + i5;
            i5++;
        }
        nextInt = i5;
        while (nextInt < i) {
            iArr2[nextInt] = i3;
            nextInt++;
            i3++;
        }
        this.mLastRandomEndPos = iArr2[i - 1];
        return iArr2;
    }

    public boolean isLogin() {
        return com.qq.reader.common.login.c.b();
    }

    public com.qq.reader.common.login.b.a getLoginUser() {
        return com.qq.reader.common.login.c.c();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        try {
            if (obj instanceof a) {
                z = this.mCardId.equals(((a) obj).getCardId());
            }
        } catch (Exception e) {
            f.a("native", e.toString());
        }
        return z;
    }

    public boolean isAddAble() {
        return false;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        return false;
    }

    protected void ShowSpannableString(TextView textView, String str, ArrayList<Drawable> arrayList) {
        textView.setVisibility(0);
        if (arrayList.size() > 0) {
            int[] iArr = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = 0;
            }
            textView.setText(com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), (CharSequence) str, textView.getTextSize(), (ArrayList) arrayList, iArr));
            return;
        }
        textView.setText(str);
    }

    protected void setAvatarImage(ImageView imageView, String str, String str2, OnClickListener onClickListener) {
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, imageView, com.qq.reader.common.imageloader.a.a().a(str2));
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    protected void setImage(ImageView imageView, String str, OnClickListener onClickListener) {
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, imageView, com.qq.reader.common.imageloader.a.a().j());
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public float getUILevel() {
        return this.mUILevel;
    }

    public void setUILevel(float f) {
        this.mUILevel = f;
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        return false;
    }

    public final void doReSave() throws JSONException {
        JSONObject jSONObject;
        Object orginCardJsonOjb = getOrginCardJsonOjb();
        if (orginCardJsonOjb instanceof JSONObject) {
            jSONObject = (JSONObject) orginCardJsonOjb;
        } else if (orginCardJsonOjb instanceof JSONArray) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", orginCardJsonOjb);
            jSONObject = jSONObject2;
        } else {
            return;
        }
        if (reSaveDataBuild(jSONObject)) {
            reSaveData();
        }
    }

    private void reSaveData() {
        Throwable th;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        if (d.b().a(getUri()) != null) {
            try {
                if (isNeedCacheOnDisk() && isDataReady()) {
                    ByteArrayInputStream byteArrayInputStream;
                    try {
                        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            serialize(byteArrayOutputStream2);
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                            try {
                                d.b().a(getUri(), byteArrayInputStream, null);
                                if (byteArrayInputStream != null) {
                                    byteArrayInputStream.close();
                                }
                                if (byteArrayOutputStream2 != null) {
                                    byteArrayOutputStream2.close();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                outputStream = byteArrayOutputStream2;
                                if (byteArrayInputStream != null) {
                                    byteArrayInputStream.close();
                                }
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayInputStream = null;
                            outputStream = byteArrayOutputStream2;
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        byteArrayInputStream = null;
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.bindPage != null) {
            this.bindPage.v();
        }
    }

    public void onCardShouldDestroy() {
    }
}
