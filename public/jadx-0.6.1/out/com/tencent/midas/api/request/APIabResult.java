package com.tencent.midas.api.request;

public class APIabResult {
    int a;
    String b;

    public APIabResult(int i, String str) {
        this.a = i;
        if (str == null || str.trim().length() == 0) {
            this.b = getResponseDesc(i);
        } else {
            this.b = str + " (response: " + getResponseDesc(i) + ")";
        }
    }

    public static String getResponseDesc(int i) {
        return "";
    }

    public String getMessage() {
        return this.b;
    }

    public int getResponse() {
        return this.a;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public boolean isPendig() {
        return this.a == 101;
    }

    public boolean isSuccess() {
        return this.a == 0;
    }

    public String toString() {
        return "IabResult: " + getMessage();
    }
}
