package ru.vsu.UI;

import ru.vsu.BusinessLogic.Logic;
import ru.vsu.Domain.FileArchive;

import java.util.Date;
import java.util.List;

class UI {

    private Logic logic = new Logic();


    public void createFileArchive(String name){
        if (logic.createFileArchive(name))
            System.out.println("Created successful");
        else
            System.out.println("FileArchive with this name already exist");

    }


    public void createFileinArchive(String filearchivename, String filename, Date creationdate) {
        if (logic.createFileinArchive(filearchivename, filename, creationdate))
            System.out.println("Created successful");
        else
            System.out.println("File with such name already exist in this FileArchive. Please rename the File");
    }


    public void createFilesinArchive(String filearchivename, List files) {

    }


    public void createFile(String filename, Date creationdate) {

    }


    public void createFilesbyList(List files) {

    }


    public FileArchive getFileArchivebyName(String filearchivename) {
        return null;
    }


    public boolean deleteFileArchivebyName(String filearchivename) {
        return false;
    }


    public List deleteFileArchivesbyList(List filearchives) {
        return null;
    }


    public boolean deleteFilebyName(String filename) {
        return false;
    }


    public List deleteFilesbyList(List files) {
        return null;
    }

}
