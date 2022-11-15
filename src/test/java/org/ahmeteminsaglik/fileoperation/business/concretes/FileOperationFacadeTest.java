package org.ahmeteminsaglik.fileoperation.business.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractWriteFile;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.ReadFileManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileImpl;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileManagement;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileOperationFacadeTest {
    FileOperationFacade fileOperationFacade;
    final static String TEST_EMPTY_FILE_TO_READ = "test-read-empty-file";
    final static String TEST_FILLED_FILE_TO_READ = "test-read-filled-file";
    final static String TEST_FILLED_FILE_TO_READ_2 = "test-read-filled-file-2";
    final static String TEST_FILE_T0_APPEND = "test-append-file";
    final static String TEST_FILE_TO_WRITE = "test-write-file";

    @BeforeAll
    public void prepareFileOperationFacade() {
        WriteFileService writeFileService = new WriteFileManagement();
        ReadFileService readFileService = new ReadFileManagement();
        FileOperationService fileOperationService = new FileOperationManagement(writeFileService, readFileService);
        fileOperationFacade = new FileOperationFacade(fileOperationService);
    }

    @BeforeEach
    public void prepareTestFilesToTest() {

        createEmptyFileForReadTest();
        createFilledFileForReadTest();
        createFilledFile2ForReadTest();
        createFileForAppendTest();
        createFileForWriteTest();

//        FileFundamental fileFund;
//        fileFund = getFileFundByFileName(TEST_EMPTY_FILE_TO_READ);
//        fileOperationFacade.write(fileFund, "");

//        fileFund = getFileFundByFileName(TEST_FILLED_FILE_TO_READ);
//        String textForReadFilledFile = "Added this\nwords to read\nfrom file";
//        fileOperationFacade.write(fileFund, textForReadFilledFile);


//        fileFund = getFileFundByFileName(TEST_FILLED_FILE_TO_READ_2);
//        String textForReadFilledFile2 = "Added this words to read from file";
//        fileOperationFacade.write(fileFund, textForReadFilledFile2);


//        fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
//        String textForAppendFile = "added this line before append anything to this file";
//        fileOperationFacade.write(fileFund, textForAppendFile);


//        fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
//        String textForWriteFile = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5\nLine 6";
//        fileOperationFacade.write(fileFund, textForWriteFile);
    }


    private void createEmptyFileForReadTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_EMPTY_FILE_TO_READ);
        fileOperationFacade.write(fileFund, "");
    }

    private void createFilledFileForReadTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILLED_FILE_TO_READ);
        String textForReadFilledFile = "Added this\nwords to read\nfrom file";
        fileOperationFacade.write(fileFund, textForReadFilledFile);
    }

    private void createFilledFile2ForReadTest() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILLED_FILE_TO_READ_2);
        String textForReadFilledFile2 = "Added this words to read from file";
        fileOperationFacade.write(fileFund, textForReadFilledFile2);
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

    @BeforeEach
    public void clearReadDataList() {
        fileOperationFacade.clearList();
    }


    @Test
    public void readFileNotFoundErrorTestCase() {
        FileFundamental fileFund = getFileFundByFileName("(INVALID FILE NAME FOR TEST)");
        fileOperationFacade.read(fileFund);
//        int actualDataListSize = fileOperationFacade.getReadDataList().size();
//        Assertions.assertEquals(0, actualDataListSize);
    }

    @Test
    public void readFunctionGivenFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_EMPTY_FILE_TO_READ);
        int actualDataListSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(0, actualDataListSize);

        fileOperationFacade.read(fileFund);
        actualDataListSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(0, actualDataListSize);
    }

    @Test
    public void readFunctionEmptyFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName("INVALID-FILE-NAME");
        fileOperationFacade.read(fileFund);
