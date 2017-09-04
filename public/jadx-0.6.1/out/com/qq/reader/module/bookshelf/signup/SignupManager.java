package com.qq.reader.module.bookshelf.signup;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetSignInfoTask;
import com.qq.reader.common.readertask.protocol.GiftQueryTask;
import com.qq.reader.common.readertask.protocol.LuckyDrawTask;
import com.qq.reader.common.readertask.protocol.SignUpTask;
import com.qq.reader.common.utils.ab;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SignupManager {
    public static SignupManager a;
    private SignInfo b = null;
    private b c = null;
    private ArrayList<a> d = null;
    private boolean e = true;

    public interface b {
        void onSignUpError(int i, int i2);

        void onSignUpReturned(int i, Object obj);
    }

    public static class DeductionExtInfo implements Serializable {
        public String bookType = "";
        public String deductionType = "";
        public String imageUrl = "";
        public String intro = "";
        public String qurl;
        public long typeValue;
    }

    public static class SignInfo implements Serializable {
        public boolean mAlreadySigned = false;
        public int mContinuedSignDay;
        private int mCurrentSignDay;
        public ArrayList<SignItem> mItems = new ArrayList();
        public long mTimestamp = 0;
        public int mTotalSupplyCount = -1;

        public int getCurrentSignDay() {
            int i = this.mCurrentSignDay;
            if (i > 7) {
                i -= 7;
            }
            return Math.min(i, this.mItems.size());
        }

        public List<SignItem> getMissDaysTillNow(int i) {
            if (this.mItems.size() == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < i - 1 && i2 < this.mItems.size()) {
                if (((SignItem) this.mItems.get(i2)).mSignedType == 0) {
                    arrayList.add(this.mItems.get(i2));
                }
                i2++;
            }
            return arrayList;
        }

        public boolean hasTryLuckdraw() {
            return false;
        }

        public boolean isCheckInToday() {
            try {
                if (((SignItem) this.mItems.get(getCurrentSignDay() - 1)).mSignedType != 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        public SignItem getNextDaySignItem() {
            int i;
            int currentSignDay = getCurrentSignDay() + 1;
            if (currentSignDay > 7) {
                i = currentSignDay - 7;
            } else {
                i = currentSignDay;
            }
            Iterator it = this.mItems.iterator();
            while (it.hasNext()) {
                SignItem signItem = (SignItem) it.next();
                if (signItem.mDay == i) {
                    return signItem;
                }
            }
            return null;
        }

        public SignItem getTodaySignItem() {
            int currentSignDay = getCurrentSignDay();
            Iterator it = this.mItems.iterator();
            while (it.hasNext()) {
                SignItem signItem = (SignItem) it.next();
                if (currentSignDay == signItem.mDay) {
                    return signItem;
                }
            }
            return null;
        }

        public String toString() {
            return "SignInfo{mItems=" + this.mItems + '}';
        }
    }

    public static class SignItem implements Serializable {
        public int mBookid;
        public int mCount;
        public int mDay;
        public String mExtInfo;
        public int mItemId;
        public int mItemType;
        public boolean mNeedAddress;
        public String mPrize;
        public int mSignedType;
    }

    public static class a {
        public int a;
        public int b;
        public String c;
        public String d;
        public int e;
        public String f;

        public String a() {
            return this.c;
        }
    }

    public static class c {
        public ArrayList<SignItem> a = new ArrayList();
        public int[] b;
    }

    public static SignupManager a() {
        if (a == null) {
            a = new SignupManager();
        }
        return a;
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public ArrayList<a> b() {
        if (this.d == null || this.d.size() < 6) {
            return null;
        }
        return this.d;
    }

    public void c() {
        f.d("SignupManager", "start clear");
        this.e = false;
        this.b = null;
    }

    public String a(ArrayList<a> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuilder.append(((a) it.next()).a());
                stringBuilder.append("/");
            }
            if (stringBuilder.toString().endsWith("/")) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        return stringBuilder.toString();
    }

    public void a(int i) {
        if (i != Calendar.getInstance().get(5) || g() == null || !this.e) {
            g.a().a(new GetSignInfoTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ SignupManager a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        f.a("SignupManager--getSignupInfo", jSONObject.toString());
                        int optInt = jSONObject.optInt("code");
                        if (optInt == 0) {
                            JSONArray optJSONArray = jSONObject.optJSONArray("weekinfo");
                            this.a.b = new SignInfo();
                            this.a.b.mContinuedSignDay = jSONObject.optInt("continuedays");
                            this.a.b.mAlreadySigned = jSONObject.optBoolean("hasSignedToday");
                            if (com.qq.reader.common.login.c.b()) {
                                this.a.b.mCurrentSignDay = jSONObject.optInt("weekday");
                            } else {
                                this.a.b.mCurrentSignDay = jSONObject.optInt("weekday") + 7;
                            }
                            this.a.b.mTimestamp = System.currentTimeMillis();
                            this.a.b.mTotalSupplyCount = jSONObject.optInt("totalSupplyCost");
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                                SignItem signItem = new SignItem();
                                signItem.mCount = jSONObject2.optInt("pcount", 0);
                                signItem.mSignedType = jSONObject2.optInt("signed", 0);
                                signItem.mPrize = jSONObject2.optString("prizeName");
                                signItem.mExtInfo = jSONObject2.optString("extinfo");
                                signItem.mItemId = jSONObject2.optInt("itemId");
                                signItem.mDay = i + 1;
                                this.a.b.mItems.add(signItem);
                            }
                            if (this.a.c != null) {
                                this.a.c.onSignUpReturned(0, this.a.b);
                            }
                            this.a.f();
                            this.a.e = true;
                            return;
                        }
                        if (this.a.c != null) {
                            this.a.c.onSignUpError(0, optInt);
                            com.qq.reader.common.monitor.debug.c.e("CHECK_IN", "mListener error code = " + optInt);
                        }
                        this.a.e = false;
                    } catch (Exception e) {
                        if (this.a.c != null) {
                            this.a.c.onSignUpError(0, -1);
                        }
                        this.a.e = false;
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.e = false;
                }
            }));
        } else if (this.c != null) {
            this.c.onSignUpReturned(0, g());
        }
    }

    public void a(final int[] iArr, final int i) {
        g.a().a(new SignUpTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SignupManager c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                int i = 0;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    f.a("SignupManager--signup", jSONObject.toString());
                    if (jSONObject.optBoolean("isLogin", true)) {
                        int optInt = jSONObject.optInt("code");
                        if (optInt == 0 || optInt == 1) {
                            c cVar = new c();
                            JSONArray optJSONArray = jSONObject.optJSONArray("plist");
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("days");
                            if (optJSONArray != null) {
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                                    SignItem signItem = new SignItem();
                                    signItem.mCount = jSONObject2.optInt("pcount", 0);
                                    signItem.mPrize = jSONObject2.optString("prizeName");
                                    signItem.mBookid = jSONObject2.optInt("innerid", 0);
                                    signItem.mItemId = jSONObject2.optInt("itemId");
                                    signItem.mItemType = i;
                                    signItem.mExtInfo = jSONObject2.optString("extinfo");
                                    if (i2 < iArr.length) {
                                        signItem.mDay = iArr[i2];
                                    } else {
                                        signItem.mDay = 0;
                                    }
                                    cVar.a.add(signItem);
                                }
                            }
                            if (optJSONArray2 != null) {
                                cVar.b = new int[optJSONArray2.length()];
                                while (i < optJSONArray2.length()) {
                                    cVar.b[i] = optJSONArray2.getInt(i);
                                    i++;
                                }
                            }
                            if (this.c.c != null) {
                                this.c.c.onSignUpReturned(1, cVar);
                            }
                        } else if (this.c.c == null) {
                        } else {
                            if (i == 1) {
                                this.c.c.onSignUpError(4, optInt);
                            } else {
                                this.c.c.onSignUpError(1, optInt);
                            }
                        }
                    } else if (this.c.c != null) {
                        this.c.c.onSignUpError(6, 0);
                    }
                } catch (Exception e) {
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.c.c != null) {
                    this.c.c.onSignUpError(1, -1);
                }
            }
        }, iArr, i));
    }

    public void d() {
        g.a().a(new GiftQueryTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SignupManager a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    f.a("SignupManager--getlottery", jSONObject.toString());
                    if (jSONObject.optInt("code") == 0) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("gifts");
                        this.a.d = new ArrayList();
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                a aVar = new a();
                                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                                aVar.a = jSONObject2.optInt("id");
                                aVar.b = jSONObject2.optInt("itemId");
                                aVar.c = jSONObject2.optString("name");
                                aVar.d = jSONObject2.optString("imgUrl");
                                aVar.e = jSONObject2.optInt("order");
                                aVar.f = jSONObject2.getString("extinfo");
                                this.a.d.add(aVar);
                            }
                        }
                        if (this.a.d == null || this.a.d.size() != 6) {
                            if (this.a.c != null) {
                                this.a.c.onSignUpError(3, -1);
                            }
                        } else if (this.a.c != null) {
                            this.a.c.onSignUpReturned(3, this.a.d);
                        }
                    } else if (this.a.c != null) {
                        this.a.c.onSignUpError(3, -1);
                    }
                } catch (Exception e) {
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.c != null) {
                    this.a.c.onSignUpError(3, -1);
                }
            }
        }));
    }

    public void e() {
        g.a().a(new LuckyDrawTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SignupManager a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    f.d("SignupManager", jSONObject.toString());
                    int optInt = jSONObject.optInt("code");
                    if (optInt == 0) {
                        SignItem signItem = new SignItem();
                        signItem.mNeedAddress = jSONObject.optBoolean("needaddress");
                        signItem.mPrize = jSONObject.optString("prizeName");
                        signItem.mCount = jSONObject.optInt("pcount");
                        signItem.mItemId = jSONObject.optInt("itemId");
                        signItem.mExtInfo = jSONObject.optString("extinfo");
                        if (this.a.c != null) {
                            this.a.c.onSignUpReturned(2, signItem);
                            return;
                        }
                        return;
                    }
                    this.a.c.onSignUpError(2, optInt);
                } catch (Exception e) {
                    this.a.c.onSignUpError(2, -1);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.c.onSignUpError(2, -1);
            }
        }));
    }

    public void a(String str, String str2) {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SignupManager a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (this.a.c != null) {
                    this.a.c.onSignUpReturned(5, null);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.c != null) {
                    this.a.c.onSignUpError(5, -1);
                }
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(e.a + "checkinaddress?").append("phoneNo=").append(str2).append("&qq=").append(str);
        readerProtocolJSONTask.setUrl(stringBuffer.toString());
        g.a().a(readerProtocolJSONTask);
    }

    public void f() {
        if (com.qq.reader.common.login.c.b() && this.b != null) {
            f.d("SignupManager", "file path:" + com.qq.reader.common.c.a.bQ);
            ab.a(com.qq.reader.common.c.a.bQ, this.b);
        }
    }

    public SignInfo g() {
        if (this.b != null) {
            return this.b;
        }
        if (com.qq.reader.common.login.c.b()) {
            f.a("SignupManager", "now using disk cache data");
            this.b = (SignInfo) ab.d(com.qq.reader.common.c.a.bQ);
        }
        return this.b;
    }

    public int b(int i) {
        if (i == 102) {
            return R.drawable.sign_grid_ticket;
        }
        if (i == 5) {
            return R.drawable.sign_grid_exp;
        }
        if (i == 103) {
            return R.drawable.sign_grid_unlock;
        }
        if (i == 104) {
            return R.drawable.sign_free_grid_book;
        }
        if (i == 105) {
            return R.drawable.sign_deduction_ticket;
        }
        return R.drawable.gift_32_b;
    }

    public String a(Context context, String str, String str2, String str3) {
        String str4 = "";
        if ("1".equals(str) && "1".equals(str2)) {
            if (TextUtils.isEmpty(str3)) {
                return context.getString(R.string.sign_deduction_classes_book);
            }
            return "《" + str3 + "》";
        } else if ("1".equals(str) && "2".equals(str2)) {
            return String.format(context.getString(R.string.sign_deduction_book_author), new Object[]{str3});
        } else if ("1".equals(str) && "3".equals(str2)) {
            return String.format(context.getString(R.string.sign_deduction_book_classes), new Object[]{str3});
        } else if ("2".equals(str) && "1".equals(str2)) {
            if (TextUtils.isEmpty(str3)) {
                return context.getString(R.string.sign_deduction_classes_comic);
            }
            return "《" + str3 + "》";
        } else if ("2".equals(str) && "3".equals(str2)) {
            return context.getString(R.string.sign_deduction_comic_allzone);
        } else {
            if ("2".equals(str) && "4".equals(str2)) {
                return String.format(context.getString(R.string.sign_deduction_comic_classes), new Object[]{str3});
            } else if ("3".equals(str) && "3".equals(str2)) {
                return context.getString(R.string.sign_deduction_listen_allzone);
            } else {
                return context.getString(R.string.sign_deduction_default_intro);
            }
        }
    }

    public String a(Context context, SignItem signItem) {
        String str = "";
        if (signItem == null) {
            return str;
        }
        if (signItem.mItemId != 105) {
            return signItem.mPrize;
        }
        try {
            JSONObject jSONObject = new JSONObject(signItem.mExtInfo);
            if (jSONObject == null) {
                return signItem.mPrize;
            }
            str = jSONObject.optString("bookType");
            if ("1".equals(str)) {
                return context.getString(R.string.sign_deduction_classes_book);
            }
            if ("2".equals(str)) {
                return context.getString(R.string.sign_deduction_classes_comic);
            }
            return context.getString(R.string.sign_deduction_classes_listen);
        } catch (Exception e) {
            return signItem.mPrize;
        }
    }
}
