package qalsdk;

import org.json.JSONObject;

/* compiled from: WifiDetectInfo */
public class af {
    public String a;
    public long b;
    public boolean c;

    public af(String str, long j, boolean z) {
        this.a = str;
        this.b = j;
        this.c = z;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ssid", this.a);
            jSONObject.put("time", this.b);
            jSONObject.put("available", this.c);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static af a(JSONObject jSONObject) {
        try {
            return new af(jSONObject.getString("ssid"), jSONObject.getLong("time"), jSONObject.getBoolean("available"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
