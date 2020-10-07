package ru.vsu;

import ru.vsu.BusinessLogic.Logic;
import ru.vsu.DAO.DAO;
import ru.vsu.DAO.DAOList;
import ru.vsu.Domain.FileArchive;
import ru.vsu.UI.UI;
import ru.vsu.UI.View;

public class Application {

    public void run(){
        DAO<FileArchive> fileArchiveDAO = DAOList.getInstanse();
        Logic logic = new Logic(fileArchiveDAO);
        View view = new View(logic);
        UI ui = new UI(view);
        ui.show();
    }
}
