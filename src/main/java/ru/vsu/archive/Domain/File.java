package ru.vsu.archive.Domain;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File {

    private String name;
    private Date creationdate;

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
