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

//    private DAOList dao = new DAOList();
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
            FileArchive filearchive = dao.getFileArchiveByNameInStorage(filearchivename);
            boolean result = filearchive.getFiles().stream().anyMatch(filefounder -> filefounder.getName().equals(file.getName()));
            if (result)
                return false;
            else {
                filearchive.addFile(file);
                return true;
            }
        }
    }

    public boolean createFileinArchive(String filearchivename, String filename) {
        return createFileinArchive(filearchivename, new File(filename));
    }

    public void createFilesinArchive(String filearchivename, List<File> files) {
        if (!dao.isFileArchiveNameInStorage(filearchivename)){
            FileArchive fileArchive = new FileArchive(filearchivename, files);
            dao.addFileArchiveToStorage(fileArchive);
// добавить проверку на то что файлы с названиями уже существуют
        } else {
            FileArchive filearchive = dao.getFileArchiveByNameInStorage(filearchivename);
            filearchive.addFiles(files);
        }
    }

    public boolean createFile(String filename) {
        return createFileinArchive("untitled", new File(filename));
    }

    public void createFilesbyList(List<File> files) { //должен быть boolean как тот что выше
        createFilesinArchive("untitled", files);
    }

    public FileArchive getFileArchivebyName(String filearchivename) {
        return dao.getFileArchiveByNameInStorage(filearchivename);
    }

    public boolean deleteFileArchivebyName(String filearchivename) {
        return dao.removeFileArchiveByNameInStorage(filearchivename);
    }

    public List deleteFileArchivesbyList(List<FileArchive> filearchives) {
        List<FileArchive> notfound = new ArrayList<>();
        for (Iterator<FileArchive> iter = filearchives.iterator(); iter.hasNext();) {
            FileArchive farch = iter.next();
            boolean result = dao.removeFileArchiveByNameInStorage(farch.getName());
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
        return dao.removeFileInFileArchiveByNameInStorage(filearchivename, filename);
    }

    public List deleteFilesbyList(List<File> files) {
        List<File> notfound = new ArrayList<>();
        for (Iterator<File> iter = files.iterator(); iter.hasNext();) {
            File file = iter.next();
            boolean result = dao.removeFileByNameInStorage(file.getName());
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

}
