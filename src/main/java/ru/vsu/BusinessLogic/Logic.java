package ru.vsu.BusinessLogic;

import ru.vsu.DAO.DAO;
import ru.vsu.DAO.DAOList;
import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.util.*;

public class Logic{

//    private static List<FileArchive> storage = new DAOList().getStorage();
    private DAO<FileArchive> dao;

    public Logic(DAO<FileArchive> dao) {
        this.dao = dao;
    }

//    private DAOList dao = new DAOList();
    public List<FileArchive> getAll(){
        return dao.getStorage();
    }

    public boolean createFileArchive(String name){
        if (!dao.isFileArchiveNameinStorage(name)){
            FileArchive fileArchive = new FileArchive(name);
            dao.addFileArchivetoStorage(fileArchive);
            return true;
        } else
            return false;
    }

    private boolean createFileinArchive(String filearchivename, File file) {
        if (!dao.isFileArchiveNameinStorage(filearchivename)){
            FileArchive fileArchive = new FileArchive(filearchivename, file);
            dao.addFileArchivetoStorage(fileArchive);
            return true;
        } else {
            FileArchive filearchive = dao.getFileArchivebyNameinStorage(filearchivename);
            boolean result = filearchive.getFiles().stream().anyMatch(filefounder -> filefounder.getName().equals(file.getName()));
            if (result)
                return false;
            else {
                filearchive.addFile(file);
                return true;
            }
        }
    }

    public boolean createFileinArchive(String filearchivename, String filename, Date creationdate) {
        return createFileinArchive(filearchivename, new File(filename, creationdate));
    }

    public void createFilesinArchive(String filearchivename, List<File> files) {
        if (!dao.isFileArchiveNameinStorage(filearchivename)){
            FileArchive fileArchive = new FileArchive(filearchivename, files);
            dao.addFileArchivetoStorage(fileArchive);
// добавить проверку на то что файлы с названиями уже существуют
        } else {
            FileArchive filearchive = dao.getFileArchivebyNameinStorage(filearchivename);
            filearchive.addFiles(files);
        }
    }

    public boolean createFile(String filename, Date creationdate) {
        return createFileinArchive("untitled", new File(filename, creationdate));
    }

    public void createFilesbyList(List<File> files) { //должен быть boolean как тот что выше
        createFilesinArchive("untitled", files);
    }

    public FileArchive getFileArchivebyName(String filearchivename) {
        return dao.getFileArchivebyNameinStorage(filearchivename);
    }

    public boolean deleteFileArchivebyName(String filearchivename) {
        return dao.removeFileArchivebyNameinStorage(filearchivename);
    }

    public List deleteFileArchivesbyList(List<FileArchive> filearchives) {
        List<FileArchive> notfound = new ArrayList<>();
        for (Iterator<FileArchive> iter = filearchives.iterator(); iter.hasNext();) {
            FileArchive farch = iter.next();
            boolean result = dao.removeFileArchivebyNameinStorage(farch.getName());
            if (result){
                iter.remove();
            } else {
                notfound.add(farch);
            }
        }
        if (!notfound.isEmpty())
            return Arrays.asList(0, notfound);
        else return Arrays.asList(0, null);
    }

    public boolean deleteFileinFileArchivebyName(String filearchivename, String filename) {
        return dao.removeFileinFileArchivebyNameinStorage(filearchivename, filename);
    }

    public List deleteFilesbyList(List<File> files) {
        List<File> notfound = new ArrayList<>();
        for (Iterator<File> iter = files.iterator(); iter.hasNext();) {
            File file = iter.next();
            boolean result = dao.removeFilebyNameinStorage(file.getName());
            if (result){
                iter.remove();
            } else {
                notfound.add(file);
            }
        }
        if (!notfound.isEmpty())
            return Arrays.asList(0, notfound);
        else return Arrays.asList(0, null);
    }

//    добавить вывод всех архивов
}
