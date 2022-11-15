package org.ahmeteminsaglik.fileoperation.dataaccess.abstracts;

import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;
import org.ahmeteminsaglik.fileoperation.utilities.ErrorResult;
import org.ahmeteminsaglik.fileoperation.utilities.Result;
import org.ahmeteminsaglik.fileoperation.utilities.SuccessResult;

import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractWriteFile {

    protected FileFundamental fileFundamental;
    private boolean appendEnable;

    protected AbstractWriteFile(FileFundamental fileFundamental) {
        this.fileFundamental = fileFundamental;
    }

    public abstract Result write(String text);

    public abstract Result append(String text);

    public abstract Result appendNextLine(String text);

    public FileFundamental getFileFundamental() {
        return fileFundamental;
    }

    public void setFileFundamental(FileFundamental fileFundamental) {
        this.fileFundamental = fileFundamental;
    }

    protected final Result doProcess(String text) {
        try {
            String filename = fileFundamental.getCompletePath();
            try (FileWriter fw = new FileWriter(filename, isAppendEnable());) {//the true will append the new data otherwise false overwrites data
                fw.write(text);
            }
            return  new SuccessResult();
        } catch (IOException ex) {
            System.err.println("IOException: " + ex.getMessage());
            return  new ErrorResult(ex.getMessage());
        } catch (NullPointerException ex) {
            System.err.println("IOException: " + ex.getMessage());
            return  new ErrorResult(ex.getMessage());
        }
    }


    protected void setAppendEnable(boolean appendEnable) {
        this.appendEnable = appendEnable;
    }

    public boolean isAppendEnable() {
        return appendEnable;
    }
}
