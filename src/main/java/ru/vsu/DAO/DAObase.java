package ru.vsu.DAO;

import ru.vsu.Domain.File;
import ru.vsu.Domain.FileArchive;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private Connection connect(){
        try {
            String url = "jdbc:postgresql://localhost:5432/archive";
            String user = "postgres";
            String password = "1";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e);
            return null;
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
                FileArchive fileArchive = new FileArchive(resultSet.getString(1));
                fileArchive.addFile(new File(resultSet.getString(2), resultSet.getString(3)));
                list.add(fileArchive);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed...");
        }
        closeConnect();
        return list;
    }

    @Override
    public boolean isFileArchiveNameInStorage(String name) {
        return false;
    }

    @Override
    public void addFileArchiveToStorage(FileArchive fileArchive) {

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

    public void closeConnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
