package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import org.apache.commons.logging.impl.NoOpLog;

public class LogSource {
    protected static Hashtable a = new Hashtable();
    protected static boolean b;
    protected static boolean c;
    protected static Constructor d = null;

    static {
        String str = null;
        b = false;
        c = false;
        try {
            if (Class.forName("org.apache.log4j.Logger") != null) {
                b = true;
            } else {
                b = false;
            }
        } catch (Throwable th) {
            b = false;
        }
        try {
            if (Class.forName("java.util.logging.Logger") == null || Class.forName("org.apache.commons.logging.impl.Jdk14Logger") == null) {
                c = false;
                try {
                    str = System.getProperty("org.apache.commons.logging.log");
                    if (str == null) {
                        str = System.getProperty("org.apache.commons.logging.Log");
                    }
                } catch (Throwable th2) {
                }
                if (str != null) {
                    try {
                        setLogImplementation(str);
                    } catch (Throwable th3) {
                        return;
                    }
                }
                try {
                    if (b) {
                        setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
                    } else if (c) {
                        setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
                    } else {
                        setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
                    }
                } catch (Throwable th4) {
                    return;
                }
            }
            c = true;
            str = System.getProperty("org.apache.commons.logging.log");
            if (str == null) {
                str = System.getProperty("org.apache.commons.logging.Log");
            }
            if (str != null) {
                setLogImplementation(str);
            } else if (b) {
                setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
            } else if (c) {
                setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
            } else {
                setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
            }
        } catch (Throwable th5) {
            c = false;
        }
    }

    private LogSource() {
    }

    public static void setLogImplementation(String str) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
        try {
            d = Class.forName(str).getConstructor(new Class[]{"".getClass()});
        } catch (Throwable th) {
            d = null;
        }
    }

    public static void setLogImplementation(Class cls) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
        d = cls.getConstructor(new Class[]{"".getClass()});
    }

    public static Log getInstance(String str) {
        Log log = (Log) a.get(str);
        if (log != null) {
            return log;
        }
        log = makeNewLogInstance(str);
        a.put(str, log);
        return log;
    }

    public static Log getInstance(Class cls) {
        return getInstance(cls.getName());
    }

    public static Log makeNewLogInstance(String str) {
        Log log;
        try {
            log = (Log) d.newInstance(new Object[]{str});
        } catch (Throwable th) {
            log = null;
        }
        if (log == null) {
            return new NoOpLog(str);
        }
        return log;
    }

    public static String[] getLogNames() {
        return (String[]) a.keySet().toArray(new String[a.size()]);
    }
}
