package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractWriteFile;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

public class WriteFileImpl extends AbstractWriteFile {
    public WriteFileImpl(FileFundamental fileFundamental) {
        super(fileFundamental);
    }

    @Override
    public void write(String text) {
        setAppendEnable(false);
        doProcess(text);
    }

    @Override
    public void append(String text) {
        setAppendEnable(true);
        doProcess(text);
    }

    @Override
    public void appendNextLine(String text) {
        text="\n"+text;
        append(text);
    }
}
