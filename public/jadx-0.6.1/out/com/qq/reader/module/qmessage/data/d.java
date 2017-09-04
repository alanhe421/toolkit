package com.qq.reader.module.qmessage.data;

import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MessagePage */
public class d {
    public static final Comparator<MessageBaseCard> a = new Comparator<MessageBaseCard>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((MessageBaseCard) obj, (MessageBaseCard) obj2);
        }

        public int a(MessageBaseCard messageBaseCard, MessageBaseCard messageBaseCard2) {
            if (messageBaseCard == null || messageBaseCard2 == null) {
                return 0;
            }
            long messageTime = messageBaseCard2.getMessageTime();
            long messageTime2 = messageBaseCard.getMessageTime();
            if (messageTime == messageTime2) {
                return 0;
            }
            if (messageTime > messageTime2) {
                return 1;
            }
            return -1;
        }
    };
    private List<MessageBaseCard> b = new ArrayList();
    private long c = 0;
    private long d = 0;
    private boolean e = false;
    private a f = null;

    protected d() {
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean a() {
        return this.e;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public a b() {
        return this.f;
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        if ((this.b == null || this.b.size() == 0) && str.length() > 0) {
            try {
                this.b = a.a(new JSONObject(str).getJSONArray("messages"), b());
                Collections.sort(this.b, a);
                b(this.b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void b(List<MessageBaseCard> list) {
        if (list != null) {
            for (MessageBaseCard messageTime : list) {
                long messageTime2 = messageTime.getMessageTime();
                if (this.c == 0 || this.d == 0) {
                    this.c = messageTime2;
                    this.d = messageTime2;
                } else {
                    long j;
                    if (this.c < messageTime2) {
                        j = this.c;
                    } else {
                        j = messageTime2;
                    }
                    this.c = j;
                    if (this.d > messageTime2) {
                        messageTime2 = this.d;
                    }
                    this.d = messageTime2;
                }
            }
        }
    }

    public void a(List<MessageBaseCard> list) {
        a(list, false);
    }

    public void a(List<MessageBaseCard> list, boolean z) {
        if (z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.b.add(0, list.get(size));
            }
            return;
        }
        this.b.addAll(list);
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.c;
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null) {
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    MessageBaseCard a = a.a(new JSONObject((String) it.next()), b());
                    if (a != null) {
                        this.b.add(a);
                    }
                }
                Collections.sort(this.b, a);
                b(this.b);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<MessageBaseCard> e() {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        return this.b;
    }
}
