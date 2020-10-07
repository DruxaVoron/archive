package ru.vsu.Domain;

import java.util.Date;

public class File {

    private String name;
    private Date creationdate;

    public File(String name, Date creationdate) {
        this.name = name;
        this.creationdate = creationdate;
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

}
