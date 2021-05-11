package ru.vsu.archive.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.archive.Domain.File;
import ru.vsu.archive.Domain.FileArchive;

import java.util.List;

@Component
public class BLogic {

//    @Autowired
    private FileService fileService;
//    @Autowired
    private FileArchiveService fileArchiveService;

    @Autowired
    public BLogic(FileService fileService, FileArchiveService fileArchiveService) {
        this.fileService = fileService;
        this.fileArchiveService = fileArchiveService;
    }

    public List<FileArchive> getAll(){
        return fileArchiveService.listAll();
    }

    public boolean createFileArchive(String name){
//        if (!fileArchiveService.exist()){
//            FileArchive fileArchive = new FileArchive(name);
//            dao.addFileArchiveToStorage(fileArchive);
//            return true;
//        } else
//            return false;
        return true;
    }

    private boolean createFileinArchive(String filearchivename, File file) {
//        if (!dao.isFileArchiveNameInStorage(filearchivename)){
//            FileArchive fileArchive = new FileArchive(filearchivename, file);
//            dao.addFileArchiveToStorage(fileArchive);
//            return true;
//        } else {
//            boolean result = dao.isFileNameInArchive(filearchivename, file.getName());
//            if (result)
//                return false;
//            else {
//                dao.addFileToStorage(filearchivename, file.getName(), file.getDate());
//                return true;
//            }
//        }
        return true;
    }

    public boolean createFileinArchive(String filearchivename, String filename) {
//        return createFileinArchive(filearchivename, new File(filename));
        return true;
    }

    public boolean deleteFileArchivebyName(String filearchivename) {
//        return dao.removeFileArchiveByNameInStorage(filearchivename);
        return true;
    }

    public boolean deleteFileinFileArchivebyName(String filearchivename, String filename) {
//        return dao.removeFileInFileArchiveByNameInStorage(filearchivename, filename);
        return true;
    }
}
