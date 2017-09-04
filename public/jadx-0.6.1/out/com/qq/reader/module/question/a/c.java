package com.qq.reader.module.question.a;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.question.card.AudioComCardOfQuestionQuiz;
import com.qq.reader.module.question.card.AudioQuestionQuizCard;
import com.qq.reader.module.question.card.AudioTitleCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfAudioQutionQuiz */
public class c extends af {
    public static final String a = c.class.getSimpleName();
    public String b;
    public boolean c = false;
    public int d = 0;
    public int e;

    public c(Bundle bundle) {
        super(bundle);
        this.o = (long) bundle.getInt("audio_pagestamp", 1);
    }

    public String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.cv);
        stringBuilder.append("aid=").append(bundle.getLong("audio_authorid"));
        stringBuilder.append("&pagestamp=").append(bundle.getInt("audio_pagestamp", 0));
        return stringBuilder.toString();
    }

    public void a(b bVar) {
        super.a(bVar);
        this.c = ((c) bVar).c;
        this.b = ((c) bVar).b;
        this.e = ((c) bVar).e;
        this.d = ((c) bVar).d;
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        try {
            if (this.o == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("quizinfo");
                if (optJSONObject != null) {
                    AudioQuestionQuizCard audioQuestionQuizCard = new AudioQuestionQuizCard(this, "");
                    audioQuestionQuizCard.fillData(optJSONObject);
                    audioQuestionQuizCard.setEventListener(l());
                    this.k.add(audioQuestionQuizCard);
                    this.l.put(audioQuestionQuizCard.getCardId(), audioQuestionQuizCard);
                    this.b = audioQuestionQuizCard.getAuthorName();
                    this.e = audioQuestionQuizCard.getState();
                }
                optJSONObject = jSONObject.optJSONObject("titleinfo");
                if (optJSONObject != null) {
                    AudioTitleCard audioTitleCard = new AudioTitleCard(this, "");
                    audioTitleCard.fillData(optJSONObject);
                    audioTitleCard.setEventListener(l());
                    this.k.add(audioTitleCard);
                    this.l.put(audioTitleCard.getCardId(), audioTitleCard);
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("qaList");
            if (optJSONArray != null) {
                boolean z;
                int length = optJSONArray.length();
                this.d = length;
                if (length >= 20) {
                    z = true;
                } else {
                    z = false;
                }
                this.c = z;
                while (i < length) {
                    a audioComCardOfQuestionQuiz = new AudioComCardOfQuestionQuiz(this, "");
                    audioComCardOfQuestionQuiz.fillData(optJSONArray.get(i));
                    audioComCardOfQuestionQuiz.setEventListener(l());
                    this.k.add(audioComCardOfQuestionQuiz);
                    this.l.put(audioComCardOfQuestionQuiz.getCardId(), audioComCardOfQuestionQuiz);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean s() {
        return this.c;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        c cVar = (c) aVar;
        this.k.addAll(cVar.k);
        this.l.putAll(cVar.l);
        this.c = cVar.c;
        this.o = cVar.o;
        return true;
    }

    public boolean a() {
        return false;
    }
}
