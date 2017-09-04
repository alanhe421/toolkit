package org.greenrobot.greendao.query;

import android.database.Cursor;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;

public class CountQuery<T> extends AbstractQuery<T> {
    private final QueryData<T> queryData;

    private static final class QueryData<T2> extends AbstractQueryData<T2, CountQuery<T2>> {
        private QueryData(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }

        protected CountQuery<T2> createQuery() {
            return new CountQuery(this, this.dao, this.sql, (String[]) this.initialValues.clone());
        }
    }

    public /* bridge */ /* synthetic */ AbstractQuery setParameter(int i, Object obj) {
        return super.setParameter(i, obj);
    }

    static <T2> CountQuery<T2> create(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return (CountQuery) new QueryData(abstractDao, str, AbstractQuery.toStringArray(objArr)).forCurrentThread();
    }

    private CountQuery(QueryData<T> queryData, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.queryData = queryData;
    }

    public CountQuery<T> forCurrentThread() {
        return (CountQuery) this.queryData.forCurrentThread(this);
    }

    public long count() {
        checkThread();
        Database database = this.dao.getDatabase();
        String str = this.sql;
        long j = this.parameters;
        Cursor rawQuery = database.rawQuery(str, j);
        try {
            if (!rawQuery.moveToNext()) {
                throw new DaoException("No result for count");
            } else if (!rawQuery.isLast()) {
                throw new DaoException("Unexpected row count: " + rawQuery.getCount());
            } else if (rawQuery.getColumnCount() != 1) {
                throw new DaoException("Unexpected column count: " + rawQuery.getColumnCount());
            } else {
                j = rawQuery.getLong(0);
                return j;
            }
        } finally {
            rawQuery.close();
        }
    }
}
