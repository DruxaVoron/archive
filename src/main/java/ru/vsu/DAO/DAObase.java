package ru.vsu.DAO;

import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DAObase implements DAO<FileArchive> {

    private static DAObase instanse;
    private Connection connection;

    public DAObase() {
//        connect();
    }

    public static DAObase getInstanse() {
        if (instanse == null){
            instanse = new DAObase();
        }
        return instanse;
    }

//    private void connect(){
//        try {
//            String url = "jdbc:postgresql://localhost:5432/archive";
//            String user = "postgres";
//            String password = "1";
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (Exception e) {
//            System.out.println("Something went wrong..." + e);
//        }
//    }

    private void connect(){
        try {
            String url = "jdbc:postgresql://localhost:5432/archive";
            String user = "postgres";
            String password = "1";
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
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
        closeConnect();
        return list;
    }

    @Override
    public boolean isFileArchiveNameInStorage(String name) {
        connect();
        String command = "SELECT EXISTS (SELECT * FROM file_archive WHERE name = '" + name + "')";
        boolean exist = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()){
                exist = resultSet.getBoolean(1);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
        closeConnect();
        return exist;
    }

    @Override
    public void addFileArchiveToStorage(FileArchive fileArchive) {
        connect();
        String command = "INSERT INTO file_archive (name) VALUES ('" + fileArchive.getName() + "')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
        }
        closeConnect();
    }

    @Override
    public FileArchive getFileArchiveByNameInStorage(String filearchivename) {
        return null;
    }

    @Override
    public boolean removeFileArchiveByNameInStorage(String filearchivename) {
        return false;
    }

    @Override
    public boolean removeFileByNameInStorage(String filename) {
        return false;
    }

    @Override
    public boolean removeFileInFileArchiveByNameInStorage(String filearchivename, String filename) {
        return false;
    }

    private void closeConnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong..." + e);
        }
    }
}
