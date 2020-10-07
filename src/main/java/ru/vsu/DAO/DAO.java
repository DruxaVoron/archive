package ru.vsu.DAO;


import ru.vsu.Domain.FileArchive;

import java.util.List;

interface DAO<T> {

    List<T> getStorage();

//    здесь методы которые потом будут работать с двумя разными типами данных
}
