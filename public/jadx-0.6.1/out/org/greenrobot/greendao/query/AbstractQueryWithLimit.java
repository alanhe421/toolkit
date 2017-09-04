package org.greenrobot.greendao.query;

import java.util.Date;
import org.greenrobot.greendao.AbstractDao;

abstract class AbstractQueryWithLimit<T> extends AbstractQuery<T> {
    protected final int limitPosition;
    protected final int offsetPosition;

    protected AbstractQueryWithLimit(AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr);
        this.limitPosition = i;
        this.offsetPosition = i2;
    }

    public AbstractQueryWithLimit<T> setParameter(int i, Object obj) {
        if (i < 0 || (i != this.limitPosition && i != this.offsetPosition)) {
            return (AbstractQueryWithLimit) super.setParameter(i, obj);
        }
        throw new IllegalArgumentException("Illegal parameter index: " + i);
    }

    public AbstractQueryWithLimit<T> setParameter(int i, Date date) {
        return setParameter(i, date != null ? Long.valueOf(date.getTime()) : null);
    }

    public AbstractQueryWithLimit<T> setParameter(int i, Boolean bool) {
        Object valueOf;
        if (bool != null) {
            valueOf = Integer.valueOf(bool.booleanValue() ? 1 : 0);
        } else {
            valueOf = null;
        }
        return setParameter(i, valueOf);
    }

    public void setLimit(int i) {
        checkThread();
        if (this.limitPosition == -1) {
            throw new IllegalStateException("Limit must be set with QueryBuilder before it can be used here");
        }
        this.parameters[this.limitPosition] = Integer.toString(i);
    }

    public void setOffset(int i) {
        checkThread();
        if (this.offsetPosition == -1) {
            throw new IllegalStateException("Offset must be set with QueryBuilder before it can be used here");
        }
        this.parameters[this.offsetPosition] = Integer.toString(i);
    }
}
