package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

public class UnsupportedZipFeatureException extends ZipException {
    private static final long serialVersionUID = 20130101;
    private final n entry;
    private final a reason;

    public static class a {
        public static final a a = new a("encryption");
        public static final a b = new a("compression method");
        public static final a c = new a("data descriptor");
        public static final a d = new a("splitting");
        private final String e;

        private a(String str) {
            this.e = str;
        }

        public String toString() {
            return this.e;
        }
    }

    public UnsupportedZipFeatureException(a aVar, n nVar) {
        super("unsupported feature " + aVar + " used in entry " + nVar.getName());
        this.reason = aVar;
        this.entry = nVar;
    }

    public UnsupportedZipFeatureException(ZipMethod zipMethod, n nVar) {
        super("unsupported feature method '" + zipMethod.name() + "' used in entry " + nVar.getName());
        this.reason = a.b;
        this.entry = nVar;
    }

    public UnsupportedZipFeatureException(a aVar) {
        super("unsupported feature " + aVar + " used in archive.");
        this.reason = aVar;
        this.entry = null;
    }

    public a getFeature() {
        return this.reason;
    }

    public n getEntry() {
        return this.entry;
    }
}
