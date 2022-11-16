package org.ahmeteminsaglik.fileoperation.business.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;

public class FileOperationManagement extends FileOperationService {
    public FileOperationManagement(WriteFileService writeFileService, ReadFileService readFileService) {
        super(writeFileService, readFileService);
    }

}
