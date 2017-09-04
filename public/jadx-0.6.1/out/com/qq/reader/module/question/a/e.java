package com.qq.reader.module.question.a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.module.bookstore.qnative.a;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.question.card.AudioComBaseCardDisablePlay;
import com.qq.reader.module.question.card.AudioComCardOfHisQuestion;
import com.qq.reader.module.question.card.AudioComCardOfMyListen;
import com.qq.reader.module.question.card.AudioQuestionCardOfMineQA;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.fragment.NativePageFragmentforQA;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfUserQA */
public class e extends af {
    private String a;

    public e(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        this.a = bundle.getString("URL_BUILD_PERE_QA_TYPE");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        String str = com.qq.reader.appconfig.e.a;
        if ("0".equals(this.a)) {
            str = str.concat("nativepage/user/myquestion");
        } else if ("1".equals(this.a)) {
            str = str.concat("nativepage/user/mylisten");
        } else if ("2".equals(this.a)) {
            str = str.concat("nativepage/user/hisquestion");
            stringBuilder.append("userId=").append(bundle.getString("URL_BUILD_PERE_UID"));
        }
        return cVar.a(str, stringBuilder.toString());
    }

    public String x() {
        return this.a;
    }

    public void a(b bVar) {
        super.a(bVar);
    }

    public boolean addMore(a aVar) {
        if (aVar instanceof e) {
            a(this.k, ((e) aVar).m());
        }
        return super.addMore(aVar);
    }

    private void a(List<com.qq.reader.module.bookstore.qnative.card.a> list, List<com.qq.reader.module.bookstore.qnative.card.a> list2) {
        if ("1".equals(this.a) && list != null && list2 != null) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (list.contains((com.qq.reader.module.bookstore.qnative.card.a) it.next())) {
                    it.remove();
                }
            }
        }
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        super.a(jSONObject, jSONObject2);
        String toLowerCase = jSONObject.optString("type").toLowerCase();
        JSONArray optJSONArray = jSONObject2.optJSONArray(toLowerCase);
        int i = 0;
        com.qq.reader.module.bookstore.qnative.card.a aVar = null;
        while (i < optJSONArray.length()) {
            com.qq.reader.module.bookstore.qnative.card.a audioQuestionCardOfMineQA;
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if ("myquestion".equals(toLowerCase)) {
                audioQuestionCardOfMineQA = new AudioQuestionCardOfMineQA(this, toLowerCase);
            } else if ("mylisten".equals(toLowerCase)) {
                audioQuestionCardOfMineQA = new AudioComCardOfMyListen(this, toLowerCase);
                ((AudioComBaseCardDisablePlay) audioQuestionCardOfMineQA).config(1);
            } else if ("hisquestion".equals(toLowerCase)) {
                audioQuestionCardOfMineQA = new AudioComCardOfHisQuestion(this, toLowerCase);
                ((AudioComBaseCardDisablePlay) audioQuestionCardOfMineQA).config(1);
            } else {
                audioQuestionCardOfMineQA = aVar;
            }
            if (audioQuestionCardOfMineQA != null) {
                audioQuestionCardOfMineQA.fillData(optJSONObject);
                audioQuestionCardOfMineQA.setEventListener(l());
                this.k.add(audioQuestionCardOfMineQA);
                this.l.put(audioQuestionCardOfMineQA.getCardId(), audioQuestionCardOfMineQA);
            }
            i++;
            aVar = audioQuestionCardOfMineQA;
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
        if (i == 1006 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                AudioData audioData = (AudioData) extras.getParcelable("AUDIO_DATA");
                for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
                    String toLowerCase = aVar.getType().toLowerCase();
                    if ("myquestion".equals(toLowerCase) || "mylisten".equals(toLowerCase) || "hisquestion".equals(toLowerCase)) {
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

    public Class c() {
        return NativePageFragmentforQA.class;
    }
}
