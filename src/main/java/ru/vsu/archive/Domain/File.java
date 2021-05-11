package ru.vsu.archive.Domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "file")
public class File {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "creationdate")
    private Date creationdate;
//    @ManyToOne
//    @JoinColumn(name = "archive_id")
//    private FileArchive fileArchive;

    public File(String name) {
        this.name = name;
        creationdate = new Date();
    }

    public File(String name, String date){
        this.name = name;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            creationdate = dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Date parsing failed...");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(creationdate);
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", creationdate=" + creationdate +
                '}';
    }
}
