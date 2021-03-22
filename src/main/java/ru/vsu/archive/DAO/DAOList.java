package ru.vsu.archive.DAO;

import ru.vsu.archive.Domain.FileArchive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DAOList implements DAO<FileArchive> {

    private static DAOList instanse;

    private List<FileArchive> storage = new ArrayList<>();

    public DAOList() {
    }

    public static DAOList getInstanse() {
        if (instanse == null){
            instanse = new DAOList();
        }
        return instanse;
    }

    private void init(){

    }

    public List<FileArchive> getStorage() {
        return storage;
    }

    public void addFileArchiveToStorage(FileArchive fileArchive){
        storage.add(fileArchive);
    }

    @Override
    public void addFileToStorage(String filearchivename, String filename, String creationdate) {

    }

    public boolean isFileArchiveNameInStorage(String name){
        Stream<FileArchive> stream = storage.stream();
        return stream.anyMatch(archive -> archive.getName().equals(name));
    }

    @Override
    public boolean isFileNameInArchive(String filearchivename, String filename) {
        return false;
    }

    public boolean removeFileArchiveByNameInStorage(String name){
        if (!isFileArchiveNameInStorage(name))
            return false;
        else
            return storage.removeIf(fileArchive -> fileArchive.getName().equals(name));
    }

    public boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename){
        boolean result = false;
        for (FileArchive filearchive : storage) {
            if (filearchive.getName().equals(filearchivename)){
                result = filearchive.getFiles().removeIf(file -> file.getName().equals(filename));
            }
            if (result)
                break;
        }
        return result;
    }
}