//        int actualDataListSize = fileOperationFacade.getReadDataList().size();
//        Assertions.assertEquals(0, actualDataListSize);
//
//        fileOperationFacade.read(fileFund);
//        actualDataListSize = fileOperationFacade.getReadDataList().size();
//        Assertions.assertEquals(0, actualDataListSize);
    }

    @Test
    public void readFunctionGivenListFileFundTestCase() {
        FileFundamental fileFund1 = getFileFundByFileName(TEST_FILLED_FILE_TO_READ);
        FileFundamental fileFund2 = getFileFundByFileName(TEST_FILLED_FILE_TO_READ_2);
        List<FileFundamental> fileFundList = new ArrayList<>();
        fileFundList.add(fileFund1);
        fileFundList.add(fileFund2);

        int readDataSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(0, readDataSize);

        fileOperationFacade.read(fileFundList);
        int totalReadFileLineSize = 4;
        readDataSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(totalReadFileLineSize, readDataSize);
    }

    @Test
    public void writeFunctionWithGivenTextListAndFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        List<String> textList = new ArrayList<>();
        textList.add("Line 1");
        textList.add("Line 2");
        textList.add("Line 3 is added");
        fileOperationFacade.write(fileFund, textList);

        /* TODO FileOperationFacade'da FileFund koyarim. Eger null degilse  write() read() calisir
         *   ama bu facade ile olacak bir sey. Ana bileseni interface acisindan yeterli degil */

        int expectedLineSize = 3;
        fileOperationFacade.read(fileFund);
        int actualLineSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(expectedLineSize, actualLineSize);
    }

    @Test
    public void writeFunctionWithGivenEmptyTextListAndFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        List<String> textList = new ArrayList<>();
        int expectedLineSize = 6;

        fileOperationFacade.write(fileFund, textList);
        fileOperationFacade.read(fileFund);
        int actualLineSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(expectedLineSize, actualLineSize);
    }


    @Test
    public void writeFunctionWithGivenTextAndFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        String text = "Line 1 is added";
        fileOperationFacade.write(fileFund, text);
        int expectedLineSize = 1;
        fileOperationFacade.read(fileFund);
        int actualLineSize = fileOperationFacade.getReadDataList().size();
        Assertions.assertEquals(expectedLineSize, actualLineSize);
    }

    @Test
    public void appendFunctionWithGivenTextAndFileFundTestCase() {


        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        fileOperationFacade.read(fileFund);
        int lineSizeBeforeAppend = fileOperationFacade.getReadDataList().size();
        int expectedLineSizeBeforeAppend = 1;

        String errMsg = "Test data is not created clearly." + TEST_FILE_T0_APPEND + " file has to have 1 line text";
        Assertions.assertEquals(expectedLineSizeBeforeAppend, lineSizeBeforeAppend, errMsg);
        fileOperationFacade.clearList();

        String text = "This text will append to file";
        fileOperationFacade.append(fileFund, text);
        fileOperationFacade.read(fileFund);

        int lineSizeAfterAppend = fileOperationFacade.getReadDataList().size();
        int expectedLineSizeAfterAppend = 2;
        Assertions.assertEquals(expectedLineSizeAfterAppend, lineSizeAfterAppend, "More then expected line is added in Append process.");
    }


    @Test
    public void appendFunctionWithGivenTextListAndFileFundTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        fileOperationFacade.read(fileFund);
        int lineSizeBeforeAppend = fileOperationFacade.getReadDataList().size();
        int expectedLineSizeBeforeAppend = 1;


        Assertions.assertEquals(expectedLineSizeBeforeAppend, lineSizeBeforeAppend,
                "Test data is not created clearly." + TEST_FILE_T0_APPEND + " file has to have 1 line text");

        fileOperationFacade.clearList();

        List<String> textList = new ArrayList<>();
        textList.add("This");
        textList.add("text");
        textList.add("will");
        textList.add("append");
        textList.add("to");
        textList.add("file");

        fileOperationFacade.append(fileFund, textList);
        fileOperationFacade.read(fileFund);

        int lineSizeAfterAppend = fileOperationFacade.getReadDataList().size();
        int expectedLineSizeAfterAppend = 7;
        Assertions.assertEquals(expectedLineSizeAfterAppend, lineSizeAfterAppend, "More then expected line is added in Append process.");
    }

    @Test
    public void fileFundCompletePathTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        String completePath = fileFund.getPath() + fileFund.getFileName() + fileFund.getExtension();
        Assertions.assertEquals(completePath, fileFund.getCompletePath());
    }

    @Test
    public void fileOperationFacadeGetterSetterFuntionTestCase() {
        FileOperationService FOservice = fileOperationFacade.getFileOperationService();
        Assertions.assertNotNull(FOservice);

        fileOperationFacade.setFileOperationService(null);
        FileOperationService FOServiceNull = fileOperationFacade.getFileOperationService();
        Assertions.assertNull(FOServiceNull);

        fileOperationFacade.setFileOperationService(FOservice);

        WriteFileService newWriteService = new WriteFileManagement();
        fileOperationFacade.getFileOperationService().setWriteFileService(newWriteService);
        Assertions.assertEquals(newWriteService, fileOperationFacade.getFileOperationService().getWriteFileService());

        ReadFileService newReadFileService = new ReadFileManagement();
        fileOperationFacade.getFileOperationService().setReadFileService(newReadFileService);
        Assertions.assertEquals(newReadFileService, fileOperationFacade.getFileOperationService().getReadFileService());
    }

    @Test
    public void fileFundGetterSetterInWriteFileImplFileTestCase() {
        FileFundamental fileFund = getFileFundByFileName(TEST_FILE_TO_WRITE);
        AbstractWriteFile writeFileImp = new WriteFileImpl(fileFund);
        fileFund = getFileFundByFileName(TEST_FILE_T0_APPEND);
        writeFileImp.setFileFundamental(fileFund);
        Assertions.assertEquals(fileFund, writeFileImp.getFileFundamental());
    }

    @Test
    public void writeFileNotFoundErrorTestCase() {
        FileFundamental fileFund = getFileFundByFileName("(INVALID FILE NAME FOR TEST)");
        AbstractWriteFile writeFileImp = new WriteFileImpl(fileFund);
        writeFileImp.write("test");
    }

    private FileFundamental getFileFundByFileName(String fileName) {
        FileFundamental fileFund = new FileFundamental();
        fileFund.setFileName(fileName);
        fileFund.setPath("src\\main\\resources\\");
        fileFund.setFileExtension(".txt");
        return fileFund;
    }

   /* private static List<FileFundamental> getFileFundEmptyFileToRead() {

        String path = "src\\main\\resources\\";
        String fileName = "test-read-empty-file";
        String fileExtension = ".txt";
        FileFundamental fileFund = new FileFundamental().setPath(path).
                setFileName(fileName).
                setFileExtension(fileExtension);

        List<FileFundamental> list = new ArrayList();
        list.add(fileFund);
        return list;
    }
 */
}