package org.greenrobot.greendao.query;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.rx.RxQuery;

public class QueryBuilder<T> {
    public static boolean LOG_SQL;
    public static boolean LOG_VALUES;
    private final AbstractDao<T, ?> dao;
    private boolean distinct;
    private final List<Join<T, ?>> joins;
    private Integer limit;
    private Integer offset;
    private StringBuilder orderBuilder;
    private String stringOrderCollation;
    private final String tablePrefix;
    private final List<Object> values;
    private final WhereCollector<T> whereCollector;

    public static <T2> QueryBuilder<T2> internalCreate(AbstractDao<T2, ?> abstractDao) {
        return new QueryBuilder(abstractDao);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao) {
        this(abstractDao, "T");
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao, String str) {
        this.dao = abstractDao;
        this.tablePrefix = str;
        this.values = new ArrayList();
        this.joins = new ArrayList();
        this.whereCollector = new WhereCollector(abstractDao, str);
        this.stringOrderCollation = " COLLATE NOCASE";
    }

    private void checkOrderBuilder() {
        if (this.orderBuilder == null) {
            this.orderBuilder = new StringBuilder();
        } else if (this.orderBuilder.length() > 0) {
            this.orderBuilder.append(",");
        }
    }

    public QueryBuilder<T> distinct() {
        this.distinct = true;
        return this;
    }

    public QueryBuilder<T> preferLocalizedStringOrder() {
        if (this.dao.getDatabase().getRawDatabase() instanceof SQLiteDatabase) {
            this.stringOrderCollation = " COLLATE LOCALIZED";
        }
        return this;
    }

    public QueryBuilder<T> stringOrderCollation(String str) {
        if (this.dao.getDatabase().getRawDatabase() instanceof SQLiteDatabase) {
            if (!(str == null || str.startsWith(" "))) {
                str = " " + str;
            }
            this.stringOrderCollation = str;
        }
        return this;
    }

    public QueryBuilder<T> where(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.whereCollector.add(whereCondition, whereConditionArr);
        return this;
    }

