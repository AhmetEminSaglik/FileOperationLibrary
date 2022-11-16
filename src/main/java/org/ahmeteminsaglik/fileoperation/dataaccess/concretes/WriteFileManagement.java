package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractWriteFile;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.util.List;

public class WriteFileManagement extends FileOperation implements WriteFileService {

    public WriteFileManagement() {
    }

    public WriteFileManagement(FileFundamental fileFund) {
        super(fileFund);
    }

    @Override
    public void write(String text) {
        if (fileFund != null) {
            write(fileFund, text);
        } else {
            System.err.println("ERROR MESSAGE EKLENECEK");
        }
    }

    @Override
    public void write(List<String> textList) {
        if (fileFund != null) {
            writeFirstIndex(fileFund, textList);
            appendListTextInForLoop(fileFund, 1, textList);
        } else {
            System.err.println("ERROR MESSAGE EKLENECEK");
        }

    }

    AbstractWriteFile writeFile = new WriteFileImpl(fileFund);

    @Override
    public void write(FileFundamental fileFund, String text) {
        writeFile.setFileFundamental(fileFund);
        writeFile.write(text);
    }

    @Override
    public void write(FileFundamental fileFund, List<String> textList) {
        writeFirstIndex(fileFund, textList);
        appendListTextInForLoop(fileFund, 1, textList);
    }

    @Override
    public void append(String text) {
        if (fileFund != null) {
            append(fileFund, text);
        } else {
            System.err.println("ERROR MESSAGE EKLENECEK");
        }
    }

    @Override
    public void append(List<String> textList) {
        if (fileFund != null) {
            appendListTextInForLoop(fileFund, 0,textList);
        } else {
            System.err.println("ERROR MESSAGE EKLENECEK");
        }
    }

    @Override
    public void append(FileFundamental fileFund, String text) {
        AbstractWriteFile writeFile = new WriteFileImpl(fileFund);
        writeFile.appendNextLine(text);
    }

    @Override
    public void append(FileFundamental fileFund, List<String> textList) {
        appendListTextInForLoop(fileFund, 0, textList);
    }

    private void appendListTextInForLoop(FileFundamental fileFund, int startIndex, List<String> textList) {
        for (int i = startIndex; i < textList.size(); i++) {
            append(fileFund, textList.get(i));
        }
    }

    private void writeFirstIndex(FileFundamental fileFund, List<String> textList) {
        if (!textList.isEmpty()) {
            write(fileFund, textList.get(0));
        } else {
            System.err.println("Could not write anything to file because TEXTLIST is empty");
        }
    }
}
