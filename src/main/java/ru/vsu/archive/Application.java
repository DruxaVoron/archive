package ru.vsu.archive;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vsu.archive.BusinessLogic.Logic;
import ru.vsu.archive.DAO.DAO;
import ru.vsu.archive.DAO.DAObase;
import ru.vsu.archive.Domain.FileArchive;
import ru.vsu.archive.UI.UI;
import ru.vsu.archive.UI.View;

public class Application {

    public void run(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UI ui = context.getBean("ui", UI.class);
        ui.show();
    }
}
