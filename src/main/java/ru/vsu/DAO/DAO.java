package ru.vsu.DAO;


import java.util.List;

public interface DAO<T> {

    List<T> getStorage();

    boolean isFileArchiveNameInStorage(String name);

    void addFileArchiveToStorage(T fileArchive);

    T getFileArchiveByNameInStorage(String filearchivename);

    boolean removeFileArchiveByNameInStorage(String filearchivename);

    boolean removeFileByNameInStorage(String filename);

    boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename);

//    здесь методы которые потом будут работать с двумя разными типами данных
}