    public QueryBuilder<T> whereOr(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.whereCollector.add(or(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }

    public WhereCondition or(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public WhereCondition and(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public <J> Join<T, J> join(Class<J> cls, Property property) {
        return join(this.dao.getPkProperty(), cls, property);
    }

    public <J> Join<T, J> join(Property property, Class<J> cls) {
        AbstractDao dao = this.dao.getSession().getDao(cls);
        return addJoin(this.tablePrefix, property, dao, dao.getPkProperty());
    }

    public <J> Join<T, J> join(Property property, Class<J> cls, Property property2) {
        return addJoin(this.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    public <J> Join<T, J> join(Join<?, T> join, Property property, Class<J> cls, Property property2) {
        return addJoin(join.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    private <J> Join<T, J> addJoin(String str, Property property, AbstractDao<J, ?> abstractDao, Property property2) {
        Join<T, J> join = new Join(str, property, abstractDao, property2, "J" + (this.joins.size() + 1));
        this.joins.add(join);
        return join;
    }

    public QueryBuilder<T> orderAsc(Property... propertyArr) {
        orderAscOrDesc(" ASC", propertyArr);
        return this;
    }

    public QueryBuilder<T> orderDesc(Property... propertyArr) {
        orderAscOrDesc(" DESC", propertyArr);
        return this;
    }

    private void orderAscOrDesc(String str, Property... propertyArr) {
        for (Property property : propertyArr) {
            checkOrderBuilder();
            append(this.orderBuilder, property);
            if (String.class.equals(property.type) && this.stringOrderCollation != null) {
                this.orderBuilder.append(this.stringOrderCollation);
            }
            this.orderBuilder.append(str);
        }
    }

    public QueryBuilder<T> orderCustom(Property property, String str) {
        checkOrderBuilder();
        append(this.orderBuilder, property).append(' ');
        this.orderBuilder.append(str);
        return this;
    }

    public QueryBuilder<T> orderRaw(String str) {
        checkOrderBuilder();
        this.orderBuilder.append(str);
        return this;
    }

    protected StringBuilder append(StringBuilder stringBuilder, Property property) {
        this.whereCollector.checkProperty(property);
        stringBuilder.append(this.tablePrefix).append('.').append('\'').append(property.columnName).append('\'');
        return stringBuilder;
    }

    public QueryBuilder<T> limit(int i) {
        this.limit = Integer.valueOf(i);
        return this;
    }

    public QueryBuilder<T> offset(int i) {
        this.offset = Integer.valueOf(i);
        return this;
    }

    public Query<T> build() {
        StringBuilder createSelectBuilder = createSelectBuilder();
        int checkAddLimit = checkAddLimit(createSelectBuilder);
        int checkAddOffset = checkAddOffset(createSelectBuilder);
        String stringBuilder = createSelectBuilder.toString();
        checkLog(stringBuilder);
        return Query.create(this.dao, stringBuilder, this.values.toArray(), checkAddLimit, checkAddOffset);
    }

    public CursorQuery buildCursor() {
        StringBuilder createSelectBuilder = createSelectBuilder();
        int checkAddLimit = checkAddLimit(createSelectBuilder);
        int checkAddOffset = checkAddOffset(createSelectBuilder);
        String stringBuilder = createSelectBuilder.toString();
        checkLog(stringBuilder);
        return CursorQuery.create(this.dao, stringBuilder, this.values.toArray(), checkAddLimit, checkAddOffset);
    }

    private StringBuilder createSelectBuilder() {
        StringBuilder stringBuilder = new StringBuilder(SqlUtils.createSqlSelect(this.dao.getTablename(), this.tablePrefix, this.dao.getAllColumns(), this.distinct));
        appendJoinsAndWheres(stringBuilder, this.tablePrefix);
        if (this.orderBuilder != null && this.orderBuilder.length() > 0) {
            stringBuilder.append(" ORDER BY ").append(this.orderBuilder);
        }
        return stringBuilder;
    }

    private int checkAddLimit(StringBuilder stringBuilder) {
        if (this.limit == null) {
            return -1;
        }
        stringBuilder.append(" LIMIT ?");
        this.values.add(this.limit);
        return this.values.size() - 1;
    }

    private int checkAddOffset(StringBuilder stringBuilder) {
        if (this.offset == null) {
            return -1;
        }
        if (this.limit == null) {
            throw new IllegalStateException("Offset cannot be set without limit");
        }
        stringBuilder.append(" OFFSET ?");
        this.values.add(this.offset);
        return this.values.size() - 1;
    }

    public DeleteQuery<T> buildDelete() {
        if (this.joins.isEmpty()) {
            String tablename = this.dao.getTablename();
            StringBuilder stringBuilder = new StringBuilder(SqlUtils.createSqlDelete(tablename, null));
            appendJoinsAndWheres(stringBuilder, this.tablePrefix);
            tablename = stringBuilder.toString().replace(this.tablePrefix + ".\"", '\"' + tablename + "\".\"");
            checkLog(tablename);
            return DeleteQuery.create(this.dao, tablename, this.values.toArray());
        }
        throw new DaoException("JOINs are not supported for DELETE queries");
    }

    public CountQuery<T> buildCount() {
        StringBuilder stringBuilder = new StringBuilder(SqlUtils.createSqlSelectCountStar(this.dao.getTablename(), this.tablePrefix));
        appendJoinsAndWheres(stringBuilder, this.tablePrefix);
        String stringBuilder2 = stringBuilder.toString();
        checkLog(stringBuilder2);
        return CountQuery.create(this.dao, stringBuilder2, this.values.toArray());
    }

    private void checkLog(String str) {
        if (LOG_SQL) {
            DaoLog.d("Built SQL for query: " + str);
        }
        if (LOG_VALUES) {
            DaoLog.d("Values for query: " + this.values);
        }
    }

    private void appendJoinsAndWheres(StringBuilder stringBuilder, String str) {
        this.values.clear();
        for (Join join : this.joins) {
            stringBuilder.append(" JOIN ").append(join.daoDestination.getTablename()).append(' ');
            stringBuilder.append(join.tablePrefix).append(" ON ");
            SqlUtils.appendProperty(stringBuilder, join.sourceTablePrefix, join.joinPropertySource).append('=');
            SqlUtils.appendProperty(stringBuilder, join.tablePrefix, join.joinPropertyDestination);
        }
        Object obj = !this.whereCollector.isEmpty() ? 1 : null;
        if (obj != null) {
            stringBuilder.append(" WHERE ");
            this.whereCollector.appendWhereClause(stringBuilder, str, this.values);
        }
        Object obj2 = obj;
        for (Join join2 : this.joins) {
            if (!join2.whereCollector.isEmpty()) {
                if (obj2 == null) {
                    stringBuilder.append(" WHERE ");
                    obj2 = 1;
                } else {
                    stringBuilder.append(" AND ");
                }
                join2.whereCollector.appendWhereClause(stringBuilder, join2.tablePrefix, this.values);
            }
            obj2 = obj2;
        }
    }

    public List<T> list() {
        return build().list();
    }

    @Experimental
    public RxQuery<T> rx() {
        return build().__InternalRx();
    }

    @Experimental
    public RxQuery<T> rxPlain() {
        return build().__internalRxPlain();
    }

    public LazyList<T> listLazy() {
        return build().listLazy();
    }

    public LazyList<T> listLazyUncached() {
        return build().listLazyUncached();
    }

    public CloseableListIterator<T> listIterator() {
        return build().listIterator();
    }

    public T unique() {
        return build().unique();
    }

    public T uniqueOrThrow() {
        return build().uniqueOrThrow();
    }

    public long count() {
        return buildCount().count();
    }
}
