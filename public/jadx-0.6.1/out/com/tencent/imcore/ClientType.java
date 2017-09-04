package com.tencent.imcore;

public enum ClientType {
    ePC((String) 1),
    eIOS((String) 110),
    eAndroid((String) 109);
    
    private final int swigValue;

    private static class aa {
        private static int a;

        static {
            a = 0;
        }

        static /* synthetic */ int a() {
            int i = a;
            a = i + 1;
            return i;
        }
    }

    private ClientType(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private ClientType(ClientType clientType) {
        this.swigValue = clientType.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static ClientType swigToEnum(int i) {
        ClientType[] clientTypeArr = (ClientType[]) ClientType.class.getEnumConstants();
        if (i < clientTypeArr.length && i >= 0 && clientTypeArr[i].swigValue == i) {
            return clientTypeArr[i];
        }
        for (ClientType clientType : clientTypeArr) {
            if (clientType.swigValue == i) {
                return clientType;
            }
        }
        throw new IllegalArgumentException("No enum " + ClientType.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
