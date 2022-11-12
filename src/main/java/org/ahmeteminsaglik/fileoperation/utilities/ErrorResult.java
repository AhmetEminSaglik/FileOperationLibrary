package org.ahmeteminsaglik.fileoperation.utilities;

public class ErrorResult extends Result {
    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String msg) {
        super(false, msg);
    }
}
