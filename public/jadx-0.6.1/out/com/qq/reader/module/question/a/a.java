package com.qq.reader.module.question.a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.question.card.FamousAuthorSayAdvCard;
import com.qq.reader.module.question.card.FamousAuthorSayBaseCard;
import com.qq.reader.module.question.card.FamousAuthorSayNormalCard;
import com.qq.reader.module.question.card.FamousAuthorSayRecommendCard;
import com.qq.reader.module.question.data.AudioData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FamousAuthorSayPage */
public class a {
    public static final Comparator<FamousAuthorSayBaseCard> a = new Comparator<FamousAuthorSayBaseCard>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((FamousAuthorSayBaseCard) obj, (FamousAuthorSayBaseCard) obj2);
        }

        public int a(FamousAuthorSayBaseCard famousAuthorSayBaseCard, FamousAuthorSayBaseCard famousAuthorSayBaseCard2) {
            if (famousAuthorSayBaseCard == null || famousAuthorSayBaseCard2 == null) {
                return 0;
            }
            long displayTime = famousAuthorSayBaseCard2.getDisplayTime();
            long displayTime2 = famousAuthorSayBaseCard.getDisplayTime();
            if (displayTime == displayTime2) {
                return 0;
            }
            if (displayTime > displayTime2) {
                return 1;
            }
            return -1;
        }
    };
    private List<FamousAuthorSayBaseCard> b = new ArrayList();
    private long c = 0;
    private long d = 0;
    private boolean e = false;
    private com.qq.reader.module.bookstore.qnative.c.a f = null;
    private boolean g = false;

    public void a(boolean z) {
        this.e = z;
    }

    public boolean a() {
        return this.e;
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.f = aVar;
    }

    public com.qq.reader.module.bookstore.qnative.c.a b() {
        return this.f;
    }

    public boolean c() {
        return this.g;
    }

    public void a(String str) {
        if (str != null && str.length() > 0) {
            if (this.b == null || this.b.size() == 0) {
                try {
                    this.g = true;
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("authorTalkList");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            a((JSONObject) optJSONArray.get(i));
                        }
                    }
                    Collections.sort(this.b, a);
                    b(this.b);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null) {
            this.g = true;
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    a(new JSONObject((String) it.next()));
                }
                Collections.sort(this.b, a);
                b(this.b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void d() {
        this.b.clear();
    }

    protected void a(JSONObject jSONObject) {
        String optString = jSONObject.optString("cardType");
        FamousAuthorSayBaseCard famousAuthorSayNormalCard;
        if ("normalList".equalsIgnoreCase(optString)) {
            this.g = false;
            famousAuthorSayNormalCard = new FamousAuthorSayNormalCard(null, optString);
            famousAuthorSayNormalCard.fillData(jSONObject);
            famousAuthorSayNormalCard.setEventListener(b());
            this.b.add(famousAuthorSayNormalCard);
        } else if ("ad".equalsIgnoreCase(optString) || "recommend".equalsIgnoreCase(optString)) {
            famousAuthorSayNormalCard = new FamousAuthorSayAdvCard(null, optString);
            famousAuthorSayNormalCard.fillData(jSONObject);
            famousAuthorSayNormalCard.setEventListener(b());
            this.b.add(famousAuthorSayNormalCard);
            long optLong = jSONObject.optLong("adExpireTime");
            if (optLong > 0 && optLong > System.currentTimeMillis()) {
                a(optString, famousAuthorSayNormalCard.getId());
            }
        } else if ("freeList".equalsIgnoreCase(optString)) {
            famousAuthorSayNormalCard = new FamousAuthorSayNormalCard(null, optString);
            famousAuthorSayNormalCard.fillData(jSONObject);
            famousAuthorSayNormalCard.setEventListener(b());
            this.b.add(famousAuthorSayNormalCard);
        } else if ("recommendAuthor".equalsIgnoreCase(optString)) {
            famousAuthorSayNormalCard = new FamousAuthorSayRecommendCard(null, optString);
            famousAuthorSayNormalCard.fillData(jSONObject);
            famousAuthorSayNormalCard.setEventListener(b());
            this.b.add(famousAuthorSayNormalCard);
        }
    }

    private void b(List<FamousAuthorSayBaseCard> list) {
        if (list != null) {
            for (FamousAuthorSayBaseCard displayTime : list) {
                long displayTime2 = displayTime.getDisplayTime();
                if (this.c == 0 || this.d == 0) {
                    this.c = displayTime2;
                    this.d = displayTime2;
                } else {
                    long j;
                    if (this.c < displayTime2) {
                        j = this.c;
                    } else {
                        j = displayTime2;
                    }
                    this.c = j;
                    if (this.d > displayTime2) {
                        displayTime2 = this.d;
                    }
                    this.d = displayTime2;
                }
            }
        }
    }

    public void a(List<FamousAuthorSayBaseCard> list) {
        a((List) list, false);
    }

    public void a(List<FamousAuthorSayBaseCard> list, boolean z) {
        if (z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.b.add(0, list.get(size));
            }
            return;
        }
        this.b.addAll(list);
    }

    public long e() {
        return this.d;
    }

    public long f() {
        return this.c;
    }

    public List<FamousAuthorSayBaseCard> g() {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        return this.b;
    }

    private void a(String str, String str2) {
        if ("ad".equalsIgnoreCase(str)) {
            if ("".equals(d.f)) {
                d.f += str2;
            } else if (!d.f.contains(str2)) {
                d.f += "," + str2;
            }
        } else if (!"recommend".equalsIgnoreCase(str)) {
        } else {
            if ("".equals(d.g)) {
                d.g += str2;
            } else if (!d.g.contains(String.valueOf(str2))) {
                d.g += "," + str2;
            }
        }
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        if (i == 1006 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                AudioData audioData = (AudioData) extras.getParcelable("AUDIO_DATA");
                for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.b) {
                    String toLowerCase = aVar.getType().toLowerCase();
                    if ("freeList".equalsIgnoreCase(toLowerCase) || "normalList".equalsIgnoreCase(toLowerCase)) {
                        try {
                            if (((com.qq.reader.module.question.card.a) aVar).isDataChanged(audioData)) {
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
