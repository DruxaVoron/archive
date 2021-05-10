package ru.vsu.archive.Domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "file_archive")
public class FileArchive {

    @Id
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "file_archive",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
