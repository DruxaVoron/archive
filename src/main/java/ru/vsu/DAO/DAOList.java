package ru.vsu.DAO;

import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class DAOList implements DAO {

    private List<FileArchive> storage = new ArrayList<>();

    public List<FileArchive> getStorage() {
        return storage;
    }

    public void setStorage(List<FileArchive> storage) {
        this.storage = storage;
    }

    public void addFileArchivetoStorage(FileArchive fileArchive){
        storage.add(fileArchive);
    }

    public boolean isFileArchiveNameinStorage(String name){
        Stream<FileArchive> stream = storage.stream();
        return stream.anyMatch(archive -> archive.getName().equals(name));
    }

    public FileArchive getFileArchivebyNameinStorage(String name){
        Stream<FileArchive> stream = storage.stream();
        return stream.filter(archive -> archive.getName().equals(name)).findFirst().get();
        // наверное не совсем правильно работает надо переделать
    }

    public List<File> getFilesinArchive(String filearchivename){
        return getFileArchivebyNameinStorage(filearchivename).getFiles();
    }

    public boolean removeFileArchivebyNameinStorage(String name){
        if (!isFileArchiveNameinStorage(name))
            return false;
        else
            return storage.removeIf(fileArchive -> fileArchive.getName().equals(name));
    }

    public boolean isFileinStorage(String name){
        boolean result = false;
        for (Iterator<FileArchive> iter = storage.iterator(); iter.hasNext();) {
            FileArchive filearchive = iter.next();
            Stream<File> stream = filearchive.getFiles().stream();
            result = stream.anyMatch(file -> file.getName().equals(name));
            if (result)
                break;
        }
        return result;
    }

    public boolean removeFilebyNameinStorage(String name){
        boolean result = false;
        for (FileArchive filearchive : storage) {
            result = filearchive.getFiles().removeIf(file -> file.getName().equals(name));
            if (result)
                break;
        }
        return result;
    }
}

