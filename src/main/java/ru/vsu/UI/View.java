package ru.vsu.UI;

import ru.vsu.BusinessLogic.Logic;
import ru.vsu.Domain.FileArchive;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);
    private Logic logic;

    public View(Logic logic) {
        this.logic = logic;
    }

    public void showAll(){
        System.out.println("All archives:");
        List<FileArchive> fileArchives = logic.getAll();
        fileArchives.forEach(System.out::println);
    }

    public void createFileArchive(){
        System.out.println("Enter the name of new archive");
        String name = scanner.nextLine();
        if (logic.createFileArchive(name))
            System.out.println("Created successful");
        else
            System.out.println("FileArchive with this name already exist");

    }

    public void createFileInArchive() {
        System.out.println("Enter the name of archive");
        String filearchivename = scanner.nextLine();
        System.out.println("Enter the name of new file");
        String filename = scanner.nextLine();
        if (logic.createFileinArchive(filearchivename, filename))
            System.out.println("Created successful");
        else{
            System.out.println("File with such name already exist in this FileArchive");
//            createFileArchive();
        }
    }

    public void deleteFileArchiveByName() {
        System.out.println("Enter the name of archive you want to delete");
        String filearchivename = scanner.nextLine();
        if (logic.deleteFileArchivebyName(filearchivename))
            System.out.println("Deleted successful");
        else {
            System.out.println("There is no FileArchive with such name.");
//            deleteFileArchiveByName();
        }
    }

    public void deleteFileInFileArchiveByName() {
        System.out.println("Enter the name of archive");
        String filearchivename = scanner.nextLine();
        System.out.println("Enter the name of file you want to delete");
        String filename = scanner.nextLine();
        if (logic.deleteFileinFileArchivebyName(filearchivename, filename))
            System.out.println("Deleted successful");
        else {
            System.out.println("There is no File or FileArchive with such name.");
//            deleteFileInFileArchiveByName();
        }
    }

}
