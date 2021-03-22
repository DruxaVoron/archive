package ru.vsu.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.vsu.archive")
@PropertySource("classpath:data.properties")
public class SpringConfiguration {

//    public DAObase daObase(){
//        return new DAObase();
//    }
//
//    public Logic logic(){
//        return new Logic();
//    }
//    public File file(){
//        return new File();
//    }

}
