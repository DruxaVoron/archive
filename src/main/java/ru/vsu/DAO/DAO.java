package ru.vsu.DAO;


import ru.vsu.Domain.FileArchive;

import java.util.List;

public interface DAO<T> {

    List<T> getStorage();

    boolean isFileArchiveNameinStorage(String name);

    void addFileArchivetoStorage(T fileArchive);

    T getFileArchivebyNameinStorage(String filearchivename);

    boolean removeFileArchivebyNameinStorage(String filearchivename);

    boolean removeFilebyNameinStorage(String filename);

    boolean removeFileinFileArchivebyNameinStorage(String filearchivename, String filename);

//    здесь методы которые потом будут работать с двумя разными типами данных
}
