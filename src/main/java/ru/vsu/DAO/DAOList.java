package ru.vsu.DAO;

import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.util.ArrayList;
import java.util.Iterator;
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

    public boolean isFileArchiveNameInStorage(String name){
        Stream<FileArchive> stream = storage.stream();
        return stream.anyMatch(archive -> archive.getName().equals(name));
    }

    public FileArchive getFileArchiveByNameInStorage(String name){
        Stream<FileArchive> stream = storage.stream();
        return stream.filter(archive -> archive.getName().equals(name)).findFirst().get();
        // наверное не совсем правильно работает надо переделать
    }

    public boolean removeFileArchiveByNameInStorage(String name){
        if (!isFileArchiveNameInStorage(name))
            return false;
        else
            return storage.removeIf(fileArchive -> fileArchive.getName().equals(name));
    }

    public boolean removeFileByNameInStorage(String name){
        boolean result = false;
        for (FileArchive filearchive : storage) {
            result = filearchive.getFiles().removeIf(file -> file.getName().equals(name));
            if (result)
                break;
        }
        return result;
    }

    public boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename){
        boolean result = false;
//        storage.stream().filter(fileArchive -> fileArchive.getName().equals(filearchivename));
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

