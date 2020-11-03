package ru.vsu.DAO;

import ru.vsu.Domain.FileArchive;

import java.util.List;

public class DAObase implements DAO<FileArchive> {

    public DAObase() {
    }

    @Override
    public List<FileArchive> getStorage() {
        return null;
    }

    @Override
    public boolean isFileArchiveNameInStorage(String name) {
        return false;
    }

    @Override
    public void addFileArchiveToStorage(FileArchive fileArchive) {

    }

    @Override
    public FileArchive getFileArchiveByNameInStorage(String filearchivename) {
        return null;
    }

    @Override
    public boolean removeFileArchiveByNameInStorage(String filearchivename) {
        return false;
    }

    @Override
    public boolean removeFileByNameInStorage(String filename) {
        return false;
    }

    @Override
    public boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename) {
        return false;
    }
}
