package ru.vsu.archive.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.vsu.archive.Domain.File;
import ru.vsu.archive.Domain.FileArchive;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
@Component
@Scope("prototype")
public class DAObase implements DAO<FileArchive> {

//    private static DAObase instanse;
    private Connection connection;
    @Value("${DAObase.url}")
    private String url;
    @Value("${DAObase.login}")
    private String user;
    @Value("${DAObase.password}")
    private String password;

    private void connect(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
    }

    @Override
    public List<FileArchive> getStorage() {
        connect();
        String command = "SELECT fa.name, f.name, f.creationdate FROM file_archive AS fa LEFT JOIN file AS f ON fa.archive_id = f.archive_id ORDER BY fa.name";
        List<FileArchive> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);

            while (resultSet.next()){
                Stream<FileArchive> stream = list.stream();
                boolean exist = stream.anyMatch(fileArchive -> {
                    try {
                        return fileArchive.getName().equals(resultSet.getString(1));
                    } catch (SQLException e) {
                        System.out.println("Something went wrong..." + e);
                        return false;
                    }
                });
                stream.close();
                if (exist){
                    Stream<FileArchive> stream1 = list.stream();
                    FileArchive fileArchive = stream1.filter(archive -> {
                        try {
                            return archive.getName().equals(resultSet.getString(1));
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }).findFirst().get();
                    fileArchive.addFile(new File(resultSet.getString(2), resultSet.getString(3)));
                    stream1.close();
                }
                else {
                    FileArchive fileArchive = new FileArchive(resultSet.getString(1));
                    String t = resultSet.getString(2);
                    if (resultSet.getString(2) == null){
                        list.add(fileArchive);
                    }
                    else {
                        fileArchive.addFile(new File(resultSet.getString(2), resultSet.getString(3)));
                        list.add(fileArchive);
                    }
                }
            }
            closeConnect();
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
        return list;
    }

    @Override
    public boolean isFileArchiveNameInStorage(String name) {
        String command = "SELECT EXISTS (SELECT * FROM file_archive WHERE name = '" + name + "')";
        return checkExisting(command);
    }

    @Override
    public boolean isFileNameInArchive(String filearchivename, String filename) {
        String command = "SELECT EXISTS (SELECT * FROM file WHERE name = '" + filename + "' AND (SELECT archive_id FROM file_archive WHERE name = '" + filearchivename + "') = archive_id)";
        return checkExisting(command);
    }

    private boolean checkExisting (String command) {
        connect();
        boolean exist = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);
            closeConnect();
            while (resultSet.next()){
                exist = resultSet.getBoolean(1);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
            closeConnect();
        }
        return exist;
    }

    @Override
    public void addFileArchiveToStorage(FileArchive fileArchive) {
        connect();
        String command = "INSERT INTO file_archive (name) VALUES ('" + fileArchive.getName() + "')";
        List<File> files = fileArchive.getFiles();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);
            closeConnect();
            if (files.size() != 0)
                for (File f:files) {
                addFileToStorage(fileArchive.getName(), f.getName(), f.getDate());
                }
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
            closeConnect();
        }
    }

    @Override
    public void addFileToStorage(String filearchivename, String filename, String creationdate) {
        connect();
        try {
            Statement statement = connection.createStatement();
            String command = "INSERT INTO file (name, creationdate, archive_id) VALUES ('" + filename + "', '" + creationdate + "'::timestamp, (SELECT archive_id FROM file_archive WHERE name = '" + filearchivename + "'))";
            statement.executeUpdate(command);
            closeConnect();
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
            closeConnect();
        }
    }

    @Override
    public boolean removeFileArchiveByNameInStorage(String filearchivename) {
        if (!isFileArchiveNameInStorage(filearchivename))
            return false;
        else {
            String command = "DELETE FROM file_archive WHERE name = '" + filearchivename + "'";
            return deleting(command);
        }
    }

    @Override
    public boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename) {
        if (!isFileArchiveNameInStorage(filearchivename))
            return false;
        else {
            if (!isFileNameInArchive(filearchivename, filename))
                return false;
            else {
                String command = "DELETE FROM file WHERE name = '" + filename + "'";
                return deleting(command);
            }
        }
    }

    private boolean deleting(String command) {
        connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);
            closeConnect();
            return true;
        } catch (SQLException e) {
            System.out.println("Something went wrong..." + e);
            closeConnect();
            return false;
        }
    }

    private void closeConnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong..." + e);
        }
    }
}
