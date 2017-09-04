package com.qq.reader.common.web.js.a;

import android.net.Uri;
import com.tencent.smtt.sdk.WebView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: JsBridgeX5 */
public class b {
    HashMap<String, b> a = new HashMap();

    /* compiled from: JsBridgeX5 */
    public static class b {
        public void call(String str, List<String> list, a aVar) {
            int i = 0;
            Method method = null;
            for (Method method2 : getClass().getDeclaredMethods()) {
                if (method2.getName().equals(str) && method2.getParameterTypes().length == list.size()) {
                    method = method2;
                    break;
                }
            }
            int size = list.size();
            while (i < size) {
                try {
                    list.set(i, URLDecoder.decode((String) list.get(i), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if (method != null) {
                Object obj = null;
                try {
                    switch (list.size()) {
                        case 0:
                            obj = method.invoke(this, new Object[0]);
                            break;
                        case 1:
                            obj = method.invoke(this, new Object[]{list.get(0)});
                            break;
                        case 2:
                            obj = method.invoke(this, new Object[]{list.get(0), list.get(1)});
                            break;
                        case 3:
                            obj = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2)});
                            break;
                        case 4:
                            obj = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3)});
                            break;
                        case 5:
                            obj = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)});
                            break;
                        case 6:
                            method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)});
                            break;
                        case 7:
                            break;
                    }
                    obj = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)});
                    if (method.getReturnType() == Void.class) {
                        method.invoke(this, new Object[]{list});
                        if (aVar != null) {
                            aVar.a(null);
                        }
                    } else if (aVar == null) {
                        return;
                    } else {
                        if (customCallback()) {
                            aVar.a(obj.toString());
                            return;
                        } else {
                            aVar.a(obj);
                            return;
                        }
                    }
                } catch (IllegalAccessException e2) {
                    if (aVar != null) {
                        aVar.a();
                    }
                } catch (InvocationTargetException e3) {
                    if (aVar != null) {
                        aVar.a();
                    }
                } catch (Exception e4) {
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            }
            if (aVar != null) {
                aVar.a();
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    /* compiled from: JsBridgeX5 */
    public static class a {
        WeakReference<WebView> a;
        long b;
        String c;

        public a(WebView webView, long j, String str) {
            this.a = new WeakReference(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj instanceof String) {
                    str = "'" + ((String) obj).replace("\\", "\\\\").replace("'", "\\'") + "'";
                } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    str = obj.toString();
                }
                webView.loadUrl("javascript:JsBridge.callback(" + this.b + ",{'r':0,'result':" + str + "});");
            }
        }

        public void a() {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
            }
        }

        public void a(String str) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    public void a(b bVar, String str) {
        this.a.put(str, bVar);
    }

    public void a(String str) {
        if (str == null) {
            this.a.clear();
        } else {
            this.a.remove(str);
        }
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        b bVar = (b) this.a.get(str);
        if (bVar != null) {
            bVar.call(str2, list, aVar);
        } else if (aVar != null) {
            aVar.a();
        }
    }

    public boolean a(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        if (!Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        try {
            a((String) arrayList.get(2), (String) arrayList.get(3), arrayList.subList(5, arrayList.size() - 1), new a(webView, Long.parseLong((String) arrayList.get(4)), str));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
