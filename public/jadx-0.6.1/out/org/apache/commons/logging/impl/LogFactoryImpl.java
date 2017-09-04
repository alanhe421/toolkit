package org.apache.commons.logging.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class LogFactoryImpl extends LogFactory {
    static Class j;
    static Class k;
    protected Hashtable d = new Hashtable();
    protected Hashtable e = new Hashtable();
    protected Constructor f = null;
    protected Class[] g;
    protected Method h;
    protected Class[] i;
    private String l;

    class AnonymousClass1 implements PrivilegedAction {
        private final String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        public Object run() {
            Object loadClass;
            ClassLoader g = LogFactoryImpl.g();
            if (g != null) {
                try {
                    loadClass = g.loadClass(this.a);
                } catch (ClassNotFoundException e) {
                }
                return loadClass;
            }
            try {
                loadClass = Class.forName(this.a);
            } catch (ClassNotFoundException e2) {
                loadClass = e2;
            }
            return loadClass;
        }
    }

    static Class c(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static ClassLoader g() throws LogConfigurationException {
        return LogFactory.a();
    }

    public LogFactoryImpl() {
        Class c;
        Class[] clsArr = new Class[1];
        if (j == null) {
            c = c("java.lang.String");
            j = c;
        } else {
            c = j;
        }
        clsArr[0] = c;
        this.g = clsArr;
        this.h = null;
        clsArr = new Class[1];
        if (k == null) {
            c = c("org.apache.commons.logging.LogFactory");
            k = c;
        } else {
            c = k;
        }
        clsArr[0] = c;
        this.i = clsArr;
    }

    public Object getAttribute(String str) {
        return this.d.get(str);
    }

    public String[] getAttributeNames() {
        Vector vector = new Vector();
        Enumeration keys = this.d.keys();
        while (keys.hasMoreElements()) {
            vector.addElement((String) keys.nextElement());
        }
        String[] strArr = new String[vector.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public Log getInstance(Class cls) throws LogConfigurationException {
        return getInstance(cls.getName());
    }

    public Log getInstance(String str) throws LogConfigurationException {
        Log log = (Log) this.e.get(str);
        if (log != null) {
            return log;
        }
        log = b(str);
        this.e.put(str, log);
        return log;
    }

    public void release() {
        this.e.clear();
    }

    public void removeAttribute(String str) {
        this.d.remove(str);
    }

    public void setAttribute(String str, Object obj) {
        if (obj == null) {
            this.d.remove(str);
        } else {
            this.d.put(str, obj);
        }
    }

    protected String b() {
        if (this.l != null) {
            return this.l;
        }
        this.l = (String) getAttribute("org.apache.commons.logging.Log");
        if (this.l == null) {
            this.l = (String) getAttribute("org.apache.commons.logging.log");
        }
        if (this.l == null) {
            try {
                this.l = System.getProperty("org.apache.commons.logging.Log");
            } catch (SecurityException e) {
            }
        }
        if (this.l == null) {
            try {
                this.l = System.getProperty("org.apache.commons.logging.log");
            } catch (SecurityException e2) {
            }
        }
        if (this.l == null && f()) {
            this.l = "org.apache.commons.logging.impl.Log4JLogger";
        }
        if (this.l == null && e()) {
            this.l = "org.apache.commons.logging.impl.Jdk14Logger";
        }
        if (this.l == null && d()) {
            this.l = "org.apache.commons.logging.impl.Jdk13LumberjackLogger";
        }
        if (this.l == null) {
            this.l = "org.apache.commons.logging.impl.SimpleLog";
        }
        return this.l;
    }

    protected Constructor c() throws LogConfigurationException {
        if (this.f != null) {
            return this.f;
        }
        String b = b();
        try {
            Class loadClass = getClass().getClassLoader().loadClass("org.apache.commons.logging.Log");
            Class d = d(b);
            if (d == null) {
                throw new LogConfigurationException(new StringBuffer().append("No suitable Log implementation for ").append(b).toString());
            } else if (loadClass.isAssignableFrom(d)) {
                try {
                    this.h = d.getMethod("setLogFactory", this.i);
                } catch (Throwable th) {
                    this.h = null;
                }
                try {
                    this.f = d.getConstructor(this.g);
                    return this.f;
                } catch (Throwable th2) {
                    LogConfigurationException logConfigurationException = new LogConfigurationException(new StringBuffer().append("No suitable Log constructor ").append(this.g).append(" for ").append(b).toString(), th2);
                }
            } else {
                Class[] interfaces = d.getInterfaces();
                for (Class name : interfaces) {
                    if ("org.apache.commons.logging.Log".equals(name.getName())) {
                        throw new LogConfigurationException("Invalid class loader hierarchy.  You have more than one version of 'org.apache.commons.logging.Log' visible, which is not allowed.");
                    }
                }
                throw new LogConfigurationException(new StringBuffer().append("Class ").append(b).append(" does not implement '").append("org.apache.commons.logging.Log").append("'.").toString());
            }
        } catch (Throwable th22) {
            LogConfigurationException logConfigurationException2 = new LogConfigurationException(th22);
        }
    }

    private static Class d(String str) throws ClassNotFoundException {
        Object doPrivileged = AccessController.doPrivileged(new AnonymousClass1(str));
        if (doPrivileged instanceof Class) {
            return (Class) doPrivileged;
        }
        throw ((ClassNotFoundException) doPrivileged);
    }

    protected boolean d() {
        try {
            d("java.util.logging.Logger");
            d("org.apache.commons.logging.impl.Jdk13LumberjackLogger");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    protected boolean e() {
        try {
            d("java.util.logging.Logger");
            d("org.apache.commons.logging.impl.Jdk14Logger");
            if (d("java.lang.Throwable").getDeclaredMethod("getStackTrace", null) == null) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    protected boolean f() {
        try {
            d("org.apache.log4j.Logger");
            d("org.apache.commons.logging.impl.Log4JLogger");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    protected Log b(String str) throws LogConfigurationException {
        try {
            Object[] objArr = new Object[]{str};
            Log log = (Log) c().newInstance(objArr);
            if (this.h != null) {
                objArr[0] = this;
                this.h.invoke(log, objArr);
            }
            return log;
        } catch (Throwable e) {
            Throwable targetException = e.getTargetException();
            if (targetException != null) {
                throw new LogConfigurationException(targetException);
            }
            throw new LogConfigurationException(e);
        } catch (Throwable e2) {
            LogConfigurationException logConfigurationException = new LogConfigurationException(e2);
        }
    }
}
