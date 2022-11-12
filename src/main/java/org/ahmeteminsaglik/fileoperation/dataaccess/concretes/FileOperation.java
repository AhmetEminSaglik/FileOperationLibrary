package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

public abstract class FileOperation {
    protected FileFundamental defaultFileFund;
    protected FileOperation() {
     //   this.defaultFileFund = ConfigFileFundamental.getFileFundamental();
    }
}
