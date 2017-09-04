package com.qq.reader.common.web.js;

import android.app.Activity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.plugin.audiobook.MusicAllTag;
import com.qq.reader.plugin.audiobook.MusicOnlineTag;
import org.json.JSONException;
import org.json.JSONObject;

public class JSReadMusicOnline extends b {
    private Activity a;

    public JSReadMusicOnline(Activity activity) {
        this.a = activity;
    }

    public void downloadbook(String str) {
        if (this.a instanceof WebBrowserForContents) {
            try {
                ((WebBrowserForContents) this.a).a(new MusicAllTag(new JSONObject(str).getLong("bid")));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void readmusic(String str) {
        if (this.a instanceof WebBrowserForContents) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                long j = jSONObject.getLong("bid");
                String string = jSONObject.getString("bname");
                String string2 = jSONObject.getString("author");
                String string3 = jSONObject.getString("reader");
                String string4 = jSONObject.getString("btime");
                String string5 = jSONObject.getString("price");
                String string6 = jSONObject.getString("csize");
                String string7 = jSONObject.getString("ctime");
                long j2 = jSONObject.getLong("cid");
                String string8 = jSONObject.getString("cname");
                String string9 = jSONObject.getString("fileFormat");
                int i = jSONObject.getInt("drm");
                int i2 = jSONObject.getInt("ccount");
                MusicOnlineTag musicOnlineTag = new MusicOnlineTag(j);
                musicOnlineTag.setBname(string).setAuthor(string2).setReader(string3).setBtime(string4).setPrice(string5).setCsize(string6).setCtime(string7).setCid(j2).setCname(string8).setFileFormat(string9).setDrmFlag(i).setChapterCount(i2);
                ((WebBrowserForContents) this.a).a(musicOnlineTag);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
