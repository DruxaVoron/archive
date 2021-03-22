package ru.vsu.archive.DAO;


import java.util.List;

public interface DAO<T> {

    List<T> getStorage();

    boolean isFileArchiveNameInStorage(String name);

    boolean isFileNameInArchive(String filearchivename, String filename);

    void addFileArchiveToStorage(T fileArchive);

    void addFileToStorage(String filearchivename, String filename, String creationdate);

    boolean removeFileArchiveByNameInStorage(String filearchivename);

    boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename);
}
