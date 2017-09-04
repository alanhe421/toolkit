package com.tencent.midas.data;

public class APPluginDataInterface {
    private static APPluginDataInterface a = new APPluginDataInterface();
    private String A = "";
    private String B = "";
    private String C = "";
    private String D = "";
    private String E = "";
    private int F = 0;
    private String G = "";
    private boolean H = true;
    private String I = "";
    private String J = "";
    private String K = "";
    private int L = 0;
    private String M = "";
    private String N = "";
    private int O = 0;
    private String P = "";
    private String Q = APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHPAY;
    private long R = 0;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";
    private boolean l = true;
    private int m = 0;
    private byte[] n = null;
    private String o = "";
    private String p = "";
    private int q = 0;
    private String r = "";
    private boolean s = true;
    private boolean t = true;
    private String u = "";
    private String v = "";
    private boolean w = true;
    private boolean x = true;
    private String y = "";
    private String z = "";

    private APPluginDataInterface() {
    }

    public static void init() {
        a = new APPluginDataInterface();
    }

    public static APPluginDataInterface singleton() {
        return a;
    }

    public String getAcctType() {
        return this.o;
    }

    public String getDiscountType() {
        return this.z;
    }

    public String getDiscountUrl() {
        return this.A;
    }

    public String getDiscoutId() {
        return this.C;
    }

    public String getDrmInfo() {
        return this.B;
    }

    public String getExtras() {
        return this.D;
    }

    public String getGoodsTokenUrl() {
        return this.E;
    }

    public String getGuid() {
        return this.P;
    }

    public String getH5Message() {
        return this.b;
    }

    public String getH5Url() {
        return this.r;
    }

    public String getLaunchInterface() {
        return this.Q;
    }

    public int getMallType() {
        return this.q;
    }

    public String getOfferId() {
        return this.c;
    }

    public String getOpenId() {
        return this.d;
    }

    public String getOpenKey() {
        return this.e;
    }

    public String getPayChannel() {
        return this.y;
    }

    public long getPayInterfaceTime() {
        return this.R;
    }

    public String getPf() {
        return this.i;
    }

    public String getPfKey() {
        return this.j;
    }

    public String getProdcutId() {
        return this.G;
    }

    public String getPropUnit() {
        return this.u;
    }

    public String getRemark() {
        return this.I;
    }

    public String getReqType() {
        return this.N;
    }

    public byte[] getResData() {
        return this.n;
    }

    public int getResId() {
        return this.m;
    }

    public String getResUrl() {
        return this.M;
    }

    public String getReserv() {
        return this.p;
    }

    public int getSaveType() {
        return this.O;
    }

    public String getSaveValue() {
        return this.k;
    }

    public String getServiceCode() {
        return this.J;
    }

    public String getServiceName() {
        return this.K;
    }

    public int getServiceType() {
        return this.L;
    }

    public String getSessionId() {
        return this.f;
    }

    public String getSessionType() {
        return this.g;
    }

    public int getTokenType() {
        return this.F;
    }

    public String getUnit() {
        return this.v;
    }

    public String getZoneId() {
        return this.h;
    }

    public boolean isAutoPay() {
        return this.H;
    }

    public boolean isCanChange() {
        return this.l;
    }

    public boolean isLogEnable() {
        return this.s;
    }

    public boolean isNumVisible() {
        return this.t;
    }

    public boolean isShowListOtherNum() {
        return this.x;
    }

    public boolean isShowNum() {
        return this.w;
    }

    public void setAcctType(String str) {
        this.o = str;
    }

    public void setAutoPay(boolean z) {
        this.H = z;
    }

    public void setCanChange(boolean z) {
        this.l = z;
    }

    public void setDiscountType(String str) {
        this.z = str;
    }

    public void setDiscountUrl(String str) {
        this.A = str;
    }

    public void setDiscoutId(String str) {
        this.C = str;
    }

    public void setDrmInfo(String str) {
        this.B = str;
    }

    public void setExtras(String str) {
        this.D = str;
    }

    public void setGoodsTokenUrl(String str) {
        this.E = str;
    }

    public void setGuid(String str) {
        this.P = str;
    }

    public void setH5Message(String str) {
        a.b = str;
    }

    public void setH5Url(String str) {
        this.r = str;
    }

    public void setLaunchInterface(String str) {
        this.Q = str;
    }

    public void setLogEnable(boolean z) {
        this.s = z;
    }

    public void setMallType(int i) {
        this.q = i;
    }

    public void setNumVisible(boolean z) {
        this.t = z;
    }

    public void setOfferId(String str) {
        this.c = str;
    }

    public void setOpenId(String str) {
        this.d = str;
    }

    public void setOpenKey(String str) {
        this.e = str;
    }

    public void setPayChannel(String str) {
        this.y = str;
    }

    public void setPayInterfaceTime(long j) {
        this.R = j;
    }

    public void setPf(String str) {
        this.i = str;
    }

    public void setPfKey(String str) {
        this.j = str;
    }

    public void setProdcutId(String str) {
        this.G = str;
    }

    public void setPropUnit(String str) {
        this.u = str;
    }

    public void setRemark(String str) {
        this.I = str;
    }

    public void setReqType(String str) {
        this.N = str;
    }

    public void setResData(byte[] bArr) {
        this.n = bArr;
    }

    public void setResId(int i) {
        this.m = i;
    }

    public void setResUrl(String str) {
        this.M = str;
    }

    public void setReserv(String str) {
        this.p = str;
    }

    public void setSaveType(int i) {
        this.O = i;
    }

    public void setSaveValue(String str) {
        this.k = str;
    }

    public void setServiceCode(String str) {
        this.J = str;
    }

    public void setServiceName(String str) {
        this.K = str;
    }

    public void setServiceType(int i) {
        this.L = i;
    }

    public void setSessionId(String str) {
        this.f = str;
    }

    public void setSessionType(String str) {
        this.g = str;
    }

    public void setShowListOtherNum(boolean z) {
        this.x = z;
    }

    public void setShowNum(boolean z) {
        this.w = z;
    }

    public void setTokenType(int i) {
        this.F = i;
    }

    public void setUnit(String str) {
        this.v = str;
    }

    public void setZoneId(String str) {
        this.h = str;
    }
}
