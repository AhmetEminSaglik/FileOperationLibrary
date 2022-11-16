package org.ahmeteminsaglik.fileoperation.business.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.util.List;


/**
 * A library to process read and write functions
 */
public class FileOperationFacade implements ReadFileService, WriteFileService {
    private FileOperationService fileOperationService;
//    private WriteFileService writeFileService;
//    private ReadFileService readFileService;

    public FileOperationFacade(WriteFileService writeFileService, ReadFileService readFileService) {
        fileOperationService = new FileOperationManagement(writeFileService, readFileService);
//        this.writeFileService = writeFileService;
//        this.readFileService = readFileService;
    }

    public void setWriteFileService(WriteFileService writeFileService) {
        fileOperationService.setWriteFileService(writeFileService);
    }

    public void setReadFileService(ReadFileService readFileService) {
        fileOperationService.setReadFileService(readFileService);
    }

    public FileOperationFacade(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    @Override
    public void read() {
        fileOperationService.getReadFileService().read();
    }

    @Override
    public void read(FileFundamental fileFund) {
        fileOperationService.getReadFileService().read(fileFund);
    }

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

    @Override
    public void write(String text) {
        fileOperationService.getWriteFileService().write(text);
    }

    @Override
    public void write(List<String> textList) {
        fileOperationService.getWriteFileService().write(textList);
    }

    @Override
    public void write(FileFundamental fileFund, String text) {
        fileOperationService.getWriteFileService().write(fileFund, text);
    }

    @Override
    public void write(FileFundamental fileFund, List<String> textList) {
        System.out.println("AA");
        fileOperationService.getWriteFileService().write(fileFund, textList);
        System.out.println("BB");
    }


    @Override
    public void append(String text) {
        fileOperationService.getWriteFileService().append(text);
    }

    @Override
    public void append(List<String> textList) {
        fileOperationService.getWriteFileService().append(textList);
    }

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
