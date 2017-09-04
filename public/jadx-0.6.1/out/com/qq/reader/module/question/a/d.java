package com.qq.reader.module.question.a;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.question.card.AudioQuestionCardOfAuthorQA;
import com.qq.reader.module.question.card.EmptyCardOfAuthorQA;
import com.qq.reader.module.question.card.HeaderCardOfAuthorQA;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.fragment.NativePageFragmentforQA;
import com.tencent.feedback.proguard.R;
import com.tencent.mm.performance.WxPerformanceHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfAuthorQA */
public class d extends af {
    List<a> a = new ArrayList();
    long b;
    boolean c;
    private int d;
    private HeaderCardOfAuthorQA e;

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        this.d = bundle.getInt("PAGE_STATUS_KEY", 0);
        long j = bundle.getLong("KEY_PAGEINDEX", 1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.a);
        stringBuilder.append("nativepage/aqa/authorhome");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("?");
        stringBuilder2.append("view=");
        switch (this.d) {
            case 0:
                stringBuilder2.append("all_waiting&pagestamp_all=0&pagestamp_waiting=0");
                break;
            case 1:
                stringBuilder2.append("all&pagestamp_all=");
                stringBuilder2.append(j);
                break;
            case 2:
                stringBuilder2.append("waiting&pagestamp_waiting=");
                stringBuilder2.append(j);
                break;
        }
        if ("nextpage".equals(bundle.getString("URL_BUILD_PERE_SIGNAL"))) {
            this.c = true;
        } else {
            this.d = bundle.getInt("PAGE_STATUS_INIT_KEY", 2);
        }
        return cVar.a(stringBuilder.toString(), stringBuilder2.toString());
    }

    public void b(int i) {
        this.d = i;
        if (this.e != null) {
            this.e.setPageStatus(i);
        }
    }

    public int x() {
        return this.d;
    }

    public d(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        if (this.a != null) {
            this.a.clear();
        }
        super.b(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("authorInfo");
        this.b = (long) jSONObject.optInt("pagestamp_waiting");
        this.o = (long) jSONObject.optInt("pagestamp_all");
        com.qq.reader.common.monitor.debug.c.e(WxPerformanceHandle.MESSAGE_TAG, "mPage waitingPageStamp = " + this.b + "   mPagestamp = " + this.o);
        if (!this.c && optJSONObject != null) {
            if (this.e == null) {
                this.e = new HeaderCardOfAuthorQA(this, "HeaderCardOfAuthorQA");
                this.e.setEventListener(l());
                this.e.setPageStatus(this.d);
            }
            this.e.fillData(optJSONObject);
        }
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        super.a(jSONObject, jSONObject2);
        String optString = jSONObject.optString("type");
        JSONArray optJSONArray = jSONObject2.optJSONArray(optString);
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            AudioQuestionCardOfAuthorQA audioQuestionCardOfAuthorQA = new AudioQuestionCardOfAuthorQA(this, optString);
            if (audioQuestionCardOfAuthorQA != null) {
                audioQuestionCardOfAuthorQA.setEventListener(l());
                audioQuestionCardOfAuthorQA.fillData(optJSONObject);
                this.l.put(audioQuestionCardOfAuthorQA.getCardId(), audioQuestionCardOfAuthorQA);
                if ("all".equals(optString.toLowerCase())) {
                    this.k.add(audioQuestionCardOfAuthorQA);
                } else if ("waiting".equals(optString)) {
                    this.a.add(audioQuestionCardOfAuthorQA);
                }
            }
            i++;
        }
    }

    public Class c() {
        return NativePageFragmentforQA.class;
    }

    public boolean s() {
        if (this.d == 1) {
            if (this.o > 0) {
                return true;
            }
            return false;
        } else if (this.d != 2 || this.b > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void a(b bVar) {
        long j = this.o;
        long j2 = this.b;
        super.a(bVar);
        this.o = j;
        this.b = j2;
        d dVar = (d) bVar;
        this.a.clear();
        this.a.addAll(dVar.a);
        this.b = dVar.b;
        this.o = dVar.o;
        this.e = dVar.e;
        b(((d) bVar).d);
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        if (!(aVar instanceof d)) {
            return false;
        }
        long j;
        long j2 = this.o;
        long j3 = this.b;
        this.a.addAll(((d) aVar).a);
        if (this.d == 1) {
            long j4 = j3;
            j3 = ((d) aVar).o;
            j = j4;
        } else if (this.d == 2) {
            j = ((d) aVar).b;
            j3 = j2;
        } else {
            j = j3;
            j3 = j2;
        }
        boolean addMore = super.addMore(aVar);
        this.o = j3;
        this.b = j;
        return addMore;
    }

    public long r() {
        if (this.d == 1) {
            return this.o;
        }
        if (this.d == 2) {
            return this.b;
        }
        return 0;
    }

    public List<a> m() {
        EmptyCardOfAuthorQA emptyCardOfAuthorQA;
        if (this.d == 1) {
            if (!this.c) {
                if (!(this.e == null || this.k.contains(this.e))) {
                    this.k.add(0, this.e);
                    this.l.put(this.e.getCardId(), this.e);
                }
                if (!this.l.containsKey(ReaderApplication.getApplicationImp().getString(R.string.author_center_fenda_no_questions)) && this.k.size() == 1) {
                    emptyCardOfAuthorQA = new EmptyCardOfAuthorQA(this, EmptyCardOfAuthorQA.class.getSimpleName(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_fenda_no_questions), R.drawable.empty12);
                    this.k.add(emptyCardOfAuthorQA);
                    this.l.put(ReaderApplication.getApplicationImp().getString(R.string.author_center_fenda_no_questions), emptyCardOfAuthorQA);
                }
            }
            return this.k;
        } else if (this.d != 2) {
            return new ArrayList();
        } else {
            if (!this.c) {
                if (!(this.e == null || this.a.contains(this.e))) {
                    this.a.add(0, this.e);
                    this.l.put(this.e.getCardId(), this.e);
                }
                if (!this.l.containsKey(ReaderApplication.getApplicationImp().getString(R.string.author_center_fenda_no_waiting_questions)) && this.a.size() == 1) {
                    emptyCardOfAuthorQA = new EmptyCardOfAuthorQA(this, EmptyCardOfAuthorQA.class.getSimpleName(), ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_fenda_no_waiting_questions), R.drawable.empty12);
                    this.a.add(emptyCardOfAuthorQA);
                    this.l.put(ReaderApplication.getApplicationImp().getString(R.string.author_center_fenda_no_waiting_questions), emptyCardOfAuthorQA);
                }
            }
            return this.a;
        }
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
        if (i == 1004 && intent != null) {
            HeaderCardOfAuthorQA headerCardOfAuthorQA = this.e;
            headerCardOfAuthorQA.getAuthorData().a(intent.getStringExtra("userIntroduce"));
            headerCardOfAuthorQA.getAuthorData().a(Integer.valueOf(intent.getStringExtra("userQuestionPrice")).intValue());
            headerCardOfAuthorQA.attachView();
        } else if (i == 1006 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                AudioData audioData = (AudioData) extras.getParcelable("AUDIO_DATA");
                if (audioData != null && audioData.a().j() != 0) {
                    a aVar;
                    if (audioData.c() == 1 && this.e != null) {
                        this.e.addIncomeByAnswer(audioData.a().i());
                    }
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        aVar = (a) it.next();
                        if ("all".equals(aVar.getType()) || "waiting".equals(aVar.getType())) {
                            try {
                                boolean isDataChanged;
                                try {
                                    isDataChanged = ((com.qq.reader.module.question.card.a) aVar).isDataChanged(audioData);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    isDataChanged = false;
                                }
                                if (isDataChanged) {
                                    it.remove();
                                    break;
                                }
                                continue;
                            } catch (Exception e2) {
                                try {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                    for (a aVar2 : this.k) {
                        if ("all".equals(aVar2.getType()) || "waiting".equals(aVar2.getType())) {
                            try {
                                if (((com.qq.reader.module.question.card.a) aVar2).isDataChanged(audioData)) {
                                    return;
                                }
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a() {
        return false;
    }

    public boolean y() {
        return this.o > 0 || this.b > 0;
    }

    public boolean b() {
        return true;
    }

    public void q() {
        super.q();
        if (this.e != null) {
            this.e.refresh();
        }
        for (a refresh : this.a) {
            refresh.refresh();
        }
    }

    public int z() {
        int i = 1;
        int size = this.a.size();
        if (size <= 0) {
            return this.a.size();
        }
        if (size >= 2) {
            if (!(this.a.get(0) instanceof HeaderCardOfAuthorQA)) {
                i = 0;
            }
            if (this.a.get(size - 1) instanceof EmptyCardOfAuthorQA) {
                i++;
            }
        } else if (!(this.a.get(0) instanceof HeaderCardOfAuthorQA)) {
            i = 0;
        }
        return size - i;
    }
}
