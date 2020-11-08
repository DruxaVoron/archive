package ru.vsu.UI;


import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {


    private View view;
    private Scanner scanner = new Scanner(System.in);
//    private Logic logic = new Logic();

    public UI(View view) {
        this.view = view;
    }

    public void show(){
        do{
            System.out.println("MAIN MENU");
            System.out.println("1 - show all archives");
            System.out.println("2 - create new archive");
            System.out.println("3 - create new file in existing archive");
            System.out.println("4 - delete archive");
            System.out.println("5 - delete file");
            System.out.println("0 - exit");

            try {
                int commandcode = scanner.nextInt();
                switch (commandcode){
                    case 1:
                        view.showAll();
                        break;
                    case 2:
                        view.createFileArchive();
                        break;
                    case 3:
                        view.createFileInArchive();
                        break;
                    case 4:
                        view.deleteFileArchiveByName();
                        break;
                    case 5:
                        view.deleteFileInFileArchiveByName();
                        break;
                    case 0:
                        System.out.println("Goodbye");
                        return;
                }
            } catch (InputMismatchException e){
                System.out.println("Incorrect input. Please enter any number above");
//                break;
            }
        } while (scanner.hasNextInt());
    }



}
