package ru.vsu.archive;

import ru.vsu.archive.BusinessLogic.Logic;
import ru.vsu.archive.DAO.DAO;
import ru.vsu.archive.DAO.DAObase;
import ru.vsu.archive.Domain.FileArchive;
import ru.vsu.archive.UI.UI;
import ru.vsu.archive.UI.View;

public class Application {

    public void run(){
        DAO<FileArchive> fileArchiveDAO = DAObase.getInstanse();
        Logic logic = new Logic(fileArchiveDAO);
        View view = new View(logic);
        UI ui = new UI(view);
        ui.show();
    }
}
