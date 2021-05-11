package ru.vsu.archive.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.archive.Domain.FileArchive;

@Repository
public interface FileArchiveRepository extends CrudRepository<FileArchive, Long> {

}
