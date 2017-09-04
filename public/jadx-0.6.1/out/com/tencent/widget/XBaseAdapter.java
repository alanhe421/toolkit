package com.tencent.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;

public abstract class XBaseAdapter extends BaseAdapter implements XListAdapter {
    private final ListDataSetObservable mDataSetObservable = new ListDataSetObservable();

    interface ListDataSetListener {
        void onRowDeleted(int i, int i2);

        void onRowDeleted(int... iArr);

        void onRowInserted(int i, int i2);

        void onRowUpdated(int i, int i2);
    }

    class ListDataSetObservable extends DataSetObservable {
        ListDataSetObservable() {
        }

        public void notifyRowInserted(int i, int i2) {
            synchronized (this.mObservers) {
                for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                    DataSetObserver dataSetObserver = (DataSetObserver) this.mObservers.get(size);
                    if (dataSetObserver instanceof ListDataSetListener) {
                        ((ListDataSetListener) dataSetObserver).onRowInserted(i, i2);
                    } else {
                        dataSetObserver.onChanged();
                    }
                }
            }
        }

        public void notifyRowDeleted(int i, int i2) {
            synchronized (this.mObservers) {
                for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                    DataSetObserver dataSetObserver = (DataSetObserver) this.mObservers.get(size);
                    if (dataSetObserver instanceof ListDataSetListener) {
                        ((ListDataSetListener) dataSetObserver).onRowDeleted(i, i2);
                    } else {
                        dataSetObserver.onChanged();
                    }
                }
            }
        }

        public void notifyRowDeleted(int... iArr) {
            synchronized (this.mObservers) {
                for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                    DataSetObserver dataSetObserver = (DataSetObserver) this.mObservers.get(size);
                    if (dataSetObserver instanceof ListDataSetListener) {
                        ((ListDataSetListener) dataSetObserver).onRowDeleted(iArr);
                    } else {
                        dataSetObserver.onChanged();
                    }
                }
            }
        }

        public void notifyRowUpdated(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                DataSetObserver dataSetObserver = (DataSetObserver) this.mObservers.get(size);
                if (dataSetObserver instanceof ListDataSetListener) {
                    ((ListDataSetListener) dataSetObserver).onRowUpdated(i, i2);
                } else {
                    dataSetObserver.onChanged();
                }
            }
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyDataSetChanged() {
        this.mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        this.mDataSetObservable.notifyInvalidated();
    }

    public void notifyRowInserted(int i, int i2) {
        this.mDataSetObservable.notifyRowInserted(i, i2);
    }

    public void notifyRowDeleted(int i, int i2) {
        this.mDataSetObservable.notifyRowDeleted(i, i2);
    }

    public void notifyRowsDeleted(int... iArr) {
        this.mDataSetObservable.notifyRowDeleted(iArr);
    }

    public void notifyRowUpdated(int i, int i2) {
        this.mDataSetObservable.notifyRowUpdated(i, i2);
    }
}
