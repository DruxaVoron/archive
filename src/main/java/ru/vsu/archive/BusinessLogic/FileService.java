package ru.vsu.archive.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.archive.DAO.FileRepository;
import ru.vsu.archive.Domain.File;

import java.util.List;

@Service
@Transactional
public class FileService {
    FileRepository repo;

    @Autowired
    public FileService(FileRepository repo) {
        this.repo = repo;
    }

    public void save(File File) {
        repo.save(File);
    }

    public List<File> listAll() {
        return (List<File>) repo.findAll();
    }

    public File get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public boolean exist(File file) {
        return repo.existsById(file.getId());
    }

}
