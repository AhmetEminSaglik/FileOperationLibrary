package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractWriteFile;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;
import org.ahmeteminsaglik.fileoperation.utilities.Result;

public class WriteFileImpl extends AbstractWriteFile {
    public WriteFileImpl(FileFundamental fileFundamental) {
        super(fileFundamental);
    }

    public WriteFileImpl() {
        super(null);
    }

    @Override
    public Result write(String text) {
        setAppendEnable(false);
        return doProcess(text);
    }

    @Override
    public Result append(String text) {
        setAppendEnable(true);
        return doProcess(text);
    }

    @Override
    public Result appendNextLine(String text) {
        text = "\n" + text;
        return append(text);
    }
}
