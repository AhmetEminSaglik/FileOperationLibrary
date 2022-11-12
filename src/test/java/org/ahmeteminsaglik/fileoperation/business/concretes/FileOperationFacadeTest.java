package org.ahmeteminsaglik.fileoperation.business.concretes;

import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.ReadFileManagement;
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
//    FileFundamental fileFund;

    @BeforeAll
    public void prepareFileOperationFacade() {
        WriteFileService writeFileService = new WriteFileManagement();
        ReadFileService readFileService = new ReadFileManagement();
        fileOperationFacade = new FileOperationFacade(writeFileService, readFileService);
    }

    /*@BeforeEach
    public void prepareFileFundConfig() {
        String path = "src\\main\\resources\\";
        String fileName = "TestFile";
        String fileExtension = ".txt";
        fileFund = new FileFundamental().setPath(path).setFileName(fileName).setFileExtension(fileExtension);
//        ConfigFileFundamental.setPath(path);
//        ConfigFileFundamental.setFileName(fileName);
//        ConfigFileFundamental.setExtension(fileExtension);
//        System.out.println("ConfigFileFundamental complete : "+ConfigFileFundamental.getFileFundamental().getCompletePath());

    }
*/
//    @ParameterizedTest
//    @MethodSource("getFileFundEmptyFileToRead")

    @BeforeEach
    public void clearReadDataList() {
        fileOperationFacade.clearList();
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
    public void writeFunctinWithGivenTextAndFileFund() {
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
/*
    private static FileFundamental getFileFundFilledFileToRead() {
        String path = "src\\main\\resources";
        String fileName = "test-read-filled-file";
        String fileExtension = ".txt";
        FileFundamental fileFund = new FileFundamental().setPath(path).
                setFileName(fileName).
                setFileExtension(fileExtension);
        return fileFund;
    }*/


}