package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ShareSecurityCheck {
    private static final String TAG = "ShareSecurityCheck";
    private static PublicKey mPublicKey = null;
    private final Context mContext;
    private final HashMap<String, String> metaContentMap = new HashMap();
    private HashMap<String, String> packageProperties;

    public ShareSecurityCheck(Context context) {
        this.mContext = context;
        if (mPublicKey == null) {
            init(this.mContext);
        }
    }

    public HashMap<String, String> getMetaContentMap() {
        return this.metaContentMap;
    }

    public HashMap<String, String> getPackagePropertiesIfPresent() {
        if (this.packageProperties != null) {
            return this.packageProperties;
        }
        String str = (String) this.metaContentMap.get(ShareConstants.PACKAGE_META_FILE);
        if (str == null) {
            return null;
        }
        for (String str2 : str.split("\n")) {
            if (!(str2 == null || str2.length() <= 0 || str2.startsWith("#"))) {
                String[] split = str2.split("=", 2);
                if (split != null && split.length >= 2) {
                    if (this.packageProperties == null) {
                        this.packageProperties = new HashMap();
                    }
                    this.packageProperties.put(split[0].trim(), split[1].trim());
                }
            }
        }
        return this.packageProperties;
    }

    public boolean verifyPatchMetaSignature(File file) {
        JarFile jarFile;
        Throwable e;
        JarFile jarFile2;
        if (!SharePatchFileUtil.isLegalFile(file)) {
            return false;
        }
        try {
            jarFile = new JarFile(file);
            try {
                Enumeration entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) entries.nextElement();
                    if (jarEntry != null) {
                        String name = jarEntry.getName();
                        if (!name.startsWith("META-INF/") && name.endsWith(ShareConstants.META_SUFFIX)) {
                            this.metaContentMap.put(name, SharePatchFileUtil.loadDigestes(jarFile, jarEntry));
                            Certificate[] certificates = jarEntry.getCertificates();
                            if (certificates == null) {
                                if (jarFile != null) {
                                    try {
                                        jarFile.close();
                                    } catch (Throwable e2) {
                                        Log.e(TAG, file.getAbsolutePath(), e2);
                                    }
                                }
                                return false;
                            } else if (!check(file, certificates)) {
                                if (jarFile != null) {
                                    try {
                                        jarFile.close();
                                    } catch (Throwable e22) {
                                        Log.e(TAG, file.getAbsolutePath(), e22);
                                    }
                                }
                                return false;
                            }
                        }
                    }
                }
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Throwable e222) {
                        Log.e(TAG, file.getAbsolutePath(), e222);
                    }
                }
                return true;
            } catch (Exception e3) {
                e222 = e3;
                jarFile2 = jarFile;
            } catch (Throwable th) {
                e222 = th;
            }
        } catch (Exception e4) {
            e222 = e4;
            jarFile2 = null;
            try {
                throw new TinkerRuntimeException(String.format("ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail", new Object[]{file.getAbsolutePath(), Long.valueOf(file.length())}), e222);
            } catch (Throwable th2) {
                e222 = th2;
                jarFile = jarFile2;
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (Throwable e5) {
                        Log.e(TAG, file.getAbsolutePath(), e5);
                    }
                }
                throw e222;
            }
        } catch (Throwable th3) {
            e222 = th3;
            jarFile = null;
            if (jarFile != null) {
                jarFile.close();
            }
            throw e222;
        }
    }

    private boolean check(File file, Certificate[] certificateArr) {
        if (certificateArr.length > 0) {
            int length = certificateArr.length - 1;
            while (length >= 0) {
                try {
                    certificateArr[length].verify(mPublicKey);
                    return true;
                } catch (Throwable e) {
                    Log.e(TAG, file.getAbsolutePath(), e);
                    length--;
                }
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private void init(Context context) {
        Throwable e;
        Closeable closeable = null;
        Closeable byteArrayInputStream;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
            try {
                mPublicKey = ((X509Certificate) instance.generateCertificate(byteArrayInputStream)).getPublicKey();
                SharePatchFileUtil.closeQuietly(byteArrayInputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    throw new TinkerRuntimeException("ShareSecurityCheck init public key fail", e);
                } catch (Throwable th) {
                    e = th;
                    closeable = byteArrayInputStream;
                    SharePatchFileUtil.closeQuietly(closeable);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            byteArrayInputStream = null;
            throw new TinkerRuntimeException("ShareSecurityCheck init public key fail", e);
        } catch (Throwable th2) {
            e = th2;
            SharePatchFileUtil.closeQuietly(closeable);
            throw e;
        }
    }
}
