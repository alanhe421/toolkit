package com.qq.reader.readengine.kernel;

public enum PageIndex {
    previous,
    current,
    next,
    previous_left,
    previous_right,
    current_left,
    current_right,
    next_left,
    next_right;

    public PageIndex getNext() {
        switch (this) {
            case previous:
                return current;
            case current:
                return next;
            default:
                return null;
        }
    }

    public PageIndex getPrevious() {
        switch (this) {
            case current:
                return previous;
            case next:
                return current;
            default:
                return null;
        }
    }
}
