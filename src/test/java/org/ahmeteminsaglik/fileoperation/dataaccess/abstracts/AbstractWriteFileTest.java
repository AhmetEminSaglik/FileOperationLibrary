package org.ahmeteminsaglik.fileoperation.dataaccess.abstracts;

import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.business.concretes.FileOperationFacade;
import org.ahmeteminsaglik.fileoperation.business.concretes.FileOperationManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.ReadFileManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileImpl;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileManagement;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;
import org.ahmeteminsaglik.fileoperation.utilities.Result;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWriteFileTest {
    /**
     * TODO
     * How to test code? Should it be  tested every function in every class or should it be tested after whole process?
     * Most of the testing examples are about testing sum function of a calculater class. But mostly in project the functions alone maybe meaningless. After a few process
     * they produce real things. So how to test exactly? If anyone helps I would be appreciate
     */
    final static String TEST_FILE_TO_WRITE = "test-write-file";
    final static String TEST_FILE_T0_APPEND = "test-append-file";
    static FileOperationFacade fileOperationFacade;

    @BeforeAll
    public static void prepareFileOperationFacade() {
        WriteFileService writeFileService = new WriteFileManagement();
        ReadFileService readFileService = new ReadFileManagement();
        FileOperationService fileOperationService = new FileOperationManagement(writeFileService, readFileService);
        fileOperationFacade = new FileOperationFacade(fileOperationService);
    }

    @BeforeEach
    public void prepareTestFileToTest() {
        createFileForAppendTest();
        createFileForWriteTest();
    }

    @Test
    public void testWriteFileImpSuccessfulTestCase() {
        AbstractWriteFile writeFile = new WriteFileImpl();
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        writeFile.setFileFundamental(fileFund);

        ReadFileManagement readFileManagement = new ReadFileManagement();
        readFileManagement.setFileFund(fileFund);

        readFileManagement.read();
        int lineNumberBeforeWrite = readFileManagement.getReadDataList().size();
        readFileManagement.clearList();
        Assertions.assertEquals(6, lineNumberBeforeWrite);

        String text = "testing write";
        writeFile.write(text);
        readFileManagement.read();
        int lineNumberAfterWrite = readFileManagement.getReadDataList().size();

        Assertions.assertEquals(1, lineNumberAfterWrite);
    }

/*    @Test
    public void testWriteFileImpErrorTestCaseNullFileFund() {
        WriteFileImpl writeFile = new WriteFileImpl();
        String text = "error Test case";
        Result result = writeFile.write(text);
        assertFalse(result.isSuccess());
        Assertions.assertEquals("Cannot invoke \"org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental.getCompletePath()\" because \"this.fileFundamental\" is null",
                result.getMsg());

    }*/

    @Test
    public void testWriteFileImpErrorTestCaseInvalidFileFundPath() {

        FileFundamental fileFund = new FileFundamental().setPath("G:\\invalid Path 2").setFileName("test").setFileExtension(".txt");
        WriteFileImpl writeFile = new WriteFileImpl();
        writeFile.setFileFundamental(fileFund);
        Assertions.assertEquals(fileFund.getCompletePath(), writeFile.getFileFundamental().getCompletePath());

        String text = "error Test case";
        Result result = writeFile.write(text);
        assertFalse(result.isSuccess());
    }


    private FileFundamental getFileFundByFileName(String fileName) {
        FileFundamental fileFund = new FileFundamental();
        fileFund.setFileName(fileName);
        fileFund.setPath("src\\main\\java\\resources\\");
        fileFund.setFileExtension(".txt");
        return fileFund;
    }

    private void createFileForAppendTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        String textForAppendFile = "added this line before append anything to this file";
        fileOperationFacade.write(fileFund, textForAppendFile);
        System.out.println("FILE CREATED");
    }

    private void createFileForWriteTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        String textForWriteFile = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5\nLine 6";
        fileOperationFacade.write(fileFund, textForWriteFile);
    }
}