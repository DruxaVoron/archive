package ru.vsu.BusinessLogic;

import ru.vsu.DAO.DAO;
import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.util.*;

public class Logic{

    private DAO<FileArchive> dao;

    public Logic(DAO<FileArchive> dao) {
        this.dao = dao;
    }

    public List<FileArchive> getAll(){
        return dao.getStorage();
    }

    public boolean createFileArchive(String name){
        if (!dao.isFileArchiveNameInStorage(name)){
            FileArchive fileArchive = new FileArchive(name);
            dao.addFileArchiveToStorage(fileArchive);
            return true;
        } else
            return false;
    }

    private boolean createFileinArchive(String filearchivename, File file) {
        if (!dao.isFileArchiveNameInStorage(filearchivename)){
            FileArchive fileArchive = new FileArchive(filearchivename, file);
            dao.addFileArchiveToStorage(fileArchive);
            return true;
        } else {
            boolean result = dao.isFileNameInArchive(filearchivename, file.getName());
            if (result)
                return false;
            else {
                dao.addFileToStorage(filearchivename, file.getName(), file.getDate());
                return true;
            }
        }
    }

    public boolean createFileinArchive(String filearchivename, String filename) {
        return createFileinArchive(filearchivename, new File(filename));
    }

    public boolean deleteFileArchivebyName(String filearchivename) {
        return dao.removeFileArchiveByNameInStorage(filearchivename);
    }

    public boolean deleteFileinFileArchivebyName(String filearchivename, String filename) {
        return dao.removeFileInFileArchiveByNameInStorage(filearchivename, filename);
    }

}
