package org.ahmeteminsaglik.fileoperation.business.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.util.List;


/**
 * A library to process read and write functions */
public class FileOperationFacade implements ReadFileService, WriteFileService {
   private FileOperationService fileOperationService;
//    private WriteFileService writeFileService;
//    private ReadFileService readFileService;

/*    public FileOperationFacade(WriteFileService writeFileService, ReadFileService readFileService) {
//        fileOperationService = new FileOperationManagement(writeFileService, readFileService);
        this.writeFileService = writeFileService;
        this.readFileService = readFileService;
    }*/

    public FileOperationFacade(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    @Override
    public void read(FileFundamental fileFund) {
        fileOperationService.getReadFileService().read(fileFund);
    }

   /* @Override
    public void read() {
        readFileService.read();
    }*/

    @Override
    public void read(List<FileFundamental> files) {
        fileOperationService.getReadFileService().read(files);
    }

    @Override
    public List<String> getReadDataList() {
        return fileOperationService.getReadFileService().getReadDataList();
    }

    @Override
    public void clearList() {
        fileOperationService.getReadFileService().clearList();
    }

   /* @Override
    public void write(String text) {
        writeFileService.write(text);
    }

    @Override
    public void write(List<String> textList) {
        writeFileService.write(textList);
    }
*/
    @Override
    public void write(FileFundamental fileFund, String text) {
        fileOperationService.getWriteFileService().write(fileFund, text);
    }

    @Override
    public void write(FileFundamental fileFund, List<String> textList) {
        fileOperationService.getWriteFileService().write(fileFund, textList);
    }

/*
    @Override
    public void append(String text) {
        writeFileService.append(text);
    }

    @Override
    public void append(List<String> textList) {
        writeFileService.append(textList);
    }
*/

    @Override
    public void append(FileFundamental fileFund, String text) {
        fileOperationService.getWriteFileService().append(fileFund, text);
    }

    @Override
    public void append(FileFundamental fileFund, List<String> textList) {
        fileOperationService.getWriteFileService().append(fileFund, textList);
    }

    public FileOperationService getFileOperationService() {
        return fileOperationService;
    }

    public void setFileOperationService(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }
}
