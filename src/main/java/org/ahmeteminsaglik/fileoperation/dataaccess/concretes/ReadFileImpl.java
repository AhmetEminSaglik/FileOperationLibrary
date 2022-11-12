package org.ahmeteminsaglik.fileoperation.dataaccess.concretes;

import org.ahmeteminsaglik.fileoperation.dataaccess.abstracts.AbstractReadFile;

public class ReadFileImpl extends AbstractReadFile {

    @Override
    public void read() {
        try {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                readDataList.add(data);
            }
            reader.close();
        } catch (Exception e) {
            /*TODO
             * if a file is not found then this this error will be added to a error list and after all files'
             *  read, the errors will be append to something like errorFile.txt
             * */

            System.err.println("Exception Occured: Could not read the file : Error Message : " + e.getMessage());

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
