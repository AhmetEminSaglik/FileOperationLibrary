import org.ahmeteminsaglik.fileoperation.business.abstracts.FileOperationService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.ReadFileService;
import org.ahmeteminsaglik.fileoperation.business.abstracts.WriteFileService;
import org.ahmeteminsaglik.fileoperation.business.concretes.FileOperationFacade;
import org.ahmeteminsaglik.fileoperation.business.concretes.FileOperationManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.ReadFileManagement;
import org.ahmeteminsaglik.fileoperation.dataaccess.concretes.WriteFileManagement;
import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

public class Main {
    public static void main(String[] args) {
        FileFundamental fileFundamental = new FileFundamental().setFileExtension("test").setPath("").setFileExtension(".txt");
        WriteFileService writeFileService = new WriteFileManagement(fileFundamental);
        ReadFileService readFileService = new ReadFileManagement(fileFundamental);
        FileOperationService fileOperationService = new FileOperationManagement(writeFileService, readFileService);
        FileOperationFacade fileOperationFacade = new FileOperationFacade(fileOperationService);
        String text = "şiş çözüm ı";
        System.out.println("TEXT : "+text);
        fileOperationFacade.write(text);
    }
}
