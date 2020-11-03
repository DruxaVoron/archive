package ru.vsu.Domain;

import java.util.Date;

public class File {

    private String name;
    private Date creationdate;

    public File(String name) {
        this.name = name;
        creationdate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", creationdate=" + creationdate +
                '}';
    }
}
