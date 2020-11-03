package ru.vsu.Domain;

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

    public FileArchive(String name, List<File> files) {
        this.name = name;
        this.files = new ArrayList<>();
        this.files.addAll(files);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    private String filesToString(){
        return files.toString();
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void addFile(File file){
        files.add(file);
    }


    public void addFiles(List<File> newfiles){
        files.addAll(newfiles);
    }

    @Override
    public String toString() {
        return "FileArchive{" +
                "name='" + name + '\'' +
                ", files=" + filesToString() +
                '}';
    }
}
