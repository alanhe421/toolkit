package com.qq.reader.module.comic.e;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: ComicDownloadHttpsCheckUtils */
public class d {
    private static a a = new a();
    private static HostnameVerifier b = new HostnameVerifier() {
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };
    private static SSLContext c;

    /* compiled from: ComicDownloadHttpsCheckUtils */
    private static class a implements X509TrustManager {
        private X509TrustManager a;
        private X509Certificate[] b;

        a() {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidCAStore");
                instance.load(null, null);
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance("X509");
                instance2.init(instance);
                TrustManager[] trustManagers = instance2.getTrustManagers();
                if (trustManagers.length > 0) {
                    this.a = (X509TrustManager) trustManagers[0];
                    this.b = this.a.getAcceptedIssuers();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                this.a.checkServerTrusted(x509CertificateArr, str);
            } catch (Throwable e) {
                e.printStackTrace();
                Throwable th = e;
                while (th != null) {
                    if (!(th instanceof CertificateExpiredException) && !(th instanceof CertificateNotYetValidException)) {
                        th = th.getCause();
                    } else {
                        return;
                    }
                }
                throw e;
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.b;
        }
    }

    public static synchronized void a() {
        synchronized (d.class) {
            if (c == null) {
                try {
                    c = SSLContext.getInstance("TLS");
                    c.init(null, new TrustManager[]{a}, new SecureRandom());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void a(okhttp3.u.a aVar) {
        a();
        aVar.a(c.getSocketFactory(), a);
        aVar.a(b);
    }
}
