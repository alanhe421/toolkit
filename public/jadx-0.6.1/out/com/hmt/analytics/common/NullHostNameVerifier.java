package com.hmt.analytics.common;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NullHostNameVerifier implements HostnameVerifier {
    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
