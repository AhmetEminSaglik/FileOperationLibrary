package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

public abstract class FileOperation {
    protected FileFundamental fileFund;
    protected FileOperation() {
        //   this.defaultFileFund = ConfigFileFundamental.getFileFundamental();
    }
    protected FileOperation(FileFundamental fileFund) {
        this.fileFund=fileFund;
    }

    public FileFundamental getFileFund() {
        return fileFund;
    }

    public void setFileFund(FileFundamental fileFund) {
        this.fileFund = fileFund;
    }

}
