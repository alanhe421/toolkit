package org.apache.commons.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory {
    protected static Hashtable a = new Hashtable();
    static Class b;
    static Class c;

    class AnonymousClass2 implements PrivilegedAction {
        static Class a;
        private final ClassLoader b;
        private final String c;

        AnonymousClass2(ClassLoader classLoader, String str) {
            this.b = classLoader;
            this.c = str;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object run() {
            /*
            r4 = this;
            r1 = 0;
            r0 = r4.b;	 Catch:{ Exception -> 0x009a }
            if (r0 == 0) goto L_0x0083;
        L_0x0005:
            r0 = r4.b;	 Catch:{ ClassNotFoundException -> 0x0014, NoClassDefFoundError -> 0x004f, ClassCastException -> 0x0069 }
            r2 = r4.c;	 Catch:{ ClassNotFoundException -> 0x0014, NoClassDefFoundError -> 0x004f, ClassCastException -> 0x0069 }
            r1 = r0.loadClass(r2);	 Catch:{ ClassNotFoundException -> 0x0014, NoClassDefFoundError -> 0x004f, ClassCastException -> 0x0069 }
            r0 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x0014, NoClassDefFoundError -> 0x004f, ClassCastException -> 0x0069 }
            r0 = (org.apache.commons.logging.LogFactory) r0;	 Catch:{ ClassNotFoundException -> 0x0014, NoClassDefFoundError -> 0x004f, ClassCastException -> 0x0069 }
        L_0x0013:
            return r0;
        L_0x0014:
            r0 = move-exception;
            r3 = r4.b;	 Catch:{ Exception -> 0x002b }
            r2 = a;	 Catch:{ Exception -> 0x002b }
            if (r2 != 0) goto L_0x004c;
        L_0x001b:
            r2 = "org.apache.commons.logging.LogFactory";
            r2 = org.apache.commons.logging.LogFactory.AnonymousClass2.a(r2);	 Catch:{ Exception -> 0x002b }
            a = r2;	 Catch:{ Exception -> 0x002b }
        L_0x0024:
            r2 = r2.getClassLoader();	 Catch:{ Exception -> 0x002b }
            if (r3 != r2) goto L_0x0083;
        L_0x002a:
            throw r0;	 Catch:{ Exception -> 0x002b }
        L_0x002b:
            r0 = move-exception;
            r2 = r1;
            r1 = r0;
        L_0x002e:
            if (r2 == 0) goto L_0x0093;
        L_0x0030:
            r0 = a;
            if (r0 != 0) goto L_0x0090;
        L_0x0034:
            r0 = "org.apache.commons.logging.LogFactory";
            r0 = org.apache.commons.logging.LogFactory.AnonymousClass2.a(r0);
            a = r0;
        L_0x003d:
            r0 = r0.isAssignableFrom(r2);
            if (r0 != 0) goto L_0x0093;
        L_0x0043:
            r0 = new org.apache.commons.logging.LogConfigurationException;
            r2 = "The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.";
            r0.<init>(r2, r1);
            goto L_0x0013;
        L_0x004c:
            r2 = a;	 Catch:{ Exception -> 0x002b }
            goto L_0x0024;
        L_0x004f:
            r0 = move-exception;
            r3 = r4.b;	 Catch:{ Exception -> 0x002b }
            r2 = a;	 Catch:{ Exception -> 0x002b }
            if (r2 != 0) goto L_0x0066;
        L_0x0056:
            r2 = "org.apache.commons.logging.LogFactory";
            r2 = org.apache.commons.logging.LogFactory.AnonymousClass2.a(r2);	 Catch:{ Exception -> 0x002b }
            a = r2;	 Catch:{ Exception -> 0x002b }
        L_0x005f:
            r2 = r2.getClassLoader();	 Catch:{ Exception -> 0x002b }
            if (r3 != r2) goto L_0x0083;
        L_0x0065:
            throw r0;	 Catch:{ Exception -> 0x002b }
        L_0x0066:
            r2 = a;	 Catch:{ Exception -> 0x002b }
            goto L_0x005f;
        L_0x0069:
            r0 = move-exception;
            r3 = r4.b;	 Catch:{ Exception -> 0x002b }
            r2 = a;	 Catch:{ Exception -> 0x002b }
            if (r2 != 0) goto L_0x0080;
        L_0x0070:
            r2 = "org.apache.commons.logging.LogFactory";
            r2 = org.apache.commons.logging.LogFactory.AnonymousClass2.a(r2);	 Catch:{ Exception -> 0x002b }
            a = r2;	 Catch:{ Exception -> 0x002b }
        L_0x0079:
            r2 = r2.getClassLoader();	 Catch:{ Exception -> 0x002b }
            if (r3 != r2) goto L_0x0083;
        L_0x007f:
            throw r0;	 Catch:{ Exception -> 0x002b }
        L_0x0080:
            r2 = a;	 Catch:{ Exception -> 0x002b }
            goto L_0x0079;
        L_0x0083:
            r0 = r4.c;	 Catch:{ Exception -> 0x002b }
            r1 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x002b }
            r0 = r1.newInstance();	 Catch:{ Exception -> 0x002b }
            r0 = (org.apache.commons.logging.LogFactory) r0;	 Catch:{ Exception -> 0x002b }
            goto L_0x0013;
        L_0x0090:
            r0 = a;
            goto L_0x003d;
        L_0x0093:
            r0 = new org.apache.commons.logging.LogConfigurationException;
            r0.<init>(r1);
            goto L_0x0013;
        L_0x009a:
            r0 = move-exception;
            r2 = r1;
            r1 = r0;
            goto L_0x002e;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.logging.LogFactory.2.run():java.lang.Object");
        }

        static Class a(String str) {
            try {
                return Class.forName(str);
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
    }

    class AnonymousClass3 implements PrivilegedAction {
        private final ClassLoader a;
        private final String b;

        AnonymousClass3(ClassLoader classLoader, String str) {
            this.a = classLoader;
            this.b = str;
        }

        public Object run() {
            if (this.a != null) {
                return this.a.getResourceAsStream(this.b);
            }
            return ClassLoader.getSystemResourceAsStream(this.b);
        }
    }

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract Log getInstance(Class cls) throws LogConfigurationException;

    public abstract Log getInstance(String str) throws LogConfigurationException;

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    protected LogFactory() {
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        Properties properties;
        Properties properties2;
        String property;
        LogFactory logFactory;
        InputStream a;
        BufferedReader bufferedReader;
        String readLine;
        String str;
        Class cls;
        Enumeration propertyNames;
        String str2;
        ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return LogFactory.a();
            }
        });
        LogFactory a2 = a(classLoader);
        if (a2 == null) {
            Properties properties3 = null;
            try {
                InputStream a3 = a(classLoader, "commons-logging.properties");
                if (a3 != null) {
                    properties = new Properties();
                    try {
                        properties.load(a3);
                        a3.close();
                    } catch (IOException e) {
                        properties3 = properties;
                        properties2 = properties3;
                        property = System.getProperty("org.apache.commons.logging.LogFactory");
                        if (property != null) {
                            logFactory = a2;
                        } else {
                            logFactory = a(property, classLoader);
                        }
                        if (logFactory == null) {
                            a2 = logFactory;
                        } else {
                            try {
                                a = a(classLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                                if (a != null) {
                                    try {
                                        bufferedReader = new BufferedReader(new InputStreamReader(a, "UTF-8"));
                                    } catch (UnsupportedEncodingException e2) {
                                        bufferedReader = new BufferedReader(new InputStreamReader(a));
                                    }
                                    readLine = bufferedReader.readLine();
                                    bufferedReader.close();
                                    logFactory = a(readLine, classLoader);
                                }
                                a2 = logFactory;
                            } catch (Exception e3) {
                                a2 = logFactory;
                            }
                        }
                        property = properties2.getProperty("org.apache.commons.logging.LogFactory");
                        if (property != null) {
                            a2 = a(property, classLoader);
                        }
                        if (a2 == null) {
                            str = "org.apache.commons.logging.impl.LogFactoryImpl";
                            if (b == null) {
                                cls = b;
                            } else {
                                cls = a("org.apache.commons.logging.LogFactory");
                                b = cls;
                            }
                            a2 = a(str, cls.getClassLoader());
                        }
                        if (a2 != null) {
                            a(classLoader, a2);
                            if (properties2 != null) {
                                propertyNames = properties2.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    a2.setAttribute(str2, properties2.getProperty(str2));
                                }
                            }
                        }
                        return a2;
                    } catch (SecurityException e4) {
                        properties3 = properties;
                        properties2 = properties3;
                        property = System.getProperty("org.apache.commons.logging.LogFactory");
                        if (property != null) {
                            logFactory = a(property, classLoader);
                        } else {
                            logFactory = a2;
                        }
                        if (logFactory == null) {
                            a = a(classLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                            if (a != null) {
                                bufferedReader = new BufferedReader(new InputStreamReader(a, "UTF-8"));
                                readLine = bufferedReader.readLine();
                                bufferedReader.close();
                                logFactory = a(readLine, classLoader);
                            }
                            a2 = logFactory;
                        } else {
                            a2 = logFactory;
                        }
                        property = properties2.getProperty("org.apache.commons.logging.LogFactory");
                        if (property != null) {
                            a2 = a(property, classLoader);
                        }
                        if (a2 == null) {
                            str = "org.apache.commons.logging.impl.LogFactoryImpl";
                            if (b == null) {
                                cls = a("org.apache.commons.logging.LogFactory");
                                b = cls;
                            } else {
                                cls = b;
                            }
                            a2 = a(str, cls.getClassLoader());
                        }
                        if (a2 != null) {
                            a(classLoader, a2);
                            if (properties2 != null) {
                                propertyNames = properties2.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    a2.setAttribute(str2, properties2.getProperty(str2));
                                }
                            }
                        }
                        return a2;
                    }
                }
                properties = null;
                properties2 = properties;
            } catch (IOException e5) {
                properties2 = properties3;
                property = System.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    logFactory = a2;
                } else {
                    logFactory = a(property, classLoader);
                }
                if (logFactory == null) {
                    a2 = logFactory;
                } else {
                    a = a(classLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                    if (a != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(a, "UTF-8"));
                        readLine = bufferedReader.readLine();
                        bufferedReader.close();
                        logFactory = a(readLine, classLoader);
                    }
                    a2 = logFactory;
                }
                property = properties2.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    a2 = a(property, classLoader);
                }
                if (a2 == null) {
                    str = "org.apache.commons.logging.impl.LogFactoryImpl";
                    if (b == null) {
                        cls = b;
                    } else {
                        cls = a("org.apache.commons.logging.LogFactory");
                        b = cls;
                    }
                    a2 = a(str, cls.getClassLoader());
                }
                if (a2 != null) {
                    a(classLoader, a2);
                    if (properties2 != null) {
                        propertyNames = properties2.propertyNames();
                        while (propertyNames.hasMoreElements()) {
                            str2 = (String) propertyNames.nextElement();
                            a2.setAttribute(str2, properties2.getProperty(str2));
                        }
                    }
                }
                return a2;
            } catch (SecurityException e6) {
                properties2 = properties3;
                property = System.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    logFactory = a(property, classLoader);
                } else {
                    logFactory = a2;
                }
                if (logFactory == null) {
                    a = a(classLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                    if (a != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(a, "UTF-8"));
                        readLine = bufferedReader.readLine();
                        bufferedReader.close();
                        logFactory = a(readLine, classLoader);
                    }
                    a2 = logFactory;
                } else {
                    a2 = logFactory;
                }
                property = properties2.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    a2 = a(property, classLoader);
                }
                if (a2 == null) {
                    str = "org.apache.commons.logging.impl.LogFactoryImpl";
                    if (b == null) {
                        cls = a("org.apache.commons.logging.LogFactory");
                        b = cls;
                    } else {
                        cls = b;
                    }
                    a2 = a(str, cls.getClassLoader());
                }
                if (a2 != null) {
                    a(classLoader, a2);
                    if (properties2 != null) {
                        propertyNames = properties2.propertyNames();
                        while (propertyNames.hasMoreElements()) {
                            str2 = (String) propertyNames.nextElement();
                            a2.setAttribute(str2, properties2.getProperty(str2));
                        }
                    }
                }
                return a2;
            }
            try {
                property = System.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    logFactory = a(property, classLoader);
                } else {
                    logFactory = a2;
                }
            } catch (SecurityException e7) {
                logFactory = a2;
            }
            if (logFactory == null) {
                a = a(classLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                if (a != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(a, "UTF-8"));
                    readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    if (!(readLine == null || "".equals(readLine))) {
                        logFactory = a(readLine, classLoader);
                    }
                }
                a2 = logFactory;
            } else {
                a2 = logFactory;
            }
            if (a2 == null && properties2 != null) {
                property = properties2.getProperty("org.apache.commons.logging.LogFactory");
                if (property != null) {
                    a2 = a(property, classLoader);
                }
            }
            if (a2 == null) {
                str = "org.apache.commons.logging.impl.LogFactoryImpl";
                if (b == null) {
                    cls = a("org.apache.commons.logging.LogFactory");
                    b = cls;
                } else {
                    cls = b;
                }
                a2 = a(str, cls.getClassLoader());
            }
            if (a2 != null) {
                a(classLoader, a2);
                if (properties2 != null) {
                    propertyNames = properties2.propertyNames();
                    while (propertyNames.hasMoreElements()) {
                        str2 = (String) propertyNames.nextElement();
                        a2.setAttribute(str2, properties2.getProperty(str2));
                    }
                }
            }
        }
        return a2;
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Log getLog(Class cls) throws LogConfigurationException {
        return getFactory().getInstance(cls);
    }

    public static Log getLog(String str) throws LogConfigurationException {
        return getFactory().getInstance(str);
    }

    public static void release(ClassLoader classLoader) {
        synchronized (a) {
            LogFactory logFactory = (LogFactory) a.get(classLoader);
            if (logFactory != null) {
                logFactory.release();
                a.remove(classLoader);
            }
        }
    }

    public static void releaseAll() {
        synchronized (a) {
            Enumeration elements = a.elements();
            while (elements.hasMoreElements()) {
                ((LogFactory) elements.nextElement()).release();
            }
            a.clear();
        }
    }

    protected static ClassLoader a() throws LogConfigurationException {
        Class a;
        try {
            if (c == null) {
                a = a("java.lang.Thread");
                c = a;
            } else {
                a = c;
            }
            return (ClassLoader) a.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
        } catch (Throwable e) {
            throw new LogConfigurationException("Unexpected IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof SecurityException) {
                return null;
            }
            throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
        } catch (NoSuchMethodException e3) {
            if (b == null) {
                a = a("org.apache.commons.logging.LogFactory");
                b = a;
            } else {
                a = b;
            }
            return a.getClassLoader();
        }
    }

    private static LogFactory a(ClassLoader classLoader) {
        if (classLoader != null) {
            return (LogFactory) a.get(classLoader);
        }
        return null;
    }

    private static void a(ClassLoader classLoader, LogFactory logFactory) {
        if (classLoader != null && logFactory != null) {
            a.put(classLoader, logFactory);
        }
    }

    protected static LogFactory a(String str, ClassLoader classLoader) throws LogConfigurationException {
        Object doPrivileged = AccessController.doPrivileged(new AnonymousClass2(classLoader, str));
        if (!(doPrivileged instanceof LogConfigurationException)) {
            return (LogFactory) doPrivileged;
        }
        throw ((LogConfigurationException) doPrivileged);
    }

    private static InputStream a(ClassLoader classLoader, String str) {
        return (InputStream) AccessController.doPrivileged(new AnonymousClass3(classLoader, str));
    }
}
