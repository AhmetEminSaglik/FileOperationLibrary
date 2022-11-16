package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractReadFile;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileManagement extends FileOperation implements ReadFileService {
    List<String> listData = new ArrayList<>(); // keeps all read data even if they are in different files.
    AbstractReadFile readFileImp = new ReadFileImpl();

    public ReadFileManagement() {
        super();
    }

    public ReadFileManagement(FileFundamental fileFund) {
        super(fileFund);
    }

    @Override
    public void read() {
        read(fileFund);
    }

    @Override
    public void read(FileFundamental fileFund) {
        try {
            readFileImp.prepareFileToRead(fileFund);
            readFileImp.read();
            listData.addAll(readFileImp.getReadDataList());
            readFileImp.clearList();
        } catch (FileNotFoundException e) {
            System.err.println("FILE NOT FOUND EXCEPTION : "+e.getMessage());
            /** TODO
             *  an error and solutioun should be add here
             *  and remove throw error here*/
//            throw new RuntimeException(e);
        }
    }

    /*@Override
    public void read() {
        read(defaultFileFund);
    }*/

    @Override
    public void read(List<FileFundamental> files) {
        for (FileFundamental fileFund : files) {
            read(fileFund);
        }
    }

    @Override
    public List<String> getReadDataList() {
        return listData;
    }

    @Override
    public void clearList() {
        listData.clear();
    }
}
