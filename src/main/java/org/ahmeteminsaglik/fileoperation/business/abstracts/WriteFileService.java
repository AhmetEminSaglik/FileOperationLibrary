package org.ahmeteminsaglik.fileoperation.business.abstracts;


import org.ahmeteminsaglik.fileoperation.entities.concretes.FileFundamental;

import java.util.List;

public interface WriteFileService {

    /**
     * write text to  as defined <b> FileFundamental in FileOperation</b> file.
     */
    void write(String text);

    /**
     * write textList to  as defined <b> FileFundamental in FileOperation</b> file.
     */
    void write(List<String> textList);

    /**
     * write text to destination file.
     */
    void write(FileFundamental fileFund, String text);

    /**
     * write textList to destination file.
     */
    void write(FileFundamental fileFund, List<String> textList);

    /**
     * append text to  as defined <b> FileFundamental in FileOperation</b> file.
     */
    void append(String text);

    /**
     * append textList to  as defined <b> FileFundamental in FileOperation</b> file.
     */
    void append(List<String> textList);

    /**
     * append text to destination file.
     */
    void append(FileFundamental fileFund, String text);

    /**
     * append textList to destination file.
     */
    void append(FileFundamental fileFund, List<String> textList);

}
