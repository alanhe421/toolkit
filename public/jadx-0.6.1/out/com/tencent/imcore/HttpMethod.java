package com.tencent.imcore;

public enum HttpMethod {
    kPost((String) 1),
    kGet,
    kPut,
    kDelete;
    
    private final int swigValue;

    private HttpMethod(int i) {
        this.swigValue = i;
        aa.a(i + 1);
    }

    private HttpMethod(HttpMethod httpMethod) {
        this.swigValue = httpMethod.swigValue;
        aa.a(this.swigValue + 1);
    }

    public static HttpMethod swigToEnum(int i) {
        HttpMethod[] httpMethodArr = (HttpMethod[]) HttpMethod.class.getEnumConstants();
        if (i < httpMethodArr.length && i >= 0 && httpMethodArr[i].swigValue == i) {
            return httpMethodArr[i];
        }
        for (HttpMethod httpMethod : httpMethodArr) {
            if (httpMethod.swigValue == i) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("No enum " + HttpMethod.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
