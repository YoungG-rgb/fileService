package kg.it.fileservice.service;

import kg.it.fileservice.models.FileModel;
import kg.it.fileservice.response.FileException;

import java.io.IOException;

public interface FileService {

    boolean save(FileModel file) throws FileException, IOException;

    boolean update(FileModel file) throws FileException, IOException;

    boolean delete(FileModel file) throws FileException, IOException;

    byte[] get(FileModel file) throws FileException, IOException;
}
