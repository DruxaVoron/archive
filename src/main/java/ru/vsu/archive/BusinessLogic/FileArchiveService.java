package ru.vsu.archive.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.archive.DAO.FileArchiveRepository;
import ru.vsu.archive.Domain.FileArchive;

import java.util.List;

@Service
@Transactional
public class FileArchiveService {
    FileArchiveRepository repo;

    @Autowired
    public FileArchiveService(FileArchiveRepository repo) {
        this.repo = repo;
    }

    public void save(FileArchive FileArchive) {
        repo.save(FileArchive);
    }

    public List<FileArchive> listAll() {
        return (List<FileArchive>) repo.findAll();
    }

    public FileArchive get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public boolean exist(FileArchive fileArchive) {
        return repo.existsById(fileArchive.getId());
    }
    
}
