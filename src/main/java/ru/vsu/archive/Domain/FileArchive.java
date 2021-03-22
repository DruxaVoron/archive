package ru.vsu.archive.Domain;

import java.util.ArrayList;
import java.util.List;

public class FileArchive {

    private String name;
    private List<File> files;

    public FileArchive(String name) {
        this.name = name;
        files = new ArrayList<>();
    }

    public FileArchive(String name, File file) {
        this.name = name;
        files = new ArrayList<>();
        files.add(file);
    }

    public String getName() {
        return name;
    }

    public List<File> getFiles() {
        return files;
    }

    private String filesToString(){
        return files.toString();
    }

    public void addFile(File file){
        files.add(file);
    }

    @Override
    public String toString() {
        return "FileArchive{" +
                "name='" + name + '\'' +
                ", files=" + filesToString() +
                '}';
    }
}
