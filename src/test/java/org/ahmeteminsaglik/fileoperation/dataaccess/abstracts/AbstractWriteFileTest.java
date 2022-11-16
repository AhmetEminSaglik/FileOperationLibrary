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
import org.ahmeteminsaglik.fileoperation.utilities.SuccessResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWriteFileTest {
    /*TODO
    *  class'rin hangisini ne sekilde test etmeliyim, mesela servisler her turlu kullaniliyor. Servisleri mi test etmeliyim yoksa servislerden implement eden managaementlarimi ?
    * hesap makinesinde 2-3 fonksiyonu 2-3 if else veya exceptionla test yapilirken goteriliyor maximum. Peki karmasik bir sistemde bunu nasil test edebiliriz.
    * Mesela sifreleme algoritmasi nasil test edebiliriz, banka islemini nasil test edebiliriz? Sadece para yatir isleminden sonra bakiye kontrolu mu yapmaliyuiz yoksa ayrica
    * o islemler icin kullanilan siniflar icinde ayri ayri testler yazilmali mi?
    * Yani evler daireler villalar yasanabilir diye kontrol etsek yeterli mi yoksa  villa, apartman, gecekondu,gokdelen her bir kati icin mi test etmeliyiz*/
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

    @Test
    public void testWriteFileImpErrorTestCaseNullFileFund() {
        WriteFileImpl writeFile = new WriteFileImpl();
        String text = "error Test case";
        Result result = writeFile.write(text);
        assertFalse(result.isSuccess());
        Assertions.assertEquals("Cannot invoke \"org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental.getCompletePath()\" because \"this.fileFundamental\" is null",
                result.getMsg());

    }

    @Test
    public void testWriteFileImpErrorTestCaseInvalidFileFundPath() {

        FileFundamental fileFund = new FileFundamental().setPath("G:\\invalid Path 2").setFileName("test").setFileExtension(".txt");
        WriteFileImpl writeFile = new WriteFileImpl();
        writeFile.setFileFundamental(fileFund);
        Assertions.assertEquals(fileFund.getCompletePath(),writeFile.getFileFundamental().getCompletePath());


        String text = "error Test case";
        Result result = writeFile.write(text);
        assertFalse(result.isSuccess());
       /* String errMsgExpected = fileFund.getCompletePath() + " (The system cannot find the path specified)";
        //The filename, directory name, or volume label syntax is incorrect
        Assertions.assertEquals(errMsgExpected, result.getMsg());

        fileFund.setPath(writeFile.getFileFundamental().getCompletePath()+" (The filename, directory name, or volume label syntax is incorrect)")*/
    }


    private FileFundamental getFileFundByFileName(String fileName) {
        FileFundamental fileFund = new FileFundamental();
        fileFund.setFileName(fileName);
        fileFund.setPath("src\\main\\resources\\");
        fileFund.setFileExtension(".txt");
        return fileFund;
    }

    private void createFileForAppendTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        String textForAppendFile = "added this line before append anything to this file";
        fileOperationFacade.write(fileFund, textForAppendFile);
    }

    private void createFileForWriteTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        String textForWriteFile = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5\nLine 6";
        fileOperationFacade.write(fileFund, textForWriteFile);
    }
}